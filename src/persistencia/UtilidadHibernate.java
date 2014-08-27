package persistencia;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class UtilidadHibernate {

	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

	// Alternatively, we could look up in JNDI here
	public static SessionFactory getSessionFactory() {
		
		
		return sessionFactory;
		
	}

	// Close caches and connection pools
	public static void shutdown() {
		getSessionFactory().close();
	}
}

