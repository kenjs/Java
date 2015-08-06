package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.hygl.dao.HyPcHddjDao;
import com.cy.hygl.dao.HyPcHwxxDao;
import com.cy.hygl.domain.HyPcHddjDomain;
import com.cy.hygl.domain.HyPcHwxxDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.service.HyPcHddjService;
import com.cy.zyegl.dao.HyPcXydjDao;

@Service
/**
 * The SERVICEIMP for 货运-派车-回单.
 * 
 * @author HJH
 */
public class HyPcHddjServiceImp extends BaseBusinessServiceImp implements HyPcHddjService {
	@Autowired
	private HyPcHddjDao dao;
	@Autowired
	private HyPcHwxxDao pcHwxxDao;
	@Autowired
	private WlglptCommonDao commonDao;
	@Autowired
	private HyPcXydjDao xyDao;
	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain)baseBusinessDomain;
		domain.setSsJgbm(userDomain.getGsbm());
		// 在此添加初始化相应代码
		if(userDomain.getCs_20052().equals("Y")){
			domain.setPcJgbm(userDomain.getBmbm());
		}
		
		initHddjSfsp(domain, userDomain);
	}
	
	public void initHddjSfsp(HyPcHddjDomain domain, UserDomain userDomain) throws Exception {
		String xtcsSfsp = commonDao.getFunXtXtcs("20207", userDomain.getGsbm(), "2");
		String xtcs20016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		domain.setXtcsSfsp(xtcsSfsp);
		domain.setXtcs20016(xtcs20016);
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain)baseBusinessDomain;
		domain.setHdjsrq(SysDateUtil.getSqlDate());
		HyPcHddjDomain dom = (HyPcHddjDomain)dao.getDomainByKey(domain);
		HyPcHwxxDomain pcHwDomain = dao.queryJsPcHwxxByPcWfhXh(domain.getPcDjxh(), domain.getWfhDjxh());
		HyPcHddjDomain wlssDomain = dao.getWlss(domain.getPcDjxh(), domain.getWfhDjxh());
		HyPcHddjDomain pcDomain = dao.getZHwSl(domain.getPcDjxh(), domain.getWfhDjxh());
		if (dom == null) {
			domain.setDdDjxh(pcHwDomain.getDdDjxh());
			domain.setXh(pcHwDomain.getXh());
			domain.setHdbh(pcHwDomain.getHdbh());
			if (StringUtils.isNotBlank(pcHwDomain.getYqDdrq())) {
				domain.setYqDdrq(SysDateUtil.parse(pcHwDomain.getYqDdrq()));
			}
			domain.setShfsDm(pcHwDomain.getShfsDm());
			domain.setSzHwSl(pcHwDomain.getHwSl());
			domain.setSzHwTj(pcHwDomain.getHwTj());
			domain.setSzHwZl(pcHwDomain.getHwZl());
			domain.setSzJsSl(pcHwDomain.getJsSl());
		}else {
			BeanUtils.copyProperties(domain, dom);
		}
		
		if(wlssDomain!=null){
			if(StringUtils.isNotBlank(wlssDomain.getWlssHwSl())){
				domain.setWlssHwSl(wlssDomain.getWlssHwSl());
			}else {
				domain.setWlssHwSl("0");
			}
			if(StringUtils.isNotBlank(wlssDomain.getWlssHwZl())){
				domain.setWlssHwZl(wlssDomain.getWlssHwZl());
			}else {
				domain.setWlssHwZl("0");
			}
			if(StringUtils.isNotBlank(wlssDomain.getWlssHwTj())){
				domain.setWlssHwTj(wlssDomain.getWlssHwTj());
			}else {
				domain.setWlssHwTj("0");
			}
			domain.setWlssDjxh(wlssDomain.getWlssDjxh());
		}
		else{
			domain.setWlssHwSl("0");
			domain.setWlssHwZl("0");
			domain.setWlssHwTj("0");
		}
		if(pcDomain!=null){
			if(StringUtils.isNotBlank(pcDomain.getZhwSl())){
				domain.setZhwSl(pcDomain.getZhwSl());
			}
			if(StringUtils.isNotBlank(pcDomain.getZhwZl())){
				domain.setZhwZl(pcDomain.getZhwZl());
			}
			if(StringUtils.isNotBlank(pcDomain.getZhwTj())){
				domain.setZhwTj(pcDomain.getZhwTj());
			}
		}
		
		
		domain.setPcHwDomain(pcHwDomain);
		
	}
	
	public void initViewMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain)baseBusinessDomain;
		HyPcHddjDomain dom = (HyPcHddjDomain)dao.getDomainByKey(domain);
		BeanUtils.copyProperties(domain, dom);
		HyPcHwxxDomain pcHwDomain = dao.queryJsPcHwxxByPcWfhXh(dom.getPcDjxh(), dom.getWfhDjxh());
		domain.setPcHwDomain(pcHwDomain);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain)baseBusinessDomain;
		
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain)baseBusinessDomain;
		doMyQuery(domain, userDomain);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain = (HyPcHddjDomain)baseBusinessDomain;
		String xtcs20016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			if("Y".equals(xtcs20016)){
				xyDao.checkXydj(domain.getPcDjxh());
			}
		}
		dao.saveDomain(domain, userDomain);
		dao.callProHddjHxcl(domain.getHdDjxh(), userDomain);
	}

	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain=(HyPcHddjDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		dao.callProHddjHxcl(domain.getHdDjxh(), userDomain);
	}

	public void plSave(BaseBusinessDomain baseBusinessDomain) throws Exception {
		HyPcHddjDomain domain=(HyPcHddjDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		/*if(domain.getStr() != null && !"".equals(domain.getStr().trim())){
			String[] strObj = domain.getStr().split("\\|");
			if(strObj.length > 0){
				for(int i = 0;i < strObj.length;i ++) {
					String[] arr = strObj[i].split(",");
					domain.setPcDjxh(arr[0]);
					domain.setHdbh(arr[1]);
					domain.setWfhDjxh(arr[2]);
					dao.plSave(domain);
				}
			}
		}*/
	}

	public void plJs(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain=(HyPcHddjDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		String[] hdxhArr = domain.getPljsStr().split("\\|");
		if(hdxhArr.length < 1){
			return;
		}
		for (int i = 0; i < hdxhArr.length; i++) {
			String[] xhs = hdxhArr[i].split("#");
			String hdDjxh = dao.plJs(xhs[0], xhs[1]);
			dao.callProHddjHxcl(hdDjxh, userDomain);
		}
	}

	
	public void saveWlssDj(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyPcHddjDomain domain=(HyPcHddjDomain)baseBusinessDomain;;
		dao.saveWlssDj(domain,userDomain);
		
	}
}
