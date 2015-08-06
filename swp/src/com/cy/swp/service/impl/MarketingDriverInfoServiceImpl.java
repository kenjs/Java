package com.cy.swp.service.impl;

import com.cy.swp.bo.*;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.util.ReadExcelUtil;
import com.cy.swp.common.util.ValidateUtil;
import com.cy.swp.dao.DriverUserInfoDao;
import com.cy.swp.dao.MarketingAssisterApplyDao;
import com.cy.swp.dao.MarketingDistributRecordDao;
import com.cy.swp.dao.MarketingDriverInfoDao;
import com.cy.swp.domain.DriverUserInfoDomain;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.domain.PageInfo;
import com.cy.swp.service.MarketingDriverInfoService;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by wyh on 2014/12/4.
 */
@Service("marketingDriverInfoServiceImpl")
public class MarketingDriverInfoServiceImpl implements MarketingDriverInfoService {
    @Resource
    private MarketingDriverInfoDao marketingDriverInfoDao;
    @Resource
    private DriverUserInfoDao driverUserInfoDao;
    @Resource
    private MarketingAssisterApplyDao marketingAssisterApplyDao;
    @Resource
    private MarketingDistributRecordDao marketingDistributRecordDao;


    //导入司机 批量保存 事物控制
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Object> importExcelDriver(InputStream inputStream, MarketingUserInfo user, String importType) throws IOException, InvalidFormatException{
        Map<String, Object> rtMap = new HashMap<String, Object>();
        List<String[]> filedsList = new ArrayList<String[]>();
        String[] fileds = new String[]{"name","carNumber","mobilePhone"};
        filedsList.add(fileds);
        fileds = new String[]{"mphone"};
        filedsList.add(fileds);
        int[] sheetNums = new int[]{0};
        //读取excel文件内容(无列表头)
        Map<Integer, List<Map<String, String>>> resmap = new ReadExcelUtil(true).readExcelFile(inputStream, filedsList, sheetNums);
        if(resmap == null){
            rtMap.put("result", "1");
            return rtMap;
        }
        //按手机号码排序并去手机号码为空、去重复、去无效格式的手机号码并返回新的集合
        List<Map<String, String>> list0 = sortRemoveLikeData(resmap.get(0), "mobilePhone");
        if(list0 == null || list0.size() == 0){
            rtMap.put("result", "2");
            return rtMap;
        }
        MarketingDriverInfoDomain mkDriverInfoDm = null;
        //数据库已存在 但未分配司机List
        List<MarketingDriverInfoDomain> noAssignList = new ArrayList<MarketingDriverInfoDomain>();
        //刷新所需的ids
        String refreshIds = "";
        //未存在的需要保存的司机List
        List<MarketingDriverInfoDomain> saveList = new ArrayList<MarketingDriverInfoDomain>();
        Integer assisterId = null;//营销专员id
        if(Constants.IMPORT_DRIVER_TYPE_ORD.equals(importType)){
            //营销人员导入
            assisterId = user.getId();
        }
        int allocatedNum = 0;//已分配条数
        for(Map<String, String> map:list0){
            //查询数据库中是否存在该司机
            mkDriverInfoDm = marketingDriverInfoDao.queryByPhone(map);
            if(mkDriverInfoDm != null){
                if(mkDriverInfoDm.getAllocateStatus() == Constants.ALLOCATE_STATUS_WAIT_KEY){//未分配的司机
                    noAssignList.add(mkDriverInfoDm);
                    if(!"".equals(refreshIds)){
                        refreshIds += ",";
                    }
                    refreshIds += mkDriverInfoDm.getId();
                }else{
                    allocatedNum++;
                }
                continue;//跳过本次循环
            }
            //获得要保存的司机客户对象 并放入需要保存的集合中
            saveList.add(getMKDriverInfo(map, assisterId));
        }
        int sucNum = 0;//批量新增受影响的行数
        if(saveList != null && saveList.size() > 0){
            //批量新增
            sucNum = marketingDriverInfoDao.addBach(saveList);
            if(Constants.IMPORT_DRIVER_TYPE_ORD.equals(importType)){
                //营销人员导入 记录到客户分配记录表 绑定
                addImportDistributRecord(saveList, assisterId);
            }
        }
        rtMap.put("result", "10");
        rtMap.put("refreshIds", refreshIds);
        rtMap.put("allNum", resmap.get(0).size());//总共条数
        rtMap.put("sucNum", sucNum);//导入成功的条数
        rtMap.put("errorNum", resmap.get(0).size() - sucNum);//未导入的条数
        rtMap.put("noAssignNum", noAssignList.size());//数据库存在，但未分配条数
        rtMap.put("allocatedNum", (resmap.get(0).size() - sucNum) - noAssignList.size());//数据库存在，但已分配条数
//        rtMap.put("noAssignList", noAssignList);//未分配的司机
//        rtMap.put("saveList", saveList);//已保存的司机
        if(Constants.IMPORT_DRIVER_TYPE_ORD.equals(importType)){
            //营销人员导入返回html列表
            //获得导入成功的html
            String sucHtml = getSucDriverHtml(saveList, user);
            rtMap.put("sucHtml", sucHtml);
            //获得未分配的html
            String noAssignHtml = getNoAssignHtml(noAssignList);
            rtMap.put("noAssignHtml", noAssignHtml);
        }
        return rtMap;
    }

