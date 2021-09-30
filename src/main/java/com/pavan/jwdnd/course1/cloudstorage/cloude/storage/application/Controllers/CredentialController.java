package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Controllers;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.CredentialsBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.ResponseObject;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.CredentialsService;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.LoginService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private CredentialsService credentialsService;
    private LoginService loginService;

    public CredentialController(CredentialsService credentialsService, LoginService loginService) {
        this.credentialsService = credentialsService;
        this.loginService = loginService;
    }


    @PostMapping("/edit")
    public String addCredential(CredentialsBean credentialsBean, Authentication authentication, Model model, @ModelAttribute(value = "credentialId") String credentialId) {
        ArrayList<ResponseObject> list = new ArrayList<>();
        int userId = loginService.getUserDetailsByUserName(authentication.getName()).getUserId();
        if (credentialId.equals("")) {
           list = credentialsService.addCredential(credentialsBean, userId);
        } else {
           list = credentialsService.updateCredential(credentialsBean, Integer.valueOf(credentialId));
        }

        for (ResponseObject obj: list) {
            model.addAttribute(obj.getFieldObjectName(),obj.getMessage());
        }
        return "result";
    }

    @RequestMapping(value = "/delete/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId, Model model) {
        ArrayList<ResponseObject> list =  credentialsService.deleteCredential(credentialId, model);
        for (ResponseObject obj: list) {
            model.addAttribute(obj.getFieldObjectName(),obj.getMessage());
        }
        return "result";
    }
}
