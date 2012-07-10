package pl.ksb.agape.view.datamodel.teacher;

import org.hibernate.Criteria;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.session.DaneSesji;
import pl.ksb.agape.view.datamodel.PaginatingDataModel;

@Name("uczniowieDataModel")
@Scope(ScopeType.EVENT)
public class UczniowieDataModel extends PaginatingDataModel<Osoba, Long> {

	private static final long serialVersionUID = -8981354069100180588L;
	@In
	private OsobaDAOLocal osobaDAOLocal;

	@In
	private DaneSesji daneSesji;

	@Override
	public Criteria createCriteriaQuery() {
		return osobaDAOLocal
				.getUczniowieByNauczyciel(daneSesji.getZalogowany());
	}

	@Override
	public Long getId(Osoba object) {
		return object.getId();
	}

	@Override
	public Osoba getObjectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
