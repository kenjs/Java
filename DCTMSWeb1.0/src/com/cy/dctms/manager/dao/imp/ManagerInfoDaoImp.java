package com.cy.dctms.manager.dao.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.ManagerInfo;
import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.ManagerInfoDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.manager.dao.IManagerInfoDao;


public class ManagerInfoDaoImp extends BaseDao implements IManagerInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ManagerInfoDomain queryManagerInfoByCode(String code) {
		try {
			return (ManagerInfoDomain) queryForObject("query_manager_info_by_code", code);
		} catch (Exception e) {
			logger.error("query_managerInfo_by_code",e);
			throw new RuntimeException();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryManagerInfoList(ManagerInfoDomain managerInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", managerInfoDomain.getPageInfo().getPageSize());//ÿҳ��¼��
			queryMap.put("start", (managerInfoDomain.getPageInfo().getCurPage()-1)*managerInfoDomain.getPageInfo().getPageSize());
			//��������
			queryMap.put("code", managerInfoDomain.getCode());
			if (StringUtils.isNotBlank(managerInfoDomain.getName())) {
				queryMap.put("name",java.net.URLDecoder.decode(managerInfoDomain.getName(),"utf-8"));
			}
			managerInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_manager_info_count",queryMap));// ��ҳ��
			List<ManagerInfoDomain> dataList = (List<ManagerInfoDomain>) queryForList("query_manager_info_by_page",queryMap);
			//����Ҫ�󶨵�δ�󶨵Ķ�ʱ�䴦��
			for (ManagerInfoDomain domain : dataList) {
				if ("1".equals(domain.getValidateMacFlag())&&StringUtils.isBlank(domain.getMacAddress())) {
					domain.setMacTime("��δ��");
				}
			}
			managerInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_managerInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void exportManagerInfo(ManagerInfoDomain managerInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//��������
			queryMap.put("code", managerInfoDomain.getCode());
			if (StringUtils.isNotBlank(managerInfoDomain.getName())) {
				queryMap.put("name",java.net.URLDecoder.decode(managerInfoDomain.getName(),"utf-8"));
			}
			List<ManagerInfoDomain> dataList = (List<ManagerInfoDomain>) queryForList("export_manager_info",queryMap);
			managerInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_managerInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public ManagerInfoDomain queryManagerInfoById(String id) {
		try {
			return (ManagerInfoDomain) queryForObject("query_manager_info_by_id", id);
		} catch (Exception e) {
			logger.error("query_managerInfo_by_id",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void saveManagerInfo(ManagerInfoDomain managerInfoDomain,String userId) {
		try {
			ManagerInfo bo = new ManagerInfo();
			bo.setCode(managerInfoDomain.getCode());
			bo.setName(String.valueOf(java.net.URLDecoder.decode(managerInfoDomain.getName(),"utf-8")));
			bo.setValidateMacFlag(Long.valueOf(managerInfoDomain.getValidateMacFlag()));
			if (!managerInfoDomain.getId().equals("0")) {
				bo.setId(Long.valueOf(managerInfoDomain.getId()));
				saveObject("modify_manager_info", bo);
				//�Բ�ҪҪ�󶨵��Ե��˺����ð󶨵�ַ�Ͱ�ʱ��Ϊ��
				if ("0".equals(managerInfoDomain.getValidateMacFlag())) {
					bo.setMacAddress(null);
					bo.setMacTime(null);
					saveObject("modify_manager_mac_info", bo);
				}
				 //��Ӳ�����־
			 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
			 	MWLIbo.setName("�޸Ĺ���Ա��Ϣ");
			 	MWLIbo.setColumnId(Long.valueOf(managerInfoDomain.getId()));
			 	String content = "";
			 	content = content + "����:"+bo.getName() +";" ;
			 	content = content + "�˺�:"+bo.getCode() +";";
			 	MWLIbo.setContent(content);
			 	MWLIbo.setManagerId(Long.valueOf(userId));
			 	MWLIbo.setTableName("t_manager_user_info");
				addObject("add_workLog_info", MWLIbo);
			}else {
				bo.setPassword(new MD5Util().getMD5ofStr(managerInfoDomain.getPassword()));
				bo.setParentId(userId);
				Long columnId = addObject("add_manager_info", bo);
				 //��Ӳ�����־
			 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
			 	MWLIbo.setName("��ӹ���Ա��Ϣ");
			 	MWLIbo.setColumnId(columnId);
			 	String content = "";
			 	content = content + "����:"+bo.getName() ;
			 	content = content + "�˺�:"+bo.getCode() ;
			 	MWLIbo.setContent(content);
			 	MWLIbo.setManagerId(Long.valueOf(userId));
			 	MWLIbo.setTableName("t_manager_user_info");
				addObject("add_workLog_info", MWLIbo);
			}
		} catch (Exception e) {
			logger.error("save_managerInfo",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void saveManagerMacInfo(String macAddress , String id) {
		try {
			ManagerInfo bo = new ManagerInfo();
			bo.setMacAddress(macAddress);
			bo.setId(Long.valueOf(id));
			bo.setMacTime(new Date());
			saveObject("modify_manager_mac_info", bo);
		} catch (Exception e) {
			logger.error("save_managerInfo",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteManagerInfo(String id ,String userId) {
		try {
			ManagerInfoDomain domain = (ManagerInfoDomain) queryForObject("query_manager_info_by_id", id);
			 deleteObject("delete_manager_info_by_id", id);
			 //��Ӳ�����־
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("ɾ������Ա��Ϣ");
		 	MWLIbo.setColumnId(Long.valueOf(id));
		 	String content = "";
		 	content = content + "����:"+domain.getName() ;
		 	content = content + "�˺�:"+domain.getCode() ;
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_manager_user_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("delete_managerInfo",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteManagerMacInfo(String id ,String userId) {
		try {
			 deleteObject("delete_manager_mac_info_by_id", id);
			 //��Ӳ�����־
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("ɾ������Ա�����ַ");
		 	MWLIbo.setColumnId(Long.valueOf(id));
		 	String content = "";
		 	ManagerInfoDomain domain = (ManagerInfoDomain) queryForObject("query_manager_info_by_id", id);
		 	content = content + "����:"+domain.getName() ;
		 	content = content + "�˺�:"+domain.getCode() ;
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_manager_user_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("delete_managerInfo",e);
			throw new RuntimeException();
		}
	}


	@Override
	public void updateManagerPassword(ManagerInfoDomain managerInfoDomain,
			String userId) {
		try {
			ManagerInfo bo = new ManagerInfo();
			bo.setPassword(new MD5Util().getMD5ofStr(managerInfoDomain.getPassword()));
			bo.setId(Long.valueOf(userId));
			saveObject("modify_manager_password", bo);
			 //��Ӳ�����־
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("����Ա�޸�����");
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setColumnId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_manager_user_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("update_managerInfo_password",e);
			throw new RuntimeException();
		}
		
	}
}
