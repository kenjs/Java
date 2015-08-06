package com.cy.xtgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.WlglptCommonService;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.dao.JsqxwhDao;
import com.cy.xtgl.domain.QyJsDomain;
import com.cy.xtgl.domain.XtJsGnmkDomain;
import com.cy.xtgl.service.JsqxwhService;

/**
 * THE ACTION FOR 企业-组织机构 角色权限维护
 * 
 * @author 闫伟
 * @date 2013.1.17
 */

@Service
public class JsqxwhServiceImp extends BaseBusinessServiceImp implements
		JsqxwhService {
	@Autowired
	private JsqxwhDao dao;

	@Autowired
	private WlglptCommonService commService;

	public void getTreeStr(BaseBusinessDomain baseBusinessDomain,UserDomain useDomain)
			throws Exception {
		XtJsGnmkDomain domain = (XtJsGnmkDomain) baseBusinessDomain;
		domain.setTreeStr(dao.getTreeStr(domain.getXtflDm(), domain.getTag(),useDomain));
	}

	public void getGnmkDmsByJsDm(BaseBusinessDomain baseBusinessDomain)
			throws Exception {
		XtJsGnmkDomain domain = (XtJsGnmkDomain) baseBusinessDomain;
		domain.setGnmkDmStr(dao.getGnmkDmsByJsDm(domain.getJsDm()));
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		XtJsGnmkDomain domain = (XtJsGnmkDomain) baseBusinessDomain;
		List<QyJsDomain> jsDomain = commService.getJsListByJgbm(domain
				.getSsJgbm());
		StringBuffer sb = new StringBuffer();
		int i = 1;// 给角色排序
		for (QyJsDomain domain2 : jsDomain) {
			sb.append(
					"<tr onclick='getGnmkByKey(" + domain2.getJsDjxh() + ","
							+ i + ")' id='xtjs-" + i + "'>")
					.append("<td class=\"td\" align=\"center\" style=\"cursor:pointer;\">")
					.append(i++).append("</td>");
			sb.append("<td class=\"td\" style=\"cursor:pointer;\" align=\"center\">")
					.append(domain2.getJsMc())
					.append("</td>")
					.append("<td class=\"td\" align=\"center\" style=\"cursor:pointer;\">")
					.append(domain2.getJsJc()).append("</td>");
			String bz = domain2.getBzsm();
			if (bz == null) {
				sb.append(
						"<td class=\"td\" align=\"center\" style=\"cursor:pointer;\">")
						.append("")
						.append("</td>").append("</tr>");
			} else {
				sb.append(
						"<td class=\"td\" align=\"center\" style=\"cursor:pointer;\">")
						.append(domain2.getBzsm())
						.append("</td>").append("</tr>");
			}

		}
		domain.setListStr(sb.toString());
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		XtJsGnmkDomain domain = (XtJsGnmkDomain) baseBusinessDomain;
		if (domain.getFlag().equals("1")) {
			dao.deleteXtJsGnmk(domain);
			String[] selGnmkDms = domain.getSelDmsStr().split(":");
			for (int i = 0; i < selGnmkDms.length; i++) {
				if ("".equals(selGnmkDms[i])) {
					continue;
				}
				// 根据主键判断是否已经存在，不存在做保存，否则不做处理
				if (!dao.checkXtJsGnmk(domain.getSelJsDm(),
						selGnmkDms[i].split(",")[0],
						selGnmkDms[i].split(",")[1])) {
					dao.saveXtJsGnmk(domain.getSelJsDm(),
							selGnmkDms[i].split(",")[0],
							selGnmkDms[i].split(",")[1]);
				}
			}
		} else if (domain.getFlag().equals("2")) {
			String[] ary = domain.getSelGnmkDm().split(",");
			String gnmkDm = ary[0];
			String xtml = ary[1];
			dao.deleteXtJsGnmkByGnmkDm(gnmkDm, domain.getJsList());
			String[] selJsDms = domain.getSelJsDmsStr().split(",");
			for (int i = 0; i < selJsDms.length; i++) {
				if ("".equals(selJsDms[i])) {
					continue;
				}
				// 根据主键判断是否已经存在，不存在做保存，否则不做处理
				if (!dao.checkXtJsGnmk(selJsDms[i], gnmkDm, xtml)) {
					dao.saveXtJsGnmk(selJsDms[i], gnmkDm, xtml);
				}
			}
		}
	}

	// 根据功能模块代码获取角色拼接字符串
	public void getJsInnerHtmlByGnmkDm(BaseBusinessDomain baseBusinessDomain)
			throws Exception {
		XtJsGnmkDomain domain = (XtJsGnmkDomain) baseBusinessDomain;
		List<QyJsDomain> gnmkDomain = dao.queryJsByDjxh(baseBusinessDomain);
		String str = "";
		for (QyJsDomain domain2 : gnmkDomain) {
			str = str + domain2.getJsDjxh() + ",";
		}
		domain.setJsList(str);
		domain.setJsStr(dao.getJsInnerHtmlByGnmkDm(domain));
	}
}
