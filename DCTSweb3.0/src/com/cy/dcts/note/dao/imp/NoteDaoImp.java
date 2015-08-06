package com.cy.dcts.note.dao.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.NoteLogInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.note.dao.INoteDao;

public class NoteDaoImp extends BaseDao implements INoteDao{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public String addNoteLogInfo(NoteLogInfo noteLogInfo) {
		try {
			return addObjectKeyString("insert_note_log_info",noteLogInfo);
		}catch (Exception e) {
			logger.error("insert_note_log_info error!",e);
			throw new RuntimeException();
		}
	}

}
