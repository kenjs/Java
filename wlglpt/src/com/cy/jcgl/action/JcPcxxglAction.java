package com.cy.jcgl.action;

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
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.service.JcPcxxglService;

/**
 * THE ACTION FOR 货运-派车信息管理
 * @author LYY
 * time  2013-5-4
 */
@Controller
@Scope("prototype")
@Action(value = "/jcgl/jcpcxxgl", results = {
		@Result(name = "init", location = "/work/jcgl/jc_pcxxgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/jcgl/jc_pcxxcx_mx.jsp")
		})
@SuppressWarnings("unchecked")
public class JcPcxxglAction extends ExtendAction {
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
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	public String initViewMx() throws Exception {
		((JcPcxxglService)getService()).initMx(getDomain(), getUserDomain());
		return "initViewMx";
	}
	
	@Resource(name = "jcPcxxglServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new JcPcxxglDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
	
