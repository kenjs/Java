package com.cy.hygl.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsh.StringUtil;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysToolsUtil;
import com.cy.common.bo.JsKpsqMx;
import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.dao.JsKpsqDao;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.domain.JsKpsqDomain;
import com.cy.hygl.service.JsKpsqService;

@Service
/**
 * The SERVICEIMP for 开票申请.
 * 
 * @author HJH
 */
public class JsKpsqServiceImp extends BaseBusinessServiceImp implements JsKpsqService {
	@Autowired
	private JsKpsqDao dao;
	
	@Autowired
	private WlglptCommonDao commonDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
		domain.setRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
		initFydjSfsp(domain, userDomain);
	}
	
	public void initFydjSfsp(JsKpsqDomain domain, UserDomain userDomain) throws Exception {
		String xtcsSfsp = commonDao.getFunXtXtcs("20210", userDomain.getGsbm(), "2");
		domain.setXtcsSfsp(xtcsSfsp);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain)baseBusinessDomain;
		if(StringUtils.isNotBlank(domain.getKpsqDjxh())){
			JsKpsqDomain dom = (JsKpsqDomain) dao.getDomainByKey(domain);
			if(dom!=null){
				domain.setKpsqfsDm(dom.getKpsqfsDm());
			}
		}
		//dao.initDomainMx(domain);
		//预开票
		if("2".equals(domain.getKpsqfsDm())){
			if(SysToolsUtil.isNullOrEmpty(domain.getKpsqDjxh())){
				initJbxx(userDomain, domain);
			}else{
				//获取基本信息根据kpsqDjxh
				dao.initDomainMx(domain);
			}
		}
		else if("1".equals(domain.getKpsqfsDm())){//对账开票
			if(SysToolsUtil.isNullOrEmpty(domain.getKpsqDjxh())){
				initJbxx(userDomain, domain);
			}else{
				JsKpsqDomain dom=(JsKpsqDomain)dao.getDomainByKey(domain);
				List<BaseBusinessDomain> dataList=null;
				if(null==dom){
					domain.setSqKpjeHj(Double.valueOf(0));//重新计算
					//检索当前维护的开票申请对帐清单列表list（临时表取数）
					dataList=dao.queryJsKpsqMxTempList(domain, userDomain);
					
					this.doCalculate(domain, dataList);
				}else{
					//获取基本信息根据kpsqDjxh
					dao.initDomainMx(domain);
					domain.setSqKpjeHj(Double.valueOf(0));//金额重新计算
					//检索当前维护的开票申请对帐清单列表list
					dataList=dao.queryJsKpsqMxList(domain, userDomain);
					
					this.doCalculate(domain, dataList);
					
					domain.setExistBz("Y");
				}
				domain.setDataList(dataList);
			}
		}
		if("3".equals(domain.getKpsqfsDm())){
			if(SysToolsUtil.isNullOrEmpty(domain.getKpsqDjxh())){
				initJbxx(userDomain, domain);
			}
			else{
				JsKpsqDomain dom=(JsKpsqDomain)dao.getDomainByKey(domain);
				List<JsKpsqDomain> dataList=null;
				if(dom!=null){
					BeanUtils.copyProperties(domain, dom);
				    dataList=(List<JsKpsqDomain>)dao.querySrKpsqMxList(domain,1);
				}
				else{
					 dataList=(List<JsKpsqDomain>)dao.querySrKpsqMxList(domain,2);
				}
				domain.setSrKpList(dataList); 
			}
		}
		
		
	}
	//初始化基本信息
	protected void initJbxx(UserDomain userDomain, JsKpsqDomain domain) throws Exception {
		domain.setExistBz("N");
		//初始化基本信息
		domain.setDwMc(SysEncodeUtil.UTF82GBK(domain.getDwMc()));
		domain.setBmMc(userDomain.bmjc);
		domain.setDjJgbm(userDomain.bmbm);
		domain.setKhMc(SysEncodeUtil.UTF82GBK(domain.getKhMc()));
		domain.setCjrMc(userDomain.czyMc);
		domain.setDjrq(SysDateUtil.getCurrentDate());
		domain.setSqKprq(SysDateUtil.getCurrentDate());
		//取得kpsqDjxh
		String kpsqDjxh = commonDao.selectSequence("SEQ_KPSQ_DJXH");
		domain.setKpsqDjxh(kpsqDjxh);
	}

	//计算申请开票金额合计
	protected void doCalculate(JsKpsqDomain domain, List<BaseBusinessDomain> dataList) {
		
		if(null==dataList || dataList.isEmpty())
			return;
		for (BaseBusinessDomain base : dataList) {
			JsKpsqDomain kp=(JsKpsqDomain)base;
			if(null==domain.getSqKpjeHj())
				domain.setSqKpjeHj(kp.getSqKpJe());
			else{
				if(null!=kp.getSqKpJe())
					domain.setSqKpjeHj(domain.getSqKpjeHj()+kp.getSqKpJe());
			}
		}
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain = (JsKpsqDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
		if("1".equals(domain.getKpsqfsDm())) {//非预开票
			//对账开票
			if("Y".equals(domain.getExistBz())){
				//更新结算-收入对帐-清单
				
			}else{
				//把临时表数据insert到正式表中
				dao.insertJsKpsqMxByTemp(domain);
				//删除临时表数据
				dao.deleteJsKpsqMxTemp(domain);
				//更新结算-收入对帐-清单			
			}
		}
		if("3".equals(domain.getKpsqfsDm())){
			List<String> je=domain.getJeStr();
			List<String> ywDjxh=domain.getYwDjxhStr();
			int i=0;
			for (String string : ywDjxh) {
				JsKpsqMx mxBo=new JsKpsqMx();
				mxBo.setKpsqDjxh(domain.getKpsqDjxh());
				mxBo.setKpsqMxflDm("3");
				mxBo.setYwDjxh(ywDjxh.get(i));
				mxBo.setSqKpje(Double.valueOf(je.get(i)));
				mxBo.setYxbz("Y");
				mxBo.setCzrq(SysDateUtil.getCurrentDate());
				int count=dao.checkSrSpMx(mxBo);
				//如果等于0，表示数据库没有，做添加。
				if(count==0){
					dao.savaSrKpMx(mxBo);
				}
				i++;
			}
		}
		
		dao.callProKpsqHxcl(domain.getKpsqDjxh(), userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain=(JsKpsqDomain)baseBusinessDomain;
		//删除结算-开票申请
		dao.deleteByKey(domain, userDomain);
		//更新结算-收入对帐-清单JS_SRDZ_QD
		dao.callProKpsqHxcl(domain.getKpsqDjxh(), userDomain);
	}
	
	@Override
	protected void doMyDeleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain=(JsKpsqDomain)baseBusinessDomain;
		List<String> xhs=domain.getKpsqmxDjxhs();
		if(null==xhs|| xhs.isEmpty())
			return;
		if("Y".equals(domain.getExistBz())){
			//删除结算-开票申请-对帐清单
			for (String xh : xhs) {
				dao.deleteJsKpsqMxByKey(xh);
			}
			List<BaseBusinessDomain> dataList=null;
			//更新结算-开票申请
			domain.setSqKpjeHj(Double.valueOf(0));//金额重新计算
			//检索当前维护的开票申请对帐清单列表list
			dataList=dao.queryJsKpsqMxList(domain, userDomain);
			this.doCalculate(domain, dataList);
			dao.updateDomain(domain, userDomain);
			//更新结算-收入对帐-清单
			
		}else{
			//删除结算-开票申请-对帐清单临时表
			for (String xh : xhs) {
				dao.deleteJsKpsqMxTempByKey(xh);
			}
		}
	}

	@Override
	protected void doMyQueryMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain=(JsKpsqDomain)baseBusinessDomain;
		if("Y".equals(domain.getDzQdXgbz())){
			this.queryDzQdMx(domain, userDomain);
		}else{
			List<BaseBusinessDomain> dataList=dao.queryDzQdList(domain, userDomain);
			List<DmbGgDomain> qdList=new ArrayList<DmbGgDomain>();
			DmbGgDomain obj=null;
			obj=new DmbGgDomain();
			obj.setMc("--请选择--");
			qdList.add(obj);
			obj=null;
			for (BaseBusinessDomain base : dataList) {
				JsKpsqDomain dom=((JsKpsqDomain)base);
				obj=new DmbGgDomain();
				obj.setDm(dom.getQdDjxh()+"#"+dom.getHeJe()+"#"+dom.getYsqKpJe()+"#"+dom.getWsqKpJe());
				obj.setMc(dom.getQdmc());
				qdList.add(obj);
				obj=null;
			}
			domain.setQdList(qdList);
		}
		
	}

	@Override
	protected void doMySaveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain=(JsKpsqDomain)baseBusinessDomain;
		if("Y".equals(domain.getExistBz())){
			JsKpsqDomain dom=(JsKpsqDomain)dao.getJsKpsqMxDomainByXh(domain.getKpsqDjxh(), domain.getQdDjxh());
			if(null!=dom)
				domain.setKpsqmxDjxh(dom.getKpsqmxDjxh());
			// 保存结算-开票申请-对帐清单
			dao.saveJsKpsqMxDomain(domain, userDomain);
			//更新结算-开票申请（JS_KPSQ）
			//List<BaseBusinessDomain> dataList=null;
			//domain.setSqKpjeHj(Double.valueOf(0));//金额重新计算
			//检索当前维护的开票申请对帐清单列表list
			//dataList=dao.queryJsKpsqMxList(domain, userDomain);
			//this.doCalculate(domain, dataList);
			//dao.updateDomain(domain, userDomain);
			//更新结算-收入对帐-清单（JS_SRDZ_QD）
			dao.callProKpsqHxcl(domain.getKpsqDjxh(), userDomain);
		}else{//保存临时表
			// 保存结算-开票申请-对帐清单临时表
			JsKpsqDomain dom=(JsKpsqDomain)dao.getJsKpsqMxTempDomainByXh(domain.getKpsqDjxh(), domain.getQdDjxh());
			if(null!=dom)
				domain.setKpsqmxDjxh(dom.getKpsqmxDjxh());
			dao.saveJsKpsqMxTempDomain(domain, userDomain);
		}
	}
	
	public void queryDzQdMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		JsKpsqDomain domain=(JsKpsqDomain)baseBusinessDomain;
		JsKpsqDomain dom=null;
		String bz=domain.getExistBz();
		if("Y".equals(domain.getExistBz())){
			dom=(JsKpsqDomain)dao.getJsKpsqMxDomainByKey(domain);
		}else{
			dom=(JsKpsqDomain)dao.getJsKpsqMxTempDomainByKey(domain);
		}
		if(null==dom)
			return;
		BeanUtils.copyProperties(domain, dom);
		domain.setExistBz(bz);
		//domain.setDzQdXgbz("Y");
	}

	
	public void querySrKpMx(JsKpsqDomain domain) throws Exception {
		// TODO Auto-generated method stub
		List<JsKpsqDomain> dataList=(List<JsKpsqDomain>)dao.querySrKpMx(domain);
		domain.setSrKpList(dataList);
	}

	
	public void savaSrKpMxTemp(JsKpsqDomain domain) throws Exception {
		List<String> ywDjxh=domain.getYwDjxhStr();
		List<String> je=domain.getJeStr();
		int i=0;
		String str="";
		for (String string : ywDjxh) {
			JsKpsqMx mxBo=new JsKpsqMx();
			mxBo.setKpsqDjxh(domain.getKpsqDjxh());
			mxBo.setKpsqMxflDm("3");
			mxBo.setYwDjxh(ywDjxh.get(i));
			mxBo.setSqKpje(Double.valueOf(je.get(i)));
			mxBo.setYxbz("Y");
			mxBo.setCzrq(SysDateUtil.getCurrentDate());
			dao.savaSrKpMxTemp(mxBo);
			
			str+=ywDjxh.get(i)+",";
			i++;
		}
		domain.setYwDjxhs(str);
	}

	
	public void deleteSqKpTemp(JsKpsqDomain domain) throws Exception {
		if(StringUtils.isNotBlank(domain.getFlag())){
			//先取得开票登记序号
			String []str=domain.getFlag().split("=");
			String kpDjxh=str[1];
			//再取得添加到临时表中的ywDjxh
			String [] array=str[0].split(",");
			for (int i = 0; i < array.length; i++) {
				JsKpsqMx mxBo=new JsKpsqMx();
				mxBo.setYwDjxh(array[i]);
				mxBo.setKpsqDjxh(kpDjxh);
				dao.deleteSqKpTemp(mxBo);
			}
		}
		
	}

	
	public void deleteSrKpMx(JsKpsqDomain domain) throws Exception {
		if(domain.getYwDjxhStr()==null||domain.getYwDjxhStr().isEmpty()){
			return;
		}
		if(domain.getJeStr()==null||domain.getJeStr().isEmpty()){
			return;
		}
		List<String> ywDjxh=domain.getYwDjxhStr();
		List<String> je=domain.getJeStr();
		int i=0;
		double zje=0;
		for (String string : ywDjxh) {
			JsKpsqMx mxBo=new JsKpsqMx();
			mxBo.setYwDjxh(ywDjxh.get(i));
			mxBo.setKpsqDjxh(domain.getKpsqDjxh());
			int count=dao.checkSrSpMx(mxBo);
			//如果明细表里存在去删除明细表
			if(count>0){
				dao.deleteSrKpMx(mxBo);
				zje+=Double.valueOf(je.get(i));
			}
			//否则删除临时表
			else{
				dao.deleteSqKpTemp(mxBo);
			}
			i++;
		}
		//删除明细表的同时，去改变主表里的总金额
		dao.updateSrKpJeByDjxh(domain,zje);
	}
	
	
}
