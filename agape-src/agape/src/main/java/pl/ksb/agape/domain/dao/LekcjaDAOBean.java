package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.domain.model.Lekcja;

/**
 * Session Bean implementation class LekcjaDAOBeab
 */
@Stateless
@LocalBean
public class LekcjaDAOBean {

	@Inject
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public LekcjaDAOBean() {
    }
    
    public Lekcja getById(Long id){
    	return em.find(Lekcja.class, id);
    }
    
	@SuppressWarnings("unchecked")
	public List<Lekcja> getListaLekcji(Long idKursu, boolean tylkoWidoczne){
    	Query q=null;
    	if (tylkoWidoczne){
    		q=em.createNamedQuery("getWidoczneLekcjeKursu").setParameter("idKursu", idKursu);
    	}else{
    		q=em.createNamedQuery("getLekcjeKursu").setParameter("idKursu", idKursu);
    	}
    	return q.getResultList();
    }
	
	public void zapisz(Lekcja lekcja){
		
		lekcja.setDataArch(Calendar.getInstance().getTime());
		lekcja.setStatus("A");
		if (lekcja.getId()==null){
			lekcja.setDataDodania(Calendar.getInstance().getTime());
			em.persist(lekcja);
		}else{
			em.merge(lekcja);
		}
	}

}
