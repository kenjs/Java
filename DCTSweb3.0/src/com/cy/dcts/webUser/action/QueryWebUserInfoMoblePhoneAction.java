package com.cy.dcts.webUser.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;

/**
 * 根据手机号码查询用户是否存在
 * @author nxj
 *
 */
public class QueryWebUserInfoMoblePhoneAction  extends BaseJsonAction {

	private static final long serialVersionUID = 3584066147487652569L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private IQueryWebUserInfoService queryWebUserInfoService;
	
	
	@Override
	protected void execMethod() throws Exception {
		String mobilephone = this.request.getParameter("mobilephone");
		String type = this.request.getParameter("type");
		String userId = this.request.getParameter("userId");
		WebUserInfo info = null;
		try {
			WebUserInfo webUserInfo = new WebUserInfo();
			webUserInfo.setMobilephone(mobilephone);
			webUserInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
			List<WebUserInfo> list= queryWebUserInfoService.queryWebUserInfo(webUserInfo);
			if("1".equals(type)) {
				if(list.size() == 1) {
					info = queryWebUserInfoService.queryWebUserInfoById(userId);
					if(info != null) {
						if(list.get(0).getMobilephone().equals(info.getMobilephone())) {
							String result = this.sendResponseToJson("0","手机号码已绑定!");
							logger.warn("query user code success. mobilephone=[{}], userId=[{}], json=[{}]",new Object[] {mobilephone, list.get(0).getId(), result });
						}else {
							String result = this.sendResponseToJson("1","手机号码已绑定!");
							logger.warn("query user code success. mobilephone=[{}], userId=[{}], json=[{}]",new Object[] {mobilephone, list.get(0).getId(), result });
						}
					}
				}else if(list.size()==0){
					String result = this.sendResponseToJson("0","手机号码 不存在!");
					logger.warn("query user code success. mobilephone=[{}],  json=[{}]",new Object[] {mobilephone, result });
				}
			}else {
				if(list.size() > 0) {
					String result = this.sendResponseToJson("1","手机号码已绑定!");
					logger.warn("query user code success. mobilephone=[{}], userId=[{}], json=[{}]",new Object[] {mobilephone, list.get(0).getId(), result });
				}else {
					String result = this.sendResponseToJson("0","手机号码 不存在!");
					logger.warn("query user code success. mobilephone=[{}],  json=[{}]",new Object[] {mobilephone, result });
				}
			}
		}catch (Exception e) {
			logger.error("query user code error! mobilephone=[{}]",new Object[] { mobilephone });
			throw new RuntimeException();
		}
	}
	
	
	public void setQueryWebUserInfoService(IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

}
