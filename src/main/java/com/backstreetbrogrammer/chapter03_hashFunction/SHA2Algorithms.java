package com.backstreetbrogrammer.chapter03_hashFunction;

public enum SHA2Algorithms {

    SHA224("SHA-224"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512"),
    SHA512_224("SHA-512/224"),
    SHA512_256("SHA-512/256");

    private final String algorithm;

    SHA2Algorithms(final String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
