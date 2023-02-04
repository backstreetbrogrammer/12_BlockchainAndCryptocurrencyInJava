package com.backstreetbrogrammer.chapter08_fullimplementation;

import com.backstreetbrogrammer.chapter08_fullimplementation.blockchain.CryptoBlockchain;
import com.backstreetbrogrammer.chapter08_fullimplementation.utils.CryptoUtils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class CryptoWallet {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public CryptoWallet() {
        final var keyPair = CryptoUtils.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    public double calculateBalance() {
        return CryptoBlockchain.UTXOs.values()
                                     .stream()
                                     .filter(transactionOutput -> transactionOutput != null && transactionOutput.isMine(publicKey))
                                     .mapToDouble(CryptoTransactionOutput::getAmount)
                                     .sum();
    }

    public CryptoTransaction transferMoney(final PublicKey receiver, final double amount) {
        if (calculateBalance() < amount) {
            System.err.println("Not enough money in the wallet!!!");
            return null;
        }

        final List<CryptoTransactionInput> inputs = new ArrayList<>();
        CryptoBlockchain.UTXOs.values()
                              .stream()
                              .filter(transactionOutput -> transactionOutput != null && transactionOutput.isMine(publicKey))
                              .forEach(transactionOutput -> inputs.add(new CryptoTransactionInput(transactionOutput.getId())));
        final var newTransaction = new CryptoTransaction(publicKey, receiver, amount, inputs);
        newTransaction.generateSignature(privateKey);

        return newTransaction;
    }
}
