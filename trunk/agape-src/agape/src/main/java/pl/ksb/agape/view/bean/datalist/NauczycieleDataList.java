package pl.ksb.agape.view.bean.datalist;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.ksb.agape.domain.model.Osoba;


@ManagedBean
@SessionScoped
public class NauczycieleDataList {

	
	//@EJB
	private List<Osoba> nauczyciele;
	
	
	
}
