package pl.ksb.agape.session;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import pl.ksb.agape.domain.dao.OsobaDAOLocal;
import pl.ksb.agape.domain.model.Osoba;

@Scope(ScopeType.PAGE)
@Name("authenticator")
public class Authenticator {
	@Logger
	private Log log;

	@In
	Identity identity;
	@In
	Credentials credentials;

	@In
	private OsobaDAOLocal osobaDAOLocal;

	@In
	private DaneSesji daneSesji;

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public boolean authenticate() {
		log.info("authenticating {0}", credentials.getUsername());

		Osoba osoba = osobaDAOLocal.authenticat(credentials.getUsername(),
				credentials.getPassword());

		if (osoba != null) {
			identity.addRole(osoba.getRodzajKonta().toString());
			daneSesji.setZalogowany(osoba);
		}
		return osoba != null ? true : false;
	}
}
