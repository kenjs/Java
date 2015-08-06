package com.cy.swp.common.constants;


public class Constants {

	/**SESSION中存储 当前登录系统帐号CODE*/
	public static final String SESSION_LOGIN_USER = "user";
	
	
	/**系统错误代码-参数错误*/
	public static final String EXCEPTION_CODE_PARAMETER_COMMON = "12000";
	/**系统错误代码-SESSION为空错误*/
	public static final String EXCEPTION_CODE_SESSION_EMPTY_COMMON = "12100";
	/**系统错误代码-业务逻辑错误*/
	public static final String EXCEPTION_CODE_BUSINESS_COMMON = "10000";
	/**系统错误代码-数据库错误*/
	public static final String EXCEPTION_CODE_DATABASE_COMMON = "11000";
	
	/**JSON系统错误代码-参数错误*/
	public static final String JSON_EXCEPTION_CODE_PARAMETER_COMMON = "-1";
	/**JSON系统错误代码-用户不存在错误*/
	public static final String JSON_EXCEPTION_CODE_USER_EMPTY_COMMON = "-9";
	/**JSON系统错误代码-业务逻辑错误*/
	public static final String JSON_EXCEPTION_CODE_BUSINESS_COMMON = "-8";
	
	
	/**系统短时间格式:  yyyy-MM-dd*/
	public static final String DATE_FORMATE_DAY = "yyyy-MM-dd";
	/**系统短时间格式:  yyyy-MM-dd HH*/
	public static final String DATE_FORMATE_HOUR = "yyyy-MM-dd HH";
	/**系统时间格式:  yyyy-MM-dd HH:mm:ss*/
	public static final String DATE_FORMATE_LONG = "yyyy-MM-dd HH:mm:ss";
	/**系统时间格式:  HH:mm:ss*/
	public static final String DATE_FORMATE_HH = "HH:mm:ss";
	/**系统时间格式:  yyyyMMdd*/
	public static final String DATE_FORMATE_TIME = "yyyyMMdd";
	/**系统时间格式:  Y年m月d日 H时m分s秒*/
	public static final String DATE_FORMATE_CN_LONG = "yyyy年MM月dd日HH时mm分ss秒";
	
	
	/**列表页默认显示记录数——主页 10*/
	public static int PAGE_SIZE_INDEX = 10;
	
	/**列表页默认显示记录数——二级页 20*/
	public static int PAGE_SIZE_MAIN = 20;
	
	/**列表页默认显示记录数——位置信息页 10*/
	public static int PAGE_SIZE_LOCATION = 10;
	
	/**删除状态--未删除 -0*/
	public static final int DELETED_FLAG_FALSE = 0;
	
	/**删除状态--删除 -1*/
	public static final int DELETED_FLAG_TRUE = 1;
	
	
	/*****************WEB用户***********************/
	
	/**缴费认证标识--未认证 -0*/
	public static final int PANYMENT_FLAG_NOT = 0;
	
	/**缴费认证标识--已认证 -1*/
	public static final int PANYMENT_FLAG_END = 1;
	
	/**个人认证标识--未认证 -0*/
	public static final int PERSONAGE_FLAG_NOT = 0;
	
	/**个人认证标识--已认证 -1*/
	public static final int PERSONAGE_FLAG_END = 1;
	
	
	/**短信发送状态--发送成功 -0*/
	public static final int NOTE_SEND_START_SUCCESS = 0;
	
	/**短信发送状态--发送失败 -1*/
	public static final int NOTE_SEND_START_ERROR = 1;
	
