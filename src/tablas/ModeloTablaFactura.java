package tablas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import control.ControlFactura;
import excepciones.*;
import gestion.GestionFacturas;

import hibernate.*;

public class ModeloTablaFactura extends DefaultTableModel {

	private static final long serialVersionUID = 300498720224771024L;
	private GestionFacturas comges;

	public ModeloTablaFactura (GestionFacturas com){
		super(null,new String[]{"Número","Id comunidad", "Fecha"});
		this.comges= com;
	}

	public void addFactura (Factura c) throws FacturaYaExiste {
		comges.addFactura(c);
		this.addToTabla(c);	
	}

	public void borraFacturaPorPosicion(int row, Comunidad c) throws FacturaNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		comges.borraFacturaPorId(id);
		this.removeRow(row);
	}

	public void actualizar (Comunidad c) throws DAOExcepcion  {
		ControlFactura controladorFactura = ControlFactura.getControladorFactura();
		this.getDataVector().clear();
		controladorFactura.GetFacturas();
		ArrayList<Factura> comunidad = comges.ListFacturas();
		System.out.println("ACTUALIZO");
		for(int j = 0;j<controladorFactura.GetNumFacturas();j++) 
			if(controladorFactura.GetActual(j).getComunidad().getIdComunidad()==c.getIdComunidad())
				this.addToTabla((Factura) controladorFactura.GetActual(j)); 
	}

	public Factura recuperaFacturaPorPosicion(int row, Comunidad c) throws FacturaNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		return comges.getFacturaPorId(id);
	}

	public Factura recuperaFacturaPorId(int id, Comunidad c) throws FacturaNoExiste{
		return	comges.getFacturaPorId(id);
	}

	public void guardaFacturas(String fichero){
		comges.guardaListaFacturas(fichero);
	}

	private void addToTabla(Factura i){
		Vector v=new Vector();
		v.add(i.getNumFactura());
		v.add(i.getComunidad().getIdComunidad());
		v.add(i.getFechaFactura());
		
		this.addRow(v);
	}
}