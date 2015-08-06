package tf56.contract.services;

import java.util.List;
import java.util.Map;
import tf56.contract.domain.CityDistance;

/**
 * @author wei.huang 2013-11-06
 */
public interface CityDistanceDao {

    /**
     * 根据发货方会员id和总包会员id查询城区距离详情列表
     * 
     * @param fromPartyId 发货方的会员id
     * @param partyId 总包的会员id
     * @author donghui.wang
     * @date 2013-11-5
     * @return 城区距离详情列表
     */
    public List<CityDistance> selectDetailList(Map map);

    /**
     * 根据发货方会员id和总包会员id查询城区距离总条数
     * 
     * @param fromPartyId 发货方的会员id
     * @param partyId 总包的会员id
     * @author donghui.wang
     * @date 2013-11-5
     * @return 城区距离总条数
     */
    public Integer countCityDistanceList(Map map);

    public String insert(CityDistance cityDistance); // 增
    /**
     * wei.haung
     * 2013-11-8
     * 根据citydistanceid删除
     */
    public String delete(Map map); // 删
    
    /**
     * wei.haung
     * 2013-11-8
     * 根据frompartyid和partyid删除
     */
    public String deleteByFromPartyIdAndPartyId(Map map);

    public String update(CityDistance cityDistance); // 改

    public String selectById(Map map); // 查Bean

    /**
     * wei.huang 2013-11-06 获取城区间距离列表(contract数据库的部分) Map partyid:当前登入的总包会员的id
     */
    public List selectList(Map map);

    /**
     * @author wei.haung
     * @date 2013-11-07
     * @function 验证记录是否存在
     * @return 记录条数
     */
    public String isExist(Map map);
    /**
     * @author wei.huang
     * @date 2013-11-11
     * @function 查询指定发货方下的城区距离列表
     * @param Map包含:frompartyid,partyid,[fromaddress],[toaddress]
     * @return List
     */
    public List selectListByFromAddressAndToAddress(Map map);
    /***
     * 检测此发货方是否已经进行过城区间距离设置，若有则返回1，无则返回0
     * @return
     */
    public String checkConsignorExist(Map map);
    
    /**
     * 根据发货方id、发货地和收货地查询城区间距离
     * 全匹配
     * @param map
     * @return
     */
    public List selectByFromAddressAndToAddressList(Map map);
}
