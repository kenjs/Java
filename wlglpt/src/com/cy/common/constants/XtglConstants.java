package com.cy.common.constants;

import java.util.HashMap;
import java.util.Map;

import com.cy.common.domain.XtXtcsDomain;
import com.cy.common.domain.XtXtmlGnmkCztsDomain;
import com.cy.framework.domain.XtErrMsgDomain;




/**
 * 应用程序常量信息
 * 
 * @author admin
 * 
 */
public class XtglConstants {

	/************* 系统登录ACTION 用于登录日志记录*********************** */
	public static final String LOGIN_IN_ACTION = "//login!loginIn";
	
	/*********ibatis排序sql语句，类似于order by xxx ,yyy desc, zzz asc**********/
	public static final String ORDER_STR = "orderStr";
	
	/************* 以下为ACTION中跳转的Result列表 begin *********************** */
	public static String RESULT_NAME_INIT = "init";
	public static String RESULT_NAME_INIT_MX = "initMx";
	public static String RESULT_NAME_QUERY = "query";
	public static String RESULT_NAME_QUERY_MX = "queryMx";
	public static String RESULT_NAME_SAVE = "save";
	public static String RESULT_NAME_SAVE_MX = "saveMx";
	public static String RESULT_NAME_DELETE = "delete";
	public static String RESULT_NAME_DELETE_MX = "deleteMx";
	public static String RESULT_NAME_PRINT = "print";
	public static String RESULT_NAME_PRINT_MX = "printMx";
	public static String RESULT_NAME_ENABLE = "saveEnable";
	public static String RESULT_NAME_DISABLE = "saveDisable";
	/************* 以下为ACTION中跳转的Result列表 end *********************** */
	
	/*************系统错误信息 begin************************/
	//数据库异常！详细错误信息：%1
	//111001 
	public static String ERR_CODE_DB_EXCEPTION = "111001";
	//数据库异常，INSERT数据错误！详细错误信息：%1
	//111002 
	public static String ERR_CODE_DB_EXCEPTION_DATA_INSERT_ERR = "111002";
	//数据库异常，UPDATE数据错误！详细错误信息：%1
	//111003 
	public static String ERR_CODE_DB_EXCEPTION_DATA_UPDATE_ERR = "111003";
	//数据库异常，DELETE数据错误！详细错误信息：%1
	//111004 
	public static String ERR_CODE_DB_EXCEPTION_DATA_DELETE_ERR = "111004";
	//数据库异常，NO_DATA_FOUND错误！
	//111005 
	public static String ERR_CODE_DB_EXCEPTION_DATA_NO_FOUND_ERR = "111005";
	//数据库异常，参数不能为空！详细错误信息：%1
	//111006 
	public static String ERR_CODE_DB_EXCEPTION_PARAM_NOT_NULL_ERR = "111006";
	//应用处理异常！
	//112001 
	public static String ERR_CODE_APP_EXCEPTION = "112001";
	//应用处理异常，%1参数不能为空！
	//112001 
	public static String ERR_CODE_APP_EXCEPTION_PARAM_NULL = "112002";
	//用户名或密码错！
	//121002 
	public static String ERR_CODE_USERNAME_PASSWORD_WRONG = "121002";
	//您的帐号已被停用，不允许进入系统！
	//121004 
	public static String ERR_CODE_USERNAME_DEPRECATED = "121004";
	//您的管理员帐号不存在！
	//121005 
	public static String ERR_CODE_ADMIN_NO_FOUND = "121005";
	//自定义错误信息，详细错误信息完全自定义，取自errMess
	public static String ERR_CODE_DIY_APP_EXCEPTION = "200001";
	
	/*************系统参数信息 begin**********************/
	//是否记录系统LOGIN日志 10004
	public static String XT_XTCS_LOGIN = "10004";
	//是否记录操作员操作日志 10005
	public static String XT_XTCS_ACTION = "10005";
	//是否记录系统的异常日志 10006
	public static String XT_XTCS_EXCEPTION = "10006";
	//功能模块打开时是否显示操作提示 50001
	public static String XT_XTCS_OPERATE_MSG = "50001";
	//表格分页时，每页显示的最大记录数 50002	
	public static String XT_XTCS_PAGE_SIZE= "50002";
	
	/*************系统参数信息 end************************/
	
	/**
	 * 成功代码
	 */
	public static String SUCCESS_CODE = "0";
	
	/**
	 * 用户个数统计
	 */
	public static long USER_NUMBER = 0;

	/**
	 * 用户信息统计,以SessionID为key
	 */
	public static Map<String, Boolean> USER_STAT = new HashMap<String, Boolean>();

	/**
	 * 错误信息Map
	 */
	public static Map<String, XtErrMsgDomain> ERROR_MES = new HashMap<String, XtErrMsgDomain>();

	/**
	 * 系统参数Map
	 */
	public static Map<String, XtXtcsDomain> XT_XTCS = new HashMap<String, XtXtcsDomain>();
	
	/**
	 * 系统目录_功能模块_操作提示Map
	 */
	public static Map<String, XtXtmlGnmkCztsDomain> XT_XTML_GNMK_CZTS = new HashMap<String, XtXtmlGnmkCztsDomain>();
	
}
