package gestion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excepciones.FacturaNoExiste;
import excepciones.FacturaYaExiste;
import excepciones.PropietarioNoExiste;
import excepciones.PropietarioYaExiste;
import hibernate.Comunidad;
import hibernate.Factura;

public class GestionFacturas {

	private ArrayList <Factura> com;

	public GestionFacturas() {
		com = new ArrayList <Factura>();
	}
	
	public void addFactura(Factura mi_Factura) throws FacturaYaExiste {
		for(int i=0; i<com.size();i++){
			if( com.get(i).getNumFactura()==mi_Factura.getNumFactura())
				throw new FacturaYaExiste();
		}
		this.com.add(mi_Factura);
	}

	public Factura getFacturaPorId(int id) throws FacturaNoExiste {
		try {
			for(int i=0; i<com.size();i++){
				if(com.get(i).getNumFactura()== id) {
					return com.get(i);
				}
			}
			throw new FacturaNoExiste();
		} catch (FacturaNoExiste e) {
			return null;
		}
	}
	
	public void borraFacturaPorId(int id) throws FacturaNoExiste {
		try {
			Iterator <Factura> it =  com.iterator();
			while(it.hasNext()){
				if(it.next().getNumFactura() == id ){
					it.remove();
					return;
				}
			}
			throw new FacturaNoExiste();
		} catch (FacturaNoExiste e){
			return;
		}
	}

	public void guardaListaFacturas(String f) {
		try {
			ObjectOutputStream objout =  
				new ObjectOutputStream ( new FileOutputStream(f)); 
			objout.writeObject(com); 
			objout.close();
		} catch (IOException e) { 
			System.out.println("Error guardando archivo: "); 
		}
	}
	
	public ArrayList ListFacturas()  {
		return com;
	}
	
	
}