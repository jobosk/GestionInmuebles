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

public class DAONota implements InterfazDAONota{
	
	private Session sesion = null;
	private Transaction tx = null;
	
	@Override
	public void crearNota(NotaInformativa clase) throws DAOExcepcion{
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
	public void borrarNota(NotaInformativa clase) throws DAOExcepcion{
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
	public void actualizarNota(NotaInformativa clase) throws DAOExcepcion{
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
	public ArrayList<NotaInformativa> encontrarNotas() throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<NotaInformativa> clases = (ArrayList) sesion.createQuery("from NotaInformativa").list(); 
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
	public ArrayList<NotaInformativa> encontrarNotasPorComunidad(int id_com) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<NotaInformativa> clases = (ArrayList) sesion.createQuery("from NotaInformativa n where n.comunidad.idComunidad = :id_com").setInteger("id_com", id_com).list(); 
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
	public NotaInformativa encontrarNotaPorId(int id) throws DAOExcepcion{
		try{
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			NotaInformativa clase = (NotaInformativa) sesion.get(NotaInformativa.class,id);
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
	public NotaInformativa encontrarNotaPorRecibo(int id_rec) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction();
			NotaInformativa clase = (NotaInformativa) sesion.createQuery("from NotaInformativa n where n.reciboInmuebles.idRecibo = :id_rec").setInteger("id_rec", id_rec).uniqueResult(); 
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
