package pl.ksb.agape.view.bean.uczen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;

import pl.ksb.agape.domain.dao.LekcjaDAOLocal;
import pl.ksb.agape.domain.dao.OdpowiedzDAOLocal;
import pl.ksb.agape.domain.dao.PytanieDAOLocal;
import pl.ksb.agape.domain.model.Lekcja;
import pl.ksb.agape.domain.model.Odpowiedz;
import pl.ksb.agape.domain.model.Pytanie;
import pl.ksb.agape.domain.model.type.PytanieOdpowiedz;
import pl.ksb.agape.session.DaneSesji;

/**
 * bean wype³nianej lekcji przez ucznia
 * 
 * @author Piotr
 * 
 */

@Scope(ScopeType.PAGE)
@Name("lekcjaBrowser")
public class LekcjaBrowser implements Serializable {

	private static final long serialVersionUID = 3790793384643405513L;

	// reguired ustawiony na false, aby mo¿na by³o
	// w metodzie init przekierowaæ na inn¹ stronê
	@In(required = false)
	private Long idLekcjaEdit;

	@In
	private PytanieDAOLocal pytanieDAOLocal;

	private List<PytanieOdpowiedz> pytaniaOdp;

	private Lekcja lekcja;
	@In
	private LekcjaDAOLocal lekcjaDAOLocal;

	@In
	private OdpowiedzDAOLocal odpowiedzDAOLocal;

	@In
	private DaneSesji daneSesji;

	@Create
	public String init() {
		if (idLekcjaEdit == null) {
			return "/pages/student/listaLekcji.xhtml";
		}
		lekcja = lekcjaDAOLocal.getById(idLekcjaEdit);
		pytaniaOdp = new ArrayList<PytanieOdpowiedz>();

		List<Pytanie> pytania = pytanieDAOLocal.wszystkiePytaniaByLekcja(lekcja
				.getId());

		for (Pytanie p : pytania) {

			Odpowiedz odp = odpowiedzDAOLocal.getByIdPytanieIdOsoba(p.getId(),
					daneSesji.getZalogowany().getId());

			if (odp == null) {
				odp = new Odpowiedz();
				odp.setIdOsoba(daneSesji.getZalogowany().getId());
				odp.setIdPytanie(p.getId());
			}
			PytanieOdpowiedz po = new PytanieOdpowiedz(p, odp);

			pytaniaOdp.add(po);

		}

		return "";

	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public List<PytanieOdpowiedz> getPytaniaOdp() {
		return pytaniaOdp;
	}

	public void setPytaniaOdp(List<PytanieOdpowiedz> pytaniaOdp) {
		this.pytaniaOdp = pytaniaOdp;
	}

	@Transactional
	public void zapisz() {
		for (PytanieOdpowiedz po : pytaniaOdp) {
			odpowiedzDAOLocal.save(po.getOdpowiedz());
		}
	}

}
