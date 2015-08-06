package com.cy.common.action;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.common.service.BaseCommonService;
import com.cy.framework.action.BaseAction;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * action��ĸ�java����Ҫ�̳�json-default,����json����  ��������struts.xml�ļ��У�Ĭ�ϻ���json-default
 */
@SuppressWarnings("serial")
@ParentPackage("basebusiness-json")
@Result(name = "json", type = "json", params = { "root", "result" })
public class ExtendAction extends BaseAction {
	
	private BaseBusinessService service;
	
	@Autowired
	private BaseCommonService commonService;

	/**
	 * �������
	 */
	protected BaseBusinessDomain domain;

	public String init() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().init(getDomain(), userDomain);
		//clXttsxx();// ���������ʾ��Ϣ��add by hel 2011-6-23
		return XtglConstants.RESULT_NAME_INIT;
	}

	public String query() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().query(getDomain(), userDomain);
		//clXttsxx();// ���������ʾ��Ϣ��add by hel 2011-6-23
		return XtglConstants.RESULT_NAME_QUERY;
	}
	
	public String download() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().download(getDomain(), userDomain);
		return XtglConstants.RESULT_NAME_QUERY;
	}

	public String save() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().save(getDomain(), userDomain);
		//clXttsxx();// ���������ʾ��Ϣ��add by hel 2011-6-23
		return XtglConstants.RESULT_NAME_SAVE;
	}

	public String delete() throws Exception {
		getService().delete(getDomain(), getUserDomain());
		return XtglConstants.RESULT_NAME_DELETE;
	}

	public String initMx() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().initMx(getDomain(), userDomain);
		return XtglConstants.RESULT_NAME_INIT_MX;
	}

	public String queryMx() throws Exception {
		UserDomain userDomain = getUserDomain();
		getService().queryMx(getDomain(), userDomain);
		return XtglConstants.RESULT_NAME_QUERY_MX;
	}

	public String saveMx() throws Exception {
		getService().saveMx(getDomain(), getUserDomain());
		return XtglConstants.RESULT_NAME_SAVE_MX;
	}

	public String deleteMx() throws Exception {
		getService().deleteMx(getDomain(), getUserDomain());
		return XtglConstants.RESULT_NAME_DELETE_MX;
	}
	
	public String saveEnable() throws Exception {
		getService().saveEnable(getDomain(), getUserDomain());
		return XtglConstants.RESULT_NAME_ENABLE;
	}
	
	public String saveDisable() throws Exception {
		getService().saveDisable(getDomain(), getUserDomain());
		return XtglConstants.RESULT_NAME_DISABLE;
	}

	/**
	 * ��ȡ��ǰ��½�û��������Ϣ
	 * 
	 * @return BaseUserDomain
	 */
	public UserDomain getUserDomain() {
		UserDomain userDomain = (UserDomain) super.getUserDomain();
		return userDomain;
	}

	/**
	 * @return domain
	 */
	public BaseBusinessDomain getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 *            Ҫ���õ� domain
	 */
	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}

	/**
	 * @return service
	 */
	public BaseBusinessService getService() {
		return service;
	}

	/**
	 * @param service
	 *            Ҫ���õ� service
	 */
	public void setService(BaseBusinessService service) {
		this.service = service;
	}


	// ���������ʾ��Ϣ add by hel
	/*public void clXttsxx() throws Exception {
		// ��ȡ����Ĭ��ֵ
		
		 * XtXtcsDomain xtcsDomain=WlfpConstants.XT_XTCS.get(WlfpConstants.XT_XTCS_OPERATE_MSG); if(xtcsDomain==null){ domain.setCzts_Csmrz("N"); }else{ domain.setCzts_Csmrz(xtcsDomain.getCsmrz()); }
		 
		String csz = commonService.getCszByCsbm(XtglConstants.XT_XTCS_OPERATE_MSG, getUserDomain());
		if (StringUtils.isBlank(csz)) {
			domain.setCzts_Csmrz("N");
		} else {
			domain.setCzts_Csmrz(csz.trim());
		}
		// ��ȡ������ʾ��Ϣ
		XtXtmlGnmkCztsDomain gnDomain = XtglConstants.XT_XTML_GNMK_CZTS.get(domain.getHstXtmlXh() + "-" + domain.getHstGnmkDm());
		if (gnDomain == null) {
			domain.setCztsxx("�ޡ�");
		} else {
			domain.setCztsxx(gnDomain.getCzts());
		}
		
		//XT_XTCS_PAGE_SIZE
		String rowNum = commonService.getCszByCsbm(XtglConstants.XT_XTCS_PAGE_SIZE, getUserDomain());
		getUserDomain().setRowNum(rowNum);
	}*/
	

}
