package pl.ksb.agape.view.bean.uczen;

import java.io.Serializable;
import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import pl.ksb.agape.domain.dao.KursDAOLocal;
import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.session.DaneSesji;

@Name("listaLekcjiBrowser")
public class ListaLekcjiBrowser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -101813579593609227L;

	@SuppressWarnings("unused")
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Long idLekcjaEdit;

	@In
	private KursDAOLocal kursDAOLocal;

	@In
	private DaneSesji daneSesji;

	Collection<Kurs> kursy;

	@Create
	public void init() {
		System.out.println("Create!!!!!");
		kursy = kursDAOLocal.getListaKursowByUczen(daneSesji.getZalogowany());
	}

	public int getLiczbaKursow() {
		return kursy == null ? 0 : kursy.size();
	}

	public Collection<Kurs> getKursy() {
		return kursy;
	}

	public void setKursy(Collection<Kurs> kursy) {
		this.kursy = kursy;
	}

	public String wyswietlLekcje(Long idLekcji) {
		idLekcjaEdit = idLekcji;
		return "/pages/components/lekcja/lekcja.xhtml";
	}
}
