package pl.ksb.agape.view.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;


@Model
public class ResetPassword  implements Serializable{
	
	private static final long serialVersionUID = 3036591875881808361L;

	@EJB
	private UserDAOBean userDAOBean;
	
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void resetPassword(){
		User user = userDAOBean.getUserByMail(address);
		if (user!=null){
			userDAOBean.resetPassword(user.getId());
		}
		address=null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nowe hasło zostało wysłane na Twoją pocztę email!"));
	}
	
	
	
	
}
