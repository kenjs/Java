package tf56.contract.services.impl;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tf56.contract.domain.ContractDictionary;
import tf56.contract.services.ContractDictionaryDao;
import tf56.sofa.serializer.JsonGenerateUtil;


public class ContractDictionaryDaoImpl extends SqlMapClientDaoSupport implements ContractDictionaryDao{
 public String insert(ContractDictionary contractDictionary){ //增 实现类
  String contractdictionaryid="", msg="ok";
  try{
   contractdictionaryid=(String) getSqlMapClientTemplate().insert("iBatisInsertContractDictionary",contractDictionary);
  }catch(Exception e){
   e.printStackTrace();
   msg=e.getMessage();
  }
  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, contractdictionaryid);
  return msgJson; //返回json到控制器, json格式:msg:ok|错误信息,id:int值
 }

 public String delete(Map map) { //删 实现类
  String msg="ok";
  try{
   int i=getSqlMapClientTemplate().delete("iBatisDeleteContractDictionary", map);
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

 public String update(ContractDictionary contractDictionary) { //改 实现类
  String contractdictionaryid="", msg="ok";
  try{
   int i=getSqlMapClientTemplate().update("iBatisUpdateContractDictionary", contractDictionary);
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
   ContractDictionary contractDictionary=(ContractDictionary) this.getSqlMapClientTemplate().queryForObject("iBatisSelectContractDictionaryById", map);
   if (contractDictionary!=null) {
    msgJson=JsonGenerateUtil.bean2json(contractDictionary);
   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return msgJson;
 }

 public List selectList(Map map) { //查询数据到Bean,然后转成map
  List list=null;
  try{
   list=this.getSqlMapClientTemplate().queryForList("iBatisSelectContractDictionaryList",map);
//   if (!list.isEmpty()) {
//    msgJson=JsonGenerateUtil.list2json(list);
//   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return list;
 }
 
 
 public String getCount(Map map){
	 String count="";
	 try{
		   count=this.getSqlMapClientTemplate().queryForObject("iBatisSelectContractDictionaryListCount",map).toString();
	 }catch(Exception e){
		  e.printStackTrace();
	 }
	 return count;
 }
 
 /**
  * @author hw
  * 检验字典是否存在
  * 2013-9-24
  */
 public String isExist(Map map){
	 String msgJson="";
	 try{
		 int count=(Integer)this.getSqlMapClientTemplate().queryForObject("iBatisSelectContractDictionaryValidateCount",map);
		 if(count>0){
			 msgJson=JsonGenerateUtil.getMsgJson("ok");
		 }else{
			 msgJson=JsonGenerateUtil.getMsgJson("不存在");
		 }
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 return msgJson;
 }
 /**
  * wei.haung
  * 2013-10-16
  * 获取Text列表
  */
 public List selectTextList(Map map){
	 List list=null;
	  try{
	   list=this.getSqlMapClientTemplate().queryForList("iBatisSelectContractDictionaryTextList",map);
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  return list;
 }
 /**
  * @author wei.huang
  * @date 2013-11-19
  * @function 通过字典类型type获取List
  * @param type 字典类型
  * @return List
  */
 public List selectListByType(String type){
	 List list=null;
	 try{
	   list=this.getSqlMapClientTemplate().queryForList("iBatisSelectListByType",type);
	 }catch(Exception e){
	   e.printStackTrace();
	 }
	 return list;
 }
}
