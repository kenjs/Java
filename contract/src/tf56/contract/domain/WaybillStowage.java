package tf56.contract.domain;

import java.util.ArrayList;
import java.util.List;

import tf56.sofa.util.SysUtil;

public class WaybillStowage { //
	private String waybillstowageid; // 配载单Id
	private String drivername; //
	private String carplatenumber; //
	private String systemdispatchnumber; //
	private String paperdispatchnumber; //
	private String cartype; //
	private String drivermobile; //
	private String dispatchdate; //
	private String inputman; // 输入人
	private String inputdate; // 新增日期或最近修改日期
	private String status; // 调度单状态
	private String waybillid;
	private String carloaddate;
	
	private String fromdate; // 托运日期
	private String todate; // 托运日期
	private String waybillnum;
	private String carbegindate; // 发车时间
	private String carenddate; // 到车时间
	private String factnum;// 核定数量
	private String factweight;// 核定重量
	private String factvolume;// 核定体积
	private String frompartyid; // 发货方会员Id
	private String topartyid; // 分包商会员Id
	private String frompartyname; // 发货方名称（客户）
	private String topartyname; // 分包商名称（承运商）
	
	private List<Waybill> waybillList;
	private List<WaybillStowageLog> waybillStowageLogList;
	
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
	
	public List<Waybill> getWaybillList() {
		if (waybillList == null) {
			waybillList = new ArrayList<Waybill>();
		}
		return waybillList;
	}

	public void setWaybillList(List<Waybill> waybillList) {
		if (waybillList == null) {
			waybillList = new ArrayList<Waybill>();
		}
		this.waybillList = waybillList;
	}

	public List<WaybillStowageLog> getWaybillStowageLogList() {
		if (waybillStowageLogList == null) {
			waybillStowageLogList = new ArrayList<WaybillStowageLog>();
		}
		return waybillStowageLogList;
	}

	public void setWaybillStowageLogList(
			List<WaybillStowageLog> waybillStowageLogList) {
		if (waybillStowageLogList == null) {
			waybillStowageLogList = new ArrayList<WaybillStowageLog>();
		}
		this.waybillStowageLogList = waybillStowageLogList;
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
	
	public String getCarloaddate() {
		if (carloaddate != null) {
			if ("".equals(carloaddate) == false)
				carloaddate = null;
		}
		return carloaddate;
	}

	public void setCarloaddate(String carloaddate) {
		if (carloaddate != null) {
			if ("".equals(carloaddate) == false)
				carloaddate = null;
		}
		this.carloaddate = carloaddate;
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

	public String getWaybillnum() {
		if (waybillnum != null) {
			if ("".equals(waybillnum))
				waybillnum = null;
		}
		return waybillnum;
	}

	public void setWaybillnum(String waybillnum) {
		if (waybillnum != null) {
			if ("".equals(waybillnum))
				waybillnum = null;
		}
		this.waybillnum = waybillnum;
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
	
	public String getWaybillid() {
		if (waybillid != null) {
			if ("".equals(waybillid))
				waybillid = null;
		}
		return waybillid;
	}

	public void setWaybillid(String waybillid) {
		if (waybillid != null) {
			if ("".equals(waybillid))
				waybillid = null;
		}
		this.waybillid = waybillid;
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
		if (systemdispatchnumber != null) {
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

	public String getCartype() {
		if (cartype != null) {
			if ("".equals(cartype))
				cartype = null;
		}
		return cartype;
	}

	public void setCartype(String cartype) {
		if (cartype != null) {
			if ("".equals(cartype))
				cartype = null;
		}
		this.cartype = cartype;
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

	public String getDispatchdate() {
		if (dispatchdate != null) {
			if ("".equals(dispatchdate))
				dispatchdate = null;
		}
		return dispatchdate;
	}

	public void setDispatchdate(String dispatchdate) {
		if (dispatchdate != null) {
			if ("".equals(dispatchdate))
				dispatchdate = null;
		}
		this.dispatchdate = dispatchdate;
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
}