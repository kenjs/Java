package com.cy.swp.service.impl;

import com.cy.swp.bo.DaySendMsgcounter;
import com.cy.swp.bo.NoteSendRecord;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.dao.MatchingDao;
import com.cy.swp.domain.MatchingDriverInfoDomain;
import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.service.MarketingNoteRecordService;
import com.cy.swp.service.MatchingService;
import com.cy.swp.service.NoteSendRecoreService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nixianjing on 15/1/6.
 */
@Service("matchingService")
public class MatchingServiceImpl implements MatchingService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MatchingDao matchingDao;

    @Resource
    private MarketingNoteRecordService marketingNoteRecordService;

    @Resource
    private NoteSendRecoreService noteSendRecoreService;





    /**********************************************车辆匹配货源*****************************************/
    @Override
    public List<OrderCargoInfoDomain> queryCargoByBusinessLine(Integer drvierId, String notCargoId) {
        return null;
    }

    @Override
    public List<OrderCargoInfoDomain> queryCargoByMarkBusinessLine(Integer markDriverId, String notCargoId) {
        return null;
    }

    @Override
    public List<OrderCargoInfoDomain> queryCargoByLineLocation(Integer drvierId, String notCargoId) {
        return null;
    }

    @Override
    public List<OrderCargoInfoDomain> queryCargoByLine(Integer drvierId, String notCargoId) {
        return null;
    }

    @Override
    public List<OrderCargoInfoDomain> queryCargoByMarkLine(Integer markDriverId, String notCargoId) {
        return null;
    }

    @Override
    public List<OrderCargoInfoDomain> queryCargoByLocation(Integer drvierId, String notCargoId) {
        return null;
    }

    @Override
    public List<OrderCargoInfoDomain> queryCargoByOftenCity(Integer markDriverId, String notCargoId) {
        return null;
    }


    /**
     * 抓取货源发送短信或推送内部接口
     * @return
     */
    @Override
    public boolean matchingDriverNote() {
        boolean result = false;
        List<OrderCargoInfoDomain> orderList = new ArrayList<OrderCargoInfoDomain>();
        orderList = matchingDao.queryOrderCargoList();
        StringBuffer cargoStr = new StringBuffer();
        for(int i = 0;i < orderList.size();i++) {
            if(i == (orderList.size()-1)) {
                cargoStr.append(orderList.get(i).getId());
            }else {
                cargoStr.append(orderList.get(i).getId()+",");
            }
            /**
             * 发送或推送司机信息
             */
            List<MatchingDriverInfoDomain> driverlist = new ArrayList<MatchingDriverInfoDomain>();
            //注册用户定位时间小于24（推送）
            List<MatchingDriverInfoDomain> driverlist1 = matchingRegistrationDriverUnionPush(orderList.get(i));
            //注册用户定位时间大于24（短信）
            List<MatchingDriverInfoDomain> driverlist2 = matchingRegistrationDriverUnionNote(orderList.get(i));
            //未注册用户（短信）
            List<MatchingDriverInfoDomain> driverlist3 = matchingNotRegistrationDriverUnionNote(orderList.get(i));
            driverlist.addAll(driverlist1);
            driverlist.addAll(driverlist2);
            /**
             * 发送企业信息
             */
            if(driverlist.size() > 0) {
                Map<String,String> companyMap = new HashMap<String, String>();
                companyMap.put("type", "0");
                companyMap.put("telephone", orderList.get(i).getContactMobilephone());
                companyMap.put("useFor","2");
                List<NoteSendRecord> companyList = noteSendRecoreService.selectNoteSendRecore(companyMap);
                if(companyList.size() == 0){
                    //发送短信给企业
                    StringBuilder compayNumber = new StringBuilder();
                    compayNumber.append("【快到网】");
                    int b = 0;
                    for(int j = 0;i<driverlist.size();j++) {
                        if(StringUtils.isNotEmpty(driverlist.get(j).getCarNumber())) {
                            if(b == 3) {
                                break;
                            }
                            compayNumber.append(driverlist.get(j).getCarNumber());
                            b++;
                            if(b<3) {
                                compayNumber.append("、");
                            }
                        }
                    }

                    compayNumber.append("符合您的找车要求，");
                    compayNumber.append("详情请登录");
                    compayNumber.append("www.56top.cn");
                    compayNumber.append("按车牌号查看联系方式");
                    /**
                     * 发送短信
                     * @param type 发送对象类别 0企业 1司机
                     * @param remark 备注
                     * @param telephone 发送手机
                     * @param content 发送内容
                     * @param useFor 用途：
                     *               1 导入货源配车有司机发送给企业的短信
                     *               2 导入货源配车无司机发送给企业的短信
                     *               3 营销专员联系客户时货源推送短信
                     *               4 营销专员主动给司机发短信
                     *               5 导入货源配车发送给司机的短信
                     * @return
                     */
                    if(b != 0) {
                        result = marketingNoteRecordService.sendTextMessageRecord("0", "导入货源匹配车辆发送给企业1天一条", orderList.get(i).getContactMobilephone(), compayNumber.toString(), "2");
                    }
                }
            }else {
                Map<String,String> companyMap = new HashMap<String, String>();
                companyMap.put("type", "0");
                companyMap.put("telephone", orderList.get(i).getContactMobilephone());
                companyMap.put("useFor","2");
                List<NoteSendRecord> companyList = noteSendRecoreService.selectNoteSendRecoreUseFor(companyMap);
                String companyNote = "【快到网】您的司机朋友推荐您登录www.56top.cn发货配车，现在就去试试吧！";
                if(companyList.size() > 0) {
                    if(Integer.parseInt(companyList.get(0).getCreateTime()) > 3) {
                        result = marketingNoteRecordService.sendTextMessageRecord("0", "导入货源匹配车辆发送给企业3天一条", orderList.get(i).getContactMobilephone(), companyNote, "2");
                    }
                } else {
                    result = marketingNoteRecordService.sendTextMessageRecord("0", "导入货源匹配车辆发送给企业3天一条", orderList.get(i).getContactMobilephone(), companyNote, "2");
                }
            }
        }
        /**
         * 批量修改
         */
        if(orderList.size()>0) {
            String[] listStr = cargoStr.toString().split(",");
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("list",listStr);
            Integer updateCargo = matchingDao.updateOrderCargo(map);
            if(updateCargo >0 ) {
                result = true;
            }
        }else {
            result = true;
        }
        return result;
    }

    /***********************************UNION查询*************************************/
    /**
     * 注册司机推送 UNION 规则
     * @param orderCargoInfoDomain
     * @return
     */
    public List<MatchingDriverInfoDomain> matchingRegistrationDriverUnionPush(OrderCargoInfoDomain orderCargoInfoDomain) {
        boolean result = false;
        int pageSize = 3; //匹配车辆条数默认值
        /**
         * 1表示已注册用户
         * 0表示推送
         * 5表示最大推送五条
         * 90表示时间间隔：90分钟内只能推送一条
         */
        List<DaySendMsgcounter> sendList = queryDaySendMsgcounterList(1, 0, 5, 90);
        //默认值加上不需要发送的数量值
        pageSize += sendList.size();
        //创建车辆集合
        List<MatchingDriverInfoDomain> list = new ArrayList<MatchingDriverInfoDomain>();

        list = queryRegDriverUnionlist(orderCargoInfoDomain, pageSize, "0");
        /****************去重递归递减***************/
        List<MatchingDriverInfoDomain> drvielist =  getlist(list,sendList);
        /****************发送短信***************/
        for(int i = 0;i<drvielist.size();i++) {
            if(i == 3) {
                break;
            }
            /**
             * @param driverId 司机id
             * @param title 推送标题
             * @param message 推送内容
             * @param id  自定义通知内容，选输项(不在通知栏显示，APP使用)Id
             * @param type 类型
             * @return nxj
             */
            result = marketingNoteRecordService.setTextMessagePush(String.valueOf(drvielist.get(i).getId()),"【快到网】提醒您！","已帮您找到优质货源，请点击查看！",String.valueOf(orderCargoInfoDomain.getId()),"0", Constants.SP_KEY_PASSIVE_MARKET);
            if(result) {
                DaySendMsgcounter daySendMsgcounter = null;
                daySendMsgcounter = queryDaySendMsgcounter(drvielist.get(i).getId(),1,0);
                if(daySendMsgcounter == null) {//新增
                    result = addDaySendMsgcounter(drvielist.get(i).getId(),1,0);
                }else {//修改
                    result = updateDaySendMsgcounter(drvielist.get(i).getId(),1,0);
                }
            }
        }
        return drvielist;
    }
    /**
     * 注册司机短信 UNION 规则
     * @param orderCargoInfoDomain
     * @return
     */
    public List<MatchingDriverInfoDomain> matchingRegistrationDriverUnionNote(OrderCargoInfoDomain orderCargoInfoDomain) {
        boolean result = false;
        int pageSize = 3; //匹配车辆条数默认值
        //短信内容
        StringBuilder driverContent = new StringBuilder();
        String companyName = orderCargoInfoDomain.getCompanyName() + "公司";
        driverContent.append("【快到网】");
        if(companyName.length() > 8) {
            companyName = companyName.substring(0,6) + "公司";
        }
        if(companyName.contains("公司公司")) {
            companyName = companyName.replace("公司公司", "公司");
        }
        driverContent.append(companyName);
        driverContent.append(orderCargoInfoDomain.getStartCity().replaceAll("市",""));
        driverContent.append("到");
        driverContent.append(orderCargoInfoDomain.getEndCity().replaceAll("市",""));
        if(orderCargoInfoDomain.getCargoWeight() != 0) {
            driverContent.append(orderCargoInfoDomain.getCargoWeight()).append("吨，");
        }else {
            if(orderCargoInfoDomain.getCargoCubage() != 0) {
                driverContent.append(orderCargoInfoDomain.getCargoCubage()).append("方，");
            }
        }
        driverContent.append("符合您的运营线路，打开快到网查收，重新安装点击 http://t.cn/RZVWDDk ");
        /**
         * 1表示已注册用户
         * 0表示推送
         * 5表示最大推送五条
         * 90表示时间间隔：90分钟内只能推送一条
         */
        List<DaySendMsgcounter> sendList = queryDaySendMsgcounterList(1, 1, 3, 90);
        //默认值加上不需要发送的数量值
        pageSize += sendList.size();
        //创建车辆集合
        List<MatchingDriverInfoDomain> list = new ArrayList<MatchingDriverInfoDomain>();

        list = queryRegDriverUnionlist(orderCargoInfoDomain, pageSize, "1");
        /****************去重递归递减***************/
        List<MatchingDriverInfoDomain> drvielist =  getlist(list,sendList);
        /****************发送短信***************/
        for(int i = 0;i<drvielist.size();i++) {
            if(i == 3) {
                break;
            }
            /**
             * 发送短信
             * @param type 发送对象类别 0企业 1司机
             * @param remark 备注
             * @param telephone 发送手机
             * @param content 发送内容
             * @param useFor 用途：1 导入货源配车有司机发送给企业的短信
             *               2 导入货源配车无司机发送给企业的短信
             *               3 营销专员联系客户时货源推送短信
             *               4 营销专员主动给司机发短信
             *               5 导入货源配车发送给司机的短信
             * @return
             */
            result = marketingNoteRecordService.sendTextMessageRecord("1", "货源匹配车辆发送短信已注册用户", drvielist.get(i).getCode(), driverContent.toString(),"5");
            if(result) {
                DaySendMsgcounter daySendMsgcounter = null;
                daySendMsgcounter = queryDaySendMsgcounter(drvielist.get(i).getId(),1,1);
                if(daySendMsgcounter == null) {//新增
                    result = addDaySendMsgcounter(drvielist.get(i).getId(),1,1);
                }else {//修改
                    result = updateDaySendMsgcounter(drvielist.get(i).getId(),1,1);
                }
            }
        }
        return drvielist;
    }
    /**
     * 未注册司机短信 UNION 规则
     * @param orderCargoInfoDomain
     * @return
     */
    public List<MatchingDriverInfoDomain> matchingNotRegistrationDriverUnionNote(OrderCargoInfoDomain orderCargoInfoDomain) {
        boolean result = false;
        int pageSize = 3; //匹配车辆条数默认值

        //短信内容
        StringBuilder driverContent = new StringBuilder();
        String companyName = orderCargoInfoDomain.getCompanyName() + "公司";
        driverContent.append("【快到网】");
        if(companyName.length() > 8) {
            companyName = companyName.substring(0,6) + "公司";
        }
        if(companyName.contains("公司公司")) {
            companyName = companyName.replace("公司公司", "公司");
        }
        driverContent.append(companyName);
        driverContent.append(orderCargoInfoDomain.getStartCity().replaceAll("市",""));
        driverContent.append("到");
        driverContent.append(orderCargoInfoDomain.getEndCity().replaceAll("市",""));
        if(orderCargoInfoDomain.getCargoWeight() != 0) {
            driverContent.append(orderCargoInfoDomain.getCargoWeight()).append("吨，");
        }else {
            if(orderCargoInfoDomain.getCargoCubage() != 0) {
                driverContent.append(orderCargoInfoDomain.getCargoCubage()).append("方，");
            }
        }
        driverContent.append("符合您的运营线路，打开快到网查收，重新安装点击 http://t.cn/RZVWDDk");
        /**
         * 2表示未注册用户
         * 1表示短信
         * 3表示最大发送3条
         * 90表示时间间隔：90分钟内只能推送一条
         */
        List<DaySendMsgcounter> sendList = queryDaySendMsgcounterList(2, 1, 3, 90);
        //默认值加上不需要发送的数量值
        pageSize += sendList.size();
        //创建车辆集合
        List<MatchingDriverInfoDomain> list = new ArrayList<MatchingDriverInfoDomain>();
        list = queryNotRegDriverUnionlist(orderCargoInfoDomain, pageSize);
        /****************去重递归递减***************/
        List<MatchingDriverInfoDomain> drvielist =  getlist(list,sendList);
        /****************发送短信***************/
        for(int i = 0;i<drvielist.size();i++) {
            if(i == 3) {
                break;
            }
            /**
             * 发送短信
             * @param type 发送对象类别 0企业 1司机
             * @param remark 备注
             * @param telephone 发送手机
             * @param content 发送内容
             * @param useFor 用途：1 导入货源配车有司机发送给企业的短信
             *               2 导入货源配车无司机发送给企业的短信
             *               3 营销专员联系客户时货源推送短信
             *               4 营销专员主动给司机发短信
             *               5 导入货源配车发送给司机的短信
             * @return
             */
            result = marketingNoteRecordService.sendTextMessageRecord("1", "货源匹配车辆发送短信已注册用户", drvielist.get(i).getCode(), driverContent.toString(),"5");
            if(result) {
                DaySendMsgcounter daySendMsgcounter = null;
                daySendMsgcounter = queryDaySendMsgcounter(drvielist.get(i).getId(),2,1);
                if(daySendMsgcounter == null) {//新增
                    result = addDaySendMsgcounter(drvielist.get(i).getId(),2,1);
                }else {//修改
                    result = updateDaySendMsgcounter(drvielist.get(i).getId(),2,1);
                }
            }
        }
        return drvielist;
    }
    /***********************************分布查询*************************************/
    /**
     * 注册司机推送
     * @param orderCargoInfoDomain
     * @return
     */
    @Override
    public boolean matchingRegistrationDriverPush(OrderCargoInfoDomain orderCargoInfoDomain) {
        boolean result = false;
        int pageSize = 3; //匹配车辆条数默认值
        int pageSizeCount = 0;
        String notDriverId = null;
        /**
         * 1表示已注册用户
         * 0表示推送
         * 5表示最大推送五条
         * 1表示时间间隔：1个小时内只能推送一条
         */
        List<DaySendMsgcounter> sendList = queryDaySendMsgcounterList(1, 0, 5, 1);

        //默认值加上不需要发送的数量值
        pageSize += sendList.size();

        //创建车辆集合
        List<MatchingDriverInfoDomain> list = new ArrayList<MatchingDriverInfoDomain>();

        /****************匹配规则***************/
        //规则1
        List<MatchingDriverInfoDomain> listone = queryDriverByBusinessLine(orderCargoInfoDomain,notDriverId,pageSize,"0");
        list.addAll(listone);
        if(list.size() < pageSize) {
            pageSizeCount = pageSize - list.size();
            notDriverId = getQueryHqlId(list);
        }
        //规则2
        List<MatchingDriverInfoDomain> listtwo = queryDriverByLineLocation(orderCargoInfoDomain,notDriverId,pageSizeCount,"0");
        list.addAll(listtwo);
        if(list.size() < pageSize) {
            pageSizeCount = pageSize - list.size();
            notDriverId = getQueryHqlId(list);
        }
        //规则3
        List<MatchingDriverInfoDomain> listthree = queryDriverByLine(orderCargoInfoDomain, notDriverId, pageSizeCount, "0");
        list.addAll(listthree);
        if(list.size() < pageSize) {
            pageSizeCount = pageSize - list.size();
            notDriverId = getQueryHqlId(list);
        }
        //规则4
        List<MatchingDriverInfoDomain> listfour = queryDriverByLocation(orderCargoInfoDomain, notDriverId, pageSizeCount, "0");
        list.addAll(listfour);
        /****************去重递归递减***************/
        List<MatchingDriverInfoDomain> drvielist =  getlist(list,sendList);
        /****************发送短信***************/
        for(int i = 0;i<drvielist.size();i++) {
            if(i == 3) {
                break;
            }



            DaySendMsgcounter daySendMsgcounter = null;
            daySendMsgcounter = queryDaySendMsgcounter(drvielist.get(i).getId(),1,0);
            if(daySendMsgcounter == null) {//新增
                result = addDaySendMsgcounter(drvielist.get(i).getId(),1,0);
            }else {//修改
                result = updateDaySendMsgcounter(drvielist.get(i).getId(),1,0);
            }

        }
        return result;
    }

    /**
     * 注册司机短信
     * @param orderCargoInfoDomain
     * @return
     */
    @Override
    public boolean matchingRegistrationDriverNote(OrderCargoInfoDomain orderCargoInfoDomain) {
        boolean result = false;
        int pageSize = 3; //匹配车辆条数默认值
        int pageSizeCount = 0;
        String notDriverId = null;
        /**
         * 1表示已注册用户
         * 1表示短信
         * 3表示最大发送三条单向
         * 2表示时间间隔：2个小时内只能发送一条
         */
        List<DaySendMsgcounter> sendList = queryDaySendMsgcounterList(1, 1, 3, 2);

        //默认值加上不需要发送的数量值
        pageSize += sendList.size();

        //创建车辆集合
        List<MatchingDriverInfoDomain> list = new ArrayList<MatchingDriverInfoDomain>();

        /****************匹配规则***************/
        //规则1
        List<MatchingDriverInfoDomain> listone = queryDriverByBusinessLine(orderCargoInfoDomain,notDriverId,pageSize,"1");
        list.addAll(listone);
        if(list.size() < pageSize) {
            pageSizeCount = pageSize - list.size();
            notDriverId = getQueryHqlId(list);
        }
        //规则2
        List<MatchingDriverInfoDomain> listtwo = queryDriverByLineLocation(orderCargoInfoDomain,notDriverId,pageSizeCount,"1");
        list.addAll(listtwo);
        if(list.size() < pageSize) {
            pageSizeCount = pageSize - list.size();
            notDriverId = getQueryHqlId(list);
        }
        //规则3
        List<MatchingDriverInfoDomain> listthree = queryDriverByLine(orderCargoInfoDomain, notDriverId, pageSizeCount, "1");
        list.addAll(listthree);
        if(list.size() < pageSize) {
            pageSizeCount = pageSize - list.size();
            notDriverId = getQueryHqlId(list);
        }
        //规则4
        List<MatchingDriverInfoDomain> listfour = queryDriverByLocation(orderCargoInfoDomain, notDriverId, pageSizeCount, "1");
        list.addAll(listfour);
        /****************去重递归递减***************/
        List<MatchingDriverInfoDomain> drvielist =  getlist(list,sendList);
        /****************发送短信***************/
        for(int i = 0;i<drvielist.size();i++) {
            if(i == 3) {
                break;
            }

        }
        return result;
    }

    /**
     * 未注册司机短信
     * @param orderCargoInfoDomain
     * @return
     */
    @Override
    public boolean matchingNotRegistrationDriverNote(OrderCargoInfoDomain orderCargoInfoDomain) {
        boolean result = false;
        int pageSize = 3; //匹配车辆条数默认值
        int pageSizeCount = 0;
        String notMarkUserId = null;
        /**
         * 2表示未注册用户
         * 1表示短信
         * 3表示最大发送三条单向
         * 2表示时间间隔：2个小时内只能发送一条
         */
        List<DaySendMsgcounter> sendList = queryDaySendMsgcounterList(2, 1, 3, 2);

        //默认值加上不需要发送的数量值
        pageSize += sendList.size();

        //创建车辆集合
        List<MatchingDriverInfoDomain> list = new ArrayList<MatchingDriverInfoDomain>();

        /****************匹配规则***************/
        //规则1
        List<MatchingDriverInfoDomain> listone = queryDriverByMarkBusinessLine(orderCargoInfoDomain,notMarkUserId,pageSize);
        list.addAll(listone);
        if(list.size() < pageSize) {
            pageSizeCount = pageSize - list.size();
            notMarkUserId = getQueryHqlId(list);
        }
        //规则2
        List<MatchingDriverInfoDomain> listtwo = queryDriverByLine(orderCargoInfoDomain,notMarkUserId,pageSizeCount);
        list.addAll(listtwo);
        if(list.size() < pageSize) {
            pageSizeCount = pageSize - list.size();
            notMarkUserId = getQueryHqlId(list);
        }
        //规则3
        List<MatchingDriverInfoDomain> listthree = queryDriverByOftenCity(orderCargoInfoDomain, notMarkUserId, pageSizeCount);
        list.addAll(listthree);
        /****************去重递归递减***************/
        List<MatchingDriverInfoDomain> drvielist =  getlist(list,sendList);
        /****************发送短信***************/
        for(int i = 0;i<drvielist.size();i++) {
            if(i == 3) {
                break;
            }

        }
        return result;
    }


    /**********************************************（导入或爬虫）货源匹配车辆（未注册）*****************************************/
    /**
     * 匹配规则：预约线路起始地目的地符合装货地卸货地，并且预约时间在装货时间内
     * @param orderCargoInfoDomain 货源对象
     * @param notMarkUserId 排除的客户司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    @Override
    public List<MatchingDriverInfoDomain> queryDriverByMarkBusinessLine(OrderCargoInfoDomain orderCargoInfoDomain, String notMarkUserId, Integer pageSize) {
        StringBuffer sql = new StringBuffer();
        sql.append("select DISTINCT ");
        sql.append(" t.id,t.mobile_phone as code,t.name,t.car_number,'0' as markType ");
        sql.append(" from t_marketing_driver_info t,t_marketing_driver_business_line t1 ");
        sql.append(" where t.id=t1.customer_driver_id and t.driver_id is null ");
        sql.append(" and DATE_FORMAT(t1.end_time,'%Y-%m-%d')>=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') ");
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince())) {
            sql.append(" and t1.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
            sql.append(" and t1.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
            sql.append(" and t1.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
            sql.append(" and t1.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getRequestStartTime())) {
            sql.append(" and DATE_FORMAT(t1.start_time,'%Y-%m-%d') <= DATE_FORMAT('"+orderCargoInfoDomain.getRequestStartTime()+"','%Y-%m-%d') ");
            sql.append(" and DATE_FORMAT(t1.end_time,'%Y-%m-%d') >= DATE_FORMAT('"+orderCargoInfoDomain.getRequestStartTime()+"','%Y-%m-%d') ");
        }
        if(StringUtils.isNotEmpty(notMarkUserId)) {
            sql.append(" and t.id not exists ("+notMarkUserId+") ");
        }
        sql.append(" ORDER BY t.last_contact_date ASC LIMIT 0,"+pageSize);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hsql", sql.toString());
        return matchingDao.queryMatchingDriverList(map);
    }

    /**
     * 匹配规则：运营线路起始地目的地来回之一符合装货地卸货地
     * @param orderCargoInfoDomain 货源对象
     * @param notMarkUserId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    @Override
    public List<MatchingDriverInfoDomain> queryDriverByLine(OrderCargoInfoDomain orderCargoInfoDomain, String notMarkUserId, Integer pageSize) {
        StringBuffer sql = new StringBuffer();
        sql.append("select DISTINCT ");
        sql.append(" t.id,t.mobile_phone as code,t.name,t.car_number,'1' as markType ");
        sql.append(" from t_marketing_driver_info t,t_marketing_driver_line t1 ");
        sql.append(" where t.id=t1.customer_driver_id and t.driver_id is null ");
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) && StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
            sql.append(" and ((");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" (t1.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t1.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t1.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t1.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ) or (");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" (t1.end_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t1.end_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t1.start_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t1.start_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ))");
        }else {
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" and t1.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t1.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t1.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t1.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
        }
        if(StringUtils.isNotEmpty(notMarkUserId)) {
            sql.append(" and t.id not exists ("+notMarkUserId+") ");
        }
        sql.append(" ORDER BY t.last_contact_date ASC LIMIT 0,"+pageSize);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hsql", sql.toString());
        return matchingDao.queryMatchingDriverList(map);
    }

    /**
     * 匹配规则 常跑城市(货源开始地)
     * @param orderCargoInfoDomain 货源对象
     * @param notMarkUserId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    @Override
    public List<MatchingDriverInfoDomain> queryDriverByOftenCity(OrderCargoInfoDomain orderCargoInfoDomain, String notMarkUserId, Integer pageSize) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sPP = new StringBuffer();
        sql.append("select DISTINCT ");
        sql.append(" t.id,t.mobile_phone as code,t.name,t.car_number,'2' as markType ");
        sql.append(" from t_marketing_driver_info t");
        sql.append(" where t.driver_id is null ");
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince())) {
            sPP.append(orderCargoInfoDomain.getStartProvince());
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sPP.append("-"+orderCargoInfoDomain.getStartCity());
            }
        }
        if(StringUtils.isNotEmpty(sPP.toString())) {
            sql.append(" and (");
            sql.append(" t.often_city1 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city2 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city3 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city4 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city5 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city6 like ('"+sPP.toString()+"') ");
            sql.append(" ) ");
        }
        if(StringUtils.isNotEmpty(notMarkUserId)) {
            sql.append(" and t.id not exists ("+notMarkUserId+") ");
        }
        sql.append(" ORDER BY t.last_contact_date ASC LIMIT 0,"+pageSize);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hsql", sql.toString());
        return matchingDao.queryMatchingDriverList(map);
    }
    /**********************************************（导入或爬虫）货源匹配车辆(已注册)*****************************************/

    /**
     * 匹配规则：预约线路起始地目的地符合装货地卸货地，并且预约时间在装货时间内
     * @param orderCargoInfoDomain 货源对象
     * @param notDriverId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    @Override
    public List<MatchingDriverInfoDomain> queryDriverByBusinessLine(OrderCargoInfoDomain orderCargoInfoDomain, String notDriverId,Integer pageSize,String msgType) {
        StringBuffer sql = new StringBuffer();
        sql.append("select DISTINCT ");
        sql.append(" t1.id,t1.code,t1.name,t1.car_number,TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) as locadate,'0' as markType ");
        sql.append(" from t_driver_business_line_info t,t_driver_user_info t1,t_location_collect_last_info t2 ");
        sql.append(" where t.driver_id = t1.id and t2.driver_id = t1.id ");
        sql.append(" and DATE_FORMAT(t.end_time,'%Y-%m-%d')>=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') ");
        if("0".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) <= 24 ");
        }else if("1".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) > 24 ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince())) {
            sql.append(" and t.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
            sql.append(" and t.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
            sql.append(" and t.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
            sql.append(" and t.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getRequestStartTime())) {
            sql.append(" and DATE_FORMAT(t.start_time,'%Y-%m-%d') <= DATE_FORMAT('"+orderCargoInfoDomain.getRequestStartTime()+"','%Y-%m-%d') ");
            sql.append(" and DATE_FORMAT(t.end_time,'%Y-%m-%d') >= DATE_FORMAT('"+orderCargoInfoDomain.getRequestStartTime()+"','%Y-%m-%d') ");
        }
        if(StringUtils.isNotEmpty(notDriverId)) {
            sql.append(" and t1.id not exists ("+notDriverId+") ");
        }
        sql.append(" ORDER BY t2.modify_time DESC LIMIT 0,"+pageSize);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hsql", sql.toString());
        return matchingDao.queryMatchingDriverList(map);
    }

    /**
     * 匹配规则：运营线路起始地目的地来回之一符合装货地卸货地，并且24h内定位信息在装货地
     * @param orderCargoInfoDomain 货源对象
     * @param notDriverId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    @Override
    public List<MatchingDriverInfoDomain> queryDriverByLineLocation(OrderCargoInfoDomain orderCargoInfoDomain, String notDriverId,Integer pageSize,String msgType) {
        StringBuffer sql = new StringBuffer();
        sql.append("select DISTINCT ");
        sql.append(" t1.id,t1.code,t1.name,t1.car_number,TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) as locadate,'0' as markType ");
        sql.append(" from t_driver_line_info t,t_driver_user_info t1,t_location_collect_last_info t2 ");
        sql.append(" where t.driver_id = t1.id and t2.driver_id = t1.id ");
        if("0".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) <= 24 ");
        }else if("1".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) > 24 ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) && StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
            sql.append(" and ((");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" (t.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ) or (");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" (t.end_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t.end_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t.start_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t.start_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ))");
        }else {
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" and t.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
        }
        if(StringUtils.isNotEmpty(notDriverId)) {
            sql.append(" and t1.id not exists ("+notDriverId+") ");
        }
        sql.append(" ORDER BY t2.modify_time DESC LIMIT 0,"+pageSize);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hsql", sql.toString());
        return matchingDao.queryMatchingDriverList(map);
    }

    /**
     * 匹配规则：运营线路起始地目的地来回之一符合装货地卸货地
     * @param orderCargoInfoDomain 货源对象
     * @param notDriverId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    @Override
    public List<MatchingDriverInfoDomain> queryDriverByLine(OrderCargoInfoDomain orderCargoInfoDomain, String notDriverId,Integer pageSize,String msgType) {
        StringBuffer sql = new StringBuffer();
        sql.append("select DISTINCT ");
        sql.append(" t1.id,t1.code,t1.name,t1.car_number,TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) as locadate,'0' as markType ");
        sql.append(" from t_driver_line_info t,t_driver_user_info t1,t_location_collect_last_info t2 ");
        sql.append(" where t.driver_id = t1.id and t2.driver_id = t1.id ");
        if("0".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) <= 24 ");
        }else if("1".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) > 24 ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) && StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
            sql.append(" and ((");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" (t.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ) or (");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" (t.end_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t.end_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t.start_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t.start_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ))");
        }else {
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" and t.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
        }
        if(StringUtils.isNotEmpty(notDriverId)) {
            sql.append(" and t1.id not exists ("+notDriverId+") ");
        }
        sql.append(" ORDER BY t2.modify_time DESC LIMIT 0,"+pageSize);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hsql", sql.toString());
        return matchingDao.queryMatchingDriverList(map);
    }

    /**
     * 匹配规则：24h内定位信息在装货地
     * @param orderCargoInfoDomain 货源对象
     * @param notDriverId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    @Override
    public List<MatchingDriverInfoDomain> queryDriverByLocation(OrderCargoInfoDomain orderCargoInfoDomain, String notDriverId,Integer pageSize,String msgType) {
        StringBuffer sql = new StringBuffer();
        sql.append("select DISTINCT ");
        sql.append(" t1.id,t1.code,t1.name,t1.car_number,TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) as locadate,'0' as markType ");
        sql.append(" from t_driver_user_info t1,t_location_collect_last_info t2 ");
        sql.append(" where t2.driver_id = t1.id ");
        if("0".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) <= 24 ");
        }else if("1".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) > 24 ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
            sql.append(" and t2.province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
            sql.append(" and t2.city = '"+orderCargoInfoDomain.getStartCity()+"' ");
        }
        if(StringUtils.isNotEmpty(notDriverId)) {
            sql.append(" and t1.id not exists ("+notDriverId+") ");
        }
        sql.append(" ORDER BY t2.modify_time DESC LIMIT 0,"+pageSize);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hsql",sql.toString());
        return matchingDao.queryMatchingDriverList(map);
    }


    /*************************************UNION*****************************************/
    /**
     * UNION 匹配规则（注册用户）
     * @param orderCargoInfoDomain
     * @param pageSize
     * @param msgType
     * @return
     */
    public List<MatchingDriverInfoDomain> queryRegDriverUnionlist(OrderCargoInfoDomain orderCargoInfoDomain,Integer pageSize,String msgType) {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.id,a.code,a.name,a.car_number,a.locadate from (");
        //规则1
        sql.append(" select DISTINCT ");
        sql.append(" t1.id,t1.code,t1.name,t1.car_number,TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) as locadate ");
        sql.append(" from t_driver_business_line_info t,t_driver_user_info t1,t_location_collect_last_info t2 ");
        sql.append(" where t.driver_id = t1.id and t2.driver_id = t1.id and t1.delete_flag = 0 and t.start = 0 ");
        sql.append(" and DATE_FORMAT(t.end_time,'%Y-%m-%d')>=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') ");
        if("0".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) <= 24 ");
        }else if("1".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) > 24 ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince())) {
            sql.append(" and t.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
            sql.append(" and t.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
            sql.append(" and t.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
            sql.append(" and t.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getRequestStartTime())) {
            sql.append(" and DATE_FORMAT(t.start_time,'%Y-%m-%d') <= DATE_FORMAT('"+orderCargoInfoDomain.getRequestStartTime()+"','%Y-%m-%d') ");
            sql.append(" and DATE_FORMAT(t.end_time,'%Y-%m-%d') >= DATE_FORMAT('"+orderCargoInfoDomain.getRequestStartTime()+"','%Y-%m-%d') ");
        }
        sql.append(" UNION ");
        //规则2
        sql.append(" select DISTINCT ");
        sql.append(" t1.id,t1.code,t1.name,t1.car_number,TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) as locadate ");
        sql.append(" from t_driver_line_info t,t_driver_user_info t1,t_location_collect_last_info t2 ");
        sql.append(" where t.driver_id = t1.id and t2.driver_id = t1.id and t1.delete_flag = 0 and t.start = 0 ");
        if("0".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) <= 24 ");
        }else if("1".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) > 24 ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) && StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
            sql.append(" and ((");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" t.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ) or (");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" t.end_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t.end_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t.start_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t.start_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ))");
        }else {
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" and t.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
        }
        sql.append(" UNION ");
        //规则3
        sql.append(" select DISTINCT ");
        sql.append(" t1.id,t1.code,t1.name,t1.car_number,TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) as locadate ");
        sql.append(" from t_driver_user_info t1,t_location_collect_last_info t2 ");
        sql.append(" where t2.driver_id = t1.id and t1.delete_flag = 0 ");
        if("0".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) <= 24 ");
        }else if("1".equals(msgType)) {
            sql.append(" and TIMESTAMPDIFF(HOUR,t2.MODIFY_TIME,sysdate()) > 24 ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
            sql.append(" and t2.province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
            sql.append(" and t2.city = '"+orderCargoInfoDomain.getStartCity()+"' ");
        }
        sql.append(" ) a ORDER BY a.locadate asc LIMIT 0,"+pageSize);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hsql",sql.toString());
        return matchingDao.queryMatchingDriverList(map);
    }


    /**
     * UNION 匹配规则（未注册用户）
     * @param orderCargoInfoDomain
     * @param pageSize
     * @return
     */
    public List<MatchingDriverInfoDomain> queryNotRegDriverUnionlist(OrderCargoInfoDomain orderCargoInfoDomain,Integer pageSize) {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.id,a.code,a.name,a.car_number,a.locadate from (");
        //规则1
        sql.append(" select DISTINCT ");
        sql.append(" t.id,t.mobile_phone as code,t.name,t.car_number,t.last_contact_date as locadate ");
        sql.append(" from t_marketing_driver_info t,t_marketing_driver_business_line t1 ");
        sql.append(" where t.id=t1.customer_driver_id and t.driver_id is null and t.phone_valid = 1 and t1.delete_flag = 0 ");
        sql.append(" and DATE_FORMAT(t1.end_time,'%Y-%m-%d')>=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') ");
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince())) {
            sql.append(" and t1.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
            sql.append(" and t1.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
            sql.append(" and t1.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
            sql.append(" and t1.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
        }
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getRequestStartTime())) {
            sql.append(" and DATE_FORMAT(t1.start_time,'%Y-%m-%d') <= DATE_FORMAT('"+orderCargoInfoDomain.getRequestStartTime()+"','%Y-%m-%d') ");
            sql.append(" and DATE_FORMAT(t1.end_time,'%Y-%m-%d') >= DATE_FORMAT('"+orderCargoInfoDomain.getRequestStartTime()+"','%Y-%m-%d') ");
        }
        sql.append(" UNION ");
        //规则2
        sql.append(" select DISTINCT ");
        sql.append(" t.id,t.mobile_phone as code,t.name,t.car_number,t.last_contact_date as locadate ");
        sql.append(" from t_marketing_driver_info t,t_marketing_driver_line t1 ");
        sql.append(" where t.id=t1.customer_driver_id and t.driver_id is null and t.phone_valid = 1 and t1.delete_flag = 0 ");
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) && StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
            sql.append(" and ((");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" t1.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t1.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t1.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t1.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ) or (");
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" t1.end_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t1.end_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t1.start_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t1.start_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
            sql.append(" ))");
        }else {
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince()) ) {
                sql.append(" and t1.start_province = '"+orderCargoInfoDomain.getStartProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sql.append(" and t1.start_city = '"+orderCargoInfoDomain.getStartCity()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndProvince())) {
                sql.append(" and t1.end_province = '"+orderCargoInfoDomain.getEndProvince()+"' ");
            }
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getEndCity())) {
                sql.append(" and t1.end_city = '"+orderCargoInfoDomain.getEndCity() + "' ");
            }
        }
        sql.append(" UNION ");
        //规则3
        StringBuffer sPP = new StringBuffer();
        sql.append(" select DISTINCT ");
        sql.append(" t.id,t.mobile_phone as code,t.name,t.car_number,t.last_contact_date as locadate ");
        sql.append(" from t_marketing_driver_info t");
        sql.append(" where t.driver_id is null and t.phone_valid = 1 ");
        if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartProvince())) {
            sPP.append(orderCargoInfoDomain.getStartProvince());
            if(StringUtils.isNotEmpty(orderCargoInfoDomain.getStartCity())) {
                sPP.append("-"+orderCargoInfoDomain.getStartCity());
            }
        }
        if(StringUtils.isNotEmpty(sPP.toString())) {
            sql.append(" and (");
            sql.append(" t.often_city1 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city2 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city3 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city4 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city5 like ('"+sPP.toString()+"') ");
            sql.append(" or t.often_city6 like ('"+sPP.toString()+"') ");
            sql.append(" ) ");
        }
        sql.append(" ) a ORDER BY a.locadate DESC LIMIT 0,"+pageSize);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hsql",sql.toString());
        return matchingDao.queryMatchingDriverList(map);
    }





    /*************************************每日发送给企业或司机的短信或推送的计数器*****************************************/

    /**
     * 查询
     * @param countNums 发送数量
     * @param targetType  目标类型
     * @param msgType 消息类型
     * @return
     */
    @Override
    public List<DaySendMsgcounter> queryDaySendMsgcounterList(Integer targetType, Integer msgType, Integer countNums, Integer locadate) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("targetType",targetType);
        map.put("msgType",msgType);
        map.put("countNums",countNums);
        map.put("locadate",locadate);
        return matchingDao.queryDaySendMsgcounterList(map);
    }


    /**
     * 查询
     * @param targetId 目标id
     * @param targetType  目标类型
     * @param msgType 消息类型
     * @return
     */
    @Override
    public DaySendMsgcounter queryDaySendMsgcounter(Integer targetId,Integer targetType,Integer msgType) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("targetId",targetId);
        map.put("targetType",targetType);
        map.put("msgType",msgType);
        return matchingDao.queryDaySendMsgcounter(map);
    }

    /**
     * 插入
     * @param targetId 目标id
     * @param targetType 目标类型
     * @param msgType 消息类型
     * @return
     */
    @Override
    public boolean addDaySendMsgcounter(Integer targetId,Integer targetType,Integer msgType) {
        boolean result = false;
        DaySendMsgcounter daySendMsgcounter = new DaySendMsgcounter();
        daySendMsgcounter.setTargetId(targetId);
        daySendMsgcounter.setTargetType(targetType);
        daySendMsgcounter.setMsgType(msgType);
        daySendMsgcounter.setCountNums(1);
        matchingDao.addDaySendMsgcounter(daySendMsgcounter);
        if(daySendMsgcounter.getId() != null) {
            result = true;
        }
        return result;
    }

    /**
     * 修改
     * @param targetId 目标id
     * @param targetType 目标类型
     * @param msgType 消息类型
     * @return
     */
    @Override
    public boolean updateDaySendMsgcounter(Integer targetId,Integer targetType,Integer msgType) {
        DaySendMsgcounter daySendMsgcounter = new DaySendMsgcounter();
        daySendMsgcounter.setTargetId(targetId);
        daySendMsgcounter.setTargetType(targetType);
        daySendMsgcounter.setMsgType(msgType);
        return matchingDao.updateDaySendMsgcounter(daySendMsgcounter);
    }

    /**
     *  拼装id
     * @param list
     * @return
     */
    public String getQueryHqlId(List<MatchingDriverInfoDomain> list) {
        StringBuffer idHsql = new StringBuffer();
        for(int i = 0;i<list.size();i++) {
            if(i == 0) {
                idHsql.append(list.get(i).getId());
            }else {
                idHsql.append(","+list.get(i).getId());
            }
        }
        return idHsql.toString();
    }


    /**
     * list对象递减
     * @param driverlist
     * @param sendlist
     * @return
     */
    public List<MatchingDriverInfoDomain> getlist(List<MatchingDriverInfoDomain> driverlist,List<DaySendMsgcounter> sendlist) {
        for(int i = 0;i<sendlist.size();i++) {
            for(int j = 0;j<driverlist.size();j++) {
                if(sendlist.get(i).getTargetId().intValue() ==  driverlist.get(j).getId().intValue()) {
                    driverlist.remove(j);
                    break;
                }
            }
        }
        return driverlist;
    }
}
