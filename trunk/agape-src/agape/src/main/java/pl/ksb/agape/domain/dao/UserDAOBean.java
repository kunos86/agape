package pl.ksb.agape.domain.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.User;

@Stateless
public class UserDAOBean extends BaseDAO<User> {

	public boolean authenticat(String login, String haslo) {
		return ((Long) getHibernateSession().createCriteria(User.class)
				.add(Restrictions.eq("email", login))
				.add(Restrictions.eq("password", haslo))
				.setProjection(Projections.rowCount()).uniqueResult()) > 0;

	}

	public void changePassword(Long userId, String newPassword) {
		User u = getById(userId);
		u.setPassword(newPassword);
		saveOrUpdate(u);

	}

	public List<User> getAll() {
		return getHibernateSession().createCriteria(User.class).list();
	}

	public Long getCountStudentsWithoutTeacher() {

		return (Long) getEntityManager()
				.createQuery(
						"select count(u) from User u where not exists(Select id from StudentTeacher s where s.student.id = u.id and s.current = 'T') "
								+ "and exists (select r.id from Role r where r.user.id=u.id and r.roleName='STUDENT')")
				.getSingleResult();
	}

	public Long getCountUsers() {
		return (Long) getHibernateSession().createCriteria(User.class)
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	//
	// public Long liczbaUczniowByNauczyciel(Osoba osoba) {
	// return (Long) getUczniowieByNauczyciel(osoba).setProjection(
	// Projections.rowCount()).uniqueResult();
	// }
	//

	@SuppressWarnings("unchecked")
	public List<User> getStudentsByTeacher(Long teacherId) {

		return getHibernateSession()
				.createQuery(
						"from User u where exists(Select id from StudentTeacher s where s.student.id = u.id "
								+ "and s.current = 'T' and s.teacher.id = :teacherId) "
								+ "and exists (select r.id from Role r where r.user.id=u.id and r.roleName='STUDENT')")
				.setParameter("teacherId", teacherId).list();

	}

	@SuppressWarnings("unchecked")
	public List<User> getStudentsWithoutTeacher() {

		return getEntityManager()
				.createQuery(
						"select u from User u where not exists (Select id from StudentTeacher s where s.student.id = u.id and s.current = 'T') "
								+ "and exists (select r.id from Role r where r.user.id=u.id and r.roleName='STUDENT')")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> getTeachers() {
		return getEntityManager()
				.createQuery(
						"select u from User u where exists (select r.id from Role r where r.user.id=u.id and r.roleName='TEACHER') ")
				.getResultList();

	}

	public User getUserByMail(String mail) {

		return (User) getHibernateSession().createCriteria(User.class)
				.add(Restrictions.eq("email", mail)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers(int firstRow, int numRows) {
		return getEntityManager().createNamedQuery("getUsers")
				.setFirstResult(firstRow).setMaxResults(numRows)
				.getResultList();
	}

	public boolean isRegistered(String mail) {
		Long c = (Long) getHibernateSession().createCriteria(User.class)
				.add(Restrictions.eq("email", mail))
				.setProjection(Projections.rowCount()).uniqueResult();
		return c != null && c > 0;

	}

	@Override
	public void save(User osoba) {
		if (osoba.getId() == null) {
			osoba.setStatus("A");
			osoba.setRegistrationDate(new Date());
		}
		saveOrUpdate(osoba);

	}

	//
	// @SuppressWarnings("unchecked")
	// public List<Osoba> getNauczyciele() {
	// return hibernateSession
	// .createCriteria(Osoba.class)
	// .add(Restrictions.eq("status", Status.AKTUALNY))
	// .add(Restrictions.or(
	// Restrictions.eq("rodzajKonta", RodzajKonta.TEACHER),
	// Restrictions.eq("rodzajKonta", RodzajKonta.COORDINATOR)))
	// .addOrder(Order.asc("nazwisko")).list();
	// }

}
