package com.cy.dcts.common.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 输出ContextPath。
 * 
 * @since 2009-5-21
 * @author 
 * @version 1.00 2009-5-21
 */
public class ContextTag extends TagSupport {
	private static final long serialVersionUID = 5459759368277711530L;

	/**
	 * 输出ContextPath。
	 */
	public int doEndTag() {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			// 输出contextPath
			pageContext.getOut().print(request.getContextPath());
		} catch (IOException ignored) {
		}
		return EVAL_PAGE;
	}

}
