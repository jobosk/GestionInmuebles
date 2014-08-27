package gestion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excepciones.InmuebleNoExiste;
import excepciones.InmuebleYaExiste;
import hibernate.Comunidad;
import hibernate.Inmueble;

public class GestionInmuebles {

	private int idComunidad;
	private ArrayList <Inmueble> inm;


	public GestionInmuebles() {
		inm =  new ArrayList <Inmueble>();
	}

	public void addInmueble(Inmueble mi_inmueble)throws InmuebleYaExiste{
		for ( Iterator iter = inm.iterator(); iter.hasNext(); ) { 
			Inmueble loadedInm = (Inmueble) iter.next();
			if( loadedInm.getId() == mi_inmueble.getId()){
				throw new InmuebleYaExiste();
			}
		}
		this.inm.add(mi_inmueble);
	}
	
	public Inmueble getInmueblePorId(int id)throws InmuebleNoExiste {
		try{
			for ( Iterator iter = inm.iterator(); iter.hasNext(); ) { 
				Inmueble loadedInm = (Inmueble) iter.next();
				if( loadedInm.getId() == id){	
					return loadedInm;
				}
			}
			throw new InmuebleNoExiste();
		} catch (InmuebleNoExiste e){
			return null;
		}
	}
	
	public void borraInmueblePorId(int id)throws InmuebleNoExiste {
		try{
			Iterator <Inmueble> it =  inm.iterator();
			while(it.hasNext()){
				if(it.next().getId() == id){
					it.remove();
					return;
				}
			}
			throw new InmuebleNoExiste();
		} catch (InmuebleNoExiste e){
			return;
		}
	}
	
	public void guardaListaInmuebles(String f) {
		try{
			ObjectOutputStream objout =  
				new ObjectOutputStream ( new FileOutputStream(f)); 
			objout.writeObject(inm); 
			objout.close();
		} catch (IOException e) { 
			System.out.println("Error guardando archivo: "); 
		}
	}
	
	public ArrayList<Inmueble> ListInmueble()  {
		return inm;
	}
}