package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories;

import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.FileBean;
import org.apache.ibatis.annotations.*;

import java.io.File;
import java.util.ArrayList;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(FileBean fileBean);


    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(int fileId);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    ArrayList<FileBean> getAllFiles(int userid);

    @Select("SELECT COUNT(*) FROM FILES WHERE userid = #{userid} AND filename = #{filename}")
    int fileCount(int userid, String filename);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    FileBean getFile(int fileId);


}
