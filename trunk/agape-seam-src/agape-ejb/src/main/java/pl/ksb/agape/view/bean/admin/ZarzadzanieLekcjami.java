package pl.ksb.agape.view.bean.admin;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;
import org.richfaces.model.selection.SimpleSelection;

import pl.ksb.agape.domain.dao.KursDAOLocal;
import pl.ksb.agape.domain.dao.LekcjaDAOLocal;
import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.domain.model.Lekcja;

@Name("zarzadzanieLekcjami")
@Scope(ScopeType.PAGE)
public class ZarzadzanieLekcjami implements Serializable {

	private static final long serialVersionUID = 5801226582923159849L;
	@In(required = false)
	private Long idKursParam;

	@In
	private KursDAOLocal kursDAOLocal;

	private Kurs kurs;

	private final static String OPERACJA_NOWY = "nowy";
	private final static String OPERACJA_EDYTUJ = "edycja";
	@Logger
	private Log log;

	@In
	private LekcjaDAOLocal lekcjaDAOLocal;

	private SimpleSelection selectedLekcja;

	private Lekcja edytowany = new Lekcja();
	private String operacja;

	@SuppressWarnings("unused")
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Long idLekcjaParam;

	@Create
	public void init() {
		kurs = kursDAOLocal.getById(idKursParam);
	}

	public String nowy() {
		operacja = OPERACJA_NOWY;
		edytowany = new Lekcja();
		return "";
	}

	public String edycja() {

		operacja = OPERACJA_EDYTUJ;
		if (selectedLekcja != null && selectedLekcja.size() > 0) {
			Long selectedKey = (Long) selectedLekcja.getKeys().next();

			log.info("wybrany : " + selectedKey);
			edytowany = lekcjaDAOLocal.getById(selectedKey);
		}

		return "";
	}

	public String listaPytan() {
		if (selectedLekcja != null && selectedLekcja.size() > 0) {
			idLekcjaParam = (Long) selectedLekcja.getKeys().next();
			return "/pages/admin/zarzadzaniePytaniami.seam";
		}
		return "";
	}

	@Restrict("#{s:hasRole('COORDINATOR')}")
	public String zapisz() {

		edytowany.setIdKursu(kurs.getId());
		if (operacja.equals(OPERACJA_NOWY)) {
			lekcjaDAOLocal.save(edytowany);
		} else {
			lekcjaDAOLocal.update(edytowany);
		}
		return "null";
	}

	public SimpleSelection getSelectedLekcja() {
		return selectedLekcja;
	}

	public void setSelectedLekcja(SimpleSelection selectedLekcja) {
		this.selectedLekcja = selectedLekcja;
	}

	public Lekcja getEdytowany() {
		return edytowany;
	}

	public void setEdytowany(Lekcja edytowany) {
		this.edytowany = edytowany;
	}

	public Kurs getKurs() {
		return kurs;
	}

	public Long getIdKurs() {
		return kurs.getId();
	}
}
