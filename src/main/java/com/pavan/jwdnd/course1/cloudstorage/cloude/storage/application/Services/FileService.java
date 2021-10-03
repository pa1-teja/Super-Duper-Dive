package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Services;

import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.CategoryConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.MessageConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Constants.TabConstants;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.ExceptionHandlers.FileException;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.FileBean;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.ResponseObject;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories.FileMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public ArrayList<ResponseObject> deleteFile(int userId) {

        ArrayList<ResponseObject> responseObjects = new ArrayList<>();
        try {
            if (fileMapper.deleteFile(userId) == 1) {
                ResponseObject object = new ResponseObject();
                object.setFieldObjectName("success");
                object.setMessage(MessageConstants.getSuccessMsg_delete(CategoryConstants.file));
                object.setStatus(false);

                responseObjects.add(object);
                ResponseObject responseObject = new ResponseObject();
                responseObject.setFieldObjectName("tabAfterSuccess");
                responseObject.setMessage(TabConstants.file);
                responseObject.setStatus(false);

                responseObjects.add(responseObject);
            } else {
                throw new FileException(MessageConstants.defaultError);
            }
        } catch (FileException exception){
            throw exception;
        }catch (Exception e){
            throw new FileException(MessageConstants.defaultError,e);
        }

        return responseObjects;
    }

    public ArrayList<FileBean> getAllFiles(int userId){
        return fileMapper.getAllFiles(userId);
    }

    public int fileCount(int userid, String fileName){
        return fileMapper.fileCount(userid,fileName);
    }

    public ResponseEntity<Resource> viewFile(int fileId) {
        try{

            FileBean fileBean = fileMapper.getFile(fileId);
            ResponseEntity entity = null;
            String type = fileBean.getContenttype();
            byte[] fileData = fileBean.getFiledata();

            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.add("content-disposition","attachment; filename=" + fileBean.getFilename());
            respHeaders.add("Content-Type",type);

            entity = new ResponseEntity(fileData, HttpStatus.OK);

            return entity;
        }catch (Exception e){
            throw new FileException(MessageConstants.defaultError,e);
        }
    }

    public ArrayList<ResponseObject> uploadFile(MultipartFile multipartFile, int userId){

        ArrayList<ResponseObject> responseObjectArrayList = new ArrayList<>();
        if (!multipartFile.isEmpty()){
            if (fileCount(userId, multipartFile.getOriginalFilename()) > 0){

                ResponseObject object = new ResponseObject();
                object.setFieldObjectName("error");
                object.setMessage(MessageConstants.fileError_duplicate);
                object.setStatus(false);
                responseObjectArrayList.add(object);
                ResponseObject responseObject = new ResponseObject();
                responseObject.setFieldObjectName("tabAfterError");
                responseObject.setMessage(TabConstants.file);
                responseObject.setStatus(false);
                responseObjectArrayList.add(responseObject);

            } else {
                try {
                    int insertFileRecord = fileMapper.insertFile(new FileBean(null,
                            multipartFile.getOriginalFilename(),
                            multipartFile.getContentType(),
                            String.valueOf(multipartFile.getSize()),
                            userId,
                            multipartFile.getBytes()));

                    if (insertFileRecord == 1) {
                        ResponseObject object = new ResponseObject();
                        object.setFieldObjectName("success");
                        object.setMessage(MessageConstants.getSuccessMsg_add(CategoryConstants.file));
                        object.setStatus(false);
                        responseObjectArrayList.add(object);
                        ResponseObject responseObject = new ResponseObject();
                        responseObject.setFieldObjectName("tabAfterSuccess");
                        responseObject.setMessage(TabConstants.file);
                        responseObject.setStatus(false);
                        responseObjectArrayList.add(responseObject);
                    } else{
                        ResponseObject object = new ResponseObject();
                        object.setFieldObjectName("error");
                        object.setMessage(MessageConstants.fileUploadFailed);
                        object.setStatus(false);
                        responseObjectArrayList.add(object);
                        ResponseObject responseObject = new ResponseObject();
                        responseObject.setFieldObjectName("tabAfterError");
                        responseObject.setMessage(TabConstants.file);
                        responseObject.setStatus(false);
                        responseObjectArrayList.add(responseObject);
                    }
                } catch (IOException e) {

                    ResponseObject object = new ResponseObject();
                    object.setFieldObjectName("otherError");
                    object.setMessage(MessageConstants.fileUploadFailed);
                    object.setStatus(false);
                    responseObjectArrayList.add(object);
                    ResponseObject responseObject = new ResponseObject();
                    responseObject.setFieldObjectName("tabAfterOtherError");
                    responseObject.setMessage(TabConstants.file);
                    responseObject.setStatus(false);
                    responseObjectArrayList.add(responseObject);
                }
            }
        }
        return responseObjectArrayList;
    }
}
