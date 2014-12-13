package pl.ksb.agape.view.bean.admin.users;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.ksb.agape.domain.dao.StudentTeacherDAOBean;
import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;

@ManagedBean
@ViewScoped
public class UsersBrowser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9207372985947002488L;


	
	@EJB
	private UserDAOBean userDAOBean;


	private boolean editMode = false;

	private User user;
	
	
	public void edit(){
		editMode=true;
		loadSelection();
		
	}
	
	
	public void save(){
		userDAOBean.saveOrUpdate(user);
		closeEditMode();
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
	
	

	private Collection<Object> selection;

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public User getUser() {
		return user;
	}
	
	
	
	
	
	
}
