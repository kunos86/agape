package pl.ksb.agape.view.bean.admin.kurs;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import pl.ksb.agape.domain.dao.KursDAOBean;
import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.util.Encoder;



@ManagedBean
@ViewScoped

public class KursyBrowser implements Serializable{

	private static final long serialVersionUID = 4732679136808487291L;


	private List<Kurs> kursy;
		
	public KursyBrowser() {
	}

	private Kurs kurs = new Kurs();
	
	private Collection<Object> selection; 
	
	@EJB
	private KursDAOBean kursDAOBean;
	
	private boolean editMode = false;
	
	
	private void wczytajKursy(){
		kursy = kursDAOBean.getListaKursow(false);
		if (kursy==null){
			kursy = new ArrayList<Kurs>();
		}
	}
	
	
    public void dodajKurs(){
    	kurs = new Kurs();
    	editMode=true;
    }
    
    public void edycjaKursu(){
    	wczytajZaznaczonyKurs();
    	editMode=true;
    }
    
    
    public boolean isEditMode() {
		return editMode;
	}


	private void wczytajZaznaczonyKurs(){
    	if (selection !=null && !selection.isEmpty()){
    		int id =(Integer) selection.toArray()[0];
    		kurs  = kursy.get(id);
    	}else{
    		kurs = new Kurs();
    	}
    }
	
    public void zapiszKurs(){
    	kursDAOBean.zapisz(kurs);
    	wczytajKursy();
    	editMode=false;
    }
    
	public void closeEditMode() {
		editMode=false;
	}

	public List<Kurs> getKursy() {
		if (kursy==null){
			wczytajKursy();
		}
		
		return kursy;
	}


	public Kurs getKurs() {
		return kurs;
	}
    
    
	public void usunObrazek() {
		kurs.setImage(null);
	}


	public Collection<Object> getSelection() {
		return selection;
	}


	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}
	
	public void przejdzDoLekcji() throws IOException{
		wczytajZaznaczonyKurs();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/agape/pages/admin/kurs/lekcje.jsf?id="+Encoder.encode(kurs.getId()));  
		
		
		
	}


	
	
}
