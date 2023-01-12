package com.backstreetbrogrammer.chapter03_hashFunction;

public enum SHA3Algorithms {

    SHA3_224("SHA3-224"),
    SHA3_256("SHA3-256"),
    SHA3_384("SHA3-384"),
    SHA3_512("SHA3-512");

    private final String algorithm;

    SHA3Algorithms(final String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
