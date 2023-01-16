package com.backstreetbrogrammer.chapter04_ledger;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CentralizedLedgerTest {

    @Test
    void createTransactionsLedgerOnDay1() throws InterruptedException {
        final var student1 = new Student("01", "Mahesh", "Sharma", 17);
        final var student2 = new Student("02", "Kaira", "Robles", 19);
        final var student3 = new Student("03", "Yaqzaan", "Ali", 18);
        final var student4 = new Student("04", "Priya", "Saxena", 16);

        final var students = List.of(student1, student2, student3, student4);

        final var transaction1 = new Transaction(1L, student1, student4, 50D, LocalDateTime.now());
        TimeUnit.MILLISECONDS.sleep(50L);

        final var transaction2 = new Transaction(2L, student3, student2, 20D, LocalDateTime.now());
        TimeUnit.MILLISECONDS.sleep(20L);

        final var transaction3 = new Transaction(3L, student2, student1, 30D, LocalDateTime.now());
        TimeUnit.MILLISECONDS.sleep(30L);

        final var transaction4 = new Transaction(4L, student4, student3, 40D, LocalDateTime.now());
        TimeUnit.MILLISECONDS.sleep(40L);

        final var transaction5 = new Transaction(5L, student1, student2, 80D, LocalDateTime.now());

        students.forEach(System.out::println);

    }
}