package pl.ksb.agape.service;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.ksb.agape.domain.dao.OsobaDAOBean;
import pl.ksb.agape.domain.model.Member;
import pl.ksb.agape.domain.model.Osoba;

/**
 * Session Bean implementation class UserManagement
 */
@Stateless
@LocalBean
public class UserManagement {

   
    public UserManagement() {
    }
    
    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    @EJB
    private OsobaDAOBean osobaDAOBean;
    
    public void register(Osoba osoba) throws Exception {
        log.info("Registering " + osoba.getEmail());
        osobaDAOBean.save(osoba);
    }
}
