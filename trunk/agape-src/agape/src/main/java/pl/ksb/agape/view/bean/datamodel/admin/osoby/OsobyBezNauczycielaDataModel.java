package pl.ksb.agape.view.bean.datamodel.admin.osoby;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import pl.ksb.agape.domain.dao.OsobaDAOBean;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.view.bean.datamodel.PaginatingDataModel;

@Named("osobyBezNauczycielaDataModel")
public class OsobyBezNauczycielaDataModel extends PaginatingDataModel<Osoba>  {

	@EJB
	public OsobaDAOBean osobaDAOBean;
	
	public OsobyBezNauczycielaDataModel() {
	}

	@Override
	public List<Osoba> getDataList(int firstRow, int numRows) {
		return osobaDAOBean.getOsobyBezNauczyciela();
	}

	@Override
	public Object getKey(Osoba t) {
		return t.getId();
	}

	@Override
	public int getTotalCount() {
		return osobaDAOBean.getLiczbaOsobyBezNauczyciela().intValue();
	}

}
