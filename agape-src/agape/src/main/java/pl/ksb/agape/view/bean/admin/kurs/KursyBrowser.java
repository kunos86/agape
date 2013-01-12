package pl.ksb.agape.view.bean.admin.kurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.ksb.agape.domain.dao.KursDAOBean;
import pl.ksb.agape.domain.model.Kurs;


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
	
	
	private void wczytajKursy(){
		kursy = kursDAOBean.getListaKursow(false);
		if (kursy==null){
			kursy = new ArrayList<Kurs>();
		}
	}
	
	
    public void dodajKurs(){
    	System.out.println("dodaj kurs");
    	kurs = new Kurs();
    }
    
    public void edycjaKursu(){
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


	
	
}
