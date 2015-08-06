package com.cy.swp.service;

import com.cy.swp.bo.DriverUserInfo;
import com.cy.swp.bo.MarketingDriverInfo;
import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.domain.PageInfo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by wyh on 2014/12/4.
 */
public interface MarketingDriverInfoService {

    /**
     * 导入司机
     * @param inputStream
     * @param user
     * @param importType
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     * @author wyh
     */
    public Map<String, Object> importExcelDriver(InputStream inputStream, MarketingUserInfo user, String importType) throws IOException, InvalidFormatException;

    /**
     * 分配司机资料
     * @param info
     * @param user
     * @return
     * @author wyh
     */
    public Map<String, String> assignDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user);

    /**
     * 查询司机资料
     * @param info
     * @param page
     * @return
     * @author wyh
     */
    public List<MarketingDriverInfoDomain> queryDriverListServ(MarketingDriverInfoDomain info, PageInfo page);

    /**
     * 查询我的司机客户资料
     * @param info
     * @param page
     * @param allDriverPage
     * @return
     * @author wyh
     */
    public List<MarketingDriverInfoDomain> queryMyDriverListServ(MarketingDriverInfoDomain info, PageInfo page, PageInfo allDriverPage);

    /**
     * 获得分配客户页面列表数据html
     * @param driverList
     * @param type
     * @return
     * @author wyh
     */
    public String getAssignCustHtml(List<MarketingDriverInfoDomain> driverList, Integer type);

    /**
     * 分配申请审核
     * @param info
     * @param user
     * @param type
     * @return
     * @author wyh
     */
    public Map<String, String> reviewDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user, String type);

    /**
     * 未联系客户页面页面查询
     * @param info
     * @param page
     * @return
     * @author wyh
     */
    public List<MarketingDriverInfoDomain> enterNoContCustomServ(MarketingDriverInfoDomain info, PageInfo page, MarketingUserInfo user);



    /**
     * 根据id获取司机客户信息(已注册)
     * @param id
     * @author nxj
     */
    public MarketingDriverInfoDomain queryMarketingDriverInfoDomainById(Integer id);



    /**
     * 根据id获取司机客户信息(未注册)
     * @param id
     * @author nxj
     */
    public MarketingDriverInfoDomain queryMarketingDriverInfoDomainNoById(Integer id);


    /**
     * 获得我的客户列表html
     * @param driverList
     * @return
     * @author wyh
     */
    public String getMyCustHtml(List<MarketingDriverInfoDomain> driverList);

    /**
     * 获得分页页面的html
     * @param page
     * @return
     * @author wyh
     */
    public String getPageHtml(PageInfo page);


    /**
     * 修改司机信息（车辆长、板、栏、车牌号）(未安装)
     * @param marketingDriverInfo
     * @author nxj
     */
    public boolean updateMarketingDriverInfoNoById(MarketingDriverInfo marketingDriverInfo);

    /**
     * 修改司机信息（车辆长、板、栏、车牌号）（已安装）
     * @param driverUserInfo
     * @author nxj
     */
    public boolean updateDriverUserInfoByDriverId(DriverUserInfo driverUserInfo);

    /**
     * 修改司机常跑城市信息
     * @param marketingDriverInfo
     * @author nxj
     */
    public boolean updateDriverUserInfoSetOftenCity(MarketingDriverInfo marketingDriverInfo);


    /**
     *修改客户司机信息（手机号码2、手机号码3）
     * @param marketingDriverInfo
     * @author nxj
     */
    public boolean updateMarketingDriverInfoSetMobilePhone(MarketingDriverInfo marketingDriverInfo);


    /**
     * 获得导入成功后未分配的html
     * @param noAssignList
     * @return
     * @author wyh
     */
    public String getNoAssignHtml(List<MarketingDriverInfoDomain> noAssignList);

    /**
     * 获得未联系客户列表html
     * @param driverList
     * @return
     * @author wyh
     */
    public String getNoCustHtml(List<MarketingDriverInfoDomain> driverList);

    /**
     * 根据driverId获取司机活跃信息(已注册)
     * @param driverId
     * @autor nxj
     */
    public MarketingDriverInfoDomain queryDriverInfoDomainByDriverId(Integer driverId);

    /**
     * 修改客户司机信息,手机号码无效（主手机号码标注无效的次数+1、营销人员Id清空、分配状态修改为待分配0、分配者id清空）
     * @param marketingDriverInfo
     * @autor nxj
     */
    public boolean updateMarketingDriverInfoSetMarkInvalidNums(MarketingDriverInfo marketingDriverInfo);


    /**
     * 修改客户司机信息（客户类别(1 2 3 4 5)）
     * @param marketingDriverInfo
     * @autor nxj
     */
    public boolean updateMarketingDriverInfoSetCategory(MarketingDriverInfo marketingDriverInfo);

    /**
     * 修改客户司机信息（最近联系时间、下次联系时间）
     * @param marketingDriverInfo
     * @autor nxj
     */
    public boolean updateMarketingDriverInfoSetContactDate(MarketingDriverInfo marketingDriverInfo);
}
