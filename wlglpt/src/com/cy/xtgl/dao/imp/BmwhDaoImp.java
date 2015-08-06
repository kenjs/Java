package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.constants.XtglConstants;
import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.common.domain.UserDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.domain.PageDomain;
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.xtgl.dao.BmwhDao;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * The DAOIMP for ��ҵ��֯��������ά��
 * 
 * @Author Yu huan
 * @Date 2013-01-8
 */

@Repository
public class BmwhDaoImp extends ExtendDaoImp implements BmwhDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
 
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(
			BaseBusinessDomain baseBusinessDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		PageDomain page = domain.getPage();
		Map<String, String> map = new HashMap<String, String>();
		int start = page.getStart();
		int pagSize = page.getPageSize();
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());
		if (StringUtils.isNotBlank(domain.getSjJgbm())) {
			map.put("sj", String.valueOf(domain.getSjJgbm()));
		}
		int count = ((Integer) (businessSqlMapClientTemplate.queryForObject(
				"getQyZzjgBmwhCount", map))).intValue();
		page.setTotalRecords(count);
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyZzjgBmwhPage", map, start, pagSize);
		return dataList;
	};

	@Override
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseDomain;
		if (StringUtils.isNotBlank(domain.getJgbm())) {
			BaseBusinessDomain dom = this.getDomainByKey(domain);
			BeanUtils.copyProperties(domain, dom);

		}
		else{
			Map<String, String> map=new HashMap<String, String>();
			map.put("jgbm", domain.getSjJgbm());
			QyZzjgDomain qyDomain=(QyZzjgDomain)businessSqlMapClientTemplate.queryForObject("selectQyzzjgBmMcByJgbm",map);
			domain.setSjMc(qyDomain.getMc());
		}
	}

	@Override
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain)
			throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseDomain;
		return (QyZzjgDomain) businessSqlMapClientTemplate.queryForObject(
				"selectQyZzjgByKey", domain);
	}

	@Override
	public void deleteByKey(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		businessSqlMapClientTemplate.delete("deleteQyZzjgByKey", domain);
	}

	@Override
	public void saveDomain(BaseBusinessDomain baseBusinessDomain,
			UserDomain user) throws Exception {
		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		QyZzjgDomain dom = null;
		if (domain.getJgbm() != null && !"".equals(domain.getJgbm().trim())) {
			dom = (QyZzjgDomain) this.getDomainByKey(domain);
		}
		String mc=SysEncodeUtil.UTF82GBK(domain.getMc());
		if (dom != null) {
			dom.setMc(domain.getMc());
			dom.setJc(domain.getJc());
			dom.setDz(domain.getDz());
			dom.setFzr(domain.getFzr());
			dom.setYb(domain.getYb());
			dom.setDh(domain.getDh());
			dom.setJc(domain.getMc());
			dom.setXgrCzyDjxh(user.getCzyDjxh());
			dom.setXgrq(SysDateUtil.getCurrentDate());
			dom.setPyjx(CnToSpellUtil.getFirstSpell(mc));
			dom.setPyqp(CnToSpellUtil.getFullSpell(mc));
			dom.setQybz(domain.getQybz());
			businessSqlMapClientTemplate.update("updateQyZzjgByKey", dom);
		} else {
			// ����ʱ��ȡ��ǰ�Ѵ��ڵ�������,������ڣ���ȡ�ñ���ĺ�4λ��תΪ��ֵ�� + 1��
			// �ٰ�ȡ�õı���תΪ4λ���ַ���������λ���Ļ���߲���0��������븸�������ļ�����봮��
			// ���ûȡ������Ĭ��Ϊ�����������ļ�����봮�� + ��0001
			QyZzjgDomain dd = new QyZzjgDomain();
			dd.setJgbm(String.valueOf(domain.getSjJgbm()));
			QyZzjgDomain sjbm = (QyZzjgDomain) this.getDomainByKey(dd);
			Long jici = new Long(sjbm.getJcbm());
			domain.setJcbm(String.valueOf(jici + 1));
			String max = this.getMaxJbdm(domain);
			if (max.equals("null")) {
				String gb = sjbm.getJbdm();
				domain.setJbdm(gb + "0001");
			} else {
				String Prefix = max.substring(0, max.length() - 4);
				String suffix = max.substring(max.length() - 4);
				int ii = Integer.parseInt(suffix);
				int gbbm = ii + 1;
				String s = "000" + gbbm;
				domain.setJbdm(Prefix + s.substring(s.length() - 4));
			}
			domain.setYxbz("Y");
			domain.setJc(domain.getMc());
			domain.setJglbDm(String.valueOf(4));
			domain.setCjrCzyDjxh(user.getCzyDjxh());
			domain.setXgrCzyDjxh(user.getCzyDjxh());
			domain.setCjrq(SysDateUtil.getCurrentDate());
			domain.setXgrq(SysDateUtil.getCurrentDate());
			domain.setPyjx(CnToSpellUtil.getFirstSpell(mc));
			domain.setPyqp(CnToSpellUtil.getFullSpell(mc));
			businessSqlMapClientTemplate.insert("insertQyZzjg", domain);
		}
	}

	public void startUse(QyZzjgDomain domain) throws Exception {
		if (StringUtils.isNotBlank(domain.getJgbm())) {
			businessSqlMapClientTemplate.update("startQyzzBmwhUse", domain);
		}
	}

	public void stopUse(QyZzjgDomain domain) throws Exception {
		businessSqlMapClientTemplate.update("stopQyzzBmwhUse", domain);
	}

	public String getMaxJbdm(QyZzjgDomain domain) throws Exception {
		// ��ȡ���Ļ�������
		String jbdm = String.valueOf(businessSqlMapClientTemplate
				.queryForObject("getQyzzBmwhMaxJbdm", domain));
		return jbdm;
	}

	public int checkQyzzBmwhMc(QyZzjgDomain domain) throws Exception {
		// ��ȡ�������ƣ������������ظ�����֤
		Map<String, String> map = new HashMap<String, String>();
		map.put("sjJgbm", "" + domain.getSjJgbm());
		map.put("mc", SysEncodeUtil.UTF82ISO(domain.getMc()));
		map.put("jgbm", domain.getJgbm());

		int jbdm = ((Integer) (businessSqlMapClientTemplate.queryForObject(
				"checkQyzzBmwhMc", map))).intValue();
		return jbdm;
	}

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain)
			throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(domain.getSjJgbm())) {
			map.put("sjJgbm", domain.getSjJgbm());
		}
		// ��������
		List<BaseBusinessDomain> dataList = businessSqlMapClientTemplate
				.queryForList("selectQyZzjgBmwhAll", map);
		return dataList;
	}



}
