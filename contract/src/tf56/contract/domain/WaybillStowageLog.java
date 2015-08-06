package tf56.contract.domain;

import tf56.sofa.util.SysUtil;

public class WaybillStowageLog {
	private String waybillstowagelogid;
	private String waybillstowageid;
	private String systemdispatchnumber;
	private String status;
	private String trace;
	private String inputdate;
	private String inputman;
	
	public String getWaybillstowagelogid() {
		if (waybillstowagelogid != null) {
			if (new SysUtil().isInt(waybillstowagelogid) == false)
				waybillstowagelogid = null;
		}
		return waybillstowagelogid;
	}
	public void setWaybillstowagelogid(String waybillstowagelogid) {
		if (waybillstowagelogid != null) {
			if (new SysUtil().isInt(waybillstowagelogid) == false)
				waybillstowagelogid = null;
		}
		this.waybillstowagelogid = waybillstowagelogid;
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
	public String getSystemdispatchnumber() {
		if (systemdispatchnumber != null) {
			if ("".equals(systemdispatchnumber))
				systemdispatchnumber = null;
		}
		return systemdispatchnumber;
	}
	public void setSystemdispatchnumber(String systemdispatchnumber) {
		if (systemdispatchnumber != null) {
			if ("".equals(systemdispatchnumber))
				systemdispatchnumber = null;
		}
		this.systemdispatchnumber = systemdispatchnumber;
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
