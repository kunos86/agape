package pl.ksb.agape.view.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

@ManagedBean(name = "test")
@RequestScoped

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public String getZalogowany()
	{
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		
		return "uzytkownik "+(currentUser.isAuthenticated()?"zalogowany " :"niezalogowany");
	}
	
	
	
	public String logout(){
		Subject subject = SecurityUtils.getSubject(); //get user
		  if (subject != null) {                     //if is not already logged out,then ... log out basterd.
			  subject.logout();
			  
		  }
		SecurityUtils.getSubject().logout();
		return "/";
	}
}
