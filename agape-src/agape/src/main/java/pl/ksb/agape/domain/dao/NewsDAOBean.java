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
public class NewsDAOBean {
	
	@Inject
	private EntityManager em;
	
	public News getById(Long id){
		return em.find(News.class, id );
	}
	
	public void zapisz(News news){
		
		news.setDataArch(Calendar.getInstance().getTime());
		if (news.getId()==null){
			
			em.persist(news);
		}else{
			em.merge(news);
		}
	}
	
	public List<News> getNewsToStartPage(int number)
	{
		return em.createQuery("Select n from News n order by n.dataArch desc").setMaxResults(number).getResultList();
	}

}
