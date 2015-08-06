package tf56.contract.services;
import java.util.List;
import java.util.Map;

import tf56.contract.domain.CityDistance;
import tf56.contract.domain.Waybill;
public interface WaybillDao {
   public String insert(Waybill waybill); //增
   public String delete(Map map); //删
   public String update(Waybill waybill); //改
   public String selectById(Map map);  //查Bean
   
   /**
    * 修改接单管理
    * xianjing.ni
    * 2013-12-13
    * @param waybill
    * @return
    */
   public String updateWaybill(Waybill waybill);
   /**
    * 查运单list
    * hcm
    * @param map(partyid毕传)
    * @return
    */
   public List selectList(Map map);  //查List
   public String selectCount(Map map);
   public String waybillDistribution(Map map);
   
   /**
	 * @author haoy
	 * @date 14-02-10
	 * 查找某个发货方的所有运单的货物的总数量、总重量和总体积
	 */
   public Map selectGoodsCount(Map map);
   /***
    * 应付or应收结算管理结算单列表
    * @author lianggui.zhou
    * @date 2013-11-18
    * @param map(参数说明：partyid:总包会员partyId，fromPartyId:发货方会员partyId，
    * wayBillNumber：运单号，clientNumber：客户号：fromDate，toDate:托运日期,inOrOut:应收or应付) 
    * @return
    */
   public List addList(Map map);
   /***
    * 统计加入结算单行列中的运单总金额,及运单最早日期，最晚日期
    * @author lianggui.zhou
    * @date 2013-11-18
    * @param map(参数说明：waybillids：运单id集合，inorout:应收or应付
    * @return
    */
   public Map statisticsWaybillAmount(Map map);
   /***
    * 运单加加入结算单行列成功后更新运单结算单号
    * @author lianggui.zhou
    * @date 2013-11-18
    * @param map(参数说明：waybillids：运单id集合，settleBillId:结算单号
    * @return
    */
   public String updateWayBillSettleBillNumber(Map map);
   /***
    * 根据结算单查询已加入结算管理的运单
    * @author lianggui.zhou
    * @date 2013-11-19
    * @param settleBillId：结算单Id
    * @return
    */
   public List selectAddedList(Map settleBillId);
   //运单配载
   //public List selectStowageList(Map waybillId);
	/***
	 * 删除结算单下的相关运单
	 * @author lianggui.zhou
	 * @date 2013-11-20
	 * @param settleBillId：结算单Id,waybillId:运单id
	 * @return
	 */
   public String deleteWayBillOfSettleBill(Map map);
   
	/***
	 * 统计结算单下的相关运单总资费
	 * @author lianggui.zhou
	 * @date 2013-11-20
	 * @param settleBillId：结算单Id,waybillId:运单id
	 * @return
	 */
   public Map statisticsAmountBySettleBillId(Map map);
	/***
	 * 结算单下的相关运单列表
	 * @author lianggui.zhou
	 * @date 2013-11-22
	 * @param settleBillId：结算单Id,inorout:应付or应收
	 * @return
	 */
   public List wayBillOfSettleBillList(Map map);
   /***
    * 结算单下的相关运单列表
    * @author yaoyan.lin
    * @date 2013-12-18
    * @param settleBillId：结算单Id,inorout:应付or应收,waybillnumber:运单号,clientnumber:客户单号
    * @return
    */
   public List wayBillOfSettleBillListWithCondition(Map map);
   /**
    * 查询运单详情 （费用录入详情）
    * @param hcm
    * @return
    */
   public Waybill selectWaybillById(Map map);
   /**
    * @author wei.huang
    * @date 2013-11-22
    * @function 根据settlebillid查询包括Waybill,Goods,WaybillAmount三张表的联合信息
    * @param settlebillid
    * @return Json字符串
    */
   public String selectMixedInfBySettleBillId(Map map);
   /***
    * 删除结算单时候更新运单结算单Id字段为Null
    * @author lianggui.zhou
    * @date 2013-11-23
    * @param settlebillid:结算单ID,inOrOut:应付or应收
    * @return
    */
   public String updateWayBillOfSettleBill(String settlebillid,String inOrOut);
   /***
    * 运单装车确认
    * @author yao.xia
    * @date 2013-11-25
    * @param
    * @return
    */
   public String load(Map map);
   /***
    * 运单发车确认
    * @author yao.xia
    * @date 2013-11-25
    * @param waybillid:运单id
    * @return
    */
   public String start(Map map);
   /***
    * 运单到车确认
    * @author yao.xia
    * @date 2013-11-25
    * @param waybillid:运单id
    * @return
    */
   public String arrive(Map map);
   /**
    * @author wei.huang
    * @date 2013-11-27
    * @function 查询符合报表要求的运单明细表(尚不包含应收应付金额)
    * @param map:一定包括partyid,skipCount和pageSize,可能含有参数(waybillnumber,status,frompartyid和topartyid:以","联接的字符串,startdate,enddate,consignorprovince,consignorcity,consignorregion,consigneeprovince,consigneecity,consigneeregion)
    * @return List
    */
   public List selectWaybillListForReport(Map map);
  
