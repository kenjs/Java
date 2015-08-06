package com.cy.hygl.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.dao.WlglptCommonDao;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.common.service.imp.BaseBusinessServiceImp;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.orm.ibatis.dialect.SysOracleDialect;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysToolsUtil;
import com.cy.hygl.dao.HyJsglSrdzDao;
import com.cy.hygl.domain.HyJsglSrdzDomain;
import com.cy.hygl.domain.JsDdHwxxDomain;
import com.cy.hygl.domain.JsSrdzDomain;
import com.cy.hygl.domain.JsSrdzDzcyDomain;
import com.cy.hygl.service.HyJsglSrdzService;

@Service
/**
 * The SERVICEIMP for �������
 * 
 * @author HJH
 */
public class HyJsglSrdzServiceImp extends BaseBusinessServiceImp implements
		HyJsglSrdzService {
	@Autowired
	private HyJsglSrdzDao dao;
	@Autowired
	private WlglptCommonDao commonDao;

	@Override
	protected void doMyInit(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		// �ڴ���ӳ�ʼ����Ӧ����
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		domain.setDefultRqQ(SysDateUtil.getFirstDayMonth());
		domain.setRqZ(SysDateUtil.getCurrentDate());
		domain.setDwDm(userDomain.gsbm);
		if (userDomain.getCs_20052().equals("Y")) {
			domain.setBmDm(userDomain.bmbm);
		}
		domain.setDzztDm("1");
		domain.setDzfsDm("1");// Ĭ�ϰ�����
	}

	@Override
	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.queryList(domain);
		domain.setDataList(dataList);
	}

	@Override
	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		List<BaseBusinessDomain> dataList = dao.downloadList(domain);
		domain.setDataList(dataList);
	}

	@Override
	public void doMyInitMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		if (SysToolsUtil.isNullOrEmpty(domain.getJsSrdzDomain().getDzDjxh())) {
			domain.setXgbz("N");
			// ��ʼ��������Ϣ,����jsDjxh
			this.initJbxxByJsDjxh(domain);
		} else {
			this.initJbxxByJsDjxh(domain);
			// ��ȡ������Ϣ����dzDjxh
			JsSrdzDomain dom = (JsSrdzDomain) dao.getDomainByKey(domain.getJsSrdzDomain());
			if (null != dom) {
				domain.setXgbz("Y");
				// �жϷ��Ͱ�ť
				if (("0".equals(dom.getWsspztDm()) || "2".equals(dom.getWsspztDm())) && "Y".equals(dom.getSpbz())) {
					domain.setSendBz(true);
				}
				domain.setJsSrdzDomain(dom);
				// ������ǰά���Ĳ����б�list����ʽ��ȡ��������dzDjxh
				List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain.getJsSrdzDomain().getDzDjxh());
				if (null == dzcyList || dzcyList.isEmpty())
					return;

				domain.setJsSrdzcyList(dzcyList);
			}
		}
	}
	// ��ʼ��������Ϣ,����jsDjxh
	protected void initJbxxByJsDjxh(HyJsglSrdzDomain domain) throws Exception {
		HyJsglSrdzDomain jsdzDomain = null;
		
		//��������������Ϣ
		if(SysToolsUtil.isNullOrEmpty(domain.getJsSrdzDomain().getDzDjxh())){
			if(StringUtils.isNotBlank(domain.getJsSrdzDomain().getJsDjxh())){
				JsSrdzDomain dom2 = dao.getDzInfo(domain.getJsSrdzDomain().getJsDjxh());
				if(dom2 != null) {
					domain.setJsSrdzDomain(dom2);
					JsSrdzDomain dom = (JsSrdzDomain) dao.getDomainByKey(domain.getJsSrdzDomain());
					if (null != dom) {					
						domain.setJsSrdzDomain(dom);
						// ������ǰά���Ĳ����б�list����ʽ��ȡ��������dzDjxh
						List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain.getJsSrdzDomain().getDzDjxh());
						if (null == dzcyList || dzcyList.isEmpty())
						return;
	
						domain.setJsSrdzcyList(dzcyList);
					}
				}
			}
		}
		
		jsdzDomain = (HyJsglSrdzDomain) dao.getJsSrdzByJsDjxh(domain.getJsSrdzDomain());
		if (jsdzDomain != null) {
			domain.getJsSrdzDomain().setSsJgbm(jsdzDomain.getDwDm());
			domain.setDzsr(jsdzDomain.getDzsr());
			domain.setDzyj(jsdzDomain.getDzyj());
			domain.setDzwj(jsdzDomain.getDzwj());
			domain.getJsSrdzDomain().setJsSr(jsdzDomain.getDzsr());// Ĭ�Ͻ�������ϼ�Ϊ��������ϼ�
			domain.getJsSrdzDomain().setJsYj(jsdzDomain.getDzyj());// Ĭ�Ͻ��������ѽ�Ϊ���������ѽ�
			domain.getJsSrdzDomain().setJsWj(jsdzDomain.getDzwj());// Ĭ�Ͻ�������δ��Ϊ��������δ��
			domain.setKhMc(jsdzDomain.getKhMc());
			domain.setDdbh(jsdzDomain.getDdbh());
			domain.setXdrq(jsdzDomain.getXdrq());
		}
		JsDdHwxxDomain dom1 = (JsDdHwxxDomain) dao.getJsDdHwxxDomain(domain.getJsSrdzDomain().getJsDjxh());
		if(dom1!=null){
			List<HyJsglSrdzDomain> list = dao.getWlssxl(dom1.getDdDjxh(), dom1.getXh());
			domain.setWlssXlList(list);
		}
	}

	protected void srDzCalculate(JsSrdzDomain jsdzDomain,
			List<JsSrdzDzcyDomain> dzcyList) {
		for (JsSrdzDzcyDomain temp : dzcyList) {
			if ("11".equals(temp.getDzcyClfsDm())) {// ��������,����������=��������+��ǰ������
				jsdzDomain.setJsSr(jsdzDomain.getJsSr() + temp.getDzcyje());
				// ���¼���δ��
				jsdzDomain.setJsWj(jsdzDomain.getJsSr() - jsdzDomain.getJsYj());
			}
			if ("12".equals(temp.getDzcyClfsDm())) {// ��������,����������=��������-��ǰ������
				jsdzDomain.setJsSr(jsdzDomain.getJsSr() - temp.getDzcyje());
				// ���¼���δ��
				jsdzDomain.setJsWj(jsdzDomain.getJsSr() - jsdzDomain.getJsYj());

			}
			if ("21".equals(temp.getDzcyClfsDm())) {// �����Ѹ�,���ѽ�=�ѽ�+��ǰ������
				jsdzDomain.setJsYj(jsdzDomain.getJsYj() + temp.getDzcyje());
				// ���¼���δ��
				jsdzDomain.setJsWj(jsdzDomain.getJsSr() - jsdzDomain.getJsYj());
			}
			if ("22".equals(temp.getDzcyClfsDm())) {// �����Ѹ�,���ѽ�=�ѽ�-��ǰ������
				jsdzDomain.setJsYj(jsdzDomain.getJsYj() - temp.getDzcyje());
				// ���¼���δ��
				jsdzDomain.setJsWj(jsdzDomain.getJsSr() - jsdzDomain.getJsYj());
			}
			if ("31".equals(temp.getDzcyClfsDm())) {// �����ζ���
				// jsdzDomain.setJsYj(jsdzDomain.getJsYj()-temp.getDzcyje());
				// ���¼���δ��
			}
			if ("32".equals(temp.getDzcyClfsDm())) {// ��������ʧ
				// jsdzDomain.setJsYj(jsdzDomain.getJsYj()-temp.getDzcyje());
				// ���¼���δ��
			}
		}
		// ������=����δ��-�µ�δ��
		// jsdzDomain.setDzcyje(jsdzDomain.getJsWj()-jsdzDomain.getDzWj());
	}

	@Override
	protected void doMyDeleteMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		List<String> xhs = domain.getXhs();
		List<String> xhsAll = domain.getXhsAll();
		if (xhs.isEmpty())
			return;
		if (xhsAll.isEmpty())
			return;
		for(String xh:xhsAll){
			JsSrdzDzcyDomain temp = new JsSrdzDzcyDomain();
			temp.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
			temp.setXh(xh);
			JsSrdzDzcyDomain dzcyDomain = (JsSrdzDzcyDomain) dao.getDomainDzcyByKey(temp);
			if(StringUtils.isNotBlank(dzcyDomain.getXcJsDjxh())){
				if(dao.checkSrdz(dzcyDomain.getXcJsDjxh())){
					throw new DiyServiceException("���ζ����ѵǼǣ������޸ģ�");
				}
			}
		}
		// ɾ������-�������-���ʲ�����ʽ��
		if ("Y".equals(domain.getXgbz())) {
			for (String xh : xhs) {
				JsSrdzDzcyDomain temp = new JsSrdzDzcyDomain();
				temp.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
				temp.setXh(xh);
				JsSrdzDzcyDomain dzcyDomain = (JsSrdzDzcyDomain) dao.getDomainDzcyByKey(temp);
				if(StringUtils.isNotBlank(dzcyDomain.getXcJsDjxh())){
					if(dao.checkSrdz(dzcyDomain.getXcJsDjxh())){
						throw new DiyServiceException("���ζ����ѵǼǣ������޸ģ�");
					}else{
						dao.deleteJsDdHwxxDomainByKey(dzcyDomain.getXcJsDjxh());
					}
				}
				dao.deleteDzcyByKey(domain.getJsSrdzDomain().getDzDjxh(), xh);
			}
			// ɾ����ʱ���ж϶��˲����־������ж��˲����¼��ΪY������N
			List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain
					.getJsSrdzDomain().getDzDjxh());

			JsSrdzDomain dom = (JsSrdzDomain) dao.getDomainByKey(domain
					.getJsSrdzDomain());
			if (null == dom)
				return;

			if (null == dzcyList || dzcyList.isEmpty()) {	
//				dom.setDzCybz("N");
//				dom.setDzcyje(Double.valueOf(0));
//				dom.setJsSr(domain.getDzsr());
//				dom.setJsYj(domain.getDzsr());
//				dom.setJsWj(0.0);
				
//				domain.setDzcyje("0");
			} else {
				double cyje=0;
				for(JsSrdzDzcyDomain e:dzcyList){
					if(e.getDzcyje()!=null){
						cyje=cyje+e.getDzcyje();
					}
				}
				dom.setDzCybz("Y");
				// dom.setDzcyje(cyje);
				// dom.setJsSr(dom.getDzSr());//Ĭ�Ͻ�������Ϊ��������
				// dom.setJsYj(dom.getDzYj());//Ĭ�Ͻ��������ѽ�Ϊ���������ѽ�
				// dom.setJsWj(dom.getDzWj());//Ĭ�Ͻ�������δ��Ϊ��������δ��
				// this.srDzCalculate(dom, dzcyList);
				
				// domain.setDzcyje(cyje+"");
			}
			dao.saveDomain(dom, userDomain);
			// ���ú�������
			domain.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
			
			dao.callHyglJsglSrdzDdhwHxcl(domain, userDomain);
			// dao.callHyglJsglSrdzDdHxcl(domain, userDomain);
		} else {// ɾ������-�������-���ʲ���-��ʱ���������
			for (String xh : xhs) {
				dao.deleteDzcyTempByKey(domain.getJsSrdzDomain().getDzDjxh(),xh);
			}
		}

	}
	
	@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		JsSrdzDomain dzDomain = domain.getJsSrdzDomain();
		if(dzDomain.getDzcyje()==null){
			dzDomain.setDzcyje(0.0);
		}
		if (dzDomain.getDzcyje() != 0.0) {
			dzDomain.setDzCybz("Y");
		}else {
			dzDomain.setDzCybz("N");
		}
		dao.saveDomain(dzDomain, userDomain);
		domain.setDzDjxh(dzDomain.getDzDjxh());
		
		// ������˲�����ϸ��
		saveDzcyMx(domain, userDomain);
		
		// ���û��˹���-�������-������ʣ��������-�����������ʱ�����ٵ���PROD��
		dao.callHyglJsglSrdzDdhwHxcl(domain, userDomain);
	}
	
	/**
	 *  ������˲�����ϸ��
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	private void saveDzcyMx(HyJsglSrdzDomain domain, UserDomain userDomain) throws Exception {
		//dao.deleteDzcyByDzDjxh(domain.getDzDjxh());
		
		List<Double> cyjes = domain.getCyjes();
		
		JsSrdzDzcyDomain dzcyDomain;
		for (int i=0; i<cyjes.size(); i++) {
			if (cyjes.get(i) == null || cyjes.get(i) == 0) {
				continue;
			}
			dzcyDomain = new JsSrdzDzcyDomain();
			if(StringUtils.isBlank(domain.getXhs().get(i))){
				Integer xh = dao.queryDzcyNextXh(domain.getDzDjxh());
				dzcyDomain.setXh(xh+"");
			}else{
				dzcyDomain.setXh(domain.getXhs().get(i));
			}
			dzcyDomain.setDzDjxh(domain.getDzDjxh());
			dzcyDomain.setDzcyje(cyjes.get(i));
			dzcyDomain.setDzcyyyDm(domain.getDzcyyyDm().get(i));
			dzcyDomain.setDzcyClfsDm(domain.getDzcyClfsDm().get(i));
			if("32".equals(dzcyDomain.getDzcyClfsDm())){
				dzcyDomain.setWlssDjxh(domain.getWlssDjxh().get(i));
				if(StringUtils.isNotBlank(domain.getXcJsDjxh().get(i))){
					//ɾ���ζ��˲����ļ�¼
					if(dao.checkSrdz(domain.getXcJsDjxh().get(i))){
						throw new DiyServiceException("���ζ����ѵǼǣ������޸ģ�");
					}else{
						dao.deleteJsDdHwxxDomainByKey(domain.getXcJsDjxh().get(i));
					}
				}
				dzcyDomain.setXcJsDjxh("");
			}else{
				dzcyDomain.setWlssDjxh("");
				dzcyDomain.setXcJsDjxh(domain.getXcJsDjxh().get(i));
			}
			
			dzcyDomain.setBz(domain.getBzs().get(i));
			
			JsDdHwxxDomain dom1;
			if("31".equals(dzcyDomain.getDzcyClfsDm())){
				JsSrdzDomain dom = new JsSrdzDomain();
				dom.setDzDjxh(domain.getDzDjxh());
				dom = (JsSrdzDomain) dao.getDomainByKey(dom);
				
				if(dom!=null){
					if(StringUtils.isBlank(dzcyDomain.getXh())||StringUtils.isBlank(dzcyDomain.getXcJsDjxh())){
						//xhΪ�ջ�xcjsDjxhΪ�գ�����һ����¼
						dom1 = (JsDdHwxxDomain) dao.getJsDdHwxxDomain(dom.getJsDjxh());
						dom1.setJsDjxh("");
					}else{
						//��ԭ���Ѳ����� ���μ�¼ �޸�
						if(dao.checkSrdz(dzcyDomain.getXcJsDjxh())){
							throw new DiyServiceException("���ζ����ѵǼǣ������޸ģ�");
						}
						dom1 = (JsDdHwxxDomain) dao.getJsDdHwxxDomain(dzcyDomain.getXcJsDjxh());
						dom1.setJsDjxh(dzcyDomain.getXcJsDjxh());
					}
					if(dom1!=null){		
						dom1.setDzSr(dzcyDomain.getDzcyje());
						dom1.setDzYj(0.0);
						dom1.setDzWj(dzcyDomain.getDzcyje());
						dom1.setDcjsbz("Y");
						dom1.setQcDzDjxh(domain.getDzDjxh());
						dom1.setDzztDm("1");
						dom1.setDzDjxh("");
						
						dao.saveJsDdHwxxDomain(dom1, userDomain);
						dzcyDomain.setXcJsDjxh(dom1.getJsDjxh());
					}					
				}
			}
			dao.saveDzcyDomain(dzcyDomain, userDomain);
		}
	}

	/*@Override
	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		// �޸�ʱ��������棬ֻ�޸ĸĽ���-�������-����������
		if ("Y".equals(domain.getXgbz())) {
			// �ж϶��˲����־������ж��˲����¼��ΪY������N
			List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain
					.getJsSrdzDomain().getDzDjxh());
			if (null == dzcyList || dzcyList.isEmpty())
				domain.getJsSrdzDomain().setDzCybz("N");
			else
				domain.getJsSrdzDomain().setDzCybz("Y");

			// �޸Ľ���-�������
			dao.saveDomain(domain.getJsSrdzDomain(), userDomain);
		} else {
			// ȡ��ʱ��list����dzDjxh
			List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyTempList(domain
					.getJsSrdzDomain().getDzDjxh());

			// ������޸Ľ���-�������-���ʲ��죨��ʽ��
			if (dzcyList != null || !dzcyList.isEmpty()) {
				domain.getJsSrdzDomain().setDzCybz("Y");// �ж϶��˲����־������ж��˲����¼��ΪY������N
				for (JsSrdzDzcyDomain dom : dzcyList) {
					dao.saveDzcyDomain(dom, userDomain);
				}
			} else {
				domain.getJsSrdzDomain().setDzCybz("N");
			}

			// �������-�������
			dao.saveDomain(domain.getJsSrdzDomain(), userDomain);
			// ɾ������-�������-���ʲ���-��ʱ��
			dao.deleteDzcyTempByDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
		}
		domain.setXgbz("Y");

		// ���ú�������
		domain.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
		// dao.callHyglJsglSrdzDdHxcl(domain, userDomain);
	}*/

	@Override
	public void doMyQueryMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		dao.initDomainDzcyMx(domain.getDzcyDomain());
	}

	@Override
	public void doMySaveMx(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		// xgbz=Yʱ��ֱ�Ӳ�����ʽ��
		if ("Y".equals(domain.getXgbz())) {
			JsSrdzDzcyDomain dzcy = domain.getDzcyDomain();
			dzcy.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());
			dao.saveDzcyDomain(dzcy, userDomain);

			// ���ʱ���ж϶��˲����־������ж��˲����¼��ΪY������N
			List<JsSrdzDzcyDomain> dzcyList = dao.queryDzcyList(domain
					.getDzcyDomain().getDzDjxh());
			if (null == dzcyList || dzcyList.isEmpty())
				return;
			JsSrdzDomain dom = (JsSrdzDomain) dao.getDomainByKey(domain
					.getJsSrdzDomain());
			if (null == dom)
				return;

			dom.setDzCybz("Y");
			// dom.setJsSr(dom.getDzSr());//Ĭ�Ͻ�������Ϊ��������
			// dom.setJsYj(dom.getDzYj());//Ĭ�Ͻ��������ѽ�Ϊ���������ѽ�
			// dom.setJsWj(dom.getDzWj());//Ĭ�Ͻ�������δ��Ϊ��������δ��
			// this.srDzCalculate(dom, dzcyList);

			dao.saveDomain(dom, userDomain);
			// ���ú�������
			domain.setDzDjxh(domain.getDzcyDomain().getDzDjxh());
			
			// dao.callHyglJsglSrdzDdHxcl(domain, userDomain);
		} else {// ������ʱ��
			JsSrdzDzcyDomain dzcy = domain.getDzcyDomain();
			dzcy.setDzDjxh(domain.getJsSrdzDomain().getDzDjxh());

			dao.saveDzcyTempDomain(domain.getDzcyDomain(), userDomain);
		}

		this.initMx(baseBusinessDomain, userDomain);
	}
	
	//��������
	public void plDz(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception {
		JsSrdzDomain dom = new JsSrdzDomain();
		HyJsglSrdzDomain domain = (HyJsglSrdzDomain) baseBusinessDomain;
		String pldzStr = domain.getPldzStr();
		if(pldzStr != null){
			String[] strs = pldzStr.split(",");
			for(int i=0;i < strs.length;i++) {
				String[] resStrs = strs[i].split("#");
				dom.setJsDjxh(resStrs[0]);
				dom.setDzDjxh(resStrs[1]);
				dom.setDzfsDm(resStrs[2]);
				
				domain = (HyJsglSrdzDomain) dao.getJsSrdzByJsDjxh(dom);
				if(domain != null) {
					dom.setJsSr(domain.getDzsr());
					dom.setSsJgbm(domain.getDwDm());
					dom.setJsYj(domain.getDzyj());
					dom.setJsWj(domain.getDzwj());
					dom.setDzcyje(0d);
					dom.setDzje(domain.getDzwj());
					dom.setDzCybz("N");
					
					dao.saveDomain(dom, userDomain);
					
					domain.setDzDjxh(dom.getDzDjxh());
					// ���û��˹���-�������-������ʣ��������-�����������ʱ�����ٵ���PROD��
					dao.callHyglJsglSrdzDdhwHxcl(domain, userDomain);
				}
			}
		}
	}

}
