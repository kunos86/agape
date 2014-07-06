package pl.ksb.agape.domain.dao;

import java.util.UUID;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pl.ksb.agape.domain.model.ActivationToken;

@Stateless
@LocalBean
public class ActivationTokenDAOBean extends BaseDAO<ActivationToken> {

	public ActivationToken generateToken(Long userId) {

		ActivationToken token = new ActivationToken();
		String tokenText = UUID.randomUUID().toString().toUpperCase() + "_"
				+ userId;
		token.setToken(tokenText);
		token.setUserId(userId);
		getHibernateSession().saveOrUpdate(token);
		return token;

	}

	public ActivationToken getToken(String token) {
		return (ActivationToken) getHibernateSession().get(
				ActivationToken.class, token);
	}

}
