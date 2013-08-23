package pl.ksb.agape.view.bean.admin.course;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import pl.ksb.agape.domain.dao.CourseDAOBean;
import pl.ksb.agape.domain.model.Course;
import pl.ksb.agape.util.Encoder;



@ManagedBean
@ViewScoped

public class CoursesBrowser implements Serializable{

	private static final long serialVersionUID = 4732679136808487291L;


	private List<Course> courses;
		
	public CoursesBrowser() {
	}

	private Course course = new Course();
	
	private Collection<Object> selection; 
	
	@EJB
	private CourseDAOBean courseDAOBean;
	
	private boolean editMode = false;
	
	
	private void loadCourses(){
		courses = courseDAOBean.getCourses(false);
		if (courses==null){
			courses = new ArrayList<Course>();
		}
	}
	
	
    public void addCourse(){
    	course = new Course();
    	editMode=true;
    }
    
    public void editeCourse(){
    	loadSelectedCourses();
    	editMode=true;
    }
    
    
    public boolean isEditMode() {
		return editMode;
	}


	private void loadSelectedCourses(){
    	if (selection !=null && !selection.isEmpty()){
    		int id =(Integer) selection.toArray()[0];
    		course  = courses.get(id);
    	}else{
    		course = new Course();
    	}
    }
	
    public void saveCourse(){
    	courseDAOBean.save(course);
    	loadCourses();
    	editMode=false;
    }
    
	public void closeEditMode() {
		editMode=false;
	}

	public List<Course> getCourses() {
		if (courses==null){
			loadCourses();
		}
		
		return courses;
	}



    
    
	public Course getCourse() {
		return course;
	}


	public void removeImage() {
		course.setImage(null);
	}


	public Collection<Object> getSelection() {
		return selection;
	}


	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}
	
	public void goToLesson() throws IOException{
		loadSelectedCourses();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/agape/pages/admin/kurs/lekcje.jsf?id="+Encoder.encode(course.getId()));  
		
		
		
	}


	
	
}
