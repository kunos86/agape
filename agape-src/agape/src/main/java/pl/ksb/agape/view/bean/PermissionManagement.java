package pl.ksb.agape.view.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.util.CryptoTools;

@ManagedBean
@RequestScoped
public class PermissionManagement {

	private String password;

	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;

	@EJB
	private UserDAOBean userDAOBean;

	private String username;

	public PermissionManagement() {

	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isCoordinatorLogged() {
		Subject subject = SecurityUtils.getSubject();
		return subject.isAuthenticated() && subject.hasRole("COORDINATOR");
	}

	public boolean isLogged() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	public boolean isStudentLogged() {
		Subject subject = SecurityUtils.getSubject();
		return subject.isAuthenticated() && subject.hasRole("STUDENT");
	}

	public boolean isTeacherLogged() {
		Subject subject = SecurityUtils.getSubject();
		return subject.isAuthenticated() && (subject.hasRole("TEACHER"));
	}

	public String login() {
		System.out.println("login user " + username);
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {

			String pass = CryptoTools.getInstance().passwordHash(password);
			UsernamePasswordToken token = new UsernamePasswordToken(username,
					pass);

			try {
				subject.login(token);
				password = null;
				User user = userDAOBean.getUserByMail(username);
				sessionLoggedUser.setUser(user);
				subject.getSession().setAttribute("USER_ID", user.getId());

				// if no exception, that's it, we're done!
				// } catch ( UnknownAccountException uae ) {
				// FacesContext.getCurrentInstance().addMessage(null, new
				// FacesMessage("Nieznane konto!"));
				// } catch ( IncorrectCredentialsException ice ) {
				// //password didn't match, try again?
				// } catch ( LockedAccountException lae ) {

			}
			// ... more types exceptions to check if you want ...
			catch (Exception ae) {
				System.out.println(ae);
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"Błąd podczas logowania. Niepoprawny login lub hasło"));
				return "";
			}
		}
		return "/pages/home.xhtml?faces-redirect=true";
	}

	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		return "/index.xhtm?faces-redirect=true";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSessionLoggedUser(SessionLoggedUser sessionLoggedUser) {
		this.sessionLoggedUser = sessionLoggedUser;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
