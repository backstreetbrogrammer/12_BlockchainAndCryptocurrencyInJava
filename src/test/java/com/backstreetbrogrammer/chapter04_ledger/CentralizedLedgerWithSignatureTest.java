package com.backstreetbrogrammer.chapter04_ledger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CentralizedLedgerWithSignatureTest {

    private final Path centralLedgerPath = Path.of("src", "test", "resources",
                                                   "central-ledger-signature.txt");

    private final StudentWithDigitalSignature student1
            = new StudentWithDigitalSignature("01", "Mahesh", "Sharma", 17);
    private final StudentWithDigitalSignature student2
            = new StudentWithDigitalSignature("02", "Kaira", "Robles", 19);
    private final StudentWithDigitalSignature student3
            = new StudentWithDigitalSignature("03", "Yaqzaan", "Ali", 18);
    private final StudentWithDigitalSignature student4
            = new StudentWithDigitalSignature("04", "Priya", "Saxena", 16);

    private final List<StudentWithDigitalSignature> students = List.of(student1, student2, student3, student4);

    @BeforeEach
    void setUp() throws IOException {
        if (centralLedgerPath.toFile().exists()) {
            Files.deleteIfExists(centralLedgerPath);
            assertFalse(centralLedgerPath.toFile().exists());
        }
    }

    @Test
    @DisplayName("Create transactions and verify it using digital signature before writing to the central ledger")
    void createTransactionsAndVerifyUsingSignatureAndWriteToCentralizedLedger() throws InterruptedException, IOException {
        final var transaction1 = student1.transact(student4, 50D);
        if (verifyTransaction(transaction1)) {
            transaction1.setStudentsCash();
            Files.writeString(centralLedgerPath, transaction1 + System.lineSeparator(), StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        TimeUnit.MILLISECONDS.sleep(10L);

        final var transaction2 = student3.transact(student2, 20D);
        if (verifyTransaction(transaction2)) {
            transaction2.setStudentsCash();
            Files.writeString(centralLedgerPath, transaction2 + System.lineSeparator(), StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        TimeUnit.MILLISECONDS.sleep(30L);

        final var transaction3 = student2.transact(student1, 30D);
        if (verifyTransaction(transaction3)) {
            transaction3.setStudentsCash();
            Files.writeString(centralLedgerPath, transaction3 + System.lineSeparator(), StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        TimeUnit.MILLISECONDS.sleep(20L);

        final var transaction4 = student4.transact(student3, 40D);
        if (verifyTransaction(transaction4)) {
            transaction4.setStudentsCash();
            Files.writeString(centralLedgerPath, transaction4 + System.lineSeparator(), StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        TimeUnit.MILLISECONDS.sleep(40L);

        final var transaction5 = student1.transact(student2, 80D);
        if (verifyTransaction(transaction5)) {
            transaction5.setStudentsCash();
            Files.writeString(centralLedgerPath, transaction5 + System.lineSeparator(), StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }

        students.forEach(System.out::println);
    }

    private boolean verifyTransaction(final TransactionWithDigitalSignature transaction) {
        final var input = transaction.toString().getBytes(StandardCharsets.UTF_8);
        final var signatureToVerify = transaction.getDigitalSignature();
        final var publicKey = transaction.getSender().getPublicKey();

        return DigitalSignatureUtil.verifyDigitalSignature(input, signatureToVerify, publicKey);
    }

}
