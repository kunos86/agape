package pl.ksb.agape.view.bean.datamodel.admin.users;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.view.bean.datamodel.PaginatingDataModel;

@Named("studentsWithoutTeacherDataModel")
public class StudentsWithoutTeacherDataModel extends PaginatingDataModel<User>  {

	@EJB
	public UserDAOBean userDAOBean;
	
	public StudentsWithoutTeacherDataModel() {
	}

	@Override
	public List<User> getDataList(int firstRow, int numRows) {
		return userDAOBean.getStudentsWithoutTeacher();
	}

	@Override
	public Object getKey(User t) {
		return t.getId();
	}

	@Override
	public int getTotalCount() {
		return userDAOBean.getCountStudentsWithoutTeacher().intValue();
	}

}
