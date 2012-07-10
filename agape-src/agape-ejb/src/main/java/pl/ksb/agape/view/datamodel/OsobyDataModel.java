package pl.ksb.agape.view.datamodel;

import org.hibernate.Criteria;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.view.bean.admin.ZarzadzanieOsobami;

@Name("osobaDataModel")
@Scope(ScopeType.EVENT)
public class OsobyDataModel extends PaginatingDataModel<Osoba, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 552055120920029071L;

	@In
	private OsobaDAOLocal osobaDAOLocal;

	@In
	private ZarzadzanieOsobami zarzadzanieOsobami;

	@Override
	public Criteria createCriteriaQuery() {
		return osobaDAOLocal.getOsoby(zarzadzanieOsobami.isPokazUsuniete());
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
