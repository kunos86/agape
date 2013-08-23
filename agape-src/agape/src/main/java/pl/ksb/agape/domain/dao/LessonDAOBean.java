package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.ksb.agape.domain.model.Lesson;

/**
 * Session Bean implementation class LekcjaDAOBeab
 */
@Stateless
@LocalBean
public class LessonDAOBean {

	@Inject
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public LessonDAOBean() {
    }
    
    public Lesson getById(Long id){
    	return em.find(Lesson.class, id);
    }
    
	@SuppressWarnings("unchecked")
	public List<Lesson> getCourseLessons(Long courseId, boolean courseEnabled){
    	Query q=null;
    	if (courseEnabled){
    		q=em.createNamedQuery("getEnabledCourseLessons").setParameter("coursId", courseId);
    	}else{
    		q=em.createNamedQuery("getCourseLessons").setParameter("coursId", courseId);
    	}
    	return q.getResultList();
    }
	
	public void save(Lesson lesson){
		
		lesson.setModificationDate(Calendar.getInstance().getTime());
		lesson.setStatus("A");
		if (lesson.getId()==null){
			lesson.setCreationDate(Calendar.getInstance().getTime());
			em.persist(lesson);
		}else{
			em.merge(lesson);
		}
	}

}
