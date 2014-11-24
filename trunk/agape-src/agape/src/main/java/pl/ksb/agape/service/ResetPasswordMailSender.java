package pl.ksb.agape.service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import pl.ksb.agape.domain.model.User;

@Stateless
public class ResetPasswordMailSender implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4677545652812351264L;

	
	@EJB
	private MailSenderService mailSenderService;



	private String getMailMessage(String password) {
		return "Hasło do twojego konta zostało zmienione. Twoje nowe hasło to: "
				+ password;

	}

	public void sendMail(User user, String newPassword) {


		mailSenderService.send(user.getEmail(), "Nowe hasło do portalu kurs.mt28.pl",
				getMailMessage(newPassword));;
	}
}
