package com.revature.beans;

import com.sun.mail.smtp.SMTPTransport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import org.apache.log4j.Logger;
import java.util.Date;
import java.util.Properties;
public class SendEmail {
//	static final Logger loggy = Logger.getLogger(SendEmail.class);
	// for example, smtp.mailgun.org
	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static final String USERNAME = System.getenv("emailAddress");
	private static final String PASSWORD = System.getenv("emailPass");
	private static final String EMAIL_FROM = System.getenv("emailAddress");
    private static final String EMAIL_SUBJECT = "Access to Admin Account";
    private static final String EMAIL_TEXT_BEGINNING = "Someone is attempting to access your admin account.\n"
    		+ "If this is you, please use this code to continue logging in : \n\n";
    
    private static final String EMAIL_TEXT_ENDING = "\n\nIf you did not authorize this request, "
    		+ "please contact the IT team immediately."
    		+ "\nThank you,\nConnections customer support.";
    
    public static boolean sendEmail(String recipientEmail, String OTP) {
    	Properties prop = new Properties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop, null);
        try {
        	Message msg = new MimeMessage(session);
		
			// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));
			// to
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail, false));
			// subject
            msg.setSubject(EMAIL_SUBJECT);
			
			// content
            msg.setText(EMAIL_TEXT_BEGINNING + OTP + EMAIL_TEXT_ENDING);
			
            msg.setSentDate(new Date());
			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());
//            loggy.info("Email was sent to alert for a password update.");
            t.close();
            return true;
        } catch (MessagingException e) {
//        	loggy.error("Email failed to send", e);
            e.printStackTrace();
            return false;
        }
    }
    
    private static final String REACTIVATE_ACC_EMAIL_SUBJECT = "Your account has been disabled";
    private static final String REACTIVATE_ACC_EMAIL_ADDRESS = "admin@admin.com";
    private static final String REACTIVATE_ACC_EMAIL = "In order to reactivate your account, please contact an administrator at: \n";
    public static boolean sendEmail(String recipientEmail) {
    	Properties prop = new Properties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop, null);
        try {
        	Message msg = new MimeMessage(session);
		
			// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));
			// to
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail, false));
			// subject
            msg.setSubject(REACTIVATE_ACC_EMAIL_SUBJECT);
			
			// content
            msg.setText(REACTIVATE_ACC_EMAIL + REACTIVATE_ACC_EMAIL_ADDRESS);
			
            msg.setSentDate(new Date());
			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());
//            loggy.info("Email was sent to alert for a password update.");
            t.close();
            return true;
        } catch (MessagingException e) {
//        	loggy.error("Email failed to send", e);
            e.printStackTrace();
            return false;
        }
    }
    
    public static int generateOTP() {
    	int max = 999999;
    	int min = 100000;
    	int range = max - min;
    	
    	int result = (int) (Math.random() * range) + min;
    	return result;
    }
}
