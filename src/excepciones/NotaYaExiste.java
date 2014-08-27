package excepciones;

public class NotaYaExiste extends Exception{
	int error;

	public NotaYaExiste() {
		super();
	}

	public String getMessage(){
		
		return ("Error 1: La nota ya existe.");
	}
	
}
