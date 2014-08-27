package gestion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import control.ControlLinea;

import excepciones.DAOExcepcion;
import excepciones.LineaFacturaNoExiste;
import excepciones.LineaFacturaYaExiste;
import excepciones.PropietarioNoExiste;
import excepciones.PropietarioYaExiste;
import hibernate.Comunidad;
import hibernate.Factura;
import hibernate.LineaFactura;

public class GestionLineas {

	private ArrayList <LineaFactura> com;

	public GestionLineas() {
		com = new ArrayList <LineaFactura>();
	}

	public void addLineaFactura(LineaFactura mi_LineaFactura) throws LineaFacturaYaExiste {
		for(int i=0; i<com.size();i++){
			if( com.get(i).getIdLinea()==mi_LineaFactura.getIdLinea())
				throw new LineaFacturaYaExiste();
		}
		this.com.add(mi_LineaFactura);
	}

	public LineaFactura getLineaFacturaPorId(int id) throws LineaFacturaNoExiste {
		try {
			for(int i=0; i<com.size();i++){
				if(com.get(i).getIdLinea()== id) {
					return com.get(i);
				}
			}
			throw new LineaFacturaNoExiste();
		} catch (LineaFacturaNoExiste e) {
			return null;
		}
	}

	public void borraLineaFacturaPorId(int id) throws LineaFacturaNoExiste {
		try {
			Iterator <LineaFactura> it =  com.iterator();
			while(it.hasNext()){
				if(it.next().getIdLinea() == id ){
					it.remove();
					return;
				}
			}
			throw new LineaFacturaNoExiste();
		} catch (LineaFacturaNoExiste e){
			return;
		}
	}

	public void guardaListaLineaFacturas(String f) {
		try {
			ObjectOutputStream objout =  
				new ObjectOutputStream ( new FileOutputStream(f)); 
			objout.writeObject(com); 
			objout.close();
		} catch (IOException e) { 
			System.out.println("Error guardando archivo: "); 
		}
	}

	public ArrayList ListLineaFacturas()  {
		return com;
	}




}