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

public class DAOPropietario implements InterfazDAOPropietario {

	private Session sesion = null;
	private Transaction tx = null;

	@Override
	public void crearPropietario(Propietario clase) throws DAOExcepcion {
		try{
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
	public void borrarPropietario(Propietario clase) throws DAOExcepcion {
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
	public void actualizarPropietario(Propietario clase) throws DAOExcepcion {
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
	public ArrayList<Propietario> encontrarPropietarios() throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<Propietario> clases = (ArrayList) sesion.createQuery("from Propietario").list(); 
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
	public Propietario encontrarPropietarioPorId(int id) throws DAOExcepcion {
		try{
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			Propietario clase = (Propietario) sesion.get(Propietario.class, id);
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
		try { 
			if (tx != null) { 
				tx.rollback(); 
			} 
		} catch (HibernateException ignored) { 
			System.out.println("No se puede hacer rollback de la transacción"); 
		} 
	}
}