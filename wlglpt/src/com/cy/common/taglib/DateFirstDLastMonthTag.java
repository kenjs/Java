package com.cy.common.taglib;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.cy.framework.util.SysDateUtil;

/**
 * 
* @Descriptoin ���ϸ��µĵ�һ��
* @Note
* @author admin
* @version
 */

public class DateFirstDLastMonthTag extends TagSupport {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	private String myName;//����
	private String myId;//ID���
	private String myValue;//Ĭ��ֵ
	private String myClass;//��ʽ
	
	/**
	 * ���ContextPath��
	 */
	@SuppressWarnings("unchecked")
	public int doEndTag() {
		try {
			pageContext.getOut().print("<input type=\"text\" ");
			
			if(StringUtils.isNotBlank(myName)){
				pageContext.getOut().print(" name=\""+myName+"\"");
			}
			if(StringUtils.isNotBlank(myId)){
				pageContext.getOut().print(" id=\""+myId+"\"");
			}
			if(StringUtils.isNotBlank(myClass)){
				pageContext.getOut().print(" class=\""+myClass+"\"");
			}
			String defaultValue = "";
			if (StringUtils.isNotBlank(myValue)) {
				defaultValue = myValue;
			}else if (StringUtils.isNotBlank(myName)) {
				Object obj = pageContext.getRequest().getAttribute(myName);
				if (obj != null) {
					defaultValue = obj.toString();
				}
			}
			
			if (StringUtils.isBlank(defaultValue)) {
				defaultValue = SysDateUtil.getFirstDayMonth(SysDateUtil.getIntervalDate(SysDateUtil.getSqlDate(), Calendar.MONTH, -1));
			}
			pageContext.getOut().print(" value=\""+defaultValue+"\"");
			
			pageContext.getOut().println("/>");
			
		} catch (IOException ignored) {
			ignored.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getMyClass() {
		return myClass;
	}

	public void setMyClass(String myClass) {
		this.myClass = myClass;
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMyValue() {
		return myValue;
	}

	public void setMyValue(String myValue) {
		this.myValue = myValue;
	}

}