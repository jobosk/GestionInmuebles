package tablas;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import control.ControlInmueble;
import excepciones.DAOExcepcion;
import excepciones.InmuebleNoExiste;
import excepciones.InmuebleYaExiste;
import gestion.GestionInmuebles;
import hibernate.*;

public class ModeloTablaInmueble extends DefaultTableModel {

	private static final long serialVersionUID = 300498720224771024L;
	private GestionInmuebles inmges;

	public ModeloTablaInmueble (GestionInmuebles inm){
		super(null,new String[]{"ID", "Escalera", "Piso"});
		this.inmges = inm;
	}

	public void addInmueble (Inmueble i) throws InmuebleYaExiste {
		inmges.addInmueble(i);
		this.addToTabla(i);
	}

	public void borraInmueblePorPosicion(int row) throws InmuebleNoExiste{
		Integer id=(Integer)getValueAt(row, 0);
		inmges.borraInmueblePorId(id);
		this.removeRow(row);
	}
	
	public void actualizar () throws DAOExcepcion  {
		ControlInmueble controladorInmueble = ControlInmueble.getControladorInmueble();
		this.getDataVector().clear();
		controladorInmueble.GetInmuebles();
		ArrayList<Inmueble> inmueble = 	inmges.ListInmueble();
		System.out.println("ACTUALIZO");
		for(int j = 0;j<controladorInmueble.GetNumInmuebles();j++) 
			this.addToTabla((Inmueble) controladorInmueble.GetActual(j)); 
	}

	public Inmueble recuperaInmueblePorPosicion(int row) throws InmuebleNoExiste{
		Integer id=(Integer)getValueAt(row, 0);
		return inmges.getInmueblePorId(id);
	}

	/*public void cargaInmuebles(String fichero){
		gestion.cargaListaInmuebles(fichero);
		this.getDataVector().clear();
		ArrayList inmueble = gestion.ListInmueble();
		for(int j = 0;j<inmueble.size();j++) 
			this.addToTabla((Inmueble) inmueble.get(j)); 
	}*/

	public void guardaInmuebles(String fichero){
		inmges.guardaListaInmuebles(fichero);
	}

	private void addToTabla(Inmueble i){
		Vector v=new Vector();
		v.add(i.getId());
		v.add(i.getEscalera());
		v.add(i.getPiso());
		v.add(i.getPuerta());
		v.add(i.getPorcentaje());
		v.add(i.getPropietario().getNif());
		v.add(i.getComunidad().getIdComunidad());
		this.addRow(v);
	}
}