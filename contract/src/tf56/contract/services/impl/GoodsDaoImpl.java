package tf56.contract.services.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tf56.contract.domain.Goods;
import tf56.contract.services.GoodsDao;
import tf56.sofa.serializer.JsonGenerateUtil;

public class GoodsDaoImpl extends SqlMapClientDaoSupport implements GoodsDao{
 public String insert(Goods goods){ //增 实现类
  String goodsid="", msg="ok";
  try{
   goodsid=(String) getSqlMapClientTemplate().insert("iBatisInsertGoods",goods);
  }catch(Exception e){
   e.printStackTrace();
   msg=e.getMessage();
  }
  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, goodsid);
  return msgJson; //返回json到控制器, json格式:msg:ok|错误信息,id:int值
 }

 public String delete(Map map) { //删 实现类
  String msg="ok";
  try{
   int i=getSqlMapClientTemplate().delete("iBatisDeleteGoods", map);
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

 public String update(Goods goods) { //改 实现类
  String goodsid="", msg="ok";
  try{
   int i=getSqlMapClientTemplate().update("iBatisUpdateGoods", goods);
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

 public String selectById(Map map) { //查询数据到Bean,然后转成map
  String msgJson="";
  try{
   Goods goods=(Goods) this.getSqlMapClientTemplate().queryForObject("iBatisSelectGoodsById", map);
   if (goods!=null) {
    msgJson=JsonGenerateUtil.bean2json(goods);
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
   List list=this.getSqlMapClientTemplate().queryForList("iBatisSelectGoodsList",map);
   if (!list.isEmpty()) {
    msgJson=JsonGenerateUtil.list2json(list);
   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return msgJson;
 }
 public List selectGoodsList(String id) { //查询数据到Bean,然后转成map
	 List list = new ArrayList();
	 Map<String,String> map = new HashMap<String, String>();
	 map.put("waybillid", id);
	  try{
	   list=this.getSqlMapClientTemplate().queryForList("iBatisSelectGoodsListByWaybillId",map);
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  return list;
	 }
 
 //
 public String selectNumSumByWaybillId(Map map){
	 String msgJson="";
	  try{
		  List list=this.getSqlMapClientTemplate().queryForList("selectGoodsNumSumByWaybillId", map);
		  if (!list.isEmpty()) {
			   msgJson=JsonGenerateUtil.list2json(list);
		  }
	  }catch(Exception e){
		   e.printStackTrace();
	  }
	 return msgJson;
 }
 public void updateWhenSettleBill(Goods goods) throws SQLException{ //改 实现类
	   String goodsid="", msg="ok";
	   int i=getSqlMapClientTemplate().update("iBatisUpdateGoodsWhenSettleBill", goods);
	   if(i!=1){
		   msg= "更新失败";
	   }
	 }
}