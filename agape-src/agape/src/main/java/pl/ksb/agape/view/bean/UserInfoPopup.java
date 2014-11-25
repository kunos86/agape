package pl.ksb.agape.view.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;



@ManagedBean
@RequestScoped
public class UserInfoPopup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9040752052454400084L;
	
	@EJB
	private UserDAOBean userDAOBean;
	
	private User user;

	public void load(Long userId){
		user = userDAOBean.getById(userId);
	}
	
	
	public User getUser() {
		return user;
	}
	
	
	

}
