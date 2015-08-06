package tf56.contract.domain;

import java.util.ArrayList;
import java.util.List;

import tf56.sofa.util.SysUtil;

public class Waybill { // 运单

	private String waybillid; // 编码Id
	private String partyid; // 总包会员Id
	private String waybillnumber; // 运单号
	private String status; // 运单状态
	private String consigndate; // 托运日期
	private String frompartyid; // 发货方会员Id
	private String consignorlinkman; // 发货联系人
	private String consignortelephonenumber; // 发货人电话
	private String consignormobilenumber; // 发货人手机
	private String consignorprovince; // 发货省
	private String consignorcity; // 发货市
	private String consignorregion; // 发货区
	private String consignortown; // 发货详细地址
	private String consignee; // 收货方
	private String consigneelinkman; // 收货联系人
	private String consigneetelephonenumber; // 收货人电话
	private String consigneemobilenumber; // 收货人手机
	private String consigneeprovince; // 收货省
	private String consigneecity; // 收货市
	private String consigneeregion; // 收货区
	private String consigneetown; // 收货详细地址
	private String distance; // 距离
	private String settletype; // 结算方式
	private String receivetype; // 收货方式
	private String arrivetype; // 到货方式
	private String backbilltype; // 回单方式
	private String backbillnum; // 回单数量
	private String urgencydegree; // 紧急程度
	private String shiptype; // 运输方式
	private String topartyid; // 分包商会员Id
	private String inputman; // 输入日期
	private String inputdate; // 输入人
	private String sureman; // 确认日期
	private String suredate; // 确认人
	private String carbegindate; // 发车时间
	private String carenddate; // 到车时间
	private String billstatus; // 计费状态
//	private String settlebillid; // 结算单Id
	private String insettlebillid; // 结算单Id
	private String outsettlebillid; // 结算单Id
	private String waybillstowageid; // 配载单Id
	private String memo; // 备注
	private String clientnumber; // 客户单号
	private String operatorid;//操作员id
	private String sourcesystem;//来源

	/** 列表 扩展属性 **/
	private String frompartyname; // 发货方名称（客户）
	private String topartyname; // 分包商名称（承运商）
	private String receivablesum;// 应收金额
	private String payablesum;// 应付金额
	private String drivername;// 司机姓名
	private String drivermobile;
	private String carplatenumber;// 车牌号
	private String systemdispatchnumber;// 系统调度单号
	private String paperdispatchnumber;
	private String goodstype;// 货物类型
	private String goodsname;// 货物名称
	private String spec; //规格
	private String factnum;// 核定数量
	private String factweight;// 核定重量
	private String factvolume;// 核定体积
	private String packagename;//包装
	private String model;//型号
	private String num;//数量

	/**** 详情 扩展 ****/
	private List<Goods> goodslist;
	private List<WaybillAmount> waybillamountlist;
	private List<WaybillLog> waybillloglist;
	private WaybillSign waybillsign;
	
	
	private String receivablefreight;// 应收运费（自动计算值）
	private String payablefreight;// 应付运费（自动计算值）
	private String eachweightprice; // 重货单价(元/kg)
	private String eachvolumeprice; // 泡货单价(元/m3)
	private String eachtonkilometerprice; // 吨公里单价(元/吨)
	private String eachcarprice;				//整车单价(元/吨)
	private String eachcubekilometerprice;				//整车单价(元/吨)
	private String billtype; //阶梯报价 按重量计费 按体积计费
	private String reporttype; //结算设置 标准报价，阶梯报价
	private String settlesetid;//结算设置id
	
	/**查询  扩展属性**/
	private String fromdate; // 托运日期
	private String todate; // 托运日期
	private List<String> statuslist;
	private List<String> billstatuslist;
	
	/**省市地址**/
	private String fromaddress;//发货地
	private String toaddress;//收货地

	public String getWaybillid() {
		if (waybillid != null) {
			if (new SysUtil().isInt(waybillid) == false)
				waybillid = null;
		}
		return waybillid;
	}

