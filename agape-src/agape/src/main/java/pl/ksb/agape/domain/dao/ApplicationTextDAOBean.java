package pl.ksb.agape.domain.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pl.ksb.agape.domain.model.ApplicationText;
import pl.ksb.agape.domain.model.dict.ApplicationTextType;

@Stateless
@LocalBean
public class ApplicationTextDAOBean extends BaseDAO<ApplicationText> {

	@SuppressWarnings("unchecked")
	public List<ApplicationText> getAll() {
		return getHibernateSession().createCriteria(ApplicationText.class)
				.addOrder(Order.desc("type")).addOrder(Order.asc("id")).list();
	}
	
	public ApplicationText getText(String id) {
		return getText(id, ApplicationTextType.TEXT);
	}
	
	public ApplicationText getHtml(String id) {
		return getText(id, ApplicationTextType.HTML);
	}

	public ApplicationText getText(String id,ApplicationTextType type) {
		ApplicationText text = (ApplicationText) getHibernateSession()
				.createCriteria(ApplicationText.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		// jeśli nie ma w bazie zapisuję, aby można było dodać w admince
		if (text == null) {
			text = new ApplicationText();
			text.setType(type);
			text.setId(id);
			super.save(text);
		}
		return text;
	}

}
