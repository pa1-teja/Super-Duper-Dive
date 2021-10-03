package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class EncryptionService {
    private final Logger logger = LoggerFactory.getLogger(EncryptionService.class);


    public String encryptValue(String data, String key){
        byte[] encryptValue = null;

        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptValue = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException exception){
            logger.error(exception.getMessage());
        }
        return Base64.getEncoder().encodeToString(encryptValue);
    }

    public String decryptValue(String data, String key){
        byte[] decryptedValue = null;

        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            decryptedValue = cipher.doFinal(Base64.getDecoder().decode(data));
        }catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException exception){
            logger.error(exception.getMessage());
        }
        return new String(decryptedValue);
    }
}
