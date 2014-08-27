package DAO;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;

import hibernate.*;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;
import persistencia.UtilidadHibernate;

public class DAOComunidad implements InterfazDAOComunidad {
	
	private Session sesion = null;
	private Transaction tx = null;
	
	@Override
	public void crearComunidad(Comunidad clase) throws DAOExcepcion {
		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			sesion.save(clase);
			tx.commit();
		} catch (JDBCConnectionException e) {
			System.out.println("No se puede conectar con la BD. Error grave");
			throw new DAOConfiguracionExcepcion(e); 
		} catch (HibernateException e) {
			rollback();
			throw new DAOExcepcion(e); 
		} finally {
			sesion.close();
		}
	}

	@Override
	public void borrarComunidad(Comunidad clase) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			sesion.delete(clase); 
			tx.commit(); 
		} catch (JDBCConnectionException e) {
			System.out.println("No se puede conectar con la BD. Error grave");
			throw new DAOConfiguracionExcepcion(e);
		} catch (HibernateException e) { 
			rollback(); 
			throw new DAOExcepcion(e); 
		} finally { 
			sesion.close(); 
		} 
	}
	
	@Override
	public void actualizarComunidad(Comunidad clase) throws DAOExcepcion {
		try{
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			sesion.update(clase);
			tx.commit(); 
		} catch (JDBCConnectionException e) {
			System.out.println("No se puede conectar con la BD. Error grave");
			throw new DAOConfiguracionExcepcion(e); 
		} catch (HibernateException e) {
			rollback();
			throw new DAOExcepcion(e); 
		} finally {
			sesion.close();
		}
	}

	@Override
	public ArrayList<Comunidad> encontrarComunidades() throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction();
			ArrayList<Comunidad> clases = (ArrayList) sesion.createQuery("from Comunidad").list(); 
			tx.commit();
			return clases; 
		} catch (JDBCConnectionException e) {
			System.out.println("No se puede conectar con la BD. Error grave");
			throw new DAOConfiguracionExcepcion(e); 
		} catch (HibernateException e) { 
			rollback(); 
			throw new DAOExcepcion(e); 
		} finally {
			sesion.close();
		}  
	}

	@Override
	public Comunidad encontrarComunidadPorId(int id) throws DAOExcepcion {
		try{
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			Comunidad clase = (Comunidad) sesion.get(Comunidad.class,id);
			tx.commit();
			return clase;
		} catch (JDBCConnectionException e) {
			System.out.println("No se puede conectar con la BD. Error grave");
			throw new DAOConfiguracionExcepcion(e); 
		} catch (HibernateException e) { 
			rollback(); 
			throw new DAOExcepcion(e); 
		} finally {
			sesion.close();
		} 
	}
	
	@Override
	public Comunidad encontrarComunidadPorRecibo(int id_rec) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			Comunidad clase = (Comunidad) sesion.createQuery("select c from Comunidad as c inner join c.notaInformativas as n where n.reciboInmuebles.idRecibo = :id_rec").setInteger("id_rec", id_rec).uniqueResult();
			tx.commit();
			return clase; 
		} catch (JDBCConnectionException e) {
			System.out.println("No se puede conectar con la BD. Error grave");
			throw new DAOConfiguracionExcepcion(e); 
		} catch (HibernateException e) { 
			rollback(); 
			throw new DAOExcepcion(e); 
		} finally {
			sesion.close();
		}
	}

	private void rollback() { 
		try { 
			if (tx != null) { 
				tx.rollback(); 
			} 
		} catch (HibernateException ignored) { 
			System.out.println("No se puede hacer rollback de la transaccion.");
		} 
	}
}
