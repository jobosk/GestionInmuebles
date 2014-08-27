package gestion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excepciones.ComunidadNoExiste;
import excepciones.PropietarioNoExiste;
import excepciones.PropietarioYaExiste;
import hibernate.Comunidad;
import hibernate.Propietario;

public class GestionPropietarios {

	private ArrayList <Propietario> prop;

	public GestionPropietarios() {
		prop = new ArrayList <Propietario>();
	}

	public void addPropietario(Propietario mi_propietario)throws PropietarioYaExiste{
		for(int i=0; i<prop.size();i++)
		{
			if( prop.get(i).getNif() == mi_propietario.getNif()){
				throw new PropietarioYaExiste();
			}
		}
		this.prop.add(mi_propietario);

	}

	public Propietario getPropietarioPorNif(int nif)throws PropietarioNoExiste {
		try{
			for(int i=0; i<prop.size();i++) {
				if( prop.get(i).getNif() == nif ){
					return prop.get(i);
				}
			}
			throw new PropietarioNoExiste();
		} catch (PropietarioNoExiste e){
			return null;
		}
	}
	
	public void borraPropietarioPorId(int nif)throws PropietarioNoExiste {
		try{
			Iterator <Propietario> it =  prop.iterator();
			while(it.hasNext()){
				if(it.next().getNif() == nif){
					it.remove();
					return;
				}
			}
			throw new PropietarioNoExiste();
		} catch (PropietarioNoExiste e){
			return;
		}
	}

	public void guardaListaPropietarios(String f) {
		try{
			ObjectOutputStream objout =  
				new ObjectOutputStream ( new FileOutputStream(f)); 
			objout.writeObject(prop); 
			objout.close();
		} catch (IOException e) { 
			System.out.println("Error guardando archivo: "); 
		}
	}
	
	public ArrayList ListPropietarios()  {
		return prop;
	}
}