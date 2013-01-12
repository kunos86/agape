package pl.ksb.agape.domain.dao;

import javax.ejb.Local;

import org.hibernate.Criteria;

@Local
public interface ListaOsobDAOLocal {
	public Criteria getListaOsob();
}
