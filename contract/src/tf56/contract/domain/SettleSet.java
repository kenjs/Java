package tf56.contract.domain;

import java.util.ArrayList;
import java.util.List;

import tf56.sofa.util.SysUtil;

/**
 * 类SettleSet.java的实现描述：结算
 * 
 * @author yaoyan.lin 2013年11月19日 上午9:44:01
 */
public class SettleSet {

    private String settlesetid;					//编码Id
    private String frompartyid;					//总包会员Id
    private String topartyid;					//发货方Id
    private String topartyidfbsid;				//分包会员Id
    private String topartyname;					//发货方名称
    private String topartyfbsname;				//分包商名称
    private String type;						//结算类型 应收结算|应付结算
    private String fromaddress;					//始发地
    private String toaddress;					//目的地
    private String eachweightprice;				//重货单价(元/kg)
    private String eachvolumeprice;				//泡货单价(元/m3)
    private String eachtonkilometerprice;		//吨公里单价(元/吨·公里)
    private String eachcubekilometerprice;		//方公里单价(元/立方·公里)
    private String eachcarprice;				//整车单价(元/车)
    private String billtype;					//计费方式
    private String reporttype;					//报价方式--标准报价，阶梯报价
    private String inputdate;					//输入日期
    private String updatedate;					//更新日期
    
    //阶梯报价详情展示信息
    private String startvalue; //
    private String endvalue; //
    private String step;
    private String unitprice; //报价
    private String settlestepsetid;
    private List<SettleStepSet> settlestepsetlist;
    
    public String getStartvalue() {
    	if (startvalue != null) {
            if ("".equals(startvalue)) startvalue = null;
        }
		return startvalue;
	}

	public void setStartvalue(String startvalue) {
		if (startvalue != null) {
            if ("".equals(startvalue)) startvalue = null;
        }
		this.startvalue = startvalue;
	}

	public String getEndvalue() {
		if (endvalue != null) {
            if ("".equals(endvalue)) endvalue = null;
        }
		return endvalue;
	}

	public void setEndvalue(String endvalue) {
		if (endvalue != null) {
            if ("".equals(endvalue)) endvalue = null;
        }
		this.endvalue = endvalue;
	}
	
	public String getStep() {
		if (step != null) {
            if ("".equals(step)) step = null;
        }
		return step;
	}

	public void setStep(String step) {
		if (step != null) {
            if ("".equals(step)) step = null;
        }
		this.step = step;
	}

	public String getUnitprice() {
		if (unitprice != null) {
            if ("".equals(unitprice)) unitprice = null;
        }
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		if (unitprice != null) {
            if ("".equals(unitprice)) unitprice = null;
        }
		this.unitprice = unitprice;
	}
	
	public String getSettlestepsetid() {
		if (settlestepsetid != null) {
            if ("".equals(settlestepsetid)) settlestepsetid = null;
        }
		return unitprice;
	}

	public void setSettlestepsetid(String settlestepsetid) {
		if (settlestepsetid != null) {
            if ("".equals(settlestepsetid)) settlestepsetid = null;
        }
		this.settlestepsetid = settlestepsetid;
	}

	public String getSettlesetid() {
        if (settlesetid != null) {
            if (new SysUtil().isInt(settlesetid) == false) settlesetid = null;
        }
        return settlesetid;
    }

    public void setSettlesetid(String settleSetId) {
        if (settleSetId != null) {
            if (new SysUtil().isInt(settleSetId) == false) settleSetId = null;
        }
        this.settlesetid = settleSetId;
    }

	public String getFrompartyid() {
		if (frompartyid != null) {
            if (new SysUtil().isInt(frompartyid) == false) frompartyid = null;
        }
		return frompartyid;
	}

	public void setFrompartyid(String frompartyId) {
		if (frompartyId != null) {
            if (new SysUtil().isInt(frompartyId) == false) frompartyId = null;
        }
		this.frompartyid = frompartyId;
	}

	public String getTopartyid() {
		if (topartyid != null) {
            if (new SysUtil().isInt(topartyid) == false) topartyid = null;
        }
		return topartyid;
	}

	public String getTopartyidfbsid() {
		if (topartyidfbsid != null) {
            if (new SysUtil().isInt(topartyidfbsid) == false) topartyidfbsid = null;
        }
		return topartyidfbsid;
	}

	public void setTopartyidfbsid(String topartyidFbsid) {
		if (topartyidfbsid != null) {
            if (new SysUtil().isInt(topartyidfbsid) == false) topartyidfbsid = null;
        }
		this.topartyidfbsid = topartyidFbsid;
	}

	public void setTopartyid(String topartyid) {
		if (topartyid != null) {
            if (new SysUtil().isInt(topartyid) == false) topartyid = null;
        }
		this.topartyid = topartyid;
	}

	public String getType() {
		if (type != null) {
            if ("".equals(type)) type = null;
        }
		return type;
	}

	public void setType(String type) {
		if (type != null) {
            if ("".equals(type)) type = null;
        }
		this.type = type;
	}

	public String getFromaddress() {
		if (fromaddress != null) {
            if ("".equals(fromaddress)) fromaddress = null;
        }
		return fromaddress;
	}

