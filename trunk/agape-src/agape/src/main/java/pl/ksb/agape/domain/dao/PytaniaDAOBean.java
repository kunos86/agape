package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.ksb.agape.domain.model.Lekcja;
import pl.ksb.agape.domain.model.Pytanie;

@Stateless
@Named@LocalBean
public class PytaniaDAOBean {
	
	
	@Inject
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public PytaniaDAOBean() {
    }
    
    public Pytanie getById(Long id){
    	return em.find(Pytanie.class,id);
    }
    
	@SuppressWarnings("unchecked")
	public List<Pytanie> getListaPytan(Long idLekcja, boolean tylkoWidoczne){
    	Query q=null;
    	if (tylkoWidoczne){
    		q=em.createNamedQuery("getWidocznePytaniaLekcji").setParameter("idLekcja", idLekcja);
    	}else{
    		q=em.createNamedQuery("getPytaniaLekcji").setParameter("idLekcja", idLekcja);
    	}
    	return q.getResultList();
    }
	
	public void zapisz(Pytanie pytanie){
		
		pytanie.setDataArch(Calendar.getInstance().getTime());
		pytanie.setStatus("A");
		if (pytanie.getId()==null){
			pytanie.setDataDodania(Calendar.getInstance().getTime());
			em.persist(pytanie);
		}else{
			em.merge(pytanie);
		}
	}

}
