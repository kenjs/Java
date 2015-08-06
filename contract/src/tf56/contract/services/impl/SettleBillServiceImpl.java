package tf56.contract.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import tf56.contract.services.SettleBillDao;
import tf56.contract.services.SettleBillService;
import tf56.sofa.util.SofaSpringContext;



public class SettleBillServiceImpl implements SettleBillService{
	/**
	 * @author yao.xia
	 * @date 2013-12-18
	 * @function 获取导出Excel表所需的全部信息
	 * @return List<Map>
	 */
	public List<Map> selectAllForExportExcel(Map map){
		String partyid = map.get("partyid").toString();
		SettleBillDao settleBillDao = (SettleBillDao)SofaSpringContext.getBean("settleBillDao");
		List<Map> list = settleBillDao.selectExportList(map);
		return list;
	}
	/**
	 * @author yao.xia
	 * @date 2013-12-18
	 * @function 导出Excel 应收
	 * @param title 文件标题
	 * @param headers 表头
	 * @param map 查询参数
	 * @param response
	 * @throws IOException 
	 */
	public void exportInExcel(String title,String[] headers,HttpServletResponse response,Map map) throws IOException{
		// 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title); 
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth(15);
        
        // 生成报表标题样式  
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        titleStyle.setBorderBottom(HSSFCellStyle.NO_FILL);  
        titleStyle.setBorderLeft(HSSFCellStyle.NO_FILL);  
        titleStyle.setBorderRight(HSSFCellStyle.NO_FILL);  
        titleStyle.setBorderTop(HSSFCellStyle.NO_FILL);  
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setWrapText(true);
        // 生成标题字体
        HSSFFont titleFont = workbook.createFont();
        titleFont.setColor(HSSFColor.BLUE.index);  
        titleFont.setFontHeightInPoints((short)28);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        titleStyle.setFont(titleFont);
        // 生成页眉页脚
        HSSFCellStyle tfStyle = workbook.createCellStyle();
        tfStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        tfStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        tfStyle.setBorderBottom(HSSFCellStyle.NO_FILL);  
        tfStyle.setBorderLeft(HSSFCellStyle.NO_FILL);  
        tfStyle.setBorderRight(HSSFCellStyle.NO_FILL);  
        tfStyle.setBorderTop(HSSFCellStyle.NO_FILL);  
        tfStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        // 生成页眉页脚字体
        HSSFFont tfFont = workbook.createFont();
        tfFont.setColor(HSSFColor.BLUE.index);  
        tfFont.setFontHeightInPoints((short)12);
        tfFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        tfStyle.setFont(tfFont);
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
        // 产生表格标题和表头行
        HSSFRow titleRow = sheet.createRow(0);
        HSSFRow topRow = sheet.createRow(1);
        HSSFRow row = sheet.createRow(2);
        for (int y = 0; y < headers.length; y++){
        	//标题
        	titleRow.setHeightInPoints((short)46);//固定高度
//        	titleRow.setZeroHeight(true);
        	HSSFCell titleCell = titleRow.createCell(y);
        	titleCell.setCellStyle(titleStyle);
        	titleCell.setCellValue("物流对账明细表");
        	//
        	HSSFCell topCell = topRow.createCell(y);
        	topCell.setCellStyle(tfStyle);
        	if(y==0){
        		topCell.setCellValue("客户名称：");
        	}else if(y==1){
        		topCell.setCellValue(map.get("organization").toString());
        	}else if(y==10){
        		topCell.setCellValue("计费周期：");
        	}else if(y==11){
        		topCell.setCellValue(map.get("settledate").toString());
        	}        	
        	//表头
            HSSFCell headCell = row.createCell(y);  
            headCell.setCellStyle(headStyle);  
            HSSFRichTextString text = new HSSFRichTextString(headers[y]);  
            headCell.setCellValue(text);
        }
        //合并单元格
        CellRangeAddress region1=new CellRangeAddress(1, 1, 1, 2);
        sheet.addMergedRegion(region1);
        CellRangeAddress region=new CellRangeAddress(0, 0, 0, 16);
        sheet.addMergedRegion(region);
        CellRangeAddress region2=new CellRangeAddress(1, 1, 11, 13);
        sheet.addMergedRegion(region2);
        //获取数据
        List<Map> list=selectAllForExportExcel(map);
        int rowIndex=2;
        if(list.size()>0){
	        //冻结第三行和第一列
	        sheet.createFreezePane(0,3);
	        //生成主体部分的数据
	        HSSFRow commonRow = null;
	        for(int i=list.size()-1;i>=0;i--){
	        	rowIndex++;
	        	commonRow = sheet.createRow(rowIndex);
	        	for(int j=0;j<headers.length;j++){
	        		HSSFCell commonCell = commonRow.createCell(j);
	        		commonCell.setCellStyle(commonStyle);
	        		switch(j){
	        		case 0:
	        			commonCell.setCellValue(list.get(i).get("clientnumber")==null?"":list.get(i).get("clientnumber").toString());
	        			continue;
	                case 1:
	                	commonCell.setCellValue(list.get(i).get("waybillnumber")==null?"":list.get(i).get("waybillnumber").toString());
	                	continue;
	        		case 2:
	        			commonCell.setCellValue(list.get(i).get("consigndate")==null?"":list.get(i).get("consigndate").toString());
	        			continue;
	                case 3:
	                	commonCell.setCellValue(list.get(i).get("consigneetown")==null?"":list.get(i).get("consigneetown").toString());
	                	continue;
	                case 4:
	                	commonCell.setCellValue((list.get(i).get("consigneeprovince")==null?"":list.get(i).get("consigneeprovince").toString())+
	                			(list.get(i).get("consigneecity")==null?"":list.get(i).get("consigneecity").toString())+
	                			(list.get(i).get("consigneeregion")==null?"":list.get(i).get("consigneeregion").toString()));
	                	continue;
	                case 5:
	                	commonCell.setCellValue((list.get(i).get("distance")==null?"":list.get(i).get("distance").toString()));
	                	continue;
	                case 6:
	                	commonCell.setCellValue((list.get(i).get("settletype")==null?"":list.get(i).get("settletype").toString()));
	                	continue;
	                case 7:
	                	commonCell.setCellValue((list.get(i).get("goodsName")==null?"":list.get(i).get("goodsName").toString()));
	                	continue;
	                case 8:
	                	commonCell.setCellValue(list.get(i).get("factweight")==null?"0.0":list.get(i).get("factweight").toString());
	                	continue;
	                case 9:
	                	commonCell.setCellValue(list.get(i).get("factvolume")==null?"0.0":list.get(i).get("factvolume").toString());
	                	continue;
	                case 10:
	                	commonCell.setCellValue(list.get(i).get("freight")==null?"0.0":list.get(i).get("freight").toString());
	                	continue;
	                case 11:
	                	commonCell.setCellValue(list.get(i).get("delivery")==null?"0.0":list.get(i).get("delivery").toString());
	                	continue;
	                case 12:
	                	commonCell.setCellValue(list.get(i).get("charge")==null?"0.0":list.get(i).get("charge").toString());
	                	continue;
	                case 13:
	                	commonCell.setCellValue(list.get(i).get("pack")==null?"0.0":list.get(i).get("pack").toString());
	                	continue;
	                case 14:
	                	commonCell.setCellValue(list.get(i).get("other")==null?"0.0":list.get(i).get("other").toString());
	                	continue;
	                case 15:
	                	commonCell.setCellValue(list.get(i).get("total")==null?"0.0":list.get(i).get("total").toString());
	                	continue;
	                case 16:
	                	commonCell.setCellValue("");
	                	continue;
	                }
	        	}
	        }
        }
        rowIndex++;
        HSSFRow footRow = sheet.createRow(rowIndex);
        for (int  x = 0; x < headers.length; x++){
        	//页脚
        	HSSFCell footCell = footRow.createCell(x);
        	footCell.setCellStyle(tfStyle);
        	if(x==0){
        		footCell.setCellValue("制表人：");
        	}else if(x==10){
        		footCell.setCellValue("审核人：");
        	}
        }
        ServletOutputStream outStream=null;
        try{
        	response.reset();    
            response.setContentType("application/x-msdownload");    
            response.setHeader("Content-Disposition","attachment; filename="+new String(title.getBytes("gb2312"),"ISO-8859-1")+".xls");    
            outStream = response.getOutputStream();
            workbook.write(outStream);
        }catch (IOException e){
            e.printStackTrace();
        }finally{    
            outStream.close();
        }   
	}
	
