package com.cy.hygl.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.common.util.ImageUpload;
import com.cy.dzgl.domain.QySpwsXmflDomain;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyZpajDomain;

/**
 * THE ACTION FOR 货运-照片安检
 * @author yw
 * time  2013-3-5
 */
@Controller
@Scope("prototype")
@Action(value = "/hygl/hyzpaj", results = {
		@Result(name = "init", location = "/work/hygl/hy_zpaj.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_zpaj_mx.jsp"),
		@Result(name = "save", location = "/work/hygl/hy_zpaj_mx.jsp"),
		@Result(name = "delete", type = "json"),
		@Result(name = "deleteHwMx", type = "json")
		})
@SuppressWarnings("unchecked")

public class HyZpajAction extends ExtendAction  {
	private File fjFile;
	private String fjFileFileName;
	
	

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyZpajDomain domain = (HyZpajDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序列");
		headList.add("状态");
		headList.add("派车单号");
		headList.add("派车日期");
		headList.add("车主姓名");
		headList.add("车辆号码");
		headList.add("挂车号码");
		headList.add("司机姓名");
		headList.add("手机号码");
		headList.add("其他联系电话");
		headList.add("派车人");
		headList.add("所属单位");
		headList.add("所属部门");
		heads.add(headList);
		int i = 1;
		String zt="";
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			HyZpajDomain element = (HyZpajDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			if(element.getAjCs()==0){
				zt="未安检";
			}
			else{
				zt="已安检";
			}
			list.add(zt);
			list.add(element.getPcDh());
			list.add(element.getPcrq());
			list.add(element.getCzxm());
			list.add(element.getClHm());
			list.add(element.getGcHm());
			list.add(element.getSjXm());
			list.add(element.getSjHm());
			list.add(element.getDianhua());
			list.add(element.getPcrXm());
			list.add(element.getSjMc());
			list.add(element.getBmMc());
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	@Override
	public String save() throws Exception {
		UserDomain userDomain = getUserDomain();
		HyZpajDomain domain = (HyZpajDomain)getDomain();
		if(fjFile!=null){
//			FileInputStream fis = new FileInputStream(fjFile);
//			byte[] buffer = new byte[fis.available()];
//			fis.read(buffer);
//			fis.close();
//			domain.setFj(buffer);
			
			Properties pro = new Properties();
			pro.load(this.getClass().getResourceAsStream("/config/systemContext.properties"));
			String path=pro.getProperty("AJZP_FILEPATH");
			String filepath=ImageUpload.saveImg(fjFile,fjFileFileName, path);
			
			domain.setZpdz(filepath);
			
			domain.setFjmcSave(fjFileFileName);
		}
		getService().save(getDomain(), userDomain);
		//	clXttsxx();//
		return "save";
	}
	
	/** 文件下载输出流*/
	@JSON(serialize=false)
	public InputStream getInputStream() {
		HyZpajDomain domain = (HyZpajDomain)getDomain();
		byte[] xznr=domain.getFj();

		//如果下载内容为空,则空,预防报错
		if(xznr==null){
			xznr=new byte[0];
		}
		return new ByteArrayInputStream(xznr);
	}
	
	@Resource(name = "hyZpajServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new HyZpajDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyZpajDomain)domain;
	}

	public File getFjFile() {
		return fjFile;
	}

	public void setFjFile(File fjFile) {
		this.fjFile = fjFile;
	}

	public String getFjFileFileName() {
		return fjFileFileName;
	}

	public void setFjFileFileName(String fjFileFileName) {
		this.fjFileFileName = fjFileFileName;
	}
}
