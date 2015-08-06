package tf56.contract.test;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import tf56.contract.domain.ConsigneeConsignorAddress;
import tf56.contract.services.ConsigneeConsignorAddressDao;
import tf56.sofa.util.ParseFormToBean;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class ConsigneeConsignorAddressDaoImplTest {
 @Autowired
 private ConsigneeConsignorAddressDao consigneeConsignorAddressDao;
 @Test
 public void testGetPageList() {
  Map map = new HashMap();
  map.put("consigneeconsignoraddressid", "30"); //
//  map.put("frompartyid", "101");                //会员ID
//  map.put("topartyid", "1001");                  //子会员ID
//  map.put("consignee", "test1");                 //
  map.put("linkman", "test2");                   //
  map.put("mobilenumber", "test3");              //手机号
  map.put("telephonenumber", "test4");           //
  map.put("province", "test5");                  //
  map.put("city", "test6");                      //市
  map.put("region", "test7");                    //区县
  map.put("town", "test8");                      //
  map.put("inputdate", "2013-10-07 09:01:21");  //输入日期
  map.put("inputman", "test9");                  //输入人
  map.put("consigneeorconsignor","0");
  map.put("checked","0");
  map.put("skipCount", "0");
  map.put("pageSize", "10");
  ConsigneeConsignorAddress consigneeConsignorAddress = new ConsigneeConsignorAddress();      //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  consigneeConsignorAddress = (ConsigneeConsignorAddress) pftb.parseToBean(map, consigneeConsignorAddress);
//  System.out.println("增  加"+consigneeConsignorAddressDao.insert(consigneeConsignorAddress)); //增
//  System.out.println("删  除"+consigneeConsignorAddressDao.delete(map));     //删
//  System.out.println("更  新"+consigneeConsignorAddressDao.updateChecked(map)); //改
  System.out.println("更  新"+consigneeConsignorAddressDao.update(consigneeConsignorAddress)); //改
//  System.out.println("查一条"+consigneeConsignorAddressDao.selectById(map)); //查一条
//  System.out.println("查多条"+consigneeConsignorAddressDao.selectList(map)); //查多条
 }
}
