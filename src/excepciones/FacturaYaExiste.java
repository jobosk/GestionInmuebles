package excepciones;

public class FacturaYaExiste extends Exception{
	int error;

	public FacturaYaExiste() {
		super();
	}

	public String getMessage(){
		
		return ("Error 1: La factura ya existe.");
	}
	
}
