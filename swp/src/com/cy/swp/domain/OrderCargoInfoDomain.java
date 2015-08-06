package com.cy.swp.domain;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.util.SysToolsUtil;


public class OrderCargoInfoDomain extends BaseDomain{
	private static final long serialVersionUID = 8617332575268144232L;
	private Integer id;
	private String cargoName;// 货物名称
	private String cargoType;// 货物类型
	private double cargoWeight;// 货物重量
	private double cargoCubage;// 货物体积
	private String requestCarLength;// 车型要求（车 长）
	private String requestCarBarType;// 车型要求（车 栏）
	private String requestCarPlateType;//板-平板、高低板
	private double freight;// 发布运费价格
	private String requestStartTime;// 要求装货时间
	private String requestEndTime;// 要求到货时间
	private String startProvince;// 装货地-省
	private String startCity;// 装货地-市
	private String startCounty;// 装货地-县
	private String startTown;// 装货地-自定义地址
	private String endProvince;// 卸货-省
	private String endCity;// 卸货-市
	private String endCounty;// 卸货-县
	private String endTown;// 卸货地-自定义地址
	private String contactName;// 联系人
	private String contactMobilephone;// 手机
	private String contactTelephone;// 固定电话
	private String remark;// 备注
	private String deployUserid;// 发布用户ID
	private String modifyUserid;// 修改用户ID
	private String companyId;// 企业ID
	private String cargoFlag;// 货源状态
	private String deletedFlag;// 删除状态
	private String cargoFlagTime;// 状态修改时间
	private String createTime;// 创建时间
	private String modifyTime;// 修改时间

	private String cargoOrigin;//插入标识（0发布货源、1导入订单货源、2营销平台导入货源）

	private String startProCityCounty;//装货地:省市区县
	private String endProCityCounty;//卸货地:省市区县
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String cargoFlagVal;// 货源状态值
	private String isExpire;//是否过期

	private String companyName;//企业名称
	private String quoteCount;//报价数量
	private String cargoAssessCount;//货源的点评数量
	
	//营销平台20141016
	private String regCompanyId;//导入公司是否已注册
	private String assistId;//协助Id
	private String cargoResult;//货源真实情况：-1 没有这个货 0 未确认 1 货还在 2 货已走
	private String marketUserId;//专员用户ID (pk-t_marketing_user_info.id)
	private String cargoInfoFrom;//货源信息来源：1 一点通 2 物流之家
	private String isMatchSuccess;//是否匹配成功(电话过的司机是否有意向拉这条货，即至少有一辆车有意向就是匹配成功)
	private String hasTransaction;//是否达成交易，以此货源产生了订单为准
	private String firstDate;//首次联系时间

	private String isMatchSuccessVal;//是否匹配成功(0未联络司机，1.成功，2.失败)
	private String cargoResultVal;//货源回复值
	private String regCompanyIdVal;//是or否
	private String importCount;//导入的数量 
	private String mark;//标记
	private String contactDriverCount;//针对某天货源联系过的司机次数
	private String contactCargoCount;//针对某个公司下的货源联系了多少条
	
	private String menuAId;//个人中心左边菜单超链接的Id
	private String errorMessage;//错误信息
	private List<OrderCargoInfoDomain> list;
	
	private String matchDriverCount;//匹配车源数量
	
