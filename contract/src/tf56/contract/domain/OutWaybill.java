package tf56.contract.domain;

import java.util.ArrayList;
import java.util.List;

import tf56.sofa.util.SysUtil;

public class OutWaybill { // 运单

	private String waybillid; // 编码Id
	private String sourcesystem;//来源
	private String partyid; // 总包会员Id
	private String clientnumber; // 客户单号
	private String status; // 运单状态
	private String consigndate; // 托运日期
	private String frompartyid; // 发货方会员Id
	private String consignor;
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
	private String memo; // 备注
	private String contractWaybillId;//总包运单号
	
	private String operatorid;//操作员id
	private String operator;//操作员
	private String operatetime;//操作时间
	private String waybillnumber; // 运单号
	private String frompartyname;
	private List<OutGoods> outGoodsList;
	
	public String getFrompartyname() {
		if(frompartyname != null) {
			if("".equals(frompartyname)) {
				frompartyname = null;
			}
		}
		return frompartyname;
	}

	public void setFrompartyname(String frompartyname) {
		if(frompartyname != null) {
			if("".equals(frompartyname)) {
				frompartyname = null;
			}
		}
		this.frompartyname = frompartyname;
	}

	public List<OutGoods> getOutGoodsList() {
		if (outGoodsList == null) {
			outGoodsList = new ArrayList<OutGoods>();
		}
		return outGoodsList;
	}

	public void setOutGoodsList(List<OutGoods> outGoodsList) {
		this.outGoodsList = outGoodsList;
	}

	public String getWaybillid() {
		if (waybillid != null) {
			if (new SysUtil().isInt(waybillid) == false)
				waybillid = null;
		}
		return waybillid;
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

	public String getConsignor() {
		if (consignor != null) {
			if ("".equals(consignor))
				consignor = null;
		}
		return consignor;
	}

	public void setConsignor(String consignor) {
		if (consignor != null) {
			if ("".equals(consignor))
				consignor = null;
		}
		this.consignor = consignor;
	}

	public String getContractWaybillId() {
		if (contractWaybillId != null) {
			if ("".equals(contractWaybillId))
				contractWaybillId = null;
		}
		return contractWaybillId;
	}

	public void setContractWaybillId(String contractWaybillId) {
		if (contractWaybillId != null) {
			if ("".equals(contractWaybillId))
				contractWaybillId = null;
		}
		this.contractWaybillId = contractWaybillId;
	}

	public String getOperator() {
		if (operator != null) {
			if ("".equals(operator))
				operator = null;
		}
		return operator;
	}

	public void setOperator(String operator) {
		if (operator != null) {
			if ("".equals(operator))
				operator = null;
		}
		this.operator = operator;
	}

	public String getOperatetime() {
		if (operatetime != null) {
			if ("".equals(operatetime))
				operatetime = null;
		}
		return operatetime;
	}

	public void setOperatetime(String operatetime) {
		if (operatetime != null) {
			if ("".equals(operatetime))
				operatetime = null;
		}
		this.operatetime = operatetime;
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
	
}