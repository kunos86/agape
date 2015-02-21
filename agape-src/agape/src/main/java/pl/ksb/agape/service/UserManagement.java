package pl.ksb.agape.service;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import pl.ksb.agape.domain.dao.UserDAOBean;
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
    
	
	@TransactionAttribute
    public void register(User user) throws Exception {
        log.info("Registering " + user.getEmail());
        for(RoleEnum enu : RoleEnum.values()){
        	Role rola = new Role();
        	rola.setUser(user);
        	rola.setRoleName(enu);
        	if (enu.equals(RoleEnum.STUDENT)){
        		rola.setEnabled(Boolean.TRUE);
        	}
        	user.addRole(rola);
        	
        }
        user.setSendMail(Boolean.TRUE);
		userDAOBean.save(user);

    }
}
