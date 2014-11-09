package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import pl.ksb.agape.domain.model.News;


@Stateless
@LocalBean
public class NewsDAOBean extends BaseDAO<News> {
	
	@Inject
	private EntityManager em;
	
	public News getById(Long id){
		return em.find(News.class, id );
	}
	
	
	public List<News> getNewsToStartPage(int number)
	{
		return em.createQuery("Select n from News n order by n.modificationDate desc").setMaxResults(number).getResultList();
	}

}
