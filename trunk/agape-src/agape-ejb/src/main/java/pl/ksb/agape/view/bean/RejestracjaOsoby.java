package pl.ksb.agape.view.bean;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Osoba;

@Name("rejestracja")
@Scope(ScopeType.PAGE)
public class RejestracjaOsoby implements Serializable {

	private static final long serialVersionUID = 3073828016819449376L;

	@Logger
	protected Log log;

	@In
	private OsobaDAOLocal osobaDAOLocal;
	private Osoba osoba = new Osoba();

	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	public String zapisz() {
		osobaDAOLocal.save(osoba);
		return "/zarejestrowano.seam";
	}

}
