package pl.ksb.agape.view.datalist;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Osoba;

@Name("nauczycieleDataList")
@Scope(ScopeType.PAGE)
public class NauczycieleDataList {
	@In
	private OsobaDAOLocal osobaDAOLocal;

	private List<Osoba> nauczyciele;

	@Create
	public void init() {
		nauczyciele = osobaDAOLocal.getNauczyciele();

	}

	public List<Osoba> getNauczyciele() {
		return nauczyciele;
	}

}
