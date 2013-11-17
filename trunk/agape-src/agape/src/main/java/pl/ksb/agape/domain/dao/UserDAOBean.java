package pl.ksb.agape.domain.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.ksb.agape.domain.model.User;

@Stateless
public class UserDAOBean {

	@Inject
	private EntityManager em;

	public User getById(Long id) {
		return em.find(User.class, id);
	}

	public void save(User osoba) {
		if (osoba.getId() == null) {
			osoba.setStatus("A");
			osoba.setRegistrationDate(new Date());
			em.persist(osoba);
		} else {
			em.merge(osoba);
		}

	}

	public boolean isRegistered(String mail) {
		boolean ret = false;
		@SuppressWarnings("unchecked")
		List<User> osoby = em.createNamedQuery("getUserByMail")
				.setParameter("email", mail).getResultList();
		if (osoby != null && !osoby.isEmpty()) {
			ret = true;
		}

		return ret;

	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers(int firstRow, int numRows) {
		return em.createNamedQuery("getUsers").setFirstResult(firstRow)
				.setMaxResults(numRows).getResultList();
	}

	public User getUserByMail(String mail) {

		// List<User> osoby = ;

		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();
		//
		// Transaction tx = session.beginTransaction();
		//
		// User u = (User) session.createCriteria(User.class)
		// .add(Restrictions.eq(User_.email.getName(), mail))
		// .uniqueResult();
		//
		// session.close();
		// return u;

		return (User) em.createNamedQuery("getUserByMail")
				.setParameter("email", mail).getSingleResult();
	}

	public Long getCountUsers() {
		return (Long) em.createQuery("Select count(u) from User u")
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getStudentsWithoutTeacher() {

		return em
				.createQuery(
						"select u from User u where not exists (Select id from StudentTeacher s where s.student.id = u.id and s.current = 'T') "
								+ "and exists (select r.id from Role r where r.user.id=u.id and r.roleName='STUDENT')")
				.getResultList();
	}

	public Long getCountStudentsWithoutTeacher() {

		return (Long) em
				.createQuery(
						"select count(u) from User u where not exists(Select id from StudentTeacher s where s.student.id = u.id and s.current = 'T') "
								+ "and exists (select r.id from Role r where r.user.id=u.id and r.roleName='STUDENT')")
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getTeachers() {
		return em
				.createQuery(
						"select u from User u where exists (select r.id from Role r where r.user.id=u.id and r.roleName='TEACHER') ")
				.getResultList();

	}

	// public void update(Osoba osoba) {
	// hibernateSession.update(osoba);
	// }
	//
	// public Osoba authenticat(String login, String haslo) {
	// return (Osoba) em.createCriteria(Osoba.class)
	// .add(Restrictions.eq("email", login))
	// .add(Restrictions.eq("haslo", haslo))
	// .add(Restrictions.eq("status", Status.AKTUALNY)).uniqueResult();
	// }
	//
	// public Criteria getOsoby(boolean usuniete) {
	// Criteria ret = hibernateSession.createCriteria(Osoba.class);
	// if (!usuniete) {
	// ret.add(Restrictions.ne("status", Status.USUNIETY));
	// }
	// return ret;
	// }
	//
	// public Osoba getById(Long id) {
	// return (Osoba) hibernateSession.createCriteria(Osoba.class)
	// .add(Restrictions.eq("id", id)).uniqueResult();
	// }
	//
	// public Criteria getCzekajacyUczniowie() {
	// Criteria ret = hibernateSession.createCriteria(Osoba.class);
	// ret.add(Restrictions.eq("status", Status.AKTUALNY));
	// ret.add(Restrictions.eq("rodzajKonta", RodzajKonta.STUDENT));
	// ret.add(Restrictions
	// .sqlRestriction("not exists (select 1 from uczniowie_naucz n where n.id_ucznia = this_.id and n.aktualny='Y')"));
	// return ret;
	// }
	//
	// public Criteria getUczniowieByNauczyciel(Osoba osoba) {
	// Criteria ret = hibernateSession.createCriteria(Osoba.class);
	// ret.add(Restrictions.eq("status", Status.AKTUALNY));
	// ret.add(Restrictions.eq("rodzajKonta", RodzajKonta.STUDENT));
	// ret.add(Restrictions
	// .sqlRestriction("exists (select 1 from uczniowie_naucz n where n.id_ucznia = this_.id and n.aktualny='Y' and n.id_nauczyciela = "
	// + osoba.getId() + ")"));
	// return ret;
	// }
	//
	// public Long liczbaUczniowByNauczyciel(Osoba osoba) {
	// return (Long) getUczniowieByNauczyciel(osoba).setProjection(
	// Projections.rowCount()).uniqueResult();
	// }
	//
	// public boolean isRegistered(String mail) {
	// boolean ret = false;
	// @SuppressWarnings("unchecked")
	// List<Osoba> osoby = hibernateSession.createCriteria(Osoba.class)
	// .add(Restrictions.eq("email", mail)).list();
	// if (osoby != null && !osoby.isEmpty()) {
	// ret = true;
	// }
	//
	// return ret;
	//
	// }
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
