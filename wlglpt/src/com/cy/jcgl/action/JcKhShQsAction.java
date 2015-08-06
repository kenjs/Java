package com.cy.jcgl.action;

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
import com.cy.jcgl.domain.JcKhShQsDomain;
import com.cy.jcgl.domain.JcPcxxglDomain;

/**
 * THE ACTION FOR 货运-派车信息管理
 * @author LYY
 * time  2013-5-4
 */
@Controller
@Scope("prototype")
@Action(value = "/jcgl/khshqsbh", results = {
		@Result(name = "init", location = "/work/jcgl/jc_khshqs.jsp"),
		@Result(name = "query", location = "/work/jcgl/jc_khshqs.jsp"),
		@Result(name = "initMx", location = "/work/jcgl/jc_pcxxcx_mx.jsp")
		})
@SuppressWarnings("unchecked")
public class JcKhShQsAction extends ExtendAction {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JcPcxxglDomain domain = (JcPcxxglDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("派车单号");
		headList.add("类别");
		headList.add("装车");
		headList.add("车辆号码");
		headList.add("挂车号码");
		headList.add("司机");
		headList.add("运费");
		headList.add("转入部门");
		headList.add("订单编号");
		headList.add("客户名称");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("货物名称");
		headList.add("包装");
		headList.add("数量");
		headList.add("重量");
		headList.add("体积");
		headList.add("发货人");
		headList.add("要求发货日期");
		headList.add("收货人");
		headList.add("要求到达日期");
		headList.add("派车人");
		headList.add("派车日期");
		headList.add("派车部门");
		headList.add("所属机构");
		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			JcPcxxglDomain element = (JcPcxxglDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getPcdh());
			list.add(element.getPcfsMc());
			list.add(element.getZcfxMc());
			list.add(element.getCyrClhm());
			list.add(element.getCyrGchm());
			list.add(element.getCyrSjxm());
			list.add(element.getYfHj());
			list.add(element.getYfHj());
			list.add(element.getZrbmMc());
			list.add(element.getDingdan());
			list.add(element.getFhrMc());
			list.add(element.getSfdMc());
			list.add(element.getMddMc());
			list.add(element.getHwMc());
			list.add(element.getHwbz());
			list.add(element.getSl());
			list.add(element.getZl());
			list.add(element.getTj());
			list.add(element.getFhrMc());
			list.add(element.getYqFhrq());
			list.add(element.getShrMc());
			list.add(element.getYqDdrq());
			list.add(element.getPcrMc());
			list.add(element.getPcrq());
			list.add(element.getPcJgbmMc());
			list.add(element.getSsJgbmMc());
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	
	@Resource(name = "jcKhShQsServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new JcKhShQsDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
	
