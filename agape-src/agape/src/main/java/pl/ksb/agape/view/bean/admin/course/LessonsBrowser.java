package pl.ksb.agape.view.bean.admin.course;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import pl.ksb.agape.domain.dao.CourseDAOBean;
import pl.ksb.agape.domain.dao.LessonDAOBean;
import pl.ksb.agape.domain.model.Course;
import pl.ksb.agape.domain.model.Lesson;
import pl.ksb.agape.util.Encoder;

@ManagedBean
@ViewScoped

public class LessonsBrowser implements Serializable {


	private static final long serialVersionUID = -3453718247835100811L;

	public LessonsBrowser() {
	}

	private List<Lesson> lessons;
	private Lesson lesson = new Lesson();

	private Collection<Object> selection;

	@EJB
	private LessonDAOBean lessonDAOBean;

	@EJB
	private CourseDAOBean courseDAOBean;

	private Course course;
	
	private boolean editMode = false;
	
	

	@PostConstruct
	public void init() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String clipId = request.getParameter("id");
			Long id = Long.decode(clipId);
			Long idCourse = Encoder.decode(id);
			course = courseDAOBean.getById(idCourse);
		} catch (Exception e) {

		}

	}

	private void loadLessons() {
		lessons = lessonDAOBean.getCourseLessons(course.getId(), false);
		if (lessons == null) {
			lessons = new ArrayList<Lesson>();
		}
	}
	
	

	public boolean isEditMode() {
		return editMode;
	}

	public void add() {
		lesson = new Lesson();
		lesson.setCourse(course);
		editMode = true;
	}

	public void edycja() {
		editMode=true;
		loadSelection();
	}
	
	private void loadSelection(){
		if (selection != null && !selection.isEmpty()) {
			int id = (Integer) selection.toArray()[0];
			lesson = lessons.get(id);
		} else {
			lesson = new Lesson();
		}
		
	}
	

	public void save() {
		lessonDAOBean.save(lesson);
		loadLessons();
		editMode=false;
	}

	public void closeEditMode() {
		editMode=false;
	}
	
	public List<Lesson> getLessons() {
		if (lessons == null) {
			loadLessons();
		}
		return lessons;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public void goToQuestions() throws IOException{
		loadSelection();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/agape/pages/admin/kurs/pytania.jsf?faces-redirect=true&id="+Encoder.encode(lesson.getId()));  

	}
	
	public void goToCourses() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("/agape/pages/admin/kurs/kursy.jsf?faces-redirect=true");  

	}
	
}
