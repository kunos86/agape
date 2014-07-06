package pl.ksb.agape.view.bean;

import java.io.Serializable;

import javax.ejb.Startup;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.ksb.agape.domain.model.User;

@ManagedBean
@SessionScoped
@Startup
public class SessionLoggedUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2033110618445819660L;

	private User user;

	public SessionLoggedUser() {
		super();
	}

	public SessionLoggedUser(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
