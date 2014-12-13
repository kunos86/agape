package pl.ksb.agape.domain.dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.ksb.agape.domain.model.Lesson;
import pl.ksb.agape.domain.model.Question;

@Stateless
@Named@LocalBean
public class QuestionDAOBean extends BaseDAO<Question>{
	
	
    /**
     * Default constructor. 
     */
    public QuestionDAOBean() {
    }
    
    public Question getById(Long id){
    	return getEntityManager().find(Question.class,id);
    }
    
	@SuppressWarnings("unchecked")
	public List<Question> getQuestions(Long lessonId, boolean enabledOnly){
    	Query q=null;
    	if (enabledOnly){
    		q=getEntityManager().createNamedQuery("getEnabledLessonQuestions").setParameter("lessonId", lessonId);
    	}else{
    		q=getEntityManager().createNamedQuery("getLessonQuestions").setParameter("lessonId", lessonId);
    	}
    	return q.getResultList();
    }
	
	public void save(Question question){
		
		question.setModificationDate(Calendar.getInstance().getTime());
		question.setStatus("A");
		if (question.getId()==null){
			question.setCreationDate(Calendar.getInstance().getTime());
			getEntityManager().persist(question);
		}else{
			getEntityManager().merge(question);
		}
	}
	public void delete(Long id) {

		Question q = getById(id);
		if(q!=null){
			delete(q);
		}
	}

}
