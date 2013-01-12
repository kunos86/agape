package pl.ksb.agape.view.bean.validator;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pl.ksb.agape.domain.dao.OsobaDAOBean;



@FacesValidator("uniqueEmailValidator")
public class UniqueEmailValidator implements Serializable, Validator {

	private static final long serialVersionUID = -4654359700527230138L;

	@EJB
	private OsobaDAOBean osobaDAOBean;
	
	
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException {
		String mail = (String) value;
		if (mail == null || mail.isEmpty()) {
			throw new ValidatorException(new FacesMessage("Pole jest wymagane"));
		}

		if (!mail
				.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			throw new ValidatorException(new FacesMessage(
					"Niepoprawny adres e-mail"));
		}


		try {
			osobaDAOBean = (OsobaDAOBean) new InitialContext().lookup("java:module/OsobaDAOBean");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (osobaDAOBean.isRegistered(mail)) {
			throw new ValidatorException(new FacesMessage(
					"Konto o podanym adresie email już istnieje"));

		}

	}
}
