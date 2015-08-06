package com.cy.dctms.common.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ����Page����action
 * @author hayden
 */
public abstract class BasePageAction extends BaseAction {

	private static final long serialVersionUID = -1559876334712310043L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected abstract String execMethod() throws Exception;

	@Override
	public String exec() {
		try {
			return execMethod();
		} catch (Exception e) {
			logger.warn("error:", e);
			try {
				sendResponseMessage("�쳣���˴β�����Ч");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				return SUCCESS;
			}
			return SUCCESS;
		}
	}
	
	/**
	 * ��Ӧ Response����
	 * */
	public void sendResponseMessage(String message) throws IOException {
		response.setContentType("text/html; charset=GBK");
		PrintWriter out = response.getWriter();
		out.println(message);
		out.flush();
		out.close();
	}
}
