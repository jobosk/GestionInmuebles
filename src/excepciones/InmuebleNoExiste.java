package excepciones;

public class InmuebleNoExiste extends Exception{
	int error;
	
	public InmuebleNoExiste() {
		super();
	}
	public String getMessage(){
		
		return ("Error 2 : El inmueble no existe.");
	}
	
}
