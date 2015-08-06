package com.cy.swp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.swp.bo.SystemNoteTemplateInfo;
import com.cy.swp.dao.SystemNoteTemplateDao;
import com.cy.swp.service.SystemNoteTemplateService;

@Service("systemNoteTemplateService")
public class SystemNoteTemplateServiceImpl implements SystemNoteTemplateService {
	
	@Resource
	private SystemNoteTemplateDao systemNoteTemplateDao;

	@Override
	public List<SystemNoteTemplateInfo> querySystemNoteTemplateInfo(Integer businessType,Integer sendType) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("businessType", businessType);
		queryMap.put("sendType", sendType);
		return systemNoteTemplateDao.querySystemNoteTemplateInfo(queryMap);
	}

	@Override
	public SystemNoteTemplateInfo querySystemNoteTemplateInfoById(Integer id) {
		return systemNoteTemplateDao.querySystemNoteTemplateInfoById(id);
	}

	public String querySystemNoteTemplateHtnl(Integer businessType,Integer sendType) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("businessType", businessType);
			queryMap.put("sendType", sendType);
			List<SystemNoteTemplateInfo> list = systemNoteTemplateDao.querySystemNoteTemplateInfo(queryMap);
			if(list.size() > 0) {
				StringBuffer noteString = new StringBuffer();	
				noteString.append("<table border=\"0\" cellspacing=\"0\" class=\"mobnr\">");
				noteString.append("<thead>");
				noteString.append("<tr>");
				noteString.append("<td width=\"70%\">模板内容</td>");

				noteString.append("</tr>");
				noteString.append("</thead>");
				for(int i = 0;i<list.size();i++) {
					noteString.append("<tr onclick=\"getNoteContent('"+list.get(i).getContent()+"');\">");
					noteString.append("<td>"+list.get(i).getContent()+"</td>");
					noteString.append("</tr>");
				}
				noteString.append("</table>");
				return noteString.toString();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
