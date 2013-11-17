package pl.ksb.agape.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import pl.ksb.agape.domain.dao.AnswerDAOBean;
import pl.ksb.agape.domain.dao.EducationStateDAOBean;
import pl.ksb.agape.domain.dao.LessonDAOBean;
import pl.ksb.agape.domain.dao.QuestionDAOBean;
import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.Answer;
import pl.ksb.agape.domain.model.EducationState;
import pl.ksb.agape.domain.model.Lesson;
import pl.ksb.agape.domain.model.Question;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.domain.model.type.QuestionAnswer;
import pl.ksb.agape.util.Encoder;

@ManagedBean
@ViewScoped
public class LessonBean implements Serializable {

	private static final long serialVersionUID = -8899982915688655263L;

	private Lesson lesson;
	private Long idStudent;
	@EJB
	private LessonDAOBean lessonDAOBean;
	@EJB
	private EducationStateDAOBean educationStateDAOBean;

	@EJB
	private QuestionDAOBean questionDAOBean;

	private List<QuestionAnswer> questionAnswerList;

	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;

	@EJB
	private AnswerDAOBean answerDAOBean;

	@EJB
	private UserDAOBean userDAOBean;

	private EducationState educationState;

	public void setSessionLoggedUser(SessionLoggedUser sessionLoggedUser) {
		this.sessionLoggedUser = sessionLoggedUser;
	}

	@PostConstruct
	public void init() {
		System.out.println("init");
		questionAnswerList = new ArrayList<QuestionAnswer>();

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String clipId = request.getParameter("idLekcja");

		Long id = Long.decode(clipId);
		Long idLesson = Encoder.decode(id);
		lesson = lessonDAOBean.getById(idLesson);

		clipId = request.getParameter("idStudent");
		if (clipId != null) {
			id = Long.decode(clipId);
			idStudent = Encoder.decode(id);
		} else {
			idStudent = sessionLoggedUser.getUser().getId();
		}
		User student = userDAOBean.getById(idStudent);

		educationState = educationStateDAOBean.getByIdLessonIdStudent(idLesson,
				idStudent);
		// zoaczenie lekcji jako podczytanej podczas pierwszego wejścia
		if (!educationState.isRead()) {
			educationState.setReadDate(Calendar.getInstance().getTime());
			educationStateDAOBean.saveOrUpdate(educationState);

		}

		// wczytanie listy pytań i zapisanych odpowiedzi
		List<Question> quesList = questionDAOBean.getQuestions(lesson.getId(),
				true);
		for (Question quest : quesList) {

			Answer answer = answerDAOBean.getByIdQuestionIdStudent(
					quest.getId(), idStudent);

			if (answer == null) {
				answer = new Answer();
				answer.setStudent(student);
				answer.setQuestion(quest);
			}
			QuestionAnswer qa = new QuestionAnswer(quest, answer);
			questionAnswerList.add(qa);

		}

	}

	public void save() {
		for (QuestionAnswer qa : questionAnswerList) {
			answerDAOBean.save(qa.getAnswer());
		}
	}

	public boolean isAnyEmptyAnswer() {

		for (QuestionAnswer qa : questionAnswerList) {
			if (qa.getAnswer().getContent() == null
					|| qa.getAnswer().getContent().isEmpty()) {
				return true;
			}
		}
		return false;

	}

	public void send() {

		if (isAnyEmptyAnswer()) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"Na wszystkie pytania musisz udzielić odpowiedzi!"));
			return;

		}

		save();
		educationState.setSentDate(Calendar.getInstance().getTime());
		educationStateDAOBean.saveOrUpdate(educationState);

	}

	public String check() {
		educationState.setCheckedDate(Calendar.getInstance().getTime());
		educationStateDAOBean.saveOrUpdate(educationState);

		return "/pages/teacher/postep.xhtml";
	}

	public Lesson getLesson() {
		return lesson;
	}

	public List<QuestionAnswer> getQuestionAnswerList() {
		return questionAnswerList;
	}

	public EducationState getEducationState() {
		return educationState;
	}

}
