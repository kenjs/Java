package com.cy.bggl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.bggl.domain.BgMpjDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR 办公管理 名片夹
 * 
 * @author 闫伟
 * @date 2013.1.22
 */

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "bggl/bgmpj",
		results = { 
		@Result(name = "initMx", location = "/work/bggl/bggl_mpj_mx.jsp"),
		@Result(name = "init", location = "/work/bggl/bggl_mpj.jsp"), 
		@Result(name = "query", type = "json"), 
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json") })
public class BgMpjAction extends ExtendAction {

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgMpjDomain domain = (BgMpjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序列");
		headList.add("名称");
		headList.add("公司");
		headList.add("地址");
		headList.add("电话");
		headList.add("邮编");
		headList.add("E-mail");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");
		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			BgMpjDomain element = (BgMpjDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getXm());
			list.add(element.getGs());
			list.add(element.getDz());
			list.add(element.getDh());
			list.add(element.getYb());
			list.add(element.getDy());
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

	@Resource(name = "bgMpjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new BgMpjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
