package com.cy.dctms.common.util;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SentMail { 
	
	private  String sendEmail;//�����ʼ���ַ
	private  String sendEmailPassword;//�����ʼ�����
	private  String sendSmtp;//�ʼ�������SMTP��ַ
	/*  �����ʼ� 
	 *  @param str_to:�ռ��˵�ַ
	 *  @param str_title���ʼ�����
	 *  @param str_content���ʼ����� */
	public  boolean send(String str_to,String str_title,String str_content) { 
		
		// str_content="<a href='www.163.com'>htmlԪ��</a>"; //for testing send html mail! 
		try { //�����ʼ��Ự
			
			Properties props=new Properties(); //������һ���ļ��д洢��-ֵ�Եģ����м���ֵ���õȺŷָ��ģ� //�洢�����ʼ�����������Ϣ 
			props.put("mail.smtp.host","smtp.qq.com"); //ͬʱͨ����֤ 
			props.put("mail.smtp.auth","true"); //���������½�һ���ʼ��Ự 
			Session s=Session.getInstance(props); 
			s.setDebug(true); //�������ӡһЩ������Ϣ��
			//���ʼ��Ự�½�һ����Ϣ����
			MimeMessage message=new MimeMessage(s); 
			//�����ʼ�
			InternetAddress from= new InternetAddress(sendEmail); //pukeyouxintest2@163.com 
			message.setFrom(from); //���÷����˵ĵ�ַ 
			// 
			// //�����ռ���,���������������ΪTO 
			InternetAddress to=new InternetAddress(str_to); //pukeyouxintest3@163.com 
			message.setRecipient(Message.RecipientType.TO, to); 
			//���ñ���
			message.setSubject(str_title); //javaѧϰ 
			//�����ż�����
			// message.setText(str_content); //�����ı��ʼ� //�����
			message.setContent(str_content, "text/html;charset=gbk"); //����HTML�ʼ� //<b>���</b><br><p>��Һ�</p> 
			//���÷���ʱ��
			message.setSentDate(new Date()); 
			//�洢�ʼ���Ϣ 
			message.saveChanges(); 
			//�����ʼ� 
			Transport transport=s.getTransport("smtp"); 
			//��smtp��ʽ��¼����,��һ�������Ƿ����ʼ��õ��ʼ�������SMTP��ַ,�ڶ�������Ϊ�û���,����������Ϊ����
			transport.connect(sendSmtp,sendEmail,sendEmailPassword); 
			//�����ʼ�,���еڶ�����������������õ��ռ��˵�ַ
			transport.sendMessage(message,message.getAllRecipients());
			transport.close(); 
			return true;
			}catch (Exception e) {  
			return false;
		} 
	}
	
	/*  �����ʼ� 
	 *  @param str_to:�ռ��˵�ַ
	 *  @param str_title���ʼ�����
	 *  @param str_content���ʼ����� */
	public  boolean sendImage(String str_to,String str_title,String str_content,String str_dir) { 
		
		// str_content="<a href='www.163.com'>htmlԪ��</a>"; //for testing send html mail! 
		try { //�����ʼ��Ự
			Properties props=new Properties(); //������һ���ļ��д洢��-ֵ�Եģ����м���ֵ���õȺŷָ��ģ� //�洢�����ʼ�����������Ϣ 
			props.put("mail.smtp.host","smtp.qq.com"); //ͬʱͨ����֤ 
			props.put("mail.smtp.auth","true"); //���������½�һ���ʼ��Ự 
			Session s=Session.getInstance(props); 
			s.setDebug(true); //�������ӡһЩ������Ϣ��
			//���ʼ��Ự�½�һ����Ϣ����
			MimeMessage message=new MimeMessage(s); 
			//�����ʼ�
			InternetAddress from= new InternetAddress(sendEmail); //pukeyouxintest2@163.com 
			message.setFrom(from); //���÷����˵ĵ�ַ 
			// 
			// //�����ռ���,���������������ΪTO 
			InternetAddress to=new InternetAddress(str_to); //pukeyouxintest3@163.com 
			message.setRecipient(Message.RecipientType.TO, to); 
			//���ñ���
			message.setSubject(str_title); //javaѧϰ 
			//�����ż�����
			MimeBodyPart mbp1= new MimeBodyPart();
		      mbp1.setContent(str_content,"text/html;charset=gbk");
		      FileDataSource fds = new FileDataSource(str_dir);
		      MimeBodyPart mbp2 = new MimeBodyPart();
		      mbp2.setFileName(fds.getName());
		      mbp2.setText("This is a beautiful car !");
		      mbp2.setDataHandler(new DataHandler(fds));
		      mbp2.setHeader("Content-ID",fds.getName());
		      MimeMultipart mp = new MimeMultipart("related");
		      mp.addBodyPart(mbp1); 
		      mp.addBodyPart(mbp2);
		      message.setContent(mp);
			
			//���÷���ʱ��
			message.setSentDate(new Date()); 
			//�洢�ʼ���Ϣ 
			message.saveChanges(); 
			//�����ʼ� 
			Transport transport=s.getTransport("smtp"); 
			//��smtp��ʽ��¼����,��һ�������Ƿ����ʼ��õ��ʼ�������SMTP��ַ,�ڶ�������Ϊ�û���,����������Ϊ����
			transport.connect(sendSmtp,sendEmail,sendEmailPassword); 
			//�����ʼ�,���еڶ�����������������õ��ռ��˵�ַ
			transport.sendMessage(message,message.getAllRecipients());
			transport.close(); 
			return true;
			}catch (Exception e) {  
			return false;
		} 
	}
	
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public void setSendEmailPassword(String sendEmailPassword) {
		this.sendEmailPassword = sendEmailPassword;
	}
	public void setSendSmtp(String sendSmtp) {
		this.sendSmtp = sendSmtp;
	}
	public String getSendEmail() {
		return sendEmail;
	}
	public String getSendEmailPassword() {
		return sendEmailPassword;
	}
	public String getSendSmtp() {
		return sendSmtp;
	}
}  



