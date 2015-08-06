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
import com.cy.zygl.domain.QyFbsJsjgDomain;
import com.cy.zygl.service.QyFbsJsjgService;

/**
 * THE ACTION FOR 企业-分包商-结算价格
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qyfbsjsjg", results = {
		@Result(name = "init", location = "/work/zygl/qyfbsjsjg.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qyfbsjsjg_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "checkSave", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyFbsJsjgAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyFbsJsjgDomain domain = (QyFbsJsjgDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
//		headList.add("结算价格登记序号(SEQ_ZY_DJXH)");
//		headList.add("机构编码(SEQ_JG_DJXH)");
//		headList.add("分包商登记序号");
//		headList.add("路线登记序号");
		headList.add("状态");
		headList.add("路线登记名称");
		headList.add("结算计量单位");
		headList.add("单价");
		headList.add("有效期起");
		headList.add("有效期止");
		headList.add("备注");
//		headList.add("登记部门");
//		headList.add("登记人");
//		headList.add("登记日期");
//		headList.add("有效标志(Y/N)");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyFbsJsjgDomain element = (QyFbsJsjgDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
//			list.add(element.getJsjgDjxh());
//			list.add(element.getSsJgbm());
//			list.add(element.getFbsDjxh());
//			list.add(element.getLxDjxh());
//			list.add(element.getJsJldwDm());
			if("1".equals(element.getZt())){
				list.add("已过期");
			}else{
				list.add("未过期");
			}
			list.add(element.getLxDjmc());
			list.add(element.getJsJldwMc());
			list.add(element.getDj());
			list.add(element.getYxqQ());
			list.add(element.getYxqZ());
			list.add(element.getBz());
//			list.add(element.getDjJgbm());
//			list.add(element.getDjrCzyDjxh());
//			list.add(element.getDjrq());
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
	
	public String checkSave() throws Exception {
		QyFbsJsjgService qyservice = (QyFbsJsjgService) getService();
		qyservice.checkSave(getDomain());
		return "checkSave";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyFbsJsjgDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyFbsJsjgDomain) domain;
	}

	@Resource(name = "qyFbsJsjgServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
