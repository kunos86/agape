package pl.ksb.agape.view.bean.admin.kurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import pl.ksb.agape.domain.dao.LekcjaDAOBean;
import pl.ksb.agape.domain.dao.PytaniaDAOBean;
import pl.ksb.agape.domain.model.Lekcja;
import pl.ksb.agape.domain.model.Pytanie;
import pl.ksb.agape.util.Encoder;


@ManagedBean
@ViewScoped
public class PytaniaBrowser implements Serializable{


	private static final long serialVersionUID = 2523628575703193830L;

	public PytaniaBrowser() {
		// TODO Auto-generated constructor stub
	}
	
	
	private List<Pytanie> pytania;


	
	
	@EJB
	private LekcjaDAOBean lekcjaDAOBean;

	@EJB
	private PytaniaDAOBean pytaniaDAOBean;

	private Pytanie pytanie = new Pytanie();
	
	private Lekcja lekcja;
	
	private boolean editMode = false;
	
	
	
	

	@PostConstruct
	public void init() {

		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String clipId = request.getParameter("id");
			Long id = Long.decode(clipId);
			Long idLekcja = Encoder.decode(id);
			lekcja = lekcjaDAOBean.getById(idLekcja);
		} catch (Exception e) {

		}
	}

	
	public void dodaj() {
		pytanie = new Pytanie();
		pytanie.setIdLekcja(lekcja.getId());
		editMode = true;
	}

	public void edycja(Long id) {
		System.out.println("edycja pytania");
		System.out.println(id);
		pytanie = pytaniaDAOBean.getById(id);
		editMode=true;
	}
	

	

	public void zapisz() {
		pytaniaDAOBean.zapisz(pytanie);
		wczytajPytania();
		editMode=false;
	}

	public void closeEditMode() {
		editMode = false;
	}
		

	public Pytanie getPytanie() {
		return pytanie;
	}



	public boolean isEditMode() {
		return editMode;
	}
	
	private void wczytajPytania() {
		init();
		pytania = pytaniaDAOBean.getListaPytan(lekcja.getId(), false);
		if (pytania == null) {
			pytania = new ArrayList<Pytanie>();
		}
	}



	public List<Pytanie> getPytania() {
		if (pytania == null){
			wczytajPytania();
		}
		return pytania;
	}
	
	
	


}
