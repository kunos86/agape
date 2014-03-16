package pl.ksb.agape.view.bean.teacher;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import pl.ksb.agape.domain.dao.CourseDAOBean;
import pl.ksb.agape.domain.dao.EducationStateDAOBean;
import pl.ksb.agape.domain.dao.LessonDAOBean;
import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.Course;
import pl.ksb.agape.domain.model.EducationState;
import pl.ksb.agape.domain.model.Lesson;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.util.Encoder;
import pl.ksb.agape.view.bean.SessionLoggedUser;

@ManagedBean
@RequestScoped
public class StudentProgresBrowser {

	@EJB
	private CourseDAOBean courseDAOBean;

	private List<Course> courses;

	@EJB
	private EducationStateDAOBean educationStateDAOBean;

	@EJB
	private LessonDAOBean lessonDAOBean;

	private Long lessonId;

	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;

	private User student;

	private Long studentId;

	@EJB
	private UserDAOBean userDAOBean;

	public List<Course> getCourses() {

		if (courses == null) {
			loadCourses();
		}
		return courses;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public String getState(Long lessonId, Long studentId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EducationState state = educationStateDAOBean.getByIdLessonIdStudent(
				lessonId, studentId);

		if (state == null) {
			return "Nieudostępniona";
		}
		if (state.getCheckedDate() != null) {
			return "Sprawdzona (" + sdf.format(state.getCheckedDate()) + ")";
		}

		if (state.getSentDate() != null) {
			return "Wysłana do spr. (" + sdf.format(state.getSentDate()) + ")";
		}
		if (state.getReadDate() != null) {
			return "Odczytana (" + sdf.format(state.getReadDate()) + ")";
		}
		if (state.getSharedDate() != null) {
			return "Udostępniona (" + sdf.format(state.getSharedDate()) + ")";
		}

		return "stan nieznany";

	}

	public User getStudent() {
		return student;
	}

	public Long getStudentId() {
		return studentId;
	}

	@PostConstruct
	public void init() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String clipId = request.getParameter("id");

			Long id = Long.decode(clipId);
			Long idStudent = Encoder.decode(id);
			student = userDAOBean.getById(idStudent);
			if (request.getParameter("new") != null) {

			}
		} catch (Exception e) {

		}

	}

	public void loadCourses() {
		courses = courseDAOBean.getCoursesForTeacher(sessionLoggedUser
				.getUser().getId());
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public void setSessionLoggedUser(SessionLoggedUser sessionLoggedUser) {
		this.sessionLoggedUser = sessionLoggedUser;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public void shareLesson() {
		EducationState state = new EducationState();
		Lesson l = lessonDAOBean.getById(lessonId);
		student = userDAOBean.getById(studentId);
		state.setLesson(l);
		state.setStudent(student);
		state.setSharedDate(Calendar.getInstance().getTime());
		educationStateDAOBean.saveOrUpdate(state);
		loadCourses();
	}
}
