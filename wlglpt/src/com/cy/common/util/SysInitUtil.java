package com.cy.common.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.XtXtcsDomain;
import com.cy.common.domain.XtXtmlGnmkCztsDomain;
import com.cy.framework.common.SysSqlMapClientTemplate;
import com.cy.framework.constants.WebConstants;
import com.cy.framework.domain.XtErrMsgDomain;


/**
 * 系统启动初始化
 * 
 * @since 2009-5-22
 * @author 
 * @version 1.00 2009-5-22
 */
@Service
public class SysInitUtil extends HttpServlet {

	/** serialVersionUID */
	private static final long serialVersionUID = 3574019451372146643L;
	
	public static Log _logger = LogFactory.getLog(SysInitUtil.class);
	
	private SysSqlMapClientTemplate frmConEncodeSqlMapClientTemplate;

	public void init(){
		//由于spring的@Autowired注解不能对Servlet，Filter起作用，所以在Servlet中对bean需要做相关的的操作
		ServletContext servletContext = getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		frmConEncodeSqlMapClientTemplate = (SysSqlMapClientTemplate) webApplicationContext.getBean("frmConEncodeSqlMapClientTemplate");
		// 初始化系统参数。
		initWebConstants();
		// 初始化异常信息
		initExceptionConfig();
		// 初始化系统日志
		initSystemLog();
		// 初始化系统目录_功能模块_操作提示
		//initXtmlGnmkCzts();
		// 钩子方法 具体应用可以覆写此方法以初始具体的应用信息
		//initHstConfig();
	}

	/**
	 * 钩子方法
	 * 
	 * @since 2009-7-24
	 * @author 
	 * @version 1.00 2009-7-24
	 */
	public void initHstConfig() {
		;
	}

	/**
	 * 初始化系统各参数。
	 * 
	 * @since 2009-5-22
	 * @author 
	 * @version 1.11 2009-5-22
	 */
	@SuppressWarnings("unchecked")
	private void initWebConstants() {
		Properties pro = new Properties();
		try {
			pro.load(this.getClass().getResourceAsStream("/config/systemContext.properties"));
			// 增加map的key=value取值，方便可以动态解析配置文件里面所有的配置信息 begin
			Map<String,String> hstPropMap = new HashMap<String,String>();
			for (Iterator iter = pro.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				hstPropMap.put(key, pro.getProperty(key, ""));
			}
			WebConstants.APP_PROP_MAP = hstPropMap;
			// 增加map的key=value取值，方便可以动态解析配置文件里面所有的配置信息 end
			WebConstants.SES_USER_INFO = pro.getProperty("SES_USER_INFO", "");
			WebConstants.SES_USER_MENU = pro.getProperty("SES_USER_MENU", "");			
			WebConstants.GLOBAL_WEB_ROOT = pro.getProperty("GLOBAL_WEB_ROOT", "");			
			WebConstants.SCHEMA_WEBAPP = pro.getProperty("SCHEMA_WEBAPP", "");			
			WebConstants.DOWNLOAD_SRC_PATH = pro.getProperty("DOWNLOAD_SRC_PATH", "");			
			WebConstants.DOWNLOAD_FILE_NAME = pro.getProperty("DOWNLOAD_FILE_NAME", "");			
			WebConstants.DOWNLOAD_MAX_SIZE = Long.parseLong(pro.getProperty("DOWNLOAD_MAX_SIZE", ""));
			WebConstants.DOWNLOAD_BUFFER_SIZE = Integer.parseInt(pro.getProperty("DOWNLOAD_BUFFER_SIZE", ""));			
			WebConstants.UPLOAD_MAX_SIZE = Integer.parseInt(pro.getProperty("UPLOAD_MAX_SIZE", ""));
			WebConstants.DEFAULT_BUFFER_SIZE = Integer.parseInt(pro.getProperty("DEFAULT_BUFFER_SIZE", ""));			
			WebConstants.DEFAULT_PAGE_NUM = Integer.parseInt(pro.getProperty("DEFAULT_PAGE_NUM", ""));
			WebConstants.TEMP_PATH = pro.getProperty("TEMP_PATH", "");			
			WebConstants.DATA_SOURCE_PROJECT = pro.getProperty("DATA_SOURCE_PROJECT", "");
			WebConstants.DATABASE_ENCODE = pro.getProperty("DATABASE_ENCODE", "");
			WebConstants.SUPPER_CZRY_DM = pro.getProperty("SUPPER_CZRY_DM", "");
			
			String[] fwkzStrs = pro.getProperty("FWKZ_IGNORE_SET", "").split(",");
			WebConstants.FWKZ_IGNORE_SET = new HashSet<String>();
			WebConstants.FWKZ_IGNORE_SET.add("/"); // 默认过滤
			for (int i = 0; i < fwkzStrs.length; i++) {
				WebConstants.FWKZ_IGNORE_SET.add(fwkzStrs[i]);
			}

		} catch (Exception e) {
			_logger.info(e.getMessage());
		}

	}

	/**
	 * 初始化异常信息
	 * 
	 * @since 2011-6-20
	 * @author 
	 * @version 1.00 
	 */
	@SuppressWarnings("unchecked")
	private void initExceptionConfig() {
		XtglConstants.ERROR_MES = new HashMap<String, XtErrMsgDomain>();
		List<XtErrMsgDomain> dataList = (List<XtErrMsgDomain>)frmConEncodeSqlMapClientTemplate.queryForList("selectXtErrMsgList");
//		try {
//			HstEncodeUtil.conISO2GBK(dataList);
//		} catch (Exception e) {
//			_logger.info(e.getMessage());
//		}		
		for(XtErrMsgDomain domain:dataList){
			XtglConstants.ERROR_MES.put(domain.getErrCode(), domain);
		}
	}
	
	/**初始化系统日志
	 * @since 2011-6-20
	 * @author 
	 * @version 1.00 
	 */
	@SuppressWarnings("unchecked")
	private void  initSystemLog() {
		XtglConstants.XT_XTCS = new HashMap<String, XtXtcsDomain>();
		List<XtXtcsDomain> dataList = (List<XtXtcsDomain>)frmConEncodeSqlMapClientTemplate.queryForList("selectXtXtcsList");
//		try {
//			HstEncodeUtil.conISO2GBK(dataList);
//		} catch (Exception e) {
//			_logger.info(e.getMessage());
//		}		
		for(XtXtcsDomain domain : dataList){
			XtglConstants.XT_XTCS.put(domain.getCsxh(), domain);
		}
	}
	
	/**
	 * 初始化系统目录_功能模块_操作提示
	 * @since 2011-6-21
	 * @author 
	 * @version 1.00 
	 */
	@SuppressWarnings("unchecked")
	private void  initXtmlGnmkCzts() {
		XtglConstants.XT_XTML_GNMK_CZTS = new HashMap<String, XtXtmlGnmkCztsDomain>();
		List<XtXtmlGnmkCztsDomain> dataList = (List<XtXtmlGnmkCztsDomain>)frmConEncodeSqlMapClientTemplate.queryForList("selectXtXtmlGnmkCztsList");
//		try {
//			HstEncodeUtil.conISO2GBK(dataList);
//		} catch (Exception e) {
//			_logger.info(e.getMessage());
//		}		
		for(XtXtmlGnmkCztsDomain domain : dataList){
			XtglConstants.XT_XTML_GNMK_CZTS.put(domain.getXtmlXh()+"-"+domain.getGnmkDm(), domain);
		}
	}
	
}
