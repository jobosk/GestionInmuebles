package pruebas;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import control.*;
import excepciones.DAOExcepcion;
import hibernate.*;
public class Prueba {

	public static void main(String[] args) throws DAOExcepcion {
		
		ControlInmueble coninm = ControlInmueble.getControladorInmueble();
		ControlComunidad concom = ControlComunidad.getControladorComunidad();
		//ControladorFactura confac = ControladorFactura.getControladorFactura();
		
		//int a = concom.GetNumComunidades();
		
		concom.NuevaComunidad(new Comunidad(1,"1",1,"asd",null,null,null));
		
		
		//Comunidad c = new Comunidad(22,"falsa",3,"ko",null,null,null);
		
		
		//Factura in = new Factura(1,nota,c,"13",lin); 
		System.out.println("asdsd");
		
		//System.out.println(concom.EncontrarComunidadPorId(122).getCalle());
		//confac.NuevaFactura(in);
		//System.out.println(confac.EncontrarFacturaPorId(1).getFechaFactura());
		
	}
}
