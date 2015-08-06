package tf56.contract.services.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tf56.contract.domain.ContractAppendix;
import tf56.contract.services.ContractAppendixDao;
import tf56.sofa.serializer.JsonGenerateUtil;

public class ContractAppendixDaoImpl extends SqlMapClientDaoSupport implements ContractAppendixDao{
 public String insert(ContractAppendix contractAppendix){ //增 实现类
  String contractappendixid="";
  try{
   contractappendixid=(String) getSqlMapClientTemplate().insert("iBatisInsertContractAppendix",contractAppendix);
  }catch(Exception e){
   e.printStackTrace();
  }
  return contractappendixid; //返回json到控制器, json格式:msg:ok|错误信息,id:int值
 }

 public String delete(Map map) { //删 实现类
  String msg="ok";
  try{
   int i=getSqlMapClientTemplate().delete("iBatisDeleteContractAppendix", map);
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

 public String update(ContractAppendix contractAppendix) { //改 实现类
  String contractappendixid="", msg="ok";
  try{
   int i=getSqlMapClientTemplate().update("iBatisUpdateAppendixTableId", contractAppendix);
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
   ContractAppendix contractAppendix=(ContractAppendix) this.getSqlMapClientTemplate().queryForObject("iBatisSelectContractAppendixById", map);
   if (contractAppendix!=null) {
    msgJson=JsonGenerateUtil.bean2json(contractAppendix);
   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return msgJson;
 }

 public List selectList(Map map) { //查询数据到Bean,然后转成map
	 List list=new ArrayList();
  try{
   list=this.getSqlMapClientTemplate().queryForList("iBatisSelectContractAppendixList",map);
  }catch(Exception e){
   e.printStackTrace();
  }
  return list;
 }
 
 /**
  * wei.huang
  * 2013-10-28
  * 检验文件是否已经存在
  * @param map：url 文件完整路径
  * @return 文件数量
  */
 public String isExist(Map map){
	 String msg="";
	 try{
		 msg=this.getSqlMapClientTemplate().queryForObject("iBatisSelectExistContractAppendixByURL", map).toString();
	 }catch(Exception e){
	  e.printStackTrace();
	 }
	 return msg;
 }
}
