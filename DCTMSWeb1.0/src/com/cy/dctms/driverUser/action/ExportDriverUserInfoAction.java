package com.cy.dctms.driverUser.action;

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
import com.cy.dctms.common.domain.DriverUserInfoDomain;
import com.cy.dctms.driverUser.service.IDriverUserInfoService;

public class ExportDriverUserInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	InputStream excelStream;
	private IDriverUserInfoService driverUserInfoService;
	private DriverUserInfoDomain driverUserInfoDomain;
	
	 
	/** 查询司机信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query driverUserInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (driverUserInfoDomain==null) {
			driverUserInfoDomain = new DriverUserInfoDomain();
		}
		driverUserInfoService.driverUserTotalDataList(driverUserInfoDomain);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		putDataOnOutputStream(out,driverUserInfoDomain.getDataList());
		excelStream = new ByteArrayInputStream(out.toByteArray());
		return "excel";
	}
	
	public void putDataOnOutputStream(OutputStream os,List<DriverUserInfoDomain> dataList) throws IOException {
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
            ws.setColumnView(0, 6);
            ws.setColumnView(1, 10);
            ws.setColumnView(2, 10);
            ws.setColumnView(3, 12);
            ws.setColumnView(4, 12);
            ws.setColumnView(5, 10);
            ws.setColumnView(6, 10);
        //String[] excelNameArray = {"序号","登录帐号","司机姓名","车牌照","联系电话","身份证","车长","吨位","体积","板","栏","备注","运营状态","新老用户","百度云推送channelId","百度云推送userId"};
		String[] excelNameArray = {"序号","司机姓名","车牌照","手机号码","安装日期",
				"找货数","报价数","订车数","订车率","订单量","订单率","电话数"};  
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
		        	DriverUserInfoDomain eclDomain = new DriverUserInfoDomain();
		        	eclDomain = dataList.get(i);
		        	String[] newLabel = new String[len];
		        	newLabel[0] = String.valueOf(i + 1);
					newLabel[1] = eclDomain.getName();
		        	newLabel[2] = eclDomain.getCarNumber();
		        	newLabel[3] = eclDomain.getCode();
		        	newLabel[4] = eclDomain.getCreateTime();
		        	newLabel[5] = eclDomain.getGoodFindNum();
		        	newLabel[6] = eclDomain.getQuoteCount();
		        	newLabel[7] = eclDomain.getOrderingCount();
		        	newLabel[8] = eclDomain.getOrderingRate();
		        	newLabel[9] = eclDomain.getPassOrderCount();
		        	newLabel[10] = eclDomain.getPassOrderRate();
		        	newLabel[11] = eclDomain.getPhoneCallNum();
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


	public void setDriverUserInfoService(IDriverUserInfoService driverUserInfoService) {
		this.driverUserInfoService = driverUserInfoService;
	}
	public DriverUserInfoDomain getDriverUserInfoDomain() {
		return driverUserInfoDomain;
	}

	public void setDriverUserInfoDomain(DriverUserInfoDomain driverUserInfoDomain) {
		this.driverUserInfoDomain = driverUserInfoDomain;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}


}
