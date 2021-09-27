package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Controllers;

import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.MessageConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.UserInfoBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.SignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final SignUpService signUpService;
    private boolean isUsrNameAvailable = false;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping
    public String getSignUpPage(@ModelAttribute("signUpFormObject") UserInfoBean userInfoBean, Model model){
        return "signup";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("signUpFormObject") UserInfoBean userInfoBean, Model model){
//        System.out.println("POST MAPPING: SIGNUP PAGE: \n first name :  " + userInfoBean.getFirstName() + "\n last name :" + userInfoBean.getLastName() + "\n username : " + userInfoBean.getUsername() + "\n password : " + userInfoBean.getPassword());

         if (signUpService.isUsernameAvailable(userInfoBean.getUsername())){
             int status = signUpService.registerUser(userInfoBean);
             if (status == 1) {model.addAttribute("signUpSuccess", true);}
             else{
                 model.addAttribute("signUpError", MessageConstants.signupError_other);
             }
         } else {
             model.addAttribute("signUpError", MessageConstants.signupError_userExists);
         }
        return "signup";
    }


    public SignUpService getSignUpService() {
        return signUpService;
    }
}
