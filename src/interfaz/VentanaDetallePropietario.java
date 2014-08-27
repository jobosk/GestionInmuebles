package interfaz;

import hibernate.Propietario;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

import control.ControlInmueble;
import control.ControlPropietario;

///import negocio.ControladorComunidad;

import tablas.ModeloTablaComunidad;
import tablas.ModeloTablaPropietario;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;
import excepciones.ComunidadYaExiste;
import excepciones.PropietarioYaExiste;


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
public class VentanaDetallePropietario extends javax.swing.JFrame {
	private JPanel Panel;
	private JLabel label1;
	private JLabel jLabel5;
	private JTextField FechaAltaTextField;
	private JTextField NumeroCuentaTextField;
	private JTextField EntidadTextField1;
	private JTextField ObservacionesTextField;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JTextField TelefonoTextField;
	private JLabel jLabel4;
	private JPanel jContent;
	private JTextField DireccionTextField;
	private JButton botonGuardar;
	private JButton botonVolver;
	private JPanel PanelSur;
	private JTextField PoblacionTextField;
	private JTextField nombreTextField;
	private JTextField idTextField;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JLabel jLabel2;
	ModeloTablaPropietario modelo;
	Propietario propietario;
	ControlPropietario controladorPropietario = ControlPropietario.getControladorPropietario();  //  @jve:decl-index=0:
	boolean esDetalle;
	private AbstractButton porcentajeTextField;
	
	
	
	
	/**
	 * Auto-generated main method to display this JFrame
	 */
	
	//ControladorPropietario controladorPropietario = ControladorPropietario.getControladorPropietario();  //  @jve:decl-index=0:
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaDetallePropietario inst = new VentanaDetallePropietario();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaDetallePropietario() {
		super();
		initGUI();
	}
	public VentanaDetallePropietario(TableModel modelo){
		super();
		this.modelo = (ModeloTablaPropietario) modelo;
		esDetalle = true;
		initGUI();		

	}


	public VentanaDetallePropietario(Propietario i){
		super();
		propietario = i;
//this.modelo = (ModeloTablaInmueble) modelo;	
		
		esDetalle = false;
		initGUI();
	}
	
	private void SalvarNueva() throws PropietarioYaExiste, DAOExcepcion
	{ // salva una nueva película
		// instancia a insertar.....
			
		
		Propietario in = new Propietario();
		
			in.setNif(Integer.parseInt(idTextField.getText()));
			in.setDireccion(DireccionTextField.getText());
			in.setEntidad(EntidadTextField1.getText());
			in.setFechaAlta(FechaAltaTextField.getText());
			in.setNombre(nombreTextField.getText());
			in.setNumeroCuenta(NumeroCuentaTextField.getText());
			in.setObservaciones(ObservacionesTextField.getText());
			in.setPoblacion(PoblacionTextField.getText());
			in.setTelefono(TelefonoTextField.getText());
			in.setInmuebles(null);
			
			
        try {
			controladorPropietario.NuevoPropietario(in);
			modelo.addPropietario(in);
		} catch (DAOConfiguracionExcepcion e2)
	    {
		JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		}
		
        
        
		
	}
	
	private void SalvarEditada() throws DAOExcepcion
	{ // Cambiar datos de una película.
		

		    Propietario in = new Propietario();
		
			in.setNif(Integer.parseInt(idTextField.getText()));
			in.setDireccion(DireccionTextField.getText());
			in.setEntidad(EntidadTextField1.getText());
			in.setFechaAlta(FechaAltaTextField.getText());
			in.setNombre(nombreTextField.getText());
			in.setNumeroCuenta(NumeroCuentaTextField.getText());
			in.setObservaciones(ObservacionesTextField.getText());
			in.setPoblacion(PoblacionTextField.getText());
			in.setTelefono(TelefonoTextField.getText());
			in.setInmuebles(null);
			
			  
		try {
			controladorPropietario.ActualizarPropietario(in);
			
		} catch (DAOConfiguracionExcepcion e2)
	    {
		JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		// System.exit(0);
	    }
	
	}
	
