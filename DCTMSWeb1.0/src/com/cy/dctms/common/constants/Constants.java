package com.cy.dctms.common.constants;


public class Constants {

	/**SESSION�д洢 ��ǰ��¼ϵͳ�ʺ�CODE*/
	public static final String SESSION_LOGIN_USER = "user";
	
	
	/**ϵͳ�������-��������*/
	public static final String EXCEPTION_CODE_PARAMETER_COMMON = "12000";
	/**ϵͳ�������-SESSIONΪ�մ���*/
	public static final String EXCEPTION_CODE_SESSION_EMPTY_COMMON = "12100";
	/**ϵͳ�������-ҵ���߼�����*/
	public static final String EXCEPTION_CODE_BUSINESS_COMMON = "10000";
	/**ϵͳ�������-���ݿ����*/
	public static final String EXCEPTION_CODE_DATABASE_COMMON = "11000";
	
	/**JSONϵͳ�������-��������*/
	public static final String JSON_EXCEPTION_CODE_PARAMETER_COMMON = "-1";
	/**JSONϵͳ�������-�û������ڴ���*/
	public static final String JSON_EXCEPTION_CODE_USER_EMPTY_COMMON = "-9";
	/**JSONϵͳ�������-ҵ���߼�����*/
	public static final String JSON_EXCEPTION_CODE_BUSINESS_COMMON = "-8";
	
	
	/**����״̬-ɾ��- -1*/
	public static final int ORDER_CARGO_DEPLOY_DELETED_KEY = -1;
	/**����״̬-ɾ��*/
	public static final String ORDER_CARGO_DEPLOY_DELETED_VALUE = "ɾ��";
	/**����״̬-δ����-0*/
	public static final int ORDER_CARGO_DEPLOY_UNDEPLOY_KEY = 0;
	/**����״̬-δ����*/
	public static final String ORDER_CARGO_DEPLOY_UNDEPLOY_VALUE = "δ����";
	/**����״̬--������-1*/
	public static final int ORDER_CARGO_DEPLOY_START_KEY = 1;
	/**����״̬--������*/
	public static final String ORDER_CARGO_DEPLOY_START_VALUE = "������";
	/**����״̬--�쳣����-7*/
	public static final int ORDER_CARGO_DEPLOY_UNNORMAL_END_KEY = 7;
	/**����״̬--�쳣����*/
	public static final String ORDER_CARGO_DEPLOY_UNNORMAL_END_VALUE = "�쳣����";
	/**����״̬--����-8*/
	public static final int ORDER_CARGO_DEPLOY_CANCAL_KEY = 8;
	/**����״̬--����*/
	public static final String ORDER_CARGO_DEPLOY_CANCAL_VALUE = "����";
	/**����״̬--����-9*/
	public static final int ORDER_CARGO_DEPLOY_END_KEY = 9;
	/**����״̬--����*/
	public static final String ORDER_CARGO_DEPLOY_END_VALUE = "����";
	
	/**��Ӫ״̬-�ճ�- 1*/
	public static final int CAR_STATE_TYPE_NULL_KEY = 1;
	/**��Ӫ״̬-�ճ�*/
	public static final String CAR_STATE_TYPE_NULL_VALUE = "�ճ�";
	/**��Ӫ״̬-����- 2*/
	public static final int CAR_STATE_TYPE_FULL_KEY  = 2;
	/**��Ӫ״̬-����*/
	public static final String CAR_STATE_TYPE_FULL_VALUE = "����";
	
	
	/**˾���÷�*/
	/**����-3*/
	public static final int DRIVER_EVALUATE_SATISFACTORY_KEY  = 3;
	/**����*/
	public static final String DRIVER_EVALUATE_SATISFACTORY_VALUE = "����";
	/**һ��-6*/
	public static final int DRIVER_EVALUATE_ARIAL_KEY  = 6;
	/**һ��*/
	public static final String DRIVER_EVALUATE_ARIAL_VALUE = "һ��";
	/**������-9*/
	public static final int DRIVER_EVALUATE_NOSATISFACTORY_KEY  = 9;
	/**������*/
	public static final String DRIVER_EVALUATE_NOSATISFACTORY_VALUE = "������";
	
	
	/***�û�δ��¼��ʾ***/
	public static final String WEB_USER_INFO_VALUE = "��¼��鿴";
	
