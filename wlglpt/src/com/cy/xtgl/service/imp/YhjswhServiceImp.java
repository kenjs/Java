package com.cy.xtgl.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.WlglptCommonService;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.SysToolsUtil;
import com.cy.xtgl.dao.YhjswhDao;
import com.cy.xtgl.domain.QyJsDomain;
import com.cy.xtgl.domain.YhjswhDomain;
import com.cy.xtgl.service.YhjswhService;

/**
 * 用户角色维护
 * 
 * @author HaoY
 * @since
 */
@Service
public class YhjswhServiceImp extends BaseBusinessServiceImp implements
		YhjswhService {
	@Autowired
	private YhjswhDao dao;

	@Autowired
	private WlglptCommonService commonService;

	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		YhjswhDomain domain = (YhjswhDomain) baseBusinessDomain;
		//设置查询条件
		

		PageDomain page = baseBusinessDomain.getPage();
		//检索用户名称
		List<YhjswhDomain> czyList = dao.queryYhMcList(domain, page);
		// 取得所有当前物流企业操作员已维护的角色
		List<YhjswhDomain> czyJsList = dao.queryJsMcList(userDomain.getQyZcxh());

		for (int i = 0; i < czyList.size(); i++) {
			YhjswhDomain ele = czyList.get(i);
			//根据czyDjxh取角色，如有多个角色，以“,”分隔
			String jsMc = "";
			for (int j = 0; j < czyJsList.size(); j++) {
				YhjswhDomain ywhDomain = czyJsList.get(j);
				if (ywhDomain.getCzyDjxh().equals(ele.getCzyDjxh())) {
					jsMc += ywhDomain.getJsJc() + ",";
				}
			}
			
			//去","
			if (jsMc.endsWith(","))
				jsMc = jsMc.substring(0, jsMc.length() - 1);
			ele.setJsJc(jsMc);
		}

		domain.setDataList(czyList);
		this.doMyInit(baseBusinessDomain, userDomain);

	}

	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		YhjswhDomain domain = (YhjswhDomain) baseBusinessDomain;
		domain.setGsbm(domain.getSsJgbm());
		domain.setGsbm(domain.getDwDjxh());

		PageDomain page = new PageDomain();
		page.setPageSize(99999);
		//检索用户名称
		List<YhjswhDomain> czyList = dao.queryYhMcList(domain, page);
		// 取得所有当前物流企业操作员已维护的角色
		List<YhjswhDomain> czyJsList = dao.queryJsMcList(userDomain.getQyZcxh());

		for (int i = 0; i < czyList.size(); i++) {
			YhjswhDomain ele = czyList.get(i);

			String jsMc = "";
			for (int j = 0; j < czyJsList.size(); j++) {
				YhjswhDomain ywhDomain = czyJsList.get(j);
				if (ywhDomain.getCzyDjxh().equals(ele.getCzyDjxh())) {
					jsMc += ywhDomain.getJsJc() + ",";
				}
			}
			if (jsMc.endsWith(","))
				jsMc = jsMc.substring(0, jsMc.length() - 1);
			ele.setJsJc(jsMc);
		}

		domain.setDataList(czyList);
	}

	protected void doMyQueryMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		YhjswhDomain domain = (YhjswhDomain) baseBusinessDomain;

		List<YhjswhDomain> dataList = new ArrayList<YhjswhDomain>();
		List<YhjswhDomain> dataListChecked = new ArrayList<YhjswhDomain>();
		List<YhjswhDomain> dataListUnChecked = new ArrayList<YhjswhDomain>();
		// 根据用户序号取得用户的主部门
		
		// 查询所有角色
		List<QyJsDomain> qyJsList = commonService.getJsListByJgbm(domain.getDwDm());

		Map<String, YhjswhDomain> map = queryChoosedJs(domain);

		if (qyJsList != null) {
			for (QyJsDomain e1 : qyJsList) {
				YhjswhDomain t = new YhjswhDomain();
				t.setJsDjxh(e1.getJsDjxh());
				t.setJsJc(e1.getJsJc());
				t.setJsMc(e1.getJsMc());
			    t.setSjMc(e1.getSjMc());
				//判断是否是用户已选择角色。如果是，selBz为Y，否则为N
				if (map.containsKey(e1.getJsDjxh())) {
					t.setSelBz("Y");
					dataListChecked.add(t);
				} else {
					t.setSelBz("N");
					dataListUnChecked.add(t);
				}
			}
		}

		dataList.addAll(dataListChecked);
		dataList.addAll(dataListUnChecked);

		domain.setDataList(dataList);
	}

	protected void doMySaveMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		YhjswhDomain domain = (YhjswhDomain) baseBusinessDomain;
		String[] selJsDms = domain.getSelJsDms().split(",");

		// 先删后增
		dao.deleteByKey(domain, userDomain);
		for (String string : selJsDms) {
			if (!SysToolsUtil.isNullOrEmpty(string) && !",".equals(string)) {
				domain.setCzyDjxh(domain.getCzyDjxh());
				domain.setJsDjxh(string);

				dao.saveDomain(domain, userDomain);
			}
		}
	}

	//查询用户已经选择的角色，返回Map<String,YhjswhDomain>
	private Map<String, YhjswhDomain> queryChoosedJs(YhjswhDomain domain)
			throws Exception {
		Map<String, YhjswhDomain> map = new HashMap<String, YhjswhDomain>();
		List<YhjswhDomain> jslist = dao.queryChoosedJs(domain);
		if (jslist != null && jslist.size() > 0) {
			for (YhjswhDomain e : jslist) {
				map.put(e.getJsDjxh(), e);
			}
		}
		return map;
	}
}
