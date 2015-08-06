package com.cy.cwgl.service.imp;



import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.cwgl.dao.CwKhysglDao;
import com.cy.cwgl.domain.CwKhysglDomain;
import com.cy.cwgl.domain.CwKhysglMxDomain;
import com.cy.cwgl.service.CwKhysglService;


@Service
/**
 * The SERVICEIMP for 财务-客户预收收入.
 * 
 * @author HJH
 */
public class CwKhysglServiceImp extends BaseBusinessServiceImp implements CwKhysglService {
	@Autowired
	private CwKhysglDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getGsbm());
		dao.initDomainMx(domain);
		domain.setJbrCzyDjxh(userDomain.getCzyDjxh());
		
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		CwKhysglDomain domain = (CwKhysglDomain)baseBusinessDomain;
		dao.saveDomain(domain, userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}

	
	public void onLook(CwKhysglDomain domain) throws Exception {
		List<CwKhysglMxDomain> dataList=dao.getKhYsMx(domain);
		domain.setMxList(dataList);
	}
	
	@Override
	public void deleteMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		CwKhysglDomain domain=(CwKhysglDomain)baseBusinessDomain;
	    if(StringUtils.isNotBlank(domain.getJsonStr())){
	    	String []ary=domain.getJsonStr().split("=");
	    	String xhs="";
	    	String djxh="";
	    	for (int i = 0; i < ary.length; i++) {
				String[] str=ary[i].split(",");
				CwKhysglMxDomain mxDoamin=new CwKhysglMxDomain();
				mxDoamin.setMxXh(str[0]);
				mxDoamin.setSrDjxh(str[1]);
				mxDoamin.setJe(str[2]);
				int count=dao.checkYsYfDj(mxDoamin);
				if(count>0){
					throw new DiyServiceException("该客户预收已有收付登记信息，不能删除！若要删除，请先作废收付登记信息");
				}
				else{
					dao.deleteMx(mxDoamin);
				}
			
				djxh=str[1];
				xhs+=str[0]+"-";
			}
	    	domain.setXhs(xhs);
	    	String je=dao.getInItJe(djxh);
	    	domain.setJe(je);
	    }
	}

	
	public void checkZcFl(CwKhysglDomain domain, UserDomain user)
			throws Exception {
			domain.setSsJgbm(user.getGsbm());
			dao.checkZcFl(domain);
		
		
	}
}
