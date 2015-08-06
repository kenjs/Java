package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR JS_SRDZ_QD is created by tools.
 * @author HJH
 */

public class JsSrdzQdDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // �嵥�Ǽ����(SEQ_QD_DJXH)
	private String khDjxh;                           // �ͻ��Ǽ����
	private String khMc;
	private String qdmc;                             // �嵥����
	private String dzqdHzfsDm;                       // �����嵥���ܷ�ʽ����
	private String dzqdhzfsMc;
	private Double heJe;                             // �ϼƽ��
	private Double yfJe;                             // �Ѹ����
	private Double wfJe;                             // δ�����
	private Double ysqKpJe;                             // �����뿪Ʊ���
	private Double wsqKpJe;                             // δ���뿪Ʊ���
	private String djrCzyDjxh;                       // �Ǽ���
	private String cjrMc;
	private String djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	private String bmMc;
	private String ssJgbm;                           // ��������
	private String dwMc;
	private String yxbz;                             // ��Ч��־(Y/N)
	
	private String jsdw;							//���㵥λ
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private List<BaseBusinessDomain> dzQdList;
	
	private String rqQ;
	private String rqZ;
	
	private String ddbh;
	private String ywDjxh;
	private String ywMxXh;							 // ҵ����ϸ���
	private String ywLydm;							 // ҵ����Դ���� 1��������ˣ�2�����õǼǣ�3��������ʧ
	private String xdrq;
	private Double dzWj;	//����δ��
	private Double jsSr;
	private Double jsYj;
	private Double jsWj;
	
	private String hwmc;                             // ��������
	private String jssl;                             // ��������
	private String hdbh;                             // �ص����
	private String bz;                               // ��װ
	private String sfd;//ʼ����
	private String mdd;//Ŀ�ĵ�
	private String sl;//����
	private String zl;//����
	private String tj;//���
	private String hwflMc;                           // ����
	private String dzcybz;//���ʲ����־
	private String dzcyje;//���˲�����
	private String dzje;
	private String dzrCzyDjxh;                       //  ������
	private String dzrMc;                       // ����������
	private String dzrRq;                       // ����������
	private String existBz;
	private String sfKpBz;                      // 'Y'��/ 'N' ��
	
	private String xtcs20212;					//��Ʊ�������
	private String kpsqDjxh;
	private String errCode1;
	private String errCode2;
	
	private List<String> ywDjxhs;
	
	private Map<String, List<JsSrdzQdDomain>> map;
	
	private List<HyZyglFydjDomain> fydjList;
	

	public JsSrdzQdDomain() {
	}

	//��ȡ�嵥�Ǽ����(SEQ_QD_DJXH)
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//�����嵥�Ǽ����(SEQ_QD_DJXH)
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	//��ȡ�ͻ��Ǽ����
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//���ÿͻ��Ǽ����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ�嵥����
	public String getQdmc() {
		return this.qdmc;
	}

	//�����嵥����
	public void setQdmc(String qdmc) {
		this.qdmc=qdmc;
	}

	//��ȡ�����嵥���ܷ�ʽ����
	public String getDzqdHzfsDm() {
		return this.dzqdHzfsDm;
	}

	//���ö����嵥���ܷ�ʽ����
	public void setDzqdHzfsDm(String dzqdHzfsDm) {
		this.dzqdHzfsDm=dzqdHzfsDm;
	}

	//��ȡ�ϼƽ��
	public Double getHeJe() {
		return this.heJe;
	}

	//���úϼƽ��
	public void setHeJe(Double heJe) {
		this.heJe=heJe;
	}

	//��ȡ�Ѹ����
	public Double getYfJe() {
		return this.yfJe;
	}

	//�����Ѹ����
	public void setYfJe(Double yfJe) {
		this.yfJe=yfJe;
	}

	//��ȡδ�����
	public Double getWfJe() {
		return this.wfJe;
	}

	//����δ�����
	public void setWfJe(Double wfJe) {
		this.wfJe=wfJe;
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

	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	public String getRqQ() {
		return rqQ;
	}

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	public String getRqZ() {
		return rqZ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getBmMc() {
		return bmMc;
	}

	public void setBmMc(String bmMc) {
		this.bmMc = bmMc;
	}

	public String getCjrMc() {
		return cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getDwMc() {
		return dwMc;
	}

	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}

	public String getDzqdhzfsMc() {
		return dzqdhzfsMc;
	}

	public void setDzqdhzfsMc(String dzqdhzfsMc) {
		this.dzqdhzfsMc = dzqdhzfsMc;
	}

	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public Double getWsqKpJe() {
		return wsqKpJe;
	}

	public void setWsqKpJe(Double wsqKpJe) {
		this.wsqKpJe = wsqKpJe;
	}

	public Double getYsqKpJe() {
		return ysqKpJe;
	}

	public void setYsqKpJe(Double ysqKpJe) {
		this.ysqKpJe = ysqKpJe;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDzcyje() {
		return dzcyje;
	}

	public void setDzcyje(String dzcyje) {
		this.dzcyje = dzcyje;
	}

	public String getDzrCzyDjxh() {
		return dzrCzyDjxh;
	}

	public void setDzrCzyDjxh(String dzrCzyDjxh) {
		this.dzrCzyDjxh = dzrCzyDjxh;
	}

	public String getDzrMc() {
		return dzrMc;
	}

	public void setDzrMc(String dzrMc) {
		this.dzrMc = dzrMc;
	}

	public String getDzrRq() {
		if(dzrRq!=null){
			try {
				dzrRq=SysDateUtil.format(SysDateUtil.parse(dzrRq));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dzrRq;
	}

	public void setDzrRq(String dzrRq) {
		this.dzrRq = dzrRq;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}

	public String getHwflMc() {
		return hwflMc;
	}

	public void setHwflMc(String hwflMc) {
		this.hwflMc = hwflMc;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public String getYwDjxh() {
		return ywDjxh;
	}

	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh = ywDjxh;
	}

	public String getJssl() {
		return jssl;
	}

	public void setJssl(String jssl) {
		this.jssl = jssl;
	}

	public Double getJsSr() {
		return jsSr;
	}

	public void setJsSr(Double jsSr) {
		this.jsSr = jsSr;
	}

	public Double getJsWj() {
		return jsWj;
	}

	public void setJsWj(Double jsWj) {
		this.jsWj = jsWj;
	}

	public Double getJsYj() {
		return jsYj;
	}

	public void setJsYj(Double jsYj) {
		this.jsYj = jsYj;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getXdrq() {
		if(null!=xdrq){
			try {
				xdrq=SysDateUtil.format(SysDateUtil.parse(xdrq));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return xdrq;
	}

	public void setXdrq(String xdrq) {
		this.xdrq = xdrq;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getExistBz() {
		return existBz;
	}

	public void setExistBz(String existBz) {
		this.existBz = existBz;
	}

	public List<String> getYwDjxhs() {
		if (ywDjxhs == null) {
			ywDjxhs = new ArrayList<String>();
		}
		return ywDjxhs;
	}

	public void setYwDjxhs(List<String> ywDjxhs) {
		this.ywDjxhs = ywDjxhs;
	}

	public Map<String, List<JsSrdzQdDomain>> getMap() {
		if(null==map)
			map=new HashMap<String, List<JsSrdzQdDomain>>();
		return map;
	}

	public void setMap(Map<String, List<JsSrdzQdDomain>> map) {
		this.map = map;
	}

	public Double getDzWj() {
		return dzWj;
	}

	public void setDzWj(Double dzWj) {
		this.dzWj = dzWj;
	}

	public String getMdd() {
		return mdd;
	}

	public void setMdd(String mdd) {
		this.mdd = mdd;
	}

	public String getSfd() {
		return sfd;
	}

	public void setSfd(String sfd) {
		this.sfd = sfd;
	}

	public String getDzcybz() {
		return dzcybz;
	}

	public void setDzcybz(String dzcybz) {
		this.dzcybz = dzcybz;
	}

	public String getDzje() {
		return dzje;
	}

	public void setDzje(String dzje) {
		this.dzje = dzje;
	}

	public String getYwMxXh() {
		return ywMxXh;
	}

	public void setYwMxXh(String ywMxXh) {
		this.ywMxXh = ywMxXh;
	}

	public String getYwLydm() {
		return ywLydm;
	}

	public void setYwLydm(String ywLydm) {
		this.ywLydm = ywLydm;
	}

	public List<HyZyglFydjDomain> getFydjList() {
		if (fydjList == null) {
			fydjList = new ArrayList<HyZyglFydjDomain>();
		}
		return fydjList;
	}

	public void setFydjList(List<HyZyglFydjDomain> fydjList) {
		this.fydjList = fydjList;
	}

	public String getSfKpBz() {
		return sfKpBz;
	}

	public void setSfKpBz(String sfKpBz) {
		this.sfKpBz = sfKpBz;
	}

	public List<BaseBusinessDomain> getDzQdList() {
		if (dzQdList == null) {
			dzQdList = new ArrayList<BaseBusinessDomain>();
		}
		return dzQdList;
	}

	public void setDzQdList(List<BaseBusinessDomain> dzQdList) {
		this.dzQdList = dzQdList;
	}

	public String getXtcs20212() {
		return xtcs20212;
	}

	public void setXtcs20212(String xtcs20212) {
		this.xtcs20212 = xtcs20212;
	}

	public String getKpsqDjxh() {
		return kpsqDjxh;
	}

	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh = kpsqDjxh;
	}

	public String getErrCode1() {
		return errCode1;
	}

	public void setErrCode1(String errCode1) {
		this.errCode1 = errCode1;
	}

	public String getErrCode2() {
		return errCode2;
	}

	public void setErrCode2(String errCode2) {
		this.errCode2 = errCode2;
	}

	public String getJsdw() {
		return jsdw;
	}

	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
	}
}
