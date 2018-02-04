package cn.lmz.mike.common.mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.lmz.mike.common.base.ArrayU;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.sec.SecurityU;

public class MailU  {
	
	private static String host=null;
	private static String username=null;	
	private static String password=null;

	public MailU(){}
	public MailU(String mail_from){
		setFrom(SecurityU.getDeValue(mail_from));
	}

	public void setFrom(String mail_from){
		String[] froms = StrU.getArray(mail_from);
		host = froms[0];
		username = froms[1];
		password = froms[2];
	}
	

	public static void send(String title,String content,String mailReceiver) throws Exception{
		try{
			Properties prop=new Properties();
			prop.setProperty("mail.host", host);
			prop.setProperty("mail.smtp.port", "25");
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			Session session=Session.getInstance(prop);
			session.setDebug(false);
			Transport ts=session.getTransport();
			ts.connect(host,username,password);
			
			String[] tos = StrU.getArray(mailReceiver);

			for(int i = 0 ;i<tos.length;i++){
				Message message = createEmail(title,session,content,tos[i]);
				ts.sendMessage(message, message.getAllRecipients());
			}
			ts.close();
			O.info("success:"+ ArrayU.toString(tos));
		 }catch(Exception e){
			 O.error(e.getMessage(), e);
			 throw e;
		}
	}
	

	public static Message createEmail(String title,Session session,String content,String receiver)throws Exception{
		MimeMessage message=new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
		message.setSubject(title+"  "+getDate(new Date(), "yyyy/MM/dd"));
		message.setContent(content, "text/html;charset=utf-8");
		message.saveChanges();
		return message;
	}

	public static String getDate(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

}
