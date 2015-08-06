package tf56.contract.services.impl;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.serfj.client.WebServiceException;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.ConsigneeConsignorAddress;
import tf56.contract.domain.OutGoods;
import tf56.contract.domain.OutWaybill;
import tf56.contract.services.ConsigneeConsignorAddressDao;
import tf56.contract.services.OrganizationService;
import tf56.contract.services.OutWaybillDao;
import tf56.contract.services.OutWaybillService;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.contract.util.ContractEnums;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.SofaSpringContext;

public class OutWaybillServiceImpl extends SqlMapClientDaoSupport implements OutWaybillService {

	@SuppressWarnings("unchecked")
	@Override
	public String transactionSaveOutWaybill(Map map) {
		String msg = "ok";
		String time = "";
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式 
		time = dateFormat.format( now ); 
		StringBuffer okMsg = new StringBuffer();
		okMsg.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		okMsg.append("<report_data>");
		okMsg.append("<respon_sys>SCM</respon_sys>");
		okMsg.append("<respon_time>"+time+"</respon_time>");
		okMsg.append("<result>");
		okMsg.append("<code>");
		okMsg.append(ContractEnums.ResultCode.SUCCESS.getValue());
		okMsg.append("</code>");
		okMsg.append("<comment>");
		okMsg.append(ContractEnums.ResultCode.SUCCESS.getDesc());
		okMsg.append("</comment>");
		okMsg.append("</result>");
		okMsg.append("</report_data>");
		
		StringBuffer falseMsg = new StringBuffer();
		falseMsg.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		falseMsg.append("<report_data>");
		falseMsg.append("<respon_sys>SCM</respon_sys>");
		falseMsg.append("<respon_time>"+time+"</respon_time>");
		falseMsg.append("<result>");
		falseMsg.append("<code>");
		falseMsg.append(ContractEnums.ResultCode.ERROR.getValue());
		falseMsg.append("</code>");
		falseMsg.append("<comment>");
		falseMsg.append(ContractEnums.ResultCode.ERROR.getDesc());
		falseMsg.append("</comment>");
		falseMsg.append("<error_code_list>");
		
		try {
			this.getSqlMapClient().startTransaction();
			OutWaybill outWaybill = new OutWaybill();
			String xmlDoc = (String) map.get("xmlDoc");
			
			String request_sys = getContentOfXml(xmlDoc,"/report_data/request_sys");
			String partyname = "";
			if("RYP".equals(request_sys)){
				partyname = "杭州传化日用品有限公司";
			}
			String request_time = getContentOfXml(xmlDoc,"/report_data/request_time");
			String clientnumber = getContentOfXml(xmlDoc,"/report_data/request_param/clientNumber");
			if(clientnumber.isEmpty()){
				falseMsg.append("<error_code>");//客户单号为空
				falseMsg.append(ContractEnums.ResultCode.ERR_CLIENTNUMBER_NULL.getValue());//客户单号为空
				falseMsg.append("</error_code>");//客户单号为空
				falseMsg.append("</error_code_list>");
				falseMsg.append("</result>");
				falseMsg.append("</report_data>");
				return falseMsg.toString();
			}
			String frompartyid = "";
			//查发货方id
			Map map1=new HashMap();
			map1.put("partyname", partyname);
		    String urlparty="party/partycs/selectPartyidByPartyName";
			ClientUtil cu=new ClientUtil(urlparty);
			String massage="";
			massage=cu.post(urlparty, map1).toString();
			Map newMap = Json2ObjectUtil.parseJSON2Map(massage);
			List list;
			String consignor = "";
			if("ok".equals(newMap.get("msg"))){
				frompartyid = (String) newMap.get("partyid");
				
				Map mapTemp=new HashMap();
				mapTemp.put("partyidlist", frompartyid);
			    urlparty="party/organizationcs/queryOrgName";
				cu=new ClientUtil(urlparty);
				massage=cu.post(urlparty, mapTemp).toString();
				list = Json2ObjectUtil.parseJSON2List(massage);
				newMap = (Map) list.get(0);
				consignor = (String) newMap.get("organization");
			}else{
				falseMsg.append("<error_code>");
				falseMsg.append(ContractEnums.ResultCode.ERR_NOTFOUND_PARTYID.getValue());//找不到对应会员id
				falseMsg.append("</error_code>");
				falseMsg.append("</error_code_list>");
				falseMsg.append("</result>");
				falseMsg.append("</report_data>");
				return falseMsg.toString();
			}
			
			
			//查总包id
			Map map2=new HashMap();
			map2.put("frompartyid", frompartyid);
			ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao) SofaSpringContext.getBean("shipperRelaSubContractorDao");
			String partyid = shipperRelaSubContractorDao.selectPartyIdForOutWaybill(map2);
			//查发货方默认信息
			Map map3=new HashMap();
			map3.put("topartyid", frompartyid);
			map3.put("frompartyid", partyid);
			ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext.getBean("consigneeConsignorAddressDao");
			ConsigneeConsignorAddress consigneeConsignorAddress = consigneeConsignorAddressDao.selectForOutWaybill(map3);
			//赋值outWaybill
			outWaybill.setSourcesystem(request_sys);
			outWaybill.setPartyid(partyid);
			outWaybill.setFrompartyid(frompartyid);
			outWaybill.setStatus("待确认");
			outWaybill.setClientnumber(clientnumber);
			outWaybill.setConsignor(consignor);
			if(consigneeConsignorAddress!=null){
				outWaybill.setConsignorlinkman(consigneeConsignorAddress.getLinkman());
				outWaybill.setConsignortelephonenumber(consigneeConsignorAddress.getTelephonenumber());
				outWaybill.setConsignormobilenumber(consigneeConsignorAddress.getMobilenumber());
				outWaybill.setConsignorprovince(consigneeConsignorAddress.getProvince());
				outWaybill.setConsignorcity(consigneeConsignorAddress.getCity());
				outWaybill.setConsignorregion(consigneeConsignorAddress.getRegion());
				outWaybill.setConsignortown(consigneeConsignorAddress.getTown());
			}

			
			String consigndate = getContentOfXml(xmlDoc,"/report_data/request_param/consignDate");
			String consignee = getContentOfXml(xmlDoc,"/report_data/request_param/consignee");
			String consigneelinkman = getContentOfXml(xmlDoc,"/report_data/request_param/consigneeLinkMan");
			String consigneemobilenumber = getContentOfXml(xmlDoc,"/report_data/request_param/consigneeMobileNumber");
			String consigneetelephonenumber = getContentOfXml(xmlDoc,"/report_data/request_param/consigneeTelephoneNumber");
			String consigneeprovince = getContentOfXml(xmlDoc,"/report_data/request_param/consigneeProvince");
			String consigneecity = getContentOfXml(xmlDoc,"/report_data/request_param/consigneeCity");
			String consigneeregion = getContentOfXml(xmlDoc,"/report_data/request_param/consigneeRegion");
			String consigneetown = getContentOfXml(xmlDoc,"/report_data/request_param/consigneeTown");

			
			outWaybill.setConsigndate(consigndate);
			outWaybill.setConsignee(consignee);
			outWaybill.setConsigneelinkman(consigneelinkman);
			outWaybill.setConsigneemobilenumber(consigneemobilenumber);
			outWaybill.setConsigneetelephonenumber(consigneetelephonenumber);
			outWaybill.setConsigneeprovince(consigneeprovince);
			outWaybill.setConsigneecity(consigneecity);
			outWaybill.setConsigneeregion(consigneeregion);
			outWaybill.setConsigneetown(consigneetown);
			
			int i = 0;
			//查是否客户单号是否已存在  不存在 则保存，存在且待确认则修改
			OutWaybillDao outWaybillDao = (OutWaybillDao) SofaSpringContext.getBean("outWaybillDao");
			map2.put("clientnumber", clientnumber);
			String waybillid = outWaybillDao.selectWaybillIdByClientNumber(map2);
			if(waybillid.isEmpty()){
				waybillid = outWaybillDao.saveOutWaybill(outWaybill);
			}else{
				outWaybill.setWaybillid(waybillid);
				i = outWaybillDao.updateOutWaybill(outWaybill);
				if(i<1){
					falseMsg.append("<error_code>");
					falseMsg.append(ContractEnums.ResultCode.ERR_CLIENTNUMBER_EXISTS.getValue());//客户单号已存在 且已确认
					falseMsg.append("</error_code>");
					falseMsg.append("</error_code_list>");
					falseMsg.append("</result>");
					falseMsg.append("</report_data>");
					return falseMsg.toString();
				}
			}
			
			if(i==1){
				map2.put("waybillid", waybillid);
				outWaybillDao.deleteOutGoods(map2);
			}
			/** 获取goods 并保存 */		
			Document document =  StrToXMLDocment(xmlDoc);
	        List<Element> list1 = document.selectNodes("/report_data/request_param/goodsList/goods");
	        for(Element e:list1){
	        	OutGoods outGoods = new OutGoods();
	        	String goodsname = getContentOfXml2(e,"goodsName");
	        	String goodstype = getContentOfXml2(e,"goodsType");
	        	String spec = getContentOfXml2(e,"spec");
	        	String model = getContentOfXml2(e,"model");
	        	String num = getContentOfXml2(e,"num");
	        	String packagename = getContentOfXml2(e,"packageName");
	        	String weight = getContentOfXml2(e,"weight");
	        	String volume = getContentOfXml2(e,"volume");
	        	
	        	outGoods.setWaybillid(waybillid);
	        	outGoods.setGoodsname(goodsname);
	        	outGoods.setGoodstype(goodstype);
	        	outGoods.setSpec(spec);
	        	outGoods.setModel(model);
	        	outGoods.setNum(num);
	        	outGoods.setPackagename(packagename);
	        	outGoods.setWeight(weight);
	        	outGoods.setVolume(volume);
	        	
	        	outWaybillDao.saveOutGoods(outGoods);
	        }
		} catch (Exception e) {
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
		if ("ok".equals(msg)) {
			return okMsg.toString();
		}else{
			falseMsg.append("<error_code>");//数据有误，保存出错
			falseMsg.append(ContractEnums.ResultCode.ERR_SAVE_FAILT.getValue());//数据有误，保存出错
			falseMsg.append("</error_code>");//数据有误，保存出错
			falseMsg.append("</error_code_list>");
			falseMsg.append("</result>");
			falseMsg.append("</report_data>");
			return falseMsg.toString();
		}
	}
	
