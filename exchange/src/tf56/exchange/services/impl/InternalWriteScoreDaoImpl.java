package tf56.exchange.services.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.exchange.domain.CusPartyScores;
import tf56.exchange.services.InternalWriteScoreDao;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ParseFormToBean;

public class InternalWriteScoreDaoImpl extends SqlMapClientDaoSupport implements InternalWriteScoreDao{
	/**
	 * @author tlp
	 * @date 2013-06-06
	 * @function 插入积分
	 */
	public String insert(Map map){ //增 实现类
		String id="", msg="ok";
		//判断用户名参数是否传递过来
		if(null==map.get("cpartyname")||"".equals(map.get("cpartyname"))){
			return JsonGenerateUtil.getMsgJson("用户名不能为空!");
		}
		if(null==map.get("csource")||"".equals(map.get("csource"))){
			return JsonGenerateUtil.getMsgJson("来源不能为空!");
		}
		if(null==map.get("cid")||"".equals(map.get("cid"))){
			return JsonGenerateUtil.getMsgJson("流水号不能为空!");
		}
		try {
			String ipartyid=(String)this.getSqlMapClientTemplate().queryForObject("iBatisSelectInternalIpartyIdByCparty", map);

			if(null==ipartyid){
				return JsonGenerateUtil.getMsgJson("该用户不存在！");
			}
			map.put("ipartyid", ipartyid);
			//判断积分总表里面有没有这条记录
			Map pm=(Map)this.getSqlMapClientTemplate().queryForObject("iBatisSelectTCusPartyScoreByPartyID", map);
			//插入时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ddate=sdf.format(new Date());
			map.put("ddate", ddate);
			//构建Bean工厂对象
			CusPartyScores cusPartyScores=new CusPartyScores();
			ParseFormToBean pftb = new ParseFormToBean();
			

			//如果没有
			if(null==pm){
				double nin=Double.parseDouble(map.get("nin").toString());
				double nout=Double.parseDouble(map.get("nout").toString());
				//如果收入小于支出
				if(nin>nout){
					return JsonGenerateUtil.getMsgJson("您的积分不足！");
				}
				//插入积分总表
				map.put("nscore", (nin-nout));
				id=(String)getSqlMapClientTemplate().insert("iBatisInsertCusPartyScorebyNotInsert",map);
				//将插入总表返回的ID放入Map中
				map.put("id", id);
				map.put("nleft", (nin-nout));
				//插入积分详情表
				cusPartyScores = (CusPartyScores) pftb.parseToBean(map, cusPartyScores);//将map转为bean对象
				id=(String) getSqlMapClientTemplate().insert("iBatisInsertCusPartyScores",cusPartyScores);
			}else{
				double nin=Double.parseDouble(map.get("nin").toString());
				double nout=Double.parseDouble(map.get("nout").toString());
				double nscore=Double.parseDouble(pm.get("nscore").toString());
				double sum=nscore+nin-nout;
				//如果收入小于支出
				if(sum<0){
					return JsonGenerateUtil.getMsgJson("您的积分不足！");
				}
				//更新积分总表
				map.put("nscore", sum);
				map.put("nleft", sum);
				map.put("id", pm.get("id"));
				this.getSqlMapClientTemplate().update("iBatisUpdateCusPartyScores", map);
				//插入积分详情表
				cusPartyScores = (CusPartyScores) pftb.parseToBean(map, cusPartyScores);//将map转为bean对象
				id=(String) getSqlMapClientTemplate().insert("iBatisInsertCusPartyScores",cusPartyScores);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonGenerateUtil.getMsgJson("参数类型不匹配！");
		}
		String msgJson=JsonGenerateUtil.getMsgIdJson(msg, id);	
		return msgJson; //返回json到控制器, json格式:msg:ok|错误信息,id:int值
	}

	
	/**
	 * @author tlp
	 * @date 2013-06-06
	 * @function 查询积分
	 */
	public String selectById(Map map) { //查询数据到Bean,然后转成map
		String id="", msg="ok";
		Map pm=new HashMap<String, Object>();
		//判断用户名参数是否传递过来
		if(null==map.get("cpartyname")||"".equals(map.get("cpartyname"))){
			return JsonGenerateUtil.getMsgJson("用户名不能为空!");
		}
		try {
			
			String ipartyid=(String)this.getSqlMapClientTemplate().queryForObject("iBatisSelectInternalIpartyIdByCparty", map);

			if(null==ipartyid){
				return JsonGenerateUtil.getMsgJson("该用户不存在！");
			}
			map.put("ipartyid", ipartyid);
			//判断积分总表里面有没有这条记录
			pm=(Map)this.getSqlMapClientTemplate().queryForObject("iBatisSelectTCusPartyScoreByPartyID", map);
			//如果没有
			if(null==pm){
				//插入积分总表
				map.put("nscore", "0");
				id=(String)getSqlMapClientTemplate().insert("iBatisInsertCusPartyScorebyNotInsert",map);
				//构建返回参数
				pm.put("nscore", "0");
				pm.put("id", id);
			}
			pm.put("msg", msg);
		}catch(Exception e){
			e.printStackTrace();
			return JsonGenerateUtil.getMsgJson("参数类型不匹配！");
		}
		String msgJson=JsonGenerateUtil.map2json(pm);	
		return msgJson;
	}
	/**
	 * @author tlp
	 * @date 2013-06-06
	 * @function 查询积分积分明细
	 */
	public String selectListScores(Map map){
		String id="", msg="ok";
		//判断用户名参数是否传递过来
		if(null==map.get("cpartyname")||"".equals(map.get("cpartyname"))){
			return JsonGenerateUtil.getMsgJson("用户名不能为空!");
		}
		try {
			String ipartyid=(String)this.getSqlMapClientTemplate().queryForObject("iBatisSelectInternalIpartyIdByCparty", map);

			if(null==ipartyid){
				return JsonGenerateUtil.getMsgJson("该用户不存在！");
			}
			map.put("ipartyid", ipartyid);
			//判断积分总表里面有没有这条记录
			List<CusPartyScores> list=this.getSqlMapClientTemplate().queryForList("iBatisSelectCusPartyScoresList", map);
			//如果没有
			if(list.isEmpty()){
				return JsonGenerateUtil.getMsgJson("无积分明细信息！");
			}else{
				return JsonGenerateUtil.list2json(list);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonGenerateUtil.getMsgJson("参数类型不匹配！");
		}
	}
}