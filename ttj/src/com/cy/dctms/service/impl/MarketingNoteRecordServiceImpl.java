package com.cy.dctms.service.impl;

import com.cy.dctms.common.bo.MarketingNoteRecordInfo;
import com.cy.dctms.common.bo.NoteSendRecord;
import com.cy.dctms.common.bo.PushSendRecord;
import com.cy.dctms.common.constants.Constants;
import com.cy.dctms.common.util.HttpPostUtil;
import com.cy.dctms.dao.CommonDao;
import com.cy.dctms.dao.MarketingNoteRecordDao;
import com.cy.dctms.service.MarketingNoteRecordService;
import com.cy.dctms.service.NoteSendRecoreService;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service("marketingNoteRecordService")
public class MarketingNoteRecordServiceImpl implements MarketingNoteRecordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MarketingNoteRecordDao marketingNoteRecordDao;

    @Resource
    private NoteSendRecoreService noteSendRecoreService;

    @Resource
    private CommonDao commonDao;

    /**
     * 发送短信
     *
     * @param type      发送对象类别 0企业 1司机
     * @param remark    备注
     * @param telephone 发送手机
     * @param content   发送内容
     * @param useFor
     * @return
     */
    @Override
    public String setNoteSendRecordInfo(String type, String remark, String telephone, String content, String useFor) {
        int sutra = 1;
        try {
            //发送短信
            String returnStr = sendingNote(telephone, content);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(returnStr);
            //推送接口发送返回状态 0成功，1失败
            sutra = node.path("errorCode") != null ? node.path("errorCode").asInt() : 1;
            String errMess = node.path("errorMsg") != null ? node.path("errorMsg").asText() : "发送失败，系统出错！";
            String objectString = node.path("object") != null ? node.path("object").asText() : "";
            int pushId = 0;
            if(!"".equals(objectString)) {
                JsonNode object = mapper.readTree(objectString);
                pushId = object.path("logKey") != null ? object.path("logKey").asInt() : 0;
            }

            NoteSendRecord noteSendRecord = new NoteSendRecord();
            noteSendRecord.setType(type);
            noteSendRecord.setUseFor(useFor);
            noteSendRecord.setTelephone(telephone);
            noteSendRecord.setContent(content);
            noteSendRecord.setEventFrom("5");
            noteSendRecord.setNoteSendedId(String.valueOf(pushId));
            noteSendRecord.setReturnStatus(String.valueOf(sutra));
            noteSendRecord.setRemark(remark);//被发送短信的人的号码保存到备注中

            noteSendRecoreService.insertIntoTemp(noteSendRecord);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String reCode = String.valueOf(sutra);
        return reCode;
    }


    /**
     * 推送
     *
     * @param driverId 司机id
     * @param title    推送标题
     * @param message  推送内容
     * @param id       自定义通知内容，选输项(不在通知栏显示，APP使用)Id
     * @param type     类型
     * @return
     */
    @Override
    public String setPushInfo(String driverId, String title, String message, String id, String type, String spKey) {
        int sutra = 1;//失败
        try {
            //查询系统参数   推送渠道
            String pushChannel = querySysCodeServ(Constants.CODE_PUSH_CHANNEL, spKey, "0");
            String returnStr = sendingPush(driverId, title, message, id, type, pushChannel);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(returnStr);
            //推送接口发送返回状态 0失败，1成功
            int code = node.path("errorCode") != null ? node.path("errorCode").asInt() : 0;
            String errMess = node.path("errorMsg") != null ? node.path("errorMsg").asText() : "推送失败，系统出错！";
            int pushId = node.path("object") != null ? node.path("object").asInt() : 0;//pk-t_push_log_info.id

            PushSendRecord pushSendRecord = new PushSendRecord();
            pushSendRecord.setEventFrom(5);
            pushSendRecord.setDriverId(Integer.parseInt(driverId));
            pushSendRecord.setPushId(pushId);
            pushSendRecord.setPushTitle(title);
            pushSendRecord.setPushContent(message);
            if (code == 1) {//推送成功
                sutra = 0;
            } else {//推送失败
                sutra = 1;
            }
            pushSendRecord.setReturnStatus(sutra);
            if (StringUtils.isNotEmpty(type)) {
                pushSendRecord.setTargetType(Integer.parseInt(type));
            }
            if (StringUtils.isNotEmpty(id)) {
                pushSendRecord.setTargetId(Integer.parseInt(id));
            }
            pushSendRecord.setUseFor(0);
            marketingNoteRecordDao.addPushSendRecord(pushSendRecord);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String reCode = String.valueOf(sutra);
        return reCode;
    }

    public void addMarketingNoteRecordInfo(String mobilephone, String content, Integer userId) {
        String[] mobilephoneString = mobilephone.split(",");
        for (int i = 0; i < mobilephoneString.length; i++) {
            Integer noteLogId = sendNoteSDKService(mobilephoneString[i], content);
            MarketingNoteRecordInfo marketingNoteRecordInfo = new MarketingNoteRecordInfo();
            marketingNoteRecordInfo.setMobilephone(mobilephoneString[i]);
            marketingNoteRecordInfo.setContent(content);
            marketingNoteRecordInfo.setNoteLogId(noteLogId);
            marketingNoteRecordInfo.setUserId(userId);
            marketingNoteRecordDao.addMarketingNoteRecordInfo(marketingNoteRecordInfo);
        }
    }
//    
    /*************************************************************发送短信方法*******************************************************/

    /**
     * *****************************************
     */
    private String sendNoteNbSDK;//内部短信接口URL
    private String sendPushSDK;
    //private String sendNoteNbtype;//通道类型
    /*********************************************/


    /**
     * 短信内部接口
     *
     * @param mobilephone
     * @param noteCode
     * @return
     */
    public String sendingNote(String mobilephone, String noteCode) {
        StringBuffer posturl = new StringBuffer(sendNoteNbSDK);
        String sendNoteNbtype = getSendNoteNbtype();
        Map<String, String> params = new HashMap<String, String>();
//        params.put("channelType", sendNoteNbtype);
        params.put("channelType", "qz");
        params.put("requestIp", "");
        params.put("phoneNum", mobilephone);
        params.put("content", noteCode);
        params.put("eventFrom", "5");//货源匹配车辆发送短信标志（调度系统标志）
        return HttpPostUtil.doPostRequest(posturl.toString(), params);

    }

    public String sendingPush(String driverId, String title, String message, String id, String type, String pushChannel) {
        StringBuffer posturl = new StringBuffer(sendPushSDK);
        Map<String, String> params = new HashMap<String, String>();
        String pushChannelStr = pushChannel;
        if (StringUtils.isEmpty(pushChannel)) {
            pushChannelStr = "1";//默认1（极光）
        }
        params.put("pushChannel", pushChannelStr);//推送渠道，默认1（极光），2百度
        params.put("eventFrom", "5");//推送请求来源 1 营销平台 2 快到网网站 3 app服务端 5调度系统
        if ("2".equals(pushChannel)) {
            params.put("type", "1");//百度推送需要的参数 推送方式，默认1（单播），2广播
        } else {
            params.put("type", "4");//极光推送 推送方式{1：全部，2：分组，3，4：one-to-one}
        }
        params.put("pushTitle", title);
        params.put("pushContent", message);
        params.put("driverId", driverId);
        params.put("tarId", id);
        params.put("jumpType", type);
        params.put("companyName", "");
        return HttpPostUtil.postXml(posturl.toString(), params);
    }

    /**
     * 解析短信返回json
     */
    @ResponseBody
    public Integer sendNoteSDKService(String mobilephone, String content) {
        Integer returnStatus = null;
        try {
            //发送短信
            String returnStr = sendingNote(mobilephone, content);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(returnStr);
            String jsonNote = node.path("object").asText();
            node = mapper.readTree(jsonNote);
            returnStatus = node.path("returnValue").asInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStatus;
    }


    public String getSendNoteNbtype() {
        String jeStr = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", "noteChannel");
            map.put("key", "marketing");
            map.put("deleteFlag", "0");
            map.put("paramType", "0");
            jeStr = commonDao.getSystemParameter(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jeStr;
    }

    /**
     * 发送短信
     *
     * @param type      发送对象类别 0企业 1司机
     * @param remark    备注
     * @param telephone 发送手机
     * @param content   发送内容
     * @param useFor
     * @return
     */
    @Override
    public boolean sendingNoteAndSaveBusiLog(String type, String remark, String telephone, String content, String useFor) {
        int sutra = 1;//失败
        try {
            sutra = 0;
            //发送短信
            String returnStr = sendingNote(telephone, content);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(returnStr);
            //推送接口发送返回状态 0成功，1失败
            sutra = node.path("errorCode") != null ? node.path("errorCode").asInt() : 1;
            String errMess = node.path("errorMsg") != null ? node.path("errorMsg").asText() : "发送失败，系统出错！";
            String objectString = node.path("object") != null ? node.path("object").asText() : "";
            int pushId = 0;
            if(!"".equals(objectString)) {
                JsonNode object = mapper.readTree(objectString);
                pushId = object.path("logKey") != null ? object.path("logKey").asInt() : 0;
            }
            NoteSendRecord noteSendRecord = new NoteSendRecord();
            noteSendRecord.setType(type);
            noteSendRecord.setUseFor(useFor);
            noteSendRecord.setTelephone(telephone);
            noteSendRecord.setContent(content);
            noteSendRecord.setEventFrom("5");
            noteSendRecord.setNoteSendedId(String.valueOf(pushId));
            noteSendRecord.setReturnStatus(String.valueOf(sutra));
            noteSendRecord.setRemark(remark);//被发送短信的人的号码保存到备注中

            noteSendRecoreService.insertIntoTemp(noteSendRecord);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        if (sutra == 0) { // 发送成功
            return true;
        } else {
            return false;
        }
    }


    /**
     * @param driverId 司机id
     * @param title    推送标题
     * @param message  推送内容
     * @param id       自定义通知内容，选输项(不在通知栏显示，APP使用)Id
     * @param type     类型
     * @return nxj
     */
    @Override
    public boolean sendingPushAndSaveBusiLog(String driverId, String title, String message, String id, String type, String spKey) {
        int sutra = 1;//失败
        try {
            //查询系统参数   推送渠道
            String pushChannel = querySysCodeServ(Constants.CODE_PUSH_CHANNEL, spKey, "0");
            String returnStr = sendingPush(driverId, title, message, id, type, pushChannel);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(returnStr);
            //推送接口发送返回状态 0失败，1成功
            int code = node.path("errorCode") != null ? node.path("errorCode").asInt() : 0;
            String errMess = node.path("errorMsg") != null ? node.path("errorMsg").asText() : "推送失败，系统出错！";
            int pushId = node.path("object") != null ? node.path("object").asInt() : 0;//pk-t_push_log_info.id

            PushSendRecord pushSendRecord = new PushSendRecord();
            pushSendRecord.setEventFrom(5);
            pushSendRecord.setDriverId(Integer.parseInt(driverId));
            pushSendRecord.setPushId(pushId);
            pushSendRecord.setPushTitle(title);
            pushSendRecord.setPushContent(message);
            if (code == 1) {//推送成功
                sutra = 0;
            } else {//推送失败
                sutra = 1;
            }
            pushSendRecord.setReturnStatus(sutra);
            if (StringUtils.isNotEmpty(type)) {
                pushSendRecord.setTargetType(Integer.parseInt(type));
            }
            if (StringUtils.isNotEmpty(id)) {
                pushSendRecord.setTargetId(Integer.parseInt(id));
            }
            pushSendRecord.setUseFor(0);
            marketingNoteRecordDao.addPushSendRecord(pushSendRecord);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sutra == 0) {
            return true;
        }
        return false;
    }

    //查询系统参数
    private String querySysCodeServ(String code, String spKey, String paramType) throws SQLException {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("deleteFlag", 0);
        paraMap.put("paramType", paramType);
        paraMap.put("spKey", spKey);
        paraMap.put("code", code);
        return commonDao.getSystemParameter(paraMap);
    }


    @Value("#{propertiesReader['note.request.url']}")
    public void sendNoteNbSDK(String sendNoteNbSDK) {
        this.sendNoteNbSDK = sendNoteNbSDK;
    }

    @Value("#{propertiesReader['push.request.url']}")
    public void sendPushSDK(String sendPushSDK) {
        this.sendPushSDK = sendPushSDK;
    }

}
