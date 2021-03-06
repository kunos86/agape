package pl.ksb.agape.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;

@Model
public class UserController {

	public UserController() {
	}

	private User osoba;

	@Inject
	private FacesContext facesContext;

	@Inject
	private UserDAOBean osobaDAOBean;

	public void register() throws Exception {
		osobaDAOBean.save(osoba);
		facesContext.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Rejestracja zakończona powodzeniem. Zaloguj się, aby przejść do serwisu.",
				"Rejestracja zakończona"));
		initNewMember();
	}

	@PostConstruct
	public void initNewMember() {
		osoba = new User();
	}
}
