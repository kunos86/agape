package pl.ksb.agape.domain.dao;

import java.util.List;

import javax.ejb.Local;

import org.hibernate.Criteria;

import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.domain.model.Osoba;

@Local
public interface KursDAOLocal {

	List<Kurs> wszystkieKursy();

	List<Kurs> getListaKursow();

	void save(Kurs kurs);

	void update(Kurs kurs);

	Kurs getById(Long id);

	Criteria wszystkieKursyCriteria();

	List<Kurs> getListaKursowByUczen(Osoba osoba);

}
