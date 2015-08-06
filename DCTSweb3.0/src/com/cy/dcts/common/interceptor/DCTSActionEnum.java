package com.cy.dcts.common.interceptor;

public enum DCTSActionEnum {

	REALDRIVERCAR(1,"realDriverCar","主页当前车源"),
	RECOMMENDREALDIVERCAR(2,"recommendRealDiverCar","推荐当前车源"),
	RETURNDRIVERCAR(3,"returnDriverCar","主页预约车源"),
	NEWORDERCARGOSTART(4,"newOrderCargoStart","主页最新货源"),
	COUNTDATE(5,"countDate","主页数据统计"),
	DRIVERBUSINESSLINEBYID(6,"driverBusinessLineById","根据司机ID查询预约线路"),
	QUERYDRIVERLINEBYID(7,"queryDriverLineById","根据司机ID查询运营线路"),
	QUERYWEBUSERINFOCODE(8,"queryWebUserInfoCode","根据用户code查询用户是否存在"),
	QUERYWEBUSERINFOMOBLEPHONE(9,"queryWebUserInfoMoblePhone","根据手机号码查询手机是否被绑定"),
	QUERYCOMPANYBYNAME(10,"queryCompanyByName","根据公司名称查询公司是否存在"),
	SENDNOTE(11,"sendNote","用户注册发送短信"),
	LOGINUSERINFO(12,"loginUserInfo","用户快速登录"),
	USERLOGOUT(13,"userLogout","用户退出"),
	RETRIEVEUSERPWS(14,"retrieveUserPws","用户找回密码"),
	IPUSERACTION(15,"ipUserAction","获取访问者地区"),
	QUERYORDERCARGOQUOTE(16,"queryOrderCargoQuote","根据登录人的id获取待发货货源(所有)"),
	QUERYORDERCARGOQUOTEBYID(17,"queryOrderCargoQuoteById","根据id查询货物"),
	QUERYQUOTELIST(18,"queryQuoteList","根据货物Id查询货物报价列表"),
	ADDLOCALORDERCARGOINFO(19,"addLocalOrderCargoInfo","新增自已公司货源"),
	DELETELOCALORDERCARGOINFO(20,"deleteLocalOrderCargoInfo","删除货源"),
	CANCLETRANSACTION(21,"cancleTransaction","取消交易（修改货源状态及交易状态，同时添加货源历史记录及交易历史记录 ）"),
	MODIFYTRANSACTIONINFOORDERSTART(22,"modifyTransactionInfoOrderStart","删除交易记录（修改交易删除状态）"),
	ADDTRANSACTIONINFO(23,"addTransactionInfo","添加订单记录"),
	ADDTRANSACTIONCARGOQUOTE(24,"addTransactionCargoQuote","添加订车记录"),
	ADDUSERDRIVER(25,"addUserDriver","收藏司机"),
	QUERYDRIVERCARTRANSACTIONINFO(26,"queryDriverCarTransactionInfo","根据司机Id查询司机交易记录"),
	REGISTERWEBUSERINFO(27,"registerWebUserInfo","保存用户注册信息"),
	LOGINWEBUSERINFO(28,"loginWebUserInfo","用户登录"),
	OPENMODIFYLOCALORDERCARGOINFO(29,"openModifyLocalOrderCargoInfo","打开修改自已公司货源信息页面"),
	MODIFYLOCALORDERCARGOINFO(30,"modifyLocalOrderCargoInfo","修改自已公司货源"),
	OPENADDLOCALORDERCARGOINFO(31,"openAddLocalOrderCargoInfo","打开新增公司货源信息页面"),
	QUERYLOCALORDERCARGOINFO(32,"queryLocalOrderCargoInfo","查询我的货源"),
	DOWNLOADORDERCARGOFILETEMPLATE(33,"downloadOrderCargoFileTemplate","货源导入-下载Excel模板"),
	UPLOADORDERCARGOFILE(34,"uploadOrderCargoFile","货源导入-上传并导入Excel文件"),
	QUERYLOCALDRIVERUSERCARINFO(35,"queryLocalDriverUserCarInfo","查询自己所有的常用车辆 "),
	QUERYDRIVERQUOTEINFO(36,"queryDriverQuoteInfo","展示司机竞价详情"),
	QUERYMOREREALDRIVERINFO(37,"queryMoreRealDriverInfo","更多当前车源"),
	QUERYMORERETURNDRIVERINFO(38,"queryMoreReturnDriverInfo","更多预约车源"),
	OPENDRIVERDETAILED(39,"openDriverDetailed","司机详细页面 "),
	QUERYLOCATIONINFO(40,"queryLocationInfo","查询某个司机的历史轨迹及当前位置（跟踪）"),
	QUERYTRANSACTIONINFO(41,"queryTransactionInfo","查询我的订单"),
	QUERYTRANSACTIONDETAIL(42,"queryTransactionDetail","查询订单（订单）详情"),
	MODIFYTRANSACTIONSTART(43,"modifyTransactionStart","修改交易状态及（货源已送达-订单完成）评价 "),
	QUERYSUCCESSCLOSETRANSACTIONINFO(44,"querySuccessCloseTransactionInfo","查询成功或失败的订单（交易记录）,待交易订单（个人中心首页）"),
	OPENMYCENTERINFORACTION(45,"openMyCenterInforAction","初始化个人中心信息"),
	ACCOUNTMANAGEMENTACTION(46,"accountManagementAction","账户管理 "),
	OPENUPDATEPWDVIEW(47,"openUpdatePwdView","打开修改密码页面"),
	UPDATEPWDACTION(48,"updatePwdAction","修改密码"),
	OPENUPDATETELEPHONEVIEW(49,"openUpdateTelephoneView","打开修改手机号码页面"),
	CHECKPHONENUMBER(50,"checkPhoneNumber","检查手机号码"),
	UPDATETELEPHONEACTION(51,"updateTelephoneAction","修改手机号码"),
	OPENEVALUATIONMANAGEMENTACTION(52,"openEvaluationManagementAction","个人中心评价管理"),
	ACCOUNTMANAGEMENTUPDATEACTION(53,"accountManagementUpdateAction","个人中心完善信息"),
	LOADUSERDRIVEREVALUATION(54,"loadUserDriverEvaluation","评价管理"),
	LOADDRIVERUSEREVALUATION(55,"loadDriverUserEvaluation",""),	
	GETAREADICT(56,"getAreaDict","地区字典"),
	GETCARBARTYPEDICT(57,"getCarBarTypeDict","车型字典"),
	GETCARLENGTHDICT(58,"getCarLengthDict","车长字典"),
	GETCARPLATETYPEDICT(59,"getCarPlateTypeDict","车板字典"),
	
	QUERYDRIVERCARGOASSESSBYCARGOID(60,"queryDriverCargoAssessByCargoId","查询司机对货源的点评"),
	PUSHMESSAGETODRIVER(61,"pushMessageToDriver","百度推送消息给司机"),
	QUERYTODAYDYNAMIC(62,"queryTodayDynamic","查询首页的今日动态"),
	TRANDEINFOCOUNT(63,"trandeInfoCount","个人中心和主页统计预约订单，待确认收货，待评价订单"),
	QUERYORDERCARGOLIST(64,"queryOrderCargoList","查询首页的最新货源"),
	CITIZENSHIPVERIFICATIONACTION(65,"citizenshipVerificationAction","身份证查询");
	
	private int operationType;
	private String operationName;
	private String remark;
	
	DCTSActionEnum(int operationType,String operationName,String remark) {
		this.operationType = operationType;
		this.operationName = operationName;
		this.remark = remark;
	}
	
	public int operationType() {
		return operationType;
	}
	
	public String operationName() {
		return operationName;
	}
	
	public String remark() {
		return remark;
	}
}
