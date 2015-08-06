package com.cy.dctms.userDirverAssess.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.UserDriverAssessInfoDomain;
import com.cy.dctms.userDirverAssess.service.IUserDriverAssessInfoService;

public class ExportUserDriverAssessInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	InputStream excelStream;
	private IUserDriverAssessInfoService userDriverAssessInfoService;
	private UserDriverAssessInfoDomain userDriverAssessInfoDomain;
	
	

	/** 查询企业对司机评价信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query userDriverAssessInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (userDriverAssessInfoDomain==null) {
			userDriverAssessInfoDomain = new UserDriverAssessInfoDomain();
		}
		userDriverAssessInfoService.exportUserDriverAssessInfo(userDriverAssessInfoDomain);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		putDataOnOutputStream(out,userDriverAssessInfoDomain.getDataList());
		excelStream = new ByteArrayInputStream(out.toByteArray());
		return "excel";
	}
	
	public void putDataOnOutputStream(OutputStream os,List<UserDriverAssessInfoDomain> dataList) throws IOException {
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
            
        String[] excelNameArray = {"序号","交易总评价","货物ID","货源名称","司机ID","司机账号","司机姓名","评价人Id","评价人账号","评价人姓名","交易订单Id","订单号","到达速度（评分）","司机服务态度（评分）","评语","评价时间"};
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
		        	UserDriverAssessInfoDomain eclDomain = new UserDriverAssessInfoDomain();
		        	eclDomain = dataList.get(i);
		        	String[] newLabel = new String[len];
		        	int num = 0;
		        	 newLabel[num] = String.valueOf(i + 1);num++;
		        	 newLabel[num] = eclDomain.getTradeEvaluateScore();num++;
		        	 newLabel[num] = eclDomain.getCargoId();num++;
		        	 newLabel[num] = eclDomain.getCargoName();num++;
		        	 newLabel[num] = eclDomain.getDriverId();num++;
		        	 newLabel[num] = eclDomain.getDriverCode();num++;
		        	 newLabel[num] = eclDomain.getDriverName();num++;
		        	 newLabel[num] = eclDomain.getUserId();num++;
		        	 newLabel[num] = eclDomain.getUserCode();num++;
		        	 newLabel[num] = eclDomain.getUserName();num++;
		        	 newLabel[num] = eclDomain.getTransactionId();num++;
		        	 newLabel[num] = eclDomain.getOrderNumber();num++;
		        	 newLabel[num] = eclDomain.getArriverEvaluateScore();num++;
		        	 newLabel[num] = eclDomain.getServeEvaluateScore();num++;
		        	 newLabel[num] = eclDomain.getAssess();num++;
		        	 newLabel[num] = eclDomain.getCreateTime();
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


	public void setUserDriverAssessInfoService(IUserDriverAssessInfoService userDriverAssessInfoService) {
		this.userDriverAssessInfoService = userDriverAssessInfoService;
	}
	public UserDriverAssessInfoDomain getUserDriverAssessInfoDomain() {
		return userDriverAssessInfoDomain;
	}

	public void setUserDriverAssessInfoDomain(UserDriverAssessInfoDomain userDriverAssessInfoDomain) {
		this.userDriverAssessInfoDomain = userDriverAssessInfoDomain;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}


}
