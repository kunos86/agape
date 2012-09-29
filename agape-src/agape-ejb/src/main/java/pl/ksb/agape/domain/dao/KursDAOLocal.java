package pl.ksb.agape.domain.dao;

import java.util.List;

import javax.ejb.Local;

import org.hibernate.Criteria;

import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.domain.model.Osoba;

@Local
public interface KursDAOLocal {

	/**
	 * lista wszystkich kurs�w
	 * 
	 * @return
	 */
	List<Kurs> wszystkieKursy();

	/**
	 * lista aktywnych kurs�w
	 * 
	 * @return
	 */
	List<Kurs> getListaKursow();

	/**
	 * lista aktywnych kurs�w dost�pnych dla nauczyciela
	 * 
	 * @param idOsoby
	 * @return
	 */
	List<Kurs> getListaKursowNauczyciela(Long idOsoby);

	void save(Kurs kurs);

	void update(Kurs kurs);

	Kurs getById(Long id);

	Criteria wszystkieKursyCriteria();

	List<Kurs> getListaKursowByUczen(Osoba osoba);

}
