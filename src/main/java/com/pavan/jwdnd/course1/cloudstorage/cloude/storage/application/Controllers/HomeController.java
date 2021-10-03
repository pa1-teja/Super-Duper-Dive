package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Controllers;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.CredentialsBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.FileBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.FormObjects.CredentialFormObject;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.FormObjects.NotesFormObject;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final EncryptionService encryptionService;
    private final FileService fileService;
    private final NotesService notesService;
    private final CredentialsService credentialsService;
    private final SignUpService signUpService;
    private final LoginService loginService;


    public HomeController(EncryptionService encryptionService, FileService fileService, NotesService notesService, CredentialsService credentialsService, SignUpService signUpService, LoginService loginService) {
        this.encryptionService = encryptionService;
        this.fileService = fileService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.signUpService = signUpService;
        this.loginService = loginService;
    }

    @GetMapping
    public String getHomePage(@ModelAttribute("NotesFormObject") NotesFormObject notesFormObject,
                              @ModelAttribute("credentialForm") CredentialFormObject credentialFormObject,
                              @ModelAttribute(value="tabOption") String tabOption,
                              FileBean fileBean,
                              Authentication authentication,
                              Model model){

        int userId = loginService.getUserDetailsByUserName(authentication.getName()).getUserId();
        model.addAttribute("fileList", fileService.getAllFiles(userId));
        model.addAttribute("noteList", notesService.getAllNotes(userId));
        model.addAttribute("credentialList", credentialsService.getAllUserCredentials(userId));
        model.addAttribute("activeTab",tabOption);
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }
}
