package tf56.contract.domain;

import tf56.sofa.util.SysUtil;

public class WaybillSign { // 运单签收
	private String waybillsignid; // 编码Id
	private String waybillid; // 运单Id
	private String waybillnumber; // 运单编号
	private String signstatus; // 签收状态
	private String signman; // 签收人
	private String signnum; // 签收数量
	private String backbilltype; // 回单类型
	private String inputdate; // 输入日期
	private String inputman; // 输入人
	private String memo; // 备注

	public String getWaybillsignid() {
		if (waybillsignid != null) {
			if (new SysUtil().isInt(waybillsignid) == false)
				waybillsignid = null;
		}
		return waybillsignid;
	}

	public void setWaybillsignid(String waybillsignid) {
		if (waybillsignid != null) {
			if (new SysUtil().isInt(waybillsignid) == false)
				waybillsignid = null;
		}
		this.waybillsignid = waybillsignid;
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

	public String getSignstatus() {
		if (signstatus != null) {
			if ("".equals(signstatus))
				signstatus = null;
		}
		return signstatus;
	}

	public void setSignstatus(String signstatus) {
		if (signstatus != null) {
			if ("".equals(signstatus))
				signstatus = null;
		}
		this.signstatus = signstatus;
	}

	public String getSignman() {
		if (signman != null) {
			if ("".equals(signman))
				signman = null;
		}
		return signman;
	}

	public void setSignman(String signman) {
		if (signman != null) {
			if ("".equals(signman))
				signman = null;
		}
		this.signman = signman;
	}

	public String getSignnum() {
		if (signnum != null) {
			if (new SysUtil().isInt(signnum) == false)
				signnum = null;
		}
		return signnum;
	}

	public void setSignnum(String signnum) {
		if (signnum != null) {
			if (new SysUtil().isInt(signnum) == false)
				signnum = null;
		}
		this.signnum = signnum;
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
}