	//------------------------ 货源来源 ----------------------------
	   /**货源来源-发布货源-0*/
		public static final String CARGO_ORIGIN_PUBLISH = "0";
	   /**货源来源-导入订单货源- 1*/
		public static final String CARGO_ORIGIN_IMPORT= "1";
		/**货源来源-营销平台导入货源- 2*/
		public static final String CARGO_ORIGIN_MARKETING_IMPORT= "2";
	
//----------------------------用户来源（0自己注册，1导入的）------------------------
	/**用户来源-自己注册- 0*/
	public static final String 	USER_ORIGIN_REGISTRATION = "0";
	/**用户来源-导入的- 1*/
	public static final String USER_ORIGIN_IMPORT= "1";
	
//------------------------司机对货源的点评2------------------------
	/**司机对货源的点评-货已走- 0*/
	public static final String CARGO_EXPORTED_TYPE_KEY = "0";
	/**司机对货源的点评-货已走*/
	public static final String CARGO_EXPORTED_TYPE_VALUE = "货已走";
	/**司机对货源的点评-货还在- 1*/
	public static final String CARGO_EXIST_TYPE_KEY= "1";
	/**司机对货源的点评-货还在*/
	public static final String CARGO_EXIST_TYPE_VALUE= "货还在";
	
	
//------------------------货源装货时间是否过期（是否失效）----------------------------
	/**货源是否过期-未过期- 0*/
	public static final String IS_EXPIRE_FALSE = "0";
	/**货源是否过期-已过期- 1*/
	public static final String IS_EXPIRE_TRUE= "1";


 //----------------------货源状态4(数据库中3个值，页面显示有4个值多一个失效,页面上的待交易查询：待交易0且未过期的，数据库中的待交易0包含：过期和未过期的)-------------------------
	/**货源状态-待交易- 0*/
	public static final String CARGO_FLAG_PENDING_TRADE_KEY = "0";
	/**货源状态-待交易*/
	public static final String CARGO_FLAG_PENDING_TRADE_VALUE = "待交易";
	/**货源状态-交易中- 1*/
	public static final String CARGO_FLAG_TRADING_KEY = "1";
	/**货源状态-交易中*/
	public static final String CARGO_FLAG_TRADING_VALUE = "交易中";
	/**货源状态-交易成功- 2*/
	public static final String CARGO_FLAG_SUCCESS_KEY = "2";
	/**货源状态-交易成功*/
	public static final String CARGO_FLAG_SUCCESS_VALUE = "交易成功";
	/**货源状态-失效- 2*/
	public static final String CARGO_FLAG_EXPRICE_KEY = "3";
	/**货源状态-失效*/
	public static final String CARGO_FLAG_EXPRICE_VALUE = "失效";
	 //----------------------货物类型（2种）-------------------------
	/**货物类型-重货- 1*/
	public static final String CARGO_TYPE_HEVAY_KEY = "1";
	/**货物类型-重货*/
	public static final String CARGO_TYPE_HEVAY_VALUE = "重货";
	/**货物类型-泡货- 2*/
	public static final String CARGO_TYPE_BULKY_KEY = "2";
	/**货物类型-泡货*/
	public static final String CARGO_TYPE_BULKY_VALUE = "泡货";
	
	//----------------------货源来源（3种：0发布货源、1导入订单货源、2营销平台导入货源）----------------------
	/**发布货源-0*/
	public static final String PUBLISH_CARGO_KEY  = "0";
	/**导入订单货源-1*/
	public static final String IMPORT_TRADE_CARGO_KEY  = "1";
	/**营销平台导入货源-2*/
	public static final String MARKETING_IMPORT_CARGO_KEY  = "2";
	
	//---------------------------货物信息来源（物流QQ，一点通，林安物流网，物通网）------------------------------
			/**货物信息来源-物流QQ-0*/
			public static final String CARGO_FROM_LOGISTICS_QQ_KEY  = "0";
			/**货物信息来源-物流QQ-0*/
			public static final String CARGO_FROM_LOGISTICS_QQ_VALUE  = "物流QQ";
			/**货物信息来源-一点通-1*/
			public static final String CARGO_FROM_WINBATCH_KEY  = "1";
			/**货物信息来源-一点通-1*/
			public static final String CARGO_FROM_WINBATCH_VALUE  = "一点通";
			/**货物信息来源-林安物流网-2*/
			public static final String CARGO_FROM_LINAN_KEY  = "2";
			/**货物信息来源-林安物流网-2*/
			public static final String CARGO_FROM_LINAN_VALUE  = "林安物流网";
			/**货物信息来源-物通网-3*/
			public static final String CARGO_FROM_TONG_NETWORK_KEY  = "3";
			/**货物信息来源-物通网-3*/
			public static final String CARGO_FROM_TONG_NETWORK_VALUE  = "物通网";
			
	
	//-----------------------是否联络过（是否匹配）------------------------------
	/**已联络（否）-0*/
	public static final String NOT_CONTACTED_KEY  = "0";
	/**已联络（否）-1*/
	public static final String NOT_CONTACTED_VALUE  = "否";
	/**已联络（是）-1*/
	public static final String ALREADY_CONTACTED_KEY  = "1";
	/**已联络（是）-1*/
	public static final String ALREADY_CONTACTED_VALUE  = "是";
	
