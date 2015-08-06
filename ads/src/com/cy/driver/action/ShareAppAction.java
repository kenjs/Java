package com.cy.driver.action;

import com.cy.driver.bo.AppShareInfo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.dao.AppShareInfoDao;
import com.cy.driver.dao.DriverUserCargoInfoDao;
import com.cy.driver.domain.DriverUserInfoDomain;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@Scope("prototype")
@Controller("shareAppAction")
public class ShareAppAction extends WebBaseAction {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private AppShareInfoDao appShareInfoDao;
	@Resource
	private DriverUserCargoInfoDao driverUserCargoInfoDao;

	@RequestMapping(value = "/decodeBase64")
	@ResponseBody
	public JSonResponse openShareView(String telephone) {
		if (StringUtils.isBlank(telephone)) {
			return JSonResponse.makeHasContentJSonRespone("-8", "信息丟失");
		}
		if (telephone.contains("&")) {
			telephone = telephone.split("&")[0];
		}
		try {
			telephone = new String(Base64.decodeBase64(telephone.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		telephone = telephone.substring(0, telephone.length() - 1);
		return JSonResponse.makeHasContentJSonRespone("1", "", telephone);
	}

	@RequestMapping (value = "/downloadByShare")
	@ResponseBody
	public JSonResponse share(String recommendedMobileNum, String recommendMobileNum) {
		if (StringUtils.isBlank(recommendedMobileNum)) {
			if (log.isDebugEnabled()) {
				log.debug("请输入被推荐人手机号码");
			}
			return JSonResponse.makeHasContentJSonRespone("-8", "请输入您的手机号码..");
		}
		if (StringUtils.isBlank(recommendMobileNum)) {
			if (log.isDebugEnabled()) {
				log.debug("推荐人手机号码未成功获取..");
			}
			return JSonResponse.makeHasContentJSonRespone("-8", "操作失败");
		}
		try {
			int count = appShareInfoDao.selectAppShareInfoByNum(recommendedMobileNum);
			DriverUserInfoDomain domain = driverUserCargoInfoDao.checkLogin(recommendedMobileNum);
			if (count == 0 && domain == null) {
				AppShareInfo appShareInfo = new AppShareInfo(recommendedMobileNum, recommendMobileNum);
				appShareInfoDao.addAppShareInfo(appShareInfo);
			}
		} catch (SQLException e) {
			log.error("ShareAppAction.class - " + e.getMessage());
			return JSonResponse.makeHasContentJSonRespone("-8", "系统出错");
		}
		return JSonResponse.makeHasContentJSonRespone("1", "操作成功");
	}
}
