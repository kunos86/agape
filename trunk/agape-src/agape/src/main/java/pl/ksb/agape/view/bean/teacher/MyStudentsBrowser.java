package pl.ksb.agape.view.bean.teacher;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.util.Encoder;
import pl.ksb.agape.view.bean.SessionLoggedUser;

@ManagedBean
@RequestScoped
public class MyStudentsBrowser {

	private Long selectedId;

	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;

	private List<User> students;

	@EJB
	private UserDAOBean userDAOBean;

	public Long getSelectedId() {
		return selectedId;
	}

	public List<User> getStudents() {
		if (students == null) {
			students = userDAOBean.getStudentsByTeacher(sessionLoggedUser
					.getUser().getId());
		}
		return students;
	}

	public void progres() throws IOException {
		FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.redirect(
						"/agape/pages/teacher/postepUcznia.jsf?faces-redirect=true&id="
								+ Encoder.encode(selectedId));

	}

	public void setSelectedId(Long selectedId) {
		this.selectedId = selectedId;
	}

	public void setSessionLoggedUser(SessionLoggedUser sessionLoggedUser) {
		this.sessionLoggedUser = sessionLoggedUser;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

}
