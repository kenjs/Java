package com.cy.common.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cy.common.domain.DmbGgDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;

/**
 * 
* @Descriptoin 数量计量单位分类下拉列表
* @Note
* @author admin
* @version
 */

public class JldwFlTag extends TagSupport {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	private String myName;//名称
	private String myId;//ID编号
	private String myValue;//默认值
	private String myClass;//样式
	private String onChange;
	private boolean contaisQxz;//请选择
	private String mcContainDmBz;
	
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	/**
	 * 输出ContextPath。
	 */
	@SuppressWarnings("unchecked")
	public int doEndTag() {
		try {
			ServletContext servletContext = pageContext.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			businessSqlMapClientTemplate = (SysSqlMapClientTemplate) webApplicationContext.getBean("businessSqlMapClientTemplate");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mcContainDmBz", mcContainDmBz);
			List<DmbGgDomain> dataList = businessSqlMapClientTemplate.queryForList("getJldwFlDdlbList", map);

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
			if (StringUtils.isNotBlank(onChange)) {
				pageContext.getOut().print(" onchange=\"" + onChange + "\"");
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
			
			int index = 1;
			for(Iterator iter=dataList.iterator();iter.hasNext();){
				DmbGgDomain domain = (DmbGgDomain)iter.next();
				if (index == 1 && StringUtils.isBlank(defaultValue)) {
					pageContext.getRequest().setAttribute(myName, domain.getDm());
				}
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

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getMcContainDmBz() {
		return mcContainDmBz;
	}

	public void setMcContainDmBz(String mcContainDmBz) {
		this.mcContainDmBz = mcContainDmBz;
	}
	
}