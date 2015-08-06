package com.cy.dcts.note.dao;

import com.cy.dcts.common.bo.NoteLogInfo;

public interface INoteDao {
	
	/**
	 * 新增发送短信信息
	 * @author nxj
	 * @param noteLogInfo
	 * @return
	 */
	public String addNoteLogInfo(NoteLogInfo noteLogInfo);
	

}
