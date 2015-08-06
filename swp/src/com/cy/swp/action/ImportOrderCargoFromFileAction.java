package com.cy.swp.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cy.swp.bo.DictInfo;
import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.bo.OrderCargoInfo;
import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.service.ImportOrderCargoService;
import com.cy.swp.common.util.DataArea;
import com.cy.swp.common.util.DataDictUtil;
import com.cy.swp.common.util.DateUtil;
import com.cy.swp.common.util.ExcelReaderUtil;
import com.cy.swp.common.util.ValidateUtil;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.constants.SysEnviron;


@Scope("prototype")
@Controller("importOrderCargoFromFileAction")
public class ImportOrderCargoFromFileAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ImportOrderCargoService importOrderCargoService;
	
	private String filePath;
	
	private String noteSendController;
	
	/**
	 * 打开导入页面
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/openImportCargo.jspx")
    protected ModelAndView openImportCargo(ModelAndView view) throws Exception  {
		if (this.getSessionUser() == null) {
			view.setViewName("login");
            return view;
        }
		view.setViewName("cargo/importOrderCargoFromFile");
		return view;
	}
	
	 /**
	  * 下载模板
	  * @param fileName
	  * @param response
	  */
	 @RequestMapping("/downloadOrderCargoFileTemplate.jspx")
	 public void execMethod(String fileName,HttpServletResponse response){  
	        response.setCharacterEncoding("utf-8");  
	        response.setContentType("multipart/form-data");  
	        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
	        try {  
	            File file = new File(SysEnviron.WEB_ROOT_REALPATH + File.separator + filePath + File.separator + fileName);  
	            InputStream inputStream=new FileInputStream(file);  
	            OutputStream os=response.getOutputStream();  
	            byte[] b=new byte[1024];  
	            int length;  
	            while((length=inputStream.read(b))>0){  
	                os.write(b,0,length);  
	            }  
	            inputStream.close();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    
	    @Value("#{propertiesReader['download.ordercargo.template.filepath']}")
	    public void setFilePath(String filePath) {
	        this.filePath = filePath;
	    }  
	    
	    
	    @Value("#{propertiesReader['note.send.controller']}")
	    public void setNoteSendController(String noteSendController) {
	        this.noteSendController = noteSendController;
	    }
	    
	    
	    
	    
	    
	    @RequestMapping("/importOrderCargoFromFile.jspx")
	    protected ModelAndView execMethod(ModelAndView view,HttpServletRequest request,HttpServletResponse response,String errorMessage,String successMessage) throws Exception{
			try{
				MarketingUserInfo marketingUserInfo=getSessionUser();
				if (marketingUserInfo == null) {
					view.setViewName("redirect:/");
		            return view;
				}
				// 　根据请求获得用户上传的文件列表信息
	    		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    		MultipartFile file = multipartRequest.getFile("uploadFile");
	    		String filename = file.getOriginalFilename();  
	    		if (filename == null || "".equals(filename)){  
	    			view.setViewName("cargo/importOrderCargoFromFile");
	    			view.addObject("errorMessage", "参数不能为空！");
	                return view;
	            }
	    		InputStream input = file.getInputStream();
	            Map<Integer, String> map = new ExcelReaderUtil().readExcelFile(input);
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
						+ Constants.CELL_SPLIT_STR + "货物来源"
						+ Constants.CELL_SPLIT_STR;

				if (!((String)map.get(Integer.valueOf(0))).equals(titleStr)) {
					view.setViewName("cargo/importOrderCargoFromFile");
					view.addObject("errorMessage", "标题为空或有误，请重新下载模板!");
	                return view;
				}
				String emptyStr = "";
				for (String str : titleStr.split(Constants.CELL_SPLIT_STR)) {
					emptyStr = emptyStr + Constants.CELL_SPLIT_STR;
				}
				List<OrderCargoInfoDomain> list = new ArrayList<OrderCargoInfoDomain>();
				for (int i = 1; (i <= map.size() - 1) && (!((String)map.get(Integer.valueOf(i))).equals(emptyStr)); i++){
					this.logger.debug("begin parse import order cargo file... row=[{}], content=[{}]", Integer.valueOf(i), map.get(Integer.valueOf(i)));
					String str = (String)map.get(Integer.valueOf(i));
					if (str.endsWith(Constants.CELL_SPLIT_STR)) {
						str = str + " ";
					}
					list.add(getOrderCargoByStr(str.split(Constants.CELL_SPLIT_STR), i + 1));
				}

				List<OrderCargoInfo> listId = importOrderCargoService.batchAddOrderCargoInfo(list,String.valueOf(marketingUserInfo.getId()));
				if("y".equalsIgnoreCase(noteSendController)) {
					importOrderCargoService.sendNoteBetweenCompanyAndDriver(listId);
				}
				view.setViewName("cargo/importOrderCargoFromFile");
				view.addObject("successMessage", String.valueOf(list.size()));
	            return view;
			} catch (Exception e) {
				this.logger.error("upload import order cargo file fail, message:", e);
				view.setViewName("cargo/importOrderCargoFromFile");
				view.addObject("errorMessage", e.getMessage());
	            return view;
			}
		}

		private OrderCargoInfoDomain getOrderCargoByStr(String[] content, int i) throws Exception {
			OrderCargoInfoDomain orderCargoInfoDomain = new OrderCargoInfoDomain();
			List<DictInfo> areaList = DataArea.createAreaData();
			List<DictInfo> carLengthList = DataDictUtil.carLengthData();//车长
			List<DictInfo> carPlateTypeList = DataDictUtil.carPlateTypeData();//板
			List<DictInfo> carBarTypeList = DataDictUtil.carBarTypeData();//栏
			List<DictInfo> cargoTypeList = DataDictUtil.cargoTypeData();//货物类型
			List<DictInfo> cargoInfoFromList = DataDictUtil.cargoInfoFromData();//货物来源
			
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
			} else {
				throw new Exception("导入失败，第" + i + "行：发货地-市不能为空！");
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
			} else {
				throw new Exception("导入失败，第" + i + "行：卸货地-市不能为空！");
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
			
			//保存货物名称
			if(StringUtils.isBlank(trimAll(content[10]))){
				orderCargoInfoDomain.setCargoName("普货");
			}else{
				orderCargoInfoDomain.setCargoName(trimAll(content[10]));
			}
			
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

			if ((StringUtils.isEmpty(content[14]))) {
				throw new Exception("导入失败，第" + i + "行：手机号必填！");//手机号必填
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

			if (StringUtils.isBlank(content[18])) {
				throw new Exception("导入失败，第" + i + "行：公司名称不能为空！");
			}
			orderCargoInfoDomain.setCompanyName(content[18]);
			
			//货物来源必填
			if (StringUtils.isBlank(content[19])) {
				throw new Exception("导入失败，第" + i + "行：货物来源不能为空！");
			}

			//取出货物来源，并保存
			if (StringUtils.isNotEmpty(content[19])) {
				boolean result=true;
				for(DictInfo cargoInfoFrom:cargoInfoFromList){
					if(trimAll(cargoInfoFrom.getName()).equals(trimAll(content[19]))){
						result=false;
						break;
					}
				}
				if(result){
					throw new Exception("导入失败，第" + i + "行：货物来源不存在！");
				}
				orderCargoInfoDomain.setCargoInfoFrom(content[19]);//保存
				
			}
			
			orderCargoInfoDomain.setCompanyId("1");
	      	orderCargoInfoDomain.setDeployUserid("2");

			orderCargoInfoDomain.setCargoOrigin("2");

			return orderCargoInfoDomain;
		}

		private static String saveFile(File srcFile, String fileExtension, String filepath)
				throws IOException{
			File file = new File(filepath);
			if (!file.exists()) {
				file.mkdirs();
			}
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(new Date()) + "." + fileExtension;

		    file = new File(filepath + "/" + fileName);
		    int i = 0;
		    while (file.exists()) {
		      fileName = sdf.format(new Date()) + "_" + i + "." + fileExtension;
		      file = new File(filepath + "/" + fileName);
		      i++;
		    }

		    InputStream in = null;
		    OutputStream out = null;
		    try {
		      in = new BufferedInputStream(new FileInputStream(srcFile), 16384);
		      out = new BufferedOutputStream(new FileOutputStream(file), 16384);
		      byte[] buffer = new byte[16384];
		      int len = 0;
		      while ((len = in.read(buffer)) > 0) {
		        out.write(buffer, 0, len);
		      }

		      if (null != in) {
		        in.close();
		      }
		      if (null != out)
		        out.close();
		    }
		    finally
		    {
		      if (null != in) {
		        in.close();
		      }
		      if (null != out) {
		        out.close();
		      }
		    }

		    return filepath + "/" + fileName;
		}

		

		public String trimAll(String str) {
			if (StringUtils.isBlank(str)) {
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
