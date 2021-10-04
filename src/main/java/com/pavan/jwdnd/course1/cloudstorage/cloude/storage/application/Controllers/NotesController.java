package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Controllers;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.NotesBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.FormObjects.NotesFormObject;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.ResponseObject;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.LoginService;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/note")
public class NotesController {

    private final NotesService notesService;
    private final LoginService loginService;

    public NotesController(NotesService notesService, LoginService loginService) {
        this.notesService = notesService;
        this.loginService = loginService;
    }

    @PostMapping("/edit")
    public String addNote(NotesFormObject formObject, Model model, Authentication authentication, @ModelAttribute(value = "noteid")String noteId){

        int userId = loginService.getUserDetailsByUserName(authentication.getName()).getUserId();
        NotesBean notesBean = new NotesBean(null,formObject.getNotetitle(),formObject.getNotedescription(),userId);
        ArrayList<ResponseObject> list;
        if (noteId.equals("")) {
             list = notesService.addNote(notesBean);
        } else {
            list = notesService.updateNote( Integer.valueOf(noteId), notesBean.getNotetitle(), notesBean.getNotedescription());
        }

        for (ResponseObject object : list) {
            model.addAttribute(object.getFieldObjectName(),object.getMessage());
        }
        return "result";
    }

    @RequestMapping(value = "/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") int noteId, Model model){
        ArrayList<ResponseObject> list = notesService.deleteNote(noteId);

        for (ResponseObject responseObject: list) {
            model.addAttribute(responseObject.getFieldObjectName(),responseObject.getMessage());
        }
        return "result";
    }
}
