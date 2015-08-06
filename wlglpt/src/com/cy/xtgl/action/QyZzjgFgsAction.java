package com.cy.xtgl.action;

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

import com.cy.xtgl.domain.QyZzjgDomain;


/**
 * THE ACTION FOR 企业-组织机构 分公司
 * @author 闫伟
 * @date 2013.1.7
*/ 	

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "xtgl/qyzzjg", 
		results = {
		@Result(name = "initMx", location = "/work/xtgl/xtgl_fgs_mx.jsp"),
		@Result(name = "init", location = "/work/xtgl/xtgl_fgs.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "saveDisable", type = "json"),
		@Result(name = "saveEnable", type = "json"),
		@Result(name = "delete", type = "json")})

public class QyZzjgFgsAction extends ExtendAction {
	
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyZzjgDomain domain = (QyZzjgDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("名称");
		headList.add("简称");
		headList.add("地址");
		headList.add("电话");
		headList.add("邮编");
		headList.add("负责人");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");
		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyZzjgDomain element = (QyZzjgDomain) e;
			List list = new ArrayList();
			list.add(i+++ "");
			list.add(element.getMc());
			list.add(element.getJc());
			list.add(element.getDz());
			list.add(element.getDh());
			list.add(element.getYb());
			list.add(element.getFzr());
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
	
		@Resource(name = "qyZzjgFgsServiceImp")	
		public void setService(BaseBusinessService service) {
			super.setService(service);
		}

		public BaseBusinessDomain getDomain() {
			if(domain==null)
			{
				domain=new QyZzjgDomain();
			}
			return domain;
		}
		public void setDomain(BaseBusinessDomain domain) {
			this.domain = domain;
		}
}

