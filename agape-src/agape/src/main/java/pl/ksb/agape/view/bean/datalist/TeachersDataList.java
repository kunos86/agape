package pl.ksb.agape.view.bean.datalist;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.ksb.agape.domain.model.User;


@ManagedBean
@SessionScoped
public class TeachersDataList {

	
	//@EJB
	private List<User> nauczyciele;
	
	
	
}
