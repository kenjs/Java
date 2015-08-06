package tf56.contract.services.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tf56.contract.domain.CityDistance;
import tf56.contract.domain.Goods;
import tf56.contract.domain.SettleStepSet;
import tf56.contract.domain.Waybill;
import tf56.contract.domain.WaybillSign;
import tf56.contract.services.GoodsDao;
import tf56.contract.services.SettleStepSetDao;
import tf56.contract.services.WaybillAmountDao;
import tf56.contract.services.WaybillDao;
import tf56.contract.services.WaybillLogDao;
import tf56.contract.services.WaybillSignDao;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;

public class WaybillDaoImpl extends SqlMapClientDaoSupport implements WaybillDao{
	public String insert(Waybill waybill) { // 增 实现类
		String waybillid = "", msg = "ok";
		try {
			waybillid = (String) getSqlMapClientTemplate().insert("iBatisInsertWaybill", waybill);
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg, waybillid);
		return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
	}

	public String delete(Map map) { // 删 实现类
		String msg = "ok";
		try {
			int i = getSqlMapClientTemplate().delete("iBatisDeleteWaybill", map);
			if (i != 1) {
				msg = "删除失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg, "");
		return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
	}

	public String update(Waybill waybill) { // 改 实现类
		String waybillid = "", msg = "ok";
		try {
			int i = getSqlMapClientTemplate().update("iBatisUpdateWayBill",waybill);
			if (i != 1) {
				msg = "更新失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg, "");
		return msgJson;
	}

	public String selectById(Map map) { // 查询数据到Bean,然后转成map
		String msgJson = "";
		try {
			Waybill waybill = (Waybill) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillById", map);
			if (waybill != null) {
				msgJson = JsonGenerateUtil.bean2json(waybill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgJson;
	}

	public List selectList(Map map) { // 查询数据到Bean,然后转成map
		List list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @author haoy
	 * @date 14-02-10
	 * 查找某个发货方的所有运单的货物的总数量、总重量和总体积
	 */
	public Map selectGoodsCount(Map map){
		Map resMap = null;
		try {
			resMap = (Map) this.getSqlMapClientTemplate().queryForObject("iBatisSelectGoodsCountByFromPartyId", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	/**
	 * 查询的记录条数
	 */
	public String selectCount(Map map) {
		String count = "";
		try {
			count = this.getSqlMapClientTemplate().queryForObject("iBatisSelectCount", map).toString();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public Waybill selectWaybillById(Map map) {
		Waybill waybill = new Waybill();
		try {
			String waybillid = map.get("waybillid").toString();
			if (StringUtils.isNotBlank(waybillid)) {
				waybill = (Waybill) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillById", map);
				GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao"); // 调用接口(实现类)
				WaybillAmountDao waybillAmountDao = (WaybillAmountDao) SofaSpringContext.getBean("waybillAmountDao"); // 调用接口(实现类)
				WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao"); // 调用接口(实现类)
				WaybillSignDao waybillSignDao = (WaybillSignDao) SofaSpringContext.getBean("waybillSignDao"); // 调用接口(实现类)
				SettleStepSetDao settleStepSetDao = (SettleStepSetDao) SofaSpringContext.getBean("settleStepSetDao"); // 调用接口(实现类)

				List<Goods> goodslist = goodsDao.selectGoodsList(waybillid);
				List amountList = waybillAmountDao.selectWaybillAmountList(waybillid);
				List logList = waybillLogDao.selectWaybillLogList(waybillid);
				WaybillSign waybillSign = waybillSignDao.selectWaybillSignById(map);

				waybill.setGoodslist(goodslist);
				waybill.setWaybillamountlist(amountList);
				waybill.setWaybillloglist(logList);
				waybill.setWaybillsign(waybillSign);
				double receivablefreightsum = 0;
				double payablefreightsum = 0;
				int i = 0,k = 0;
				// 自动计算应收与应付运费
				if (waybill != null) {
					List<Waybill> inList = (List<Waybill>) this.getSqlMapClientTemplate().queryForList("iBatisIncomeById", map);
					List<Waybill> outList =(List<Waybill>)this.getSqlMapClientTemplate().queryForList("iBatisExpenditureById", map);
					Waybill waybillIncome = null;
					Waybill waybillExpenditure = null;
					if(inList.size()>0){
						waybillIncome = inList.get(0);
					}
					if(outList.size()>0){
						waybillExpenditure = outList.get(0);
					}
					double distance = 0;
					if (StringUtils.isNotBlank(waybill.getDistance())&& (Double.parseDouble(waybill.getDistance())) > 0) {
						distance = Double.parseDouble(waybill.getDistance());
					}
					
					//计算标准报价 费用
					for (Goods goods : goodslist) {
						double receivablefreight = 0;
						double payablefreight = 0;
						double weight = 0;
						double volume = 0;
						i++;
						// 数值有效性判断
						if (StringUtils.isNotBlank(goods.getFactweight())&& Double.parseDouble(goods.getFactweight()) > 0) {
							weight = Double.parseDouble(goods.getFactweight());
						}
						if (StringUtils.isNotBlank(goods.getFactvolume())&& Double.parseDouble(goods.getFactvolume()) > 0) {
							volume = Double.parseDouble(goods.getFactvolume());
						}
						//计算应收
						if (waybillIncome != null) {
							double eachweightprice = 0;
							double eachvolumeprice = 0;
							double eachtonkilometerprice = 0;
							double eachcarprice = 0;
							double eachcubekilometerprice = 0;
							if (StringUtils.isNotBlank(waybillIncome.getEachweightprice())&& (Double.parseDouble(waybillIncome.getEachweightprice())) > 0) {
								eachweightprice = Double.parseDouble(waybillIncome.getEachweightprice());
							}
							if (StringUtils.isNotBlank(waybillIncome.getEachvolumeprice())&& (Double.parseDouble(waybillIncome.getEachvolumeprice())) > 0) {
								eachvolumeprice = Double.parseDouble(waybillIncome.getEachvolumeprice());
							}
							if (StringUtils.isNotBlank(waybillIncome.getEachtonkilometerprice())&& (Double.parseDouble(waybillIncome.getEachtonkilometerprice())) > 0) {
								eachtonkilometerprice = Double.parseDouble(waybillIncome.getEachtonkilometerprice());
							}
							if (StringUtils.isNotBlank(waybillIncome.getEachcarprice())&& (Double.parseDouble(waybillIncome.getEachcarprice())) > 0) {
								eachcarprice = Double.parseDouble(waybillIncome.getEachcarprice());
							}
							if (StringUtils.isNotBlank(waybillIncome.getEachcubekilometerprice())&& (Double.parseDouble(waybillIncome.getEachcubekilometerprice())) > 0) {
								eachcubekilometerprice = Double.parseDouble(waybillIncome.getEachcubekilometerprice());
							}
							if ("按重量计费".equals(goods.getMeasuretype())) {
								receivablefreight = weight * eachweightprice;
							}else if ("按体积计费".equals(goods.getMeasuretype())) {
								receivablefreight = volume * eachvolumeprice;
							}else if ("按整车计费".equals(goods.getMeasuretype())) {
								receivablefreight = eachcarprice;
							}else if ("按方公里计费".equals(goods.getMeasuretype())) {
								receivablefreight = volume * distance* eachcubekilometerprice;
							}else if ("按吨公里计费".equals(goods.getMeasuretype())){
								receivablefreight = weight/1000 * distance* eachtonkilometerprice;
							}
						}
						//计算应付
						if (waybillExpenditure != null) {
							double eachweightprice = 0;
							double eachvolumeprice = 0;
							double eachtonkilometerprice = 0;
							double eachcarprice = 0;
							double eachcubekilometerprice = 0;
							if (StringUtils.isNotBlank(waybillExpenditure.getEachweightprice())&& (Double.parseDouble(waybillExpenditure.getEachweightprice())) > 0) {
								eachweightprice = Double.parseDouble(waybillExpenditure.getEachweightprice());
							}
							if (StringUtils.isNotBlank(waybillExpenditure.getEachvolumeprice())&& (Double.parseDouble(waybillExpenditure.getEachvolumeprice())) > 0) {
								eachvolumeprice = Double.parseDouble(waybillExpenditure.getEachvolumeprice());
							}
							if (StringUtils.isNotBlank(waybillExpenditure.getEachtonkilometerprice())&& (Double.parseDouble(waybillExpenditure.getEachtonkilometerprice())) > 0) {
								eachtonkilometerprice = Double.parseDouble(waybillExpenditure.getEachtonkilometerprice());
							}
							if (StringUtils.isNotBlank(waybillExpenditure.getEachcarprice())&& (Double.parseDouble(waybillExpenditure.getEachcarprice())) > 0) {
								eachcarprice = Double.parseDouble(waybillExpenditure.getEachcarprice());
							}
							if (StringUtils.isNotBlank(waybillExpenditure.getEachcubekilometerprice())&& (Double.parseDouble(waybillExpenditure.getEachcubekilometerprice())) > 0) {
								eachcubekilometerprice = Double.parseDouble(waybillExpenditure.getEachcubekilometerprice());
							}
							if ("按重量计费".equals(goods.getMeasuretype())) {
								payablefreight = weight * eachweightprice;
							} else if ("按体积计费".equals(goods.getMeasuretype())) {
								payablefreight = volume * eachvolumeprice;
							}else if ("按整车计费".equals(goods.getMeasuretype())) {
								payablefreight = eachcarprice;
							}else if ("按方公里计费".equals(goods.getMeasuretype())) {
								payablefreight = volume * distance* eachcubekilometerprice;
							}else if ("按吨公里计费".equals(goods.getMeasuretype())){
								payablefreight = weight/1000 * distance* eachtonkilometerprice;
							}
						}
						if ("按整车计费".equals(goods.getMeasuretype())) {
							k++;
							receivablefreightsum = receivablefreight;
							payablefreightsum = payablefreight;
						}else{
							receivablefreightsum = receivablefreightsum+ receivablefreight;
							payablefreightsum = payablefreightsum+ payablefreight;
						}
					}
					
					//判断是否是阶梯报价
					List<SettleStepSet> inSettleStepSetList = null;
					List<SettleStepSet> outSettleStepSetList = null;
					Map mapTemp = new HashMap<String, String>();
					double eachweightprice = 0;
					double eachvolumeprice = 0;
					double effectiveWeight = 0;
					double effectiveVolume = 0;
					for(Goods goods : goodslist){
						double weight = 0;
						double volume = 0;
						// 数值有效性判断
						if (StringUtils.isNotBlank(goods.getFactweight())&& Double.parseDouble(goods.getFactweight()) > 0) {
							weight = Double.parseDouble(goods.getFactweight());
						}
						if (StringUtils.isNotBlank(goods.getFactvolume())&& Double.parseDouble(goods.getFactvolume()) > 0) {
							volume = Double.parseDouble(goods.getFactvolume());
						}
						//取有效重量和体积
						if ("按重量计费".equals(goods.getMeasuretype())) {
							effectiveWeight = effectiveWeight+weight;
						} else if ("按体积计费".equals(goods.getMeasuretype())) {
							effectiveVolume = effectiveVolume+volume;
						}
					}
					//阶梯报价 应收
					if(waybillIncome != null){
						if("阶梯报价".equals(waybillIncome.getReporttype())){
							for(Waybill inTemp:inList){
								mapTemp.put("settlesetid", inTemp.getSettlesetid());
								inSettleStepSetList = settleStepSetDao.selectList(mapTemp);
								if("按重量计费".equals(inTemp.getBilltype())){
									//取对应重量单价
									for(SettleStepSet temp:inSettleStepSetList){
										double startvalue = 0;
										double endvalue = 0;
										if (StringUtils.isNotBlank(temp.getStartvalue())&& Double.parseDouble(temp.getStartvalue()) >= 0) {
											startvalue = Double.parseDouble(temp.getStartvalue());
										}
										if (StringUtils.isNotBlank(temp.getEndvalue())&& Double.parseDouble(temp.getEndvalue()) > 0) {
											endvalue = Double.parseDouble(temp.getEndvalue());
										}
										if(effectiveWeight<=endvalue||endvalue == 0){
											if(effectiveWeight>startvalue){
												double unitprice = 0;
												if (StringUtils.isNotBlank(temp.getUnitprice())&& Double.parseDouble(temp.getUnitprice()) > 0) {
													unitprice = Double.parseDouble(temp.getUnitprice());
												}
												eachweightprice = unitprice;
											}
										}
									}
								}else if ("按体积计费".equals(inTemp.getBilltype())) {
									//取对应体积单价
									for(SettleStepSet temp:inSettleStepSetList){
										double startvalue = 0;
										double endvalue = 0;
										if (StringUtils.isNotBlank(temp.getStartvalue())&& Double.parseDouble(temp.getStartvalue()) > 0) {
											startvalue = Double.parseDouble(temp.getStartvalue());
										}
										if (StringUtils.isNotBlank(temp.getEndvalue())&& Double.parseDouble(temp.getEndvalue()) > 0) {
											endvalue = Double.parseDouble(temp.getEndvalue());
										}
										if(effectiveVolume<=endvalue||endvalue == 0){
											if(effectiveVolume>startvalue){
												double unitprice = 0;
												if (StringUtils.isNotBlank(temp.getUnitprice())&& Double.parseDouble(temp.getUnitprice()) > 0) {
													unitprice = Double.parseDouble(temp.getUnitprice());
												}
												eachvolumeprice = unitprice;
											}
										}
									}
								}
							}
							receivablefreightsum = effectiveWeight*eachweightprice+effectiveVolume*eachvolumeprice;
						}
					}
					//阶梯报价 应付
					if(waybillExpenditure !=null){
						if("阶梯报价".equals(waybillExpenditure.getReporttype())){
							eachweightprice = 0;
							eachvolumeprice = 0;
							for(Waybill outTemp:outList){
								mapTemp.put("settlesetid", outTemp.getSettlesetid());
								outSettleStepSetList = settleStepSetDao.selectList(mapTemp);
								if("按重量计费".equals(outTemp.getBilltype())){
									//取对应重量单价
									for(SettleStepSet temp:outSettleStepSetList){
										double startvalue = 0;
										double endvalue = 0;
										if (StringUtils.isNotBlank(temp.getStartvalue())&& Double.parseDouble(temp.getStartvalue()) > 0) {
											startvalue = Double.parseDouble(temp.getStartvalue());
										}
										if (StringUtils.isNotBlank(temp.getEndvalue())&& Double.parseDouble(temp.getEndvalue()) > 0) {
											endvalue = Double.parseDouble(temp.getEndvalue());
										}
										if(effectiveWeight<=endvalue||endvalue == 0){
											if(effectiveWeight>startvalue){
												double unitprice = 0;
												if (StringUtils.isNotBlank(temp.getUnitprice())&& Double.parseDouble(temp.getUnitprice()) > 0) {
													unitprice = Double.parseDouble(temp.getUnitprice());
												}
												eachweightprice = unitprice;
											}
										}
									}
								}else if ("按体积计费".equals(outTemp.getBilltype())) {
									//取对应体积单价
									for(SettleStepSet temp:outSettleStepSetList){
										double startvalue = 0;
										double endvalue = 0;
										if (StringUtils.isNotBlank(temp.getStartvalue())&& Double.parseDouble(temp.getStartvalue()) > 0) {
											startvalue = Double.parseDouble(temp.getStartvalue());
										}
										if (StringUtils.isNotBlank(temp.getEndvalue())&& Double.parseDouble(temp.getEndvalue()) > 0) {
											endvalue = Double.parseDouble(temp.getEndvalue());
										}
										if(effectiveVolume<=endvalue||endvalue == 0){
											if(effectiveVolume>startvalue){
												double unitprice = 0;
												if (StringUtils.isNotBlank(temp.getUnitprice())&& Double.parseDouble(temp.getUnitprice()) > 0) {
													unitprice = Double.parseDouble(temp.getUnitprice());
												}
												eachvolumeprice = unitprice;
											}
										}
									}
								}
							}
							payablefreightsum = effectiveWeight*eachweightprice+effectiveVolume*eachvolumeprice;
						}
					}
				}
				//校验数据 是否有误 同一单货物必须都是 按整车计算 或都不是
				if(k>0&&k<i){
					receivablefreightsum = 0;
					payablefreightsum = 0;
				}
				waybill.setReceivablefreight(receivablefreightsum + "");
				waybill.setPayablefreight(payablefreightsum + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waybill;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-22
	 * @function 根据settlebillid查询包括Waybill,Goods,WaybillAmount三张表的联合信息
	 * @param settlebillid
	 * @return Json字符串
	 */
    public String selectMixedInfBySettleBillId(Map map) {
        List<Map> list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectMixedInfBySettleBillId", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String msg = JsonGenerateUtil.getListJson(list);
        return msg;
    }

	/***
	 * 应付or应收结算管理结算单列表
	 * @author lianggui.zhou
	 * @date 2013-11-18
	 * @param map(参数说明：partyId:总包会员partyId，fromPartyId:发货方会员partyId，wayBillNumber：运单号，
	 * clientNumber：客户号：fromDate，toDate:托运日期，inOrOut:应收or应付) 
	 * @return
	 */
    public List addList(Map map) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = this.getSqlMapClientTemplate().queryForList("iBatisAddList", map);
        return list;
    }
	/***
	 * 统计加入结算单行列中的运单总金额,及运单最早日期，最晚日期
	 * @author lianggui.zhou
	 * @date 2013-11-18
	 * @param map(参数说明：waybillids：运单id集合，inorout:应收or应付
	 * @return
	 */
    public Map statisticsWaybillAmount(Map map) {
        Map result = (Map) this.getSqlMapClientTemplate().queryForObject("iBatisStatisticsWaybillAmount", map);
        return result;
    }
	/***
	 * 运单加加入结算单行列成功后更新运单结算单号
	 * @author lianggui.zhou
	 * @date 2013-11-18
	 * @param map(参数说明：waybillids：运单id集合，settleBillId:结算单号
	 * @return
	 */
    public String updateWayBillSettleBillNumber(Map map) {
        String msg = "sorry";
        try {
            int i = this.getSqlMapClientTemplate().update("iBatisUpdateWayBillSettleBillNumber", map);
            if (i > 0) {
                msg = "ok";
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return msg;
    }
	/***
	 * 根据结算单查询已加入结算管理的运单
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * @param settleBillId：结算单Id
	 * @return
	 */
    public List selectAddedList(Map map) {
        List list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectAddedList", map);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
	/***
	 * 删除结算单下的相关运单
	 * @author lianggui.zhou
	 * @date 2013-11-20
	 * @param settleBillId：结算单Id,waybillId:运单id
	 * @return
	 */
    public String deleteWayBillOfSettleBill(Map map) {
        String msg = "ok";
        try {
            int i = this.getSqlMapClientTemplate().update("iBatisDeleteWayBillOfSettleBill", map);
            if (i <= 0) {
                msg = "sorry";
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return msg;
    }
	/***
	 * 结算单下的相关运单列表
	 * @author lianggui.zhou
	 * @date 2013-11-22
	 * @param settleBillId：结算单Id,inorout:应付or应收
	 * @return
	 */
    public List wayBillOfSettleBillList(Map map) {
        List list = new ArrayList();
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisWayBillOfSettleBillList", map);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    /***
     * 结算单下的相关运单列表
     * @author yaoyan.lin
     * @date 2013-12-18
     * @param settleBillId：结算单Id,inorout:应付or应收,waybillnumber:运单号,clientnumber:客户单号
     * @return
     */
    public List wayBillOfSettleBillListWithCondition(Map map) {
    	List list = new ArrayList();
    	try {
    		list = this.getSqlMapClientTemplate().queryForList("wayBillOfSettleBillListWithCondition", map);
    	} catch (DataAccessException e) {
    		e.printStackTrace();
    	}
    	return list;
    }

  /***
	 * 统计结算单下的相关运单总资费
	 * @author lianggui.zhou
	 * @date 2013-11-20
	 * @param settleBillId：结算单Id,waybillId:运单id
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public Map statisticsAmountBySettleBillId(Map map) {
        Map result = new HashMap();
        try {
            result = (Map) this.getSqlMapClientTemplate().queryForObject("iBatisStatisticsAmountBySettleBillId", map);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
    /***
     * 删除结算单时候更新运单结算单Id字段为Null
     * @author lianggui.zhou
     * @date 2013-11-23
     * @param settlebillid:结算单ID,inOrOut:应收or应付
     * @return
     */
    public String updateWayBillOfSettleBill(String settlebillid,String inOrOut){
        String msg="sorry";
        Map map=new HashMap();
        map.put("settlebillid", settlebillid);
        map.put("inorout", inOrOut);
        try {
            int i=this.getSqlMapClientTemplate().delete("iBatisUpdateWayBillOfSettleBill",map);
            if(i>0){
                msg="ok";
            }
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return msg;
    }
/**
 * 运单分派
 * @author haoyong
 * @date 2013-11-22
 * @param map
 * @return
 */
public String waybillDistribution(Map map) {
	String msg = "ok";
	try {
		int i = this.getSqlMapClientTemplate().update("waybilldistribution", map);
		if(i==0){
		    msg= "运单分派失败";
		 }
	} catch (DataAccessException e) {
		e.printStackTrace();
	}
	String msgJson = JsonGenerateUtil.getMsgIdJson(msg, "");
	return msgJson;
}

/***
     * 运单装车确认
     * @author yao.xia
     * @date 2013-11-25
     * @param waybillid:运单id
     * @return
     */
    public String load(Map map){
    	String msg = "ok";
		try {
			//修改货物核定数据
			String data = map.get("jsonText").toString();
			List<Map<String, Object>> dataList=new ArrayList<Map<String, Object>>();
			dataList=Json2ObjectUtil.parseJSON2List(data);
			Goods goods = new Goods();//物品实体类
			for(Map map2:dataList){
				ParseFormToBean pftbg=new ParseFormToBean();
				goods = (Goods)pftbg.parseToBean(map2, goods);
				int x = getSqlMapClientTemplate().update("iBatisFactGoods",goods);
				if (x != 1) {
					msg = "核定数据失败";
					return msg;
				}
			}
			//1.根据id找到运单
			Waybill waybill = new Waybill();
			Map map1 = new HashMap();
			map1.put("waybillid", map.get("waybillid").toString());
			waybill = (Waybill) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillById", map1);
			//2.修改运单配载单号&状态
			waybill.setClientnumber(map.get("clientnumber").toString());
			waybill.setConsigndate(map.get("consigndate").toString());
			waybill.setConsignorlinkman(map.get("consignorlinkman").toString());
			waybill.setConsignortelephonenumber(map.get("consignortelephonenumber").toString());
			waybill.setConsignormobilenumber(map.get("consignormobilenumber").toString());
			waybill.setConsignorprovince(map.get("consignorprovince").toString());
			waybill.setConsignorcity(map.get("consignorcity").toString());
			waybill.setConsignorregion(map.get("consignorregion").toString());
			
			waybill.setConsignortown(map.get("consignortown").toString());
			waybill.setConsignee(map.get("consignee").toString());
			waybill.setConsigneelinkman(map.get("consigneelinkman").toString());
			waybill.setConsigneetelephonenumber(map.get("consigneetelephonenumber").toString());
			waybill.setConsigneemobilenumber(map.get("consigneemobilenumber").toString());
			waybill.setConsigneeprovince(map.get("consigneeprovince").toString());
			waybill.setConsigneecity(map.get("consigneecity").toString());
			waybill.setConsigneeregion(map.get("consigneeregion").toString());
			
			waybill.setConsigneetown(map.get("consigneetown").toString());
			waybill.setDistance(map.get("distance").toString());
			waybill.setSettletype(map.get("settletype").toString());
			waybill.setReceivetype(map.get("receivetype").toString());
			waybill.setArrivetype(map.get("arrivetype").toString());
			waybill.setBackbilltype(map.get("backbilltype").toString());
			waybill.setBackbillnum(map.get("backbillnum").toString());
			waybill.setUrgencydegree(map.get("urgencydegree").toString());
			waybill.setShiptype(map.get("shiptype").toString());
			waybill.setMemo(map.get("memo").toString());
			
			waybill.setSureman(map.get("sureman").toString());//确认人
			waybill.setSuredate(map.get("suredate").toString());//确认时间
			waybill.setStatus("已装车");
			int i = getSqlMapClientTemplate().update("iBatisUpdateWayBill",waybill);
			if (i != 1) {
				msg = "装车失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg,"");
		return msgJson;
    }
    /***
     * 运单发车确认
     * @author yao.xia
     * @date 2013-11-25
     * @param waybillid:运单id
     * @return
     */
    public String start(Map map){
    	String msg = "ok";
		try {
			//1.根据id找到运单
			Waybill waybill = new Waybill();
			Map map1 = new HashMap();
			map1.put("waybillid", map.get("waybillid").toString());
			waybill = (Waybill) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillById", map1);
			//2.修改运单配载单号&状态
			waybill.setCarbegindate(map.get("carbegindate").toString());
			waybill.setStatus("已发车");
			int i = getSqlMapClientTemplate().update("iBatisUpdateWayBill",waybill);
			if (i != 1) {
				msg = "确认发车失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg,"");
		return msgJson;
    }
    /***
     * 运单到车确认
     * @author yao.xia
     * @date 2013-11-25
     * @param waybillid:运单id
     * @return
     */
    public String arrive(Map map){
    	String msg = "ok";
		try {
			//1.根据id找到运单
			Waybill waybill = new Waybill();
			Map map1 = new HashMap();
			map1.put("waybillid", map.get("waybillid").toString());
			waybill = (Waybill) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillById", map1);
			//2.修改运单配载单号&状态
			waybill.setCarenddate(map.get("carenddate").toString());
			waybill.setStatus("已到车");
			int i = getSqlMapClientTemplate().update("iBatisUpdateWayBill",waybill);
			if (i != 1) {
				msg = "确认到车失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg,"");
		return msgJson;
    }
    /**
     * @author wei.huang
     * @date 2013-11-27
     * @function 查询符合报表要求的运单明细表(尚不包含应收应付金额)
     * @param map:一定包括partyid,skipCount和pageSize,可能含有参数(waybillnumber,status,frompartyid和topartyid:以","联接的字符串,startdate,enddate,consignorprovince,consignorcity,consignorregion,consigneeprovince,consigneecity,consigneeregion)
     * @return List
     */
    public List selectWaybillListForReport(Map map){
    	List list=null;
    	try{
    		list=this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillDetailForReport", map);
    	}catch(Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
    
    /**
     * 查询某条结算单下的所有运单信息
     * @author lianggui.zhou
     * @date 2013-11-30
     * @param map{"settlebillid":"结算单id","inorout":"0 or 1"}
     * @return List<Map>
     */
    public List<Map> selectWaybillsOfSettBill(Map map){
        List<Map> result=null;
        try {
            result=this.getSqlMapClientTemplate().queryForList("R", map);
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    /**
     * @author wei.huang
     * @date 2013-11-29
     * @function 获取同一partyId下的不重复的以"-"连接的frompartyid或topartyid的字符串
     * @param partyid 总包会员的id
     * @param fromorto 0=“分包商”，1=“发货方”
     * @return
     */
    public String selectNoRepeatFromOrToPartyId(String partyid,int fromorto){
    	StringBuffer sb=new StringBuffer();
    	try{
    		List<String> list=null;
    		if(fromorto==0){
    			list=this.getSqlMapClientTemplate().queryForList("iBatisSelectNoRepeatToPartyId", partyid);
    		}else{
    			list=this.getSqlMapClientTemplate().queryForList("iBatisSelectNoRepeatFromPartyId", partyid);
    		}
    		for(int i=0;i<list.size();i++){
    			if(list.get(i)!=null&&!list.get(i).equals("")){
    				sb.append(list.get(i));
    				if((i+1)<list.size()){
        				sb.append("-");
        			}
    			}
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}
    	return sb.toString();
    }
    /**
     * @author nixainjing
     * @date 2013-12-4
     * @param 根据运单号号查询
     * @param waybillnumber
     * @return
     */
	public String selectWaybillNumber(String waybillnumber) {
		StringBuffer sb=new StringBuffer();
    	try{
    		List<String> list=null;
    		list=this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillNumber", waybillnumber);
    		if(list.size() == 1) {
    			sb.append(list.get(0));
    		}else {
    			sb.append("NO");
    		}
    	}catch(Exception e) {
			e.printStackTrace();
		}
    	return sb.toString();
	}

	/**
	 * nixianjing
	 * 
	 * 2013-12-05
	 * 根据客户单号模糊查询运单号
	 */
	public List selectWaybillNumberList(Map map) {
		List list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillNumberList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取总包会员的一个发货方
	 * @author haoyong
	 * @date 2013-12-05
	 * @return
	 */
	public String selectFrompartyid(Map map){
		String frompartyid = "";
		try {
			frompartyid = (String)this.getSqlMapClientTemplate().queryForObject("iBatisSelectFrompartyid", map);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return frompartyid;
	}
	
	/**
	 * 获取发货方的分包商（城区间距离维护+为维护但是已关联的）
	 * @param map
	 * @return
	 */
	public List<CityDistance> selectSubContractorList(Map map) {
		List<CityDistance> list = null;
		try {
			list=this.getSqlMapClientTemplate().queryForList("iBatisSelectSubContractor", map);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 修改接单管理
	 * 2013-12-13
	 * xianjing.ni
	 */
	public String updateWaybill(Waybill waybill) {
		String waybillid = "", msg = "ok";
		try {
			int i = getSqlMapClientTemplate().update("iBatisUpdateWayBillgoods",waybill);
			if (i != 1) {
				msg = "更新失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg, "");
		return msgJson;
	}
	
	/**
	 * @author haoyong
	 * @date 13-12-18
	 * @function 根据调度单查寻运单
	 * @param map
	 * @return
	 */
	public List selectWaybillByStowageId(Map map){
		List list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillByStowageId", map);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @author xianjing.ni
	 * @date 13-12-19
	 * @function 根据调度单查寻运单(包括货物信息)
	 * @param map
	 * @return
	 */
	public List selectWaybillGoodsByStowageId(Map map) {
		List list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillGoodsByStowageId", map);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @author haoyong
	 * @date 13-12-19
	 * @function 判断同一张调度单的运单状态是否一致，如果一致则改变调度单状态。
	 * @param map
	 * @return
	 */
	public Map checkWaybillStatus(Map map){
		Map resMap = null;
		try {
			resMap = (Map) this.getSqlMapClientTemplate().queryForObject("iBatisCheckWaybillStatus", map);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return resMap;
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-28
	 * @function 获取总包层面的费用统计信息
	 * @return
	 */
	@Override
	public List selectStatistic_Contract_Amount(Map map) {
		List list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectStatistic_Contract_Amount",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-28
	 * @function 获取总包层面的货物统计信息
	 * @return
	 */
	@Override
	public List selectStatistic_Contract_Goods(Map map) {
		List list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectStatistic_Contract_Goods",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取不重复的以"-"连接的partyid的字符串
	 * @return
	 */
	public String selectNoRepeatPartyId(){
		StringBuffer sPartyId=new StringBuffer();
        try {
            List<String> list = this.getSqlMapClientTemplate().queryForList("iBatisSelectNoRepeatPartyId");
    		for(int i=0,len=list.size();i<len;i++){
    			sPartyId.append(list.get(i));
    			if(i+1<len){
    				sPartyId.append("-");
    			}
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sPartyId.toString();
	}
	/**
	    * @author wei.huang
	    * @date 2013-12-30
	    * @function 获取指定总包下不重复的以"-"连接的frompartyid的字符串
	    * @return
	    */
	   public String selectNoRepeatFromPartyId(String partyid){
		   StringBuffer sPartyId=new StringBuffer();
	        try {
	            List<String> list = this.getSqlMapClientTemplate().queryForList("iBatisSelectNoRepeatFromPartyId",partyid);
	    		for(int i=0,len=list.size();i<len;i++){
	    			sPartyId.append(list.get(i));
	    			if(i+1<len){
	    				sPartyId.append("-");
	    			}
	    		}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return sPartyId.toString();
	   }
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取发货方层面的货物统计信息
	 * @return
	 */
	public List selectStatistic_Consignor_Goods(Map map){
		List list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectStatistic_Consignor_Goods",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取发货方层面的费用统计信息
	 * @return
	 */
	public List selectStatistic_Consignor_Amount(Map map){
		List list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectStatistic_Consignor_Amount",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取运单层面的货物统计信息
	 * @return
	 */
	public List selectStatistic_Waybill_Goods(Map map){
		List list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectStatistic_Waybill_Goods",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取运单层面的费用统计信息
	 * @return
	 */
	public List selectStatistic_Waybill_Amount(Map map){
		List list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectStatistic_Waybill_Amount",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	/***
     * 统计昨日发货票数
     * @author yao.xia
     * @param map(organization,description)
     * @return 昨日发货票数
     */
    public String selectYestodayCount(Map map){
    	String msg="";
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DATE, -1);    //得到前一天
    	String  yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    	try {
    		map.put("consigndate", yestedayDate);
    		msg = this.getSqlMapClientTemplate().queryForObject("iBatisSelectYestodayCount", map).toString();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return msg;
    }
    /***
     * 统计本月共计发货票数
     * @author yao.xia
     * @param map(organization,description)
     * @return 本月共计发货票数
     */
    public String selectMouthCount(Map map){
    	String msg="";
    	Calendar calendar = Calendar.getInstance();
    	//获取本月第一天
    	calendar.set(GregorianCalendar.DAY_OF_MONTH, 1); 
    	String firstDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    	//获取本月最后一天
    	calendar.set( Calendar.DATE, 1 );
    	calendar.roll(Calendar.DATE, - 1 );
    	String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    	try {
    		map.put("fromdate", firstDay);
    		map.put("todate", lastDay);
    		msg = this.getSqlMapClientTemplate().queryForObject("iBatisSelectMouthCount", map).toString();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return msg;
    }
    /**
	 * @author yaoyan.lin
	 * @date 2014-02-18
	 * @function 结算信息
	 * @map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 */
	public List selectExportList(Map map){
		List<Waybill> list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectExportWaybillList", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

	
}