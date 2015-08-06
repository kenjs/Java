package tf56.contract.domain;

import tf56.sofa.util.SysUtil;

public class WaybillAmount { // 运单费用
	private String waybillamountid; // 编码Id
	private String waybillid; // 运单Id
	private String inorout; // 应收应付
	private String frompartyid; // 总包会员Id
	private String topartyid; // 发货方或分包商会员Id
	private String amountitem; // 费用项目
	private String amount; //

	public String getWaybillamountid() {
		if (waybillamountid != null) {
			if (new SysUtil().isInt(waybillamountid) == false)
				waybillamountid = null;
		}
		return waybillamountid;
	}

	public void setWaybillamountid(String waybillamountid) {
		if (waybillamountid != null) {
			if (new SysUtil().isInt(waybillamountid) == false)
				waybillamountid = null;
		}
		this.waybillamountid = waybillamountid;
	}

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

	public String getInorout() {
		if (inorout != null) {
			if ("".equals(inorout))
				inorout = null;
		}
		return inorout;
	}

	public void setInorout(String inorout) {
		if (inorout != null) {
			if ("".equals(inorout))
				inorout = null;
		}
		this.inorout = inorout;
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

	public String getAmountitem() {
		if (amountitem != null) {
			if ("".equals(amountitem))
				amountitem = null;
		}
		return amountitem;
	}

	public void setAmountitem(String amountitem) {
		if (amountitem != null) {
			if ("".equals(amountitem))
				amountitem = null;
		}
		this.amountitem = amountitem;
	}

	public String getAmount() {
		if (amount != null) {
			if (new SysUtil().isNum(amount) == false)
				amount = null;
		}
		return amount;
	}

	public void setAmount(String amount) {
		if (amount != null) {
			if (new SysUtil().isNum(amount) == false)
				amount = null;
		}
		this.amount = amount;
	}
}