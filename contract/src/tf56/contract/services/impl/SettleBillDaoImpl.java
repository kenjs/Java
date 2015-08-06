package tf56.contract.services.impl;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.SettleBill;
import tf56.contract.services.CityDistanceDao;
import tf56.contract.services.GoodsDao;
import tf56.contract.services.OrganizationService;
import tf56.contract.services.SettleBillDao;
import tf56.contract.services.WaybillDao;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.SofaSpringContext;

public class SettleBillDaoImpl extends SqlMapClientDaoSupport implements SettleBillDao{
	/**
	 * @author yao.xia
	 * @date 2013-12-19
	 * @function 结算信息
	 * @map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 */
	public List selectExportList(Map map){
		List<Map> list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectExportBillList", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-19
	  * @function 结算单列表(仅contract部分的数据)
	 * @map@map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 */
	public List selectList(Map map){
		List<Map> list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectSettleBillList", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-19
	 * @function 结算单列表记录条数
	 * @map@map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 */
	public String selectListCount(Map map){
		String count = "";
        try {
            count = this.getSqlMapClientTemplate().queryForObject("iBatisSelectSettleBillCount", map).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-20
	 * @function 获取收/发货方的partyid
	 * @param map(inorout=1表示发货方，inorout=0表示分包商;partyid:总包id)
	 */
	public List selectInOutPartyIdList(Map map){
		List<Map> list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectInOutPartyIdList", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-20
	 * @function 审核结算单
	 */
	public String updateStatusBySettleBillId(Map map){
		String msg="ok";
		try {
            int i = this.getSqlMapClientTemplate().update("iBatisUpdateStatusBySettleBillId",map);
            if(i!=1){
            	msg="sorry";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonGenerateUtil.getMsgJson(msg);
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-21
	 * @function 更新实收未收金额
	 * @param map(settlebillid,billamount,status)
	 */
	public String updateAmountBySettleBillId(Map map){
		String msg="ok";
		try {
            int i = this.getSqlMapClientTemplate().update("iBatisUpdateAmountBySettleBillId",map);
            if(i!=1){
            	msg="sorry";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonGenerateUtil.getMsgJson(msg);
	}
	/***
	 * 查询当前记录中最后一条的结算单号
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * @param inOrOut:YS or YF
	 * @return
	 */
	public String selectLastSettleBillNumber(String inOrOut){
		Date date=new Date();
		String msg="";
		Object obj=this.getSqlMapClientTemplate().queryForObject("iBatisSelectLastSettleBillNumber");
		if(obj==null){
		    String type="";
		    if(inOrOut.equals("1")){
		        type="YS";
		    }
		    if(inOrOut.equals("1")){
		        type="YF";
		    }
			DateFormat format= new java.text.SimpleDateFormat("yyMMdd");
			msg=type+format.format(date)+"000000";
		}else{
		    msg=obj.toString();
		}
		return msg;
	}
	/***
	 * 插入结算单
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * @param settleBill
	 * @return
	 */
	public String insert(SettleBill settleBill){
		String msg="ok";
		String i=this.getSqlMapClientTemplate().insert("iBatisInsert", settleBill).toString();
		if(Integer.parseInt(i)<=0){
			msg="sorry";
		}
		return JsonGenerateUtil.getMsgIdJson(msg, i);
	}
	/***
	 * 更新结算单
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * @param settleBill
	 * @return
	 */
	public String update(Map map){
		String msg="sorry";
		try {
			int i=this.getSqlMapClientTemplate().update("iBatisUpdate", map);
			if(i>0){
				msg="ok";
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
   /***
     * 删除阶结算单
     * 
     * @author lianggui.zhou
     * @date 2013-11-23
     * @param settleBillId:结算单Id
     * @return
     */
    public String delete(String settleBillId){
        Map map=new HashMap();
        map.put("settlebillid", settleBillId);
        String msg="sorry";
        try {
            int i=this.getSqlMapClientTemplate().delete("iBatisDelete",map);
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
     * 根据结算单号查询结算单信息（导出数据用）
     * @author lianggui.zhou
     * @date 2013-11-30
     * @param settleBillId:结算单号，inOrOut:应收or应付
     * @return Map
     */
    public Map exportData(String settleBillNumber,String inOrOut){
        Map data=null;
        Map params=new HashMap();
        params.put("settlebillid", settleBillNumber);
        params.put("inorout", inOrOut);
        OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
        try {
            /******结算单信息开始********/
            data=(Map)this.getSqlMapClientTemplate().queryForObject("iBatisExportData",params);//结算单信息
            Map org=new HashMap();
            org.put("partyid", data.get("partyid"));
            String orgname=organizationService.selectOrganizationNameByPartyId(org);
            List orglist=Json2ObjectUtil.parseJSON2List(orgname);
            org=(Map)orglist.get(0);
            orgname=org.get("name").toString();
            data.remove("partyid");
            data.put("organization", orgname);
            /******结算单信息结束********/
            /******运单信息开始********/
            List<Map> waybillList=waybillData(params);
            /******运单信息结束********/
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return data;
    }
    private List<Map> waybillData(Map map){
        WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
        List<Map> waybillList=waybillDao.selectWaybillsOfSettBill(map);
        return waybillList;
    }
    private List<Map> goodsData(String waybillIds){
        GoodsDao goodsDao=(GoodsDao)SofaSpringContext.getBean("goodsDao");
       // List<Map> goods=
        return null;
    }
}
