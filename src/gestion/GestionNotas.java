package gestion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excepciones.ComunidadNoExiste;
import excepciones.NotaNoExiste;
import excepciones.NotaYaExiste;
import hibernate.Comunidad;
import hibernate.NotaInformativa;

public class GestionNotas {

	private ArrayList <NotaInformativa> prop;

	public GestionNotas() {
		prop = new ArrayList <NotaInformativa>();
	}

	public void addNotaInformativa(NotaInformativa nota)throws NotaYaExiste{
		for(int i=0; i<prop.size();i++)
		{
			if( prop.get(i).getIdNota() == nota.getIdNota()){
				throw new NotaYaExiste();
			}
		}
		this.prop.add(nota);

	}

	public NotaInformativa getNotaInformativaPorId(int id)throws NotaNoExiste {
		try{
			for(int i=0; i<prop.size();i++) {
				if( prop.get(i).getIdNota() == id ){
					return prop.get(i);
				}
			}
			throw new NotaNoExiste();
		} catch (NotaNoExiste e){
			return null;
		}
	}
	
	public void borraNotaInformativaPorId(int id)throws NotaNoExiste {
		try{
			Iterator <NotaInformativa> it =  prop.iterator();
			while(it.hasNext()){
				if(it.next().getIdNota() == id){
					it.remove();
					return;
				}
			}
			throw new NotaNoExiste();
		} catch (NotaNoExiste e){
			return;
		}
	}

	
	public ArrayList ListNotaInformativas()  {
		return prop;
	}
}