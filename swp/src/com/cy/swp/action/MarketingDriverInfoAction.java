package com.cy.swp.action;

import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.constants.SysEnviron;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.domain.DataDictInfoDomain;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.domain.PageInfo;
import com.cy.swp.service.MarketingDriverInfoService;
import com.cy.swp.service.MarketingUserInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 司机资料action
 * Created by wyh on 2014/12/4.
 */
@Scope("prototype")
@Controller("marketingDriverInfoAction")
public class MarketingDriverInfoAction extends BaseAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MarketingDriverInfoService driverInfoService;
    @Resource
    private MarketingUserInfoService marketingUserInfoService;

    private DataDictInfoDomain dataDictInfoDomain = new DataDictInfoDomain();//初始化数据字典

    private String filePath;//模版文件路径

    /**
     * 打开导入页面
     * @param view
     * @return
     * @throws Exception
     */
    @RequestMapping("/openImportDriver.jspx")
    public ModelAndView openImportDriver(ModelAndView view,String importType) throws Exception  {
        if (this.getSessionUser() == null) {
            view.setViewName("login");
            return view;
        }
        String menuAId = "";//菜单id
        if(Constants.IMPORT_DRIVER_TYPE_VIP.equals(importType)){
            menuAId = Constants.MENU_AID_CLIENT_ASSIGN;
        }else{
            menuAId = Constants.MENU_AID_ADD_DRIVER;
        }
        view.addObject("menuAId", menuAId);
        view.addObject("importType", importType);
        view.setViewName("driverClient/importDriverFile");
        return view;
    }

    /**
     * 下载模板
     * @param fileName
     */
    @RequestMapping("/downloadDriverFileTemplate.jspx")
    public void downloadDriverFileTemplate(String fileName){
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
        InputStream inputStream = null;
        OutputStream os = null;
        try {
            File file = new File(SysEnviron.WEB_ROOT_REALPATH + File.separator + filePath + File.separator + fileName);
            inputStream = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b=new byte[1024];
            int length;
            while((length=inputStream.read(b))>0){
                os.write(b,0,length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 执行导入操作
     */
    @RequestMapping("/importExcelDriverMess.jspx")
    public ModelAndView importExcelDriverMess(ModelAndView view,String importType){
        String menuAId = "";//菜单id
        if(Constants.IMPORT_DRIVER_TYPE_VIP.equals(importType)){
            menuAId = Constants.MENU_AID_CLIENT_ASSIGN;
        }else{
            menuAId = Constants.MENU_AID_ADD_DRIVER;
        }
        view.addObject("menuAId", menuAId);
        view.addObject("importType",importType);
        try {
            MarketingUserInfo marketingUserInfo = getSessionUser();
            if (marketingUserInfo == null) {
                view.setViewName("login");
                return view;
            }
            // 　根据请求获得用户上传的文件列表信息
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("uploadFile");
            String filename = file.getOriginalFilename();
            if (filename == null || "".equals(filename)){
                view.setViewName("driverClient/importDriverFile");
                view.addObject("errorMessage", "参数不能为空！");
                return view;
            }
            InputStream input = file.getInputStream();
            if(!(FileInputStream.class.getName().equals(input.getClass().getName()))){
                System.out.println("input的类名不一直");
                view.setViewName("driverClient/importDriverFile");
                view.addObject("errorMessage", "不是xls格式的文件");
                return view;
            }
            Map<String, Object> resmap = driverInfoService.importExcelDriver(input, marketingUserInfo, importType);
            if("1".equals(resmap.get("result"))){
                view.setViewName("driverClient/importDriverFile");
                view.addObject("errorMessage", "解析excel失败！");
                return view;
            }else if("2".equals(resmap.get("result"))){
                view.setViewName("driverClient/importDriverFile");
                view.addObject("errorMessage", "导入失败，excel中的手机号码全为空/格式不正确！");
                return view;
            }
            view.addObject("result", "10");
            view.setViewName("driverClient/importDriverFile");
            view.addObject("successMessage","导入成功");
            view.addObject("rtmapdata",resmap);
            return view;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("upload import order cargo file fail, message:", e);
            view.setViewName("driverClient/importDriverFile");
            view.addObject("errorMessage", e.getMessage());
            return view;
        }
    }

    /**
     * 进入客户分配页面
     * @autor wyh
     */
    @RequestMapping("/enterCustom.jspx")
    public ModelAndView enterCustom(MarketingDriverInfoDomain info,PageInfo page){
        ModelAndView view = new ModelAndView();
        MarketingUserInfo user = getSessionUser();
        //判断是否登陆
        if (user == null) {
            view.setViewName("login");
            return view;
        }
        String menuAId = Constants.MENU_AID_CLIENT_ASSIGN;//菜单id
        view.addObject("menuAId", menuAId);
        //初始化查询条件
        if(info.getAllocateStatus() == null){
            //未分配
            info.setAllocateStatus(Constants.ALLOCATE_STATUS_WAIT_KEY);
            //号码有效
            info.setPhoneValid(Constants.PHONE_VALID_YES);
            page.setCurPage(1);
            page.setPageSize(50);
        }
        try {
            //查询操作
            List<MarketingDriverInfoDomain> driverList = driverInfoService.queryDriverListServ(info, page);
            //设置页面列表的listhtml
            info.setListHtml(driverInfoService.getAssignCustHtml(driverList, info.getAllocateStatus()));
            MarketingUserInfo marketingUserInfo = new MarketingUserInfo();
            marketingUserInfo.setPosition(Constants.POSITION_LEADER_KEY);
            List<MarketingUserInfo> userList = marketingUserInfoService.queryByPositionSer(marketingUserInfo);
            info.setMarketingUserlist(userList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        //司机客户资料状态
        Map<String, Object> stateMap = new HashMap<String, Object>();
        stateMap.put("waitKey", Constants.ALLOCATE_STATUS_WAIT_KEY);
        stateMap.put("ingKey", Constants.ALLOCATE_STATUS_ING_KEY);
        stateMap.put("endKey", Constants.ALLOCATE_STATUS_END_KEY);
        view.addObject("stateMap", stateMap);
        view.addObject("importType", Constants.IMPORT_DRIVER_TYPE_VIP);
        view.addObject("info", info);
        view.addObject("page", page);
        view.setViewName("driverClient/driverClientAssign");
        return view;
    }

    /**
     * 分配司机资料
     * @param info
     * @param page
     * @return
     */
    @RequestMapping("/assignDriver.jspx")
    @ResponseBody
    public JSonRespone assignDriver(MarketingDriverInfoDomain info, PageInfo page){
        try {
            MarketingUserInfo user = getSessionUser();
            //判断是否登陆
            if (user == null) {
                return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
            }
            logger.debug("add cargo driver contacts begin. userId=[{}]", getSessionUser().getId());
            //获得需要司机资料ids
            String[] driverIds = StringUtils.split(info.getDriverIds(), ",");
            //营销专员id
            Integer assisterId = info.getAssisterId();
            if (driverIds == null || driverIds.length == 0) {
                return JSonRespone.makeHasContentJSonRespone("2", "分配失败，请勾选要分配的客户资料");
            }
            if (assisterId == null) {
                return JSonRespone.makeHasContentJSonRespone("2", "分配失败，请选择要被分配的营销人员");
            }
            //分配操作
            Map<String, String> rtMap = driverInfoService.assignDriverServ(info, user);
            if ("0".equals(rtMap.get("result"))) {
                return JSonRespone.makeHasContentJSonRespone("2", "分配失败，司机资料已被分配或已被申请，请刷新或重新查询");
            }
            //查询操作
            List<MarketingDriverInfoDomain> driverList = driverInfoService.queryDriverListServ(info, page);
            int listAllNum = 0;//客户总数量
            if (driverList != null) {
                listAllNum = driverList.size();
            }
            rtMap.put("listAllNum", listAllNum + "");
            //获得页面列表所需的html
            String html = driverInfoService.getAssignCustHtml(driverList, info.getAllocateStatus());
            rtMap.put("html", html);
            return JSonRespone.makeHasContentJSonRespone("0", "success", rtMap);
        } catch (Exception e){
            logger.debug(e.getMessage());
            return JSonRespone.makeHasContentJSonRespone("2", e.getMessage());
        }
    }

    /**
     * 分配申请审核
     * @param info
     * @param page
     * @return
     */
    @RequestMapping("/reviewDriver.jspx")
    @ResponseBody
    public JSonRespone reviewDriver(MarketingDriverInfoDomain info, PageInfo page, String reviewType){
        try {
            MarketingUserInfo user = getSessionUser();
            //判断是否登陆
            if (user == null) {
                return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
            }
            logger.debug("add cargo driver contacts begin. userId=[{}]", getSessionUser().getId());
            //获得需要司机资料ids
            String[] driverIds = StringUtils.split(info.getDriverIds(), ",");
            if (driverIds == null || driverIds.length == 0) {
                return JSonRespone.makeHasContentJSonRespone("2", "失败，请勾选要分配的客户资料");
            }
            //获得需要客户分配申请表ids
            String[] assisterApplyIds = StringUtils.split(info.getAssisterApplyIds(), ",");
            if (assisterApplyIds == null || assisterApplyIds.length == 0 || StringUtils.isEmpty(reviewType)) {
                return JSonRespone.makeHasContentJSonRespone("2", "失败，系统出错");
            }
            if (!"10".equals(reviewType) && !"20".equals(reviewType)) {
                //直接分配操作
                //营销专员id
                Integer assisterId = info.getAssisterId();
                if (assisterId == null) {
                    return JSonRespone.makeHasContentJSonRespone("2", "失败，请选择要被分配的营销人员");
                }
            }
            //分配操作
            Map<String, String> rtMap = driverInfoService.reviewDriverServ(info, user, reviewType);
            if ("0".equals(rtMap.get("result"))) {
                return JSonRespone.makeHasContentJSonRespone("2", rtMap.get("message"));
            }
            //查询操作
            List<MarketingDriverInfoDomain> driverList = driverInfoService.queryDriverListServ(info, page);
            int listAllNum = 0;//客户总数量
            if (driverList != null) {
                listAllNum = driverList.size();
            }
            rtMap.put("listAllNum", listAllNum + "");
            //获得页面列表所需的html
            String html = driverInfoService.getAssignCustHtml(driverList, info.getAllocateStatus());
            rtMap.put("html", html);
            return JSonRespone.makeHasContentJSonRespone("0", "success", rtMap);
        } catch (Exception e){
            logger.debug(e.getMessage());
            return JSonRespone.makeHasContentJSonRespone("2", e.getMessage());
        }
    }

    /**
     * 客户分配页面ajax请求切换table页
     * @param info
     * @param page
     * @return
     */
    @RequestMapping("/cutDriverTable.jspx")
    @ResponseBody
    public JSonRespone cutDriverTable(MarketingDriverInfoDomain info, PageInfo page){
        MarketingUserInfo user = getSessionUser();
        //判断是否登陆
        if (user == null) {
            return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
        }
        logger.debug("add cargo driver contacts begin. userId=[{}]", getSessionUser().getId());
        Map<String, Object> rtMap = new HashMap<String, Object>();
        //查询操作
        List<MarketingDriverInfoDomain> driverList = driverInfoService.queryDriverListServ(info, page);
        rtMap.put("listAllNum", page.getTotalRecords());
        //获得页面列表所需的html
        String html = driverInfoService.getAssignCustHtml(driverList, info.getAllocateStatus());
        rtMap.put("html", html);
        return JSonRespone.makeHasContentJSonRespone("0", "success", rtMap);
    }

    /**
     * 进入未联系客户页面(查询)
     * @autor wyh
     */
    @RequestMapping("/enterNoContCustom.jspx")
    public ModelAndView enterNoContCustom(MarketingDriverInfoDomain info,PageInfo page){
        ModelAndView view = new ModelAndView();
        MarketingUserInfo user = getSessionUser();
        //判断是否登陆
        if (user == null) {
            view.setViewName("login");
            return view;
        }
        String menuAId = Constants.MENU_AID_NO_CONTACT_CLIENT;//菜单id
        view.addObject("menuAId", menuAId);
        try {
            //未联系客户页面页面查询
            List<MarketingDriverInfoDomain> list = driverInfoService.enterNoContCustomServ(info, page, user);
            //页面列表html
            info.setListHtml(driverInfoService.getNoCustHtml(list));
            //分页html
            info.setPageHtml(driverInfoService.getPageHtml(page));
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        view.addObject("info", info);
        view.addObject("page", page);
        view.addObject("allCustomKey", Constants.NO_CUSTOM_TYPE_ALL);//所有客户的值
        view.addObject("makCustomKey", Constants.NO_CUSTOM_TYPE_MAK);//预约客户的值
        view.setViewName("driverClient/noContactClient");
        return view;
    }

    /**
     * 进入我的客户页面(查询)
     * @autor wyh
     */
    @RequestMapping("/enterMyCustom.jspx")
    public ModelAndView enterMyCustom(MarketingDriverInfoDomain info,PageInfo page){
        ModelAndView view = new ModelAndView();
        MarketingUserInfo user = getSessionUser();
        //判断是否登陆
        if (user == null) {
            view.setViewName("login");
            return view;
        }
        String menuAId = Constants.MENU_AID_MY_CLIENT;//菜单id
        view.addObject("menuAId", menuAId);
        //初始化查询条件
        info.setAssisterIdQuery(user.getId());//当前登录营销人员的id
        if(info.getOptRegister() == null){
            //已注册
            info.setOptRegister(Constants.REGISTER_YES_KEY);
        }
        if(page.getPageSize() == Constants.PAGE_SIZE_MAIN){
            page.setPageSize(50);//默认显示50条
        }
        //客户总量存放
        PageInfo allDriverPage = new PageInfo();
        try {
            //查询我的客户司机资料
            List<MarketingDriverInfoDomain> driverList = driverInfoService.queryMyDriverListServ(info, page, allDriverPage);
            info.setListHtml(driverInfoService.getMyCustHtml(driverList));
            info.setPageHtml(driverInfoService.getPageHtml(page));
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        view.addObject("info", info);
        view.addObject("page", page);
        view.addObject("allDriverPage", allDriverPage);
        view.addObject("registerNoKey", Constants.REGISTER_NO_KEY);
        view.addObject("registerYesKey", Constants.REGISTER_YES_KEY);
        view.addObject("dataDictInfoDomain", dataDictInfoDomain);
        view.setViewName("driverClient/myClient");
        return view;
    }

    @Value("#{propertiesReader['download.ordercargo.template.filepath']}")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
