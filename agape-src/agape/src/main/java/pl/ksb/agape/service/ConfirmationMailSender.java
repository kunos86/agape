package pl.ksb.agape.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import pl.ksb.agape.domain.dao.ActivationTokenDAOBean;
import pl.ksb.agape.domain.model.ActivationToken;
import pl.ksb.agape.domain.model.User;

@Stateless
@LocalBean
public class ConfirmationMailSender {

	@EJB
	private ActivationTokenDAOBean activationTokenDAOBean;

	@EJB
	private MailSenderService mailSenderService;

	private String getActivationUrl(String token) {

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String realPath = request.getRequestURL().substring(
				0,
				request.getRequestURL().indexOf(request.getContextPath())
						+ request.getContextPath().length());

		return realPath + "/mailConfirmationServlet?t=" + token;
	}

	private String getMailMessage(String token) {
		return "Dziękujemy za zainteresowanie naszym portalem. Aby aktywować konto kliknij na link poniżej. "
				+ getActivationUrl(token);

	}

	public void sendMail(User user) {
		ActivationToken token = activationTokenDAOBean.generateToken(user
				.getId());

		mailSenderService.send(user.getEmail(), "Potwierdzenie rejestracji",
				getMailMessage(token.getToken()));
	}

}
