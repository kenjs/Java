package com.cy.dzgl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.dzgl.domain.QySpwsSplcszDomain;
import com.cy.dzgl.service.QySpwsSplcszService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR 企业-审批文书-审批流程设置
 * @author anq
 */
 @Controller
@Scope("prototype")
@Action(value = "/dzgl/qyspwssplcsz", results = {
		@Result(name = "init", location = "/work/dzgl/qyspwssplcsz.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/dzgl/qyspwssplcsz_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "initSpjcMx", location = "/work/dzgl/qyspwssplcsz_spjc.jsp"),
		@Result(name = "saveSpjc", type = "json"),
		@Result(name = "deleteSplcZb", location = "/work/dzgl/qyspwssplcsz_mx.jsp"),
		@Result(name = "cxszSplc", location = "/work/dzgl/qyspwssplcsz_mx.jsp"),
		@Result(name = "qxszSplc", type = "json"),
		@Result(name = "queryQySpwsXmflbz", type = "json")
		})
@SuppressWarnings("unchecked")
public class QySpwsSplcszAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("单位");
		headList.add("部门");
		headList.add("业务分类");
		headList.add("业务环节");
		headList.add("文书");
		headList.add("项目分类");
		headList.add("终审时限(天)");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QySpwsSplcszDomain element = (QySpwsSplcszDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getDwMc());
			list.add(element.getSsJgmc());
			list.add(element.getYwflMc());
			list.add(element.getYwhjMc());
			list.add(element.getWsMc());
			list.add(element.getXmflmc());
			list.add(element.getZssx());
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
	
	public String initSpjcMx() throws Exception {
		((QySpwsSplcszService)getService()).initSpjcMx(getDomain(), getUserDomain());
		
		return "initSpjcMx";
	}
	
	public String saveSpjc() throws Exception {
		((QySpwsSplcszService) getService()).saveSpjc(getDomain(), getUserDomain());
		
		return "saveSpjc";
	}
	
	public String deleteSplcZb() throws Exception {
		((QySpwsSplcszService) getService()).deleteSplcZb(getDomain(), getUserDomain());
		super.createMessageSucc();
		
		return "deleteSplcZb";
	}
	
	public String queryQySpwsXmflbz() throws Exception {
		((QySpwsSplcszService) getService()).queryQySpwsXmflbzByKey(getDomain(), getUserDomain());
		
		return "queryQySpwsXmflbz";
	}
	
	public String cxszSplc() throws Exception {
		((QySpwsSplcszService) getService()).saveMx(getDomain(), getUserDomain());
		
		return "cxszSplc";
	}
	
	public String qxszSplc() throws Exception {
		((QySpwsSplcszService) getService()).deleteMx(getDomain(), getUserDomain());
		
		return "qxszSplc";
	}
	
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QySpwsSplcszDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QySpwsSplcszDomain) domain;
	}

	@Resource(name = "qySpwsSplcszServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
