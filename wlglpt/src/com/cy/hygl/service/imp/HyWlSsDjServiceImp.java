package com.cy.hygl.service.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.HyWlssdjMx;
import com.cy.common.bo.HyWlssdjZp;
import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.dao.HyWlSsDjDao;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyWlssdjDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.HyWlSsDjService;
import com.cy.zyegl.dao.HyPcXydjDao;

@Service
/**
 * The SERVICEIMP for 物流损失登记
 * 
 * @author HJH
 */
public class HyWlSsDjServiceImp extends BaseBusinessServiceImp implements HyWlSsDjService {
	@Autowired
	private HyWlSsDjDao dao;
	@Autowired
	private WlglptCommonDao commonDao;
	@Autowired
	private HyPcXydjDao xyDao;

	@Override
	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		dao.initDomainMx(baseBusinessDomain);
		if ("Y".equals(domain.getSpbz()) && ("0".equals(domain.getWsspztDm()) || "2".equals(domain.getWsspztDm()))) {
			domain.setSendBz(true);
		}
		domain.setZgsbm(userDomain.getZgsbm());
		List<HyZyglFydjDomain> sjList;
		if(StringUtils.isBlank(domain.getDdDjxh())||StringUtils.isBlank(domain.getXh())){
			HyWlssdjDomain dom = (HyWlssdjDomain) dao.getDomainByKey(domain);
			sjList = dao.selectSjList(dom.getDdDjxh(),dom.getHwmxxh());
		}else{
			sjList= dao.selectSjList(domain.getDdDjxh(), domain.getXh());
		}
		domain.setSjList(sjList);
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		String xtcs20016 = commonDao.getFunXtXtcs("20016", userDomain.getGsbm(), "2");
		if(StringUtils.isNotBlank(domain.getPcDjxh())){
			if("Y".equals(xtcs20016)){
				xyDao.checkXydj(domain.getPcDjxh());
			}
		}
		dao.saveDomain(domain, userDomain);
		if(!("Y".equals(domain.getSpbcbz()))){
			dao.wlssDjHxcl(domain, userDomain);
		}
		
		List<String> xhs=domain.getXhs();
		List<String> wlssyyWhXhs=domain.getWlssyyWhXhs();
		List<String> wlssclfsDms=domain.getWlssclfsDms();
		List<String> wlssMxJes = domain.getWlssMxJes();
		List<String> zrrDjxhs = domain.getZrrDjxhs();
		if(null==xhs|| xhs.isEmpty()){
			return;
		}
		int i = 0;
		for (String xh : xhs) {
			HyWlssdjMx bo = new HyWlssdjMx();
			bo.setWlssDjxh(domain.getWlssDjxh());
			bo.setXh(xh);
			bo.setWlssclfsDm(wlssclfsDms.get(i));
			bo.setWlssyyWhXh(wlssyyWhXhs.get(i));
			bo.setJe(wlssMxJes.get(i));
			bo.setPcygCzyDjxh(zrrDjxhs.get(i));
			dao.saveHyWlssdjMx(bo);
			if(!("Y".equals(domain.getSpbcbz()))){
				dao.wlssDjHxcl(domain, userDomain);
			}
			i++;
		}
	}


	public void getHw(HyZyglFydjDomain domain, UserDomain userDomain) throws Exception {
		if("Y".equals(domain.getConBz())){
			HyZyglFydjDomain qbDomain = new HyZyglFydjDomain();
			qbDomain.setMc("---请选择---");
			domain.getDataList().add(qbDomain);
		}
		
		if (StringUtils.isNotBlank(domain.getPcDjxh())) {
			List<HyZyglFydjDomain> bmList = dao.getHw(domain, userDomain);
			domain.getDataList().addAll(bmList);
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteWlssdjMx(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		
		List<String> xhs=domain.getXhs();
		if(null==xhs|| xhs.isEmpty())
			return;
		
		for (String xh : xhs) {
			if(StringUtils.isNotBlank(xh)){
				dao.deleteWlssdjMx(domain.getWlssDjxh(),xh);
				if(!("Y".equals(domain.getSpbcbz()))){
					dao.wlssDjHxcl(domain, userDomain);
				}
			}
		}
		/*double je =dao.getWlssdjJe(domain.getWlssDjxh());
		domain.setJe(je);
		dao.updateWlssdjJe(domain.getWlssDjxh(), je+"");*/
	}

	
	@Override
	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		dao.deleteByKey(domain, userDomain);
		dao.wlssDjHxcl(domain, userDomain);
	}

	
	public void toLook(HyZyglFydjDomain baseBusinessDomain, UserDomain userDomain)
			throws Exception {
		this.doMyInitMx(baseBusinessDomain, userDomain);
		
	}

	
	public void checkWlDj(HyZyglFydjDomain domain, UserDomain userDomain)
			throws Exception {
		int count=dao.checkWlDj(domain);
		if(count>0){
			domain.setTager("Y");
		}
		else{
			domain.setTager("N");
		}
	}
	public void initSsMx(HyZyglFydjDomain baseBusinessDomain, UserDomain userDomain)throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		HyPcxxglDomain dom = (HyPcxxglDomain) dao.getHyPcAllByDjxh(domain.getPcDjxh());
		HyZyglFydjDomain hwDom=(HyZyglFydjDomain) dao.selectHyPcHwxxWhenWlss(domain);
		domain.setPcdh(dom.getPcdh());
		domain.setClhm4Query(dom.getCyrClhm());
		domain.setCyrGchm(dom.getCyrGchm());
		domain.setCyrSjxm(dom.getCyrSjxm());
		domain.setPcrMc(dom.getPcrMc());	
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");	
		domain.setPcrq(sim.format(dom.getPcrq()));	
		domain.setPcJgmc(dom.getPcJgbmMc());	
		domain.setSsJgmc(dom.getSsJgbmMc());
		
		domain.setDdDjxh(hwDom.getDdDjxh());
		domain.setHwmxxh(hwDom.getXh());
		domain.setWfhDjxh(hwDom.getWfhDjxh());
		domain.setHwmc(hwDom.getHwmc());
		domain.setKhDjxh(hwDom.getFhrDjxh());
		domain.setKhmc(hwDom.getFhrMc());
		domain.setSl(hwDom.getSl());
		domain.setTj(hwDom.getTj());
		domain.setZl(hwDom.getZl());
		domain.setJsSl(hwDom.getJsSl());
		
		String xtcsSfsp = commonDao.getFunXtXtcs("20205", userDomain.getGsbm(), "2");
		domain.setXtcsSfsp(xtcsSfsp);
	}
	public void querySsMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.getSsList(domain, userDomain);
		domain.setDataList(dataList);
	}
	
	
	public void saveSsZp(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyZyglFydjDomain domain = (HyZyglFydjDomain)baseBusinessDomain;
		
		dao.saveFileDomain(domain,userDomain);
		
	}

	public List<HyWlssdjZp> queryPhoto(String wlssdjxh) throws Exception {
		List<HyWlssdjZp> list=dao.queryPhoto(wlssdjxh);
		return list;
	}

	public String deletePhoto(String zpscxh,HyZyglFydjDomain domain, UserDomain userDomain) throws Exception {
		String zpdz=dao.deletePhoto(zpscxh,domain,userDomain);
		
		return zpdz;
	}

	public List<String> deletePhotoes(HyZyglFydjDomain domain, UserDomain userDomain) throws Exception {
		List<String> list=dao.deletePhotoes(domain,userDomain);
		return list;
	}
	
}
