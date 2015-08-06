package com.cy.swp.service.impl;

import com.cy.swp.bo.MarketingMaintainRecord;
import com.cy.swp.dao.MarketingMaintainRecordDao;
import com.cy.swp.domain.MarketingMaintainRecordDomain;
import com.cy.swp.service.MarketingMaintainRecordService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户维护记录ServiceImpl
 * Created by nixianjing on 14/12/10.
 */
@Service("marketingMaintainRecordServiceImpl")
public class MarketingMaintainRecordServiceImpl implements MarketingMaintainRecordService {


    @Resource
    private MarketingMaintainRecordDao marketingMaintainRecordDao;


    @Override
    public String getMarketingMaintainRecordHtml(List<MarketingMaintainRecordDomain> list,Integer curPage,Integer pageSize) {
        StringBuffer recordHtml = new StringBuffer();
        recordHtml.append("<thead>");
        recordHtml.append("<tr>");
        recordHtml.append("<td width='50'>序号</td>");
        recordHtml.append("<td>记录人</td>");
        recordHtml.append("<td width='300'>记录内容</td>");
        recordHtml.append("<td>客户类别</td>");
        recordHtml.append("<td>记录时间</td>");
        recordHtml.append("<td width='90'>记录类型</td>");
        recordHtml.append("<td>录音</td>");
        recordHtml.append("</tr>");
        recordHtml.append("</thead>");
        Integer b = ((curPage.intValue()-1)*pageSize);
        for (int i = 0;i < list.size();i++) {
            recordHtml.append("<tr>");
            recordHtml.append("<td>"+(b.intValue()+i+1)+"</td>");
            recordHtml.append("<td>"+list.get(i).getAssisterName()+"</td>");
            recordHtml.append("<td style=\" overflow:hidden; word-break: break-all;\">"+list.get(i).getRecordContent()+"</td>");
            if(list.get(i).getCategory() != null) {
                recordHtml.append("<td>"+list.get(i).getCategory()+"类</td>");
            }else {
                recordHtml.append("<td></td>");
            }
            recordHtml.append("<td>"+list.get(i).getActionTime()+"</td>");
            if(list.get(i).getMaintainAction() != null) {
                if(list.get(i).getMaintainAction() == 1) {
                    recordHtml.append("<td>电话</td>");
                }else if(list.get(i).getMaintainAction() == 2) {
                    recordHtml.append("<td>发送短信</td>");
                }else if(list.get(i).getMaintainAction() == 3) {
                    recordHtml.append("<td>修改信息</td>");
                }else if(list.get(i).getMaintainAction() == 4){
                    recordHtml.append("<td>推送信息</td>");
                }
            }else {
                recordHtml.append("<td></td>");
            }
            if(StringUtils.isNotEmpty(list.get(i).getRecordFilePath())) {
                recordHtml.append("<td>录音</td> ");
            }else {
                recordHtml.append("<td></td> ");
            }
            recordHtml.append("</tr>");
        }
        return recordHtml.toString();
    }

    @Override
    public MarketingMaintainRecordDomain queryMarketingMaintainRecordDomainList(MarketingMaintainRecordDomain marketingMaintainRecordDomain) {
        List<MarketingMaintainRecordDomain> list = new ArrayList<MarketingMaintainRecordDomain>();
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("customerKind","2");
        queryMap.put("customerId",marketingMaintainRecordDomain.getCustomerId());
        if(marketingMaintainRecordDomain.getPageInfo() != null) {//分页查询
            queryMap.put("pageSize",marketingMaintainRecordDomain.getPageInfo().getPageSize());
            queryMap.put("curPage",marketingMaintainRecordDomain.getPageInfo().getPageSize()*(marketingMaintainRecordDomain.getPageInfo().getCurPage()-1));
            marketingMaintainRecordDomain.getPageInfo().setCurPageNo(marketingMaintainRecordDomain.getPageInfo().getCurPage());
            marketingMaintainRecordDomain.getPageInfo().setTotalRecords(marketingMaintainRecordDao.queryMarketingMaintainRecordDomainCount(queryMap));
            list = marketingMaintainRecordDao.queryMarketingMaintainRecordDomainList(queryMap);
        }else {//查询所有
            list = marketingMaintainRecordDao.queryMarketingMaintainRecordDomainList(queryMap);
        }
        marketingMaintainRecordDomain.setList(list);
        return marketingMaintainRecordDomain;
    }

    @Override
    public Integer addMarketingMaintainRecord(MarketingMaintainRecord marketingMaintainRecord) {
        return marketingMaintainRecordDao.addMarketingMaintainRecord(marketingMaintainRecord);
    }
}
