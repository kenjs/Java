package com.cy.swp.service;

import com.cy.swp.bo.SystemNoteTemplateInfo;

import java.util.List;
import java.util.Map;

public interface SystemNoteTemplateService {
	
	/**
	 * 返回HTML页面
	 * @param businessType
	 * @param sendType
	 * @return
	 */
	public String querySystemNoteTemplateHtnl(Integer businessType,Integer sendType);


	/**
	 * 查询短信模板
	 * @param businessType
	 * @param sendType
	 * @return
	 */
	public List<SystemNoteTemplateInfo> querySystemNoteTemplateInfo(Integer businessType,Integer sendType);


	/**
	 * 根据id查询短信模板
	 * @param id
	 * @return
	 */
	public SystemNoteTemplateInfo querySystemNoteTemplateInfoById(Integer id);

}
