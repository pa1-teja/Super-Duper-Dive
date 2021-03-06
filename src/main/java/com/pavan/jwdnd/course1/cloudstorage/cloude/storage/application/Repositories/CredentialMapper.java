package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.CredentialsBean;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int addCredential(CredentialsBean credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    ArrayList<CredentialsBean> getAllCredential(int userid);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    int deleteCredential(int credentialid);

    @Select("SELECT key FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    String getKey(int credentialid);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} WHERE credentialId = #{credentialId}")
    int updateCredential(int credentialId, String url, String username, String password);
}
