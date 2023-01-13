package com.backstreetbrogrammer.chapter03_hashFunction;

import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SHAAlgorithmsTest {

    private final String originalString = "b@ck$treetBrogr@mmer";

    private String bytesToHex(final byte[] hash) {
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

    @Test
    @DisplayName("Test basic SHA-256 algorithm is consistent")
    void testSHA256AlgorithmConsistency() throws NoSuchAlgorithmException {
        final var digest = MessageDigest.getInstance(SHA2Algorithms.SHA256.getAlgorithm());

        final var encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        assertEquals(32, encodedHash.length);

        final var hashValue = bytesToHex(encodedHash);
        assertEquals(64, hashValue.length());
        System.out.println(hashValue);

        for (int i = 0; i < 10; i++) {
            final var hashValueNew = bytesToHex(digest.digest(originalString.getBytes(StandardCharsets.UTF_8)));
            assertEquals(hashValue, hashValueNew);
        }
    }

    @Test
    @DisplayName("Test different ways to create SHA-256 algorithm hash value")
    void testDifferentWaysToCreateSHA256HashValue() throws NoSuchAlgorithmException {
        final var digest = MessageDigest.getInstance(SHA2Algorithms.SHA256.getAlgorithm());
        final var encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        final var hashValueJdk = bytesToHex(encodedHash);
        System.out.println(hashValueJdk);

        // using Google Guava
        final var hashValueGuava = Hashing.sha256()
                                          .hashString(originalString, StandardCharsets.UTF_8)
                                          .toString();
        assertEquals(hashValueJdk, hashValueGuava);

        // using Apache Commons Codecs
        final var hashValueCodecs = DigestUtils.sha256Hex(originalString);
        assertEquals(hashValueJdk, hashValueCodecs);
    }

    @Test
    @DisplayName("Test all SHA2 algorithms")
    void testAllSHA2Algorithms() throws NoSuchAlgorithmException {
        for (final var sha2Algorithm : SHA2Algorithms.values()) {
            final var digest = MessageDigest.getInstance(sha2Algorithm.getAlgorithm());
            final var encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            final var hashValue = bytesToHex(encodedHash);
            System.out.printf("SHA2 algorithm=%s%nHash value=%s%nHash length (bits)=%d%n", sha2Algorithm.getAlgorithm(),
                              hashValue,
                              hashValue.length() * 4);
            System.out.println("-------------------------------");
        }
    }

    @Test
    @DisplayName("Test create SHA-256 algorithm hash value for a file")
    void testSHA256HashValueForFile() throws NoSuchAlgorithmException, IOException {
        final var filePath = Path.of("src", "test", "resources", "dataFile.txt").toString();
        var digest = MessageDigest.getInstance(SHA2Algorithms.SHA256.getAlgorithm());

        try (final var dis = new DigestInputStream(new FileInputStream(filePath), digest)) {
            while (dis.read() != -1) {
                // do nothing - just to clear the data
            }
            digest = dis.getMessageDigest();
        }

        final var encodedHash = digest.digest();
        final var hashValue = bytesToHex(encodedHash);
        assertEquals(64, hashValue.length());
        System.out.println(hashValue);
    }

    @Test
    @DisplayName("Test basic SHA3-256 algorithm is consistent")
    void testSHA3_256AlgorithmConsistency() throws NoSuchAlgorithmException {
        final var digest = MessageDigest.getInstance(SHA3Algorithms.SHA3_256.getAlgorithm());

        final var encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        assertEquals(32, encodedHash.length);

        final var hashValue = bytesToHex(encodedHash);
        assertEquals(64, hashValue.length());
        System.out.println(hashValue);

        for (int i = 0; i < 10; i++) {
            final var hashValueNew = bytesToHex(digest.digest(originalString.getBytes(StandardCharsets.UTF_8)));
            assertEquals(hashValue, hashValueNew);
        }
    }

    @Test
    @DisplayName("Test different ways to create SHA3-256 algorithm hash value")
    void testDifferentWaysToCreateSHA3_256HashValue() throws NoSuchAlgorithmException {
        final var digest = MessageDigest.getInstance(SHA3Algorithms.SHA3_256.getAlgorithm());
        final var encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        final var hashValueJdk = bytesToHex(encodedHash);
        System.out.println(hashValueJdk);

        // using Apache Commons Codecs
        final var hashValueCodecs = DigestUtils.sha3_256Hex(originalString);
        assertEquals(hashValueJdk, hashValueCodecs);
    }

    @Test
    @DisplayName("Test all SHA3 algorithms")
    void testAllSHA3Algorithms() throws NoSuchAlgorithmException {
        for (final var sha3Algorithm : SHA3Algorithms.values()) {
            final var digest = MessageDigest.getInstance(sha3Algorithm.getAlgorithm());
            final var encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            final var hashValue = bytesToHex(encodedHash);
            System.out.printf("SHA3 algorithm=%s%nHash value=%s%nHash length (bits)=%d%n", sha3Algorithm.getAlgorithm(),
                              hashValue,
                              hashValue.length() * 4);
            System.out.println("-------------------------------");
        }
    }

    @Test
    @DisplayName("Test unknown SHA algorithm should throw Exception")
    void testUnknownSHAAlgorithmShouldThrowException() {
        final var exception = assertThrows(NoSuchAlgorithmException.class,
                                           () -> MessageDigest.getInstance("SHA4-256"));
        assertEquals(exception.getMessage(), "SHA4-256 MessageDigest not available");
    }

}
