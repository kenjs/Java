package com.cy.zygl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.domain.QyKhHwxhDomain;

/**
 * THE ACTION FOR 企业-客户-货物型号信息
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qykhhwxh", results = {
		@Result(name = "init", location = "/work/zygl/qykhhwxh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qykhhwxh_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyKhHwxhAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyKhHwxhDomain domain = (QyKhHwxhDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("客户名称");
		headList.add("货物名称");
		headList.add("货物型号名称");
		headList.add("货物型号简称");
		headList.add("货物型号全称");
//		headList.add("拼音全称");
//		headList.add("拼音简称");
		headList.add("长度计量单位");
		headList.add("长度");
		headList.add("宽度");
		headList.add("高度");
		headList.add("包装计量单位");
//		headList.add("折结算计量单位换算比例");
//		headList.add("包装折成本计量单位换算比例");
		headList.add("结算计量单位");
		headList.add("成本计量单位");
//		headList.add("备注");
//		headList.add("登记部门");
//		headList.add("登记人");
//		headList.add("登记日期");
//		headList.add("启用标志(Y/N)");
//		headList.add("有效标志(Y/N)");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyKhHwxhDomain element = (QyKhHwxhDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getKhmc());
			list.add(element.getHwmc());
			list.add(element.getXhmc());
			list.add(element.getXhjc());
			list.add(element.getXhqc());
//			list.add(element.getPyqc());
//			list.add(element.getPyjc());
			list.add(element.getCdJldwMc());
			list.add(element.getCd());
			list.add(element.getKd());
			list.add(element.getGd());
			list.add(element.getBzJldwMc());
//			list.add(element.getBzJsHsbl());
//			list.add(element.getBzCbHsbl());
			list.add(element.getJsJldwMc());
			list.add(element.getCbJldwMc());
//			list.add(element.getBz());
//			list.add(element.getDjJgbm());
//			list.add(element.getDjrCzyDjxh());
//			list.add(element.getDjrq());
//			list.add(element.getQybz());
//			list.add(element.getYxbz());
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
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyKhHwxhDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyKhHwxhDomain) domain;
	}

	@Resource(name = "qyKhHwxhServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
