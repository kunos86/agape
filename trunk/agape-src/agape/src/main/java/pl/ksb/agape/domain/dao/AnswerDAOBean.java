package pl.ksb.agape.domain.dao;

import java.util.Calendar;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.Answer;
import pl.ksb.agape.util.HibernateUtil;

@Stateless
@LocalBean
public class AnswerDAOBean {

	@Inject
	private EntityManager em;

	public Answer getByIdQuestionIdStudent(Long idQuestion, Long idStudent) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();

		Answer ret = (Answer) session.createCriteria(Answer.class)
				.add(Restrictions.eq("question.id", idQuestion))
				.add(Restrictions.eq("student.id", idStudent)).uniqueResult();
		// session.close();
		return ret;
	}

	public void save(Answer answer) {
		answer.setModyficationDate(Calendar.getInstance().getTime());

		if (answer.getId() == null) {
			em.persist(answer);
		} else {
			em.merge(answer);
		}
		// Session hibernateSession = HibernateUtil.getSessionFactory()
		// .getCurrentSession();
		//
		// Transaction tx = hibernateSession.beginTransaction();
		// answer.setModyficationDate(Calendar.getInstance().getTime());
		// hibernateSession.saveOrUpdate(answer);
		// tx.commit();

	}
}
