/**
 * @athor changmeng.liu
 * @date 2014-4-17
 * @version 1.0
 * @fileName  ResultCode.java
 * @update 
 */
package tf56.contract.util;

/**
 * 总包枚举类
 * @athor changmeng.liu
 * @date 2014-4-17
 * @version 1.0
 * @update 
 */
/**
 * 
 * 外部系统提交返回代码
 * @athor changmeng.liu
 * @date 2014-4-17
 * @version 1.0
 * @update
 */
public class ContractEnums {

	public enum ResultCode{
	SUCCESS(200,"成功"),
	ERROR(500,"失败"),
	ERR_CLIENTNUMBER_NULL(501,"客户单号为空"),
	ERR_NOTFOUND_PARTYID(502,"找不到对应的会员ID"),
	ERR_SAVE_FAILT(503,"保存失败"),
	ERR_CLIENTNUMBER_EXISTS(504,"客户单号已存在");//且该单已确认
	private int value;
	private String desc;

	private ResultCode(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	public Integer getOrdinal() {
		
		return ordinal();
	}

	public String getName() {
		return name();
	}
	}
}
