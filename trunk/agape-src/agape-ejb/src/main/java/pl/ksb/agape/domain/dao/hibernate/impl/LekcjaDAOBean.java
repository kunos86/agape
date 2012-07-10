package pl.ksb.agape.domain.dao.hibernate.impl;

import java.util.Calendar;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.LekcjaDAOLocal;
import pl.ksb.agape.domain.model.Lekcja;
import pl.ksb.agape.domain.model.dict.Status;

@Stateless
@Name("lekcjaDAOLocal")
@AutoCreate
public class LekcjaDAOBean extends HibernateBaseDAO<Lekcja> implements
		LekcjaDAOLocal {

	private static final long serialVersionUID = -8304889277396702888L;

	public LekcjaDAOBean() {
		super(Lekcja.class);
	}

	public void save(Lekcja lekcja) {
		lekcja.setDataArch(Calendar.getInstance().getTime());
		lekcja.setDataDodania(Calendar.getInstance().getTime());
		lekcja.setStatus(Status.AKTUALNY);
		hibernateSession.save(lekcja);

	}

	public void update(Lekcja lekcja) {
		lekcja.setDataArch(Calendar.getInstance().getTime());
		hibernateSession.update(lekcja);

	}

	@Override
	public Lekcja getById(Long id) {

		return (Lekcja) hibernateSession.createCriteria(Lekcja.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
	}

	public Criteria wszystkieLekcjeByKursCriteria(Long idKursu) {
		return hibernateSession.createCriteria(Lekcja.class)
				.add(Restrictions.eq("status", Status.AKTUALNY))
				.add(Restrictions.eq("idKursu", idKursu));
	}
}
