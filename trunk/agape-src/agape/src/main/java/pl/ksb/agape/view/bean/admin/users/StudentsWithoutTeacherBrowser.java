package pl.ksb.agape.view.bean.admin.users;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.ksb.agape.domain.dao.StudentTeacherDAOBean;
import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;

@ManagedBean
@ViewScoped
public class StudentsWithoutTeacherBrowser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8863631155804982591L;

	@EJB
	private UserDAOBean userDAOBean;

	@EJB
	private StudentTeacherDAOBean studentTeacherDAOBean;

	private boolean editMode = false;

	private Long teacherId;

	private User user;

	public User getUser() {
		return user;
	}

	public boolean isEditMode() {
		return editMode;
	}

	private Collection<Object> selection;

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public void addTeacher() {
		loadSelection();

		editMode = true;

	}

	public void save() {
		loadSelection();
		User teacher = userDAOBean.getById(teacherId);
		studentTeacherDAOBean.setCuurentTeacherForStudent(user, teacher);
		editMode = false;
	}

	public void closeEditMode() {
		editMode = false;
	}

	private void loadSelection() {
		if (selection != null && !selection.isEmpty()) {
			long id = (Long) selection.toArray()[0];
			user = userDAOBean.getById(id);
		}
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

}
