package excepciones;

public class LineaFacturaYaExiste extends Exception{
	int error;

	public LineaFacturaYaExiste() {
		super();
	}

	public String getMessage(){
		
		return ("Error 1: La l�nea de la factura ya existe.");
	}
	
}
