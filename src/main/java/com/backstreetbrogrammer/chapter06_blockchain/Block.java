package com.backstreetbrogrammer.chapter06_blockchain;

import com.backstreetbrogrammer.chapter04_ledger.DigitalSignatureUtil;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

public final class Block {

    private final long id;
    private final String previousHash;
    private final List<BlockchainTransaction> transactions = new LinkedList<>();
    private final LocalDateTime timeStamp;

    private int nonce;
    private String hash;

    public Block(final long id, final String previousHash) {
        this.id = id;
        this.previousHash = previousHash;
        timeStamp = LocalDateTime.now();
        generateHash();
    }

    public boolean addTransaction(final BlockchainTransaction transaction) {
        boolean isAdded = false;
        if (verifyTransaction(transaction)) {
            isAdded = transactions.add(transaction);
            generateHash();
        }
        return isAdded;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void incrementNonce() {
        nonce++;
    }

    public void generateHash() {
        final var transactionsToString = new StringBuilder();
        transactions.forEach(transaction -> transactionsToString.append(transaction).append(System.lineSeparator()));
        String dataToHash = String.format("%d%s%d%d%s", id,
                                          previousHash,
                                          timeStamp.toInstant(ZoneOffset.UTC).toEpochMilli(),
                                          nonce,
                                          transactionsToString);
        hash = BlockchainUtils.generateHash(dataToHash);
    }

    private boolean verifyTransaction(final BlockchainTransaction transaction) {
        final var input = transaction.toString().getBytes(StandardCharsets.UTF_8);
        final var signatureToVerify = transaction.getDigitalSignature();
        final var publicKey = transaction.getSender().getPublicKey();

        return DigitalSignatureUtil.verifyDigitalSignature(input, signatureToVerify, publicKey);
    }
}
