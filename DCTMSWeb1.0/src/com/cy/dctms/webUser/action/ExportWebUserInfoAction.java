package com.cy.dctms.webUser.action;

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
import com.cy.dctms.common.domain.WebUserInfoDomain;
import com.cy.dctms.webUser.service.IWebUserInfoService;

public class ExportWebUserInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	InputStream excelStream;
	private IWebUserInfoService webUserInfoService;
	private WebUserInfoDomain webUserInfoDomain;
	
	

	/** ��ѯ��ҵ��Ϣ�б�
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query webUserInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (webUserInfoDomain==null) {
			webUserInfoDomain = new WebUserInfoDomain();
		}
		webUserInfoService.queryWebUserInfoTransactionList(webUserInfoDomain);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		putDataOnOutputStream(out,webUserInfoDomain.getDataList());
		excelStream = new ByteArrayInputStream(out.toByteArray());
		return "excel";
	}
	
	public void putDataOnOutputStream(OutputStream os,List<WebUserInfoDomain> dataList) throws IOException {
		WritableWorkbook workbook = null;
		try {
			workbook = Workbook.createWorkbook(os);
			WritableSheet ws = workbook.createSheet("Sheet1", 3);
			
			//���õ�Ԫ������ָ�ʽ
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
            
       // String[] excelNameArray = {"���","��¼����","�û�����","��˾����","�ֻ���","����ʱ��","QQ����","��������","���֤����","�û�״̬","����¼IP��ַ","����¼ʱ��","��ҵ��֤��ʶ","��ҵ��֤ʱ��","�ɷ���֤��ʶ","�ɷ���֤ʱ��","������֤��ʶ","������֤ʱ��","�����û�","�û���Դ"};
            String[] excelNameArray = {"���","��ҵ����","�ʺ�","��ϵ�绰","�ʺ�����","ע������","��Դ��","������","������","�ɽ���","�ɽ���"};  
            int len = excelNameArray.length;
            //������ͷ��
	        for (int j=0;j < len;j++){
	        	if(j != 0){
	        		ws.addCell(new Label(j, 0, excelNameArray[j], wcf));
	        	}else{
	        		ws.addCell(new Label(j, 0, excelNameArray[j], wcfBlack));
	        	}
	        }
	        
	        //��������
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
		        	WebUserInfoDomain eclDomain = new WebUserInfoDomain();
		        	eclDomain = dataList.get(i);
		        	String[] newLabel = new String[len];
		        	newLabel[0] = String.valueOf(i + 1);
		        	newLabel[1] = eclDomain.getCompanyName();
		        	newLabel[2] = eclDomain.getCode();
		        	newLabel[3] = eclDomain.getMobilephone();
		        	newLabel[4] = eclDomain.getUserType();
		        	newLabel[5] = eclDomain.getCreateTime();
		        	newLabel[6] = eclDomain.getAccumulateCargoCount();
		        	newLabel[7] = eclDomain.getOrderCargoCount();
		        	newLabel[8] = eclDomain.getOrderCargoRate();
		        	newLabel[9] = eclDomain.getAccumulateTransactionCount();
		        	newLabel[10] = eclDomain.getAccumulateTransactionRate();
		   
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


	public void setWebUserInfoService(IWebUserInfoService webUserInfoService) {
		this.webUserInfoService = webUserInfoService;
	}
	public WebUserInfoDomain getWebUserInfoDomain() {
		return webUserInfoDomain;
	}

	public void setWebUserInfoDomain(WebUserInfoDomain webUserInfoDomain) {
		this.webUserInfoDomain = webUserInfoDomain;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}


}
