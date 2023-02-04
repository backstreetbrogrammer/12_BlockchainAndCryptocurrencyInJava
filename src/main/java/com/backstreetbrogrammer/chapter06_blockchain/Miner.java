package com.backstreetbrogrammer.chapter06_blockchain;

import org.apache.commons.lang3.StringUtils;

public class Miner {

    private double reward;

    public double getReward() {
        return reward;
    }

    public void mine(final Block block, final Blockchain blockchain) {
        while (!isGoldenHash(block)) {
            block.incrementNonce();
            block.generateHash();
        }

        System.out.printf("%s has just been mined...%n", block);
        System.out.printf("Hash=%s%n", block.getHash());

        blockchain.addBlock(block);
        reward += BlockchainUtils.REWARD;
    }

    private boolean isGoldenHash(final Block block) {
        final var leadingZeros = StringUtils.leftPad("0", BlockchainUtils.DIFFICULTY, '0');
        return block.getHash().substring(0, BlockchainUtils.DIFFICULTY).equals(leadingZeros);
    }
}
