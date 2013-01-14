package pl.ksb.agape.view.bean.admin.kurs;

import java.io.IOException;
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

import pl.ksb.agape.domain.dao.KursDAOBean;
import pl.ksb.agape.domain.dao.LekcjaDAOBean;
import pl.ksb.agape.domain.model.Kurs;
import pl.ksb.agape.domain.model.Lekcja;
import pl.ksb.agape.util.Encoder;

@ManagedBean
@ViewScoped

public class LekcjeBrowser implements Serializable {


	private static final long serialVersionUID = -3453718247835100811L;

	public LekcjeBrowser() {
	}

	private List<Lekcja> lekcje;
	private Lekcja lekcja = new Lekcja();

	private Collection<Object> selection;

	@EJB
	private LekcjaDAOBean lekcjaDAOBean;

	@EJB
	private KursDAOBean kursDAOBean;

	private Kurs kurs;
	
	private boolean editMode = false;
	
	

	@PostConstruct
	public void init() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String clipId = request.getParameter("id");
			Long id = Long.decode(clipId);
			Long idKursu = Encoder.decode(id);
			kurs = kursDAOBean.getById(idKursu);
		} catch (Exception e) {

		}

	}

	private void wczytajLekcje() {
//		init();
		lekcje = lekcjaDAOBean.getListaLekcji(kurs.getId(), false);
		if (lekcje == null) {
			lekcje = new ArrayList<Lekcja>();
		}
	}
	
	

	public boolean isEditMode() {
		return editMode;
	}

	public void dodaj() {
		System.out.println("dodaj");
		lekcja = new Lekcja();
		lekcja.setIdKursu(kurs.getId());
		editMode = true;
	}

	public void edycja() {
		editMode=true;
		wczytajZaznaczenie();
	}
	
	private void wczytajZaznaczenie(){
		if (selection != null && !selection.isEmpty()) {
			int id = (Integer) selection.toArray()[0];
			lekcja = lekcje.get(id);
		} else {
			lekcja = new Lekcja();
		}
		
	}
	

	public void zapisz() {
		lekcjaDAOBean.zapisz(lekcja);
		wczytajLekcje();
		editMode=false;
	}

	public void closeEditMode() {
		editMode=false;
	}
	
	public List<Lekcja> getLekcje() {
		if (lekcje == null) {
			wczytajLekcje();
		}
		return lekcje;
	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public void przejdzDoPytn() throws IOException{
		wczytajZaznaczenie();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/agape/pages/admin/kurs/pytania.jsf?id="+Encoder.encode(lekcja.getId()));  

	}
	
}
