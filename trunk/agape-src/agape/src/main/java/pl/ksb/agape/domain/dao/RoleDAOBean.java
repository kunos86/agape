package pl.ksb.agape.domain.dao;

import java.util.Calendar;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.ksb.agape.domain.model.Role;

@Stateless
@LocalBean
public class RoleDAOBean {

	@Inject
	private EntityManager em;
	
	public void save(Role rola){
		rola.setModificationDate(Calendar.getInstance().getTime());
		if (rola.getId()==null){
			em.persist(rola);
		}else{
			em.merge(rola);
		}
	}
}
