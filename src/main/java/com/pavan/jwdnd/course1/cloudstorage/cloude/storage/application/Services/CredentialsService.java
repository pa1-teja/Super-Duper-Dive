package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.CategoryConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.MessageConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.TabConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.ExceptionHandlers.CredentialException;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.CredentialsBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.ResponseObject;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories.CredentialMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class CredentialsService {

    private EncryptionService encryptionService;
    private CredentialMapper credentialMapper;

    public CredentialsService(EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }

    public ArrayList<ResponseObject> addCredential(CredentialsBean credentialsBean, int userId){
        ArrayList<ResponseObject> list = new ArrayList<>();
        try{
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);
            String key = Base64.getEncoder().encodeToString(salt);
            String encryptPassword = encryptionService.encryptValue(credentialsBean.getPassword(),key);
            credentialsBean.setPassword(encryptPassword);
            int insertCredRecord = credentialMapper.addCredential(credentialsBean);
            if (insertCredRecord == 1){
                ResponseObject responseObject = new ResponseObject();

                responseObject.setFieldObjectName("success");
                responseObject.setMessage(MessageConstants.getSuccessMsg_add(CategoryConstants.credential));
                responseObject.setStatus(false);
                list.add(responseObject);

                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.credential);
                responseObject.setStatus(false);
                list.add(responseObject);
            }else {
                throw new CredentialException(MessageConstants.defaultError);
            }
        }catch (CredentialException e){
            throw e;
        } catch (Exception e){
            throw new CredentialException(MessageConstants.defaultError,e);
        }
        return list;
    }

    public ArrayList<CredentialsBean> getAllUserCredentials(int userId){
        return credentialMapper.getAllCredential(userId);
    }

    public ArrayList<ResponseObject> deleteCredential(int credentialId, Model model) {
        ArrayList<ResponseObject> list = new ArrayList<>();
        try {
            //delete a credential
            if (credentialMapper.deleteCredential(credentialId) == 1) {

                ResponseObject responseObject = new ResponseObject();

                responseObject.setFieldObjectName("success");
                responseObject.setMessage(MessageConstants.getSuccessMsg_delete(CategoryConstants.credential));
                responseObject.setStatus(false);
                list.add(responseObject);

                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.credential);
                responseObject.setStatus(false);
                list.add(responseObject);

            } else {
                throw new CredentialException(MessageConstants.defaultError);
            }
        } catch(CredentialException ce) {
            throw ce;
        } catch(Exception e) {
            throw new CredentialException(MessageConstants.defaultError, e);
        }
        return list;
    }

    public ArrayList<ResponseObject> updateCredential(CredentialsBean credentialsBean , int credentialId) {
        ArrayList<ResponseObject> list = new ArrayList<>();
        try {
            //getKey
            String key = credentialMapper.getKey(credentialId);
            String encryptedPassword = encryptionService.encryptValue(credentialsBean.getPassword(), key);
            if (credentialMapper.updateCredential(credentialId, credentialsBean.getUrl(), credentialsBean.getUsername(), encryptedPassword) == 1) {
                ResponseObject responseObject = new ResponseObject();

                responseObject.setFieldObjectName("success");
                responseObject.setMessage(MessageConstants.getSuccessMsg_edit(CategoryConstants.credential));
                responseObject.setStatus(false);
                list.add(responseObject);

                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.credential);
                responseObject.setStatus(false);
                list.add(responseObject);
            } else {
                throw new CredentialException(MessageConstants.defaultError);
            }
        } catch(CredentialException ce) {
            throw ce;
        } catch(Exception e) {
            throw new CredentialException(MessageConstants.defaultError, e);
        }

        return list;
    }
}
