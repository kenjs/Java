package com.cy.dctms.dao;

import com.cy.dctms.common.bo.DaySendMsgcounter;
import com.cy.dctms.domain.MatchingDriverInfoDomain;
import com.cy.dctms.domain.OrderCargoInfoDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 匹配规则Dao
 * 包含：
 * 货源匹配车辆
 * 车辆匹配货源
 *
 * Created by nixianjing on 15/1/6.
 */
@Repository("matchingDao")
public interface MatchingDao {


    /**********************************************匹配*****************************************/
    /**
     * 获取司机信息
     * @param map (key=hsql ,value=动态sql)
     * @return
     */
    public List<MatchingDriverInfoDomain> queryMatchingDriverList(Map<String, String> map);


    /**
     * 获取货源信息
     * @param sql 动态sql
     * @return
     */
   // public List<OrderCargoInfoDomain> queryMatchingCargoList(String sql);



    /*************************************每日发送给企业或司机的短信或推送的计数器*****************************************/


    /**
     * 查询
     * @param map（发送目标类型、发送消息类型、发送次数 ）
     * @return
     */
    public List<DaySendMsgcounter> queryDaySendMsgcounterList(Map<String, Object> map);

    /**
     * 查询
     * @param map（发送目标id、发送目标类型、发送消息类型）
     * @return
     */
    public DaySendMsgcounter queryDaySendMsgcounter(Map<String, Object> map);

    /**
     * 插入
     * @param daySendMsgcounter
     * @return
     */
    public Integer addDaySendMsgcounter(DaySendMsgcounter daySendMsgcounter);

    /**
     * 修改
     * @param daySendMsgcounter
     * @return
     */
    public boolean updateDaySendMsgcounter(DaySendMsgcounter daySendMsgcounter);



    /**********************************************货源处理*********************************************************/


    public List<OrderCargoInfoDomain> queryOrderCargoList();


    /**
     * 根据货源id批量修改
     * @param map
     * @return
     */
    Integer updateOrderCargo(Map<String, Object> map);

}
