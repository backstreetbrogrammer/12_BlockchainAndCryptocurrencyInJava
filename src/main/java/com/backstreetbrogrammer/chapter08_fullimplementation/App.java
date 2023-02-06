package com.backstreetbrogrammer.chapter08_fullimplementation;

import com.backstreetbrogrammer.chapter08_fullimplementation.blockchain.CryptoBlock;
import com.backstreetbrogrammer.chapter08_fullimplementation.blockchain.CryptoBlockchain;
import com.backstreetbrogrammer.chapter08_fullimplementation.utils.CryptoUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.concurrent.atomic.AtomicLong;

public class App {

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        // create wallets + blockchain + a single miner
        final var userA = new CryptoWallet();
        final var userB = new CryptoWallet();
        final var lender = new CryptoWallet();

        final var chain = new CryptoBlockchain();
        final var miner = new CryptoMiner();

        // create genesis transaction that sends 500 coins to userA
        final var genesisTransaction = new CryptoTransaction(lender.getPublicKey(),
                                                             userA.getPublicKey(),
                                                             500D,
                                                             null);
        genesisTransaction.generateSignature(lender.getPrivateKey());
        genesisTransaction.setTransactionId("0");
        genesisTransaction.addTransactionOutput(new CryptoTransactionOutput(genesisTransaction.getTransactionId(),
                                                                            genesisTransaction.getReceiver(),
                                                                            genesisTransaction.getAmount()));
        final var getGenesisTransactionOutputs = genesisTransaction.getOutputs().get(0);
        CryptoBlockchain.UTXOs.put(getGenesisTransactionOutputs.getId(), getGenesisTransactionOutputs);

        System.out.println("\nConstructing the genesis block...");

        final var idGenerator = new AtomicLong();
        final var genesisBlock = new CryptoBlock(idGenerator.getAndIncrement(), CryptoUtils.GENESIS_PREV_HASH);
        genesisBlock.addTransaction(genesisTransaction);
        miner.mine(genesisBlock, chain);

        // Block 1
        final var block1 = new CryptoBlock(idGenerator.getAndIncrement(), genesisBlock.getHash());
        System.out.println("\nuserA's balance is:" + userA.calculateBalance());
        System.out.println("userA tries to send 120 coins to userB...");
        block1.addTransaction(userA.transferMoney(userB.getPublicKey(), 120D));
        miner.mine(block1, chain);
        System.out.printf("userA's balance is: %.2f %n", userA.calculateBalance());
        System.out.printf("userB's balance is: %.2f %n", userB.calculateBalance());

        // Block 2
        final var block2 = new CryptoBlock(idGenerator.getAndIncrement(), block1.getHash());
        System.out.println("\nuserA tries to send more funds (600 coins) to userB...");
        block2.addTransaction(userA.transferMoney(userB.getPublicKey(), 600D));
        miner.mine(block2, chain);
        System.out.printf("userA's balance is: %.2f %n", userA.calculateBalance());
        System.out.printf("userB's balance is: %.2f %n", userB.calculateBalance());

        // Block 3
        final var block3 = new CryptoBlock(idGenerator.getAndIncrement(), block2.getHash());
        System.out.println("\nuserB tries to send 110 coins to userA...");
        block3.addTransaction(userB.transferMoney(userA.getPublicKey(), 110D));
        miner.mine(block3, chain);
        System.out.printf("userA's balance is: %.2f %n", userA.calculateBalance());
        System.out.printf("userB's balance is: %.2f %n", userB.calculateBalance());

        System.out.printf("%nMiner's reward: %.2f%n", miner.getReward());
    }
}
