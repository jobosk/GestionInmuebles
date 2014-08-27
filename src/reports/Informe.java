package reports;

import hibernate.Comunidad;

import java.util.HashMap;

import org.hibernate.Session;

import persistencia.UtilidadHibernate;




import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.view.JasperViewer;

public class Informe {

	private String fileName;

	public void generaInformeComunidad(int idCom, String file){
		fileName = file;
		JasperPrint informe = null;
		Session sesion= UtilidadHibernate.getSessionFactory().openSession();
		HashMap parametros = new HashMap();
		parametros.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,sesion);
		parametros.put("COM",idCom);
		try {
			informe= JasperFillManager.fillReport(fileName, parametros);
		} catch (JRException e){
			e.printStackTrace();
		}
		JasperViewer.viewReport(informe,false);
		System.out.print("Informe");
	}
	
	
	public void generaInformeConcepto(int idConcepto, String file){
		fileName = file;
		JasperPrint informe = null;
		Session sesion= UtilidadHibernate.getSessionFactory().openSession();
		HashMap parametros = new HashMap();
		parametros.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,sesion);
		parametros.put("COM",idConcepto);
		try {
			informe= JasperFillManager.fillReport(fileName, parametros);
		} catch (JRException e){
			e.printStackTrace();
		}
		JasperViewer.viewReport(informe,false);
		System.out.print("Informe");
	}
	

	
	
	public void generaInformeNotificacionNuevoRecibo(Comunidad com,String file){
		fileName = file;
		JasperPrint informe = null;
		Session sesion= UtilidadHibernate.getSessionFactory().openSession();
		HashMap parametros = new HashMap();
		parametros.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,sesion);
		parametros.put("COM",com);
		try {
			informe= JasperFillManager.fillReport(fileName, parametros);
		} catch (JRException e){
			e.printStackTrace();
		}
		JasperViewer.viewReport(informe,false);
		System.out.print("Informe");
	}
}
