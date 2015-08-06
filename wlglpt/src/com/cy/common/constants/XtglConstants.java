package com.cy.common.constants;

import java.util.HashMap;
import java.util.Map;

import com.cy.common.domain.XtXtcsDomain;
import com.cy.common.domain.XtXtmlGnmkCztsDomain;
import com.cy.framework.domain.XtErrMsgDomain;




/**
 * Ӧ�ó�������Ϣ
 * 
 * @author admin
 * 
 */
public class XtglConstants {

	/************* ϵͳ��¼ACTION ���ڵ�¼��־��¼*********************** */
	public static final String LOGIN_IN_ACTION = "//login!loginIn";
	
	/*********ibatis����sql��䣬������order by xxx ,yyy desc, zzz asc**********/
	public static final String ORDER_STR = "orderStr";
	
	/************* ����ΪACTION����ת��Result�б� begin *********************** */
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
	/************* ����ΪACTION����ת��Result�б� end *********************** */
	
	/*************ϵͳ������Ϣ begin************************/
	//���ݿ��쳣����ϸ������Ϣ��%1
	//111001 
	public static String ERR_CODE_DB_EXCEPTION = "111001";
	//���ݿ��쳣��INSERT���ݴ�����ϸ������Ϣ��%1
	//111002 
	public static String ERR_CODE_DB_EXCEPTION_DATA_INSERT_ERR = "111002";
	//���ݿ��쳣��UPDATE���ݴ�����ϸ������Ϣ��%1
	//111003 
	public static String ERR_CODE_DB_EXCEPTION_DATA_UPDATE_ERR = "111003";
	//���ݿ��쳣��DELETE���ݴ�����ϸ������Ϣ��%1
	//111004 
	public static String ERR_CODE_DB_EXCEPTION_DATA_DELETE_ERR = "111004";
	//���ݿ��쳣��NO_DATA_FOUND����
	//111005 
	public static String ERR_CODE_DB_EXCEPTION_DATA_NO_FOUND_ERR = "111005";
	//���ݿ��쳣����������Ϊ�գ���ϸ������Ϣ��%1
	//111006 
	public static String ERR_CODE_DB_EXCEPTION_PARAM_NOT_NULL_ERR = "111006";
	//Ӧ�ô����쳣��
	//112001 
	public static String ERR_CODE_APP_EXCEPTION = "112001";
	//Ӧ�ô����쳣��%1��������Ϊ�գ�
	//112001 
	public static String ERR_CODE_APP_EXCEPTION_PARAM_NULL = "112002";
	//�û����������
	//121002 
	public static String ERR_CODE_USERNAME_PASSWORD_WRONG = "121002";
	//�����ʺ��ѱ�ͣ�ã����������ϵͳ��
	//121004 
	public static String ERR_CODE_USERNAME_DEPRECATED = "121004";
	//���Ĺ���Ա�ʺŲ����ڣ�
	//121005 
	public static String ERR_CODE_ADMIN_NO_FOUND = "121005";
	//�Զ��������Ϣ����ϸ������Ϣ��ȫ�Զ��壬ȡ��errMess
	public static String ERR_CODE_DIY_APP_EXCEPTION = "200001";
	
	/*************ϵͳ������Ϣ begin**********************/
	//�Ƿ��¼ϵͳLOGIN��־ 10004
	public static String XT_XTCS_LOGIN = "10004";
	//�Ƿ��¼����Ա������־ 10005
	public static String XT_XTCS_ACTION = "10005";
	//�Ƿ��¼ϵͳ���쳣��־ 10006
	public static String XT_XTCS_EXCEPTION = "10006";
	//����ģ���ʱ�Ƿ���ʾ������ʾ 50001
	public static String XT_XTCS_OPERATE_MSG = "50001";
	//����ҳʱ��ÿҳ��ʾ������¼�� 50002	
	public static String XT_XTCS_PAGE_SIZE= "50002";
	
	/*************ϵͳ������Ϣ end************************/
	
	/**
	 * �ɹ�����
	 */
	public static String SUCCESS_CODE = "0";
	
	/**
	 * �û�����ͳ��
	 */
	public static long USER_NUMBER = 0;

	/**
	 * �û���Ϣͳ��,��SessionIDΪkey
	 */
	public static Map<String, Boolean> USER_STAT = new HashMap<String, Boolean>();

	/**
	 * ������ϢMap
	 */
	public static Map<String, XtErrMsgDomain> ERROR_MES = new HashMap<String, XtErrMsgDomain>();

	/**
	 * ϵͳ����Map
	 */
	public static Map<String, XtXtcsDomain> XT_XTCS = new HashMap<String, XtXtcsDomain>();
	
	/**
	 * ϵͳĿ¼_����ģ��_������ʾMap
	 */
	public static Map<String, XtXtmlGnmkCztsDomain> XT_XTML_GNMK_CZTS = new HashMap<String, XtXtmlGnmkCztsDomain>();
	
}
