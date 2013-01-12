package pl.ksb.agape.view.datamodel;

import org.hibernate.Criteria;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.KursDAOLocal;
import pl.ksb.agape.domain.model.Kurs;

@Name("kursDataModel")
@Scope(ScopeType.EVENT)
/**
 * data model dla listy kursów w panelu administracyjnym 
 */
public class KursyDataModel extends PaginatingDataModel<Kurs, Long> {

	private static final long serialVersionUID = -726281656162331080L;
	@In
	private KursDAOLocal kursDAOLocal;

	@Override
	public Criteria createCriteriaQuery() {
		return kursDAOLocal.wszystkieKursyCriteria();
	}

	@Override
	public Long getId(Kurs object) {
		return object.getId();
	}

	@Override
	public Kurs getObjectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
