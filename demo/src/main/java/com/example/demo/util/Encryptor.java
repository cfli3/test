package com.example.demo.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Encryptor {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey()
    {
        MessageDigest sha = null;
        String keyString = "thisisa128bitkey";

        try {
            key = keyString.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // Encrypt Message string
    public String encryptMessage(String message)
        throws InvalidKeyException, NoSuchPaddingException,
                NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException,
                UnsupportedEncodingException{

        setKey();

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedMessage = cipher.doFinal(message.getBytes("UTF-8"));
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessage);
        //System.out.println("Encoded message: " + encodedMessage);

        return encodedMessage;
    }


    //public String decryptMessage(byte[] encryptedMessage, byte[] keyBytes)
    public String decryptMessage(String encryptedMessage)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {

        setKey();

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
    }
}
