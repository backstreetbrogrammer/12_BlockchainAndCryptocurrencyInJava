package com.backstreetbrogrammer.chapter08_fullimplementation.utils;

import com.backstreetbrogrammer.chapter03_hashFunction.SHA2Algorithms;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class CryptoUtils {

    private CryptoUtils() {
    }

    public static final int DIFFICULTY = 1;
    public static final String GENESIS_PREV_HASH = StringUtils.leftPad("0", 64, '0');
    public static final double MINER_REWARD = 6.25D;

    public static String generateHash(final String data) {
        try {
            final var digest = MessageDigest.getInstance(SHA2Algorithms.SHA256.getAlgorithm());
            final var encodedHash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (final NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(final byte[] hash) {
        final var hexString = new StringBuilder(2 * hash.length);
        for (final byte b : hash) {
            final var i = 0xff & b; // convert signed 'byte' to unsigned 'int'
            final var hex = Integer.toHexString(i);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static KeyPair generateKeyPair() {
        try {
            final var keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            final var params = new ECGenParameterSpec("prime256v1");
            keyPairGenerator.initialize(params);
            return keyPairGenerator.generateKeyPair();
        } catch (final NoSuchProviderException | NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] createDigitalSignature(final String input,
                                                final PrivateKey privateKey) {
        try {
            final var signature = Signature.getInstance("ECDSA", "BC");
            signature.initSign(privateKey);
            signature.update(input.getBytes(StandardCharsets.UTF_8));
            return signature.sign();
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyDigitalSignature(final String input,
                                                 final byte[] signatureToVerify,
                                                 final PublicKey publicKey) {
        try {
            final var signature = Signature.getInstance("ECDSA", "BC");
            signature.initVerify(publicKey);
            signature.update(input.getBytes(StandardCharsets.UTF_8));
            return signature.verify(signatureToVerify);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
