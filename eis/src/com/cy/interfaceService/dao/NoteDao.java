package com.cy.interfaceService.dao;

import com.cy.interfaceService.bo.NoteLogInfo;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by haoy on 2014/10/16.
 */
@Repository("noteDao")
public interface NoteDao {
    public long addNoteLogInfo(NoteLogInfo noteLogInfo) throws SQLException;
}
