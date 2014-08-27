// default package
// Generated 29-abr-2013 19:22:03 by Hibernate Tools 3.2.4.GA
package hibernate;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class ReciboInmueble.
 * @see .ReciboInmueble
 * @author Hibernate Tools
 */
public class ReciboInmuebleHome {

	private static final Log log = LogFactory.getLog(ReciboInmuebleHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(ReciboInmueble transientInstance) {
		log.debug("persisting ReciboInmueble instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ReciboInmueble instance) {
		log.debug("attaching dirty ReciboInmueble instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReciboInmueble instance) {
		log.debug("attaching clean ReciboInmueble instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ReciboInmueble persistentInstance) {
		log.debug("deleting ReciboInmueble instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReciboInmueble merge(ReciboInmueble detachedInstance) {
		log.debug("merging ReciboInmueble instance");
		try {
			ReciboInmueble result = (ReciboInmueble) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ReciboInmueble findById(int id) {
		log.debug("getting ReciboInmueble instance with id: " + id);
		try {
			ReciboInmueble instance = (ReciboInmueble) sessionFactory
					.getCurrentSession().get("ReciboInmueble", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReciboInmueble instance) {
		log.debug("finding ReciboInmueble instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"ReciboInmueble").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
