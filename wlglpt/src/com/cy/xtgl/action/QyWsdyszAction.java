package com.cy.xtgl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.xtgl.domain.QyWsdyszDomain;
import com.cy.xtgl.service.imp.QyWsdyszServiceImp;

/**
 * THE ACTION FOR 企业-文书打印设置
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/xtgl/qywsdysz", results = {
		@Result(name = "init", location = "/work/qywsdysz.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "selectBj", type = "json"),
		@Result(name = "initMx", location = "/work/qywsdysz_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyWsdyszAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyWsdyszDomain domain = (QyWsdyszDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("维护序号(SEQ_ZY_DJXH)");
		headList.add("所属机构(当前所属公司)");
		headList.add("文书代码，1001:托运单");
		headList.add("左边距");
		headList.add("上边距");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyWsdyszDomain element = (QyWsdyszDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getWhXh());
			list.add(element.getSsJgbm());
			list.add(element.getWsDm());
			list.add(element.getLeftMargin());
			list.add(element.getTopMargin());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	public String selectBj() throws Exception {		
		UserDomain userDomain = getUserDomain();
		((QyWsdyszServiceImp)getService()).selectBj(getDomain(), userDomain);	
		return "selectBj";
	}
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyWsdyszDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyWsdyszDomain) domain;
	}

	@Resource(name = "qyWsdyszServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
