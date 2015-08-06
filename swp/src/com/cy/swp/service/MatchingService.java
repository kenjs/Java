package com.cy.swp.service;

import com.cy.swp.bo.DaySendMsgcounter;
import com.cy.swp.domain.DriverUserInfoDomain;
import com.cy.swp.domain.MatchingDriverInfoDomain;
import com.cy.swp.domain.OrderCargoInfoDomain;

import java.util.List;
import java.util.Map;

/**
 * 匹配规则Service
 * Created by nixianjing on 15/1/6.
 */
public interface MatchingService {


    /**
     * 抓取货源发送短信或推送内部接口
     * @return
     */
    public boolean matchingDriverNote();


    /**
     * 注册司机推送
     * @param orderCargoInfoDomain
     * @return
     */
    public boolean matchingRegistrationDriverPush(OrderCargoInfoDomain orderCargoInfoDomain);


    /**
     * 注册司机短信
     * @param orderCargoInfoDomain
     * @return
     */
    public boolean matchingRegistrationDriverNote(OrderCargoInfoDomain orderCargoInfoDomain);


    /**
     * 未注册司机短信
     * @param orderCargoInfoDomain
     * @return
     */
    public boolean matchingNotRegistrationDriverNote(OrderCargoInfoDomain orderCargoInfoDomain);

    /**********************************************（导入或爬虫）货源匹配车辆(未注册用户)*****************************************/
    /**
     * 匹配规则：预约线路起始地目的地符合装货地卸货地，并且预约时间在装货时间内
     * @param orderCargoInfoDomain 货源对象
     * @param notMarkUserId 排除的客户司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    public List<MatchingDriverInfoDomain> queryDriverByMarkBusinessLine(OrderCargoInfoDomain orderCargoInfoDomain,String notMarkUserId,Integer pageSize);


    /**
     * 匹配规则：运营线路起始地目的地来回之一符合装货地卸货地
     * @param orderCargoInfoDomain 货源对象
     * @param notMarkUserId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    public List<MatchingDriverInfoDomain> queryDriverByLine(OrderCargoInfoDomain orderCargoInfoDomain,String notMarkUserId,Integer pageSize);

    /**
     * 匹配规则 常跑城市
     * @param orderCargoInfoDomain 货源对象
     * @param notMarkUserId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @return
     */
    public List<MatchingDriverInfoDomain> queryDriverByOftenCity(OrderCargoInfoDomain orderCargoInfoDomain,String notMarkUserId,Integer pageSize);


    /**********************************************（导入或爬虫）货源匹配车辆(注册用户)*****************************************/
    /**
     * 匹配规则：预约线路起始地目的地符合装货地卸货地，并且预约时间在装货时间内
     * @param orderCargoInfoDomain 货源对象
     * @param notDriverId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @param pageSize 查询数据数量（多少条数据）
     * @param msgType 消息类型 0 推送消息(已注册用户定位时间小于等于24小时) 1 短信消息(已注册用户定位时间大于24小时)
     * @return
     */
    public List<MatchingDriverInfoDomain> queryDriverByBusinessLine(OrderCargoInfoDomain orderCargoInfoDomain,String notDriverId,Integer pageSize,String msgType);


    /**
     * 匹配规则：运营线路起始地目的地来回之一符合装货地卸货地，并且24h内定位信息在装货地
     * @param orderCargoInfoDomain 货源对象
     * @param notDriverId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @param pageSize 查询数据数量（多少条数据）
     * @param msgType 消息类型 0 推送消息(已注册用户定位时间小于等于24小时) 1 短信消息(已注册用户定位时间大于24小时)
     * @return
     */
    public List<MatchingDriverInfoDomain> queryDriverByLineLocation(OrderCargoInfoDomain orderCargoInfoDomain,String notDriverId,Integer pageSize,String msgType);

    /**
     * 匹配规则：运营线路起始地目的地来回之一符合装货地卸货地
     * @param orderCargoInfoDomain 货源对象
     * @param notDriverId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @param pageSize 查询数据数量（多少条数据）
     * @param msgType 消息类型 0 推送消息(已注册用户定位时间小于等于24小时) 1 短信消息(已注册用户定位时间大于24小时)
     * @return
     */
    public List<MatchingDriverInfoDomain> queryDriverByLine(OrderCargoInfoDomain orderCargoInfoDomain,String notDriverId,Integer pageSize,String msgType);

