package com.cy.bggl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.cy.bggl.domain.BgQdqtDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * THE ACTION FOR 办公签到签退 上班签到
 * @author 闫伟
 * @date 2013.1.22
*/ 

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "bggl/bgsbqd", 
		results = {
		@Result(name = "initMx", location = "/work/bggl/bggl_sbqd_mx.jsp"),
		@Result(name = "init", location = "/work/bggl/bggl_sbqd.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
public class BgSbqdAction extends ExtendAction {
	
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgQdqtDomain domain = (BgQdqtDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序号");
		headList.add("日期");
		headList.add("应签退时间");
		headList.add("实际签退时间");
		headList.add("是否迟到");
		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			BgQdqtDomain element = (BgQdqtDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getRq());
			list.add(element.getYqdSj());
			list.add(element.getSjQdqtSj());
			list.add(element.getTagg());
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	@Resource(name = "bgSbqdServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new BgQdqtDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
