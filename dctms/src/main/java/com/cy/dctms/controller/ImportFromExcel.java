package com.cy.dctms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cy.dctms.constants.Constants;
import com.cy.dctms.entity.CompanyInfo;
import com.cy.dctms.entity.DictInfo;
import com.cy.dctms.entity.OrderCargoInfo;
import com.cy.dctms.entity.OrderCargoInfoDomain;
import com.cy.dctms.entity.WebUserInfo;
import com.cy.dctms.model.CompanyInfoModel;
import com.cy.dctms.model.DriverUserModel;
import com.cy.dctms.model.OrderCargoModel;
import com.cy.dctms.model.WebUserModel;
import com.cy.dctms.util.ConvertOrderCargoInfoUtil;
import com.cy.dctms.util.DataArea;
import com.cy.dctms.util.DataDictUtil;
import com.cy.dctms.util.DateUtil;
import com.cy.dctms.util.ExcelReaderUtil;
import com.cy.dctms.util.ReadPropertiesFile;
import com.cy.dctms.util.ValidateUtil;

@Controller
public class ImportFromExcel {

	@Autowired
	private OrderCargoModel orderCargoModel;
	@Autowired
	private CompanyInfoModel companyInfoModel;
	@Autowired
	private DriverUserModel driverUserModel;
	@Autowired
	private WebUserModel webUserModel;
		
	@RequestMapping(value="/importExcel",method=RequestMethod.POST)
	@ResponseBody
	public String importExcel(@RequestParam MultipartFile uploadFile) throws Exception{		
		String msg = "";		
		try {
			String realPath = ReadPropertiesFile.getString("config", "uploadFilePath");
			String fileName = uploadFile.getOriginalFilename();
			FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), new File(realPath, fileName));  
			//String name = saveFile(this.uploadFile, "xls", this.upLoadFilePath);
			Map<Integer, String> map = new ExcelReaderUtil().readExcelFile(realPath + fileName);
			String titleStr = "装货地省" 
					+ Constants.CELL_SPLIT_STR + "装货地市"
					+ Constants.CELL_SPLIT_STR + "装货地区"
					+ Constants.CELL_SPLIT_STR + "卸货地省"
					+ Constants.CELL_SPLIT_STR + "卸货地市"
					+ Constants.CELL_SPLIT_STR + "卸货地区"
					+ Constants.CELL_SPLIT_STR + "车长"
					+ Constants.CELL_SPLIT_STR + "车板"
					+ Constants.CELL_SPLIT_STR + "车栏"
					+ Constants.CELL_SPLIT_STR + "货物类型"
					+ Constants.CELL_SPLIT_STR + "货物名称"
					+ Constants.CELL_SPLIT_STR + "货物重量（吨）"
					+ Constants.CELL_SPLIT_STR + "货物体积（方）"
					+ Constants.CELL_SPLIT_STR + "联系人"
					+ Constants.CELL_SPLIT_STR + "手机号码"
					+ Constants.CELL_SPLIT_STR + "联系电话"
					+ Constants.CELL_SPLIT_STR + "装货时间"
					+ Constants.CELL_SPLIT_STR + "卸货时间"
					+ Constants.CELL_SPLIT_STR + "公司名称"
					+ Constants.CELL_SPLIT_STR;

			if (!((String)map.get(Integer.valueOf(0))).equals(titleStr)) {
				msg = "标题有误, 请重新下载模板！";
				return "{\"msg\": \"" + msg +"\"}";
			}
			
