package tf56.contract.services;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import tf56.contract.domain.WaybillAmount;
public interface WaybillAmountDao {
   /**
    * 新增
    * hcm
    * @param waybillAmount
    * @throws Exception
    */
	public void insert(WaybillAmount waybillAmount)throws SQLException;
   /**
    * 根据waybillid 删除
    * hcm
    * @param map
    * @throws Exception
    */
   public void delete(Map map)throws SQLException;
   public String update(WaybillAmount waybillAmount); //改
   public String selectById(Map map);  //查Bean
   public String selectList(Map map);  //查List
   /**
    * 根据运单id查list
    * hcm
    * @param id
    * @return
    */
   public List selectWaybillAmountList(String id);
   
   /**
    * 根据waybillid更新运单的计费状态
    * @param waybillId 运单id
    * @param billStatus 计费状态
    * @author donghui.wang
    * @return 更新是否成功
    */
   public String updateBillStatusByWaybillId(String waybillId,String billStatus);
   /**
    * 保存费用
    * hcm
    * @param map
    * @return
    */
   public String transactionSaveWaybillamount(Map map); 
   /**
    * 结算管理中修改的保存费用
    * hcm
    * @param map
    * @return
    */
   public String transactionSaveWaybillamountWhenSettleBill(Map map);
   /**
    * @author wei.huang
    * @date 2013-11-28
    * @function 查询应收应付总金额
    * @param partyId总包partyid
    * @return List
    */
   public List selectStatisticAmount(String partyId);
   /**
    * 运单修改，修改topartyid
    * @author hcm
    * @param map
    * @return
    */
   public int updateTopartyid(Map map);
}