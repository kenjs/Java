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

	private String bgDjxh; // �칫�Ǽ����(SEQ_BG_DJXH)

	private String czyDjxh; // ����Ա�Ǽ����

	private String rq; // ����(YYYY-MM-DD)

	private String qdqtDm; // ǩ��ǩ�˴���

	private String yQdqtSj; // Ӧǩ��ǩ��ʱ��

	private String yqdSj; // Ӧǩ��ǩ��ʱ��

	private String sjQdqtSj; // ʵ��ǩ��ǩ��ʱ��

	private String cdztbz; // �ٵ����˱�־(Y/N)

	private Double cdztsj; // �ٵ�����ʱ��(����)

	private String startTime; // ��ʼʱ��

	private String endTime; // ����ʱ��

	private List<BaseBusinessDomain> dataList; // ��ѯ�б�

	private List<String> startList;

	private String sbSj; // �ϰ�ʱ��
	private String sjSbSj; // ʵ���ϰ�ʱ��
	private String xbSj; // �°�ʱ��
	private String sjXbSj;  // ʵ���°�ʱ��
	private String tagg; //�ٵ�������
	private String oldDate;  //����һ�ŵ�����
	private String newDate;  //���µ�ǰ������

	public String getTagg() {
		if (qdqtDm != null) {
			if (qdqtDm.equals("1")) {
				if (cdztbz != null) {
					if (cdztbz.equals("Y")) {
						tagg = "�ٵ�";
					} else {
						tagg = "����";
					}
				}
			} else if (qdqtDm.equals("2")) {
				if (cdztbz != null) {
					if (cdztbz.equals("Y")) {
						tagg = "����";
					} else {
						tagg = "����";
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

	// ��ȡ�칫�Ǽ����(SEQ_BG_DJXH)
	public String getBgDjxh() {
		return this.bgDjxh;
	}

	// ���ð칫�Ǽ����(SEQ_BG_DJXH)
	public void setBgDjxh(String bgDjxh) {
		this.bgDjxh = bgDjxh;
	}

	// ��ȡ����Ա�Ǽ����
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	// ���ò���Ա�Ǽ����
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh = czyDjxh;
	}

	// ��ȡ����(YYYY-MM-DD)
	public String getRq() {
		try {
			return SysDateUtil.getYyyyMmdd(this.rq);
		} catch (Exception e) {
			return this.rq;
		}
	}

	// ��������(YYYY-MM-DD)
	public void setRq(String rq) {
		this.rq = rq;
	}

	// ��ȡǩ��ǩ�˴���
	public String getQdqtDm() {
		return this.qdqtDm;
	}

	// ����ǩ��ǩ�˴���
	public void setQdqtDm(String qdqtDm) {
		this.qdqtDm = qdqtDm;
	}

	// ��ȡӦǩ��ǩ��ʱ��
	public String getYQdqtSj() {
		try {
			return SysDateUtil.getYyyyMmdd(this.yQdqtSj).substring(0, SysDateUtil.getYyyyMmdd(this.yQdqtSj).length() - 2);
		} catch (Exception e) {
			return this.yQdqtSj;
		}
	}

	// ����Ӧǩ��ǩ��ʱ��
	public void setYQdqtSj(String yQdqtSj) {
		this.yQdqtSj = yQdqtSj;
	}

	// ��ȡʵ��ǩ��ǩ��ʱ��
	public String getSjQdqtSj() {
		try {
			return sjQdqtSj.substring(0, sjQdqtSj.length() - 1);
		} catch (Exception e) {
			return this.sjQdqtSj;
		}
	}

	// ����ʵ��ǩ��ǩ��ʱ��
	public void setSjQdqtSj(String sjQdqtSj) {
		this.sjQdqtSj = sjQdqtSj;
	}

	// ��ȡ�ٵ����˱�־(Y/N)
	public String getCdztbz() {
		return this.cdztbz;
	}

	// ���óٵ����˱�־(Y/N)
	public void setCdztbz(String cdztbz) {
		this.cdztbz = cdztbz;
	}

	// ��ȡ�ٵ�����ʱ��(����)
	public Double getCdztsj() {
		return this.cdztsj;
	}

	// ���óٵ�����ʱ��(����)
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
