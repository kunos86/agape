package pl.ksb.agape.view.datalist;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.model.dict.RodzajKonta;

@Name("rodzajKontaDataList")
@Scope(ScopeType.STATELESS)
public class RodzajKontaDataList {

	public RodzajKonta[] getRodzajeKontValues() {
		return RodzajKonta.values();
	}

}
