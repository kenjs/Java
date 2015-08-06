package tf56.contract.test;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import tf56.contract.domain.GoodsType;
import tf56.contract.services.GoodsTypeDao;
import tf56.sofa.util.ParseFormToBean;

/**
 * @author wei.huang
 * @date 2013-10-15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class GoodsTypeDaoImplTest {
 @Autowired
 private GoodsTypeDao goodsTypeDao;
 @Test
 public void testGetPageList() {
  Map map = new HashMap();
  map.put("goodstypeid", "100");                //
  map.put("frompartyid", "100");                //父会员ID
  map.put("topartyid", "100");                  //承接货盘的会员ID
  map.put("goodsname", "test");                 //货物名称
  map.put("helpcode", "test");                  //
  map.put("package", "test");                   //
  map.put("unitweight", "100");                 //
  map.put("unitvolume", "100");                 //
  map.put("chargetype", "test");                //
  map.put("inputdate", "test");                 //
  map.put("inputman", "test");                  //
  GoodsType goodsType = new GoodsType();      //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  goodsType = (GoodsType) pftb.parseToBean(map, goodsType);
//  System.out.println("增  加"+goodsTypeDao.insert(goodsType)); //增
//  System.out.println("删  除"+goodsTypeDao.delete(map));     //删
//  System.out.println("更  新"+goodsTypeDao.update(goodsType)); //改
//  System.out.println("查一条"+goodsTypeDao.selectById(map)); //查一条
//  System.out.println("查多条"+goodsTypeDao.selectList(map)); //查多条
 }
}
