package com.cy.zygl.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.dao.QyYlClxxShclDao;
import com.cy.zygl.domain.QyYlClxxDomain;
import com.cy.zygl.service.QyYlClxxShclService;
@Service
/**
 * The SERVICEIMP for 企业-运力-车辆信息.
 * 
 * @author Haoy
 */
public class QyYlClxxShclServiceImp extends BaseBusinessServiceImp implements QyYlClxxShclService {
	@Autowired
	private QyYlClxxShclDao dao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
//		QyYlClxxDomain domain = (QyYlClxxDomain)baseBusinessDomain;
		// 在此添加初始化相应代码
	}

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain)baseBusinessDomain;
		if (StringUtils.isBlank(domain.getClDjxh())){
			domain.setClhm(SysEncodeUtil.ISO2GBK(domain.getClhm()));
			if("1".equals(domain.getPcfsDm())){
				domain.setThclbz("Y");
			}else if("2".equals(domain.getPcfsDm())){
				domain.setYsclbz("Y");
			}else if("3".equals(domain.getPcfsDm())){
				domain.setPsclbz("Y");
			}
		}
		dao.initDomainMx(domain);
		List<QyYlClxxDomain> sjList = dao.querySj(domain.getClDjxh());
		domain.setSjList(sjList);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain)baseBusinessDomain;
		if(domain != null && !"".equals(domain.getCzXm())){
			domain.setCzXm(SysEncodeUtil.UTF82ISO(domain.getCzXm()));
		}
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		List<QyYlClxxDomain> sjList = new ArrayList<QyYlClxxDomain>();
		for (BaseBusinessDomain e : dataList) {
			String sjXms = "";
			QyYlClxxDomain e1 = (QyYlClxxDomain)e;
			//根据车辆登记序号检索司机
			sjList = dao.querySj(e1.getClDjxh());
			if(sjList.size() > 0){
				for (QyYlClxxDomain el : sjList) {
					sjXms += el.getSjXm() + ",";
				}
			}
			if(sjXms.endsWith(",")){
				sjXms = sjXms.substring(0,sjXms.length() - 1);
			}
			e1.setSjXm(sjXms);
		}
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain)baseBusinessDomain;
		if(domain != null && !"".equals(domain.getCzXm())){
			domain.setCzXm(SysEncodeUtil.UTF82ISO(domain.getCzXm()));
		}
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		List<QyYlClxxDomain> sjList = new ArrayList<QyYlClxxDomain>();
		for (BaseBusinessDomain e : dataList) {
			String sjXms = "";
			QyYlClxxDomain e1 = (QyYlClxxDomain)e;
			//根据车辆登记序号检索司机
			sjList = dao.querySj(e1.getClDjxh());
			if(sjList.size() > 0){
				for (QyYlClxxDomain el : sjList) {
					sjXms += el.getSjXm() + ",";
				}
			}
			if(sjXms.endsWith(",")){
				sjXms = sjXms.substring(0,sjXms.length() - 1);
			}
			e1.setSjXm(sjXms);
		}
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain)baseBusinessDomain;
		if(StringUtils.isBlank(domain.getSjXm()) && StringUtils.isBlank(domain.getSjZjhm())){
			if(dao.checkClhm(domain) > 0){
				ServiceException se = new ServiceException();
				se.setErrorCode("121201");
				se.setErrorMess("车辆号码重复");
				throw se;
			}
		}		
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain)baseBusinessDomain;
		SysEncodeUtil.decodeURL(domain);
		
		dao.saveCl(domain, userDomain);
		
		List<String> xhs=domain.getXhs();
		List<String> sjXms = domain.getSjXms();
		List<String> jszhms = domain.getJszhms();
		List<String> sjZjhms = domain.getSjZjhms();
		List<String> sjSJhms = domain.getSjSjhms();
		List<String> sjLxdhs = domain.getSjLxdhs();
		
		if(null==xhs|| xhs.isEmpty()){
			return;
		}
		int i = 0;
		for (String xh : xhs) {
			QyYlClxxDomain dom = new QyYlClxxDomain();
			dom.setClDjxh(domain.getClDjxh());
			dom.setXh(xh);
			dom.setSjXm(sjXms.get(i));
			dom.setSjZjhm(sjZjhms.get(i));
			dom.setJszhm(jszhms.get(i));
			dom.setSjLxdh(sjLxdhs.get(i));
			dom.setSjSjhm(sjSJhms.get(i));
			dao.saveSj(dom);
			i++;
		}	
	}
		
	
	@Override
	protected void doMySaveAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain)baseBusinessDomain;
		if(StringUtils.isBlank(domain.getClDjxh())){
			domain.setClDjxh(dao.getCurrentXh(domain));
		}
		
	}
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		QyYlClxxDomain domain=(QyYlClxxDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
	}

	public void deleteSjxx(BaseBusinessDomain baseBusinessDomain) throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain) baseBusinessDomain;
		List<String> xhs=domain.getXhs();
		String clDjxh = domain.getClDjxh();
		if(null==xhs|| xhs.isEmpty())
			return;
		
			//删除结算-开票申请-对帐清单
			for (String xh : xhs) {
				dao.deleteSj(xh, clDjxh);
			}
		
	}

	public void doMyInitSjMx(BaseBusinessDomain baseBusinessDomain)throws Exception {
		QyYlClxxDomain domain = (QyYlClxxDomain) baseBusinessDomain;
		if(StringUtils.isNotBlank(domain.getClDjxh()) && StringUtils.isNotBlank(domain.getXh())){
			QyYlClxxDomain dom = dao.getSjxx(domain.getClDjxh(), domain.getXh());
			if(dom != null){
				BeanUtils.copyProperties(domain, dom);
			}	
		}			
	}
}

