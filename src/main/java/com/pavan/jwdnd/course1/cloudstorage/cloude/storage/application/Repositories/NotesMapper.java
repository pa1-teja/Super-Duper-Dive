package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Repositories;

import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.Models.NotesBean;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface NotesMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(NotesBean notesBean);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    ArrayList<NotesBean> getAllNotes(int userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int deleteNote(int noteid);

    @Update("UPDATE NOTES SET  notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    int updateNote(int noteId, String notetitle, String noteDescription);


    @Select("SELECT COUNT(*) FROM NOTES WHERE userid = #{userId} AND notetitle = #{noteTitle}")
    int noteCount(int userId, String noteTitle);
}
