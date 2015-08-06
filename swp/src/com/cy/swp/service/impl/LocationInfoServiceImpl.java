package com.cy.swp.service.impl;

import com.cy.swp.dao.LocationInfoDao;
import com.cy.swp.domain.LocationCollectInfoDomain;
import com.cy.swp.service.LocationInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nixianjing on 14/12/22.
 */
@Service("locationInfoService")
public class LocationInfoServiceImpl implements LocationInfoService {

    @Resource
    private LocationInfoDao locationInfoDao;

    @Override
    public LocationCollectInfoDomain queryLocationInfoPageList(LocationCollectInfoDomain locationCollectInfoDomain) {
        List<LocationCollectInfoDomain> list = new ArrayList<LocationCollectInfoDomain>();
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("driverId",locationCollectInfoDomain.getDriverId());
        if(locationCollectInfoDomain.getPageInfo() != null) {
            queryMap.put("pageSize",locationCollectInfoDomain.getPageInfo().getPageSize());
            queryMap.put("curPage",locationCollectInfoDomain.getPageInfo().getPageSize()*(locationCollectInfoDomain.getPageInfo().getCurPage()-1));
            locationCollectInfoDomain.getPageInfo().setCurPageNo(locationCollectInfoDomain.getPageInfo().getCurPage());
            locationCollectInfoDomain.getPageInfo().setTotalRecords(locationInfoDao.queryLocationInfoPageCount(queryMap));
            list = locationInfoDao.queryLocationInfoPageList(queryMap);
        }else {
            list = locationInfoDao.queryLocationInfoList(queryMap);
        }
        locationCollectInfoDomain.setList(list);
        return locationCollectInfoDomain;
    }




    public String getqueryLocationInfoHtml(List<LocationCollectInfoDomain> list,Integer curPage,Integer pageSize) {
        StringBuffer locationhtml = new StringBuffer();
        locationhtml.append("<tr>");
        locationhtml.append("<td>序</td>");
        locationhtml.append("<td>定位详细地址</td>");
        locationhtml.append("<td>定位时间</td>");
        locationhtml.append("</tr>");
        Integer b = ((curPage.intValue()-1)*pageSize);
        for(int i = 0;i<list.size();i++) {
            locationhtml.append("<tr>");
            locationhtml.append("<td>"+(b.intValue()+i+1)+"</td>");
            locationhtml.append("<td>"+list.get(i).getLocation()+"</td>");
            locationhtml.append("<td>"+list.get(i).getCollectTime()+"</td>");
            locationhtml.append("</tr>");
        }
        return locationhtml.toString();
    }
}
