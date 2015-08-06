package com.cy.driver.baidunyunpush.service;
/**
 * 百度云消息推送
 * 参数：device_type => 1: web 2: pc 3:android 4:ios 5:wp
 * 		message_type = 0 (默认为0) {0:消息,1:通知}
 * 		apiKey： 应用标识，终端上的绑定和服务端推送消息时都要用到。
 * 		secretKey： 应用私钥，服务端推送消息时用到。
 * 		app id ： 应用ID，就是百度开发者中心的应用基本信息中的应用ID。客户端绑定调用返回值中可获得。
 * 		channel id ： 推送通道ID，通常指一个终端，如一台android系统手机。客户端绑定调用返回值中可获得。
 * 		user id ： 应用的用户ID，一个应用在多个端，可以都属于同一用户（即对应一个userid）。
 * 					user id和channel id配合可以唯一指定一个应用的特定终端。如果应用不是基于百度账户的账户体系，
 * 					单独用user就通常指定了一个应用的特定终端。客户端绑定调用返回值中可获得。
 * @author Administrator
 *
 */
public interface AndroidPushMessageService {

	/**
	 * 单播消息推送
	 * 必须指定手 机端的ChannelId， 手机端的UserId
	 * @param messageType 消息类型,
	 * 		  message 要推送的消息,
	 * 		  channelId, userId
	 * @return
	 */
	public String pushUnicastMessage (int messageType,String message,long channelId,String userId);
	
	/**
	 * 组播消息推送(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
	 * @param messageType 消息类型,
	 * 		  message 要推送的消息,
	 * 		  tagName
	 * @return
	 */
	public String pushTagMessage(int messageType,String message,String tagName);
	
	/**
	 * 广播消息推送(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
	 * @param messageType 消息类型,
	 * 		  message 要推送的消息
	 * @return
	 */
	public String pushBroadcastMessage(int messageType,String message);
}
