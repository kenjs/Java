package tf56.contract.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.CityDistance;
import tf56.contract.services.CityDistanceDao;
import tf56.sofa.serializer.JsonGenerateUtil;

/**
 * @author wei.huang 2013-11-5
 */
public class CityDistanceDaoImpl extends SqlMapClientDaoSupport implements CityDistanceDao {

    public String insert(CityDistance cityDistance) { // 增 实现类
        String citydistanceid = "", msg = "ok";
        try {
            citydistanceid = (String) getSqlMapClientTemplate().insert("iBatisInsertCityDistance", cityDistance);
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        String msgJson = JsonGenerateUtil.getMsgIdJson(msg, citydistanceid);
        return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
    }

    /**
     * 根据发货方会员id和总包会员id查询城区距离详情列表
     * 
     * @param fromPartyId 发货方的会员id
     * @param partyId 总包的会员id
     * @param skipCount
     * @param pageSize
     * @author donghui.wang
     * @date 2013-11-5
     * @return 城区距离详情列表
     */
    @Override
    public List<CityDistance> selectDetailList(Map param) {
        List<CityDistance> result = null;

        try {
            result = this.getSqlMapClientTemplate().queryForList("iBatisSelectList", param);
        } catch (Exception e) {
            result = null;
        }

        return result;
    }
    /**
     * wei.haung
     * 2013-11-8
     * 根据citydistanceid删除
     */
    public String delete(Map map) { // 删 实现类
        String msg = "ok";
        try {
            int i = getSqlMapClientTemplate().delete("iBatisDeleteCityDistance", map);
            if (i != 1) {
                msg = "删除失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        String msgJson = JsonGenerateUtil.getMsgIdJson(msg, "");
        return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
    }
    /**
     * wei.haung
     * 2013-11-8
     * 根据frompartyid和partyid删除
     */
    public String deleteByFromPartyIdAndPartyId(Map map) { // 删 实现类
        String msg = "ok";
        try {
            int i = getSqlMapClientTemplate().delete("iBatisDeleteCityDistanceByFromPartyIdAndPartyId", map);
            if (i < 1) {
                msg = "删除失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        String msgJson = JsonGenerateUtil.getMsgIdJson(msg, "");
        return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
    }

    public String update(CityDistance cityDistance) { // 改 实现类
        String citydistanceid = "", msg = "ok";
        try {
            int i = getSqlMapClientTemplate().update("iBatisUpdateCityDistance", cityDistance);
            if (i != 1) {
                msg = "更新失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        String msgJson = JsonGenerateUtil.getMsgIdJson(msg, cityDistance.getCitydistanceid());
        return msgJson;
    }

    public String selectById(Map map) { // 查询数据到Bean,然后转成map
        String msgJson = "";
        try {
            CityDistance cityDistance = (CityDistance) this.getSqlMapClientTemplate().queryForObject(
                                                                                                     "iBatisSelectCityDistanceById",
                                                                                                     map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgJson;
    }

    /**
     * wei.huang 2013-11-06 获取城区间距离列表(contract数据库的部分) Map partyid:当前登入的总包会员的id
     */
    public List selectList(Map map) {
        List<Map> list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectCityDistanceList", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @author wei.haung
     * @date 2013-11-07
     * @function 验证记录是否存在
     * @return 记录条数
     */
    public String isExist(Map map) {
        String count = "";
        try {
            count = this.getSqlMapClientTemplate().queryForObject("iBatisCheckCityDistance", map).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 根据发货方会员id和总包会员id查询城区距离总条数
     * 
     * @param fromPartyId 发货方的会员id
     * @param partyId 总包的会员id
     * @author donghui.wang
     * @date 2013-11-5
     * @return 城区距离总条数
     */
    @Override
    public Integer countCityDistanceList(Map param) {
        try {
            Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("iBatisCountCityDistanceList",
                                                                                    param);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * @author wei.huang
     * @date 2013-11-11
     * @function 查询指定发货方下的城区距离列表
     * @return List
     */
    public List selectListByFromAddressAndToAddress(Map map){
    	List<Map> list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectCityDistanceListByFromAddressAndToAddress", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /***
     * 检测此发货方是否已经进行过城区间距离设置，若有则返回1，无则返回0
     * @return
     */
    public String checkConsignorExist(Map map){
       String msg="0";
       try {
        int i=(Integer)this.getSqlMapClientTemplate().queryForObject("iBatisCheckConsignorExist", map);
        if(i>0){
            msg="1";
        }
    } catch (DataAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return msg;
    }

    /****
     * 根据发货方id、发货地和收货地查询城区间距离
     * 全匹配
     * 
     */
	public List selectByFromAddressAndToAddressList(Map map) {
		List<Map> list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectCityDistanceByFromAddressAndToAddressList", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
}
