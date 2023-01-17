package com.backstreetbrogrammer.chapter04_ledger;

public enum SigningAlgorithms {

    SHA256withRSA("SHA256withRSA"),
    SHA1withRSA("SHA1withRSA"),
    SHA1withDSA("SHA1withDSA"),
    MD5withRSA("MD5withRSA");

    private final String algorithm;

    SigningAlgorithms(final String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
