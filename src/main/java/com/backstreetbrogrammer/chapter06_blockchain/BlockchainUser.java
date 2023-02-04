package com.backstreetbrogrammer.chapter06_blockchain;

import com.backstreetbrogrammer.chapter04_ledger.DigitalSignatureUtil;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.UUID;

public final class BlockchainUser {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final int age;

    private double totalBitcoins;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public BlockchainUser(final String firstName,
                          final String lastName,
                          final int age,
                          final double totalBitcoins) {
        this.userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        this.totalBitcoins = totalBitcoins;

        final var keyPair = DigitalSignatureUtil.generateKeyPair();
        if (keyPair != null) {
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }
    }

    public String getUserId() {
        return userId;
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

    public double getTotalBitcoins() {
        return totalBitcoins;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void depositBitcoins(final double bitcoins) {
        totalBitcoins += bitcoins;
    }

    public void withdrawBitcoins(final double bitcoins) {
        if (bitcoins > totalBitcoins) {
            System.out.printf("Not enough bitcoins to withdraw: %.2f", bitcoins);
            return;
        }
        totalBitcoins -= bitcoins;
    }

    @Override
    public String toString() {
        return "BlockchainUser{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", totalBitcoins=" + totalBitcoins +
                '}';
    }

    public BlockchainTransaction transact(final long transactionId,
                                          final BlockchainUser receiver,
                                          final double bitcoins) {
        final var transaction = new BlockchainTransaction(transactionId,
                                                          this,
                                                          receiver,
                                                          bitcoins,
                                                          LocalDateTime.now());
        final var digitalSignature = sign(transaction);
        transaction.setDigitalSignature(digitalSignature);
        return transaction;
    }

    private byte[] sign(final BlockchainTransaction transaction) {
        return DigitalSignatureUtil.createDigitalSignature(transaction.toString().getBytes(StandardCharsets.UTF_8),
                                                           privateKey);
    }
}
