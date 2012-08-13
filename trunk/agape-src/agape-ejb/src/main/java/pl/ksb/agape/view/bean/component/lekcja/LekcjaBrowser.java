package pl.ksb.agape.view.bean.component.lekcja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.LekcjaDAOLocal;
import pl.ksb.agape.domain.dao.OdpowiedzDAOLocal;
import pl.ksb.agape.domain.dao.PytanieDAOLocal;
import pl.ksb.agape.domain.dao.StanZaawansowaniaDAOLocal;
import pl.ksb.agape.domain.model.Lekcja;
import pl.ksb.agape.domain.model.Odpowiedz;
import pl.ksb.agape.domain.model.Pytanie;
import pl.ksb.agape.domain.model.StanZaawansowania;
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

	/**
	 * id ucznia, który rozwi¹za³ lekcjê, jeœli wartoœæ nie zostanie podana, w
	 * metodzie init pobierane jest id zalogowanej osoby
	 */
	@In(required = false)
	private Long idOsoba;

	@In
	private StanZaawansowaniaDAOLocal stanZaawansowaniaDAOLocal;

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

	private StanZaawansowania stan;

	@Create
	public String init() {
		if (idLekcjaEdit == null) {
			return "/pages/student/listaLekcji.xhtml";
		}
		lekcja = lekcjaDAOLocal.getById(idLekcjaEdit);
		pytaniaOdp = new ArrayList<PytanieOdpowiedz>();
		Long osoba = idOsoba;
		if (osoba == null) {
			osoba = daneSesji.getZalogowany().getId();
		}
		stan = stanZaawansowaniaDAOLocal.getByIdLekcjaIdOsoba(lekcja.getId(),
				osoba);

		/*
		 * oznaczenie lekcji jako odczytanej, wykonuje siê jeœli uczeñ wchodzi
		 * pierwszy raz w lekcjê.
		 */
		if (!stan.isCzyOdczytano()) {
			stan.setCzyOdczytano(true);
			stan.setDataOdczytania(Calendar.getInstance().getTime());
			stanZaawansowaniaDAOLocal.saveOrUpdate(stan);
		}

		List<Pytanie> pytania = pytanieDAOLocal.wszystkiePytaniaByLekcja(lekcja
				.getId());

		for (Pytanie p : pytania) {

			Odpowiedz odp = odpowiedzDAOLocal.getByIdPytanieIdOsoba(p.getId(),
					osoba);

			if (odp == null) {
				odp = new Odpowiedz();
				odp.setIdOsoba(osoba);
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

	public void zapisz() {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Lekcja zosta³a zapisana."));
		for (PytanieOdpowiedz po : pytaniaOdp) {
			odpowiedzDAOLocal.save(po.getOdpowiedz());
		}
	}

	public void wyslij() {

		for (PytanieOdpowiedz po : pytaniaOdp) {
			if (po.getOdpowiedz().getTresc() == null
					|| po.getOdpowiedz().getTresc().isEmpty()) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"Na wszytskie pytania musisz udzieliæ odpowiedzi."));
				return;
			}
		}

		zapisz();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Lekcja zosta³a wys³ana."));
		stan.setDataWyslania(Calendar.getInstance().getTime());
		stanZaawansowaniaDAOLocal.saveOrUpdate(stan);

	}

	public String sprawdz() {
		stan.setDataSprawdzenia(Calendar.getInstance().getTime());
		stanZaawansowaniaDAOLocal.saveOrUpdate(stan);

		return "";
	}

	public StanZaawansowania getStan() {
		return stan;
	}

	public boolean isCzyWyslano() {
		return (stan.getDataWyslania() != null);
	}

	public boolean isCzySprawdzono() {
		return (stan.getDataSprawdzenia() != null);
	}
}
