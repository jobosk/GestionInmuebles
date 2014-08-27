package interfaz;

import hibernate.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import control.*;
import excepciones.ComunidadNoExiste;
import excepciones.ConceptoYaExiste;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;
import excepciones.ExcepcionIntervalo;
import excepciones.InmuebleYaExiste;
import excepciones.PropietarioNoExiste;
import tablas.*;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class VentanaDetalleConcepto extends javax.swing.JFrame {
	private JPanel Panel;
	private JLabel label1;
	private JPanel jContent;
	private JTextField descripcionTextField;
	private JButton botonGuardar;
	private JButton botonVolver;
	private JPanel PanelSur;
	private JTextField claveTextField;
	private JTextField idTextField;
	private JLabel jLabel3;
	private JLabel jLabel2;
	ModeloTablaConcepto modelo;
	Concepto concepto;
	boolean esDetalle;
	/**
	 * Auto-generated main method to display this JFrame
	 */
	
	ControlConcepto controladorConcepto = ControlConcepto.getControladorConcepto();  //  @jve:decl-index=0:
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaDetalleConcepto inst = new VentanaDetalleConcepto();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaDetalleConcepto() {
		super();
		initGUI();
	}
	public VentanaDetalleConcepto(TableModel modelo){
		super();
		this.modelo = (ModeloTablaConcepto) modelo;
		esDetalle = true;
		initGUI();		

	}

	private int sugiereNumeroConcepto() throws DAOExcepcion{
		for(int i = 1;i<1000000;i++){
			if(controladorConcepto.EncontrarConceptoPorId(i)==null) return i;
			}
		return -1;
		}

	public VentanaDetalleConcepto(Concepto i){
		super();
		concepto = i;
		//this.modelo = (ModeloTablaConcepto) modelo;	
		
		esDetalle = false;
		initGUI();
	
	}
	
	
	private void SalvarNueva() throws ConceptoYaExiste, DAOExcepcion, NumberFormatException, ComunidadNoExiste, PropietarioNoExiste
	{ // salva una nueva película
		// instancia a insertar.....
			
		ControlComunidad comunidades = new ControlComunidad();
		ControlPropietario propietarios = new ControlPropietario();
		
		Concepto in = new Concepto(); 
				in.setIdConcepto(Integer.parseInt(idTextField.getText()));
				in.setClaveConcepto((claveTextField.getText()));
				in.setDescripcion(descripcionTextField.getText()); 
				
				try {
			controladorConcepto.NuevoConcepto(in);
			modelo.addConcepto(in);
		} catch (DAOConfiguracionExcepcion e2)
	    {
		JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		}
		
        
        
		
	}
	
	private void SalvarEditada() throws NumberFormatException, DAOExcepcion
	{ // Cambiar datos de un concepto.
		

		
		ControlComunidad comunidades = new ControlComunidad();
		ControlPropietario propietarios = new ControlPropietario();
		
		Concepto in = new Concepto(); 
		in.setIdConcepto(Integer.parseInt(idTextField.getText()));
		in.setClaveConcepto((claveTextField.getText()));
		in.setDescripcion(descripcionTextField.getText()); 
	      
		try {
			controladorConcepto.ActualizarConcepto(in);
		
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la capa de acceso a datos", "No se puede actualiar la Película", JOptionPane.ERROR_MESSAGE);
		}  catch (DAOConfiguracionExcepcion e2)
	    {
		JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		// System.exit(0);
	    }
	
	}
	
	private boolean guardarCambios() throws ConceptoYaExiste, DAOExcepcion, NumberFormatException, ComunidadNoExiste, PropietarioNoExiste
	{ 
		   // entradas correctas.
			if (esDetalle) SalvarNueva();
			else SalvarEditada();
			return true;	
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (esDetalle)this.setTitle("Añadir concepto");
			else this.setTitle("Editar concepto");
			{
				jContent = new JPanel();
				getContentPane().add(jContent);
				jContent.setBounds(7, 0, 10, 10);
				jContent.setName("Datos Concepto");
				{
					Panel = new JPanel();
					jContent.add(Panel);
					Panel.setLayout(null);
					Panel.setPreferredSize(new java.awt.Dimension(470, 288));
					{
						label1 = new JLabel();
						Panel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						label1.setText("Id");
						label1.setBounds(36, 26, 23, 19);
					}
					{
						idTextField = new JTextField();
						Panel.add(idTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						idTextField.setText(Integer.toString(sugiereNumeroConcepto()));
						idTextField.setBounds(161, 24, 244, 23);
					}
					{
						jLabel2 = new JLabel();
						Panel.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel2.setText("Clave");
						jLabel2.setBounds(36, 62, 42, 16);
					}
					{
						claveTextField = new JTextField();
						Panel.add(claveTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						claveTextField.setText("Clave");
						claveTextField.setBounds(161, 59, 244, 23);
					}
					{
						jLabel3 = new JLabel();
						Panel.add(jLabel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel3.setText("Descripción");
						jLabel3.setBounds(36, 102, 101, 16);
					}
					{
						descripcionTextField = new JTextField();
						Panel.add(descripcionTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						descripcionTextField.setText("Descripción");
						descripcionTextField.setBounds(161, 99, 244, 23);
					}
				}
				if(!esDetalle){
				idTextField.setText(String.valueOf(concepto.getIdConcepto()));
				idTextField.setEditable(false);
				descripcionTextField.setText(concepto.getDescripcion());
				
				claveTextField.setText(concepto.getClaveConcepto());
				
				}
				{
					PanelSur = new JPanel();
					jContent.add(PanelSur);
					{
						botonGuardar = new JButton();
						PanelSur.add(botonGuardar);
						botonGuardar.setText("Guardar");
						botonGuardar.setPreferredSize(new java.awt.Dimension(86, 23));
						botonGuardar.setEnabled(true);
						botonGuardar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("botonGuardar.actionPerformed, event="+evt);
								int id = -1;
								boolean guarda = true;
								try{															
									id =  Integer.parseInt(idTextField.getText());
									}
								catch (Exception e) {
									guarda=false;
									Object[] options = {"Aceptar"}; 
									int n = JOptionPane.showOptionDialog(
									null, "Formato de ID no válido.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null, 
									options,
									options[0]);						
								}
								
							
								if(guarda){
									if(claveTextField.getText().length()>0 &&
											descripcionTextField.getText().length()>0)
											{
										//AÑADIR A BD
											try {
												if (guardarCambios()){
													dispose();}
											} catch (ConceptoYaExiste e) {
												// TODO Auto-generated catch block
												Object[] options = {"Aceptar"}; 
												int n = JOptionPane.showOptionDialog(
												null, "El concepto ya existe.","Error",JOptionPane.OK_OPTION,
												JOptionPane.QUESTION_MESSAGE,
												null, 
												options,
												options[0]);
												e.printStackTrace();
											} catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
												Object[] options = {"Aceptar"}; 
												int n = JOptionPane.showOptionDialog(
												null, "Error en la base de datos.","Error",JOptionPane.OK_OPTION,
												JOptionPane.QUESTION_MESSAGE,
												null, 
												options,
												options[0]);
												e.printStackTrace();
											} catch (NumberFormatException e) {
												// TODO Auto-generated catch block
											
												e.printStackTrace();
											} catch (ComunidadNoExiste e) {
												// TODO Auto-generated catch block
												Object[] options = {"Aceptar"}; 
												int n = JOptionPane.showOptionDialog(
												null, "La comunidad no existe.","Error",JOptionPane.OK_OPTION,
												JOptionPane.QUESTION_MESSAGE,
												null, 
												options,
												options[0]);
												e.printStackTrace();
											} catch (PropietarioNoExiste e) {
												// TODO Auto-generated catch block
												Object[] options = {"Aceptar"}; 
												int n = JOptionPane.showOptionDialog(
												null, "El propietario no existe.","Error",JOptionPane.OK_OPTION,
												JOptionPane.QUESTION_MESSAGE,
												null, 
												options,
												options[0]);
												e.printStackTrace();
											}
										
									}
									else {
										Object[] options = {"Aceptar"}; 
										int n = JOptionPane.showOptionDialog(
										null, "Por favor, rellena todos los campos.","Error",JOptionPane.OK_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										null, 
										options,
										options[0]);
									}
								}
							}

						

						//TODO add your code for botonGuardar.actionPerformed
					
				});
			}
			{
				botonVolver = new JButton();
				PanelSur.add(botonVolver);
				botonVolver.setText("Volver");
				botonVolver.setPreferredSize(new java.awt.Dimension(94, 23));
				botonVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("botonVolver.actionPerformed, event="+evt);
						//TODO add your code for botonVolver.actionPerformed
						dispose();
					}
				});
			}
		}
	}
	pack();
	setSize(500, 400);
} catch (Exception e) {
	//add your error handling code here
	e.printStackTrace();
}
}

}
