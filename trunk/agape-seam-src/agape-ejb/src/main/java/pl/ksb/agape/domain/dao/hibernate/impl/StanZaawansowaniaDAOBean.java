package pl.ksb.agape.domain.dao.hibernate.impl;

import javax.ejb.Stateless;

import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.StanZaawansowaniaDAOLocal;
import pl.ksb.agape.domain.model.StanZaawansowania;

@Stateless
@Name("stanZaawansowaniaDAOLocal")
@AutoCreate
public class StanZaawansowaniaDAOBean extends
		HibernateBaseDAO<StanZaawansowania> implements
		StanZaawansowaniaDAOLocal {

	private static final long serialVersionUID = -146477646621677585L;

	public StanZaawansowania getByIdLekcjaIdOsoba(Long idLekcja, Long idOsoba) {
		return (StanZaawansowania) hibernateSession
				.createCriteria(StanZaawansowania.class)
				.add(Restrictions.eq("lekcja.id", idLekcja))
				.add(Restrictions.eq("osoba.id", idOsoba)).uniqueResult();
	}

	public StanZaawansowaniaDAOBean() {
		super(StanZaawansowania.class);

	}

	@Override
	public void saveOrUpdate(StanZaawansowania t) {
		hibernateSession.saveOrUpdate(t);
		hibernateSession.flush();
	}

}
