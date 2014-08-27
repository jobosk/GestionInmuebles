package tablas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import control.ControlLinea;
import control.ControlNota;
import excepciones.*;
import control.ControlConcepto;
import gestion.GestionLineas;
import gestion.GestionNotas;

import hibernate.*;

public class ModeloTablaNota extends DefaultTableModel {

	private static final long serialVersionUID = 300498720224771024L;
	private GestionNotas comges;
	private ControlNota conges = new ControlNota().getControladorNota();

	public ModeloTablaNota (GestionNotas com){
		super(null,new String[]{"Id nota","Importe","Id comunidad","Fecha"});
		this.comges= com;
	}

	public void addLinea (NotaInformativa c) throws NotaYaExiste, DAOExcepcion {
		comges.addNotaInformativa(c);
		this.addToTabla(c);	
	}

	public void borraNotaInformativaPorPosicion(int row, Comunidad c) throws NotaNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		comges.borraNotaInformativaPorId(id);
		this.removeRow(row);
	}

	public void actualizar (Comunidad c) throws DAOExcepcion  {
		ControlNota controladorNota = ControlNota.getControladorNota();
		this.getDataVector().clear();
		controladorNota.GetNotas();
		ArrayList<NotaInformativa> comunidad = comges.ListNotaInformativas();
		
		
		for(int j = 0;j<controladorNota.GetNumNotas();j++) {
			
			if(controladorNota.GetActual(j).getComunidad().getIdComunidad()==c.getIdComunidad()){
				this.addToTabla((NotaInformativa) controladorNota.GetActual(j));
			}
		}
	}

	public NotaInformativa recuperaNotaPorPosicion(int row, Comunidad c) throws NotaNoExiste{
		int id =  (Integer) getValueAt(row, 0);
		return comges.getNotaInformativaPorId(id);
	}

	public NotaInformativa recuperaNotaPorId(int id, Comunidad c) throws NotaNoExiste{
		return	comges.getNotaInformativaPorId(id);
	}

	private void addToTabla(NotaInformativa i) throws DAOExcepcion{
		
		Vector v=new Vector();		
		
		v.add(i.getIdNota());		
		v.add(i.getImporteNota());		
		v.add(i.getComunidad().getIdComunidad());
		v.add(i.getFechaCalculo());
		this.addRow(v);
	}
}