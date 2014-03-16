package pl.ksb.agape.view.bean.teacher;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import pl.ksb.agape.domain.dao.EducationStateDAOBean;
import pl.ksb.agape.domain.model.EducationState;
import pl.ksb.agape.util.Encoder;
import pl.ksb.agape.view.bean.SessionLoggedUser;

@ManagedBean
@RequestScoped
public class LessonToCheck implements Serializable {

	private static final long serialVersionUID = -2612671661343865062L;

	@EJB
	private EducationStateDAOBean educationStateDAOBean;

	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;

	private List<EducationState> studentEducationState;

	public List<EducationState> getStudentEducationState() {
		if (studentEducationState == null) {
			studentEducationState = educationStateDAOBean
					.getStateAndLessonToCheck(sessionLoggedUser.getUser()
							.getId());
		}

		return studentEducationState;
	}

	public String goToLesson(Long idLesson, Long idStudent) {
		return "/pages/lekcja.xhtml?faces-redirect=true&idLekcja="
				+ Encoder.encode(idLesson) + "&idStudent="
				+ Encoder.encode(idStudent);
	}

	public void setSessionLoggedUser(SessionLoggedUser sessionLoggedUser) {
		this.sessionLoggedUser = sessionLoggedUser;
	}
}
