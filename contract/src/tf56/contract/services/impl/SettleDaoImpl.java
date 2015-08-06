package tf56.contract.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.SettleSet;
import tf56.contract.domain.SettleStepSet;
import tf56.contract.services.SettleDao;
import tf56.sofa.serializer.JsonGenerateUtil;

/**
 * 
 * @author yaoyan.lin
 *
 */
public class SettleDaoImpl extends SqlMapClientDaoSupport implements SettleDao {

	/**
	 * yaoyan.lin 2013-11-20
	 * @param settleSet
	 * @return
	 */
	public String insert(SettleSet settleSet) { // 增 实现类
        String settleSetId = "", msg = "ok";
        try {
        	settleSetId = (String) getSqlMapClientTemplate().insert("iBatisInsertSettleSet", settleSet);
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        String msgJson = JsonGenerateUtil.getMsgIdJson(msg, settleSetId);
        return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
    }
	
	/**
	 * yaoyan.lin 2014-03-17
	 * @param settleStepSet
	 * @return
	 */
	public String insert(SettleStepSet settleStepSet) { // 增 实现类
        String settleSetId = "";
        try {
        	settleSetId = (String) getSqlMapClientTemplate().insert("iBatisInsertSettleStepSet", settleStepSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settleSetId;
    }
	
	/**
	 * yaoyan.lin 2013-11-19
	 * 
	 * @param map获取结算设置列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String settleSetList(Map param) {
		if(StringUtils.isNotBlank(param.get("type").toString())){
			param.put("type", "%"+param.get("type")+"%");
		}
		if(StringUtils.isNotBlank(param.get("toPartyId").toString())){
			param.put("toPartyId", "%"+param.get("toPartyId")+"%");
		}
		if(StringUtils.isNotBlank(param.get("toPartyId_fbs").toString())){
			param.put("toPartyId_fbs", "%"+param.get("toPartyId_fbs")+"%");
		}
		List<Map> list = new ArrayList<Map>();
		list = this.getSqlMapClientTemplate().queryForList("iBatisSelectSettleSetList", param);
		String count=this.getSqlMapClientTemplate().queryForObject("iBatisSelectSettleSetListCount", param).toString();
		String msg = JsonGenerateUtil.getPageListJson(list, count);
		return msg;
	}
	/**
	 * yaoyan.lin 2013-11-21
	 * 
	 * @param map获取结算设置列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map> selectList(Map param) {
		if(StringUtils.isNotBlank(param.get("type").toString())){
			param.put("type", param.get("type"));
		}
		if(StringUtils.isNotBlank(param.get("topartyid").toString())){
			param.put("topartyid", "%"+param.get("topartyid")+"%");
		}
		if(StringUtils.isNotBlank(param.get("topartyidfbsid").toString())){
			param.put("topartyidfbsid", "%"+param.get("topartyidfbsid")+"%");
		}
		if((StringUtils.isNotBlank(param.get("topartyid").toString()))&&(StringUtils.isNotBlank(param.get("topartyidfbsid").toString()))){
			return null;//如果发货方和分包商都不为空，则直接返回null
		}
		List<Map> list = new ArrayList<Map>();
		list = this.getSqlMapClientTemplate().queryForList("iBatisSelectSettleSetList", param);
		return list;
	}
	
	/**
     * @author yaoyan.lin
     * @date 2013-11-20
     * @function 验证记录是否存在
     * @return 记录条数
     */
    @SuppressWarnings("unchecked")
	public String isExist(Map map) {
        String count = "";
        try {
            count = this.getSqlMapClientTemplate().queryForObject("iBatisCheckSettleSet", map).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    /**
     * @author yaoyan.lin
     * @date 2013-11-20
     * @function 验证记录是否存在
     * @return 记录条数
     */
    @SuppressWarnings("unchecked")
	public String isExist_jt(Map map) {
        String count = "";
        try {
            count = this.getSqlMapClientTemplate().queryForObject("iBatisCheckSettleSet_jt", map).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * yaoyan.lin
     * 2013-11-20
     * 根据settleSetId删除
     */
    @SuppressWarnings("unchecked")
	public String delete(Map map) { // 删 实现类
        String msg = "ok";
        try {
            int i = getSqlMapClientTemplate().delete("iBatisDeleteSettleSet", map);
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

	@Override
	public String update(SettleSet settleSet) { // 改 实现类
        String msg = "ok";
        try {
            int i = getSqlMapClientTemplate().update("iBatisUpdateSettleSet", settleSet);
            if (i != 1) {
                msg = "更新失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        String msgJson = JsonGenerateUtil.getMsgIdJson(msg, settleSet.getSettlesetid());
        return msgJson;
    }

	@Override
	public String update(SettleStepSet settleStepSet) { // 改 实现类
        String msg = "ok";
        try {
            int i = getSqlMapClientTemplate().update("iBatisUpdateSettleSet_jt", settleStepSet);
            if (i != 1) {
                msg = "更新失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        String msgJson = JsonGenerateUtil.getMsgIdJson(msg, settleStepSet.getSettlesetid());
        return msgJson;
    }
    
    /**
     * yaoyan.lin
     * 2013-11-21
     * 根据topartyid和type删除
     */
    public String deleteByTopartyidAndType(Map map) { // 删 实现类
        String msg = "ok";
        try {
            int i = getSqlMapClientTemplate().delete("iBatisDeleteByTopartyidAndType", map);
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

    /**
     * 根据发货方会员id和总包会员id查询结算设置详情列表
     * 
     * @param frompartyid 总包的会员id
     * @param topartyid 发货方的会员id
     * @param type 结算类型
     * @author yaoyan.lin
     * @date 2013-11-25
     * @return 结算设置详情列表
     */
    @Override
    public List<SettleSet> selectList(String frompartyid, String topartyid, String type) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("frompartyid", frompartyid);
        param.put("topartyid", topartyid);
        param.put("type", type);

        List<SettleSet> result = null;

        try {
            result = this.getSqlMapClientTemplate().queryForList("iBatisSelectSettleSetDetailList", param);
        } catch (Exception e) {
            result = null;
        }

        return result;
    }

    /**
     * 根据发货方会员id和总包会员id查询结算(阶梯)设置详情列表
     * 
     * @param frompartyid 总包的会员id
     * @param topartyid 发货方的会员id
     * @param type 结算类型
     * @author yao.xia
     * @date 2014-03-17
     * @return 结算设置(阶梯报价)详情列表
     */
    @Override
    public List<SettleSet> selectJtList(String frompartyid, String topartyid, String type) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("frompartyid", frompartyid);
        param.put("topartyid", topartyid);
        param.put("type", type);
        
        List<SettleSet> result = null;
        try {
            result = this.getSqlMapClientTemplate().queryForList("iBatisSelectSettleSetDetailJtList", param);
        } catch (Exception e) {
            result = null;
        }

        return result;
    }
    
    /**
     * 根据发货方会员id和总包会员id查询结算设置总条数
     * 
     * @param frompartyid 总包的会员id
     * @param topartyid 发货方的会员id
     * @author yaoyan.lin
     * @date 2013-11-25
     * @return 结算设置总条数
     */
    @Override
    public Integer countSettleSetList(String frompartyid, String topartyid, String type) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("frompartyid", frompartyid);
        param.put("topartyid", topartyid);
        param.put("type", type);

        try {
            Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("iBatisCountSettleSetList",
                                                                                    param);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

	@Override
	public String selectListBySettleSetId(Map param) {
		List<Map> list = new ArrayList<Map>();
		list = this.getSqlMapClientTemplate().queryForList("selectListBySettleSetId", param);
		String count=this.getSqlMapClientTemplate().queryForObject("selectListBySettleSetIdCount", param).toString();
		String msg = JsonGenerateUtil.getPageListJson(list, count);
		return msg;
	}

    /**
     * 根据发货商/分包商id和结算类型查询记录
     * 
     * @param map
     * @author yaoyan.lin
     * @date 2013-11-26
     * @return 
     */
	@Override
	public String settleSetListByTopartyidAndType(Map param) {
		List<Map> list = new ArrayList<Map>();
		list = this.getSqlMapClientTemplate().queryForList("settleSetListByTopartyidAndType", param);
		String count=this.getSqlMapClientTemplate().queryForObject("settleSetListByTopartyidAndTypeCount", param).toString();
		String msg = JsonGenerateUtil.getPageListJson(list, count);
		return msg;
	}

	/**
     * yaoyan.lin
     * 2014-03-17
     * 根据settleSetId删除
     */
	@Override
	public void deleteStep(Map map) { // 删 实现类
        try {
            getSqlMapClientTemplate().delete("iBatisDeleteStep", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
     * 根据发货商/分包商id和结算类型查询记录
     * 
     * @param map
     * @author yaoyan.lin
     * @date 2013-11-26
     * @return 
     */
	@Override
	public List settleSetListByTopartyidAndType_jt(Map param) {
		List list = new ArrayList();
		list = this.getSqlMapClientTemplate().queryForList("settleSetListByTopartyidAndType_jt", param);
		return list;
	}
	
	@Override
	public String settleSetListByTopartyidAndType_jtCount(Map param) {
		String count=this.getSqlMapClientTemplate().queryForObject("settleSetListByTopartyidAndTypeCount_jt", param).toString();
		return count;
	}

	@Override
	public String delete_jt(Map map) { // 删 实现类
        String msg = "ok";
        try {
            int i = getSqlMapClientTemplate().delete("iBatisDeleteSettleSet_jt", map);
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

	@Override
	public String selectJtCount(Map param) {
		List<Map> list = new ArrayList<Map>();
		String count=this.getSqlMapClientTemplate().queryForObject("iBatisSelectJtCount", param).toString();
		return count;
	}

	@Override
	public List settleStepSetList(Map param) {
		List list = new ArrayList();
		list = this.getSqlMapClientTemplate().queryForList("settleStepSetList", param);
		return list;
	}

	@Override
	public String settleStepSetListCount(Map param) {
		String count=this.getSqlMapClientTemplate().queryForObject("settleStepSetListCount", param).toString();
		return count;
	}

	@Override
	public String save_delete(Map map) { // 删 实现类
        String msg = "ok";
        try {
            int i = getSqlMapClientTemplate().delete("iBatisTableDelete", map);
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

}
