package pl.ksb.agape.domain.dao.hibernate.impl;

import java.util.Calendar;

import javax.ejb.Stateless;

import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.OdpowiedzDAOLocal;
import pl.ksb.agape.domain.model.Odpowiedz;

@Stateless
@Name("odpowiedzDAOLocal")
@AutoCreate
public class OdpowiedzDAOBean extends HibernateBaseDAO<Odpowiedz> implements
		OdpowiedzDAOLocal {

	private static final long serialVersionUID = -1266205759018147790L;

	public OdpowiedzDAOBean() {
		super(Odpowiedz.class);
	}

	public Odpowiedz getByIdPytanieIdOsoba(Long idPytanie, Long idOsoba) {

		return (Odpowiedz) hibernateSession.createCriteria(Odpowiedz.class)
				.add(Restrictions.eq("idPytanie", idPytanie))
				.add(Restrictions.eq("idOsoba", idOsoba)).uniqueResult();
	}

	public void save(Odpowiedz odp) {
		odp.setDataArch(Calendar.getInstance().getTime());
		hibernateSession.saveOrUpdate(odp);

	}

}
