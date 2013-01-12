package pl.ksb.agape.view.bean.admin;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;
import org.richfaces.model.selection.SimpleSelection;

import pl.ksb.agape.domain.dao.KursDAOLocal;
import pl.ksb.agape.domain.model.Kurs;

@Name("zarzadzanieKursami")
@Scope(ScopeType.PAGE)
public class ZarzadzanieKursami implements Serializable {

	private static final long serialVersionUID = 6830913670205225180L;
	private final static String OPERACJA_NOWY = "nowy";
	private final static String OPERACJA_EDYTUJ = "edycja";
	@Logger
	private Log log;

	@In
	private KursDAOLocal kursDAOLocal;

	@SuppressWarnings("unused")
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Long idKursParam;

	private SimpleSelection selectedKurs;
	private Kurs edytowany = new Kurs();
	private String operacja;

	public String nowy() {
		operacja = OPERACJA_NOWY;
		edytowany = new Kurs();
		return "";
	}

	public String edycja() {

		operacja = OPERACJA_EDYTUJ;
		if (selectedKurs != null && selectedKurs.size() > 0) {
			Long selectedKey = (Long) selectedKurs.getKeys().next();

			log.info("wybrany : " + selectedKey);
			edytowany = kursDAOLocal.getById(selectedKey);
		}

		return "";
	}

	public String listaLekcji() {
		return "/pages/admin/zarzadzanieLekcjami.seam";
	}

	public void select() {
		idKursParam = (Long) selectedKurs.getKeys().next();
	}

	public void usunObrazek() {
		edytowany.setImage(null);
	}

	@Restrict("#{s:hasRole('COORDINATOR')}")
	public String zapiszKurs() {

		if (operacja.equals(OPERACJA_NOWY)) {
			kursDAOLocal.save(edytowany);
		} else {
			kursDAOLocal.update(edytowany);
		}
		return "null";
	}

	public SimpleSelection getSelectedKurs() {
		return selectedKurs;
	}

	public void setSelectedKurs(SimpleSelection selectedKurs) {
		this.selectedKurs = selectedKurs;
	}

	public Kurs getEdytowany() {
		return edytowany;
	}

	public void setEdytowany(Kurs edytowany) {
		this.edytowany = edytowany;
	}

}
