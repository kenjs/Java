package com.cy.zygl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_YL_CLXX is created by tools.
 * @author HJH
 */

public class QyYlClxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String clDjxh;                           // �����Ǽ����(SEQ_ZY_DJXH)
	private String czXm;                             // ����_����
	private String czZjlxDm;                         // ����_֤�����ʹ���
	private String czZjhm;                           // ����_֤������
	private String czLxdh;                           // ����_��ϵ�绰
	private String gcbz;                             // �ҳ���־��Y/N��
	private String cyrClDjxh ;						 // �����Ǽ����
	private String clhm;                             // ��������
	private String clsxDm;                           // �������Դ���
	private String thclbz;                           // ���������־(Y/N)
	private String ysclbz;                           // ���䳵����־(Y/N)
	private String psclbz;                           // ���ͳ�����ʶ(Y/N)
	private String clxhwhDjxh;                       // �����ͺ�ά�����
	private String bz;                               // ��ע
	private String djrCzyDjxh;                       // �Ǽ���
	private String djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String zjlxMc;

	private String xh;                               // 
	private String sjXm;                             // ˾������
	private String sjZjhm;                           // ˾�����֤����
	private String sjSjhm;                           // ˾���ֻ�����
	private String sjLxdh;                           // ˾��������ϵ�绰
	private String jszhm;                            // ˾����ʻ֤����
	private String whbz;                             // ά����־(H�ֹ�ά����Z�Զ�ά��)
	private List<String> xhs;
	private String xxgxfsDm;
	private String xxgxfsMc;
	
	
	private List<String> sjXms;                             // ˾������
	private List<String> sjZjhms;                           // ˾�����֤����
	private List<String> sjSjhms;                           // ˾���ֻ�����
	private List<String> sjLxdhs;                           // ˾��������ϵ�绰
	private List<String> jszhms;                            // ˾����ʻ֤����
	
	private String sjInfo;
	private String callOpenWinFun ;
	
	private String pcfsDm;
	private String cz;
	private String tj;
	private String cd;
	private String kd;
	private String gd;
	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getKd() {
		return kd;
	}

	public void setKd(String kd) {
		this.kd = kd;
	}

	public String getGd() {
		return gd;
	}

	public void setGd(String gd) {
		this.gd = gd;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private List<QyYlClxxDomain> sjList;
	
	public QyYlClxxDomain() {
	}

	//��ȡ�����Ǽ����(SEQ_ZY_DJXH)
	public String getClDjxh() {
		return this.clDjxh;
	}

	//���ó����Ǽ����(SEQ_ZY_DJXH)
	public void setClDjxh(String clDjxh) {
		this.clDjxh=clDjxh;
	}

	//��ȡ����_����
	public String getCzXm() {
		return this.czXm;
	}

	//���ó���_����
	public void setCzXm(String czXm) {
		this.czXm=czXm;
	}

	//��ȡ����_֤�����ʹ���
	public String getCzZjlxDm() {
		return this.czZjlxDm;
	}

	//���ó���_֤�����ʹ���
	public void setCzZjlxDm(String czZjlxDm) {
		this.czZjlxDm=czZjlxDm;
	}

	//��ȡ����_֤������
	public String getCzZjhm() {
		return this.czZjhm;
	}

	//���ó���_֤������
	public void setCzZjhm(String czZjhm) {
		this.czZjhm=czZjhm;
	}

	//��ȡ����_��ϵ�绰
	public String getCzLxdh() {
		return this.czLxdh;
	}

	//���ó���_��ϵ�绰
	public void setCzLxdh(String czLxdh) {
		this.czLxdh=czLxdh;
	}

	//��ȡ��������
	public String getClhm() {
		return clhm;
	}

	//���ó�������
	public void setClhm(String clhm) {
		this.clhm=clhm;
	}

	//��ȡ�������Դ���
	public String getClsxDm() {
		return this.clsxDm;
	}

	//���ó������Դ���
	public void setClsxDm(String clsxDm) {
		this.clsxDm=clsxDm;
	}

	//��ȡ���������־(Y/N)
	public String getThclbz() {
		return this.thclbz;
	}

	//�������������־(Y/N)
	public void setThclbz(String thclbz) {
		this.thclbz=thclbz;
	}

	//��ȡ���䳵����־(Y/N)
	public String getYsclbz() {
		return this.ysclbz;
	}

	//�������䳵����־(Y/N)
	public void setYsclbz(String ysclbz) {
		this.ysclbz=ysclbz;
	}

	//��ȡ���ͳ�����ʶ(Y/N)
	public String getPsclbz() {
		return this.psclbz;
	}

	//�������ͳ�����ʶ(Y/N)
	public void setPsclbz(String psclbz) {
		this.psclbz=psclbz;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ�Ǽ���
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//���õǼ���
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ�Ǽ�����
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//���õǼ�����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ������
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//��ȡ��������
	public String getCjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
	}

	//���ô�������
	public void setCjrq(String cjrq) {
		this.cjrq=cjrq;
	}

	//��ȡ�޸���
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//�����޸���
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//��ȡ�޸�����
	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	//�����޸�����
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}

	public String getCjrMc() {
		return this.cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getXgrMc() {
		return this.xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
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

	public String getZjlxMc() {
		return zjlxMc;
	}

	public void setZjlxMc(String zjlxMc) {
		this.zjlxMc = zjlxMc;
	}

	public List<QyYlClxxDomain> getSjList() {
		if(sjList == null){
			sjList = new ArrayList<QyYlClxxDomain>();
		}
		return sjList;
	}

	public void setSjList(List<QyYlClxxDomain> sjList) {
		this.sjList = sjList;
	}

	public String getJszhm() {
		return jszhm;
	}

	public void setJszhm(String jszhm) {
		this.jszhm = jszhm;
	}

	public String getSjLxdh() {
		return sjLxdh;
	}

	public void setSjLxdh(String sjLxdh) {
		this.sjLxdh = sjLxdh;
	}

	public String getSjSjhm() {
		return sjSjhm;
	}

	public void setSjSjhm(String sjSjhm) {
		this.sjSjhm = sjSjhm;
	}

	public String getSjXm() {
		return sjXm;
	}

	public void setSjXm(String sjXm) {
		this.sjXm = sjXm;
	}

	public String getSjZjhm() {
		return sjZjhm;
	}

	public void setSjZjhm(String sjZjhm) {
		this.sjZjhm = sjZjhm;
	}

	public String getWhbz() {
		return whbz;
	}

	public void setWhbz(String whbz) {
		this.whbz = whbz;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSjInfo() {
		return sjInfo;
	}

	public void setSjInfo(String sjInfo) {
		this.sjInfo = sjInfo;
	}

	public String getXxgxfsDm() {
		return xxgxfsDm;
	}

	public void setXxgxfsDm(String xxgxfsDm) {
		this.xxgxfsDm = xxgxfsDm;
	}

	public String getXxgxfsMc() {
		return xxgxfsMc;
	}

	public void setXxgxfsMc(String xxgxfsMc) {
		this.xxgxfsMc = xxgxfsMc;
	}

	public String getPcfsDm() {
		return pcfsDm;
	}

	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm = pcfsDm;
	}

	public String getGcbz() {
		return gcbz;
	}

	public void setGcbz(String gcbz) {
		this.gcbz = gcbz;
	}

	public String getClxhwhDjxh() {
		return clxhwhDjxh;
	}

	public void setClxhwhDjxh(String clxhwhDjxh) {
		this.clxhwhDjxh = clxhwhDjxh;
	}

	public List<String> getXhs() {
		return xhs;
	}

	public void setXhs(List<String> xhs) {
		this.xhs = xhs;
	}

	public String getCallOpenWinFun() {
		return callOpenWinFun;
	}

	public void setCallOpenWinFun(String callOpenWinFun) {
		this.callOpenWinFun = callOpenWinFun;
	}

	public String getCyrClDjxh() {
		return cyrClDjxh;
	}

	public void setCyrClDjxh(String cyrClDjxh) {
		this.cyrClDjxh = cyrClDjxh;
	}

	public List<String> getSjXms() {
		return sjXms;
	}

	public void setSjXms(List<String> sjXms) {
		this.sjXms = sjXms;
	}

	public List<String> getSjZjhms() {
		return sjZjhms;
	}

	public void setSjZjhms(List<String> sjZjhms) {
		this.sjZjhms = sjZjhms;
	}

	public List<String> getSjSjhms() {
		return sjSjhms;
	}

	public void setSjSjhms(List<String> sjSjhms) {
		this.sjSjhms = sjSjhms;
	}

	public List<String> getSjLxdhs() {
		return sjLxdhs;
	}

	public void setSjLxdhs(List<String> sjLxdhs) {
		this.sjLxdhs = sjLxdhs;
	}

	public List<String> getJszhms() {
		return jszhms;
	}

	public void setJszhms(List<String> jszhms) {
		this.jszhms = jszhms;
	}

}
