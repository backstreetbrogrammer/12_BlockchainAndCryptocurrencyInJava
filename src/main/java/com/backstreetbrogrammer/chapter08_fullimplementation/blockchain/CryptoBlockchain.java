package com.backstreetbrogrammer.chapter08_fullimplementation.blockchain;

import com.backstreetbrogrammer.chapter08_fullimplementation.CryptoTransactionOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CryptoBlockchain {

    public static final List<CryptoBlock> blockChain = new ArrayList<>();

    public static final Map<String, CryptoTransactionOutput> UTXOs = new HashMap<>();

    public void addBlock(final CryptoBlock block) {
        blockChain.add(block);
    }

    public int size() {
        return blockChain.size();
    }

    @Override
    public String toString() {
        final var stringBuilder = new StringBuilder();
        blockChain.forEach(block -> stringBuilder.append(block).append(System.lineSeparator()));
        return stringBuilder.toString();
    }
}
