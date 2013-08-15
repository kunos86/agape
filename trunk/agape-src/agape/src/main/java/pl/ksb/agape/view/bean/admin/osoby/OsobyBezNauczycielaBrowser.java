package pl.ksb.agape.view.bean.admin.osoby;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.ksb.agape.domain.dao.OsobaDAOBean;
import pl.ksb.agape.domain.model.Osoba;

@ManagedBean
@ViewScoped
public class OsobyBezNauczycielaBrowser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8863631155804982591L;

	
	@EJB
	private OsobaDAOBean osobaDAOBean;
	
	private boolean editMode = false;
	
	private Osoba osoba;
	
	
	
	

	public Osoba getOsoba() {
		return osoba;
	}

	public boolean isEditMode() {
		return editMode;
	}
	
	private Collection<Object> selection;

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	} 
	
	public void dodajNaczyciela(){
		wczytajZaznaczenie();
		
		editMode=true;
		
	}
	
    public void zapisz(){
    	editMode=false;
    }
    
	public void closeEditMode() {
		editMode=false;
	}
	

	
	
	
	private void wczytajZaznaczenie(){
    	if (selection !=null && !selection.isEmpty()){
    		long id =(Long) selection.toArray()[0];
    		osoba = osobaDAOBean.getById(id);
    	}
    }
	

	
	
	
	

}
