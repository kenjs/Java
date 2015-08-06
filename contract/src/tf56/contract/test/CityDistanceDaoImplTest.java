package tf56.contract.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import tf56.contract.domain.CityDistance;
import tf56.contract.services.CityDistanceDao;
import tf56.contract.services.impl.PartyServiceImpl;
import tf56.sofa.util.ParseFormToBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext-*.xml" })
public class CityDistanceDaoImplTest {
 @Autowired
 private CityDistanceDao cityDistanceDao;
 @Autowired
 private PartyServiceImpl partyServiceImpl;
 
    // private PartyServiceImpl partyServiceImpl;
    @Test
    public void testGetPageList() {
        Map map = new HashMap();
        map.put("citydistanceid", "7"); //
        map.put("frompartyid", "118441"); // 会员ID
        map.put("partyid", "87"); // 会员ID
        map.put("topartyid", "0"); // 承接货盘的会员ID
        map.put("fromaddress", ""); //
        map.put("toaddress", ""); //
        map.put("distance", "1111"); //
        map.put("inputdate", "2013-11-06 13:42:25"); // 新增日期
        map.put("inputman", "测试"); // 输入人
        map.put("updatedate", "2013-11-06 13:42:25"); //
        map.put("updateman", "测试");
        map.put("skipCount", "0");
        map.put("pageSize", "10");
        // try {
        // System.err.println("查多条"+partyServiceImpl.cityDistanceList(map));
        // } catch (JSONException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } //查多条

        CityDistance cityDistance = new CityDistance(); // 把map转成bean对象
        ParseFormToBean pftb = new ParseFormToBean();
        cityDistance = (CityDistance) pftb.parseToBean(map, cityDistance);
        // System.out.println("增  加"+cityDistanceDao.insert(cityDistance)); //增
        // System.out.println("删  除"+cityDistanceDao.delete(map)); //删
//        System.out.println("更  新" + cityDistanceDao.update(cityDistance)); // 改
        // System.out.println("查一条"+cityDistanceDao.selectById(map)); //查一条
        // System.out.println("查多条"+cityDistanceDao.selectList(map)); //查多条
        System.err.println("查多条"+cityDistanceDao.selectListByFromAddressAndToAddress(map)); //查多条
    }
}
