package pl.ksb.agape.view.datamodel;

import org.hibernate.Criteria;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.LekcjaDAOLocal;
import pl.ksb.agape.domain.model.Lekcja;
import pl.ksb.agape.view.bean.admin.ZarzadzanieLekcjami;

/**
 * datamodel listy lekcji w panelu administratora
 * 
 * @author Piotr
 * 
 */
@Name("lekcjeDataModel")
@Scope(ScopeType.EVENT)
public class LekcjaDataModel extends PaginatingDataModel<Lekcja, Long> {

	private static final long serialVersionUID = -395967110738688974L;

	@In
	private LekcjaDAOLocal lekcjaDAOLocal;

	@In
	private ZarzadzanieLekcjami zarzadzanieLekcjami;

	@Override
	public Criteria createCriteriaQuery() {
		return lekcjaDAOLocal.wszystkieLekcjeByKursCriteria(zarzadzanieLekcjami
				.getIdKurs());
	}

	@Override
	public Long getId(Lekcja object) {
		return object.getId();
	}

	@Override
	public Lekcja getObjectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
