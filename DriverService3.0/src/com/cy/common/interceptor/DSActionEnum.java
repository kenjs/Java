package com.cy.common.interceptor;

public enum DSActionEnum {
	
	REGISTER(1,"register","注册"),
	LOGIN(2,"loginUserInfoAction","登录"),
	CHECKNEWVERSION(3,"checkNewVersion","版本更新检查"),
	DOWNLOADNEWVERSION(4,"downloadNewVersion","版本下载"),
	DRIVERUSERADDINFOACTION(5,"driverUserAddInfoAction","填写资料"),
	DRIVERUSERUPDATECODE(6,"driverUserUpdateCode","修改手机号"),

	DRIVERBUSINESSLINEINFOADDACTION(7,"driverBusinessLineInfoAddAction","新增预约"),
	DRIVERBUSINESSLINEINFOUPDATEACTION(8,"driverBusinessLineInfoUpdateAction","修改预约"),
	DRIVERBUSINESSLINEINFODELETEACTION(9,"driverBusinessLineInfoDeleteAction","删除预约"),
	DRIVERBUSINESSLINEINFOQUERYACTION(10,"driverBusinessLineInfoQueryAction","查询预约"),
	DRIVERBUSINESSLINEINFONUMQUERYACTION(11,"driverBusinessLineInfoNumQueryAction","查询预约数目"),
	SELECTNEARBYCARGOCOUNTACTION(12,"selectNearByCargoCountAction","附近货源量"),
	SELECTORDERCARGOCOUNTACTION(13,"selectOrderCargoCountAction","符合预约的货源量"),
	SELECTNEARBYCARGOLISTACTION(14,"selectNearByCargoListAction","附近货源列表"),
	SELECTCARGOSUITORDERLISTACTION(15,"selectCargoSuitOrderListAction","符合预约的货列表"),
	UPDATEDRIVERUSERINFOACTION(16,"updateDriverUserInfoAction","修改Driver信息"),
	USERBASICINFORMATIONQUERYACTION(17,"userBasicInformationQueryAction","用户基本信息查询"),
	NOTESENDACTION(18,"noteSendAction","手机验证码 "),
	QUERYDRIVERNOTIFICATIONINFOLISTACTION(19,"queryDriverNotificationInfoListAction","消息列表查询"),
	USERCERTIFICATEINFOUPLOADACTION(20,"uploadFile","图片上传"),
	SELECTCOMPANYINFO(21,"selectCompanyInfo","查找公司信息"),

	DRIVERLINEINFOADDACTION(22,"driverLineInfoAddAction","新增营运线路"),
	DRIVERLINEINFODELETEACTION(23,"driverLineInfoDeleteAction","删除营运线路"),
	DRIVERLINEINFOUPDATEACTION(24,"driverLineInfoUpdateAction","修改营运线路"),
	DRIVERLINEINFOSELECTACTION(25,"driverLineInfoSelectAction","查询营运线路"),

	SELECTCARGOLISTACTION(26,"selectCargoListAction","搜索找货"),
	SELECTCARGONUMBYDRIVERLINEACTION(27,"selectCargoNumByDriverLineAction","根据司机线路查找符合条件的货物数量"),
	SELECTCARGOLISTBYDRIVERLINEACTION(28,"selectCargoListByDriverLineAction","根据司机线路查找符合条件的货物"),
	SELECTORDERCARGODETAILACTION(29,"selectOrderCargoDetailAction","货源详情"),
	ATTENTIONCARGOINFOACTION(30,"attentionCargoInfoAction","关注货源"),
	COMMENTCARGOINFOACTION(31,"commentCargoInfoAction","点评货源"),

	TRANSACTIONNUMBERWITHCOMACTION(32,"transactionNumberWithComAction","企业用车订单条数"),
	TRANSACTIONINFOLISTACTION(33,"transactionInfoListAction","司机订单列表"),
	TRANSACTIONINFODETAILACTION(34,"transactionInfoDetailAction","订单详情"),
	UNLOADCARGOACTION(35,"unloadCargoAction","卸货动作"),

	SELECTQUOTETRANSACTIONLISTACTION(37,"selectQuoteTransactionListAction","所有报过价的订单列表"),
	SELECTRANSACTIONLISTBYTRADESTATACTION(38,"selecTransactionListByTradeStatAction","根据交易状态查找"),
	ADDQUOTEINFOACTION(39,"addQuoteInfoAction","新增报价"),
	SELECTATTENTIONCARGOLISTACTION(40,"selectAttentionCargoListAction","我关注的货源"),

	LOCATIONLASTINFOINSERTACTION(41,"locationLastInfoInsertAction","位置信息上传"),

	ADDNEWDRIVERUSERASSESSACTION(42,"addNewDriverUserAssessAction","新增货源评价"),
	UPDATEDRIVERUSERASSESSACTION(43,"updateDriverUserAssessAction","修改货源评价"),

	PUSHUNICASTMESSAGE(44,"pushUnicastMessage","单播消息推送"),
	PUSHTAGMESSAGE(45,"pushTagMessage","组播消息推送"),
	PUSHBROADCASTMESSAGE(46,"pushBroadcastMessage","广播消息推送");
	
	//往下 48开始....
	
	private int operationType;
	private String operationName;
	private String remark;
	
	DSActionEnum(int operationType,String operationName,String remark) {
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
