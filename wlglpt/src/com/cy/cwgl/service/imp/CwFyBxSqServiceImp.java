package com.cy.cwgl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.cwgl.dao.CwFpKpdjDao;
import com.cy.cwgl.dao.CwFyBxSqDao;
import com.cy.cwgl.domain.CwFpKpdjDomain;
import com.cy.cwgl.domain.CwFyBxSqMxDomain;
import com.cy.cwgl.domain.CwFybxsqDomain;
import com.cy.cwgl.service.CwFpKpdjService;
import com.cy.cwgl.service.CwFyBxSqService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The SERVICE for 财务-开票登记
 * 
 * @author LYY
 */
@Service
public class CwFyBxSqServiceImp extends BaseBusinessServiceImp implements CwFyBxSqService{
	@Autowired
	private CwFyBxSqDao dao;
	
	@Autowired
	private WlglptCommonDao commonDao;
	
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		@SuppressWarnings("unused")
		CwFybxsqDomain domain = (CwFybxsqDomain)baseBusinessDomain;
		domain.setRqq(SysDateUtil.getCurrentDate());
		// 在此添加初始化相应代码
		this.initFydjSfsp(domain, userDomain);
	}

	public void initFydjSfsp(CwFybxsqDomain domain, UserDomain userDomain) throws Exception {
		String xtcsSfsp = commonDao.getFunXtXtcs("20211", userDomain.getGsbm(), "2");
		domain.setXtcsSfsp(xtcsSfsp);
	}
	
	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryAllList(domain,userDomain);
		
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain)baseBusinessDomain;
		dao.initDomainMx(domain);
		List<BaseBusinessDomain> dataList=dao.queryMxList(domain, userDomain);
		domain.setFyList(dataList);
		if(("0".equals(domain.getWsspztDm()) || "2".equals(domain.getWsspztDm())) && "Y".equals(domain.getSpbz())){
			domain.setSendBz(true);
		}
	}
	
	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getCzyDjxh());
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}
	
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFybxsqDomain domain = (CwFybxsqDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		dao.saveDomain(domain, userDomain);
		List<String> xhs=domain.getMxXh();
		List<String>  fylb=domain.getFylbDjxh();
		List<String>  fyxm=domain.getFyxmDjxh();
		List<String>  fyje=domain.getFyje();
		List<String>  bxje=domain.getBxje();
		List<String>  mxbz=domain.getFybz();
		int i=0;
		for (String fylbValue : fylb) {
			CwFyBxSqMxDomain mxDomain=new CwFyBxSqMxDomain();
			mxDomain.setCwDjxh(domain.getCwDjxh());
			mxDomain.setFylbDjxh(fylbValue);
			mxDomain.setFyxmDjxh(fyxm.get(i));
			mxDomain.setFyJe(fyje.get(i));
			mxDomain.setBxJe(bxje.get(i));
			mxDomain.setBz(mxbz.get(i));
			//修改
			if(xhs.get(i)!=null&&!xhs.get(i).equals("")){
				mxDomain.setMxDjxh(xhs.get(i));
				dao.updateMx(mxDomain,userDomain,domain);
			}
			//添加
			else{
				dao.saveMx(mxDomain,userDomain,domain);
			}
			i++;
		}
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwFybxsqDomain domain=(CwFybxsqDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}
	
	@Override
	public void deleteMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		CwFybxsqDomain domain=(CwFybxsqDomain)baseBusinessDomain;
		
		if(StringUtils.isNotBlank(domain.getJsonStr())){
			String ary[]=domain.getJsonStr().split("-");
			for (int i = 0; i < ary.length; i++) {
				CwFyBxSqMxDomain mxDomain=new CwFyBxSqMxDomain();
				String str[]=ary[i].split(",");
				mxDomain.setCwDjxh(str[1]);
				mxDomain.setMxDjxh(str[0]);
				mxDomain.setYxbz(str[2]);
				dao.deleteMx(mxDomain,userDomain,domain);
			}
			domain.setXhs(domain.getJsonStr());
			//domain.setCwDjxh(ary[0].split(",")[1]);
			//List<BaseBusinessDomain> dataList=dao.queryMxList(domain, userDomain);
			//domain.setFyList(dataList);
		}
		
	}


	public void checkXmfl(CwFybxsqDomain domain) throws Exception {
		boolean tag=false;
		if(domain.getJsonStr()!=null&&!domain.getJsonStr().equals("")){
			String[] ary=domain.getJsonStr().split(",");
			String str="";
			for (int i = 0; i < ary.length; i++) {
				CwFyBxSqMxDomain mx=new CwFyBxSqMxDomain();
				mx.setCwDjxh(ary[i]);
				String djxh=dao.checkXmfl(mx);
				str+=djxh+",";
			}
			if(!str.equals("")){
				domain.setTager("1");
				String [] xmDjxh=str.split(",");
				for (int i = 0; i < xmDjxh.length; i++) {
					for (int j = i; j < xmDjxh.length; j++) {
						if(!xmDjxh[i].equals(xmDjxh[j])){
							domain.setTager("2");
							tag=true;
							break;
						}
					}
					if(tag){
						break;
					}
				}
				//如果审批流程DJXH是一样的话，取数组的第一个就好了
				if(domain.getTager().equals("1")){
					domain.setXmflDjxh(xmDjxh[0]);
				}
			}
			
		}
		
		
		
	}

	
	public void onView(CwFybxsqDomain domain,UserDomain userDomain) throws Exception {
		this.initMx(domain, userDomain);
		String jzMc=dao.getMcByJgbm(domain.getFyjzDwDjxh());
		String jfMc=dao.getMcByJgbm(domain.getFyzfDwDjxh());
		domain.setJfdw(SysEncodeUtil.ISO2GBK(jfMc));
		domain.setJzdw(SysEncodeUtil.ISO2GBK(jzMc));
	}
}
