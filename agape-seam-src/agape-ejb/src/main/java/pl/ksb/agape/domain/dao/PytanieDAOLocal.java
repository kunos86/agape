package pl.ksb.agape.domain.dao;

import java.util.List;

import javax.ejb.Local;

import org.hibernate.Criteria;

import pl.ksb.agape.domain.model.Pytanie;

@Local
public interface PytanieDAOLocal {

	void save(Pytanie pytanie);

	void update(Pytanie pytanie);

	Pytanie getById(Long id);

	Criteria wszystkiePytaniaByLekcjaCriteria(Long idLekcja);

	List<Pytanie> wszystkiePytaniaByLekcja(Long idLekcja);
}