	public void setWaybillid(String waybillid) {
		if (waybillid != null) {
			if (new SysUtil().isInt(waybillid) == false)
				waybillid = null;
		}
		this.waybillid = waybillid;
	}
	
	
	public String getOperatorid() {
		if(operatorid != null) {
			if(new SysUtil().isInt(operatorid) == false)
				operatorid = null;
		}
		return operatorid;
	}
	
	
	public void setOperatorid(String operatorid) {
		if (operatorid != null) {
			if (new SysUtil().isInt(operatorid) == false)
				operatorid = null;
		}
		this.operatorid = operatorid;
	}
	

	public String getSourcesystem() {
		if (sourcesystem != null) {
			if ("".equals(sourcesystem))
				sourcesystem = null;
		}
		return sourcesystem;
	}

	public void setSourcesystem(String sourcesystem) {
		if (sourcesystem != null) {
			if ("".equals(sourcesystem))
				sourcesystem = null;
		}
		this.sourcesystem = sourcesystem;
	}

	public String getPartyid() {
		if (partyid != null) {
			if (new SysUtil().isInt(partyid) == false)
				partyid = null;
		}
		return partyid;
	}

	public void setPartyid(String partyid) {
		if (partyid != null) {
			if (new SysUtil().isInt(partyid) == false)
				partyid = null;
		}
		this.partyid = partyid;
	}

	public String getWaybillnumber() {
		if (waybillnumber != null) {
			if ("".equals(waybillnumber))
				waybillnumber = null;
		}
		return waybillnumber;
	}

	public void setWaybillnumber(String waybillnumber) {
		if (waybillnumber != null) {
			if ("".equals(waybillnumber))
				waybillnumber = null;
		}
		this.waybillnumber = waybillnumber;
	}

	public String getStatus() {
		if (status != null) {
			if ("".equals(status))
				status = null;
		}
		return status;
	}

	public void setStatus(String status) {
		if (status != null) {
			if ("".equals(status))
				status = null;
		}
		this.status = status;
	}

	public String getConsigndate() {
		if (consigndate != null) {
			if ("".equals(consigndate))
				consigndate = null;
		}
		return consigndate;
	}

	public void setConsigndate(String consigndate) {
		if (consigndate != null) {
			if ("".equals(consigndate))
				consigndate = null;
		}
		this.consigndate = consigndate;
	}

	public String getFrompartyid() {
		if (frompartyid != null) {
			if (new SysUtil().isInt(frompartyid) == false)
				frompartyid = null;
		}
		return frompartyid;
	}

	public void setFrompartyid(String frompartyid) {
		if (frompartyid != null) {
			if (new SysUtil().isInt(frompartyid) == false)
				frompartyid = null;
		}
		this.frompartyid = frompartyid;
	}

	public String getConsignorlinkman() {
		if (consignorlinkman != null) {
			if ("".equals(consignorlinkman))
				consignorlinkman = null;
		}
		return consignorlinkman;
	}

	public void setConsignorlinkman(String consignorlinkman) {
		if (consignorlinkman != null) {
			if ("".equals(consignorlinkman))
				consignorlinkman = null;
		}
		this.consignorlinkman = consignorlinkman;
	}

	public String getConsignortelephonenumber() {
		if (consignortelephonenumber != null) {
			if ("".equals(consignortelephonenumber))
				consignortelephonenumber = null;
		}
		return consignortelephonenumber;
	}

	public void setConsignortelephonenumber(String consignortelephonenumber) {
		if (consignortelephonenumber != null) {
			if ("".equals(consignortelephonenumber))
				consignortelephonenumber = null;
		}
		this.consignortelephonenumber = consignortelephonenumber;
	}

	public String getConsignormobilenumber() {
		if (consignormobilenumber != null) {
			if ("".equals(consignormobilenumber))
				consignormobilenumber = null;
		}
		return consignormobilenumber;
	}

	public void setConsignormobilenumber(String consignormobilenumber) {
		if (consignormobilenumber != null) {
			if ("".equals(consignormobilenumber))
				consignormobilenumber = null;
		}
		this.consignormobilenumber = consignormobilenumber;
	}

	public String getConsignorprovince() {
		if (consignorprovince != null) {
			if ("".equals(consignorprovince))
				consignorprovince = null;
		}
		return consignorprovince;
	}

	public void setConsignorprovince(String consignorprovince) {
		if (consignorprovince != null) {
			if ("".equals(consignorprovince))
				consignorprovince = null;
		}
		this.consignorprovince = consignorprovince;
	}

