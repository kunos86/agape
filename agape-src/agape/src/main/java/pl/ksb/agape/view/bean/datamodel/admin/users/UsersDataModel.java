package pl.ksb.agape.view.bean.datamodel.admin.users;

import java.util.List;

import javax.ejb.EJB;



import javax.enterprise.context.RequestScoped;
import javax.inject.Named;





import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.view.bean.datamodel.PaginatingDataModel;
import pl.ksb.agape.view.bean.datamodel.filter.UserFilter;

@Named
@RequestScoped
public class UsersDataModel extends     PaginatingDataModel<User> {

	

	private static final long serialVersionUID = 77363677407931868L;

	@EJB
	private UserDAOBean userDAOBean;
	
	private UserFilter filter = new UserFilter();
	
	@Override
	public List<User> getDataList(int firstRow, int numRows) {
		return userDAOBean.getUsers(firstRow,numRows, filter);
	}

	@Override
	public Object getKey(User t) {
		return t.getId();
	}

	@Override
	public int getTotalCount() {
		return userDAOBean.getCountUsers().intValue();
	}

	public UserFilter getFilter() {
		return filter;
	}
	
	public void search(){
		
	}

	public void setFilter(UserFilter filter) {
		this.filter = filter;
	}
	
	




}
