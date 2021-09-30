package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.ExceptionHandlers;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.MessageConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.TabConstants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = MultipartException.class)
    public ModelAndView resolveFileUploadException(MultipartException e) {
        ModelAndView modelAndView = new ModelAndView("result.html");
        modelAndView.addObject("error", MessageConstants.fileError_exceedLimit);
        modelAndView.addObject("tabAfterError", TabConstants.file);
        return modelAndView;
    }

    @ExceptionHandler(value = com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.ExceptionHandlers.NoteException.class)
    public ModelAndView NoteExceptionHandler(Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView("result.html");
        modelAndView.addObject("error", e.getMessage());
        modelAndView.addObject("tabAfterError", TabConstants.note);
        return modelAndView;
    }

    @ExceptionHandler(value = FileException.class)
    public ModelAndView FileExceptionHandler(Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView("result.html");
        modelAndView.addObject("error", e.getMessage());
        modelAndView.addObject("tabAfterError", TabConstants.file);
        return modelAndView;
    }

}