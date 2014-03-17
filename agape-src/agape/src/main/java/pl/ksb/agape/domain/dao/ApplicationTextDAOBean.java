package pl.ksb.agape.domain.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.ApplicationText;

@Stateless
@LocalBean
public class ApplicationTextDAOBean extends BaseDAO<ApplicationText> {

	@SuppressWarnings("unchecked")
	public List<ApplicationText> getAll() {
		return getHibernateSession().createCriteria(ApplicationText.class)
				.list();
	}

	public ApplicationText getText(String id) {
		ApplicationText text = (ApplicationText) getHibernateSession()
				.createCriteria(ApplicationText.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		// jeśli nie ma w bazie zapisuję, aby można było dodać w admince
		if (text == null) {
			text = new ApplicationText();
			text.setId(id);
			super.save(text);
		}
		return text;
	}

}
