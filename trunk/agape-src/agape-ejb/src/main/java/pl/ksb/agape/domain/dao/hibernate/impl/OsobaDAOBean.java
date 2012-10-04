package pl.ksb.agape.domain.dao.hibernate.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.domain.model.dict.RodzajKonta;
import pl.ksb.agape.domain.model.dict.Status;

@Stateless
@Name("osobaDAOLocal")
@AutoCreate
public class OsobaDAOBean implements OsobaDAOLocal, Serializable {

	private static final long serialVersionUID = -8786332250856753751L;

	@In
	protected Session hibernateSession;

	@Logger
	protected Log log;

	public void save(Osoba osoba) {
		osoba.setDataRej(new Date());
		osoba.setStatus(Status.AKTUALNY);
		osoba.setRodzajKonta(RodzajKonta.STUDENT);
		hibernateSession.save(osoba);

	}

	public void update(Osoba osoba) {
		hibernateSession.update(osoba);
	}

	public Osoba authenticat(String login, String haslo) {
		return (Osoba) hibernateSession.createCriteria(Osoba.class)
				.add(Restrictions.eq("email", login))
				.add(Restrictions.eq("haslo", haslo))
				.add(Restrictions.eq("status", Status.AKTUALNY)).uniqueResult();
	}

	public Criteria getOsoby(boolean usuniete) {
		Criteria ret = hibernateSession.createCriteria(Osoba.class);
		if (!usuniete) {
			ret.add(Restrictions.ne("status", Status.USUNIETY));
		}
		return ret;
	}

	public Osoba getById(Long id) {
		return (Osoba) hibernateSession.createCriteria(Osoba.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
	}

	public Criteria getCzekajacyUczniowie() {
		Criteria ret = hibernateSession.createCriteria(Osoba.class);
		ret.add(Restrictions.eq("status", Status.AKTUALNY));
		ret.add(Restrictions.eq("rodzajKonta", RodzajKonta.STUDENT));
		ret.add(Restrictions
				.sqlRestriction("not exists (select 1 from uczniowie_naucz n where n.id_ucznia = this_.id and n.aktualny='Y')"));
		return ret;
	}

	public Criteria getUczniowieByNauczyciel(Osoba osoba) {
		Criteria ret = hibernateSession.createCriteria(Osoba.class);
		ret.add(Restrictions.eq("status", Status.AKTUALNY));
		ret.add(Restrictions.eq("rodzajKonta", RodzajKonta.STUDENT));
		ret.add(Restrictions
				.sqlRestriction("exists (select 1 from uczniowie_naucz n where n.id_ucznia = this_.id and n.aktualny='Y' and n.id_nauczyciela = "
						+ osoba.getId() + ")"));
		return ret;
	}

	public boolean isRegistered(String mail) {
		boolean ret = false;
		@SuppressWarnings("unchecked")
		List<Osoba> osoby = hibernateSession.createCriteria(Osoba.class)
				.add(Restrictions.eq("email", mail)).list();
		if (osoby != null && !osoby.isEmpty()) {
			ret = true;
		}

		return ret;

	}

	@SuppressWarnings("unchecked")
	public List<Osoba> getNauczyciele() {
		return hibernateSession
				.createCriteria(Osoba.class)
				.add(Restrictions.eq("status", Status.AKTUALNY))
				.add(Restrictions.or(
						Restrictions.eq("rodzajKonta", RodzajKonta.TEACHER),
						Restrictions.eq("rodzajKonta", RodzajKonta.COORDINATOR)))
				.addOrder(Order.asc("nazwisko")).list();
	}

}
