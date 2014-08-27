package excepciones;

public class ConceptoYaExiste extends Exception{
	int error;

	public ConceptoYaExiste() {
		super();
	}

	public String getMessage(){
		
		return ("Error 1: El Concepto ya existe.");
	}
	
}
