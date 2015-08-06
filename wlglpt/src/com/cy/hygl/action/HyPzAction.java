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
import com.cy.hygl.domain.HyPzDomain;
import com.cy.hygl.service.HyPcxxglService;
import com.cy.hygl.service.HyPzService;

/**
 * THE ACTION FOR 货运-配载
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/ddpzxxgl", results = {
		@Result(name = "init", location = "/work/hygl/ddpzxxgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/ddpzxxgl_mx.jsp"),
		@Result(name = "onQingdan", location = "/work/hygl/pz_qingdan.jsp"),
		@Result(name = "viewPzXx", location = "/work/hygl/hy_pz_view.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "saveWfhxx4Pz", type = "json"),
		@Result(name = "queryPzHwxx", type = "json"),
		@Result(name = "queryPz4View", type = "json"),
		@Result(name = "deleteWfhxx4Pz", type = "json"),
		@Result(name = "initPcxxFromPz", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyPzAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyPzDomain domain = (HyPzDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("货站");
		headList.add("车辆型号");
		headList.add("配载_承重(吨)");
		headList.add("配载_体积(方)");
		
		headList.add("配载收入");
		headList.add("客户");
		headList.add("货物名称");
		headList.add("始发地");
		headList.add("目的地");
		
		headList.add("数量");
		headList.add("重量");
		headList.add("体积");
		headList.add("包装");
		headList.add("发货地址");
		
		headList.add("要求发货日期");
		headList.add("收货人");
		headList.add("收货地址");
		headList.add("要求到达日期");
		

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyPzDomain element = (HyPzDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getHzmc());
			list.add(element.getClxh());
			list.add(element.getPzCz());
			list.add(element.getPzTj());
			
			list.add(element.getPzsr());
			list.add(element.getFhrMc());
			list.add(element.getHwMc());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			
			list.add(element.getSl());
			list.add(element.getTj());
			list.add(element.getZl());
			list.add(element.getHwbz());
			list.add(element.getFhrDz());
			
			list.add(element.getYqFhrq());
			list.add(element.getShrMc());
			list.add(element.getShrDz());
			list.add(element.getYqDdrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String saveWfhxx4Pz() throws Exception {
		((HyPzService)getService()).saveWfhxx4Pz(getDomain(), getUserDomain());
		return "saveWfhxx4Pz";
	}
	
	/**
	 * 
	* @Description: 检索配载单对应的货物信息 
	* @Note
	* @author 
	* @since 2013-5-20
	* @return
	* @throws Exception
	 */
	public String queryPzHwxx() throws Exception {
		((HyPzService)getService()).queryPzHwxx(getDomain(), getUserDomain());
		return "queryPzHwxx";
	}
	
	public String deleteWfhxx4Pz() throws Exception {
		((HyPzService)getService()).deleteWfhxx4Pz(getDomain(), getUserDomain());
		return "deleteWfhxx4Pz";
	}
	
	public String initPcxxFromPz() throws Exception {
		((HyPzService)getService()).initPcxxFromPz(getDomain(), getUserDomain());
		return "initPcxxFromPz";
	}
	
	public String onQingdan() throws Exception{
		((HyPzService)getService()).onQingdan(getDomain(), getUserDomain());
		return "onQingdan";
	}
	
	/**
	 * 配载预览
	 * @return
	 * @throws Exception
	 */
	public String viewPzXx() throws Exception {
		((HyPzService)getService()).viewPz(getDomain());
		return "viewPzXx";
	}
	
	/**
	 * 检索配载货物
	 * @return
	 * @throws Exception
	 */
	public String queryPz4View() throws Exception {
		((HyPzService)getService()).queryPzHw4View(getDomain());
		return "queryPz4View";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyPzDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyPzDomain) domain;
	}

	@Resource(name = "hyPzServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
