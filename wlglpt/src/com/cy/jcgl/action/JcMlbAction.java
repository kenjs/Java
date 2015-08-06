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
import com.cy.jcgl.domain.JcYxsjbDomain;

/**
 * THE ACTION
 * 
 */
@Controller
@Scope("prototype")
@Action(value = "/jcgl/jymlb", results = {
		@Result(name = "init", location = "/work/jcgl/jc_jymlb.jsp"),
		@Result(name="query",type="json")
		})
@SuppressWarnings("unchecked")
public class JcMlbAction extends ExtendAction {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JcYxsjbDomain domain = (JcYxsjbDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("单位名称");
		headList.add("客户名称");
		headList.add("下单日期");
		headList.add("托单号");
		headList.add("车辆号码");
		headList.add("目的地");
		headList.add("回单号");
		headList.add("收入");
		headList.add("回扣");
		headList.add("运输费");
		headList.add("提货费");
		headList.add("配送费");
		headList.add("物流损失收入");
		headList.add("物流损失支出");
		headList.add("毛利");
		headList.add("毛利率");
		
		heads.add(headList);
		int i = 0;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			JcYxsjbDomain element = (JcYxsjbDomain) e;
			List list = new ArrayList();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = "";
			if(element.getXdrq() != null){
				dateStr = sdf.format(sdf.parse(element.getXdrq()));
			}
			if(i==0){
				list.add("合计");
				i++;
			}else{
				list.add(i+++ "");
			}
			list.add(element.getSsjgMc());
			list.add(element.getKhmc());
			
			list.add(dateStr);
			list.add(element.getDdbh());
			list.add(element.getClhm());
			list.add(element.getMdd());
			list.add(element.getHdbh());
			
			list.add(StringUtils.isBlank(element.getZsr())?"":Double.parseDouble(element.getZsr()));
			list.add(StringUtils.isBlank(element.getHk())?"":Double.parseDouble(element.getHk()));
			list.add(StringUtils.isBlank(element.getYsf())?"":Double.parseDouble(element.getYsf()));
			list.add(StringUtils.isBlank(element.getThf())?"":Double.parseDouble(element.getYsf()));
			list.add(StringUtils.isBlank(element.getPsf())?"":Double.parseDouble(element.getPsf()));
			list.add(StringUtils.isBlank(element.getWlssSr())?"":Double.parseDouble(element.getWlssSr()));
			list.add(StringUtils.isBlank(element.getWlssZc())?"":Double.parseDouble(element.getWlssZc()));
			list.add(element.getMl());
			list.add(element.getMll());
			
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	@Resource(name = "jcJymlbServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new JcYxsjbDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
	
