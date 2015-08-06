package tf56.contract.domain;

import tf56.sofa.util.SysUtil;

public class Goods { // 运单子表
	private String goodsid; // 编码Id
	private String waybillid; // 运单Id
	private String goodstype; // 货物类型
	private String goodsname; // 货物名称
	private String spec; // 规格
	private String model; // 模式
	private String num; // 数量
	private String factnum; // 核定数量
	private String packagename; // 包装
	private String weight; // 重量
	private String factweight; // 核定重量
	private String volume; // 体积
	private String factvolume; // 核定体积
	private String claimvalue; // 声明价值
	private String measuretype; // 计费方式
	private String unitweight;//单位重量
	private String unitvolume;//单位体积

	
	public String getUnitvolume() {
		if (unitvolume != null) {
			if (new SysUtil().isNum(unitvolume) == false)
				unitvolume = null;
		}
		return unitvolume;
	}

	public void setUnitvolume(String unitvolume) {
		if (unitvolume != null) {
			if (new SysUtil().isNum(unitvolume) == false)
				unitvolume = null;
		}
		this.unitvolume = unitvolume;
	}
	
	public String getUnitweight() {
		if (unitweight != null) {
			if (new SysUtil().isNum(unitweight) == false)
				unitweight = null;
		}
		return unitweight;
	}

	public void setUnitweight(String unitweight) {
		if (unitweight != null) {
			if (new SysUtil().isNum(unitweight) == false)
				unitweight = null;
		}
		this.unitweight = unitweight;
	}
	
	
	public String getGoodsid() {
		if (goodsid != null) {
			if (new SysUtil().isInt(goodsid) == false)
				goodsid = null;
		}
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		if (goodsid != null) {
			if (new SysUtil().isInt(goodsid) == false)
				goodsid = null;
		}
		this.goodsid = goodsid;
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

	public String getWeight() {
		if (weight != null) {
			if (new SysUtil().isNum(weight) == false)
				weight = null;
		}
		return weight;
	}

	public void setWeight(String weight) {
		if (weight != null) {
			if (new SysUtil().isNum(weight) == false)
				weight = null;
		}
		this.weight = weight;
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

	public String getVolume() {
		if (volume != null) {
			if (new SysUtil().isNum(volume) == false)
				volume = null;
		}
		return volume;
	}

	public void setVolume(String volume) {
		if (volume != null) {
			if (new SysUtil().isNum(volume) == false)
				volume = null;
		}
		this.volume = volume;
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

	public String getClaimvalue() {
		if (claimvalue != null) {
			if (new SysUtil().isNum(claimvalue) == false)
				claimvalue = null;
		}
		return claimvalue;
	}

	public void setClaimvalue(String claimvalue) {
		if (claimvalue != null) {
			if (new SysUtil().isNum(claimvalue) == false)
				claimvalue = null;
		}
		this.claimvalue = claimvalue;
	}

	public String getMeasuretype() {
		if (measuretype != null) {
			if ("".equals(measuretype))
				measuretype = null;
		}
		return measuretype;
	}

	public void setMeasuretype(String measuretype) {
		if (measuretype != null) {
			if ("".equals(measuretype))
				measuretype = null;
		}
		this.measuretype = measuretype;
	}
}