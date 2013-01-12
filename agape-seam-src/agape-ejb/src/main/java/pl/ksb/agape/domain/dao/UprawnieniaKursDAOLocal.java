package pl.ksb.agape.domain.dao;

import javax.ejb.Local;

import org.hibernate.Criteria;

import pl.ksb.agape.domain.model.UprawnieniaKurs;

@Local
public interface UprawnieniaKursDAOLocal {
	Criteria getUprawnieniaKursu(Long idKursu);

	void remove(UprawnieniaKurs uprawnieniaKurs);

	void saveOrUpdate(UprawnieniaKurs uprawnieniaKurs);

}
