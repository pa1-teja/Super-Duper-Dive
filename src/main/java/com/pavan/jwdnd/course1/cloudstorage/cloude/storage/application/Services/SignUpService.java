package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services;

import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.UserInfoBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories.UserMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class SignUpService {

    private HashService hashService;
    private UserMapper userMapper;
    private EncryptionService encryptionService;

    public SignUpService(HashService hashService, UserMapper userMapper, EncryptionService encryptionService) {
        this.hashService = hashService;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUserDetailsByUserName(username) == null;
    }

    public int registerUser(UserInfoBean userInfoBean){
        hashService.getHashedPassword(userInfoBean);
        int insertRecordId = userMapper.insertNewUserRecord(userInfoBean);
        return insertRecordId;
    }
}
