package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.ksb.agape.domain.model.StudentTeacher;
import pl.ksb.agape.domain.model.User;

@Stateless
public class StudentTeacherDAOBean {

	@Inject
	private EntityManager em;

	public void setCuurentTeacherForStudent(User student, User teacher) {

		@SuppressWarnings("unchecked")
		List<StudentTeacher> stl = em
				.createQuery(
						"Select st from StudentTeacher st where st.student = :user and st.current = :current")
				.setParameter("user", student).setParameter("current", "T")
				.getResultList();

		for (StudentTeacher st : stl) {
			st.setCurrent(false);
			st.setDeletedDate(Calendar.getInstance().getTime());
			em.merge(st);
		}
		StudentTeacher newState = new StudentTeacher();
		newState.setStudent(student);
		newState.setTeacher(teacher);
		newState.setCurrent(true);
		newState.setCreationDate(Calendar.getInstance().getTime());
		em.persist(newState);
	}

}
