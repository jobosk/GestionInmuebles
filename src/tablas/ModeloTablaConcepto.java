package tablas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import control.ControlConcepto;
import control.ControlPropietario;
import excepciones.*;
import gestion.GestionConceptos;
import gestion.GestionPropietarios;
import hibernate.Comunidad;
import hibernate.Concepto;
import hibernate.Propietario;

public class ModeloTablaConcepto extends DefaultTableModel {

	private static final long serialVersionUID = 300498720224771024L;
	private GestionConceptos conges;

	public ModeloTablaConcepto (GestionConceptos con){
		super(null,new String[]{"ID","Clave concepto", "Descripción" });
		this.conges = con;
	}

	public void addConcepto (Concepto c) throws ConceptoYaExiste {
		conges.addConcepto(c);
		this.addToTabla(c);
	}

	public void borraConceptoPorPosicion(int row) throws ConceptoNoExiste{
		int nif = (Integer) getValueAt(row, 0);
		conges.borraConceptoPorId(nif);
		this.removeRow(row);
	}

	public void actualizar () throws DAOExcepcion  {
		ControlConcepto controladorConcepto = ControlConcepto.getControladorConcepto();
		this.getDataVector().clear();
		controladorConcepto.GetConceptos();

		ArrayList<Concepto> concepto = conges.ListConceptos();
		
		for(int j = 0;j<controladorConcepto.GetNumConceptos();j++) 
			this.addToTabla((Concepto) controladorConcepto.GetActual(j)); 
	}

	public Concepto recuperaConceptoPorPosicion(int row) throws ConceptoNoExiste{
		int id=(Integer)getValueAt(row, 0);
		return conges.getConceptoPorId(id);
	}

	public Concepto recuperaConceptoPorId(int id) throws ConceptoNoExiste{
		return conges.getConceptoPorId(id);
	}

	public void guardaConceptos(String fichero){
		conges.guardaListaConceptos(fichero);
	}

	private void addToTabla(Concepto i){
		Vector v=new Vector();
		v.add(i.getIdConcepto());
		v.add(i.getClaveConcepto());
		v.add(i.getDescripcion());		
		this.addRow(v);
	}
}