	/**
	 * @author yao.xia
	 * @date 2013-12-19
	 * @function 导出Excel 应付
	 * @param title 文件标题
	 * @param headers 表头
	 * @param map 查询参数
	 * @param response
	 * @throws IOException 
	 */
	public void exportOutExcel(String title,String[] headers,HttpServletResponse response,Map map) throws IOException{
		// 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title); 
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth(15); 
        // 生成报表标题样式  
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        titleStyle.setBorderBottom(HSSFCellStyle.NO_FILL);  
        titleStyle.setBorderLeft(HSSFCellStyle.NO_FILL);  
        titleStyle.setBorderRight(HSSFCellStyle.NO_FILL);  
        titleStyle.setBorderTop(HSSFCellStyle.NO_FILL);  
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成标题字体
        HSSFFont titleFont = workbook.createFont();
        titleFont.setColor(HSSFColor.BLUE.index);  
        titleFont.setFontHeightInPoints((short)28);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        titleStyle.setFont(titleFont);
        // 生成页眉页脚
        HSSFCellStyle tfStyle = workbook.createCellStyle();
        tfStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        tfStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        tfStyle.setBorderBottom(HSSFCellStyle.NO_FILL);  
        tfStyle.setBorderLeft(HSSFCellStyle.NO_FILL);  
        tfStyle.setBorderRight(HSSFCellStyle.NO_FILL);  
        tfStyle.setBorderTop(HSSFCellStyle.NO_FILL);  
        tfStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        // 生成页眉页脚字体
        HSSFFont tfFont = workbook.createFont();
        tfFont.setColor(HSSFColor.BLUE.index);  
        tfFont.setFontHeightInPoints((short)12);
        tfFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
        tfStyle.setFont(tfFont);
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
        // 产生表格标题和表头行
        HSSFRow titleRow = sheet.createRow(0);
        HSSFRow topRow = sheet.createRow(1);
        HSSFRow row = sheet.createRow(2);
        for (int y = 0; y < headers.length; y++){
        	//标题
        	titleRow.setHeightInPoints((short)46);//固定高度
//        	titleRow.setZeroHeight(true);
        	HSSFCell titleCell = titleRow.createCell(y);
        	titleCell.setCellStyle(titleStyle);
        	titleCell.setCellValue("物流对账明细表");
        	//
        	HSSFCell topCell = topRow.createCell(y);
        	topCell.setCellStyle(tfStyle);
        	if(y==0){
        		topCell.setCellValue("客户名称：");
        	}else if(y==1){
        		topCell.setCellValue(map.get("organization").toString());
        	}else if(y==10){
        		topCell.setCellValue("计费周期：");
        	}else if(y==11){
        		topCell.setCellValue(map.get("settledate").toString());
        	}
        	//表头
            HSSFCell headCell = row.createCell(y);  
            headCell.setCellStyle(headStyle);  
            HSSFRichTextString text = new HSSFRichTextString(headers[y]);  
            headCell.setCellValue(text);
        }
        //合并单元格
        CellRangeAddress region=new CellRangeAddress(0, 0, 0, 18);
        sheet.addMergedRegion(region);
        CellRangeAddress region1=new CellRangeAddress(1, 1, 11, 13);
        sheet.addMergedRegion(region1);
        CellRangeAddress region2=new CellRangeAddress(1, 1, 1, 2);
        sheet.addMergedRegion(region2);
        //获取数据
        List<Map> list=selectAllForExportExcel(map);
        int rowIndex=2;
        if(list.size()>0){
	        //冻结第一行和第一列
	        sheet.createFreezePane(0, 3);
	        //生成主体部分的数据
	        HSSFRow commonRow = null;
	        for(int i=list.size()-1;i>=0;i--){
	        	rowIndex++;
	        	commonRow = sheet.createRow(rowIndex);
	        	for(int j=0;j<headers.length;j++){
	        		HSSFCell commonCell = commonRow.createCell(j);
	        		commonCell.setCellStyle(commonStyle);
	        		switch(j){
	        		case 0:
	        			commonCell.setCellValue(list.get(i).get("clientnumber")==null?"":list.get(i).get("clientnumber").toString());
	        			continue;
	                case 1:
	                	commonCell.setCellValue(list.get(i).get("waybillnumber")==null?"":list.get(i).get("waybillnumber").toString());
	                	continue;
	                case 2:
	                	commonCell.setCellValue(list.get(i).get("systemDispatchNumber")==null?"":list.get(i).get("systemDispatchNumber").toString());
	                	continue;
	        		case 3:
	        			commonCell.setCellValue(list.get(i).get("consigndate")==null?"":list.get(i).get("consigndate").toString());
	        			continue;
	                case 4:
	                	commonCell.setCellValue(list.get(i).get("consigneetown")==null?"":list.get(i).get("consigneetown").toString());
	                	continue;
	                case 5:
	                	commonCell.setCellValue((list.get(i).get("consigneeprovince")==null?"":list.get(i).get("consigneeprovince").toString())+
	                			(list.get(i).get("consigneecity")==null?"":list.get(i).get("consigneecity").toString())+
	                			(list.get(i).get("consigneeregion")==null?"":list.get(i).get("consigneeregion").toString()));
	                	continue;
	                case 6:
	                	commonCell.setCellValue((list.get(i).get("distance")==null?"":list.get(i).get("distance").toString()));
	                	continue;
	                case 7:
	                	commonCell.setCellValue((list.get(i).get("settletype")==null?"":list.get(i).get("settletype").toString()));
	                	continue;
	                case 8:
	                	commonCell.setCellValue((list.get(i).get("goodsName")==null?"":list.get(i).get("goodsName").toString()));
	                	continue;
	                case 9:
	                	commonCell.setCellValue(list.get(i).get("factnum")==null?"0.0":list.get(i).get("factnum").toString());
	                	continue;
	                case 10:
	                	commonCell.setCellValue(list.get(i).get("factweight")==null?"0.0":list.get(i).get("factweight").toString());
	                	continue;
	                case 11:
	                	commonCell.setCellValue(list.get(i).get("factvolume")==null?"0.0":list.get(i).get("factvolume").toString());
	                	continue;
	                case 12:
	                	commonCell.setCellValue(list.get(i).get("freight")==null?"0.0":list.get(i).get("freight").toString());
	                	continue;
	                case 13:
	                	commonCell.setCellValue(list.get(i).get("delivery")==null?"0.0":list.get(i).get("delivery").toString());
	                	continue;
	                case 14:
	                	commonCell.setCellValue(list.get(i).get("charge")==null?"0.0":list.get(i).get("charge").toString());
	                	continue;
	                case 15:
	                	commonCell.setCellValue(list.get(i).get("pack")==null?"0.0":list.get(i).get("pack").toString());
	                	continue;
	                case 16:
	                	commonCell.setCellValue(list.get(i).get("other")==null?"0.0":list.get(i).get("other").toString());
	                	continue;
	                case 17:
	                	commonCell.setCellValue(list.get(i).get("total")==null?"0.0":list.get(i).get("total").toString());
	                	continue;
	                case 18:
	                	commonCell.setCellValue("");
	                	continue;
	                }
	        	}
	        }
        }
        rowIndex++;
        HSSFRow footRow = sheet.createRow(rowIndex);
        for (int x = 0; x < headers.length; x++){
        	//页脚
        	HSSFCell footCell = footRow.createCell(x);
        	footCell.setCellStyle(tfStyle);
        	if(x == 0){
        		footCell.setCellValue("制表人：");
        	}else if(x == 10){
        		footCell.setCellValue("审核人：");
        	}
        }
        ServletOutputStream outStream=null;
        try{
        	response.reset();    
            response.setContentType("application/x-msdownload");    
            response.setHeader("Content-Disposition","attachment; filename="+new String(title.getBytes("gb2312"),"ISO-8859-1")+".xls");    
            outStream = response.getOutputStream();
            workbook.write(outStream);
        }catch (IOException e){
            e.printStackTrace();
        }finally{    
            outStream.close();
        }
	}
}
