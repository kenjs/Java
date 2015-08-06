package com.cy.hygl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import com.cy.hygl.domain.HyWlSsDjGlDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.HyWlSsDjService;


/**
 * THE ACTION FOR 货运-模版-物流损失(查询，mx页面为审核审批件)
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/wlssdjgl", results = {
		@Result(name = "init", location = "/work/hygl/hy_wlssdjgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_wlssdjgl_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "getHw", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "checkTemplateName", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyWlSsDjGlAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyWlSsDjGlDomain domain = (HyWlSsDjGlDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();

		headList.add("序号");
		headList.add("状态");
		headList.add("订单编号");
		headList.add("客户名称");
		headList.add("货物名称");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("转入部门");
		headList.add("结算数量");
		headList.add("发货地址");
		headList.add("收货地址");
		headList.add("金额");
		
		headList.add("损失原因");
		headList.add("派车单号");
		headList.add("车辆号码");
		headList.add("挂车号码");
		headList.add("司机");
		headList.add("运费");
		headList.add("预付");
		headList.add("派车人");
		headList.add("派车日期");
		headList.add("派车部门");
		headList.add("业务单位");

	

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyWlSsDjGlDomain element = (HyWlSsDjGlDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			if(StringUtils.isNotBlank(element.getWlssDjxh())){
				list.add("已登记");
			}else{
				list.add("未登记");
			}
			list.add(element.getDdbh());
			list.add(element.getKhmc());
			list.add(element.getHwMc());
			list.add(element.getFhrXzqhMc());
			
			list.add(element.getShrXzqhMc());
			list.add(element.getZrbmMc());
			list.add(element.getJsSl());
			list.add(element.getFhrDz());
			list.add(element.getShrDz());
			
			list.add(element.getJe()==null?"":Double.parseDouble(element.getJe()));
			list.add(element.getWlssyyMc());
			list.add(element.getPcdh());
			list.add(element.getCyrClhm());
			list.add(element.getCyrGchm());
			
			list.add(element.getCyrSjxm());
			list.add(element.getYfHj());
			list.add(element.getYfYfYf());
			list.add(element.getPcrMc());
			list.add(element.getPcrQ());
			list.add(element.getPcJgmc());
			list.add(element.getSsJgmc());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String getHw() throws Exception{
		HyWlSsDjGlDomain domain=(HyWlSsDjGlDomain)getDomain();
		//((HyWlSsDjService)getService()).getHw(domain,getUserDomain());
		return "getHw";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyWlSsDjGlDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyMbTydDomain) domain;
	}

	@Resource(name = "hyWlSsDjGlServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
