package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.UserInfoBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS")
    ArrayList<UserInfoBean> getAllUsersDetails();

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    UserInfoBean getUserDetailsByUserName(String username);

    @Select("SELECT * FROM USERS WHERE userid = #{userid}")
    UserInfoBean getUserDetailsByUserID(int userid);

    @Select("SELECT * FROM USERS WHERE firstname = #{firstname}")
    UserInfoBean getUserDetailsByFirstName(String firstname);

    @Select("SELECT * FROM USERS WHERE lastname = #{lastname}")
    UserInfoBean getUserDetailsByLastName(String lastname);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertNewUserRecord(UserInfoBean userCreds);
}
