package com.backstreetbrogrammer.chapter04_ledger;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;

public final class StudentWithDigitalSignature {
    private final String studentId;
    private final String firstName;
    private final String lastName;
    private final int age;

    private double totalCash = 200D;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public StudentWithDigitalSignature(final String studentId,
                                       final String firstName,
                                       final String lastName,
                                       final int age) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        final var keyPair = DigitalSignatureUtil.generateKeyPair();
        if (keyPair != null) {
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(final double totalCash) {
        this.totalCash = totalCash;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", totalCash=" + totalCash +
                '}';
    }

    public TransactionWithDigitalSignature transact(final long transactionId,
                                                    final StudentWithDigitalSignature receiver,
                                                    final double amount) {
        final var transaction = new TransactionWithDigitalSignature(transactionId,
                                                                    this,
                                                                    receiver,
                                                                    amount,
                                                                    LocalDateTime.now());
        final var digitalSignature = sign(transaction);
        transaction.setDigitalSignature(digitalSignature);
        return transaction;
    }

    private byte[] sign(final TransactionWithDigitalSignature transaction) {
        return DigitalSignatureUtil.createDigitalSignature(transaction.toString().getBytes(StandardCharsets.UTF_8),
                                                           privateKey);
    }
}