	private String html1;
	private String html2;
	private String html3;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getCargoType() {
		return cargoType;
	}
	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}

	public String getRequestCarLength() {
		return requestCarLength;
	}
	public void setRequestCarLength(String requestCarLength) {
		this.requestCarLength = requestCarLength;
	}
	public String getRequestCarBarType() {
		return requestCarBarType;
	}
	public void setRequestCarBarType(String requestCarBarType) {
		this.requestCarBarType = requestCarBarType;
	}
	public String getRequestStartTime() {
		return requestStartTime;
	}
	public void setRequestStartTime(String requestStartTime) {
		this.requestStartTime = requestStartTime;
	}
	public String getRequestEndTime() {
		return requestEndTime;
	}
	public void setRequestEndTime(String requestEndTime) {
		this.requestEndTime = requestEndTime;
	}
	public String getStartProvince() {
		return startProvince;
	}
	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getStartCounty() {
		return startCounty;
	}
	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}
	public String getStartTown() {
		return startTown;
	}
	public void setStartTown(String startTown) {
		this.startTown = startTown;
	}
	public String getEndProvince() {
		return endProvince;
	}
	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public String getEndCounty() {
		return endCounty;
	}
	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}
	public String getEndTown() {
		return endTown;
	}
	public void setEndTown(String endTown) {
		this.endTown = endTown;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactMobilephone() {
		return contactMobilephone;
	}
	public void setContactMobilephone(String contactMobilephone) {
		this.contactMobilephone = contactMobilephone;
	}
	public String getContactTelephone() {
		return contactTelephone;
	}
	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeployUserid() {
		return deployUserid;
	}
	public void setDeployUserid(String deployUserid) {
		this.deployUserid = deployUserid;
	}
	public String getModifyUserid() {
		return modifyUserid;
	}
	public void setModifyUserid(String modifyUserid) {
		this.modifyUserid = modifyUserid;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCargoFlag() {
		return cargoFlag;
	}
	public void setCargoFlag(String cargoFlag) {
		this.cargoFlag = cargoFlag;
		if(SysToolsUtil.isNullOrEmpty(cargoFlag)){
			return;
		}
		try {
			if(cargoFlag.equals(Constants.CARGO_FLAG_PENDING_TRADE_KEY)){
				this.setCargoFlagVal(Constants.CARGO_FLAG_PENDING_TRADE_VALUE);
			}else if(cargoFlag.equals(Constants.CARGO_FLAG_TRADING_KEY)){
				this.setCargoFlagVal(Constants.CARGO_FLAG_TRADING_VALUE);
			}else if(cargoFlag.equals(Constants.CARGO_FLAG_SUCCESS_KEY)){
				this.setCargoFlagVal(Constants.CARGO_FLAG_SUCCESS_VALUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getCargoFlagTime() {
		return cargoFlagTime;
	}
	public void setCargoFlagTime(String cargoFlagTime) {
		this.cargoFlagTime = cargoFlagTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getStartProCityCounty() {
		return startProCityCounty;
	}
	public void setStartProCityCounty(String startProCityCounty) {
		this.startProCityCounty = startProCityCounty;
	}
	public String getEndProCityCounty() {
		return endProCityCounty;
	}
	public void setEndProCityCounty(String endProCityCounty) {
		this.endProCityCounty = endProCityCounty;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public double getCargoWeight() {
		return cargoWeight;
	}
	public void setCargoWeight(double cargoWeight) {
		this.cargoWeight = cargoWeight;
	}
	public double getCargoCubage() {
		return cargoCubage;
	}
	public void setCargoCubage(double cargoCubage) {
		this.cargoCubage = cargoCubage;
	}
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	public String getRequestCarPlateType() {
		return requestCarPlateType;
	}
	public void setRequestCarPlateType(String requestCarPlateType) {
		this.requestCarPlateType = requestCarPlateType;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMenuAId() {
		return menuAId;
	}
	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}
	public List<OrderCargoInfoDomain> getList() {
		return list;
	}
	public void setList(List<OrderCargoInfoDomain> list) {
		this.list = list;
	}
	public String getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCargoFlagVal() {
		return cargoFlagVal;
	}
	public void setCargoFlagVal(String cargoFlagVal) {
		this.cargoFlagVal = cargoFlagVal;

	}
	public String getQuoteCount() {
		return quoteCount;
	}
	public void setQuoteCount(String quoteCount) {
		this.quoteCount = quoteCount;
	}
	public String getIsExpire() {
		return isExpire;
	}
	public void setIsExpire(String isExpire) {
		this.isExpire = isExpire;
	}
	public String getCargoAssessCount() {
		return cargoAssessCount;
	}
	public void setCargoAssessCount(String cargoAssessCount) {
		this.cargoAssessCount = cargoAssessCount;
	}
	public String getCargoOrigin() {
		return cargoOrigin;
	}
	public void setCargoOrigin(String cargoOrigin) {
		this.cargoOrigin = cargoOrigin;
	}
	public String getImportCount() {
		return importCount;
	}
	public void setImportCount(String importCount) {
		this.importCount = importCount;
	}
	public String getRegCompanyId() {
		return regCompanyId;
	}
	public void setRegCompanyId(String regCompanyId) {
		this.regCompanyId = regCompanyId;
	}

	public String getRegCompanyIdVal() {
		return regCompanyIdVal;
	}
	public void setRegCompanyIdVal(String regCompanyIdVal) {
		this.regCompanyIdVal = regCompanyIdVal;
	}
	public String getAssistId() {
		return assistId;
	}
	public void setAssistId(String assistId) {
		this.assistId = assistId;
	}
	public String getCargoResult() {
		return cargoResult;
	}
	public void setCargoResult(String cargoResult) {
		this.cargoResult = cargoResult;
//		if (StringUtils.isEmpty(cargoResult)) {
//            return;
//        }
//        try {
//        	if(Constants.CARGO_REPLYRESULT_NONENTITY_KEY.equals(cargoResult)){
//    			this.setCargoResultVal(Constants.CARGO_REPLYRESULT_NONENTITY_VALUE);
//    		}else if(Constants.CARGO_REPLYRESULT_UNKNOWN_KEY.equals(cargoResult)){
//    			this.setCargoResultVal(Constants.CARGO_REPLYRESULT_UNKNOWN_VALUE);
//    		}else if(Constants.CARGO_REPLYRESULT_EXIST_KEY.equals(cargoResult)){
//    			this.setCargoResultVal(Constants.CARGO_REPLYRESULT_EXIST_VALUE);
//    		}else if(Constants.CARGO_REPLYRESULT_HAD_COVERED_KEY.equals(cargoResult)){
//    			this.setCargoResultVal(Constants.CARGO_REPLYRESULT_HAD_COVERED_VALUE);
//    		}
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
	}
	public String getMarketUserId() {
		return marketUserId;
	}
	public void setMarketUserId(String marketUserId) {
		this.marketUserId = marketUserId;
	}
	public String getCargoInfoFrom() {
		return cargoInfoFrom;
	}
	public void setCargoInfoFrom(String cargoInfoFrom) {
		this.cargoInfoFrom = cargoInfoFrom;
	}
	public String getIsMatchSuccess() {
		return isMatchSuccess;
	}
	public void setIsMatchSuccess(String isMatchSuccess) {
		this.isMatchSuccess = isMatchSuccess;
//		if(StringUtils.isEmpty(isMatchSuccess)){
//			return ;
//		}
//		try {
//			if(Constants.BRIDGING_NOT_SUCCESS.equals(isMatchSuccess)){
//				this.setIsMatchSuccessVal(Constants.BRIDGING_NOT_SUCCESS_VALUE);
//			}else if(Constants.BRIDGING_SUCCESS.equals(isMatchSuccess)){
//				this.setIsMatchSuccessVal(Constants.BRIDGING_SUCCESS_VALUE);
//			}else if(Constants.NOT_BRIDGING_KEY.equals(isMatchSuccess)){
//				this.setIsMatchSuccessVal(Constants.NOT_BRIDGING_VALUE);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	public String getHasTransaction() {
		return hasTransaction;
	}
	public void setHasTransaction(String hasTransaction) {
		this.hasTransaction = hasTransaction;
	}
	public String getIsMatchSuccessVal() {
		return isMatchSuccessVal;
	}
	public void setIsMatchSuccessVal(String isMatchSuccessVal) {
		this.isMatchSuccessVal = isMatchSuccessVal;
	}
	public String getCargoResultVal() {
		return cargoResultVal;
	}
	public void setCargoResultVal(String cargoResultVal) {
		this.cargoResultVal = cargoResultVal;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getContactDriverCount() {
		return contactDriverCount;
	}
	public void setContactDriverCount(String contactDriverCount) {
		this.contactDriverCount = contactDriverCount;
	}
	public String getContactCargoCount() {
		return contactCargoCount;
	}
	public void setContactCargoCount(String contactCargoCount) {
		this.contactCargoCount = contactCargoCount;
	}
	public String getMatchDriverCount() {
		return matchDriverCount;
	}
	public void setMatchDriverCount(String matchDriverCount) {
		this.matchDriverCount = matchDriverCount;
	}
	public String getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}
	public String getHtml1() {
		return html1;
	}
	public void setHtml1(String html1) {
		this.html1 = html1;
	}
	public String getHtml2() {
		return html2;
	}
	public void setHtml2(String html2) {
		this.html2 = html2;
	}
	public String getHtml3() {
		return html3;
	}
	public void setHtml3(String html3) {
		this.html3 = html3;
	}

	

}
