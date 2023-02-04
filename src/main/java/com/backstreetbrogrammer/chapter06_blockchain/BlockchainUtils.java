package com.backstreetbrogrammer.chapter06_blockchain;

import com.backstreetbrogrammer.chapter03_hashFunction.SHA2Algorithms;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class BlockchainUtils {

    private BlockchainUtils() {
    }

    public static final int DIFFICULTY = 1;
    public static final int REWARD = 10;
    public static final String GENESIS_PREV_HASH = StringUtils.leftPad("0", 64, '0');

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
}
