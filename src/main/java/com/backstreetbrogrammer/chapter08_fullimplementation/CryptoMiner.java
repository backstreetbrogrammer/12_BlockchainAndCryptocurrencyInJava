package com.backstreetbrogrammer.chapter08_fullimplementation;

import com.backstreetbrogrammer.chapter08_fullimplementation.blockchain.CryptoBlock;
import com.backstreetbrogrammer.chapter08_fullimplementation.blockchain.CryptoBlockchain;
import com.backstreetbrogrammer.chapter08_fullimplementation.utils.CryptoUtils;
import org.apache.commons.lang3.StringUtils;

public class CryptoMiner {

    private double reward;

    public double getReward() {
        return reward;
    }

    public void mine(final CryptoBlock block, final CryptoBlockchain blockchain) {
        while (!isGoldenHash(block)) {
            block.incrementNonce();
            block.generateHash();
        }

        System.out.printf("%s has just been mined...%n", block);
        System.out.printf("Hash=%s%n", block.getHash());

        blockchain.addBlock(block);
        reward += CryptoUtils.MINER_REWARD;
    }

    private boolean isGoldenHash(final CryptoBlock block) {
        final var leadingZeros = StringUtils.leftPad("0", CryptoUtils.DIFFICULTY, '0');
        return block.getHash().substring(0, CryptoUtils.DIFFICULTY).equals(leadingZeros);
    }
}
