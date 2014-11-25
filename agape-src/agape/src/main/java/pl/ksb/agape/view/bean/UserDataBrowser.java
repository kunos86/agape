package pl.ksb.agape.view.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;


@ManagedBean
@ViewScoped
public class UserDataBrowser implements Serializable{
	
	

	private static final long serialVersionUID = -9196602030711630565L;

	@ManagedProperty(value = "#{sessionLoggedUser}")
	private SessionLoggedUser sessionLoggedUser;

	@EJB
	private UserDAOBean userDAOBean;

	private User user;
	
	
	
	@PostConstruct
	public void init(){
		user = sessionLoggedUser.getUser();
	}

	
	public void save(){
		userDAOBean.save(user);
		//sessionLoggedUser.setUser(user);
		FacesContext.getCurrentInstance().getExternalContext()
		.getSessionMap()
		.put("sessionLoggedUser", sessionLoggedUser);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage( "Dane zostały zapisane"));
	}
	
	public void setSessionLoggedUser(SessionLoggedUser sessionLoggedUser) {
		this.sessionLoggedUser = sessionLoggedUser;
	}

	public User getUser() {
		return user;
	}
	
	

}
