package com.cy.dcts.driverCar.action;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.util.ConvertCharactersUtil;
import com.cy.dcts.common.util.ValidateUtil;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;

/**
 * 更多当前车源
 * @author nxj
 *
 */
public class QueryMoreRealDriverInfoAction extends BasePageAction {
	
	private static final long serialVersionUID = 4733217700945733698L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverUserCarInfoService driverUserCarInfoService;
	
	@Override
	protected String execMethod() throws Exception {
		DriverUserInfoDomain driverUserInfoDomain = getDriverUserInfoDomain();
		//判断如果是GET提交方式就转码
		if ("GET".equalsIgnoreCase(this.request.getMethod())) {
			if (this.request.getHeader("User-Agent").toLowerCase().indexOf("MSIE".toLowerCase()) != -1) {
				ConvertCharactersUtil.convertISO2GBK(driverUserInfoDomain);
			} else {
				ConvertCharactersUtil.convertISO2UTF(driverUserInfoDomain);
			}
		}
		List<DriverUserInfoDomain> list = driverUserCarInfoService.queryRealMoreDriverCarByPage(driverUserInfoDomain);
		driverUserInfoDomain.setList(list);
		this.request.setAttribute("driverUserInfoDomain",driverUserInfoDomain);
		return SUCCESS;
	}

	private DriverUserInfoDomain getDriverUserInfoDomain() throws ParseException{
		DriverUserInfoDomain driverUserInfoDomain = new DriverUserInfoDomain();
		PageInfo pageInfo = new PageInfo();
		if(StringUtils.isNotEmpty(this.request.getParameter("id"))) {
			driverUserInfoDomain.setId(this.request.getParameter("id"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("code"))) {
			driverUserInfoDomain.setCode(this.request.getParameter("code"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carNumber"))) {
			driverUserInfoDomain.setCarNumber(this.request.getParameter("carNumber"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carPlateType"))) {
			driverUserInfoDomain.setCarPlateType(this.request.getParameter("carPlateType"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carBarType"))) {
			driverUserInfoDomain.setCarBarType(this.request.getParameter("carBarType"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carLength"))) {
			driverUserInfoDomain.setCarLength(this.request.getParameter("carLength"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carWeight"))) {
			driverUserInfoDomain.setCarWeight(this.request.getParameter("carWeight"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carCubage"))) {
			driverUserInfoDomain.setCarCubage(this.request.getParameter("carCubage"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("startPcc"))) {
			String[] startPcc = this.request.getParameter("startPcc").split("-");
			for(int i = 0;i < startPcc.length;i++) {
				if(i == 0) {
					driverUserInfoDomain.setStartProvince(startPcc[0]);
				}else if(i == 1) {
					driverUserInfoDomain.setStartCity(startPcc[1]);
				}else if(i == 2) {
					driverUserInfoDomain.setStartCounty(startPcc[2]);
				}
			}
			driverUserInfoDomain.setStartPcc(this.request.getParameter("startPcc"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("endPcc"))) {
			String[] endPcc = this.request.getParameter("endPcc").split("-");
			for(int i = 0;i < endPcc.length;i++) {
				if(i == 0) {
					driverUserInfoDomain.setEndProvince(endPcc[0]);
				}else if(i == 1) {
					driverUserInfoDomain.setEndCity(endPcc[1]);
				}else if(i == 2) {
					driverUserInfoDomain.setEndCounty(endPcc[2]);
				}
			}
			driverUserInfoDomain.setEndPcc(this.request.getParameter("endPcc"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("lastLocation"))) { 
			String[] Location = this.request.getParameter("lastLocation").split("-");
			for(int i = 0;i < Location.length;i++) {
				if(i == 0) {
					driverUserInfoDomain.setProvince(Location[0]);
				}else if(i == 1) {
					driverUserInfoDomain.setCity(Location[1]);
				}else if(i == 2) {
					driverUserInfoDomain.setCounty(Location[2]);
				}
			}
			driverUserInfoDomain.setLastLocation(this.request.getParameter("lastLocation"));
		}
		if(StringUtils.isNotEmpty(this.request.getParameter("carNumberPhoneId"))) {
			if(ValidateUtil.validateTelePhone(this.request.getParameter("carNumberPhoneId")) == true) {
				driverUserInfoDomain.setCode(this.request.getParameter("carNumberPhoneId"));
			}else {
				driverUserInfoDomain.setCarNumber(this.request.getParameter("carNumberPhoneId"));
			}
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
		driverUserInfoDomain.setPageInfo(pageInfo);
		return driverUserInfoDomain;
	}

	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}

	
	
}
