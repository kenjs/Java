package tf56.contract.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.ShipperRelaSubContractor;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.sofa.serializer.JsonGenerateUtil;

public class ShipperRelaSubContractorDaoImpl extends SqlMapClientDaoSupport implements ShipperRelaSubContractorDao{
	/****
	 * 校验总包与分包是否已经关联，
	 * @param partyid 总包partyid
	 * @param topartyid 分包商partyid
	 * @param frompartyid 发货方partyid
	 * @return ok or sorry
	 */
	@Override
	public String checkRealtionExsit(Map map) {
		String msg="sorry";
		int i=(Integer)this.getSqlMapClientTemplate().queryForObject("iBatisCheckRealtionExsit", map);
		if(i<=0){
			msg="ok";
		}
		return msg;
	}
	/****
	 * 总包关联分包商或总包关联发货方
	 * @param partyid
	 * @param topartyid
	 * @param frompartyid
	 * @return
	 */
	@Override
	public String transactionInsertShipperRelaSubContractor(Map map) {
		String msg="sorry";
		try {
			this.getSqlMapClient().startTransaction();
			String result = (String)this.getSqlMapClientTemplate().insert("iBatisInsertShipperRelaSubContractor",map).toString();//建立总包与分包关系
			if(!"".equals(result)){
			    msg = "ok";
			}
			//此处插入总包扩展表
			String formpartyid=map.get("partyid").toString();
			List list=new ArrayList();
			if(map.get("shipperorsubcontractor").equals("0") && (map.get("saler") != null && !"".equals(map.get("saler").toString()))){//总包--分包商关系
				Map map1=new HashMap();
				map1.put("frompartyid", formpartyid);
				map1.put("shipperorsubcontractor", "0");
				map1.put("topartyid", map.get("topartyid"));
				map1.put("attributename", "业务员");
				map1.put("attributevalue", map.get("saler").toString());
				list.add(map1);
				msg=this.getSqlMapClientTemplate().insert("iBatisInsertContractAttribute", map1).toString();
				msg="ok";
			}
			if(map.get("shipperorsubcontractor").equals("1")){//总包--发货方关系
				Map map1=new HashMap();
				map1.put("frompartyid", formpartyid);
				map1.put("shipperorsubcontractor", "1");
				map1.put("topartyid", map.get("frompartyid"));
				map1.put("attributename", "业务员");
				map1.put("attributevalue", map.get("saler"));
				msg=this.getSqlMapClientTemplate().insert("iBatisInsertContractAttribute", map1).toString();
				map1.put("attributename", "客户号");
				map1.put("attributevalue", map.get("clientcode"));
				msg=this.getSqlMapClientTemplate().insert("iBatisInsertContractAttribute", map1).toString();
				this.getSqlMapClient().commitTransaction();
				msg="ok";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("") {
			};
		}finally{
			try {
				this.getSqlMapClient().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	/***
	 * 查询总包会员下的分包商或发货方
	 * @author lianggui.zhou
	 * @date 2013-10-17
	 * @param partyid 总包partyid
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<Map> contractAndSubcontractList(String partyId,String partytype,String frompartyid,Map map){
		List<Map> list=new ArrayList<Map>();
		map.put("partyid", partyId);map.put("partytype",partytype);
		map.put("frompartyid",frompartyid);
		if(map.get("yshy")!=null){
			if(StringUtils.isNotEmpty(map.get("yshy").toString())){
				map.put("yshy","%"+map.get("yshy").toString()+"%");
			}
		}
		if(map.get("jyfw")!=null){
			if(StringUtils.isNotEmpty(map.get("jyfw").toString())){
				map.put("jyfw","%"+map.get("jyfw").toString()+"%");
			}
		}
		list=this.getSqlMapClientTemplate().queryForList("iBatisContractAndSubcontractList",map);
		return list;
	}
	/***
	 * 查询发货方关联的所有分包商id
	 * @author hcm
	 * @date 2014-1-9
	 * @param frompartyid 总包frompartyid
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<Map> subcontractList(String frompartyid){
		Map map = new HashMap();
		List<Map> list=new ArrayList<Map>();
		map.put("frompartyid", frompartyid);
		list=this.getSqlMapClientTemplate().queryForList("iBatisSubcontractList",map);
		return list;
	}
	/****
	 * 总包导入发货方时，关联分包商
	 * @author lianggui.zhou
	 * @date 2013-10-23
	 * @param partyid总包会员partyid,topartyid 分包商partyid,frompartyid发货方partyid,map 附加参数
	 * @return
	 */
	public String transactionContractLinkSubContractor(String partyid,String topartyid,String frompartyid,Map map){
		map.put("partyid", partyid);
		map.put("frompartyid", frompartyid);
		map.put("topartyid", topartyid);
		String msg="sorry";
		int count=0;
		count=(Integer)this.getSqlMapClientTemplate().queryForObject("iBatisContractLinkSubContractorCount", map);
		try {
		try {
			if(count<=0){
				int m=Integer.parseInt(this.getSqlMapClientTemplate().insert("iBatisInsertShipperRelaSubContractor", map).toString());
				if(m>0){
					msg="ok";
				}
			}else{
				this.getSqlMapClient().startTransaction();
				int i=this.getSqlMapClientTemplate().update("iBatisUpdateShipperRelaSubContractor", map);
				int j=this.getSqlMapClientTemplate().delete("iBatisDeleteShipperRelaSubContractor", map);
				if(i>0 || j>0){
					msg="ok";
				}
				this.getSqlMapClient().commitTransaction();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			throw new DataAccessException("") {
			};
		}
		}finally{
			try {
				this.getSqlMapClient().endTransaction();//此处一定要结束事务，不管成功与否
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new JsonGenerateUtil().getMsgJson(msg);
	}
	
	/**
     * 删除总包下的发货方
     * @param map frompartyid(发货方会员id) 和 partyid(总包会员id)
     * @return 是否成功
     * @author donghui.wang
     * @date 2013-10-23
     */
    @Override
    public String deleteConsigner(Map map) {
        String result = "ok";
        try{
            this.getSqlMapClientTemplate().update("iBatisDeleteConsigner",map);
        }catch(Exception e){
            result = "sorry";
        }
        return result;
    }
    
    /**
     * 删除总包下的分包商
     * @param map frompartyid(分包商会员id) 和 partyid(总包会员id)
     * @return 是否成功
     * @author donghui.wang
     * @date 2013-10-23
     */
    @Override
    public String deleteSubContract(Map map) {
        String result = "ok";
        try{
           int i= this.getSqlMapClientTemplate().update("iBatisDeleteSubContract",map);
            int j=this.getSqlMapClientTemplate().delete("iBatisDelContractAppendix", map);//删除总包下的分包商或发货方附件表记录
        }catch(Exception e){
            result = "sorry";
        }
        return result;
    }
    
    /**
     * 判断总包下的某一个分包商是否存在有发货方关联
     * @param map
     * @return 1-存在 0-不存在
     * @author donghui.wang
     * @date 2013-10-23
     */
    @Override
    public String isSubContractContainsSender(Map params) {
        Integer result = 0;
        try{
            result = (Integer) this.getSqlMapClientTemplate().queryForObject("iBatisIsSubContractContainsSender",params);
            if(result > 0){
                result = 1;
            }
        }catch(Exception e){
            result = 0;
        }
        return result.toString();
    }
    public List<Map> shipperRelaSubContractorList(String partyid){
        List<Map> list=new ArrayList<Map>();
        try {
            list=this.getSqlMapClientTemplate().queryForList("iBatisShipperRelaSubContractorList",partyid);
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    /***
	 * 查询分包商银行保理列表
	 * @author HCM
	 * @date 2014-2-27
	 * @param 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<Map> factoringList(Map map){
		List<Map> list=new ArrayList<Map>();
		list=this.getSqlMapClientTemplate().queryForList("iBatisFactoringList",map);
		return list;
	}
	/***
	 * 查询分包商银行保理详情
	 * @author HCM
	 * @date 2014-2-27
	 * @param 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<Map> factoringDetail(Map map){
		 List<Map> result = this.getSqlMapClientTemplate().queryForList("iBatisFactoringDetail",map);
	     return result;
	}
	/***
	 * 查询分包商银行保理发货方详情
	 * @author HCM
	 * @date 2014-2-27
	 * @param 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<ShipperRelaSubContractor> factoringDetailFhf(Map map){
		 List<ShipperRelaSubContractor> result = this.getSqlMapClientTemplate().queryForList("iBatisFactoringDetailFhf",map);
	     return result;
	}
	/****
	 * 根据frompartyid查partyid
	 * hcm
	 */
	@Override
	public String selectPartyIdForOutWaybill(Map map) {
		int i=(Integer)this.getSqlMapClientTemplate().queryForObject("iBatisSelectPartyIdForOutWaybill", map);
		return i+"";
	}
}