	public String getConsignorcity() {
		if (consignorcity != null) {
			if ("".equals(consignorcity))
				consignorcity = null;
		}
		return consignorcity;
	}

	public void setConsignorcity(String consignorcity) {
		if (consignorcity != null) {
			if ("".equals(consignorcity))
				consignorcity = null;
		}
		this.consignorcity = consignorcity;
	}

	public String getConsignorregion() {
		if (consignorregion != null) {
			if ("".equals(consignorregion))
				consignorregion = null;
		}
		return consignorregion;
	}

	public void setConsignorregion(String consignorregion) {
		if (consignorregion != null) {
			if ("".equals(consignorregion))
				consignorregion = null;
		}
		this.consignorregion = consignorregion;
	}

	public String getConsignortown() {
		if (consignortown != null) {
			if ("".equals(consignortown))
				consignortown = null;
		}
		return consignortown;
	}

	public void setConsignortown(String consignortown) {
		if (consignortown != null) {
			if ("".equals(consignortown))
				consignortown = null;
		}
		this.consignortown = consignortown;
	}

	public String getConsignee() {
		if (consignee != null) {
			if ("".equals(consignee))
				consignee = null;
		}
		return consignee;
	}

	public void setConsignee(String consignee) {
		if (consignee != null) {
			if ("".equals(consignee))
				consignee = null;
		}
		this.consignee = consignee;
	}

	public String getConsigneelinkman() {
		if (consigneelinkman != null) {
			if ("".equals(consigneelinkman))
				consigneelinkman = null;
		}
		return consigneelinkman;
	}

	public void setConsigneelinkman(String consigneelinkman) {
		if (consigneelinkman != null) {
			if ("".equals(consigneelinkman))
				consigneelinkman = null;
		}
		this.consigneelinkman = consigneelinkman;
	}

	public String getConsigneetelephonenumber() {
		if (consigneetelephonenumber != null) {
			if ("".equals(consigneetelephonenumber))
				consigneetelephonenumber = null;
		}
		return consigneetelephonenumber;
	}

	public void setConsigneetelephonenumber(String consigneetelephonenumber) {
		if (consigneetelephonenumber != null) {
			if ("".equals(consigneetelephonenumber))
				consigneetelephonenumber = null;
		}
		this.consigneetelephonenumber = consigneetelephonenumber;
	}

	public String getConsigneemobilenumber() {
		if (consigneemobilenumber != null) {
			if ("".equals(consigneemobilenumber))
				consigneemobilenumber = null;
		}
		return consigneemobilenumber;
	}

	public void setConsigneemobilenumber(String consigneemobilenumber) {
		if (consigneemobilenumber != null) {
			if ("".equals(consigneemobilenumber))
				consigneemobilenumber = null;
		}
		this.consigneemobilenumber = consigneemobilenumber;
	}

	public String getConsigneeprovince() {
		if (consigneeprovince != null) {
			if ("".equals(consigneeprovince))
				consigneeprovince = null;
		}
		return consigneeprovince;
	}

	public void setConsigneeprovince(String consigneeprovince) {
		if (consigneeprovince != null) {
			if ("".equals(consigneeprovince))
				consigneeprovince = null;
		}
		this.consigneeprovince = consigneeprovince;
	}

	public String getConsigneecity() {
		if (consigneecity != null) {
			if ("".equals(consigneecity))
				consigneecity = null;
		}
		return consigneecity;
	}

	public void setConsigneecity(String consigneecity) {
		if (consigneecity != null) {
			if ("".equals(consigneecity))
				consigneecity = null;
		}
		this.consigneecity = consigneecity;
	}

	public String getConsigneeregion() {
		if (consigneeregion != null) {
			if ("".equals(consigneeregion))
				consigneeregion = null;
		}
		return consigneeregion;
	}

	public void setConsigneeregion(String consigneeregion) {
		if (consigneeregion != null) {
			if ("".equals(consigneeregion))
				consigneeregion = null;
		}
		this.consigneeregion = consigneeregion;
	}

	public String getConsigneetown() {
		if (consigneetown != null) {
			if ("".equals(consigneetown))
				consigneetown = null;
		}
		return consigneetown;
	}

	public void setConsigneetown(String consigneetown) {
		if (consigneetown != null) {
			if ("".equals(consigneetown))
				consigneetown = null;
		}
		this.consigneetown = consigneetown;
	}

