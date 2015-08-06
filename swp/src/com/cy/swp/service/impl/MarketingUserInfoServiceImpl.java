package com.cy.swp.service.impl;

import javax.annotation.Resource;


import com.cy.swp.common.constants.Constants;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.domain.MarketingUserInfoDomain;
import com.cy.swp.domain.StatisticalAnalyseDomain;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.dao.MarketingUserInfoDao;
import com.cy.swp.service.MarketingUserInfoService;

import java.util.*;

@Service("marketingUserInfoService")
public class MarketingUserInfoServiceImpl implements MarketingUserInfoService{

	@Resource
    private MarketingUserInfoDao marketingUserInfoDao;
	
	public MarketingUserInfo MarketingUserInfoLogin(MarketingUserInfo marketingUserInfo) {
		MarketingUserInfo userInfo = marketingUserInfoDao.queryMarketingUserInfo(marketingUserInfo);
		return userInfo;
	}

    @Override
    public Integer addMarketingUserInfo(MarketingUserInfoDomain marketingUserInfoDomain) {
        return marketingUserInfoDao.addMarketingUserInfo(marketingUserInfoDomain);
    }

    @Override
    public boolean updateMarketingUserInfo(MarketingUserInfoDomain marketingUserInfoDomain) {
        return marketingUserInfoDao.updateMarketingUserInfo(marketingUserInfoDomain);
    }

    @Override
    public MarketingUserInfoDomain queryMarketingUserInfoDomainPagelist(MarketingUserInfoDomain marketingUserInfoDomain) {
        List<MarketingUserInfoDomain> list = new ArrayList<MarketingUserInfoDomain>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",marketingUserInfoDomain.getCode());
        map.put("name",marketingUserInfoDomain.getName());
        map.put("position",marketingUserInfoDomain.getPosition());
        map.put("joinGroup",marketingUserInfoDomain.getJoinGroup());
        map.put("deleteFlag",marketingUserInfoDomain.getDeleteFlag());
        map.put("phoneNumber",marketingUserInfoDomain.getPhoneNumber());
        if(marketingUserInfoDomain.getPageInfo() != null) {
            map.put("pageSize",marketingUserInfoDomain.getPageInfo().getPageSize());
            map.put("curPage",marketingUserInfoDomain.getPageInfo().getPageSize()*(marketingUserInfoDomain.getPageInfo().getCurPage()-1));
            marketingUserInfoDomain.getPageInfo().setCurPageNo(marketingUserInfoDomain.getPageInfo().getCurPage());
            marketingUserInfoDomain.getPageInfo().setTotalRecords(marketingUserInfoDao.queryMarketingUserInfoCount(map));
            list = marketingUserInfoDao.queryMarketingUserInfoDomainPage(map);
        }else {
            list = marketingUserInfoDao.queryMarketingUserInfoDomainList(map);
        }
        marketingUserInfoDomain.setList(list);
        return marketingUserInfoDomain;
    }

    @Override
    public boolean updateUserPassword(MarketingUserInfo marketingUserInfo) {
        return marketingUserInfoDao.updateUserPassword(marketingUserInfo);
    }

    //根据职务position获得人员（暂用于查询营销人员）
    @Override
    public List<MarketingUserInfo> queryByPositionSer(MarketingUserInfo marketingUserInfo) {
        if(marketingUserInfo.getPosition() == null){
            return new ArrayList<MarketingUserInfo>();
        }
        return marketingUserInfoDao.queryByPosition(marketingUserInfo);
    }

