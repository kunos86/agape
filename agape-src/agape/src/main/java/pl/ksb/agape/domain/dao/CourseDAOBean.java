package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.Course;
import pl.ksb.agape.domain.model.Lesson;

/**
 * Session Bean implementation class KursDAOBean
 */
@Stateless
@LocalBean
public class CourseDAOBean extends BaseDAO<Course> {

	@SuppressWarnings("unused")
	@Inject
	private Logger log;

	@SuppressWarnings("unchecked")
	public List<Course> getCourses(boolean enabled) {
		Query q = null;
		if (enabled) {
			q = getEntityManager().createNamedQuery("getEnabledCourse");
		} else {
			q = getEntityManager().createNamedQuery("getCourse");
		}
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCoursesForTeacher(Long teacherId) {
		// TODO: dodac wczytywanie uprawnien
		List<Course> l = getHibernateSession().createCriteria(Course.class)
				.setFetchMode("lessons", FetchMode.EAGER)
				.add(Restrictions.eq("status", "A"))
				.addOrder(Order.asc("number"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return l;
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCoursesListForStudent(Long userId) {

		List<Course> ret = getHibernateSession().createCriteria(Course.class)
				.setFetchMode("lessons", FetchMode.EAGER)
				.createAlias("lessons", "l")
				.setFetchMode("l.educationStates", FetchMode.EAGER)
				.createAlias("l.educationStates", "st")
				.add(Restrictions.eq("st.student.id", userId))
				.add(Restrictions.eq("enabled", true))
				.add(Restrictions.eq("l.enabled", true))
				.addOrder(Order.asc("number"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		for (Course c : ret) {
			Hibernate.initialize(c.getLessons());
			for (Lesson l : c.getLessons()) {
				Hibernate.initialize(l.getEducationStates());
			}
		}
		return ret;
	}

	@Override
	public void save(Course course) {
		course.setModificationDate(Calendar.getInstance().getTime());
		course.setStatus("A");
		if (course.getId() == null) {
			course.setCreationDate(Calendar.getInstance().getTime());
		}
		saveOrUpdate(course);
	}

}
