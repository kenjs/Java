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
 * ϵͳ������ʼ��
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
		//����spring��@Autowiredע�ⲻ�ܶ�Servlet��Filter�����ã�������Servlet�ж�bean��Ҫ����صĵĲ���
		ServletContext servletContext = getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		frmConEncodeSqlMapClientTemplate = (SysSqlMapClientTemplate) webApplicationContext.getBean("frmConEncodeSqlMapClientTemplate");
		// ��ʼ��ϵͳ������
		initWebConstants();
		// ��ʼ���쳣��Ϣ
		initExceptionConfig();
		// ��ʼ��ϵͳ��־
		initSystemLog();
		// ��ʼ��ϵͳĿ¼_����ģ��_������ʾ
		//initXtmlGnmkCzts();
		// ���ӷ��� ����Ӧ�ÿ��Ը�д�˷����Գ�ʼ�����Ӧ����Ϣ
		//initHstConfig();
	}

	/**
	 * ���ӷ���
	 * 
	 * @since 2009-7-24
	 * @author 
	 * @version 1.00 2009-7-24
	 */
	public void initHstConfig() {
		;
	}

	/**
	 * ��ʼ��ϵͳ��������
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
			// ����map��key=valueȡֵ��������Զ�̬���������ļ��������е�������Ϣ begin
			Map<String,String> hstPropMap = new HashMap<String,String>();
			for (Iterator iter = pro.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				hstPropMap.put(key, pro.getProperty(key, ""));
			}
			WebConstants.APP_PROP_MAP = hstPropMap;
			// ����map��key=valueȡֵ��������Զ�̬���������ļ��������е�������Ϣ end
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
			WebConstants.FWKZ_IGNORE_SET.add("/"); // Ĭ�Ϲ���
			for (int i = 0; i < fwkzStrs.length; i++) {
				WebConstants.FWKZ_IGNORE_SET.add(fwkzStrs[i]);
			}

		} catch (Exception e) {
			_logger.info(e.getMessage());
		}

	}

	/**
	 * ��ʼ���쳣��Ϣ
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
	
	/**��ʼ��ϵͳ��־
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
	 * ��ʼ��ϵͳĿ¼_����ģ��_������ʾ
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
