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
 * Home object for domain model class LineaFactura.
 * @see .LineaFactura
 * @author Hibernate Tools
 */
public class LineaFacturaHome {

	private static final Log log = LogFactory.getLog(LineaFacturaHome.class);

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

	public void persist(LineaFactura transientInstance) {
		log.debug("persisting LineaFactura instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(LineaFactura instance) {
		log.debug("attaching dirty LineaFactura instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LineaFactura instance) {
		log.debug("attaching clean LineaFactura instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(LineaFactura persistentInstance) {
		log.debug("deleting LineaFactura instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LineaFactura merge(LineaFactura detachedInstance) {
		log.debug("merging LineaFactura instance");
		try {
			LineaFactura result = (LineaFactura) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LineaFactura findById(int id) {
		log.debug("getting LineaFactura instance with id: " + id);
		try {
			LineaFactura instance = (LineaFactura) sessionFactory
					.getCurrentSession().get("LineaFactura", id);
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

	public List findByExample(LineaFactura instance) {
		log.debug("finding LineaFactura instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"LineaFactura").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
