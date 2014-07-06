package pl.ksb.agape.service;

import java.util.Date;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

@Stateless
@LocalBean
public class MailSenderService {

	private final String host = "mail.kunos.pl";
	private final String password = "agape";
	private final String userName = "biblijny.kompas@kunos.pl";

	public void _send(String recipientEmail, String title, String message)
			throws AddressException, MessagingException {
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, null);

		// -- Create a new message --
		MimeMessage msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --
		msg.setFrom(new InternetAddress(userName));
		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipientEmail, false));

		msg.setSubject(title);
		msg.setText(message, "utf-8");
		msg.setSentDate(new Date());

		SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

		t.connect(host, userName, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}

	public void send(String address, String subject, String message) {
		try {
			_send(address, subject, message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
