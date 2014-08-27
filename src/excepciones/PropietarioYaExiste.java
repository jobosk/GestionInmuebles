package excepciones;

public class PropietarioYaExiste extends Exception{
	int error;

	public PropietarioYaExiste() {
		super();
	}

	public String getMessage(){
		
		return ("Error 1: El Propietario ya existe.");
	}
	
}
