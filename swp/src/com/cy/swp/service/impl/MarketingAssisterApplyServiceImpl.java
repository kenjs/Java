package com.cy.swp.service.impl;

import com.cy.swp.bo.*;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.dao.*;
import com.cy.swp.domain.DriverUserInfoDomain;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.domain.MarketingMaintainRecordDomain;
import com.cy.swp.service.MarketingAssisterApplyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wyh on 2014/12/15.
 */
@Service("marketingAssisterApplyServiceImpl")
public class MarketingAssisterApplyServiceImpl implements MarketingAssisterApplyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MarketingDriverInfoDao marketingDriverInfoDao;
    @Resource
    private MarketingAssisterApplyDao marketingAssisterApplyDao;
    @Resource
    private DriverUserInfoDao driverUserInfoDao;
    @Resource
    private MarketingDistributRecordDao marketingDistributRecordDao;
    @Resource
    private MarketingMaintainRecordDao marketingMaintainRecordDao;

    //分配申请service
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, String> applyDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user)throws SQLException{
        Map<String, String> rtMap = new HashMap<String, String>();
        String[] driverIds = StringUtils.split(info.getDriverIds(), ",");
        List<Integer> ids = new ArrayList<Integer>();
        MarketingDriverInfoDomain driverInfo = null;
        for(String id:driverIds) {
            driverInfo = marketingDriverInfoDao.queryById(new Integer(id));
            if(driverInfo == null){
                continue;
            }
            if(Constants.ALLOCATE_STATUS_WAIT_KEY == driverInfo.getAllocateStatus()
                    && driverInfo.getDeleteFlag() == 0){
                //等待分配的司机
                ids.add(driverInfo.getId());
            }
        }
        if(ids.size() == 0){
            rtMap.put("result", "1");
            rtMap.put("message", "分配申请失败，勾选的司机已被申请或已被分配，请点击待分配");
            return rtMap;
        }
        //司机资料数据参数
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("allocateStatus", Constants.ALLOCATE_STATUS_ING_KEY);
        paraMap.put("assisterId", user.getId());
        paraMap.put("list", ids);
        //申请审核数据参数
        List<MarketingAssisterApply> paraApplyList = new ArrayList<MarketingAssisterApply>();
        MarketingAssisterApply assisterApply = null;
        for(Integer id:ids){
            assisterApply = new MarketingAssisterApply();
            assisterApply.setAssisterId(user.getId());
            assisterApply.setCustomerId(id);
            assisterApply.setCustomerKind(Constants.CUSTOMER_KIND_DRIVER_KEY);
            assisterApply.setAuditStatus(Constants.AUDIT_STATUS_WAIT_KEY);
            paraApplyList.add(assisterApply);
        }
        bachApply(paraMap, paraApplyList);
        rtMap.put("result", "0");
        return rtMap;
    }

    //批量更新
    private void bachApply(Map<String, Object> paraMap, List<MarketingAssisterApply> paraApplyList)throws SQLException{
        int dnum = marketingDriverInfoDao.updateBachApplyById(paraMap);
        int anum = marketingAssisterApplyDao.addBach(paraApplyList);
        if(dnum != anum){
            throw new SQLException("分配申请失败，司机的资料已被申请或已被分配，请点击待分配");
        }
    }

    //刷新营销人员导入后展示的待分配司机service
    @Override
    public List<MarketingDriverInfoDomain> refreshImportDriverServ(String[] driverIds){
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("allocateStatus", Constants.ALLOCATE_STATUS_WAIT_KEY);
        paraMap.put("list", driverIds);
        return marketingDriverInfoDao.queryByIdIn(paraMap);
    }



    //添加单条司机客户
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Object> addSingleDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user){
        Map<String, Object> rtMap = new HashMap<String, Object>();
        //手机号码为空
        if(StringUtils.isEmpty(info.getMobilePhone())){
            rtMap.put("result", "1");
            rtMap.put("message", "添加失败，手机号码不能为空！");
            return rtMap;
        }
        info.setMobilePhone(info.getMobilePhone().trim());//去两边的空字符
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("mobilePhone", info.getMobilePhone());
        //查询数据库中是否存在该司机
        MarketingDriverInfoDomain mkDriverInfoDm = marketingDriverInfoDao.queryByPhone(paraMap);
        if(mkDriverInfoDm != null){
            if(mkDriverInfoDm.getAllocateStatus() == Constants.ALLOCATE_STATUS_WAIT_KEY){
                rtMap.put("result", "2");
            }else{
                rtMap.put("result", "3");
            }
            rtMap.put("driverInfo", mkDriverInfoDm);
            return rtMap;
        }
        //设置申请人id
        info.setAssisterId(user.getId());
        //获得要保存的司机资料Domain
        MarketingDriverInfoDomain saveDriverInfo = getInfoDomain(info, paraMap);
        int num = 0;//保存影响的行数
        try {
            //新增单条 司机客户
            num = marketingDriverInfoDao.add(saveDriverInfo);
            if(saveDriverInfo.getId() == null){
                throw new RuntimeException("添加失败，系统出错！");
            }
            //新增单条 客户分配记录
            MarketingDistributRecord recordPara = new MarketingDistributRecord();
            recordPara.setCustomerKind(Constants.CUSTOMER_KIND_DRIVER_KEY);
            recordPara.setCustomerId(saveDriverInfo.getId());
            recordPara.setDistributerId(user.getId());
            recordPara.setAssisterId(user.getId());
            recordPara.setCustomerLevel(saveDriverInfo.getRealLevel());
            recordPara.setDistributType(Constants.DISTRIBUT_TYPE_BINDING_KEY);
            int rnum = marketingDistributRecordDao.add(recordPara);
            if(num == 1 && rnum == num){
                rtMap.put("result", "10");
                rtMap.put("message", "添加成功！");
                return rtMap;
            }
            throw new RuntimeException("添加失败，系统出错！");
        } catch (Exception e){
            logger.debug("function addSingleDriverServ add error:",e.getMessage());
            throw new RuntimeException("添加失败，该司机资料在添加时被其他营销人员先添加！");
        }
    }

    //获得要保存的司机资料Domain
    private MarketingDriverInfoDomain getInfoDomain(MarketingDriverInfoDomain info, Map<String, String> paraMap){
        String realLevel = Constants.REAL_LEVEL_D;//司机客户等级 默认D类
        String name = info.getName();//司机姓名
        String carNumber = info.getCarNumber().toUpperCase();//车牌号 并转大写

        //根据手机号码查询，计数司机运营线路条数，计数司机活跃记录表的条数 查询司机注册信息表
        DriverUserInfoDomain driverUserInfo = driverUserInfoDao.queryByPhone(paraMap);
        if(driverUserInfo != null){
            if((Constants.SUBMIT_TYPE_SHYES_KEY+"").equals(driverUserInfo.getSubmitType())){
                //已认证完成的司机 客户等级A类
                realLevel = Constants.REAL_LEVEL_A;
            }else if(driverUserInfo.getCountDriverLine() > 0){
                //至少有一条运营线路的司机 客户等级B类
                realLevel = Constants.REAL_LEVEL_B;
            }else if(driverUserInfo.getCountMonAct() > 0){
                //月存活（一个月内APP有操作过） 客户等级C类
                realLevel = Constants.REAL_LEVEL_C;
            }
            if(StringUtils.isNotEmpty(driverUserInfo.getName())){
                name = driverUserInfo.getName();
            }
            if(StringUtils.isNotEmpty(driverUserInfo.getCarNumber())){
                carNumber = driverUserInfo.getCarNumber();
            }
            info.setDriverId(driverUserInfo.getId());
        }
        info.setAllocateStatus(Constants.ALLOCATE_STATUS_END_KEY);
        info.setRealLevel(realLevel);
        info.setName(name);
        info.setCarNumber(carNumber);
        return info;
    }

    //申请单条司机客户serv
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Object> applySingleDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user){
        Map<String, Object> rtMap = new HashMap<String, Object>();
        if(info.getId() == null){
            rtMap.put("result", "1");
            rtMap.put("message", "申请失败，系统出错！");
            return rtMap;
        }
        //根据id查询
        MarketingDriverInfoDomain rtDriverInfo = marketingDriverInfoDao.queryById(info.getId());
        if(rtDriverInfo == null){
            rtMap.put("result", "1");
            rtMap.put("message", "申请失败，该数据已不存在！");
            return rtMap;
        }
        if(rtDriverInfo.getDeleteFlag() != 0){
            rtMap.put("result", "1");
            rtMap.put("message", "申请失败，该数据已被删除！");
            return rtMap;
        }
        if(rtDriverInfo.getAllocateStatus() != Constants.ALLOCATE_STATUS_WAIT_KEY){
            rtMap.put("result", "1");
            rtMap.put("message", "申请失败，该数据已被申请/分配！");
            return rtMap;
        }
        //保存申请单条司机客户
        saveApplySingleDriver(info.getId(), user.getId());
        rtMap.put("result", "10");
        rtMap.put("message", "申请成功！");
        return rtMap;
    }

    //保存申请单条司机客户
    private void saveApplySingleDriver(Integer id, Integer userId){
        //司机资料修改参数
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("allocateStatus", Constants.ALLOCATE_STATUS_ING_KEY);
        paraMap.put("assisterId", userId);
        paraMap.put("allocateStatusOld", Constants.ALLOCATE_STATUS_WAIT_KEY);//where条件
        paraMap.put("id", id);//where条件

        //司机资料申请新增参数
        MarketingAssisterApply applyPara = new MarketingAssisterApply();
        applyPara.setAssisterId(userId);
        applyPara.setCustomerKind(Constants.CUSTOMER_KIND_DRIVER_KEY);
        applyPara.setCustomerId(id);
        applyPara.setAuditStatus(Constants.AUDIT_STATUS_WAIT_KEY);

        int dNum = marketingDriverInfoDao.updateById(paraMap);
        int aNum = marketingAssisterApplyDao.add(applyPara);
        if(dNum != 1 || aNum != 1){
            throw new RuntimeException("申请失败！");
        }
    }

    //查询标记司机手机号码无效的记录serv
    @Override
    public List<MarketingMaintainRecordDomain> queryMaintainRecordServ(String id,Integer customerKind){
        MarketingMaintainRecord recordPara = new MarketingMaintainRecord();
        recordPara.setCustomerKind(customerKind);
        recordPara.setCustomerId(new Integer(id));
        recordPara.setRecordContent(Constants.RECORD_CONTENT_VAL);
        return marketingMaintainRecordDao.queryByRecordContentList(recordPara);
    }
}
