package tf56.contract.services.impl;
import java.util.List;
import java.util.Map;

import net.sf.serfj.annotations.POST;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tf56.contract.domain.GoodsType;
import tf56.contract.services.GoodsTypeDao;
import tf56.sofa.serializer.JsonGenerateUtil;

/**
 * @author wei.huang
 * @date 2013-10-15
 */
public class GoodsTypeDaoImpl extends SqlMapClientDaoSupport implements GoodsTypeDao{
 public String insert(GoodsType goodsType){ //增 实现类
  String goodstypeid="", msg="ok";
  try{
   goodstypeid=(String) getSqlMapClientTemplate().insert("iBatisInsertGoodsType",goodsType);
  }catch(Exception e){
   e.printStackTrace();
   msg=e.getMessage();
  }
  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, goodstypeid);
  return msgJson; //返回json到控制器, json格式:msg:ok|错误信息,id:int值
 }

 public String delete(Map map) { //删 实现类
  String msg="ok";
  try{
   int i=getSqlMapClientTemplate().delete("iBatisDeleteGoodsType", map);
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

 public String update(GoodsType goodsType) { //改 实现类
  String goodstypeid="", msg="ok";
  try{
   int i=getSqlMapClientTemplate().update("iBatisUpdateGoodsType", goodsType);
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
   GoodsType goodsType=(GoodsType) this.getSqlMapClientTemplate().queryForObject("iBatisSelectGoodsTypeById", map);
   if (goodsType!=null) {
    msgJson=JsonGenerateUtil.bean2json(goodsType);
   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return msgJson;
 }
/**
 * wei.huang
 * 2013-10-15
 * 获取列表
 */
 public List selectList(Map map) {
  List list=null;
  try{
   list=this.getSqlMapClientTemplate().queryForList("iBatisSelectGoodsTypeList",map);
  }catch(Exception e){
   e.printStackTrace();
  }
  return list;
 }
 /**
  * wei.huang
  * 2013-10-15
  * 获取记录总条数
  */
 public String getCount(Map map){
	 String count="";
	 try{
		   count=this.getSqlMapClientTemplate().queryForObject("iBatisSelectGoodsTypeCount",map).toString();
		  }catch(Exception e){
		   e.printStackTrace();
		  }
		  return count;
 }
 /**
  * wei.huang
  * 2013-10-16
  * 修改默认货物
  * @return
  */
 public String updateChecked(Map map){
	 String goodstypeid="", msg="ok";
	  try{
	   int i=getSqlMapClientTemplate().update("iBatisUpdateGoodsTypeChecked", map);
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
}
