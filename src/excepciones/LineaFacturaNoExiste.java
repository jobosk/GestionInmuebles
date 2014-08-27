package excepciones;

public class LineaFacturaNoExiste extends Exception{
	int error;
	
	public LineaFacturaNoExiste() {
		super();
	}
	public String getMessage(){
		
		return ("Error 2 : La línea de la factura no existe.");
	}
	
}
