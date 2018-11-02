package com.bbs.controller.common.tools;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	
	/*private static MailSender singleton = null;
	 
    private MailSender(){}
    public static MailSender getSingleton() {
        if(singleton == null){ 
        	singleton = new MailSender();
        }
        return singleton;
    }*/

	/**
	 * 发送文本邮件
	 * @throws MessagingException 
	 * 
	 * @throws Exception
	 */
	public void sendMail(String toAddress,String content){
		Transport transport =null;
		try {
			Properties props = new Properties();
			// 基本的邮件会话
			Session session = Session.getInstance(props);
			// 启用调试模式
			 session.setDebug(true);
			// 构建一封邮件
			Message msg = new MimeMessage(session);

			// -----------------------构建邮件的核心四要素-----------------------------------
			// 构建发送者地址
			Address fromAddres = new InternetAddress("v_wanchanghuang@163.com");//"发送者邮件地址"
			// 设置发送者邮箱地址
			msg.setFrom(fromAddres);

			// 设置接受邮箱地址[Message.RecipientType.TO 正常发送，Message.RecipientType.CC
			// 抄送，Message.RecipientType.BCC 密送]
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));//"接受邮件地址"

			// 构建邮件正文和主题
			// 设置主题
			msg.setSubject("武汉青山海伦社区系统邮件");//【重要】JavaMail邮件测试
			// 设置内容
			msg.setText(content);//"你好，javaMail邮件测试。"
			// -----------------------构建邮件的核心四要素-----------------------------------

			// 设置邮件有那台服务器发送
			// 创建邮件传输者
			transport = session.getTransport("smtp");
			// 连接服务器
			transport.connect("smtp.163.com","v_wanchanghuang@163.com","wan1989n7y4r");//("smtp.163.com", "发送邮件的邮件地址", "发送邮件的密码");

			// 将邮件的正文添加到发送的服务器中
			// 发送邮件
			transport.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(transport!=null){
					System.out.println("进入邮箱发送关闭。。。 ");
				  transport.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MailSender mes=new MailSender();
		mes.sendMail("1033074035@qq.com", "测试内容");
	}
	
}
