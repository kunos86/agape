package pl.ksb.agape.view.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import pl.ksb.agape.service.MailSenderService;

@ManagedBean
@RequestScoped
public class EditProfileBrowser {

	@EJB
	private MailSenderService mailSenderService;

}
