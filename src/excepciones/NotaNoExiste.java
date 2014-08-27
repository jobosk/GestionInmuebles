package excepciones;

public class NotaNoExiste extends Exception{
	int error;
	
	public NotaNoExiste() {
		super();
	}
	public String getMessage(){
		
		return ("Error 2 : La nota no existe.");
	}
	
}
