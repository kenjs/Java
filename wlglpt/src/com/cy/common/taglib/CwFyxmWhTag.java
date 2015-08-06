package com.cy.common.taglib;



import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.constants.WebConstants;


/**
 * 
* @Descriptoin 输出财务-费用项目-维护下拉列表
* @Note
* @author HJH
* @version
 */

public class CwFyxmWhTag extends TagSupport {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	private String myName;//名称
	private String myId;//ID编号
	private String myValue;//默认值
	private String myClass;//样式
	private boolean contaisQxz;//请选择
	private String mcContainDmBz;
	private String disabled;
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getMcContainDmBz() {
		return mcContainDmBz;
	}

	public void setMcContainDmBz(String mcContainDmBz) {
		this.mcContainDmBz = mcContainDmBz;
	}

	@Autowired
	private SysSqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 输出ContextPath。
	 */
	@SuppressWarnings("unchecked")
	public int doEndTag() {
		try {
			ServletContext servletContext = pageContext.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			sqlMapClientTemplate = (SysSqlMapClientTemplate) webApplicationContext.getBean("businessSqlMapClientTemplate");
			UserDomain userDomain = (UserDomain)pageContext.getSession().getAttribute(WebConstants.SES_USER_INFO);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("zgs", userDomain.zgsbm);
			map.put("mcContainDmBz", mcContainDmBz);
			List<DmbGgDomain> dataList = sqlMapClientTemplate.queryForList("getCwFyXmWhList",map);

			pageContext.getOut().print("<select");
			if(StringUtils.isNotBlank(myName)){
				pageContext.getOut().print(" name=\""+myName+"\"");
			}
			if(StringUtils.isNotBlank(myId)){
				pageContext.getOut().print(" id=\""+myId+"\"");
			}
			if(StringUtils.isNotBlank(myClass)){
				pageContext.getOut().print(" class=\""+myClass+"\"");
			}
			if(StringUtils.isNotBlank(disabled)){
				pageContext.getOut().print(" disabled=\""+disabled+"\"");
			}
			pageContext.getOut().println(">");
			if(contaisQxz==true){
				pageContext.getOut().println("<option value=\"\">---请选择---</option>");
			}
			
			String defaultValue = "";
			if(StringUtils.isNotBlank(myValue)){
				defaultValue = myValue;
			}else if(StringUtils.isNotBlank(myName)){
				Object obj = pageContext.getRequest().getAttribute(myName);
				if(obj!=null){
					defaultValue = (String)obj;
				}
			}
			
			for(Iterator iter=dataList.iterator();iter.hasNext();){
				DmbGgDomain domain = (DmbGgDomain)iter.next();
				if(domain.getDm().equals(defaultValue)){
					pageContext.getOut().println("<option value=\""+domain.getDm()+"\" selected=\"selected\">"+domain.getMc()+"</option>");
				}else{
					pageContext.getOut().println("<option value=\""+domain.getDm()+"\">"+domain.getMc()+"</option>");
				}
			}
			pageContext.getOut().println("</select>");
		} catch (IOException ignored) {
			ignored.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public boolean isContaisQxz() {
		return contaisQxz;
	}

	public void setContaisQxz(boolean contaisQxz) {
		this.contaisQxz = contaisQxz;
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