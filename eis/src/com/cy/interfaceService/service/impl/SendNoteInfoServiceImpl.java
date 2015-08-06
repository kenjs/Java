package com.cy.interfaceService.service.impl;

import com.cy.interfaceService.bo.NoteLogInfo;
import com.cy.interfaceService.bo.NotesendBlacklist;
import com.cy.interfaceService.common.HttpUtils;
import com.cy.interfaceService.common.Md5Util;
import com.cy.interfaceService.common.MobileUtil;
import com.cy.interfaceService.dao.NoteDao;
import com.cy.interfaceService.dao.NotesendBlacklistDao;
import com.cy.interfaceService.domain.NoteInfo;
import com.cy.interfaceService.service.SendNoteInfoService;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
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
 * Created by nixianjing on 15/1/28.
 */
@Service("sendNoteInfoService")
public class SendNoteInfoServiceImpl implements SendNoteInfoService {

    /**
     * 沃动
     */
    private String wdRequestUrl;
    private String wdName;
    private String wdPassWord;
    private String wdAction;
    private String wdUserid;
    private String wdCharacterCode;
    private String wdIsNoSignName;
    private String wdIsNoFront;
    private String wdMaxSendNumber;


    /**
     * 群正
     */
    private String qzRequestUrl;
    private String qzName;
    private String qzPassWord;
    private String qzFlag;
    private String qzCharacterCode;
    private String qzIsNoSignName;
    private String qzIsNoFront;
    private String qzMaxSendNumber;


    /**
     * 宁波验证码短信通道
     */
    private String nbRequestUrl;
    private String nbName;
    private String nbPassWord;
    private String nbTjpc;
    private String nbYzm;
    private String nbCharacterCode;
    private String nbIsNoSignName;
    private String nbIsNoFront;
    private String nbMaxSendNumber;


    /**
     * 宁波行业通道
     */
    private String nbhRequestUrl;
    private String nbhName;
    private String nbhPassWord;
    private String nbhTjpc;
    private String nbhYzm;
    private String nbhCharacterCode;
    private String nbhIsNoSignName;
    private String nbhIsNoFront;
    private String nbhMaxSendNumber;

    /**
     * 沃伦短信通道
     */
    private String wlRequestUrl;
    private String wlLoginname;
    private String wlPassword;
    private String wlContentEncoding;
    private String wlNeedSign;
    private String wlPrefixSign;


    /**
     * 短信性质主备
     */
    private String yzmKey;
    private String xttzKey;
    private String dxyxKey;
    private String dhxsKey;
    private String chppKey;

    /**
     * 短信性质要过黑名单值
     */
    private String hmdValue;

    /**
     * 手机号码运营商
     */
    private String mobileSectionNo;//移动
    private String unicomSectionNo;//联通
    private String telecomSectionNo;//电信


    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private NoteDao noteDao;

    @Resource
    private NotesendBlacklistDao notesendBlacklistDao;


