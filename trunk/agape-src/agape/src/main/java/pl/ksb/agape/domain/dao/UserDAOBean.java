package pl.ksb.agape.domain.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.service.MailSenderService;
import pl.ksb.agape.service.ResetPasswordMailSender;
import pl.ksb.agape.util.CryptoTools;
import pl.ksb.agape.view.bean.datamodel.filter.UserFilter;

@Stateless
public class UserDAOBean extends BaseDAO<User> {

	@Inject
	private Logger log;

	@EJB
	private ResetPasswordMailSender resetPasswordMailSender;

	public boolean authenticat(String login, String haslo) {
		return ((Long) getHibernateSession().createCriteria(User.class)
				.add(Restrictions.eq("email", login))
				.add(Restrictions.eq("password", haslo))
				.setProjection(Projections.rowCount()).uniqueResult()) > 0;

	}

	public void changePassword(Long userId, String newPassword) {
		User u = getById(userId);
		u.setPassword(CryptoTools.getInstance().passwordHash(newPassword));
		saveOrUpdate(u);

	}

	public void resetPassword(Long userId) {
		User u = getById(userId);
		if (u != null) {
			String newPassword = CryptoTools.getInstance().randomPassword();
			resetPasswordMailSender.sendMail(u, newPassword);
			u.setPassword(CryptoTools.getInstance().passwordHash(newPassword));
			saveOrUpdate(u);
			log.info("Reset password for " + u.getEmail());
		}

	}

	public List<User> getAll() {
		return getHibernateSession().createCriteria(User.class).list();
	}

	public Long getCountStudentsWithoutTeacher() {

		return (Long) getEntityManager()
				.createQuery(
						"select count(u) from User u where u.mailConfirmation  = true and  not exists(Select id from StudentTeacher s where s.student.id = u.id and s.current = 'T') "
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
						"select u from User u where  u.mailConfirmation = true and  not exists (Select id from StudentTeacher s where s.student.id = u.id and s.current = 'T') "
								+ "and exists (select r.id from Role r where r.user.id=u.id and r.roleName='STUDENT')")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> getTeachers() {
		return getEntityManager()
				.createQuery(
						"select u from User u where  u.mailConfirmation = true and  exists (select r.id from Role r where r.user.id=u.id and r.roleName='TEACHER') ")
				.getResultList();

	}

	public User getUserByMail(String mail) {

		return (User) getHibernateSession().createCriteria(User.class)
				.add(Restrictions.eq("email", mail)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers(int firstRow, int numRows, UserFilter filter) {

		Criteria c = getHibernateSession().createCriteria(User.class);

		if (filter.getName() != null && !filter.getName().isEmpty()) {
			c.add(Restrictions.like("name", filter.getName() + "%").ignoreCase());
		}
		if (filter.getSurname() != null && !filter.getSurname().isEmpty()) {
			c.add(Restrictions.like("surname", filter.getSurname() + "%").ignoreCase());
		}
		if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
			c.add(Restrictions.like("email", filter.getEmail() + "%").ignoreCase());
		}
		c.setFirstResult(firstRow).setMaxResults(numRows)
				.addOrder(Order.asc("name")).addOrder(Order.asc("email"));
		return c.list();
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
