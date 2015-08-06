package com.cy.dcts.dict.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseAjaxAction;
import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.DictInfo;
import com.cy.dcts.common.util.DataDictUtil;

public class CarPlateTypeDictInfoAction extends BaseJsonAction {

	/**
	 * 数据字典-车辆-板
	 */
	private static final long serialVersionUID = 7401234716746260588L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	List<DictInfo> list;

	@Override
	protected void execMethod() throws Exception {
		list = DataDictUtil.carPlateTypeData();
		String result = this.sendResponseToJson("1","获取数据字典车辆板成功!",list);
		logger.debug("get car plante type dict info success. size = [{}] , result = [{}]",list.size(),result);
	}

	public List<DictInfo> getList() {
		return list;
	}

	public void setList(List<DictInfo> list) {
		this.list = list;
	}

}
