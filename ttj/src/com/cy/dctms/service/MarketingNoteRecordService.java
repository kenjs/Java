package com.cy.dctms.service;

public interface MarketingNoteRecordService {

	/**
	 * 短信发送
	 * @param mobilephone
	 * @param content
	 * @param userId
	 */
	public void addMarketingNoteRecordInfo(String mobilephone, String content, Integer userId);
	
	
//	public String sendingNote(String mobilephone, String noteCode);


	/**
	 *
	 * @param type 发送对象类别 0企业 1司机
	 * @param remark 备注
	 * @param telephone 发送手机
	 * @param content 发送内容
	 * @param useFor
	 * @return 用途：1 导入货源配车有司机发送给企业的短信 2 导入货源配车无司机发送给企业的短信
	 */
	public String setNoteSendRecordInfo(String type, String remark, String telephone, String content, String useFor);

    /**
     * 发送短信
     * @param type
     * @param remark
     * @param telephone
     * @param content
     * @param useFor
     * @return
     */
    public boolean sendingNoteAndSaveBusiLog(String type, String remark, String telephone, String content, String useFor);

	/**
	 *
	 * @param driverId 司机id
	 * @param title 推送标题
	 * @param message 推送内容
	 * @param id  自定义通知内容，选输项(不在通知栏显示，APP使用)Id
	 * @param type 类型
	 * @return nxj
	 */
	public String setPushInfo(String driverId, String title, String message, String id, String type, String spKey);


	/**
	 *
	 * @param driverId 司机id
	 * @param title 推送标题
	 * @param message 推送内容
	 * @param id  自定义通知内容，选输项(不在通知栏显示，APP使用)Id
	 * @param type 类型
	 * @return nxj
	 */
	public boolean sendingPushAndSaveBusiLog(String driverId, String title, String message, String id, String type, String spKey);
}