	public String getDistance() {
		if (distance != null) {
			if (new SysUtil().isNum(distance) == false)
				distance = null;
		}
		return distance;
	}

	public void setDistance(String distance) {
		if (distance != null) {
			if (new SysUtil().isNum(distance) == false)
				distance = null;
		}
		this.distance = distance;
	}

	public String getSettletype() {
		if (settletype != null) {
			if ("".equals(settletype))
				settletype = null;
		}
		return settletype;
	}

	public void setSettletype(String settletype) {
		if (settletype != null) {
			if ("".equals(settletype))
				settletype = null;
		}
		this.settletype = settletype;
	}

	public String getReceivetype() {
		if (receivetype != null) {
			if ("".equals(receivetype))
				receivetype = null;
		}
		return receivetype;
	}

	public void setReceivetype(String receivetype) {
		if (receivetype != null) {
			if ("".equals(receivetype))
				receivetype = null;
		}
		this.receivetype = receivetype;
	}

	public String getArrivetype() {
		if (arrivetype != null) {
			if ("".equals(arrivetype))
				arrivetype = null;
		}
		return arrivetype;
	}

	public void setArrivetype(String arrivetype) {
		if (arrivetype != null) {
			if ("".equals(arrivetype))
				arrivetype = null;
		}
		this.arrivetype = arrivetype;
	}

	public String getBackbilltype() {
		if (backbilltype != null) {
			if ("".equals(backbilltype))
				backbilltype = null;
		}
		return backbilltype;
	}

	public void setBackbilltype(String backbilltype) {
		if (backbilltype != null) {
			if ("".equals(backbilltype))
				backbilltype = null;
		}
		this.backbilltype = backbilltype;
	}

	public String getBackbillnum() {
		if (backbillnum != null) {
			if ("".equals(backbillnum))
				backbillnum = null;
		}
		return backbillnum;
	}

	public void setBackbillnum(String backbillnum) {
		if (backbillnum != null) {
			if ("".equals(backbillnum))
				backbillnum = null;
		}
		this.backbillnum = backbillnum;
	}

	public String getUrgencydegree() {
		if (urgencydegree != null) {
			if ("".equals(urgencydegree))
				urgencydegree = null;
		}
		return urgencydegree;
	}

	public void setUrgencydegree(String urgencydegree) {
		if (urgencydegree != null) {
			if ("".equals(urgencydegree))
				urgencydegree = null;
		}
		this.urgencydegree = urgencydegree;
	}

	public String getShiptype() {
		if (shiptype != null) {
			if ("".equals(shiptype))
				shiptype = null;
		}
		return shiptype;
	}

	public void setShiptype(String shiptype) {
		if (shiptype != null) {
			if ("".equals(shiptype))
				shiptype = null;
		}
		this.shiptype = shiptype;
	}

	public String getTopartyid() {
		if (topartyid != null) {
			if (new SysUtil().isInt(topartyid) == false)
				topartyid = null;
		}
		return topartyid;
	}

	public void setTopartyid(String topartyid) {
		if (topartyid != null) {
			if (new SysUtil().isInt(topartyid) == false)
				topartyid = null;
		}
		this.topartyid = topartyid;
	}

	public String getInputman() {
		if (inputman != null) {
			if ("".equals(inputman))
				inputman = null;
		}
		return inputman;
	}

	public void setInputman(String inputman) {
		if (inputman != null) {
			if ("".equals(inputman))
				inputman = null;
		}
		this.inputman = inputman;
	}

	public String getInputdate() {
		if (inputdate != null) {
			if ("".equals(inputdate))
				inputdate = null;
		}
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		if (inputdate != null) {
			if ("".equals(inputdate))
				inputdate = null;
		}
		this.inputdate = inputdate;
	}

	public String getSureman() {
		if (sureman != null) {
			if ("".equals(sureman))
				sureman = null;
		}
		return sureman;
	}

	public void setSureman(String sureman) {
		if (sureman != null) {
			if ("".equals(sureman))
				sureman = null;
		}
		this.sureman = sureman;
	}

	public String getSuredate() {
		if (suredate != null) {
			if ("".equals(suredate))
				suredate = null;
		}
		return suredate;
	}

	public void setSuredate(String suredate) {
		if (suredate != null) {
			if ("".equals(suredate))
				suredate = null;
		}
		this.suredate = suredate;
	}

