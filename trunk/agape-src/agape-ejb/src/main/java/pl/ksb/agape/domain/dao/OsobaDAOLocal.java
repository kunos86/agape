package pl.ksb.agape.domain.dao;

import javax.ejb.Local;

import org.hibernate.Criteria;

import pl.ksb.agape.domain.model.Osoba;

@Local
public interface OsobaDAOLocal {
	void save(Osoba osoba);

	void update(Osoba osoba);

	Osoba authenticat(String login, String haslo);

	Criteria getOsoby(boolean usuniete);

	Osoba getById(Long id);

	Criteria getCzekajacyUczniowie();

	Criteria getUczniowieByNauczyciel(Osoba osoba);
}