	private boolean guardarCambios() throws PropietarioYaExiste, DAOExcepcion
	{ // se utiliza para guardar una nueva película o para 
	  // guardar los cambios de una edición.
		
		   // entradas correctas.
			if (esDetalle) SalvarNueva();
			else SalvarEditada();
			return true;
		
			
	         
		
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (esDetalle)this.setTitle("Añadir propietario");
			else this.setTitle("Editar propietario");
			{
				jContent = new JPanel();
				getContentPane().add(jContent);
				jContent.setBounds(7, 0, 10, 10);
				jContent.setName("Datos Propietario");
				{
					Panel = new JPanel();
					jContent.add(Panel);
					Panel.setLayout(null);
					Panel.setPreferredSize(new java.awt.Dimension(470, 288));
					{
						label1 = new JLabel();
						Panel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						label1.setText("NIF");
						label1.setBounds(36, 12, 23, 19);
					}
					{
						idTextField = new JTextField();
						Panel.add(idTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						idTextField.setText("ID");
						idTextField.setBounds(161, 10, 244, 23);
					}
					{
						jLabel2 = new JLabel();
						Panel.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel2.setText("Nombre");
						jLabel2.setBounds(36, 43, 42, 16);
					}
					{
						nombreTextField = new JTextField();
						Panel.add(nombreTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						nombreTextField.setText("nombre");
						nombreTextField.setBounds(161, 39, 244, 23);
					}
					{
						jLabel3 = new JLabel();
						Panel.add(jLabel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel3.setText("Dirección");
						jLabel3.setBounds(36, 71, 68, 16);
					}
					{
						jLabel1 = new JLabel();
						Panel.add(jLabel1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel1.setText("Población");
						jLabel1.setBounds(36, 98, 66, 21);
					}
					{
						DireccionTextField = new JTextField();
						Panel.add(DireccionTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						DireccionTextField.setText("Direccion");
						DireccionTextField.setBounds(161, 68, 244, 23);
					}
					{
						PoblacionTextField = new JTextField();
						Panel.add(PoblacionTextField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						PoblacionTextField.setText("Poblacion");
						PoblacionTextField.setBounds(161, 97, 244, 23);
					}
					{
						jLabel4 = new JLabel();
						Panel.add(jLabel4);
						jLabel4.setText("Teléfono");
						jLabel4.setBounds(36, 129, 42, 14);
					}
					{
						TelefonoTextField = new JTextField();
						Panel.add(TelefonoTextField);
						TelefonoTextField.setText("Teléfono");
						TelefonoTextField.setBounds(161, 126, 244, 21);
					}
					{
						jLabel5 = new JLabel();
						Panel.add(jLabel5);
						jLabel5.setText("Observaciones");
						jLabel5.setBounds(36, 155, 71, 14);
					}
					{
						jLabel6 = new JLabel();
						Panel.add(jLabel6);
						jLabel6.setText("Fecha Alta");
						jLabel6.setBounds(36, 182, 51, 14);
					}
					{
						jLabel7 = new JLabel();
						Panel.add(jLabel7);
						jLabel7.setText("Entidad");
						jLabel7.setBounds(36, 209, 36, 14);
					}
					{
						jLabel8 = new JLabel();
						Panel.add(jLabel8);
						jLabel8.setText("Número de cuenta");
						jLabel8.setBounds(36, 236, 88, 14);
					}
					{
						ObservacionesTextField = new JTextField();
						Panel.add(ObservacionesTextField);
						ObservacionesTextField.setText("Observaciones");
						ObservacionesTextField.setBounds(161, 152, 244, 21);
					}
					{
						FechaAltaTextField = new JTextField();
						Panel.add(FechaAltaTextField);
						FechaAltaTextField.setText("Fecha de alta");
						FechaAltaTextField.setBounds(161, 179, 244, 21);
					}
					{
						EntidadTextField1 = new JTextField();
						Panel.add(EntidadTextField1);
						EntidadTextField1.setText("Entidad");
						EntidadTextField1.setBounds(161, 206, 244, 21);
					}
					{
						NumeroCuentaTextField = new JTextField();
						Panel.add(NumeroCuentaTextField);
						NumeroCuentaTextField.setText("Número de cuenta");
						NumeroCuentaTextField.setBounds(161, 233, 244, 21);
					}
				}
				if(!esDetalle){
					idTextField.setText(String.valueOf(propietario.getNif()));
					idTextField.setEditable(false);
					FechaAltaTextField.setText(propietario.getFechaAlta());
					NumeroCuentaTextField.setText(propietario.getNumeroCuenta());
					nombreTextField.setText(propietario.getNombre());
					PoblacionTextField.setText(propietario.getPoblacion());
					ObservacionesTextField.setText(propietario.getObservaciones());
					TelefonoTextField.setText(propietario.getTelefono());
					
					DireccionTextField.setText(propietario.getDireccion());
					EntidadTextField1.setText(propietario.getEntidad());

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
									null, "Formato ID no válido.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null, 
									options,
									options[0]);
									
							
								}
								if(guarda){
									if(		idTextField.getText().length()>0 &&
											DireccionTextField.getText().length()>0 &&
											EntidadTextField1.getText().length()>0 &&
											FechaAltaTextField.getText().length()>0 &&
											nombreTextField.getText().length()>0 &&
											NumeroCuentaTextField.getText().length()>0 &&
											ObservacionesTextField.getText().length()>0 &&
											PoblacionTextField.getText().length()>0 &&
											TelefonoTextField.getText().length()>0){
										//AÑADIR A BD
											try {
												if (guardarCambios())
													dispose();
											} catch (PropietarioYaExiste e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
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
