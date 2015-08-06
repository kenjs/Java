package com.cy.hygl.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.dao.XyjsZbYjdzDao;
import com.cy.hygl.domain.XyjsZbYjdzDomain;
import com.cy.hygl.domain.XyjsZbYjdzShDomain;
import com.cy.hygl.service.XyjsZbYjdzService;

@Service
/**
 * The SERVICEIMP for ���ν���-ת��-�½����.
 * 
 * @author XIAY
 */
public class XyjsZbYjdzServiceImp extends BaseBusinessServiceImp implements XyjsZbYjdzService {
	@Autowired
	private XyjsZbYjdzDao dao;
	@Autowired
	private WlglptCommonDao commonDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain)baseBusinessDomain;
		
		//���ηְ����½�������� �����Ƿ�����������ζ��� 2013-10-08 by xiay
		String xtcs20213 = commonDao.getFunXtXtcs("20213", userDomain.getGsbm(), "2");
		domain.setSfsp(xtcs20213);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain)baseBusinessDomain;
		//�ְ����½��Ƿ���������
		String xtcs20213 = commonDao.getFunXtXtcs("20213", userDomain.getGsbm(), "2");
		domain.setSfsp(xtcs20213);
		dao.saveDomain(domain, userDomain);
		domain.setSfsp(xtcs20213);
		//�ְ����½��Ƿ��Զ�������������
		String xtcs20214 = commonDao.getFunXtXtcs("20214", userDomain.getGsbm(), "2");
		domain.setZdsp(xtcs20214);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzDomain domain=(XyjsZbYjdzDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	//��������
	public void plDz(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		XyjsZbYjdzDomain dom = new XyjsZbYjdzDomain();
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseBusinessDomain;
		String pldzStr = domain.getPldzStr();
		if(pldzStr != null){
			String[] strs = pldzStr.split(",");
			for(int i=0;i < strs.length;i++) {
				String str = strs[i];
				dom.setDzDjxh(str);
				//
				domain = (XyjsZbYjdzDomain)dao.getDomainByKey(dom);
				if(domain != null) {
					domain.setDzje(domain.getJsYj()); //���˽��
					domain.setDzCybz("N");   //���˲����־
					domain.setDzcyje(0.0);	 //������
					domain.setDzrCzyDjxh(userDomain.czyDjxh); //������
					domain.setDzrq(SysDateUtil.getCurrentDate());//��������
					domain.setDzJgbm(userDomain.bmbm);	//���˲���
					//�ְ����½��Ƿ���������
					String xtcs20213 = commonDao.getFunXtXtcs("20213", userDomain.getGsbm(), "2");
					domain.setSfsp(xtcs20213);
					// ���ö��˱��淽�� --���˺�
					dao.saveDomain(domain, userDomain);
				}
			}
		}
	}
	
	public void dyyl(BaseBusinessDomain baseDomain, 
			UserDomain userDomain) throws Exception{
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		List<BaseBusinessDomain> printlist = dao.downloadList(baseDomain);
		//
		domain.setDataList(printlist);
	}
	
	public void viewMx(BaseBusinessDomain baseDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain)baseDomain;
		dao.initDomainMx(domain);
	}
}
