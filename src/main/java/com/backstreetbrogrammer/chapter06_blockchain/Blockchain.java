package com.backstreetbrogrammer.chapter06_blockchain;

import com.google.common.collect.ImmutableList;

import java.util.LinkedList;
import java.util.List;

public final class Blockchain {
    private final LinkedList<Block> blockChain = new LinkedList<>();

    public void addBlock(final Block block) {
        blockChain.add(block);
    }

    public List<Block> getBlockChain() {
        return ImmutableList.copyOf(blockChain);
    }

    public int getSize() {
        return blockChain.size();
    }

    public Block getLastBlock() {
        return blockChain.getLast();
    }

    @Override
    public String toString() {
        final var stringBuilder = new StringBuilder();
        blockChain.forEach(block -> {
            stringBuilder.append(block).append(System.lineSeparator());
        });
        return stringBuilder.toString();
    }


}
