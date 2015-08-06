package com.cy.bggl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_QDQT is created by tools.
 * 
 * @author HJH
 */

public class BgQdqtDomain extends BaseBusinessDomain {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String bgDjxh; // 办公登记序号(SEQ_BG_DJXH)

	private String czyDjxh; // 操作员登记序号

	private String rq; // 日期(YYYY-MM-DD)

	private String qdqtDm; // 签到签退代码

	private String yQdqtSj; // 应签到签退时间

	private String yqdSj; // 应签到签退时间

	private String sjQdqtSj; // 实际签到签退时间

	private String cdztbz; // 迟到早退标志(Y/N)

	private Double cdztsj; // 迟到早退时间(分钟)

	private String startTime; // 开始时间

	private String endTime; // 结束时间

	private List<BaseBusinessDomain> dataList; // 查询列表

	private List<String> startList;

	private String sbSj; // 上班时间
	private String sjSbSj; // 实际上班时间
	private String xbSj; // 下班时间
	private String sjXbSj;  // 实际下班时间
	private String tagg; //迟到、正常
	private String oldDate;  //本月一号的日期
	private String newDate;  //本月当前的日期

	public String getTagg() {
		if (qdqtDm != null) {
			if (qdqtDm.equals("1")) {
				if (cdztbz != null) {
					if (cdztbz.equals("Y")) {
						tagg = "迟到";
					} else {
						tagg = "正常";
					}
				}
			} else if (qdqtDm.equals("2")) {
				if (cdztbz != null) {
					if (cdztbz.equals("Y")) {
						tagg = "早退";
					} else {
						tagg = "正常";
					}
				}
			}
		}

		return tagg;
	}

	public void setTagg(String tagg) {
		this.tagg = tagg;
	}

	public BgQdqtDomain() {
	}

	// 获取办公登记序号(SEQ_BG_DJXH)
	public String getBgDjxh() {
		return this.bgDjxh;
	}

	// 设置办公登记序号(SEQ_BG_DJXH)
	public void setBgDjxh(String bgDjxh) {
		this.bgDjxh = bgDjxh;
	}

	// 获取操作员登记序号
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	// 设置操作员登记序号
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh = czyDjxh;
	}

	// 获取日期(YYYY-MM-DD)
	public String getRq() {
		try {
			return SysDateUtil.getYyyyMmdd(this.rq);
		} catch (Exception e) {
			return this.rq;
		}
	}

	// 设置日期(YYYY-MM-DD)
	public void setRq(String rq) {
		this.rq = rq;
	}

	// 获取签到签退代码
	public String getQdqtDm() {
		return this.qdqtDm;
	}

	// 设置签到签退代码
	public void setQdqtDm(String qdqtDm) {
		this.qdqtDm = qdqtDm;
	}

	// 获取应签到签退时间
	public String getYQdqtSj() {
		try {
			return SysDateUtil.getYyyyMmdd(this.yQdqtSj).substring(0, SysDateUtil.getYyyyMmdd(this.yQdqtSj).length() - 2);
		} catch (Exception e) {
			return this.yQdqtSj;
		}
	}

	// 设置应签到签退时间
	public void setYQdqtSj(String yQdqtSj) {
		this.yQdqtSj = yQdqtSj;
	}

	// 获取实际签到签退时间
	public String getSjQdqtSj() {
		try {
			return sjQdqtSj.substring(0, sjQdqtSj.length() - 1);
		} catch (Exception e) {
			return this.sjQdqtSj;
		}
	}

	// 设置实际签到签退时间
	public void setSjQdqtSj(String sjQdqtSj) {
		this.sjQdqtSj = sjQdqtSj;
	}

	// 获取迟到早退标志(Y/N)
	public String getCdztbz() {
		return this.cdztbz;
	}

	// 设置迟到早退标志(Y/N)
	public void setCdztbz(String cdztbz) {
		this.cdztbz = cdztbz;
	}

	// 获取迟到早退时间(分钟)
	public Double getCdztsj() {
		return this.cdztsj;
	}

	// 设置迟到早退时间(分钟)
	public void setCdztsj(Double cdztsj) {
		this.cdztsj = cdztsj;
	}

	public List<BaseBusinessDomain> getDataList() {
		if (dataList == null) {
			dataList = new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSbSj() {
		return sbSj;
	}

	public void setSbSj(String sbSj) {
		this.sbSj = sbSj;
	}

	public List<String> getStartList() {
		return startList;
	}

	public void setStartList(List<String> startList) {
		this.startList = startList;
	}

	public String getYqdSj() {
		try {
			return yqdSj.substring(0, yqdSj.length() - 1);
		} catch (Exception e) {
			return yqdSj;
		}

	}

	public void setYqdSj(String yqdSj) {
		this.yqdSj = yqdSj;
	}

	public String getXbSj() {
		return xbSj;
	}

	public void setXbSj(String xbSj) {
		this.xbSj = xbSj;
	}

	public String getNewDate() {
		return newDate;
	}

	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}

	public String getOldDate() {
		return oldDate;
	}

	public void setOldDate(String oldDate) {
		this.oldDate = oldDate;
	}

	public String getSjSbSj() {
		return sjSbSj;
	}

	public void setSjSbSj(String sjSbSj) {
		this.sjSbSj = sjSbSj;
	}

	public String getSjXbSj() {
		return sjXbSj;
	}

	public void setSjXbSj(String sjXbSj) {
		this.sjXbSj = sjXbSj;
	}

}
