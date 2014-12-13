package pl.ksb.agape.view.bean.admin.course;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import pl.ksb.agape.domain.dao.LessonDAOBean;
import pl.ksb.agape.domain.dao.QuestionDAOBean;
import pl.ksb.agape.domain.model.Lesson;
import pl.ksb.agape.domain.model.Question;
import pl.ksb.agape.domain.model.QuestionAddition;
import pl.ksb.agape.domain.model.dto.UploadedFile;
import pl.ksb.agape.util.Encoder;
import pl.ksb.agape.view.bean.SessionLoggedUser;
import pl.ksb.agape.view.bean.tools.FileUploaderBean;

@ManagedBean
@ViewScoped
public class QuestionsBrowser implements Serializable {

	private static final long serialVersionUID = 2523628575703193830L;

	public QuestionsBrowser() {
		// TODO Auto-generated constructor stub
	}

	private List<Question> questions;

	@EJB
	private LessonDAOBean lessonDAOBean;

	@EJB
	private QuestionDAOBean questionDAOBean;

	private Question question = new Question();
	private QuestionAddition questionAddition = new QuestionAddition();

	private Lesson lesson;
	private Long lessonId;

	private boolean editMode = false;

	private boolean additionEditMode = false;

	@PostConstruct
	public void init() {

		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String clipId = request.getParameter("id");
			if (clipId != null && !clipId.isEmpty()) {
				Long id = Long.decode(clipId);
				lessonId = Encoder.decode(id);
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap()
						.put("questionBrowserLessonId", lessonId);
			}
			lessonId = (Long) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap()
					.get("questionBrowserLessonId");

		} catch (Exception e) {

		}
	}

	public void add() {
		question = new Question();
		question.setLesson(getLesson());
		editMode = true;
	}

	public void edit(Long id) {
		question = questionDAOBean.getById(id);
		editMode = true;
	}

	public void delete(Long id) {

		try {
			questionDAOBean.delete(id);
		} catch (Exception e) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"Nie udało się usunąć pytania. Pytanie może być powiązane z innym obiektem."));
		}
		loadQuestions();

	}

	public Lesson getLesson() {
		if (lesson == null) {
			lesson = lessonDAOBean.getById(lessonId);
		}

		return lesson;
	}

	public void save() {
		questionDAOBean.save(question);
		loadQuestions();
		editMode = false;
	}

	public void addNewAddition() {
		additionEditMode = true;
		questionAddition = new QuestionAddition();

	}

	public void closeEditMode() {
		editMode = false;
		additionEditMode = false;
	}

	public void closeAdditionEditMode() {
		questionAddition = new QuestionAddition();
		additionEditMode = false;
	}

	public void deleteAddition(Long id) {

		for (QuestionAddition qa : question.getQuestionAdditions()) {
			if (qa.getId().equals(id)) {
				question.getQuestionAdditions().remove(qa);
				// questionDAOBean.save(question);
				// loadQuestions();
				return;
			}
		}

	}

	public Question getQuestion() {
		return question;
	}

	public boolean isEditMode() {
		return editMode;
	}

	private void loadQuestions() {

		questions = questionDAOBean.getQuestions(getLesson().getId(), false);
		if (questions == null) {
			questions = new ArrayList<Question>();
		}
	}

	public List<Question> getQuestions() {
		if (questions == null) {
			loadQuestions();
		}
		return questions;
	}

	public void goToLessons() throws IOException {
		FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.redirect(
						"/agape/pages/admin/kurs/lekcje.jsf?faces-redirect=true&id="
								+ Encoder.encode(getLesson().getCourse()
										.getId()));

	}

	public QuestionAddition getQuestionAddition() {
		return questionAddition;
	}

	public boolean isAdditionEditMode() {
		return additionEditMode;
	}

	public List<SelectItem> getFileTypeSelectItems() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (QuestionAddition.FileType type : QuestionAddition.FileType
				.values()) {
			list.add(new SelectItem(type, type.getName()));
		}
		return list;

	}

	public String goToLesson() {
		System.out.println("go to lesson");
		return "/pages/lekcja.xhtml?faces-redirect=true&idLekcja="
				+ Encoder.encode(lessonId);
	}

	public void saveAddtion() {
		FileUploaderBean b = (FileUploaderBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("fileUploaderBean");

		if (b == null || b.getUploadedFiles().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Nie dodano pliku!"));
			return;
		}
		UploadedFile file = b.getUploadedFiles().get(0);

		questionAddition.setContent(file.getContent());
		questionAddition.setFileName(file.getName());
		questionAddition.setMimeType(file.getContentType());
		questionAddition.setQuestion(question);

		question.getQuestionAdditions().add(questionAddition);

		// questionDAOBean.save(question);

		b.clearList();
		closeAdditionEditMode();

	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

}
