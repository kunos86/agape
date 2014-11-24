package pl.ksb.agape.view.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.util.CryptoTools;

@ManagedBean
@RequestScoped
public class ChangePasswordBrowser {

	private String confirmPassword;

	private String currentPassword;

	private String newPassword;
	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;

	@EJB
	private UserDAOBean userDAOBean;

	public void changePassword() {

		if (!userDAOBean.authenticat(sessionLoggedUser.getUser().getEmail(),
				CryptoTools.getInstance().passwordHash(currentPassword))) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Aktualne hasło jest niepoprawne!",
							"Aktualne hasło jest niepoprawne!"));
			return;
		}
		userDAOBean.changePassword(sessionLoggedUser.getUser().getId(),
				newPassword);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Twoje hasło zostało zmienione!",
						"Twoje hasło zostało zmienione!"));

	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setSessionLoggedUser(SessionLoggedUser sessionLoggedUser) {
		this.sessionLoggedUser = sessionLoggedUser;
	}

}
