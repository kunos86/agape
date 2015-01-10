package pl.ksb.agape.view.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.service.ConfirmationMailSender;

@ManagedBean
@RequestScoped
public class HomeBean {
	@EJB
	private ConfirmationMailSender confirmationMailSender;

	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;
	
	private User currentTeacher; 

	@EJB
	private UserDAOBean userDAOBean;

	public void reloadSessionUser() {

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String reload = request.getParameter("reloadUser");
		if (reload != null) {

			User u = userDAOBean.getById(sessionLoggedUser.getUser().getId());
			sessionLoggedUser.setUser(u);

		}

	}

	public void sendConfirmationMail() {
		confirmationMailSender.sendMail(sessionLoggedUser.getUser());

	}

	public void setSessionLoggedUser(SessionLoggedUser sessionLoggedUser) {
		this.sessionLoggedUser = sessionLoggedUser;
	}

	public User getCurrentTeacher() {
		if (currentTeacher==null){
			currentTeacher = userDAOBean.getCurrentTeacherForStudent(sessionLoggedUser.getUser().getId());
		}
		return currentTeacher;
	}


	
	

}
