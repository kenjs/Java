package tf56.contract.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.serfj.RestController;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jaxen.pattern.Pattern;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mysql.jdbc.Field;

import tf56.contract.services.OrganizationService;
import tf56.contract.services.PartyService;
import tf56.contract.services.ReportQueryService;
import tf56.contract.services.WaybillAmountDao;
import tf56.contract.services.WaybillDao;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.SofaSpringContext;

public class ReportQueryServiceImpl extends SqlMapClientDaoSupport implements ReportQueryService {
	/**
	 * @author wei.huang
	 * @date 2013-11-28
	 * @function 获取完整的满足报表信息要求的运单明细表
	 * @return Json
	 */
	public String selectWaybillDetailForReport(Map map){
		String partyid = map.get("partyid").toString();
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		//获取以"-"连接的frompartyid字符串
		String fromPartyIdList=waybillDao.selectNoRepeatFromOrToPartyId(partyid, 1);
		map.put("frompartyid_temp", fromPartyIdList);
		//获取以"-"连接的topartyid字符串
		String toPartyIdList=waybillDao.selectNoRepeatFromOrToPartyId(partyid, 0);
		map.put("topartyid_temp", toPartyIdList);
		/***根据收发货方名称模糊查询与之匹配的partyid的集合，并转换成指定形式的参数   开始***/
		//发货方参数处理
		if(map.get("consignor")!=null){
			if(!map.get("consignor").toString().equals("")){
				Map fromTempMap=new HashMap();
				fromTempMap.put("word", map.get("consignor").toString());
				fromTempMap.put("partyidlist", fromPartyIdList);
				OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
				String msg_From=organizationService.queryOrgName(fromTempMap);
				List<Map<String,Object>> fromList=Json2ObjectUtil.parseJSON2List(msg_From);
				StringBuffer finalFromPartyIdList=new StringBuffer();
				//转换为以","连接的字符串
				if(fromList.size()>0){
					for(int i=0;i<fromList.size();i++){
						finalFromPartyIdList.append(fromList.get(i).get("partyid").toString());
						if((i+1)<fromList.size()){
							finalFromPartyIdList.append(",");
						}
					}
				}else{
					finalFromPartyIdList.append("-1");//表示不存在相关发货方
				}
				map.put("frompartyidlist", finalFromPartyIdList.toString());
			}
		}
		//分包商参数处理
		if(map.get("consignee")!=null){
			if(!map.get("consignee").toString().equals("")){
				Map toTempMap=new HashMap();
				toTempMap.put("word", map.get("consignee").toString());
				toTempMap.put("partyidlist", toPartyIdList);
				OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
				String msg_To=organizationService.queryOrgName(toTempMap);
				List<Map<String,Object>> toList=Json2ObjectUtil.parseJSON2List(msg_To);
				StringBuffer finalToPartyIdList=new StringBuffer();
				//转换为以","连接的字符串
				if(toList.size()>0){
					for(int i=0;i<toList.size();i++){
						finalToPartyIdList.append(toList.get(i).get("partyid").toString());
						if((i+1)<toList.size()){
							finalToPartyIdList.append(",");
						}
					}
				}else{
					finalToPartyIdList.append("-1");//表示不存在相关分包商
				}
				map.put("topartyidlist", finalToPartyIdList.toString());
			}
		}
		/***根据收发货方名称模糊查询与之匹配的partyid的集合，并转换成指定形式的参数   结束***/
		//获取毛利率查询条件
		String condition_GrossMargin="全部";
		if(map.get("grossmargin")!=null){
			condition_GrossMargin=map.get("grossmargin").toString();
		}
		//获取运单表的主要信息(包含发货方、分包商的名称)
		PartyService partyService=(PartyService)SofaSpringContext.getBean("partyService");
		List<Map> list=partyService.addConsignorConsigneeToWaybillListForReport(map);
		//获取运单应收应付金额的统计信息
		WaybillAmountDao waybillAmountDao=(WaybillAmountDao)SofaSpringContext.getBean("waybillAmountDao");
		List<Map> myAmountList=waybillAmountDao.selectStatisticAmount(partyid);
		float totalNum=0;//总数量
		float totalWeight=0;//总重量
		float totalVolume=0;//总体积
		float totalInAmount=0;//总应收金额
		float totalOutAmount=0;//总应付金额
		for(int i=0;i<list.size();i++){
			/**拼接应收应付金额  开始**/
			boolean flagIn=false;//标记是否有应收金额
			boolean flagOut=false;//标记是否有应付金额
			for(int j=0;j<myAmountList.size();j++){
				if(list.get(i).get("waybillid").toString().equals(myAmountList.get(j).get("waybillid").toString())){
					if(myAmountList.get(j).get("inorout").toString().equals("0")){
						list.get(i).put("needoutamount", myAmountList.get(j).get("amount").toString());
						flagOut=true;
					}else{
						list.get(i).put("needinamount", myAmountList.get(j).get("amount").toString());
						flagIn=true;
					}
					myAmountList.remove(j);
					j--;
				}
			}
			if(!flagOut){
				list.get(i).put("needoutamount", "0");
			}
			if(!flagIn){
				list.get(i).put("needinamount", "0");
			}
			/**拼接应收应付金额  结束**/
			
			/**计算毛利、毛利率以及各种统计数据  开始**/
			float inAmount=Float.parseFloat(list.get(i).get("needinamount").toString());
			float outAmount=Float.parseFloat(list.get(i).get("needoutamount").toString());
			float margin=0;
			list.get(i).put("grossprofit",new DecimalFormat("###########0.00").format(inAmount-outAmount));//毛利
			if(!list.get(i).get("needinamount").toString().equals("0")){
				margin=(inAmount-outAmount)/inAmount;
				list.get(i).put("grossmargin",margin);//毛利率
				
			}else{
				list.get(i).put("grossmargin","0");//毛利率
			}
			//当要求为正常时仅保留毛利率大于等于3%的记录，当要求为异常时仅保留毛利率小于3%的记录
			if(condition_GrossMargin.equals("正常")){
				if(margin<0.03){
					list.remove(i);
					i--;
					continue;
				}
			}else if(condition_GrossMargin.equals("异常")){
				if(margin>=0.03){
					list.remove(i);
					i--;
					continue;
				}
			}
			if(list.get(i).get("factnum")==null){
				list.get(i).put("factnum","0");
			}else if(list.get(i).get("factnum").toString().equals("")){
				list.get(i).remove("factnum");
				list.get(i).put("factnum","0");
			}
			if(list.get(i).get("factweight")==null){
				list.get(i).put("factweight","0");
			}else if(list.get(i).get("factweight").toString().equals("")){
				list.get(i).remove("factweight");
				list.get(i).put("factweight","0");
			}
			if(list.get(i).get("factvolume")==null){
				list.get(i).put("factvolume","0");
			}else if(list.get(i).get("factvolume").toString().equals("")){
				list.get(i).remove("factvolume");
				list.get(i).put("factvolume","0");
			}
			
			//计算统计数据
			totalNum+=Float.parseFloat(list.get(i).get("factnum").toString());//统计数量
			totalWeight+=Float.parseFloat(list.get(i).get("factweight").toString());//统计重量
			totalVolume+=Float.parseFloat(list.get(i).get("factvolume").toString());//统计体积
			totalInAmount+=inAmount;//统计应收金额
			totalOutAmount+=outAmount;//统计应付金额
			/**计算毛利、毛利率以及各种统计数据  结束**/
		}
		//按分页要求筛选数据并统计数据
		List<Map<String,Object>> returnList=new ArrayList<Map<String,Object>>();//返回给前台的数据
		int pageSize=Integer.parseInt(map.get("pageSize").toString());
		int skipCount=Integer.parseInt(map.get("skipCount").toString());
		int listLen=list.size();
		for(int i=skipCount;i<(pageSize+skipCount);i++){
			if(i>=listLen){
				break;
			}
			returnList.add(list.get(i));
		}
		//将统计数据放到开头
		Map<String,Object> totalMap=new HashMap<String,Object>();
		totalMap.put("totalnum",new DecimalFormat("###########0.00").format(totalNum));
		totalMap.put("totalweight", new DecimalFormat("###########0.000").format(totalWeight));
		totalMap.put("totalvolume",new DecimalFormat("###########0.000").format(totalVolume));
		totalMap.put("totalinamount",new DecimalFormat("###,###,###,##0.00").format(totalInAmount));
		totalMap.put("totaloutamount",new DecimalFormat("###,###,###,##0.00").format(totalOutAmount));
		totalMap.put("totalgrossprofit",new DecimalFormat("###,###,###,##0.00").format(totalInAmount-totalOutAmount));
		if(!(String.valueOf(totalInAmount).equals("0.0"))){
			totalMap.put("totalgrossmargin",(totalInAmount-totalOutAmount)/totalInAmount);
		}else{
			totalMap.put("totalgrossmargin","0");
		}
		returnList.add(0,totalMap);
		String msgJson=JsonGenerateUtil.getPageListJson(returnList, String.valueOf(listLen));
		return msgJson;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-28
	 * @function 获取导出Excel表所需的全部信息
	 * @return List<Map>
	 */
	public List<Map> selectAllForExportExcel(Map map){
		String partyid = map.get("partyid").toString();
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		//获取以"-"连接的frompartyid字符串
		String fromPartyIdList=waybillDao.selectNoRepeatFromOrToPartyId(partyid, 1);
		map.put("frompartyid_temp", fromPartyIdList);
		//获取以"-"连接的topartyid字符串
		String toPartyIdList=waybillDao.selectNoRepeatFromOrToPartyId(partyid, 0);
		map.put("topartyid_temp", toPartyIdList);
		/***根据收发货方名称模糊查询与之匹配的partyid的集合，并转换成指定形式的参数   开始***/
		//发货方参数处理
		if(map.get("consignor")!=null){
			if(!map.get("consignor").toString().equals("")){
				Map fromTempMap=new HashMap();
				fromTempMap.put("word", map.get("consignor").toString());
				fromTempMap.put("partyidlist", fromPartyIdList);
				OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
				String msg_From=organizationService.queryOrgName(fromTempMap);
				List<Map<String,Object>> fromList=Json2ObjectUtil.parseJSON2List(msg_From);
				StringBuffer finalFromPartyIdList=new StringBuffer();
				//转换为以","连接的字符串
				if(fromList.size()>0){
					for(int i=0;i<fromList.size();i++){
						finalFromPartyIdList.append(fromList.get(i).get("partyid").toString());
						if((i+1)<fromList.size()){
							finalFromPartyIdList.append(",");
						}
					}
				}else{
					finalFromPartyIdList.append("-1");//表示不存在相关发货方
				}
				map.put("frompartyidlist", finalFromPartyIdList.toString());
			}
		}
		//分包商参数处理
		if(map.get("consignee")!=null){
			if(!map.get("consignee").toString().equals("")){
				Map toTempMap=new HashMap();
				toTempMap.put("word", map.get("consignee").toString());
				toTempMap.put("partyidlist", toPartyIdList);
				OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
				String msg_To=organizationService.queryOrgName(toTempMap);
				List<Map<String,Object>> toList=Json2ObjectUtil.parseJSON2List(msg_To);
				StringBuffer finalToPartyIdList=new StringBuffer();
				//转换为以","连接的字符串
				if(toList.size()>0){
					for(int i=0;i<toList.size();i++){
						finalToPartyIdList.append(toList.get(i).get("partyid").toString());
						if((i+1)<toList.size()){
							finalToPartyIdList.append(",");
						}
					}
				}else{
					finalToPartyIdList.append("-1");//表示不存在相关分包商
				}
				map.put("topartyidlist", finalToPartyIdList.toString());
			}
		}
		/***根据收发货方名称模糊查询与之匹配的partyid的集合，并转换成指定形式的参数   结束***/
		//获取毛利率查询条件
		String condition_GrossMargin="全部";
		if(map.get("grossmargin")!=null){
			condition_GrossMargin=map.get("grossmargin").toString();
		}
		//获取运单表的主要信息(包含发货方、分包商的名称)
		PartyService partyService=(PartyService)SofaSpringContext.getBean("partyService");
		List<Map> list=partyService.addConsignorConsigneeToWaybillListForReport(map);
		//获取运单应收应付金额的统计信息
		WaybillAmountDao waybillAmountDao=(WaybillAmountDao)SofaSpringContext.getBean("waybillAmountDao");
		List<Map> myAmountList=waybillAmountDao.selectStatisticAmount(partyid);
		float totalNum=0;//总数量
		float totalWeight=0;//总重量
		float totalVolume=0;//总体积
		float totalInAmount=0;//总应收金额
		float totalOutAmount=0;//总应付金额
		List<Map<String,Object>> returnList=new ArrayList<Map<String,Object>>();//返回给前台的数据
		for(int i=0;i<list.size();i++){
			/**拼接应收应付金额  开始**/
			boolean flagIn=false;//标记是否有应收金额
			boolean flagOut=false;//标记是否有应付金额
			for(int j=0;j<myAmountList.size();j++){
				if(list.get(i).get("waybillid").toString().equals(myAmountList.get(j).get("waybillid").toString())){
					if(myAmountList.get(j).get("inorout").toString().equals("0")){
						list.get(i).put("needoutamount", myAmountList.get(j).get("amount").toString());
						flagOut=true;
					}else{
						list.get(i).put("needinamount", myAmountList.get(j).get("amount").toString());
						flagIn=true;
					}
					myAmountList.remove(j);
					j--;
				}
			}
			if(!flagOut){
				list.get(i).put("needoutamount", "0");
			}
			if(!flagIn){
				list.get(i).put("needinamount", "0");
			}
			/**拼接应收应付金额  结束**/
			
			/**计算毛利、毛利率以及各种统计数据  开始**/
			float inAmount=Float.parseFloat(list.get(i).get("needinamount").toString());
			float outAmount=Float.parseFloat(list.get(i).get("needoutamount").toString());
			float margin=0;
			list.get(i).put("grossprofit",new DecimalFormat("###########0.00").format(inAmount-outAmount));//毛利
			if(!list.get(i).get("needinamount").toString().equals("0")){
				margin=(inAmount-outAmount)/inAmount;
				list.get(i).put("grossmargin",margin);//毛利率
				
			}else{
				list.get(i).put("grossmargin","0");//毛利率
			}
			if(list.get(i).get("factnum")==null){
				list.get(i).put("factnum","0");
			}else if(list.get(i).get("factnum").toString().equals("")){
				list.get(i).remove("factnum");
				list.get(i).put("factnum","0");
			}
			
			if(list.get(i).get("factweight")==null){
				list.get(i).put("factweight","0");
			}else if(list.get(i).get("factweight").toString().equals("")){
				list.get(i).remove("factweight");
				list.get(i).put("factweight","0");
			}
			
			if(list.get(i).get("factvolume")==null){
				list.get(i).put("factvolume","0");
			}else if(list.get(i).get("factvolume").toString().equals("")){
				list.get(i).remove("factvolume");
				list.get(i).put("factvolume","0");
			}
			
			/***根据毛利率的要求过滤  开始***/
			//当要求为正常时仅保留毛利率大于等于3%的记录，当要求为异常时仅保留毛利率小于3%的记录
			if(condition_GrossMargin.equals("正常")){
				if(margin<0.03){
					list.remove(i);
					i--;
					continue;
				}
			}else if(condition_GrossMargin.equals("异常")){
				if(margin>=0.03){
					list.remove(i);
					i--;
					continue;
				}
			}
			/***根据毛利率的要求过滤  结束***/
			totalNum+=Float.parseFloat(list.get(i).get("factnum").toString());//统计数量
			totalWeight+=Float.parseFloat(list.get(i).get("factweight").toString());//统计重量
			totalVolume+=Float.parseFloat(list.get(i).get("factvolume").toString());//统计体积
			totalInAmount+=inAmount;//统计应收金额
			totalOutAmount+=outAmount;//统计应付金额
			/**计算毛利、毛利率以及各种统计数据  结束**/
		}
		
		if(list.size()>0){
			Map<String,Object> totalMap=new HashMap<String,Object>();
			totalMap.put("totalnum",new DecimalFormat("###########0.00").format(totalNum));
			totalMap.put("totalweight", new DecimalFormat("###########0.000").format(totalWeight));
			totalMap.put("totalvolume",new DecimalFormat("###########0.000").format(totalVolume));
			totalMap.put("totalinamount",new DecimalFormat("###,###,###,##0.00").format(totalInAmount));
			totalMap.put("totaloutamount",new DecimalFormat("###,###,###,##0.00").format(totalOutAmount));
			totalMap.put("totalgrossprofit",new DecimalFormat("###,###,###,##0.00").format(totalInAmount-totalOutAmount));
			if(!(String.valueOf(totalInAmount).equals("0.0"))){
				totalMap.put("totalgrossmargin",(totalInAmount-totalOutAmount)/totalInAmount);
			}else{
				totalMap.put("totalgrossmargin","0");
			}
			list.add(totalMap);//将统计信息插入到list的末尾
		}
		return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-4
	 * @function 导出Excel
	 * @param title 文件标题
	 * @param headers 表头
	 * @param map 查询参数
	 * @param response
	 * @throws IOException 
	 */
	public void exportExcel(String title,String[] headers,HttpServletResponse response,Map map) throws IOException{
		// 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title); 
        // 设置表格默认列宽度为22个字节  
        sheet.setDefaultColumnWidth(22); 
        // 生成表头样式  
        HSSFCellStyle headStyle = workbook.createCellStyle(); 
        // 设置这些样式 
        headStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成表头字体 
        HSSFFont headFont = workbook.createFont(); 
        headFont.setColor(HSSFColor.VIOLET.index);  
        headFont.setFontHeightInPoints((short) 12);  
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        // 把字体应用到当前的样式  
        headStyle.setFont(headFont);  
        // 生成并设置第一行(统计行)样式  
        HSSFCellStyle statisticStyle = workbook.createCellStyle();
        statisticStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        statisticStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        statisticStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        statisticStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        statisticStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        statisticStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        statisticStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        HSSFFont statisticFont = workbook.createFont();
        statisticFont.setColor(HSSFColor.BLUE.index);  
        statisticFont.setFontHeightInPoints((short)12);
        statisticFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        statisticStyle.setFont(statisticFont);
        //生成主体样式
        HSSFCellStyle commonStyle = workbook.createCellStyle();
        commonStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        commonStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        commonStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        commonStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        commonStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        commonStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFFont commonFont = workbook.createFont();
        commonFont.setColor(HSSFColor.BLACK.index);  
        commonFont.setFontHeightInPoints((short) 12);  
        commonFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        commonStyle.setFont(commonFont);
        //生成标记字体
        HSSFCellStyle markStyle = workbook.createCellStyle();
        markStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        markStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        markStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        markStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        markStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        markStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFFont markFont = workbook.createFont();
        markFont.setColor(HSSFColor.RED.index);  
        markFont.setFontHeightInPoints((short) 12);  
        markFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        markStyle.setFont(markFont);
        // 产生表格标题行和统计行
        HSSFRow row = sheet.createRow(0);
        HSSFRow statisticRow = sheet.createRow(1);
        HSSFCell statisticCell=null;
        for (int i = 0; i < headers.length; i++)
        {  
            HSSFCell headCell = row.createCell(i);  
            headCell.setCellStyle(headStyle);  
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
            headCell.setCellValue(text);
            statisticCell=statisticRow.createCell(i);
            statisticCell.setCellStyle(statisticStyle);
            statisticCell.setCellValue("合计");
        }
        //合并单元格
        CellRangeAddress region=new CellRangeAddress(1, 1, 0, 7);
        sheet.addMergedRegion(region);
        //获取数据
        List<Map> list=selectAllForExportExcel(map);
        if(list.size()>0){
	        //填写第一行统计行的数据
	        for(int i=8;i<15;i++){
	        	statisticCell=statisticRow.getCell(i);
	            switch(i){
	            case 8:
	            	statisticCell.setCellValue(list.get(list.size()-1).get("totalnum").toString());
	            	continue;
	            case 9:
	            	statisticCell.setCellValue(list.get(list.size()-1).get("totalweight").toString());
	            	continue;
	            case 10:
	            	statisticCell.setCellValue(list.get(list.size()-1).get("totalvolume").toString());
	            	continue;
	            case 11:
	            	statisticCell.setCellValue(list.get(list.size()-1).get("totalinamount").toString());
	            	continue;
	            case 12:
	            	statisticCell.setCellValue(list.get(list.size()-1).get("totaloutamount").toString());
	            	continue;
	            case 13:
	            	statisticCell.setCellValue(list.get(list.size()-1).get("totalgrossprofit").toString());
	            	continue;
	            case 14:
	            	float margin=Float.parseFloat(list.get(list.size()-1).get("totalgrossmargin").toString())*100;
	            	String sMargin=new DecimalFormat("###,###,###,##0.00").format(margin)+"%";
	            	statisticCell.setCellValue(sMargin);
	            	continue;
	            }
	        }
	        //冻结第一、二行和第一列
	        sheet.createFreezePane(1, 2);
	        //生成主体部分的数据
	        HSSFRow commonRow = null;
	        int rowIndex=1;
	        for(int i=list.size()-2;i>=0;i--){
	        	rowIndex++;
	        	commonRow = sheet.createRow(rowIndex);
	        	for(int j=0;j<15;j++){
	        		HSSFCell commonCell = commonRow.createCell(j);
	        		commonCell.setCellStyle(commonStyle);
	        		switch(j){
	        		case 0:
	        			commonCell.setCellValue(list.get(i).get("waybillnumber").toString());
	        			continue;
	                case 1:
	                	commonCell.setCellValue(list.get(i).get("status")==null?"":list.get(i).get("status").toString());
	                	continue;
	        		case 2:
	        			commonCell.setCellValue(list.get(i).get("urgencydegree")==null?"":list.get(i).get("urgencydegree").toString());
	        			continue;
	                case 3:
	                	commonCell.setCellValue(list.get(i).get("consigndate")==null?"":list.get(i).get("consigndate").toString());
	                	continue;
	                case 4:
	                	commonCell.setCellValue(list.get(i).get("consignor")==null?"":list.get(i).get("consignor").toString());
	                	continue;
	                case 5:
	                	commonCell.setCellValue((list.get(i).get("consignorprovince")==null?"":list.get(i).get("consignorprovince").toString())+
	                			(list.get(i).get("consignorcity")==null?"":list.get(i).get("consignorcity").toString())+
	                			(list.get(i).get("consignorregion")==null?"":list.get(i).get("consignorregion").toString()));
	                	continue;
	                case 6:
	                	commonCell.setCellValue((list.get(i).get("consigneeprovince")==null?"":list.get(i).get("consigneeprovince").toString())+
	                			(list.get(i).get("consigneecity")==null?"":list.get(i).get("consigneecity").toString())+
	                        	(list.get(i).get("consigneeregion")==null?"":list.get(i).get("consigneeregion").toString()));
	                	continue;
	                case 7:
	                	commonCell.setCellValue(list.get(i).get("consignee")==null?"":list.get(i).get("consignee").toString());
	                	continue;
	                case 8:
	                	commonCell.setCellValue(list.get(i).get("factnum").toString());
	                	continue;
	                case 9:
	                	commonCell.setCellValue(list.get(i).get("factweight").toString());
	                	continue;
	                case 10:
	                	commonCell.setCellValue(list.get(i).get("factvolume").toString());
	                	continue;
	                case 11:
	                	commonCell.setCellValue(list.get(i).get("needinamount").toString());
	                	continue;
	                case 12:
	                	commonCell.setCellValue(list.get(i).get("needoutamount").toString());
	                	continue;
	                case 13:
	                	commonCell.setCellValue(list.get(i).get("grossprofit").toString());
	                	continue;
	                case 14:
	                	float margin=Float.parseFloat(list.get(i).get("grossmargin").toString());
	                	if(margin<0.03){
	                		commonCell.setCellStyle(markStyle);
	            		}
	                	String sMargin=new DecimalFormat("###,###,###,##0.00").format(margin*100)+"%";
	                	commonCell.setCellValue(sMargin);
	                	continue;
	                }
	        	}
	        }
        }
        ServletOutputStream outStream=null;
        try
        {
        	response.reset();    
            response.setContentType("application/x-msdownload");    
            response.setHeader("Content-Disposition","attachment; filename="+new String(title.getBytes("gb2312"),"ISO-8859-1")+".xls");    
            outStream = response.getOutputStream();
            workbook.write(outStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally{    
            outStream.close();
        }   
	}
}
