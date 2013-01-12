package pl.ksb.agape.view.bean.admin;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;
import org.richfaces.model.selection.SimpleSelection;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Osoba;

@Name("zarzadzanieOsobami")
@Scope(ScopeType.PAGE)
public class ZarzadzanieOsobami implements Serializable {

	private static final long serialVersionUID = 5171478372030576647L;

	@Logger
	protected Log log;

	@In
	private OsobaDAOLocal osobaDAOLocal;
	private SimpleSelection selected;
	private boolean pokazUsuniete = false;
	private Osoba edytowany = new Osoba();

	public String edycja() {

		if (selected != null && selected.size() > 0) {
			Long selectedKey = (Long) selected.getKeys().next();

			log.info("wybrany : " + selectedKey);
			edytowany = osobaDAOLocal.getById(selectedKey);
		}

		return "";
	}

	@Restrict("#{s:hasRole('COORDINATOR')}")
	public String zapisz() {
		osobaDAOLocal.update(edytowany);
		return "null";
	}

	public boolean isPokazUsuniete() {
		return pokazUsuniete;
	}

	public void setPokazUsuniete(boolean pokazUsuniete) {
		this.pokazUsuniete = pokazUsuniete;
	}

	public Osoba getEdytowany() {
		return edytowany;
	}

	public void setEdytowany(Osoba edytowany) {
		this.edytowany = edytowany;
	}

	public SimpleSelection getSelected() {
		return selected;
	}

	public void setSelected(SimpleSelection selected) {
		this.selected = selected;
	}

}
