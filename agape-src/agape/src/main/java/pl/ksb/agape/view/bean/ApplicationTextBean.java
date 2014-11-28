package pl.ksb.agape.view.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.ksb.agape.domain.dao.ApplicationTextDAOBean;
import pl.ksb.agape.domain.model.ApplicationText;
import pl.ksb.agape.domain.model.dict.ApplicationTextType;

@ManagedBean(name = "_text")
@ApplicationScoped
public class ApplicationTextBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ApplicationTextDAOBean applicationTextDAOBean;

	private final Map<String, ApplicationText> texts = new HashMap<String, ApplicationText>();

	public void celarCache() {
		texts.clear();
	}

	public String get(String id) {
		if (!texts.containsKey(id)) {
			ApplicationText text = applicationTextDAOBean.getText(id, ApplicationTextType.TEXT);
			texts.put(text.getId(), text);
		}
		return texts.get(id).getText();

	}
	
	
	public String getHtml(String id) {
		if (!texts.containsKey(id)) {
			ApplicationText text = applicationTextDAOBean.getText(id, ApplicationTextType.HTML);
			texts.put(text.getId(), text);
		}
		return texts.get(id).getText();

	}

}
