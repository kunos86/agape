package pl.ksb.agape.view.bean.admin.kurs;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

import pl.ksb.agape.domain.dao.KursDAOBean;
import pl.ksb.agape.domain.model.Kurs;


@ManagedBean
@ViewScoped

public class KursyBrowser implements Serializable{

	private static final long serialVersionUID = 4732679136808487291L;


	public KursyBrowser() {
	}

	private Kurs kurs = new Kurs();
	
	@EJB
	private KursDAOBean kursDAOBean;
	
	
    public void dodajKurs(){
    	System.out.println("dodaj kurs");
    	kurs = new Kurs();
    }
	
    public void zapiszKurs(){
    	System.out.println("zapisz kurs");
    	
    	kursDAOBean.zapisz(kurs);
    	
    }

	public Kurs getKurs() {
		return kurs;
	}
    
    
	public void usunObrazek() {
		kurs.setImage(null);
	}
	
	
}
