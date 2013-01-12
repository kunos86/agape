package pl.ksb.agape.domain.dao.hibernate.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

/**
 * Podstawowe DAO
 */
public abstract class HibernateBaseDAO<T> implements Serializable {

	private static final long serialVersionUID = 1995414133003829008L;

	@In
	protected Session hibernateSession;

	@Logger
	protected Log log;

	Class<T> clazz;

	protected HibernateBaseDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		return (T) hibernateSession.get(clazz, id);
	}

	public void saveOrUpdate(T t) {
		hibernateSession.saveOrUpdate(t);
	}

}
