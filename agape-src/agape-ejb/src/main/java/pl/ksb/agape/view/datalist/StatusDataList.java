package pl.ksb.agape.view.datalist;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import pl.ksb.agape.domain.model.dict.Status;

@Name("statusDataList")
@Scope(ScopeType.STATELESS)
public class StatusDataList {

	public Status[] getStatusValues() {
		return Status.values();
	}
}
