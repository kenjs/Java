package com.cy.dcts.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.constants.Constants;

public class ExcelReaderUtil {
Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

    /**
     * 读取Excel表格表头的内容
     * @param InputStream
     * @return String 表头内容的数组
     */
    public String readExcelTitle(InputStream is) {
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
           logger.warn("readExcelTitle error.", e);
           return "";
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        String str = "";
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
       // System.out.println("colNum:" + colNum);
        for (int i = 0; i < colNum; i++) {
            //title[i] = getStringCellValue(row.getCell((short) i));
            str = str + getCellFormatValue(row.getCell((short) i)) + Constants.CELL_SPLIT_STR;
        }
        return str;
    }

    /**
     * 读取Excel数据内容
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, String> readExcelContent(InputStream is) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            wb = new HSSFWorkbook(new POIFSFileSystem(is));
        } catch (IOException e) {
        	//logger.debug("", e);
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                str += getCellFormatValue(row.getCell((short) j)).trim() + Constants.CELL_SPLIT_STR;
                j++;
            }
            content.put(i, str);
            str = "";
        }
        return content;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
    	if (cell == null) {
            return "";
        }
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     * 
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
//    private String getDateCellValue(HSSFCell cell) {
//        String result = "";
//        try {
//            int cellType = cell.getCellType();
//            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
//                Date date = cell.getDateCellValue();
//                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
//                        + "-" + date.getDate();
//            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
//                String date = getStringCellValue(cell);
//                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
//            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
//                result = "";
//            }
//        } catch (Exception e) {
//            System.out.println("日期格式不正确!");
//            e.printStackTrace();
//        }
//        return result;
//    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {// 判断当前Cell的Type
			case HSSFCell.CELL_TYPE_STRING: {// 如果当前Cell的Type为STRING
				cellvalue = cell.getRichStringCellValue().getString();// 取得当前的Cell字符串
				break;
			}
			case HSSFCell.CELL_TYPE_FORMULA: {// Excel里面的“公式”，可以用cell.getNumericCellValue()来获得“结果”，
				if (HSSFDateUtil.isCellDateFormatted(cell)) {// 判断当前的cell是否为Date
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMATE_LONG);
					cellvalue = sdf.format(date);
					break;
				 }// else {
					// cellvalue = String.valueOf(cell.getNumericCellValue());// 取得当前Cell的数值，默认保留一位小数
				//}
			}
			case HSSFCell.CELL_TYPE_NUMERIC: {// 如果当前Cell的Type为NUMERIC
				if (HSSFDateUtil.isCellDateFormatted(cell)) {// 判断当前的cell是否为Date
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMATE_LONG);
					cellvalue = sdf.format(date);
					break;
				 }
				double d = cell.getNumericCellValue();
				if (d - (int) d < Double.MIN_VALUE) { // 是否为int型
					cellvalue = Integer.toString((int) d);
				} else { // 是否为double型
					cellvalue = Double.toString(cell.getNumericCellValue());
				}
				break;
			}
			default:// 默认的Cell值
				cellvalue = " ";
			}
		}
        return cellvalue;

    }
    
    //读取EXCEL文件
	public Map<Integer, String> readExcelFile(String fileAbsPath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileAbsPath);
			// 读取Excel表格表头
			
		    String title = readExcelTitle(inputStream);
		    logger.debug("获得Excel表格的标题=[{}]", title);

		    // 读取Excel表格内容
		    Map<Integer, String> map = readExcelContent(inputStream);
		    map.put(0, title);
		    
		    String emptyStr = "";
		    for(String str :title.split(Constants.CELL_SPLIT_STR)){
		    	emptyStr += Constants.CELL_SPLIT_STR;
		    }
		    logger.debug("获得Excel表格的内容:");
		    for (int i = 1; i <= map.size(); i++) {
		        if(emptyStr.equals(map.get(i))){
		        	logger.debug("成功获得Excel表格的列数 =[{}], 行数=[{}]", title.split(Constants.CELL_SPLIT_STR).length, --i);
		        	break;
		        }
		        logger.debug("i=" + i + " \t" + map.get(i));
		    }
		    return map;
		} catch (FileNotFoundException e) {
			logger.error("reader import order cargo file fail, message:", e);
			return null;
		} finally {
			if(inputStream !=null){
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

    public static void main(String[] args) {
      new ExcelReaderUtil().readExcelFile("D:\\template22.xls");
      new ExcelReaderUtil().readExcelFile("E:\\tomcat-6.0.37-8888\\temp\\20131120.xls");
    }
}
