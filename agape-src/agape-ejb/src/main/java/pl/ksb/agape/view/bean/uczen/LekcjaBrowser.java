package pl.ksb.agape.view.bean.uczen;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.LekcjaDAOLocal;
import pl.ksb.agape.domain.model.Lekcja;

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

	private Lekcja lekcja;
	@In
	private LekcjaDAOLocal lekcjaDAOLocal;

	@Create
	public String init() {
		if (idLekcjaEdit == null) {
			return "/pages/student/listaLekcji.xhtml";
		}
		lekcja = lekcjaDAOLocal.getById(idLekcjaEdit);
		return "";

	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

}
