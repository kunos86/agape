package pl.ksb.agape.view.datalist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("wojewodztwaList")
@Scope(ScopeType.PAGE)
public class WojewodztwoList implements Serializable {

	private static final long serialVersionUID = 3442225792105266357L;
	private List<String> listaWoj = null;

	public List<String> getListaWoj() {
		return listaWoj;
	}

	public void setListaWoj(List<String> listaWoj) {
		this.listaWoj = listaWoj;
	}

	@Create
	public void init() {
		listaWoj = new ArrayList<String>();
		listaWoj.add("dolnoœl¹skie");
		listaWoj.add("kujawsko-pomorskie");
		listaWoj.add("lubelskie");
		listaWoj.add("lubuskie");
		listaWoj.add("³ódzkie");
		listaWoj.add("ma³opolskie");
		listaWoj.add("mazowieckie");
		listaWoj.add("opolskie");
		listaWoj.add("podkarpackie");
		listaWoj.add("podlaskie");
		listaWoj.add("pomorskie");
		listaWoj.add("œl¹skie");
		listaWoj.add("œwiêtokrzyskie");
		listaWoj.add("warmiñsko-mazurskie");
		listaWoj.add("wielkopolskie");
		listaWoj.add("zachodniopomorskie");
		listaWoj.add("z poza Polski");
	}
}
