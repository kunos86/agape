package pl.ksb.agape.view.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import pl.ksb.agape.domain.dao.ActivationTokenDAOBean;
import pl.ksb.agape.domain.model.ActivationToken;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.service.MailSenderService;
import pl.ksb.agape.service.UserManagement;
import pl.ksb.agape.util.CryptoTools;

@ManagedBean(name = "registration")
@ViewScoped
public class UserRegistration implements Serializable {

	private static final long serialVersionUID = 3073828016819449376L;

	@EJB
	private ActivationTokenDAOBean activationTokenDAOBean;

	@Inject
	private FacesContext facesContext;

	@EJB
	private MailSenderService mailSenderService;
	private String password2;

	private boolean regulations;

	private User user = new User();

	//
	@EJB
	private UserManagement userManagement;

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

	public String getPassword2() {
		return password2;
	}

	public User getUser() {
		return user;
	}

	public boolean isRegulations() {
		return regulations;
	}

	public String save() {
		String ret = "";
		if (!regulations) {
			facesContext
					.addMessage(
							null,
							new FacesMessage(
									"Niezaakceptowales regulaminu. Aby sie zarejestrowac musisz zaakceptowac regulamin servisu."));
			return "";
		}

		try {
			user.setPassword(CryptoTools.getInstance().passwordHash(
					user.getPassword()));
			userManagement.register(user);

			ActivationToken token = activationTokenDAOBean.generateToken(user
					.getId());

			mailSenderService.send(user.getEmail(),
					"Potwierdzenie rejestracji",
					getMailMessage(token.getToken()));

			facesContext
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									"Rejestracja zakończona powodzeniem. Zaloguj się, aby przejść do servisu.",
									"Rejestracja zakończona"));

			ret = "registered";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Błąd rejestracji osoby",
					"Wystąpił błąd podczas rejestracji nowej osoby. "));
		}
		return ret;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public void setRegulations(boolean regulations) {
		this.regulations = regulations;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
