package com.cy.hygl.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.bo.HyWlssdjZp;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.common.util.ImageUpload;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyMbTydDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.HyWlSsDjService;


/**
 * THE ACTION FOR 物流损失照片上传
 * @author FWC
 */
@Controller
@Scope("prototype")
@Action(value="/hygl/wlssdjzp",results={
		@Result(name = "initMx", location = "/work/hygl/hy_wlssdj_zp.jsp"),
		@Result(name = "uploadFile", location = "/work/hygl/hy_wlssdj_zp.jsp"),
		@Result(name = "showPhoto",type = "json"),
		@Result(name= "deletePhoto", type = "json"),
		@Result(name= "deletePhotoes", type = "json"),
		@Result(name= "uploadFilegl", location="/work/hygl/hy_wlssdjgl_mx.jsp")
		
})
public class HyWlssDjZpAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long serialVersionUID = 1L;
	private File zpfile;
	private String zpfileFileName;//原想文件名
	private String zpfileName;//自定义文件名
	private String wlssdjxh;
	private String zpscxh;



	//物流损失照片上传页面初始化
	public String uploadFile() throws Exception{
		UserDomain userDomain = getUserDomain();
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		
		
		Properties pro = new Properties();
		pro.load(this.getClass().getResourceAsStream("/config/systemContext.properties"));
		String path=pro.getProperty("WLSS_FILEPATH");
		String filepath=ImageUpload.saveImg(zpfile,zpfileFileName, path);
		
		if(zpfileName.length()<1){
			zpfileName=filepath.substring(filepath.lastIndexOf("\\")+1, filepath.lastIndexOf("."));
		} 
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);
		
		domain.setZpdz(filepath);
		domain.setZpmc(zpfileName);
		domain.setXgsj(time);
		
		((HyWlSsDjService)getService()).saveSsZp(domain, userDomain);
		initMx();
		return "uploadFile";
	}
	
	public String uploadFilegl() throws Exception{
		UserDomain userDomain = getUserDomain();
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		
		
		Properties pro = new Properties();
		pro.load(this.getClass().getResourceAsStream("/config/systemContext.properties"));
		String path=pro.getProperty("WLSS_FILEPATH");
		String filepath=ImageUpload.saveImg(zpfile,zpfileFileName, path);
		
		if(zpfileName.length()<1){
			zpfileName=filepath.substring(filepath.lastIndexOf("\\")+1, filepath.lastIndexOf("."));
		} 
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);
		
		domain.setZpdz(filepath);
		domain.setZpmc(zpfileName);
		domain.setXgsj(time);
		
		((HyWlSsDjService)getService()).saveSsZp(domain, userDomain);
		initMx();
		return "uploadFilegl";
	}
	
	/**
	 * 
	* @Description: AJAX调用输出照片序号
	* @Note
	* @author FWC
	* @since 2013-10-14
	* @return
	* @throws Exception
	 */
	public String showPhoto() throws Exception{
		PrintWriter out=null;
		List<HyWlssdjZp> list=((HyWlSsDjService)getService()).queryPhoto(wlssdjxh);
		
		try {
			out=response.getWriter();
			JSONArray json=JSONArray.fromObject(list);
			
			out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
		return "showPhoto";
	}
	/**
	 * 
	* @Description: 删除照片
	* @Note
	* @author 
	* @since 2013-10-14
	* @return
	 * @throws Exception 
	 */
	public String deletePhoto() throws Exception{
		UserDomain userDomain = getUserDomain();
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		
		
		String zpdz=((HyWlSsDjService)getService()).deletePhoto(zpscxh,domain,userDomain);
		if(!(zpdz.length()<1)){
			ImageUpload.deleteImg(zpdz);
		}
		return "deletePhoto";
	}
	/**
	 * 
	* @Description: 批量删除照片
	* @Note
	* @author 
	* @since 2013-10-14
	* @return
	* @throws Exception
	 */
	public String deletePhotoes() throws Exception{
		UserDomain userDomain = getUserDomain();
		HyZyglFydjDomain domain=(HyZyglFydjDomain)getDomain();
		
		List<String> list=((HyWlSsDjService)getService()).deletePhotoes(domain,userDomain);
		for (String zpdz : list) {
			if(!(zpdz.length()<1)){
				ImageUpload.deleteImg(zpdz);
			}
		}
		return "deletePhotoes";
		
	}
	
	
	
	public File getZpfile() {
		return zpfile;
	}
	public void setZpfile(File zpfile) {
		this.zpfile = zpfile;
	}

	public String getZpfileFileName() {
		return zpfileFileName;
	}

	public void setZpfileFileName(String zpfileFileName) {
		this.zpfileFileName = zpfileFileName;
	}
	
	public String getZpfileName() {
		return zpfileName;
	}

	public void setZpfileName(String zpfileName) {
		this.zpfileName = zpfileName;
	}


	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyZyglFydjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyMbTydDomain) domain;
	}

	@Resource(name = "hyWlSsDjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public String getWlssdjxh() {
		return wlssdjxh;
	}

	public void setWlssdjxh(String wlssdjxh) {
		this.wlssdjxh = wlssdjxh;
	}

	public String getZpscxh() {
		return zpscxh;
	}

	public void setZpscxh(String zpscxh) {
		this.zpscxh = zpscxh;
	}
	

}
