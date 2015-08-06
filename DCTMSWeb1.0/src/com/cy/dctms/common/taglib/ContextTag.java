package com.cy.dctms.common.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * Êä³öContextPath¡£
 * 
 * @since 2009-5-21
 * @author 
 * @version 1.00 2009-5-21
 */
public class ContextTag extends TagSupport {
	private static final long serialVersionUID = 5459759368277711530L;

	/**
	 * Êä³öContextPath¡£
	 */
	public int doEndTag() {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			// Êä³öcontextPath
			pageContext.getOut().print(request.getContextPath());
		} catch (IOException ignored) {
		}
		return EVAL_PAGE;
	}

}
