package tablas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import control.ControlComunidad;
import excepciones.*;
import gestion.GestionComunidades;

import hibernate.*;

public class ModeloTablaComunidad extends DefaultTableModel {

	private static final long serialVersionUID = 300498720224771024L;
	private GestionComunidades comges;

	public ModeloTablaComunidad (GestionComunidades com){
		super(null,new String[]{"ID", "Calle"});
		this.comges= com;
	}

	public void addComunidad (Comunidad c) throws ComunidadYaExiste {
		comges.addComunidad(c);
		this.addToTabla(c);	
	}

	public void borraComunidadPorPosicion(int row) throws ComunidadNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		comges.borraComunidadPorId(id);
		this.removeRow(row);
	}

	public void actualizar() throws DAOExcepcion  {
		ControlComunidad controladorComunidad = ControlComunidad.getControladorComunidad();
		this.getDataVector().clear();
		controladorComunidad.GetComunidades();
		ArrayList <Comunidad> comunidad = comges.ListComunidades();
		System.out.println("ACTUALIZO");
		for(int j = 0;j<controladorComunidad.GetNumComunidades();j++) 
			this.addToTabla((Comunidad) controladorComunidad.GetActual(j)); 
	}

	public Comunidad recuperaComunidadPorPosicion(int row) throws ComunidadNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		return comges.getComunidadPorId(id);
	}

	public Comunidad recuperaComunidadPorId(int id) throws ComunidadNoExiste{
		return	comges.getComunidadPorId(id);
	}

	public void guardaComunidades(String fichero){
		comges.guardaListaComunidades(fichero);
	}

	private void addToTabla(Comunidad i){
		Vector v=new Vector();
		v.add(i.getIdComunidad());
		v.add(i.getCalle());
		v.add(i.getMaxRecibosPendientes());
		v.add(i.getEstado());
		this.addRow(v);
	}
}