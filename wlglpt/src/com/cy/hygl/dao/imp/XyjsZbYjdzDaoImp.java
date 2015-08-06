package com.cy.hygl.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.PageDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.constants.XtglConstants;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.common.bo.CwYsyf;
import com.cy.common.bo.XyjsZbYjdz;
import com.cy.hygl.dao.XyjsZbYjdzDao;
import com.cy.hygl.domain.XyjsZbYjdzDomain;

/**
 * The DAOIMP for ���ν���-ת��-�½����.
 * 
 * @author XIAY
 */

@Repository
public class XyjsZbYjdzDaoImp implements XyjsZbYjdzDao {
	@Autowired
	private SysSqlMapClientTemplate businessSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		// ��ҳ���
		PageDomain page = domain.getPage();
		int start = page.getStart();
		int pagSize = page.getPageSize();

		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getZrbmDm())){
			map.put("zrbmDm", domain.getZrbmDm());
		}
		if(StringUtils.isNotBlank(domain.getZrbmDjxh())){
			map.put("zrbmDjxh", domain.getZrbmDjxh());
		}
		if(StringUtils.isNotBlank(domain.getPcrqQ())){
			map.put("pcrqQ", domain.getPcrqQ());
		}
		if(StringUtils.isNotBlank(domain.getPcrqZ())){
			map.put("pcrqZ", domain.getPcrqZ());
		}
		if(StringUtils.isNotBlank(domain.getDzbz())){
			map.put("dzbz", domain.getDzbz());
		}
		
		// ����ҳ���ϵ�����������������
		map.put(XtglConstants.ORDER_STR, page.getOrderStr());

		// ��������
		int totalRecords = ((Integer)(businessSqlMapClientTemplate.queryForObject("getXyjsZbYjdzRowCount", map))).intValue();
		page.setTotalRecords(totalRecords);
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsZbYjdzPage", map,start,pagSize);
		
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		Map<String, String> map = new HashMap<String, String>();
		// ���ò�ѯ����
		if(StringUtils.isNotBlank(domain.getSsJgbm())){
			map.put("ssJgbm", domain.getSsJgbm());
		}
		if(StringUtils.isNotBlank(domain.getZrbmDm())){
			map.put("zrbmDm", domain.getZrbmDm());
		}
		if(StringUtils.isNotBlank(domain.getZrbmDjxh())){
			map.put("zrbmDjxh", domain.getZrbmDjxh());
		}
		if(StringUtils.isNotBlank(domain.getPcrqQ())){
			map.put("pcrqQ", domain.getPcrqQ());
		}
		if(StringUtils.isNotBlank(domain.getPcrqZ())){
			map.put("pcrqZ", domain.getPcrqZ());
		}
		if(StringUtils.isNotBlank(domain.getDzbz())){
			map.put("dzbz", domain.getDzbz());
		}

		// ��������
		List<BaseBusinessDomain>  dataList = businessSqlMapClientTemplate.queryForList("selectXyjsZbYjdzAll", map);
		return dataList;
	}

	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		XyjsZbYjdz bo = new XyjsZbYjdz();

		// ����������ѯ���� ������������ôΪ�޸� ��������Ϊ���
		XyjsZbYjdzDomain dom = (XyjsZbYjdzDomain) this.getDomainByKey(domain);

		if(dom != null){
			BeanUtils.copyProperties(bo, dom);
			
			bo.setDzje(domain.getDzje());   //���˽��
			bo.setDzrCzyDjxh(user.czyDjxh); //������
			bo.setDzrq(SysDateUtil.getCurrentDate());//��������
			bo.setDzJgbm(user.bmbm);	//���˲���
			bo.setDzCybz(domain.getDzCybz());//���˲����־
			bo.setDzcyje(domain.getDzcyje());//���˲�����
			bo.setDzbz("Y");   //���˱�־
			
			businessSqlMapClientTemplate.update("updateXyjsZbYjdzByKey", bo);
		}else{
			BeanUtils.copyProperties(bo, domain);

			businessSqlMapClientTemplate.insert("insertXyjsZbYjdz", bo);
		}
		//ϵͳ�����趨Ϊ: ����Ҫ����
		if(domain.getSfsp().equals("N")){
			bo.setSpbz("Y");
			bo.setWsspztDm("4");//����Ҫ����ʱ����״̬
			businessSqlMapClientTemplate.update("updateXyjsZbYjdzByKey", bo);
			
			//��Ӳ���Ӧ����Ϣ
			CwYsyf sf = new CwYsyf();
			
			//�˷ѽ��㷽����)
			if(dom.getZrbmDm().equals("1")){
				sf.setYfjsfDm("21");//�ͻ�
			}else if(dom.getZrbmDm().equals("2")){
				sf.setYfjsfDm("22");//�ֹ�˾
			}else if(dom.getZrbmDm().equals("3")){
				sf.setYfjsfDm("23");//�ְ���
			}
			sf.setYfjsfDjxh(dom.getZrbmDjxh());	//�˷ѽ��㷽�Ǽ����
			sf.setKmdlDm("2");		//Ӧ���˿�
			sf.setKmxlDm("107");	//ת���½�
			sf.setYsyflyDm("36");	//Ӧ��Ӧ����Դ����
			sf.setYwDjxh(dom.getDzDjxh());		//ҵ��Ǽ����(�Ӿ���ҵ���Ӧ��ͬ��)
			sf.setCsrq(SysDateUtil.getCurrentDate());	//��������
			sf.setYsyfztDm("11");		//δ��
			sf.setYsfJe(domain.getDzje()+"");//Ӧ�ո����
			sf.setYisfJe("0.0");			//���ո����
			sf.setWsfJe(domain.getDzje()+"");//δ�ո����
			sf.setSm("�ɳ����ţ�"+dom.getPcdh()+"�ɳ����ڣ�"+SysDateUtil.format(dom.getPcrq())+"�������ƣ�"
					+dom.getHwmc()+"����֧����"+domain.getDzje()); //˵��
			sf.setYxbz("Y");			//��Ч��־(Y/N)
			sf.setDjJgbm(user.bmbm);		//�Ǽǲ���
			sf.setSsJgbm(dom.getSsJgbm());	//��������
			
			businessSqlMapClientTemplate.insert("insertCwYsyf",sf);
		}
	}

	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ����������ѯ����
		map.put("dzDjxh", domain.getDzDjxh());

		domain = (XyjsZbYjdzDomain)businessSqlMapClientTemplate.queryForObject("selectXyjsZbYjdzByKey", map);
		return domain;
	}
	

	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		Map<String,String> map = new HashMap<String, String>();
		// ������������
		map.put("dzDjxh", domain.getDzDjxh());

		businessSqlMapClientTemplate.update("deleteXyjsZbYjdzByKey", map);
	}

	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception {
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) baseDomain;
		if(StringUtils.isNotBlank(domain.getDzDjxh())){
			XyjsZbYjdzDomain dom = (XyjsZbYjdzDomain) this.getDomainByKey(domain);
			if(dom!=null){
				BeanUtils.copyProperties(domain, dom);
			}
		}

	}
}
