package pl.ksb.agape.domain.dao;

import javax.ejb.Local;

import pl.ksb.agape.domain.model.Odpowiedz;

@Local
public interface OdpowiedzDAOLocal {

	Odpowiedz getById(Long id);

	Odpowiedz getByIdPytanieIdOsoba(Long idPytanie, Long idOsoba);

	void save(Odpowiedz odp);

}
