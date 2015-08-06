package com.cy.common.interceptor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.cy.common.bo.XtLogException;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.common.exception.DiyServiceException;
import com.cy.framework.constants.WebConstants;
import com.cy.framework.domain.XtErrMsgDomain;
import com.cy.framework.exception.ServiceException;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysEncodeUtil;
import com.cy.framework.util.SysToolsUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;

/**
 * 
* @Descriptoin ��¼ϵͳ�쳣��־
* @Note
* @author Administrator
* @since 2012-12-20 ����10:10:47 
* @version
 */
@SuppressWarnings("serial")
public class SysIntegratedExceptionInterceptor extends AbstractInterceptor {

	@Autowired
	private SqlMapClientTemplate frmSqlMapClientTemplate;

	public static Log _logger = LogFactory.getLog(SysIntegratedExceptionInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result;

		try {
			result = invocation.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			boolean ajaxMethod = false;
			ajaxMethod = isAjaxRequest();

			// �����쳣������errorcode
			ServiceException se = handleException(e);

			// ��¼��־���ļ�
			if (_logger.isInfoEnabled()) {
				handleLogging(e);
			}
			// ��¼��־�����ݿ�
			// �Ƿ��¼ϵͳLOGIN��־ Y ��¼,N ����¼
			if ("Y".equals(XtglConstants.XT_XTCS.get(XtglConstants.XT_XTCS_EXCEPTION).getCsmrz())) {
				log2Db(invocation, e);
			}

			// String aMessage = "������룺" + se.getErrorCode() + "<br> ������Ϣ��" + se.getErrorMess() + "<br> ����ʽ��" + se.getErrorDo();
			String aMessage = "������룺" + se.getErrorCode() + "<br> ������Ϣ��" + dealErrorMess(se.getErrorMess(), se.getParamList());
			if (StringUtils.isNotBlank(se.getErrorDo())) {
				aMessage += "<br> ����ʽ��<br>" + se.getErrorDo();
			}
			if (ajaxMethod) {
				// �����쳣�ж�
				HttpServletResponse response = ServletActionContext.getResponse();
				String contentType = "text/plain;charset=UTF-8";
				response.setContentType(contentType);
				response.setStatus(500);
				response.getWriter().write(aMessage);
				return null;
			} else {
				// �����Զ�����쳣������Ϣ
				Object action = invocation.getAction();
				if (action instanceof ValidationAware) {
					ValidationAware validationAwareAction = (ValidationAware) action;
					validationAwareAction.addActionError(aMessage);
				} else {
					_logger.debug("Action [" + action + "] is not ValidationAware, no message / error that are storeable");
				}

				// �˴�Ҫ��action�з����ķ���ֵ����ͷ�������ͬ
				return invocation.getProxy().getMethod();

			}
		}

		return result;
	}

	/**
	 * 
	 * @Description: ��ȡ����֮��Ĵ�����Ϣ���滻����$1$��
	 * @Note ��������Ϊ$1$��$2$�ۼӣ���˳���paramList��˳�򱣳�һ�£���$1$��Ӧ�ľ���paramList�ĵ�1��ֵ��$2$��Ӧ�ľ���paramList�ĵ�2��ֵ
	 * @author 
	 * @since 2011-6-23
	 * @param errorMess
	 * @param paramList
	 * @return
	 */
	private String dealErrorMess(String errorMess, List<String> paramList) {
		String result = errorMess;
		for (int i = 0; i < paramList.size(); i++) {
			result = result.replaceAll("\\$" + (i + 1) + "\\$", paramList.get(i));
		}
		if(result.length() > 200){
			result = result.substring(0,200)+"......";
		}
		return result;
	}

