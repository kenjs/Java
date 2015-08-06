package com.cy.driver.domain;

/**
 * 订单domain
 * @date 2014-6-5
 * @author haoyong
 *
 */
public class TransactionInfoDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7143531606640202736L;

	private String id;//主键（自增、初始值1）
	private String orderNumber;//订单编号
	private String cargoId;//货源ID
	private String driverId;//车源ID
	private String deployUserid;//用户ID
	private String companyId;//企业ID
	private String tradeFair;//交易金额
	private String tradeStart;//交易状态
	private String tradeStartTime;//交易状态修改时间
	private String orderStart;//订单状态（是否有效）
	private String remark;//备注
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	private String tradeCancelOrigin;//交易取消来源
	
	private String companyName;
	private String driverName;
	private String deployUserName;
	//货物
	private String cargoName;//货物名称
	private String cargoType;//货物类型
	private String cargoWeight;//重量（货物）
	private String cargoCubage;//体积（货物）
	private String requestCarLength;//车型要求（车长）
	private String requestCarPlateType;//板-平板、高低板
	private String requestCarBarType;//车型要求（车 栏）
	private String freight;//发布运费价格
	private String requestStartTime;//要求装货时间
	private String requestEndTime;//要求到货时间
	private String startProvince;//装货地-省
	private String startCity;//装货地-市
	private String startCounty;//装货地-县
	private String startTown;//装货地-自定义地址
	private String endProvince;//卸货地-省
	private String endCity;//卸货地-市
	private String endCounty;//卸货地-县
	private String endTown;//卸货地-自定义地址
	private String contactName;//联系人
	private String contactMobilephone;//手机号
	private String contactTelephone;
	
	private String shipperCode;//发货方编码代码
	private String shipperOrderNo;//发货单号
	private String deliveryTime;//发布时间
	private String arrivalTime;//到货时间
	private String receiveTime;//收货时间
	private String receiverCode;//收货方编码代码
	private String receiverOrderNo;//收货方订单编号
	private String shipperCompanyCode;//物流公司编码代码
	
	private String shipperCompanyId;//物流公司id
	private String shipperCodeId;//发货方id'
	private String receiverCodeId;//收货方id
	
	private String shipperCompanyName;//物流公司名称
	private String shipperCodeName;//发货方名称
	private String receiverCodeName;//收货方名称

    private int invoice;        //发货单
    private int receipt;        //回单

	private int orderType;		//订单类型(1 在线定车订单 2 导入的订单)
	private int quantity;
	
	public TransactionInfoDomain() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNumber() {
        if (orderNumber == null) {
            return "";
        }
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCargoId() {
        if (cargoId == null) {
            return "";
        }
		return cargoId;
	}
	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}
	public String getDriverId() {
        if (driverId == null) {
            return "";
        }
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDeployUserid() {
        if (deployUserid == null) {
            return "";
        }
		return deployUserid;
	}
	public void setDeployUserid(String deployUserid) {
		this.deployUserid = deployUserid;
	}
	public String getCompanyId() {
        if (companyId == null) {
            return "";
        }
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getTradeFair() {
        if (tradeFair == null) {
            return "";
        }
		return tradeFair;
	}
	public void setTradeFair(String tradeFair) {
		this.tradeFair = tradeFair;
	}
	public String getTradeStart() {
        if (tradeStart == null) {
            return "";
        }
		return tradeStart;
	}
	public void setTradeStart(String tradeStart) {
		this.tradeStart = tradeStart;
	}
	public String getTradeStartTime() {
        if (tradeStartTime == null) {
            return "";
        }
		return tradeStartTime;
	}
	public void setTradeStartTime(String tradeStartTime) {
		this.tradeStartTime = tradeStartTime;
	}
	public String getContactTelephone() {
        if (contactTelephone == null) {
            return "";
        }
		return contactTelephone;
	}
	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}
	public String getOrderStart() {
        if (orderStart == null) {
            return "";
        }
		return orderStart;
	}
	public void setOrderStart(String orderStart) {
		this.orderStart = orderStart;
	}
	public String getRemark() {
        if (remark == null) {
            return "";
        }
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
        if (createTime == null) {
            return "";
        }
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
        if (modifyTime == null) {
            return "";
        }
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getCargoName() {
        if (cargoName == null) {
            return "";
        }
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getCargoType() {
        if (cargoType == null) {
            return "";
        }
		return cargoType;
	}
	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}
	public String getCargoWeight() {
        if (cargoWeight == null) {
            return "";
        }
        return cargoWeight;
	}
	public void setCargoWeight(String cargoWeight) {
		this.cargoWeight = cargoWeight;
	}
	public String getCargoCubage() {
        if (cargoCubage == null) {
            return "";
        }
		return cargoCubage;
	}
	public void setCargoCubage(String cargoCubage) {
		this.cargoCubage = cargoCubage;
	}
	public String getRequestCarLength() {
        if (requestCarLength == null) {
            return "";
        }
		return requestCarLength;
	}
	public void setRequestCarLength(String requestCarLength) {
		this.requestCarLength = requestCarLength;
	}
	public String getRequestCarPlateType() {
        if (requestCarPlateType == null) {
            return "";
        }
		return requestCarPlateType;
	}
	public void setRequestCarPlateType(String requestCarPlateType) {
		this.requestCarPlateType = requestCarPlateType;
	}
	public String getRequestCarBarType() {
        if (requestCarBarType == null) {
            return "";
        }
		return requestCarBarType;
	}
	public void setRequestCarBarType(String requestCarBarType) {
		this.requestCarBarType = requestCarBarType;
	}
	public String getFreight() {
        if (freight == null) {
            return "";
        }
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getRequestStartTime() {
        if (requestStartTime == null) {
            return "";
        }
		return requestStartTime;
	}
	public void setRequestStartTime(String requestStartTime) {
		this.requestStartTime = requestStartTime;
	}
	public String getRequestEndTime() {
        if (requestEndTime == null) {
            return "";
        }
		return requestEndTime;
	}
	public void setRequestEndTime(String requestEndTime) {
		this.requestEndTime = requestEndTime;
	}
	public String getStartProvince() {
        if (startProvince == null) {
            return "";
        }
		return startProvince;
	}
	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}
	public String getStartCity() {
        if (startCity == null) {
            return "";
        }
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getStartCounty() {
        if (startCounty == null) {
            return "";
        }
		return startCounty;
	}
	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}
	public String getStartTown() {
        if (startTown == null) {
            return "";
        }
		return startTown;
	}
	public void setStartTown(String startTown) {
		this.startTown = startTown;
	}
	public String getEndProvince() {
        if (endProvince == null) {
            return "";
        }
		return endProvince;
	}
	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}
	public String getEndCity() {
        if (endCity == null) {
            return "";
        }
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public String getEndCounty() {
        if (endCounty == null) {
            return "";
        }
		return endCounty;
	}
	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}
	public String getEndTown() {
        if (endTown == null) {
            return "";
        }
		return endTown;
	}
	public void setEndTown(String endTown) {
		this.endTown = endTown;
	}
	public String getContactName() {
        if (contactName == null) {
            return "";
        }
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getCompanyName() {
        if (companyName == null) {
            return "";
        }
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContactMobilephone() {
        if (contactMobilephone == null) {
            return "";
        }
		return contactMobilephone;
	}
	public void setContactMobilephone(String contactMobilephone) {
		this.contactMobilephone = contactMobilephone;
	}
	public String getTradeCancelOrigin() {
        if (tradeCancelOrigin == null) {
            return "";
        }
		return tradeCancelOrigin;
	}
	public void setTradeCancelOrigin(String tradeCancelOrigin) {
		this.tradeCancelOrigin = tradeCancelOrigin;
	}
	public String getShipperCode() {
        if (shipperCode == null) {
            return "";
        }
		return shipperCode;
	}
	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}
	public String getShipperOrderNo() {
        if (shipperOrderNo == null) {
            return "";
        }
		return shipperOrderNo;
	}
	public void setShipperOrderNo(String shipperOrderNo) {
		this.shipperOrderNo = shipperOrderNo;
	}
	public String getDeliveryTime() {
        if (deliveryTime == null) {
            return "";
        }
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getArrivalTime() {
        if (arrivalTime == null) {
            return "";
        }
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getReceiveTime() {
        if (receiveTime == null) {
            return "";
        }
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getReceiverCode() {
        if (receiverCode == null) {
            return "";
        }
		return receiverCode;
	}
	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}
	public String getReceiverOrderNo() {
        if (receiverOrderNo == null) {
            return "";
        }
		return receiverOrderNo;
	}
	public void setReceiverOrderNo(String receiverOrderNo) {
		this.receiverOrderNo = receiverOrderNo;
	}
	public String getDriverName() {
        if (driverName == null) {
            return "";
        }
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDeployUserName() {
        if (deployUserName == null) {
            return "";
        }
		return deployUserName;
	}
	public void setDeployUserName(String deployUserName) {
		this.deployUserName = deployUserName;
	}
	public String getShipperCompanyCode() {
        if (shipperCompanyCode == null) {
            return "";
        }
		return shipperCompanyCode;
	}
	public void setShipperCompanyCode(String shipperCompanyCode) {
		this.shipperCompanyCode = shipperCompanyCode;
	}
	public String getShipperCompanyId() {
        if (shipperCompanyId == null) {
            return "";
        }
		return shipperCompanyId;
	}
	public void setShipperCompanyId(String shipperCompanyId) {
		this.shipperCompanyId = shipperCompanyId;
	}
	public String getShipperCodeId() {
        if (shipperCodeId == null) {
            return "";
        }
		return shipperCodeId;
	}
	public void setShipperCodeId(String shipperCodeId) {
		this.shipperCodeId = shipperCodeId;
	}
	public String getReceiverCodeId() {
        if (receiverCodeId == null) {
            return "";
        }
		return receiverCodeId;
	}
	public void setReceiverCodeId(String receiverCodeId) {
		this.receiverCodeId = receiverCodeId;
	}
	public String getShipperCompanyName() {
        if (shipperCompanyName == null) {
            return "";
        }
		return shipperCompanyName;
	}
	public void setShipperCompanyName(String shipperCompanyName) {
		this.shipperCompanyName = shipperCompanyName;
	}
	public String getShipperCodeName() {
        if (shipperCodeName == null) {
            return "";
        }
		return shipperCodeName;
	}
	public void setShipperCodeName(String shipperCodeName) {
		this.shipperCodeName = shipperCodeName;
	}
	public String getReceiverCodeName() {
        if (receiverCodeName == null) {
            return "";
        }
		return receiverCodeName;
	}
	public void setReceiverCodeName(String receiverCodeName) {
		this.receiverCodeName = receiverCodeName;
	}

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getReceipt() {
        return receipt;
    }

    public void setReceipt(int receipt) {
        this.receipt = receipt;
    }

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
