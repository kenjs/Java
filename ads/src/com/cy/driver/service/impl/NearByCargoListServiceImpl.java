package com.cy.driver.service.impl;

import com.cy.driver.dao.DriverLineInfoDao;
import com.cy.driver.dao.DriverUserCargoInfoDao;
import com.cy.driver.dao.OrderCargoInfoDao;
import com.cy.driver.domain.DriverLineInfoDomain;
import com.cy.driver.domain.OrderCargoInfoDomain;
import com.cy.driver.service.NearByCargoListService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by haoy on 2015/1/14.
 */
@Service("nearByCargoListService")
public class NearByCargoListServiceImpl implements NearByCargoListService {

    private Logger log = LoggerFactory.getLogger(getClass());

    /** 声明一个静态变量存放组合的30条货源信息 **/
    private static List<OrderCargoInfoDomain> cargoInfoDomains = new ArrayList<OrderCargoInfoDomain>(30);

    @Resource
    private DriverLineInfoDao driverLineInfoDao;
    @Resource
    private OrderCargoInfoDao orderCargoInfoDao;
    @Resource
    private DriverUserCargoInfoDao driverUserCargoInfoDao;

    private List<OrderCargoInfoDomain> makeUpNearByCargoList(OrderCargoInfoDomain domain, Map<String, Object> par) throws SQLException {
        List<OrderCargoInfoDomain> dataList = new ArrayList<OrderCargoInfoDomain>(30);//返回的集合

        //查询司机运营线路
        @SuppressWarnings("unchecked")
        List<DriverLineInfoDomain> driverLines = (List<DriverLineInfoDomain>) driverLineInfoDao.selectDriverLineInfoList(domain.getDriverId());


        //以司机运营线路的城市为目的地查找货源
        List<OrderCargoInfoDomain> orderCargoInfoDomains = new LinkedList<OrderCargoInfoDomain>();

        if (driverLines != null && driverLines.size() > 0) {
            List<String> provinceList = new ArrayList<String>();    //存放司机运营线路省份的
            List<String> cityList = new ArrayList<String>();   //存放司机运营线路城市的
            for (DriverLineInfoDomain e : driverLines) {
                provinceList.add(e.getStartProvince());
                provinceList.add(e.getEndProvince());

                cityList.add(e.getStartCity());
                cityList.add(e.getEndCity());
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("provinces", provinceList);
            map.put("cities", cityList);
            map.putAll(par);

            orderCargoInfoDomains = orderCargoInfoDao.queryCargoByLines(map);

        } //~ 查找结束

        List<String> ids = new ArrayList<String>();//用于存放已经查找出来的货源id

        for (OrderCargoInfoDomain e : orderCargoInfoDomains) {
            ids.add(e.getId());
        }

        List<List<OrderCargoInfoDomain>> siftedCargo = siftCargoByCity(orderCargoInfoDomains);//把查询的集合按城市分组

        List<OrderCargoInfoDomain> okCargo = new ArrayList<OrderCargoInfoDomain>(10);//用于存放组合好的货源

        /**
         * 这个循环是用来组合货源的
         * 规则如下：1. 3页，每页10条数据，一共30条（如果足够）
         *          2. 每页的前几条数据由司机运营线路的城市个数决定。例如，如果司机的运营线路有3个
         *              不同的城市，并且都能找到货源，则这10条数据的前3条就是从这3个城市找到的货源
         *              中各取一条，剩下的7条从附近货源中补充。
         *          3. 30条货源中不能有相同的货源（id相同）
         */
        for (int j = 0; j < 3; j++) {
            if (siftedCargo.size() == 1) {
                List<OrderCargoInfoDomain> list = siftedCargo.get(0);
                for (int i = 0; i < list.size(); i++) {
                    OrderCargoInfoDomain e = list.get(i);
                    okCargo.add(e);

                    if (okCargo.size() == 6) {
                        break;
                    }
                }
                list.removeAll(okCargo);
                if (list.size() == 0) {
                    siftedCargo.remove(list);
                }
            } else if (siftedCargo.size() > 1){
                //这个循环是从每组中取出一个对象
                for (int i = 0; i < siftedCargo.size(); i++) {
                    List<OrderCargoInfoDomain> list = siftedCargo.get(i);
                    if (list.size() > 0) {
                        OrderCargoInfoDomain e = list.get(0);
                        okCargo.add(e);
                        list.remove(e);
                    }
                }

                for (int i = 0; i < siftedCargo.size(); i++) {
                    if (siftedCargo.get(i).size() == 0) {
                        siftedCargo.remove(siftedCargo.get(i));
                    }
                }
            }

            int size = 10 - okCargo.size();
            Map<String, Object> parMap = new HashMap<String, Object>();
            parMap.put("offSet", size);
            parMap.put("ids", ids);
            parMap.putAll(par);

            List<OrderCargoInfoDomain> cargoes = orderCargoInfoDao.queryCargoLocationAndNotExistIn(parMap);

            okCargo.addAll(cargoes);
            for (OrderCargoInfoDomain e : cargoes) {
                ids.add(e.getId());
            }

            dataList.addAll(okCargo);

            if (okCargo.size() < 10) {
                break;
            }
            okCargo.clear();
        } //~ 组合结束

        if (log.isDebugEnabled()) {
            log.debug("操作结束后dataList大小-" + dataList.size());
        }

        return dataList;
    }

    @Override
    public List<OrderCargoInfoDomain> searchCargo(OrderCargoInfoDomain domain) throws SQLException {

        Map<String, Object> map = convertDomainToMap(domain);

        //查询司机当前位置
        String province = "";
        String city = "";
        Map<String, Object> locationMap = driverUserCargoInfoDao.selectDriverLastLocation(domain.getDriverId());
        if(locationMap != null) {
            if(locationMap.containsKey("province")) {
                province = locationMap.get("province").toString();
            }
            if(locationMap.containsKey("city")) {
                city = locationMap.get("city").toString();
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("司机当前位置：" + province + "--" + city);
        }

        List<OrderCargoInfoDomain> dataList;

        switch (domain.getWay()) {
            case 0:
                log.debug("cargoInfoDomains大小为-" + cargoInfoDomains.size());
                dataList = new ArrayList<OrderCargoInfoDomain>();
                int page = (domain.getFromSize() % 10) > 0 ? (domain.getFromSize() / 10 + 1 + 1) : (domain.getFromSize() / 10 + 1);

                int size;
                if (page == 1) {
                    map.put("startProvince", province);
                    map.put("startCity", city);
                    map.remove("ids");
                    cargoInfoDomains = makeUpNearByCargoList(domain, map);
                    log.debug("cargoInfoDomains大小为-" + cargoInfoDomains.size());

                    size = cargoInfoDomains.size() / (10 * page) >= 1 ? (10 * page) : cargoInfoDomains.size();
                    for (int i = 0; i < size; i++) {
                        dataList.add(cargoInfoDomains.get(i));
                    }
                } else if (page <= 3){
                    log.debug("cargoInfoDomains大小为-" + cargoInfoDomains.size());
                    size = cargoInfoDomains.size() / (10 * page) >= 1 ? (10 * page) : cargoInfoDomains.size();
                    for (int i = (page - 1) * 10; i < size; i++) {
                        dataList.add(cargoInfoDomains.get(i));
                    }
                } else {
                    map.put("startProvince", province);
                    map.put("startCity", city);
                    map.put("fromSize", String.valueOf((page - 4) * 10));
                    map.put("listSize", String.valueOf(domain.getListSize()));
                    dataList = orderCargoInfoDao.selectCargoList(map);
                }
                break;
            case 1:
                map.remove("ids");

                if (StringUtils.isBlank(domain.getStartProvince()) && StringUtils.isBlank(domain.getStartCity())) {
                    domain.setStartProvince(province);
                    domain.setStartCity(city);
                }

                map.put("startTime", domain.getStartTime());
                map.put("endTime", domain.getEndTime());
                map.put("startProvince", domain.getStartProvince());
                map.put("endProvince", domain.getEndProvince());
                map.put("startCity", domain.getStartCity());
                map.put("endCity", domain.getEndCity());
                map.put("carType", domain.getCarType());
                map.put("fromSize", String.valueOf(domain.getFromSize()));
                map.put("listSize", String.valueOf(domain.getListSize()));

                dataList = orderCargoInfoDao.selectCargoList(map);
                break;
            default:
                dataList = new ArrayList<OrderCargoInfoDomain>();
                break;
        }

        return dataList;
    }

    /**
     * 根据城市筛选
     * @param list
     */
    private List<List<OrderCargoInfoDomain>> siftCargoByCity(List<OrderCargoInfoDomain> list) {
        List<List<OrderCargoInfoDomain>> dataList = new LinkedList<List<OrderCargoInfoDomain>>();
        if (list == null || list.size() <= 0) {
            return dataList;
        }

        for (int i = 0; i < list.size(); i++) {
            List<OrderCargoInfoDomain> targetList = new ArrayList<OrderCargoInfoDomain>();
            OrderCargoInfoDomain domain = list.get(i);
            targetList.add(domain);
            list.remove(domain);
            for (int j = 0; j < list.size(); j++) {
                OrderCargoInfoDomain e = list.get(j);
                if (domain.getEndProvince().equals(e.getEndProvince())
                        && domain.getEndCity().equals(e.getEndCity())) {
                    targetList.add(e);
                    list.remove(e);
                    j = -1;
                }
            }
            i = -1;
            dataList.add(targetList);
        }

        Collections.sort(dataList, new Comparator<List<OrderCargoInfoDomain>>() {
            @Override
            public int compare(List<OrderCargoInfoDomain> o1, List<OrderCargoInfoDomain> o2) {
                return o2.size() - o1.size() ;
            }
        });

        return dataList;
    }


    private Map<String, Object> convertDomainToMap(OrderCargoInfoDomain domain) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (domain == null) {
            return map;
        }

        String carLength = domain.getRequestCarLength();
        String carWeight = domain.getCargoWeight();

        if (carLength.contains("全部")) {
            map.put("minCarLength", "");
            map.put("maxCarLength", "");
        }

        if (carLength.contains("以下") && carLength.contains("米")) {
            map.put("minCarLength", "");
            map.put("maxCarLength", carLength.split("米")[0]);
        }

        if (carLength.contains("以上") && carLength.contains("米")) {
            map.put("minCarLength", carLength.split("米")[0]);
            map.put("maxCarLength", "");
        }

        if (carLength.contains("-") && carLength.contains("米")) {
            map.put("minCarLength", carLength.split("-")[0].split("米")[0]);
            map.put("maxCarLength", carLength.split("-")[1].split("米")[0]);
        }

        if (carWeight.contains("全部")) {
            map.put("minLoad", "");
            map.put("maxLoad", "");
        }

        if (carWeight.contains("以下") && carWeight.contains("吨")) {
            map.put("minLoad", "");
            map.put("maxLoad", carWeight.split("吨")[0]);
        }

        if (carWeight.contains("以上") && carWeight.contains("吨")) {
            map.put("minLoad", carWeight.split("吨")[0]);
            map.put("maxLoad", "");
        }

        if (carWeight.contains("-") && carWeight.contains("吨")) {
            map.put("minLoad", carWeight.split("-")[0].split("吨")[0]);
            map.put("maxLoad", carWeight.split("-")[1].split("吨")[0]);
        }

        List<String> ids;

//        if (StringUtils.isNotBlank(domain.getCargoIds())) {
//            ids = new ArrayList<String>();
//            String[] idsArr = domain.getCargoIds().split(",");
//            for (int i = 0; i < idsArr.length; i++) {
//                ids.add(idsArr[i]);
//            }
//            map.put("ids", ids);
//        }

        if (cargoInfoDomains.size() > 0) {
            ids = new ArrayList<String>();
            for (OrderCargoInfoDomain e : cargoInfoDomains) {
                ids.add(e.getId());
            }
            map.put("ids", ids);
        }

        return map;
    }
 }
