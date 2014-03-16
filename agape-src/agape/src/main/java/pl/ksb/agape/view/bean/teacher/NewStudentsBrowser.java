package pl.ksb.agape.view.bean.teacher;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import pl.ksb.agape.domain.dao.StudentTeacherDAOBean;
import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.util.Encoder;
import pl.ksb.agape.view.bean.SessionLoggedUser;

@ManagedBean
@RequestScoped
public class NewStudentsBrowser {

	private Long selectedId;

	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;

	private List<User> students;

	@EJB
	private StudentTeacherDAOBean studentTeacherDAOBean;

	@EJB
	private UserDAOBean userDAOBean;

	public void addStudentToMe() throws IOException {
		// TODO: sprawdzenie czy uczeń nie został wybrany przez innego
		// nauczyciela w innej sesji

		if (!studentTeacherDAOBean.hasStudentTeacher(selectedId)) {

			User student = userDAOBean.getById(selectedId);
			studentTeacherDAOBean.setCuurentTeacherForStudent(student,
					sessionLoggedUser.getUser());

			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/agape/pages/teacher/postepUcznia.jsf?faces-redirect=true&new=T&id="
									+ Encoder.encode(selectedId));

		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									"Uczeń właśnie został wybrany przez innego nauczyciela.",
									"Uczeń własnie został wybrany przez innego nauczyciela."));

		}

	}

	public Long getSelectedId() {
		return selectedId;
	}

	public List<User> getStudents() {
		if (students == null) {
			loadStudents();
		}
		return students;
	}

	private void loadStudents() {
		students = userDAOBean.getStudentsWithoutTeacher();
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
