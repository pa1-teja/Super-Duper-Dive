package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Controllers;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final EncryptionService encryptionService;
    private final FileService fileService;
    private final NotesService notesService;
    private final CredentialsService credentialsService;
    private final SignUpService signUpService;


    public HomeController(EncryptionService encryptionService, FileService fileService, NotesService notesService, CredentialsService credentialsService, SignUpService signUpService) {
        this.encryptionService = encryptionService;
        this.fileService = fileService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.signUpService = signUpService;
    }

    @GetMapping
    public String getHomePage(){
        return "home";
    }
}
