package com.cy.bggl.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.bggl.domain.BgGzlxDomain;
import com.cy.bggl.service.BgGzlxService;
import com.cy.common.action.ExtendAction;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;

/**
 * THE ACTION FOR 办公-工作联系
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/bggl/bggzlx", results = {
		@Result(name = "init", location = "/work/bggl/bggzlx.jsp"),
		@Result(name = "initSjx", location = "/work/bggl/bggzlx_sjx.jsp"),
		@Result(name = "downloadForSjx", location="/common/download.jsp"),
		@Result(name = "initCgx", location = "/work/bggl/bggzlx_cgx.jsp"),
		@Result(name = "downloadForCgx", location="/common/download.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "queryForSjx", type = "json"),
		@Result(name = "queryForCgx", type = "json"),
		@Result(name = "updateCkbz", type = "json"),
		@Result(name = "initMx", location = "/work/bggl/bggzlx_mx.jsp"),
		@Result(name = "save",   location = "/work/bggl/bggzlx_mx.jsp"),
		@Result(name = "delete", type = "json"),
		@Result(name = "deleteFj", type = "json"),
		@Result(name = "fjxz" , type = "stream", 
				params = { 
				"contentType", "application/octet-stream;charset=GBK", 
				"inputName","inputStream", 
				"contentDisposition", "attachment;filename=${domain.fjmc}", 
				"bufferSize", "3673600" 
			})})
@SuppressWarnings("unchecked")
public class BgGzlxAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	private List<File> upload;   //保存上传的文件
	private List<String> uploadFileName;   //保存上传的文件名

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgGzlxDomain domain = (BgGzlxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("主题");
		headList.add("发送人");
		headList.add("发送日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			BgGzlxDomain element = (BgGzlxDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getZt());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String downloadForSjx() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgGzlxDomain domain = (BgGzlxDomain) getDomain();
		BgGzlxService service = (BgGzlxService)getService();
		service.doMyDownloadForSjx(domain, userDomain);
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("查看标志");
		headList.add("查看日期");
		headList.add("主题");
		headList.add("发送人");
		headList.add("发送时间");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			BgGzlxDomain element = (BgGzlxDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getCkbzMc());
			list.add(element.getCkrq());
			list.add(element.getZt());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "downloadForSjx";
	}
	
	
	public String downloadForCgx() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgGzlxDomain domain = (BgGzlxDomain) getDomain();
		BgGzlxService service = (BgGzlxService)getService();
		service.doMyDownloadForCgx(domain, userDomain);
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("主题");		
		headList.add("创建人");
		headList.add("创建时间");
		headList.add("发送人");
		headList.add("发送时间");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			BgGzlxDomain element = (BgGzlxDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getZt());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "downloadForCgx";
	}
	
	public String initSjx() throws Exception {
		return "initSjx";
	}
	
	public String initCgx() throws Exception {
		return "initCgx";
	}
	
	@Override
	public String save() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgGzlxDomain domain = (BgGzlxDomain)getDomain();
		List<byte[]> uploadValueList = new ArrayList<byte[]>();
		List<String> uploadNameList = new ArrayList<String>();		
		for (int i = 0; i < getUploadFileName().size(); i++) {          
            String filename = getUploadFileName().get(i);
            InputStream is = new FileInputStream(upload.get(i));
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();			
            uploadValueList.add(buffer);
            uploadNameList.add(filename);
		}		
		domain.setUploadValueList(uploadValueList);
		domain.setUploadNameList(uploadNameList);
		getService().save(getDomain(), userDomain);
		return XtglConstants.RESULT_NAME_SAVE;
	}
	
	public String queryForSjx() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgGzlxDomain domain = (BgGzlxDomain)getDomain();
		BgGzlxService service = (BgGzlxService)getService();
		service.queryForSjxDomain(domain, userDomain);
		return "queryForSjx";
	}
	
	public String queryForCgx() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgGzlxDomain domain = (BgGzlxDomain)getDomain();
		BgGzlxService service = (BgGzlxService)getService();
		service.queryForCgxDomain(domain, userDomain);
		return "queryForCgx";		
	}
	
	public String fjxz() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgGzlxDomain domain = (BgGzlxDomain)getDomain();
		BgGzlxService service = (BgGzlxService)getService();
		service.queryFjDomain(domain, userDomain);
		domain.setFjmc(SysEncodeUtil.GBK2ISO(domain.getFjmc()));
		return "fjxz";
	}
	
	
	public String deleteFj() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgGzlxDomain domain = (BgGzlxDomain)getDomain();
		BgGzlxService service = (BgGzlxService)getService();
		service.deleteFjDomain(domain, userDomain);
		return "deleteFj";
	}
	
	public String updateCkbz() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgGzlxDomain domain = (BgGzlxDomain)getDomain();
		BgGzlxService service = (BgGzlxService)getService();
		service.updateJsrDomain(domain, userDomain);
		return "updateCkbz";
	}
	
	
	
	/** 文件下载输出流*/
	@JSON(serialize=false)
	public InputStream getInputStream() {
		BgGzlxDomain domain = (BgGzlxDomain)getDomain();
		byte[] xznr = domain.getFjnr();

		//如果下载内容为空,则空,预防报错
		if(xznr==null){
			xznr=new byte[0];
		}
		return new ByteArrayInputStream(xznr);
	}

	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new BgGzlxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (BgGzlxDomain) domain;
	}

	@Resource(name = "bgGzlxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadFileName() {
		if(uploadFileName == null){
			uploadFileName = new ArrayList<String>();
		}
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}
