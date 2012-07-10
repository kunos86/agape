package pl.ksb.agape.domain.dao;

import javax.ejb.Local;

import pl.ksb.agape.domain.model.UczenNauczyciel;

@Local
public interface UczenNauczycielDAOLocal {

	public void save(UczenNauczyciel uczenNauczyciel);

	public void update(UczenNauczyciel uczenNauczyciel);

}
