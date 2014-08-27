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

public class DAOConcepto implements InterfazDAOConcepto{

	private Session sesion = null;
	private Transaction tx = null;

	@Override
	public void crearConcepto(Concepto clase) throws DAOExcepcion{
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
	public void borrarConcepto(Concepto clase) throws DAOExcepcion{
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
	public void actualizarConcepto(Concepto clase) throws DAOExcepcion{
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
	public ArrayList<Concepto> encontrarConceptos() throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<Concepto> clases = (ArrayList) sesion.createQuery("from Concepto").list(); 
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
	public Concepto encontrarConceptoPorId(int id) throws DAOExcepcion{
		try{
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			Concepto clase = (Concepto) sesion.get(Concepto.class,id);
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
	public Concepto encontrarConceptoPorLinea(int id_lin) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			Concepto clase = (Concepto) sesion.createQuery("from Concepto c where c.lineaFacturas.idLinea = :id_lin").setInteger("id_lin", id_lin).uniqueResult(); 
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