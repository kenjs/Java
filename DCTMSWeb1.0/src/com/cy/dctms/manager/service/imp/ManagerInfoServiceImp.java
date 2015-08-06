package com.cy.dctms.manager.service.imp;

import org.apache.commons.lang.StringUtils;

import com.cy.dctms.common.domain.ManagerInfoDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.manager.dao.IManagerInfoDao;
import com.cy.dctms.manager.service.IManagerInfoService;

public class ManagerInfoServiceImp implements IManagerInfoService {

	private IManagerInfoDao managerInfoDao;

	@Override
	public ManagerInfoDomain userLogin(ManagerInfoDomain loginUserInfo) {
		ManagerInfoDomain managerInfoDomain = managerInfoDao.queryManagerInfoByCode(loginUserInfo.getCode());
		if (managerInfoDomain==null) {
			managerInfoDomain = new ManagerInfoDomain();
		}
		if(managerInfoDomain!=null&& new MD5Util().getMD5ofStr(loginUserInfo.getPassword()).equals(managerInfoDomain.getPassword())){
		}else {//��һ���ж��û���������
			managerInfoDomain.setErrorMessage("�û������������");
			return managerInfoDomain;
		}
		if ("1".equals(managerInfoDomain.getValidateMacFlag())) {//�ж��Ƿ���Ҫ��
			//û��mac��ַ��������ʾ���ò��
			if(StringUtils.isBlank(loginUserInfo.getMacAddress())){
				managerInfoDomain.setErrorMessage("û�м�⵽ActiveX�ؼ����밲װ���ã�");
				return managerInfoDomain;
			}
			//���ݿ���û��mac��ַ�ģ���mac��ַ��������ȡ����
			if(StringUtils.isBlank(managerInfoDomain.getMacAddress())){
				managerInfoDao.saveManagerMacInfo(new MD5Util().getMD5ofStr(loginUserInfo.getMacAddress()),managerInfoDomain.getId());
				return managerInfoDomain;
			}
			//���ݿ�����mac��ַ�Ľ��бȶ��Ƿ�һ��
			if (new MD5Util().getMD5ofStr(loginUserInfo.getMacAddress()).equals(managerInfoDomain.getMacAddress())) {
				return managerInfoDomain;
			}
			managerInfoDomain.setErrorMessage("�Ѱ��������ԣ�");
		}
		return managerInfoDomain;
	}
	@Override
	public void updateManagerPassword(ManagerInfoDomain managerInfoDomain,
			String userId) {
		managerInfoDao.updateManagerPassword(managerInfoDomain,userId);
		
	}
	@Override
	public void queryManagerInfoList(ManagerInfoDomain managerInfoDomain) {
		 managerInfoDao.queryManagerInfoList(managerInfoDomain);
	}
	@Override
	public void exportManagerInfo(ManagerInfoDomain managerInfoDomain) {
		 managerInfoDao.exportManagerInfo(managerInfoDomain);
	}
	@Override
	public ManagerInfoDomain queryManagerInfoMxById(String id) {
		 return managerInfoDao.queryManagerInfoById(id);
	}
	@Override
	public void saveManagerInfo(ManagerInfoDomain managerInfoDomain ,String userId) {
		ManagerInfoDomain domain = new ManagerInfoDomain();
		if ("0".equals(managerInfoDomain.getId())) {
			domain = managerInfoDao.queryManagerInfoByCode(managerInfoDomain.getCode());
		}
		if (domain!=null&&StringUtils.isNotBlank(domain.getId())) {
			managerInfoDomain.setId("error");
			return;
		}
		managerInfoDao.saveManagerInfo(managerInfoDomain,userId);
	}
	@Override
	public void deleteManagerInfo(String id,String userId) {
		managerInfoDao.deleteManagerInfo(id,userId);
		
	}
	public void setManagerInfoDao(IManagerInfoDao managerInfoDao) {
		this.managerInfoDao = managerInfoDao;
	}
}
