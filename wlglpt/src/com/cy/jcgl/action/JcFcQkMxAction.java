package com.cy.jcgl.action;

import java.text.SimpleDateFormat;
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
import com.cy.jcgl.domain.JcFcQkMxDomain;

/**
 * THE ACTION FOR 货运-派车信息管理
 * @author LYY
 * time  2013-5-4
 */
@Controller
@Scope("prototype")
@Action(value = "/jcgl/fcqkmx", results = {
		@Result(name = "init", location = "/work/jcgl/jc_fcqkmx.jsp"),
		@Result(name = "query", type = "json")
		})
@SuppressWarnings("unchecked")
public class JcFcQkMxAction extends ExtendAction {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JcFcQkMxDomain domain = (JcFcQkMxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//序号、派车单号、承运单位、派车人、派车日期】、托运单号、客户单位、下单日期、到站、
		//收货方、货物名称、数量、重量、收款方式、回单号、【司机运费、预付运费、审批状态
		headList.add("序号");
		headList.add("派车单号");
		headList.add("承运单位");
		headList.add("派车人");
		headList.add("派车日期");
		headList.add("托运单号");
		headList.add("客户单位");
		headList.add("下单日期");
		headList.add("到站");
		headList.add("收货方");
		headList.add("货物名称");
		headList.add("数量");
		headList.add("重量");
		headList.add("收款方式");
		headList.add("回单号");
		headList.add("司机运费");
		headList.add("预付运费");
		headList.add("状态");
		heads.add(headList);
		
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			JcFcQkMxDomain element = (JcFcQkMxDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getPcdh());
			list.add(element.getClhm());
			list.add(element.getPcrMc());
			if(StringUtils.isNotBlank(element.getPcrq())){
				list.add(sdf.format(sdf.parse(element.getPcrq())));
			}else{
				list.add("");
			}
			list.add(element.getTydh());
			list.add(element.getKhmc());
			if(StringUtils.isNotBlank(element.getXdrq())){
				list.add(sdf.format(sdf.parse(element.getXdrq())));
			}else{
				list.add("");
			}	
			list.add(element.getShrDz());
			list.add(element.getShrMc());
			list.add(element.getHwmc());
			list.add(element.getSl());
			list.add(element.getZl());
			list.add(element.getSkfs());
			list.add(element.getHdh());
			list.add(element.getSjyf());
			list.add(element.getYfyf());
			list.add(element.getSpzt());
			
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	@Resource(name = "jcFcQkMxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new JcFcQkMxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
	
