package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.bo.XtJsGnmk;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.WlglptCommonService;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.dao.JsqxwhDao;
import com.cy.xtgl.domain.QyJsDomain;
import com.cy.xtgl.domain.XtGnmkMenuDomain;
import com.cy.xtgl.domain.XtJsGnmkDomain;

/**
 * THE ACTION FOR ��ҵ-��֯���� ��ɫȨ��ά��
 * 
 * @author ��ΰ
 * @date 2013.1.17
 */

@Repository
public class JsqxwhDaoImp extends ExtendDaoImp implements JsqxwhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@Autowired
	private WlglptCommonService commService;

	// ����ϵͳ�����ȡ ��ϵͳ�µ�Ŀ¼�͹���ģ������νṹ ��htmlƬ��
	public String getTreeStr(String xtflDm, String tabPageNum,UserDomain user) throws Exception {
		String treeStr;
		List<XtGnmkMenuDomain> list = getXtGnmkMenu(user);
		treeStr = createTree(list, tabPageNum);
		return treeStr;
	}

	// ��ȡ�˵�
	private List<XtGnmkMenuDomain> getXtGnmkMenu(UserDomain use) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("zcxh", use.getQyZcxh());
		List<XtGnmkMenuDomain> dataList = businessSqlMapClientTemplate.queryForList("selectXtGnmkMenuByXtflDm", parameterMap);
		return dataList;
	}

	// ƴ�ӣ���װ��
	private String createTree(List<XtGnmkMenuDomain> menuList, String tabPageNum)
			throws Exception {
		StringBuffer str = new StringBuffer("");
		String inputType = "checkbox";
		for (int i = 0; i < menuList.size(); i++) {
			XtGnmkMenuDomain menu = menuList.get(i);
			if(menu.getRemark() == null){menu.setRemark("");}
			if (menu.getSjMenuDm() == null || "".equals(menu.getSjMenuDm())) {// ���ϼ��ڵ�
				// Ĭ��ǰ�������ڵ�չ��
				if (checkXj(menuList, menu.getMenuDm())) {// �����¼�
					str.append("<li>")
							.append("<div>")
							.append("<input type=\"" + inputType
									+ "\" value=\"" + menu.getMenuDm()
									+ "\" name=\"menus" + tabPageNum
									+ "\" onclick=\"onSelect(" + tabPageNum
									+ ",this.id,this.checked)\" id=\""
									+ tabPageNum + "-" + menu.getNode() + "-"
									+ menu.getMenuDm() + "-" + menu.getPxxh()
									+ "-" + menu.getSjMenuDm() + "\"/>")
							.append("<img src=\"/wlglpt/resource/pageframe/images/openedfolder.gif\" width=\"15\" height=\"15\" align=\"absmiddle\" />")
							.append(menu.getMenuMc()+" "+menu.getRemark()).append("</div>").append("<ul style='display:none'>");
					this.getTree(menuList, menu.getMenuDm(), str, tabPageNum);
					str.append("</ul></li>");
				} else {// �������¼�
					if ("Y".equals(menu.getNode())) {
						str.append("<li>")
								.append("<div>")
								.append("<input type=\"" + inputType
										+ "\" name=\"menus" + tabPageNum
										+ "\" onclick=\"onSelect(" + tabPageNum
										+ ",this.id,this.checked)\" id=\""
										+ tabPageNum + "-" + menu.getNode()
										+ "-" + menu.getMenuDm() + "-"
										+ menu.getPxxh() + "-"
										+ menu.getSjMenuDm() + "\"/>")
								.append("<img src=\"/wlglpt/resource/pageframe/images/openedfolder.gif\" width=\"15\" height=\"15\" align=\"absmiddle\" />")
								.append(menu.getMenuMc()+" "+menu.getRemark()).append("</div></li>");
					} else {
						str.append("<li>")
								.append("<div>")
								.append("<input type=\"" + inputType
										+ "\" value=\"" + menu.getMenuDm()
										+ "\" name=\"menus" + tabPageNum
										+ "\" onclick=\"onSelect(" + tabPageNum
										+ ",this.id,this.checked)\" id=\""
										+ tabPageNum + "-" + menu.getNode()
										+ "-" + menu.getMenuDm() + "-"
										+ menu.getPxxh() + "-"
										+ menu.getSjMenuDm() + "\"/>")
								.append("<img src=\"/wlglpt/resource/pageframe/images/openedfolder.gif\" width=\"15\" height=\"15\" align=\"absmiddle\" />")
								.append(menu.getMenuMc()+" "+menu.getRemark()).append("</div></li>");
					}
				}
			}
		}
		return str.toString();
	}

	public String getGnmkDmsByJsDm(String jsDm) throws Exception {
		StringBuffer gnmkDms = new StringBuffer("");
		Map<String, String> map = new HashMap<String, String>();
		map.put("jsDm", jsDm);
		List<XtJsGnmkDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectXtJsGnmkByJsDm", map);
		if (dataList != null && dataList.size() > 0) {
			for (XtJsGnmkDomain domain : dataList) {
				gnmkDms.append(domain.getGnmkDm()).append(",");
			}
		}
		return gnmkDms.toString();
	}

	private boolean checkXj(List<XtGnmkMenuDomain> list, String sjXtmlXh)
			throws Exception {
		boolean exist = false;
		for (int i = 0; i < list.size(); i++) {
			XtGnmkMenuDomain menu = list.get(i);
			if (!"".equals(menu.getSjMenuDm())
					&& sjXtmlXh.equals(menu.getSjMenuDm())) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	private void getTree(List<XtGnmkMenuDomain> list, String sjXtmlXh,
			StringBuffer str, String tabPageNum) throws Exception {
		String inputType = "checkbox";
		for (int i = 0; i < list.size(); i++) {
			XtGnmkMenuDomain menu = list.get(i);
			if(menu.getRemark() == null){menu.setRemark("");}
			if (sjXtmlXh.equals(menu.getSjMenuDm())) {
				if (checkXj(list, menu.getMenuDm())) {
					str.append("<li>")
							.append("<div>")
							.append("<input type=\"" + inputType
									+ "\" value=\"" + menu.getMenuDm()
									+ "\" name=\"menus" + tabPageNum
									+ "\" onclick=\"onSelect(" + tabPageNum
									+ ",this.id,this.checked)\" id=\""
									+ tabPageNum + "-" + menu.getNode() + "-"
									+ menu.getMenuDm() + "-" + menu.getPxxh()
									+ "-" + menu.getSjMenuDm() + "\"/>")
							.append("<img src=\"/wlglpt/resource/pageframe/images/openedfolder.gif\" width=\"15\" height=\"15\" align=\"absmiddle\" />")
							.append(menu.getMenuMc()+" "+menu.getRemark()).append("</div><ul style='display:none'>");
					this.getTree(list, menu.getMenuDm(), str, tabPageNum);
					str.append("</ul></li>");
				} else {
					if ("Y".equals(menu.getNode())) {
						str.append("<li>")
								.append("<div>")
								.append("<input type=\"" + inputType
										+ "\" value=\"" + menu.getMenuDm()
										+ "\" name=\"menus" + tabPageNum
										+ "\" onclick=\"onSelect(" + tabPageNum
										+ ",this.id,this.checked)\" id=\""
										+ tabPageNum + "-" + menu.getNode()
										+ "-" + menu.getMenuDm() + "-"
										+ menu.getPxxh() + "-"
										+ menu.getSjMenuDm() + "\"/>")
								.append("<img src=\"/wlglpt/resource/pageframe/images/openedfolder.gif\" width=\"15\" height=\"15\" align=\"absmiddle\" />")
								.append(menu.getMenuMc()+" "+menu.getRemark()).append("</div></li>");
					} else {
						str.append("<li>")
								.append("<div>")
								.append("<input type=\"" + inputType
										+ "\" value=\"" + menu.getMenuDm()
										+ "\" name=\"menus" + tabPageNum
										+ "\" onclick=\"onSelect(" + tabPageNum
										+ ",this.id,this.checked)\" id=\""
										+ tabPageNum + "-" + menu.getNode()
										+ "-" + menu.getMenuDm() + "-"
										+ menu.getPxxh() + "-"
										+ menu.getSjMenuDm() + "\"/>")
								.append("<img src=\"/wlglpt/resource/pageframe/images/menufile.gif\" width=\"15\" height=\"15\" align=\"absmiddle\" />")
								.append(menu.getMenuMc()+" "+menu.getRemark()).append("</div></li>");
					}
				}
			}
		}
	}

	// ���ϵͳ��ɫ����ģ��
	public void saveXtJsGnmk(String jsDm, String gnmkDm, String xtml)
			throws Exception {
		XtJsGnmk obj = new XtJsGnmk();
		obj.setJsDm(jsDm);
		obj.setGnmkDm(gnmkDm);
		obj.setXtml(xtml);
		businessSqlMapClientTemplate.insert("insertXtJsGnmk", obj);
	}

	// ɾ��ϵͳ-��ɫ-����ģ��
	public void deleteXtJsGnmk(XtJsGnmkDomain domain) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("jsDm", domain.getSelJsDm());
		String allNode = domain.getAllQueryNoneNodeGnmkDms();
		allNode = allNode.substring(0, allNode.length() - 1);
		parameterMap.put("gnmkDm", allNode);
		businessSqlMapClientTemplate.delete("deleteXtJsGnmk", parameterMap);
	}

	// �����������ϵͳ-��ɫ-����ģ���Ƿ����
	public boolean checkXtJsGnmk(String jsDm, String gnmkDm, String xtml)
			throws Exception {
		boolean flag = false;// Ĭ�ϲ�����
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("jsDm", jsDm);
		parameterMap.put("gnmkDm", gnmkDm);
		parameterMap.put("xtml", xtml);
		XtJsGnmkDomain domain = (XtJsGnmkDomain) businessSqlMapClientTemplate
				.queryForObject("selectXtJsGnmkByKey", parameterMap);
		if (domain != null) {
			flag = true;
		}
		return flag;
	}

	// ���ݹ���ģ������ȡ��ɫƴ���ַ���
	public String getJsInnerHtmlByGnmkDm(BaseBusinessDomain dom)
			throws Exception {
		XtJsGnmkDomain dom1 = (XtJsGnmkDomain) dom;
		QyJsDomain domain = new QyJsDomain();
		List<QyJsDomain> jsList = commService.getJsListByJgbm(dom1.getSsJgbm());// �õ���ǰ����������Ķ�Ӧ�Ľ�ɫ
		String jsDmsStr = this.getJsDmsByGnmkDm(dom1.getGnmkDm());// �õ�����ģ���Ӧ�Ľ�ɫ
		String[] jsDms = jsDmsStr.split(",");
		StringBuffer htmlBefore = new StringBuffer();
		StringBuffer htmlAfter = new StringBuffer();
		int index = 1;
		int index2 = 1;
		int k = 0;
		int j = 0;
		// ��ΪҪ�����ж�
		for (int n = 0; n < jsList.size(); n++) {
			domain = jsList.get(n);
			for (int i = 0; i < jsDms.length; i++) {
				if (jsDms[i].equals(domain.getJsDjxh())) {
					k++;
				}
			}
		}
		if (jsList.size() > 0) {
			for (int n = 0; n < jsList.size(); n++) {
				StringBuffer sb = new StringBuffer();
				boolean tag = false;
				domain = jsList.get(n);
				sb.append("<tr>").append("<td class=\"td\" align=\"center\">");
				for (int i = 0; i < jsDms.length; i++) {
					if (jsDms[i].equals(domain.getJsDjxh())) {
						tag = true;
						break;
					}
				}
				if (!tag) {
					j++;
					if (j == 1) {
						index2 = k + 1;
					}
					sb.append(
							"<input name=\"multiBoxs\" type=\"checkbox\" value=\"")
							.append(domain.getJsDjxh()).append("\"/></td>")
							.append("<td class=\"td\" align=\"center\">")
							.append(index2++).append("</td>");
				} else {
					sb.append(
							"<input name=\"multiBoxs\" type=\"checkbox\" checked=\"checked\" value=\"")
							.append(domain.getJsDjxh()).append("\"/></td>")
							.append("<td class=\"td\" align=\"center\">")
							.append(index++).append("</td>");
				}
				sb.append("<td class=\"td\" align=\"center\">")
						.append(domain.getJsMc())
						.append("</td>")
						.append("<td class=\"td\" align=\"center\" style=\"cursor:pointer;\">")
						.append(domain.getJsJc())
						.append("</td>");
				String bz = domain.getBzsm();
				if (bz == null) {
					sb.append(
							"<td class=\"td\" align=\"center\" style=\"cursor:pointer;\">")
							.append("").append("</td>")
							.append("</tr>");
				} else {
					sb.append(
							"<td class=\"td\" align=\"center\" style=\"cursor:pointer;\">")
							.append(domain.getBzsm())
							.append("</td>").append("</tr>");
				}

				if (tag) {
					htmlBefore.append(sb);
				} else {
					htmlAfter.append(sb);
				}
			}
		}
		return htmlBefore.append(htmlAfter).toString();
	}

	// ���ݹ���ģ������ȡ��XT_JS_GNMK����֮��Ӧ�����н�ɫ����
	@SuppressWarnings("unchecked")
	private String getJsDmsByGnmkDm(String gnmkDm) throws Exception {
		StringBuffer jsDms = new StringBuffer("");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("gnmkDm", gnmkDm);
		List<XtJsGnmkDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectXtJsGnmkByGnmkDm", parameterMap);
		if (dataList != null && !dataList.isEmpty()) {
			for (XtJsGnmkDomain domain : dataList) {
				jsDms.append(domain.getJsDm()).append(",");
			}
		}
		return jsDms.toString();
	}

	// ɾ��ϵͳ-��ɫ-����ģ����ݹ���ģ�����
	public void deleteXtJsGnmkByGnmkDm(String gnmkDm, String jsDm)
			throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("gnmkDm", gnmkDm);
		String str = jsDm.substring(0, jsDm.length() - 1);
		parameterMap.put("jsDm", str);
		businessSqlMapClientTemplate.delete("deleteXtJsGnmkByGnmkDm",
				parameterMap);
	}

	// ����������ȡ����Ӧ�Ľ�ɫ
	public List<QyJsDomain> queryJsByDjxh(BaseBusinessDomain domain)
			throws Exception {
		List<QyJsDomain> dataList = null;
		XtJsGnmkDomain qyDomain = (XtJsGnmkDomain) domain;
		if (StringUtils.isNotBlank(String.valueOf(qyDomain.getSsJgbm()))) {
			dataList = businessSqlMapClientTemplate.queryForList(
					"getJsmcByDjxh", qyDomain);
		}
		return dataList;
	}
}
