package com.cy.dctms.operationLog.action;

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
import com.cy.dctms.common.domain.OperationLogDomain;
import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.operationLog.service.IOperationLogService;

public class ExportOperationLogAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	InputStream excelStream;
	private IOperationLogService operationLogService;
	private OperationLogDomain operationLogDomain;
	
	

	/** 查询操作日志信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query operationLog list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (operationLogDomain==null) {
			operationLogDomain = new OperationLogDomain();
		}
		operationLogService.exportOperationLog(operationLogDomain);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		putDataOnOutputStream(out,operationLogDomain.getDataList());
		excelStream = new ByteArrayInputStream(out.toByteArray());
		return "excel";
	}
	
	public void putDataOnOutputStream(OutputStream os,List<OperationLogDomain> dataList) throws IOException {
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
            
        String[] excelNameArray = {"序号","操作人ID","操作人账号","操作人姓名","操作时间","备注"};
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
		        	OperationLogDomain eclDomain = new OperationLogDomain();
		        	eclDomain = dataList.get(i);
		        	String[] newLabel = new String[len];
		        	newLabel[0] = String.valueOf(i + 1);
		        	newLabel[1] = eclDomain.getUserDriverId();
		        	newLabel[2] = eclDomain.getUserDriverCode();
		        	newLabel[3] = eclDomain.getUserDriverName();
		        	newLabel[4] = eclDomain.getCreateTime();
		        	newLabel[5] = eclDomain.getRemark();
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


	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	public OperationLogDomain getOperationLogDomain() {
		return operationLogDomain;
	}

	public void setOperationLogDomain(OperationLogDomain operationLogDomain) {
		this.operationLogDomain = operationLogDomain;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}


}
