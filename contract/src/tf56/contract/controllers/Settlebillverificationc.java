package tf56.contract.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.log4j.Logger;

import tf56.contract.domain.SettleBillVerification;
import tf56.contract.services.SettleBillVerificationDao;
import tf56.contract.util.GetFormatDate;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

/**
 * @author wei.huang
 * @date 2013-11-13
 */
public class Settlebillverificationc extends RestController {
	private final Logger log = Logger.getLogger("Settlebillverificationc.java");
	/**
	 * @author wei.huang
	 * @date 2013-11-21
	 * @function 新增核销发票
	 * @throws IOException
	 */
	@POST
	public String insert() throws IOException{
        Map formMap =this.getParams();
        formMap = SysUtil.removeFilter(formMap);
        
        HttpServletRequest request = this .getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        
        String currentDate=GetFormatDate.getCurrentDate();
        String realname=sessionBean.getRealname();
        String msgJson="ok";
        for(int i=0,len=formMap.size();i<len;i++){
            String billInf=formMap.get("bill"+i).toString();
            String[] singleBill=billInf.split(",");
            Map billMap=new HashMap();
            billMap.put("settlebillid", singleBill[0]);
            billMap.put("billcode", singleBill[1]);
            billMap.put("billnumber", singleBill[2]);
            billMap.put("billamount", singleBill[3]);
            billMap.put("billman", singleBill[4]);
            billMap.put("billdate", singleBill[5]);
            if(singleBill.length!=7){
            	billMap.put("planpaydate", "");
            }else{
            	billMap.put("planpaydate", singleBill[6]);
            }
            billMap.put("inputdate",currentDate);
            billMap.put("inputman",realname);
            SettleBillVerification settleBillVerification = new SettleBillVerification();  //把map转成bean对象
            ParseFormToBean pftb = new ParseFormToBean();
            settleBillVerification = (SettleBillVerification) pftb.parseToBean(billMap, settleBillVerification);//将map转为bean对象
            SettleBillVerificationDao settleBillVerificationDao = (SettleBillVerificationDao) SofaSpringContext.getBean("settleBillVerificationDao"); //调用接口(实现类)
            String msg=settleBillVerificationDao.insert(settleBillVerification);
            if(!msg.contains("ok")){
                msgJson="sorry";
                break;
            }
        }
        HttpServletResponse response=this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(msgJson));
    }
	/**
	 * @author wei.huang
	 * @date 2013-11-21
	 * @function 获取发票列表
	 * @throws IOException
	 */
	@POST
	public String selectList() throws IOException{
		Map formMap =this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		SettleBillVerificationDao settleBillVerificationDao = (SettleBillVerificationDao) SofaSpringContext.getBean("settleBillVerificationDao"); //调用接口(实现类)
		String msgJson=settleBillVerificationDao.selectList(formMap.get("settlebillid").toString());  //调用Dao同名方法
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-19
	 * @function 跳转到发票应收核销页面
	 */
	@GET
	public void inVerification(){
		
	}
	/**
	 * @author wei.huang
	 * @date 2013-12-19
	 * @function 跳转到发票应付核销页面
	 */
	@GET
	public void outVerification(){
		
	}
}
