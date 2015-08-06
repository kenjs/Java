package com.cy.interfaceService.dao;

import com.cy.interfaceService.bo.NotesendBlacklist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by nixianjing on 15/1/28.
 */
@Repository("notesendBlacklistDao")
public interface NotesendBlacklistDao {

    public List<NotesendBlacklist> queryNotesendBlacklistMap(Map<String,Object> map);


    public List<NotesendBlacklist> queryNotesendBlacklist(String mobilephone);

}
