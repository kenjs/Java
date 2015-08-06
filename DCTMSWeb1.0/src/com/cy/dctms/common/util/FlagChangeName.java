package com.cy.dctms.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * ��־ת����
 * @author WJL
 *
 */
public class FlagChangeName {
	//��˱�־
	public static String auditFlag(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "�����";
		}else if ("1".equals(src)) {
			result = "ͨ��";
		}else if ("-1".equals(src)) {
			result ="δͨ��";
		}
		return result;
	}
	//��ҵ��֤��־
	public static String enterpriseFlag(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "δ��֤";
		}else if ("1".equals(src)) {
			result = "����֤";
		}
		return result;
	}
	//��ǰ��Ӫ״̬
	public static String carStateType(String src){
		String result ="";
		if ("1".equals(src)) {
			result = "���";
		}else if ("2".equals(src)) {
			result = "����";
		}else if ("3".equals(src)) {
			result ="��Ϣ";
		}
		return result;
	}
	//�����û���־
	public static String newOrOldAppUser(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "���û�";
		}else if ("1".equals(src)) {
			result = "���û�";
		}
		return result;
	}
	//�û���ԴUSER_ORIGIN
	public static String userOrigin(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "�Լ�ע��";
		}else if ("1".equals(src)) {
			result = "�����";
		}
		return result;
	}
	//����
	public static String assess(String src){
		String result ="";
		if ("3".equals(src)) {
			result = "����";
		}else if ("6".equals(src)) {
			result = "����";
		}else if("9".equals(src)){
			result = "����";
		}
		return result;
	}
	
	//����״̬
	public static String tradeState(String src){
		String result ="";
		if ("1".equals(src)) {
			result = "�ȴ�˾��ȷ��";
		}else if ("3".equals(src)) {
			result = "�������";
		}else if ("5".equals(src)) {
			result = "�������";
		}else if ("6".equals(src)) {
			result = "����ȡ��";
		}else if ("7".equals(src)) {
			result = "˾����ж��";
		}
		return result;
	}
	
	//����״̫
	public static String orderState(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "��Ч";
		}else if ("1".equals(src)) {
			result = "��Ч";
		}
		return result;
	}
	//����ȡ����Դ
	public static String orderCancelOrigin(String src){
		String result ="";
		if ("1".equals(src)) {
			result = "˾��ȡ��";
		}else if ("2".equals(src)) {
			result = "����ȡ��";
		}
		else if ("3".equals(src)) {
			result = "ȫȡ��";
		}
		return result;
	}
	
	//����״̬
	@SuppressWarnings("deprecation")
	public static String cargoFlag(String src,String requestTime){
		String result ="";
		if ("1".equals(src)) {
			result = "������";
		}else if ("2".equals(src)) {
			result = "�ɹ�";
		}else if("0".equals(src)){
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date endTime = null;
			try {
				endTime = df.parse(requestTime);
			} catch (ParseException e) {
				return "";
			}
			//�Ƚ�ʱ��
			if (endTime.getTime()>new Date().getTime()) {
				result = "������";
			}else {
				result = "ʧЧ";
			}
			
		}
		return result;
	}
	
	//��������
	public static String cargoType(String src){
		String result ="";
		if ("1".equals(src)) {
			result = "�ػ�";
		}else if ("2".equals(src)) {
			result = "�ݻ�";
		}
		return result;
	}
	
	//��˽��
	public static String submitType(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "δ�ύ";
		}else if ("1".equals(src)) {
			result = "���ύ";
		}
		else if ("2".equals(src)) {
			result = "δͨ��";
		}
		else if ("3".equals(src)) {
			result = "��ͨ��";
		}
		return result;
	}
	//'ע���û����ͣ�������ҵ0��������1���ջ���2��',
	public static String userType(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "������ҵ";
		}else if ("1".equals(src)) {
			result = "������";
		}
		else if ("2".equals(src)) {
			result = "�ջ���";
		}
		return result;
	}
	//'���빦������(0�������湦�ܡ�1�����֤��ѯ���ܡ�2��VIP����)',
	public static String applyType(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "�����湦��";
		}else if ("1".equals(src)) {
			result = "���֤��ѯ";
		}
		else if ("2".equals(src)) {
			result = "VIP����";
		}
		return result;
	}
	//'���״̬��0�ȴ���ˡ�-1���ʧ�ܡ�1��˳ɹ���',
	public static String verifyStart(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "�ȴ����";
		}else if ("-1".equals(src)) {
			result = "���ʧ��";
		}
		else if ("1".equals(src)) {
			result = "��˳ɹ�";
		}
		return result;
	}

}
