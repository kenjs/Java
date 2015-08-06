package com.cy.dcts.webUser.action;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.DataDictInfoDomain;
/**
 * 评价管理页面
 * @date 2014-5-22
 * @author haoyong
 *
 */
public class OpenEvaluationManagementAction extends BasePageAction{
	private static final long serialVersionUID = 1235984265409000870L;
	private DataDictInfoDomain dataDictInfoDomain=new DataDictInfoDomain();
	@Override
	protected String execMethod() throws Exception {
		return SUCCESS;
	}
	public DataDictInfoDomain getDataDictInfoDomain() {
		return dataDictInfoDomain;
	}
	public void setDataDictInfoDomain(DataDictInfoDomain dataDictInfoDomain) {
		this.dataDictInfoDomain = dataDictInfoDomain;
	}

}
