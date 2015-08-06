package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PZ_HWXX is created by tools.
 * @author HJH
 */

public class HyPzHwxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pzDjxh;                           // 配载登记序号
	private String wfhDjxh;                          // 配载货物序号(未发货登记序号)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物明细序号)
	private Double hwSl;                             // 货物_数量
	private Double hwZl;                             // 货物_重量
	private Double hwTj;                             // 货物_体积
	private Double sr;                               // 收入
	private Double yjPsf;                            // 预计配送费
	private String bz;                         // 备注
	private String hzJgbm;
	
	private String pchwLsxh;
	
	private String zrbmDm;
	private String zrbmDjxh;
	private String zrbmDz;
	private String zrbmLxr;
	private String zrbmLxdh;
	private String zrbmXzqhDm;

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyPzHwxxDomain() {
	}

	//获取配载登记序号
	public String getPzDjxh() {
		return this.pzDjxh;
	}

	//设置配载登记序号
	public void setPzDjxh(String pzDjxh) {
		this.pzDjxh=pzDjxh;
	}

	//获取配载货物序号(未发货登记序号)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置配载货物序号(未发货登记序号)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取序号(货物明细序号)
	public String getXh() {
		return this.xh;
	}

	//设置序号(货物明细序号)
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取货物_数量
	public Double getHwSl() {
		return this.hwSl;
	}

	//设置货物_数量
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//获取货物_重量
	public Double getHwZl() {
		return this.hwZl;
	}

	//设置货物_重量
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//获取货物_体积
	public Double getHwTj() {
		return this.hwTj;
	}

	//设置货物_体积
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	//获取收入
	public Double getSr() {
		return this.sr;
	}

	//设置收入
	public void setSr(Double sr) {
		this.sr=sr;
	}

	//获取预计配送费
	public Double getYjPsf() {
		return this.yjPsf;
	}

	//设置预计配送费
	public void setYjPsf(Double yjPsf) {
		this.yjPsf=yjPsf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getPchwLsxh() {
		return pchwLsxh;
	}

	public void setPchwLsxh(String pchwLsxh) {
		this.pchwLsxh = pchwLsxh;
	}

	public String getHzJgbm() {
		return hzJgbm;
	}

	public void setHzJgbm(String hzJgbm) {
		this.hzJgbm = hzJgbm;
	}

	public String getZrbmDm() {
		return zrbmDm;
	}

	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm = zrbmDm;
	}

	public String getZrbmDjxh() {
		return zrbmDjxh;
	}

	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh = zrbmDjxh;
	}

	public String getZrbmDz() {
		return zrbmDz;
	}

	public void setZrbmDz(String zrbmDz) {
		this.zrbmDz = zrbmDz;
	}

	public String getZrbmLxr() {
		return zrbmLxr;
	}

	public void setZrbmLxr(String zrbmLxr) {
		this.zrbmLxr = zrbmLxr;
	}

	public String getZrbmLxdh() {
		return zrbmLxdh;
	}

	public void setZrbmLxdh(String zrbmLxdh) {
		this.zrbmLxdh = zrbmLxdh;
	}

	public String getZrbmXzqhDm() {
		return zrbmXzqhDm;
	}

	public void setZrbmXzqhDm(String zrbmXzqhDm) {
		this.zrbmXzqhDm = zrbmXzqhDm;
	}

	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
}
