package excepciones;

public class ComunidadYaExiste extends Exception{
	int error;

	public ComunidadYaExiste() {
		super();
	}

	public String getMessage(){
		
		return ("Error 1: La comunidad ya existe.");
	}
	
}
