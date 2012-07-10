package pl.ksb.agape.domain.dao;

import javax.ejb.Local;

import org.hibernate.Criteria;

import pl.ksb.agape.domain.model.Lekcja;

@Local
public interface LekcjaDAOLocal {
	void save(Lekcja lekcja);

	void update(Lekcja kurs);

	Lekcja getById(Long id);

	Criteria wszystkieLekcjeByKursCriteria(Long idKursu);
}
