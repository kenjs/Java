package com.cy.dctms.service.impl;

import com.cy.dctms.common.bo.TransactionInfo;
import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.dao.TransactionInfoDao;
import com.cy.dctms.service.TransactionInfoService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2014/10/28.
 */
@Service("transactionInfoService")
public class TransactionInfoServiceImpl implements TransactionInfoService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String exportXlsPath;

    @Resource
    private TransactionInfoDao transactionInfoDao;

    @Override
    public void exportExcel() throws Exception {
        String filePath = exportXlsPath + "畅宇导入订单" + DateUtils.getYyyyMmDdH() + ".xls";

        String start = DateUtils.getBeforeDay(10);
        String end = DateUtils.getCurrentDate();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startTime",start);
        map.put("endTime",end);
        map.put("companyId",2);
        List<TransactionInfo> dataList = transactionInfoDao.queryExportTransactionInfo(map);
        File file = new File(exportXlsPath);
        if (! file.exists()) {
            file.mkdirs();
        }
        OutputStream os = new FileOutputStream(new File(filePath));
        export2Excel("畅宇" + start + "~" + end + "导入订单", os, dataList);
    }

    private void export2Excel(String title,OutputStream os, List<TransactionInfo> dataList) {
        String[]  headers = {"订单日期","订单号码","货物名称","发货方","订单状态","司机姓名","司机号码",
                "车牌号","确认卸货","发货单上传","回单上传","发货时间","到货时间","收货时间"};
        //创建一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生产一个工作表
        HSSFSheet sheet = workbook.createSheet(title);
        //设置列默认宽度
        sheet.setDefaultColumnWidth(22);
        //设置表头样式
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置表头字体
        HSSFFont headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short) 12);
        headFont.setColor(HSSFColor.VIOLET.index);
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //把表头字体应用到表头样式
        headStyle.setFont(headFont);
        //生成主体样式
        HSSFCellStyle commonStyle = workbook.createCellStyle();
        commonStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        commonStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        commonStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        commonStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        commonStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        commonStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        commonStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        commonStyle.setLocked(false);
        HSSFFont commonFont = workbook.createFont();
        commonFont.setColor(HSSFColor.BLACK.index);
        commonFont.setFontHeightInPoints((short) 12);
        commonFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        commonStyle.setFont(commonFont);
        //设置表格标题
        HSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell headCell = headRow.createCell(i);
            headCell.setCellStyle(headStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            headCell.setCellValue(text);
        }

        if (dataList.size() > 0) {
            //生产主体表格的数据
            HSSFRow hssfRow;
            for (int i = 0; i < dataList.size(); i++) {
                TransactionInfo info = dataList.get(i);

                hssfRow = sheet.createRow(i + 1);
                //订单日期单元格
                HSSFCell transactionDate = hssfRow.createCell(0);
                transactionDate.setCellStyle(commonStyle);
                transactionDate.setCellValue(info.getTransactionDate());
                //订单号码
                HSSFCell orderNumber = hssfRow.createCell(1);
                orderNumber.setCellStyle(commonStyle);
                orderNumber.setCellValue(info.getOrderNumber());
                //货物名称
                HSSFCell cargoName = hssfRow.createCell(2);
                cargoName.setCellStyle(commonStyle);
                cargoName.setCellValue(info.getCargoName());
                //发货方
                HSSFCell shipper = hssfRow.createCell(3);
                shipper.setCellStyle(commonStyle);
                shipper.setCellValue(info.getShipper());
                //订单状态
                HSSFCell orderStartStr = hssfRow.createCell(4);
                orderStartStr.setCellStyle(commonStyle);
                orderStartStr.setCellValue(info.getOrderStartStr());
                //司机姓名
                HSSFCell driverName = hssfRow.createCell(5);
                driverName.setCellStyle(commonStyle);
                driverName.setCellValue(info.getDriverName());
                //司机号码
                HSSFCell driverTelephone = hssfRow.createCell(6);
                driverTelephone.setCellStyle(commonStyle);
                driverTelephone.setCellValue(info.getDriverTelephone());
                //车牌号
                HSSFCell carNum = hssfRow.createCell(7);
                carNum.setCellStyle(commonStyle);
                carNum.setCellValue(info.getCarNumber());
                //确认卸货
                HSSFCell isReceiving = hssfRow.createCell(8);
                isReceiving.setCellStyle(commonStyle);
                isReceiving.setCellValue(info.isReceiving() ? "是" : "否");
                //发货单上传
                HSSFCell isInvoiceUpload = hssfRow.createCell(9);
                isInvoiceUpload.setCellStyle(commonStyle);
                isInvoiceUpload.setCellValue(info.isInvoiceUpload() ? "是" : "否");
                //回单上传
                HSSFCell isReceiptUpload = hssfRow.createCell(10);
                isReceiptUpload.setCellStyle(commonStyle);
                isReceiptUpload.setCellValue(info.isReceiptUpload() ? "是" : "否");
                //发货时间
                HSSFCell deliveryTime = hssfRow.createCell(11);
                deliveryTime.setCellStyle(commonStyle);
                deliveryTime.setCellValue(info.getDeliveryTime());
                //到货时间
                HSSFCell arrivalTime = hssfRow.createCell(12);
                arrivalTime.setCellStyle(commonStyle);
                arrivalTime.setCellValue(info.getArrivalTime());
                //收货时间
                HSSFCell receiveTime = hssfRow.createCell(13);
                receiveTime.setCellStyle(commonStyle);
                receiveTime.setCellValue(info.getReceiveTime());
            }
        }
        try {
            workbook.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getExportXlsPath() {
        return exportXlsPath;
    }

    @Value("#{propertiesReader['export.xls.path']}")
    public void setExportXlsPath(String exportXlsPath) {
        this.exportXlsPath = exportXlsPath;
    }
}
