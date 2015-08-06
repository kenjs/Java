package tf56.contract.services.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tf56.contract.domain.WaybillLog;
import tf56.contract.services.WaybillLogDao;
import tf56.sofa.serializer.JsonGenerateUtil;

public class WaybillLogDaoImpl extends SqlMapClientDaoSupport implements WaybillLogDao{
 public String insert(Map map){ //增 实现类
  String waybilllogid="", msg="ok";
  try{
   waybilllogid=(String) getSqlMapClientTemplate().insert("iBatisInsertWaybillLog",map);
  }catch(Exception e){
   e.printStackTrace();
   msg=e.getMessage();
  }
  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, waybilllogid);
  return msgJson; //返回json到控制器, json格式:msg:ok|错误信息,id:int值
 }

 public String delete(Map map) { //删 实现类
  String msg="ok";
  try{
   int i=getSqlMapClientTemplate().delete("iBatisDeleteWaybillLog", map);
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

 public String update(WaybillLog waybillLog) { //改 实现类
  String waybilllogid="", msg="ok";
  try{
   int i=getSqlMapClientTemplate().update("iBatisUpdateWaybillLog", waybillLog);
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
   WaybillLog waybillLog=(WaybillLog) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillLogById", map);
   if (waybillLog!=null) {
    msgJson=JsonGenerateUtil.bean2json(waybillLog);
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
   List list=this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillLogList",map);
   if (!list.isEmpty()) {
    msgJson=JsonGenerateUtil.list2json(list);
   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return msgJson;
 }
 public List selectWaybillLogList(String id){
	 List list = new ArrayList();
	 Map<String,String> map = new HashMap<String, String>();
	 map.put("waybillid", id);
	 try{
	   list=this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillLogListByWaybillId",map);
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  return list;
	}
}