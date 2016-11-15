package com.airchina.cqmp.core.email;
import java.io.File;

/**
 * 处理内容:邮件对象类
 * @version: 1.0
 * @see:net.uni.ap.email.EmailObject.java
 * @date:2012-11-8
 * @author:sunjiaxiao
 */
public class EmailObject {
	String[] mailAddresses;//邮件地址
	String title;	//标题
	String textType;	//文本类别。"html" 为html 其他为普通文本
	String text;		//文本内容
	File[] files;	//附件列表
	
	
	String mailServer;//手动设置发件邮箱服务器
	String mailCount;//手动设置发件邮箱和密码
	String mailPassword;//密码


	public String[] getMailAddresses() {
		return mailAddresses;
	}
	public void setMailAddresses(String[] mailAddresses) {
		this.mailAddresses = mailAddresses;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTextType() {
		return textType;
	}
	public void setTextType(String textType) {
		this.textType = textType;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public File[] getFiles() {
		return files;
	}
	public void setFiles(File[] files) {
		this.files = files;
	}
	public String getMailServer() {
		return mailServer;
	}
	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}
	public String getMailCount() {
		return mailCount;
	}
	public void setMailCount(String mailCount) {
		this.mailCount = mailCount;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}


}