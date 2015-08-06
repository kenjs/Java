package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR DM_XZQH is created by tools.
 * @author HJH
 */

public class DmXzqhDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xzqhDm;                           // ������������
	private String xzqhMc;                           // ������������
	private String xzqhJc;                           // �����������
	private String xzqhQc;                           // ��������ȫ��
	private String xzqhjbDm;                         // ���������������
	private String sjXzqhDm;                         // �ϼ�������������
	private String jbdm;                             // �������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String xzdqDm;                           // ������������
	private String xzdqMc;							 //	������������
	private String xzqhjbFlBm;                       // ������������������
	private String pyqc;                             // ƴ��ȫ��
	private String pyjc;                             // ƴ�����

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public DmXzqhDomain() {
	}

	//��ȡ������������
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//����������������
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//��ȡ������������
	public String getXzqhMc() {
		return this.xzqhMc;
	}

	//����������������
	public void setXzqhMc(String xzqhMc) {
		this.xzqhMc=xzqhMc;
	}

	//��ȡ�����������
	public String getXzqhJc() {
		return this.xzqhJc;
	}

	//���������������
	public void setXzqhJc(String xzqhJc) {
		this.xzqhJc=xzqhJc;
	}

	//��ȡ��������ȫ��
	public String getXzqhQc() {
		return this.xzqhQc;
	}

	//������������ȫ��
	public void setXzqhQc(String xzqhQc) {
		this.xzqhQc=xzqhQc;
	}

	//��ȡ���������������
	public String getXzqhjbDm() {
		return this.xzqhjbDm;
	}

	//�������������������
	public void setXzqhjbDm(String xzqhjbDm) {
		this.xzqhjbDm=xzqhjbDm;
	}

	//��ȡ�ϼ�������������
	public String getSjXzqhDm() {
		return this.sjXzqhDm;
	}

	//�����ϼ�������������
	public void setSjXzqhDm(String sjXzqhDm) {
		this.sjXzqhDm=sjXzqhDm;
	}

	//��ȡ�������
	public String getJbdm() {
		return this.jbdm;
	}

	//���ü������
	public void setJbdm(String jbdm) {
		this.jbdm=jbdm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ������������
	public String getXzdqDm() {
		return this.xzdqDm;
	}

	//����������������
	public void setXzdqDm(String xzdqDm) {
		this.xzdqDm=xzdqDm;
	}

	public String getXzdqMc() {
		return xzdqMc;
	}

	public void setXzdqMc(String xzdqMc) {
		this.xzdqMc = xzdqMc;
	}

	//��ȡ������������������
	public String getXzqhjbFlBm() {
		return this.xzqhjbFlBm;
	}

	//����������������������
	public void setXzqhjbFlBm(String xzqhjbFlBm) {
		this.xzqhjbFlBm=xzqhjbFlBm;
	}

	//��ȡƴ��ȫ��
	public String getPyqc() {
		return this.pyqc;
	}

	//����ƴ��ȫ��
	public void setPyqc(String pyqc) {
		this.pyqc=pyqc;
	}

	//��ȡƴ�����
	public String getPyjc() {
		return this.pyjc;
	}

	//����ƴ�����
	public void setPyjc(String pyjc) {
		this.pyjc=pyjc;
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