    //进入司机统计分析（查询）
    @Override
    public Map<String, Object> enterStatisticalAnalyseServ(MarketingDriverInfoDomain info){
        Map<String, Object> rtMap = new HashMap<String, Object>();
        if(StringUtils.isEmpty(info.getJoinGroup())){
            //初始化查询条件
            info.setJoinGroup("-1");
            //取当前时间
            Date curDate = new Date();
            //查询开始时间
            String startNextDate = DateUtil.formatDate(curDate, "yyyy-MM") + "-01";
            //查询截至时间
            String endNextDate = DateUtil.formatDate(curDate, Constants.DATE_FORMATE_DAY);
            info.setStartNextDate(startNextDate);
            info.setEndNextDate(endNextDate);
        }
        if(StringUtils.isEmpty(info.getStartNextDate())
                || StringUtils.isEmpty(info.getEndNextDate())){
            rtMap.put("result", "1");
            rtMap.put("message", "系统出错，查询条件为空！");
            return rtMap;
        }
        //查询统计分析
        List<StatisticalAnalyseDomain> list = queryStatisticalAnalyseServ(info);
        //获得页面中的html列表内容
        info.setListHtml(getStatisticalAnalyseHtml(list));
        rtMap.put("result", "0");
        rtMap.put("message", "success");
        return rtMap;
    }

    //查询统计分析
    private List<StatisticalAnalyseDomain> queryStatisticalAnalyseServ(MarketingDriverInfoDomain info){
        Map<String, Object> paraMap = new HashMap<String, Object>();
        //职务  查询条件
        paraMap.put("position", Constants.POSITION_LEADER_KEY);
        //分组  查询条件
        paraMap.put("joinGroup", info.getJoinGroup());
        //查询开始时间  查询条件
        paraMap.put("startNextDate", info.getStartNextDate() + " 00:00:00");
        //查询截至时间  查询条件
        paraMap.put("endNextDate", info.getEndNextDate() + " 23:59:59");
        return marketingUserInfoDao.queryStatisticalAnalyse(paraMap);
    }

    //获得页面中的html列表内容
    private String getStatisticalAnalyseHtml(List<StatisticalAnalyseDomain> list){
        String html = "";
        if(list == null || list.size() == 0){
            html += "<tr>";
            html += "<td colspan='7' align='center'>暂无符合条件的数据</td>";
            html += "</tr>";
            return html;
        }
        int customerNumSum = 0;//客户数量合计
        int callOutNumSum = 0;//呼出数量合计
        int validPhoneNumSum = 0;//有效电话量合计
        int driverRegNumSum = 0;//司机注册量合计
        int driverAuthNumSum = 0;//司机认证量合计
        StatisticalAnalyseDomain item = null;
        String name = "";//成员姓名
        html += "<tbody>";
        for(int i = 0;i < list.size();i++){
            item = list.get(i);
            customerNumSum += item.getCustomerNum();
            callOutNumSum += item.getCallOutNum();
            validPhoneNumSum += item.getValidPhoneNum();
            driverRegNumSum += item.getDriverRegNum();
            driverAuthNumSum += item.getDriverAuthNum();
            //判断是否是组长
            if ( Constants.POSITION_LEADER_KEY == item.getPosition() ) {
                name = item.getName() + "(组长)";
            } else {
                name = item.getName();
            }
            html += "<tr align=\"center\">";
            html += getTdHtml(i + 1);//序号
            html += getTdHtml(name);//成员名称
            html += getTdHtml(item.getCustomerNum());//客户数量
            html += getTdHtml(item.getCallOutNum());//呼出数量
            html += getTdHtml(item.getValidPhoneNum());//有效电话量
            html += getTdHtml(item.getDriverRegNum());//司机注册量
            html += getTdHtml(item.getDriverAuthNum());//司机认证量
            html += "</tr>";
        }
        html += "</tbody>";
        html += "<tfoot>";
        html += "<tr align=\"center\">";
        html += "<td colspan='2'>合计</td>";
        html += getTdHtml(customerNumSum);
        html += getTdHtml(callOutNumSum);
        html += getTdHtml(validPhoneNumSum);
        html += getTdHtml(driverRegNumSum);
        html += getTdHtml(driverAuthNumSum);
        html += "</tr>";
        html += "</tfoot>";
        return html;
    }

    private String getTdHtml(Object val){
        return "<td title='" + (val == null ? "" : val.toString()) + "'>" + (val == null ? "" : val.toString()) + "</td>";
    }
}
