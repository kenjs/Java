package com.cy.dcts.webUser.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.util.MD5Util;
import com.cy.dcts.webUser.service.UpdatePwdService;
/**
 * 修改密码  更新数据库信息
 * @date 2014-5-21
 * @author haoyong
 *
 */
public class UpdatePwdAction extends BaseJsonAction{
	private static final long serialVersionUID = 8006080487515344889L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private UpdatePwdService updatePwdService;
	private String userId="";
	
	@Override
	protected void execMethod() throws Exception {
		boolean flag;
		String result;
		if(this.getSessionUser() == null){
			sendResponseToJson("1","请先登录");
			return;
		}
		WebUserInfo info = new WebUserInfo();
		try {
			String pwd = request.getParameter("pwd");
			if(StringUtils.isBlank(pwd)) {
				sendResponseToJson("2","修改失败");
			}
			if(StringUtils.isEmpty(userId)){
				info.setId(getSessionUser().getId());
			}else{
				info.setId(userId);
			}
			
			info.setPassword(new MD5Util().getMD5ofStr(pwd));
			
			flag = updatePwdService.updateUserPwd(info);
			if(flag) {
				result = this.sendResponseToJson("0","修改成功");
			} else
			{
				result = this.sendResponseToJson("2","修改失败");
			}
			logger.warn("update password result.josn=[{}]", new Object[]{result});
		} catch (Exception e) {
			logger.error("update password fail.error=[{}]", new Object[]{e.getMessage()});
			throw new RuntimeException();
		}
	}
	public void setUpdatePwdService(
			UpdatePwdService updatePwdService) {
		this.updatePwdService = updatePwdService;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
