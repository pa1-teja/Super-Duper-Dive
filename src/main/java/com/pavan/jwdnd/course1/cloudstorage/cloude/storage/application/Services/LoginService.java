package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.UserInfoBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginService {


    private HashService hashService;
    private UserMapper userMapper;
    private EncryptionService encryptionService;

    public LoginService(HashService hashService, UserMapper userMapper, EncryptionService encryptionService) {
        this.hashService = hashService;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUserDetailsByUserName(username) == null;
    }

    public UserInfoBean getUserDetailsByUserName(String username){
        return userMapper.getUserDetailsByUserName(username);
    }
}
