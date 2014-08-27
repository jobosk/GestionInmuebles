package tablas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import control.ControlLinea;
import excepciones.*;
import control.ControlConcepto;
import gestion.GestionLineas;

import hibernate.*;

public class ModeloTablaLineaFactura extends DefaultTableModel {

	private static final long serialVersionUID = 300498720224771024L;
	private GestionLineas comges;
	private ControlConcepto concont = new ControlConcepto().getControladorConcepto();

	public ModeloTablaLineaFactura (GestionLineas com){
		super(null,new String[]{"Id linea","Importe","Observación","Id concepto","Descripción concepto"});
		this.comges= com;
	}

	public void addLinea (LineaFactura c) throws LineaFacturaYaExiste, DAOExcepcion {
		comges.addLineaFactura(c);
		this.addToTabla(c);	
	}

	public void borraLineaFacturaPorPosicion(int row, Comunidad c) throws LineaFacturaNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		comges.borraLineaFacturaPorId(id);
		this.removeRow(row);
	}

	public void actualizar (Factura c) throws DAOExcepcion  {
		ControlLinea controladorLineaFactura = ControlLinea.getControladorLinea();
		this.getDataVector().clear();
		controladorLineaFactura.GetLineas();
		ArrayList<LineaFactura> comunidad = comges.ListLineaFacturas();
		
		
		for(int j = 0;j<controladorLineaFactura.GetNumLineas();j++) {
			
			if(controladorLineaFactura.GetActual(j).getFactura().getNumFactura() ==c.getNumFactura()){
				this.addToTabla((LineaFactura) controladorLineaFactura.GetActual(j));
			}
		}
	}

	public LineaFactura recuperaLineaFacturaPorPosicion(int row, Comunidad c) throws LineaFacturaNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		return comges.getLineaFacturaPorId(id);
	}

	public LineaFactura recuperaLineaFacturaPorId(int id, Comunidad c) throws LineaFacturaNoExiste{
		return	comges.getLineaFacturaPorId(id);
	}

	public void guardaLineaFacturas(String fichero){
		comges.guardaListaLineaFacturas(fichero);
	}

	private void addToTabla(LineaFactura i) throws DAOExcepcion{
		
		Vector v=new Vector();
		
		v.add(i.getIdLinea());
		v.add(i.getImporteLinea());
		v.add(i.getObservacion());
		
		Concepto c = (Concepto) concont.EncontrarConceptoPorLinea(i.getIdLinea()); 
		v.add(c.getIdConcepto());
		v.add(c.getDescripcion());
		//v.add(concont.EncontrarConceptoPorLinea(i.getIdLinea()).getIdConcepto());
		//v.add(i.getConcepto().getIdConcepto());
		//v.add(i.getConcepto().getDescripcion());
		
		this.addRow(v);
	}
}