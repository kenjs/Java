package com.cy.cwgl.action;

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
import com.cy.cwgl.domain.CwZjrbDomain;
import com.cy.cwgl.service.CwZjrbService;

/**
 * THE ACTION FOR 财务-资金日报
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/cwgl/cwzjrb", results = {
		@Result(name = "init", location = "/work/cwgl/cwzjrb.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", type = "json"),
		@Result(name = "cxTjMx", type = "json"),
		@Result(name = "qtsrMx", location = "/work/cwgl/cwqtsr_mx.jsp"),
		@Result(name = "initSzMx", location = "/work/cwgl/lsjlsz_mx.jsp"),
		@Result(name = "initXxSzMx", location = "/work/cwgl/zjrb_szmx.jsp"),
		@Result(name = "querySzMx", type = "json"),
		@Result(name = "saveQtsr", type = "json"),
		@Result(name = "deleteQtsr", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class CwZjrbAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	public String cxTjMx()throws Exception{
		UserDomain userDomain = getUserDomain();
		((CwZjrbService)getService()).cxTjMx(domain, userDomain);
		return "cxTjMx";
	}
	public String qtsrMx()throws Exception{
		UserDomain userDomain = getUserDomain();
		((CwZjrbService)getService()).qtsrMx(domain, userDomain);
		return "qtsrMx";
	}
	public String initSzMx()throws Exception{
		return "initSzMx";
	}
	public String initXxSzMx()throws Exception{
		return "initXxSzMx";
	}
	public String querySzMx()throws Exception{
		UserDomain userDomain = getUserDomain();
		((CwZjrbService)getService()).querySzMx(domain, userDomain);
		return "querySzMx";
	}
	public String saveQtsr()throws Exception{
		UserDomain userDomain = getUserDomain();
		((CwZjrbService)getService()).doMySaveQtsr(domain, userDomain);
		return "saveQtsr";
	}
	public String deleteQtsr()throws Exception{
		UserDomain userDomain = getUserDomain();
		((CwZjrbService)getService()).deleteQtsr(domain,userDomain);
		return "deleteQtsr";
	}
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		CwZjrbDomain domain = (CwZjrbDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
//		headList.add("财务登记序号(SEQ_CW_DJXH)");
//		headList.add("所属机构");
//		headList.add("昨日库存现金");
//		headList.add("昨日库存银行");
//		headList.add("库存现金");
//		headList.add("库存银行");
//		headList.add("收入现金");
//		headList.add("收入银行");
//		headList.add("支出现金");
//		headList.add("支出银行");
//		headList.add("库存现金输入值");
//		headList.add("库存银行输入值");
//		headList.add("收入现金输入值");
//		headList.add("收入银行输入值");
//		headList.add("支出现金输入值");
//		headList.add("支出银行输入值");
//		headList.add("日期");
//		headList.add("创建人");
//		headList.add("创建日期");
//		headList.add("修改人");
//		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			CwZjrbDomain element = (CwZjrbDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
//			list.add(element.getCwDjxh());
//			list.add(element.getSsJgbm());
//			list.add(element.getZrKcXj());
//			list.add(element.getZrKcYh());
//			list.add(element.getKcXj());
//			list.add(element.getKcYh());
//			list.add(element.getSrXj());
//			list.add(element.getSrYh());
//			list.add(element.getZcXj());
//			list.add(element.getZcYh());
//			list.add(element.getKcXjSrz());
//			list.add(element.getKcYhSrz());
//			list.add(element.getSrXjSrz());
//			list.add(element.getSrYhSrz());
//			list.add(element.getZcXjSrz());
//			list.add(element.getZcYhSrz());
//			list.add(element.getRq());
//			list.add(element.getCjrMc());
//			list.add(element.getCjrq());
//			list.add(element.getXgrMc());
//			list.add(element.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new CwZjrbDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (CwZjrbDomain) domain;
	}

	@Resource(name = "cwZjrbServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
