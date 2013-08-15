package pl.ksb.agape.domain.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;


import pl.ksb.agape.domain.model.Osoba;

@Stateless

public class OsobaDAOBean  {


	@Inject
	private EntityManager em;
	
	public Osoba getById(Long id){
		return em.find(Osoba.class, id);
	}
	   


	public void save(Osoba osoba) {
		if (osoba.getId()==null){
			osoba.setStatus("A");
			osoba.setDataRej(new Date());
			em.persist(osoba);
		}else{
			em.merge(osoba);
		}

	}
	
	
	
	
	
	
	
	public boolean isRegistered(String mail) {
		boolean ret = false;
		@SuppressWarnings("unchecked")
		List<Osoba> osoby = em.createNamedQuery("getOsobaMail").setParameter("email", mail).getResultList();
		if (osoby != null && !osoby.isEmpty()) {
			ret = true;
		}

		return ret;

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Osoba> getWszystkieOsoby(int firstRow, int numRows){
		return em.createNamedQuery("getWszystkieOsoby").setFirstResult(firstRow).setMaxResults(numRows).getResultList();	
	}

	
	public Long getLiczbaOsob(){
		return (Long) em.createQuery("Select count(o) from Osoba o").getSingleResult();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Osoba> getOsobyBezNauczyciela(){
		
			
		return em.createQuery("select o from Osoba o where not exists (Select id from UczenNauczyciel u where u.uczen.id = o.id and aktualny = 'T') ").getResultList();
	}
	
	
	public Long getLiczbaOsobyBezNauczyciela(){
		
			
		return (Long)em.createQuery("select count(o) from Osoba o where not exists (Select id from UczenNauczyciel u where u.uczen.id = o.id and aktualny = 'T') ").getSingleResult();
	}
	
	public List<Osoba> getListaNauczycieli(){
		return em.createQuery("select o from Osoba o where exists (Select id from UczenNauczyciel u where u.uczen.id = o.id and aktualny = 'T') ").getResultList();

	}

	
	
	
//	public void update(Osoba osoba) {
//		hibernateSession.update(osoba);
//	}
//
//	public Osoba authenticat(String login, String haslo) {
//		return (Osoba) em.createCriteria(Osoba.class)
//				.add(Restrictions.eq("email", login))
//				.add(Restrictions.eq("haslo", haslo))
//				.add(Restrictions.eq("status", Status.AKTUALNY)).uniqueResult();
//	}
//
//	public Criteria getOsoby(boolean usuniete) {
//		Criteria ret = hibernateSession.createCriteria(Osoba.class);
//		if (!usuniete) {
//			ret.add(Restrictions.ne("status", Status.USUNIETY));
//		}
//		return ret;
//	}
//
//	public Osoba getById(Long id) {
//		return (Osoba) hibernateSession.createCriteria(Osoba.class)
//				.add(Restrictions.eq("id", id)).uniqueResult();
//	}
//
//	public Criteria getCzekajacyUczniowie() {
//		Criteria ret = hibernateSession.createCriteria(Osoba.class);
//		ret.add(Restrictions.eq("status", Status.AKTUALNY));
//		ret.add(Restrictions.eq("rodzajKonta", RodzajKonta.STUDENT));
//		ret.add(Restrictions
//				.sqlRestriction("not exists (select 1 from uczniowie_naucz n where n.id_ucznia = this_.id and n.aktualny='Y')"));
//		return ret;
//	}
//
//	public Criteria getUczniowieByNauczyciel(Osoba osoba) {
//		Criteria ret = hibernateSession.createCriteria(Osoba.class);
//		ret.add(Restrictions.eq("status", Status.AKTUALNY));
//		ret.add(Restrictions.eq("rodzajKonta", RodzajKonta.STUDENT));
//		ret.add(Restrictions
//				.sqlRestriction("exists (select 1 from uczniowie_naucz n where n.id_ucznia = this_.id and n.aktualny='Y' and n.id_nauczyciela = "
//						+ osoba.getId() + ")"));
//		return ret;
//	}
//
//	public Long liczbaUczniowByNauczyciel(Osoba osoba) {
//		return (Long) getUczniowieByNauczyciel(osoba).setProjection(
//				Projections.rowCount()).uniqueResult();
//	}
//
//	public boolean isRegistered(String mail) {
//		boolean ret = false;
//		@SuppressWarnings("unchecked")
//		List<Osoba> osoby = hibernateSession.createCriteria(Osoba.class)
//				.add(Restrictions.eq("email", mail)).list();
//		if (osoby != null && !osoby.isEmpty()) {
//			ret = true;
//		}
//
//		return ret;
//
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Osoba> getNauczyciele() {
//		return hibernateSession
//				.createCriteria(Osoba.class)
//				.add(Restrictions.eq("status", Status.AKTUALNY))
//				.add(Restrictions.or(
//						Restrictions.eq("rodzajKonta", RodzajKonta.TEACHER),
//						Restrictions.eq("rodzajKonta", RodzajKonta.COORDINATOR)))
//				.addOrder(Order.asc("nazwisko")).list();
//	}

}
