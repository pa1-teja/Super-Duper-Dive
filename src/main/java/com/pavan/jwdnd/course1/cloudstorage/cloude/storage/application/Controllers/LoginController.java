package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Controllers;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.UserInfoBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.HashService;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService, HashService hashService) {
        this.loginService = loginService;
        this.hashService = hashService;
    }

    private final HashService hashService;

    @GetMapping
    public String getLoginPage(@ModelAttribute("loginFormObject") UserInfoBean loginBean, Model model){
        return "login";
    }

    @PostMapping
    public String getLoginCreds(@ModelAttribute("loginFormObject") UserInfoBean loginBean, Model model) {
        System.out.println("Login Page POST MAPPING : username : " + loginBean.getUsername() + " /  password : " + loginBean.getPassword() );
        //TODO: hash the creds and store it in database.

        UserInfoBean infoBean = null;

        if (loginService.isUsernameAvailable(loginBean.getUsername())){
            model.addAttribute("isUserExists",true);
        } else{
            infoBean = loginService.getUserDetailsByUserName(loginBean.getUsername());
        }

        if (infoBean != null) {
            if (loginBean.getUsername().equals(infoBean.getUsername())
//                     && hashService.getHashedPassword(loginBean).equals(infoBean.getPassword())
            )
            {
                model.addAttribute("isInvalid", false);
                return "redirect:/home";
            } else {
                model.addAttribute("isInvalid", true);
            }
        }

        return "login";
    }
}
