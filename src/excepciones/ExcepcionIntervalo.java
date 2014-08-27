package excepciones;

import javax.swing.JOptionPane;

public class ExcepcionIntervalo extends Exception {
	Object[] options = {"Aceptar"}; 
	int n = JOptionPane.showOptionDialog(
			null, "El porcentaje debe ser menor que 100.","Error",JOptionPane.OK_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null, 
			options,
			options[0]);
}
