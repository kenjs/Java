package com.cy.interfaceService.service.impl;

import com.cy.interfaceService.bo.NoteLogInfo;
import com.cy.interfaceService.bo.NotesendBlacklist;
import com.cy.interfaceService.common.HttpUtils;
import com.cy.interfaceService.common.Md5Util;
import com.cy.interfaceService.dao.NoteDao;
import com.cy.interfaceService.dao.NotesendBlacklistDao;
import com.cy.interfaceService.domain.NoteInfo;
import com.cy.interfaceService.service.NoteSendService;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by haoy on 2014/10/16.
 */
@Service("noteSendService")
public class NoteSendServiceImpl implements NoteSendService{

    private String wdRequestUrl;
    private String qzRequestUrl;
    private String nbRequestUrl;
    private String zsRequestUrl;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private NoteDao noteDao;

    @Resource
    private NotesendBlacklistDao notesendBlacklistDao;

    @Override
    public NoteInfo sendNote(NoteLogInfo logInfo) throws Exception {
        String phoneNum = logInfo.getPhoneNum();
        String content = logInfo.getContent();
        String channelType = logInfo.getChannelType();
        NoteInfo info = null;

        String sendRst = "";
        int sendStart = 1;
        if ("qz".equalsIgnoreCase(channelType)) {
            /**
             * 短信黑名单交验
             */
            List<NotesendBlacklist> list = notesendBlacklistDao.queryNotesendBlacklist(phoneNum);
            if(list.size()>0) {
                info = new NoteInfo(phoneNum, -1, 0);
                if (log.isDebugEnabled()) {
                    log.debug("此手机号码：" + phoneNum+",已加入黑名单，不予发送短信！");
                }
                return info;
            }
            content = content.replace("【快到网】","");
            Map<String, String> params = new  HashMap<String, String>();
            params.put("flag", "sendsms");
            params.put("loginname", "kdw");
            params.put("password", "hioPLH68Q5");
            params.put("",  new Md5Util().getMD5ofStr("hioPLH68Q5").toUpperCase());
            params.put("p", phoneNum);
            params.put("c", URLEncoder.encode(content, "UTF-8"));

//            String url = qzRequestUrl + "?flag=sendsms" + "&loginname=kdw&password=kdw@kdw&"
//                    + new Md5Util().getMD5ofStr("kdw@kdw").toUpperCase() + "&p="
//                    + phoneNum + "&c=" + URLEncoder.encode(content, "UTF-8");
//            sendRst = HttpUtils.doGetRequest(url);
            sendRst = HttpUtils.doPostRequest(qzRequestUrl, params);
            if (sendRst.contains(",")) {
                String value = sendRst.split(",")[0];
                if (StringUtils.isNotBlank(value)) {
                    sendStart = Integer.parseInt(value);
                }
            }
        } else if ("wd".equalsIgnoreCase(channelType)) {
            Map<String, String> params = new  HashMap<String, String>();
            params.put("action", "send");
            params.put("userid", "71");
            params.put("account", "kdw");
            params.put("password", "123456");
            params.put("",  new Md5Util().getMD5ofStr("kdw@kdw").toUpperCase());
            params.put("mobile", phoneNum);
            params.put("content", URLEncoder.encode(content, "GBK"));

//            String urlStr = wdRequestUrl + "?action=send&userid=71&account=快到网&password=654321&mobile="
//                    +phoneNum+"&content="+content;
//            sendRst = HttpUtils.noteSendTow(urlStr);
            sendRst = HttpUtils.doPostRequest(wdRequestUrl, params);
            Map map = parseXml2Map(sendRst);
            if("success".equalsIgnoreCase(map.get("returnstatus").toString())) {
                sendStart = 0;
            }
        } else if ("nb".equalsIgnoreCase(channelType)) {
           //content = content.replace("【快到网】","");
            int yzm = Integer.parseInt(phoneNum.substring(7,11)) * 3 + 2345;
            Map<String, String> params = new  HashMap<String, String>();
            params.put("tjpc", "123");
            params.put("usr", "chen");
            params.put("pwd", "chen1234");
            params.put("mobile", phoneNum);
            params.put("msg", URLEncoder.encode(content, "GBK"));
            params.put("yzm", yzm + "");

//            String url = nbRequestUrl + "?tjpc=123&usr=chen&pwd=chen1234&mobile=" + phoneNum + "&msg=" + content + "&yzm=" + yzm;
//            sendRst = HttpUtils.doGetRequest(url);
            sendRst = HttpUtils.doPostRequest(nbRequestUrl, params);
            sendStart = Integer.parseInt(sendRst.trim());
        }else if ("nbh".equalsIgnoreCase(channelType)) {
            /**
             * 短信黑名单交验
             */
            List<NotesendBlacklist> list = notesendBlacklistDao.queryNotesendBlacklist(phoneNum);
            if(list.size()>0) {
                info = new NoteInfo(phoneNum, -1, 0);
                if (log.isDebugEnabled()) {
                    log.debug("此手机号码：" + phoneNum+",已加入黑名单，不予发送短信！");
                }
                return info;
            }
            //content = content.replace("【快到网】","");
            int yzm = Integer.parseInt(phoneNum.substring(7,11)) * 3 + 4434;
            Map<String, String> params = new  HashMap<String, String>();
            params.put("tjpc", "123");
            params.put("usr", "kdwhy");
            params.put("pwd", "kdwhy123@");
            params.put("mobile", phoneNum);
            params.put("msg", URLEncoder.encode(content, "GBK"));
            params.put("yzm", yzm + "");

//            String url = nbRequestUrl + "?tjpc=123&usr=chen&pwd=chen1234&mobile=" + phoneNum + "&msg=" + content + "&yzm=" + yzm;
//            sendRst = HttpUtils.doGetRequest(url);
            sendRst = HttpUtils.doPostRequest(nbRequestUrl, params);
            sendStart = Integer.parseInt(sendRst.trim());
        } else if ("zs".equalsIgnoreCase(channelType)) {
            String md5 = Md5Util.MD5("1000663" + "||"+phoneNum+"||"+ "kdwyzm6688");

            Map<String, String> params = new  HashMap<String, String>();
            params.put("userid", "1000663");
            params.put("smstype", "0");
            params.put("phones", phoneNum);
            params.put("content", URLEncoder.encode(content,"UTF-8"));
            params.put("sendtermid", "9999");
            params.put("sendtime", "1");
            params.put("md5", md5.toLowerCase());

            sendRst = HttpUtils.doPostRequest(zsRequestUrl, params);

            String returnStatus = getXmlStr(sendRst, "status");
            if("0".equals(returnStatus)) {
                sendStart = 0;
            }else {
                sendStart = 1;
            }

        }
        logInfo.setSendStart(sendStart);
        logInfo.setReturnedValue(sendRst);
        if (StringUtils.isBlank(logInfo.getCompanyId())) {
            logInfo.setCompanyId("1");
        }
        logInfo.setKind("0");
        noteDao.addNoteLogInfo(logInfo);
        long logKey = logInfo.getId();

        info = new NoteInfo(phoneNum, sendStart, logKey);

        if (log.isDebugEnabled()) {
            log.debug("短信发送返回结果：" + sendRst);
        }
        return info;
    }