	/**ɾ��״̬--δɾ�� -0*/
	public static final int DELETED_FLAG_FALSE = 0;
	
	/**ɾ��״̬--ɾ�� -1*/
	public static final int DELETED_FLAG_TRUE = 1;
	
	/**����ǩ�ձ�־--δж�� -0*/
	public static final int SIGN_FLAG_FALSE = 0;
	
	/**����ǩ�ձ�־--��ж�� -1*/
	public static final int SIGN_FLAG_TRUE = 1;
	
	/**android�ֻ�ϵͳ*/
	public static final String OS_ANDROID="android";
	/**IOS�ֻ�ϵͳ*/
	public static final String OS_IOS="ios";
	
	/**excel�зָ���  !@!*/
	public static final String CELL_SPLIT_STR = "!@!";
	
	/**���ͷָ���  &nbsp;*/
	public static final String TYPES_SPLIT_STR = " ";
	
	
	/**ϵͳ��ʱ���ʽ:  yyyy-MM-dd*/
	public static final String DATE_FORMATE_DAY = "yyyy-MM-dd";
	/**ϵͳ��ʱ���ʽ:  yyyy-MM-dd HH*/
	public static final String DATE_FORMATE_HOUR = "yyyy-MM-dd HH";
	/**ϵͳʱ���ʽ:  yyyy-MM-dd HH:mm:ss*/
	public static final String DATE_FORMATE_LONG = "yyyy-MM-dd HH:mm:ss";
	/**ϵͳʱ���ʽ:  yyyyMMdd*/
	public static final String DATE_FORMATE_TIME = "yyyyMMdd";
	/**ϵͳʱ���ʽ:  Y��m��d�� Hʱm��s��*/
	public static final String DATE_FORMATE_CN_LONG = "yyyy��MM��dd��HHʱmm��ss��";
	
	/**�б�ҳĬ����ʾ��¼��������ҳ 10*/
	public static int PAGE_SIZE_INDEX = 10;
	
	/**�б�ҳĬ����ʾ��¼����������ҳ 20*/
	public static int PAGE_SIZE_MAIN = 20;
	
	/**�б�ҳĬ����ʾ��¼������λ����Ϣҳ 10*/
	public static int PAGE_SIZE_LOCATION = 10;
	
	
	//log��¼��ʼ��ͳһ��DCTMS��ά��������
	
	//public static final String DRIVER_APP_ANDROID_ACTIVE_PER_DAY_TAG = "";
	//public static final String DRIVER_APP_ANDROID_ACTIVE_PER_MONTH_TAG = "";
	
	/**app �����ذ�װ��� DRIVER_APP_DOWNLOAD_TAG�����¼�ֶμ�ֵ��OS=android,ios,wp��TIME*/
	public static final String DRIVER_APP_DOWNLOAD_TAG = "DRIVER_APP_DOWNLOAD_TAG";

	/**app �û���¼��� DRIVER_APP_LOGIN_TAG�����¼�ֶμ�ֵ��OS��DRIVERID��DRIVERCODE��REGISTERFLAG=0,1��TIME*/
	public static final String DRIVER_APP_LOGIN_TAG = "DRIVER_APP_LOGIN_TAG";
	
	/**app �û�������� DRIVER_APP_RUN_TAG�����¼�ֶμ�ֵ��OS��CARGOID��DRIVERID��DRIVERCODE��TIME*/
	public static final String DRIVER_APP_RUN_TAG = "DRIVER_APP_RUN_TAG";
	
