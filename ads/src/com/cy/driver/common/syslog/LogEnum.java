package com.cy.driver.common.syslog;

/**
 * Created by haoy on 2014/9/23.
 */
public enum LogEnum {

    register(1, "register", "注册"),
    login(2, "loginUserInfoAction", "登陆"),
    chkAppVersion(3, "checkNewVersion", "版本更新检查"),
    downloadApp(4, "downloadNewVersion", "APP下载"),
    driverUserAddInfo(5, "driverUserAddInfoAction", "APP用户补充资料"),
    updateMobilePhone(6, "driverUserUpdateCode", "修改手机号码"),
    addBusinessLine(7, "driverBusinessLineInfoAddAction", "新增预约"),
    updateBusinessLine(8, "driverBusinessLineInfoUpdateAction", "修改预约"),
    deleteBusinessLine(9, "driverBusinessLineInfoDeleteAction", "删除预约"),
    queryBusinessLineList(10, "driverBusinessLineInfoQueryAction", "查询预约列表"),
    queryBusinessLineNum(11, "driverBusinessLineInfoNumQueryAction", "查询预约数目"),
    selectNearByCargoCountAction(12, "selectNearByCargoCountAction", "附近货源量"),
    selectOrderCargoCountAction(13, "selectOrderCargoCountAction", "符合预约货源量"),
    nearByCargoList(14, "selectNearByCargoListAction", "附近货源列表"),
    suitBusinessLineCargoList(15, "selectCargoSuitOrderListAction", "符合预约的货列表"),
    updateDriverInfo(16, "updateDriverUserInfoAction", "修改司机信息"),
    driverBasicInfoQuery(17, "userBasicInformationQueryAction", "用户基本信息查询"),
    noteSend(18, "noteSendAction", "短信发送"),
    infoList(19, "queryDriverNotificationInfoListAction", "消息列表查询"),
    uploadFile(20, "uploadFile", "图片上传"),
    queryCompanyInfo(21, "selectCompanyInfo", "查找公司信息"),
    addDriverLine(22, "driverLineInfoAddAction", "新增营运线路"),
    deleteDriverLine(23, "driverLineInfoDeleteAction", "删除营运线路"),
    updateDriverLine(24, "driverLineInfoUpdateAction", "修改营运线路"),
    queryDriverLine(25, "driverLineInfoSelectAction", "查询营运线路"),
    searchCargo(26, "selectCargoListAction", "搜索找货"),
    queryHomePageNumInfo(27, "queryHomePageNumInfo", "首页预约数量、货源数量等信息"),
    searchCargoByDriverLine(28, "selectCargoListByDriverLineAction", "根据司机线路查找符合条件的货物"),
    cargoDetail(29, "selectOrderCargoDetailAction", "货源详情"),
    attentionCargo(30, "attentionCargoInfoAction", "关注货源"),
    commentCargo(31, "commentCargoInfoAction", "点评货源"),
    companyCarTransactionNum(32, "transactionNumberWithComAction", "企业用车订单条数"),
    driverTransactionList(33, "transactionInfoListAction", "司机订单列表"),
    transactionDetail(34, "transactionInfoDetailAction", "订单详情"),
    unloadCargo(35, "unloadCargoAction", "卸货动作"),
    driverConfirmCancelUseCar(36, "cancelConfirmUseCarAction", "司机确认取消用车"),
    quotedTransactionList(37, "selectQuoteTransactionListAction", "所有报过价的订单列表"),
    queryByTradeStat(38, "selecTransactionListByTradeStatAction", "根据交易状态查找"),
    addQuote(39, "addQuoteInfoAction", "新增报价"),
    myAttentedCargo(40, "selectAttentionCargoListAction", "我关注的货源"),
    location(41, "locationLastInfoInsertAction", "位置信息上传"),
    addAssess(42, "addNewDriverUserAssessAction", "新增货源评价"),
    updateAssess(43, "updateDriverUserAssessAction", "修改货源评价"),
    pushUnicastMessage(44, "pushUnicastMessage", "单播消息推送"),
    pushTagMessage(45, "pushTagMessage", "组播消息推送"),
    pushBroadcastMessage(46, "pushBroadcastMessage", "广播消息推送"),
    driverConfirmUseCar(47, "cancelConfirmUseCarAction", "司机确认用车"),
    driverIsAssessed(48, "selectUserDriverAssessById", "根据交易id查找用户对司机是否已评价"),
    downloadImg(49, "downloadImage", "图片下载"),
    getFileName(50, "getFileName", "获取图片名称"),
    completedTransaction(51, "selectDealedTransactionInMyLog", "我的记录（已经成交的订单）"),
    searchQuoteInfo(52, "selectQuoteInfoByDriverAndCargoId", "根据货源id和司机id查找报价信息"),
    queryTransactionForEvaluation(53, "selecWaitEvaluationTransactionList", "待评价订单"),
    addTransactionHistory(54, "addTransactionLastInfo", "新增交易状态历史"),
    driverCancelTransaction(55, "driverCancelTransaction", "司机取消订单"),
    transactionCount(56, "selectMyOrderNum", "我的订单数量"),
    completeCargoInTansaction(57, "selectTransactionCargoList", "已成交货源"),
    IDN(58, "uploadIdentityNumber", "上传身份证"),
    remindCargo(59, "cargoInfoRemind", "货源消息提醒"),
    cargoIsGone(60, "selectDriverCargoAssessNumByCargoId", "某条货源被多少次标注为货已走"),
    updateOldUserInfo(61, "updateOldUserInfo", "修改老用户信息"),
    setBaiduPushId(62, "setBaiduPushId", "百度云推送设置channelID和userID"),
    driverEvalutedInfoByUser(63, "selectUserDriverAssessNum", "查询用户对司机的评价信息"),
    driverEvalutedInfoByCargoer(64, "selectUserDriverAssessInfoList", "查询货主对司机的评价信息"),
    queryWebInfo(65, "selectWebuserInfoByParentIdAndEncoded", "查询WEB用户信息"),
    receiptUpload(66, "receiptUpload", "回单上传"),
    receiptVerify(67, "receiptVerify", "货单确认"),
    receiptDelete(68, "receiptDelete", "回单删除"),
    queryTransactionById(69, "selectReceiptListByTransactionId", "根据订单id查找回单"),
    queryReceiptById(70, "selectReceiptById", "根据id查找回单"),
    queryImportDealedTransaction(71, "selectImportDealedTransactionList", "查找我的记录已经成交的导入的订单"),
    queyrImportTransactionForUnload(72, "selectWaittingUnloadImportTransactionList", "查找导入的待卸货订单"),
    queryImportTransactionForLoad(73, "查找导入的待卸货订单", "查找导入的待装货订单"),
    queryVIPInfo(74, "queryPactDriverInfoByType", "待确认会员信息查询"),
    updatePact(75, "updatePactDriverInfo", "修改合同状态"),
    queryVipLines(76, "queryVipDriverLineListAction", "vip会员司机线路查询"),
    updateVipLineStat(77, "updateVipDriverLineAction", "修改vip会员司机线路状态"),
    driverCall(78, "driverTelephoneAction", "司机拨打电话咨询"),
    receiptImgDownLoad(79, "receiptDownloadImage", "回单图片下载"),
    mobilePhoneInfo(80, "driverTelephoneInfoAction", "保存司机手机信息"),
    importSuccessTransactions(81, "queryImportSuccessTransactionsAction", "查询导入的成功交易的订单"),
    pushLogClicked(82, "pushLogClicked", "查看通知"),
    searchCargoAction(83, "searchCargoAction", "搜索找货和附近货源"),
    queryNearByCargoList(84, "queryNearByCargoList", "附近货源查询");


    private int operationType;
    private String operationName;
    private String remark;

    LogEnum(int operationType, String operationName, String remark) {
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
