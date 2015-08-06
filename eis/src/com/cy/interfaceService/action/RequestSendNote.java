package com.cy.interfaceService.action;

import com.cy.interfaceService.bo.NoteLogInfo;
import com.cy.interfaceService.domain.JsonResponse;
import com.cy.interfaceService.domain.NoteInfo;
import com.cy.interfaceService.service.NoteSendService;
import com.cy.interfaceService.service.SendNoteInfoService;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2014/10/16.
 */
@Scope("prototype")
@Controller("requestSendNote")
public class RequestSendNote {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private NoteSendService noteSendService;

    @Resource
    private SendNoteInfoService sendNoteInfoService;

    /**
     * 短信发送 多个号码之间用半角逗号隔开
     * @param noteLogInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendNote")
    @ResponseBody
    public JsonResponse sendNote(NoteLogInfo noteLogInfo) throws Exception {
        try {
            if (StringUtils.isBlank(noteLogInfo.getChannelType())) {
                throw new IllegalArgumentException("请选择您要发送的短信通道！");
            }
            if (StringUtils.isBlank(noteLogInfo.getPhoneNum())) {
                throw new IllegalArgumentException("手机号码不能为空！");
            }
            if (StringUtils.isBlank(noteLogInfo.getContent())) {
                throw new IllegalArgumentException("短信内容不能为空！");
            }
            NoteInfo info = noteSendService.sendNote(noteLogInfo);

            ObjectMapper mapper = new ObjectMapper();

            String obj = mapper.writeValueAsString(info);
            if(info.getReturnValue() == -1) {
                return JsonResponse.sendJsonResponse("-1","", obj);
            }
            return JsonResponse.sendJsonResponse("0","", obj);
        } catch (Exception e) {
            log.error(e.getMessage());
            return JsonResponse.sendJsonResponse("1","发送失败：" + e.getMessage());
        }
    }

    /**
     * 自定义发送
     * map存放每个手机号码对应的短信内容
     * @param requestIp
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendByCustom")
    @ResponseBody
    public JsonResponse sendNote(String requestIp, String json) throws Exception {
        try {
            List<NoteInfo> noteInfos = noteSendService.sendNote(requestIp, json);
            ObjectMapper mapper = new ObjectMapper();

            String obj = mapper.writeValueAsString(noteInfos);
            return JsonResponse.sendJsonResponse("0","", obj);
        } catch (Exception e) {
            log.error(e.getMessage());
            return JsonResponse.sendJsonResponse("1","发送失败：" + e.getMessage());
        }
    }



    /**
     * 最新短信接口  主/备
     *
     * 短信发送
     * @param noteLogInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/kindSendNote")
    @ResponseBody
    public JsonResponse kindSendNote(NoteLogInfo noteLogInfo) throws Exception {
        try {
            if (StringUtils.isBlank(noteLogInfo.getKind())) {
                throw new IllegalArgumentException("请选择您要发送的短信性质！");
            }
            if (StringUtils.isBlank(noteLogInfo.getPhoneNum())) {
                throw new IllegalArgumentException("手机号码不能为空！");
            }
            if (StringUtils.isBlank(noteLogInfo.getContent())) {
                throw new IllegalArgumentException("短信内容不能为空！");
            }
            if(StringUtils.isBlank(noteLogInfo.getSendOutType())) {
                throw new IllegalArgumentException("发送对象不能为空！");
            }
            Map<String,Object> map = new HashMap<String, Object>();
            map = sendNoteInfoService.sendNote(noteLogInfo);
            String returnCount = "发送成功！";
            if("-1".equals(map.get("sendStart").toString())) {
                returnCount = "此号码已拉入黑名单,不予发送短信！";
            }else if("-2".equals(map.get("sendStart").toString())) {
                returnCount = "发送错误！短信接口没有'"+noteLogInfo.getKind()+"'此短信性质！";
            }
            return JsonResponse.sendJsonResponse(map.get("sendStart").toString(),returnCount, map.get("logKey").toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            return JsonResponse.sendJsonResponse("1","发送失败：" + e.getMessage(),"0");
        }
    }
}
