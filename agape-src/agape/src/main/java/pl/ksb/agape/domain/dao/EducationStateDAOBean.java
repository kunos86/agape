package pl.ksb.agape.domain.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.EducationState;
import pl.ksb.agape.util.HibernateUtil;

@Stateless
@LocalBean
public class EducationStateDAOBean {

	@Inject
	private EntityManager em;

	public EducationState getByIdLessonIdStudent(Long lessonId, Long studentId) {
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.getCurrentSession();

		Transaction tx = hibernateSession.beginTransaction();
		return (EducationState) hibernateSession
				.createCriteria(EducationState.class)
				.add(Restrictions.eq("lesson.id", lessonId))
				.add(Restrictions.eq("student.id", studentId)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<EducationState> getStateAndLessonToCheck(Long teacherId) {

		Session hibernateSession = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		Transaction tx = hibernateSession.beginTransaction();
		return hibernateSession
				.createQuery(
						"from EducationState e left join fetch e.lesson left join fetch e.student where e.sentDate is not null and e.checkedDate is null and e.student.id in "
								+ "(Select st.student.id from StudentTeacher st where st.current = 'T' and st.teacher.id = :teacherId) ")
				.setParameter("teacherId", teacherId).list();

	}

	public void saveOrUpdate(EducationState t) {

		if (t.getId() == null) {
			em.persist(t);
		} else {
			em.merge(t);
		}
		// Session hibernateSession = HibernateUtil.getSessionFactory()
		// .getCurrentSession();
		//
		// Transaction tx = hibernateSession.beginTransaction();
		//
		// hibernateSession.saveOrUpdate(t);
		// tx.commit();

	}

}
