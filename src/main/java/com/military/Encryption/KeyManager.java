package com.military.Encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class KeyManager {
    private static final String KEY_FILE = "encryptionKey.key"; // 
    private static final String ALGORITHM = "AES";

    // Save the generated key to a file
    public static void saveKeyToFile(SecretKey key) throws Exception {
        File keyFile = new File(KEY_FILE);
        try (FileOutputStream fos = new FileOutputStream(keyFile)) {
            fos.write(key.getEncoded());
        }
    }

    // Load the key from the file
    public static SecretKey loadKeyFromFile() throws Exception {
        File keyFile = new File(KEY_FILE);
        if (! keyFile.exists()) {
            throw new IllegalStateException("Encryption key file not found!");
        }

        byte[] keyBytes = new byte[16]; // AES key size
        try (FileInputStream fis = new FileInputStream(keyFile)) {
            fis.read(keyBytes);
        }

        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    // Generate and save the key if it doesn't already exist
    public static void ensureKeyExists() throws Exception {
        File keyFile = new File(KEY_FILE);
        if (!keyFile.exists()) {
            SecretKey key = EncryptionUtility.generateKey();
            saveKeyToFile(key);
        }
    }
}
