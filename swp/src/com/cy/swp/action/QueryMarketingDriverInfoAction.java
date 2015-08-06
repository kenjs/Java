package com.cy.swp.action;

import com.cy.swp.bo.*;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.util.DataDictUtil;
import com.cy.swp.common.util.DateUtil;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.domain.*;
import com.cy.swp.service.*;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nixianjing on 14/12/12.
 */
@Scope("prototype")
@Controller("queryMarketingDriverInfoAction")
public class QueryMarketingDriverInfoAction extends BaseAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private MarketingUserInfoService marketingUserInfoService;

    @Resource
    private MarketingDriverInfoService driverInfoService;

    @Resource
    private MarketingDriverLineService marketingDriverLineService;

    @Resource
    private MarketingDriverBusinessLineService marketingDriverBusinessLineService;

    @Resource
    private MarketingMaintainRecordService marketingMaintainRecordService;

    @Resource
    private MarketingNoteRecordService marketingNoteRecordService;

    @Resource
    private OrderCargoInfoService orderCargoInfoService;

    @Resource
    private LocationInfoService locationInfoService;

    @Resource
    private SystemNoteTemplateService systemNoteTemplateService;

    /**
     * 根据司机客户id查询司机客户详细信息
     * @param id
     * @autor nxj
     */
    @RequestMapping("/queryMarketingDriverInfoN.jspx")
    @ResponseBody
    public JSonRespone queryMarketingDriverInfoN(String id,String status) {
        try {
            if(StringUtils.isEmpty(id)) {
                return JSonRespone.makeHasContentJSonRespone("1", "参数id不能为空！");
            }
            MarketingDriverInfoDomain marketingDriverInfoDomain = null;
            if("N".equals(status)) {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(Integer.parseInt(id));
            }else {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainById(Integer.parseInt(id));
            }
            if(StringUtils.isEmpty(marketingDriverInfoDomain.getNextContactDate())) {
                marketingDriverInfoDomain.setNextContactDate(DateUtil.parseDayDataFrom(DateUtil.getNextDay(new Date(),30))+" 00:00");
            }
            return JSonRespone.makeHasContentJSonRespone("0", "success", marketingDriverInfoDomain);
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();

        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }



    /**
     * 获取数据活跃信息
     * @param driverId
     * @return
     */
    @RequestMapping("/queryActiveDriverInfo.jspx")
    @ResponseBody
    public JSonRespone queryActiveDriverInfo(String driverId) {
        try {
            if(StringUtils.isEmpty(driverId)) {
                return JSonRespone.makeHasContentJSonRespone("-1", "司机driverId不能为空！");
            }
            MarketingDriverInfoDomain marketingDriverInfoDomain = driverInfoService.queryDriverInfoDomainByDriverId(Integer.parseInt(driverId));
            return JSonRespone.makeHasContentJSonRespone("0", "success", marketingDriverInfoDomain);
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }

    /**
     * 根据司机客户id 或 司机driverId获取线路List
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/queryDriverLineByIdOrDriverId.jspx")
    @ResponseBody
    public JSonRespone queryDriverLineByIdOrDriverId(String id,String status) {
        try {
            if("N".equals(status)) {
                List<MarketingDriverLine> list = marketingDriverLineService.queryMarketingDriverLineByCustomerDriverId(id);
                return JSonRespone.makeHasContentJSonRespone("0", "获取司机运营线路成功！",list);
            }else {
                List<DriverLineInfo> list = marketingDriverLineService.queryDriverLineInfoByDriverId(id);
                return JSonRespone.makeHasContentJSonRespone("0", "获取司机运营线路成功！",list);
            }
        }catch(Exception e){
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 根据运营线路id获取运营路线
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/queryDriverLineInfoById.jspx")
    @ResponseBody
    public JSonRespone queryDriverLineInfoById(String id,String status) {
        try {
            StringBuffer driverLineInfoHtml = new StringBuffer();
            if("N".equals(status)) {
                MarketingDriverLine marketingDriverLine = new MarketingDriverLine();
                if(StringUtils.isNotEmpty(id)) {
                    marketingDriverLine = marketingDriverLineService.queryMarketingDriverLineById(Integer.parseInt(id));
                }
                driverLineInfoHtml.append("<div class=\"tabfl\">");
                driverLineInfoHtml.append("<div style=\"height: 30px; text-align: center;\" id=\"updateNewsId\"></div>");
                driverLineInfoHtml.append("<table border=\"0\" cellspacing=\"1\"  cellpadding=\"3\" style=\"margin-top:-1px; text-align:left;\">");
                driverLineInfoHtml.append("<tr>");
                driverLineInfoHtml.append("<td>运营线路：</td>");
                driverLineInfoHtml.append("<td><input id=\"startPcc\" name=\"startPcc\" type=\"text\" value=\"");
                if(StringUtils.isNotEmpty(marketingDriverLine.getStartProvince())) {
                    driverLineInfoHtml.append(marketingDriverLine.getStartProvince());
                    if(StringUtils.isNotEmpty(marketingDriverLine.getStartCity())) {
                        driverLineInfoHtml.append("-"+marketingDriverLine.getStartCity());
                        if(StringUtils.isNotEmpty(marketingDriverLine.getStartCounty())) {
                            driverLineInfoHtml.append("-"+marketingDriverLine.getStartCounty());
                        }
                    }
                }
                driverLineInfoHtml.append("\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverLineInfoHtml.append("<td><span class=\"icon1\">&nbsp;</span></td>");
                driverLineInfoHtml.append("<td><input id=\"endPcc\" name=\"endPcc\" type=\"text\" value=\"");
                if(StringUtils.isNotEmpty(marketingDriverLine.getEndProvince())) {
                    driverLineInfoHtml.append(marketingDriverLine.getEndProvince());
                    if(StringUtils.isNotEmpty(marketingDriverLine.getEndCity())) {
                        driverLineInfoHtml.append("-"+marketingDriverLine.getEndCity());
                        if(StringUtils.isNotEmpty(marketingDriverLine.getEndCounty())) {
                            driverLineInfoHtml.append("-"+marketingDriverLine.getEndCounty());
                        }
                    }
                }
                driverLineInfoHtml.append("\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverLineInfoHtml.append("</tr>");
                driverLineInfoHtml.append("</table>");
                driverLineInfoHtml.append("<div class=\"buttons\"><input id=\"driverLineId\" name=\"driverLineId\" type=\"hidden\" value=\"");
                if(marketingDriverLine.getId() != null) {
                    driverLineInfoHtml.append(marketingDriverLine.getId());
                }
                driverLineInfoHtml.append("\" />");
                driverLineInfoHtml.append("<a href=\"javascript:updateDriverLineById('"+status+"');\">保存</a><a href=\"javascript:closes();\">取消</a></div>");
                driverLineInfoHtml.append("</div>");
            }else {
                DriverLineInfo driverLineInfo = new DriverLineInfo();
                if(StringUtils.isNotEmpty(id)) {
                    driverLineInfo = marketingDriverLineService.queryDriverLineInfoById(Integer.parseInt(id));
                }
                driverLineInfoHtml.append("<div class=\"tabfl\">");
                driverLineInfoHtml.append("<div style=\"height: 30px; text-align: center;\" id=\"updateNewsId\"></div>");
                driverLineInfoHtml.append("<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" style=\"margin-top:-1px;\">");
                driverLineInfoHtml.append("<tr>");
                driverLineInfoHtml.append("<td>运营线路：</td>");
                driverLineInfoHtml.append("<td><input id=\"startPcc\" name=\"startPcc\" type=\"text\" value=\"");
                if(StringUtils.isNotEmpty(driverLineInfo.getStartProvince())) {
                    driverLineInfoHtml.append(driverLineInfo.getStartProvince());
                    if(StringUtils.isNotEmpty(driverLineInfo.getStartCity())) {
                        driverLineInfoHtml.append("-"+driverLineInfo.getStartCity());
                        if(StringUtils.isNotEmpty(driverLineInfo.getStartCounty())) {
                            driverLineInfoHtml.append("-"+driverLineInfo.getStartCounty());
                        }
                    }
                }
                driverLineInfoHtml.append("\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverLineInfoHtml.append("<td><span class=\"icon1\">&nbsp;</span></td>");
                driverLineInfoHtml.append("<td><input id=\"endPcc\" name=\"endPcc\" type=\"text\" value=\"");
                if(StringUtils.isNotEmpty(driverLineInfo.getEndProvince())) {
                    driverLineInfoHtml.append(driverLineInfo.getEndProvince());
                    if(StringUtils.isNotEmpty(driverLineInfo.getEndCity())) {
                        driverLineInfoHtml.append("-"+driverLineInfo.getEndCity());
                        if(StringUtils.isNotEmpty(driverLineInfo.getEndCounty())) {
                            driverLineInfoHtml.append("-"+driverLineInfo.getEndCounty());
                        }
                    }
                }
                driverLineInfoHtml.append("\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverLineInfoHtml.append("</tr>");
                driverLineInfoHtml.append("</table>");
                driverLineInfoHtml.append("<div class=\"buttons\"><input id=\"driverLineId\" name=\"driverLineId\" type=\"hidden\" value=\"");
                if(driverLineInfo.getId() != null) {
                    driverLineInfoHtml.append(driverLineInfo.getId());
                }
                driverLineInfoHtml.append("\" />");
                driverLineInfoHtml.append("<a href=\"javascript:updateDriverLineById('"+status+"');\">保存</a><a href=\"javascript:closes();\">取消</a></div>");
                driverLineInfoHtml.append("</div>");
            }
            return JSonRespone.makeHasContentJSonRespone("0", "获取司机运营线路成功！",driverLineInfoHtml.toString());
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 编辑司机运营线路（新增或修改）
     * @param id
     * @param startPcc
     * @param endPcc
     * @param status
     * @return
     */
    @RequestMapping("/updateDriverLineInfo.jspx")
    @ResponseBody
    public JSonRespone updateDriverLineInfo(String id,String customerDriverId,String startPcc,String endPcc,String status,String marId) {
        try {
            MarketingDriverInfoDomain marketingDriverInfoDomain = new MarketingDriverInfoDomain();
            if("N".equals(status)) {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(Integer.parseInt(customerDriverId));
            }else {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainById(Integer.parseInt(marId));
            }
            if(StringUtils.isNotEmpty(id)) {
                if("N".equals(status)) {
                    MarketingDriverLine marketingDriverLine = changeMarketingDriverLine(id,customerDriverId,startPcc,endPcc);
                    MarketingDriverLine marketingDriverLines = marketingDriverLineService.queryMarketingDriverLineById(marketingDriverLine.getId());
                    boolean resuls = marketingDriverLineService.updateMarketingDriverLine(marketingDriverLine);
                    if(resuls) {
                        //修改记录
                        String lineString = getMaDriverLineInfo(marketingDriverLines, marketingDriverLine);
                        if(StringUtils.isNotEmpty(lineString)) {
                            addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,lineString);
                        }
                        return JSonRespone.makeHasContentJSonRespone("0", "编辑司机运营线路成功！",id);
                    }else {
                        return JSonRespone.makeHasContentJSonRespone("-1", "编辑司机运营线路失败！",id);
                    }
                }else {
                    DriverLineInfo driverLineInfo = changeDriverLineInfo(id,customerDriverId,startPcc,endPcc);
                    DriverLineInfo driverLineInfos = marketingDriverLineService.queryDriverLineInfoById(driverLineInfo.getId());
                    boolean resuls = marketingDriverLineService.updateDriverLineInfo(driverLineInfo);
                    if(resuls) {
                        //修改记录
                        String lineString = getDriverLineInfo(driverLineInfos,driverLineInfo);
                        if(StringUtils.isNotEmpty(lineString)) {
                            addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,lineString);
                        }
                        return JSonRespone.makeHasContentJSonRespone("0", "编辑司机运营线路成功！",id);
                    }else {
                        return JSonRespone.makeHasContentJSonRespone("-1", "编辑司机运营线路失败！",id);
                    }
                }
            }else {
                if("N".equals(status)) {
                    MarketingDriverLine marketingDriverLine = changeMarketingDriverLine(id,customerDriverId,startPcc,endPcc);
                    marketingDriverLineService.addMarketingDriverLine(marketingDriverLine);
                    if(marketingDriverLine.getId() != null) {
                        addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,"新增运营线路："+startPcc+"到"+endPcc);
                        return JSonRespone.makeHasContentJSonRespone("0", "编辑司机运营线路成功！",marketingDriverLine.getId());
                    }else {
                        return JSonRespone.makeHasContentJSonRespone("-1", "编辑司机运营线路失败！","");
                    }
                }else {
                    DriverLineInfo driverLineInfo = changeDriverLineInfo(id,customerDriverId,startPcc,endPcc);
                    marketingDriverLineService.addDriverLineInfo(driverLineInfo);
                    if(driverLineInfo.getId() != null) {
                        addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,"新增运营线路："+startPcc+"到"+endPcc);
                        return JSonRespone.makeHasContentJSonRespone("0", "编辑司机运营线路成功！",driverLineInfo.getId());
                    }else {
                        return JSonRespone.makeHasContentJSonRespone("-1", "编辑司机运营线路失败！","");
                    }
                }
            }
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 获取司机的预约线路
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/queryDriverBuLineInfoList.jspx")
    @ResponseBody
    public JSonRespone queryDriverBuLineInfoList(String id,String status){
        try {
            String driverBuLineListHtml = "";
            if("N".equals(status)) {
                driverBuLineListHtml = marketingDriverBusinessLineService.queryMarketingDriverBusinessLineByCustomerDriverId(Integer.parseInt(id));
            }else {
                driverBuLineListHtml = marketingDriverBusinessLineService.queryDriverBusinessLineInfoByDriverId(Integer.parseInt(id));
            }
            return JSonRespone.makeHasContentJSonRespone("0", "获取司机预约线路成功！",driverBuLineListHtml);
        }catch (Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 获取司机的预约线路
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/queryDriverBuLineInfoById.jspx")
    @ResponseBody
    public JSonRespone queryDriverBuLineInfoById(String id,String status){
        //报价类型
        List<DictInfo> businessLineQuoteType = DataDictUtil.businessLineQuoteType();
        try {
            StringBuffer driverInfoHtml = new StringBuffer();
            if(StringUtils.isNotEmpty(id)) {
                MarketingDriverBusinessLineDomain marketingDriverBusinessLineDomain = new MarketingDriverBusinessLineDomain();
                if("N".equals(status)) {
                    marketingDriverBusinessLineDomain = marketingDriverBusinessLineService.queryMarketingDriverBusinessLineById(Integer.parseInt(id));
                }else {
                    marketingDriverBusinessLineDomain = marketingDriverBusinessLineService.queryDriverBusinessLineInfoById(Integer.parseInt(id));
                }
                driverInfoHtml.append("<div class=\"tabfl\">");
                driverInfoHtml.append("<div style=\"height: 30px; text-align: center;\" id=\"updateNewsId\"></div>");
                driverInfoHtml.append("<table border=\"0\" cellspacing=\"1\"  cellpadding=\"3\" style=\"margin-top:-1px; text-align:left;\">");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td align=\"right\">预约时间：</td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"startTime\" name=\"startTime\" type=\"text\" value=\""+marketingDriverBusinessLineDomain.getStartTime()+"\" readonly=\"readonly\" onclick=\"WdatePicker()\" class=\"int m3\"/></td>");
                driverInfoHtml.append("<td><span class=\"icon1\">&nbsp;</span></td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"endTime\" name=\"endTime\" type=\"text\" value=\""+marketingDriverBusinessLineDomain.getEndTime()+"\" readonly=\"readonly\" onclick=\"WdatePicker()\" class=\"int m3\"/></td>");
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td align=\"right\">预约线路：</td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"startPcc\" name=\"startPcc\" type=\"text\" value=\"");
                if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getStartProvince())) {
                    driverInfoHtml.append(marketingDriverBusinessLineDomain.getStartProvince());
                    if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getStartCity())) {
                        driverInfoHtml.append("-"+marketingDriverBusinessLineDomain.getStartCity());
                        if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getStartCounty())) {
                            driverInfoHtml.append("-"+marketingDriverBusinessLineDomain.getStartCounty());
                        }
                    }
                }
                driverInfoHtml.append("\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverInfoHtml.append("<td><span class=\"icon1\">&nbsp;</span></td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"endPcc\" name=\"endPcc\" type=\"text\" value=\"");
                if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getEndProvince())) {
                    driverInfoHtml.append(marketingDriverBusinessLineDomain.getEndProvince());
                    if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getEndCity())) {
                        driverInfoHtml.append("-"+marketingDriverBusinessLineDomain.getEndCity());
                        if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getEndCounty())) {
                            driverInfoHtml.append("-"+marketingDriverBusinessLineDomain.getEndCounty());
                        }
                    }
                }
                driverInfoHtml.append("\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td align=\"right\">报价类型：</td>");
                driverInfoHtml.append("<td align=\"left\">&nbsp;&nbsp;<select id=\"quoteType\" name=\"quoteType\">");
                for(int i = 0;i<businessLineQuoteType.size();i++) {
                    if(marketingDriverBusinessLineDomain.getQuoteType() != null) {
                        if(businessLineQuoteType.get(i).getCode().equals(String.valueOf(marketingDriverBusinessLineDomain.getQuoteType()))) {
                            driverInfoHtml.append("<option value=\""+businessLineQuoteType.get(i).getCode()+"\" selected=\"true\" >"+businessLineQuoteType.get(i).getName()+"</option>");
                        }else {
                            driverInfoHtml.append("<option value=\""+businessLineQuoteType.get(i).getCode()+"\" >"+businessLineQuoteType.get(i).getName()+"</option>");
                        }
                    }else {
                        driverInfoHtml.append("<option value=\""+businessLineQuoteType.get(i).getCode()+"\" >"+businessLineQuoteType.get(i).getName()+"</option>");
                    }

                }
                driverInfoHtml.append("</select></td>");
                driverInfoHtml.append("<td align=\"right\">报价金额：</td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"quoteFair\" name=\"quoteFair\" type=\"text\" value=\""+marketingDriverBusinessLineDomain.getQuoteFair()+"\"/></td>");
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("</table>");
                driverInfoHtml.append("<div class=\"buttons\"><input id=\"driverBusLineId\" name=\"driverBusLineId\" type=\"hidden\" value=\""+marketingDriverBusinessLineDomain.getId()+"\" />");
                driverInfoHtml.append("<a href=\"javascript:updateDriverBuLine('"+status+"');\">保存</a><a href=\"javascript:closes();\">取消</a></div>");
                driverInfoHtml.append("</div>");

            }else {
                driverInfoHtml.append("<div class=\"tabfl\">");
                driverInfoHtml.append("<div style=\"height: 30px; text-align: center;\" id=\"updateNewsId\"></div>");
                driverInfoHtml.append("<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" style=\"margin-top:-1px;\">");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td align=\"right\">预约时间：</td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"startTime\" name=\"startTime\" type=\"text\" value=\"\" readonly=\"readonly\" onclick=\"WdatePicker()\" class=\"int m3\"/></td>");
                driverInfoHtml.append("<td><span class=\"icon1\">&nbsp;</span></td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"endTime\" name=\"endTime\" type=\"text\" value=\"\" readonly=\"readonly\" onclick=\"WdatePicker()\" class=\"int m3\"/></td>");
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td align=\"right\">预约线路：</td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"startPcc\" name=\"startPcc\" type=\"text\" value=\"");
                driverInfoHtml.append("\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverInfoHtml.append("<td><span class=\"icon1\">&nbsp;</span></td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"endPcc\" name=\"endPcc\" type=\"text\" value=\"");
                driverInfoHtml.append("\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td align=\"right\">报价类型：</td>");
                driverInfoHtml.append("<td align=\"left\">&nbsp;&nbsp;<select id=\"quoteType\" name=\"quoteType\">");
                for(int i = 0;i<businessLineQuoteType.size();i++) {
                    driverInfoHtml.append("<option value=\""+businessLineQuoteType.get(i).getCode()+"\" >"+businessLineQuoteType.get(i).getName()+"</option>");
                }
                driverInfoHtml.append("</select></td>");
                driverInfoHtml.append("<td align=\"right\">报价金额：</td>");
                driverInfoHtml.append("<td align=\"left\"><input id=\"quoteFair\" name=\"quoteFair\" type=\"text\" value=\"\"/></td>");
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("</table>");
                driverInfoHtml.append("<div class=\"buttons\"><input id=\"driverBusLineId\" name=\"driverBusLineId\" type=\"hidden\" value=\"\" />");
                driverInfoHtml.append("<a href=\"javascript:updateDriverBuLine('"+status+"');\">保存</a><a href=\"javascript:closes();\">取消</a></div>");
                driverInfoHtml.append("</div>");
            }

            return JSonRespone.makeHasContentJSonRespone("0", "获取司机预约线路成功！",driverInfoHtml.toString());
        }catch (Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }

    /**
     * 编辑预约线路
     * @param marketingDriverBusinessLineDomain
     * @param status
     * @return
     */
    @RequestMapping("/updateDriverBuLineInfo.jspx")
    @ResponseBody
    public JSonRespone updateDriverBuLineInfo(MarketingDriverBusinessLineDomain marketingDriverBusinessLineDomain,String status,String marId) {
        try {
            MarketingDriverInfoDomain marketingDriverInfoDomain = new MarketingDriverInfoDomain();
            if("N".equals(status)) {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(marketingDriverBusinessLineDomain.getCustomerDriverId());
            }else {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainById(Integer.parseInt(marId));
            }
            if(marketingDriverBusinessLineDomain.getId() != null) {
                MarketingDriverBusinessLineDomain marketingDriverBusinessLineDomains = new MarketingDriverBusinessLineDomain();
                boolean resel = false;
                if("N".equals(status)) {
                    marketingDriverBusinessLineDomains = marketingDriverBusinessLineService.queryMarketingDriverBusinessLineById(marketingDriverBusinessLineDomain.getId());
                    resel = marketingDriverBusinessLineService.updateMarketingDriverBusinessLine(changeMarketingDriverBuLineInfo(marketingDriverBusinessLineDomain));

                }else {
                    marketingDriverBusinessLineDomains = marketingDriverBusinessLineService.queryDriverBusinessLineInfoById(marketingDriverBusinessLineDomain.getId());
                    resel = marketingDriverBusinessLineService.updateDriverBusinessLineInfo(changeDriverBusinessLineInfo(marketingDriverBusinessLineDomain));
                }
                if(resel) {
                    String lineString = getDriverBusinessLineInfo(marketingDriverBusinessLineDomains,changeMarketingDriverBuLineInfo(marketingDriverBusinessLineDomain));
                    if(StringUtils.isNotEmpty(lineString)) {
                        addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,lineString);
                    }
                    return JSonRespone.makeHasContentJSonRespone("0", "编辑司机预约线路成功！",marketingDriverBusinessLineDomain.getId());
                }else {
                    return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
                }
            }else {
                if("N".equals(status)) {
                    MarketingDriverBusinessLine marketingDriverBusinessLine = changeMarketingDriverBuLineInfo(marketingDriverBusinessLineDomain);
                    marketingDriverBusinessLineService.addMarketingDriverBusinessLine(marketingDriverBusinessLine);
                    addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,"新增预约线路：预约线路为"+marketingDriverBusinessLineDomain.getStartPcc()+"到"+marketingDriverBusinessLineDomain.getEndPcc()+",预约时间为"+marketingDriverBusinessLineDomain.getStartTime()+"到"+marketingDriverBusinessLineDomain.getEndTime());
                    return JSonRespone.makeHasContentJSonRespone("0", "编辑司机预约线路成功！",marketingDriverBusinessLine.getId());
                }else {
                    DriverBusinessLineInfo driverBusinessLineInfo = changeDriverBusinessLineInfo(marketingDriverBusinessLineDomain);
                    marketingDriverBusinessLineService.addDriverBusinessLineInfo(driverBusinessLineInfo);
                    addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,"新增预约线路：预约线路为"+marketingDriverBusinessLineDomain.getStartPcc()+"到"+marketingDriverBusinessLineDomain.getEndPcc()+",预约时间为"+marketingDriverBusinessLineDomain.getStartTime()+"到"+marketingDriverBusinessLineDomain.getEndTime());
                    return JSonRespone.makeHasContentJSonRespone("0", "编辑司机预约线路成功！",driverBusinessLineInfo.getId());
                }
            }

        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }



    /**
     * 查询修改车辆信息
     * @param marketingDriverInfoDomain
     * @param status 处理数据状态（N表示未注册）
     * @param type 处理类型（1为查询，2为修改）
     * @return
     */
    @RequestMapping("/queryUpdateDriverInfoByDriverInfoDomain.jspx")
    @ResponseBody
    public JSonRespone queryUpdateDriverInfoByDriverInfoDomain(MarketingDriverInfoDomain marketingDriverInfoDomain,String status,String type) {
        try {
            //长
            List<DictInfo> listCarLength = DataDictUtil.carLengthData();
            //板
            List<DictInfo> listPlateType = DataDictUtil.carPlateTypeData();
            //栏
            List<DictInfo> listCarBarType = DataDictUtil.carBarTypeData();
            //重量
            List<DictInfo> listCarWeight = DataDictUtil.carWeightData();
            //体积
            List<DictInfo> listCarCubage = DataDictUtil.carCubageData();
            if("1".equals(type)) {
                StringBuffer driverInfoHtml = new StringBuffer();
                if("N".equals(status)) {
                    marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(marketingDriverInfoDomain.getId());
                }else {
                    marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainById(marketingDriverInfoDomain.getId());
                }
                driverInfoHtml.append("<div class=\"tabfl\">");
                driverInfoHtml.append("<div style=\"height: 30px; text-align: center;\" id=\"updateNewsId\"></div>");
                driverInfoHtml.append("<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" style=\"margin-top:-1px; text-align:left;\">");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td>司机姓名：</td>");
                driverInfoHtml.append("<td><input id=\"carName\" name=\"carName\" type=\"text\" value=\""+marketingDriverInfoDomain.getName()+"\"/></td>");
                driverInfoHtml.append("<td>QQ号码：</td>");
                if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getQqNumber())) {
                    driverInfoHtml.append("<td colspan=\"3\"><input id=\"qqNumber\" name=\"qqNumber\" type=\"text\" value=\""+marketingDriverInfoDomain.getQqNumber()+"\"/></td>");
                }else {
                    driverInfoHtml.append("<td colspan=\"3\"><input id=\"qqNumber\" name=\"qqNumber\" type=\"text\" value=\"\"/></td>");
                }
                driverInfoHtml.append("</tr>");

                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td>车牌号：</td>");
                driverInfoHtml.append("<td><input id=\"carNumber\" name=\"carNumber\" type=\"text\" value=\""+marketingDriverInfoDomain.getCarNumber()+"\"/></td>");
                driverInfoHtml.append("<td>长：</td>");
                driverInfoHtml.append("<td><select id=\"carLength\" name=\"carLength\">");
                for(int i = 0;i<listCarLength.size();i++) {
                    if(listCarLength.get(i).getName().equals(marketingDriverInfoDomain.getCarLength())) {
                        driverInfoHtml.append("<option value=\""+listCarLength.get(i).getName()+"\" selected=\"true\" >"+listCarLength.get(i).getName()+"</option>");
                    }else {
                        driverInfoHtml.append("<option value=\""+listCarLength.get(i).getName()+"\" >"+listCarLength.get(i).getName()+"</option>");
                    }
                }
                driverInfoHtml.append("</select></td>");
                driverInfoHtml.append("<td>板：</td>");
                driverInfoHtml.append("<td><select id=\"carPlateType\" name=\"carPlateType\">");
                for(int i = 0;i<listPlateType.size();i++) {
                    if(listPlateType.get(i).getName().equals(marketingDriverInfoDomain.getCarPlateType())) {
                        driverInfoHtml.append("<option value=\""+listPlateType.get(i).getName()+"\" selected=\"true\" >"+listPlateType.get(i).getName()+"</option>");
                    }else {
                        driverInfoHtml.append("<option value=\""+listPlateType.get(i).getName()+"\" >"+listPlateType.get(i).getName()+"</option>");
                    }
                }
                driverInfoHtml.append("</select></td>");
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td>栏：</td>");
                driverInfoHtml.append("<td><select id=\"carBarType\" name=\"carBarType\">");
                for(int i = 0;i<listCarBarType.size();i++) {
                    if(listCarBarType.get(i).getName().equals(marketingDriverInfoDomain.getCarBarType())) {
                        driverInfoHtml.append("<option value=\""+listCarBarType.get(i).getName()+"\" selected=\"true\" >"+listCarBarType.get(i).getName()+"</option>");
                    }else {
                        driverInfoHtml.append("<option value=\""+listCarBarType.get(i).getName()+"\" >"+listCarBarType.get(i).getName()+"</option>");
                    }
                }
                driverInfoHtml.append("</select></td>");
                driverInfoHtml.append("<td>重量(运力)：</td>");
                driverInfoHtml.append("<td><select id=\"carWeight\" name=\"carWeight\">");
                for(int i = 0;i<listCarWeight.size();i++) {
                    if(listCarWeight.get(i).getName().equals(marketingDriverInfoDomain.getCarWeight())) {
                        driverInfoHtml.append("<option value=\""+listCarWeight.get(i).getName()+"\" selected=\"true\" >"+listCarWeight.get(i).getName()+"</option>");
                    }else {
                        driverInfoHtml.append("<option value=\""+listCarWeight.get(i).getName()+"\" >"+listCarWeight.get(i).getName()+"</option>");
                    }
                }
                driverInfoHtml.append("</select></td>");
                driverInfoHtml.append("<td>体积(运力)：</td>");
                driverInfoHtml.append("<td><select id=\"carCubage\" name=\"carCubage\">");
                for(int i = 0;i<listCarCubage.size();i++) {
                    if(listCarCubage.get(i).getName().equals(marketingDriverInfoDomain.getCarCubage())) {
                        driverInfoHtml.append("<option value=\""+listCarCubage.get(i).getName()+"\" selected=\"true\" >"+listCarCubage.get(i).getName()+"</option>");
                    }else {
                        driverInfoHtml.append("<option value=\""+listCarCubage.get(i).getName()+"\" >"+listCarCubage.get(i).getName()+"</option>");
                    }
                }
                driverInfoHtml.append("</select></td>");
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("</table>");
                driverInfoHtml.append("<div class=\"buttons\"><a href=\"javascript:updateDriverInfo('"+status+"');\">保存</a><a href=\"javascript:closes();\">取消</a></div>");
                driverInfoHtml.append("</div>");
                return JSonRespone.makeHasContentJSonRespone("0", "获取车辆信息成功！",driverInfoHtml.toString());
            }else if("2".equals(type)) {
                MarketingDriverInfoDomain queryMarketingDriverInfoDomain = new MarketingDriverInfoDomain();
                if("N".equals(status)) {
                    queryMarketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(marketingDriverInfoDomain.getId());
                }else {
                    queryMarketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainById(marketingDriverInfoDomain.getId());
                }
                if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getName())) {
                    if(!marketingDriverInfoDomain.getName().equals(queryMarketingDriverInfoDomain.getName())) {
                        addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,"司机姓名：'"+queryMarketingDriverInfoDomain.getName()+"'修改为'"+marketingDriverInfoDomain.getName()+"'");
                    }
                }
                String updateContrast = updateContrast(marketingDriverInfoDomain,queryMarketingDriverInfoDomain,"0");
                if(StringUtils.isNotEmpty(updateContrast)) {
                    addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,updateContrast);
                }
                boolean rutesl = false;
                if("N".equals(status)) {
                    rutesl = driverInfoService.updateMarketingDriverInfoNoById(changeDriverInfo(marketingDriverInfoDomain));
                }else {
                    MarketingDriverInfo marketingDriverInfoes = new MarketingDriverInfo();
                    marketingDriverInfoes.setId(marketingDriverInfoDomain.getId());
                    marketingDriverInfoes.setQqNumber(marketingDriverInfoDomain.getQqNumber());
                    rutesl = driverInfoService.updateMarketingDriverInfoNoById(marketingDriverInfoes);
                    rutesl = driverInfoService.updateDriverUserInfoByDriverId(changeDriverUserInfo(marketingDriverInfoDomain));
                }
                if(rutesl == false) {
                    return JSonRespone.makeHasContentJSonRespone("0", "编辑车辆信息失败！");
                }else {
                    return JSonRespone.makeHasContentJSonRespone("0", "编辑车辆信息成功！");
                }
            }
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 查询修改司机常跑城市信息
     * @param marketingDriverInfoDomain
     * @param type
     * @return
     */
    @RequestMapping("/queryUpdateDriverInfoSetOftenCity.jspx")
    @ResponseBody
    public JSonRespone queryUpdateDriverInfoSetOftenCity(MarketingDriverInfoDomain marketingDriverInfoDomain,String status,String type) {
        try {
            if("1".equals(type)) {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(marketingDriverInfoDomain.getId());
                StringBuffer driverInfoHtml = new StringBuffer();
                driverInfoHtml.append("<div class=\"tabfl\">");
                driverInfoHtml.append("<div style=\"height: 30px; text-align: center;\" id=\"updateNewsId\"></div>");
                driverInfoHtml.append("<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" style=\"margin-top:-1px; text-align:left;\">");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td>常跑城市1：</td>");
                driverInfoHtml.append("<td><input id=\"oftenCity1\" name=\"oftenCity1\" type=\"text\" value=\""+marketingDriverInfoDomain.getOftenCity1()+"\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverInfoHtml.append("<td>常跑城市2：</td>");
                driverInfoHtml.append("<td><input id=\"oftenCity2\" name=\"oftenCity2\" type=\"text\" value=\""+marketingDriverInfoDomain.getOftenCity2()+"\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverInfoHtml.append("<td>常跑城市3：</td>");
                driverInfoHtml.append("<td><input id=\"oftenCity3\" name=\"oftenCity3\" type=\"text\" value=\""+marketingDriverInfoDomain.getOftenCity3()+"\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td>常跑城市4：</td>");
                driverInfoHtml.append("<td><input id=\"oftenCity4\" name=\"oftenCity4\" type=\"text\" value=\""+marketingDriverInfoDomain.getOftenCity4()+"\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverInfoHtml.append("<td>常跑城市5：</td>");
                driverInfoHtml.append("<td><input id=\"oftenCity5\" name=\"oftenCity5\" type=\"text\" value=\""+marketingDriverInfoDomain.getOftenCity5()+"\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                driverInfoHtml.append("<td>常跑城市6：</td>");
                if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity6())) {
                    driverInfoHtml.append("<td><input id=\"oftenCity6\" name=\"oftenCity6\" type=\"text\" value=\""+marketingDriverInfoDomain.getOftenCity6()+"\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                }else {
                    driverInfoHtml.append("<td><input id=\"oftenCity6\" name=\"oftenCity6\" type=\"text\" value=\"\" readonly=\"readonly\" class=\"city_input  inputFocus proCityQueryAll proCitySelAll\" ov=\"请选择/输入城市名称\"/></td>");
                }
                driverInfoHtml.append("</tr>");
                driverInfoHtml.append("</table>");
                driverInfoHtml.append("<div class=\"buttons\"><a href=\"javascript:updateDirverInfoSetOftenCity('"+status+"');\">保存</a><a href=\"javascript:closes();\">取消</a></div>");
                driverInfoHtml.append("</div>");
                return JSonRespone.makeHasContentJSonRespone("0", "获取司机常跑城市信息成功！",driverInfoHtml.toString());
            }else if("2".equals(type)) {
                MarketingDriverInfoDomain queryMarketingDriverInfoDomain = new MarketingDriverInfoDomain();
                queryMarketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(marketingDriverInfoDomain.getId());
                String updateContrast = updateContrast(marketingDriverInfoDomain,queryMarketingDriverInfoDomain,"1");
                if(StringUtils.isNotEmpty(updateContrast)) {
                    addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,updateContrast);
                }
                boolean rutesl = driverInfoService.updateDriverUserInfoSetOftenCity(changeDriverInfo(marketingDriverInfoDomain));
                if(rutesl == false) {
                    return JSonRespone.makeHasContentJSonRespone("0", "编辑司机常跑城市信息失败！");
                }else {
                    return JSonRespone.makeHasContentJSonRespone("0", "编辑司机常跑城市信息成功！");
                }
            }
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }

        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }

    /**
     * 查询司机用户备用手机号码
     * @param marketingDriverInfoDomain
     * @param orderId
     * @return
     */
    @RequestMapping("/queryMobilePhone.jspx")
    @ResponseBody
    public JSonRespone queryMobilePhone(MarketingDriverInfoDomain marketingDriverInfoDomain,String orderId) {
        try {
            StringBuffer driverInfoHtml = new StringBuffer();
            marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(marketingDriverInfoDomain.getId());
            driverInfoHtml.append("<div class=\"tabfl\">");
            driverInfoHtml.append("<div style=\"height: 30px; text-align: center;\" id=\"updateNewsId\"></div>");
            driverInfoHtml.append("<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" style=\"margin-top:-1px; text-align:left;\">");
            driverInfoHtml.append("<tr>");
            driverInfoHtml.append("<td>备用手机号码：</td>");
            if("2".equals(orderId)) {
                driverInfoHtml.append("<td><input id=\"mobilePhone2\" name=\"mobilePhone2\" type=\"text\" value=\""+marketingDriverInfoDomain.getMobilePhone2()+"\"/></td>");
            }else if("3".equals(orderId)) {
                driverInfoHtml.append("<td><input id=\"mobilePhone3\" name=\"mobilePhone3\" type=\"text\" value=\""+marketingDriverInfoDomain.getMobilePhone3()+"\"/></td>");
            }
            driverInfoHtml.append("</tr>");
            driverInfoHtml.append("</table>");
            driverInfoHtml.append("<div class=\"buttons\"><a href=\"javascript:updateMobilePhone('"+orderId+"');\">保存</a><a href=\"javascript:closes();\">取消</a></div>");
            driverInfoHtml.append("</div>");
            return JSonRespone.makeHasContentJSonRespone("0", "编辑司机备用手机号码成功！",driverInfoHtml.toString());
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 编辑备用号码
     * @param marketingDriverInfoDomain
     * @param orderId
     * @return
     */
    @RequestMapping("/updateMobilePhone.jspx")
    @ResponseBody
    public JSonRespone updateMobilePhone(MarketingDriverInfoDomain marketingDriverInfoDomain,String orderId) {
        try {
            MarketingDriverInfoDomain marketingDriverInfoDomaines = driverInfoService.queryMarketingDriverInfoDomainNoById(marketingDriverInfoDomain.getId());
            String mobilePhone = "";
            MarketingDriverInfo marketingDriverInfo = new MarketingDriverInfo();
            marketingDriverInfo.setId(marketingDriverInfoDomain.getId());
            if("2".equals(orderId)) {
                mobilePhone = marketingDriverInfoDomain.getMobilePhone2();
                marketingDriverInfo.setMobilePhone2(marketingDriverInfoDomain.getMobilePhone2());
                marketingDriverInfo.setMobilePhone3(marketingDriverInfoDomaines.getMobilePhone3());
            }else if("3".equals(orderId)){
                mobilePhone = marketingDriverInfoDomain.getMobilePhone3();
                marketingDriverInfo.setMobilePhone3(marketingDriverInfoDomain.getMobilePhone3());
                marketingDriverInfo.setMobilePhone2(marketingDriverInfoDomaines.getMobilePhone2());
            }

            String updateContrast = updateContrast(marketingDriverInfoDomain,marketingDriverInfoDomaines,"1");
            if(StringUtils.isNotEmpty(updateContrast)) {
                addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),3,null,null,updateContrast);
            }
            boolean reslt = driverInfoService.updateMarketingDriverInfoSetMobilePhone(marketingDriverInfo);
            return JSonRespone.makeHasContentJSonRespone("0", "编辑司机备用手机号码成功！",mobilePhone);
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 号码无效
     * @param marketingDriverInfo
     * @return
     */
    @RequestMapping("/updateWuxiaohaoma.jspx")
    @ResponseBody
    public JSonRespone updateWuxiaohaoma(MarketingDriverInfo marketingDriverInfo) {
        try {
            marketingDriverInfo.setAllocateStatus(0);
            driverInfoService.updateMarketingDriverInfoSetMarkInvalidNums(marketingDriverInfo);
            addMarketingMaintainRecord(marketingDriverInfo.getCategory(), marketingDriverInfo.getId(), 3, null, null, Constants.RECORD_CONTENT_VAL);
            return JSonRespone.makeHasContentJSonRespone("0", "操作无效号码成功！");
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }

    /**
     * 获取司机客户类别
     * @param id
     * @return
     */
    @RequestMapping("/queryCategoryById.jspx")
    @ResponseBody
    public JSonRespone queryCategoryById(String id) {
        try {
            StringBuffer driverInfoHtml = new StringBuffer();
            List<DictInfo> listCarLength = DataDictUtil.driverInfoCategory();
            MarketingDriverInfoDomain marketingDriverInfoDomain = new MarketingDriverInfoDomain();
            marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(Integer.parseInt(id));
            driverInfoHtml.append("<select id=\"categoryselsct\" name=\"categoryselsct\" style=\"width: 134px;\" onchange=\"updateCategoryHtml();\">");
            for(int i = 0;i<listCarLength.size();i++) {
                if(marketingDriverInfoDomain.getCategory().equals(listCarLength.get(i).getCode())) {
                    driverInfoHtml.append("<option value=\""+listCarLength.get(i).getCode()+"\"  selected=\"true\">"+listCarLength.get(i).getName()+"</option>");
                }else {
                    driverInfoHtml.append("<option value=\""+listCarLength.get(i).getCode()+"\" >"+listCarLength.get(i).getName()+"</option>");
                }
            }
            driverInfoHtml.append("</select>");
            driverInfoHtml.append("<input type=\"hidden\" id=\"category\" name=\"category\" value=\""+marketingDriverInfoDomain.getCategory()+"\"/>");
            return JSonRespone.makeHasContentJSonRespone("0", "获取司机客户类别成功！",driverInfoHtml.toString());
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 修改客户类别
     * @param id
     * @param categoryselsct
     * @return
     */
    @RequestMapping("/updateCategoryById.jspx")
    @ResponseBody
    public JSonRespone updateCategoryById(String id,String categoryselsct) {
        try {
            if(StringUtils.isEmpty(id)) {
                return JSonRespone.makeHasContentJSonRespone("1", "参数id不能为空！");
            }
            StringBuffer driverInfoHtml = new StringBuffer();
            StringBuffer driverCounte = new StringBuffer();
            List<DictInfo> listCarLength = DataDictUtil.driverInfoCategory();
            MarketingDriverInfoDomain marketingDriverInfoDomain = null;
            MarketingDriverInfo marketingDriverInfo = new MarketingDriverInfo();
            marketingDriverInfo.setId(Integer.parseInt(id));
            marketingDriverInfo.setCategory(categoryselsct);
            if(StringUtils.isNotEmpty(categoryselsct)) {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(Integer.parseInt(id));
                if(!marketingDriverInfoDomain.getCategory().equals(categoryselsct)) {
                    driverCounte.append("客户类别有'"+marketingDriverInfoDomain.getCategory()+"'类修改为'"+categoryselsct+"'类。");
                }
                //修改客户类别
                boolean restl = driverInfoService.updateMarketingDriverInfoSetCategory(marketingDriverInfo);
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(Integer.parseInt(id));
                //客户维护记录操作
                if(StringUtils.isNotEmpty(driverCounte.toString())) {
                    addMarketingMaintainRecord(marketingDriverInfo.getCategory(), marketingDriverInfo.getId(), 3, null, null, driverCounte.toString());
                }
            }
            return JSonRespone.makeHasContentJSonRespone("0", "success");
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();

        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 提交
     * @param id
     * @param content1
     * @param content2
     * @param content3
     * @param isValidCall
     * @return
     */
    @RequestMapping("/addMarketingMaintainRecordById.jspx")
    @ResponseBody
    public JSonRespone addMarketingMaintainRecordById(String id,String nextContactDate,String content1,String content2,String content3,String isValidCall,String hasPurpose) {
        try {
            StringBuffer recordContent = new StringBuffer();
            MarketingDriverInfo marketingDriverInfo = new MarketingDriverInfo();
            marketingDriverInfo.setId(Integer.parseInt(id));
            marketingDriverInfo.setNextContactDate(DateUtil.parseLongDataFromStr(nextContactDate + ":00"));
            marketingDriverInfo.setHasPurpose(Integer.parseInt(hasPurpose));
            driverInfoService.updateMarketingDriverInfoSetContactDate(marketingDriverInfo);
            MarketingDriverInfoDomain marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(Integer.parseInt(id));
            if(StringUtils.isNotEmpty(content1)) {
                recordContent.append("产品、服务："+content1+"  ");
            }
            if(StringUtils.isNotEmpty(content2)) {
                recordContent.append("客户异议："+content2+"  ");
            }
            if(StringUtils.isNotEmpty(content3)) {
                recordContent.append("改进建议："+content3+"  ");
            }
            recordContent.append("下次联系时间："+nextContactDate);
            if("0".equals(hasPurpose)) {
                recordContent.append("是否有意向：无意向");
            }else {
                recordContent.append("是否有意向：有意向");
            }
            if(StringUtils.isNotEmpty(recordContent.toString())) {
                addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),1 ,Integer.parseInt(isValidCall), null,recordContent.toString());
            }
            return JSonRespone.makeHasContentJSonRespone("0", "提交成功！");
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }


    /**
     * 查询客户维护记录
     * @param marketingMaintainRecordDomain
     * @param pageInfo
     * @return
     */
    @RequestMapping("/queryMarketingMaintainRecordPage.jspx")
    @ResponseBody
    public JSonRespone queryMarketingMaintainRecordPage(MarketingMaintainRecordDomain marketingMaintainRecordDomain,PageInfo pageInfo) {
        try {
            marketingMaintainRecordDomain.setPageInfo(pageInfo);
            marketingMaintainRecordDomain  = marketingMaintainRecordService.queryMarketingMaintainRecordDomainList(marketingMaintainRecordDomain);
            marketingMaintainRecordDomain.setHtml1(marketingMaintainRecordService.getMarketingMaintainRecordHtml(marketingMaintainRecordDomain.getList(),pageInfo.getCurPage(),pageInfo.getPageSize()));
            return JSonRespone.makeHasContentJSonRespone("0", "提交成功！",marketingMaintainRecordDomain);
        }catch (Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "程序逻辑出现错误！");
    }

    /**
     * 匹配货源
     * @param id
     * @return
     */
    @RequestMapping("/queryMarketingOrderCargoById.jspx")
    @ResponseBody
    public JSonRespone queryMarketingOrderCargoById(String id,String start) {
        try {
            List<OrderCargoInfoDomain> list = orderCargoInfoService.queryOrderCargoInfoDomainDriverInfoHsql(id,start);
            String cagoHtml = orderCargoInfoService.getOrderCargoInfoString(list,start);
            return JSonRespone.makeHasContentJSonRespone("0", "匹配货源失败！",cagoHtml);
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "匹配货源失败！");
    }


    /**
     * 发送或推送货源给司机
     * @param cargoId
     * @return
     */
    @RequestMapping("/setCargoNotePushInfo.jspx")
    @ResponseBody
    public JSonRespone setCargoNotePushInfo(String id,String cargoId,String start) {
        try {
            MarketingDriverInfoDomain marketingDriverInfoDomain = new MarketingDriverInfoDomain();
            OrderCargoInfo orderCargoInfo = orderCargoInfoService.queryOrderCargoInfoById(cargoId);
            StringBuffer noteInfoContet = new StringBuffer();
            if("N".equals(start)) {//发送短信
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(Integer.parseInt(id));
                String companyName = orderCargoInfo.getCompanyName() + "公司";
                noteInfoContet.append("【快到网】");
                if(companyName.length() > 8) {
                    companyName = companyName.substring(0,6) + "公司";
                }
                if(companyName.contains("公司公司")) {
                    companyName = companyName.replace("公司公司", "公司");
                }
                noteInfoContet.append(companyName);

                if(StringUtils.isNotEmpty(orderCargoInfo.getStartCity())) {
                    noteInfoContet.append(orderCargoInfo.getStartCity());
                }else {
                    noteInfoContet.append(orderCargoInfo.getStartProvince());
                }
                noteInfoContet.append("到");
                if(StringUtils.isNotEmpty(orderCargoInfo.getEndCity())) {
                    noteInfoContet.append(orderCargoInfo.getEndCity()+",");
                }else {
                    noteInfoContet.append(orderCargoInfo.getEndProvince()+",");
                }
                noteInfoContet.append(orderCargoInfo.getCargoName());
                if(orderCargoInfo.getCargoWeight() != 0) {
                    noteInfoContet.append(orderCargoInfo.getCargoWeight()+"吨,");
                }else {
                    noteInfoContet.append(orderCargoInfo.getCargoCubage()+"方,");
                }
                noteInfoContet.append("符合您的运营线路,");
                noteInfoContet.append(orderCargoInfo.getContactName());
                if(StringUtils.isNotEmpty(orderCargoInfo.getContactMobilephone())) {
                    noteInfoContet.append(orderCargoInfo.getContactMobilephone());
                }else {
                    noteInfoContet.append(orderCargoInfo.getContactTelephone());
                }
                noteInfoContet.append("打开快到网查收，重新安装点击 http://t.cn/RzF33v7");
                /**
                 *
                 * @param type 发送对象类别 0企业 1司机
                 * @param remark 备注
                 * @param telephone 发送手机
                 * @param content 发送内容
                 * @param useFor
                 * @return 用途：1 导入货源配车有司机发送给企业的短信 2 导入货源配车无司机发送给企业的短信 3营销平台客户司机匹配货源给司机发送短信 4营销平台客户管理司机主动发送短信
                 */
                marketingNoteRecordService.setNoteSendRecordInfo("1","",marketingDriverInfoDomain.getMobilePhone(),noteInfoContet.toString(),"3");
                /**
                 *
                 * @param category 客户类别(1 2 3 4 5)
                 * @param customerId 客户id
                 * @param maintainAction 维护动作类型:1 电话联系 2 发送短信 3 修改信息 4推送
                 * @param isValidCall 是否有效电话： 0 否 1 是
                 * @param recordFilePath 录音文件PATH
                 * @param recordContent 记录内容
                 * @return
                 */
                addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),2 ,null, null,noteInfoContet.toString());
                return JSonRespone.makeHasContentJSonRespone("0", "发送货源成功！");
            }else {//推送
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainById(Integer.parseInt(id));
                noteInfoContet.append("已帮您找到优质货源，请点击查看！");
                /**
                 * 推送
                 * @param driverId 司机id
                 * @param title 推送标题
                 * @param message 推送内容
                 * @param id  自定义通知内容，选输项(不在通知栏显示，APP使用)Id
                 * @param type 类型 0货源 1订单
                 * @return
                 */
                marketingNoteRecordService.setPushInfo(String.valueOf(marketingDriverInfoDomain.getDriverId()),"【快到网】提醒您！",noteInfoContet.toString(),cargoId,"0", Constants.SP_KEY_INITIATIVE_MARKET);
                addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),4 ,null, null,"【快到网】提醒您！" + noteInfoContet.toString());
                return JSonRespone.makeHasContentJSonRespone("0", "推送货源成功！");
            }
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "发送货源给数据失败！");
    }


    /**
     * 获取车辆运动轨迹
     * @param
     * @return
     */
    @RequestMapping("/queryLocation.jspx")
    @ResponseBody
    public JSonRespone queryLocation(LocationCollectInfoDomain locationCollectInfoDomain,PageInfo pageInfo) {
        try {
            locationCollectInfoDomain.setPageInfo(pageInfo);
            locationCollectInfoDomain = locationInfoService.queryLocationInfoPageList(locationCollectInfoDomain);
            locationCollectInfoDomain.setHtml(locationInfoService.getqueryLocationInfoHtml(locationCollectInfoDomain.getList(),locationCollectInfoDomain.getPageInfo().getCurPage(),locationCollectInfoDomain.getPageInfo().getPageSize()));
            return JSonRespone.makeHasContentJSonRespone("0", "查询轨迹成功！",locationCollectInfoDomain);
        }catch (Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "获取轨迹失败！");
    }


    /**
     * 查看司机图片
     * @param id
     * @param type
     * @return
     */
    @RequestMapping("/queryDriverImg.jspx")
    @ResponseBody
    public JSonRespone queryDriverImg(String id,String type) {
        try {
            MarketingDriverInfoDomain marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainById(Integer.parseInt(id));
            if("1".equals(type)) {
                if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getDriversLicense())) {//驾驶证路径
                    return JSonRespone.makeHasContentJSonRespone("0", "驾驶证！",marketingDriverInfoDomain.getDrivingLicense());
                }else {
                    return JSonRespone.makeHasContentJSonRespone("-1", "驾驶证不存在！");
                }
            }else {
                if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getDrivingLicense())) {//行驶证路径
                    return JSonRespone.makeHasContentJSonRespone("0", "行驶证！",marketingDriverInfoDomain.getDriversLicense());
                }else {
                    return JSonRespone.makeHasContentJSonRespone("-1", "行驶证不存在！");
                }
            }
        } catch (Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "查看图片错误！");
    }



    /**
     * 给司机发送短信（不针对货源）
     * @param marketingDriverInfoDomain
     * @param start
     * @return
     */
    @RequestMapping("/queryNoteInfoByDriverId.jspx")
    @ResponseBody
    public JSonRespone queryNoteInfoByDriverId(MarketingDriverInfoDomain marketingDriverInfoDomain,String start) {
        try {
            if("N".equals(start)) {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(marketingDriverInfoDomain.getId());
            }else {
                marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainById(marketingDriverInfoDomain.getId());
            }
            List<SystemNoteTemplateInfo>  list = systemNoteTemplateService.querySystemNoteTemplateInfo(1,1);
            StringBuffer driverInfoHtml = new StringBuffer();
            driverInfoHtml.append("<div class=\"tabfl\">");
            driverInfoHtml.append("<div style=\"height: 30px; text-align: center;\" id=\"updateNewsId\"></div>");
            driverInfoHtml.append("<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" style=\"margin-top:-1px;\">");
            for(int i = 0; i<list.size(); i++) {
                driverInfoHtml.append("<tr>");
                driverInfoHtml.append("<td><input id=\"noteInfoId\" onchange=\"querySystemNoteById('"+list.get(i).getId()+"')\" name=\"noteInfoId\" type=\"radio\" value=\""+list.get(i).getId()+"\"/></td>");
                driverInfoHtml.append("<td>"+list.get(i).getContent()+"</td>");
                driverInfoHtml.append("</tr>");
            }
            driverInfoHtml.append("</table>");
            driverInfoHtml.append("<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" style=\"margin-top:-1px;\">");
            driverInfoHtml.append("<tr>");
            driverInfoHtml.append("<td style=\"width: 100px;\">预览发送短信:</td>");
            driverInfoHtml.append("<td><textarea rows=\"6\" cols=\"80\" id=\"contentNoteId\" name=\"contentNoteId\" style=\"resize: none;\"></textarea></td>");
            driverInfoHtml.append("</tr>");
            driverInfoHtml.append("</table>");
            driverInfoHtml.append("<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" style=\"margin-top:-1px;\">");
            driverInfoHtml.append("<tr>");
            driverInfoHtml.append("<td>选择手机号码:</td>");
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getMobilePhone())) {
                driverInfoHtml.append("<td><input id=\"mobilePhoneNoteId\" name=\"mobilePhoneNoteId\" type=\"radio\" value=\""+marketingDriverInfoDomain.getMobilePhone()+"\"/>"+marketingDriverInfoDomain.getMobilePhone()+"</td>");
            }
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getMobilePhone2())) {
                driverInfoHtml.append("<td><input id=\"mobilePhoneNoteId\" name=\"mobilePhoneNoteId\" type=\"radio\" value=\""+marketingDriverInfoDomain.getMobilePhone2()+"\"/>"+marketingDriverInfoDomain.getMobilePhone2()+"</td>");
            }
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getMobilePhone3())) {
                driverInfoHtml.append("<td><input id=\"mobilePhoneNoteId\" name=\"mobilePhoneNoteId\" type=\"radio\" value=\""+marketingDriverInfoDomain.getMobilePhone3()+"\"/>"+marketingDriverInfoDomain.getMobilePhone3()+"</td>");
            }
            driverInfoHtml.append("</tr>");
            driverInfoHtml.append("</table>");
            driverInfoHtml.append("<div class=\"buttons\"><a href=\"javascript:addNoteInfo();\">发送</a><a href=\"javascript:closes();\">取消</a></div>");
            driverInfoHtml.append("</div>");
            return JSonRespone.makeHasContentJSonRespone("0", "获取短信发送页面成功！",driverInfoHtml.toString());
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "获取短信发送页面失败！");
    }


    /**
     * 主动发送短信
     * @param marketingDriverInfoDomain
     * @param noteId
     * @param mobilePhone
     * @return
     */
    @RequestMapping("/addNoteInfoByDriverId.jspx")
    @ResponseBody
    public JSonRespone addNoteInfoByDriverId(MarketingDriverInfoDomain marketingDriverInfoDomain,String noteId,String mobilePhone) {
        try{
            marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(marketingDriverInfoDomain.getId());
            SystemNoteTemplateInfo systemNoteTemplateInfo = systemNoteTemplateService.querySystemNoteTemplateInfoById(Integer.parseInt(noteId));
            /**
             * @param type 发送对象类别 0企业 1司机
             * @param remark 备注
             * @param telephone 发送手机
             * @param content 发送内容
             * @param useFor
             * @return 用途：1 导入货源配车有司机发送给企业的短信 2 导入货源配车无司机发送给企业的短信 3营销平台客户司机匹配货源给司机发送短信 4营销平台客户管理司机主动发送短信
             */
            marketingNoteRecordService.setNoteSendRecordInfo("1","营销平台发送给司机短信",mobilePhone,systemNoteTemplateInfo.getContent(),"4");
            try{
                addMarketingMaintainRecord(marketingDriverInfoDomain.getCategory(),marketingDriverInfoDomain.getId(),2,null,null,systemNoteTemplateInfo.getContent());
            }catch(Exception e) {
                logger.error("发送短信模板内容，记录客户维护信息出现错误！");
                e.printStackTrace();
            }
            return JSonRespone.makeHasContentJSonRespone("0", "发送短信成功！");
        }catch (Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "发送短信失败！");
    }


    /**
     * 预览短信
     * @param systemNoteTemplateInfo
     * @return
     */
    @RequestMapping("/querySystemNoteById.jspx")
    @ResponseBody
    public JSonRespone querySystemNoteById(SystemNoteTemplateInfo systemNoteTemplateInfo) {
        try {
            systemNoteTemplateInfo = systemNoteTemplateService.querySystemNoteTemplateInfoById(systemNoteTemplateInfo.getId());
            return JSonRespone.makeHasContentJSonRespone("0", "预览短信成功！",systemNoteTemplateInfo.getContent());
        }catch(Exception e) {
            logger.error("程序逻辑出现错误！");
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "预览短信失败！");
    }



    /**
     *
     * @param category 客户类别(1 2 3 4 5)
     * @param customerId 客户id
     * @param maintainAction 维护动作类型:1 电话联系 2 发送短信 3 修改信息 4推送
     * @param isValidCall 是否有效电话： 0 否 1 是
     * @param recordFilePath 录音文件PATH
     * @param recordContent 记录内容
     * @return
     */
    public boolean addMarketingMaintainRecord(String category,Integer customerId,Integer maintainAction,Integer isValidCall,String recordFilePath,String recordContent){
        MarketingMaintainRecord marketingMaintainRecord = new MarketingMaintainRecord();
        marketingMaintainRecord.setAssisterId(getSessionUser().getId());
        if(StringUtils.isNotEmpty(category)) {
            marketingMaintainRecord.setCategory(Integer.parseInt(category));
        }
        marketingMaintainRecord.setCustomerKind(2);
        marketingMaintainRecord.setCustomerId(customerId);
        if(maintainAction != null) {
            marketingMaintainRecord.setMaintainAction(maintainAction);
        }
        if(isValidCall != null) {
            marketingMaintainRecord.setIsValidCall(isValidCall);
        }
        marketingMaintainRecord.setRecordFilePath(recordFilePath);
        marketingMaintainRecord.setRecordContent(recordContent);
        Integer id = marketingMaintainRecordService.addMarketingMaintainRecord(marketingMaintainRecord);
        if(id != null) {
            return true;
        }
        return false;
    }



    /**
     *
     * @param bo1 要修改的信息
     * @param bo2 现有信息
     * @param type 对比类型（0对比车辆信息、1对比长跑线路、2修改备用手机号码）
     * @return
     */
    public String updateContrast(MarketingDriverInfoDomain bo1,MarketingDriverInfoDomain bo2,String type) {
        StringBuffer updateString = new StringBuffer();
        if("0".equals(type)) {
            if(!bo1.getQqNumber().equals(bo2.getQqNumber())) {
                if(StringUtils.isNotEmpty(bo2.getQqNumber())) {
                    updateString.append("QQ号码由'"+bo2.getQqNumber()+"'修改为'"+bo1.getQqNumber()+"',");
                }else {
                    updateString.append("完善QQ号码'"+bo1.getQqNumber()+"',");
                }
            }
            if(!bo1.getCarNumber().equals(bo2.getCarNumber())) {
                if(StringUtils.isNotEmpty(bo2.getCarNumber())) {
                    updateString.append("车牌号由'"+bo2.getCarNumber()+"'修改为'"+bo1.getCarNumber()+"',");
                }else {
                    updateString.append("完善车牌号'"+bo1.getCarNumber()+"',");
                }
            }
            if(!"请选择".equals(bo1.getCarLength())) {
                if(!bo1.getCarLength().equals(bo2.getCarLength())) {
                    if(StringUtils.isNotEmpty(bo2.getCarLength())) {
                        updateString.append("车辆长度由'"+bo2.getCarLength()+"'修改为'"+bo1.getCarLength()+"',");
                    }else {
                        updateString.append("完善车辆长'"+bo1.getCarLength()+"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getCarLength())) {
                    updateString.append("车辆长度由'"+bo2.getCarLength()+"'修改为没有车长,");
                }
            }
            if(!"请选择".equals(bo1.getCarPlateType())) {
                if(!bo1.getCarPlateType().equals(bo2.getCarPlateType())) {
                    if(StringUtils.isNotEmpty(bo2.getCarPlateType())) {
                        updateString.append("车板由'"+bo2.getCarPlateType()+"'修改为'"+bo1.getCarPlateType()+"',");
                    }else {
                        updateString.append("完善车板'"+bo1.getCarPlateType() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getCarPlateType())) {
                    updateString.append("车板由'"+bo2.getCarPlateType()+"'修改为没有车板,");
                }
            }
            if(!"请选择".equals(bo1.getCarBarType())) {
                if(!bo1.getCarBarType().equals(bo2.getCarBarType())) {
                    if(StringUtils.isNotEmpty(bo2.getCarBarType())) {
                        updateString.append("车栏由'"+bo2.getCarBarType()+"'修改为'"+bo1.getCarBarType()+"',");
                    }else {
                        updateString.append("完善车栏'"+bo1.getCarBarType() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getCarBarType())) {
                    updateString.append("车栏由'"+bo2.getCarBarType()+"'修改为没有车栏,");
                }
            }
            if(!"请选择".equals(bo1.getCarWeight())) {
                if(!bo1.getCarWeight().equals(bo2.getCarWeight())) {
                    if(StringUtils.isNotEmpty(bo2.getCarWeight())) {
                        updateString.append("车辆重量(运力)由'"+bo2.getCarWeight()+"'修改为'"+bo1.getCarWeight()+"',");
                    }else {
                        updateString.append("完善车辆重量(运力)'"+bo1.getCarWeight() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getCarWeight())) {
                    updateString.append("车辆重量(运力)由'"+bo2.getCarWeight()+"'修改为没有车辆重量(运力),");
                }
            }
            if(!"请选择".equals(bo1.getCarCubage())) {
                if(!bo1.getCarCubage().equals(bo2.getCarCubage())) {
                    if(StringUtils.isNotEmpty(bo2.getCarCubage())) {
                        updateString.append("车辆体积(运力)由'"+bo2.getCarCubage()+"'修改为'"+bo1.getCarCubage()+"',");
                    }else {
                        updateString.append("完善车辆体积(运力)'"+bo1.getCarCubage() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getCarCubage())) {
                    updateString.append("车辆体积(运力)由'"+bo2.getCarCubage()+"'修改为没有车辆体积(运力),");
                }
            }
        }else if("1".equals(type)) {
            if(StringUtils.isNotEmpty(bo1.getOftenCity1())) {
                if(!bo1.getOftenCity1().equals(bo2.getOftenCity1())) {
                    if(StringUtils.isNotEmpty(bo2.getOftenCity1())) {
                        updateString.append("车辆长跑城市1由'"+bo2.getOftenCity1()+"'修改为'"+bo1.getOftenCity1()+"',");
                    }else {
                        updateString.append("车辆长跑城市1'"+bo1.getOftenCity1() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getOftenCity1())) {
                    updateString.append("车辆长跑城市1由'"+bo2.getOftenCity1()+"'修改为空,");
                }
            }
            if(StringUtils.isNotEmpty(bo1.getOftenCity2())) {
                if(!bo1.getOftenCity2().equals(bo2.getOftenCity2())) {
                    if(StringUtils.isNotEmpty(bo2.getOftenCity2())) {
                        updateString.append("车辆长跑城市2由'"+bo2.getOftenCity2()+"'修改为'"+bo1.getOftenCity2()+"',");
                    }else {
                        updateString.append("车辆长跑城市2'"+bo1.getOftenCity2() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getOftenCity2())) {
                    updateString.append("车辆长跑城市2由'"+bo2.getOftenCity2()+"'修改为空,");
                }
            }
            if(StringUtils.isNotEmpty(bo1.getOftenCity3())) {
                if(!bo1.getOftenCity3().equals(bo2.getOftenCity3())) {
                    if(StringUtils.isNotEmpty(bo2.getOftenCity3())) {
                        updateString.append("车辆长跑城市3由'"+bo2.getOftenCity3()+"'修改为'"+bo1.getOftenCity3()+"',");
                    }else {
                        updateString.append("车辆长跑城市3'"+bo1.getOftenCity3() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getOftenCity3())) {
                    updateString.append("车辆长跑城市3由'"+bo2.getOftenCity3()+"'修改为空,");
                }
            }
            if(StringUtils.isNotEmpty(bo1.getOftenCity4())) {
                if(!bo1.getOftenCity4().equals(bo2.getOftenCity4())) {
                    if(StringUtils.isNotEmpty(bo2.getOftenCity4())) {
                        updateString.append("车辆长跑城市4由'"+bo2.getOftenCity4()+"'修改为'"+bo1.getOftenCity4()+"',");
                    }else {
                        updateString.append("车辆长跑城市4'"+bo1.getOftenCity4() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getOftenCity4())) {
                    updateString.append("车辆长跑城市4由'"+bo2.getOftenCity4()+"'修改为空,");
                }
            }
            if(StringUtils.isNotEmpty(bo1.getOftenCity5())) {
                if(!bo1.getOftenCity5().equals(bo2.getOftenCity5())) {
                    if(StringUtils.isNotEmpty(bo2.getOftenCity5())) {
                        updateString.append("车辆长跑城市5由'"+bo2.getOftenCity5()+"'修改为'"+bo1.getOftenCity5()+"',");
                    }else {
                        updateString.append("车辆长跑城市5'"+bo1.getOftenCity5() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getOftenCity5())) {
                    updateString.append("车辆长跑城市5由'"+bo2.getOftenCity5()+"'修改为空,");
                }
            }
            if(StringUtils.isNotEmpty(bo1.getOftenCity6())) {
                if(!bo1.getOftenCity6().equals(bo2.getOftenCity6())) {
                    if(StringUtils.isNotEmpty(bo2.getOftenCity6())) {
                        updateString.append("车辆长跑城市6由'"+bo2.getOftenCity6()+"'修改为'"+bo1.getOftenCity6()+"',");
                    }else {
                        updateString.append("车辆长跑城市6'"+bo1.getOftenCity6() +"',");
                    }
                }
            }else {
                if(StringUtils.isNotEmpty(bo2.getOftenCity6())) {
                    updateString.append("车辆长跑城市6由'"+bo2.getOftenCity6()+"'修改为空,");
                }
            }
        }else if("2".equals(type)) {

        }
        return updateString.toString();
    }


    /**
     * 对象转换
     * MarketingDriverBusinessLineDomain 转换 MarketingDriverBusinessLine
     * @param marketingDriverBusinessLineDomain
     * @return
     */
    public MarketingDriverBusinessLine changeMarketingDriverBuLineInfo(MarketingDriverBusinessLineDomain marketingDriverBusinessLineDomain) {
        try {
            MarketingDriverBusinessLine marketingDriverBusinessLine = new MarketingDriverBusinessLine();
            marketingDriverBusinessLine.setId(marketingDriverBusinessLineDomain.getId());
            marketingDriverBusinessLine.setCustomerDriverId(marketingDriverBusinessLineDomain.getCustomerDriverId());
            marketingDriverBusinessLine.setStartTime(DateUtil.parseDayDataFromStr(marketingDriverBusinessLineDomain.getStartTime()));
            marketingDriverBusinessLine.setEndTime(DateUtil.parseDayDataFromStr(marketingDriverBusinessLineDomain.getEndTime()));
            if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getStartPcc())) {
                String[] startpcc = marketingDriverBusinessLineDomain.getStartPcc().split("-");
                if(startpcc.length == 1) {
                    marketingDriverBusinessLine.setStartProvince(startpcc[0]);
                }else if(startpcc.length == 2) {
                    marketingDriverBusinessLine.setStartProvince(startpcc[0]);
                    marketingDriverBusinessLine.setStartCity(startpcc[1]);
                }else if(startpcc.length == 3) {
                    marketingDriverBusinessLine.setStartProvince(startpcc[0]);
                    marketingDriverBusinessLine.setStartCity(startpcc[1]);
                    marketingDriverBusinessLine.setStartCounty(startpcc[2]);
                }
            }
            if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getEndPcc())) {
                String[] endpcc = marketingDriverBusinessLineDomain.getEndPcc().split("-");
                if(endpcc.length == 1) {
                    marketingDriverBusinessLine.setEndProvince(endpcc[0]);
                }else if(endpcc.length == 2) {
                    marketingDriverBusinessLine.setEndProvince(endpcc[0]);
                    marketingDriverBusinessLine.setEndCity(endpcc[1]);
                }else if(endpcc.length == 3) {
                    marketingDriverBusinessLine.setEndProvince(endpcc[0]);
                    marketingDriverBusinessLine.setEndCity(endpcc[1]);
                    marketingDriverBusinessLine.setEndCounty(endpcc[2]);
                }
            }
            marketingDriverBusinessLine.setQuoteType(marketingDriverBusinessLineDomain.getQuoteType());
            marketingDriverBusinessLine.setQuoteFair(marketingDriverBusinessLineDomain.getQuoteFair());
            return marketingDriverBusinessLine;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转换
     * MarketingDriverBusinessLineDomain 转换 DriverBusinessLineInfo
     * @param marketingDriverBusinessLineDomain
     * @return
     */
    public DriverBusinessLineInfo changeDriverBusinessLineInfo(MarketingDriverBusinessLineDomain marketingDriverBusinessLineDomain) {
        try {
            DriverBusinessLineInfo driverBusinessLineInfo = new DriverBusinessLineInfo();
            driverBusinessLineInfo.setId(marketingDriverBusinessLineDomain.getId());
            driverBusinessLineInfo.setDriverId(marketingDriverBusinessLineDomain.getCustomerDriverId());
            driverBusinessLineInfo.setStartTime(DateUtil.parseDayDataFromStr(marketingDriverBusinessLineDomain.getStartTime()));
            driverBusinessLineInfo.setEndTime(DateUtil.parseDayDataFromStr(marketingDriverBusinessLineDomain.getEndTime()));
            if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getStartPcc())) {
                String[] startpcc = marketingDriverBusinessLineDomain.getStartPcc().split("-");
                if(startpcc.length == 1) {
                    driverBusinessLineInfo.setStartProvince(startpcc[0]);
                }else if(startpcc.length == 2) {
                    driverBusinessLineInfo.setStartProvince(startpcc[0]);
                    driverBusinessLineInfo.setStartCity(startpcc[1]);
                }else if(startpcc.length == 3) {
                    driverBusinessLineInfo.setStartProvince(startpcc[0]);
                    driverBusinessLineInfo.setStartCity(startpcc[1]);
                    driverBusinessLineInfo.setStartCounty(startpcc[2]);
                }
            }
            if(StringUtils.isNotEmpty(marketingDriverBusinessLineDomain.getEndPcc())) {
                String[] endpcc = marketingDriverBusinessLineDomain.getEndPcc().split("-");
                if(endpcc.length == 1) {
                    driverBusinessLineInfo.setEndProvince(endpcc[0]);
                }else if(endpcc.length == 2) {
                    driverBusinessLineInfo.setEndProvince(endpcc[0]);
                    driverBusinessLineInfo.setEndCity(endpcc[1]);
                }else if(endpcc.length == 3) {
                    driverBusinessLineInfo.setEndProvince(endpcc[0]);
                    driverBusinessLineInfo.setEndCity(endpcc[1]);
                    driverBusinessLineInfo.setEndCounty(endpcc[2]);
                }
            }
            driverBusinessLineInfo.setQuoteType(marketingDriverBusinessLineDomain.getQuoteType());
            driverBusinessLineInfo.setQuoteFair(marketingDriverBusinessLineDomain.getQuoteFair());
            return driverBusinessLineInfo;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MarketingDriverInfo changeDriverInfo(MarketingDriverInfoDomain marketingDriverInfoDomain) {
        StringBuffer cartypes = new StringBuffer();
        MarketingDriverInfo marketingDriverInfo = new MarketingDriverInfo();
        marketingDriverInfo.setId(marketingDriverInfoDomain.getId());
        marketingDriverInfo.setCarNumber(marketingDriverInfoDomain.getCarNumber());
        if(!"请选择".equals(marketingDriverInfoDomain.getCarLength())) {
            marketingDriverInfo.setCarLength(marketingDriverInfoDomain.getCarLength());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarLength())) {
                cartypes.append(marketingDriverInfoDomain.getCarLength()+ " ");
            }
        }
        if(!"请选择".equals(marketingDriverInfoDomain.getCarPlateType())) {
            marketingDriverInfo.setCarPlateType(marketingDriverInfoDomain.getCarPlateType());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarPlateType())) {
                cartypes.append(marketingDriverInfoDomain.getCarPlateType()+ " ");
            }
        }
        if(!"请选择".equals(marketingDriverInfoDomain.getCarBarType())) {
            marketingDriverInfo.setCarBarType(marketingDriverInfoDomain.getCarBarType());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarBarType())) {
                cartypes.append(marketingDriverInfoDomain.getCarBarType()+ " ");
            }
        }
        if(!"请选择".equals(marketingDriverInfoDomain.getCarWeight())) {
            marketingDriverInfo.setCarWeight(marketingDriverInfoDomain.getCarWeight());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarWeight())) {
                cartypes.append(marketingDriverInfoDomain.getCarWeight()+ " ");
            }
        }
        if(!"请选择".equals(marketingDriverInfoDomain.getCarCubage())) {
            marketingDriverInfo.setCarCubage(marketingDriverInfoDomain.getCarCubage());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarCubage())) {
                cartypes.append(marketingDriverInfoDomain.getCarCubage()+ " ");
            }
        }
        marketingDriverInfo.setCarTypes(cartypes.toString());
        marketingDriverInfo.setOftenCity1(marketingDriverInfoDomain.getOftenCity1());
        marketingDriverInfo.setOftenCity2(marketingDriverInfoDomain.getOftenCity2());
        marketingDriverInfo.setOftenCity3(marketingDriverInfoDomain.getOftenCity3());
        marketingDriverInfo.setOftenCity4(marketingDriverInfoDomain.getOftenCity4());
        marketingDriverInfo.setOftenCity5(marketingDriverInfoDomain.getOftenCity5());
        marketingDriverInfo.setOftenCity6(marketingDriverInfoDomain.getOftenCity6());
        marketingDriverInfo.setMobilePhone2(marketingDriverInfoDomain.getMobilePhone2());
        marketingDriverInfo.setMobilePhone3(marketingDriverInfoDomain.getMobilePhone3());
        marketingDriverInfo.setName(marketingDriverInfoDomain.getName());
        marketingDriverInfo.setQqNumber(marketingDriverInfoDomain.getQqNumber());
        return marketingDriverInfo;
    }


    public MarketingDriverLine changeMarketingDriverLine(String id,String customerDriverId,String strartPcc,String endPcc) {
        MarketingDriverLine marketingDriverLine = new MarketingDriverLine();
        if(StringUtils.isNotEmpty(id)) {
            marketingDriverLine.setId(Integer.parseInt(id));
        }
        String[] spcc = strartPcc.split("-");
        String[] epcc = endPcc.split("-");
        if(spcc.length == 1) {
            marketingDriverLine.setStartProvince(spcc[0]);
        }else if(spcc.length == 2) {
            marketingDriverLine.setStartProvince(spcc[0]);
            marketingDriverLine.setStartCity(spcc[1]);
        }else if(spcc.length == 3) {
            marketingDriverLine.setStartProvince(spcc[0]);
            marketingDriverLine.setStartCity(spcc[1]);
            marketingDriverLine.setStartCounty(spcc[2]);
        }
        if(epcc.length == 1) {
            marketingDriverLine.setEndProvince(epcc[0]);
        }else if(epcc.length == 2) {
            marketingDriverLine.setEndProvince(epcc[0]);
            marketingDriverLine.setEndCity(epcc[1]);
        }else if(epcc.length == 3) {
            marketingDriverLine.setEndProvince(epcc[0]);
            marketingDriverLine.setEndCity(epcc[1]);
            marketingDriverLine.setEndCounty(epcc[2]);
        }
        marketingDriverLine.setCustomerDriverId(Integer.parseInt(customerDriverId));
        return marketingDriverLine;
    }

    public DriverLineInfo changeDriverLineInfo(String id,String driverId,String strartPcc,String endPcc) {
        DriverLineInfo driverLineInfo = new DriverLineInfo();
        if(StringUtils.isNotEmpty(id)) {
            driverLineInfo.setId(Integer.parseInt(id));
        }
        String[] spcc = strartPcc.split("-");
        String[] epcc = endPcc.split("-");
        if(spcc.length == 1) {
            driverLineInfo.setStartProvince(spcc[0]);
        }else if(spcc.length == 2) {
            driverLineInfo.setStartProvince(spcc[0]);
            driverLineInfo.setStartCity(spcc[1]);
        }else if(spcc.length == 3) {
            driverLineInfo.setStartProvince(spcc[0]);
            driverLineInfo.setStartCity(spcc[1]);
            driverLineInfo.setStartCounty(spcc[2]);
        }
        if(epcc.length == 1) {
            driverLineInfo.setEndProvince(epcc[0]);
        }else if(epcc.length == 2) {
            driverLineInfo.setEndProvince(epcc[0]);
            driverLineInfo.setEndCity(epcc[1]);
        }else if(epcc.length == 3) {
            driverLineInfo.setEndProvince(epcc[0]);
            driverLineInfo.setEndCity(epcc[1]);
            driverLineInfo.setEndCounty(epcc[2]);
        }
        driverLineInfo.setDriverId(Integer.parseInt(driverId));
        return driverLineInfo;
    }

    public DriverUserInfo changeDriverUserInfo(MarketingDriverInfoDomain marketingDriverInfoDomain) {
        StringBuffer cartypes = new StringBuffer();
        DriverUserInfo driverUserInfo = new DriverUserInfo();
        driverUserInfo.setId(String.valueOf(marketingDriverInfoDomain.getDriverId()));
        driverUserInfo.setCarNumber(marketingDriverInfoDomain.getCarNumber());
        if(!"请选择".equals(marketingDriverInfoDomain.getCarLength())) {
            driverUserInfo.setCarLength(marketingDriverInfoDomain.getCarLength());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarLength())) {
                cartypes.append(marketingDriverInfoDomain.getCarLength()+ " ");
            }
        }
        if(!"请选择".equals(marketingDriverInfoDomain.getCarPlateType())) {
            driverUserInfo.setCarPlateType(marketingDriverInfoDomain.getCarPlateType());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarPlateType())) {
                cartypes.append(marketingDriverInfoDomain.getCarPlateType()+ " ");
            }
        }
        if(!"请选择".equals(marketingDriverInfoDomain.getCarBarType())) {
            driverUserInfo.setCarBarType(marketingDriverInfoDomain.getCarBarType());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarBarType())) {
                cartypes.append(marketingDriverInfoDomain.getCarBarType()+ " ");
            }
        }
        if(!"请选择".equals(marketingDriverInfoDomain.getCarWeight())) {
            driverUserInfo.setCarWeight(marketingDriverInfoDomain.getCarWeight());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarWeight())) {
                cartypes.append(marketingDriverInfoDomain.getCarWeight()+ " ");
            }
        }
        if(!"请选择".equals(marketingDriverInfoDomain.getCarCubage())) {
            driverUserInfo.setCarCubage(marketingDriverInfoDomain.getCarCubage());
            if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getCarCubage())) {
                cartypes.append(marketingDriverInfoDomain.getCarCubage()+ " ");
            }
        }
        driverUserInfo.setCarTypes(cartypes.toString());
        driverUserInfo.setName(marketingDriverInfoDomain.getName());
        return driverUserInfo;
    }


    /**
     * 客户运营路线
     * @param bo1 被修改的值
     * @param bo2 要修改的值
     * @return
     */
    //比较运营线路修改那些值
    public String getMaDriverLineInfo(MarketingDriverLine bo1,MarketingDriverLine bo2) {
        StringBuffer lineString = new StringBuffer();
        StringBuffer sbo1String = new StringBuffer();
        StringBuffer sbo2String = new StringBuffer();
        StringBuffer ebo1String = new StringBuffer();
        StringBuffer ebo2String = new StringBuffer();
        if(StringUtils.isNotEmpty(bo1.getStartProvince())) {
            sbo1String.append(bo1.getStartProvince());
            if(StringUtils.isNotEmpty(bo1.getStartCity())) {
                sbo1String.append("-"+bo1.getStartCity());
                if(StringUtils.isNotEmpty(bo1.getStartCounty())) {
                    sbo1String.append("-"+bo1.getStartCounty());
                }
            }
        }
        if(StringUtils.isNotEmpty(bo2.getStartProvince())) {
            sbo2String.append(bo2.getStartProvince());
            if(StringUtils.isNotEmpty(bo2.getStartCity())) {
                sbo2String.append("-"+bo2.getStartCity());
                if(StringUtils.isNotEmpty(bo2.getStartCounty())) {
                    sbo2String.append("-"+bo2.getStartCounty());
                }
            }
        }
        if(StringUtils.isNotEmpty(bo1.getEndProvince())) {
            ebo1String.append(bo1.getEndProvince());
            if(StringUtils.isNotEmpty(bo1.getEndCity())) {
                ebo1String.append("-"+bo1.getEndCity());
                if(StringUtils.isNotEmpty(bo1.getEndCounty())) {
                    ebo1String.append("-"+bo1.getEndCounty());
                }
            }
        }
        if(StringUtils.isNotEmpty(bo2.getEndProvince())) {
            ebo2String.append(bo2.getEndProvince());
            if(StringUtils.isNotEmpty(bo2.getEndCity())) {
                ebo2String.append("-"+bo2.getEndCity());
                if(StringUtils.isNotEmpty(bo2.getEndCounty())) {
                    ebo2String.append("-"+bo2.getEndCounty());
                }
            }
        }
        if(!sbo1String.toString().equals(sbo2String.toString())) {
            lineString.append("运营线路出发地'"+sbo1String.toString()+"'修改为'"+sbo2String.toString()+"',");
        }
        if(!ebo1String.toString().equals(ebo2String.toString())) {
            lineString.append("运营线路到达地'"+sbo1String.toString()+"'修改为'"+sbo2String.toString()+"',");
        }
        return lineString.toString();
    }

    /**
     * 运营路线
     * @param bo1 被修改的值
     * @param bo2 要修改的值
     * @return
     */
    //比较运营线路修改那些值
    public String getDriverLineInfo(DriverLineInfo bo1,DriverLineInfo bo2) {
        StringBuffer lineString = new StringBuffer();
        StringBuffer sbo1String = new StringBuffer();
        StringBuffer sbo2String = new StringBuffer();
        StringBuffer ebo1String = new StringBuffer();
        StringBuffer ebo2String = new StringBuffer();
        if(StringUtils.isNotEmpty(bo1.getStartProvince())) {
            sbo1String.append(bo1.getStartProvince());
            if(StringUtils.isNotEmpty(bo1.getStartCity())) {
                sbo1String.append("-"+bo1.getStartCity());
                if(StringUtils.isNotEmpty(bo1.getStartCounty())) {
                    sbo1String.append("-"+bo1.getStartCounty());
                }
            }
        }
        if(StringUtils.isNotEmpty(bo2.getStartProvince())) {
            sbo2String.append(bo2.getStartProvince());
            if(StringUtils.isNotEmpty(bo2.getStartCity())) {
                sbo2String.append("-"+bo2.getStartCity());
                if(StringUtils.isNotEmpty(bo2.getStartCounty())) {
                    sbo2String.append("-"+bo2.getStartCounty());
                }
            }
        }
        if(StringUtils.isNotEmpty(bo1.getEndProvince())) {
            ebo1String.append(bo1.getEndProvince());
            if(StringUtils.isNotEmpty(bo1.getEndCity())) {
                ebo1String.append("-"+bo1.getEndCity());
                if(StringUtils.isNotEmpty(bo1.getEndCounty())) {
                    ebo1String.append("-"+bo1.getEndCounty());
                }
            }
        }
        if(StringUtils.isNotEmpty(bo2.getEndProvince())) {
            ebo2String.append(bo2.getEndProvince());
            if(StringUtils.isNotEmpty(bo2.getEndCity())) {
                ebo2String.append("-"+bo2.getEndCity());
                if(StringUtils.isNotEmpty(bo2.getEndCounty())) {
                    ebo2String.append("-"+bo2.getEndCounty());
                }
            }
        }
        if(!sbo1String.toString().equals(sbo2String.toString())) {
            lineString.append("运营线路出发地'"+sbo1String.toString()+"'修改为'"+sbo2String.toString()+"',");
        }
        if(!ebo1String.toString().equals(ebo2String.toString())) {
            lineString.append("运营线路到达地'"+sbo1String.toString()+"'修改为'"+sbo2String.toString()+"',");
        }
        return lineString.toString();
    }

    /**
     * 客户维护记录修改预约线路
     * @param bo1
     * @param bo2
     * @return
     */
    public String getDriverBusinessLineInfo(MarketingDriverBusinessLineDomain bo1,MarketingDriverBusinessLine bo2) throws ParseException {
        StringBuffer lineString = new StringBuffer();
        StringBuffer sbo1String = new StringBuffer();
        StringBuffer sbo2String = new StringBuffer();
        StringBuffer ebo1String = new StringBuffer();
        StringBuffer ebo2String = new StringBuffer();
        if(StringUtils.isNotEmpty(bo1.getStartProvince())) {
            sbo1String.append(bo1.getStartProvince());
            if(StringUtils.isNotEmpty(bo1.getStartCity())) {
                sbo1String.append("-"+bo1.getStartCity());
                if(StringUtils.isNotEmpty(bo1.getStartCounty())) {
                    sbo1String.append("-"+bo1.getStartCounty());
                }
            }
        }
        if(StringUtils.isNotEmpty(bo2.getStartProvince())) {
            sbo2String.append(bo2.getStartProvince());
            if(StringUtils.isNotEmpty(bo2.getStartCity())) {
                sbo2String.append("-"+bo2.getStartCity());
                if(StringUtils.isNotEmpty(bo2.getStartCounty())) {
                    sbo2String.append("-"+bo2.getStartCounty());
                }
            }
        }
        if(StringUtils.isNotEmpty(bo1.getEndProvince())) {
            ebo1String.append(bo1.getEndProvince());
            if(StringUtils.isNotEmpty(bo1.getEndCity())) {
                ebo1String.append("-"+bo1.getEndCity());
                if(StringUtils.isNotEmpty(bo1.getEndCounty())) {
                    ebo1String.append("-"+bo1.getEndCounty());
                }
            }
        }
        if(StringUtils.isNotEmpty(bo2.getEndProvince())) {
            ebo2String.append(bo2.getEndProvince());
            if(StringUtils.isNotEmpty(bo2.getEndCity())) {
                ebo2String.append("-"+bo2.getEndCity());
                if(StringUtils.isNotEmpty(bo2.getEndCounty())) {
                    ebo2String.append("-"+bo2.getEndCounty());
                }
            }
        }
        if(!sbo1String.toString().equals(sbo2String.toString())) {
            lineString.append("预约线路出发地'"+sbo1String.toString()+"'修改为'"+sbo2String.toString()+"',");
        }
        if(!ebo1String.toString().equals(ebo2String.toString())) {
            lineString.append("预约线路到达地'"+ebo1String.toString()+"'修改为'"+ebo2String.toString()+"',");
        }
        if(!bo1.getStartTime().equals(DateUtil.parseDayDataFrom(bo2.getStartTime()))) {
            lineString.append("预约开始时间：'"+bo1.getStartTime()+"'修改为'"+DateUtil.parseDayDataFrom(bo2.getStartTime())+"',");
        }
        if(!bo1.getEndTime().equals(DateUtil.parseDayDataFrom(bo2.getEndTime()))) {
            lineString.append("预约结束时间：'"+bo1.getEndTime()+"'修改为'"+DateUtil.parseDayDataFrom(bo2.getEndTime())+"',");
        }

        return lineString.toString();
    }

}
