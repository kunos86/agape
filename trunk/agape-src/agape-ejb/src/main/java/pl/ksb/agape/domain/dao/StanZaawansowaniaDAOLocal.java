package pl.ksb.agape.domain.dao;

import javax.ejb.Local;

import pl.ksb.agape.domain.model.StanZaawansowania;

@Local
public interface StanZaawansowaniaDAOLocal {
	StanZaawansowania getById(Long Id);

	StanZaawansowania getByIdLekcjaIdOsoba(Long idLekcja, Long idOsoba);

	void saveOrUpdate(StanZaawansowania stanZaawansowania);

}
