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


    @GetMapping
    public String getLoginPage(@ModelAttribute("loginFormObject") UserInfoBean loginBean, Model model){
        return "login";
    }

}
