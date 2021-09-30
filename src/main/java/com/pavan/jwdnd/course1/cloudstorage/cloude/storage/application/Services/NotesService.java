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
import org.springframework.ui.Model;

import java.util.ArrayList;

@Service
public class NotesService {

    private NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public ArrayList<ResponseObject> addNote(NotesBean notesBean, int userId) {
        ArrayList<ResponseObject> list = new ArrayList<>();
        try {
            if (notesBean.getNotedescription().length() > SizeConstants.noteMaxSize) {
                throw new NoteException(MessageConstants.noteError_exceedLimit);
            }
            ResponseObject responseObject = new ResponseObject();
            int insertRecordId = notesMapper.addNote(notesBean);
            if (insertRecordId == 1) {
                responseObject.setFieldObjectName("success");
                responseObject.setMessage(MessageConstants.getSuccessMsg_add(CategoryConstants.note));
                responseObject.setStatus(false);

                list.add(responseObject);

                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.note);
                responseObject.setStatus(false);
            } else {
                throw new NoteException(MessageConstants.defaultError);
            }
        } catch (NoteException e) {
            throw e;
        }
        return list;
    }

    public ArrayList<NotesBean> getAllNotes(int userId){
        return notesMapper.getAllNotes(userId);
    }

    public ArrayList<ResponseObject> updateNote(int noteId, String notetitle, String noteDescription){
        ArrayList<ResponseObject> list = new ArrayList<>();
        try {
            ResponseObject responseObject = new ResponseObject();
            int updateRecordId = notesMapper.updateNote(noteId,notetitle,noteDescription);
            if (updateRecordId == 1) {
                responseObject.setFieldObjectName("success");
                responseObject.setMessage(MessageConstants.getSuccessMsg_edit(CategoryConstants.note));
                responseObject.setStatus(false);

                list.add(responseObject);

                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.note);
                responseObject.setStatus(false);

            } else {
                throw new NoteException(MessageConstants.defaultError);
            }
        }catch (NoteException e){
            throw e;
        }catch(Exception e) {
        throw new NoteException(MessageConstants.defaultError, e);
    }
        return list;
    }


    public ArrayList<ResponseObject> deleteNote(int noteId) {
        ArrayList<ResponseObject> list = new ArrayList<>();
        try {
            ResponseObject responseObject = new ResponseObject();
            int deleteRecordId = notesMapper.deleteNote(noteId);
            if (deleteRecordId == 1) {
                responseObject.setFieldObjectName("success");
                responseObject.setMessage(MessageConstants.getSuccessMsg_delete(CategoryConstants.note));
                responseObject.setStatus(false);
                list.add(responseObject);

                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.note);
                responseObject.setStatus(false);
                list.add(responseObject);
            } else {
                throw new NoteException(MessageConstants.defaultError);
            }
        } catch(NoteException ne) {
            throw ne;
        } catch(Exception e) {
            throw new NoteException(MessageConstants.defaultError, e);
        }
        return list;
    }

}