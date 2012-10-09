package pl.ksb.agape.view.bean.teacher;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.KursDAOLocal;
import pl.ksb.agape.domain.dao.LekcjaDoSprDAOLocal;
import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.session.DaneSesji;

@Name("teacherHomeBrowser")
@Scope(ScopeType.PAGE)
public class TeacherHomeBrowser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7098638810960852132L;

	@In
	private DaneSesji daneSesji;

	@In
	private KursDAOLocal kursDAOLocal;

	@In
	private LekcjaDoSprDAOLocal lekcjaDoSprDAOLocal;

	@In
	private OsobaDAOLocal osobaDAOLocal;

	private Long liczbaUczniow;

	private Long liczbaNiesprawdzonychLekcji;

	private String listaKursowStr;

	@Create
	public void init() {
		List<Kurs> kursy = kursDAOLocal
				.getListaKursowNauczyciela(this.daneSesji.getZalogowany()
						.getId());
		listaKursowStr = "";
		for (Kurs k : kursy) {
			listaKursowStr += "Kurs nr." + k.getNrKursu() + " - "
					+ k.getTytul() + ", ";
		}
		liczbaNiesprawdzonychLekcji = lekcjaDoSprDAOLocal
				.liczbaLekcjiDoSprawdzenia(this.daneSesji.getZalogowany()
						.getId());

		liczbaUczniow = osobaDAOLocal.liczbaUczniowByNauczyciel(this.daneSesji
				.getZalogowany());

	}

	public Osoba getOsoba() {
		return daneSesji.getZalogowany();
	}

	public String getListaKursowStr() {
		return listaKursowStr;
	}

	public Long getLiczbaUczniow() {
		return liczbaUczniow;
	}

	public Long getLiczbaNiesprawdzonychLekcji() {
		return liczbaNiesprawdzonychLekcji;
	}

}
