package cn.lmz.mike.common.mail;

import cn.lmz.mike.common.base.ArrayU;
import cn.lmz.mike.common.base.PropU;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.io.FileU;
import cn.lmz.mike.common.sec.SecurityU;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class MailU  {

	public static MailU mail = null;
	private static final Logger log = LoggerFactory.getLogger(MailU.class);

	private static String host=null;
	private static String username=null;	
	private static String password=null;

	public static final String MAIL_CONFIG="mail.properties";

	public static final String MAIL_FROM="MAIL_FROM";
	public static final String MAIL_TO="MAIL_TO";

	public static final String MAIL_HOST="mail.from.host";
	public static final String MAIL_USERNAME="mail.from.username";
	public static final String MAIL_PASSWORD="mail.from.password";

	public MailU(){}
	public MailU(String mail_from){
		setFrom(SecurityU.getDeValue(mail_from));
	}

	public static void setFrom(String mail_from){
		String[] froms = StrU.getArray(mail_from);
		host = froms[0];
		username = froms[1];
		password = froms[2];
	}

	public static MailU getInstance(){
		if(mail==null) {
			synchronized (MailU.class) {
				if(mail==null) {
					mail = new MailU();
					Map<String, String> map = PropU.getMap(FileU.getPath(MAIL_CONFIG));
					if (map != null) {
						host = map.get(MAIL_HOST);
						username = map.get(MAIL_USERNAME);
						password = map.get(MAIL_PASSWORD);
					}
				}
			}
		}
		return mail;
	}
	public static MailU getInstance(String mail_from){
		if(mail==null) {
			synchronized (MailU.class) {
				if(mail==null) {
					mail = new MailU();
					mail.setFrom(mail_from);
				}
			}
		}
		return mail;
	}

	public static void send(String title,String content,String mailReceiver) throws Exception{
		try{
			Properties prop=new Properties();
			prop.setProperty("mail.host", host);
			//prop.setProperty("mail.smtp.port", "25");
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");

			// 开启SSL加密，否则会失败
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.socketFactory", sf);

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
			log.info("success:"+ ArrayU.toString(tos));
		 }catch(Exception e){
			 log.error(e.getMessage(), e);
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

	public static void main(String[] args) {

		String mailfrom="smtp.qq.com|295925348@qq.com|dvrdhuheexzhybhah";
		MailU mail = MailU.getInstance(mailfrom);
		try {
			mail.send("test","ttttttttttttsd","281247137@qq.com");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
