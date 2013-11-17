package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.Course;
import pl.ksb.agape.util.HibernateUtil;

/**
 * Session Bean implementation class KursDAOBean
 */
@Stateless
@LocalBean
public class CourseDAOBean {

	/**
	 * Default constructor.
	 */
	public CourseDAOBean() {
	}

	@SuppressWarnings("unused")
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	public Course getById(long id) {
		return em.find(Course.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCourses(boolean enabled) {
		Query q = null;
		if (enabled) {
			q = em.createNamedQuery("getEnabledCourse");
		} else {
			q = em.createNamedQuery("getCourse");
		}
		return q.getResultList();
	}

	public void save(Course course) {

		course.setModificationDate(Calendar.getInstance().getTime());
		course.setStatus("A");
		if (course.getId() == null) {
			course.setCreationDate(Calendar.getInstance().getTime());
			em.persist(course);
		} else {
			em.merge(course);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCoursesListForStudent(Long userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();

		List<Course> ret = session
				.createCriteria(Course.class)
				.setFetchMode("lessons", FetchMode.EAGER)
				.createAlias("lessons", "l")
				.setFetchMode("l.educationStates", FetchMode.EAGER)
				.createAlias("l.educationStates", "st")
				.add(Restrictions.eq("st.student.id", userId))
				// .add(Restrictions.eq("enabled", true))
				// .add(Restrictions.eq("l.enabled", true))
				.addOrder(Order.asc("number"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		// tx.commit();
		// session.close();

		return ret;
	}

}