	private Document StrToXMLDocment(String xmlDoc) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlDoc);// DocumentHelper.parseText(str)这个方法将传入的XML字符串转换处理后返回一个Document对象
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
	
	private String getContentOfXml(String xmlDoc,String nodesName){
		/** 获取不重复的单个节点的内容*/
        Document document =  StrToXMLDocment(xmlDoc);
        List<Element> list0 = document.selectNodes(nodesName);
        String value = "";
        for(Element el : list0){
        	value = el.getText();
        }
        return value;
	}
	private String getContentOfXml2(Element e,String nodesName){
		/** 获取重复节点的某一个节点的内容 */
        List<Element> list2 = e.selectNodes(nodesName);
        String value = "";
        for(Element el : list2){
        	value = el.getText();
        }
        return value;
	}

	/**
	 * @author haoyong
	 * @date 2014-4-18
	 * 根据运单号查找运单
	 * @param map
	 * @return
	 */
	@Override
	public String selectOutWaybillByWaybillId(Map map) {		
		OutWaybillDao outWaybillDao = (OutWaybillDao) SofaSpringContext.getBean("outWaybillDao");
		OutWaybill outWaybill = outWaybillDao.selectOutWaybillByWaybillId(map);
		List<OutGoods> list = outWaybillDao.selectOutGoodsByWaybillId(map);
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		String frompartyname="";
		if(StringUtils.isNotBlank(outWaybill.getFrompartyid())){
			map.clear();
			map.put("partyid",outWaybill.getFrompartyid());
			String result=organizationService.selectOrganizationNameByPartyId(map);
			if(StringUtils.isNotBlank(result)) {
				List<Map<String, Object>> listOrg = Json2ObjectUtil.parseJSON2List(result);
				for(Map org:listOrg){
					frompartyname=org.get("name").toString();
				}
			}
		}
		outWaybill.setFrompartyname(frompartyname);		
		outWaybill.setOutGoodsList(list);
		String msgJson="";
	    if (outWaybill!=null) {
	    	msgJson=JsonGenerateUtil.bean2json(outWaybill);
	    }
		return msgJson;
	}

	@Override
	public String saveOrderWaybill(OutWaybill waybill) {
		OutWaybillDao outWaybillDao = (OutWaybillDao) SofaSpringContext.getBean("outWaybillDao");
		String msg = outWaybillDao.saveOrderWaybill(waybill);
		return msg;
	}
	
	
}
