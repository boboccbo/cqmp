package com.airchina.cqmp.core.email;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * 处理内容:发送邮件工具类
 * @version: 1.0
 * @see:net.uni.ap.email.EmailUtil.java
 * @date:2012-5-21
 * @author:sunjiaxiao
 */
public class EmailUtil {

	/**
	 * @param mailAddress 接收邮件地址
	 * @param title  标题
	 * @param textType  内容类别"html"或者正常文本
	 * @param text   内容,根据textType判断
	 * @return
	 * @方法说明 发送邮件方法
	 * @date 2012-5-21
	 * @author sunjiaxiao
	 */
	public static String sendEmail(EmailObject emailObj){
		//建立邮件会话
		Properties props=new Properties();
		//存储发送邮件服务器的信息
		props.put("mail.smtp.host",(emailObj.getMailServer()==null ? EmailConStant.MAIL_SERVER :emailObj.getMailServer()));
		//同时通过验证
		props.put("mail.smtp.auth","true");
		//根据属性新建一个邮件会话
		Session s= Session.getInstance(props);
		s.setDebug(true); //有他会打印一些调试信息。

		//由邮件会话新建一个消息对象
		MimeMessage message=new MimeMessage(s);
		//设置邮件
		try {
			InternetAddress from = new InternetAddress((emailObj.getMailCount()==null ? EmailConStant.MAIL_COUNT : emailObj.getMailCount()));
			message.setFrom(from);


			InternetAddress[] tos = new InternetAddress[emailObj.getMailAddresses().length];
			// //设置收件人,并设置其接收类型为TO
			for (int i = 0;i< emailObj.getMailAddresses().length;i++){
				tos[i] = new InternetAddress(emailObj.getMailAddresses()[i]);
			}
			message.setRecipients(Message.RecipientType.TO, tos);
			message.setSubject(emailObj.getTitle());
			//设置发信时间
			message.setSentDate(new Date());
			//存储邮件信息
			message.saveChanges();

			//发送邮件
			Transport transport=s.getTransport("smtp");
			//以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
			transport.connect((emailObj.getMailServer()==null ? EmailConStant.MAIL_SERVER :emailObj.getMailServer())
					,(emailObj.getMailCount()==null ? EmailConStant.MAIL_COUNT : emailObj.getMailCount())
					,(emailObj.getMailPassword()==null ? EmailConStant.MAIL_PASSWORD : emailObj.getMailPassword()));
			Multipart mp = new MimeMultipart();
			MimeBodyPart bmp = new MimeBodyPart();

			if (emailObj.getTextType()!=null&&emailObj.getTextType().equals("html"))
				bmp.setContent(emailObj.getText(),"text/html;charset=gbk");
			else
				bmp.setText(emailObj.getText());
			mp.addBodyPart(bmp);
			
			
			//发送附件
			/*if(emailObj.getFiles()!=null&&emailObj.getFiles().length>0){
				for(File fs:emailObj.getFiles()){
					if(fs.exists()){
	                    bmp=new MimeBodyPart();
						FileDataSource fds=new FileDataSource(BaseExcelConstants.UPLOADPATH+"/"+fs.getName());
						bmp.setDataHandler(new DataHandler(fds)); 
						String newName =fs.getName().substring(0, fs.getName().lastIndexOf("-"))+fs.getName().substring(fs.getName().lastIndexOf("."));
						//名字需要先转为ISO 再转为GB2312 否则邮件内会显示乱码
						newName =new String(newName.getBytes("gb2312"),"ISO8859-1");
						bmp.setFileName(newName);
						mp.addBodyPart(bmp);
					}
				}
			}*/
			emailObj.setFiles(null);
			message.setContent(mp);
			message.saveChanges();
			//发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message,message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String[] strs = {"sunjiaxiao@sinosoft.com.cn"};
		EmailObject obj = new EmailObject();
		obj.setMailAddresses(strs);
		obj.setTitle("测试");
		obj.setTextType("text");
		obj.setText("哈哈哈");
		sendEmail(obj);
	}
}
