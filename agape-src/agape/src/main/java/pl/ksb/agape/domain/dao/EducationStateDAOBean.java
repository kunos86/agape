package pl.ksb.agape.domain.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.EducationState;

@Stateless
@LocalBean
public class EducationStateDAOBean extends BaseDAO<EducationState> {

	public EducationState getByIdLessonIdStudent(Long lessonId, Long studentId) {
		;
		return (EducationState) getHibernateSession()
				.createCriteria(EducationState.class)
				.add(Restrictions.eq("lesson.id", lessonId))
				.add(Restrictions.eq("student.id", studentId)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<EducationState> getStateAndLessonToCheck(Long teacherId) {
		return getHibernateSession()
				.createQuery(
						"from EducationState e left join fetch e.lesson left join fetch e.student where e.sentDate is not null and e.checkedDate is null and e.student.id in "
								+ "(Select st.student.id from StudentTeacher st where st.current = 'T' and st.teacher.id = :teacherId) ")
				.setParameter("teacherId", teacherId).list();

	}

}