	//----------------------- 是否达成交易------------------------------
		/** 是否达成交易-否-0*/
		public static final String TRANSACTION_FAIL_KEY  = "0";
		/** 是否达成交易-否-0*/
		public static final String TRANSACTION_FAIL_VALUE  = "否";
		/** 是否达成交易-是-1*/
		public static final String TRANSACTION_SUCCESS_KEY  = "1";
		/** 是否达成交易-是-1*/
		public static final String TRANSACTION_SUCCESS_VALUE  = "是";
	
	//-----------------------是否匹配成功:0 表示未联系过司机 1 成功 2 失败（至少有一辆车有意向去拉这个货算成功）------------------------------
			/**是否匹配成功（是否有意向车辆）-未联系过车辆-0*/
			public static final String NOT_BRIDGING_KEY  = "0";
			/**是否匹配成功（是否有意向车辆）--未联系过车辆-0*/
			public static final String NOT_BRIDGING_VALUE  = "未联系车源";
			/**是否匹配成功（是否有意向车辆）-成功-1*/
			public static final String BRIDGING_SUCCESS  = "1";
			/**是否匹配成功（是否有意向车辆）-成功-1*/
			public static final String BRIDGING_SUCCESS_VALUE  = "成功";
			/**是否匹配成功（是否有意向车辆）-失败-2*/
			public static final String BRIDGING_NOT_SUCCESS  = "2";
			/**是否匹配成功（是否有意向车辆）-失败-2*/
			public static final String BRIDGING_NOT_SUCCESS_VALUE  = "失败";
	
			
	//-----------------------导入的公司是否注册（是or否）------------------------------
		/**导入的公司未注册-0*/
		public static final String COMP_NOT_REGISTER_KEY  = "0";
		/**导入的公司未注册-未注册-0*/
		public static final String COMP_NOT_REGISTER_VALUE  = "未注册";
		/**导入的公司是注册-1*/
		public static final String COMP_REALLY_REGISTER_KEY  = "1";
		/**导入的公司是注册-已注册-1*/
		public static final String COMP_REALLY_REGISTER_VALUE  = "已注册";

		
		
 //--------------------------企业,货源,司机电话反馈结果-------------------------------
		//电话司机反馈（0有意向，1无意向，2未明确态度）
		/**电话司机反馈-有意向-0*/
		public static final String DRIVER_REPLYRESULT_INTENTION_KEY  = "0";
		/**电话司机反馈-有意向-0*/
		public static final String DRIVER_REPLYRESULT_INTENTION_VALUE  = "有意向并发送通知";
		/**电话司机反馈-无意向-1*/
		public static final String DRIVER_REPLYRESULT_NOT_INTENTION_KEY  = "1";
		/**电话司机反馈-无意向-1*/
		public static final String DRIVER_REPLYRESULT_NOT_INTENTION_VALUE  = "无意向";
		/**电话司机反馈-未明确态度-2*/
		public static final String DRIVER_REPLYRESULT_UNKNOWN_KEY  = "2";
		/**电话司机反馈-未明确态度-2*/
		public static final String DRIVER_REPLYRESULT_UNKNOWN_VALUE  = "未明确态度";
		
		/**电话司机反馈-无效号码-3*/
		public static final String DRIVER_REPLYRESULT_THREE_UNKNOWN_KEY  = "3";
		/**电话司机反馈-无效号码-3*/
		public static final String DRIVER_REPLYRESULT_THREE_UNKNOWN_VALUE  = "无效号码";
		
