package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.CategoryConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.MessageConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.SizeConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.TabConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.ExceptionHandlers.NoteException;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.NotesBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.ResponseObject;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories.NotesMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotesService {

    private final NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public ArrayList<ResponseObject> addNote(NotesBean notesBean) {
        ArrayList<ResponseObject> list = new ArrayList<>();
        try {
            if (notesBean.getNotedescription().length() > SizeConstants.noteMaxSize) {
                throw new NoteException(MessageConstants.noteError_exceedLimit);
            }

            int insertRecordId = notesMapper.addNote(notesBean);
            if (insertRecordId == 1) {
                ResponseObject object = new ResponseObject();
                object.setFieldObjectName("success");
                object.setMessage(MessageConstants.getSuccessMsg_add(CategoryConstants.note));
                object.setStatus(false);

                list.add(object);

                ResponseObject responseObject = new ResponseObject();
                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.note);
                responseObject.setStatus(false);
                list.add(responseObject);
            } else {
                ResponseObject  object = new ResponseObject();
                object.setFieldObjectName("error");
                object.setMessage(MessageConstants.defaultError );
                object.setStatus(false);
                list.add(object);

                ResponseObject responseObject = new ResponseObject();
                responseObject.setFieldObjectName("tabAfterError");
                responseObject.setMessage(TabConstants.note);
                responseObject.setStatus(false);
                list.add(responseObject);
            }
        } catch (NoteException e) {
            ResponseObject  object = new ResponseObject();
            object.setFieldObjectName("error");
            object.setMessage(MessageConstants.defaultError );
            object.setStatus(false);
            list.add(object);

            ResponseObject responseObject = new ResponseObject();
            responseObject.setFieldObjectName("tabAfterError");
            responseObject.setMessage(TabConstants.note);
            responseObject.setStatus(false);
            list.add(responseObject);
        }
        return list;
    }

    public ArrayList<NotesBean> getAllNotes(int userId){
        return notesMapper.getAllNotes(userId);
    }

    public ArrayList<ResponseObject> updateNote(int noteId, String notetitle, String noteDescription){
        ArrayList<ResponseObject> list = new ArrayList<>();
        try {

            int updateRecordId = notesMapper.updateNote(noteId,notetitle,noteDescription);
            if(updateRecordId == 1) {
                ResponseObject  object = new ResponseObject();
                object.setFieldObjectName("success");
                object.setMessage(MessageConstants.getSuccessMsg_edit(CategoryConstants.note));
                object.setStatus(false);
                list.add(object);

                ResponseObject responseObject = new ResponseObject();
                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.note);
                responseObject.setStatus(false);
                list.add(responseObject);
            } else {
                ResponseObject  object = new ResponseObject();
                object.setFieldObjectName("error");
                object.setMessage(MessageConstants.defaultError );
                object.setStatus(false);
                list.add(object);

                ResponseObject responseObject = new ResponseObject();
                responseObject.setFieldObjectName("tabAfterError");
                responseObject.setMessage(TabConstants.note);
                responseObject.setStatus(false);
                list.add(responseObject);
            }
        }catch (NoteException e){
            ResponseObject  object = new ResponseObject();
            object.setFieldObjectName("error");
            object.setMessage(MessageConstants.defaultError );
            object.setStatus(false);
            list.add(object);

            ResponseObject responseObject = new ResponseObject();
            responseObject.setFieldObjectName("tabAfterError");
            responseObject.setMessage(TabConstants.note);
            responseObject.setStatus(false);
            list.add(responseObject);
        }catch(Exception e) {
            ResponseObject  object = new ResponseObject();
            object.setFieldObjectName("error");
            object.setMessage(MessageConstants.defaultError );
            object.setStatus(false);
            list.add(object);

            ResponseObject responseObject = new ResponseObject();
            responseObject.setFieldObjectName("tabAfterError");
            responseObject.setMessage(TabConstants.note);
            responseObject.setStatus(false);
            list.add(responseObject);
    }
        return list;
    }


    public ArrayList<ResponseObject> deleteNote(int noteId) {
        ArrayList<ResponseObject> list = new ArrayList<>();
        try {

            int deleteRecordId = notesMapper.deleteNote(noteId);
            if (deleteRecordId == 1) {
                ResponseObject object = new ResponseObject();
                object.setFieldObjectName("success");
                object.setMessage(MessageConstants.getSuccessMsg_delete(CategoryConstants.note));
                object.setStatus(false);
                list.add(object);

                ResponseObject responseObject = new ResponseObject();
                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.note);
                responseObject.setStatus(false);
                list.add(responseObject);
            } else {
                ResponseObject object = new ResponseObject();
                object.setFieldObjectName("error");
                object.setMessage(MessageConstants.getFailureMsg_delete(CategoryConstants.note));
                object.setStatus(false);
                list.add(object);

                ResponseObject responseObject = new ResponseObject();
                responseObject.setFieldObjectName("tabAfterError");
                responseObject.setMessage(TabConstants.note);
                responseObject.setStatus(false);
                list.add(responseObject);
            }
        } catch(NoteException ne) {
            ResponseObject object = new ResponseObject();
            object.setFieldObjectName("error");
            object.setMessage(MessageConstants.getFailureMsg_delete(CategoryConstants.note));
            object.setStatus(false);
            list.add(object);

            ResponseObject responseObject = new ResponseObject();
            responseObject.setFieldObjectName("tabAfterError");
            responseObject.setMessage(TabConstants.note);
            responseObject.setStatus(false);
            list.add(responseObject);
        } catch(Exception e) {
            ResponseObject object = new ResponseObject();
            object.setFieldObjectName("error");
            object.setMessage(MessageConstants.defaultError);
            object.setStatus(false);
            list.add(object);

            ResponseObject responseObject = new ResponseObject();
            responseObject.setFieldObjectName("tabAfterError");
            responseObject.setMessage(TabConstants.note);
            responseObject.setStatus(false);
            list.add(responseObject);
        }
        return list;
    }

}
