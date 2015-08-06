package com.cy.common.service.imp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cy.common.dao.WsspCommonDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WsspCommonDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.common.service.WsspCommonService;
import com.cy.dzgl.dao.QyWsSpZbDao;
import com.cy.dzgl.domain.QyWsSpZbDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysToolsUtil;

@Service
public class WsspCommonServiceImp extends BaseBusinessServiceImp implements WsspCommonService {
	

	@Autowired
	private WsspCommonDao dao;
	
	@Autowired
	private QyWsSpZbDao wsspZbDao;
	
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		//��ѯ��ҵ-����-����-�ӱ�
		QyWsSpZbDomain wsspZbDomain=(QyWsSpZbDomain)wsspZbDao.getDomainByKey(new QyWsSpZbDomain(domain.getWsspxh(),domain.getSpxh()));
		
		if(null==wsspZbDomain){
			new DiyServiceException("δ�ҵ����������Ĵ�������¼");
		}
		
		domain.setSpjg(wsspZbDomain.getSpjg());
		domain.setSpyj(wsspZbDomain.getSpyj());
		//���������ж�
		dao.queryWszsJudge(domain, userDomain);
		if("N".equals(wsspZbDomain.getSpbz())){
			domain.setSaveBz(true);
			domain.setBackBz(true);
			if(!SysToolsUtil.isNullOrEmpty(wsspZbDomain.getSprCzyDjxh()) && !SysToolsUtil.isNullOrEmpty(wsspZbDomain.getSprq()) && !SysToolsUtil.isNullOrEmpty(wsspZbDomain.getSpjg())){
				if(domain.isJudgeBz()){
					domain.setSendBz(false);
				}else{
					domain.setSendBz(true);
					//��ʼ��������Ϣ
					this.initSendXX(userDomain, domain);
				}
			}
		}else {
			// �������˻�
			if (domain.isJudgeBz()) {
				domain.setBackBz(true);
				// �������־�û�false
				domain.setJudgeBz(false);
			}
		}		
	}

	@Override
	protected void doMyInitAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		//ȡ������url
		domain.setSpjUrl(dao.queryWsspjUrl(domain.getWsspxh()));
		//ȡ��ת��Ϣlist
		domain.setWssplzList(dao.querySplzcx(domain, userDomain));
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		//����QY_WS_SP_ZB��ҵ-����-����-�ӱ�
		this.updateWsspzbDomain(baseBusinessDomain, userDomain);
	}

	@Override
	protected void doMySaveAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		//���������ж�
		dao.queryWszsJudge(domain, userDomain);
		domain.setBackBz(true);
		domain.setSaveBz(true);
		//��������ʱ��û�з���
		if(domain.isJudgeBz()){
			domain.setSendBz(false);
		}else{
			domain.setSendBz(true);
			//��ʼ��������Ϣ
			this.initSendXX(userDomain, domain);
		}
		//ȡ������url
		domain.setSpjUrl(dao.queryWsspjUrl(domain.getWsspxh()));
		//ȡ��ת��Ϣlist
		domain.setWssplzList(dao.querySplzcx(domain, userDomain));
	}
	

	@Override
	protected void doMySaveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		//�ȸ���QY_WS_SP_ZB��ҵ-����-����-�ӱ�
		updateWsspzbDomain(baseBusinessDomain, userDomain);
		//��������
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.saveJudge(domain, userDomain);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveBack(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		//�ȸ���QY_WS_SP_ZB��ҵ-����-����-�ӱ�
		updateWsspzbDomain(baseBusinessDomain, userDomain);
		//�����˻�
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.saveBack(domain, userDomain);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void send(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		//�ȸ���QY_WS_SP_ZB��ҵ-����-����-�ӱ�
		updateWsspzbDomain(baseBusinessDomain, userDomain);
		//���÷���
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.saveSend(domain, userDomain);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void plSend(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		//�ȸ���QY_WS_SP_ZB��ҵ-����-����-�ӱ�
		updateWsspzbDomain(baseBusinessDomain, userDomain);
		//���������ж�
		dao.queryWszsJudge(domain, userDomain);
		if(domain.isJudgeBz()){
			//��������
			dao.saveJudge(domain, userDomain);
		}else{
			//���÷���
			dao.saveSend(domain, userDomain);
		}
	}
	//��ѯ����������תlist
	public void queryWssplzList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		//��������������ת
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.querySplzcx(domain, userDomain);
	}
	
	//����ʱ����QY_WS_SP_ZB��ҵ-����-����-�ӱ�
	protected void updateWsspzbDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		QyWsSpZbDomain spzb=new QyWsSpZbDomain();
		spzb.setWsSpxh(domain.getWsspxh());
		spzb.setSpxh(domain.getSpxh());
		spzb.setSpjg(domain.getSpjg());
		spzb.setSpyj(domain.getSpyj());
		//����QY_WS_SP_ZB��ҵ-����-����-�ӱ�
		wsspZbDao.updateDomain(spzb, userDomain);
	}
	
	//��ʼ��������Ϣ
	public void initSendXX(UserDomain userDomain, WsspCommonDomain domain) throws Exception {
		WsspCommonDomain dom=dao.queryFsSelect(domain, userDomain);
		if(null==dom)
			return;
		domain.setSpjdsm(dom.getSpjdsm());
		domain.setSpjgjgDm(dom.getSpjgjgDm());
		domain.setSpjgjgmc(dom.getSpjgjgmc());
		domain.setSpJgbm(dom.getSpJgbm());
		domain.setSpjgmc(dom.getSpjgmc());
		domain.setGwDjxh(dom.getGwDjxh());
		domain.setGwmc(dom.getGwmc());
		domain.setSprJdxh(dom.getSprJdxh());
		domain.setWsspms(dom.getWsspms());
		domain.setWsspmsmc(dom.getWsspmsmc());
		
		List<DmbGgDomain> sprList=new ArrayList<DmbGgDomain>();
		
		initSprList(userDomain, dom, sprList);
		
		domain.setSprList(sprList);
	}
	
	public void querySpyj(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		QyWsSpZbDomain wsspZbDomain=(QyWsSpZbDomain)wsspZbDao.getDomainByKey(new QyWsSpZbDomain(domain.getWsspxh(),domain.getSpxh()));
		if(wsspZbDomain!=null)
			domain.setWsSpZbDomain(wsspZbDomain);
	}
	
	//@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void queryWsspms(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		domain.setNextWssp(true);//����Ҫ������һ����
		WsspCommonDomain dom=dao.queryFsSelect(domain, userDomain);
		if(null==dom)
			return;
		//domain.setSpjdsm(dom.getSpjdsm());
		//domain.setSpJgbm(dom.getSpJgbm());
		//domain.setSpjgmc(dom.getSpjgmc());
		//domain.setGwDjxh(dom.getGwDjxh());
		//domain.setGwmc(dom.getGwmc());
		//domain.setSprJdxh(dom.getSprJdxh());
		domain.setWsspms(dom.getWsspms());
		//domain.setWsspmsmc(dom.getWsspmsmc());
		domain.setRtnCode(domain.getRtnCode());
	}
	
	//��ʼ���״η�����Ϣ
	public void initScSendXx(WsspCommonDomain domain,UserDomain userDomain) throws Exception {
		WsspCommonDomain dom=dao.queryScFsSelect(domain, userDomain);
		if(null==dom)
			return;
		domain.setSpjdsm(dom.getSpjdsm());
		domain.setSpjgjgDm(dom.getSpjgjgDm());
		domain.setSpjgjgmc(dom.getSpjgjgmc());
		domain.setSpJgbm(dom.getSpJgbm());
		domain.setSpjgmc(dom.getSpjgmc());
		domain.setGwDjxh(dom.getGwDjxh());
		domain.setGwmc(dom.getGwmc());
		domain.setSprJdxh(dom.getSprJdxh());
		domain.setWsspms(dom.getWsspms());
		domain.setWsspmsmc(dom.getWsspmsmc());
		
		List<DmbGgDomain> sprList=new ArrayList<DmbGgDomain>();
		
		initSprList(userDomain, dom, sprList);
		
		domain.setSprList(sprList);
	}

	protected void initSprList(UserDomain userDomain, WsspCommonDomain dom, List<DmbGgDomain> sprList) throws Exception {
		DmbGgDomain obj=new DmbGgDomain();
		obj.setDm("");
		obj.setMc("--��ѡ��--");
		sprList.add(obj);
		//������ѡ��������
		if("1".equals(dom.getWsspms())){
			List<DmbGgDomain> dataList =null;
			//�������µ���
			if("1".equals(dom.getSpjgjgDm()))
				dataList=dao.querySprListBySpJgbm(userDomain.bmbm);
			//����λ�µ���
			if("2".equals(dom.getSpjgjgDm()))
				dataList=dao.queryWssprByBdwList(userDomain.gsbm);
			//�ܹ�˾�µ���
			if("3".equals(dom.getSpjgjgDm()))
				dataList=dao.queryWssprByZgsList(userDomain.zgsbm);
			if (dataList != null) {
				sprList.addAll(dataList);
			}
		}
		//����λѡ��������
		if("2".equals(dom.getWsspms())){
			List<DmbGgDomain> dataList=dao.querySprListByGwDjxh(dom.getGwDjxh());
			if (dataList != null) {
				sprList.addAll(dataList);
			}
		}
		//3=����λ��������	,ֱ�ӷ���
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void scSend(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		//�����״η���
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.saveScSend(domain, userDomain);
	}
	//��ȡ���� ���������������
	public String queryWssplcszxh(WsspCommonDomain domain,UserDomain user) throws Exception{
		return dao.queryWssplcszxh(domain, user);
	}
	
}
