package com.cy.dcts.location.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.LocationCollectInfo;
import com.cy.dcts.common.bo.TransactionLastInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.LocationCollectInfoDomain;
import com.cy.dcts.common.domain.LocationCollectLastInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.util.SysToolsUtil;
import com.cy.dcts.location.service.ILocationInfoService;
import com.cy.dcts.transactionLast.service.ITransactionLastService;
/**
 * 查询司机历史轨迹（轨迹跟踪）
 * @author zdy
 *
 */
public class QueryLocationInfoAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private ILocationInfoService locationInfoService;
    private ITransactionLastService transactionLastService;
    
    private LocationCollectInfoDomain locationCollectInfoDomain;
    private LocationCollectInfo locationCollectInfo;
    private LocationCollectLastInfoDomain locationCollectLastInfoDomain;
    private String flag="";// 区分点击的是"0"搜索按钮（初始化从第一条开始查）还是"1"分页链接
    
	@Override
	protected String execMethod() throws Exception {
		//判断是否登陆
		if (getSessionUser() == null) {
			return LOGIN;
		}
		logger.debug("query location info begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		//参数验证
		if(locationCollectInfoDomain==null){
			locationCollectInfoDomain=new LocationCollectInfoDomain();
		}
		if(locationCollectInfoDomain.getPageInfo()==null||"0".equals(flag)){//0是点击搜索按钮
			locationCollectInfoDomain.setPageInfo(new PageInfo());
		}
		if(SysToolsUtil.isNullOrEmpty(locationCollectInfoDomain.getDriverId())){
			logger.debug("query location info parameter error !");
			return ERROR;
		}
		
		if(StringUtils.isNotEmpty(locationCollectInfoDomain.getTradeId())){//我的订单中的历史轨迹跟踪
		
	  /**
	   *原有的： 货物跟踪：2待装货，3和7（7是司机确认已卸货，货主这边还未确认送达所有web端仍然处于运输跟踪）运输跟踪，4已送达，5.订单完成	
	   * (1)待装货，运输跟踪:位置收集时间>=待装货修改时间
	   * (2)已送达，订单完成:待装货修改时间<= 位置收集时间<=已经送达的交易状态的修改时间
	   * 现在：运输跟踪状态修改日期<=位置收集时间<=运输跟踪状态修改日期+10天
	   */
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			//根据交易Id和运输跟踪查询交易历史记录，得到运输跟踪状态修改时间20140705
			TransactionLastInfo transactionLastInfo=transactionLastService.queryTransactionLastByIdAndStart(locationCollectInfoDomain.getTradeId(), Constants.TRADE_START_IN_TRANSIT_KEY);
			  //存放运输跟踪状态修改时间20140705
			if(transactionLastInfo!=null){
				locationCollectInfoDomain.setAfterLoadTradeModifyTime(dateFormat.format(transactionLastInfo.getCreateTime()));
				//运输跟踪状态修改时间(开始时间)+10天=结束时间
				GregorianCalendar gc=new GregorianCalendar(); 
				gc.setTime(dateFormat.parse(dateFormat.format(transactionLastInfo.getCreateTime()))); 
				gc.add(+5,10); //+5表示加天数
				gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
				locationCollectInfoDomain.setArrivedTradeModifyTime(dateFormat.format(gc.getTime()));
					
				/**if(Constants.TRADE_START_AFTER_LOADING_KEY.equals(locationCollectInfoDomain.getTradeStart())||
					//	Constants.TRADE_START_IN_TRANSIT_KEY.equals(locationCollectInfoDomain.getTradeStart())||
						Constants.TRADE_START_DISBURDEN_KEY.equals(locationCollectInfoDomain.getTradeStart())){
					//当前交易状态为:待装货和运输跟踪的 
					locationCollectInfoDomain.setArrivedTradeModifyTime("");
				//}else if(Constants.TRADE_START_SUCCESS_KEY.equals(locationCollectInfoDomain.getTradeStart())){//当前交易状态为:订单完成的
					
				}*/
			}
		
		}else{//合同司机信息中的历史轨迹跟踪
			locationCollectInfoDomain.setAfterLoadTradeModifyTime("");
		}
		logger.debug("query location collect last domain by driverId begin.");
		locationCollectLastInfoDomain=locationInfoService.queryLocationCollectLastDomainByDriverId(locationCollectInfoDomain.getDriverId());
		
		logger.debug("query location collectInfo driverId byPage begin.");
		List<LocationCollectInfoDomain> list=locationInfoService.queryLocationCollectInfoByDriverId(locationCollectInfoDomain);
		locationCollectInfoDomain.setList(list);
		logger.debug("query location info success. locationCollectInfoDomain.list=[{}]",list.size());
		return SUCCESS;
	}
	public ILocationInfoService getLocationInfoService() {
		return locationInfoService;
	}
	public void setLocationInfoService(ILocationInfoService locationInfoService) {
		this.locationInfoService = locationInfoService;
	}
	public LocationCollectInfoDomain getLocationCollectInfoDomain() {
		return locationCollectInfoDomain;
	}
	public void setLocationCollectInfoDomain(
			LocationCollectInfoDomain locationCollectInfoDomain) {
		this.locationCollectInfoDomain = locationCollectInfoDomain;
	}
	
	public LocationCollectInfo getLocationCollectInfo() {
		return locationCollectInfo;
	}
	public void setLocationCollectInfo(LocationCollectInfo locationCollectInfo) {
		this.locationCollectInfo = locationCollectInfo;
	}
	public void setTransactionLastService(
			ITransactionLastService transactionLastService) {
		this.transactionLastService = transactionLastService;
	}
	public LocationCollectLastInfoDomain getLocationCollectLastInfoDomain() {
		return locationCollectLastInfoDomain;
	}
	public void setLocationCollectLastInfoDomain(
			LocationCollectLastInfoDomain locationCollectLastInfoDomain) {
		this.locationCollectLastInfoDomain = locationCollectLastInfoDomain;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
  
}
