package pl.ksb.agape.view.bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.LekcjaDAOLocal;
import pl.ksb.agape.domain.dao.PytanieDAOLocal;
import pl.ksb.agape.domain.model.Lekcja;
import pl.ksb.agape.domain.model.Pytanie;

@Name("zarzadzaniePytaniami")
@Scope(ScopeType.PAGE)
public class ZarzadniePytaniami implements Serializable {

	private static final long serialVersionUID = -9169044509459204297L;

	@In(required = false)
	private Long idLekcjaParam;
	private Lekcja lekcja;

	private Pytanie edytowanePytanie;

	@In
	private PytanieDAOLocal pytanieDAOLocal;

	@In
	private LekcjaDAOLocal lekcjaDAOLocal;

	@Create
	public void init() {
		lekcja = lekcjaDAOLocal.getById(idLekcjaParam);

	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public Pytanie getEdytowanePytanie() {
		return edytowanePytanie;
	}

	public void setEdytowanePytanie(Pytanie edytowanePytanie) {
		this.edytowanePytanie = edytowanePytanie;
	}

	public List<Pytanie> getPytania() {
		List<Pytanie> pytania = pytanieDAOLocal.wszystkiePytaniaByLekcja(lekcja
				.getId());
		if (pytania == null) {
			pytania = new ArrayList<Pytanie>();
		}
		return pytania;
	}

	public void nowe() {
		edytowanePytanie = new Pytanie();
	}

	public void edytuj(Long id) {
		edytowanePytanie = pytanieDAOLocal.getById(id);

	}

	public void save() {
		edytowanePytanie.setNrLekcja(lekcja.getId());
		if (edytowanePytanie.getId() == null) {
			pytanieDAOLocal.save(edytowanePytanie);
		} else {
			pytanieDAOLocal.update(edytowanePytanie);
		}
	}

}