	/**app �û�����ԤԼ��·��� DRIVER_APP_DEPLOY_CAR_LINE_TAG�����¼�ֶμ�ֵ��OS��DEPLOYCARLINE��DEPLOYCARLINETIME��DRIVERID��DRIVERCODE��TIME*/
	public static final String DRIVER_APP_DEPLOY_CAR_LINE_TAG = "DRIVER_APP_DEPLOY_CAR_LINE_TAG";

	/** web �û�ע���� WEB_USER_REGISTER_TAG�����¼�ֶμ�ֵ��USERID��USERCODE��TIME*/
	public static final String WEB_USER_REGISTER_TAG = "WEB_USER_REGISTER_TAG";
	
	/** web�û�������Դ��� WEB_USER_ADD_ORDER_CARGO_TAG�����¼�ֶμ�ֵ��USERID��USERCODE��CARGOID��REDEPLOYFLAG=0��TIME*/
	public static final String WEB_USER_ADD_ORDER_CARGO_TAG = "WEB_USER_ADD_ORDER_CARGO_TAG";
	
	/** web�û����·�����Դ��� WEB_USER_RE_ADD_ORDER_CARGO_TAG�����¼�ֶμ�ֵ��USERID��USERCODE��CARGOID��REDEPLOYFLAG=1��TIME*/
	public static final String WEB_USER_RE_ADD_ORDER_CARGO_TAG = "WEB_USER_RE_ADD_ORDER_CARGO_TAG";
	
	/** web�û��޸Ļ�Դ����ʱ���� WEB_USER_MODIFY_ORDER_CARGO_TIME_TAG�����¼�ֶμ�ֵ��USERID��USERCODE��CARGOID��REDEPLOYFLAG=1��TIME*/
	public static final String WEB_USER_MODIFY_ORDER_CARGO_TIME_TAG = "WEB_USER_MODIFY_ORDER_CARGO_TIME_TAG";
	
	/** web�û��������׼�¼��� WEB_USER_ADD_TRANSACTION_TAG�����¼�ֶμ�ֵ��USERID��USERCODE��CARGOID��DRIVERID��DRIVERCODE��TRANSACTIONID��TIME*/
	public static final String WEB_USER_ADD_TRANSACTION_TAG = "WEB_USER_ADD_TRANSACTION_TAG";
	
	/**app �û��ϴ���ַ������Ϣ��� DRIVER_APP_LOCATION_COLLECT_TAG�����¼�ֶμ�ֵ��OS��LOCATIONID,DRIVERID��DRIVERCODE��TIME*/
	public static final String DRIVER_APP_LOCATION_COLLECT_TAG = "DRIVER_APP_LOCATION_COLLECT_TAG";
	
	
	/**app �û��ֻ���Ϣ��� DRIVER_APP_MOBILE_TAG�����¼�ֶμ�ֵ��OS��DRIVERID��DRIVERCODE��MTYPE��BRAND��OSRELEASE��TIME*/
	public static final String DRIVER_APP_MOBILE_TAG = "DRIVER_APP_MOBILE_TAG";
	
	/** �ٶ������Ͳ���apiKey*/
    //public static final String apiKey = "VoAvvLZ69qyFKmOGSe3bmKez";
	/** �ٶ������Ͳ���secretKey*/
	//public static final String secretKey = "Q9qAcjwcbCe7uHOt2kZGjMcmyIInAhkv";
	/** �ٶ�����������apiKey*/
	public static final String apiKey = "9vAr0wp7pNEGlzzr8qGkjkUw";
	/** �ٶ�����������apiKey*/
	public static final String secretKey = "eVQAomws0wcWDYrI8n5KmCCONf54eGWD";
	//log��¼������ͳһ��DCTMS��ά��������
	/** ������ͱ���*/
    public static final String auditTitle = "�����֤";
    /** С���㱣��λ��*/
    public static final int decimalNum = 2;
}
