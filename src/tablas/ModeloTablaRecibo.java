package tablas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import control.ControlComunidad;
import control.ControlInmueble;
import control.ControlRecibo;
import excepciones.*;
import control.ControlConcepto;
import gestion.GestionInmuebles;
import gestion.GestionLineas;
import gestion.GestionNotas;
import gestion.GestionRecibos;

import hibernate.*;

public class ModeloTablaRecibo extends DefaultTableModel {

	private static final long serialVersionUID = 300498720224771024L;
	private GestionRecibos recges;

	private ControlComunidad concom = new ControlComunidad().getControladorComunidad();
	private ControlInmueble coninm = new ControlInmueble().getControladorInmueble();

	public ModeloTablaRecibo (GestionRecibos com){
		super(null,new String[]{"Id recibo","Id comunidad","Id Inmueble","Porcentaje inmueble","Importe a pagar", "Fecha de pago"});
		this.recges= com;
	}

	public void addLinea (ReciboInmueble c) throws NotaYaExiste, DAOExcepcion {
		recges.addReciboInmueble(c);
		this.addToTabla(c);	
	}

	public void borraReciboInmueblePorPosicion(int row, Comunidad c) throws NotaNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		recges.borraReciboInmueblePorId(id);
		this.removeRow(row);
	}

	public void actualizar (Comunidad c) throws DAOExcepcion  {
		ControlRecibo controladorRecibo = ControlRecibo.getControladorRecibo();
		this.getDataVector().clear();
		controladorRecibo.GetRecibos();
		ArrayList <ReciboInmueble> recibo = recges.ListReciboInmuebles();
		for(int j = 0;j<controladorRecibo.GetNumRecibos();j++) {
			//if(controladorRecibo.GetActual(j).getNotaInformativa().getComunidad().getIdComunidad() == c.getIdComunidad())
				this.addToTabla((ReciboInmueble) controladorRecibo.GetActual(j));
		}
	}

	public ReciboInmueble recuperaNotaPorPosicion(int row, Comunidad c) throws NotaNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		return recges.getReciboInmueblePorId(id);
	}

	public ReciboInmueble recuperaNotaPorId(int id, Comunidad c) throws NotaNoExiste{
		return	recges.getReciboInmueblePorId(id);
	}

	private void addToTabla(ReciboInmueble i) throws DAOExcepcion{
		Comunidad com = (Comunidad) concom.EncontrarComunidadPorRecibo(i.getIdRecibo());
		Inmueble inm = (Inmueble) coninm.EncontrarInmueblePorRecibo(i.getIdRecibo());
		Vector v = new Vector();
		v.add(i.getIdRecibo());		
		v.add(com.getIdComunidad());
		v.add(inm.getId());
		v.add(inm.getPorcentaje());
		v.add(i.getImporte());
		v.add(i.getFechaPago());
		this.addRow(v);
	}
}