package excepciones;

public class FacturaNoExiste extends Exception{
	int error;
	
	public FacturaNoExiste() {
		super();
	}
	public String getMessage(){
		
		return ("Error 2 : La factura no existe.");
	}
	
}
