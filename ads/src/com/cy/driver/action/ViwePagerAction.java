package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.dao.ViwePagerDao;
import com.cy.driver.domain.ImgDeployInfoDomain;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图片轮播
 * Created by haoy on 2014/12/5.
 */
@Scope("prototype")
@Controller("viwePagerAction")
public class ViwePagerAction extends AuthenticationAction {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ViwePagerDao viwePagerDao;

    @RequestMapping(value = "/viwePager")
    @ResponseBody
    public JSonResponse viwePager(String driverId) {
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            List<ImgDeployInfoDomain> list = viwePagerDao.queryViewPagers();

            JSONArray jsonObject = null;
            if (list != null && list.size() > 0) {
                jsonObject = JSONArray.fromObject(list);
            }

            return JSonResponse.makeHasContentJSonRespone("1", "轮播图片", jsonObject);
        } catch (Exception e) {
            log.error("ViwePagerAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统出错, 请稍后重试。");
        }
    }
}
