package pl.ksb.agape.view.datamodel;

import org.hibernate.Criteria;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import pl.ksb.agape.domain.dao.UprawnieniaKursDAOLocal;
import pl.ksb.agape.domain.model.UprawnieniaKurs;
import pl.ksb.agape.view.bean.admin.UprawnieniaKursyBrowser;

@Name("uprawnieniaKursNauczDataModel")
public class UprawnieniaKursNauczDataModel extends
		PaginatingDataModel<UprawnieniaKurs, Long> {

	private static final long serialVersionUID = -3642574943714657894L;

	@In
	private UprawnieniaKursDAOLocal uprawnieniaKursDAOLocal;

	@In
	private UprawnieniaKursyBrowser uprawnieniaKursyBrowser;

	@Override
	public Criteria createCriteriaQuery() {

		return uprawnieniaKursDAOLocal
				.getUprawnieniaKursu(uprawnieniaKursyBrowser
						.getSelectedIdKurs());
	}

	@Override
	public Long getId(UprawnieniaKurs object) {
		return object.getId();
	}

	@Override
	public UprawnieniaKurs getObjectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
