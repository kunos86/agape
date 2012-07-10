package pl.ksb.agape.domain.dao.hibernate.impl;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.ListaOsobDAOLocal;
import pl.ksb.agape.domain.model.Osoba;

@Stateless
@Name("listaOsobDAOBean")
@AutoCreate
public class ListaOsobDAOBean extends HibernateBaseDAO<Osoba> implements
		ListaOsobDAOLocal {

	private static final long serialVersionUID = 1225031789030001875L;

	public ListaOsobDAOBean() {
		super(Osoba.class);
	}

	public Criteria getListaOsob() {
		return this.hibernateSession.createCriteria(Osoba.class);
	}

}
