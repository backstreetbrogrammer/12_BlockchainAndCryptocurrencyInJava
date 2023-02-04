package com.backstreetbrogrammer.chapter08_fullimplementation;

import com.backstreetbrogrammer.chapter08_fullimplementation.utils.CryptoUtils;

import java.security.PublicKey;

public class CryptoTransactionOutput {

    private String id;
    private String parentTransactionId;
    private PublicKey receiver;
    private double amount;

    public CryptoTransactionOutput(final String parentTransactionId, final PublicKey receiver, final double amount) {
        this.parentTransactionId = parentTransactionId;
        this.receiver = receiver;
        this.amount = amount;
        generateId();
    }

    private void generateId() {
        id = CryptoUtils.generateHash(String.format("%s%f%s", receiver.toString(), amount, parentTransactionId));
    }

    public boolean isMine(final PublicKey publicKey) {
        return publicKey == receiver;
    }

    public String getId() {
        return id;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(final String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public void setReceiver(final PublicKey receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }
}
