package com.cy.dcts.transaction.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.DriverUserInfo;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.common.util.ValidateUtil;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.transaction.service.ITransactionInfoService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
import com.mysql.jdbc.MysqlDataTruncation;

public class SaveImportTrandeCargoAction extends BasePageAction{
	private static final long serialVersionUID = -1632633988764801448L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;
	
    private IDriverUserCarInfoService driverUserCarInfoService;
	private IQueryWebUserInfoService queryWebUserInfoService;
	
	private TransactionInfoDomain transactionInfoDomain=new TransactionInfoDomain();
	
    private String sucessMessages="";//保存成功的数据信息
    private String retMessages="";//保存不符合条件的数据到页面
    private String errorMessage="";//保存错误信息
	
	private String cargoNames[];
	private String startProvinces[];
	private String startCitys[];
	private String startCountys[];
	private String endProvinces[];
	private String endCitys[];
	private String endCountys[];
	private String shipperCodes[];
	private String shipperOrderNos[];
	private String receiverCodes[];
	private String receiverOrderNos[];
	private String shipperCompanyCodes[];
	private String codes[];
	private String driverIds[]; 
	@Override
	protected String execMethod() throws Exception {
		if(getSessionUser() == null){
			logger.warn("save import transaction order cargo fail. session is empty.");
			return LOGIN;
		}
		try {
		//将字段数组中的值循环存入对象并保持入集合
		List<TransactionInfo> tradeList = new ArrayList<TransactionInfo>();
		List<OrderCargoInfo> cargoList = new ArrayList<OrderCargoInfo>();
		//返回有不符合要求的司机信息（显示在列表）
		List<TransactionInfoDomain> tradeDomainList = new ArrayList<TransactionInfoDomain>();
		for (int a = 0; a < cargoNames.length; a++) {
			TransactionInfo trandeInfoDomain=new TransactionInfo();
			OrderCargoInfo orderCargoInfo=new OrderCargoInfo();
			//保存不符合要求的对象
			TransactionInfoDomain trdeDm=new TransactionInfoDomain();
			//标志
			String flags="";
			
		    String cargoName=cargoNames[a];
			String startProvince=startProvinces[a];
			String startCity=startCitys[a];
			String startCounty=startCountys[a];
			String endProvince=endProvinces[a];
			String endCity=endCitys[a];
			String endCounty=endCountys[a];
			String shipperCode=shipperCodes[a];
			String shipperOrderNo=shipperOrderNos[a];
			String receiverCode=receiverCodes[a];
			String receiverOrderNo=receiverOrderNos[a];
			String shipperCompanyCode=shipperCompanyCodes[a]; 
			String code=codes[a];//司机手机号
			String driverId=null;
			
			//-------------校验数据-------------
			//物流企业登录发货方必填
			if(StringUtils.isEmpty(shipperCode)&&Constants.USER_TYPE_ENTERPRISE_KEY.equals(getSessionUser().getUserType())){
				flags+="7";
			}
			
			//验证发货方编码
			if(StringUtils.isNotEmpty(shipperCode)){
				if(Constants.USER_TYPE_SHIPPER_KEY.equals(getSessionUser().getUserType())){//登录者是发货方
					if(!shipperCode.equals(getSessionUser().getEncoded())){//登录者输入的发货方编码与自己的实际编码不一致
						flags+="7";
					}else{
						trandeInfoDomain.setShipperCodeId(getSessionUser().getId());
					}
				}else{
					//(1)验证修改的子货主（发货方）是否有效，表t_web_user_info根据父级userId，用户类型和发货方编码
					Integer shipperId=queryWebUserInfoService.queryWebUserByParentIdUsertypeEncoded(getSessionUser().getId(),shipperCode,Constants.USER_TYPE_SHIPPER_KEY);
					if(shipperId==null||"".equals(shipperId)){
						logger.warn("transaction info shipper code not exists.");
						flags+="7";
					}else{
						trandeInfoDomain.setShipperCodeId(shipperId.toString());
					}
				}
				}
			
			//校验发货单号不为空
			if(StringUtils.isEmpty(shipperOrderNo)){
				flags+="8";
			}
			
			
			//验证收货方编码(必填)
			if(StringUtils.isNotEmpty(receiverCode)) {
				//（2）判断修改的收货方是否有效（在web_user_info表中存在） 根据收货方编码，用户类型和父级Id（即当请登录者的userId）
				Integer receiverId=queryWebUserInfoService.queryWebUserByParentIdUsertypeEncoded(getSessionUser().getId(),receiverCode,Constants.USER_TYPE_RECEIVE_KEY);
				if(receiverId==null||"".equals(receiverId)){
					logger.warn("import transaction info receiver not exists.");
					flags+="9";
				}else{
					trandeInfoDomain.setReceiverCodeId(receiverId.toString());
				}
			}else{
				flags+="9";
			}
			
			//校验收货单号不为空
			if(StringUtils.isEmpty(receiverOrderNo)){
				flags+="a";
			}
		
			//校验物流企业，承运商不是必填的
			if(StringUtils.isNotEmpty(shipperCompanyCode)) {
				if(Constants.USER_TYPE_ENTERPRISE_KEY.equals(getSessionUser().getUserType())){//登录者是物流企业
					if(!shipperCompanyCode.equals(getSessionUser().getEncoded())){//登录者输入的物流企业编码与自己的实际编码不一致
						flags+="b";
					}else{
						trandeInfoDomain.setShipperCompanyId(getSessionUser().getId());
					}
				}else{
					//（3）验证修改的子账号承运商（物流企业）是否有效，表t_web_user_info根据父级userId，用户类型和物流企业用户的编码
					Integer shipperComId=queryWebUserInfoService.queryWebUserByParentIdUsertypeEncoded(getSessionUser().getId(),shipperCompanyCode,Constants.USER_TYPE_ENTERPRISE_KEY);
					if(shipperComId==null||"".equals(shipperComId)){
						logger.warn("transaction info shipper company not exists.");
						flags+="b";
					}else{
						trandeInfoDomain.setShipperCompanyId(shipperComId.toString());
					}
				}
				
			}
			
			
			// 校验司机号码   （ 物流企业导入订单货源，司机号码不能为空）
			if(StringUtils.isNotEmpty(code)&&ValidateUtil.validateTelePhone(code)) {
				//（4）.输入的手机号不为空时去判断该司机是否在司机库中存在
				DriverUserInfo driverUserInfo=driverUserCarInfoService.queryDriverInfoByCode(code);
				//发货人导入 时：若承运人不为空则承运商必填
				if(Constants.USER_TYPE_SHIPPER_KEY.equals(getSessionUser().getUserType())&&StringUtils.isEmpty(trandeInfoDomain.getShipperCompanyCode())){
					flags+="b";
				}
				if(driverUserInfo!=null){
					driverId=driverUserInfo.getId();
				}else{
					logger.warn("transaction info driver not exists.");
					flags+="c";
				}
				
			}
			
			
			if(flags.equals("")){//该对象的信息都符合要求 保存到数据库
				
				orderCargoInfo.setCargoName(cargoName);
				orderCargoInfo.setStartProvince(startProvince);
				orderCargoInfo.setStartCity(startCity);
				orderCargoInfo.setStartCounty(startCounty);
				orderCargoInfo.setEndProvince(endProvince);
				orderCargoInfo.setEndCity(endCity);
				orderCargoInfo.setEndCounty(endCounty);
				
				if(StringUtils.isEmpty(shipperCode)&&Constants.USER_TYPE_SHIPPER_KEY.equals(getSessionUser().getUserType())){//发货方登录导入
					trandeInfoDomain.setShipperCode(getSessionUser().getEncoded());
					trandeInfoDomain.setShipperCodeId(getSessionUser().getId());
				}else{
					trandeInfoDomain.setShipperCode(shipperCode);
				}
				if(StringUtils.isEmpty(shipperCompanyCode)&&Constants.USER_TYPE_ENTERPRISE_KEY.equals(getSessionUser().getUserType())){//物流企业登录导入
					trandeInfoDomain.setShipperCompanyCode(getSessionUser().getEncoded());
					trandeInfoDomain.setShipperCompanyId(getSessionUser().getId());
				}else{
					trandeInfoDomain.setShipperCompanyCode(shipperCompanyCode);
				}
				
				trandeInfoDomain.setShipperOrderNo(shipperOrderNo);
				trandeInfoDomain.setReceiverCode(receiverCode);
				trandeInfoDomain.setReceiverOrderNo(receiverOrderNo);
				
				trandeInfoDomain.setDriverId(driverId);
				cargoList.add(orderCargoInfo);
				tradeList.add(trandeInfoDomain);
			}else{//该对象的信息存在不符合要求的返回页面
				trdeDm.setCargoName(cargoName);
				trdeDm.setStartProvince(startProvince);
				trdeDm.setStartCity(startCity);
				trdeDm.setStartCounty(startCounty);
				trdeDm.setEndProvince(endProvince);
				trdeDm.setEndCity(endCity);
				trdeDm.setEndCounty(endCounty);
				trdeDm.setShipperCode(shipperCode);
				trdeDm.setShipperOrderNo(shipperOrderNo);
				trdeDm.setReceiverCode(receiverCode);
				trdeDm.setReceiverOrderNo(receiverOrderNo);
				trdeDm.setShipperCompanyCode(shipperCompanyCode);
				trdeDm.setCode(code);
				trdeDm.setFlas(flags);
				 tradeDomainList.add(trdeDm);
			}
			
			
		}

		//批量添加订单（保存符合要求的对象）
		logger.debug("batch add transaction info begin....");
		List <String> tradeListId=transactionInfoService.batchAddTransactionInfos(tradeList,cargoList, getSessionUser());
		sucessMessages="提示：成功保存"+tradeListId.size()+"条";//保存成功的数量
		//返回不符要求的信息到页面
		transactionInfoDomain.setList(tradeDomainList);
		retMessages="不符合规范的数据"+tradeDomainList.size()+"条";//保存失败的数量
		logger.debug("batch add transaction orderCargo info success!tradeListId.size=[{}]",tradeListId.size());
		
		if(tradeDomainList.size()<=0){
			return "yesSuccess";
		}
		} catch(MysqlDataTruncation msException){
			errorMessage="发货单号或收货单号太长";
			return ERROR;
		} catch (Exception e) {
			errorMessage="保存失败";
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public ITransactionInfoService getTransactionInfoService() {
		return transactionInfoService;
	}
	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}
	public String[] getCargoNames() {
		return cargoNames;
	}
	public void setCargoNames(String[] cargoNames) {
		this.cargoNames = cargoNames;
	}
	public String[] getStartProvinces() {
		return startProvinces;
	}
	public void setStartProvinces(String[] startProvinces) {
		this.startProvinces = startProvinces;
	}
	public String[] getStartCitys() {
		return startCitys;
	}
	public void setStartCitys(String[] startCitys) {
		this.startCitys = startCitys;
	}
	public String[] getStartCountys() {
		return startCountys;
	}
	public void setStartCountys(String[] startCountys) {
		this.startCountys = startCountys;
	}
	public String[] getEndProvinces() {
		return endProvinces;
	}
	public void setEndProvinces(String[] endProvinces) {
		this.endProvinces = endProvinces;
	}
	public String[] getEndCitys() {
		return endCitys;
	}
	public void setEndCitys(String[] endCitys) {
		this.endCitys = endCitys;
	}
	public String[] getEndCountys() {
		return endCountys;
	}
	public void setEndCountys(String[] endCountys) {
		this.endCountys = endCountys;
	}
	public String[] getShipperCodes() {
		return shipperCodes;
	}
	public void setShipperCodes(String[] shipperCodes) {
		this.shipperCodes = shipperCodes;
	}
	public String[] getShipperOrderNos() {
		return shipperOrderNos;
	}
	public void setShipperOrderNos(String[] shipperOrderNos) {
		this.shipperOrderNos = shipperOrderNos;
	}
	public String[] getReceiverCodes() {
		return receiverCodes;
	}
	public void setReceiverCodes(String[] receiverCodes) {
		this.receiverCodes = receiverCodes;
	}
	public String[] getReceiverOrderNos() {
		return receiverOrderNos;
	}
	public void setReceiverOrderNos(String[] receiverOrderNos) {
		this.receiverOrderNos = receiverOrderNos;
	}
	public String[] getShipperCompanyCodes() {
		return shipperCompanyCodes;
	}
	public void setShipperCompanyCodes(String[] shipperCompanyCodes) {
		this.shipperCompanyCodes = shipperCompanyCodes;
	}
	public String[] getCodes() {
		return codes;
	}
	public void setCodes(String[] codes) {
		this.codes = codes;
	}
	public String[] getDriverIds() {
		return driverIds;
	}
	public void setDriverIds(String[] driverIds) {
		this.driverIds = driverIds;
	}
	public IDriverUserCarInfoService getDriverUserCarInfoService() {
		return driverUserCarInfoService;
	}
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
	public IQueryWebUserInfoService getQueryWebUserInfoService() {
		return queryWebUserInfoService;
	}
	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

	public TransactionInfoDomain getTransactionInfoDomain() {
		return transactionInfoDomain;
	}

	public void setTransactionInfoDomain(TransactionInfoDomain transactionInfoDomain) {
		this.transactionInfoDomain = transactionInfoDomain;
	}

	public String getSucessMessages() {
		return sucessMessages;
	}

	public void setSucessMessages(String sucessMessages) {
		this.sucessMessages = sucessMessages;
	}

	public String getRetMessages() {
		return retMessages;
	}

	public void setRetMessages(String retMessages) {
		this.retMessages = retMessages;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	

}
