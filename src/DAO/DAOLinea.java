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

public class DAOLinea implements InterfazDAOLinea{
	
	private Session sesion = null;
	private Transaction tx = null;
	
	@Override
	public void crearLinea(LineaFactura clase) throws DAOExcepcion{
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
	public void borrarLinea(LineaFactura clase) throws DAOExcepcion{
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
	public void actualizarLinea(LineaFactura clase) throws DAOExcepcion{
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
	public ArrayList<LineaFactura> encontrarLineas() throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<LineaFactura> clases = (ArrayList) sesion.createQuery("from LineaFactura").list(); 
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
	public ArrayList<LineaFactura> encontrarLineasPorFactura(int id_fac) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<LineaFactura> clases = (ArrayList) sesion.createQuery("from LineaFactura l where l.factura.numFactura = :id_fac").setInteger("id_fac", id_fac).list(); 
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
	public ArrayList<LineaFactura> encontrarLineasPorConcepto(int id_con) throws DAOExcepcion {
		try { 
			sesion = UtilidadHibernate.getSessionFactory().openSession(); 
			tx = sesion.beginTransaction(); 
			ArrayList<LineaFactura> clases = (ArrayList) sesion.createQuery("from LineaFactura l where l.concepto.idConcepto = :id_con").setInteger("id_con", id_con).list(); 
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
	public LineaFactura encontrarLineaPorId(int id) throws DAOExcepcion{
		try{
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			LineaFactura clase = (LineaFactura) sesion.get(LineaFactura.class,id);
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