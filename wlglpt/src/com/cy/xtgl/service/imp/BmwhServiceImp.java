package com.cy.xtgl.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.xtgl.dao.BmwhDao;
import com.cy.xtgl.domain.QyZzjgDomain;
import com.cy.xtgl.service.BmwhService;
/**
 * The ServiceIMP for 企业组织机构部门维护
 * 
 * @author Yu huan
 * @data 2013-01-8
 */
@Service
public class BmwhServiceImp extends BaseBusinessServiceImp implements
		BmwhService {
	@Autowired
	private BmwhDao dao;

	// 检索数据
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		List<BaseBusinessDomain> bmwhList = dao.queryList(domain);
		domain.setDataList(bmwhList);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	// 添加部门维护
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	// 部门维护的删除操作
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	// 下载操作
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	// 部门维护中部门名称进行重复验证
	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		int count = dao.checkQyzzBmwhMc(domain);
		// 如果count>0，有重复，抛异常为110301
		if (count > 0) {
			ServiceException se = new ServiceException();
			se.setErrorCode("110301");
			throw se;
		}
	}

	//	 部门维护中的状态启用操作
	@Override
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.startUse(domain);
	}

	// 部门维护中的状态停用操作
	@Override
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		QyZzjgDomain domain = (QyZzjgDomain) baseBusinessDomain;
		dao.stopUse(domain);
	}

}
