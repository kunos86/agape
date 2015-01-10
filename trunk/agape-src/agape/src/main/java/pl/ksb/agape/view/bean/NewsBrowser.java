package pl.ksb.agape.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;






import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;



import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import pl.ksb.agape.domain.dao.NewsDAOBean;
import pl.ksb.agape.domain.model.News;


@Named
@RequestScoped
public class NewsBrowser implements Serializable{
	

	private static final long serialVersionUID = -2782420131978937579L;

	private static final int NUMBER_OF_NEWS = 5;

	@EJB
	private NewsDAOBean newsDAOBean;
	
	private List<Integer> years;
	
	private Integer year;
	
	
	private List<News> news;
	

	public List<News> getNews() {
		wczytajNews();
		return news;
	}
	
	private void wczytajNews(){
		
		if (year ==null){
			news = newsDAOBean.getNewsToStartPage(NUMBER_OF_NEWS);
		}else{
			news = newsDAOBean.getNewsFromYear(year);
		}
		
		
		if(news==null){
			news= new ArrayList<News>();
		}
	}

	public List<Integer> getYears() {
		
		if (years==null){
			// zastanowić się czy nie pobierać tego z bazy
			years=new ArrayList<Integer>();
			for (int i = Calendar.getInstance().get(Calendar.YEAR); i>= 2014; i--){			
				years.add(i);
			}
		}
		return years;
	}


	public String changeYear() {
		return "";
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
