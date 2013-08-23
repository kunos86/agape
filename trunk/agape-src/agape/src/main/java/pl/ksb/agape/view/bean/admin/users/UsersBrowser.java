package pl.ksb.agape.view.bean.admin.users;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.ksb.agape.view.bean.datamodel.admin.users.UsersDataModel;



@ManagedBean
@ViewScoped
public class UsersBrowser implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -9207372985947002488L;

	private UsersDataModel usersDataModel;
 
    private String filterName = "";
 
    public String getFilterName() {
        return filterName;
    }
 
    public void setFilterName(String filterName) {
        boolean changed = !this.filterName.equals(filterName);
        this.filterName = filterName;
        if (changed){
            initModel();
        }
    }
 

    
    
    public UsersDataModel getUsersDataModel() {
    	if (usersDataModel==null ){
    		initModel();
    	}
		return usersDataModel;
	}



	public void setUsersDataModel(UsersDataModel usersDataModel) {
		this.usersDataModel = usersDataModel;
	}

	private void initModel() {
		usersDataModel = new UsersDataModel();
    }
}