		/**电话司机反馈-未联系-4*/
		public static final String DRIVER_REPLYRESULT_Four_UNKNOWN_KEY  = "4";
		/**电话司机反馈-未联系-4*/
		public static final String DRIVER_REPLYRESULT_FOUR_UNKNOWN_VALUE  = "未联系";

	 //电话货源反馈结果(没有该货源，未明确答复，货还在，货已走)
		/**电话货源反馈没有该货源- -1*/
		public static final String CARGO_REPLYRESULT_NONENTITY_KEY  = "-1";
		/**电话货源反馈-没有该货源- -1*/
		public static final String CARGO_REPLYRESULT_NONENTITY_VALUE  = "虚假货源";
		/**电话货源反馈不确定-0*/
		public static final String CARGO_REPLYRESULT_UNKNOWN_KEY  = "0";
		/**电话货源反馈-不确定-0*/
		public static final String CARGO_REPLYRESULT_UNKNOWN_VALUE  = "未明确答复";
		/**电话货源反馈货还在-1*/
		public static final String CARGO_REPLYRESULT_EXIST_KEY  = "1";
		/**电话货源反馈-货还在-1*/
		public static final String CARGO_REPLYRESULT_EXIST_VALUE  = "货还在";
		/**电话货源反馈货已走-2*/
		public static final String CARGO_REPLYRESULT_HAD_COVERED_KEY  = "2";
		/**电话货源反馈-货已走-2*/
		public static final String CARGO_REPLYRESULT_HAD_COVERED_VALUE  = "货已走";
		/**企业号码无效货源标记为无效货源-3*/
		public static final String CARGO_REPLYRESULT_HAD_THERR_COVERED_KEY  = "3";
		/**企业号码无效货源标记为无效货源-3*/
		public static final String CARGO_REPLYRESULT_HAD_THERR_COVERED_VALUE  = "无效号码";
		
	
	    //电话企业反馈结果（0无效号码，1已注册，2有注册意向，3无注册意向，4未明确态度）
		/**电话企业反馈结果-无效号码-0*/
		public static final String COMP_REPLYRESULT_INVALID_KEY  = "0";
		/**电话企业反馈结果-无效号码-0*/
		public static final String COMP_REPLYRESULT_INVALID_VALUE  = "无效号码";
		/**电话企业反馈结果-已注册-1*/
		public static final String COMP_REPLYRESULT_REGISTER_KEY  = "1";
		/**电话企业反馈结果-已注册-1*/
		public static final String COMP_REPLYRESULT_REGISTER_VALUE  = "已注册";
		/**电话企业反馈结果-有注册意向-2*/
		public static final String COMP_REPLYRESULT_INTENTION_KEY  = "2";
		/**电话企业反馈结果-有注册意向-2*/
		public static final String COMP_REPLYRESULT_INTENTION_VALUE  = "有意向";
		/**电话企业反馈结果-无注册意向-3*/
		public static final String COMP_REPLYRESULT_NOT_INTENTION_KEY  = "3";
		/**电话企业反馈结果-无注册意向-3*/
		public static final String COMP_REPLYRESULT_NOT_INTENTION_VALUE  = "无意向";
		/**电话企业反馈结果-未明确意向-3*/
		public static final String COMP_REPLYRESULT_NOT_SPECIFY_KEY  = "4";
		/**电话企业反馈结果-未明确意向-3*/
		public static final String COMP_REPLYRESULT_NOT_SPECIFY_VALUE  = "未接听";





	
	/**货物签收标志--未卸货 -0*/
	public static final int SIGN_FLAG_FALSE = 0;
	
	/**货物签收标志--已卸货 -1*/
	public static final int SIGN_FLAG_TRUE = 1;
	
	
	/**excel列分隔符  !@!*/
	public static final String CELL_SPLIT_STR = "!@!";
	
