package com.cy.dcts.company.action;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.company.service.ICompanyService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
/**
 * 打开企业认证
 * @author zdy
 *
 */
public class OpenCompanyAuthenticationAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IQueryWebUserInfoService queryWebUserInfoService;
	private ICompanyService companyService;
	
	private CompanyInfo companyInfo;
	private WebUserInfo webUserInfo;
	private String menuAId;//个人中心的菜单显示
	private String provinceCityCountyStr;//省-市-区县
	private String flag;
	@Override
	protected String execMethod() throws Exception {
		
		//判断是否登陆
		if(getSessionUser()==null){
			return LOGIN;
		}
		try {
		logger.debug("open company authentication begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		
		//查询企业是否已认证
		webUserInfo=queryWebUserInfoService.queryWebUserInfoById(getSessionUser().getId());
		//if(String.valueOf(Constants.ENTERPRISE_FLAG_END).equals(webUserInfo.getEnterpriseFlag())){//已认证
			//return "authenticationEnd";//跳到提示用户已认证的界面
		//}else{
			//查询公司的相关信息		
			companyInfo=companyService.queryCompanyInfoById(getSessionUser().getCompanyId());
		//}					            
			if(companyInfo==null){
				logger.debug("query  company info fail!");
			}else{
				logger.debug("open company authentication success! companyInfo.name=[{}],companyId=[{}]",companyInfo.getCompanyName(),companyInfo.getId());
				//拼接公司地址
				if(StringUtils.isNotEmpty(companyInfo.getCompanyCounty())){
					this.setProvinceCityCountyStr(companyInfo.getCompanyProvince()+"-"+companyInfo.getCompanyCity()+"-"+companyInfo.getCompanyCounty());
				}else if(StringUtils.isNotEmpty(companyInfo.getCompanyCity())){
					this.setProvinceCityCountyStr(companyInfo.getCompanyProvince()+"-"+companyInfo.getCompanyCity());	
				}else{
					this.setProvinceCityCountyStr(companyInfo.getCompanyProvince());	
				}
				
				//若图片地址为null,复制为“”
				if(companyInfo.getBusinessImages()==null){
					companyInfo.setBusinessImages("");
				}
				if(companyInfo.getOrganizationImages()==null){
					companyInfo.setOrganizationImages("");
				}
				
				//重新认证清除公司一些信息
				String flagStr = request.getParameter("flag");
				if(flagStr != null && "2".equals(flagStr)) {
					companyInfo.setBusinessLicence("");
					companyInfo.setBusinessImages("");
					companyInfo.setOrganizationCode("");
					companyInfo.setOrganizationImages("");
					companyInfo.setContactName("");
					companyInfo.setContactTelephone("");
					companyInfo.setCompanyAddress("");
					setProvinceCityCountyStr("");
					//setFlag(flagStr);
				} else {
					setFlag("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public IQueryWebUserInfoService getQueryWebUserInfoService() {
		return queryWebUserInfoService;
	}
	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}
	public ICompanyService getCompanyService() {
		return companyService;
	}
	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}
	public WebUserInfo getWebUserInfo() {
		return webUserInfo;
	}
	public void setWebUserInfo(WebUserInfo webUserInfo) {
		this.webUserInfo = webUserInfo;
	}
	public String getMenuAId() {
		return menuAId;
	}
	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}
	public String getProvinceCityCountyStr() {
		return provinceCityCountyStr;
	}
	public void setProvinceCityCountyStr(String provinceCityCountyStr) {
		this.provinceCityCountyStr = provinceCityCountyStr;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
