package com.cy.jcgl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.dao.imp.ExtendDaoImp;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.jcgl.dao.JcKhShQsDao;
import com.cy.jcgl.domain.JcKhShQsDomain;


/**
 * The DAOIMP for 货运-派车信息管理
 * 
 * @date 2013.1.29
 * @author 闫伟
 */
@Repository
public class JcKhShQsDaoImp extends ExtendDaoImp implements JcKhShQsDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain) throws Exception {
		JcKhShQsDomain domain=(JcKhShQsDomain)baseDomain;
		//SysEncodeUtil.decodeURL(domain);
		//SysEncodeUtil.conGBK2ISO(domain);
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BaseBusinessDomain> dataList = null;
		
		map.put("ssJgbm", domain.getSjJgbm());
		map.put("rqq", domain.getRqq());
		map.put("rqz", domain.getRqz());
		map.put("fhrDjxh", domain.getFhrDjxh());
		String fhrMc = domain.getFhrMc();
		if(StringUtils.isNotBlank(fhrMc)){
			fhrMc = SysEncodeUtil.GBK2ISO(fhrMc);
		}
		map.put("fhrMc", fhrMc);
		
		if("Y".equals(domain.getXydjbz())){
			//协议登记时
			dataList = businessSqlMapClientTemplate.queryForList("queryKhJyqkList2", map);
		} else {
			//不需要协议登记
			dataList = businessSqlMapClientTemplate.queryForList("queryKhJyqkList", map);
		}
		return dataList;
	}

}
