package com.cy.dctms.driverSurviveRate.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WritableFont;
import jxl.write.WriteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.DriverSurviveRateDomain;
import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.driverSurviveRate.service.IDriverSurviveRateService;

public class ExportDriverSurviveRateAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	InputStream excelStream;
	private IDriverSurviveRateService driverSurviveRateService;
	private DriverSurviveRateDomain driverSurviveRateDomain;
	
	

	/** 查询查询存活率信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query driverSurviveRate list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (driverSurviveRateDomain==null) {
			driverSurviveRateDomain = new DriverSurviveRateDomain();
		}
		driverSurviveRateService.queryDriverSurviveRateList(driverSurviveRateDomain);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		putDataOnOutputStream(out,driverSurviveRateDomain.getDataList());
		excelStream = new ByteArrayInputStream(out.toByteArray());
		return "excel";
	}
	
	public void putDataOnOutputStream(OutputStream os,List<DriverSurviveRateDomain> dataList) throws IOException {
		WritableWorkbook workbook = null;
		try {
			workbook = Workbook.createWorkbook(os);
			WritableSheet ws = workbook.createSheet("Sheet1", 3);
			
			//设置单元格的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,
                     						   UnderlineStyle.NO_UNDERLINE,Colour.RED);
            WritableFont wfColor = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,
					   						   UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setBorder(Border.ALL, BorderLineStyle.THIN);

            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf.setAlignment(Alignment.CENTRE); 
            WritableCellFormat wcfBlack = new WritableCellFormat(wfColor);
            wcfBlack.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcfBlack.setAlignment(Alignment.CENTRE); 
            wcfBlack.setBorder(Border.ALL, BorderLineStyle.THIN);
            
            ws.setRowView(0, 500);
//            ws.setColumnView(0, 6);
//            ws.setColumnView(1, 8);
//            ws.setColumnView(2, 20);
//            ws.setColumnView(3, 15);
//            ws.setColumnView(4, 6);
//            ws.setColumnView(5, 10);
//            ws.setColumnView(6, 10);
//            ws.setColumnView(7, 12);
            
        String[] excelNameArray = {"序号","日期","新注册数量","前注册数量","存活量","存活率","活跃量","活跃率","找货次数","预约条数","电话拨打数量"};
            int len = excelNameArray.length;
            //设置列头名
	        for (int j=0;j < len;j++){
	        	if(j != 0){
	        		ws.addCell(new Label(j, 0, excelNameArray[j], wcf));
	        	}else{
	        		ws.addCell(new Label(j, 0, excelNameArray[j], wcfBlack));
	        	}
	        }
	        
	        //设置内容
	        wcf = new WritableCellFormat();
	        wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
	        WritableCellFormat wcfCenter = new WritableCellFormat();
	        wcfCenter.setAlignment(Alignment.CENTRE);
	        wcfCenter.setBorder(Border.ALL, BorderLineStyle.THIN);
	        WritableCellFormat wcfRight = new WritableCellFormat();
	        wcfRight.setAlignment(Alignment.RIGHT);
	        wcfRight.setBorder(Border.ALL, BorderLineStyle.THIN);
	        for (int i = 0; i < dataList.size(); i++){
		        for (int j=0;j < len;j++){
		        	DriverSurviveRateDomain eclDomain = new DriverSurviveRateDomain();
		        	eclDomain = dataList.get(i);
		        	String[] newLabel = new String[len];
					int num = 0;
		        	newLabel[num] = String.valueOf(i + 1);num++;
		        	newLabel[num] = eclDomain.getQueryTime();num++;
		        	newLabel[num] = eclDomain.getRegisterCount();num++;
		        	newLabel[num] = eclDomain.getForeRegisterCount();num++;
		        	newLabel[num] = eclDomain.getSurviveCount();num++;
		        	newLabel[num] = eclDomain.getSurviveRate();num++;
		        	newLabel[num] = eclDomain.getActiveCount();num++;
		        	newLabel[num] = eclDomain.getActiveRate();num++;
		        	newLabel[num] = eclDomain.getGoodFindNum();num++;
		        	newLabel[num] = eclDomain.getAppointNum();num++;
		        	newLabel[num] = eclDomain.getPhoneCallNum();num++;
		        	if(j != 1 && j != 2 && j != 3){
		        		ws.addCell(new Label(j, i+1,newLabel[j], wcfCenter));
		        	}else{
		        		ws.addCell(new Label(j, i+1,newLabel[j], wcf));
		        	}
		        } 
	        }	
	        workbook.write();
        }catch (Exception e) {
        	throw new RuntimeException(e);
		}finally{
			try {
				workbook.close();
			} catch (WriteException e) {
				throw new RuntimeException(e);
			}
		}
	}


	public void setDriverSurviveRateService(IDriverSurviveRateService driverSurviveRateService) {
		this.driverSurviveRateService = driverSurviveRateService;
	}
	public DriverSurviveRateDomain getDriverSurviveRateDomain() {
		return driverSurviveRateDomain;
	}

	public void setDriverSurviveRateDomain(DriverSurviveRateDomain driverSurviveRateDomain) {
		this.driverSurviveRateDomain = driverSurviveRateDomain;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}


}
