package com.cy.xtgl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.QySwdnDshDomain;
/**
* THE ACTION FOR 上网电脑管理
* @author HaoY
*/
@Controller
@Scope("prototype")
@Action(value = "/xtgl/swdngl", results = {
		@Result(name = "init", location = "/work/xtgl/qyswdngl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "start", type = "json"),
		@Result(name = "stop", type = "json")
		})
@SuppressWarnings("unchecked")		
public class QySwdnAction extends ExtendAction{
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QySwdnDshDomain domain = (QySwdnDshDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("申请人");
		headList.add("申请日期");
		headList.add("审核人");
		headList.add("审核日期");
		headList.add("MAC地址");
		headList.add("备注");
		
		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QySwdnDshDomain element = (QySwdnDshDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getSqrMc());
			list.add(element.getSqrq());
			list.add(element.getShrMc());
			list.add(element.getShrq());
			list.add(element.getMac());
			list.add(element.getBzsm());
			
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String startUse() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().saveEnable(getDomain(), userDomain);
		return "start";
	}
	
	public String stopUse() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().saveDisable(getDomain(), userDomain);
		return "stop";
	}

	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QySwdnDshDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QySwdnDshDomain) domain;
	}

	@Resource(name="qySwdnServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
