package com.cy.dcts.webUser.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
import com.cy.dcts.webUser.service.ISaveWebUserInfoService;
/**
 * 修改电话号码 更新数据库信息
 * @date 2014-5-21
 * @author haoyong
 *
 */
public class UpdateTelephoneAction extends BaseJsonAction{
	private static final long serialVersionUID = 8006080487515344889L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ISaveWebUserInfoService saveWebUserInfoService;
	private IQueryWebUserInfoService queryWebUserInfoService;
	@Override
	protected void execMethod() throws Exception {
		boolean flag = false;
		String result = "";
		Object obj = request.getSession().
										getAttribute(Constants.SESSION_LOGIN_USER);
		if(obj == null) {
			sendResponseToJson("0","修改失败");
			return;
		}
		WebUserInfo info = (WebUserInfo) obj;
		try {
			String newTelephone = request.getParameter("telephone");
			if(StringUtils.isBlank(newTelephone)) {
				sendResponseToJson("0","修改失败");
				return;
			}
			info.setMobilephone(newTelephone);
			WebUserInfo userInfo = queryWebUserInfoService.queryWebUserInfoByMobilephone(newTelephone);
			if(userInfo == null) {
				flag = saveWebUserInfoService.modifyWebUserInfoMobilephoneById(info);
				if(flag) {
					result = this.sendResponseToJson("1","修改成功");
					request.getSession().setAttribute(Constants.SESSION_LOGIN_USER, 
							queryWebUserInfoService.queryWebUserInfoById(((WebUserInfo)obj).getId()));
					
				} else{
					result = this.sendResponseToJson("0","修改失败");
				}
			} else {
				result = this.sendResponseToJson("-1", "该手机号码已注册过");
			}
			logger.warn("update password result.josn=[{}]", new Object[]{result});
		} catch (Exception e) {
			logger.error("update password fail.error=[{}]", new Object[]{e.getMessage()});
			throw new RuntimeException();
		}
	}
	public void setSaveWebUserInfoService(
			ISaveWebUserInfoService saveWebUserInfoService) {
		this.saveWebUserInfoService = saveWebUserInfoService;
	}
	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}
	
}
