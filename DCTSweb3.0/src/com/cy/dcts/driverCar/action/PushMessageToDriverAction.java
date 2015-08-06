package com.cy.dcts.driverCar.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.NoteLogInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.push.BaiduPush;
import com.cy.dcts.ipUrlStr.service.IIpUrlStrService;
import com.cy.dcts.note.service.INoteService;
/**
 * 货主确认定车时，提醒司机拉货（百度云推送消息给司机）
 * @author zdy
 *
 */
public class PushMessageToDriverAction extends BaseJsonAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private DriverUserInfoDomain driverUserInfoDomain;
	
	
	@Override
	protected void execMethod() throws Exception {
		//判断是否登陆
		if (getSessionUser() == null) {
			this.sendResponseToJson("1", "请先登录");
			return ;
		}
		/**
		 * 多处调用到该action，type是不同入口传入的值相当与一个标志（暂时备用）
		 *type=1是个人中心首页及我的订单中的直接点击"提醒司机确认"；
		 *type=2是通过货找车，车找货的确认定车成功后同时提醒司机确认；
		 *type=3是货主取消订单
		 *type=4是货主确认收货
		 */
		String type=request.getParameter("type");
		String description="";
		String titleContext="";
		logger.debug("baidu push message to driver begin!");
		if("1".equals(type)||"2".equals(type)){
			titleContext="【快到网】恭喜您！";
			description="有一笔订单等待您确认！";
		}else if("3".equals(type)){
			titleContext="【快到网】很遗憾！";
			description="对方取消了给您的订单！";
		}else if("4".equals(type)){
			titleContext="【快到网】恭喜您！";
			description="您有订单已收货确认,可评价！";
		}
		
		boolean retVal=BaiduPush.pushUnicastNotification(Long.decode(driverUserInfoDomain.getBaiduChannelId()), driverUserInfoDomain.getBaiduUserId(),titleContext, description);
		if(retVal==true){
			this.sendResponseToJson("0", "success");
			logger.debug("baidu push message to driver success!");
		}else{
			this.sendResponseToJson("2", "通知司机失败");
			logger.debug("baidu push message to driver fails!");
		}
	}
	
	public DriverUserInfoDomain getDriverUserInfoDomain() {
		return driverUserInfoDomain;
	}
	public void setDriverUserInfoDomain(DriverUserInfoDomain driverUserInfoDomain) {
		this.driverUserInfoDomain = driverUserInfoDomain;
	}

}
