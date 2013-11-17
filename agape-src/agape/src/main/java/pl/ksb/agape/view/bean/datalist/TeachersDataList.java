package pl.ksb.agape.view.bean.datalist;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import pl.ksb.agape.domain.dao.UserDAOBean;
import pl.ksb.agape.domain.model.User;


@Named
public class TeachersDataList {

	@EJB
	private UserDAOBean userDAOBean;
	
	public List<SelectItem> getSelectItemsList(){
		List<User> users = userDAOBean.getTeachers();
		List<SelectItem> teachers = new ArrayList<SelectItem>();
		for(User u : users){
			teachers.add(new SelectItem(u.getId(),  u.getSurname() + " "+u.getName() ));
		}
		return teachers;	
	}	
	
	public List<User> getList(){
		return userDAOBean.getTeachers();	
	}	
}
