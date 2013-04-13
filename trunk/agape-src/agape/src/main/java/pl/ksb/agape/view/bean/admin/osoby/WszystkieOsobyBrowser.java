package pl.ksb.agape.view.bean.admin.osoby;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.ksb.agape.view.bean.datamodel.admin.osoby.OsobyDataModel;



@ManagedBean
@ViewScoped
public class WszystkieOsobyBrowser implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -9207372985947002488L;

	private OsobyDataModel osobyDataModel;
 
    private String filterName = "";
 
    public String getFilterName() {
        return filterName;
    }
 
    public void setFilterName(String filterName) {
        boolean changed = !this.filterName.equals(filterName);
        this.filterName = filterName;
        if (changed){
            initGoodsModel();
        }
    }
 

    
    
    public OsobyDataModel getOsobyDataModel() {
    	if (osobyDataModel==null ){
    		initGoodsModel();
    	}
		return osobyDataModel;
	}

	public void setOsobyDataModel(OsobyDataModel osobyDataModel) {
		this.osobyDataModel = osobyDataModel;
	}

	private void initGoodsModel() {
		osobyDataModel = new OsobyDataModel();
    }
}
