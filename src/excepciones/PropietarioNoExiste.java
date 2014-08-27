package excepciones;

public class PropietarioNoExiste extends Exception{
	int error;
	
	public PropietarioNoExiste() {
		super();
	}
	public String getMessage(){
		
		return ("Error 2 : El Propietario no existe.");
	}
	
}
