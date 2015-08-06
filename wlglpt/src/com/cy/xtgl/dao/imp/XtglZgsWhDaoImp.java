package com.cy.xtgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.common.domain.UserDomain;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.CnToSpellUtil;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.xtgl.dao.XtglZgsWhDao;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * The DAOIMP for 企业-组织机构.
 * 
 * @Descriptoin 总公司维护dao 
* @Note
* @author ylp
* @since 2013-1-9 下午05:22:51
 */

@Repository
public class XtglZgsWhDaoImp implements XtglZgsWhDao {
	
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
    
	//保存
	@SuppressWarnings("static-access")
	public void saveDomain(BaseBusinessDomain businessDomain, UserDomain userdomain) throws Exception {		
		QyZzjgDomain domain = (QyZzjgDomain)businessDomain;
		QyZzjgDomain dom = (QyZzjgDomain)getDomainByKey(domain);
	    String s = SysEncodeUtil.UTF82GBK(domain.getMc());
		
		dom.setMc(s);
		dom.setJc(domain.getJc());
		dom.setDh(domain.getDh());
		dom.setFzr(domain.getFzr());
		dom.setYb(domain.getYb());
		dom.setDz(domain.getDz());
		
		dom.setPyqp(CnToSpellUtil.getFullSpell(s));//设置拼音全拼
		dom.setPyjx(CnToSpellUtil.getFirstSpell(s));//设置拼音简写
		dom.setXgrCzyDjxh(userdomain.czyDjxh);
		dom.setXgrq(SysDateUtil.getCurrentDate().toString());
		businessSqlMapClientTemplate.update("updateQyZzjgByKey", dom);
	}

	// 根据企业组织序号获取domain
	public QyZzjgDomain getDomainByQyzcxh(UserDomain userdomain) throws Exception {		
		Map<String, String> map = new HashMap<String, String>();
		map.put("qyzcxh", userdomain.getQyZcxh());

		QyZzjgDomain dom = (QyZzjgDomain) businessSqlMapClientTemplate
				.queryForObject("selectQyZzjgByQyzcxh", map);

		return dom;
	}

	// 检查修改后的domain名称是否有重复的
	public boolean checkMcre(QyZzjgDomain domain) throws Exception {
		Map<String, String> map = new HashMap<String, String>();	
		map.put("jgbm", domain.getJgbm());
		map.put("mc", SysEncodeUtil.UTF82ISO(domain.getMc()));
		
		Object obj=businessSqlMapClientTemplate.queryForObject("selectQyZzjgnumByKeymc", map);
		int count = 0;
		if(obj!=null)
			 count = ((Integer)obj).intValue();
		if (count > 0)
			return true;// 重复
		return false;// 不重复
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain)
			throws Exception {
		QyZzjgDomain dom = (QyZzjgDomain)domain;
		return (QyZzjgDomain) businessSqlMapClientTemplate.queryForObject("selectQyZzjgByKey",dom);
	}

	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain)
			throws Exception {
		return null;
	}

	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain)
			throws Exception {
		return null;
	}

	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain)
			throws Exception {
		
	}

	public void initDomainMx(BaseBusinessDomain domain) throws Exception {
		
	}
}