    /**
     *
     * @param noteLogInfo
     * @return
     * @throws Exception
     * @kid 短信性质：1 验证码短信 2 系统通知 3  短信营销 4  电话营销 5  车配匹配
     */
    @Override
    public Map<String,Object> sendNote(NoteLogInfo noteLogInfo) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        if(isNotNotesendBlacklist(noteLogInfo.getKind(), noteLogInfo.getPhoneNum(), noteLogInfo.getSendOutType())) {
            map.put("sendStart","-1");
            map.put("logKey","0");
            return map;
        }
        if ("1".equals(noteLogInfo.getKind())) {//验证码短信
            String[] channel = yzmKey.split(",");
            for(int i = 0;i<channel.length;i++) {
                map = channelSendNote(noteLogInfo,channel[i]);
                if("0".equals(map.get("sendStart").toString())){
                    break;
                }
            }
        }else if("2".equals(noteLogInfo.getKind())){//系统通知
            String[] channel = xttzKey.split(",");
            for(int i = 0;i<channel.length;i++) {
                map = channelSendNote(noteLogInfo,channel[i]);
                if("0".equals(map.get("sendStart").toString())){
                    break;
                }
            }
        }else if("3".equals(noteLogInfo.getKind())){//短信营销
            String[] channel = dxyxKey.split(",");
            for(int i = 0;i<channel.length;i++) {
                map = channelSendNote(noteLogInfo,channel[i]);
                if("0".equals(map.get("sendStart").toString())){
                    break;
                }
            }
        }else if("4".equals(noteLogInfo.getKind())){//电话营销
            String[] channel = dhxsKey.split(",");
            for(int i = 0;i<channel.length;i++) {
                map = channelSendNote(noteLogInfo,channel[i]);
                if("0".equals(map.get("sendStart").toString())){
                    break;
                }
            }
        }else if("5".equals(noteLogInfo.getKind())){//车配匹配
            noteLogInfo.setContent(noteLogInfo.getContent()+" /退订回复N");
            String[] channel = chppKey.split(",");
            for(int i = 0;i<channel.length;i++) {
                map = channelSendNote(noteLogInfo,channel[i]);
                if("0".equals(map.get("sendStart").toString())){
                    break;
                }
            }
        }else{
            map.put("sendStart","-2");
            map.put("logKey","0");
            return map;
        }
        return map;
    }


    /**
     * 根据短信通道发送短信
     * @param noteLogInfo 发送短信对象
     * @param channelType 通道类型
     * @return
     * @throws Exception
     */
    private Map<String,Object> channelSendNote(NoteLogInfo noteLogInfo,String channelType) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        if("wd".equals(channelType)) {
            noteLogInfo.setContent(resetSendNoteContent(noteLogInfo.getContent(),"wd"));//短信内容重置
            map = wdSendNote(noteLogInfo);
        }else if("nb".equals(channelType)) {
            noteLogInfo.setContent(resetSendNoteContent(noteLogInfo.getContent(),"nb"));//短信内容重置
            map = nbSendNote(noteLogInfo);
        }else if("nbh".equals(channelType)) {
            noteLogInfo.setContent(resetSendNoteContent(noteLogInfo.getContent(),"nbh"));//短信内容重置
            map = nbhSendNote(noteLogInfo);
        }else if("nbz".equals(channelType)) {
            map.put("sendStart","1");
            map.put("logKey","0");
//            noteLogInfo.setContent(resetSendNoteContent(noteLogInfo.getContent(),"nbz"));//短信内容重置
//            map = nbzSendNote(noteLogInfo);
        }else if("qz".equals(channelType)) {
            noteLogInfo.setContent(resetSendNoteContent(noteLogInfo.getContent(),"qz"));//短信内容重置
            map = qzSendNote(noteLogInfo);
        }else if("wl".equals(channelType)) {
            noteLogInfo.setContent(resetSendNoteContent(noteLogInfo.getContent(),"wl"));//短信内容重置
            map = wlSendNote(noteLogInfo);
        }
        return map;
    }


    /**
     * 沃动短信
     * @param noteLogInfo
     * @return
     */
    private Map<String,Object> wdSendNote(NoteLogInfo noteLogInfo) throws Exception {
        String sendRst = "";
        int sendStart = 1;
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", wdAction);
        params.put("userid", wdUserid);
        params.put("account", wdName);
        params.put("password", wdPassWord);
        params.put("",  new Md5Util().getMD5ofStr("kdw@kdw").toUpperCase());
        params.put("mobile", noteLogInfo.getPhoneNum());
        params.put("content", URLEncoder.encode(noteLogInfo.getContent(), wdCharacterCode));
        sendRst = HttpUtils.doPostRequest(wdRequestUrl, params);
        Map map = parseXml2Map(sendRst);
        if("success".equalsIgnoreCase(map.get("returnstatus").toString())) {
            sendStart = 0;
        }
        noteLogInfo.setSendStart(sendStart);
        noteLogInfo.setReturnedValue(sendRst);
        if (StringUtils.isBlank(noteLogInfo.getCompanyId())) {
            noteLogInfo.setCompanyId("1");
        }
        noteLogInfo.setChannelType("wd");
        noteDao.addNoteLogInfo(noteLogInfo);
        long logKey = noteLogInfo.getId();
        Map<String,Object> remap = new HashMap<String, Object>();
        remap.put("sendStart",sendStart);
        remap.put("logKey",logKey);
        return remap;
    }

    /**
     * 群正短信通道
     * @param noteLogInfo
     * @return
     * @throws Exception
     */
    private Map<String,Object> qzSendNote(NoteLogInfo noteLogInfo) throws Exception {
        String sendRst = "";
        int sendStart = 1;
        NoteInfo noteInfo = null;
        Map<String, String> params = new  HashMap<String, String>();
        params.put("flag", qzFlag);
        params.put("loginname", qzName);
        params.put("password", qzPassWord);
        params.put("",  new Md5Util().getMD5ofStr(qzPassWord).toUpperCase());
        params.put("p", noteLogInfo.getPhoneNum());
        params.put("c", URLEncoder.encode(noteLogInfo.getContent(), qzCharacterCode));
        sendRst = HttpUtils.doPostRequest(qzRequestUrl, params);
        if (sendRst.contains(",")) {
            String value = sendRst.split(",")[0];
            if (StringUtils.isNotBlank(value)) {
                sendStart = Integer.parseInt(value);
            }
        }
        if(sendStart == 0) {
            sendStart = 0;
        }else {
            sendStart = 1;
        }
        noteLogInfo.setSendStart(sendStart);
        noteLogInfo.setReturnedValue(sendRst);
        if (StringUtils.isBlank(noteLogInfo.getCompanyId())) {
            noteLogInfo.setCompanyId("1");
        }
        noteLogInfo.setChannelType("qz");
        noteDao.addNoteLogInfo(noteLogInfo);
        long logKey = noteLogInfo.getId();
        Map<String,Object> remap = new HashMap<String, Object>();
        remap.put("sendStart",sendStart);
        remap.put("logKey",logKey);
        return remap;
    }

    /**
     * 宁波验证码通道
     * @param noteLogInfo
     * @return
     * @throws Exception
     */
    private Map<String,Object> nbSendNote(NoteLogInfo noteLogInfo) throws Exception {
        String sendRst = "";
        int sendStart = 1;
        int yzm = Integer.parseInt(noteLogInfo.getPhoneNum().substring(7, 11)) * 3 + Integer.parseInt(nbYzm);
        Map<String, String> params = new  HashMap<String, String>();
        params.put("tjpc", nbTjpc);
        params.put("usr", nbName);
        params.put("pwd", nbPassWord);
        params.put("mobile", noteLogInfo.getPhoneNum());
        params.put("msg", URLEncoder.encode(noteLogInfo.getContent(), nbCharacterCode));
        params.put("yzm", yzm + "");
        sendRst = HttpUtils.doPostRequest(nbRequestUrl, params);
        sendStart = Integer.parseInt(sendRst.trim());
        if(sendStart == 0) {
            sendStart = 0;
        }else {
            sendStart = 1;
        }

        noteLogInfo.setSendStart(sendStart);
        noteLogInfo.setReturnedValue(sendRst);
        if (StringUtils.isBlank(noteLogInfo.getCompanyId())) {
            noteLogInfo.setCompanyId("1");
        }
        noteLogInfo.setChannelType("nb");
        noteDao.addNoteLogInfo(noteLogInfo);
        long logKey = noteLogInfo.getId();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("sendStart",sendStart);
        map.put("logKey",logKey);
        return map;
    }


    /**
     * 宁波行业
     * @param noteLogInfo
     * @return
     * @throws Exception
     */
    private Map<String,Object> nbhSendNote(NoteLogInfo noteLogInfo) throws Exception {
        String sendRst = "";
        int sendStart = 1;
        int yzm = Integer.parseInt(noteLogInfo.getPhoneNum().substring(7, 11)) * 3 + Integer.parseInt(nbhYzm);
        Map<String, String> params = new  HashMap<String, String>();
        params.put("tjpc", nbhTjpc);
        params.put("usr", nbhName);
        params.put("pwd", nbhPassWord);
        params.put("mobile", noteLogInfo.getPhoneNum());
        params.put("msg", URLEncoder.encode(noteLogInfo.getContent(), nbhCharacterCode));
        params.put("yzm", yzm + "");
        sendRst = HttpUtils.doPostRequest(nbhRequestUrl, params);
        sendStart = Integer.parseInt(sendRst.trim());
        if(sendStart == 0) {
            sendStart = 0;
        }else {
            sendStart = 1;
        }
        noteLogInfo.setSendStart(sendStart);
        noteLogInfo.setReturnedValue(sendRst);
        if (StringUtils.isBlank(noteLogInfo.getCompanyId())) {
            noteLogInfo.setCompanyId("1");
        }
        noteLogInfo.setChannelType("nbh");
        noteDao.addNoteLogInfo(noteLogInfo);
        long logKey = noteLogInfo.getId();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("sendStart",sendStart);
        map.put("logKey",logKey);
        return map;
    }

    /**
     * 沃伦短信发送
     * @param noteLogInfo
     * @return
     * @throws Exception
     */
    private Map<String, Object> wlSendNote(NoteLogInfo noteLogInfo) throws Exception {
        //构建请求参数
        Map<String, String> pars = new HashMap<String, String>();
        pars.put("loginname", wlLoginname);
        pars.put("password", wlPassword);
        pars.put("mobile", noteLogInfo.getPhoneNum());
        pars.put("content", URLEncoder.encode(noteLogInfo.getContent(), wlContentEncoding));
        pars.put("extNo", "");
        String returnValue = HttpUtils.doPostRequest(wlRequestUrl, pars);

        StringTokenizer st = new StringTokenizer(returnValue, ";");
        int counts = st.countTokens();

        int sendState = 1;
        if (counts > 1) {
            String total = "";
            String success = "";
            String lose = "";
            while (st.hasMoreElements()) {
                String next = st.nextToken().replaceAll("\\s*", "");
                if (next.contains("total") && next.contains(":")) {
                    total = next.split(":")[1];
                } else
                    if (next.contains("success") && next.contains(":")) {
                        success = next.split(":")[1];
                    } else
                        if (next.contains("lose") && next.contains(":")) {
                            lose = next.split(":")[1];
                        }
            }
            if ("0".equals(lose)) {
                sendState = 0;
            }
        }
        noteLogInfo.setSendStart(sendState);
        noteLogInfo.setReturnedValue(returnValue);
        if (StringUtils.isBlank(noteLogInfo.getCompanyId())) {
            noteLogInfo.setCompanyId("1");
        }
        noteLogInfo.setChannelType("wl");
        noteDao.addNoteLogInfo(noteLogInfo);
        long logKey = noteLogInfo.getId();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("sendStart",sendState);
        map.put("logKey",logKey);
        return map;
    }


    /**
     * 重置短信内容
     * @param content 短信内容
     * @param sendType 短信通道类型
     * @return
     */
    private String resetSendNoteContent(String content,String sendType){
        content = content.replace("【快到网】","");
        if("wd".equals(sendType)) {//验证码短信
            if("YES".equals(wdIsNoSignName)) {//加签名
                if("YES".equals(wdIsNoFront)) {//签名前置
                    content = "【快到网】"+content;
                }else {//签名后置
                    content = content+"【快到网】";
                }
            }
        }else if("qz".equals(sendType)){//系统通知
            if("YES".equals(qzIsNoSignName)) {//加签名
                if("YES".equals(qzIsNoFront)) {//签名前置
                    content = "【快到网】"+content;
                }else {//签名后置
                    content = content+"【快到网】";
                }
            }
        }else if("nb".equals(sendType)){//短信营销
            if("YES".equals(nbIsNoSignName)) {//加签名
                if("YES".equals(nbIsNoFront)) {//签名前置
                    content = "【快到网】"+content;
                }else {//签名后置
                    content = content+"【快到网】";
                }
            }
        }else if("nbh".equals(sendType)){//电话营销
            if("YES".equals(nbhIsNoSignName)) {//加签名
                if("YES".equals(nbhIsNoFront)) {//签名前置
                    content = "【快到网】"+content;
                }else {//签名后置
                    content = content+"【快到网】";
                }
            }
        } else if ("wl".equals(sendType)) {
            if("YES".equals(wlNeedSign)) {//加签名
                if("YES".equals(wlPrefixSign)) {//签名前置
                    content = "【快到网】"+content;
                }else {//签名后置
                    content = content+"【快到网】";
                }
            }
        }
        return content;
    }


    /**
     * 验证司机是否在黑名单
     * @param phoneNum
     * @return true在黑名单 false不再黑名单
     */
    private boolean isNotNotesendBlacklist(String kind,String phoneNum,String sendOutType) {
        boolean state = false;
        String[] hmdKey = hmdValue.toString().split(",");
        for(int i = 0;i<hmdKey.length;i++) {
            if(hmdKey[i].toString().equals(kind)) {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("mobilephone",phoneNum);
                map.put("disableNoteType",sendOutType);
                map.put("deletedFlag","0");
                List<NotesendBlacklist> list = notesendBlacklistDao.queryNotesendBlacklistMap(map);
                if(list.size()>0) {
                    state = true;
                }
            }
            if(state) {
                break;
            }
        }
        return state;
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


    public String getWdRequestUrl() {
        return wdRequestUrl;
    }

    @Value("#{propertiesReader['wd.request.url']}")
    public void setWdRequestUrl(String wdRequestUrl) {
        this.wdRequestUrl = wdRequestUrl;
    }

    public String getWdName() {
        return wdName;
    }

    @Value("#{propertiesReader['wd.request.name']}")
    public void setWdName(String wdName) {
        this.wdName = wdName;
    }

    public String getWdPassWord() {
        return wdPassWord;
    }

    @Value("#{propertiesReader['wd.request.password']}")
    public void setWdPassWord(String wdPassWord) {
        this.wdPassWord = wdPassWord;
    }

    public String getWdAction() {
        return wdAction;
    }

    @Value("#{propertiesReader['wd.request.action']}")
    public void setWdAction(String wdAction) {
        this.wdAction = wdAction;
    }

    public String getWdUserid() {
        return wdUserid;
    }

    @Value("#{propertiesReader['wd.request.userid']}")
    public void setWdUserid(String wdUserid) {
        this.wdUserid = wdUserid;
    }

    public String getWdCharacterCode() {
        return wdCharacterCode;
    }

    @Value("#{propertiesReader['wd.request.characterCode']}")
    public void setWdCharacterCode(String wdCharacterCode) {
        this.wdCharacterCode = wdCharacterCode;
    }

    public String getWdIsNoSignName() {
        return wdIsNoSignName;
    }

    @Value("#{propertiesReader['wd.request.isNoSignName']}")
    public void setWdIsNoSignName(String wdIsNoSignName) {
        this.wdIsNoSignName = wdIsNoSignName;
    }

    public String getWdIsNoFront() {
        return wdIsNoFront;
    }

    @Value("#{propertiesReader['wd.request.isNoFront']}")
    public void setWdIsNoFront(String wdIsNoFront) {
        this.wdIsNoFront = wdIsNoFront;
    }

    public String getWdMaxSendNumber() {
        return wdMaxSendNumber;
    }

    @Value("#{propertiesReader['wd.request.maxSendNumber']}")
    public void setWdMaxSendNumber(String wdMaxSendNumber) {
        this.wdMaxSendNumber = wdMaxSendNumber;
    }

    public String getQzMaxSendNumber() {
        return qzMaxSendNumber;
    }

    @Value("#{propertiesReader['qz.request.maxSendNumber']}")
    public void setQzMaxSendNumber(String qzMaxSendNumber) {
        this.qzMaxSendNumber = qzMaxSendNumber;
    }

    public String getQzRequestUrl() {
        return qzRequestUrl;
    }

    @Value("#{propertiesReader['qz.request.url']}")
    public void setQzRequestUrl(String qzRequestUrl) {
        this.qzRequestUrl = qzRequestUrl;
    }

    public String getQzName() {
        return qzName;
    }

    @Value("#{propertiesReader['qz.request.name']}")
    public void setQzName(String qzName) {
        this.qzName = qzName;
    }

    public String getQzPassWord() {
        return qzPassWord;
    }

    @Value("#{propertiesReader['qz.request.password']}")
    public void setQzPassWord(String qzPassWord) {
        this.qzPassWord = qzPassWord;
    }

    public String getQzFlag() {
        return qzFlag;
    }

    @Value("#{propertiesReader['qz.request.flag']}")
    public void setQzFlag(String qzFlag) {
        this.qzFlag = qzFlag;
    }

    public String getQzCharacterCode() {
        return qzCharacterCode;
    }

    @Value("#{propertiesReader['qz.request.characterCode']}")
    public void setQzCharacterCode(String qzCharacterCode) {
        this.qzCharacterCode = qzCharacterCode;
    }

    public String getQzIsNoSignName() {
        return qzIsNoSignName;
    }

    @Value("#{propertiesReader['qz.request.isNoSignName']}")
    public void setQzIsNoSignName(String qzIsNoSignName) {
        this.qzIsNoSignName = qzIsNoSignName;
    }

    public String getQzIsNoFront() {
        return qzIsNoFront;
    }

    @Value("#{propertiesReader['qz.request.isNoFront']}")
    public void setQzIsNoFront(String qzIsNoFront) {
        this.qzIsNoFront = qzIsNoFront;
    }

    public String getNbRequestUrl() {
        return nbRequestUrl;
    }

    @Value("#{propertiesReader['nb.request.url']}")
    public void setNbRequestUrl(String nbRequestUrl) {
        this.nbRequestUrl = nbRequestUrl;
    }

    public String getNbName() {
        return nbName;
    }

    @Value("#{propertiesReader['nb.request.name']}")
    public void setNbName(String nbName) {
        this.nbName = nbName;
    }

    public String getNbPassWord() {
        return nbPassWord;
    }

    @Value("#{propertiesReader['nb.request.password']}")
    public void setNbPassWord(String nbPassWord) {
        this.nbPassWord = nbPassWord;
    }

    public String getNbTjpc() {
        return nbTjpc;
    }

    @Value("#{propertiesReader['nb.request.tjpc']}")
    public void setNbTjpc(String nbTjpc) {
        this.nbTjpc = nbTjpc;
    }

    public String getNbCharacterCode() {
        return nbCharacterCode;
    }

    @Value("#{propertiesReader['nb.request.characterCode']}")
    public void setNbCharacterCode(String nbCharacterCode) {
        this.nbCharacterCode = nbCharacterCode;
    }

    public String getNbIsNoSignName() {
        return nbIsNoSignName;
    }

    @Value("#{propertiesReader['nb.request.isNoSignName']}")
    public void setNbIsNoSignName(String nbIsNoSignName) {
        this.nbIsNoSignName = nbIsNoSignName;
    }

    public String getNbIsNoFront() {
        return nbIsNoFront;
    }

    @Value("#{propertiesReader['nb.request.isNoFront']}")
    public void setNbIsNoFront(String nbIsNoFront) {
        this.nbIsNoFront = nbIsNoFront;
    }

    public String getNbMaxSendNumber() {
        return nbMaxSendNumber;
    }

    @Value("#{propertiesReader['nb.request.maxSendNumber']}")
    public void setNbMaxSendNumber(String nbMaxSendNumber) {
        this.nbMaxSendNumber = nbMaxSendNumber;
    }

    public String getNbhRequestUrl() {
        return nbhRequestUrl;
    }

    @Value("#{propertiesReader['nbh.request.url']}")
    public void setNbhRequestUrl(String nbhRequestUrl) {
        this.nbhRequestUrl = nbhRequestUrl;
    }

    public String getNbhMaxSendNumber() {
        return nbhMaxSendNumber;
    }

    @Value("#{propertiesReader['nbh.request.maxSendNumber']}")
    public void setNbhMaxSendNumber(String nbhMaxSendNumber) {
        this.nbhMaxSendNumber = nbhMaxSendNumber;
    }

    public String getNbhName() {
        return nbhName;
    }

    @Value("#{propertiesReader['nbh.request.name']}")
    public void setNbhName(String nbhName) {
        this.nbhName = nbhName;
    }

    public String getNbhPassWord() {
        return nbhPassWord;
    }

    @Value("#{propertiesReader['nbh.request.password']}")
    public void setNbhPassWord(String nbhPassWord) {
        this.nbhPassWord = nbhPassWord;
    }

    public String getNbhTjpc() {
        return nbhTjpc;
    }

    @Value("#{propertiesReader['nbh.request.tjpc']}")
    public void setNbhTjpc(String nbhTjpc) {
        this.nbhTjpc = nbhTjpc;
    }

    public String getNbhCharacterCode() {
        return nbhCharacterCode;
    }

    @Value("#{propertiesReader['nbh.request.characterCode']}")
    public void setNbhCharacterCode(String nbhCharacterCode) {
        this.nbhCharacterCode = nbhCharacterCode;
    }

    public String getNbhIsNoSignName() {
        return nbhIsNoSignName;
    }

    @Value("#{propertiesReader['nbh.request.isNoSignName']}")
    public void setNbhIsNoSignName(String nbhIsNoSignName) {
        this.nbhIsNoSignName = nbhIsNoSignName;
    }

    public String getNbhIsNoFront() {
        return nbhIsNoFront;
    }

    @Value("#{propertiesReader['nbh.request.isNoFront']}")
    public void setNbhIsNoFront(String nbhIsNoFront) {
        this.nbhIsNoFront = nbhIsNoFront;
    }

    public String getMobileSectionNo() {
        return mobileSectionNo;
    }

    @Value("#{propertiesReader['mobile.operators.section.no']}")
    public void setMobileSectionNo(String mobileSectionNo) {
        this.mobileSectionNo = mobileSectionNo;
    }

    public String getTelecomSectionNo() {
        return telecomSectionNo;
    }

    @Value("#{propertiesReader['telecom.operators.section.no']}")
    public void setTelecomSectionNo(String telecomSectionNo) {
        this.telecomSectionNo = telecomSectionNo;
    }

    public String getUnicomSectionNo() {
        return unicomSectionNo;
    }

    @Value("#{propertiesReader['unicom.operators.section.no']}")
    public void setUnicomSectionNo(String unicomSectionNo) {
        this.unicomSectionNo = unicomSectionNo;
    }

    public String getYzmKey() {
        return yzmKey;
    }

    @Value("#{propertiesReader['yzm.request.channel.key']}")
    public void setYzmKey(String yzmKey) {
        this.yzmKey = yzmKey;
    }

    public String getXttzKey() {
        return xttzKey;
    }

    @Value("#{propertiesReader['xttz.request.channel.key']}")
    public void setXttzKey(String xttzKey) {
        this.xttzKey = xttzKey;
    }

    public String getDxyxKey() {
        return dxyxKey;
    }

    @Value("#{propertiesReader['dxyx.request.channel.key']}")
    public void setDxyxKey(String dxyxKey) {
        this.dxyxKey = dxyxKey;
    }

    public String getDhxsKey() {
        return dhxsKey;
    }

    @Value("#{propertiesReader['dhxs.request.channel.key']}")
    public void setDhxsKey(String dhxsKey) {
        this.dhxsKey = dhxsKey;
    }

    public String getChppKey() {
        return chppKey;
    }

    @Value("#{propertiesReader['chpp.request.channel.key']}")
    public void setChppKey(String chppKey) {
        this.chppKey = chppKey;
    }

    public String getNbhYzm() {
        return nbhYzm;
    }

    @Value("#{propertiesReader['nbh.request.yzm']}")
    public void setNbhYzm(String nbhYzm) {
        this.nbhYzm = nbhYzm;
    }

    public String getNbYzm() {
        return nbYzm;
    }

    @Value("#{propertiesReader['nb.request.yzm']}")
    public void setNbYzm(String nbYzm) {
        this.nbYzm = nbYzm;
    }

    public String getHmdValue() {
        return hmdValue;
    }

    @Value("#{propertiesReader['hmd.request.sendNote.value']}")
    public void setHmdValue(String hmdValue) {
        this.hmdValue = hmdValue;
    }

    @Value("#{propertiesReader['wl.request.url']}")
    public void setWlRequestUrl(String wlRequestUrl) {
        this.wlRequestUrl = wlRequestUrl;
    }

    @Value("#{propertiesReader['wl.loginname']}")
    public void setWlLoginname(String wlLoginname) {
        this.wlLoginname = wlLoginname;
    }

    @Value("#{propertiesReader['wl.password']}")
    public void setWlPassword(String wlPassword) {
        this.wlPassword = wlPassword;
    }

    @Value("#{propertiesReader['wl.noteContent.encoding']}")
    public void setWlContentEncoding(String wlContentEncoding) {
        this.wlContentEncoding = wlContentEncoding;
    }

    @Value("#{propertiesReader['wl.request.isNoSignName']}")
    public void setWlNeedSign(String wlNeedSign) {
        this.wlNeedSign = wlNeedSign;
    }

    @Value("#{propertiesReader['wl.request.isNoFront']}")
    public void setWlPrefixSign(String wlPrefixSign) {
        this.wlPrefixSign = wlPrefixSign;
    }
}
