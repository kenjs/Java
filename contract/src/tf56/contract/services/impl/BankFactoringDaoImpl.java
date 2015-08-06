package tf56.contract.services.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tf56.contract.domain.BankFactoring;
import tf56.contract.domain.ShipperRelaSubContractor;
import tf56.contract.services.BankFactoringDao;
import tf56.contract.services.ContractAttributeDao;
import tf56.contract.services.WaybillService;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.JsonUtil;
import tf56.sofa.util.SofaSpringContext;

public class BankFactoringDaoImpl extends SqlMapClientDaoSupport implements BankFactoringDao{
 public String insert(BankFactoring bankFactoring){ //增 实现类
  String bankfactoringid="", msg="ok";
  try{
   bankfactoringid=(String) getSqlMapClientTemplate().insert("iBatisInsertBankFactoring",bankFactoring);
  }catch(Exception e){
   e.printStackTrace();
   msg=e.getMessage();
  }
  String msgJson=JsonGenerateUtil.getMsgIdJson(msg, bankfactoringid);
  return msgJson; //返回json到控制器, json格式:msg:ok|错误信息,id:int值
 }

 public String update(BankFactoring bankFactoring) { //改 实现类
  String bankfactoringid="", msg="ok";
  try{
   int i=getSqlMapClientTemplate().update("iBatisUpdateBankFactoring", bankFactoring);
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

 @SuppressWarnings("unchecked")
public String selectList(Map map) { //查询数据到Bean,然后转成map
  String msgJson="";
  try{
   List<BankFactoring> list=this.getSqlMapClientTemplate().queryForList("iBatisSelectBankFactoringList",map);
   //取发货方名称
   WaybillService waybillService = (WaybillService) SofaSpringContext.getBean("waybillService");
   map.put("partytype", "发货方");
	String consignorStr = waybillService.combiWaybillList(map);
	Map consignorMap = Json2ObjectUtil.parseJSON2Map(consignorStr);
	List consignorList = (List) consignorMap.get("Data");
	
	List list1 = new ArrayList();
	BankFactoring dom = null;
	Map mapTemp = null;
	String partyId = "";
	for (BankFactoring obj:list) {
		dom  = obj;
		for (Object object : consignorList) {
			mapTemp = (Map) object;
			partyId = (String) mapTemp.get("partyid");
			if(partyId.equals(dom.getFrompartyid())){
				dom.setFrompartyname((String)mapTemp.get("organization"));
			}
		}
		list1.add(dom);
	}
   if(list1.isEmpty() || list1 == null){
	   msgJson = JsonGenerateUtil.getSelfDefinedJson("");;
   }else{
	   msgJson = JsonGenerateUtil.list2json(list1);
   }
  }catch(Exception e){
   e.printStackTrace();
  }
  return msgJson;
 }
	 /**
      * @author wei.huang
      * @date 2014-3-5
      * @function 获取分包商的保理资格及账期
	  * @param map[settlebillid]
	  * @return
	  */
	public String selectConsigneeBusinessDays(Map map){
		String msgJson="";
		try{
			Map m=(Map)this.getSqlMapClientTemplate().queryForObject("iBatisSelectConsigneeBusinessDays",map);
			//判断是否启用银行保理
			if(m!=null){
				if(m.get("isenabled")!=null){
					if(m.get("isenabled").toString().equals("1")){
						ContractAttributeDao contractAttributeDao=(ContractAttributeDao)SofaSpringContext.getBean("contractAttributeDao");
						Map mm=contractAttributeDao.selectBankFactoringStatus(map);
						if(mm==null){//当无数据时，将isenabled设置为0
							m.remove("isenabled");
							m.put("isenabled", "0");
						}else{
							if(mm.get("status")==null){//当银行保理状态为空时，将isenabled设置为0
								m.remove("isenabled");
								m.put("isenabled", "0");
							}else if(mm.get("status").toString().equals("停用")){//当银行保理状态为停用时，将isenabled设置为0
								m.remove("isenabled");
								m.put("isenabled", "0");
							}
						}
					}
				}
			}
			msgJson=JsonUtil.map2json(m);
		}catch(Exception e){
			e.printStackTrace();
		}
		return msgJson;
	}
}