package com.backstreetbrogrammer.chapter08_fullimplementation;

public class CryptoTransactionInput {

    // every input has an output - this is transaction id of the output
    private String transactionOutputId;

    // unspent transaction output
    private CryptoTransactionOutput UTXO;

    public CryptoTransactionInput(final String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }

    public String getTransactionOutputId() {
        return transactionOutputId;
    }

    public void setTransactionOutputId(final String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }

    public CryptoTransactionOutput getUTXO() {
        return UTXO;
    }

    public void setUTXO(final CryptoTransactionOutput UTXO) {
        this.UTXO = UTXO;
    }
}
