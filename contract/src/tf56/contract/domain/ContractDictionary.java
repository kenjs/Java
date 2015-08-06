package tf56.contract.domain;
import tf56.sofa.util.SysUtil;

public class ContractDictionary {  //
	private String contractdictionaryid; //
	private String type; //
	private String text; //
	private String description; //
	private String inputdate; //输入时间
	private String updatedate; //
	public String getContractdictionaryid() {
		if (contractdictionaryid!=null) {if (new SysUtil().isInt(contractdictionaryid)==false) contractdictionaryid=null;}
		return contractdictionaryid;
	}
	public void setContractdictionaryid(String contractdictionaryid) {
		if (contractdictionaryid!=null) {if (new SysUtil().isInt(contractdictionaryid)==false) contractdictionaryid=null;}
		this.contractdictionaryid = contractdictionaryid;
	}
	public String getType() {
		if (type!=null) {if ("".equals(type)) type=null;}
		return type;
	}
	public void setType(String type) {
		if (type!=null) {if ("".equals(type)) type=null;}
		this.type = type;
	}
	public String getText() {
		if (text!=null) {if ("".equals(text)) text=null;}
		return text;
	}
	public void setText(String text) {
		if (text!=null) {if ("".equals(text)) text=null;}
		this.text = text;
	}
	public String getDescription() {
		if (description!=null) {if ("".equals(description)) description=null;}
		return description;
	}
	public void setDescription(String description) {
		if (description!=null) {if ("".equals(description)) description=null;}
		this.description = description;
	}
	public String getInputdate() {
		if (inputdate!=null) {if ("".equals(inputdate)) inputdate=null;}
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		if (inputdate!=null) {if ("".equals(inputdate)) inputdate=null;}
		this.inputdate = inputdate;
	}
	public String getUpdatedate() {
		if (updatedate!=null) {if ("".equals(updatedate)) updatedate=null;}
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		if (updatedate!=null) {if ("".equals(updatedate)) updatedate=null;}
		this.updatedate = updatedate;
	}
}
