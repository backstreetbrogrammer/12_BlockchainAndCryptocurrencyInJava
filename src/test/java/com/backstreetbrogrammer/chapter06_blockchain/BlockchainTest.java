package com.backstreetbrogrammer.chapter06_blockchain;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockchainTest {

    private final AtomicLong blockId = new AtomicLong();

    @Test
    void testBlockchain() {
        final var blockchain = new Blockchain();
        final var miner = new Miner();

        final var genesisBlock = new Block(blockId.getAndIncrement(), BlockchainUtils.GENESIS_PREV_HASH);
        miner.mine(genesisBlock, blockchain);

        final var block1 = new Block(blockId.getAndIncrement(), blockchain.getLastBlock().getHash());
        miner.mine(block1, blockchain);

        final var block2 = new Block(blockId.getAndIncrement(), blockchain.getLastBlock().getHash());
        miner.mine(block2, blockchain);

        assertEquals(3, blockchain.getSize());

        System.out.printf("%nBLOCKCHAIN~%n%s%n", blockchain);
        System.out.printf("Miner's reward: %.2f", miner.getReward());
    }
}
