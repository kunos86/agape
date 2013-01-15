package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.ksb.agape.domain.model.Kurs;

/**
 * Session Bean implementation class KursDAOBean
 */
@Stateless
@LocalBean
public class KursDAOBean {

    /**
     * Default constructor. 
     */
    public KursDAOBean() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unused")
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    public Kurs getById(long id){
    	return em.find(Kurs.class, id);
    }
    
	@SuppressWarnings("unchecked")
	public List<Kurs> getListaKursow(boolean tylkoWidoczne){
    	Query q=null;
    	if (tylkoWidoczne){
    		q=em.createNamedQuery("getWidoczneKursy");
    	}else{
    		q=em.createNamedQuery("getWszystkieKursy");
    	}
    	return q.getResultList();
    }
	
	public void zapisz(Kurs kurs){
		
		kurs.setDataArch(Calendar.getInstance().getTime());
		kurs.setStatus("A");
		if (kurs.getId()==null){
			kurs.setDataDodania(Calendar.getInstance().getTime());
			em.persist(kurs);
		}else{
			em.merge(kurs);
		}
	}
	

	
}
