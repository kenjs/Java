package com.cy.dctms.common.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * ���һ��JavaScript���룬����һ����jcontextPath����������JavaScript����ʹ�á�
 * <p>
 * 
 * @since 2009-6-5
 * @author 
 * @version 1.00 2009-6-5
 */
public class ScriptTag extends TagSupport {
	private static final long serialVersionUID = 3748951650295812173L;

	/**
	 * ���һ��JavaScript���룬����һ����jcontextPath��������
	 */
	public int doEndTag() {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			// ���contextPath
			pageContext.getOut().println("<script language=\"javascript\">");
			pageContext.getOut().println("\tvar jcontextPath=\"" + request.getContextPath() + "\";");
			pageContext.getOut().println("</script>");

		} catch (IOException ignored) {

		}
		return EVAL_PAGE;
	}

}
