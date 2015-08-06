package com.cy.bggl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.bggl.domain.BgGzrDomain;
import com.cy.bggl.service.BgGzrService;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR 办公管理 工作日
 * 
 * @author 闫伟
 * @date 2013.1.24
 */

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "bggl/bggzr", 
		results = {
		@Result(name = "initMx", location = "/work/bggl/bggl_gzr_mx.jsp"),
		@Result(name = "init", location = "/work/bggl/bggl_gzr.jsp"), 
		@Result(name = "query", type = "json"), 
		@Result(name = "save", type = "json"),
		@Result(name = "updateGzrByJgbm", type = "json"),
		@Result(name = "delete", type = "json") })
public class BgGzrAction extends ExtendAction {

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgGzrDomain domain = (BgGzrDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序列");
		headList.add("日期");
		headList.add("节假日");
		headList.add("星期几");
		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			BgGzrDomain element = (BgGzrDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getRq());
			list.add(element.getGzrDm());
			list.add(element.getWeekdayDm());
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}

	public String updateGzrByJgbm() throws Exception {
		BgGzrService service = (BgGzrService) getService();
		service.updateGzrByJgbm(getDomain());
		return "updateGzrByJgbm";
	}

	@Resource(name = "bgGzrServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new BgGzrDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
