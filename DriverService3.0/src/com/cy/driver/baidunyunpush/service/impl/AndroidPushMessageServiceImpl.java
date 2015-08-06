package com.cy.driver.baidunyunpush.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.cy.driver.baidunyunpush.service.AndroidPushMessageService;
/**
 * 百度云消息推送
 * 参数：device_type => 1: web 2: pc 3:android 4:ios 5:wp
 * 		message_type = 0 (默认为0) {0:消息,1:通知}
 * 		通知：{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}
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
public class AndroidPushMessageServiceImpl implements AndroidPushMessageService{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	// 1. 设置developer平台的ApiKey/SecretKey
	private String apiKey;
	private String secretKey;
	private BaiduChannelClient channelClient;
	
	/**
	 * 初始化 BaiduChannelClient
	 */
	public void init() {
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);	
		// 2. 创建BaiduChannelClient对象实例
		channelClient = new BaiduChannelClient(pair);
	}
	
	/**
	 * 单播消息推送 (消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
	 * 必须指定手 机端的ChannelId， 手机端的UserId
	 * @param messageType 消息类型,
	 * 		  message 要推送的消息,
	 * 		  channelId, userId
	 * @return
	 */
	public String pushUnicastMessage (int messageType,String message,long channelId,String userId) {
		String responseMessage = "";
		// 3. 若要了解交互细节，请注册YunLogHandler类
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				log.info(event.getMessage());
			}
		});
		
		try {
			// 4. 创建请求类对象
			// 手机端的ChannelId， 手机端的UserId
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3);// device_type => 1: web 2: pc 3:android
									 // 4:ios 5:wp
			request.setChannelId(channelId);
			request.setUserId(userId);
			
			request.setMessageType(messageType);//message_type = 0 (默认为0) {0:消息,1:通知}
			request.setMessage(message);
			
			// 5. 调用pushMessage接口
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
			
			responseMessage = response.getSuccessAmount() + "";
			log.info("push amount:" + response.getSuccessAmount());
		} catch (ChannelClientException e) {
			 // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
        	log.error(String.format(
                    				"request_id: %d, error_code: %d, error_message: %s",
                    					e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
		
		return responseMessage;
	}
	
	/**
	 * 组播消息推送(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
	 * @param messageType 消息类型,
	 * 		  message 要推送的消息,
	 * 		  tagName
	 * @return
	 */
	public String pushTagMessage(int messageType,String message,String tagName) {
		String responseMessage = "";
		// 3. 若要了解交互细节，请注册YunLogHandler类
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				log.info(event.getMessage());
			}
		});
		
		try {
			PushTagMessageRequest request = new PushTagMessageRequest();
			
			request.setDeviceType(3);
			request.setTagName(tagName);
			request.setMessageType(messageType);
			request.setMessage(message);
			
			PushTagMessageResponse response = channelClient.pushTagMessage(request);
			
			responseMessage = response.getSuccessAmount() + "";
			log.info("push amount:" + response.getSuccessAmount());
		} catch (ChannelClientException e) {
			 // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
        	log.error(String.format(
    				"request_id: %d, error_code: %d, error_message: %s",
    					e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
		
		return responseMessage;
	}

	/**
	 * 广播消息推送(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
	 * @param messageType 消息类型,
	 * 		  message 要推送的消息
	 * @return
	 */
	public String pushBroadcastMessage(int messageType,String message) {
		String responseMessage = "";
		// 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
            	log.info(event.getMessage());
            }
        });
        
        try {

            // 4. 创建请求类对象
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android
                                      // 4:ios 5:wp

            request.setMessageType(messageType);
            request.setMessage(message);

            // 5. 调用pushMessage接口
            PushBroadcastMessageResponse response = channelClient
                    .pushBroadcastMessage(request);

            // 6. 认证推送成功
            responseMessage = response.getSuccessAmount() + "";
            log.info("push amount:" + response.getSuccessAmount());
        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
        	log.error(String.format(
    				"request_id: %d, error_code: %d, error_message: %s",
    					e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
		return responseMessage;
	}

	public void setBdApiKey(String bdApiKey) {
		apiKey = bdApiKey;
	}
	
	public void setBdSecretKey (String bdSecretKey) {
		secretKey = bdSecretKey;
	}
}