	public String getCarbegindate() {
		if (carbegindate != null) {
			if ("".equals(carbegindate))
				carbegindate = null;
		}
		return carbegindate;
	}

	public void setCarbegindate(String carbegindate) {
		if (carbegindate != null) {
			if ("".equals(carbegindate))
				carbegindate = null;
		}
		this.carbegindate = carbegindate;
	}

	public String getCarenddate() {
		if (carenddate != null) {
			if ("".equals(carenddate))
				carenddate = null;
		}
		return carenddate;
	}

	public void setCarenddate(String carenddate) {
		if (carenddate != null) {
			if ("".equals(carenddate))
				carenddate = null;
		}
		this.carenddate = carenddate;
	}

	public String getBillstatus() {
		if (billstatus != null) {
			if ("".equals(billstatus))
				billstatus = null;
		}
		return billstatus;
	}

	public void setBillstatus(String billstatus) {
		if (billstatus != null) {
			if ("".equals(billstatus))
				billstatus = null;
		}
		this.billstatus = billstatus;
	}

//	public String getSettlebillid() {
//		if (settlebillid != null) {
//			if (new SysUtil().isInt(settlebillid) == false)
//				settlebillid = null;
//		}
//		return settlebillid;
//	}
//
//	public void setSettlebillid(String settlebillid) {
//		if (settlebillid != null) {
//			if (new SysUtil().isInt(settlebillid) == false)
//				settlebillid = null;
//		}
//		this.settlebillid = settlebillid;
//	}
	public void setInsettlebillid(String insettlebillid) {
		if (insettlebillid != null) {
			if (new SysUtil().isInt(insettlebillid) == false)
				insettlebillid = null;
		}
		this.insettlebillid = insettlebillid;
	}
	public String getInsettlebillid() {
		if (insettlebillid != null) {
			if (new SysUtil().isInt(insettlebillid) == false)
				insettlebillid = null;
		}
		return insettlebillid;
	}

	public void setOutsettlebillid(String outsettlebillid) {
		if (outsettlebillid != null) {
			if (new SysUtil().isInt(outsettlebillid) == false)
				outsettlebillid = null;
		}
		this.outsettlebillid = outsettlebillid;
	}
	
	public String getOutsettlebillid() {
		if (outsettlebillid != null) {
			if (new SysUtil().isInt(outsettlebillid) == false)
				outsettlebillid = null;
		}
		return outsettlebillid;
	}

	public String getWaybillstowageid() {
		if (waybillstowageid != null) {
			if (new SysUtil().isInt(waybillstowageid) == false)
				waybillstowageid = null;
		}
		return waybillstowageid;
	}

	public void setWaybillstowageid(String waybillstowageid) {
		if (waybillstowageid != null) {
			if (new SysUtil().isInt(waybillstowageid) == false)
				waybillstowageid = null;
		}
		this.waybillstowageid = waybillstowageid;
	}

	public String getMemo() {
		if (memo != null) {
			if ("".equals(memo))
				memo = null;
		}
		return memo;
	}

	public void setMemo(String memo) {
		if (memo != null) {
			if ("".equals(memo))
				memo = null;
		}
		this.memo = memo;
	}

	public String getClientnumber() {
		if (clientnumber != null) {
			if ("".equals(clientnumber))
				clientnumber = null;
		}
		return clientnumber;
	}

	public void setClientnumber(String clientnumber) {
		if (clientnumber != null) {
			if ("".equals(clientnumber))
				clientnumber = null;
		}
		this.clientnumber = clientnumber;
	}
	public String getFrompartyname() {
		if (frompartyname != null) {
			if ("".equals(frompartyname))
				frompartyname = null;
		}
		return frompartyname;
	}

	public void setFrompartyname(String frompartyname) {
		if (frompartyname != null) {
			if ("".equals(frompartyname))
				frompartyname = null;
		}
		this.frompartyname = frompartyname;
	}

	public String getTopartyname() {
		if (topartyname != null) {
			if ("".equals(topartyname))
				topartyname = null;
		}
		return topartyname;
	}

	public void setTopartyname(String topartyname) {
		if (topartyname != null) {
			if ("".equals(topartyname))
				topartyname = null;
		}
		this.topartyname = topartyname;
	}

