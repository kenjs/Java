package com.cy.swp.service.impl;

import com.cy.swp.bo.DriverBusinessLineInfo;
import com.cy.swp.bo.MarketingDriverBusinessLine;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.dao.MarketingDriverBusinessLineDao;
import com.cy.swp.domain.MarketingDriverBusinessLineDomain;
import com.cy.swp.service.MarketingDriverBusinessLineService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nixianjing on 14/12/16.
 */
@Service("marketingDriverBusinessLineServiceImpl")
public class MarketingDriverBusinessLineServiceImpl implements MarketingDriverBusinessLineService{


    @Resource
    private MarketingDriverBusinessLineDao marketingDriverBusinessLineDao;

    @Override
    public List<MarketingDriverBusinessLineDomain> queryMarketingDriverBusinessLineByCustomerDriverIdList(Integer customerDriverId) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("customerDriverId",customerDriverId);
        queryMap.put("deleteFlag", Constants.DELETED_FLAG_FALSE);
        List<MarketingDriverBusinessLineDomain> list = marketingDriverBusinessLineDao.queryMarketingDriverBusinessLineByCustomerDriverId(queryMap);
        return list;
    }

    @Override
    public List<MarketingDriverBusinessLineDomain> queryDriverBusinessLineInfoByIdList(Integer driverId) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("driverId",driverId);
        queryMap.put("start", Constants.DELETED_FLAG_FALSE);
        List<MarketingDriverBusinessLineDomain> list = marketingDriverBusinessLineDao.queryDriverBusinessLineInfoByDriverId(queryMap);
        return list;
    }

    @Override
    public String queryMarketingDriverBusinessLineByCustomerDriverId(Integer customerDriverId) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("customerDriverId",customerDriverId);
        queryMap.put("deleteFlag", Constants.DELETED_FLAG_FALSE);
        List<MarketingDriverBusinessLineDomain> list = marketingDriverBusinessLineDao.queryMarketingDriverBusinessLineByCustomerDriverId(queryMap);
        StringBuffer driverBuLineHtml = new StringBuffer();
        for(int i = 0;i<list.size();i++) {
            if(i == 0) {
                driverBuLineHtml.append("<tr>");
                driverBuLineHtml.append("<td rowspan=\"3\" style=\"width:40px\">预<br />约<br />信<br />息</td>");
                driverBuLineHtml.append("<td width=\"92px\">预约时间</td>");
                driverBuLineHtml.append("<td width=\"220px\">"+list.get(i).getStartTime()+"-"+list.get(i).getEndTime()+"</td>");
                driverBuLineHtml.append("<td width=\"297px\" colspan=\"2\">");
                if(StringUtils.isNotEmpty(list.get(i).getStartProvince())) {
                    driverBuLineHtml.append(list.get(i).getStartProvince());
                    if (StringUtils.isNotEmpty(list.get(i).getStartCity())) {
                        driverBuLineHtml.append(list.get(i).getStartCity());
                        if (StringUtils.isNotEmpty(list.get(i).getStartCounty())) {
                            driverBuLineHtml.append(list.get(i).getStartCounty());

                        }
                    }
                }
                driverBuLineHtml.append(" —— ");
                if(StringUtils.isNotEmpty(list.get(i).getEndProvince())) {
                    driverBuLineHtml.append(list.get(i).getEndProvince());
                    if (StringUtils.isNotEmpty(list.get(i).getEndCity())) {
                        driverBuLineHtml.append(list.get(i).getEndCity());
                        if (StringUtils.isNotEmpty(list.get(i).getEndCounty())) {
                            driverBuLineHtml.append(list.get(i).getEndCounty());

                        }
                    }
                }
                driverBuLineHtml.append("</td>");
                driverBuLineHtml.append("<td width=\"92px\">");
                if(list.get(i).getQuoteType() != null) {
                    if(Constants.BUSINESS_LINE_ZC_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_ZC_VALUE);
                    }else if(Constants.BUSINESS_LINE_AD_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_AD_VALUE);
                    }else if(Constants.BUSINESS_LINE_AF_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_AF_VALUE);
                    }
                }
                driverBuLineHtml.append("</td>");
                driverBuLineHtml.append("<td width=\"50px\">"+list.get(i).getQuoteFair()+"</td>");
                driverBuLineHtml.append("<td width=\"88px\"><input id=\"driverBuLineId"+i+"\" name=\"driverBuLineId"+i+"\" type=\"hidden\" value=\""+list.get(i).getId()+"\" />");
                driverBuLineHtml.append("<input name=\"\" type=\"button\" value=\"编辑\" onclick=\"queryDriverBuLineById('"+i+"','N');\" /></td>");
                driverBuLineHtml.append("</tr>");
            }else {
                driverBuLineHtml.append("<tr>");
                driverBuLineHtml.append("<td width=\"92px\">预约时间</td>");
                driverBuLineHtml.append("<td width=\"220px\">"+list.get(i).getStartTime()+"-"+list.get(i).getEndTime()+"</td>");
                driverBuLineHtml.append("<td width=\"297px\" colspan=\"2\">");
                if(StringUtils.isNotEmpty(list.get(i).getStartProvince())) {
                    driverBuLineHtml.append(list.get(i).getStartProvince());
                    if (StringUtils.isNotEmpty(list.get(i).getStartCity())) {
                        driverBuLineHtml.append(list.get(i).getStartCity());
                        if (StringUtils.isNotEmpty(list.get(i).getStartCounty())) {
                            driverBuLineHtml.append(list.get(i).getStartCounty());

                        }
                    }
                }
                driverBuLineHtml.append(" —— ");
                if(StringUtils.isNotEmpty(list.get(i).getEndProvince())) {
                    driverBuLineHtml.append(list.get(i).getEndProvince());
                    if (StringUtils.isNotEmpty(list.get(i).getEndCity())) {
                        driverBuLineHtml.append(list.get(i).getEndCity());
                        if (StringUtils.isNotEmpty(list.get(i).getEndCounty())) {
                            driverBuLineHtml.append(list.get(i).getEndCounty());

                        }
                    }
                }
                driverBuLineHtml.append("</td>");
                driverBuLineHtml.append("<td width=\"92px\">");
                if(list.get(i).getQuoteType() != null) {
                    if(Constants.BUSINESS_LINE_ZC_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_ZC_VALUE);
                    }else if(Constants.BUSINESS_LINE_AD_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_AD_VALUE);
                    }else if(Constants.BUSINESS_LINE_AF_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_AF_VALUE);
                    }
                }
                driverBuLineHtml.append("</td>");
                driverBuLineHtml.append("<td width=\"50px\">"+list.get(i).getQuoteFair()+"</td>");
                driverBuLineHtml.append("<td width=\"82px\"><input id=\"driverBuLineId"+i+"\" name=\"driverBuLineId"+i+"\" type=\"hidden\" value=\""+list.get(i).getId()+"\" />");
                driverBuLineHtml.append("<input name=\"\" type=\"button\" value=\"编辑\" onclick=\"queryDriverBuLineById('"+i+"','N');\" /></td>");
                driverBuLineHtml.append("</tr>");
            }
        }
        for(int j = 0;j<(3-list.size());j++) {
            if(list.size() == 0 && j == 0) {
                driverBuLineHtml.append("<tr>");
                driverBuLineHtml.append("<td rowspan=\"3\" style=\"width:40px\">预<br />约<br />信<br />息</td>");
                driverBuLineHtml.append("<td width=\"92px\">预约时间</td>");
                driverBuLineHtml.append("<td width=\"220px\"></td>");
                driverBuLineHtml.append("<td width=\"297px\" colspan=\"2\"></td>");
                driverBuLineHtml.append("<td width=\"92px\"></td>");
                driverBuLineHtml.append("<td width=\"50px\"></td>");
                driverBuLineHtml.append("<td width=\"82px\"><input id=\"driverBuLineId"+(2-(3-list.size()-j-1))+"\" name=\"driverBuLineId"+(2-(3-list.size()-j-1))+"\" type=\"hidden\" value=\"\" />");
                driverBuLineHtml.append("<input name=\"\" type=\"button\" value=\"编辑\" onclick=\"queryDriverBuLineById('"+(2-(3-list.size()-j-1))+"','N');\" /></td>");
                driverBuLineHtml.append("</tr>");
            }else {
                driverBuLineHtml.append("<tr>");
                driverBuLineHtml.append("<td width=\"92px\">预约时间</td>");
                driverBuLineHtml.append("<td width=\"220px\"></td>");
                driverBuLineHtml.append("<td width=\"297px\" colspan=\"2\"></td>");
                driverBuLineHtml.append("<td width=\"92px\"></td>");
                driverBuLineHtml.append("<td width=\"45px\"></td>");
                driverBuLineHtml.append("<td width=\"82px\"><input id=\"driverBuLineId"+(2-(3-list.size()-j-1))+"\" name=\"driverBuLineId"+(2-(3-list.size()-j-1))+"\" type=\"hidden\" value=\"\" />");
                driverBuLineHtml.append("<input name=\"\" type=\"button\" value=\"编辑\" onclick=\"queryDriverBuLineById('"+(2-(3-list.size()-j-1))+"','N');\" /></td>");
                driverBuLineHtml.append("</tr>");
            }
        }
        return driverBuLineHtml.toString();
    }

    @Override
    public String queryDriverBusinessLineInfoByDriverId(Integer driverId) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("driverId",driverId);
        queryMap.put("start", Constants.DELETED_FLAG_FALSE);
        List<MarketingDriverBusinessLineDomain> list = marketingDriverBusinessLineDao.queryDriverBusinessLineInfoByDriverId(queryMap);
        StringBuffer driverBuLineHtml = new StringBuffer();
        for(int i = 0;i<list.size();i++) {
            if(i == 0) {
                driverBuLineHtml.append("<tr>");
                driverBuLineHtml.append("<td rowspan=\"3\" style=\"width:40px\">预<br />约<br />信<br />息</td>");
                driverBuLineHtml.append("<td width=\"92px\">预约时间</td>");
                driverBuLineHtml.append("<td width=\"220px\">"+list.get(i).getStartTime()+"-"+list.get(i).getEndTime()+"</td>");
                driverBuLineHtml.append("<td width=\"297px\" colspan=\"2\">");
                if(StringUtils.isNotEmpty(list.get(i).getStartProvince())) {
                    driverBuLineHtml.append(list.get(i).getStartProvince());
                    if (StringUtils.isNotEmpty(list.get(i).getStartCity())) {
                        driverBuLineHtml.append(list.get(i).getStartCity());
                        if (StringUtils.isNotEmpty(list.get(i).getStartCounty())) {
                            driverBuLineHtml.append(list.get(i).getStartCounty());

                        }
                    }
                }
                driverBuLineHtml.append(" —— ");
                if(StringUtils.isNotEmpty(list.get(i).getEndProvince())) {
                    driverBuLineHtml.append(list.get(i).getEndProvince());
                    if (StringUtils.isNotEmpty(list.get(i).getEndCity())) {
                        driverBuLineHtml.append(list.get(i).getEndCity());
                        if (StringUtils.isNotEmpty(list.get(i).getEndCounty())) {
                            driverBuLineHtml.append(list.get(i).getEndCounty());

                        }
                    }
                }
                driverBuLineHtml.append("</td>");
                driverBuLineHtml.append("<td width=\"92px\">");
                if(list.get(i).getQuoteType() != null) {
                    if(Constants.BUSINESS_LINE_ZC_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_ZC_VALUE);
                    }else if(Constants.BUSINESS_LINE_AD_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_AD_VALUE);
                    }else if(Constants.BUSINESS_LINE_AF_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_AF_VALUE);
                    }
                }
                driverBuLineHtml.append("</td>");
                driverBuLineHtml.append("<td width=\"50px\">"+list.get(i).getQuoteFair()+"</td>");
                driverBuLineHtml.append("<td width=\"82px\"><input id=\"driverBuLineId"+i+"\" name=\"driverBuLineId"+i+"\" type=\"hidden\" value=\""+list.get(i).getId()+"\" />");
                driverBuLineHtml.append("<input name=\"\" type=\"button\" value=\"编辑\" onclick=\"queryDriverBuLineById('"+i+"','Y');\" /></td>");
                driverBuLineHtml.append("</tr>");
            }else {
                driverBuLineHtml.append("<tr>");
                driverBuLineHtml.append("<td width=\"92px\">预约时间</td>");
                driverBuLineHtml.append("<td width=\"220px\">"+list.get(i).getStartTime()+"-"+list.get(i).getEndTime()+"</td>");
                driverBuLineHtml.append("<td width=\"297px\" colspan=\"2\">");
                if(StringUtils.isNotEmpty(list.get(i).getStartProvince())) {
                    driverBuLineHtml.append(list.get(i).getStartProvince());
                    if (StringUtils.isNotEmpty(list.get(i).getStartCity())) {
                        driverBuLineHtml.append(list.get(i).getStartCity());
                        if (StringUtils.isNotEmpty(list.get(i).getStartCounty())) {
                            driverBuLineHtml.append(list.get(i).getStartCounty());

                        }
                    }
                }
                driverBuLineHtml.append(" —— ");
                if(StringUtils.isNotEmpty(list.get(i).getEndProvince())) {
                    driverBuLineHtml.append(list.get(i).getEndProvince());
                    if (StringUtils.isNotEmpty(list.get(i).getEndCity())) {
                        driverBuLineHtml.append(list.get(i).getEndCity());
                        if (StringUtils.isNotEmpty(list.get(i).getEndCounty())) {
                            driverBuLineHtml.append(list.get(i).getEndCounty());

                        }
                    }
                }
                driverBuLineHtml.append("</td>");
                driverBuLineHtml.append("<td width=\"92px\">");
                if(list.get(i).getQuoteType() != null) {
                    if(Constants.BUSINESS_LINE_ZC_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_ZC_VALUE);
                    }else if(Constants.BUSINESS_LINE_AD_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_AD_VALUE);
                    }else if(Constants.BUSINESS_LINE_AF_KEY == list.get(i).getQuoteType()) {
                        driverBuLineHtml.append(Constants.BUSINESS_LINE_AF_VALUE);
                    }
                }
                driverBuLineHtml.append("</td>");
                driverBuLineHtml.append("<td width=\"50px\">"+list.get(i).getQuoteFair()+"</td>");
                driverBuLineHtml.append("<td width=\"82px\"><input id=\"driverBuLineId"+i+"\" name=\"driverBuLineId"+i+"\" type=\"hidden\" value=\""+list.get(i).getId()+"\" />");
                driverBuLineHtml.append("<input name=\"\" type=\"button\" value=\"编辑\" onclick=\"queryDriverBuLineById('"+i+"','Y');\" /></td>");
                driverBuLineHtml.append("</tr>");
            }
        }
        for(int j = 0;j<(3-list.size());j++) {
            if(list.size() == 0 && j == 0) {
                driverBuLineHtml.append("<tr>");
                driverBuLineHtml.append("<td rowspan=\"3\" style=\"width:40px\">预<br />约<br />信<br />息</td>");
                driverBuLineHtml.append("<td width=\"92px\">预约时间</td>");
                driverBuLineHtml.append("<td width=\"220px\"></td>");
                driverBuLineHtml.append("<td width=\"297px\" colspan=\"2\"></td>");
                driverBuLineHtml.append("<td width=\"92px\"></td>");
                driverBuLineHtml.append("<td width=\"50px\"></td>");
                driverBuLineHtml.append("<td width=\"82px\"><input id=\"driverBuLineId"+(2-(3-list.size()-j-1))+"\" name=\"driverBuLineId"+(2-(3-list.size()-j-1))+"\" type=\"hidden\" value=\"\" />");
                driverBuLineHtml.append("<input name=\"\" type=\"button\" value=\"编辑\" onclick=\"queryDriverBuLineById('"+(2-(3-list.size()-j-1))+"','Y');\" /></td>");
                driverBuLineHtml.append("</tr>");
            }else {
                driverBuLineHtml.append("<tr>");
                driverBuLineHtml.append("<td width=\"92px\">预约时间</td>");
                driverBuLineHtml.append("<td width=\"220px\"></td>");
                driverBuLineHtml.append("<td width=\"297px\" colspan=\"2\"></td>");
                driverBuLineHtml.append("<td width=\"92px\"></td>");
                driverBuLineHtml.append("<td width=\"50px\"></td>");
                driverBuLineHtml.append("<td width=\"82px\"><input id=\"driverBuLineId"+(2-(3-list.size()-j-1))+"\" name=\"driverBuLineId"+(2-(3-list.size()-j-1))+"\" type=\"hidden\" value=\"\" />");
                driverBuLineHtml.append("<input name=\"\" type=\"button\" value=\"编辑\" onclick=\"queryDriverBuLineById('"+(2-(3-list.size()-j-1))+"','Y');\" /></td>");
                driverBuLineHtml.append("</tr>");
            }
        }
        return driverBuLineHtml.toString();
    }

    @Override
    public MarketingDriverBusinessLineDomain queryMarketingDriverBusinessLineById(Integer id) {
        return marketingDriverBusinessLineDao.queryMarketingDriverBusinessLineById(id);
    }

    @Override
    public MarketingDriverBusinessLineDomain queryDriverBusinessLineInfoById(Integer id) {
        return marketingDriverBusinessLineDao.queryDriverBusinessLineInfoById(id);
    }

    @Override
    public boolean updateMarketingDriverBusinessLine(MarketingDriverBusinessLine marketingDriverBusinessLine) {
        return marketingDriverBusinessLineDao.updateMarketingDriverBusinessLine(marketingDriverBusinessLine);
    }

    @Override
    public boolean updateDriverBusinessLineInfo(DriverBusinessLineInfo driverBusinessLineInfo) {
        return marketingDriverBusinessLineDao.updateDriverBusinessLineInfo(driverBusinessLineInfo);
    }

    @Override
    public Integer addMarketingDriverBusinessLine(MarketingDriverBusinessLine marketingDriverBusinessLine) {
        marketingDriverBusinessLine.setDeleteFlag(Constants.DELETED_FLAG_FALSE);
        return marketingDriverBusinessLineDao.addMarketingDriverBusinessLine(marketingDriverBusinessLine);
    }

    @Override
    public Integer addDriverBusinessLineInfo(DriverBusinessLineInfo driverBusinessLineInfo) {
        driverBusinessLineInfo.setStart(Constants.DELETED_FLAG_FALSE);
        return marketingDriverBusinessLineDao.addDriverBusinessLineInfo(driverBusinessLineInfo);
    }
}
