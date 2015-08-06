package com.cy.swp.action;

import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.domain.DataDictInfoDomain;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.domain.MarketingMaintainRecordDomain;
import com.cy.swp.domain.PageInfo;
import com.cy.swp.service.MarketingAssisterApplyService;
import com.cy.swp.service.MarketingDriverInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 客户分配申请action
 * Created by wyh on 2014/12/10.
 */
@Scope("prototype")
@Controller("marketingAssisterApplyAction")
public class MarketingAssisterApplyAction extends BaseAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MarketingAssisterApplyService ApplyService;
    @Resource
    private MarketingDriverInfoService driverInfoService;

    private DataDictInfoDomain dataDictInfoDomain = new DataDictInfoDomain();//初始化数据字典

    /**
     * 分配申请
     * @author wyh
     */
    @RequestMapping("/applyDriver.jspx")
    @ResponseBody
    public JSonRespone applyDriver(MarketingDriverInfoDomain info){
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
                return JSonRespone.makeHasContentJSonRespone("2", "分配失败，请勾选要分配的客户资料");
            }
            //分配申请service
            Map<String, String> resMap = ApplyService.applyDriverServ(info, user);
            if ("1".equals(resMap.get("result"))) {
                return JSonRespone.makeHasContentJSonRespone("2", resMap.get("message"));
            }
            return JSonRespone.makeHasContentJSonRespone("0", "分配申请成功！");
        }catch (Exception e){
            logger.debug(e.getMessage());
            return JSonRespone.makeHasContentJSonRespone("2", e.getMessage());
        }
    }

    /**
     * 刷新营销人员导入后展示的待分配司机
     * @author wyh
     */
    @RequestMapping("/refreshImportDriver.jspx")
    @ResponseBody
    public JSonRespone refreshImportDriver(String refreshIds){
        MarketingUserInfo user = getSessionUser();
        //判断是否登陆
        if (user == null) {
            return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
        }
        logger.debug("add cargo driver contacts begin. userId=[{}]", getSessionUser().getId());
        //获得需要司机资料ids
        String[] driverIds = StringUtils.split(refreshIds, ",");
        if(driverIds == null || driverIds.length == 0){
            return JSonRespone.makeHasContentJSonRespone("2", "刷新失败，参数无数据");
        }
        //刷新营销人员导入后展示的待分配司机service
        List<MarketingDriverInfoDomain> list = ApplyService.refreshImportDriverServ(driverIds);
        //获得html
        String html = driverInfoService.getNoAssignHtml(list);
        return JSonRespone.makeHasContentJSonRespone("0", "刷新成功！", html);
    }

    /**
     * 打开添加客户页面
     * @param view
     * @return
     * @throws Exception
     */
    @RequestMapping("/enterAddDriverInfo.jspx")
    public ModelAndView enterAddDriverInfo(ModelAndView view) throws Exception  {
        if (this.getSessionUser() == null) {
            view.setViewName("login");
            return view;
        }
        String menuAId = Constants.MENU_AID_ADD_DRIVER;//菜单id
        view.addObject("menuAId", menuAId);
        view.addObject("importType", Constants.IMPORT_DRIVER_TYPE_ORD);
        view.addObject("dataDictInfoDomain", dataDictInfoDomain);
        view.setViewName("driverClient/addDriverInfo");
        return view;
    }

    /**
     * 添加单条司机客户
     * @author wyh
     */
    @RequestMapping("/addSingleDriver.jspx")
    @ResponseBody
    public JSonRespone addSingleDriver(MarketingDriverInfoDomain info){
        try {
            MarketingUserInfo user = getSessionUser();
            //判断是否登陆
            if (user == null) {
                return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
            }
            logger.debug("add cargo driver contacts begin. userId=[{}]", getSessionUser().getId());
            //分配申请service
            Map<String, Object> resMap = ApplyService.addSingleDriverServ(info, user);
            if("1".equals(resMap.get("result").toString())){
                return JSonRespone.makeHasContentJSonRespone("2", resMap.get("message").toString());
            }
            return JSonRespone.makeHasContentJSonRespone("0", "成功！", resMap);
        }catch (Exception e){
            logger.debug(e.getMessage());
            return JSonRespone.makeHasContentJSonRespone("2", e.getMessage());
        }
    }

    /**
     * 申请单条司机客户
     * @author wyh
     */
    @RequestMapping("/applySingleDriver.jspx")
    @ResponseBody
    public JSonRespone applySingleDriver(MarketingDriverInfoDomain info){
        try {
            MarketingUserInfo user = getSessionUser();
            //判断是否登陆
            if (user == null) {
                return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
            }
            logger.debug("add cargo driver contacts begin. userId=[{}]", getSessionUser().getId());
            //分配申请service
            Map<String, Object> resMap = ApplyService.applySingleDriverServ(info, user);
            if("1".equals(resMap.get("result").toString())){
                return JSonRespone.makeHasContentJSonRespone("2", resMap.get("message").toString());
            }
            return JSonRespone.makeHasContentJSonRespone("0", "申请成功！");
        }catch (Exception e){
            logger.debug(e.getMessage());
            return JSonRespone.makeHasContentJSonRespone("2", e.getMessage());
        }
    }

    //查询标记司机手机号码无效的记录
    @RequestMapping("/queryMaintainRecord.jspx")
    @ResponseBody
    public JSonRespone queryMaintainRecord(String id){
        try {
            MarketingUserInfo user = getSessionUser();
            //判断是否登陆
            if (user == null) {
                return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
            }
            logger.debug("add cargo driver contacts begin. userId=[{}]", getSessionUser().getId());
            if(StringUtils.isEmpty(id)){
                return JSonRespone.makeHasContentJSonRespone("2", "系统出错");
            }
            //查询标记司机手机号码无效的记录serv
            List<MarketingMaintainRecordDomain> list = ApplyService.queryMaintainRecordServ(id, Constants.CUSTOMER_KIND_DRIVER_KEY);
            return JSonRespone.makeHasContentJSonRespone("0", "申请成功！", list);
        }catch (Exception e){
            logger.debug(e.getMessage());
            return JSonRespone.makeHasContentJSonRespone("2", e.getMessage());
        }
    }
}
