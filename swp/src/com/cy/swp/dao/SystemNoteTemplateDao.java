package com.cy.swp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cy.swp.bo.SystemNoteTemplateInfo;

@Repository("systemNoteTemplateDao")
public interface SystemNoteTemplateDao {
	
	/**
	 * 查询短信模板
	 * @param queryMap
	 * @return
	 */
	public List<SystemNoteTemplateInfo> querySystemNoteTemplateInfo(Map<String, Object> queryMap);


	/**
	 * 根据id查询短信模板
	 * @param id
	 * @return
	 */
	public SystemNoteTemplateInfo querySystemNoteTemplateInfoById(Integer id);

}