	/**车型分隔符  &nbsp;*/
	public static final String TYPES_SPLIT_STR = " ";
	
	
	//----------------------------个人中心左边菜单超链接Id（9个）-----------------------------------
	/**导入货源-*/
	public static final String MENU_AID_IMPORT_CARGO = "a_id_1";
	/**我的客户*/
	public static final String MENU_AID_MY_CLIENT = "a_id_2";
	/**未联系客户*/
	public static final String MENU_AID_NO_CONTACT_CLIENT = "a_id_3";
	/**添加客户*/
	public static final String MENU_AID_ADD_DRIVER = "a_id_4";
	/**客户分配*/
	public static final String MENU_AID_CLIENT_ASSIGN = "a_id_5";
	/**统计分析*/
	public static final String MENU_AID_COUNT_ANALYSE = "a_id_6";
	
	//---------------------------------百度云推送的两个常量 apiKey secretKey-----------------------------------------
	/** 百度云推送apiKey*/
	public static final String apiKey = "9vAr0wp7pNEGlzzr8qGkjkUw";
	/** 百度云推送secretKey*/
	public static final String secretKey = "eVQAomws0wcWDYrI8n5KmCCONf54eGWD";


    //---------------------------------营销平台常量-----------------------------------------
	//司机客户资料 客户等级(ABCD)
    public static final String REAL_LEVEL_A = "A";
	public static final String REAL_LEVEL_B = "B";
	public static final String REAL_LEVEL_C = "C";
	public static final String REAL_LEVEL_D = "D";

    //司机客户资料 分配状态(0 待分配 1 分配中 2 已分配)
    public static final int ALLOCATE_STATUS_WAIT_KEY = 0;
    public static final String ALLOCATE_STATUS_WAIT_VAL = "待分配";
    public static final int ALLOCATE_STATUS_ING_KEY = 1;
    public static final String ALLOCATE_STATUS_ING_VAL = "分配中";
    public static final int ALLOCATE_STATUS_END_KEY = 2;
    public static final String ALLOCATE_STATUS_END_VAL = "已分配";

    //注册司机帐号信息 审核提交标识（0未提交1已提交2审核未通过3审核已通过）
    public static final int SUBMIT_TYPE_NOSUB_KEY = 0;
    public static final String SUBMIT_TYPE_NOSUB_VAL = "未提交";
    public static final int SUBMIT_TYPE_YESSUB_KEY = 1;
    public static final String SUBMIT_TYPE_YESSUB_VAL = "已提交";
    public static final int SUBMIT_TYPE_SHNO_KEY = 2;
    public static final String SUBMIT_TYPE_SHNO_VAL = "审核未通过";
    public static final int SUBMIT_TYPE_SHYES_KEY = 3;
    public static final String SUBMIT_TYPE_SHYES_VAL = "审核已通过";

    //导入司机的模式 10营销人员导入 20主管及以上导入
    public static final String IMPORT_DRIVER_TYPE_ORD = "10";
    public static final String IMPORT_DRIVER_TYPE_VIP = "20";

    //客户分配申请表 -1 申请未通过 0 等待审核 1 审核通过
    public static final int AUDIT_STATUS_NO_KEY = -1;
    public static final String AUDIT_STATUS_NO_val = "申请未通过";
    public static final int AUDIT_STATUS_WAIT_KEY = 0;
    public static final String AUDIT_STATUS_WAIT_VAL = "等待审核";
    public static final int AUDIT_STATUS_YES_KEY = 1;
    public static final String AUDIT_STATUS_YES_VAL = "审核通过";

    //营销专员帐户信息表 职务 1 经理 2 主管 3 组长 4 专员
    public static final int POSITION_MANAGER_KEY = 1;
    public static final String position_manager_val = "经理";
    public static final int POSITION_DIRECTOR_KEY = 2;
    public static final String POSITION_DIRECTOR_val = "主管";
    public static final int POSITION_LEADER_KEY = 3;
    public static final String POSITION_LEADER_val = "组长";
    public static final int POSITION_COMM_KEY = 4;
    public static final String POSITION_COMM_val = "专员";

    //未联系客户查询 1所有客户 2预约客户
    public static final String NO_CUSTOM_TYPE_ALL = "1";
    public static final String NO_CUSTOM_TYPE_MAK = "2";


	//运营路线类型
	public static final Integer DRIVER_LINE_DAN_KEY = 1;
	public static final String DRIVER_LINE_DAN_VAL = "单向";
	public static final Integer DRIVER_LINE_SHUANG_KEY = 2;
	public static final String DRIVER_LINE_SHUANG_VAL = "双向";


