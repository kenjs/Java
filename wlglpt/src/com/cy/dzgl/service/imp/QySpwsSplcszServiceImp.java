package com.cy.dzgl.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.dzgl.dao.QySpwsSplcszDao;
import com.cy.dzgl.domain.QySpwsSplcszDomain;
import com.cy.dzgl.domain.QySpwsSplcszZbDomain;
import com.cy.dzgl.service.QySpwsSplcszService;
import com.cy.framework.domain.BaseBusinessDomain;

@Service
/**
 * The SERVICEIMP for 企业-审批文书-审批流程设置.
 * 
 * @author anq
 */
public class QySpwsSplcszServiceImp extends BaseBusinessServiceImp implements QySpwsSplcszService {
	@Autowired
	private QySpwsSplcszDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setZgsbm(userDomain.getZgsbm());
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain)baseBusinessDomain;
		//dao.initDomainMx(domain);
		domain.setZgsbm(userDomain.getZgsbm());
		dao.queryWsxx(domain, userDomain);
		dao.queryWssplcsz(domain, userDomain);
		List<QySpwsSplcszZbDomain> zbList = dao.querySplcszZbList(domain.getSplcSzxh());
		domain.setZbList(zbList);
	}
	
	@Override
	protected void doMyInitMxAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain)baseBusinessDomain;
		//判断保存按钮是否存在，当审批流程设置序号为空时;点击设置时传入的单位或部门跟根据审批流程设置序号取的ssJgbm一致时
		if(domain.getSplcSzxh() == null || domain.getSplcSzxh().longValue() <= 0)
			domain.setSaveBz("Y");
		else{
			if(domain.getCurDwbm().equals(domain.getSsJgbm())){
				domain.setSaveBz("Y");
				domain.setQxszbz("Y");
			}else
				domain.setCxszbz("Y");
		}	
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain)baseBusinessDomain;
		domain.setZgsbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain)baseBusinessDomain;
		domain.setZgsbm(userDomain.getZgsbm());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain=(QySpwsSplcszDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		dao.deleteSpjcBySplcSzxh(domain, userDomain);
	}
	
	public void initSpjcMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QySpwsSplcszDomain domain=(QySpwsSplcszDomain)baseBusinessDomain;
		dao.initSpjcDomainMx(domain);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveSpjc(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		dao.saveSpjc(baseBusinessDomain, userDomain);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteSplcZb(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		dao.deleteSplcZb(baseBusinessDomain, userDomain);
		initMx(baseBusinessDomain, userDomain);
	}
	
	public void queryQySpwsXmflbzByKey(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		dao.queryQySpwsXmflbzByKey(baseBusinessDomain, userDomain);
	}

	@Override
	protected void doMyDeleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		super.doMyDeleteMx(baseBusinessDomain, userDomain);
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain)baseBusinessDomain;
		dao.deleteQxszSplc(domain, userDomain);
	}

	@Override
	protected void doMySaveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		super.doMySaveMx(baseBusinessDomain, userDomain);
		QySpwsSplcszDomain domain = (QySpwsSplcszDomain)baseBusinessDomain;
		dao.saveCxszSplc(domain, userDomain);
		//重新检索数据
		this.initMx(domain, userDomain);
		//置取消设置标志
		domain.setQxszbz("Y");
	}

}