    //营销人员导入时记录到客户分配记录表 绑定
    private int addImportDistributRecord(List<MarketingDriverInfoDomain> saveList, Integer userId){
        List<Map<String, Object>> paraList = new ArrayList<Map<String, Object>>();
        Map<String, Object> itemMap = null;
        for(MarketingDriverInfoDomain item:saveList){
            itemMap = new HashMap<String, Object>();
            itemMap.put("customerKind", Constants.CUSTOMER_KIND_DRIVER_KEY);
            itemMap.put("mobilePhone", item.getMobilePhone());
            itemMap.put("distributerId", userId);
            itemMap.put("assisterId", userId);
            itemMap.put("customerLevel", item.getRealLevel());
            itemMap.put("distributType", Constants.DISTRIBUT_TYPE_BINDING_KEY);
            paraList.add(itemMap);
        }
        return marketingDistributRecordDao.addBachApplyByMphone(paraList);
    }

    //获得导入成功的html
    private String getSucDriverHtml(List<MarketingDriverInfoDomain> saveList, MarketingUserInfo user){
        String html = "";
        if(saveList == null || saveList.size() == 0){
            html += "<tr>";
            html += "<td colspan='10' align='center'>暂无符合条件的数据</td>";
            html += "</tr>";
            return html;
        }
        String customerTypeName = "未安装";//客户类型
        for(MarketingDriverInfoDomain driverInfo:saveList){
            html += "<tr align='center'>";
            html += getTdHtml(driverInfo.getName());//司机姓名
            html += getTdHtml(driverInfo.getCarNumber());//车牌号
            html += getTdHtml(driverInfo.getMobilePhone());//手机号码
            html += getTdHtml(driverInfo.getLastUserTime());//最近使用时间
            html += getTdHtml(driverInfo.getUser15DayNum());//近15天使用次数
            html += getTdHtml(driverInfo.getLastLocation());//最近定位地点
            html += getTdHtml(driverInfo.getLastLocationTime());//最近定位时间
            html += getTdHtml((StringUtils.isNotEmpty(driverInfo.getRealLevel()) ? driverInfo.getRealLevel() + "类" : ""));//客户等级
            if(driverInfo.getDriverId() != null){
                //司机已注册
                if((Constants.SUBMIT_TYPE_SHYES_KEY+"").equals(driverInfo.getCustomerType())){
                    customerTypeName = "已认证";
                }else{
                    customerTypeName = "未认证";
                }
            }else{
                customerTypeName = "未安装";
            }
            html += getTdHtml(customerTypeName);//客户状态
            html += getTdHtml(user.getName());//所属人
            html += "</tr>";
        }
        return html;
    }

    //获得导入成功后未分配的html
    @Override
    public String getNoAssignHtml(List<MarketingDriverInfoDomain> noAssignList){
        String html = "";
        if(noAssignList == null || noAssignList.size() == 0){
            html += "<tr>";
            html += "<td colspan='10' align='center'>暂无符合条件的数据</td>";
            html += "</tr>";
            return html;
        }
        String customerTypeName = "未安装";//客户类型
        for(MarketingDriverInfoDomain driverInfo:noAssignList){
            html += "<tr align='center'>";
            html += "<td style='width:30px;'>";
            //勾选框
            html += "<input type='checkbox' name='infoIds' value='" + (driverInfo.getId() != null ? driverInfo.getId() : 0) + "' />";
            html += "</td>";
            html += getTdHtml(driverInfo.getName());//司机姓名
            html += getTdHtml(driverInfo.getCarNumber());//车牌号
            html += getTdHtml(driverInfo.getMobilePhone());//手机号码
            html += getTdHtml(driverInfo.getLastUserTime());//最近使用时间
            html += getTdHtml(driverInfo.getUser15DayNum());//近15天使用次数
            html += getTdHtml(driverInfo.getLastLocation());//最近定位地点
            html += getTdHtml(driverInfo.getLastLocationTime());//最近定位时间
            html += getTdHtml((StringUtils.isNotEmpty(driverInfo.getRealLevel()) ? driverInfo.getRealLevel() + "类" : ""));//客户等级
            if(driverInfo.getDriverId() != null){
                //司机已注册
                if((Constants.SUBMIT_TYPE_SHYES_KEY+"").equals(driverInfo.getCustomerType())){
                    customerTypeName = "已认证";
                }else{
                    customerTypeName = "未认证";
                }
            }else{
                customerTypeName = "未安装";
            }
            html += getTdHtml(customerTypeName);//客户状态
            html += "</tr>";
        }
        return html;
    }

