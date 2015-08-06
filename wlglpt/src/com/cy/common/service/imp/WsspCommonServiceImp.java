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
		//查询企业-文书-审批-子表
		QyWsSpZbDomain wsspZbDomain=(QyWsSpZbDomain)wsspZbDao.getDomainByKey(new QyWsSpZbDomain(domain.getWsspxh(),domain.getSpxh()));
		
		if(null==wsspZbDomain){
			new DiyServiceException("未找到符合条件的待审批记录");
		}
		
		domain.setSpjg(wsspZbDomain.getSpjg());
		domain.setSpyj(wsspZbDomain.getSpyj());
		//文书终审判断
		dao.queryWszsJudge(domain, userDomain);
		if("N".equals(wsspZbDomain.getSpbz())){
			domain.setSaveBz(true);
			domain.setBackBz(true);
			if(!SysToolsUtil.isNullOrEmpty(wsspZbDomain.getSprCzyDjxh()) && !SysToolsUtil.isNullOrEmpty(wsspZbDomain.getSprq()) && !SysToolsUtil.isNullOrEmpty(wsspZbDomain.getSpjg())){
				if(domain.isJudgeBz()){
					domain.setSendBz(false);
				}else{
					domain.setSendBz(true);
					//初始化发送信息
					this.initSendXX(userDomain, domain);
				}
			}
		}else {
			// 终审后可退回
			if (domain.isJudgeBz()) {
				domain.setBackBz(true);
				// 将终审标志置回false
				domain.setJudgeBz(false);
			}
		}		
	}

	@Override
	protected void doMyInitAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		//取审批件url
		domain.setSpjUrl(dao.queryWsspjUrl(domain.getWsspxh()));
		//取流转信息list
		domain.setWssplzList(dao.querySplzcx(domain, userDomain));
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		//更新QY_WS_SP_ZB企业-文书-审批-子表
		this.updateWsspzbDomain(baseBusinessDomain, userDomain);
	}

	@Override
	protected void doMySaveAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		//文书终审判断
		dao.queryWszsJudge(domain, userDomain);
		domain.setBackBz(true);
		domain.setSaveBz(true);
		//允许终审时，没有发送
		if(domain.isJudgeBz()){
			domain.setSendBz(false);
		}else{
			domain.setSendBz(true);
			//初始化发送信息
			this.initSendXX(userDomain, domain);
		}
		//取审批件url
		domain.setSpjUrl(dao.queryWsspjUrl(domain.getWsspxh()));
		//取流转信息list
		domain.setWssplzList(dao.querySplzcx(domain, userDomain));
	}
	

	@Override
	protected void doMySaveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		//先更新QY_WS_SP_ZB企业-文书-审批-子表
		updateWsspzbDomain(baseBusinessDomain, userDomain);
		//调用终审
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.saveJudge(domain, userDomain);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveBack(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		//先更新QY_WS_SP_ZB企业-文书-审批-子表
		updateWsspzbDomain(baseBusinessDomain, userDomain);
		//调用退回
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.saveBack(domain, userDomain);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void send(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		//先更新QY_WS_SP_ZB企业-文书-审批-子表
		updateWsspzbDomain(baseBusinessDomain, userDomain);
		//调用发送
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.saveSend(domain, userDomain);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void plSend(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		//先更新QY_WS_SP_ZB企业-文书-审批-子表
		updateWsspzbDomain(baseBusinessDomain, userDomain);
		//文书终审判断
		dao.queryWszsJudge(domain, userDomain);
		if(domain.isJudgeBz()){
			//调用终审
			dao.saveJudge(domain, userDomain);
		}else{
			//调用发送
			dao.saveSend(domain, userDomain);
		}
	}
	//查询文书审批流转list
	public void queryWssplzList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		//调用文书审批流转
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.querySplzcx(domain, userDomain);
	}
	
	//保存时更新QY_WS_SP_ZB企业-文书-审批-子表
	protected void updateWsspzbDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		QyWsSpZbDomain spzb=new QyWsSpZbDomain();
		spzb.setWsSpxh(domain.getWsspxh());
		spzb.setSpxh(domain.getSpxh());
		spzb.setSpjg(domain.getSpjg());
		spzb.setSpyj(domain.getSpyj());
		//更新QY_WS_SP_ZB企业-文书-审批-子表
		wsspZbDao.updateDomain(spzb, userDomain);
	}
	
	//初始化发送信息
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
		domain.setNextWssp(true);//置需要查找下一审批
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
	
	//初始化首次发送信息
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
		obj.setMc("--请选择--");
		sprList.add(obj);
		//按部门选择审批人
		if("1".equals(dom.getWsspms())){
			List<DmbGgDomain> dataList =null;
			//本部门下的人
			if("1".equals(dom.getSpjgjgDm()))
				dataList=dao.querySprListBySpJgbm(userDomain.bmbm);
			//本单位下的人
			if("2".equals(dom.getSpjgjgDm()))
				dataList=dao.queryWssprByBdwList(userDomain.gsbm);
			//总公司下的人
			if("3".equals(dom.getSpjgjgDm()))
				dataList=dao.queryWssprByZgsList(userDomain.zgsbm);
			if (dataList != null) {
				sprList.addAll(dataList);
			}
		}
		//按岗位选择审批人
		if("2".equals(dom.getWsspms())){
			List<DmbGgDomain> dataList=dao.querySprListByGwDjxh(dom.getGwDjxh());
			if (dataList != null) {
				sprList.addAll(dataList);
			}
		}
		//3=按岗位发送审批	,直接发送
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void scSend(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception{
		//调用首次发送
		WsspCommonDomain domain=(WsspCommonDomain)baseBusinessDomain;
		dao.saveScSend(domain, userDomain);
	}
	//获取文书 审批流程设置序号
	public String queryWssplcszxh(WsspCommonDomain domain,UserDomain user) throws Exception{
		return dao.queryWssplcszxh(domain, user);
	}
	
}
