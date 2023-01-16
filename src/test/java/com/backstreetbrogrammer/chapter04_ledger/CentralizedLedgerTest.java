package com.backstreetbrogrammer.chapter04_ledger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CentralizedLedgerTest {

    private final Path centralLedgerPath = Path.of("src", "test", "resources",
                                                   "central-ledger.txt");

    private final Student student1 = new Student("01", "Mahesh", "Sharma", 17);
    private final Student student2 = new Student("02", "Kaira", "Robles", 19);
    private final Student student3 = new Student("03", "Yaqzaan", "Ali", 18);
    private final Student student4 = new Student("04", "Priya", "Saxena", 16);

    private final List<Student> students = List.of(student1, student2, student3, student4);

    @BeforeEach
    void setUp() throws IOException {
        if (centralLedgerPath.toFile().exists()) {
            Files.deleteIfExists(centralLedgerPath);
            assertFalse(centralLedgerPath.toFile().exists());
        }
    }

    @Test
    void createTransactionsLedgerOnDay1() throws InterruptedException, IOException {
        final var transaction1 = new Transaction(1L, student1, student4, 50D, LocalDateTime.now());
        Files.writeString(centralLedgerPath, transaction1 + System.lineSeparator(), StandardOpenOption.CREATE,
                          StandardOpenOption.APPEND);
        TimeUnit.MILLISECONDS.sleep(10L);

        final var transaction2 = new Transaction(2L, student3, student2, 20D, LocalDateTime.now());
        Files.writeString(centralLedgerPath, transaction2 + System.lineSeparator(), StandardOpenOption.CREATE,
                          StandardOpenOption.APPEND);
        TimeUnit.MILLISECONDS.sleep(30L);

        final var transaction3 = new Transaction(3L, student2, student1, 30D, LocalDateTime.now());
        Files.writeString(centralLedgerPath, transaction3 + System.lineSeparator(), StandardOpenOption.CREATE,
                          StandardOpenOption.APPEND);
        TimeUnit.MILLISECONDS.sleep(20L);

        final var transaction4 = new Transaction(4L, student4, student3, 40D, LocalDateTime.now());
        Files.writeString(centralLedgerPath, transaction4 + System.lineSeparator(), StandardOpenOption.CREATE,
                          StandardOpenOption.APPEND);
        TimeUnit.MILLISECONDS.sleep(40L);

        final var transaction5 = new Transaction(5L, student1, student2, 80D, LocalDateTime.now());
        Files.writeString(centralLedgerPath, transaction5 + System.lineSeparator(), StandardOpenOption.CREATE,
                          StandardOpenOption.APPEND);

        students.forEach(System.out::println);
    }


}
