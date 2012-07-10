package pl.ksb.agape.view.bean.teacher;

import java.io.Serializable;
import java.util.Calendar;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;
import org.richfaces.model.selection.SimpleSelection;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.dao.UczenNauczycielDAOLocal;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.domain.model.UczenNauczyciel;
import pl.ksb.agape.session.DaneSesji;

@Name("czekajaceOsobyBrowser")
@Scope(ScopeType.PAGE)
public class CzekajaceOsobyBrowser implements Serializable {

	private static final long serialVersionUID = 1059215794829651479L;

	@Logger
	protected Log log;

	@In
	private OsobaDAOLocal osobaDAOLocal;

	@In
	private UczenNauczycielDAOLocal uczenNauczycielDAOLocal;

	@In
	private DaneSesji daneSesji;
	private SimpleSelection selected;
	private Osoba edytowany = new Osoba();
	private Long selectedId;

	public String edycja() {

		if (selected != null && selected.size() > 0) {
			Long selectedKey = (Long) selected.getKeys().next();
			edytowany = osobaDAOLocal.getById(selectedKey);
		}

		return "";
	}

	@Restrict("#{s:hasRole('COORDINATOR') || s:hasRole('TEACHER')}")
	public String zapisz() {
		UczenNauczyciel un = new UczenNauczyciel();
		un.setAktualny(true);
		un.setDataDodania(Calendar.getInstance().getTime());
		un.setUczen(edytowany);
		un.setNauczyciel(daneSesji.getZalogowany());
		uczenNauczycielDAOLocal.save(un);
		return "null";
	}

	public void select() {
		if (selected != null && selected.size() > 0) {
			selectedId = (Long) selected.getKeys().next();

		}

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

	public Long getSelectedId() {
		return selectedId;
	}

}
