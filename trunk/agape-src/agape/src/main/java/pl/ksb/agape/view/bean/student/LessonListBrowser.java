package pl.ksb.agape.view.bean.student;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pl.ksb.agape.domain.dao.CourseDAOBean;
import pl.ksb.agape.domain.model.Course;
import pl.ksb.agape.util.Encoder;
import pl.ksb.agape.view.bean.SessionLoggedUser;

@ManagedBean
@ViewScoped
public class LessonListBrowser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3787352847216492485L;
	@EJB
	private CourseDAOBean courseDAOBean;
	Collection<Course> courses;

	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;

	public void setSessionLoggedUser(SessionLoggedUser sessionLoggedUser) {
		this.sessionLoggedUser = sessionLoggedUser;
	}

	@PostConstruct
	public void init() {
		courses = courseDAOBean.getCoursesListForStudent(sessionLoggedUser
				.getUser().getId());
	}

	public Collection<Course> getCourses() {
		return courses;
	}

	public int getCoursesCount() {
		return courses == null ? 0 : courses.size();
	}

	public String goToLesson(Long idLesson) {
		return "/pages/lekcja.xhtml?faces-redirect=true&idLekcja="
				+ Encoder.encode(idLesson);
	}

}
