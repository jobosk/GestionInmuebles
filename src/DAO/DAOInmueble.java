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

public class DAOInmueble implements InterfazDAOInmueble {
	
	private Session sesion = null;
	private Transaction tx = null;
	
	@Override
	public void crearInmueble(Inmueble clase) throws DAOExcepcion {
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
	public void borrarInmueble(Inmueble clase) throws DAOExcepcion {
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
	public void actualizarInmueble(Inmueble clase) throws DAOExcepcion {
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
	public ArrayList<Inmueble> encontrarInmuebles() throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<Inmueble> clases = (ArrayList) sesion.createQuery("from Inmueble").list(); 
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
	public ArrayList<Inmueble> encontrarInmueblesPorComunidad(int id_com) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<Inmueble> clases = (ArrayList) sesion.createQuery("from Inmueble i where i.comunidad = :id_com").setInteger("id_com", id_com).list(); 
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
	public ArrayList<Inmueble> encontrarInmueblesPorPropietario(int nif_prop) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<Inmueble> clases = (ArrayList) sesion.createQuery("from Inmueble i where i.propietario = :nif_prop").setInteger("nif_prop", nif_prop).list(); 
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
	public Inmueble encontrarInmueblePorId(int id) throws DAOExcepcion {
		try{
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			Inmueble clase =(Inmueble) sesion.get(Inmueble.class,id);
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
	public Inmueble encontrarInmueblePorRecibo(int id_rec) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			Inmueble clase = (Inmueble) sesion.createQuery("from Inmueble i where i.reciboInmuebles.idRecibo = :id_rec").setInteger("id_rec", id_rec).uniqueResult(); 
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

	private void rollback(){ 
		try{ 
			if (tx != null){ 
				tx.rollback(); 
			} 
		}catch (HibernateException ignored){ 
			System.out.println("No se puede conectar con la BD. Error grave");
		} 
	}
}