    //分配司机资料
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, String> assignDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user) {
        Map<String, String> rtMap = new HashMap<String, String>();
        //获得需要司机资料ids
        String[] driverIds = StringUtils.split(info.getDriverIds(), ",");
        //营销专员id
        Integer assisterId = info.getAssisterId();

        //司机资料修改参数
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("assisterId", assisterId);
        //已分配状态 修改值
        paraMap.put("allocateStatus", Constants.ALLOCATE_STATUS_END_KEY);
        //分配人id 修改值
        paraMap.put("distributerId", user.getId());
        //是否营销人员发展的注册用户：0 否 1 是   修改值
        paraMap.put("regThroughAssist", Constants.REG_THROUGH_ASSIST_NOT);
        //是否营销人员发展的认证用户：0 否 1 是   修改值
        paraMap.put("authThroughAssist", Constants.AUTH_THROUGH_ASSIST_NOT);
        //号码是否有效：0 无效 1 有效   修改值
        paraMap.put("phoneValid", Constants.PHONE_VALID_YES);
        //待分配状态 where条件
        paraMap.put("allocateStatusOld", info.getAllocateStatus());
        paraMap.put("list", driverIds);
        int nums = 0;
        if(info.getAllocateStatus() == Constants.ALLOCATE_STATUS_END_KEY){
            //已分配的页面中点击分配
            //批量新增 司机资料被转移时，记录被转移人的记录 解绑
            int brnums = addDistributRecordBeApply(driverIds, user.getId(), Constants.DISTRIBUT_TYPE_UNBUNDLING_KEY);
            //批量新增 分配时 记录分配给营销人员的记录 绑定
            int rnums = addDistributRecordApply(driverIds, user.getId(), assisterId, Constants.DISTRIBUT_TYPE_BINDING_KEY);
            //根据id、分配状态allocate_status批量修改 直接分配的修改 司机资料表
            nums = marketingDriverInfoDao.updateBachById(paraMap);
            if(nums <= 0 || nums != driverIds.length || nums != rnums || nums != brnums ){
                throw new RuntimeException("分配失败，您勾选的司机客户资料部分已被分配！请刷新或重新查询！");
            }
        }else{
            //待分配的页面中点击分配
            //批量新增 分配时 记录分配给营销人员的记录 绑定
            int rnums = addDistributRecordApply(driverIds, user.getId(), assisterId, Constants.DISTRIBUT_TYPE_BINDING_KEY);
            //根据id、分配状态allocate_status批量修改 直接分配的修改 司机资料表
            nums = marketingDriverInfoDao.updateBachById(paraMap);
            if(nums <= 0 || nums != driverIds.length || nums != rnums ){
                throw new RuntimeException("分配失败，您勾选的司机客户资料部分已被分配！请刷新或重新查询！");
            }
        }
        rtMap.put("result", "10");
        rtMap.put("errNum", (driverIds.length - nums)+"");
        rtMap.put("sucNum", nums+"");
        return rtMap;
    }

    //批量新增 分配时 记录分配给营销人员的记录
    private int addDistributRecordApply(String[] driverIds, Integer distributerId, Integer assisterId, Integer distributType){
        //客户分配记录 新增参数
        List<MarketingDistributRecord> recordList = new ArrayList<MarketingDistributRecord>();
        MarketingDistributRecord record = null;
        for(String id:driverIds){
            record = new MarketingDistributRecord();
            record.setCustomerKind(Constants.CUSTOMER_KIND_DRIVER_KEY);
            record.setCustomerId(new Integer(id));
            record.setDistributerId(distributerId);
            record.setAssisterId(assisterId);
            record.setDistributType(distributType);
            recordList.add(record);
        }
        return marketingDistributRecordDao.addBachApply(recordList);
    }

    //批量新增 司机资料被转移时，记录被转移人的记录
    private int addDistributRecordBeApply(String[] driverIds, Integer distributerId, Integer distributType){
        //客户分配记录 新增参数
        List<MarketingDistributRecord> recordList = new ArrayList<MarketingDistributRecord>();
        MarketingDistributRecord record = null;
        for(String id:driverIds){
            record = new MarketingDistributRecord();
            record.setCustomerKind(Constants.CUSTOMER_KIND_DRIVER_KEY);
            record.setCustomerId(new Integer(id));
            record.setDistributerId(distributerId);
            record.setDistributType(distributType);
            recordList.add(record);
        }
        return marketingDistributRecordDao.addBachBeApply(recordList);
    }

    //分配申请审核
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, String> reviewDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user, String type) {
        Map<String, String> rtMap = new HashMap<String, String>();
        //获得需要司机资料ids
        String[] driverIds = StringUtils.split(info.getDriverIds(), ",");
        int nums = 0;//数据库影响的行数
        if("10".equals(type)){
            //司机客户资料申请 同意的操作
            nums = agree(info, user);
        }else if("20".equals(type)){
            //司机客户资料申请 不同意的操作
            nums = disagree(info, user);
        }else{
            //司机客户资料申请 直接分配给营销人员
            nums = directDistribution(info, user);
        }
        if(nums > 0){
            //修改成功
            if(nums == driverIds.length){
                rtMap.put("result", "10");
            }else{
                rtMap.put("result", "1");
            }
            rtMap.put("errNum", (driverIds.length - nums)+"");
            rtMap.put("sucNum", nums+"");
        }else{
            //修改失败
            rtMap.put("result", "0");
            rtMap.put("message", "失败，系统出错！");
        }
        return rtMap;
    }

    //司机客户资料申请同意的操作
    private int agree(MarketingDriverInfoDomain info, MarketingUserInfo user){
        //获得需要司机资料ids
        String[] driverIds = StringUtils.split(info.getDriverIds(), ",");
        //获得需要客户分配申请表ids
        String[] assisterApplyIds = StringUtils.split(info.getAssisterApplyIds(), ",");
        //客户司机资料表 修改参数
        Map<String, Object> paraMap = new HashMap<String, Object>();
        //已分配状态 修改值
        paraMap.put("allocateStatus", Constants.ALLOCATE_STATUS_END_KEY);
        //分配人id 修改值
        paraMap.put("distributerId", user.getId());
        //号码是否有效：0 无效 1 有效   修改值
        paraMap.put("phoneValid", Constants.PHONE_VALID_YES);
        //分配中状态 where条件
        paraMap.put("allocateStatusOld", Constants.ALLOCATE_STATUS_ING_KEY);
        //id数组 where条件
        paraMap.put("list", driverIds);

        //客户分配申请表 修改参数
        Map<String, Object> applyMap = new HashMap<String, Object>();
        //审核状态审核通过 修改值
        applyMap.put("auditStatus", Constants.AUDIT_STATUS_YES_KEY);
        //审核人id 修改值
        applyMap.put("auditerId", user.getId());
        //id数组 where条件
        applyMap.put("list", assisterApplyIds);
        //根据id、分配状态allocate_status批量修改 同意的修改 司机资料表
        int nums = marketingDriverInfoDao.updateBachById(paraMap);
        //根据id批量修改审核状态audit_status为0 id in () 申请表
        marketingAssisterApplyDao.updateBachById(applyMap);
        //批量新增 司机资料被转移时，记录被转移人的记录 绑定
        int rnums = addDistributRecordBeApply(driverIds, user.getId(), Constants.DISTRIBUT_TYPE_BINDING_KEY);
        if(nums <= 0 || nums != rnums){
            throw new RuntimeException("审核失败，您勾选的司机客户资料部分已被审核！请刷新或重新查询！");
        }
        return nums;
    }

    //司机客户资料申请 不同意的操作
    private int disagree(MarketingDriverInfoDomain info, MarketingUserInfo user){
        //获得需要司机资料ids
        String[] driverIds = StringUtils.split(info.getDriverIds(), ",");
        //获得需要客户分配申请表ids
        String[] assisterApplyIds = StringUtils.split(info.getAssisterApplyIds(), ",");
        //客户司机资料表 修改参数
        Map<String, Object> paraMap = new HashMap<String, Object>();
        //待分配状态 修改值
        paraMap.put("allocateStatus", Constants.ALLOCATE_STATUS_WAIT_KEY);
        //把申请人id去掉 修改值
//        paraMap.put("assisterId", "");
        //分配人id 修改值
//        paraMap.put("distributerId", user.getId());
        //是否营销人员发展的注册用户：0 否 1 是   修改值
        paraMap.put("regThroughAssist", Constants.REG_THROUGH_ASSIST_NOT);
        //是否营销人员发展的认证用户：0 否 1 是   修改值
        paraMap.put("authThroughAssist", Constants.AUTH_THROUGH_ASSIST_NOT);
        //分配中状态 where条件
        paraMap.put("allocateStatusOld", Constants.ALLOCATE_STATUS_ING_KEY);
        //id数组 where条件
        paraMap.put("list", driverIds);

        //客户分配申请表 修改参数
        Map<String, Object> applyMap = new HashMap<String, Object>();
        //审核状态审核未通过 修改值
        applyMap.put("auditStatus", Constants.AUDIT_STATUS_NO_KEY);
        //审核人id 修改值
        applyMap.put("auditerId", user.getId());
        //id数组 where条件
        applyMap.put("list", assisterApplyIds);
        //根据id、分配状态allocate_status批量修改 不同意的修改 司机资料表
        int nums = marketingDriverInfoDao.updateBachByIdDisagree(paraMap);
        //根据id批量修改审核状态audit_status为0 id in () 申请表
        marketingAssisterApplyDao.updateBachById(applyMap);
        return nums;
    }

    //司机客户资料申请 直接分配给营销人员
    private int directDistribution(MarketingDriverInfoDomain info, MarketingUserInfo user){
        //获得需要司机资料ids
        String[] driverIds = StringUtils.split(info.getDriverIds(), ",");
        //获得需要客户分配申请表ids
        String[] assisterApplyIds = StringUtils.split(info.getAssisterApplyIds(), ",");
        //营销人员id
        Integer assisterId = info.getAssisterId();
        if(assisterId == null){
            return -10;
        }
        //客户司机资料表 修改参数
        Map<String, Object> paraMap = new HashMap<String, Object>();
        //已分配状态 修改值
        paraMap.put("allocateStatus", Constants.ALLOCATE_STATUS_END_KEY);
        //号码是否有效：0 无效 1 有效   修改值
        paraMap.put("phoneValid", Constants.PHONE_VALID_YES);
        //申请人id 修改值
        paraMap.put("assisterId", assisterId);
        //分配人id 修改值
        paraMap.put("distributerId", user.getId());
        //分配中状态 where条件
        paraMap.put("allocateStatusOld", Constants.ALLOCATE_STATUS_ING_KEY);
        //id数组 where条件
        paraMap.put("list", driverIds);

        //客户分配申请表 修改参数
        Map<String, Object> paraApplyMap = new HashMap<String, Object>();
        paraApplyMap.put("assisterId", assisterId);//申请人id 修改值判断条件
        paraApplyMap.put("auditerId", user.getId());//审核人id 修改值
        paraApplyMap.put("list", assisterApplyIds);//ids where条件
        //根据id、分配状态allocate_status批量修改 审核环节直接分配的修改
        int nums = marketingDriverInfoDao.updateBachByIdDirect(paraMap);
        //根据id批量修改审核状态audit_status为0 id in () 申请表
        marketingAssisterApplyDao.updateForBachById(paraApplyMap);
        //批量新增 分配时 记录分配给营销人员的记录 绑定
        int rnums = addDistributRecordApply(driverIds, user.getId(), assisterId, Constants.DISTRIBUT_TYPE_BINDING_KEY);
        if(nums <= 0 || nums != rnums){
            throw new RuntimeException("分配失败，您勾选的司机客户资料部分已被分配！请刷新或重新查询！");
        }
        return nums;
    }

    //查询
    @Override
    public List<MarketingDriverInfoDomain> queryDriverListServ(MarketingDriverInfoDomain info, PageInfo page) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        //------查询条件
        //排序方式 默认3按客户司机资料分配时间排序(考虑到我的客户页面的查询)
        String orderType = info.getAllocateStatus() != null ? info.getAllocateStatus().toString() : "3";
        paraMap.put("orderType", orderType);
        paraMap.put("curPage", (page.getCurPage() - 1) * page.getPageSize());
        paraMap.put("pageSize", page.getPageSize());
        //0 未删除 1 已删除
        paraMap.put("deleteFlag", 0);
        //分配状态
        paraMap.put("allocateStatus", info.getAllocateStatus());
        //号码是否有效
        paraMap.put("phoneValid", info.getPhoneValid());
        //客户等级
        paraMap.put("realLevel", info.getRealLevel());
        //客户分类
        paraMap.put("category", info.getCategory());
        //所属人或者申请人
        paraMap.put("assisterId", info.getAssisterIdQuery());
        //手机号码
        paraMap.put("mobilePhone", info.getMobilePhone());
        //车牌号
        paraMap.put("carNumber", info.getCarNumber());
        //姓名
        paraMap.put("name", info.getName());
        //车长
        paraMap.put("carLength", info.getCarLength());
        //板-平板、高低板
        paraMap.put("carPlateType", info.getCarPlateType());
        //栏-高栏、低栏
        paraMap.put("carBarType", info.getCarBarType());
        //运力-吨位
        paraMap.put("carWeight", info.getCarWeight());
        //运力-体积
        paraMap.put("carCubage", info.getCarCubage());
        //0未注册 1已注册
        paraMap.put("optRegister", info.getOptRegister());

        //客户分配条数查询
        page.setTotalRecords(marketingDriverInfoDao.queryListCount(paraMap));

        //客户分配的列表查询
        return marketingDriverInfoDao.queryListPage(paraMap);
    }

    //查询我的客户司机资料
    @Override
    public List<MarketingDriverInfoDomain> queryMyDriverListServ(MarketingDriverInfoDomain info, PageInfo page, PageInfo allDriverPage) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        //------查询条件
        //排序方式 默认3按客户司机资料分配时间排序(考虑到我的客户页面的查询)
        String orderType = info.getAllocateStatus() != null ? info.getAllocateStatus().toString() : "3";
        paraMap.put("orderType", orderType);
        paraMap.put("curPage", (page.getCurPage() - 1) * page.getPageSize());
        paraMap.put("pageSize", page.getPageSize());
        //0 未删除 1 已删除
        paraMap.put("deleteFlag", 0);
        //分配状态
        paraMap.put("allocateStatus", info.getAllocateStatus());
        //客户等级
        paraMap.put("realLevel", info.getRealLevel());
        //客户分类
        paraMap.put("category", info.getCategory());
        //所属人或者申请人
        paraMap.put("assisterId", info.getAssisterIdQuery());
        //手机号码
        paraMap.put("mobilePhone", info.getMobilePhone());
        //车牌号
        paraMap.put("carNumber", info.getCarNumber());
        //姓名
        paraMap.put("name", info.getName());
        //车长
        paraMap.put("carLength", info.getCarLength());
        //板-平板、高低板
        paraMap.put("carPlateType", info.getCarPlateType());
        //栏-高栏、低栏
        paraMap.put("carBarType", info.getCarBarType());
        //运力-吨位
        paraMap.put("carWeight", info.getCarWeight());
        //运力-体积
        paraMap.put("carCubage", info.getCarCubage());
        //0未注册 1已注册
        paraMap.put("optRegister", info.getOptRegister());

        //查询客户总量
        Map<String, Object> paraAllMap = new HashMap<String, Object>();
        paraAllMap.put("deleteFlag", 0);
        paraAllMap.put("assisterId", info.getAssisterIdQuery());
        paraAllMap.put("optRegister", info.getOptRegister());
        allDriverPage.setTotalRecords(marketingDriverInfoDao.queryListCount(paraAllMap));

        //客户分配条数查询
        page.setTotalRecords(marketingDriverInfoDao.queryListCount(paraMap));

        //客户分配的列表查询
        return marketingDriverInfoDao.queryListPage(paraMap);
    }

    //未联系客户页面页面查询
    @Override
    public List<MarketingDriverInfoDomain> enterNoContCustomServ(MarketingDriverInfoDomain info, PageInfo page, MarketingUserInfo user){
        //初始化始化未联系客户的所有客户查询条件和分页信息
        initNoContReq(info, page);
        //查询返回的结果
        List<MarketingDriverInfoDomain> list = null;
        if(Constants.NO_CUSTOM_TYPE_ALL.equals(info.getNoCustomType())){
            //查询未联系客户中所有客户的列表
            list = queryNoContAllListPage(info, page, user);
        }else{
            //查询未联系客户中预约客户的列表
            list = queryNoContMakListPage(info, page, user);
        }
        return list;
    }

    //查询未联系客户中所有客户的列表
    private List<MarketingDriverInfoDomain> queryNoContAllListPage(MarketingDriverInfoDomain info, PageInfo page, MarketingUserInfo user){
        //查询参数
        Map<String, Object> paraMap = new HashMap<String, Object>();
        //查询开始条数
        paraMap.put("curPage", (page.getCurPage() - 1) * page.getPageSize());
        //查询条数
        paraMap.put("pageSize", page.getPageSize());
        //查询未删除的
        paraMap.put("deleteFlag", 0);
        //查询当前登录营销人员id
        paraMap.put("assisterId", user.getId());
        //查询最后联系时间在N个月内的除外
        paraMap.put("monNum",info.getNoCustomMonth());
        //手机号码
        paraMap.put("mobilePhone", info.getMobilePhone());
        //车牌号
        paraMap.put("carNumber", info.getCarNumber());
        //姓名
        paraMap.put("name", info.getName());

        //未联系客户中所有客户条数查询
        page.setTotalRecords(marketingDriverInfoDao.queryNoContAllListCount(paraMap));

        //未联系客户中所有客户的列表查询
        return marketingDriverInfoDao.queryNoContAllListPage(paraMap);
    }

    //查询未联系客户中预约客户的列表
    private List<MarketingDriverInfoDomain> queryNoContMakListPage(MarketingDriverInfoDomain info, PageInfo page, MarketingUserInfo user){
        //查询参数
        Map<String, Object> paraMap = new HashMap<String, Object>();
        //查询开始条数
        paraMap.put("curPage", (page.getCurPage() - 1) * page.getPageSize());
        //查询条数
        paraMap.put("pageSize", page.getPageSize());
        //查询未删除的
        paraMap.put("deleteFlag", 0);
        //查询当前登录营销人员id
        paraMap.put("assisterId", user.getId());
        //查询开始时间
        paraMap.put("startTime", info.getStartNextDate().trim() + " 00:00:00");
        //查询截至时间
        paraMap.put("endTime", info.getEndNextDate().trim() + " 23:59:59");
        //手机号码
        paraMap.put("mobilePhone", info.getMobilePhone());
        //车牌号
        paraMap.put("carNumber", info.getCarNumber());
        //姓名
        paraMap.put("name", info.getName());

        //未联系客户中预约客户条数查询
        page.setTotalRecords(marketingDriverInfoDao.queryNoContMakListCount(paraMap));

        //未联系客户中预约客户的列表查询
        return marketingDriverInfoDao.queryNoContMakListPage(paraMap);
    }

    //初始化始化未联系客户的所有客户查询条件和分页信息
    private void initNoContReq(MarketingDriverInfoDomain info, PageInfo page){
        if(StringUtils.isEmpty(info.getNoCustomType())) {//第一次进入未联系客户页面
            info.setNoCustomType(Constants.NO_CUSTOM_TYPE_ALL);//默认显示所有客户
            info.setNoCustomMonth(-6);//默认半年
            //默认取当前时间
            String curDate = DateUtil.formatDate(new Date(), Constants.DATE_FORMATE_DAY);
            info.setStartNextDate(curDate);
            info.setEndNextDate(curDate);

            page.setCurPage(1);//默认第一页
            page.setPageSize(50);//默认50条
        }
    }

    //获得分配客户页面列表数据html
    @Override
    public String getAssignCustHtml(List<MarketingDriverInfoDomain> driverList, Integer type){
        String html = "";
        String colspan = "10";//未分配页面的列表数
        html += "<thead>";
        html += "<tr align='center'>";
        html += "<td style='width:3%;'>&nbsp;</td>";
        html += "<td style='width:8%;'>司机姓名</td>";
        html += "<td style='width:10%;'>车牌号</td>";
        html += "<td style='width:11%;'>手机号码</td>";
        html += "<td style='width:10%;'>最近使用时间</td>";
        html += "<td style='width:9%;'>近15天使用<br/>次数</td>";
        html += "<td style='width:14%;'>最近定位地点</td>";
        html += "<td style='width:10%;'>最近定位时间</td>";
        html += "<td style='width:8%;'>客户状态</td>";
        html += "<td style='width:9%;'>标记无效<br/>次数</td>";
        switch (type) {
            case Constants.ALLOCATE_STATUS_ING_KEY :
                colspan = "11";
                html += "<td style='width:8%;'>申请人</td>";
                break;
            case Constants.ALLOCATE_STATUS_END_KEY :
                colspan = "11";
                html += "<td style='width:8%;'>所属人</td>";
                break;
            default:
                colspan = "10";
                break;
        }
        html += "</tr>";
        html += "</thead>";
        if(driverList == null || driverList.size() == 0){
            html += "<tr>";
            html += "<td colspan='" + colspan + "' align='center'>暂无符合条件的数据</td>";
            html += "</tr>";
            return html;
        }
        String customerTypeName = "未安装";//客户状态名称
        String markInvalidNums = "";//标记次数
        for(MarketingDriverInfoDomain driverInfo:driverList){
            html += "<tr align='center'>";
            html += "<td>";
            //勾选框
            html += "<input type='checkbox' name='infoIds' value='" + (driverInfo.getId() != null ? driverInfo.getId() : 0) + "' />";
            if(type == Constants.ALLOCATE_STATUS_ING_KEY){
                //隐藏的input 客户分配申请表id
                html += "<input type='hidden' value='" + (driverInfo.getAssisterApplyIds() != null ? driverInfo.getAssisterApplyIds() : 0) + "'/>";
            }
            html += "</td>";
            html += getTdHtml(driverInfo.getName());//司机名称
            html += getTdHtml(driverInfo.getCarNumber());//车牌号
            html += getTdHtml(driverInfo.getMobilePhone());//手机号码
            html += getTdHtml(driverInfo.getLastUserTime());//最近使用时间
            html += getTdHtml(driverInfo.getUser15DayNum());//近15天使用次数
            html += getTdHtml(driverInfo.getLastLocation());//最近定位地点
            html += getTdHtml(driverInfo.getLastLocationTime());//最近定位时间
            if(driverInfo.getDriverId() != null){
                //司机已注册
                if((Constants.SUBMIT_TYPE_SHYES_KEY+"").equals(driverInfo.getCustomerType())){
                    customerTypeName = "已认证";
                }else{
                    customerTypeName = "未认证";
                }
            }else{
                customerTypeName = "未安装";
            }
            html += getTdHtml(customerTypeName);//客户状态
            markInvalidNums = (driverInfo.getMarkInvalidNums() != null ? driverInfo.getMarkInvalidNums() : 0) + "";
            //标记无效次数
            html += "<td title=\""+markInvalidNums+"\" onmouseover=\"on_mouse_over(this,'"+(driverInfo.getId() != null ? driverInfo.getId() : 0)+"')\">";
            html += markInvalidNums;
            html += "</td>";
            if(type == Constants.ALLOCATE_STATUS_ING_KEY
                    || type == Constants.ALLOCATE_STATUS_END_KEY){
                //已分配的客户页面或者申请分配审核页面
                html += getTdHtml(driverInfo.getAssisterName());//所属人或者是申请人
            }
            html += "</tr>";
        }
        return html;
    }

    //获得我的客户列表html
    @Override
    public String getMyCustHtml(List<MarketingDriverInfoDomain> driverList){
        String html = "";
        if(driverList == null || driverList.size() == 0){
            html += "<tr>";
            html += "<td colspan='9' align='center'>暂无符合条件的数据</td>";
            html += "</tr>";
            return html;
        }
        String customerTypeName = "未安装";//客户状态名称
        String openType = "0";//是否注册  0未注册 1已注册
        for(MarketingDriverInfoDomain driverInfo:driverList){
            if(driverInfo.getDriverId() != null){
                openType = "1";
                //司机已注册
                if((Constants.SUBMIT_TYPE_SHYES_KEY+"").equals(driverInfo.getCustomerType())){
                    customerTypeName = "已认证";
                }else{
                    customerTypeName = "未认证";
                }
            }else{
                openType = "0";
                customerTypeName = "未安装";
            }
            html += "<tr align=\"center\" style=\"cursor:pointer;\" onclick=\"openDriverDetails('"+driverInfo.getId()+"','"+openType+"','"+(driverInfo.getDriverId() != null ? driverInfo.getDriverId() : 0)+"')\">";
            html += getTdHtml(driverInfo.getName());//司机名称
            html += getTdHtml(driverInfo.getCarNumber());//车牌号
            html += getTdHtml(driverInfo.getMobilePhone());//手机号码
            html += getTdHtml(driverInfo.getLastContactDate());//最近联系时间
            html += getTdHtml(driverInfo.getLastUserTime());//最近使用时间
            html += getTdHtml(driverInfo.getUser15DayNum());//近15天使用次数
            html += getTdHtml(driverInfo.getLastLocation());//最近定位地点
            html += getTdHtml(driverInfo.getLastLocationTime());//最近定位时间
            html += getTdHtml(customerTypeName);//客户状态
            html += "</tr>";
        }
        return html;
    }

    //获得未联系客户列表html
    @Override
    public String getNoCustHtml(List<MarketingDriverInfoDomain> driverList){
        String html = "";
        if(driverList == null || driverList.size() == 0){
            html += "<tr>";
            html += "<td colspan='8' align='center'>暂无符合条件的数据</td>";
            html += "</tr>";
            return html;
        }
        String customerTypeName = "未安装";//客户状态名称
        String openType = "0";//是否注册  0未注册 1已注册
        for(MarketingDriverInfoDomain driverInfo:driverList){
            if(driverInfo.getDriverId() != null){
                openType = "1";
                //司机已注册
                if((Constants.SUBMIT_TYPE_SHYES_KEY+"").equals(driverInfo.getCustomerType())){
                    customerTypeName = "已认证";
                }else{
                    customerTypeName = "未认证";
                }
            }else{
                openType = "0";
                customerTypeName = "未安装";
            }
            html += "<tr align='center' style=\"cursor:pointer;\" onclick=\"openDriverDetails('"+driverInfo.getId()+"','"+openType+"','"+(driverInfo.getDriverId() != null ? driverInfo.getDriverId() : 0)+"')\">";
            html += getTdHtml(driverInfo.getName());//司机名称
            html += getTdHtml(driverInfo.getCarNumber());//车牌号
            html += getTdHtml(driverInfo.getMobilePhone());//手机号码
            html += getTdHtml(driverInfo.getLastContactDate());//最近联系时间
            html += getTdHtml(driverInfo.getLastUserTime());//最近使用时间
            html += getTdHtml(driverInfo.getUser15DayNum());//近15天使用次数
            html += getTdHtml(driverInfo.getNextContactDate());//预约时间
            html += getTdHtml(customerTypeName);//客户状态
            html += "</tr>";
        }
        return html;
    }

    //获得分页页面的html
    @Override
    public String getPageHtml(PageInfo page){
        String html = "";
        int endPageNum = 1;//尾页
        int upPageNum = 1;//上一页
        int downPageNum = 1;//下一页
        if(page.getTotalPages() < 2){
            endPageNum = 1;
            upPageNum = 1;
        }else{
            endPageNum = page.getTotalPages();
        }
        if(page.getCurPage() <= page.getTotalPages() && page.getCurPage() > 1){
            upPageNum = page.getCurPage()-1;
        }else{
            upPageNum = 1;
        }
        if(page.getCurPage() < page.getTotalPages() && page.getCurPage() > 0){
            downPageNum = page.getCurPage()+1;
        }else{
            if(page.getTotalPages() > 1){
                downPageNum = page.getTotalPages();
            }else{
                downPageNum = 1;
            }
        }
        html += "<dl>";
        html += "<dt><a href=\"javascript:getRealPageInfo(1);\">首页</a></dt>";
        html += "<dt><a href=\"javascript:getRealPageInfo('"+upPageNum+"');\">上一页</a></dt>";
        html += "<dt><a href=\"javascript:getRealPageInfo('"+downPageNum+"');\">下一页</a></dt>";
        html += "<dd><a href=\"javascript:getRealPageInfo('"+endPageNum+"');\">尾页</a></dd>";
        html += "第<input type='text' id='goCurPage' value='"+page.getCurPage()+"' style='width: 30px;text-align: center;'/>页";
        html += "<dt><a href=\"javascript:goSelect();\">GO</a></dt>";
        html += "<dd><a>共"+page.getTotalPages()+"页/"+page.getTotalRecords()+"条信息</a></dd>";
        html += "</dl>";
        return html;
    }

    private String getTdHtml(String val){
        return "<td title='" + val + "'>" + val + "</td>";
    }

    /** 按手机号码排序并去手机号码为空、去重复、去无效格式的手机号码并返回新的集合 @author wyh */
    private List<Map<String, String>> sortRemoveLikeData(List<Map<String, String>> list, String keyStr){
        final String key = keyStr;
        //排序
        Collections.sort(list,new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                return o1.get(key).compareTo(o2.get(key));
            }
        });
        List<Map<String, String>> rtList = new ArrayList<Map<String, String>>();
        //去重复
        for(int i = 0;i < list.size();i++){
            if(StringUtils.isEmpty(list.get(i).get(keyStr))
                    || !ValidateUtil.validateTelePhone(list.get(i).get(keyStr))){
                continue;
            }
            if(rtList.size() != 0){
                //取出新集合rtList最后一个对象与当前要入的的对象进行比较
                if((rtList.get(rtList.size()-1).get(keyStr)).equals(list.get(i).get(keyStr))){
                    //相同的内容跳过本次循环
                    continue;
                }
            }
            rtList.add(list.get(i));
        }
        return rtList;
    }

    //获得要保存的司机客户对象 @autor wyh
    private MarketingDriverInfoDomain getMKDriverInfo(Map<String, String> paraMap, Integer assisterId){
        String realLevel = Constants.REAL_LEVEL_D;//司机客户等级 默认D类
        String name = paraMap.get("name") != null ? paraMap.get("name") : "";//司机姓名
        String mobilePhone = paraMap.get("mobilePhone");//手机号码
        String carNumber = paraMap.get("carNumber") != null ? paraMap.get("carNumber").toUpperCase() : "";//车牌号 并转大写
        MarketingDriverInfoDomain mkDriverInfoDm = new MarketingDriverInfoDomain();
        //根据手机号码查询，计数司机运营线路条数，计数司机活跃记录表的条数 查询司机注册信息表
//        DriverUserInfoDomain driverUserInfo = driverUserInfoDao.queryByPhone(paraMap);
//        if(driverUserInfo != null){
//            if((Constants.SUBMIT_TYPE_SHYES_KEY+"").equals(driverUserInfo.getSubmitType())){
//                //已认证完成的司机 客户等级A类
//                realLevel = Constants.REAL_LEVEL_A;
//            }else if(driverUserInfo.getCountDriverLine() > 0){
//                //至少有一条运营线路的司机 客户等级B类
//                realLevel = Constants.REAL_LEVEL_B;
//            }else if(driverUserInfo.getCountMonAct() > 0){
//                //月存活（一个月内APP有操作过） 客户等级C类
//                realLevel = Constants.REAL_LEVEL_C;
//            }
//            if(StringUtils.isNotEmpty(driverUserInfo.getName())){
//                name = driverUserInfo.getName();
//            }
//            if(StringUtils.isNotEmpty(driverUserInfo.getCarNumber())){
//                carNumber = driverUserInfo.getCarNumber();
//            }
//            mkDriverInfoDm.setDriverId(driverUserInfo.getId());
//            mkDriverInfoDm.setLastLocation(driverUserInfo.getLastLocation());
//            mkDriverInfoDm.setLastLocationTime(driverUserInfo.getLastTime());
//            mkDriverInfoDm.setUser15DayNum(driverUserInfo.getUser15DayNum());
//            mkDriverInfoDm.setLastUserTime(driverUserInfo.getUsedRecentTime());
//            mkDriverInfoDm.setCustomerType(driverUserInfo.getSubmitType());
//        }
        mkDriverInfoDm.setRealLevel(realLevel);
        mkDriverInfoDm.setName(name);
        mkDriverInfoDm.setCarNumber(carNumber);
        mkDriverInfoDm.setMobilePhone(mobilePhone);
        if(assisterId != null){//分配人id
            //已分配
            mkDriverInfoDm.setAllocateStatus(Constants.ALLOCATE_STATUS_END_KEY);
            mkDriverInfoDm.setAssisterId(assisterId);
        }else{
            //待分配
            mkDriverInfoDm.setAllocateStatus(Constants.ALLOCATE_STATUS_WAIT_KEY);
        }
        return mkDriverInfoDm;
    }

    @Override
    public MarketingDriverInfoDomain queryDriverInfoDomainByDriverId(Integer driverId) {
        return marketingDriverInfoDao.queryDriverInfoDomainByDriverId(driverId);
    }

    @Override
    public boolean updateMarketingDriverInfoSetMobilePhone(MarketingDriverInfo marketingDriverInfo) {
        return marketingDriverInfoDao.updateMarketingDriverInfoSetMobilePhone(marketingDriverInfo);
    }

    @Override
    public boolean updateMarketingDriverInfoSetCategory(MarketingDriverInfo marketingDriverInfo) {
        return marketingDriverInfoDao.updateMarketingDriverInfoSetCategory(marketingDriverInfo);
    }

    @Override
    public boolean updateMarketingDriverInfoSetMarkInvalidNums(MarketingDriverInfo marketingDriverInfo) {
        return marketingDriverInfoDao.updateMarketingDriverInfoSetMarkInvalidNums(marketingDriverInfo);
    }

    @Override
    public MarketingDriverInfoDomain queryMarketingDriverInfoDomainById(Integer id) {
        return marketingDriverInfoDao.queryMarketingDriverInfoDomainById(id);
    }


    @Override
    public boolean updateDriverUserInfoSetOftenCity(MarketingDriverInfo marketingDriverInfo) {
        return marketingDriverInfoDao.updateMarketingDriverInfoSetOftenCity(marketingDriverInfo);
    }

    @Override
    public boolean updateMarketingDriverInfoNoById(MarketingDriverInfo marketingDriverInfo) {
        return marketingDriverInfoDao.updateMarketingDriverInfoSetDriverInfo(marketingDriverInfo);
    }

    @Override
    public boolean updateDriverUserInfoByDriverId(DriverUserInfo driverUserInfo) {
        return driverUserInfoDao.updateDriverUserInfoSetDriverInfo(driverUserInfo);
    }

    @Override
    public boolean updateMarketingDriverInfoSetContactDate(MarketingDriverInfo marketingDriverInfo) {
        return marketingDriverInfoDao.updateMarketingDriverInfoSetContactDate(marketingDriverInfo);
    }

    @Override
    public MarketingDriverInfoDomain queryMarketingDriverInfoDomainNoById(Integer id) {
        return marketingDriverInfoDao.queryMarketingDriverInfoDomainNoById(id);
    }
}
