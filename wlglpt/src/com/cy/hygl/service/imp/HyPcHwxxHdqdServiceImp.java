package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.hygl.dao.HyPcHwxxHdqdDao;
import com.cy.hygl.dao.HyPcHwxxHdqdMxDao;
import com.cy.hygl.domain.HyPcHwxxHdqdDomain;
import com.cy.hygl.service.HyPcHwxxHdqdService;

@Service
/**
 * The SERVICEIMP for 货运-派车-货物信息-回单清单.
 * 
 * @author HJH
 */
public class HyPcHwxxHdqdServiceImp extends BaseBusinessServiceImp implements HyPcHwxxHdqdService {
	@Autowired
	private HyPcHwxxHdqdDao dao;
	@Autowired
	private HyPcHwxxHdqdMxDao mxDao;
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain=(HyPcHwxxHdqdDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		List<HyPcHwxxHdqdDomain> list = dao.hdDjxhList(domain.getHdqdDjxh()); 
		for(HyPcHwxxHdqdDomain dom:list){
			String hdqdDjxh = dao.selectQdDjxh(dom.getHdDjxh(), userDomain.getGsbm());
			mxDao.updateDqbzBykey(dom.getHdDjxh(), hdqdDjxh);
		}
		//删 清单对应明细
		mxDao.deleteByKey(domain, userDomain);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void dbfs(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain=(HyPcHwxxHdqdDomain)baseBusinessDomain;
		domain.setJszt("1");
		dao.saveDomain(domain, userDomain);
		HyPcHwxxHdqdDomain dom = new HyPcHwxxHdqdDomain();
		
		BeanUtils.copyProperties(dom, domain);
		dom.setDqztbz("1");
		List<String> xhs=domain.getHdDjxhs();
		if(null==xhs|| xhs.isEmpty())
			return;
		
		for (String xh : xhs) {
			if(StringUtils.isNotBlank(xh)){
				dom.setHdDjxh(xh);
				mxDao.updateDqbzByqdDjxh(xh);
				mxDao.saveDomain(dom, userDomain);
			}
		}
	}
	public void doQueryQd(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryQdList(domain);
		domain.setDataList(dataList);
	}
	public void qdjs(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		HyPcHwxxHdqdDomain domain=(HyPcHwxxHdqdDomain)baseBusinessDomain;
		dao.updatejsztWhenJs(domain.getHdqdDjxh());
	}
    public void qdth(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
    	HyPcHwxxHdqdDomain domain=(HyPcHwxxHdqdDomain)baseBusinessDomain;
		int count = dao.checkTh(domain.getHdqdDjxh());
		if(count>0){
			throw new DiyServiceException("回单已被发送，不在本公司！");
		}else{
	    	dao.updatejsztWhenTh(domain.getHdqdDjxh());
		}
	}
    public void queryHdByQd(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryHdByQd(domain);
		domain.setDataList(dataList);
	}
}
