package pl.ksb.agape.view.datalist;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.KursDAOLocal;
import pl.ksb.agape.domain.model.Kurs;

@Name("listaKursow")
@Scope(ScopeType.PAGE)
public class KursList {

	@In
	private KursDAOLocal kursDAOLocal;

	private List<Kurs> kursy;

	public List<Kurs> getKursy() {
		System.out.println("Kursy size: " + kursy.size());
		return kursy;
	}

	public void setKursy(List<Kurs> kursy) {
		this.kursy = kursy;
	}

	@Create
	public void init() {
		kursy = kursDAOLocal.getListaKursow();
	}

}
