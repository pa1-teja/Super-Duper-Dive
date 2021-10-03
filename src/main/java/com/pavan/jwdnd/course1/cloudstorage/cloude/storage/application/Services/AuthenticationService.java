package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.UserInfoBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories.UserMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

    private final UserMapper userMapper;
    private final HashService hashService;

    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            try {
                String user = authentication.getName();
                String password = authentication.getCredentials().toString();

                UserInfoBean infoBean = userMapper.getUserDetailsByUserName(user);
                if (infoBean != null) {
                    String encSalt = infoBean.getSalt();
                    String hashedPassword = hashService.getHashedValue(password, encSalt);

                    if (infoBean.getPassword().equals(hashedPassword)) {
                        return new UsernamePasswordAuthenticationToken(user, password, new ArrayList<>());
                    }
                } else {
                    System.out.println(getClass().getName() + " : user :" + user + " / password : " + password);
                    throw new BadCredentialsException("User is not registered yet");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
