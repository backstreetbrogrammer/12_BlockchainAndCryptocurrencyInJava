package com.backstreetbrogrammer.chapter04_ledger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

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

    private final AtomicLong transactionId = new AtomicLong();

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
        transactAndWriteToLedger(student1, student4, 50D);
        transactAndWriteToLedger(student3, student2, 20D);
        transactAndWriteToLedger(student2, student1, 30D);
        transactAndWriteToLedger(student4, student3, 40D);
        transactAndWriteToLedger(student1, student2, 80D);

        students.forEach(System.out::println);
    }

    @Test
    @DisplayName("Create fake transactions which fails digital signature verification and thus not written to the " +
            "central ledger")
    void createFakeTransactionAndVerifyItAsInvalid() {
        final var fakeTransaction = new TransactionWithDigitalSignature(transactionId.addAndGet(1L),
                                                                        student2,
                                                                        student1,
                                                                        100D,
                                                                        LocalDateTime.now());
        final var keyPair = DigitalSignatureUtil.generateKeyPair();
        PrivateKey privateKey = null;
        if (keyPair != null) {
            privateKey = keyPair.getPrivate();
        }
        final var digitalSignature
                = DigitalSignatureUtil.createDigitalSignature(fakeTransaction.toString().getBytes(StandardCharsets.UTF_8),
                                                              privateKey);
        fakeTransaction.setDigitalSignature(digitalSignature);
        assertFalse(verifyTransaction(fakeTransaction));
    }

    private void transactAndWriteToLedger(final StudentWithDigitalSignature sender,
                                          final StudentWithDigitalSignature receiver,
                                          final double amount) throws IOException, InterruptedException {
        final var transaction = sender.transact(transactionId.addAndGet(1L), receiver, amount);
        if (verifyTransaction(transaction)) {
            transaction.setStudentsCash();
            Files.writeString(centralLedgerPath, transaction + System.lineSeparator(), StandardOpenOption.CREATE,
                              StandardOpenOption.APPEND);
        }
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(10L, 50L));
    }

    private boolean verifyTransaction(final TransactionWithDigitalSignature transaction) {
        final var input = transaction.toString().getBytes(StandardCharsets.UTF_8);
        final var signatureToVerify = transaction.getDigitalSignature();
        final var publicKey = transaction.getSender().getPublicKey();

        return DigitalSignatureUtil.verifyDigitalSignature(input, signatureToVerify, publicKey);
    }

}
