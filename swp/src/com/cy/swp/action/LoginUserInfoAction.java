package com.cy.swp.action;

import javax.annotation.Resource;

import com.cy.swp.bo.DictInfo;
import com.cy.swp.common.util.DataDictUtil;
import com.cy.swp.domain.MarketingUserInfoDomain;
import com.cy.swp.domain.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.swp.action.BaseAction;
import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.common.util.MD5Util;
import com.cy.swp.service.MarketingUserInfoService;

import java.util.Date;
import java.util.List;

@Scope("prototype")
@Controller("loginUserInfoAction")
public class LoginUserInfoAction extends BaseAction{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
    private MarketingUserInfoService marketingUserInfoService;
	
	@RequestMapping("/loginUserInfoJson.jspx")
	@ResponseBody
    protected JSonRespone loginUserInfoJson(MarketingUserInfo marketingUserInfo) {
		try {
			if(StringUtils.isEmpty(marketingUserInfo.getCode())) {//用户名不能为空
				return JSonRespone.makeHasContentJSonRespone("divCodeMpt", "divCodeNull", "账号不能为空");
			}
			if(StringUtils.isEmpty(marketingUserInfo.getPassword())) {//密码不能为空
				return JSonRespone.makeHasContentJSonRespone("divPwdMpt", "divPwdNull", "密码不能为空");
			}
			MarketingUserInfo userInfo = marketingUserInfoService.MarketingUserInfoLogin(marketingUserInfo);
			//判断用户是否存在，如果用户名不存在就返回null
			 if(userInfo != null) {
				 if(userInfo.getDeleteFlag() == 1){
					 return JSonRespone.makeHasContentJSonRespone("divCodeMpt", "divCodeNotMpt", "用户已锁定,无法使用！");
				 }
				//判断密码是否有误，如果有误就返回1
				if(!userInfo.getPassword().equals(new MD5Util().getMD5ofStr(marketingUserInfo.getPassword()))) {
					return JSonRespone.makeHasContentJSonRespone("divPwdMpt", "divPwdError", "密码错误");
				}
			}else {
				return JSonRespone.makeHasContentJSonRespone("divCodeMpt", "divCodeNot", "账号不存在");
			}
			this.putSessionUser(userInfo);
			return JSonRespone.makeHasContentJSonRespone("1", "登录成功");
		}catch (Exception e) {
			logger.debug("login json error");
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/**
	 * 登录成功进入后台首页
	 * @param view
	 * @return
	 */
	@RequestMapping("/openIndexUser.jspx")
    protected ModelAndView openIndexUser(ModelAndView view) {
		if (this.getSessionUser() == null) {
			view.setViewName("redirect:/");
            return view;
        }
		view.setViewName("index/index");
		return view;
	}


	@RequestMapping("/userLogout.jspx")
	@ResponseBody
	protected ModelAndView execMethod() throws Exception {
		putSessionUser(null);
		return new ModelAndView("redirect:/");
	}


	/**
	 *
	 * @param userId 用户id
	 * @param password 要修改的密码
	 * @param password1 原始密码
	 * @return
	 */
	@RequestMapping("/updatePassWord.jspx")
	@ResponseBody
	public JSonRespone updatePassWord(String userId,String password,String password1) {
		try {
			if(StringUtils.isEmpty(userId)) {
				return JSonRespone.makeHasContentJSonRespone("1", "参数错误！");
			}
			if(StringUtils.isEmpty(password1)) {
				return JSonRespone.makeHasContentJSonRespone("1", "原始密码不能为空！");
			}
			if(StringUtils.isEmpty(password)) {
				return JSonRespone.makeHasContentJSonRespone("1", "新密码不能为空！");
			}
			MarketingUserInfo userInfo = new MarketingUserInfo();
			userInfo.setId(Integer.parseInt(userId));
			userInfo = marketingUserInfoService.MarketingUserInfoLogin(userInfo);
			if(!userInfo.getPassword().equals(new MD5Util().getMD5ofStr(password1))) {
				return JSonRespone.makeHasContentJSonRespone("1", "原始密码错误！");
			}
			userInfo.setPassword(new MD5Util().getMD5ofStr(password));
			boolean resle = marketingUserInfoService.updateUserPassword(userInfo);
			if(resle) {
				return JSonRespone.makeHasContentJSonRespone("0", "修改密码成功！");
			}
			return JSonRespone.makeHasContentJSonRespone("1", "修改失败！参数错误！");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return JSonRespone.makeHasContentJSonRespone("1", "修改失败！参数错误！");
	}


	@RequestMapping("/updatePw.jspx")
	@ResponseBody
	public JSonRespone updatePw(String userId) {
		try {
			if(StringUtils.isEmpty(userId)) {
				return JSonRespone.makeHasContentJSonRespone("1", "参数错误！");
			}
			MarketingUserInfo userInfo = new MarketingUserInfo();
			userInfo.setId(Integer.parseInt(userId));
			userInfo = marketingUserInfoService.MarketingUserInfoLogin(userInfo);
			userInfo.setPassword(new MD5Util().getMD5ofStr("123456"));
			boolean resle = marketingUserInfoService.updateUserPassword(userInfo);
			if(resle) {
				return JSonRespone.makeHasContentJSonRespone("0", "修改密码成功！");
			}
			return JSonRespone.makeHasContentJSonRespone("1", "修改失败！参数错误！");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return JSonRespone.makeHasContentJSonRespone("1", "修改失败！参数错误！");
	}


	@RequestMapping("/updateDeFl.jspx")
	@ResponseBody
	public JSonRespone updateDeFl(String userId,String deleteFalg) {
		try {
			if(StringUtils.isEmpty(userId)) {
				return JSonRespone.makeHasContentJSonRespone("1", "参数错误！");
			}
			MarketingUserInfoDomain userInfo = new MarketingUserInfoDomain();
			userInfo.setId(Integer.parseInt(userId));
			userInfo.setDeleteFlag(Integer.parseInt(deleteFalg));
			boolean resle = marketingUserInfoService.updateMarketingUserInfo(userInfo);
			if(resle) {
				return JSonRespone.makeHasContentJSonRespone("0", "修改密码成功！");
			}
			return JSonRespone.makeHasContentJSonRespone("1", "修改失败！参数错误！");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return JSonRespone.makeHasContentJSonRespone("1", "修改失败！参数错误！");
	}

	@RequestMapping("/queryMarketingUserInfoDomainPageList.jspx")
	protected ModelAndView queryMarketingUserInfoDomainPageList(ModelAndView view,MarketingUserInfoDomain marketingUserInfoDomain) {
		view.setViewName("user/queryUserPageInfo");
		if(marketingUserInfoDomain.getPageInfo() == null) {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(1);
			marketingUserInfoDomain.setPageInfo(pageInfo);
		}
		marketingUserInfoDomain.getPageInfo().setPageSize(10);
		marketingUserInfoDomain = marketingUserInfoService.queryMarketingUserInfoDomainPagelist(marketingUserInfoDomain);
		view.addObject(marketingUserInfoDomain);
		return view;
	}


	@RequestMapping("/addUserInfo.jspx")
	@ResponseBody
	public JSonRespone addUserInfo(String userId) {
		try {
			List<DictInfo> userPosition = DataDictUtil.userPosition();
			List<DictInfo> userJoinGroup = DataDictUtil.userJoinGroup();
			List<DictInfo> userSex = DataDictUtil.userSex();
			List<DictInfo> userDelete = DataDictUtil.userDelete();
			StringBuffer userStr = new StringBuffer();
			userStr.append("<div class=\"tabfl\">");
			userStr.append("<div style=\"height: 30px; text-align: center;\" id=\"updateNewsId\"></div>");
			userStr.append("<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" style=\"margin-top:-1px;text-align: left;\">");
			if(StringUtils.isNotEmpty(userId)) {
				MarketingUserInfo userInfo = new MarketingUserInfo();
				userInfo.setId(Integer.parseInt(userId));
				userInfo = marketingUserInfoService.MarketingUserInfoLogin(userInfo);
				userStr.append("<tr>");
				userStr.append("<td>登录名：</td>");
				userStr.append("<td><input id=\"code\" name=\"code\" type=\"text\" value=\""+userInfo.getCode()+"\"/><input id=\"id\" name=\"id\" type=\"hidden\" value=\""+userInfo.getId()+"\"/></td>");
				userStr.append("<td>用户名：</td>");
				userStr.append("<td><input id=\"name\" name=\"name\" type=\"text\" value=\""+userInfo.getName()+"\"/></td>");
				userStr.append("<td>性别：</td>");
				userStr.append("<td>&nbsp;&nbsp;<select id=\"sex\" name=\"sex\">");
				for(int i = 0;i<userSex.size();i++) {
					if(userSex.get(i).getCode().equals(String.valueOf(userInfo.getSex())) || userInfo.getSex() == null) {
						userStr.append("<option value=\""+userSex.get(i).getCode()+"\" selected=\"true\" >"+userSex.get(i).getName()+"</option>");
					}else {
						userStr.append("<option value=\""+userSex.get(i).getCode() +"\" >"+userSex.get(i).getName()+"</option>");
					}
				}
				userStr.append("</select></td>");
				userStr.append("</tr>");
				userStr.append("<tr>");
				userStr.append("<td>手机号码：</td>");
				userStr.append("<td><input id=\"phoneNumber\" name=\"phoneNumber\" type=\"text\" value=\"");
				if(StringUtils.isNotEmpty(userInfo.getPhoneNumber())) {
					userStr.append(userInfo.getPhoneNumber());
				}
				userStr.append("\"/></td>");
				userStr.append("<td>联系电话：</td>");
				userStr.append("<td><input id=\"contactNumber\" name=\"contactNumber\" type=\"text\" value=\"");
				if(StringUtils.isNotEmpty(userInfo.getContactNumber())) {
					userStr.append(userInfo.getContactNumber());
				}
				userStr.append("\"/></td>");
				userStr.append("<td>年龄：</td>");
				userStr.append("<td><input id=\"age\" name=\"age\" type=\"text\" value=\"");
				if(userInfo.getAge() != null) {
					userStr.append(userInfo.getAge());
				}
				userStr.append("\"/></td>");
				userStr.append("</tr>");
				userStr.append("<tr>");
				userStr.append("<td>职务：</td>");
				userStr.append("<td>&nbsp;&nbsp;<select id=\"position\" name=\"position\">");
				for(int i = 0;i<userPosition.size();i++) {
					if(userPosition.get(i).getCode().equals(String.valueOf(userInfo.getPosition()))) {
						userStr.append("<option value=\""+userPosition.get(i).getCode()+"\" selected=\"true\" >"+userPosition.get(i).getName()+"</option>");
					}else {
						userStr.append("<option value=\""+userPosition.get(i).getCode() +"\" >"+userPosition.get(i).getName()+"</option>");
					}
				}
				userStr.append("</select></td>");
				userStr.append("<td>所属组：</td>");
				userStr.append("<td>&nbsp;&nbsp;<select id=\"joinGroup\" name=\"joinGroup\">");
				for(int i = 0;i<userJoinGroup.size();i++) {
					if(userJoinGroup.get(i).getCode().equals(String.valueOf(userInfo.getJoinGroup()))) {
						userStr.append("<option value=\""+userJoinGroup.get(i).getCode()+"\" selected=\"true\" >"+userJoinGroup.get(i).getName()+"</option>");
					}else {
						userStr.append("<option value=\""+userJoinGroup.get(i).getCode() +"\" >"+userJoinGroup.get(i).getName()+"</option>");
					}
				}
				userStr.append("</select></td>");
				userStr.append("<td>是否启用：</td>");
				userStr.append("<td>&nbsp;&nbsp;<select id=\"deleteFlag\" name=\"deleteFlag\">");
				for(int i = 0;i<userSex.size();i++) {
					if(userDelete.get(i).getCode().equals(String.valueOf(userInfo.getDeleteFlag()))) {
						userStr.append("<option value=\""+userDelete.get(i).getCode()+"\" selected=\"true\" >"+userDelete.get(i).getName()+"</option>");
					}else {
						userStr.append("<option value=\""+userDelete.get(i).getCode() +"\" >"+userDelete.get(i).getName()+"</option>");
					}
				}
				userStr.append("</select></td>");
				userStr.append("</tr>");
				userStr.append("<tr>");
				userStr.append("<td>地址：</td>");
				userStr.append("<td colspan=\"5\"><input id=\"address\" name=\"address\" type=\"text\" value=\"");
				if(StringUtils.isNotEmpty(userInfo.getAddress())) {
					userStr.append(userInfo.getAddress());
				}
				userStr.append("\" style=\"width:600px;\"/></td>");
				userStr.append("</tr>");
			}else {
				userStr.append("<tr>");
				userStr.append("<td>登录名：</td>");
				userStr.append("<td><input id=\"code\" name=\"code\" type=\"text\" value=\"\"/><input id=\"id\" name=\"id\" type=\"hidden\" value=\"\"/></td>");
				userStr.append("<td>密码：</td>");
				userStr.append("<td>密码默认123456,登录用户登录以后自行修改！</td>");
				userStr.append("<td>用户名：</td>");
				userStr.append("<td><input id=\"name\" name=\"name\" type=\"text\" value=\"\"/></td>");
				userStr.append("</tr>");
				userStr.append("<tr>");
				userStr.append("<td>性别：</td>");
				userStr.append("<td>&nbsp;&nbsp;<select id=\"sex\" name=\"sex\">");
				for(int i = 0;i<userSex.size();i++) {
					userStr.append("<option value=\""+userSex.get(i).getCode() +"\" >"+userSex.get(i).getName()+"</option>");
				}
				userStr.append("</select></td>");
				userStr.append("<td>手机号码：</td>");
				userStr.append("<td><input id=\"phoneNumber\" name=\"phoneNumber\" type=\"text\" value=\"\"/></td>");
				userStr.append("<td>联系电话：</td>");
				userStr.append("<td><input id=\"contactNumber\" name=\"contactNumber\" type=\"text\" value=\"\"/></td>");
				userStr.append("</tr>");
				userStr.append("<tr>");
				userStr.append("<td>年龄：</td>");
				userStr.append("<td><input id=\"age\" name=\"age\" type=\"text\" value=\"\"/></td>");
				userStr.append("<td>职务：</td>");
				userStr.append("<td>&nbsp;&nbsp;<select id=\"position\" name=\"position\">");
				for(int i = 0;i<userPosition.size();i++) {
					userStr.append("<option value=\""+userPosition.get(i).getCode() +"\" >"+userPosition.get(i).getName()+"</option>");
				}
				userStr.append("</select></td>");
				userStr.append("<td>所属组：</td>");
				userStr.append("<td>&nbsp;&nbsp;<select id=\"joinGroup\" name=\"joinGroup\">");
				for(int i = 0;i<userJoinGroup.size();i++) {
					userStr.append("<option value=\""+userJoinGroup.get(i).getCode() +"\" >"+userJoinGroup.get(i).getName()+"</option>");
				}
				userStr.append("</select></td>");
				userStr.append("</tr>");
				userStr.append("<tr>");
				userStr.append("<td>地址：</td>");
				userStr.append("<td colspan=\"3\"><input id=\"address\" name=\"address\" type=\"text\" value=\"\" style=\"width: 300px;\"/></td>");
				userStr.append("<td>是否启用：</td>");
				userStr.append("<td >&nbsp;&nbsp;<select id=\"deleteFlag\" name=\"deleteFlag\">");
				for(int i = 0;i<userSex.size();i++) {
					userStr.append("<option value=\""+userDelete.get(i).getCode() +"\" >"+userDelete.get(i).getName()+"</option>");
				}
				userStr.append("</select></td>");
				userStr.append("</tr>");
			}
			userStr.append("</table>");
			userStr.append("<div class=\"buttons\"><a href=\"javascript:saveUser();\">保存</a><a href=\"javascript:userInfocloses();\">关闭</a></div>");
			userStr.append("</div>");
			return JSonRespone.makeHasContentJSonRespone("0", "保存成功！参数错误！",userStr.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return JSonRespone.makeHasContentJSonRespone("1", "保存失败！参数错误！");
	}

	@RequestMapping("/saveUserInfo.jspx")
	@ResponseBody
	public JSonRespone saveUserInfo(MarketingUserInfoDomain userInfo) {
		try {
			if(userInfo.getId() == null) {
				userInfo.setPassword(new MD5Util().getMD5ofStr("123456"));
				marketingUserInfoService.addMarketingUserInfo(userInfo);
			}else {
				marketingUserInfoService.updateMarketingUserInfo(userInfo);
			}
			return JSonRespone.makeHasContentJSonRespone("0", "编辑成功！");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSonRespone.makeHasContentJSonRespone("1", "保存失败！参数错误！");
	}

	
}
