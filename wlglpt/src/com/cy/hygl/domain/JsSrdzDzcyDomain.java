package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;

/**
 * The DOMAIN class FOR JS_SRDZ_DZCY is created by tools.
 * @author HJH
 */

public class JsSrdzDzcyDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // ���˵Ǽ����
	private String xh;                               // ���
	private Double dzcyje;                           // ���ʲ�����
	private String dzcyyyDm;                         // ���ʲ���ԭ�����
	private String dzcyyyMc;                         // ���ʲ���ԭ�����
	private String dzcyClfsDm;                       // ���ʲ��촦��ʽ����
	private String dzcyClfsMc;                       // ���ʲ��촦��ʽ����
	private String bz;                               // ��ע˵��
	//private String preJsDjxh;                        // ���ν���Ǽ����
	private String xcJsDjxh;//�´�_����Ǽ����
	private String wlssDjxh;                         // ������ʧ�Ǽ����

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public JsSrdzDzcyDomain() {
	}


	public String getDzDjxh() {
		return dzDjxh;
	}


	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh = dzDjxh;
	}


	//��ȡ���
	public String getXh() {
		return this.xh;
	}

	//�������
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ���ʲ�����
	public Double getDzcyje() {
		return this.dzcyje;
	}

	//���ö��ʲ�����
	public void setDzcyje(Double dzcyje) {
		this.dzcyje=dzcyje;
	}

	//��ȡ���ʲ���ԭ�����
	public String getDzcyyyDm() {
		return this.dzcyyyDm;
	}

	//���ö��ʲ���ԭ�����
	public void setDzcyyyDm(String dzcyyyDm) {
		this.dzcyyyDm=dzcyyyDm;
	}

	//��ȡ���ʲ��촦��ʽ����
	public String getDzcyClfsDm() {
		return this.dzcyClfsDm;
	}

	//���ö��ʲ��촦��ʽ����
	public void setDzcyClfsDm(String dzcyClfsDm) {
		this.dzcyClfsDm=dzcyClfsDm;
	}

	//��ȡ��ע˵��
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע˵��
	public void setBz(String bz) {
		this.bz=bz;
	}

	/*//��ȡ���ν���Ǽ����
	public String getPreJsDjxh() {
		return this.preJsDjxh;
	}

	//���ö��ν���Ǽ����
	public void setPreJsDjxh(String preJsDjxh) {
		this.preJsDjxh=preJsDjxh;
	}*/

	//��ȡ������ʧ�Ǽ����
	public String getWlssDjxh() {
		return this.wlssDjxh;
	}

	//����������ʧ�Ǽ����
	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh=wlssDjxh;
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

	public String getDzcyClfsMc() {
		return dzcyClfsMc;
	}

	public void setDzcyClfsMc(String dzcyClfsMc) {
		this.dzcyClfsMc = dzcyClfsMc;
	}

	public String getDzcyyyMc() {
		return dzcyyyMc;
	}

	public void setDzcyyyMc(String dzcyyyMc) {
		this.dzcyyyMc = dzcyyyMc;
	}

	public String getXcJsDjxh() {
		return xcJsDjxh;
	}


	public void setXcJsDjxh(String xcJsDjxh) {
		this.xcJsDjxh = xcJsDjxh;
	}
}
