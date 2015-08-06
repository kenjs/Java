package tf56.contract.domain;

import tf56.sofa.util.SysUtil;

public class WaybillLog { // 运单操作日志
	private String waybilllogid; // 编码Id
	private String waybillid; // 运单Id
	private String waybillnumber; // 运单编号
	private String status; // 状态
	private String trace; // 跟踪详情
	private String inputdate; // 输入日期
	private String inputman; // 输入人

	public String getWaybilllogid() {
		if (waybilllogid != null) {
			if (new SysUtil().isInt(waybilllogid) == false)
				waybilllogid = null;
		}
		return waybilllogid;
	}

	public void setWaybilllogid(String waybilllogid) {
		if (waybilllogid != null) {
			if (new SysUtil().isInt(waybilllogid) == false)
				waybilllogid = null;
		}
		this.waybilllogid = waybilllogid;
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

	public String getTrace() {
		if (trace != null) {
			if ("".equals(trace))
				trace = null;
		}
		return trace;
	}

	public void setTrace(String trace) {
		if (trace != null) {
			if ("".equals(trace))
				trace = null;
		}
		this.trace = trace;
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
}