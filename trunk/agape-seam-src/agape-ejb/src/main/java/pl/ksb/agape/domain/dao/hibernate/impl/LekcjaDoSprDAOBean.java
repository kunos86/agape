package pl.ksb.agape.domain.dao.hibernate.impl;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.LekcjaDoSprDAOLocal;
import pl.ksb.agape.domain.model.view.LekcjaDoSpr;

@Stateless
@Name("lekcjaDoSprDAOLocal")
@AutoCreate
public class LekcjaDoSprDAOBean extends HibernateBaseDAO<LekcjaDoSpr> implements
		LekcjaDoSprDAOLocal {

	private static final long serialVersionUID = 4489953190754534440L;

	public LekcjaDoSprDAOBean() {
		super(LekcjaDoSpr.class);
	}

	public Criteria wszystkieLekcjeDoSprByIdNauczycielCriteria(Long idNauczciela) {
		return hibernateSession.createCriteria(LekcjaDoSpr.class).add(
				Restrictions.eq("idNauczyciela", idNauczciela));
	}

	public Long liczbaLekcjiDoSprawdzenia(Long idNauczciela) {
		return (Long) wszystkieLekcjeDoSprByIdNauczycielCriteria(idNauczciela)
				.setProjection(Projections.rowCount()).uniqueResult();
	}

}
