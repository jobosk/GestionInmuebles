package tablas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import control.ControlPropietario;
import excepciones.*;
import gestion.GestionPropietarios;
import hibernate.Propietario;

public class ModeloTablaPropietario extends DefaultTableModel {

	private static final long serialVersionUID = 300498720224771024L;
	private GestionPropietarios propges;

	public ModeloTablaPropietario (GestionPropietarios prop){
		super(null,new String[]{"NIF", "Nombre", "Direccion" });
		this.propges = prop;
	}

	public void addPropietario (Propietario p) throws PropietarioYaExiste {
		propges.addPropietario(p);
		this.addToTabla(p);
	}

	public void borraPropietarioPorPosicion(int row) throws PropietarioNoExiste{
		int nif = (Integer) getValueAt(row, 0);
		propges.borraPropietarioPorId(nif);
		this.removeRow(row);
	}

	public void actualizar () throws DAOExcepcion  {
		ControlPropietario controladorPropietario = ControlPropietario.getControladorPropietario();
		this.getDataVector().clear();
		controladorPropietario.GetPropietarios();
		ArrayList<Propietario> propietario = propges.ListPropietarios();
		System.out.println("ACTUALIZO");
		for(int j = 0;j<controladorPropietario.GetNumPropietarios();j++) 
			this.addToTabla((Propietario) controladorPropietario.GetActual(j)); 
	}

	public Propietario recuperaPropietarioPorPosicion(int row) throws PropietarioNoExiste{
		int id=(Integer)getValueAt(row, 0);
		return propges.getPropietarioPorNif(id);
	}

	public Propietario recuperaPropietarioPorId(int nif) throws PropietarioNoExiste{
		return	propges.getPropietarioPorNif(nif);
	}

	/*public void cargaPropietarios(String fichero){
		propietario.cargaListaPropietarios(fichero);
		this.getDataVector().clear();
		ArrayList Propietario = propietario.ListPropietario();
		for(int j = 0;j<Propietario.size();j++) 
			this.addToTabla((Propietario) Propietario.get(j)); 
	}*/

	public void guardaPropietarios(String fichero){
		propges.guardaListaPropietarios(fichero);
	}

	private void addToTabla(Propietario i){
		Vector v=new Vector();
		v.add(i.getNif());
		v.add(i.getNombre());
		v.add(i.getDireccion());
		v.add(i.getPoblacion());
		v.add(i.getTelefono());
		v.add(i.getObservaciones());
		v.add(i.getFechaAlta());
		v.add(i.getEntidad());
		v.add(i.getNumeroCuenta());
		this.addRow(v);
	}
}