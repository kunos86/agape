package pl.ksb.agape.view.bean.datamodel.admin.users;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.richfaces.model.ArrangeableState;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.view.bean.datamodel.PaginatingDataModel;


@Named
@ManagedBean
@ViewScoped
public class UsersDataModel extends     PaginatingDataModel<User> {

	
	@EJB
	private UserDAOBean userDAOBean;
	
	@Override
	public List<User> getDataList(int firstRow, int numRows) {
		return userDAOBean.getUsers(firstRow,numRows);
	}

	@Override
	public Object getKey(User t) {
		return t.getId();
	}

	@Override
	public int getTotalCount() {
		return userDAOBean.getCountUsers().intValue();
	}




}
