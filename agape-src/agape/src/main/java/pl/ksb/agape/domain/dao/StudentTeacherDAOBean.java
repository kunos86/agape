package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.StudentTeacher;
import pl.ksb.agape.domain.model.User;

@Stateless
public class StudentTeacherDAOBean extends BaseDAO<StudentTeacher> {

	public boolean hasStudentTeacher(Long studentId) {
		Long count = (Long) getHibernateSession()
				.createCriteria(StudentTeacher.class)
				.add(Restrictions.eq("current", "T"))
				.createAlias("student", "s")
				.add(Restrictions.eq("s.id", studentId))
				.setProjection(Projections.rowCount()).uniqueResult();

		return count.longValue() > 0;

	}

	public void setCuurentTeacherForStudent(User student, User teacher) {

		@SuppressWarnings("unchecked")
		List<StudentTeacher> stl = getHibernateSession()
				.createQuery(
						"Select st from StudentTeacher st where st.student = :user and st.current = :current")
				.setParameter("user", student).setParameter("current", "T")
				.list();

		for (StudentTeacher st : stl) {
			st.setCurrent(false);
			st.setDeletedDate(Calendar.getInstance().getTime());
			getHibernateSession().saveOrUpdate(st);
		}
		StudentTeacher newState = new StudentTeacher();
		newState.setStudent(student);
		newState.setTeacher(teacher);
		newState.setCurrent(true);
		newState.setCreationDate(Calendar.getInstance().getTime());
		getHibernateSession().saveOrUpdate(newState);
	}

}
