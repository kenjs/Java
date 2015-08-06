package com.cy.hygl.domain;

import com.cy.framework.domain.BaseBusinessDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����-��Ʊ���.
 * @author LYY
 */

public class HyKpsqshDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xh;                           // ���
	private String wsSpxh ;						//����������־
	private String spxh ;						//�������
	private String jdxh ;						//�ڵ����
	private String fsthbz ;						//��־
	private String fsrCzyDjxh ;					//
	private String fsrMc ;						//
	private String fsrq ;						//
	private String spjzsj ;						//������ֹʱ��
	private String cqbz ;						//
	private String sprMc ;						//����������
	private String sprq ;						//��������
	private String spjg ;						//�����۸�
	private String kpsqfsDm ;					//��Ʊ���뷽ʽ����
	private String kpsqfsMc ;					//��Ʊ���뷽ʽ����
	private String khDjxh ;						//�ͻ��Ǽ����
	private String khMc ;						//�ͻ�����
	private String sqKpjeHj ;					//���뿪Ʊ���ϼ�
	private String sqKprq ;						//���뿪Ʊ����
	private String bzsm ;						//��־˵��
	private String djrCzyDjxh ;					//�Ǽ��˲������
	private String djrMc ;						//�Ǽ�������
	private String djJgbm ;						//�ǼǼ�ܲ���
	private String bmmc ;						//��������
	private String ssJgbm ;						//������ܲ���
	private String dwmc ;						//

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private Map<String,List<HyKpsqshDomain>> map;

	private String rqQ;
	private String rqZ;
	private String shbz;//��˱�־

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getBzsm() {
		return bzsm;
	}

	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}

	public String getCqbz() {
		return cqbz;
	}

	public void setCqbz(String cqbz) {
		this.cqbz = cqbz;
	}

	public List<BaseBusinessDomain> getDataList() {
		if(dataList == null){
			dataList = new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	public String getDjJgbm() {
		return djJgbm;
	}

	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}

	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}

	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}

	public String getDjrMc() {
		return djrMc;
	}

	public void setDjrMc(String djrMc) {
		this.djrMc = djrMc;
	}

	public String getFsrCzyDjxh() {
		return fsrCzyDjxh;
	}

	public void setFsrCzyDjxh(String fsrCzyDjxh) {
		this.fsrCzyDjxh = fsrCzyDjxh;
	}

	public String getFsrMc() {
		return fsrMc;
	}

	public void setFsrMc(String fsrMc) {
		this.fsrMc = fsrMc;
	}

	public String getFsrq() {
		return fsrq;
	}

	public void setFsrq(String fsrq) {
		this.fsrq = fsrq;
	}

	public String getFsthbz() {
		return fsthbz;
	}

	public void setFsthbz(String fsthbz) {
		this.fsthbz = fsthbz;
	}

	public String getJdxh() {
		return jdxh;
	}

	public void setJdxh(String jdxh) {
		this.jdxh = jdxh;
	}

	public String getKhDjxh() {
		return khDjxh;
	}

	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}

	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public String getKpsqfsDm() {
		return kpsqfsDm;
	}

	public void setKpsqfsDm(String kpsqfsDm) {
		this.kpsqfsDm = kpsqfsDm;
	}

	public String getKpsqfsMc() {
		return kpsqfsMc;
	}

	public void setKpsqfsMc(String kpsqfsMc) {
		this.kpsqfsMc = kpsqfsMc;
	}

	public String getSpjg() {
		return spjg;
	}

	public void setSpjg(String spjg) {
		this.spjg = spjg;
	}

	public String getSpjzsj() {
		return spjzsj;
	}

	public void setSpjzsj(String spjzsj) {
		this.spjzsj = spjzsj;
	}

	public String getSprMc() {
		return sprMc;
	}

	public void setSprMc(String sprMc) {
		this.sprMc = sprMc;
	}

	public String getSprq() {
		return sprq;
	}

	public void setSprq(String sprq) {
		this.sprq = sprq;
	}

	public String getSpxh() {
		return spxh;
	}

	public void setSpxh(String spxh) {
		this.spxh = spxh;
	}

	public String getSqKpjeHj() {
		return sqKpjeHj;
	}

	public void setSqKpjeHj(String sqKpjeHj) {
		this.sqKpjeHj = sqKpjeHj;
	}

	public String getSqKprq() {
		return sqKprq;
	}

	public void setSqKprq(String sqKprq) {
		this.sqKprq = sqKprq;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getWsSpxh() {
		return wsSpxh;
	}

	public void setWsSpbz(String wsSpxh) {
		this.wsSpxh = wsSpxh;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public Map<String, List<HyKpsqshDomain>> getMap() {
		if(map == null){
			map = new HashMap<String, List<HyKpsqshDomain>>();
		}
		return map;
	}

	public void setMap(Map<String, List<HyKpsqshDomain>> map) {
		this.map = map;
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

	public String getShbz() {
		return shbz;
	}

	public void setShbz(String shbz) {
		this.shbz = shbz;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh = wsSpxh;
	}
	

}
