package pl.ksb.agape.view.datamodel.teacher;

import org.hibernate.Criteria;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.dao.LekcjaDoSprDAOLocal;
import pl.ksb.agape.domain.model.view.LekcjaDoSpr;
import pl.ksb.agape.session.DaneSesji;
import pl.ksb.agape.view.datamodel.PaginatingDataModel;

@Name("lekcjeDoSprDataModel")
@Scope(ScopeType.PAGE)
public class LekcjeDoSprDataModel extends
		PaginatingDataModel<LekcjaDoSpr, Long> {
	private static final long serialVersionUID = -98772061179599877L;

	@In
	private LekcjaDoSprDAOLocal lekcjaDoSprDAOLocal;

	@In
	private DaneSesji daneSesji;

	@Override
	public Criteria createCriteriaQuery() {

		return lekcjaDoSprDAOLocal
				.wszystkieLekcjeDoSprByIdNauczycielCriteria(daneSesji
						.getZalogowany().getId());
	}

	@Override
	public Long getId(LekcjaDoSpr object) {

		return object.getId();
	}

	@Override
	public LekcjaDoSpr getObjectById(Long id) {
		return null;
	}

}
