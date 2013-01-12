package pl.ksb.agape.view.bean.teacher;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.richfaces.model.selection.SimpleSelection;

import pl.ksb.agape.domain.dao.LekcjaDoSprDAOLocal;
import pl.ksb.agape.domain.model.view.LekcjaDoSpr;

@Name("lekcjeDoSprBrowser")
@Scope(ScopeType.PAGE)
public class LekcjeDoSprBrowser implements Serializable {

	private static final long serialVersionUID = -8762275092105226840L;
	private SimpleSelection selection;
	private LekcjaDoSpr lekcja;

	@In
	private LekcjaDoSprDAOLocal lekcjaDoSprDAOLocal;

	@SuppressWarnings("unused")
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Long idLekcjaEdit;

	@SuppressWarnings("unused")
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Long idOsoba;

	public SimpleSelection getSelection() {
		return selection;
	}

	public void setSelection(SimpleSelection selection) {
		this.selection = selection;
	}

	public String sprawdz() {
		if (selection != null) {

			Long id = (Long) selection.getKeys().next();
			lekcja = lekcjaDoSprDAOLocal.getById(id);

			idLekcjaEdit = lekcja.getIdLekcja();
			idOsoba = lekcja.getIdOsoba();
			return "/pages/components/lekcja/lekcja.xhtml";

		}
		return "";

	}

}
