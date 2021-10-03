package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants;

public class MessageConstants {


    public static final String defaultError = "Something went wrong, Please try again";

    public static final String signupError_userExists = "The username already exists. Please go back to \"Login\" screen and enter the login credentials to enter.";
    public static final String signupError_other = "An error in the registration process. Please try again after some time.";

    public static final String fileError_duplicate = "This file was uploaded on to our database earlier.";
    public static final String fileError_empty = "Please choose a file to upload and then click on \"upload\" button.";
    public static final String fileError_exceedLimit = "Maximum File size limit exceeded. Please try compressing the file and try again";

    public static final String noteError_exceedLimit = "You have exceeded the maximum character limit(" + SizeConstants.noteMaxSize + ") on your note!";

    public static String getSuccessMsg_add(String category) {
        return switch (category) {
            case TabConstants.file -> category + " uploaded successfully!";
            case TabConstants.note -> category + " added successfully";
            case TabConstants.credential -> category + " saved successfully";
            default -> "We have no idea what you have done successfully";
        };
    }

    public static String getSuccessMsg_edit(String category) {
        return switch (category) {
            case TabConstants.note -> "Note modified successfully!";
            case TabConstants.credential -> "Credentials updated successfully!";
            case TabConstants.file -> "We do not allow file modifications. How did you do it?";
            default -> "useless fellow what the fuck did you modify?";
        };
    }

    public static String getSuccessMsg_delete(String category) {
        return switch (category) {
            case TabConstants.file -> "File deleted successfully!";
            case TabConstants.note -> "Note deleted successfully!";
            case TabConstants.credential -> "Credentials deleted successfully!";
            default -> "Your face deleted successfully!";
        };
    }

    public static final String getFailureMsg_delete(String category) {
        return switch (category) {
            case TabConstants.file -> "File deletion failed. Please try again after sometime.";
            case TabConstants.note -> "Note deletion failed. Please try again after sometime.";
            case TabConstants.credential -> "Credentials deletion failed. Please try again after sometime.";
            default -> "Your face deletion failed. Please hit your head against a wall.";
        };
    }

    public static final String fileUploadFailed = "File upload failed. Please again after sometime.";

}
