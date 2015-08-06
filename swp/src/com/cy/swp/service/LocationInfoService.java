package com.cy.swp.service;

import com.cy.swp.domain.LocationCollectInfoDomain;

import java.util.List;
import java.util.Map;

/**
 * Created by nixianjing on 14/12/22.
 */
public interface LocationInfoService {

    public LocationCollectInfoDomain queryLocationInfoPageList(LocationCollectInfoDomain locationCollectInfoDomain);

    public String getqueryLocationInfoHtml(List<LocationCollectInfoDomain> list,Integer curPage,Integer pageSize);

}