	public String getReceivablesum() {
		if (receivablesum != null) {
			if ("".equals(receivablesum))
				receivablesum = null;
		}
		return receivablesum;
	}

	public void setReceivablesum(String receivablesum) {
		if (receivablesum != null) {
			if ("".equals(receivablesum))
				receivablesum = null;
		}
		this.receivablesum = receivablesum;
	}

	public String getPayablesum() {
		if (payablesum != null) {
			if ("".equals(payablesum))
				payablesum = null;
		}
		return payablesum;
	}

	public void setPayablesum(String payablesum) {
		if (payablesum != null) {
			if ("".equals(payablesum))
				payablesum = null;
		}
		this.payablesum = payablesum;
	}

	public String getDrivername() {
		if (drivername != null) {
			if ("".equals(drivername))
				drivername = null;
		}
		return drivername;
	}

	public void setDrivername(String drivername) {
		if (drivername != null) {
			if ("".equals(drivername))
				drivername = null;
		}
		this.drivername = drivername;
	}
	public String getDrivermobile() {
		if (drivermobile != null) {
			if ("".equals(drivermobile))
				drivermobile = null;
		}
		return drivermobile;
	}
	public void setDrivermobile(String drivermobile) {
		if (drivermobile != null) {
			if ("".equals(drivermobile))
				drivermobile = null;
		}
		this.drivermobile = drivermobile;
	}
	public String getCarplatenumber() {
		if (carplatenumber != null) {
			if ("".equals(carplatenumber))
				carplatenumber = null;
		}
		return carplatenumber;
	}

	public void setCarplatenumber(String carplatenumber) {
		if (carplatenumber != null) {
			if ("".equals(carplatenumber))
				carplatenumber = null;
		}
		this.carplatenumber = carplatenumber;
	}

	public String getSystemdispatchnumber() {
		if (systemdispatchnumber != null) {
			if ("".equals(systemdispatchnumber))
				systemdispatchnumber = null;
		}
		return systemdispatchnumber;
	}

	public void setSystemdispatchnumber(String systemdispatchnumber) {
		if (carplatenumber != null) {
			if ("".equals(systemdispatchnumber))
				systemdispatchnumber = null;
		}
		this.systemdispatchnumber = systemdispatchnumber;
	}
	
	public String getPaperdispatchnumber() {
		if (paperdispatchnumber != null) {
			if ("".equals(paperdispatchnumber))
				paperdispatchnumber = null;
		}
		return paperdispatchnumber;
	}

	public void setPaperdispatchnumber(String paperdispatchnumber) {
		if (paperdispatchnumber != null) {
			if ("".equals(paperdispatchnumber))
				paperdispatchnumber = null;
		}
		this.paperdispatchnumber = paperdispatchnumber;
	}

	public String getGoodstype() {
		if (goodstype != null) {
			if ("".equals(goodstype))
				goodstype = null;
		}
		return goodstype;
	}

	public void setGoodstype(String goodstype) {
		if (goodstype != null) {
			if ("".equals(goodstype))
				goodstype = null;
		}
		this.goodstype = goodstype;
	}

	public String getGoodsname() {
		if (goodsname != null) {
			if ("".equals(goodsname))
				goodsname = null;
		}
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		if (goodsname != null) {
			if ("".equals(goodsname))
				goodsname = null;
		}
		this.goodsname = goodsname;
	}

	public String getFactnum() {
		if (factnum != null) {
			if (new SysUtil().isNum(factnum) == false)
				factnum = null;
		}
		return factnum;
	}

	public void setFactnum(String factnum) {
		if (factnum != null) {
			if (new SysUtil().isNum(factnum) == false)
				factnum = null;
		}
		this.factnum = factnum;
	}

	public String getFactweight() {
		if (factweight != null) {
			if (new SysUtil().isNum(factweight) == false)
				factweight = null;
		}
		return factweight;
	}

	public void setFactweight(String factweight) {
		if (factweight != null) {
			if (new SysUtil().isNum(factweight) == false)
				factweight = null;
		}
		this.factweight = factweight;
	}

	public String getFactvolume() {
		if (factvolume != null) {
			if (new SysUtil().isNum(factvolume) == false)
				factvolume = null;
		}
		return factvolume;
	}

