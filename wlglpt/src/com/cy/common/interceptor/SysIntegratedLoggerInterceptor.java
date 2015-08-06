package com.cy.common.interceptor;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.cy.common.bo.XtLogAction;
import com.cy.common.bo.XtLogLogin;
import com.cy.common.constants.XtglConstants;
import com.cy.common.domain.UserDomain;
import com.cy.framework.constants.WebConstants;
import com.cy.framework.util.SysDateUtil;
import com.cy.framework.util.SysRequestUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 记录系统操作日志
 * 
 * @Descriptoin
 * @Note
 * @author anq
 * @since 2011-6-22 下午09:12:55
 * @version
 */
@SuppressWarnings("serial")
public class SysIntegratedLoggerInterceptor extends AbstractInterceptor {

	@Autowired
	private SqlMapClientTemplate frmSqlMapClientTemplate;

	public static Log _logger = LogFactory.getLog(SysIntegratedLoggerInterceptor.class);
	private static final String START_MESSAGE = "Starting execution stack for action ";
	private static final String FINISH_MESSAGE = "Finishing execution stack for action ";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logMessage(invocation, START_MESSAGE);
		// 开始时间
		String kssj = SysDateUtil.format(SysDateUtil.getCurrentTime(), SysDateUtil.TIME_DATETIME_FORMAT);
		long start = System.currentTimeMillis();
		String result = invocation.invoke();
		// 结束时间
		String jssj = SysDateUtil.format(SysDateUtil.getCurrentTime(), SysDateUtil.TIME_DATETIME_FORMAT);
		long end = System.currentTimeMillis();
		log2Db(invocation, kssj, jssj, end - start);
		logMessage(invocation, FINISH_MESSAGE);
		return result;
	}

	/**
	 * @Description: 记录信息到数据库
	 * @Note
	 * @author yzs
	 * @since 2011-6-20
	 * @param invocation
	 * @param kssj
	 * @param jssj
	 * @param timeUsed
	 * @throws Exception
	 * @throws DataAccessException
	 */
	private void log2Db(ActionInvocation invocation, String kssj, String jssj, long timeUsed) {
		StringBuilder action = new StringBuilder();
		try {
			String namespace = invocation.getProxy().getNamespace();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				action.append(namespace).append("/");
			}

			action.append(invocation.getProxy().getActionName());
			action.append("!").append(invocation.getProxy().getMethod());

			String actionStr = action.toString();
			UserDomain userDomain = (UserDomain) invocation.getInvocationContext().getSession().get(WebConstants.SES_USER_INFO);
			if (null == userDomain || StringUtils.isBlank(userDomain.getCzyDjxh())) {
				userDomain = new UserDomain();
				userDomain.setCzyDjxh("0000000000000000");
				userDomain.setXtyhflDm("00");
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			String sessionId = request.getSession().getId();
			String serverIp = InetAddress.getLocalHost().getHostAddress();
			String serverPort = request.getServerPort()+"";

			// 记录登录到数据库
			if ("Y".equals(XtglConstants.XT_XTCS.get(XtglConstants.XT_XTCS_LOGIN).getCsmrz())) {
				if (XtglConstants.LOGIN_IN_ACTION.equals(actionStr)) {
					XtLogLogin xll = new XtLogLogin();

					xll.setCzyDjXh(userDomain.getCzyDjxh());
					xll.setXtyhflDm(StringUtils.isBlank(userDomain.getXtyhflDm()) ? "00" : userDomain.getXtyhflDm());
					xll.setDlsj(SysDateUtil.format(SysDateUtil.getCurrentTime(), SysDateUtil.TIME_DATETIME_FORMAT));
					String ip = SysRequestUtil.getIpAddr(request);
					xll.setIp(ip);
					xll.setMac(userDomain.getMacAddr());
					xll.setSessionId(sessionId);
					xll.setServerip(serverIp);
					xll.setServerport(serverPort);

					frmSqlMapClientTemplate.insert("insertXtLogLogin", xll);
				}
			}

			// 记录操作到数据库
			if ("Y".equals(XtglConstants.XT_XTCS.get(XtglConstants.XT_XTCS_ACTION).getCsmrz())) {
				XtLogAction xla = new XtLogAction();
				xla.setCzyDjXh(userDomain.getCzyDjxh());
				xla.setXtyhflDm(StringUtils.isBlank(userDomain.getXtyhflDm()) ? "00" : userDomain.getXtyhflDm());
				String xtmlXh = ServletActionContext.getRequest().getParameter("domain.xtmlXh");				
				String gnmkDm = ServletActionContext.getRequest().getParameter("domain.gnmkDm");
				if (StringUtils.isNotBlank(xtmlXh)) {
					xla.setXtmlXh(Long.parseLong(xtmlXh));
				}
				xla.setGnmkDm(gnmkDm);
				xla.setAction(actionStr);
				xla.setKssj(kssj);
				xla.setJssj(jssj);
				xla.setTimeUsed(timeUsed * 1.00);
				xla.setSessionId(sessionId);
				xla.setServerip(serverIp);
				xla.setServerport(serverPort);

				frmSqlMapClientTemplate.insert("insertXtLogAction", xla);
			}

		} catch (Exception ee) {
			_logger.error(ee);
		}
	}

	private void logMessage(ActionInvocation invocation, String baseMessage) {
		if (_logger.isInfoEnabled()) {
			StringBuilder message = new StringBuilder(baseMessage);
			String namespace = invocation.getProxy().getNamespace();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				message.append(namespace).append("/");
			}

			message.append(invocation.getProxy().getActionName());
			message.append("!").append(invocation.getProxy().getMethod());
			_logger.info(message.toString());
		}
	}

}
