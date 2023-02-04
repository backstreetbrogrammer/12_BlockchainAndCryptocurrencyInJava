package com.backstreetbrogrammer.chapter06_blockchain;

import java.time.LocalDateTime;

public final class BlockchainTransaction {

    private final long transactionId;
    private final BlockchainUser sender;
    private final BlockchainUser receiver;
    private final double bitcoins;
    private final LocalDateTime transactionTime;

    private byte[] digitalSignature;

    public BlockchainTransaction(final long transactionId,
                                 final BlockchainUser sender,
                                 final BlockchainUser receiver,
                                 final double bitcoins,
                                 final LocalDateTime transactionTime) {
        this.transactionId = transactionId;
        this.sender = sender;
        this.receiver = receiver;
        this.bitcoins = bitcoins;
        this.transactionTime = transactionTime;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public BlockchainUser getSender() {
        return sender;
    }

    public BlockchainUser getReceiver() {
        return receiver;
    }

    public double getBitcoins() {
        return bitcoins;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setBlockchainUserBitcoins() {
        sender.withdrawBitcoins(bitcoins);
        receiver.depositBitcoins(bitcoins);
    }

    public byte[] getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(final byte[] digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    @Override
    public String toString() {
        return "BlockchainTransaction{" +
                "transactionId=" + transactionId +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", bitcoins=" + bitcoins +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
