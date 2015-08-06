package tf56.contract.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tf56.contract.services.OrganizationService;
import tf56.contract.services.PartyService;
import tf56.contract.services.WaybillDao;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;

public class Contractstatisticc extends RestController{
	private final Logger log = Logger.getLogger("Contractstatisticc.java");
	/**
	 * @author wei.huang
	 * @date 2013-12-28
	 * @function 获取总包层面的货物以及费用、利润等的统计信息
	 * @return
	 */
	@POST
	public String selectStatistic_Contract(){
		Map params=SysUtil.removeFilter(this.getParams());
//		params=SysUtil.getIsoDecodeMap(params);
		int pageSize=Integer.parseInt(params.get("pageSize").toString());
		int skipCount=Integer.parseInt(params.get("skipCount").toString());
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		//获取指定的形式partyid
		String partyIdList=waybillDao.selectNoRepeatPartyId();
		/**根据organization名称模糊查询与之匹配的partyid的集合  开始**/
		if(params.get("organization")!=null){
			if(!params.get("organization").toString().equals("")){
				Map contractTempMap=new HashMap();
				contractTempMap.put("word", params.get("organization").toString());
				contractTempMap.put("partyidlist", partyIdList);
				OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
				String msg_From=organizationService.queryOrgName(contractTempMap);
				List<Map<String,Object>> conractPartyIdList=Json2ObjectUtil.parseJSON2List(msg_From);
				//转换为以","连接的字符串
				StringBuffer finalPartyIdList=new StringBuffer();
				if(conractPartyIdList.size()>0){
					for(int i=0;i<conractPartyIdList.size();i++){
						finalPartyIdList.append(conractPartyIdList.get(i).get("partyid").toString());
						if((i+1)<conractPartyIdList.size()){
							finalPartyIdList.append(",");
						}
					}
				}else{
					finalPartyIdList.append("-1");//表示不存在相关发货方
				}
				params.put("partyidlist", finalPartyIdList.toString());
			}
		}
		/**根据organization名称模糊查询与之匹配的partyid的集合  结束**/
		List<Map> contract_Goods=waybillDao.selectStatistic_Contract_Goods(params);
		List<Map> contract_Amount=waybillDao.selectStatistic_Contract_Amount(params);
		//获取总包层面的货物统计信息(包含组织名称)
		PartyService partyService=(PartyService)SofaSpringContext.getBean("partyService");
		List<Map> fullContract_Goods=partyService.addOrganizationForStatistic_Contract(contract_Goods, partyIdList);
		//应收应付金额拼接并统计
		int allWaybill=0;
		float allNum=0,allWeight=0,allVolume=0,allInAmount=0,allOutAmount=0,allProfit=0,allMargin=0;
		for(int i=0;i<fullContract_Goods.size();i++){
			boolean flagIn=false;//标记是否有应收金额
			boolean flagOut=false;//标记是否有应付金额
			for(int j=0,amountLen=contract_Amount.size();j<amountLen;j++){
				if(fullContract_Goods.get(i).get("partyid").toString().equals(contract_Amount.get(j).get("partyid").toString())){
					if(contract_Amount.get(j).get("inorout").toString().equals("0")){
						fullContract_Goods.get(i).put("needoutamount",contract_Amount.get(j).get("totalamount").toString());
						flagOut=true;
					}else{
						fullContract_Goods.get(i).put("needinamount",contract_Amount.get(j).get("totalamount").toString());
						flagIn=true;
					}
				}
			}
			if(!flagOut){
				fullContract_Goods.get(i).put("needoutamount", "0");
			}
			if(!flagIn){
				fullContract_Goods.get(i).put("needinamount", "0");
			}
			//毛利、毛利率计算
			float inAmount=Float.parseFloat(fullContract_Goods.get(i).get("needinamount").toString());
			float outAmount=Float.parseFloat(fullContract_Goods.get(i).get("needoutamount").toString());
			float margin=0;
			if(!fullContract_Goods.get(i).get("needinamount").toString().equals("0")){
				margin=(inAmount-outAmount)/inAmount;
				fullContract_Goods.get(i).put("grossmargin",margin);//毛利率
			}else{
				fullContract_Goods.get(i).put("grossmargin","0");//毛利率
			}
			String sGrossMargin=params.get("grossmargin").toString();
			if(!sGrossMargin.equals("全部")){
				if(sGrossMargin.equals("正常")){
					if(margin<0.03){
						fullContract_Goods.remove(i);
						i--;
						continue;
					}
				}else{
					if(margin>=0.03){
						fullContract_Goods.remove(i);
						i--;
						continue;
					}
				}
			}
			fullContract_Goods.get(i).put("grossprofit",new DecimalFormat("###,###,###,##0.00").format(inAmount-outAmount));//毛利
			if(fullContract_Goods.get(i).get("totalnum")==null){
				fullContract_Goods.get(i).put("totalnum","0");
			}else if(fullContract_Goods.get(i).get("totalnum").toString().equals("")){
				fullContract_Goods.get(i).remove("totalnum");
				fullContract_Goods.get(i).put("totalnum","0");
			}
			if(fullContract_Goods.get(i).get("totalweight")==null){
				fullContract_Goods.get(i).put("totalweight","0");
			}else if(fullContract_Goods.get(i).get("totalweight").toString().equals("")){
				fullContract_Goods.get(i).remove("totalweight");
				fullContract_Goods.get(i).put("totalweight","0");
			}
			if(fullContract_Goods.get(i).get("totalvolume")==null){
				fullContract_Goods.get(i).put("totalvolume","0");
			}else if(fullContract_Goods.get(i).get("totalvolume").toString().equals("")){
				fullContract_Goods.get(i).remove("totalvolume");
				fullContract_Goods.get(i).put("totalvolume","0");
			}
			allWaybill+=Integer.parseInt(fullContract_Goods.get(i).get("totalwaybill").toString());
			allNum+=Float.parseFloat(fullContract_Goods.get(i).get("totalnum").toString());
			allWeight+=Float.parseFloat(fullContract_Goods.get(i).get("totalweight").toString());
			allVolume+=Float.parseFloat(fullContract_Goods.get(i).get("totalvolume").toString());
			allInAmount+=Float.parseFloat(fullContract_Goods.get(i).get("needinamount").toString());
			allOutAmount+=Float.parseFloat(fullContract_Goods.get(i).get("needoutamount").toString());
		}
		//按分页要求筛选数据并统计数据
		List list=new ArrayList();
		int listLen=fullContract_Goods.size();
		String count= String.valueOf(listLen);
		for(int i=skipCount;i<(pageSize+skipCount);i++){
			if(i>=listLen){
				break;
			}
			list.add(fullContract_Goods.get(i));
		}
		allProfit=allInAmount-allOutAmount;
		if(!String.valueOf(allInAmount).equals("0.0")){
			allMargin=allProfit/allInAmount;
		}
		Map allMap=new HashMap();
		allMap.put("allwaybill", String.valueOf(allWaybill));
		allMap.put("allnum", new DecimalFormat("###########0.00").format(allNum));
		allMap.put("allweight", new DecimalFormat("###########0.000").format(allWeight));
		allMap.put("allvolume", new DecimalFormat("###########0.000").format(allVolume));
		allMap.put("allinamount", new DecimalFormat("###,###,###,##0.00").format(allInAmount));
		allMap.put("alloutamount", new DecimalFormat("###,###,###,##0.00").format(allOutAmount));
		allMap.put("allprofit", new DecimalFormat("###,###,###,##0.00").format(allProfit));
		allMap.put("allmargin", allMargin);
		list.add(0, allMap);
		String msgJson=JsonGenerateUtil.getPageListJson(list, count);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取发货方层面的货物以及费用、利润等的统计信息
	 * @return
	 */
	@POST
	public String selectStatistic_Consignor(){
		Map params=SysUtil.removeFilter(this.getParams());
//		params=SysUtil.getIsoDecodeMap(params);
		int pageSize=Integer.parseInt(params.get("pageSize").toString());
		int skipCount=Integer.parseInt(params.get("skipCount").toString());
		String partyid=params.get("partyid").toString();
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		//获取指定的形式partyid
		String fromPartyIdList=waybillDao.selectNoRepeatFromPartyId(partyid);
		//**根据organization名称模糊查询与之匹配的frompartyid的集合  开始**//*
		if(params.get("organization")!=null){
			if(!params.get("organization").toString().equals("")){
				Map consignorTempMap=new HashMap();
				consignorTempMap.put("word", params.get("organization").toString());
				consignorTempMap.put("partyidlist", fromPartyIdList);
				OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
				String msg_From=organizationService.queryOrgName(consignorTempMap);
				List<Map<String,Object>> consignorPartyIdList=Json2ObjectUtil.parseJSON2List(msg_From);
				//转换为以","连接的字符串
				StringBuffer finalPartyIdList=new StringBuffer();
				if(consignorPartyIdList.size()>0){
					for(int i=0;i<consignorPartyIdList.size();i++){
						finalPartyIdList.append(consignorPartyIdList.get(i).get("partyid").toString());
						if((i+1)<consignorPartyIdList.size()){
							finalPartyIdList.append(",");
						}
					}
				}else{
					finalPartyIdList.append("-1");//表示不存在相关发货方
				}
				params.put("partyidlist", finalPartyIdList.toString());
			}
		}
		/**根据organization名称模糊查询与之匹配的partyid的集合  结束**/
		List<Map> consignor_Goods=waybillDao.selectStatistic_Consignor_Goods(params);
		List<Map> consignor_Amount=waybillDao.selectStatistic_Consignor_Amount(params);
		//获取总包层面的货物统计信息(包含组织名称)
		PartyService partyService=(PartyService)SofaSpringContext.getBean("partyService");
		List<Map> fullConsignor_Goods=partyService.addOrganizationForStatistic_Consignor(consignor_Goods, fromPartyIdList);
		//应收应付金额拼接并统计
		int allWaybill=0;
		float allNum=0,allWeight=0,allVolume=0,allInAmount=0,allOutAmount=0,allProfit=0,allMargin=0;
		for(int i=0;i<fullConsignor_Goods.size();i++){
			boolean flagIn=false;//标记是否有应收金额
			boolean flagOut=false;//标记是否有应付金额
			for(int j=0,amountLen=consignor_Amount.size();j<amountLen;j++){
				if(fullConsignor_Goods.get(i).get("frompartyid").toString().equals(consignor_Amount.get(j).get("frompartyid").toString())){
					if(consignor_Amount.get(j).get("inorout").toString().equals("0")){
						fullConsignor_Goods.get(i).put("needoutamount",consignor_Amount.get(j).get("totalamount").toString());
						flagOut=true;
					}else{
						fullConsignor_Goods.get(i).put("needinamount",consignor_Amount.get(j).get("totalamount").toString());
						flagIn=true;
					}
				}
			}
			if(!flagOut){
				fullConsignor_Goods.get(i).put("needoutamount", "0");
			}
			if(!flagIn){
				fullConsignor_Goods.get(i).put("needinamount", "0");
			}
			//毛利、毛利率计算
			float inAmount=Float.parseFloat(fullConsignor_Goods.get(i).get("needinamount").toString());
			float outAmount=Float.parseFloat(fullConsignor_Goods.get(i).get("needoutamount").toString());
			float margin=0;
			if(!fullConsignor_Goods.get(i).get("needinamount").toString().equals("0")){
				margin=(inAmount-outAmount)/inAmount;
				fullConsignor_Goods.get(i).put("grossmargin",margin);//毛利率
			}else{
				fullConsignor_Goods.get(i).put("grossmargin","0");//毛利率
			}
			String sGrossMargin=params.get("grossmargin").toString();
			if(!sGrossMargin.equals("全部")){
				if(sGrossMargin.equals("正常")){
					if(margin<0.03){
						fullConsignor_Goods.remove(i);
						i--;
						continue;
					}
				}else{
					if(margin>=0.03){
						fullConsignor_Goods.remove(i);
						i--;
						continue;
					}
				}
			}
			fullConsignor_Goods.get(i).put("grossprofit",new DecimalFormat("###,###,###,##0.00").format(inAmount-outAmount));//毛利
			if(fullConsignor_Goods.get(i).get("totalnum")==null){
				fullConsignor_Goods.get(i).put("totalnum","0");
			}else if(fullConsignor_Goods.get(i).get("totalnum").toString().equals("")){
				fullConsignor_Goods.get(i).remove("totalnum");
				fullConsignor_Goods.get(i).put("totalnum","0");
			}
			if(fullConsignor_Goods.get(i).get("totalweight")==null){
				fullConsignor_Goods.get(i).put("totalweight","0");
			}else if(fullConsignor_Goods.get(i).get("totalweight").toString().equals("")){
				fullConsignor_Goods.get(i).remove("totalweight");
				fullConsignor_Goods.get(i).put("totalweight","0");
			}
			if(fullConsignor_Goods.get(i).get("totalvolume")==null){
				fullConsignor_Goods.get(i).put("totalvolume","0");
			}else if(fullConsignor_Goods.get(i).get("totalvolume").toString().equals("")){
				fullConsignor_Goods.get(i).remove("totalvolume");
				fullConsignor_Goods.get(i).put("totalvolume","0");
			}
			allWaybill+=Integer.parseInt(fullConsignor_Goods.get(i).get("totalwaybill").toString());
			allNum+=Float.parseFloat(fullConsignor_Goods.get(i).get("totalnum").toString());
			allWeight+=Float.parseFloat(fullConsignor_Goods.get(i).get("totalweight").toString());
			allVolume+=Float.parseFloat(fullConsignor_Goods.get(i).get("totalvolume").toString());
			allInAmount+=Float.parseFloat(fullConsignor_Goods.get(i).get("needinamount").toString());
			allOutAmount+=Float.parseFloat(fullConsignor_Goods.get(i).get("needoutamount").toString());
		}
		//按分页要求筛选数据并统计数据
		List list=new ArrayList();
		int listLen=fullConsignor_Goods.size();
		String count= String.valueOf(listLen);
		for(int i=skipCount;i<(pageSize+skipCount);i++){
			if(i>=listLen){
				break;
			}
			list.add(fullConsignor_Goods.get(i));
		}
		allProfit=allInAmount-allOutAmount;
		if(!String.valueOf(allInAmount).equals("0.0")){
			allMargin=allProfit/allInAmount;
		}
		Map allMap=new HashMap();
		allMap.put("allwaybill", String.valueOf(allWaybill));
		allMap.put("allnum", new DecimalFormat("###########0.00").format(allNum));
		allMap.put("allweight", new DecimalFormat("###########0.000").format(allWeight));
		allMap.put("allvolume", new DecimalFormat("###########0.000").format(allVolume));
		allMap.put("allinamount", new DecimalFormat("###,###,###,##0.00").format(allInAmount));
		allMap.put("alloutamount", new DecimalFormat("###,###,###,##0.00").format(allOutAmount));
		allMap.put("allprofit", new DecimalFormat("###,###,###,##0.00").format(allProfit));
		allMap.put("allmargin", allMargin);
		list.add(0, allMap);
		String msgJson=JsonGenerateUtil.getPageListJson(list, count);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-30
	 * @function 获取运单层面的货物以及费用、利润等的统计信息
	 * @return
	 */
	@POST
	public String selectStatistic_Waybill(){
		Map params=SysUtil.removeFilter(this.getParams());
//		params=SysUtil.getIsoDecodeMap(params);
		int pageSize=Integer.parseInt(params.get("pageSize").toString());
		int skipCount=Integer.parseInt(params.get("skipCount").toString());
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		List<Map> waybill_Goods=waybillDao.selectStatistic_Waybill_Goods(params);
		List<Map> waybill_Amount=waybillDao.selectStatistic_Waybill_Amount(params);
		//应收应付金额拼接并统计
		float allNum=0,allWeight=0,allVolume=0,allInAmount=0,allOutAmount=0,allProfit=0,allMargin=0;
		for(int i=0;i<waybill_Goods.size();i++){
			boolean flagIn=false;//标记是否有应收金额
			boolean flagOut=false;//标记是否有应付金额
			for(int j=0,amountLen=waybill_Amount.size();j<amountLen;j++){
				if(waybill_Goods.get(i).get("waybillid").toString().equals(waybill_Amount.get(j).get("waybillid").toString())){
					if(waybill_Amount.get(j).get("inorout").toString().equals("0")){
						waybill_Goods.get(i).put("needoutamount",waybill_Amount.get(j).get("totalamount").toString());
						flagOut=true;
					}else{
						waybill_Goods.get(i).put("needinamount",waybill_Amount.get(j).get("totalamount").toString());
						flagIn=true;
					}
				}
			}
			if(!flagOut){
				waybill_Goods.get(i).put("needoutamount", "0");
			}
			if(!flagIn){
				waybill_Goods.get(i).put("needinamount", "0");
			}
			//毛利、毛利率计算
			float inAmount=Float.parseFloat(waybill_Goods.get(i).get("needinamount").toString());
			float outAmount=Float.parseFloat(waybill_Goods.get(i).get("needoutamount").toString());
			float margin=0;
			if(!waybill_Goods.get(i).get("needinamount").toString().equals("0")){
				margin=(inAmount-outAmount)/inAmount;
				waybill_Goods.get(i).put("grossmargin",margin);//毛利率
			}else{
				waybill_Goods.get(i).put("grossmargin","0");//毛利率
			}
			String sGrossMargin=params.get("grossmargin").toString();
			if(!sGrossMargin.equals("全部")){
				if(sGrossMargin.equals("正常")){
					if(margin<0.03){
						waybill_Goods.remove(i);
						i--;
						continue;
					}
				}else{
					if(margin>=0.03){
						waybill_Goods.remove(i);
						i--;
						continue;
					}
				}
			}
			waybill_Goods.get(i).put("grossprofit",new DecimalFormat("###,###,###,##0.00").format(inAmount-outAmount));//毛利
			if(waybill_Goods.get(i).get("totalnum")==null){
				waybill_Goods.get(i).put("totalnum","0");
			}else if(waybill_Goods.get(i).get("totalnum").toString().equals("")){
				waybill_Goods.get(i).remove("totalnum");
				waybill_Goods.get(i).put("totalnum","0");
			}
			if(waybill_Goods.get(i).get("totalweight")==null){
				waybill_Goods.get(i).put("totalweight","0");
			}else if(waybill_Goods.get(i).get("totalweight").toString().equals("")){
				waybill_Goods.get(i).remove("totalweight");
				waybill_Goods.get(i).put("totalweight","0");
			}
			if(waybill_Goods.get(i).get("totalvolume")==null){
				waybill_Goods.get(i).put("totalvolume","0");
			}else if(waybill_Goods.get(i).get("totalvolume").toString().equals("")){
				waybill_Goods.get(i).remove("totalvolume");
				waybill_Goods.get(i).put("totalvolume","0");
			}
			allNum+=Float.parseFloat(waybill_Goods.get(i).get("totalnum").toString());
			allWeight+=Float.parseFloat(waybill_Goods.get(i).get("totalweight").toString());
			allVolume+=Float.parseFloat(waybill_Goods.get(i).get("totalvolume").toString());
			allInAmount+=Float.parseFloat(waybill_Goods.get(i).get("needinamount").toString());
			allOutAmount+=Float.parseFloat(waybill_Goods.get(i).get("needoutamount").toString());
		}
		//按分页要求筛选数据并统计数据
		List list=new ArrayList();
		int listLen=waybill_Goods.size();
		String count= String.valueOf(listLen);
		for(int i=skipCount;i<(pageSize+skipCount);i++){
			if(i>=listLen){
				break;
			}
			list.add(waybill_Goods.get(i));
		}
		allProfit=allInAmount-allOutAmount;
		if(!String.valueOf(allInAmount).equals("0.0")){
			allMargin=allProfit/allInAmount;
		}
		Map allMap=new HashMap();
		allMap.put("allnum", new DecimalFormat("###########0.00").format(allNum));
		allMap.put("allweight", new DecimalFormat("###########0.000").format(allWeight));
		allMap.put("allvolume", new DecimalFormat("###########0.000").format(allVolume));
		allMap.put("allinamount", new DecimalFormat("###,###,###,##0.00").format(allInAmount));
		allMap.put("alloutamount", new DecimalFormat("###,###,###,##0.00").format(allOutAmount));
		allMap.put("allprofit", new DecimalFormat("###,###,###,##0.00").format(allProfit));
		allMap.put("allmargin", allMargin);
		list.add(0, allMap);
		String msgJson=JsonGenerateUtil.getPageListJson(list, count);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
}
