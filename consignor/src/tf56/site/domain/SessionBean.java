package tf56.site.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author wjj
 * 用户定义session信息
 */
public class SessionBean implements Serializable
{
	/**
	 * 
	 * session.setAttribute("operatorid", map.get("operatorid").toString());
		session.setAttribute("partyname", map.get("partyname").toString());
		session.setAttribute("partyid", map.get("partyid").toString());
		session.setAttribute("mobilenumber", map.get("mobilenumber").toString());
		session.setAttribute("email", map.get("email").toString());
		session.setAttribute("partytype", map.get("partytype").toString());
		session.setAttribute("password", map.get("password").toString());
		session.setAttribute("operator", map.get("operator").toString());
		session.setAttribute("realname", map.get("realname").toString());
		session.setAttribute("securitypermission", map.get("securitypermission").toString());
		session.setAttribute("oldpartyid", map.get("oldpartyid").toString());
		session.setAttribute("accountNumber", map.get("accountNumber").toString());
		session.setAttribute("partyrelationship", map.get("partyrelationship"));
		session.setAttribute("tradetype", map.get("tradetype"));
	 */

	private String operatorid;
	private String partyname;
	private String partyid;
	private String mobilenumber;
	private String email;
	private String partytype;
	private String password;
	private String operator;
	private String realname;
	private String securitypermission;
	private String oldpartyid;
	private String accountNumber;
	private List partyrelationship;
	private String tradetype;
	private Map car;
	
	private static final long serialVersionUID = -3673259816255517899L;
	
	
	public String getOperatorid() {
		return operatorid;
	}



	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}



	public String getPartyname() {
		return partyname;
	}



	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}



	public String getPartyid() {
		return partyid;
	}



	public void setPartyid(String partyid) {
		this.partyid = partyid;
	}



	public String getMobilenumber() {
		return mobilenumber;
	}



	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPartytype() {
		return partytype;
	}



	public void setPartytype(String partytype) {
		this.partytype = partytype;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getOperator() {
		return operator;
	}



	public void setOperator(String operator) {
		this.operator = operator;
	}



	public String getRealname() {
		return realname;
	}



	public void setRealname(String realname) {
		this.realname = realname;
	}



	public String getSecuritypermission() {
		return securitypermission;
	}



	public void setSecuritypermission(String securitypermission) {
		this.securitypermission = securitypermission;
	}



	public String getOldpartyid() {
		return oldpartyid;
	}



	public void setOldpartyid(String oldpartyid) {
		this.oldpartyid = oldpartyid;
	}



	public String getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}



	public List getPartyrelationship() {
		return partyrelationship;
	}



	public void setPartyrelationship(List partyrelationship) {
		this.partyrelationship = partyrelationship;
	}



	public String getTradetype() {
		return tradetype;
	}



	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}



	public Map getCar() {
		return car;
	}



	public void setCar(Map car) {
		this.car = car;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public SessionBean(String operatorid, String partyname, String partyid,
			String mobilenumber, String email, String partytype,
			String password, String operator, String realname,
			String securitypermission, String oldpartyid, String accountNumber,
			List partyrelationship, String tradetype, Map car) {
		super();
		this.operatorid = operatorid;
		this.partyname = partyname;
		this.partyid = partyid;
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.partytype = partytype;
		this.password = password;
		this.operator = operator;
		this.realname = realname;
		this.securitypermission = securitypermission;
		this.oldpartyid = oldpartyid;
		this.accountNumber = accountNumber;
		this.partyrelationship = partyrelationship;
		this.tradetype = tradetype;
		this.car = car;
	}


	public SessionBean(String operatorid, String partyname, String partyid,
			String mobilenumber, String email, String partytype,
			String password, String operator, String realname,
			String securitypermission, String oldpartyid, String accountNumber,
			List partyrelationship, String tradetype) {
		super();
		this.operatorid = operatorid;
		this.partyname = partyname;
		this.partyid = partyid;
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.partytype = partytype;
		this.password = password;
		this.operator = operator;
		this.realname = realname;
		this.securitypermission = securitypermission;
		this.oldpartyid = oldpartyid;
		this.accountNumber = accountNumber;
		this.partyrelationship = partyrelationship;
		this.tradetype = tradetype;
	}


	
	public SessionBean(){
		super();
	}
	
	
}