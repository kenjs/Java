package com.cy.hygl.action;

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
import com.cy.hygl.domain.HyMbTydDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.HyWlSsDjService;


/**
 * THE ACTION FOR 货运-模版-物流损失（明细，查询用做对账时查看）
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/wlssdj", results = {
		@Result(name = "init", location = "/work/hygl/hywlssck.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initSsMx", location = "/work/hygl/hy_wlssdj_ssmx.jsp"),
		@Result(name = "querySsMx", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_wlssdj_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "getHw", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "deleteWlssdjMx", type = "json"),
		@Result(name = "checkWlDj", type = "json"),
		@Result(name = "toLook", location = "/work/hygl/hy_wlssdjgl_mx.jsp"),
		@Result(name = "checkTemplateName", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyWlSsDjAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyMbTydDomain domain = (HyMbTydDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("客户名称");
		headList.add("模版名称");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("发货地址");
		headList.add("收货人");
		headList.add("收货地址");
		headList.add("登记部门");
		headList.add("所属机构");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyMbTydDomain element = (HyMbTydDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getFhrMc());
			list.add(element.getMbmc());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getFhrDz());
			list.add(element.getShrMc());
			list.add(element.getShrDz());
			list.add(element.getDjJgmc());
			list.add(element.getSsJgmc());
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
	
	public String deleteWlssdjMx() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).deleteWlssdjMx(domain,getUserDomain());
		return "deleteWlssdjMx";
	}
	
	public String getHw() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).getHw(domain,getUserDomain());
		return "getHw";
	}
	
	public String initSsMx() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).initSsMx(domain,getUserDomain());
		return "initSsMx";
	}
	public String querySsMx() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).querySsMx(domain,getUserDomain());
		return "querySsMx";
	}
	public String checkWlDj() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).checkWlDj(domain,getUserDomain());
		return "checkWlDj";
	}
	
	public String toLook() throws Exception{
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		((HyWlSsDjService)getService()).toLook(domain,getUserDomain());
		return "toLook";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyZyglFydjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyMbTydDomain) domain;
	}

	@Resource(name = "hyWlSsDjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
