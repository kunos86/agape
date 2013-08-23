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

import pl.ksb.agape.domain.dao.LessonDAOBean;
import pl.ksb.agape.domain.dao.QuestionDAOBean;
import pl.ksb.agape.domain.model.Lesson;
import pl.ksb.agape.domain.model.Question;
import pl.ksb.agape.util.Encoder;


@ManagedBean
@ViewScoped
public class QuestionsBrowser implements Serializable{


	private static final long serialVersionUID = 2523628575703193830L;

	public QuestionsBrowser() {
		// TODO Auto-generated constructor stub
	}
	
	
	private List<Question> questions;


	
	
	@EJB
	private LessonDAOBean lessonDAOBean;

	@EJB
	private QuestionDAOBean questionAOBean;

	private Question question = new Question();
	
	private Lesson lesson;
	
	private boolean editMode = false;
	
	
	
	

	@PostConstruct
	public void init() {

		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String clipId = request.getParameter("id");
			Long id = Long.decode(clipId);
			Long lessonId = Encoder.decode(id);
			lesson = lessonDAOBean.getById(lessonId);
		} catch (Exception e) {

		}
	}

	
	public void add() {
		question = new Question();
		question.setLesson(lesson);
		editMode = true;
	}

	public void edit(Long id) {
		question = questionAOBean.getById(id);
		editMode=true;
	}
	

	

	public void save() {
		questionAOBean.save(question);
		loadQuestions();
		editMode=false;
	}

	public void closeEditMode() {
		editMode = false;
	}
		

	public Question getQuestion() {
		return question;
	}



	public boolean isEditMode() {
		return editMode;
	}
	
	private void loadQuestions() {
		init();
		questions = questionAOBean.getQuestions(lesson.getId(), false);
		if (questions == null) {
			questions = new ArrayList<Question>();
		}
	}



	public List<Question> getQuestions() {
		if (questions == null){
			loadQuestions();
		}
		return questions;
	}
	
	public void goToLessons() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("/agape/pages/admin/kurs/lekcje.jsf?id="+Encoder.encode(lesson.getCourse().getId()));  

	}
	
	


}
