package pl.ksb.agape.domain.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class BaseDAO<T extends Serializable> {

	public Class<T> clazz;

	@PersistenceContext(unitName = "agapeUnit")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	protected BaseDAO() {

		Type t = getClass().getGenericSuperclass();
		Type arg;
		if (t instanceof ParameterizedType) {
			arg = ((ParameterizedType) t).getActualTypeArguments()[0];
		} else if (t instanceof Class) {
			arg = ((ParameterizedType) ((Class) t).getGenericSuperclass())
					.getActualTypeArguments()[0];

		} else {
			throw new RuntimeException("Can not handle type construction for '"
					+ getClass() + "'!");
		}

		if (arg instanceof Class) {
			this.clazz = (Class<T>) arg;
		} else if (arg instanceof ParameterizedType) {
			this.clazz = (Class<T>) ((ParameterizedType) arg).getRawType();
		} else {
			throw new RuntimeException("Problem dtermining generic class for '"
					+ getClass() + "'! ");
		}
	}

	@SuppressWarnings("unchecked")
	public T getById(Long o) {
		return (T) getHibernateSession().get(clazz, o);

	}

	protected EntityManager getEntityManager() {
		return em;
	}

	protected Session getHibernateSession() {
		return (Session) this.em.getDelegate();
	}

	public void save(T t) {
		getHibernateSession().save(t);

	}

	public void saveOrUpdate(T t) {

		getHibernateSession().saveOrUpdate(t);

	}

	public void update(T t) {
		getHibernateSession().update(t);

	}

}
