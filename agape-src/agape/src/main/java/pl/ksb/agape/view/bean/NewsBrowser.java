package pl.ksb.agape.view.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import pl.ksb.agape.domain.dao.NewsDAOBean;
import pl.ksb.agape.domain.model.News;


@ManagedBean
@RequestScoped
public class NewsBrowser {
	
	private static final int NUMBER_OF_NEWS = 5;

	@EJB
	private NewsDAOBean newsDAOBean;
	
	
	private List<News> news;


	public List<News> getNews() {
		if (news==null){
			wczytajNews();
		}
		return news;
	}
	
	private void wczytajNews(){
		news = newsDAOBean.getNewsToStartPage(NUMBER_OF_NEWS);
		if(news==null){
			news= new ArrayList<News>();
		}
	}
	
	
	
	
}
