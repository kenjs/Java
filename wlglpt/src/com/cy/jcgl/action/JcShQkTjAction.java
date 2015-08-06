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
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.domain.JcShQkTjDomain;
import com.cy.jcgl.service.JcPcxxglService;

/**
 * THE ACTION FOR 货运-派车信息管理
 * @author LYY
 * time  2013-5-4
 */
@Controller
@Scope("prototype")
@Action(value = "/jcgl/shqktj", results = {
		@Result(name = "init", location = "/work/jcgl/jc_shqktj.jsp"),
		@Result(name = "query", location = "/work/jcgl/jc_shqktj.jsp"),
		@Result(name = "initMx", location = "/work/jcgl/jc_pcxxcx_mx.jsp")
		})
@SuppressWarnings("unchecked")
public class JcShQkTjAction extends ExtendAction {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JcShQkTjDomain domain = (JcShQkTjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("客户名称");
		headList.add("本月重量（吨）");
		headList.add("本月体积（方）");
		headList.add("本月小计");
		headList.add("本月现付");
		headList.add("本月到付");
		headList.add("本月月结");
		headList.add("本月回扣");
		headList.add("本年重量（吨）");
		headList.add("本年体积（方）");
		headList.add("本年小计");
		headList.add("本年现付");
		headList.add("本年到付");
		headList.add("本年月结");
		headList.add("本年回扣");
		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			JcShQkTjDomain element = (JcShQkTjDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getKhMc());
			list.add(element.getByZl());
			list.add(element.getByTj());
			list.add(element.getByXj());
			list.add(element.getByXf());
			list.add(element.getByDj());
			list.add(element.getByHf());
			list.add(element.getByHk());
			list.add(element.getBnZl());
			list.add(element.getBnTj());
			list.add(element.getBnXj());
			list.add(element.getBnXf());
			list.add(element.getBnDj());
			list.add(element.getBnHf());
			list.add(element.getBnHk());
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	public String initViewMx() throws Exception {
		((JcPcxxglService)getService()).initMx(getDomain(), getUserDomain());
		return "initViewMx";
	}
	
	@Resource(name = "jcShQkTjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new JcShQkTjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
	
