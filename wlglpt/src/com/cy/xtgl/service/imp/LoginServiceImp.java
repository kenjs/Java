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
 * @Descriptoin 登录业务处理
 * @Note
 * @author anq
 * @since 2012-12-18 下午05:12:32
 * @version
 */
@Service
public class LoginServiceImp extends BaseBusinessServiceImp implements
		LoginService {

	@Autowired
	private LoginDao dao;

	/**
	 * 检测用户账号
	 */
	public void checkUserinfo(UserDomain userDomain) throws Exception {
		// 后台检测前台输入的信息
		if (userDomain != null) {
			// 后台判断企业编码是否为空
			if (StringUtils.isBlank(userDomain.getQybm())) {
				userDomain.setIsLoginSuccess(false);
				ServiceException se = new ServiceException();
				se.setErrorCode("112001");
				throw se;
			}
			if (StringUtils.isBlank(userDomain.getDlzh())) {// 后台判断用户名是否为空
				userDomain.setIsLoginSuccess(false);
				ServiceException se = new ServiceException();
				se.setErrorCode("112001");
				throw se;
			}
			if (StringUtils.isEmpty(userDomain.getDlmm())) {// 后台判断登录密码是否为空
				userDomain.setIsLoginSuccess(false);
				ServiceException se = new ServiceException();
				se.setErrorCode("112001");
				throw se;
			}
		} else {// 后台判断登录信息是否为空
			ServiceException se = new ServiceException();
			se.setErrorCode("112001");
			throw se;
		}
		// 判断用户登录信息是否正确
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
		//企业编码可能为小写，必须转换为大写 2013-10-12 add by xiay
		qySwdnDsh.setQybm(userDomain.getQybm().toUpperCase());
		qySwdnDsh.setCzyDjxh(userDomain.getCzyDjxh());//操作员登记序号
		//end
		
		dao.saveSwdnDsh(qySwdnDsh);
	}

	/**
	 * 账号检测成功，获取用户相关信息
	 */
	public UserDomain getUserInfo(UserDomain userDomain) throws Exception {
		UserDomain returnDomain = new UserDomain();

		// 获取登录用户的相关信息
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
	 * 获取用户所属菜单栏
	 */
	public String getUserMenu(UserDomain domain, UserDomain userDomain)
			throws Exception {
		String returnValue = "";
		// 到dao获取当前用户可用的权限菜单
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
	 * @Description: 获取树的根节点
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
			// 当树为节点，且为第一级时，加载树的JSON
			if (StringUtils.isNotBlank(domain.getNode())
					&& domain.getNode().equals("Y")) {
				if (parIndex != 0) {
					strTree.append(",");
				}
				strTree.append("{");
				strTree.append("\"text\":\"").append(domain.getGnmkMc())
						.append("\",");
				if (parIndex == 0) {// 第一个节点展开
					strTree.append("\"expanded\": true,\"classes\":\"folder\"");
				} else {
					strTree.append("\"expanded\": false,\"classes\":\"folder\"");
				}
				// 获取树的子节点
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
	 * @Description: 获取树的子节点(递归循环)
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
					// 判断url 连接中有没有?如果有 则用&连接 如果没有则?连接
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
