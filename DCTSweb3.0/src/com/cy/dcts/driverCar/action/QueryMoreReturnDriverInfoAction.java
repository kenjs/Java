package com.cy.dcts.driverCar.action;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.DriverBusinessLineInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.util.ConvertCharactersUtil;
import com.cy.dcts.driverLine.service.IDriverCarLineService;

/**
 * 更多预约车源
 * @author nxj
 *
 */
public class QueryMoreReturnDriverInfoAction extends BasePageAction  {

	private static final long serialVersionUID = 4255510330049235661L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverCarLineService driverCarLineService;
	
	@Override
	protected String execMethod() throws Exception {
		DriverBusinessLineInfoDomain driverBusinessLineInfoDomain = getDriverBusinessLineInfoDomain();
		driverBusinessLineInfoDomain.setDeleteFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));//司机表删除状态
		//driverBusinessLineInfoDomain.setFreezeFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));//司机表冻结标志
		//driverBusinessLineInfoDomain.setAuditFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));//司机表审核标志
		driverBusinessLineInfoDomain.setStart(String.valueOf(Constants.DELETED_FLAG_FALSE));//预约线路表删除状态
		if ("GET".equalsIgnoreCase(this.request.getMethod())) {
			if (this.request.getHeader("User-Agent").toLowerCase().indexOf("MSIE".toLowerCase()) != -1) {
				ConvertCharactersUtil.convertISO2GBK(driverBusinessLineInfoDomain);
			} else {
				ConvertCharactersUtil.convertISO2UTF(driverBusinessLineInfoDomain);
			}
		}
		List<DriverBusinessLineInfoDomain> list = driverCarLineService.queryDriverLineByPage(driverBusinessLineInfoDomain);
		driverBusinessLineInfoDomain.setList(list);
		this.request.setAttribute("driverBusinessLineInfoDomain",driverBusinessLineInfoDomain);
		return SUCCESS;
	}

	private DriverBusinessLineInfoDomain getDriverBusinessLineInfoDomain() throws ParseException{
		DriverBusinessLineInfoDomain driverBusinessLineInfoDomain = new DriverBusinessLineInfoDomain();
		PageInfo pageInfo = new PageInfo();
		if(StringUtils.isNotEmpty(this.request.getParameter("id"))) {
			driverBusinessLineInfoDomain.setId(this.request.getParameter("id"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("code"))) {
			driverBusinessLineInfoDomain.setCode(this.request.getParameter("code"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carNumber"))) {
			driverBusinessLineInfoDomain.setCarNumber(this.request.getParameter("carNumber"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carPlateType"))) {
			driverBusinessLineInfoDomain.setCarPlateType(this.request.getParameter("carPlateType"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carBarType"))) {
			driverBusinessLineInfoDomain.setCarBarType(this.request.getParameter("carBarType"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carLength"))) {
			driverBusinessLineInfoDomain.setCarLength(this.request.getParameter("carLength"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carWeight"))) {
			driverBusinessLineInfoDomain.setCarWeight(this.request.getParameter("carWeight"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carCubage"))) {
			driverBusinessLineInfoDomain.setCarCubage(this.request.getParameter("carCubage"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("startTime"))) {
			driverBusinessLineInfoDomain.setStartTime(this.request.getParameter("startTime"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("startPcc"))) {
			String[] startPcc = this.request.getParameter("startPcc").split("-");
			if(startPcc.length == 1) {
				driverBusinessLineInfoDomain.setStartProvince(startPcc[0]);
			}
			if(startPcc.length == 2) {
				driverBusinessLineInfoDomain.setStartProvince(startPcc[0]);
				driverBusinessLineInfoDomain.setStartCity(startPcc[1]);
			}
			if(startPcc.length == 3) {
				driverBusinessLineInfoDomain.setStartProvince(startPcc[0]);
				driverBusinessLineInfoDomain.setStartCity(startPcc[1]);
				driverBusinessLineInfoDomain.setStartCounty(startPcc[2]);
			}
			driverBusinessLineInfoDomain.setStartPcc(this.request.getParameter("startPcc"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("endPcc"))) {
			String[] endPcc = this.request.getParameter("endPcc").split("-");
			if(endPcc.length == 1) {
				driverBusinessLineInfoDomain.setEndProvince(endPcc[0]);
			}
			if(endPcc.length == 2) {
				driverBusinessLineInfoDomain.setEndProvince(endPcc[0]);
				driverBusinessLineInfoDomain.setEndCity(endPcc[1]);			
			}
			if(endPcc.length == 3) {
				driverBusinessLineInfoDomain.setEndProvince(endPcc[0]);
				driverBusinessLineInfoDomain.setEndCity(endPcc[1]);
				driverBusinessLineInfoDomain.setEndCounty(endPcc[2]);
			}
			driverBusinessLineInfoDomain.setEndPcc(this.request.getParameter("endPcc"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("pageSize"))) {
			pageInfo.setPageSize(Integer.parseInt(this.request.getParameter("pageSize")));
		}else {
			pageInfo.setPageSize(16);
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("curPage"))) {
			if("0".equals(this.request.getParameter("curPage"))) {
				pageInfo.setCurPage(0);
			}else {
				pageInfo.setCurPage(Integer.parseInt(this.request.getParameter("curPage"))-1);
				pageInfo.setCurPageNo(Integer.parseInt(this.request.getParameter("curPage")));
			}
		}else {
			pageInfo.setCurPage(0);
		}
		driverBusinessLineInfoDomain.setPageInfo(pageInfo);
		return driverBusinessLineInfoDomain;
	}
	
	public void setDriverCarLineService(IDriverCarLineService driverCarLineService) {
		this.driverCarLineService = driverCarLineService;
	}

}
