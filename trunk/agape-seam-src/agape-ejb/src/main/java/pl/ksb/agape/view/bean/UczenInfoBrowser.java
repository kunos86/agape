package pl.ksb.agape.view.bean;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Osoba;

@Scope(ScopeType.PAGE)
@Name("uczenInfoBrowser")
public class UczenInfoBrowser implements Serializable {

	private static final long serialVersionUID = -7084582505358500360L;

	@In
	private OsobaDAOLocal osobaDAOLocal;

	private Osoba osoba = new Osoba();

	private Long idUcznia;

	public void wczytajInfo() {
		osoba = osobaDAOLocal.getById(idUcznia);
	}

	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	public Long getIdUcznia() {
		return idUcznia;
	}

	public void setIdUcznia(Long idUcznia) {
		this.idUcznia = idUcznia;
	}

}