			String emptyStr = "";
			for (String str : titleStr.split(Constants.CELL_SPLIT_STR)) {
				emptyStr = emptyStr + Constants.CELL_SPLIT_STR;
			}
			List<OrderCargoInfo> list = new ArrayList<OrderCargoInfo>();
			for (int i = 1; (i <= map.size() - 1) && (!((String)map.get(Integer.valueOf(i))).equals(emptyStr)); i++){			
				String str = (String)map.get(Integer.valueOf(i));
				if (str.endsWith(Constants.CELL_SPLIT_STR)) {
					str = str + " ";
				}
				list.add(ConvertOrderCargoInfoUtil.getOrderCargoInfoFromDomain(getOrderCargoByStr(str.split(Constants.CELL_SPLIT_STR), i + 1)));
			}
			orderCargoModel.addCargo(list);
			msg = "成功导入"+ list.size() +"条数据!";
		} catch (Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return "{\"msg\": \"" + msg +"\"}";
	}
	
	@RequestMapping(value="/downloadExcel",method=RequestMethod.GET)
	public String download(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");  
		request.setCharacterEncoding("UTF-8");  
		BufferedInputStream bis = null;  
		BufferedOutputStream bos = null;  
		
		String downloadPath = ReadPropertiesFile.getString("config", "downloadPath");
		String downloadFileName = ReadPropertiesFile.getString("config", "downloadFileName");
			
		String ctxPath = request.getSession().getServletContext()  
			        .getRealPath(downloadPath + downloadFileName);  
			
		long fileLength = new File(ctxPath).length();  
  
		response.setContentType("application/octet-stream");  
		response.setHeader("Content-disposition", "attachment; filename="  
		        + new String(downloadFileName.getBytes("utf-8"), "ISO8859-1"));  
		response.setHeader("Content-Length", String.valueOf(fileLength));  
  
		bis = new BufferedInputStream(new FileInputStream(ctxPath));  
		bos = new BufferedOutputStream(response.getOutputStream());  
		byte[] buff = new byte[2048];  
		int bytesRead;  
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
		    bos.write(buff, 0, bytesRead);  
		}  
		bis.close();  
		bos.close();
		return null;
	}
	
	private OrderCargoInfoDomain getOrderCargoByStr(String[] content, int i) throws Exception {
		OrderCargoInfoDomain orderCargoInfoDomain = new OrderCargoInfoDomain();
		List<DictInfo> areaList = DataArea.createAreaData();
		List<DictInfo> carLengthList = DataDictUtil.carLengthData();//车长
		List<DictInfo> carPlateTypeList = DataDictUtil.carPlateTypeData();//板
		List<DictInfo> carBarTypeList = DataDictUtil.carBarTypeData();//栏
		List<DictInfo> cargoTypeList = DataDictUtil.cargoTypeData();//货物类型
		String startProvinceCode = null;
		String startCityCode = null;
		String endProvinceCode = null;
		String endCityCode = null;
		if (StringUtils.isNotEmpty(content[0])) {
			orderCargoInfoDomain.setStartProvince(trimAll(content[0]));
			boolean result = true;
			for (DictInfo dictInfo : areaList) {
				if (("0".equals(dictInfo.getParentCode())) && (orderCargoInfoDomain.getStartProvince().equals(trimAll(dictInfo.getName())))) {
					startProvinceCode = dictInfo.getCode();
					result = false;
					break;
				}
			}

			if (result)
				throw new Exception("导入失败，第" + i + "行：发货地-" + content[0] + "不存在！");
			} else {
				throw new Exception("导入失败，第" + i + "行：发货地-省不能为空！");
			}
		if (StringUtils.isNotEmpty(content[1])) {
			orderCargoInfoDomain.setStartCity(trimAll(content[1]));
			boolean result = true;
			for (DictInfo dictInfo : areaList) {
				if ((dictInfo.getParentCode().equals(startProvinceCode)) && (orderCargoInfoDomain.getStartCity().equals(trimAll(dictInfo.getName())))) {
					startCityCode = dictInfo.getCode();
					result = false;
					break;
				}
			}

			if (result) {
				throw new Exception("导入失败，第" + i + "行：发货地-" + content[1] + "不存在！");
			}
		}
		if (StringUtils.isNotEmpty(content[2])) {
			orderCargoInfoDomain.setStartCounty(trimAll(content[2]));
			boolean result = true;
			for (DictInfo dictInfo : areaList) {
				if ((dictInfo.getParentCode().equals(startCityCode)) && 
						(orderCargoInfoDomain.getStartCounty().equals(trimAll(dictInfo.getName())))) {
					result = false;
					break;
				}
			}

			if (result) {
				throw new Exception("导入失败，第" + i + "行：发货地-" + content[2] + "不存在！");
			}
		}
		if (StringUtils.isNotEmpty(content[3])) {
			orderCargoInfoDomain.setEndProvince(trimAll(content[3]));
			boolean result = true;
			for (DictInfo dictInfo : areaList) {
				if (("0".equals(dictInfo.getParentCode())) && 
						(orderCargoInfoDomain.getEndProvince().equals(trimAll(dictInfo.getName())))) {
					endProvinceCode = dictInfo.getCode();
					result = false;
					break;
				}
			}

			if (result)
				throw new Exception("导入失败，第" + i + "行：卸货地-" + content[3] + "不存在！");
			} else {
				throw new Exception("导入失败，第" + i + "行：卸货地省不能为空！");
	    }
		if (StringUtils.isNotEmpty(content[4])) {
			orderCargoInfoDomain.setEndCity(trimAll(content[4]));
			boolean result = true;
			for (DictInfo dictInfo : areaList) {
				if ((dictInfo.getParentCode().equals(endProvinceCode)) && 
						(orderCargoInfoDomain.getEndCity().equals(trimAll(dictInfo.getName())))) {
					endCityCode = dictInfo.getCode();
					result = false;
					break;
				}
			}

			if (result) {
				throw new Exception("导入失败，第" + i + "行：卸货地-" + content[4] + "不存在！");
			}
		}
		if (StringUtils.isNotEmpty(content[5])) {
			orderCargoInfoDomain.setEndCounty(trimAll(content[5]));
			boolean result = true;
			for (DictInfo dictInfo : areaList) {
				if ((dictInfo.getParentCode().equals(endCityCode)) && 
						(orderCargoInfoDomain.getEndCounty().equals(trimAll(dictInfo.getName())))) {
					result = false;
					break;
				}
			}

			if (result) {
				throw new Exception("导入失败，第" + i + "行：卸货地-" + content[5] + "不存在！");
			}
		}
		if (StringUtils.isNotEmpty(content[6])) {
			orderCargoInfoDomain.setRequestCarLength(content[6]);
			boolean result = true;
			for (DictInfo dictInfo : carLengthList) {
				if (trimAll(dictInfo.getName()).equals(trimAll(orderCargoInfoDomain.getRequestCarLength()))) {
					result = false;
					break;
				}
			}
			if (result) {
				throw new Exception("导入失败，第" + i + "行：车辆长度-" + content[6] + "不存在！");
			}
		}
		if (StringUtils.isNotEmpty(content[7])) {
			orderCargoInfoDomain.setRequestCarPlateType(content[7]);
			boolean result = true;
			for (DictInfo dictInfo : carPlateTypeList) {
				if (trimAll(orderCargoInfoDomain.getRequestCarPlateType()).equals(trimAll(dictInfo.getName()))) {
					result = false;
					break;
				}
			}
			if (result) {
				throw new Exception("导入失败，第" + i + "行：车板-" + content[7] + "不存在！");
			}
		}
		if (StringUtils.isNotEmpty(content[8])) {
			orderCargoInfoDomain.setRequestCarBarType(content[8]);
			boolean result = true;
			for (DictInfo dictInfo : carBarTypeList) {
				if (trimAll(orderCargoInfoDomain.getRequestCarBarType()).equals(trimAll(dictInfo.getName()))) {
					result = false;
					break;
				}
			}
			if (result) {
				throw new Exception("导入失败，第" + i + "行：车栏-" + content[8] + "不存在！");
			}
		}
		if (StringUtils.isNotEmpty(content[9])) {
			orderCargoInfoDomain.setCargoType(content[9]);
			boolean result = true;
			for (DictInfo dictInfo : cargoTypeList) {
				if (trimAll(orderCargoInfoDomain.getCargoType()).equals(trimAll(dictInfo.getName()))) {
					orderCargoInfoDomain.setCargoType(dictInfo.getCode());
					result = false;
					break;
				}	
			}
			if (result) {
				throw new Exception("导入失败，第" + i + "行：货物类型-" + content[9] + "不存在！");
			}
		}
		orderCargoInfoDomain.setCargoName(trimAll(content[10]));
		if (StringUtils.isNotEmpty(content[11])) {
			orderCargoInfoDomain.setCargoWeight(Double.parseDouble(trimAll(content[11])));
			if (StringUtils.isNotEmpty(content[12])) {
				orderCargoInfoDomain.setCargoCubage(Double.parseDouble(trimAll(content[12])));
			}
		} else if (StringUtils.isNotEmpty(content[12])) {
			orderCargoInfoDomain.setCargoCubage(Double.parseDouble(trimAll(content[12])));
		} else {
			throw new Exception("导入失败，第" + i + "行：货物重量或体积必须填写其中一个！");
		}

		if (StringUtils.isNotEmpty(content[13])) {
			orderCargoInfoDomain.setContactName(trimAll(content[13]));
		} else {
			throw new Exception("导入失败，第" + i + "行：联系人不能为空！");
			}

		if ((StringUtils.isEmpty(content[14])) && (StringUtils.isEmpty(content[15]))) {
			throw new Exception("导入失败，第" + i + "行：手机号或固定电话必填一个！");
		}
		if (StringUtils.isNotEmpty(content[14])) {
			orderCargoInfoDomain.setContactMobilephone(trimAll(content[14]));
			if (!ValidateUtil.validateTelePhone(trimAll(orderCargoInfoDomain.getContactMobilephone()))) {
				throw new Exception("导入失败，第" + i + "行：手机号码-" + content[14] + "格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(content[15])) {
			orderCargoInfoDomain.setContactTelephone(content[15]);
			if ((!ValidateUtil.validatePhone(orderCargoInfoDomain.getContactTelephone())) 
					&& (!ValidateUtil.validatePhonees(orderCargoInfoDomain.getContactTelephone()))) {
				throw new Exception("导入失败，第" + i + "行：固定电话-" + content[15] + "格式不正确，请填写正确的固定电话！");
			}

		}

		if (StringUtils.isNotEmpty(content[16])) {
			int year = DateUtil.getYear(new Date()),startYear = DateUtil.getYear(content[16]);
			if(year != startYear) {
				throw new Exception("导入失败，第" + i + "行：装货时间<年>要为当前年！");
			}
			if(DateUtil.isEarly(content[16],DateUtil.parseDayDataFrom(new Date()))){
				throw new Exception("导入失败，第" + i + "行：装货时间不能小于当前时间！");
			}
			orderCargoInfoDomain.setRequestStartTime(content[16]);
		} else {
			throw new Exception("导入失败，第" + i + "行：装货时间不能为空！");
		}

		if (StringUtils.isNotEmpty(content[17])) {
			if(DateUtil.isEarly(content[17], content[16])){
				throw new Exception("导入失败，第" + i + "行：装货时间不能晚于卸货时间！");
			}
			orderCargoInfoDomain.setRequestEndTime(content[17]);
		}

		if (StringUtils.isEmpty(content[18])) {
			throw new Exception("导入失败，第" + i + "行：公司名称不能为空！");
		}
		String companyName = trimAll(content[18]);
		orderCargoInfoDomain.setCompanyName(companyName);
		String userId = "";
		String companyId = companyInfoModel.queryIdByCompanyName(companyName);
		if (! StringUtils.isEmpty(companyId)) {
			orderCargoInfoDomain.setCompanyId(companyId);
			userId = companyInfoModel.queryIdByCompanyId(companyId);
			if (! StringUtils.isEmpty(userId)) {
				orderCargoInfoDomain.setDeployUserid(userId);
			} else {
				WebUserInfo webUserInfo = new WebUserInfo();
				userId = insertWebUserInfo(webUserInfo, companyId);
				orderCargoInfoDomain.setDeployUserid(userId);
			}
		} else {
	      CompanyInfo companyInfo = new CompanyInfo();
	      companyInfo.setCompanyName(companyName);
	      companyInfo.setUserOrigin("1");
	      companyInfo.setDeletedFlag("0");
	      companyId = companyInfoModel.addCompanyInfo(companyInfo);
	
	      WebUserInfo webUserInfo = new WebUserInfo();
	      userId = insertWebUserInfo(webUserInfo, companyId);
	
	      orderCargoInfoDomain.setCompanyId(companyId);
	      orderCargoInfoDomain.setDeployUserid(userId);
		}

		return orderCargoInfoDomain;
	}
	
	private String insertWebUserInfo(WebUserInfo webUserInfo, String companyId) throws Exception {
	    webUserInfo.setCompanyId(companyId);
	    webUserInfo.setCode(companyId);
	    webUserInfo.setPassword("123456");
	    webUserInfo.setUserOrigin("1");
	    webUserInfo.setDeletedFlag("0");
	    webUserInfo.setLoginIp("192.168.10");
	    webUserInfo.setEnterpriseFlag("0");
	    webUserInfo.setPanymentFlag("0");
	    webUserInfo.setPersonageFlag("0");
	
	    return webUserModel.addWebUserInfo(webUserInfo);
	}

	
	public String trimAll(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		for (int i = 0; i < str.length(); i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				str = str.replaceAll("\\s*", "");
				break;
			}
		}
		return str;
	}

}