   /**
    * 查询某条结算单下的所有运单信息
    * @author lianggui.zhou
    * @date 2013-11-30
    * @param map{"settlebillid":"结算单id","inorout":"0 or 1"}
    * @return List<Map>
    */
   public List<Map> selectWaybillsOfSettBill(Map map);
   
   /**
    * @author wei.huang
    * @date 2013-11-29
    * @function 获取同一partyId下的不重复的以"-"连接的frompartyid或topartyid的字符串
    * @param partyid 总包会员的id
    * @param fromorto 0=“分包商”，1=“发货方”
    * @return
    */
   public String selectNoRepeatFromOrToPartyId(String partyid,int fromorto);
   /**
    * @author wei.huang
    * @date 2013-12-30
    * @function 获取不重复的以"-"连接的partyid的字符串
    * @return
    */
   public String selectNoRepeatPartyId();
   /**
    * @author wei.huang
    * @date 2013-12-30
    * @function 获取指定总包下不重复的以"-"连接的frompartyid的字符串
    * @return
    */
   public String selectNoRepeatFromPartyId(String partyid);
   /**
    * @author nixainjing
    * @date 2013-12-4
    * @param 根据运单号号查询
    * @param waybillnumber
    * @return
    */
   public String selectWaybillNumber(String waybillnumber);
   
   /**
    * nixianjing
    * 
    * 根据客户号模糊查询最新运单号
    * @param map
    * @return
    */
   public List selectWaybillNumberList(Map map);
   
   /**
	 * 获取总包会员的一个发货方
	 * @author haoyong
	 * @date 2013-12-05
	 * @return
	 */
   public String selectFrompartyid(Map map);
   
   /**
	 * 获取发货方的分包商（城区间距离维护+为维护但是已关联的）
	 * @param map
	 * @return
	 */
	public List<CityDistance> selectSubContractorList(Map map);
	
	/**
	 * @author haoyong
	 * @date 13-12-18
	 * @function 根据调度单查寻运单
	 * @param map
	 * @return
	 */
	public List selectWaybillByStowageId(Map map);
	
	/**
	 * @author haoyong
	 * @date 13-12-19
	 * @function 判断同一张调度单的运单状态是否一致，如果一致则改变调度单状态。
	 * @param map
	 * @return
	 */
	public Map checkWaybillStatus(Map map);
	
	/**
	 * @author xianjing.ni
	 * @date 13-12-19
	 * @function 根据调度单查寻运单(包括货物信息)
	 * @param map
	 * @return
	 */
	public List selectWaybillGoodsByStowageId(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-12-28
	 * @function 获取总包层面的货物统计信息
	 * @return
	 */
	public List selectStatistic_Contract_Goods(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-12-28
	 * @function 获取总包层面的费用统计信息
	 * @return
	 */
	public List selectStatistic_Contract_Amount(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取发货方层面的货物统计信息
	 * @return
	 */
	public List selectStatistic_Consignor_Goods(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取发货方层面的费用统计信息
	 * @return
	 */
	public List selectStatistic_Consignor_Amount(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取运单层面的货物统计信息
	 * @return
	 */
	public List selectStatistic_Waybill_Goods(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取运单层面的费用统计信息
	 * @return
	 */
	public List selectStatistic_Waybill_Amount(Map map);
	/***
     * 统计昨日发货票数
     * @author yao.xia
     * @param map(partyid,description)
     * @return 昨日发货票数
     */
    public String selectYestodayCount(Map map);
    /***
     * 统计本月共计发货票数
     * @author yao.xia
     * @param map(organization,description)
     * @return 本月共计发货票数
     */
    public String selectMouthCount(Map map);
    
    /**
	 * @author yaoyan.lin
	 * @date 2014-02-18
	 * @function 结算信息
	 * @map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 */
	public List selectExportList(Map map);
}