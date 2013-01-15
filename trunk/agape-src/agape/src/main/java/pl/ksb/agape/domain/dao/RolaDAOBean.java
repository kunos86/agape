package pl.ksb.agape.domain.dao;

import java.util.Calendar;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.ksb.agape.domain.model.Rola;

@Stateless
@LocalBean
public class RolaDAOBean {

	@Inject
	private EntityManager em;
	
	public void zapisz(Rola rola){
		rola.setDataArch(Calendar.getInstance().getTime());
		if (rola.getId()==null){
			em.persist(rola);
		}else{
			em.merge(rola);
		}
	}
}