    //客户分配申请 1 客户企业  2 客户司机
    public static final int CUSTOMER_KIND_ENTER_KEY = 1;
    public static final String CUSTOMER_KIND_ENTER_VAL = "客户企业";
    public static final int CUSTOMER_KIND_DRIVER_KEY = 2;
    public static final String CUSTOMER_KIND_DRIVER_VAL = "客户司机";


	//报价类型
	public static final int BUSINESS_LINE_ZC_KEY = 1;
	public static final String BUSINESS_LINE_ZC_VALUE = "整车报价";
	public static final int BUSINESS_LINE_AD_KEY = 2;
	public static final String BUSINESS_LINE_AD_VALUE = "按吨报价";
	public static final int BUSINESS_LINE_AF_KEY = 3;
	public static final String BUSINESS_LINE_AF_VALUE = "按方报价";



    //我的客户查询 0未注册 1已注册
    public static final Integer REGISTER_NO_KEY = 0;
    public static final Integer REGISTER_YES_KEY = 1;

    //客户维护记录表 record_content的标记无效号码记录的内容
    public static final String RECORD_CONTENT_VAL = "@!该司机手机号码标记为无效!@";

    //客户分配记录表   1  绑定 -1 解绑
    public static final Integer DISTRIBUT_TYPE_BINDING_KEY = 1;
    public static final String DISTRIBUT_TYPE_BINDING_VAL = "绑定";
    public static final Integer DISTRIBUT_TYPE_UNBUNDLING_KEY = -1;
    public static final String DISTRIBUT_TYPE_UNBUNDLING_VAL = "解绑";

    //司机客户资料  是否营销人员发展的注册用户：0 否 1 是
    public static final Integer REG_THROUGH_ASSIST_NOT = 0;
    public static final Integer REG_THROUGH_ASSIST_YES = 1;

    //司机客户资料  是否营销人员发展的认证用户：0 否 1 是
    public static final Integer AUTH_THROUGH_ASSIST_NOT = 0;
    public static final Integer AUTH_THROUGH_ASSIST_YES = 1;

    //司机客户资料  号码是否有效：0 无效 1 有效
    public static final Integer PHONE_VALID_NOT = 0;
    public static final Integer PHONE_VALID_YES = 1;

    //营销平台用户表  所属组： 0 未分组 1 营销一组 2 营销二组 3 营销三组
    public static final Integer JOIN_GROUP_NOT_KEY = 0;
    public static final String JOIN_GROUP_NOT_VAL = "未分组";
    public static final Integer JOIN_GROUP_ONE_KEY = 1;
    public static final String JOIN_GROUP_ONE_VAL = "营销一组";
    public static final Integer JOIN_GROUP_TWO_KEY = 2;
    public static final String JOIN_GROUP_TWO_VAL = "营销二组";
    public static final Integer JOIN_GROUP_THREE_KEY = 3;
    public static final String JOIN_GROUP_THREE_VAL = "营销三组";

    //--------------------发送来源（1 营销平台 2 快到网网站 3 app服务端）------------------
    /**短信发送来源- 营销平台- 1*/
    public static final Integer EVEN_FROM_MARKETING_KEY = 1;
    /**短信发送来源- 营销平台*/
    public static final String EVEN_FROM_MARKETING_VALUE= "营销平台";

    //推送的系统参数编码
    public static final String CODE_PUSH_CHANNEL = "pushChannel";
    //推送的参数Key
    //系统消息类PUSH(1 极光 2 百度)
    public static final String SP_KEY_SYS_MSG = "sysMsg";
    //等待响应类PUSH（1 极光 2 百度）
    public static final String SP_KEY_WAIT_RESPONE = "waitRespone";
    //主动营销类PUSH(1 极光 2 推送)
    public static final String SP_KEY_INITIATIVE_MARKET = "initiativeMarket";
    //被动营销类PUSH(1 极光 2 百度)
    public static final String SP_KEY_PASSIVE_MARKET = "passiveMarket";
}
