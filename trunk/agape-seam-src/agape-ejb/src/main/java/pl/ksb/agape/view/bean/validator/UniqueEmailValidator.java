package pl.ksb.agape.view.bean.validator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.validation.ValidatorFactory;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;

@Name("uniqueEmailValidator")
@Scope(ScopeType.CONVERSATION)
@org.jboss.seam.annotations.faces.Validator
@BypassInterceptors
public class UniqueEmailValidator implements Serializable, Validator {

	private static final long serialVersionUID = -4654359700527230138L;

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
		ValidatorFactory validatorFactory;
		OsobaDAOLocal osobaDAOLocal = (OsobaDAOLocal) Component
				.getInstance("osobaDAOLocal");
		if (osobaDAOLocal.isRegistered(mail)) {
			throw new ValidatorException(new FacesMessage(
					"Konto o podanym adresie email ju¿ istnieje"));

		}

	}
}