	/**
	 * @Description:�ж��Ƿ���ajax������
	 * @Note
	 * @author 
	 * @since 2011-6-20
	 * @return
	 */
	private boolean isAjaxRequest() {
		boolean ajaxMethod;
		String requestType = ServletActionContext.getRequest().getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			ajaxMethod = true;
		} else {
			ajaxMethod = false;
		}
		return ajaxMethod;
	}

	/**
	 * @Description:
	 * @Note
	 * @author 
	 * @since 2011-6-20
	 * @param invocation
	 * @param e
	 * @throws Exception
	 * @throws DataAccessException
	 */
	private void log2Db(ActionInvocation invocation, Exception e) {
		try {
			XtLogException xle = new XtLogException();
			xle.setCssj(SysDateUtil.format(SysDateUtil.getCurrentTime(), SysDateUtil.TIME_DATETIME_FORMAT));
			xle.setCwxx(e.getMessage() + "\n" + SysToolsUtil.getStackTrace(e));
			UserDomain userDomain = (UserDomain) invocation.getInvocationContext().getSession().get(WebConstants.SES_USER_INFO);
			if (null == userDomain || StringUtils.isBlank(userDomain.getCzyDjxh())) {
				userDomain = new UserDomain();
				userDomain.setCzyDjxh("0000000000000000");
				userDomain.setXtyhflDm("00");
			}
			xle.setCzyDjXh(userDomain.getCzyDjxh());
			xle.setXtyhflDm(userDomain.getXtyhflDm());
			String xtmlXh = ServletActionContext.getRequest().getParameter("domain.xtmlXh");
			String gnmkDm = ServletActionContext.getRequest().getParameter("domain.gnmkDm");
			if (StringUtils.isNotBlank(xtmlXh)) {
				xle.setXtmlXh(Long.parseLong(xtmlXh));
			}
			xle.setGnmkDm(gnmkDm);
			String serverIp = InetAddress.getLocalHost().getHostAddress();
			String serverPort = ServletActionContext.getRequest().getServerPort()+"";
			StringBuilder action = new StringBuilder();
			String namespace = invocation.getProxy().getNamespace();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				action.append(namespace).append("/");
			}

			action.append(invocation.getProxy().getActionName());

			xle.setAction(action.toString() + "!" + invocation.getProxy().getMethod());

			xle.setServerip(serverIp);
			xle.setServerport(serverPort);
			frmSqlMapClientTemplate.insert("insertXtLogException", xle);
		} catch (Exception ee) {
			_logger.error(ee);
		}
	}

	private ServiceException handleException(Exception e) {
		// ���ݿ��쳣
		if (e instanceof DataAccessException) {
			DataAccessException dae = (DataAccessException) e;
			ServiceException se = new ServiceException(dae);
			se.setErrorCode(XtglConstants.ERR_CODE_DB_EXCEPTION);
			setErrorMess(se);
			String message = dae.getMessage();
			message = SysEncodeUtil.ISO2GBK(message);
			int oraIndex = message.indexOf("ORA-");
			if(oraIndex>0){
				message = message.substring(oraIndex);

				if(message.length() >=4){
					int secondOraIndex = message.substring(4,message.length()).indexOf("ORA");
					if(secondOraIndex>0){
						message = message.substring(0,secondOraIndex+4);
					}
				}
				
				
				//ȥ�������"�ź�\n
				message = message.replaceAll("\"", "").replaceAll("\n", "");
			}
			List<String> paramList = new ArrayList<String>();
			paramList.add(message);
			se.setParamList(paramList);
			return se;
		}
		
		if (e instanceof DiyServiceException) {
			ServiceException se = new ServiceException();
			se.setErrorCode(XtglConstants.ERR_CODE_DIY_APP_EXCEPTION);
			setErrorMess(se);
			
			List<String> paramList = new ArrayList<String>();
			paramList.add(e.getMessage());
			se.setParamList(paramList);
			
			return se;
		}
		// ҵ���쳣
		if (e instanceof ServiceException) {
			ServiceException se = (ServiceException) e;
			if (StringUtils.isBlank(se.getErrorCode())) {
				se.setErrorCode(XtglConstants.ERR_CODE_APP_EXCEPTION);
			}
			setErrorMess(se);
			return se;
		}
		// Ĭ�Ϸ���Ӧ�ó����쳣
		ServiceException se = new ServiceException(e);
		se.setErrorCode(XtglConstants.ERR_CODE_APP_EXCEPTION);

		setErrorMess(se);
		return se;
	}

	/**
	 * ���ô������õ������Ϣ
	 * 
	 * @Description: WlfpConstants.ERROR_MES������ʱ������ݿ����
	 * @Note
	 * @author 
	 * @since 2011-6-22
	 * @param se
	 */
	private void setErrorMess(ServiceException se) {
		if (StringUtils.isBlank(se.getErrorCode())) {
			se.setErrorCode(XtglConstants.ERR_CODE_APP_EXCEPTION);
		}
		if (XtglConstants.ERROR_MES.containsKey(se.getErrorCode())) {
			XtErrMsgDomain msg = XtglConstants.ERROR_MES.get(se.getErrorCode());
			se.setErrorMess(msg.getErrMess());
			se.setErrorDo(msg.getErrDo());
		}
	}

	/**
	 * Handles the logging of the exception.
	 * 
	 * @param e
	 *            the exception to log.
	 */
	protected void handleLogging(Exception e) {
		_logger.error(e.getMessage(), e);
	}

	protected String findResultFromExceptions(List<ExceptionMappingConfig> exceptionMappings, Throwable t) {
		String result = null;

		// Check for specific exception mappings.
		if (exceptionMappings != null) {
			int deepest = Integer.MAX_VALUE;
			for (Object exceptionMapping : exceptionMappings) {
				ExceptionMappingConfig exceptionMappingConfig = (ExceptionMappingConfig) exceptionMapping;
				int depth = getDepth(exceptionMappingConfig.getExceptionClassName(), t);
				if (depth >= 0 && depth < deepest) {
					deepest = depth;
					result = exceptionMappingConfig.getResult();
				}
			}
		}

		return result;
	}

	/**
	 * Return the depth to the superclass matching. 0 means ex matches exactly. Returns -1 if there's no match. Otherwise, returns depth. Lowest depth wins.
	 * 
	 * @param exceptionMapping
	 *            the mapping classname
	 * @param t
	 *            the cause
	 * @return the depth, if not found -1 is returned.
	 */
	public int getDepth(String exceptionMapping, Throwable t) {
		return getDepth(exceptionMapping, t.getClass(), 0);
	}

	private int getDepth(String exceptionMapping, Class<?> exceptionClass, int depth) {
		if (exceptionClass.getName().contains(exceptionMapping)) {
			// Found it!
			return depth;
		}
		// If we've gone as far as we can go and haven't found it...
		if (exceptionClass.equals(Throwable.class)) {
			return -1;
		}
		return getDepth(exceptionMapping, exceptionClass.getSuperclass(), depth + 1);
	}

	/**
	 * Default implementation to handle ExceptionHolder publishing. Pushes given ExceptionHolder on the stack. Subclasses may override this to customize publishing.
	 * 
	 * @param invocation
	 *            The invocation to publish Exception for.
	 * @param exceptionHolder
	 *            The exceptionHolder wrapping the Exception to publish.
	 */
	protected void publishException(ActionInvocation invocation, ExceptionHolder exceptionHolder) {
		invocation.getStack().push(exceptionHolder);
	}

}