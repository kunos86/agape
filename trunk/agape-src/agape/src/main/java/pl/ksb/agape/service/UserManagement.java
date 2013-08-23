package pl.ksb.agape.service;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.dao.RoleDAOBean;
import pl.ksb.agape.domain.model.Member;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.domain.model.Role;
import pl.ksb.agape.domain.model.dict.RoleEnum;

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

    
    @EJB
    private UserDAOBean userDAOBean;
    
	@Inject
	private RoleDAOBean roleDAOBean;
	
	@TransactionAttribute
    public void register(User user) throws Exception {
        log.info("Registering " + user.getEmail());
        userDAOBean.save(user);
		Role rola = new Role();
		rola.setUser(user);
		rola.setRoleName(RoleEnum.STUDENT);
		roleDAOBean.save(rola);
    }
}
