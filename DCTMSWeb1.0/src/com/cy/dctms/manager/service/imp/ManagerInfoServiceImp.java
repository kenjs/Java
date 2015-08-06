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
		}else {//第一步判断用户名和密码
			managerInfoDomain.setErrorMessage("用户名或密码错误！");
			return managerInfoDomain;
		}
		if ("1".equals(managerInfoDomain.getValidateMacFlag())) {//判断是否需要绑定
			//没有mac地址传过来提示配置插件
			if(StringUtils.isBlank(loginUserInfo.getMacAddress())){
				managerInfoDomain.setErrorMessage("没有检测到ActiveX控件，请安装配置！");
				return managerInfoDomain;
			}
			//数据库中没有mac地址的，有mac地址传过来获取保存
			if(StringUtils.isBlank(managerInfoDomain.getMacAddress())){
				managerInfoDao.saveManagerMacInfo(new MD5Util().getMD5ofStr(loginUserInfo.getMacAddress()),managerInfoDomain.getId());
				return managerInfoDomain;
			}
			//数据库中有mac地址的进行比对是否一致
			if (new MD5Util().getMD5ofStr(loginUserInfo.getMacAddress()).equals(managerInfoDomain.getMacAddress())) {
				return managerInfoDomain;
			}
			managerInfoDomain.setErrorMessage("已绑定其他电脑！");
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
