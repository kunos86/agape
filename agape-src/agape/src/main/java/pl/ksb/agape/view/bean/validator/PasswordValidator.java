package pl.ksb.agape.view.bean.validator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;



@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator, Serializable {

	private static final long serialVersionUID = -3153968932785488353L;

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		

		String passwordId = (String) component.getAttributes().get("passId");

		// Find the actual JSF component for the client ID.
		UIInput passwordInput = (UIInput) context.getViewRoot().findComponent(
				passwordId);

		if (passwordInput != null) {
			// Get its value, the entered password of the first field.
			String password = (String) passwordInput.getValue();

			// Cast the value of the entered password of the second field back
			// to String.
			String confirm = (String) value;

			// Compare the first password with the second password.
			if (!password.equals(confirm)) {
				throw new ValidatorException(
						new FacesMessage("Hasła są różne !"));
			}

		}

	}

}
