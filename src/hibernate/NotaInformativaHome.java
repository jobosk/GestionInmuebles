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
 * Home object for domain model class NotaInformativa.
 * @see .NotaInformativa
 * @author Hibernate Tools
 */
public class NotaInformativaHome {

	private static final Log log = LogFactory.getLog(NotaInformativaHome.class);

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

	public void persist(NotaInformativa transientInstance) {
		log.debug("persisting NotaInformativa instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(NotaInformativa instance) {
		log.debug("attaching dirty NotaInformativa instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NotaInformativa instance) {
		log.debug("attaching clean NotaInformativa instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(NotaInformativa persistentInstance) {
		log.debug("deleting NotaInformativa instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NotaInformativa merge(NotaInformativa detachedInstance) {
		log.debug("merging NotaInformativa instance");
		try {
			NotaInformativa result = (NotaInformativa) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public NotaInformativa findById(int id) {
		log.debug("getting NotaInformativa instance with id: " + id);
		try {
			NotaInformativa instance = (NotaInformativa) sessionFactory
					.getCurrentSession().get("NotaInformativa", id);
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

	public List findByExample(NotaInformativa instance) {
		log.debug("finding NotaInformativa instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"NotaInformativa").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
