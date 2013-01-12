package pl.ksb.agape.view.bean.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.model.selection.SimpleSelection;

import pl.ksb.agape.domain.dao.UprawnieniaKursDAOLocal;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.domain.model.UprawnieniaKurs;

@Name("uprawnieniaKursyBrowser")
@Scope(ScopeType.PAGE)
public class UprawnieniaKursyBrowser {

	private SimpleSelection selectedKurs;
	private SimpleSelection selectedNauczyciel;
	private Long wybraneIdKursu;
	private List<Osoba> wybraniNauczyciele = new ArrayList<Osoba>();

	@In
	private UprawnieniaKursDAOLocal uprawnieniaKursDAOLocal;

	public List<Osoba> getWybraniNauczyciele() {
		return wybraniNauczyciele;
	}

	public void setWybraniNauczyciele(List<Osoba> wybraniNauczyciele) {
		this.wybraniNauczyciele = wybraniNauczyciele;
	}

	public SimpleSelection getSelectedNauczyciel() {
		return selectedNauczyciel;
	}

	public void setSelectedNauczyciel(SimpleSelection selectedNauczyciel) {
		this.selectedNauczyciel = selectedNauczyciel;
	}

	public SimpleSelection getSelectedKurs() {
		return selectedKurs;
	}

	public void setSelectedKurs(SimpleSelection selectedKurs) {
		this.selectedKurs = selectedKurs;
	}

	public Long getSelectedIdKurs() {

		if (selectedKurs != null && selectedKurs.getKeys().hasNext()) {
			wybraneIdKursu = (Long) selectedKurs.getKeys().next();
		}
		return wybraneIdKursu;
	}

	public void dodajUprawnienia() {
		for (Osoba osoba : wybraniNauczyciele) {
			UprawnieniaKurs upraw = new UprawnieniaKurs();

			upraw.setDataZm(Calendar.getInstance().getTime());
			upraw.setOsoba(osoba);
			upraw.setIdKurs(getSelectedIdKurs());
			uprawnieniaKursDAOLocal.saveOrUpdate(upraw);
		}
		wybraniNauczyciele = new ArrayList<Osoba>();
	}

}
