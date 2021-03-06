package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.UserInfoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class HashService {

    private final Logger logger = LoggerFactory.getLogger(HashService.class);

    public String getHashedValue(String data, String salt){
        byte[] hashedValue = null;

        KeySpec keySpec = new PBEKeySpec(data.toCharArray(), salt.getBytes(),5000,128);
        try{
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hashedValue = secretKeyFactory.generateSecret(keySpec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException exception){
            logger.error(exception.getMessage());
        }
        return Base64.getEncoder().encodeToString(hashedValue);
    }


    public void getHashedPassword(UserInfoBean userInfoBean){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        userInfoBean.setSalt(encodedSalt);
        String hashedPassword = getHashedValue(userInfoBean.getPassword(),encodedSalt);
        userInfoBean.setPassword(hashedPassword);
    }
}
