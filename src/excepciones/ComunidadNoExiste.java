package excepciones;

public class ComunidadNoExiste extends Exception{
	int error;
	
	public ComunidadNoExiste() {
		super();
	}
	public String getMessage(){
		
		return ("Error 2 : La comunidad no existe.");
	}
	
}
