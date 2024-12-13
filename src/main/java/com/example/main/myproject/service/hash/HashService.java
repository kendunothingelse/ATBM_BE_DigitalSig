package com.example.main.myproject.service.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class HashService {
    // Hashes a text message with the specified algorithm (SHA-256 or MD5)
    public String hashText(String text, String algorithm) throws NoSuchAlgorithmException {
        // Initialize MessageDigest with the specified algorithm
        MessageDigest digest = MessageDigest.getInstance(algorithm);

        // Compute the hash value for the text message
        byte[] hashBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));

        // Convert the byte array to a hexadecimal string and return
        return bytesToHex(hashBytes);
    }

    // Utility method to convert a byte array to a hexadecimal string
    private String bytesToHex(byte[] bytes) {
        try (Formatter formatter = new Formatter()) {
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        }
    }
}
