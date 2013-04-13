package pl.ksb.agape.view.bean.datamodel.admin.osoby;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.richfaces.model.ArrangeableState;

import pl.ksb.agape.domain.dao.OsobaDAOBean;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.view.bean.datamodel.PaginatingDataModel;


@Named
@ManagedBean
@ViewScoped
public class OsobyDataModel extends PaginatingDataModel<Osoba> {

	
	@EJB
	private OsobaDAOBean osobaDAOBean;
	
	@Override
	public List<Osoba> getDataList(int firstRow, int numRows) {
		return osobaDAOBean.getWszystkieOsoby();
	}

	@Override
	public Object getKey(Osoba t) {
		return t.getId();
	}

	@Override
	public int getTotalCount() {
		return osobaDAOBean.getLiczbaOsob().intValue();
	}




}
