package pl.ksb.agape.view.bean;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

import pl.ksb.agape.domain.model.dict.RodzajKonta;

@Scope(ScopeType.SESSION)
@Name("user")
@Startup
public class User implements Serializable {
	private static final long serialVersionUID = -285557761234464615L;
	private String login;
	private RodzajKonta rodzajKonta = RodzajKonta.VISITOR;
	private Long userId;
	private boolean zalogowany = false;

	public boolean isZalogowany() {
		return zalogowany;
	}

	public void setZalogowany(boolean zalogowany) {
		this.zalogowany = zalogowany;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public RodzajKonta getRodzajKonta() {
		return rodzajKonta;
	}

	public void setRodzajKonta(RodzajKonta rodzajKonta) {
		this.rodzajKonta = rodzajKonta;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isStudent() {
		return rodzajKonta.equals(RodzajKonta.STUDENT);
	}

	public boolean isTeacher() {
		return rodzajKonta.equals(RodzajKonta.TEACHER);
	}

	public boolean isCoordinator() {
		return rodzajKonta.equals(RodzajKonta.COORDINATOR);
	}

}
