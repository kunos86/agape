package pl.ksb.agape.domain.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.criterion.Order;

import pl.ksb.agape.domain.model.News;

@Stateless
@LocalBean
public class NewsDAOBean extends BaseDAO<News> {

	@SuppressWarnings("unchecked")
	public List<News> getNewsFromYear(int year) {
		return getHibernateSession()
				.createQuery(
						"from News where year(modificationDate) = :year order by modificationDate desc ")
				.setParameter("year", year).list();
	}

	@SuppressWarnings("unchecked")
	public List<News> getNewsToStartPage(int number) {
		return getHibernateSession().createCriteria(News.class)
				.addOrder(Order.desc("modificationDate")).setMaxResults(number)
				.list();
	}

}
