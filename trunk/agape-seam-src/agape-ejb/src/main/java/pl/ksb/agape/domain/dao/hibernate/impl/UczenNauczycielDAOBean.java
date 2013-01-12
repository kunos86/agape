package pl.ksb.agape.domain.dao.hibernate.impl;

import javax.ejb.Stateless;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.UczenNauczycielDAOLocal;
import pl.ksb.agape.domain.model.UczenNauczyciel;

@Stateless
@Name("uczenNauczycielDAOLocal")
@AutoCreate
public class UczenNauczycielDAOBean extends HibernateBaseDAO<UczenNauczyciel>
		implements UczenNauczycielDAOLocal {

	private static final long serialVersionUID = 8721845888423129600L;

	public UczenNauczycielDAOBean() {
		super(UczenNauczyciel.class);
	}

	public void save(UczenNauczyciel uczenNauczyciel) {
		hibernateSession.save(uczenNauczyciel);

	}

	public void update(UczenNauczyciel uczenNauczyciel) {
		hibernateSession.update(uczenNauczyciel);

	}

}
