package com.cy.dcts.common.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 输出一段JavaScript代码，产生一个“jcontextPath”变量，供JavaScript代码使用。
 * <p>
 * 
 * @since 2009-6-5
 * @author 
 * @version 1.00 2009-6-5
 */
public class ScriptTag extends TagSupport {
	private static final long serialVersionUID = 3748951650295812173L;

	/**
	 * 输出一段JavaScript代码，产生一个“jcontextPath”变量。
	 */
	public int doEndTag() {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			// 输出contextPath
			pageContext.getOut().println("<script language=\"javascript\">");
			pageContext.getOut().println("\tvar jcontextPath=\"" + request.getContextPath() + "\";");
			pageContext.getOut().println("</script>");

		} catch (IOException ignored) {

		}
		return EVAL_PAGE;
	}

}
