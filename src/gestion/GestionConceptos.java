package gestion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excepciones.ConceptoNoExiste;
import excepciones.ConceptoYaExiste;
import excepciones.PropietarioNoExiste;
import excepciones.PropietarioYaExiste;
import hibernate.Concepto;

public class GestionConceptos {

	private ArrayList <Concepto> com;

	public GestionConceptos() {
		com = new ArrayList <Concepto>();
	}
	
	public void addConcepto(Concepto mi_Concepto) throws ConceptoYaExiste {
		for(int i=0; i<com.size();i++){
			if( com.get(i).getIdConcepto() == mi_Concepto.getIdConcepto())
				throw new ConceptoYaExiste();
		}
		this.com.add(mi_Concepto);
	}

	public Concepto getConceptoPorId(int id) throws ConceptoNoExiste {
		try {
			for(int i=0; i<com.size();i++){
				if( com.get(i).getIdConcepto() == id ){
					return com.get(i);
				}
			}
			throw new ConceptoNoExiste();
		} catch (ConceptoNoExiste e) {
			return null;
		}
	}
	
	public void borraConceptoPorId(int id) throws ConceptoNoExiste {
		try {
			Iterator <Concepto> it =  com.iterator();
			while(it.hasNext()){
				if(it.next().getIdConcepto() == id){
					it.remove();
					return;
				}
			}
			throw new ConceptoNoExiste();
		} catch (ConceptoNoExiste e){
			return;
		}
	}

	public void guardaListaConceptos(String f) {
		try {
			ObjectOutputStream objout =  
				new ObjectOutputStream ( new FileOutputStream(f)); 
			objout.writeObject(com); 
			objout.close();
		} catch (IOException e) { 
			System.out.println("Error guardando archivo: "); 
		}
	}
	
	public ArrayList<Concepto> ListConceptos()  {
		return com;
	}
}