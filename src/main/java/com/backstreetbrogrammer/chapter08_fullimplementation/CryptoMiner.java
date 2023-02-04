package com.backstreetbrogrammer.chapter08_fullimplementation;

public class CryptoMiner {

    private double reward;

    public double getReward() {
        return reward;
    }

    /*public void mine(final Block block, final Blockchain blockchain) {
        while (!isGoldenHash(block)) {
            block.incrementNonce();
            block.generateHash();
        }

        System.out.printf("%s has just been mined...%n", block);
        System.out.printf("Hash=%s%n", block.getHash());

        blockchain.addBlock(block);
        reward += CryptoUtils.REWARD;
    }

    private boolean isGoldenHash(final Block block) {
        final var leadingZeros = StringUtils.leftPad("0", CryptoUtils.DIFFICULTY, '0');
        return block.getHash().substring(0, CryptoUtils.DIFFICULTY).equals(leadingZeros);
    }*/
}
