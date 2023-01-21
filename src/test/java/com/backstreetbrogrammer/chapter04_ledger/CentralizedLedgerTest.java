package com.backstreetbrogrammer.chapter04_ledger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CentralizedLedgerTest {

    private final Path centralLedgerPath = Path.of("src", "test", "resources",
                                                   "central-ledger.txt");

    private final Student student1 = new Student("01", "Mahesh", "Sharma", 17);
    private final Student student2 = new Student("02", "Kaira", "Robles", 19);
    private final Student student3 = new Student("03", "Yaqzaan", "Ali", 18);
    private final Student student4 = new Student("04", "Priya", "Saxena", 16);

    private final List<Student> students = List.of(student1, student2, student3, student4);

    private final AtomicLong transactionId = new AtomicLong();

    @BeforeEach
    void setUp() throws IOException {
        if (centralLedgerPath.toFile().exists()) {
            Files.deleteIfExists(centralLedgerPath);
            assertFalse(centralLedgerPath.toFile().exists());
        }
    }

    @Test
    @DisplayName("Create transactions and write to the central ledger file")
    void createTransactionsAndWriteToCentralizedLedger() throws InterruptedException, IOException {
        transactAndWriteToLedger(student1, student4, 50D);
        transactAndWriteToLedger(student3, student2, 20D);
        transactAndWriteToLedger(student2, student1, 30D);
        transactAndWriteToLedger(student4, student3, 40D);
        transactAndWriteToLedger(student1, student2, 80D);

        students.forEach(System.out::println);
    }

    private void transactAndWriteToLedger(final Student sender,
                                          final Student receiver,
                                          final double amount) throws IOException, InterruptedException {
        final var transaction = new Transaction(transactionId.addAndGet(1L), sender, receiver, amount,
                                                LocalDateTime.now());
        Files.writeString(centralLedgerPath, transaction + System.lineSeparator(), StandardOpenOption.CREATE,
                          StandardOpenOption.APPEND);
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(10L, 50L));
    }

}
