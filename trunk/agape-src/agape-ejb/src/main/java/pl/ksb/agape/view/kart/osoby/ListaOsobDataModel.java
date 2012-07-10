package pl.ksb.agape.view.kart.osoby;

import org.hibernate.Criteria;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.ListaOsobDAOLocal;
import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.view.datamodel.PaginatingDataModel;

@Name("listaOsobDM")
public class ListaOsobDataModel extends PaginatingDataModel<Osoba, Long> {

	private static final long serialVersionUID = 2976380822203123398L;
	@In
	private ListaOsobDAOLocal listaOsobDAOBean;

	@Override
	public Criteria createCriteriaQuery() {

		return listaOsobDAOBean.getListaOsob();
	}

	@Override
	public Long getId(Osoba object) {
		return object.getId();
	}

	@Override
	public Osoba getObjectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
