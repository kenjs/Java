package com.cy.zygl.action;

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
import com.cy.zygl.domain.QyFbsDjxxDomain;
import com.cy.zygl.domain.QyKhJsjgDomain;
import com.cy.zygl.service.QyClxhwhService;
import com.cy.zygl.service.QyKhJsjgService;

/**
 * THE ACTION FOR 企业-分包商-登记信息
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qykhjsjg", results = {
		@Result(name = "init", location = "/work/zygl/qykhjsjg.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "saveCheck", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qykhjsjg_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyKhJsjgAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyKhJsjgDomain domain = (QyKhJsjgDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("所属机构");
		headList.add("客户名称");
		headList.add("是否使用全部货物");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("货物名称");
		headList.add("价格计算公式");
		headList.add("价格计算系统公式");
		headList.add("有效期起");
		headList.add("有效期止");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);
		String str="";
		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyKhJsjgDomain element = (QyKhJsjgDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getSjMc());
			list.add(element.getKhMc());
			if(element.getSyfw().equals("Y")){
				str="是";
			}
			else{
				str="否";
			}
			list.add(str);
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getHwMc());
			list.add(element.getJgjsgs());
			list.add(element.getXtgs());
			list.add(element.getYxqQ());
			list.add(element.getYxqZ());
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
	
	public String saveCheck() throws Exception{
		((QyKhJsjgService)getService()).saveCheck(getDomain());
		return "saveCheck";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyKhJsjgDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyKhJsjgDomain) domain;
	}

	@Resource(name = "qyKhJsjgServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
