package pl.ksb.agape.view.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.service.ConfirmationMailSender;
import pl.ksb.agape.service.UserManagement;
import pl.ksb.agape.util.CryptoTools;

@ManagedBean(name = "registration")
@ViewScoped
public class UserRegistration implements Serializable {

	private static final long serialVersionUID = 3073828016819449376L;

	@EJB
	private ConfirmationMailSender confirmationMailSender;

	@Inject
	private FacesContext facesContext;

	private boolean regulations;

	private User user = new User();

	//
	@EJB
	private UserManagement userManagement;

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
									FacesMessage.SEVERITY_ERROR,
									"Nie zaakceptowałeś/aś regulaminu!",
									"Nie zaakceptowałeś/aś regulaminu. Aby sie zarejestrować musisz zaakceptować regulamin servisu."));
			return "";
		}

		try {
			user.setPassword(CryptoTools.getInstance().passwordHash(
					user.getPassword()));
			userManagement.register(user);
			confirmationMailSender.sendMail(user);

			facesContext
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									"Rejestracja zakończona powodzeniem. Zaloguj się, aby przejść do serwisu.",
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


	public void setRegulations(boolean regulations) {
		this.regulations = regulations;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
