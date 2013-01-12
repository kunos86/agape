package pl.ksb.agape.domain.dao.hibernate.impl;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.UprawnieniaKursDAOLocal;
import pl.ksb.agape.domain.model.UprawnieniaKurs;

@Stateless
@Name("uprawnieniaKursDAOLocal")
@AutoCreate
public class UprawnieniaKursDAOBean extends HibernateBaseDAO<UprawnieniaKurs>
		implements UprawnieniaKursDAOLocal {

	private static final long serialVersionUID = -230692275901613455L;

	public UprawnieniaKursDAOBean() {
		super(UprawnieniaKurs.class);
	}

	public Criteria getUprawnieniaKursu(Long idKursu) {
		return hibernateSession.createCriteria(UprawnieniaKurs.class)
				.setFetchMode("osoba", FetchMode.EAGER)
				.createAlias("osoba", "osoba")
				.add(Restrictions.eq("idKurs", idKursu));
	}

	public void remove(UprawnieniaKurs uprawnieniaKurs) {
		hibernateSession.delete(uprawnieniaKurs);

	}

}
