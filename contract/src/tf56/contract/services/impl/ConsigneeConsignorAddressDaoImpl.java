package tf56.contract.services.impl;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tf56.contract.domain.ConsigneeConsignorAddress;
import tf56.contract.services.ConsigneeConsignorAddressDao;
import tf56.sofa.serializer.JsonGenerateUtil;

public class ConsigneeConsignorAddressDaoImpl extends SqlMapClientDaoSupport implements ConsigneeConsignorAddressDao{
 public String insert(ConsigneeConsignorAddress consigneeConsignorAddress){ //增 实现类
  String consigneeconsignoraddressid="", msg="ok";
  try{
   consigneeconsignoraddressid=(String) getSqlMapClientTemplate().insert("iBatisInsertConsigneeConsignorAddress",consigneeConsignorAddress);
  }catch(Exception e){
   e.printStackTrace();
   msg=e.getMessage();
  }
  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, consigneeconsignoraddressid);
  return msgJson; //返回json到控制器, json格式:msg:ok|错误信息,id:int值
 }

 public String delete(Map map) { //删 实现类
  String msg="ok";
  try{
   int i=getSqlMapClientTemplate().delete("iBatisDeleteConsigneeConsignorAddress", map);
   if(i!=1){
    msg= "删除失败";
   }
  }catch(Exception e){
   e.printStackTrace();
   msg=e.getMessage();
  }
  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, "");
  return msgJson; //返回json到控制器, json格式:msg:ok|错误信息,id:int值
 }

 public String update(ConsigneeConsignorAddress consigneeConsignorAddress) { //改 实现类
  String consigneeconsignoraddressid="", msg="ok";
  try{
   int i=getSqlMapClientTemplate().update("iBatisUpdateConsigneeConsignorAddress", consigneeConsignorAddress);
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
  *@author wei.huang
  *@since 2013-10-9
  *修改指定记录的checked的值
  */
 public String updateChecked(Map map){
	 String msg="ok";
	  try{
	   int i=getSqlMapClientTemplate().update("iBatisUpdateAddressChecked", map);
	   if(i<1){
	    msg= "更新失败";
	   }
	  }catch(Exception e){
	   e.printStackTrace();
	   msg=e.getMessage();
	  }
	  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, "");
	  return msgJson; 
 }
 
 public String selectById(Map map) { //查询数据到Bean,然后转成map
  String msgJson="";
  try{
   ConsigneeConsignorAddress consigneeConsignorAddress=(ConsigneeConsignorAddress) this.getSqlMapClientTemplate().queryForObject("iBatisSelectConsigneeConsignorAddressById", map);
   if (consigneeConsignorAddress!=null) {
    msgJson=JsonGenerateUtil.bean2json(consigneeConsignorAddress);
   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return msgJson;
 }

 /**
  * @author wei.huang
  * @return 获取查询列表
  * @since 2013-10-7
  */
 public List selectList(Map map) { //查询数据到Bean,然后转成map
	 List list=null;
  try{
     list=this.getSqlMapClientTemplate().queryForList("iBatisSelectConsigneeConsignorAddressList",map);
//   if (!list.isEmpty()) {
//    msgJson=JsonGenerateUtil.list2json(list);
//   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return list;
 }
 
 /**
  * @author wei.huang
  * @return 查询的记录条数
  * @since 2013-10-7
  */
 public String selectCount(Map map){
	 String count="";
	  try{
	   count=this.getSqlMapClientTemplate().queryForObject("iBatisSelectConsigneeConsignorAddressCount",map).toString();
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  return count;
 }
 /**
  * 查bean为订单
  * @param map
  * @return
  */
 public ConsigneeConsignorAddress selectForOutWaybill(Map map) { //查询数据到Bean,然后转成map
	 ConsigneeConsignorAddress consigneeConsignorAddress = new ConsigneeConsignorAddress(); 
	 try{
	   consigneeConsignorAddress=(ConsigneeConsignorAddress) this.getSqlMapClientTemplate().queryForObject("iBatisSelectConsigneeConsignorAddressForOutWaybill", map);
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  return consigneeConsignorAddress;
	 }
}
