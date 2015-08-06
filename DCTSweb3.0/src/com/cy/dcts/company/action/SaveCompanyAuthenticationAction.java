package com.cy.dcts.company.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.company.service.ICompanyService;
import com.cy.dcts.webUser.service.ISaveWebUserInfoService;

public class SaveCompanyAuthenticationAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ICompanyService companyService;
	private ISaveWebUserInfoService saveWebUserInfoService;
	private CompanyInfo companyInfo;
	private String flag;
	private String errorMessage;//保存错误信息
	private String provinceCityCountyStr;//省-市-区县 
	private String menuAId;//个人中心的菜单显示
	
	//文件上传
		private File upload[];
		private String uploadFileName[];//文件名称
		private String uploadContentType[];//文件类型
		private String filePath;//文件路径
		
		private String reBusinessImagesName; //重命名后的营业照图片名称
		private String reOrgImagesName;//重命名后的组织机构照图片名称
		
		
   
		private SimpleDateFormat formats=new SimpleDateFormat("yyyyMMddHHmmss");
	@Override
	protected String execMethod() throws Exception {
		//判断是否登陆
		if(getSessionUser()==null){
			return LOGIN;
		}
		logger.debug("save company authentication begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		
		//读入文件，输出到E:/apache-tomcat-6.0.37/file
		if(upload!=null){
			try {
				uploadFile();
				//uploadImage();
			} catch (Exception e) {
				logger.error(e.getMessage());
				this.setErrorMessage("上传图片失败，请重新上传！");
				e.printStackTrace();
				return ERROR;
			}
		}else{
			logger.error("upload file null!");
			this.setErrorMessage("请选择上传的文件！");
		}
		//营业执照照片或组织机构照片是否被删除,若是则需要修改用户表中的提交审核标识
		String isCancleBusinessImage="0";
		String isCancleOrgImage="0";
		if(StringUtils.isNotEmpty(companyInfo.getBusinessImages())&&StringUtils.isEmpty(companyInfo.getBusinessLicence())){
			isCancleBusinessImage="1";
		}
		if(StringUtils.isNotEmpty(companyInfo.getOrganizationImages())&&StringUtils.isEmpty(companyInfo.getOrganizationCode())){
			isCancleOrgImage="1";
		}
		
		
		//修改自己公司的信息（数据库保存营业路径）
		//把营业执照照片路径和组织机构照片路径保存到对象中
		if(StringUtils.isNotEmpty(reBusinessImagesName)){
			companyInfo.setBusinessImages(filePath+getSessionUser().getCompanyId()+"/"+reBusinessImagesName);
		}
		if(StringUtils.isNotEmpty(reOrgImagesName)){
			companyInfo.setOrganizationImages(filePath+getSessionUser().getCompanyId()+"/"+reOrgImagesName);
		}
		
		companyInfo.setId(getSessionUser().getCompanyId());
		//数据库中<isNotNull property="businessImages">BUSINESS_IMAGES = #businessImages#,</isNotNull>
				if(StringUtils.isEmpty(companyInfo.getBusinessLicence())){
					companyInfo.setBusinessImages("");//若营业执照注册号为空，则营业执照照片也为空
				}
				if(StringUtils.isEmpty(companyInfo.getOrganizationCode())){
					companyInfo.setOrganizationImages("");//若组织机构注册号为空，则组织机构照片也为空
				}
		//修改公司信息
		companyService.modifyCompanyInfoById(companyInfo);
		
		//flag=2表示企业重新认证，直接进行企业认证时若营业执照和组织机构照片任何一个修改过(包含删除照片即注册号为空)
		if("2".equals(flag)||StringUtils.isNotEmpty(reBusinessImagesName)||StringUtils.isNotEmpty(reOrgImagesName)||"1".equals(isCancleBusinessImage)||"1".equals(isCancleOrgImage)) {
			WebUserInfo info = new WebUserInfo();
			info.setId(getSessionUser().getId());
			info.setEnterpriseFlag("0");//0企业未认证
			info.setSubmitType("1");//1已提交
			//修改t_web_user_info 表中的企业认证标识及提交审核标识
			saveWebUserInfoService.modifyWebUserInfoEnterpriseFlagById(info);
		}
		
		logger.debug("save company authentication success. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		//修改用户的手机号(待续)
		return SUCCESS;
	}

	//上传文件
	private void uploadFile() throws Exception{
		//String path = ServletActionContext.getServletContext().getRealPath(filePath);
		logger.info("上传文件路径: " + filePath+getSessionUser().getCompanyId());
		String realFilePath=filePath+getSessionUser().getCompanyId();
		File file = new File(realFilePath);//一个公司对应一个文件夹（用户和公司时一对一的关系）
		File file2 = null;
		if(!file.exists())
		{
			file.mkdirs();
		}
		for(int i=0;i<upload.length;i++)
		{
			file2 = upload[i];
			if(!StringUtils.isEmpty(reOrgImagesName)&&!StringUtils.isEmpty(reBusinessImagesName)){
				uploadFileName[0]=reBusinessImagesName;
				uploadFileName[1]=reOrgImagesName;
			} else{
				if(StringUtils.isNotEmpty(reBusinessImagesName)){
					uploadFileName[0]=reBusinessImagesName;
				} 
				if(StringUtils.isNotEmpty(reOrgImagesName)){
					uploadFileName[0]=reOrgImagesName;
				}
			}
			if(file2.isFile() && file2.exists()) { 
				FileUtils.copyFile(upload[i], new File(realFilePath,uploadFileName[i]));
			}
		}
	}	

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ICompanyService getCompanyService() {
		return companyService;
	}

	public String getProvinceCityCountyStr() {
		return provinceCityCountyStr;
	}

	public void setProvinceCityCountyStr(String provinceCityCountyStr) {
		this.provinceCityCountyStr = provinceCityCountyStr;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public void setSaveWebUserInfoService(
			ISaveWebUserInfoService saveWebUserInfoService) {
		this.saveWebUserInfoService = saveWebUserInfoService;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getReBusinessImagesName() {
		return reBusinessImagesName;
	}

	public void setReBusinessImagesName(String reBusinessImagesName) {
		this.reBusinessImagesName = reBusinessImagesName;
	}

	public String getReOrgImagesName() {
		return reOrgImagesName;
	}

	public void setReOrgImagesName(String reOrgImagesName) {
		this.reOrgImagesName = reOrgImagesName;
	}

}
