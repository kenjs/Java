package com.cy.dcts.dict.action;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.DictInfo;
import com.cy.dcts.common.util.DataDictUtil;
/**
 * 数据字典车长
 * nxj
 */
public class CarLengthDictInfoAction extends BaseJsonAction {

	
	private static final long serialVersionUID = 709234004437940545L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	List<DictInfo> list;
	
	@Override
	protected void execMethod() throws Exception {
		list = DataDictUtil.carLengthData();
		String result = this.sendResponseToJson("1","获取数据字典车辆 长成功!",list);
		logger.debug("get car length dict info success. size = [{}] , result = [{}]",
				list.size(),result);
	}

	public List<DictInfo> getList() {
		return list;
	}

	public void setList(List<DictInfo> list) {
		this.list = list;
	}
}
