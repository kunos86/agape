package pl.ksb.agape.view.bean.admin.users;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

	private List<User> users;

	public List<User> getUsers() {
		if (users == null) {
			users = userDAOBean.getAll();
		}
		return users;
	}

	// private UsersDataModel usersDataModel;
	//
	// private String filterName = "";
	//
	// public String getFilterName() {
	// return filterName;
	// }

	// public void setFilterName(String filterName) {
	// boolean changed = !this.filterName.equals(filterName);
	// this.filterName = filterName;
	// if (changed){
	// initModel();
	// }
	// }

	//
	// public UsersDataModel getUsersDataModel() {
	// if (usersDataModel==null ){
	// initModel();
	// }
	// return usersDataModel;
	// }
	//
	//
	//
	// public void setUsersDataModel(UsersDataModel usersDataModel) {
	// this.usersDataModel = usersDataModel;
	// }

	// private void initModel() {
	// usersDataModel = new UsersDataModel();
	// }
}
