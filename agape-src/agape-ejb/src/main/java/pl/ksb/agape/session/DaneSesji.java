package pl.ksb.agape.session;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

import pl.ksb.agape.domain.model.Osoba;

@Name("daneSesji")
@Scope(ScopeType.SESSION)
@Startup
public class DaneSesji implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2344010477219109313L;

	private Osoba zalogowany;

	public Osoba getZalogowany() {
		return zalogowany;
	}

	public void setZalogowany(Osoba zalogowany) {
		this.zalogowany = zalogowany;
	}

}
