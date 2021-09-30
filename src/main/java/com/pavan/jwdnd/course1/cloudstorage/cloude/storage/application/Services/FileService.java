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

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public ArrayList<ResponseObject> deleteFile(int userId) {
        ResponseObject responseObject = new ResponseObject();
        ArrayList<ResponseObject> responseObjects = new ArrayList<>();
        try {
            if (fileMapper.deleteFile(userId) == 1) {
                responseObject.setFieldObjectName("success");
                responseObject.setMessage(MessageConstants.getSuccessMsg_delete(CategoryConstants.file));
                responseObject.setStatus(false);

                responseObjects.add(responseObject);

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
        ResponseObject responseObject = new ResponseObject();
        ArrayList<ResponseObject> responseObjectArrayList = new ArrayList<>();
        if (!multipartFile.isEmpty()){
            if (fileCount(userId, multipartFile.getOriginalFilename()) > 0){
                throw new FileException(MessageConstants.fileError_duplicate);
            } else {
                try {
                    int insertFileRecord = fileMapper.insertFile(new FileBean(null,
                            multipartFile.getOriginalFilename(),
                            multipartFile.getContentType(),
                            String.valueOf(multipartFile.getSize()),
                            userId,
                            multipartFile.getBytes()));

                    if (insertFileRecord == 1) {
                        responseObject.setFieldObjectName("success");
                        responseObject.setMessage(MessageConstants.getSuccessMsg_add(CategoryConstants.file));
                        responseObject.setStatus(false);
                        responseObjectArrayList.add(responseObject);
                        responseObject.setFieldObjectName("tabAfterSuccess");
                        responseObject.setMessage(TabConstants.file);
                        responseObject.setStatus(false);
                        responseObjectArrayList.add(responseObject);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseObjectArrayList;
    }
}
