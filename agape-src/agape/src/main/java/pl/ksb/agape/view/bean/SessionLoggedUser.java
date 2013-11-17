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

	public SessionLoggedUser() {
		super();
	}

	public SessionLoggedUser(User user) {
		super();
		this.user = user;
	}

	private User user;

	public User getUser() {
		System.out.println(this);
		return user;
	}

	public void setUser(User user) {
		System.out.println(this);
		this.user = user;
	}

}
