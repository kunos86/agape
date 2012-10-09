package pl.ksb.agape.domain.dao;

import javax.ejb.Local;

import org.hibernate.Criteria;

import pl.ksb.agape.domain.model.view.LekcjaDoSpr;

@Local
public interface LekcjaDoSprDAOLocal {
	LekcjaDoSpr getById(Long id);

	Criteria wszystkieLekcjeDoSprByIdNauczycielCriteria(Long idNauczciela);

	Long liczbaLekcjiDoSprawdzenia(Long idNauczciela);

}
