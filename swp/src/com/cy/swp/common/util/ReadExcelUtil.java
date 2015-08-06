package com.cy.swp.common.util;


import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取excel文件工具（支持xls,xlsx的格式）
 * Created by wyh on 2014/12/4.
 */
public class ReadExcelUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private boolean isTitle = true;//excel是否有列表头（默认true，有列表头）

    //无参构造函数
    public ReadExcelUtil(){}

    //有参构造函数
    public ReadExcelUtil(boolean isTitle){
        this.isTitle = isTitle;
    }

    /**
     * 读取Excel文件（支持xls,xlsx的格式）
     * @param inputStream 文件流
     * @param filedsList 字段名称list（list里面存放的是fileds数组例：["name","mphone"]）
     * @param sheetNums 工作表的编号数组
     * @return Map<Integer, List<Map<String, String>>> Integer存的是工作表的编号数
     * @throws IOException
     * @throws InvalidFormatException
     */
    public Map<Integer, List<Map<String, String>>> readExcelFile(InputStream inputStream, List<String[]> filedsList, int[] sheetNums) throws IOException, InvalidFormatException {
        try {
            //创建读取excel的Workbook对象
//            Workbook book = createWorkBook(inputStream);
            Workbook book = WorkbookFactory.create(inputStream);
            //需要返回的map
            Map<Integer, List<Map<String, String>>> rtMap = new HashMap<Integer, List<Map<String, String>>>();
            List<Map<String, String>> list = null;
            Sheet sheet = null;
            int rows = 0;//excel有效的行数
            for (int i = 0; i < sheetNums.length; i++) {
                sheet = book.getSheetAt(sheetNums[i]);//获得工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
                list = readExcelContent(sheet, sheetNums[i], filedsList.get(i));//读取Excel数据内容
                rtMap.put(sheetNums[i], list);
            }
            return rtMap;
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
        }
    }

    /** 读取Excel数据内容 */
    private List<Map<String, String>> readExcelContent(Sheet sheet, int sheetNum, String[] fileds){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        int rows = sheet.getPhysicalNumberOfRows();
        int start = 0;
        if(isTitle){
            if(rows < 2){
                throw new RuntimeException("excel的Sheet"+(sheetNum+1)+"中的数据为空");
            }
            Row oneRow = sheet.getRow(0);//第一行（列表头）
            int cells = oneRow.getPhysicalNumberOfCells();//第一行的有效数
            if(fileds.length != cells){
                logger.info("ReadExcelUtil中的私有方法readExcelContent判断第一列的列表头的title列表数与String[] fileds的长度不相等");
                throw new RuntimeException("excel的Sheet"+(sheetNum+1)+"中的第一列列表头的title列表数与String[] fileds的长度不相等");
            }
            start = 1;
        }else{
            if(rows < 1){
                throw new RuntimeException("excel的Sheet"+(sheetNum+1)+"中的数据为空");
            }
            start = 0;
        }
        Row row = null;
        Cell cell = null;
        Map<String, String> map = null;
        for (int i = start;i < rows;i++) {
            row = sheet.getRow(i);
            int j = 0;
            map = new HashMap<String, String>();
            while (j < fileds.length) {
                cell = row.getCell(j);//获得单元格对象
                map.put(fileds[j], getCellFormatValue(cell));//往map里放入单元格的String数据
                j++;
            }
            list.add(map);
        }
        return list;
    }

    /** 获取单元格数据内容为字符串类型的数据 */
    private String getCellFormatValue(Cell cell){
        if(cell == null){
            return "";
        }
        String strCell = "";
        cell.setCellType(Cell.CELL_TYPE_STRING);
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        //去两边的空格
        return strCell.trim();
    }

    /** 创建读取excel的Workbook对象 */
    private Workbook createWorkBook(InputStream inputStream) throws IOException, InvalidFormatException {
        if (!inputStream.markSupported()) {
            inputStream = new PushbackInputStream(inputStream, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(inputStream)) {
            //解析2003的excel（xls）格式的
            return new HSSFWorkbook(inputStream);
        }
        if (POIXMLDocument.hasOOXMLHeader(inputStream)) {
            //解析2007的excel(xlsx)格式的
            return new XSSFWorkbook(OPCPackage.open(inputStream));
        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");
    }
}
