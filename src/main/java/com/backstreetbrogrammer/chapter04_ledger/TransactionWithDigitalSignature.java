package com.backstreetbrogrammer.chapter04_ledger;

import java.time.LocalDateTime;

public final class TransactionWithDigitalSignature {

    private final long transactionId;
    private final StudentWithDigitalSignature sender;
    private final StudentWithDigitalSignature receiver;
    private final double amount;
    private final LocalDateTime transactionTime;

    private byte[] digitalSignature;

    public TransactionWithDigitalSignature(final long transactionId,
                                           final StudentWithDigitalSignature sender,
                                           final StudentWithDigitalSignature receiver,
                                           final double amount,
                                           final LocalDateTime transactionTime) {
        this.transactionId = transactionId;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public StudentWithDigitalSignature getSender() {
        return sender;
    }

    public StudentWithDigitalSignature getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setStudentsCash() {
        final var senderCash = sender.getTotalCash() - amount;
        sender.setTotalCash(senderCash);

        final var receiverCash = receiver.getTotalCash() + amount;
        receiver.setTotalCash(receiverCash);
    }

    public byte[] getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(final byte[] digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    @Override
    public String toString() {
        return "TransactionWithDigitalSignature{" +
                "transactionId=" + transactionId +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