	public void setFactvolume(String factvolume) {
		if (factvolume != null) {
			if (new SysUtil().isNum(factvolume) == false)
				factvolume = null;
		}
		this.factvolume = factvolume;
	}

	public java.util.List<Goods> getGoodslist() {
		if (goodslist == null) {
			goodslist = new ArrayList<Goods>();
		}
		return goodslist;
	}

	public void setGoodslist(java.util.List<Goods> goodslist) {
		this.goodslist = goodslist;
	}

	public java.util.List<WaybillAmount> getWaybillamountlist() {
		if (waybillamountlist == null) {
			waybillamountlist = new ArrayList<WaybillAmount>();
		}
		return waybillamountlist;
	}

	public void setWaybillamountlist(
			java.util.List<WaybillAmount> waybillamountlist) {
		this.waybillamountlist = waybillamountlist;
	}

	public java.util.List<WaybillLog> getWaybillloglist() {
		if (waybillloglist == null) {
			waybillloglist = new ArrayList<WaybillLog>();
		}
		return waybillloglist;
	}

	public void setWaybillloglist(java.util.List<WaybillLog> waybillloglist) {
		this.waybillloglist = waybillloglist;
	}

	public WaybillSign getWaybillsign() {
		if (waybillsign == null) {
			waybillsign = new WaybillSign();
		}
		return waybillsign;
	}

	public void setWaybillsign(WaybillSign waybillsign) {
		this.waybillsign = waybillsign;
	}

	public String getReceivablefreight() {
		if (receivablefreight != null) {
			if ("".equals(receivablefreight))
				receivablefreight = null;
		}
		return receivablefreight;
	}

	public void setReceivablefreight(String receivablefreight) {
		if (receivablefreight != null) {
			if ("".equals(receivablefreight))
				receivablefreight = null;
		}
		this.receivablefreight = receivablefreight;
	}

	public String getPayablefreight() {
		if (payablefreight != null) {
			if ("".equals(payablefreight))
				payablefreight = null;
		}
		return payablefreight;
	}

	public void setPayablefreight(String payablefreight) {
		if (payablefreight != null) {
			if ("".equals(payablefreight))
				payablefreight = null;
		}
		this.payablefreight = payablefreight;
	}

	public String getEachweightprice() {
		if (eachweightprice != null) {
			if (new SysUtil().isNum(eachweightprice)== false)
				eachweightprice = null;
		}
		return eachweightprice;
	}

	public void setEachweightprice(String eachweightprice) {
		if (eachweightprice != null) {
			if (new SysUtil().isNum(eachweightprice)== false)
				eachweightprice = null;
		}
		this.eachweightprice = eachweightprice;
	}

	public String getEachvolumeprice() {
		if (eachvolumeprice != null) {
			if (new SysUtil().isNum(eachvolumeprice)== false)
				eachvolumeprice = null;
		}
		return eachvolumeprice;
	}

	public void setEachvolumeprice(String eachvolumeprice) {
		if (eachvolumeprice != null) {
			if (new SysUtil().isNum(eachvolumeprice)== false)
				eachvolumeprice = null;
		}
		this.eachvolumeprice = eachvolumeprice;
	}

	public String getEachtonkilometerprice() {
		if (eachtonkilometerprice != null) {
			if (new SysUtil().isNum(eachtonkilometerprice) == false)
				eachtonkilometerprice = null;
		}
		return eachtonkilometerprice;
	}

	public void setEachtonkilometerprice(String eachtonkilometerprice) {
		if (eachtonkilometerprice != null) {
			if (new SysUtil().isNum(eachtonkilometerprice) == false)
				eachtonkilometerprice = null;
		}
		this.eachtonkilometerprice = eachtonkilometerprice;
	}
	public String getEachcarprice() {
		if (eachcarprice != null) {
			if (new SysUtil().isNum(eachcarprice) == false)
				eachcarprice = null;
		}
		return eachcarprice;
	}
	
	public void setEachcarprice(String eachcarprice) {
		if (eachcarprice != null) {
			if (new SysUtil().isNum(eachcarprice) == false)
				eachcarprice = null;
		}
		this.eachcarprice = eachcarprice;
	}
	
	public String getEachcubekilometerprice() {
		if (eachcubekilometerprice != null) {
			if (new SysUtil().isNum(eachcubekilometerprice) == false)
				eachcubekilometerprice = null;
		}
		return eachcubekilometerprice;
	}