	public void setFromaddress(String fromaddress) {
		if (fromaddress != null) {
            if ("".equals(fromaddress)) fromaddress = null;
        }
		this.fromaddress = fromaddress;
	}

	public String getToaddress() {
		if (toaddress != null) {
            if ("".equals(toaddress)) toaddress = null;
        }
		return toaddress;
	}

	public void setToaddress(String toaddress) {
		if (toaddress != null) {
            if ("".equals(toaddress)) toaddress = null;
        }
		this.toaddress = toaddress;
	}

	public String getEachweightprice() {
		if (eachweightprice != null) {
            if ("".equals(eachweightprice)) eachweightprice = null;
        }
		return eachweightprice;
	}

	public void setEachweightprice(String eachWeightPrice) {
		if (eachWeightPrice != null) {
            if ("".equals(eachWeightPrice)) eachWeightPrice = null;
        }
		this.eachweightprice = eachWeightPrice;
	}

	public String getEachvolumeprice() {
		if (eachvolumeprice != null) {
            if ("".equals(eachvolumeprice)) eachvolumeprice = null;
        }
		return eachvolumeprice;
	}

	public void setEachvolumeprice(String eachVolumePrice) {
		if (eachVolumePrice != null) {
            if ("".equals(eachVolumePrice)) eachVolumePrice = null;
        }
		this.eachvolumeprice = eachVolumePrice;
	}

	public String getEachtonkilometerprice() {
		if (eachtonkilometerprice != null) {
            if ("".equals(eachtonkilometerprice)) eachtonkilometerprice = null;
        }
		return eachtonkilometerprice;
	}

	public void setEachtonkilometerprice(String eachTonKilometerPrice) {
		if (eachTonKilometerPrice != null) {
            if ("".equals(eachTonKilometerPrice)) eachTonKilometerPrice = null;
        }
		this.eachtonkilometerprice = eachTonKilometerPrice;
	}
	
	public String getEachcubekilometerprice() {
		if (eachcubekilometerprice != null) {
			if ("".equals(eachcubekilometerprice)) eachcubekilometerprice = null;
		}
		return eachcubekilometerprice;
	}
	
	public void setEachcubekilometerprice(String eachcubekilometerprice) {
		if (eachcubekilometerprice != null) {
			if ("".equals(eachcubekilometerprice)) eachcubekilometerprice = null;
		}
		this.eachcubekilometerprice = eachcubekilometerprice;
	}

	public String getEachcarprice() {
		if (eachcarprice != null) {
			if ("".equals(eachcarprice)) eachcarprice = null;
		}
		return eachcarprice;
	}
	
	public void setEachcarprice(String eachcarprice) {
		if (eachcarprice != null) {
			if ("".equals(eachcarprice)) eachcarprice = null;
		}
		this.eachcarprice = eachcarprice;
	}
	
	public String getReporttype() {
		if (reporttype != null) {
			if ("".equals(reporttype)) reporttype = null;
		}
		return reporttype;
	}
	
	public void setReporttype(String reporttype) {
		if (reporttype != null) {
			if ("".equals(reporttype)) reporttype = null;
		}
		this.reporttype = reporttype;
	}

	public String getBilltype() {
		if (billtype != null) {
			if ("".equals(billtype)) billtype = null;
		}
		return billtype;
	}
	
	public void setBilltype(String billtype) {
		if (billtype != null) {
			if ("".equals(billtype)) billtype = null;
		}
		this.billtype = billtype;
	}

	public String getInputdate() {
        if (inputdate != null) {
            if ("".equals(inputdate)) inputdate = null;
        }
		return inputdate;
	}

	public void setInputdate(String inputDate) {
        if (inputDate != null) {
            if ("".equals(inputDate)) inputDate = null;
        }
		this.inputdate = inputDate;
	}

	public String getUpdatedate() {
        if (updatedate != null) {
            if ("".equals(updatedate)) updatedate = null;
        }
		return updatedate;
	}

	public void setUpdatedate(String updateDate) {
        if (updateDate != null) {
            if ("".equals(updateDate)) updateDate = null;
        }
		this.updatedate = updateDate;
	}

	public String getTopartyname() {
        if (topartyname != null) {
            if ("".equals(topartyname)) topartyname = null;
        }
		return topartyname;
	}

	public void setTopartyname(String topartyname) {
        if (topartyname != null) {
            if ("".equals(topartyname)) topartyname = null;
        }
		this.topartyname = topartyname;
	}

	public String getTopartyfbsname() {
        if (topartyfbsname != null) {
            if ("".equals(topartyfbsname)) topartyfbsname = null;
        }
		return topartyfbsname;
	}

	public void setTopartyfbsname(String topartyfbsname) {
        if (topartyfbsname != null) {
            if ("".equals(topartyfbsname)) topartyfbsname = null;
        }
		this.topartyfbsname = topartyfbsname;
	}
	
	public java.util.List<SettleStepSet> getSettlestepsetlist() {
		if (settlestepsetlist == null) {
			settlestepsetlist = new ArrayList<SettleStepSet>();
		}
		return settlestepsetlist;
	}

	public void setSettlestepsetlist(java.util.List<SettleStepSet> settlestepsetlist) {
		this.settlestepsetlist = settlestepsetlist;
	}

}
