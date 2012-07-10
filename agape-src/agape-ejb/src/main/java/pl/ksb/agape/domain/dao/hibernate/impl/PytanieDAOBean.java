package pl.ksb.agape.domain.dao.hibernate.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.PytanieDAOLocal;
import pl.ksb.agape.domain.model.Pytanie;
import pl.ksb.agape.domain.model.dict.Status;

@Stateless
@Name("pytanieDAOLocal")
@AutoCreate
public class PytanieDAOBean extends HibernateBaseDAO<Pytanie> implements
		PytanieDAOLocal {

	private static final long serialVersionUID = 2029102023560164636L;

	public PytanieDAOBean() {
		super(Pytanie.class);
	}

	public void save(Pytanie pytanie) {
		pytanie.setDataArch(Calendar.getInstance().getTime());
		pytanie.setDataDodania(Calendar.getInstance().getTime());
		pytanie.setStatus(Status.AKTUALNY);
		hibernateSession.save(pytanie);

	}

	public void update(Pytanie pytanie) {
		pytanie.setDataArch(Calendar.getInstance().getTime());
		hibernateSession.update(pytanie);

	}

	public Criteria wszystkiePytaniaByLekcjaCriteria(Long idLekcja) {
		return hibernateSession.createCriteria(Pytanie.class)
				.add(Restrictions.eq("status", Status.AKTUALNY))
				.add(Restrictions.eq("nrLekcja", idLekcja));
	}

	@SuppressWarnings("unchecked")
	public List<Pytanie> wszystkiePytaniaByLekcja(Long idLekcja) {
		return hibernateSession.createCriteria(Pytanie.class)
				.add(Restrictions.eq("status", Status.AKTUALNY))
				.add(Restrictions.eq("nrLekcja", idLekcja))
				.addOrder(Order.asc("nrPytanie")).list();
	}
}
