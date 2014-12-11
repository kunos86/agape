package pl.ksb.agape.view.bean.datalist;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import pl.ksb.agape.domain.dao.CourseDAOBean;
import pl.ksb.agape.domain.model.Course;

@Named
public class CoursesDataList {

	@EJB
	private CourseDAOBean courseDAOBean;
	
	public List<Course> getList(){
	
		return courseDAOBean.getCourses(true);	
	}	
	

}
