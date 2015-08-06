package com.cy.swp.common.util;

import com.cy.swp.common.constants.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ExcelWriteUtil {

    public static void main(String[] args) {
        new ExcelWriteUtil().saveExcelFile("d://exceltext.xls", new ArrayList<String>());
    }

    /**
     * 写EXCEL文件，传入文件名、数据源
     *
     * @author hayden
     */
    public void saveExcelFile(String FileName, List<String> list) {
        FileOutputStream fileoutputstream;
        try {
            fileoutputstream = new FileOutputStream(FileName);
            try {
                this.createExcelBook(list).write(fileoutputstream);
            } catch (IOException e) {
            } finally {
                try {
                    if (fileoutputstream != null)
                        fileoutputstream.close();
                } catch (IOException e) {
                }
            }
        } catch (FileNotFoundException e) {
        }
    }

    public void saveExcelFile(HSSFWorkbook hssfworkbook, String FileName) {
        FileOutputStream fileoutputstream;
        try {
            fileoutputstream = new FileOutputStream(FileName);
            try {
                hssfworkbook.write(fileoutputstream);
            } catch (IOException e) {
            } finally {
                try {
                    if (fileoutputstream != null)
                        fileoutputstream.close();
                } catch (IOException e) {
                }
            }
        } catch (FileNotFoundException e) {
        }
    }

    // 创建excel文件对象
    public HSSFWorkbook createExcelBook(List<String> list) {
        HSSFWorkbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("数据");
        //wb.setSheetName(0, "数据");
        Font fontTitle = createFonts(wb, Font.BOLDWEIGHT_BOLD, "宋体", false, (short) 200);
        Font fontContent = createFonts(wb, Font.BOLDWEIGHT_NORMAL, "宋体", false, (short) 200);

        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i);
            String[] strs = list.get(i).split(Constants.CELL_SPLIT_STR);
            for (int j = 0; j < strs.length; j++) {
                if (i == 0) {
                    createCell(wb, row, j, strs[j], fontTitle);
                } else {
                    createCell(wb, row, j, strs[j], fontContent);
                }
                //sheet.autoSizeColumn((short)j); //自动列宽
            }
        }
        if (list.size() > 0) {
            for (int k = 0; k < list.get(0).split(Constants.CELL_SPLIT_STR).length; k++) {
                try {
                    sheet.autoSizeColumn((short) k); //自动列宽
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return wb;
    }

    // 创建excel文件对象
//	public HSSFWorkbook exportExcelForStudentTemp(List studentList) {
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// 创建�?��张表
//		Sheet sheet = wb.createSheet();
//		// 创建第一�?
//		Row row = sheet.createRow(0);
//		// 创建第二�?
//		Row row1 = sheet.createRow(1);
//		// 文件头字�?
//		Font font0 = createFonts(wb, Font.BOLDWEIGHT_BOLD, "宋体", false,
//				(short) 200);
//		Font font1 = createFonts(wb, Font.BOLDWEIGHT_NORMAL, "宋体", false,
//				(short) 200);
//		// 合并第一行的单元�?
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
//		// 设置第一列的文字
//		createCell(wb, row, 0, "总数", font0);
//		// 合并第一行的2列以后到8列（不包含第二列�?
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 8));
//		// 设置第二列的文字
//		createCell(wb, row, 2, "基本信息", font0);
//		// 给第二行添加文本
//		createCell(wb, row1, 0, "序号", font1);
//		createCell(wb, row1, 1, "版本", font1);
//		createCell(wb, row1, 2, "姓名", font1);
//		createCell(wb, row1, 3, "性别", font1);
//		createCell(wb, row1, 4, "年龄", font1);
//		createCell(wb, row1, 5, "年级", font1);
//		createCell(wb, row1, 6, "学校", font1);
//		createCell(wb, row1, 7, "父母名称", font1);
//		createCell(wb, row1, 8, "籍贯", font1);
//		createCell(wb, row1, 9, "联系方式", font1);
//		// 第三行表�?
//		int l = 2;
//		// 这里将学员的信心存入到表格中
//		for (int i = 0; i < studentList.size(); i++) {
//			// 创建�?��
//			Row rowData = sheet.createRow(l++);
//			Student stu = studentList.get(i);
//			createCell(wb, rowData, 0, String.valueOf(i + 1), font1);
//			createCell(wb, rowData, 1, "3.0", font1);
//			createCell(wb, rowData, 2, stu.getName(), font1);
//			createCell(wb, rowData, 3, stu.getStudentsex(), font1);
//			createCell(wb, rowData, 4, stu.getStudentage(), font1);
//			createCell(wb, rowData, 5, stu.getGrade().getName(), font1);
//			createCell(wb, rowData, 6, stu.getStudentschool(), font1);
//			createCell(wb, rowData, 7, stu.getparents(), font1);
//			createCell( wb, rowData, 8, stu.getStudentprovince() + stu.getStudentcity()
//							+ stu.getStudentarea(), font1);
//			createCell(wb, rowData, 9, stu.getContact(), font1);
//
//		}
//		return wb;
//	}

    /**
     * 创建单元格并设置样式,�?
     */
    public void createCell(Workbook wb, Row row, int column, String value, Font font) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        if (StringUtils.isNotBlank(value)) {
            try {
                cell.setCellValue(Double.parseDouble(value));
            } catch (NumberFormatException e) {
            }
        }
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); //粗边�?
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边�?
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边�?
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边�?
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边�?
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 设置字体
     */
    public Font createFonts(Workbook wb, short bold, String fontName,
                            boolean isItalic, short hight) {
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setBoldweight(bold);
        font.setItalic(isItalic);
        font.setFontHeight(hight);
        return font;
    }

    /**
     * 判断是否为数�?
     */
    public boolean isNumeric(String str) {
        if (str == null || "".equals(str.trim()) || str.length() > 10)
            return false;
        Pattern pattern = Pattern.compile("^0|[1-9]\\d*(\\.\\d+)?$");
        return pattern.matcher(str).matches();
    }

    // public static void main(String[] args) throws IOException {
    //
    // // 工作�?
    // HSSFWorkbook hssfworkbook = new HSSFWorkbook();
    // // 创建sheet�?
    // HSSFSheet hssfsheet = hssfworkbook.createSheet();
    // // sheet名称乱码处理
    // hssfworkbook.setSheetName(0, "研发部门", HSSFWorkbook.ENCODING_UTF_16);
    // // 取得第一�?
    // HSSFRow hssfrow = hssfsheet.createRow(0);
    // // 创建第一个单元格 并处理乱�?
    // HSSFCell hssfcell_0 = hssfrow.createCell((short) 0);
    // hssfcell_0.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
    // // 对第�?��单元格赋�?
    // hssfcell_0.setCellValue("研发工程�?");
    //
    // // 日期单元格格式处�?
    // HSSFCellStyle hssfcellstyle = hssfworkbook.createCellStyle();
    // // m/d/yy h:mm
    // hssfcellstyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
    // // 创建第二个单元格
    // HSSFCell hssfcell_1 = hssfrow.createCell((short) 1);
    // hssfcell_1.setCellValue(new Date());
    // hssfcell_1.setCellStyle(hssfcellstyle);
    // hssfrow.createCell((short) 2).setCellValue(true);
    // hssfrow.createCell((short) 3).setCellValue(122.00);
    // // 输出
    // FileOutputStream fileoutputstream = new FileOutputStream(
    // "d://exceltext.xls");
    // hssfworkbook.write(fileoutputstream);
    // fileoutputstream.close();
    // }
}
