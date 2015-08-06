package com.cy.hygl.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysToolsUtil;
import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.dao.CwYsyfSrdjDao;
import com.cy.hygl.dao.JsKpsqDao;
import com.cy.hygl.dao.JsSrdzQdDao;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.domain.JsSrdzQdDomain;
import com.cy.hygl.service.JsSrdzQdService;

@Service
/**
 * The SERVICEIMP for 结算-收入对帐-清单.
 * 
 * @author HJH
 */
public class JsSrdzQdServiceImp extends BaseBusinessServiceImp implements JsSrdzQdService {
	@Autowired
	private JsSrdzQdDao dao;
	@Autowired
	private WlglptCommonDao commonDao;
	@Autowired
	private CwYsyfSrdjDao ysyfDao;
	@Autowired
	JsKpsqDao jsKpsqDao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
	}
	
	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		
		if(SysToolsUtil.isNullOrEmpty(domain.getQdDjxh())){
			domain.setExistBz("N");
			//初始化基本信息
			domain.setDwMc(SysEncodeUtil.UTF82GBK(domain.getDwMc()));
			domain.setBmMc(userDomain.bmjc);
			domain.setDjJgbm(userDomain.bmbm);
			domain.setKhMc(SysEncodeUtil.UTF82GBK(domain.getKhMc()));
			domain.setCjrMc(userDomain.czyMc);
			domain.setDjrq(SysDateUtil.getCurrentDate());
			//取得qdDjxh
			String qdDjxh = commonDao.selectSequence("SEQ_QD_DJXH");
			domain.setQdDjxh(qdDjxh);
		}else{
			JsSrdzQdDomain dom=(JsSrdzQdDomain) dao.getDomainByKey(domain);
			if(null==dom){
				//检索当前维护的清单明细列表list（临时表取数）
				List<BaseBusinessDomain> dataList=dao.queryDzQdMxTemp(domain, userDomain);
				domain.setDataList(dataList);
				
				//初始化基本信息
				domain.setHeJe(dao.calQdHjJe(domain.getQdDjxh()));
			}else{
				//获取基本信息根据qdDjxh
				dao.initDomainMx(domain);
				//检索当前维护的清单明细列表list
				List<BaseBusinessDomain> dataList=dao.queryDzQdMx(domain, userDomain);
				domain.setDataList(dataList);
				domain.setExistBz("Y");
			}
		}
		int res = dao.checkQdDel(domain.getQdDjxh());
		int checkRes = dao.checkQDCwInfo(domain.getQdDjxh());
		
		domain.setErrCode1(String.valueOf(res));
		domain.setErrCode2(String.valueOf(checkRes));
		String xtcs20212 = commonDao.getFunXtXtcs("20212", userDomain.getGsbm(), "2");
		domain.setXtcs20212(xtcs20212);
	}
	//计算合计金额和组装数据，便于合并行
	protected void doCalculate(JsSrdzQdDomain domain, Map<String, List<JsSrdzQdDomain>> map, List<BaseBusinessDomain> dataList) {
		List<JsSrdzQdDomain> itemList=null;
		if(null==dataList || dataList.isEmpty())
			return;
		for (BaseBusinessDomain base : dataList) {
			JsSrdzQdDomain qd=(JsSrdzQdDomain)base;
			
			if(map.containsKey(qd.getDdbh())){
				map.get(qd.getDdbh()).add(qd);
			}else{
				itemList=new ArrayList<JsSrdzQdDomain>();
				itemList.add(qd);
				map.put(qd.getDdbh(), itemList);
				
				if(null==domain.getHeJe())
					domain.setHeJe(qd.getJsWj());
				else{
					if(null!=qd.getJsWj())
						domain.setHeJe(domain.getHeJe()+qd.getJsWj());
				}
			}
		}
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		
		//检索当前维护的清单明细列表list
		List<BaseBusinessDomain> dataList=dao.queryDzQdMx(domain, userDomain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain=(JsSrdzQdDomain)baseBusinessDomain;
		int res = dao.checkQdDel(domain.getQdDjxh());
		if(res > 0){
			ServiceException se = new ServiceException();
			se.setErrorCode("200002");
			se.setErrorMess("该清单已经开票申请，不能修改。");
			throw se;
		}
		
		int checkRes = dao.checkQDCwInfo(domain.getQdDjxh());
		if(checkRes > 0){
			ServiceException se = new ServiceException();
			se.setErrorCode("200003");
			se.setErrorMess("该清单已经支付登记，不能修改。");
			throw se;
		}
		
	}
	
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		//修改时，删除已存在的财务信息
		if(StringUtils.isNotBlank(domain.getQdDjxh())){
			dao.deleCwInfo(domain.getQdDjxh());
		}
		
		dao.saveDomain(domain, userDomain);
		
		//
		if("Y".equals(domain.getExistBz())){
			// 更新结算-收入对帐-订单中清单登记序号
			dao.updateJsSrDzDd(domain.getQdDjxh());
			
			//取物清单中对应流损失登记序号
			List<JsSrdzQdDomain> list = dao.selectSrdzQdMxWhenWlss(domain);
			for(JsSrdzQdDomain dom:list){
				ysyfDao.deleteYsyfXx(dom.getYwDjxh());
			}
		}else{//把临时表数据insert到正式表中
			dao.saveQdMxDomainByTemp(domain.getQdDjxh());
			//删除临时表数据
			dao.deleteDzQdMxTempByQdDjxh(domain.getQdDjxh());
			
			// 更新结算-收入对帐-订单中清单登记序号
			dao.updateJsSrDzDd(domain.getQdDjxh());
			
			
			//取物清单中对应流损失登记序号
			List<JsSrdzQdDomain> list = dao.selectSrdzQdMxWhenWlss(domain);
			for(JsSrdzQdDomain dom:list){
				ysyfDao.deleteYsyfXx(dom.getYwDjxh());
			}
		}
		
		String kpsqDjxh = commonDao.selectSequence("SEQ_KPSQ_DJXH");
		domain.setKpsqDjxh(kpsqDjxh);
		
		//选择不开票时，生成财务信息
		if("N".equals(domain.getSfKpBz())){			
			dao.cwYsfSrdz(domain);
		}
		
		//开票不申请时，生成开票信息
		
		if("Y".equals(domain.getSfKpBz()) && "N".equals(domain.getXtcs20212())){
			dao.saveKp(domain, userDomain);
			jsKpsqDao.callProKpsqHxcl(kpsqDjxh, userDomain);
		}
		
		domain.setExistBz("Y");//置已存在标志
	}

	@Override
	protected void doMyDeleteBefore(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain=(JsSrdzQdDomain)baseBusinessDomain;
		int res = dao.checkQdDel(domain.getQdDjxh());
		if(res > 0){
			ServiceException se = new ServiceException();
			se.setErrorCode("200002");
			se.setErrorMess("该清单已经开票申请，不能删除。");
			throw se;
		}
		
		int checkRes = dao.checkQDCwInfo(domain.getQdDjxh());
		if(checkRes > 0){
			ServiceException se = new ServiceException();
			se.setErrorCode("200003");
			se.setErrorMess("该清单已经支付登记，不能删除。");
			throw se;
		}
	}
	
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain=(JsSrdzQdDomain)baseBusinessDomain;
		List<JsSrdzQdDomain> list = dao.selectSrdzQdMxWhenWlss(domain);
		for(JsSrdzQdDomain dom:list){
			ysyfDao.updateYsyfXx(dom.getYwDjxh());
		}
		dao.deleteByKey(domain, userDomain);
		//删除已存在的财务信息
		if(StringUtils.isNotBlank(domain.getQdDjxh())){
			dao.deleCwInfo(domain.getQdDjxh());
		}
		updateJsSrdzQdxhToNullByQdDjxh(domain.getQdDjxh());
	}
	
	private void updateJsSrdzQdxhToNullByQdDjxh(String qdDjxh) throws Exception {
		dao.updateJsSrdzQdxhToNullByQdDjxh(qdDjxh);
	}

	@Override
	protected void doMyDeleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		List<String> xhs=domain.getYwDjxhs();
		if(null==xhs|| xhs.isEmpty())
			return;
		if("Y".equals(domain.getExistBz())){
			for (String xh : xhs) {
				String[] xhSplit = xh.split(",");
				dao.deleteDzQdMx(domain.getQdDjxh(),xhSplit[0], xhSplit[1], userDomain);
				// 若明细序号为0，则为收入对账记录，更新收入对账对应记录的清单序号为空。
				if ("0".equals(xhSplit[1])) {
					dao.deleteDzQdMxUpdateSrDzDdOfIsNull(xhSplit[1]);
				}
				
				ysyfDao.updateYsyfXx(xhSplit[0]);
			}
			dao.updateQdHeJeByKey(domain.getQdDjxh());
		}else{
			for (String xh : xhs) {
				String[] xhSplit = xh.split(",");
				dao.deleteDzQdMxTemp(domain.getQdDjxh(),xhSplit[0], xhSplit[1], userDomain);
			}
		}
		
	}

	@Override
	protected void doMyQueryMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList =dao.queryYdz(domain, userDomain);
		//domain.setDataList(dataList);
		Map<String, List<JsSrdzQdDomain>> map=new HashMap<String, List<JsSrdzQdDomain>>();
		List<JsSrdzQdDomain> itemList=null;
		for (BaseBusinessDomain base : dataList) {
			JsSrdzQdDomain dom=(JsSrdzQdDomain)base;
			if(map.containsKey(dom.getDdbh())){
				map.get(dom.getDdbh()).add(dom);
			}else{
				itemList=new ArrayList<JsSrdzQdDomain>();
				itemList.add(dom);
				map.put(dom.getDdbh(), itemList);
			}
		}
		domain.setMap(map);
	}

	@Override
	protected void doMySaveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		List<String> xhs=domain.getYwDjxhs();
		if("Y".equals(domain.getExistBz())){
			for (String xh : xhs) {
				domain.setYwDjxh(xh);
				domain.setYwMxXh("0");
				domain.setYwLydm("1");
				dao.saveDzQdMxDomain(domain, userDomain);
			}
			dao.updateQdHeJeByKey(domain.getQdDjxh());
			//更新结算-收入对帐-订单中清单登记序号
			dao.updateJsSrDzDd(domain.getQdDjxh());
		}else{
			for (String xh : xhs) {
				domain.setYwDjxh(xh);
				domain.setYwMxXh("0");
				domain.setYwLydm("1");
				dao.saveDzQdMxTempDomain(domain, userDomain);
			}
		}
	}
	
	public void queryFydjList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		List<HyZyglFydjDomain> fydjList = dao.queryFydjList(domain, userDomain);
		domain.setFydjList(fydjList);
	}
	
	public void saveFydjMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		List<String> xhs=domain.getYwDjxhs();
		if("Y".equals(domain.getExistBz())){
			for (String xh : xhs) {
				String[] xhSplit = xh.split(",");
				domain.setYwDjxh(xhSplit[0]);
				domain.setYwMxXh(xhSplit[1]);
				domain.setYwLydm("2");
				dao.saveDzQdMxDomain(domain, userDomain);
			}
			dao.updateQdHeJeByKey(domain.getQdDjxh());
		}else{
			for (String xh : xhs) {
				String[] xhSplit = xh.split(",");
				domain.setYwDjxh(xhSplit[0]);
				domain.setYwMxXh(xhSplit[1]);
				domain.setYwLydm("2");
				dao.saveDzQdMxTempDomain(domain, userDomain);
			}
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveWlssMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		List<String> xhs=domain.getYwDjxhs();
		if("Y".equals(domain.getExistBz())){
			for (String xh : xhs) {
				String[] xhSplit = xh.split(",");
				domain.setYwDjxh(xhSplit[0]);
				domain.setYwMxXh(xhSplit[1]);
				domain.setYwLydm("3");
				dao.saveDzQdMxDomain(domain, userDomain);
				
				ysyfDao.deleteYsyfXx(xhSplit[0]);
			}
			dao.updateQdHeJeByKey(domain.getQdDjxh());
					
		}else{
			for (String xh : xhs) {
				String[] xhSplit = xh.split(",");
				domain.setYwDjxh(xhSplit[0]);
				domain.setYwMxXh(xhSplit[1]);
				domain.setYwLydm("3");
				dao.saveDzQdMxTempDomain(domain, userDomain);
			}
		}
	}
	
	public void viewDzQdMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsSrdzQdDomain domain = (JsSrdzQdDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		//检索当前维护的清单明细列表list
		List<BaseBusinessDomain> dataList=dao.queryDzQdMx(domain, userDomain);
		domain.setDataList(dataList);
		domain.setExistBz("Y");
		
		domain.setDzQdList(dataList);
	}
	
}
