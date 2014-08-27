package excepciones;

public class ConceptoNoExiste extends Exception{
	int error;
	
	public ConceptoNoExiste() {
		super();
	}
	public String getMessage(){
		
		return ("Error 2 : El Concepto no existe.");
	}
	
}
