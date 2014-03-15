package pl.ksb.agape.view.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;

@ManagedBean
@RequestScoped
public class TeachersBrowser {

	private List<User> teachers;

	@EJB
	private UserDAOBean userDAOBean;

	public List<User> getTeachers() {
		if (teachers == null) {
			teachers = userDAOBean.getTeachers();
		}

		return teachers;
	}

	public void setTeachers(List<User> teachers) {
		this.teachers = teachers;
	}

}