    @Override
    public List sendNote(String requestIp, String json) throws Exception {
        NoteLogInfo noteLogInfo;

        List<NoteInfo> noteInfos = new ArrayList<NoteInfo>();

        String phoneNum;
        String content;
        String channelType;
        String companyId;
        List<Map<String, Object>> list = parseJSon2List(json);
        for (int i = 0; i < list.size(); i++) {
            noteLogInfo = new NoteLogInfo();

            Map<String,Object> map = list.get(i);
            phoneNum = (String) map.get("phone");
            content = (String) map.get("content");
            channelType = (String) map.get("channelType");
            Object obj = map.get("companyId");
            if (obj == null) {
                companyId = "1";
            } else {
                companyId = (String) obj;
            }

            noteLogInfo.setChannelType(channelType);
            noteLogInfo.setPhoneNum(phoneNum);
            noteLogInfo.setRequestIp(requestIp);
            noteLogInfo.setContent(content);
            noteLogInfo.setCompanyId(companyId);
            noteLogInfo.setEventFrom(4);

            NoteInfo info = sendNote(noteLogInfo);
            noteInfos.add(info);
        }
        return noteInfos;
    }

    /**
     * json 字符串解析为list
     * @param json
     * @return
     * @throws Exception
     */
    private List<Map<String, Object>> parseJSon2List(String json) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> list = null;
        try {
            list = objectMapper.readValue(json, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析XML为map
     * @param xml
     * @return
     */
    public Map parseXml2Map(String xml) {
        Map map = new HashMap();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            String returnstatus = rootElt.elementTextTrim("returnstatus"); // 拿到returnsms节点下的子节点title值
            String message = rootElt.elementTextTrim("message"); // 拿到returnsms节点下的子节点title值
            String remainpoint = rootElt.elementTextTrim("remainpoint"); // 拿到returnsms节点下的子节点title值
            String taskID = rootElt.elementTextTrim("taskID"); // 拿到returnsms节点下的子节点title值
            String successCounts = rootElt.elementTextTrim("successCounts"); // 拿到returnsms节点下的子节点title值
            map.put("returnstatus", returnstatus);
            map.put("message", message);
            map.put("remainpoint", remainpoint);
            map.put("taskID", taskID);
            map.put("successCounts", successCounts);
        } catch (DocumentException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * xml转map
     * @param xml
     * @return
     */
    public String getXmlStr(String xml,String key) {
        Map<String,String> map = new HashMap<String, String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
            Element rootEle = doc.getRootElement();
            List<Attribute> list = rootEle.attributes();
            for (Attribute attribute : list) {
                String name = attribute.getName();
                String value = attribute.getValue();
                map.put(name, value);
            }
            Iterator iterator = rootEle.elementIterator("taskid");
            while(iterator.hasNext()) {
                Element taskIdEle = (Element) iterator.next();
                map.put(taskIdEle.getName(), taskIdEle.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map.get(key);
    }

    public String getWdRequestUrl() {
        return wdRequestUrl;
    }

    @Value("#{propertiesReader['wd.request.url']}")
    public void setWdRequestUrl(String wdRequestUrl) {
        this.wdRequestUrl = wdRequestUrl;
    }

    public String getQzRequestUrl() {
        return qzRequestUrl;
    }

    @Value("#{propertiesReader['qz.request.url']}")
    public void setQzRequestUrl(String qzRequestUrl) {
        this.qzRequestUrl = qzRequestUrl;
    }

    public String getNbRequestUrl() {
        return nbRequestUrl;
    }

    @Value("#{propertiesReader['nb.request.url']}")
    public void setNbRequestUrl(String nbRequestUrl) {
        this.nbRequestUrl = nbRequestUrl;
    }

    public String getZsRequestUrl() {
        return zsRequestUrl;
    }

    @Value("#{propertiesReader['zs.request.url']}")
    public void setZsRequestUrl(String zsRequestUrl) {
        this.zsRequestUrl = zsRequestUrl;
    }
}
