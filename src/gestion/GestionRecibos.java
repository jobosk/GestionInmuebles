package gestion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excepciones.NotaNoExiste;
import excepciones.NotaYaExiste;
import hibernate.ReciboInmueble;

public class GestionRecibos {

	private ArrayList <ReciboInmueble> rec;

	public GestionRecibos() {
		rec = new ArrayList <ReciboInmueble>();
	}

	public void addReciboInmueble(ReciboInmueble nota)throws NotaYaExiste{
		for(int i=0; i<rec.size();i++)
		{
			if( rec.get(i).getIdRecibo() == nota.getIdRecibo()){
				throw new NotaYaExiste();
			}
		}
		this.rec.add(nota);

	}

	public ReciboInmueble getReciboInmueblePorId(int id)throws NotaNoExiste {
		try{
			for(int i=0; i<rec.size();i++) {
				if( rec.get(i).getIdRecibo() == id ){
					return rec.get(i);
				}
			}
			throw new NotaNoExiste();
		} catch (NotaNoExiste e){
			return null;
		}
	}
	
	public void borraReciboInmueblePorId(int id)throws NotaNoExiste {
		try{
			Iterator <ReciboInmueble> it =  rec.iterator();
			while(it.hasNext()){
				if(it.next().getIdRecibo() == id){
					it.remove();
					return;
				}
			}
			throw new NotaNoExiste();
		} catch (NotaNoExiste e){
			return;
		}
	}
	
	public ArrayList ListReciboInmuebles()  {
		return rec;
	}
}