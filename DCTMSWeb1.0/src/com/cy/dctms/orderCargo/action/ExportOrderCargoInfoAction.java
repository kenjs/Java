package com.cy.dctms.orderCargo.action;

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
import com.cy.dctms.common.domain.OrderCargoInfoDomain;
import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.orderCargo.service.IOrderCargoInfoService;

public class ExportOrderCargoInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	InputStream excelStream;
	private IOrderCargoInfoService orderCargoInfoService;
	private OrderCargoInfoDomain orderCargoInfoDomain;
	
	

	/** 查询货物信息信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query orderCargoInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (orderCargoInfoDomain==null) {
			orderCargoInfoDomain = new OrderCargoInfoDomain();
		}
		orderCargoInfoService.exportOrderCargoInfo(orderCargoInfoDomain);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		putDataOnOutputStream(out,orderCargoInfoDomain.getDataList());
		excelStream = new ByteArrayInputStream(out.toByteArray());
		return "excel";
	}
	
	public void putDataOnOutputStream(OutputStream os,List<OrderCargoInfoDomain> dataList) throws IOException {
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
            
        String[] excelNameArray = {"序号","货物名称","货物类型","重量（货物）","体积（货物）","车型要求（车长）","板-平板、高低板","车型要求（车 栏）","发布运费价格","要求装货时间","要求到货时间","装货地-省","装货地-市","装货地-县","装货地-自定义地址","卸货地-省","卸货地-市","卸货地-县","卸货地-自定义地址","联系人","手机号","固定电话","备注","发布用户ID","修改用户ID","企业ID","货源状态","状态修改时间"};
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
		        	OrderCargoInfoDomain eclDomain = new OrderCargoInfoDomain();
		        	eclDomain = dataList.get(i);
		        	String[] newLabel = new String[len];
					int num = 0;
		        	newLabel[num] = String.valueOf(i + 1);num++;
		        	newLabel[num] = eclDomain.getCargoName();num++;
		        	newLabel[num] = eclDomain.getCargoType();num++;
		        	newLabel[num] = eclDomain.getCargoWeight();num++;
		        	newLabel[num] = eclDomain.getCargoCubage();num++;
		        	newLabel[num] = eclDomain.getRequestCarLength();num++;
		        	newLabel[num] = eclDomain.getRequestCarPlateType();num++;
		        	newLabel[num] = eclDomain.getRequestCarBarType();num++;
		        	newLabel[num] = eclDomain.getFreight();num++;
		        	newLabel[num] = eclDomain.getRequestStartTime();num++;
		        	newLabel[num] = eclDomain.getRequestEndTime();num++;
		        	newLabel[num] = eclDomain.getStartProvince();num++;
		        	newLabel[num] = eclDomain.getStartCity();num++;
		        	newLabel[num] = eclDomain.getStartCounty();num++;
		        	newLabel[num] = eclDomain.getStartTown();num++;
		        	newLabel[num] = eclDomain.getEndProvince();num++;
		        	newLabel[num] = eclDomain.getEndCity();num++;
		        	newLabel[num] = eclDomain.getEndCounty();num++;
		        	newLabel[num] = eclDomain.getEndTown();num++;
		        	newLabel[num] = eclDomain.getContactName();num++;
		        	newLabel[num] = eclDomain.getContactMobilephone();num++;
		        	newLabel[num] = eclDomain.getContactTelephone();num++;
		        	newLabel[num] = eclDomain.getRemark();num++;
		        	newLabel[num] = eclDomain.getDeployUserid();num++;
		        	newLabel[num] = eclDomain.getModifyUserid();num++;
		        	newLabel[num] = eclDomain.getCompanyId();num++;
		        	newLabel[num] = eclDomain.getCargoFlag();num++;
		        	newLabel[num] = eclDomain.getCargoFlagTime();num++;
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


	public void setOrderCargoInfoService(IOrderCargoInfoService orderCargoInfoService) {
		this.orderCargoInfoService = orderCargoInfoService;
	}
	public OrderCargoInfoDomain getOrderCargoInfoDomain() {
		return orderCargoInfoDomain;
	}

	public void setOrderCargoInfoDomain(OrderCargoInfoDomain orderCargoInfoDomain) {
		this.orderCargoInfoDomain = orderCargoInfoDomain;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}


}