	public void setEachcubekilometerprice(String eachcubekilometerprice) {
		if (eachcubekilometerprice != null) {
			if (new SysUtil().isNum(eachcubekilometerprice) == false)
				eachcubekilometerprice = null;
		}
		this.eachcubekilometerprice = eachcubekilometerprice;
	}

	public String getSpec() {
		if (spec != null) {
			if ("".equals(spec))
				spec = null;
		}
		return spec;
	}

	public void setSpec(String spec) {
		if (spec != null) {
			if ("".equals(spec))
				spec = null;
		}
		this.spec = spec;
	}

	public String getFromdate() {
		if (fromdate != null) {
			if ("".equals(fromdate))
				fromdate = null;
		}
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		if (fromdate != null) {
			if ("".equals(fromdate))
				fromdate = null;
		}
		this.fromdate = fromdate;
	}

	public String getTodate() {
		if (todate != null) {
			if ("".equals(todate))
				todate = null;
		}
		return todate;
	}

	public void setTodate(String todate) {
		if (todate != null) {
			if ("".equals(todate))
				todate = null;
		}
		this.todate = todate;
	}

	public List<String> getStatuslist() {
		if (statuslist == null) {
			statuslist = new ArrayList<String>();
		}
		return statuslist;
	}

	public void setStatuslist(List<String> statuslist) {
		this.statuslist = statuslist;
	}

	public List<String> getBillstatuslist() {
		if (billstatuslist == null) {
			billstatuslist = new ArrayList<String>();
		}
		return billstatuslist;
	}

	public void setBillstatuslist(List<String> billstatuslist) {
		this.billstatuslist = billstatuslist;
	}
	
	public String getPackagename() {
		if (packagename != null) {
			if ("".equals(packagename))
				packagename = null;
		}
		return packagename;
	}

	public void setPackagename(String packagename) {
		if (packagename != null) {
			if ("".equals(packagename))
				packagename = null;
		}
		this.packagename = packagename;
	}

	

	public String getModel() {
		if (model != null) {
			if ("".equals(model))
				model = null;
		}
		return model;
	}

	public void setModel(String model) {
		if (model != null) {
			if ("".equals(model))
				model = null;
		}
		this.model = model;
	}

	public String getNum() {
		if (num != null) {
			if (new SysUtil().isNum(num) == false)
				num = null;
		}
		return num;
	}

	public void setNum(String num) {
		if (num != null) {
			if (new SysUtil().isNum(num) == false)
				num = null;
		}
		this.num = num;
	}


	public String getFromaddress() {
		if (fromaddress != null) {
			if ("".equals(fromaddress))
				fromaddress = null;
		}
		return fromaddress;
	}

	public void setFromaddress(String fromaddress) {
		if (fromaddress != null) {
			if ("".equals(fromaddress))
				fromaddress = null;
		}
		this.fromaddress = fromaddress;
	}

	public String getToaddress() {
		if (toaddress != null) {
			if ("".equals(toaddress))
				toaddress = null;
		}
		return toaddress;
	}

	public void setToaddress(String toaddress) {
		if (toaddress != null) {
			if ("".equals(toaddress))
				toaddress = null;
		}
		this.toaddress = toaddress;
	}

	public String getBilltype() {
		if (billtype != null) {
			if ("".equals(billtype))
				billtype = null;
		}
		return billtype;
	}

	public void setBilltype(String billtype) {
		if (billtype != null) {
			if ("".equals(billtype))
				billtype = null;
		}
		this.billtype = billtype;
	}

	public String getReporttype() {
		if (reporttype != null) {
			if ("".equals(reporttype))
				reporttype = null;
		}
		return reporttype;
	}

	public void setReporttype(String reporttype) {
		if (reporttype != null) {
			if ("".equals(reporttype))
				reporttype = null;
		}
		this.reporttype = reporttype;
	}

	public String getSettlesetid() {
		if (settlesetid != null) {
			if ("".equals(settlesetid))
				settlesetid = null;
		}
		return settlesetid;
	}

	public void setSettlesetid(String settlesetid) {
		if (settlesetid != null) {
			if ("".equals(settlesetid))
				settlesetid = null;
		}
		this.settlesetid = settlesetid;
	}
	

}