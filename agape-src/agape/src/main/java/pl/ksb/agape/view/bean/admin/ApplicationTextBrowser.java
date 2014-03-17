package pl.ksb.agape.view.bean.admin;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.ksb.agape.domain.dao.ApplicationTextDAOBean;
import pl.ksb.agape.domain.model.ApplicationText;

@ManagedBean
@ViewScoped
public class ApplicationTextBrowser implements Serializable {

	private static final long serialVersionUID = -9146556825141035500L;

	@EJB
	private ApplicationTextDAOBean applicationTextDAOBean;

	private List<ApplicationText> texts;

	public List<ApplicationText> getTexts() {
		if (texts == null) {
			texts = applicationTextDAOBean.getAll();
		}
		return texts;
	}

	public void save() {
		for (ApplicationText t : texts) {
			applicationTextDAOBean.update(t);
		}
	}

	public void setTexts(List<ApplicationText> texts) {
		this.texts = texts;
	}

}
