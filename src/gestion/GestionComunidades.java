package gestion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excepciones.ComunidadNoExiste;
import excepciones.ComunidadYaExiste;
import hibernate.Comunidad;

public class GestionComunidades {

	private ArrayList<Comunidad> com;

	public GestionComunidades() {
		com = new ArrayList<Comunidad>();
	}
	
	public void addComunidad(Comunidad mi_Comunidad) throws ComunidadYaExiste {
		for(int i=0; i<com.size();i++){
			if( com.get(i).getIdComunidad() == mi_Comunidad.getIdComunidad())
				throw new ComunidadYaExiste();
		}
		this.com.add(mi_Comunidad);
	}

	public Comunidad getComunidadPorId(int id) throws ComunidadNoExiste {
		try {
			for(int i=0; i<com.size();i++){
				if( com.get(i).getIdComunidad() == id ){
					return com.get(i);
				}
			}
			throw new ComunidadNoExiste();
		} catch (ComunidadNoExiste e) {
			return null;
		}
	}
	
	public void borraComunidadPorId(int id) throws ComunidadNoExiste {
		try {
			Iterator <Comunidad> it =  com.iterator();
			while(it.hasNext()){
				if(it.next().getIdComunidad() == id){
					it.remove();
					return;
				}
			}
			throw new ComunidadNoExiste();
		} catch (ComunidadNoExiste e){
			return;
		}
	}

	public void guardaListaComunidades(String f) {
		try {
			ObjectOutputStream objout =  
				new ObjectOutputStream ( new FileOutputStream(f)); 
			objout.writeObject(com); 
			objout.close();
		} catch (IOException e) { 
			System.out.println("Error guardando archivo: "); 
		}
	}
	
	public ArrayList ListComunidades()  {
		return com;
	}
}