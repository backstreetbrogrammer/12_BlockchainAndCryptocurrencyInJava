package com.backstreetbrogrammer.chapter08_fullimplementation;

import com.backstreetbrogrammer.chapter08_fullimplementation.blockchain.CryptoBlockchain;
import com.backstreetbrogrammer.chapter08_fullimplementation.utils.CryptoUtils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class CryptoTransaction {

    private String transactionId;
    private PublicKey sender;
    private PublicKey receiver;
    private double amount;
    private byte[] signature;

    private final List<CryptoTransactionInput> inputs = new ArrayList<>();
    private final List<CryptoTransactionOutput> outputs = new ArrayList<>();

    public CryptoTransaction(final PublicKey sender,
                             final PublicKey receiver,
                             final double amount,
                             final List<CryptoTransactionInput> inputs) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;

        if (inputs != null) {
            this.inputs.addAll(inputs);
        }
        calculateHash();
    }

    public CryptoTransaction(final PublicKey sender,
                             final PublicKey receiver,
                             final double amount,
                             final byte[] signature,
                             final List<CryptoTransactionInput> inputs) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.signature = signature;
        this.inputs.addAll(inputs);
        calculateHash();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public List<CryptoTransactionOutput> getOutputs() {
        return outputs;
    }

    public void addTransactionOutput(final CryptoTransactionOutput output) {
        if (output != null) {
            outputs.add(output);
        }
    }

    private void calculateHash() {
        transactionId = CryptoUtils.generateHash(getHashData());
    }

    public void generateSignature(final PrivateKey privateKey) {
        signature = CryptoUtils.createDigitalSignature(getHashData(), privateKey);
    }

    public boolean verifySignature() {
        return CryptoUtils.verifyDigitalSignature(getHashData(), signature, sender);
    }

    public boolean verifyTransaction() {
        if (!verifySignature()) {
            System.out.println("Invalid transaction as signature is invalid");
            return false;
        }

        // collect all UTXOs
        inputs.forEach(input -> input.setUTXO(CryptoBlockchain.UTXOs.get(input.getTransactionOutputId())));

        // send value to receiver
        outputs.add(new CryptoTransactionOutput(transactionId, receiver, amount));

        // send the leftover balance to sender
        outputs.add(new CryptoTransactionOutput(transactionId, sender, getInputsSum() - amount));

        // update UTXOs
        outputs.forEach(output -> CryptoBlockchain.UTXOs.put(output.getId(), output));
        inputs.stream()
              .filter(input -> (input != null) && (input.getUTXO() != null))
              .forEach(input -> CryptoBlockchain.UTXOs.remove(input.getUTXO().getId()));

        return true;
    }

    private String getHashData() {
        return String.format("%s%s%f",
                             sender.toString(),
                             receiver.toString(),
                             amount);
    }

    public double getInputsSum() {
        return inputs.stream()
                     .filter(input -> (input != null) && (input.getUTXO() != null))
                     .mapToDouble(input -> input.getUTXO().getAmount())
                     .sum();
    }

    @Override
    public String toString() {
        return "CryptoTransaction{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", inputs=" + inputs +
                ", outputs=" + outputs +
                '}';
    }
}
