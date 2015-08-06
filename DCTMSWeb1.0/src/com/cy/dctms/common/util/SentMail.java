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
	
	private  String sendEmail;//发送邮件地址
	private  String sendEmailPassword;//发送邮件密码
	private  String sendSmtp;//邮件服务器SMTP地址
	/*  发送邮件 
	 *  @param str_to:收件人地址
	 *  @param str_title：邮件标题
	 *  @param str_content：邮件正文 */
	public  boolean send(String str_to,String str_title,String str_content) { 
		
		// str_content="<a href='www.163.com'>html元素</a>"; //for testing send html mail! 
		try { //建立邮件会话
			
			Properties props=new Properties(); //用来在一个文件中存储键-值对的，其中键和值是用等号分隔的， //存储发送邮件服务器的信息 
			props.put("mail.smtp.host","smtp.qq.com"); //同时通过验证 
			props.put("mail.smtp.auth","true"); //根据属性新建一个邮件会话 
			Session s=Session.getInstance(props); 
			s.setDebug(true); //有他会打印一些调试信息。
			//由邮件会话新建一个消息对象
			MimeMessage message=new MimeMessage(s); 
			//设置邮件
			InternetAddress from= new InternetAddress(sendEmail); //pukeyouxintest2@163.com 
			message.setFrom(from); //设置发件人的地址 
			// 
			// //设置收件人,并设置其接收类型为TO 
			InternetAddress to=new InternetAddress(str_to); //pukeyouxintest3@163.com 
			message.setRecipient(Message.RecipientType.TO, to); 
			//设置标题
			message.setSubject(str_title); //java学习 
			//设置信件内容
			// message.setText(str_content); //发送文本邮件 //你好吗？
			message.setContent(str_content, "text/html;charset=gbk"); //发送HTML邮件 //<b>你好</b><br><p>大家好</p> 
			//设置发信时间
			message.setSentDate(new Date()); 
			//存储邮件信息 
			message.saveChanges(); 
			//发送邮件 
			Transport transport=s.getTransport("smtp"); 
			//以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
			transport.connect(sendSmtp,sendEmail,sendEmailPassword); 
			//发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message,message.getAllRecipients());
			transport.close(); 
			return true;
			}catch (Exception e) {  
			return false;
		} 
	}
	
	/*  发送邮件 
	 *  @param str_to:收件人地址
	 *  @param str_title：邮件标题
	 *  @param str_content：邮件正文 */
	public  boolean sendImage(String str_to,String str_title,String str_content,String str_dir) { 
		
		// str_content="<a href='www.163.com'>html元素</a>"; //for testing send html mail! 
		try { //建立邮件会话
			Properties props=new Properties(); //用来在一个文件中存储键-值对的，其中键和值是用等号分隔的， //存储发送邮件服务器的信息 
			props.put("mail.smtp.host","smtp.qq.com"); //同时通过验证 
			props.put("mail.smtp.auth","true"); //根据属性新建一个邮件会话 
			Session s=Session.getInstance(props); 
			s.setDebug(true); //有他会打印一些调试信息。
			//由邮件会话新建一个消息对象
			MimeMessage message=new MimeMessage(s); 
			//设置邮件
			InternetAddress from= new InternetAddress(sendEmail); //pukeyouxintest2@163.com 
			message.setFrom(from); //设置发件人的地址 
			// 
			// //设置收件人,并设置其接收类型为TO 
			InternetAddress to=new InternetAddress(str_to); //pukeyouxintest3@163.com 
			message.setRecipient(Message.RecipientType.TO, to); 
			//设置标题
			message.setSubject(str_title); //java学习 
			//设置信件内容
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
			
			//设置发信时间
			message.setSentDate(new Date()); 
			//存储邮件信息 
			message.saveChanges(); 
			//发送邮件 
			Transport transport=s.getTransport("smtp"); 
			//以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
			transport.connect(sendSmtp,sendEmail,sendEmailPassword); 
			//发送邮件,其中第二个参数是所有已设好的收件人地址
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



