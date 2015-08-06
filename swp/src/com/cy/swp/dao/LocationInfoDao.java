package com.cy.swp.dao;

import com.cy.swp.domain.LocationCollectInfoDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by nixianjing on 14/12/22.
 */
@Repository("locationInfoDao")
public interface LocationInfoDao {


    public Integer queryLocationInfoPageCount(Map<String,Object> hashMpa);


    public List<LocationCollectInfoDomain> queryLocationInfoPageList(Map<String,Object> hashMpa);

    public List<LocationCollectInfoDomain> queryLocationInfoList(Map<String,Object> hashMpa);


}
