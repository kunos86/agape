package pl.ksb.agape.domain.model.type;

import java.io.Serializable;

import pl.ksb.agape.domain.model.Answer;
import pl.ksb.agape.domain.model.Question;

public class QuestionAnswer implements Serializable {

	private static final long serialVersionUID = 8912570422004555183L;
	private Question question;
	private Answer answer;

	public QuestionAnswer(Question question, Answer answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

}
