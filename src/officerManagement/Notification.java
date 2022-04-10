package officerManagement;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Notification {
	String recipient;
	String message;
	String subject;
	
	public Notification(String recipient, String message, String subject) {
		this.recipient=recipient;
		this.message=message;
		this.subject=subject;
	}
	
	public boolean sendMail() {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String email="2171project@gmail.com";
		String password="sg-epk@jtk931.048596";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email,password);
			}
		});
		
		Message message2 = prepareMessage(session,email);
		
		try {
			Transport.send(message2);
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	private Message prepareMessage(Session session, String email) {
		Message message2 = new MimeMessage(session);
		try {
			message2.setFrom(new InternetAddress(email));
			message2.setRecipient(Message.RecipientType.TO, new InternetAddress(this.recipient));
			message2.setSubject(this.subject);
			message2.setText(this.message);
			
			return message2;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

}
