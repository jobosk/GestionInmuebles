package excepciones;

public class InmuebleYaExiste extends Exception{
	int error;

	public InmuebleYaExiste() {
		super();
	}

	public String getMessage(){
		
		return ("Error 1: El inmueble ya existe.");
	}
	
}

