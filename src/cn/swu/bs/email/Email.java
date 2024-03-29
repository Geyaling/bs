package cn.swu.bs.email;
import java.io.UnsupportedEncodingException; 
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
 
public class Email {
	private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private String smtpServer; // SMTP服务器地址
	private String port; // 端口
	private String username; // 登录SMTP服务器的用户名
	private String password; // 登录SMTP服务器的密码
	private List<String> recipients = new ArrayList<String>(); // 收件人地址集合
	private String subject; // 邮件主题
	private String content; // 邮件正文
	public Email() {}
	public Email(String smtpServer, String port, String username,
			String password, List<String> recipients, String subject,
			String content) {
		this.smtpServer = smtpServer;
		this.port = port;
		this.username = username;
		this.password = password;
		this.recipients = recipients;
		this.subject = subject;
		this.content = content;
	}
 
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
 
	public void setPort(String port) {
		this.port = port;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
 
	public void setSubject(String subject) {
		this.subject = subject;
	}
 
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 
	 * 进行base64加密，防止中文乱码
	 * 
	 */
	public String changeEncode(String str) {
		try {
			str = MimeUtility.encodeText(new String(str.getBytes(), "UTF-8"),
					"UTF-8", "B"); // "B"代表Base64
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	 //正式发邮件
	public boolean sendMail() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtpServer);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.class", SSL_FACTORY); // 使用JSSE的SSL															// socketfactory来取代默认的socketfactory
		properties.put("mail.smtp.socketFactory.fallback", "false"); // 只处理SSL的连接,对于非SSL的连接不做处理
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.socketFactory.port", port);
		properties.put("mail.smtp.ssl.enable", true);
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		try {
			// 发件人
			Address address = new InternetAddress(username);
			message.setFrom(address);
			// 收件人
			for (String recipient : recipients) {
				System.out.println("收件人：" + recipient);
				Address toAddress = new InternetAddress(recipient);
				message.setRecipient(MimeMessage.RecipientType.TO, toAddress); // 设置收件人,并设置其接收类型为T
				// message.addRecipient(MimeMessage.RecipientType.TO,
				// toAddress); //发送多个，
				/**
				 * 
				 * TO：代表有健的主要接收者。 CC：代表有健的抄送接收者。 BCC：代表邮件的暗送接收者。
				 * 
				 */
			}
 
			// 主题
			message.setSubject(changeEncode(subject));
			// 时间
			message.setSentDate(new Date());
			Multipart multipart = new MimeMultipart();
			// 添加文本
			BodyPart text = new MimeBodyPart();
			text.setContent(content,"text/html;charset=GBK");
			multipart.addBodyPart(text);
			// 清空收件人集合，附件集合
			recipients.clear();
			message.setContent(multipart);
			message.saveChanges();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
 
		try {
			Transport transport = session.getTransport("smtp");
			transport.connect(smtpServer, username, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}