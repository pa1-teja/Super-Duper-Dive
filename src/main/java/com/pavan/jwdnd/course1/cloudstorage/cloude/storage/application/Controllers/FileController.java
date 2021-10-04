package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Controllers;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.MessageConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.TabConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.FileBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.ResponseObject;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.FileService;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.LoginService;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.SignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final LoginService loginService;

    public FileController(FileService fileService, LoginService loginService) {
        this.fileService = fileService;
        this.loginService = loginService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload")MultipartFile multipartFile, Authentication authentication, Model model){
        int userId = loginService.getUserDetailsByUserName(authentication.getName()).getUserId();

        if (!multipartFile.isEmpty()) {
            ArrayList<ResponseObject> responseObjects = fileService.uploadFile(multipartFile, userId);

            for (ResponseObject object : responseObjects) {
                model.addAttribute(object.getFieldObjectName(), object.getMessage());
            }
        }else {
            model.addAttribute("error", MessageConstants.fileError_empty);
            model.addAttribute("tabAfterError", TabConstants.file);
        }

        return "result";
    }

    @RequestMapping(value = "/delete/{fieldId}")
    public String deleteFile(@PathVariable("fieldId") int fieldId, Model model){
        ArrayList<ResponseObject> responseObjects = fileService.deleteFile(fieldId);

        for (ResponseObject object : responseObjects) {
            model.addAttribute(object.getFieldObjectName(),object.getMessage());
        }
        return "result";
    }

    @GetMapping(value = "/view/{fieldId}")
    public ResponseEntity<Resource> viewFile(@PathVariable("fieldId") int fieldId, Authentication authentication){
       return fileService.viewFile(fieldId);
    }
}
