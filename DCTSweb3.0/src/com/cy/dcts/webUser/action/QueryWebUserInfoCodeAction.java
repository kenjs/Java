package com.cy.dcts.webUser.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;

/**
 * 根据登录用户名查询用户是否存在
 * @author nxj
 *
 */
public class QueryWebUserInfoCodeAction extends BaseJsonAction {

	private static final long serialVersionUID = 2026125707557156173L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IQueryWebUserInfoService queryWebUserInfoService;

	@Override
	protected void execMethod() throws Exception {
		String code = this.request.getParameter("code");
		try {
			WebUserInfo webUserInfo = new WebUserInfo();
			webUserInfo.setCode(code);
			webUserInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
			List<WebUserInfo> list=  queryWebUserInfoService.queryWebUserInfo(webUserInfo);
			if(list.size() > 0) {
				String result = this.sendResponseToJson("1","用户名不可使用!");
				logger.warn("query user code success. code=[{}], userId=[{}], json=[{}]",new Object[] {code, list.get(0).getId(), result });
			}
			return;
		}catch (Exception e) {
			logger.error("query user code error! code=[{}]",new Object[] { code });
			throw new RuntimeException();
		}
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}
	
}
