package tf56.exchange.controllers;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import net.sf.serfj.annotations.GET;
import tf56.exchange.services.InternalWriteScoreDao;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
public class Internalwritescorec extends RestController{
	//增 必须把获取的map转为bean对数据库进行操作
	@POST
	public String insert() throws IOException{
		Map formMap =this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		//InternalWriteScore internalWriteScore = new InternalWriteScore();		//把map转成bean对象
		//ParseFormToBean pftb = new ParseFormToBean();
		//internalWriteScore = (InternalWriteScore) pftb.parseToBean(formMap, internalWriteScore);//将map转为bean对象
		InternalWriteScoreDao internalWriteScoreDao = (InternalWriteScoreDao) SofaSpringContext.getBean("internalWriteScoreDao"); //调用接口(实现类)
		String msgJson=internalWriteScoreDao.insert(formMap);  //调用Dao同名方法
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); //返回 Json格式: 自定义
	}	
	//查一条
	@POST
	public String selectByCPartyName(){
		Map formMap =this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		InternalWriteScoreDao internalWriteScoreDao = (InternalWriteScoreDao) SofaSpringContext.getBean("internalWriteScoreDao"); //调用接口(实现类)
		String msgJson=internalWriteScoreDao.selectById(formMap); //删除和查询的参数用map
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); //返回 Json格式: 自定义
	}
	//查多条
	@POST
	public String selectListScores(){
		Map formMap =this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		InternalWriteScoreDao internalWriteScoreDao = (InternalWriteScoreDao) SofaSpringContext.getBean("internalWriteScoreDao"); //调用接口(实现类)
		String msgJson=internalWriteScoreDao.selectListScores(formMap); //删除和查询的参数用map
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); //返回给 Json格式: 自定义
	}
	
}