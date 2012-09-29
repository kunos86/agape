package pl.ksb.agape.view.bean.teacher;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.KursDAOLocal;
import pl.ksb.agape.domain.dao.LekcjaDAOLocal;
import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.dao.StanZaawansowaniaDAOLocal;
import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.domain.model.Lekcja;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.domain.model.StanZaawansowania;
import pl.ksb.agape.view.bean.User;

@Name("udostepnianieBrowser")
@Scope(ScopeType.PAGE)
public class UdostepnianieBrowser implements Serializable {

	private static final long serialVersionUID = -3306667308817776768L;

	@In(required = false)
	private Long idOsoba;

	private Osoba osoba;
	private List<Kurs> kursy;

	@In
	private KursDAOLocal kursDAOLocal;

	@In
	private OsobaDAOLocal osobaDAOLocal;

	@In
	private LekcjaDAOLocal lekcjaDAOLocal;

	@In
	private StanZaawansowaniaDAOLocal stanZaawansowaniaDAOLocal;

	@In
	private User user;

	@Create
	public void init() {
		osoba = osobaDAOLocal.getById(idOsoba);
	}

	public List<Kurs> getKursy() {
		if (kursy == null) {
			kursy = kursDAOLocal.getListaKursowNauczyciela(user.getUserId());
		}
		return kursy;
	}

	public Osoba getOsoba() {
		return osoba;
	}

	public String getStan(Long idLekcja) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StanZaawansowania stan = stanZaawansowaniaDAOLocal
				.getByIdLekcjaIdOsoba(idLekcja, osoba.getId());

		if (stan == null) {
			return "Nieudostêpniona";
		}
		if (stan.getDataSprawdzenia() != null) {
			return "Sprawdzona (" + sdf.format(stan.getDataSprawdzenia()) + ")";
		}

		if (stan.getDataWyslania() != null) {
			return "Wys³ana do spr. (" + sdf.format(stan.getDataWyslania())
					+ ")";
		}
		if (stan.getDataOdczytania() != null) {
			return "Odczytana (" + sdf.format(stan.getDataOdczytania()) + ")";
		}
		if (stan.getDataUdostepnienia() != null) {
			return "Udostêpniona (" + sdf.format(stan.getDataUdostepnienia())
					+ ")";
		}

		return "stan nieznany";

	}

	public void udostepnij(Long idLekcji) {

		StanZaawansowania stan = new StanZaawansowania();
		stan.setOsoba(osoba);
		Lekcja lekcja = lekcjaDAOLocal.getById(idLekcji);
		stan.setLekcja(lekcja);

		stan.setDataUdostepnienia(Calendar.getInstance().getTime());
		stanZaawansowaniaDAOLocal.saveOrUpdate(stan);

	}
}
