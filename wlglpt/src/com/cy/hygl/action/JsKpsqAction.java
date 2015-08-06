package com.cy.hygl.action;

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
import com.cy.common.service.BaseBusinessService;
import com.cy.hygl.domain.JsKpsqDomain;
import com.cy.hygl.service.JsKpsqService;
import com.cy.hygl.service.imp.JsKpsqServiceImp;

/**
 * THE ACTION FOR 开票申请
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/jskpsq", results = {
		@Result(name = "init", location = "/work/hygl/jskpsq.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "querySrKpMxInit", location = "/work/hygl/jskpsq_srkp_mx_query.jsp"),
		@Result(name = "querySrKpMx", location = "/work/hygl/jskpsq_srkp_mx_query.jsp"),
		@Result(name = "initMx", location = "/work/hygl/jskpsq_mx.jsp"),
		@Result(name = "initSrKpMx", location = "/work/hygl/jskpsq_srkp_mx.jsp"),
		@Result(name = "initDzKpMx", location = "/work/hygl/jskpsq_dzkp_mx.jsp"),
		@Result(name = "initMxCk", location = "/work/hygl/jskpsq_mxck.jsp"),
		@Result(name = "initDzKpMxCk", location = "/work/hygl/jskpsq_dzkp_mxck.jsp"),
		@Result(name = "queryMx", location = "/work/hygl/jskpsq_mx_query.jsp"),
		@Result(name = "updateMx", location = "/work/hygl/jskpsq_dzkp_mx_update.jsp"),
		@Result(name = "deleteSqKpTemp", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "saveMx", type = "json"),
		@Result(name = "savaSrKpMxTemp", type = "json"),
		@Result(name = "deleteMx", type = "json"),
		@Result(name = "deleteSrKpMx", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class JsKpsqAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	

	@Override
	public String initMx() throws Exception {
		super.initMx();
		JsKpsqDomain domain = (JsKpsqDomain) getDomain();
		if("2".equals(domain.getKpsqfsDm()))
			return "initMx"; 
		else if("3".equals(domain.getKpsqfsDm())){
			return "initSrKpMx";
		}
		else
			return "initDzKpMx";
	}
	
	public String querySrKpMx()throws Exception{
		JsKpsqDomain domain = (JsKpsqDomain) getDomain();
		JsKpsqService service=(JsKpsqService)getService();
		service.querySrKpMx(domain);
		return "querySrKpMx";
	}
	
	public String querySrKpMxInit() throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) getDomain();
		JsKpsqService service=(JsKpsqService)getService();
		//service.querySrKpMx(domain);
		return "querySrKpMxInit";
	}
	
	public String deleteSqKpTemp()throws Exception{
		JsKpsqDomain domain = (JsKpsqDomain) getDomain();
		JsKpsqService service=(JsKpsqService)getService();
		service.deleteSqKpTemp(domain);
		return "deleteSqKpTemp";
	}
	
	public String deleteSrKpMx()throws Exception{
		JsKpsqDomain domain = (JsKpsqDomain) getDomain();
		JsKpsqService service=(JsKpsqService)getService();
		service.deleteSrKpMx(domain);
		return "deleteSrKpMx";
	}
	
	public String savaSrKpMxTemp()throws Exception{
		JsKpsqDomain domain = (JsKpsqDomain) getDomain();
		JsKpsqService service=(JsKpsqService)getService();
		service.savaSrKpMxTemp(domain);
		return "savaSrKpMxTemp";
	}
	
	public String initMxCk() throws Exception {
		super.initMx();
		JsKpsqDomain domain = (JsKpsqDomain) getDomain();
		if("2".equals(domain.getKpsqfsDm()))
			return "initMxCk"; 
		else
			return "initDzKpMxCk";
	}
	@Override
	public String queryMx() throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain) getDomain();
		String bz=domain.getDzQdXgbz();
		super.queryMx();
		domain.setDzQdXgbz(bz);
		if("Y".equals(domain.getDzQdXgbz()))
			return "updateMx"; 
		else
			return "queryMx";
	}

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JsKpsqDomain domain = (JsKpsqDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("审批");
		headList.add("申请方式");
		headList.add("客户名称");
		headList.add("申请开票金额");
		headList.add("申请开票日期");
		
		headList.add("登记人");
		headList.add("登记日期");
		headList.add("登记部门");
		headList.add("所属单位");
		headList.add("开票单位");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			JsKpsqDomain element = (JsKpsqDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getWsspztMc());
			list.add(element.getKpsqfsMc());
			list.add(element.getKhMc());
			list.add(element.getSqKpjeHj());
			list.add(element.getSqKprq());
			
			list.add(element.getCjrMc());
			list.add(element.getDjrq());
			list.add(element.getBmMc());
			list.add(element.getDwMc());
			list.add(element.getKpDwJgMc());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new JsKpsqDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (JsKpsqDomain) domain;
	}

	@Resource(name = "jsKpsqServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
