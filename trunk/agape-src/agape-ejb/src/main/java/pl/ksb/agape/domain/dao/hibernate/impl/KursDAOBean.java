package pl.ksb.agape.domain.dao.hibernate.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import pl.ksb.agape.domain.dao.KursDAOLocal;
import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.domain.model.dict.Status;

@Stateless
@Name("kursDAOLocal")
@AutoCreate
public class KursDAOBean implements KursDAOLocal {

	@In
	protected Session hibernateSession;

	@Logger
	protected Log log;

	@SuppressWarnings("unchecked")
	public List<Kurs> wszystkieKursy() {

		return hibernateSession.createCriteria(Kurs.class)
				.add(Restrictions.eq("status", Status.AKTUALNY))
				.addOrder(Order.asc("nrKursu")).list();
	}

	public Criteria wszystkieKursyCriteria() {

		return hibernateSession.createCriteria(Kurs.class).add(
				Restrictions.eq("status", Status.AKTUALNY));
		// .addOrder(Order.asc("nrKursu"));
	}

	@SuppressWarnings("unchecked")
	public List<Kurs> getListaKursow() {
		List<Kurs> l = hibernateSession.createCriteria(Kurs.class)
				.add(Restrictions.eq("status", Status.AKTUALNY))
				.addOrder(Order.asc("nrKursu")).list();

		return l;
	}

	public void save(Kurs kurs) {
		kurs.setDataArch(Calendar.getInstance().getTime());
		kurs.setDataDodania(Calendar.getInstance().getTime());
		kurs.setStatus(Status.AKTUALNY);
		hibernateSession.save(kurs);

	}

	public void update(Kurs kurs) {
		kurs.setDataArch(Calendar.getInstance().getTime());
		hibernateSession.update(kurs);

	}

	public Kurs getById(Long id) {
		return (Kurs) hibernateSession.createCriteria(Kurs.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Kurs> getListaKursowByUczen(Osoba osoba) {
		// String hql = "from Kurs k inner join fetch k.lekcje as l WHERE l.";

		return hibernateSession.createCriteria(Kurs.class)
				.setFetchMode("lekcje", FetchMode.EAGER)
				.createAlias("lekcje", "l")
				.setFetchMode("l.stanyZaawansowania", FetchMode.EAGER)
				.createAlias("l.stanyZaawansowania", "st")
				.add(Restrictions.eq("st.osoba.id", osoba.getId()))
				.add(Restrictions.eq("widocznosc", true))
				.add(Restrictions.eq("l.widocznosc", true)).list();
	}

}
