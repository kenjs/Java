package tf56.contract.domain;

import tf56.sofa.util.SysUtil;

public class OutGoods { // 运单子表
	private String goodsid; // 编码Id
	private String waybillid; // 运单Id
	private String goodstype; // 货物类型
	private String goodsname; // 货物名称
	private String spec; // 规格
	private String model; // 模式
	private String num; // 数量
	private String packagename; // 包装
	private String weight; // 重量
	private String volume; // 体积


	
	
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
			if ("".equals(num))
				num = null;
		}
		return num;
	}

	public void setNum(String num) {
		if (num != null) {
			if ("".equals(num))
				num = null;
		}
		this.num = num;
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
			if ("".equals(weight))
				weight = null;
		}
		return weight;
	}

	public void setWeight(String weight) {
		if (weight != null) {
			if ("".equals(weight))
				weight = null;
		}
		this.weight = weight;
	}

	public String getVolume() {
		if (volume != null) {
			if ("".equals(volume))
				volume = null;
		}
		return volume;
	}

	public void setVolume(String volume) {
		if (volume != null) {
			if ("".equals(volume))
				volume = null;
		}
		this.volume = volume;
	}
}