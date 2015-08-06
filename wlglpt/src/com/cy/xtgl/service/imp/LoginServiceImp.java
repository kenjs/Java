package com.cy.xtgl.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.bo.QySwdnDsh;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.XtGnmkDomain;
import com.cy.common.domain.XtXtmlDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.xtgl.dao.LoginDao;
import com.cy.xtgl.domain.QyRyGwDomain;
import com.cy.xtgl.service.LoginService;

/**
 * 
 * @Descriptoin ��¼ҵ����
 * @Note
 * @author anq
 * @since 2012-12-18 ����05:12:32
 * @version
 */
@Service
public class LoginServiceImp extends BaseBusinessServiceImp implements
		LoginService {

	@Autowired
	private LoginDao dao;

	/**
	 * ����û��˺�
	 */
	public void checkUserinfo(UserDomain userDomain) throws Exception {
		// ��̨���ǰ̨�������Ϣ
		if (userDomain != null) {
			// ��̨�ж���ҵ�����Ƿ�Ϊ��
			if (StringUtils.isBlank(userDomain.getQybm())) {
				userDomain.setIsLoginSuccess(false);
				ServiceException se = new ServiceException();
				se.setErrorCode("112001");
				throw se;
			}
			if (StringUtils.isBlank(userDomain.getDlzh())) {// ��̨�ж��û����Ƿ�Ϊ��
				userDomain.setIsLoginSuccess(false);
				ServiceException se = new ServiceException();
				se.setErrorCode("112001");
				throw se;
			}
			if (StringUtils.isEmpty(userDomain.getDlmm())) {// ��̨�жϵ�¼�����Ƿ�Ϊ��
				userDomain.setIsLoginSuccess(false);
				ServiceException se = new ServiceException();
				se.setErrorCode("112001");
				throw se;
			}
		} else {// ��̨�жϵ�¼��Ϣ�Ƿ�Ϊ��
			ServiceException se = new ServiceException();
			se.setErrorCode("112001");
			throw se;
		}
		// �ж��û���¼��Ϣ�Ƿ���ȷ
		dao.checkUserInfo(userDomain);
		if (userDomain.getRtnCode() != null
				&& userDomain.getRtnCode().intValue() == 0) {
			userDomain.setIsLoginSuccess(true);
			userDomain.setLoginMessage("login!loginIn");
		}
	}

	public void saveSwdnSh(UserDomain userDomain) throws Exception {
		QySwdnDsh qySwdnDsh = new QySwdnDsh();
		if (StringUtils.isNotBlank(userDomain.getDlzh())) {
			qySwdnDsh.setCzyDjxh(userDomain.getCzyDjxh());
		}
		qySwdnDsh.setMac(userDomain.getMacAddr());
		qySwdnDsh.setSqrCzyDjxh(userDomain.getCzyDjxh());
		//��ҵ�������ΪСд������ת��Ϊ��д 2013-10-12 add by xiay
		qySwdnDsh.setQybm(userDomain.getQybm().toUpperCase());
		qySwdnDsh.setCzyDjxh(userDomain.getCzyDjxh());//����Ա�Ǽ����
		//end
		
		dao.saveSwdnDsh(qySwdnDsh);
	}

	/**
	 * �˺ż��ɹ�����ȡ�û������Ϣ
	 */
	public UserDomain getUserInfo(UserDomain userDomain) throws Exception {
		UserDomain returnDomain = new UserDomain();

		// ��ȡ��¼�û��������Ϣ
		if (userDomain != null) {
			if (StringUtils.isBlank(userDomain.getCzyDjxh())) {
				ServiceException se = new ServiceException();
				se.setErrorCode("112001");
				throw se;
			} else {
				returnDomain = dao.getUserInfo(userDomain);
				returnDomain.setDlzh(userDomain.getDlzh());
				returnDomain.setXtyhflDm("2");
				returnDomain.setMacAddr(userDomain.getMacAddr());
			}
		}
		return returnDomain;
	}

	public void initTopMenu(UserDomain domain, UserDomain userDomain)
			throws Exception {
		List<XtXtmlDomain> topMenuList = dao.getTopMenu(userDomain);
		String xtmlXh = dao.queryLatestOprXtml(userDomain.getCzyDjxh());
		if (StringUtils.isBlank(xtmlXh)) {
			xtmlXh = "1009";
		}
		domain.setXtmlXh(xtmlXh);
		domain.setTopMenuList(topMenuList);
	}

	/**
	 * ��ȡ�û������˵���
	 */
	public String getUserMenu(UserDomain domain, UserDomain userDomain)
			throws Exception {
		String returnValue = "";
		// ��dao��ȡ��ǰ�û����õ�Ȩ�޲˵�
		List<XtGnmkDomain> subMenuList = dao.getUserMenu(domain, userDomain);
		domain.setSubMenuList(subMenuList);
		List<XtGnmkDomain> latestOprMenuList = dao.queryLatestOprMenu(domain,
				userDomain);
		domain.setLatestOprMenuList(latestOprMenuList);
		// returnValue = getParentTree(subMenuList);
		return returnValue;
	}

	/**
	 * 
	 * @Description: ��ȡ���ĸ��ڵ�
	 * @Note
	 * @author
	 * @since 2012-12-18
	 * @param resultList
	 * @return
	 */
	private String getParentTree(List<XtGnmkDomain> resultList) {
		if (resultList == null || resultList.size() <= 0) {
			return "";
		}
		StringBuffer strTree = new StringBuffer("[");
		String strSon = "";
		String lev = "";
		int parIndex = 0;
		for (XtGnmkDomain domain : resultList) {
			// lev = domain.getPxxh().substring(2,4);
			strSon = "";
			// ����Ϊ�ڵ㣬��Ϊ��һ��ʱ����������JSON
			if (StringUtils.isNotBlank(domain.getNode())
					&& domain.getNode().equals("Y")) {
				if (parIndex != 0) {
					strTree.append(",");
				}
				strTree.append("{");
				strTree.append("\"text\":\"").append(domain.getGnmkMc())
						.append("\",");
				if (parIndex == 0) {// ��һ���ڵ�չ��
					strTree.append("\"expanded\": true,\"classes\":\"folder\"");
				} else {
					strTree.append("\"expanded\": false,\"classes\":\"folder\"");
				}
				// ��ȡ�����ӽڵ�
				strSon = getSonTree(resultList, domain);
				strTree.append(strSon);
				strTree.append("}");
				parIndex++;
			}
		}
		strTree.append("]");
		return strTree.toString();
	}

	/**
	 * 
	 * @Description: ��ȡ�����ӽڵ�(�ݹ�ѭ��)
	 * @Note
	 * @author
	 * @since 2012-12-18
	 * @param resultList
	 * @param preDomain
	 * @return
	 */
	private String getSonTree(List<XtGnmkDomain> resultList,
			XtGnmkDomain preDomain) {
		StringBuffer returnBuf = new StringBuffer();
		StringBuffer strSonTree = new StringBuffer();
		String sonHead = ",\"children\":[";
		String sonBottom = "]";
		String strSon = "";
		int sonIndex = 0;
		for (XtGnmkDomain domain : resultList) {
			strSon = "";
			if (StringUtils.isNotBlank(preDomain.getGnmkDm())
					&& StringUtils.isNotBlank(domain.getSjMenuDm())
					&& preDomain.getGnmkDm().equals(domain.getSjMenuDm())) {
				if (sonIndex != 0) {
					strSonTree.append(",");
				}
				strSonTree.append("{");
				strSonTree.append("\"text\":\"").append(domain.getGnmkMc())
						.append("\",");
				if (domain.getNode().equals("Y")) {
					strSonTree
							.append("\"expanded\": false,\"classes\":\"folder\"");
				} else {
					// �ж�url ��������û��?����� ����&���� ���û����?����
					String url = domain.getUrl();
					if (domain.getUrl().indexOf("?") > 0) {
						url = url + "&";
					} else {
						url = url + "?";
					}
					url = url + "domain.gnmkDm=" + domain.getGnmkDm()
							+ "&domain.xtmlXh=" + preDomain.getGnmkDm();
					strSonTree.append("\"url\":\"").append(url).append("\",");
					strSonTree
							.append("\"expanded\": false,\"classes\":\"file\"");
				}
				strSon = getSonTree(resultList, domain);
				strSonTree.append(strSon);
				strSonTree.append("}");
				sonIndex++;
			}
		}
		if (strSonTree.toString().trim().length() > 0) {
			returnBuf.append(sonHead).append(strSonTree).append(sonBottom);
		}
		return returnBuf.toString();
	}

	public void initGwqh(QyRyGwDomain domain, UserDomain userDomain)
			throws Exception {
		List<BaseBusinessDomain> list = dao.queryGw(userDomain.getCzyDjxh());
		domain.setDataList(list);
	}


	public void initCdDh(UserDomain domain, UserDomain userDomain)
			throws Exception {
		this.initTopMenu(domain, userDomain);
		this.getUserMenu(domain, userDomain);
		List<XtXtmlDomain> list = domain.getTopMenuList();
		List<XtGnmkDomain> subMenuList = null;
		List<XtGnmkDomain> dataList = new ArrayList<XtGnmkDomain>();
		Map<String, List<XtGnmkDomain>> map1 = new HashMap<String, List<XtGnmkDomain>>();
		Map<String, List<XtGnmkDomain>> map2 = new HashMap<String, List<XtGnmkDomain>>();
		XtGnmkDomain xtDomain = new XtGnmkDomain();

		for (XtXtmlDomain xtXtmlDomain : list) {
			List<XtGnmkDomain> subMenuList1 = new ArrayList<XtGnmkDomain>();
			List<XtGnmkDomain> subMenuList2 = new ArrayList<XtGnmkDomain>();

			domain.setXtmlXh(String.valueOf(xtXtmlDomain.getXtmlXh()));
			subMenuList = dao.getUserMenu(domain, userDomain);
			xtXtmlDomain.setGnList(subMenuList);
			for (XtGnmkDomain xtGnmkDomain : xtXtmlDomain.getGnList()) {
				if (xtGnmkDomain.getNode().equals("Y")) {
					subMenuList1.add(xtGnmkDomain);
				} else {
					subMenuList2.add(xtGnmkDomain);
				}

			}
			xtXtmlDomain.setGnList(subMenuList1);
			for (XtGnmkDomain sub : xtXtmlDomain.getGnList()) {
				List<XtGnmkDomain> listt = new ArrayList<XtGnmkDomain>();
				for (XtGnmkDomain sub2 : subMenuList2) {
					if (sub2.getGnmkDm().indexOf(sub.getGnmkDm()) != -1) {
						listt.add(sub2);
					}
				}
				sub.setDomainList(listt);
			}

		}

	}
}
