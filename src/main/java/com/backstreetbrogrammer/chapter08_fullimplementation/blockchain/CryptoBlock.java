package com.backstreetbrogrammer.chapter08_fullimplementation.blockchain;

import com.backstreetbrogrammer.chapter08_fullimplementation.CryptoTransaction;
import com.backstreetbrogrammer.chapter08_fullimplementation.utils.CryptoUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

public class CryptoBlock {

    private final long id;
    private final String previousHash;
    private final List<CryptoTransaction> transactions = new LinkedList<>();
    private final LocalDateTime timeStamp;

    private int nonce;
    private String hash;

    public CryptoBlock(final long id, final String previousHash) {
        this.id = id;
        this.previousHash = previousHash;
        timeStamp = LocalDateTime.now();
        generateHash();
    }

    public String getHash() {
        return hash;
    }

    public void generateHash() {
        final var transactionsToString = new StringBuilder();
        transactions.forEach(transaction -> transactionsToString.append(transaction).append(System.lineSeparator()));
        final var dataToHash = String.format("%d%s%d%d%s", id,
                                             previousHash,
                                             timeStamp.toInstant(ZoneOffset.UTC).toEpochMilli(),
                                             nonce,
                                             transactionsToString);
        hash = CryptoUtils.generateHash(dataToHash);
    }

    public void incrementNonce() {
        nonce++;
    }

    public boolean addTransaction(final CryptoTransaction transaction) {
        if (transaction == null) return false;
        if (!CryptoUtils.GENESIS_PREV_HASH.equals(previousHash)) {
            if (!transaction.verifyTransaction()) {
                System.out.println("Invalid transaction!!!");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.printf("Valid transaction and added to the block~>%s%n", this);
        return true;
    }

    @Override
    public String toString() {
        return "CryptoBlock{" +
                "id=" + id +
                ", previousHash='" + previousHash + '\'' +
                ", transactions=" + transactions +
                ", timeStamp=" + timeStamp +
                ", nonce=" + nonce +
                ", hash='" + hash + '\'' +
                '}';
    }
}
