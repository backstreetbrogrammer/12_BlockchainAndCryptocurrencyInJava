package com.backstreetbrogrammer.chapter04_ledger;

import java.security.*;

public class DigitalSignatureUtil {

    private static final String RSA = "RSA";

    public static KeyPair generateKeyPair() {
        final var secureRandom = new SecureRandom();
        final KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] createDigitalSignature(final byte[] input,
                                                final PrivateKey Key) {
        try {
            final var signature = Signature.getInstance(SigningAlgorithms.SHA256withRSA.getAlgorithm());
            signature.initSign(Key);
            signature.update(input);
            return signature.sign();
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyDigitalSignature(final byte[] input,
                                                 final byte[] signatureToVerify,
                                                 final PublicKey key) {
        try {
            final var signature = Signature.getInstance(SigningAlgorithms.SHA256withRSA.getAlgorithm());
            signature.initVerify(key);
            signature.update(input);
            return signature.verify(signatureToVerify);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
