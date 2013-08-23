package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.ksb.agape.domain.model.Course;

/**
 * Session Bean implementation class KursDAOBean
 */
@Stateless
@LocalBean
public class CourseDAOBean {

    /**
     * Default constructor. 
     */
    public CourseDAOBean() {
    }

    @SuppressWarnings("unused")
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;  
    
    public Course getById(long id){
    	return em.find(Course.class, id);
    }
    
	@SuppressWarnings("unchecked")
	public List<Course> getCourses(boolean enabled){
    	Query q=null;
    	if (enabled){
    		q=em.createNamedQuery("getEnabledCourse");
    	}else{
    		q=em.createNamedQuery("getCourse");
    	}
    	return q.getResultList();
    }
	
	public void save(Course course){
		
		course.setModificationDate(Calendar.getInstance().getTime());
		course.setStatus("A");
		if (course.getId()==null){
			course.setCreationDate(Calendar.getInstance().getTime());
			em.persist(course);
		}else{
			em.merge(course);
		}
	}
	

	
}
