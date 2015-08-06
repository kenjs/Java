package tf56.contract.services.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.Goods;
import tf56.contract.domain.WaybillAmount;
import tf56.contract.services.GoodsDao;
import tf56.contract.services.WaybillAmountDao;
import tf56.contract.services.WaybillDao;
import tf56.contract.services.WaybillLogDao;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.SofaSpringContext;

public class WaybillAmountDaoImpl extends SqlMapClientDaoSupport implements WaybillAmountDao{
 public void insert(WaybillAmount waybillAmount)throws SQLException{ //增 实现类
  String waybillamountid="";
  waybillamountid=(String) getSqlMapClientTemplate().insert("iBatisInsertWaybillAmount",waybillAmount);  
 }

 public void delete(Map map)throws SQLException { //删 实现类
  String msg="ok";
   int i=getSqlMapClientTemplate().delete("iBatisDeleteWaybillAmount", map);
   if(i!=1){
	   msg= "删除失败";
   }
 }

 public String update(WaybillAmount waybillAmount) { //改 实现类
  String waybillamountid="", msg="ok";
  try{
   int i=getSqlMapClientTemplate().update("iBatisUpdateWaybillAmount", waybillAmount);
   if(i!=1){
    msg= "更新失败";
   }
  }catch(Exception e){
   e.printStackTrace();
   msg=e.getMessage();
  }
  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, "");
  return msgJson;
 }
 /**
  * 运单修改中改topartyid
  * @param map
  * @return
  */
 public int updateTopartyid(Map map) { //改 实现类
	  int i=getSqlMapClientTemplate().update("iBatisUpdateWaybillAmountTopartyid", map);
	  return i;
 }
 public String selectById(Map map) { //查询数据到Bean,然后转成map
  String msgJson="";
  try{
   WaybillAmount waybillAmount=(WaybillAmount) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillAmountById", map);
   if (waybillAmount!=null) {
    msgJson=JsonGenerateUtil.bean2json(waybillAmount);
   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return msgJson;
 }

 public String selectList(Map map) { //查询数据到Bean,然后转成map
  String msgJson="";
  try{
   int count=(Integer) this.getSqlMapClientTemplate().queryForObject("iBatisSelectCount", map);
   List list=this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillAmountList",map);
   if (!list.isEmpty()) {
    msgJson=JsonGenerateUtil.list2json(list);
   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return msgJson;
 }
 public List selectWaybillAmountList(String id){
	 List list = new ArrayList();
	 Map<String,String> map = new HashMap<String, String>();
	 map.put("waybillid", id);
	  try{
	   list=this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillAmountListByWaybillId",map);
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  return list;
	}

 /**
  * 根据waybillid更新运单的计费状态
  * @param waybillId 运单id
  * @param billStatus 计费状态
  * @author donghui.wang
  * @return 更新是否成功
  */
@Override
public String updateBillStatusByWaybillId(String waybillId, String billStatus) {
    Map map = new HashMap();
    String msg = "sorry";
    map.put("waybillid", waybillId);
    map.put("billstatus", billStatus);
    try{
        this.getSqlMapClientTemplate().update("iBatisUpdateBillStatusByWaybillId",map);
        msg = "ok";
    }catch(Exception e){
        msg = "sorry";
    }
    return msg;
}
public String transactionSaveWaybillamount(Map map){
	String msg="ok";
	try {
		this.getSqlMapClient().startTransaction();
		this.delete(map);
		WaybillAmount waybillAmount = new WaybillAmount();
		waybillAmount.setWaybillid(map.get("waybillid").toString());
		waybillAmount.setFrompartyid(map.get("partyid").toString());
		Set<String> key = map.keySet();
		int k = 0;
	    for (Iterator it = key.iterator(); it.hasNext();) {
	        String s = (String) it.next();
	        String amount = map.get(s).toString();
	        char[] c = s.toCharArray();
	        if(c.length<3){
	        	continue;
	        }
	        char[] fy =new char[c.length-2];
	        String strFy = "";//费用名
	        
	    	if(c[0]=='y' && c[1]=='s'){
	    		waybillAmount.setInorout("1");//收
	    		waybillAmount.setTopartyid(map.get("frompartyid").toString());//发货方
	        }else if(c[0]=='y' && c[1]=='f'){
	        	waybillAmount.setInorout("0");//付
	        	waybillAmount.setTopartyid(map.get("topartyid").toString());//分包方
	        }else{
	        	continue;
	        }
	    	for(int i = 2;i<c.length;i++){
	    		fy[i-2]=c[i];
	    	}
	    	strFy = String.valueOf(fy);
	    	//set 费用名
	    	if("yf".equals(strFy)){
	    		waybillAmount.setAmountitem("运费");
	    	}else if("bxf".equals(strFy)){
	    		waybillAmount.setAmountitem("保险费");
	    	}else if("thf".equals(strFy)){
	    		waybillAmount.setAmountitem("提货费");
	    	}else if("shf".equals(strFy)){
	    		waybillAmount.setAmountitem("送货费");
	    	}else if("bzf".equals(strFy)){
	    		waybillAmount.setAmountitem("包装费");
	    	}else if("ryfjf".equals(strFy)){
	    		waybillAmount.setAmountitem("燃油附加费");
	    	}else if("dxtzf".equals(strFy)){
	    		waybillAmount.setAmountitem("短信通知费");
	    	}else if("qt".equals(strFy)){
	    		waybillAmount.setAmountitem("其他费用");
	    	}else{
	    		continue;
	    	}
	    	//set 费用
	    	if(StringUtils.isNotBlank(amount)&&Double.parseDouble(amount)>0){
				waybillAmount.setAmount(map.get(s).toString());
			}else {
				continue;
			}
	    	this.insert(waybillAmount);
	    	k++;
	    }
	    String str = map.get("jfzt").toString();
	    if("已计费".equals(str)){
		    if(k==0){
		    	str = "未计费";
		    }
	    }
	    this.updateBillStatusByWaybillId(map.get("waybillid").toString(), str);
	} catch (SQLException e) {
		 e.printStackTrace();
		 msg=e.getMessage();
	}finally{
		try {
			this.getSqlMapClient().endTransaction();
		}catch (SQLException e2) {
			e2.printStackTrace();
			msg=e2.getMessage();
		}
	}
	String msgJson=JsonGenerateUtil.getMsgIdJson(msg, "");
	return msgJson;
}
public String transactionSaveWaybillamountWhenSettleBill(Map map){
	String msgJson="";
	try {
		msgJson = this.transactionSaveWaybillamount(map);
		this.getSqlMapClient().startTransaction();
		GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao"); // 调用接口(实现类)
		Goods goods = new Goods();
		String[] goodsid = map.get("goodsid").toString().split("#");
		String[] factnum = map.get("factnum").toString().split("#");
		String[] factweight = map.get("factweight").toString().split("#");
		String[] factvolume = map.get("factvolume").toString().split("#");
		if(goodsid.length>0){
			int i = 0;
			for(String id:goodsid){
				if(StringUtils.isBlank(id)){
					continue;
				}
				goods.setGoodsid(id);
				goods.setFactnum(factnum[i]);
				goods.setFactweight(factweight[i]);
				goods.setFactvolume(factvolume[i]);
				goodsDao.updateWhenSettleBill(goods);
				i++;
			}
		}
	} catch (SQLException e) {
		 e.printStackTrace();
		msgJson=JsonGenerateUtil.getMsgIdJson(e.getMessage(), "");
	}finally{
		try {
			this.getSqlMapClient().endTransaction();
		}catch (SQLException e2) {
			e2.printStackTrace();
			msgJson=JsonGenerateUtil.getMsgIdJson(e2.getMessage(), "");
		}
	}
	return msgJson;
}

	/**
	 * @author wei.huang
	 * @date 2013-11-28
	 * @function 查询应收应付总金额
	 * @param partyId总包partyid
	 * @return List
	 */
	public List selectStatisticAmount(String partyId){
		List list=null;
		try{
			list=this.getSqlMapClientTemplate().queryForList("iBatisSelectStatisticAmountForReport", partyId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
