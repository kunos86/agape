package pl.ksb.agape.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import pl.ksb.agape.domain.dao.OsobaDAOBean;
import pl.ksb.agape.domain.model.Osoba;

@Model
public class OsobaController {

	public OsobaController() {
	}

	private Osoba osoba;

	@Inject
	private FacesContext facesContext;

	@Inject
	private OsobaDAOBean osobaDAOBean;

	public void register() throws Exception {
		osobaDAOBean.save(osoba);
		facesContext.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Rejestracja zakończona powodzeniem. Zaloguj się, aby przejść do serwisu.",
				"Rejestracja zakończona"));
//		pushEvent.fire(String.format("New member added: %s (id: %d)",
//				newMember.getName(), newMember.getId()));
		initNewMember();
	}

	@PostConstruct
	public void initNewMember() {
		osoba = new Osoba();
	}
}
