package pl.ksb.agape.session;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Scope(ScopeType.EVENT)
@Name("testBean")
public class TestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7082184004430555028L;

	public void ustawButton() {
		System.out
				.println("Ustaw button now test ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
