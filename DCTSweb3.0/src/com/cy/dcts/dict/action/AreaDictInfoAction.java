package com.cy.dcts.dict.action;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.util.DataArea;
import com.cy.dcts.common.util.DataAreaDictUtil;
/**
 *  地区
 * @author nxj
 *
 */
public class AreaDictInfoAction extends BaseJsonAction {

	private static final long serialVersionUID = -2811963857444517048L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String message;
	@Override
	protected void execMethod() throws Exception {
		message = DataAreaDictUtil.converAreaListToJson(DataArea
				.createAreaData());
		String result = this.sendResponseToJson("1","获取地区数据字典成功!",message);
		logger.trace("get area data dict info success. result = [{}]", result);
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
