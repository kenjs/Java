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
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.service.HyJsglPsfqrService;

/**
 * 
 */
@Controller		
@Scope("prototype")	
@Action(value = "/hygl/jspsfqr", results = { 
		@Result(name = "init", location = "/work/hygl/hy_psfqr.jsp"),
		@Result(name = "initMx", location = "/work/hygl/hy_psfqr_mx.jsp"),
		@Result(name = "viewMx", location = "/work/hygl/hy_psfqr_mx_readonly.jsp"),
		@Result(name = "save", type = "json"),		
		@Result(name = "delete", type = "json"),	
		@Result(name = "plqr", type = "json"),
		@Result(name = "print", location = "/work/hygl/hy_psfqr_print.jsp"),
		@Result(name = "query", type = "json")})
public class HyJsglPsfqrAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	public String view() throws Exception{
		this.getService().initMx(this.getDomain(), this.getUserDomain());
		return "view";
	}

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyPcxxglDomain domain = (HyPcxxglDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();

		headList.add("序号");
		headList.add("派车单号");
		headList.add("派车日期");
		headList.add("订单编号");
		headList.add("客户名称");
		headList.add("货物名称");
		headList.add("收货人");
		headList.add("下游单位");
		headList.add("数量");
		headList.add("重量");
		headList.add("体积");
		headList.add("车辆信息");
		headList.add("运费");
		headList.add("预付");
		headList.add("收入");
		headList.add("到付");
		headList.add("重量");
		headList.add("体积");
		headList.add("送货方式");
		headList.add("配送费");
		headList.add("是否确认");

		headList.add("派车人");
		
		headList.add("派车部门");
		headList.add("所属机构");


		heads.add(headList);

		int i = 1;
		String pcdh = "";
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyPcxxglDomain element = (HyPcxxglDomain) e;
			List list = new ArrayList();
			
			list.add(i++ + "");
			list.add(element.getPcdh());
			list.add(element.getPcrq());
			list.add(element.getDingdan());
			list.add(element.getFhrMc());
			list.add(element.getHwMc());
			list.add(element.getShrMc());
			list.add(element.getZrbmMc());
			list.add(StringUtils.isBlank(element.getSl())?"":Double.parseDouble(element.getSl()));
			list.add(StringUtils.isBlank(element.getZl())?"":Double.parseDouble(element.getZl()));
			list.add(StringUtils.isBlank(element.getTj())?"":Double.parseDouble(element.getTj()));
			list.add(element.getClxx());
			
			if (!pcdh.equals(element.getPcdh())) {
				if (StringUtils.isNotBlank(element.getYfhjyf())) {
					String[] yfhjyf = element.getYfhjyf().split("/");
					if (yfhjyf.length >= 1) {
						list.add(StringUtils.isBlank(yfhjyf[0])?"":Double.parseDouble(yfhjyf[0]));
					}else {
						list.add("");
					}
					if (yfhjyf.length >= 2) {
						list.add(StringUtils.isBlank(yfhjyf[1])?"":Double.parseDouble(yfhjyf[1]));
					}else {
						list.add("");
					}
				}else {
					list.add("");
					list.add("");
				}
				if (StringUtils.isNotBlank(element.getSrhjdf())) {
					String[] srhjdf = element.getSrhjdf().split("/");
					if (srhjdf.length >= 1) {
						list.add(StringUtils.isBlank(srhjdf[0])?"":Double.parseDouble(srhjdf[0]));
					}else {
						list.add("");
					}
					if (srhjdf.length >= 2) {
						list.add(StringUtils.isBlank(srhjdf[1])?"":Double.parseDouble(srhjdf[1]));
					}else {
						list.add("");
					}
				}else {
					list.add("");
					list.add("");
				}
				
				list.add(StringUtils.isBlank(element.getZcZl())?"":Double.parseDouble(element.getZcZl()));
				list.add(StringUtils.isBlank(element.getZcTj())?"":Double.parseDouble(element.getZcTj()));
				
				pcdh = element.getPcdh();
			}else {
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
			}
			
			list.add(element.getZs());
			list.add(StringUtils.isBlank(element.getSrPsf())?"":Double.parseDouble(element.getSrPsf()));
			
			if("Y".equals(element.getSfQr())){			
				if("自提".equals(element.getZs())){
					list.add("");
				}else{
					list.add("已确认");
				}
			}else{
				if("自提".equals(element.getZs())){
					list.add("");
				}else{
					list.add("未确认");
				}
			}
			
			list.add(element.getPcrMc());
			list.add(element.getPcJgbmMc());
			list.add(element.getSsJgbmMc());


			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String viewMx() throws Exception {
		((HyJsglPsfqrService)getService()).viewMx(getDomain());
		return "viewMx";
	}
	
	public String plqr() throws Exception {
		((HyJsglPsfqrService)getService()).plqr(getDomain(),getUserDomain());
		return "plqr";
	}
	
	//打印预览
	public String dyyl() throws Exception{
		super.download();
		return "print";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyPcxxglDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyPcxxglDomain) domain;
	}

	@Resource(name = "hyJsglPsfqrServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
