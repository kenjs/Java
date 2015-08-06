package tf56.contract.test;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import tf56.contract.domain.ContractAppendix;
import tf56.contract.services.ContractAppendixDao;
import tf56.sofa.util.ParseFormToBean;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class ContractAppendixDaoImplTest {
 @Autowired
 private ContractAppendixDao contractAppendixDao;
 @Test
 public void testGetPageList() {
  Map map = new HashMap();
  map.put("contractappendixid", "100");         //
  map.put("tablename", "test");                 //
  map.put("tableid", "test");                   //
  map.put("type", "test");                      //
  map.put("filename", "test");                  //
  map.put("url", "D:\\Apache Software Foundation\\Tomcat 7.0\\webapps\\site\\attachment\\黄威_心得.docx");
  map.put("inputdate", "test");                 //
  ContractAppendix contractAppendix = new ContractAppendix();      //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  contractAppendix = (ContractAppendix) pftb.parseToBean(map, contractAppendix);
  System.err.println("检验："+contractAppendixDao.isExist(map));
//  System.out.println("增  加"+contractAppendixDao.insert(contractAppendix)); //增
//  System.out.println("删  除"+contractAppendixDao.delete(map));     //删
//  System.out.println("更  新"+contractAppendixDao.update(contractAppendix)); //改
//  System.out.println("查一条"+contractAppendixDao.selectById(map)); //查一条
//  System.out.println("查多条"+contractAppendixDao.selectList(map)); //查多条
 }
}
