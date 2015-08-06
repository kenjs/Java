package tf56.contract.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.log4j.Logger;

import tf56.contract.util.AuthImage;
import tf56.contract.util.ReadPropertiesFile;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;

public class Loginc extends RestController{
	
	public static Logger logger = Logger.getLogger(Loginc.class); 
	
	
	/**
	 * @author wusong
	 */
	
	@GET
	public void login(){//打开登录页面
		logger.debug("打开登录页面");
	}
	/**
	 * @author wusong
	 * @description 用户退出清空session
	 */
	@GET
	public void logout(){//
		clearSession();
	}
	
	@GET
	public void authImage(){//验证码
		AuthImage authImage = new AuthImage();
		HttpServletRequest request = this.getResponseHelper().getRequest();
		HttpServletResponse response = this.getResponseHelper().getResponse();
		try {
			authImage.service(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author wusong
	 * @description 根据权限定位到不同页面
	 */
	@GET
	public void login_main(){//打开登录后内部页面 
		
		//根据权限跳转到相应页面
		try {
			this.renderPage("main_partycenter.html");
//			this.renderPage("main_3pl.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @author hkf
	 * @throws IOException 
	 * @description 新入口根据权限定位到不同页面
	 */
	@GET
	public void index_main() throws IOException{//打开登录后内部页面 
		
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    if(SessionUtil.sessionExists(request)){//memcached中有session
            //取memcached中的session
            SessionBean sessionBean = SessionUtil.getSession(request);
            if(sessionBean == null){
                
            }
            String partyid = sessionBean.getPartyid() == null ? "" : (String) sessionBean.getPartyid();
            
            if(sessionBean.getPartyname()!=null){
                this.renderPage("index_contract.html");
            }
        }
	}
	

	
	/**
	 * @author wusong
	 * @function 登录成功后初始跳转页面
	 * @date 2012-12-23
	 */
	@GET
	public String getInit(){
	    
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
	    
		String partyname;
		String operator;
		String partytype;
		String tradetype;
		String username_part = "";
		String username_all = "";
		String realname="";
		String email = "";
		String mobilenumber = "";
		if(sessionBean.getPartyname()==null){
			partyname="";
			operator="";
			partytype="";
			tradetype = "";
		}else{
			partyname = sessionBean.getPartyname().toString();
			operator = sessionBean.getOperator().toString();
			partytype = sessionBean.getPartytype().toString();
			tradetype = sessionBean.getTradetype().toString();
			realname = sessionBean.getRealname().toString();
			email = sessionBean.getEmail().toString();
			mobilenumber = sessionBean.getMobilenumber().toString();
			if(!"admin".equals(operator)){//如果是非主帐号登录
				username_all = partyname+"/"+operator;
				//取前6位字符
				partyname = partyname.length()>6?partyname.substring(0, 6)+"..":partyname;
				//取前4位字符
				operator = operator.length()>4?operator.substring(0, 4)+"..":operator;
				username_part = partyname+"/"+operator+"";
			}else{//主帐号登录，隐藏admin操作员
				//取前10位字符
				username_part = partyname.length()>10?partyname.substring(0, 10)+"..":partyname;
				username_all = partyname+"";
			}
		}
		
		String str = JsonGenerateUtil.getSelfDefinedJson("{\"username_part\":\""+username_part+"\",\"username_all\":\""+username_all+"\",\"partytype\":\""+partytype+"\",\"realname\":\""+realname+"\",\"email\":\""+email+"\",\"mobilenumber\":\""+mobilenumber+"\",\"tradetype\":\""+tradetype+"\"}").replace("\\", "");
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	/**
	 * @author hcm
	 * @function 取用户角色
	 * @date 2012-1-7
	 */
	@GET
	public String getJsInit(){
	    
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
	    
		String operator;
		String jsStr;
		if(sessionBean.getPartyname()==null){
			operator="";
			jsStr="";
		}else{
			operator = sessionBean.getOperator().toString();
			String js = sessionBean.getSecuritypermission();
			jsStr="";
			if(js!=null&&!("".equals(js))){
				List<Map<String, Object>> list = Json2ObjectUtil.parseJSON2List(js);
				if(list.size()>0){
					Map<String, Object> temp = list.get(0);
					jsStr = temp.get("SecurityPermission").toString();
				}
			}
		}
		String str = JsonGenerateUtil.getSelfDefinedJson("{\"jsStr\":\""+jsStr+"\",\"operator\":\""+operator+"\"}").replace("\\", "");
		//String str = JsonGenerateUtil.getSelfDefinedJson("{\"operator\":\""+operator+"\"}").replace("\\", "");
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	
	/**@author wusong
	 * @descript:清空session
	 * @param map
	 */
	private void clearSession(){
	  //让cookie和session失效
        HttpServletRequest request = this.getResponseHelper().getRequest();
        HttpServletResponse response = this.getResponseHelper().getResponse();
        SessionUtil.invalidateSession(request, response);
		
	}
	
	/**@author wusong
	 * @function 打开3pl主界面
	 * @date 2013-01-16
	 */
	@GET
	public void main_3pl(){
		
	}
	/**@author wusong
	 * @function 打开帐号管理主界面
	 * @date 2013-01-16
	 */
	@GET
	public void main_partycenter(){
		
		
	}
	/**@author wusong
	 * @function 打开我要发布主界面
	 * @date 2012-12-25
	 */
	@GET
	public void main_goodssource(){
		
	}
	
	
	/**@author wei.huang
	 * @function 获取登录的会员id和name
	 * @date 2013-10-11
	 */
	@POST
	public String getIdAndName(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		Map map=new HashMap();
		map.put("partyid", sessionBean.getPartyid());
		map.put("realname", sessionBean.getRealname());
		String str=JsonGenerateUtil.map2json(map);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	
	 /**
     * 从配置文件获取其他系统的ip
     * @author donghui.wang
     * @since 2013-12-18
     * @return
     */
    @GET
    public String getMainIp(){
        Map resultmap = new HashMap();
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        if(sessionBean == null){
            resultmap.put("islogin", "no");
        }else{
            resultmap.put("islogin", "yes");
        }
        Map paramsMap = this.getParams();
        //从配置文件中读取ip
        ReadPropertiesFile rp=new ReadPropertiesFile();
        String contractip=rp.getString("config/application", "siteip");
        //转成Json返回前台页面
        
        resultmap.put("siteip", contractip);
        String ip = JsonGenerateUtil.map2json(resultmap);
        
        HttpServletResponse response = this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, ip);
    }
	
}

