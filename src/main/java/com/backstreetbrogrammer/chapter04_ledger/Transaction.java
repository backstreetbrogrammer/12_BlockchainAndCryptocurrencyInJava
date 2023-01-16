package com.backstreetbrogrammer.chapter04_ledger;

import java.time.LocalDateTime;

public final class Transaction {

    private final long transactionId;
    private final Student sender;
    private final Student receiver;
    private final double amount;
    private final LocalDateTime transactionTime;

    public Transaction(final long transactionId,
                       final Student sender,
                       final Student receiver,
                       final double amount,
                       final LocalDateTime transactionTime) {
        this.transactionId = transactionId;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.transactionTime = transactionTime;

        setStudentsCash();
    }

    public long getTransactionId() {
        return transactionId;
    }

    public Student getSender() {
        return sender;
    }

    public Student getReceiver() {
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

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
