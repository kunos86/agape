package pl.ksb.agape.domain.dao;

import java.util.Calendar;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.Answer;

@Stateless
@LocalBean
public class AnswerDAOBean extends BaseDAO<Answer> {

	public Answer getByIdQuestionIdStudent(Long idQuestion, Long idStudent) {

		Answer ret = (Answer) getHibernateSession()
				.createCriteria(Answer.class)
				.add(Restrictions.eq("question.id", idQuestion))
				.add(Restrictions.eq("student.id", idStudent)).uniqueResult();
		return ret;
	}

	@Override
	public void save(Answer answer) {
		answer.setModyficationDate(Calendar.getInstance().getTime());
		saveOrUpdate(answer);

	}
}