    /**
     * 匹配规则：24h内定位信息在装货地
     * @param orderCargoInfoDomain 货源对象
     * @param notDriverId 排除的司机id(多个)可以为空（例：1,2,3,4）
     * @param pageSize 查询数据数量（多少条数据）
     * @param msgType 消息类型 0 推送消息(已注册用户定位时间小于等于24小时) 1 短信消息(已注册用户定位时间大于24小时)
     * @return
     */
    public List<MatchingDriverInfoDomain> queryDriverByLocation(OrderCargoInfoDomain orderCargoInfoDomain,String notDriverId,Integer pageSize,String msgType);


    /**********************************************车辆匹配货源*****************************************/

    /**
     * 匹配规则：预约线路起始地目的地符合装货地卸货地，并且预约时间在装货时间内
     * @param drvierId 司机id
     * @param notCargoId 排除的司机id
     * @return
     */
    public List<OrderCargoInfoDomain> queryCargoByBusinessLine(Integer drvierId,String notCargoId);


    /**
     * 匹配规则：预约线路起始地目的地符合装货地卸货地，并且预约时间在装货时间内
     * @param markDriverId 司机客户id
     * @param notCargoId 排除的客户司机id
     * @return
     */
    public List<OrderCargoInfoDomain> queryCargoByMarkBusinessLine(Integer markDriverId,String notCargoId);


    /**
     * 匹配规则：运营线路起始地目的地来回之一符合装货地卸货地，并且24h内定位信息在装货地
     * @param drvierId 司机id
     * @param notCargoId 排除的司机id
     * @return
     */
    public List<OrderCargoInfoDomain> queryCargoByLineLocation(Integer drvierId,String notCargoId);


    /**
     * 匹配规则：运营线路起始地目的地来回之一符合装货地卸货地
     * @param drvierId 司机id
     * @param notCargoId 排除的司机id
     * @return
     */
    public List<OrderCargoInfoDomain> queryCargoByLine(Integer drvierId,String notCargoId);


    /**
     * 匹配规则：运营线路起始地目的地来回之一符合装货地卸货地
     * @param markDriverId 司机客户id
     * @param notCargoId 排除的客户司机id
     * @return
     */
    public List<OrderCargoInfoDomain> queryCargoByMarkLine(Integer markDriverId,String notCargoId);

    /**
     *  匹配规则：24h内定位信息在装货地
     * @param drvierId 司机id
     * @param notCargoId 排除的司机id
     * @return
     */
    public List<OrderCargoInfoDomain> queryCargoByLocation(Integer drvierId,String notCargoId);


    /**
     * 匹配规则：长跑城市符合装货地
     * @param markDriverId 司机客户id
     * @param notCargoId 排除的客户司机id
     * @return
     */
    public List<OrderCargoInfoDomain> queryCargoByOftenCity(Integer markDriverId,String notCargoId);


    /*************************************每日发送给企业或司机的短信或推送的计数器*****************************************/


    /**
     * 查询
     * @param countNums 发送数量
     * @param targetType  目标类型
     * @param msgType 消息类型
     * @return
     */
    public List<DaySendMsgcounter> queryDaySendMsgcounterList(Integer targetType,Integer msgType,Integer countNums,Integer locadate);

    /**
     * 查询
     * @param targetId 目标id
     * @param targetType  目标类型
     * @param msgType 消息类型
     * @return
     */
    public DaySendMsgcounter queryDaySendMsgcounter(Integer targetId,Integer targetType,Integer msgType);

    /**
     * 插入
     * @param targetId 目标id
     * @param targetType 目标类型
     * @param msgType 消息类型
     * @return
     */
    public boolean addDaySendMsgcounter(Integer targetId,Integer targetType,Integer msgType);

    /**
     * 修改
     * @param targetId 目标id
     * @param targetType 目标类型
     * @param msgType 消息类型
     * @return
     */
    public boolean updateDaySendMsgcounter(Integer targetId,Integer targetType,Integer msgType);


}
