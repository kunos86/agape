package pl.ksb.agape.view.datamodel.teacher;

import org.hibernate.Criteria;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.view.datamodel.PaginatingDataModel;

@Name("czekajaceOsobyDataModel")
@Scope(ScopeType.EVENT)
public class CzekajaceOsobyDataModel extends PaginatingDataModel<Osoba, Long> {

	private static final long serialVersionUID = 1569935425542436454L;
	@In
	private OsobaDAOLocal osobaDAOLocal;

	@Override
	public Criteria createCriteriaQuery() {
		return osobaDAOLocal.getCzekajacyUczniowie();
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

	@Override
	protected String getSortField() {
		// descending = true;
		return "dataRej";
	}

}
