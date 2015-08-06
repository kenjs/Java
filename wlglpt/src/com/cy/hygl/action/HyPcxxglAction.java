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
import com.cy.hygl.domain.HyQingDanDomain;
import com.cy.hygl.service.HyPcxxglService;
import com.cy.hygl.service.HyTydglService;
/**
 * THE ACTION FOR 货运-派车信息管理
 * @author yw
 * time  2013-3-4
 */
@Controller
@Scope("prototype")
@Action(value = "/hygl/hypcxxgl", results = {
		@Result(name = "init", location = "/work/hygl/hy_pcxxgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_pcxxgl_mx.jsp"),
		@Result(name = "initViewMx", location = "/work/hygl/hy_pcxxgl_mx_readonly.jsp"),
		@Result(name = "qingdan", location = "/work/hygl/hy_qingdan.jsp"),
		@Result(name = "qingdan4SJ", location = "/work/hygl/hy_qingdan_sj.jsp"),
		@Result(name = "qingdan4XH", location = "/work/hygl/hy_qingdan_xh.jsp"),
		@Result(name = "saveWfhxx4Pc", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "deleteWfhxx4Pc", type = "json"),
		@Result(name = "queryPcHwxx", type = "json"),
		@Result(name = "modify", type = "json"),
		@Result(name = "queryPcxxSjsInitVal", type = "json"),
		@Result(name = "queryWfhxx", type = "json"),
		@Result(name = "initWfhxx4Pc", location = "/work/hygl/hy_pcxxgl_hwmx.jsp"),
		@Result(name = "updateWfhxx4Pc", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyPcxxglAction extends ExtendAction {
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyPcxxglDomain domain = (HyPcxxglDomain) getDomain();
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
		headList.add("预付");
		headList.add("收入");
		headList.add("到付");
		headList.add("重量");
		headList.add("体积");
		headList.add("送货");
		headList.add("订单编号");
		headList.add("客户名称");
		headList.add("货物名称");
		headList.add("始发地");
		headList.add("目的地");
		headList.add("下游单位");
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
		String pcdh = "";
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			HyPcxxglDomain element = (HyPcxxglDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getPcdh());
			list.add(element.getPcfsMc());
			list.add(element.getZcfxMc());
			list.add(element.getCyrClhm());
			list.add(element.getCyrGchm());
			list.add(element.getCyrSjxm());
			if (!pcdh.equals(element.getPcdh())) {
				if (StringUtils.isNotBlank(element.getYfhjyf())) {
					String[] yfhjyf = element.getYfhjyf().split("/");
					if (yfhjyf.length >= 1) {
						list.add(yfhjyf[0]==null?"":Double.parseDouble(yfhjyf[0]));
					}else {
						list.add("");
					}
					if (yfhjyf.length >= 2) {
						list.add(yfhjyf[1]==null?"":Double.parseDouble(yfhjyf[1]));
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
						list.add(srhjdf[0]==null?"":Double.parseDouble(srhjdf[0]));
					}else {
						list.add("");
					}
					if (srhjdf.length >= 2) {
						list.add(srhjdf[1]==null?"":Double.parseDouble(srhjdf[1]));
					}else {
						list.add("");
					}
				}else {
					list.add("");
					list.add("");
				}
				
				list.add(element.getZcZl()==null?"":Double.parseDouble(element.getZcZl()));
				list.add(element.getZcTj()==null?"":Double.parseDouble(element.getZcTj()));
				
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
			list.add(element.getDingdan());
			list.add(element.getFhrMc());
			list.add(element.getHwMc());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getZrbmMc());
			list.add(element.getHwbz());
			list.add(element.getSl()==null?"":Double.parseDouble(element.getSl()));
			list.add(element.getZl()==null?"":Double.parseDouble(element.getZl()));
			list.add(element.getTj()==null?"":Double.parseDouble(element.getTj()));
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
		ExcelExportPOI.createExpXls(ps,request);
		return "download";
	}
	
	@SuppressWarnings("unchecked")
	public String downloadQingDan() throws Exception {
		super.download();
		HyPcxxglDomain domain = (HyPcxxglDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序号");
		headList.add("订单编号");
		headList.add("货物名称");
		headList.add("目的地");
		headList.add("数量");
		headList.add("重量");
		headList.add("体积");
		headList.add("发货人");
		headList.add("收货人");
		headList.add("收货地址");
		headList.add("送货方式");
		headList.add("到付");
		headList.add("配送费");
		headList.add("代收货款");
		heads.add(headList);
		int i = 1;
		String pcdh = "";
		for (BaseBusinessDomain e : (List<HyQingDanDomain>) domain.getQdList()) {
			HyQingDanDomain element = (HyQingDanDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getDdbh());
	
			list.add(element.getHwMc());
			list.add(element.getMddDz());
			list.add(element.getSl()==null?"":Double.parseDouble(element.getSl()));
			list.add(element.getZl()==null?"":Double.parseDouble(element.getZl()));
			list.add(element.getTj()==null?"":Double.parseDouble(element.getTj()));
			list.add(element.getFhr());
			list.add(element.getShrMc());
			list.add(element.getShrDz());
			list.add(element.getShfsMc());
			list.add(element.getDs()==null?"":Double.parseDouble(element.getDs()));
			list.add(element.getSrPsf()==null?"":Double.parseDouble(element.getSrPsf()));
			list.add(element.getDsHk()==null?"":Double.parseDouble(element.getDsHk()));
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps,request);
		return "download";
	}
	
	public String initViewMx() throws Exception {
		((HyPcxxglService)getService()).initMx(getDomain(), getUserDomain());
		return "initViewMx";
	}
	
	public String saveWfhxx4Pc() throws Exception {
		((HyPcxxglService)getService()).saveWfhxx4Pc(getDomain(), getUserDomain());
		return "saveWfhxx4Pc";
	}
	
	public String queryWfhxx() throws Exception {
		((HyPcxxglService)getService()).queryWfhxx(getDomain(), getUserDomain());
		return "queryWfhxx";
	}
	
	public String onQingdan() throws Exception {
		((HyPcxxglService)getService()).qingdan(getDomain(), getUserDomain());
		String qybm = getUserDomain().qybm;
		String forward = "qingdan";
		//String forward = "qingdan4" + qybm.toUpperCase();
		if("SJ".equals(qybm)){
			forward = "qingdan4SJ";
		}else if("XH".equals(qybm)){
			forward = "qingdan4XH";
		}
		
		return forward;
	}
	
	/**
	 * 
	* @Description: 检索派车单对应的货物信息 
	* @Note
	* @author 
	* @since 2013-3-12
	* @return
	* @throws Exception
	 */
	public String queryPcHwxx() throws Exception {
		((HyPcxxglService)getService()).queryPcHwxx(getDomain(), getUserDomain());
		return "queryPcHwxx";
	}
	
	public String modify() throws Exception {
		((HyPcxxglService)getService()).modify(getDomain(), getUserDomain());
		return "modify";
	}
	
	public String initWfhxx4Pc() throws Exception {
		((HyPcxxglService)getService()).initWfhxx4Pc(getDomain(), getUserDomain());
		return "initWfhxx4Pc";
	}
	
	public String updateWfhxx4Pc() throws Exception {
		((HyPcxxglService)getService()).updateWfhxx4Pc(getDomain(), getUserDomain());
		return "updateWfhxx4Pc";
	}
	
	public String deleteWfhxx4Pc() throws Exception {
		((HyPcxxglService)getService()).deleteWfhxx4Pc(getDomain(), getUserDomain());
		return "deleteWfhxx4Pc";
	}
	
	public String queryPcxxSjsInitVal() throws Exception {
		((HyPcxxglService)getService()).queryPcxxSjsInitVal(getDomain(), getUserDomain());
		return "queryPcxxSjsInitVal";
	}
	
	@Resource(name = "hyPcxxglServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new HyPcxxglDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
	
