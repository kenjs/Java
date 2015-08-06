package com.cy.xtgl.domain;


import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The DOMAIN class FOR XT_JS_GNMK is created by tools.
 * @author HJH
 */

public class XtJsGnmkDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsDm;                             // ��ɫ����
	private String gnmkDm;                           // ����ģ�����
	private String jsDjxh;                           // ��λ����(SEQ_GW_DJXH��DM_GW.GW_DM)
	private String jsMc;                            //��ɫ����
	private String xtml;                             //����ϵͳĿ¼
	private String tag;                              //��ǩtag=��1���ǽ�ɫ-Ȩ�ޣ�������Ȩ��-��ɫ
	private String xtflDm;                            //ϵͳ�������
	private String ssJgbm;                           //��������
	private String treeStr;                          //ͨ��action��getTreeStr�ķ�����ȡ
	private String gnmkDmStr;                         //ͨ��action��queryGnmkDmsByJsDm�ķ�����ȡ��
	private String jsStr;							  //ͨ��action��queryJsInnerHtmlByGnmkDm�ķ�����ȡ
	private String selDmsStr;						  //tab1��ʹ�ã���ȡѡ�еĸ�ѡ��(��Ӧ�Ĳ˵�Ϊ��Ŀ¼���)��Ӧ��value
	private String allQueryNoneNodeGnmkDms ;//tab1��ȡȨ��������node=N�Ĳ˵�������Ŀ¼��㣩
	private String selJsDm;							 //��ɫ-Ȩ��ѡ��Ľ�ɫ����
	private String selGnmkDm;						//Ȩ��-��ɫѡ��Ĺ���ģ�����
	private String selJsDmsStr;						//Ȩ��-��ɫѡ���еĽ�ɫ����str�����ܰ���������ã�����
	private String listStr;
	private String flag;   							//�����жϣ��Ǳ����ɫȨ�ޣ�����Ȩ�޽�ɫ
	private String jsList; 							//���������ɫ
	private List<BaseBusinessDomain> dataListGw;  //��ɫ�б�
	public String getListStr() {
		return listStr;
	}



	public void setListStr(String listStr) {
		this.listStr = listStr;
	}



	public XtJsGnmkDomain() {
	}
	
	

	public List<BaseBusinessDomain> getDataListGw() {
		if(dataListGw==null)
		{
			dataListGw=new ArrayList<BaseBusinessDomain>();
		}
		return dataListGw;
	}



	public void setDataListGw(List<BaseBusinessDomain> dataListGw) {
		this.dataListGw = dataListGw;
	}



	public String getXtflDm() {
		return xtflDm;
	}

	public void setXtflDm(String xtflDm) {
		this.xtflDm = xtflDm;
	}


	//��ȡ��ɫ����
	public String getJsDm() {
		return this.jsDm;
	}

	//���ý�ɫ����
	public void setJsDm(String jsDm) {
		this.jsDm=jsDm;
	}

	//��ȡ����ģ�����
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	//���ù���ģ�����
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm=gnmkDm;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getGnmkDmStr() {
		return gnmkDmStr;
	}

	public void setGnmkDmStr(String gnmkDmStr) {
		this.gnmkDmStr = gnmkDmStr;
	}

	public String getJsStr() {
		return jsStr;
	}

	public void setJsStr(String jsStr) {
		this.jsStr = jsStr;
	}

	public String getTreeStr() {
		return treeStr;
	}

	public void setTreeStr(String treeStr) {
		this.treeStr = treeStr;
	}

	public String getAllQueryNoneNodeGnmkDms() {
		return allQueryNoneNodeGnmkDms;
	}

	public void setAllQueryNoneNodeGnmkDms(String allQueryNoneNodeGnmkDms) {
		this.allQueryNoneNodeGnmkDms = allQueryNoneNodeGnmkDms;
	}

	public String getSelDmsStr() {
		return selDmsStr;
	}

	public void setSelDmsStr(String selDmsStr) {
		this.selDmsStr = selDmsStr;
	}

	public String getSelJsDm() {
		return selJsDm;
	}

	public void setSelJsDm(String selJsDm) {
		this.selJsDm = selJsDm;
	}

	public String getSelGnmkDm() {
		return selGnmkDm;
	}

	public void setSelGnmkDm(String selGnmkDm) {
		this.selGnmkDm = selGnmkDm;
	}

	public String getSelJsDmsStr() {
		return selJsDmsStr;
	}

	public void setSelJsDmsStr(String selJsDmsStr) {
		this.selJsDmsStr = selJsDmsStr;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}






	public String getJsDjxh() {
		return jsDjxh;
	}



	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh = jsDjxh;
	}



	public String getJsMc() {
		return jsMc;
	}



	public void setJsMc(String jsMc) {
		this.jsMc = jsMc;
	}



	public String getXtml() {
		return xtml;
	}



	public void setXtml(String xtml) {
		this.xtml = xtml;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public String getJsList() {
		return jsList;
	}



	public void setJsList(String jsList) {
		this.jsList = jsList;
	}





}