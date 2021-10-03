package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.UserInfoBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginService {


    private final HashService hashService;
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;

    public LoginService(HashService hashService, UserMapper userMapper, EncryptionService encryptionService) {
        this.hashService = hashService;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public UserInfoBean getUserDetailsByUserName(String username){
        return userMapper.getUserDetailsByUserName(username);
    }
}
