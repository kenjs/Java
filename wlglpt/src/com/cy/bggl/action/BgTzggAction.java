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
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.common.action.ExtendAction;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.bggl.domain.BgTzggDomain;
import com.cy.bggl.service.BgTzggService;

/**
 * THE ACTION FOR �칫-֪ͨ����
 * @author HJH
 */
@Controller
@Scope("prototype")
@Action(value = "/bggl/bgtzgg", results = {
		@Result(name = "init", location = "/work/bggl/bgtzgg.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/bggl/bgtzgg_mx.jsp"),
		@Result(name = "save", location = "/work/bggl/bgtzgg_mx.jsp"),
		@Result(name = "delete", type = "json"),
		@Result(name = "deleteFj", type = "json"),
		@Result(name = "queryMx", location = "/work/bggl/bgtzgg_mx_ck.jsp"),
		@Result(name = "fjxz" , type = "stream", 
				params = { 
				"contentType", "application/octet-stream;charset=GBK", 
				"inputName","inputStream", 
				"contentDisposition", "attachment;filename=${domain.fjmc}", 
				"bufferSize", "3673600" 
			})
		})
@SuppressWarnings("unchecked")
public class BgTzggAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	
	private List<File> upload;   //�����ϴ����ļ�
	private List<String> uploadFileName;   //�����ϴ����ļ���
	
	public String save() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgTzggDomain domain = (BgTzggDomain) getDomain();
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
		return "save";
	}
	
	public String deleteFj() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgTzggService service=(BgTzggService)this.getService();
		service.deleteMx(domain, userDomain);
		return "deleteFj";
	}


	@Override
	public String queryMx() throws Exception {
		//super.queryMx();
		UserDomain userDomain = getUserDomain();
		BgTzggService service=(BgTzggService)this.getService();
		service.saveMx(domain, userDomain);
		service.queryMx(domain, userDomain);
		return "queryMx";
	}
	
	public String fjxz() throws Exception {
		UserDomain userDomain = getUserDomain();
		BgTzggDomain domain = (BgTzggDomain) getDomain();
		BgTzggService service=(BgTzggService)this.getService();
		service.queryFj(domain, userDomain);
		domain.setFjmc(SysEncodeUtil.GBK2ISO(domain.getFjmc()));
		return "fjxz";
	}

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgTzggDomain domain = (BgTzggDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("״̬");
		headList.add("����");
		headList.add("������Χ");
		headList.add("�¼�����");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			BgTzggDomain element = (BgTzggDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			if("2".equals(element.getBcztDm()))
				list.add("�ѷ���");
			if("1".equals(element.getBcztDm()))
				list.add("�ݴ�");
			
			list.add(element.getZt());
			list.add(element.getJgmc());
			if("Y".equals(element.getXjgxbz()))
				list.add("��");
			else
				list.add("��");
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	/** �ļ����������*/
	@JSON(serialize=false)
	public InputStream getInputStream() {
		BgTzggDomain domain = (BgTzggDomain) getDomain();
		byte[] xznr = domain.getFjnr();

		//�����������Ϊ��,���,Ԥ������
		if(xznr==null){
			xznr=new byte[0];
		}
		return new ByteArrayInputStream(xznr);
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new BgTzggDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (BgTzggDomain) domain;
	}

	@Resource(name = "bgTzggServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public List<File> getUpload() {
		return upload;
	}

	public List<String> getUploadFileName() {
		if(uploadFileName == null){
			uploadFileName = new ArrayList<String>();
		}
		return uploadFileName;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}
