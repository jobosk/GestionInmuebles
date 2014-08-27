package interfaz;

import hibernate.Comunidad;
import hibernate.Inmueble;
import hibernate.Propietario;

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
public class VentanaDetalleInmueble extends javax.swing.JFrame {
	private JPanel Panel;
	private JLabel label1;
	private JLabel jLabel5;
	private JLabel Comunidad;
	private JTextField ComunidadTextField;
	private JTextField PropietarioTextField;
	private JLabel jLabel4;
	private JTextField PorcentajeTextField;
	private JPanel jContent;
	private JTextField PisoTextField;
	private JButton botonGuardar;
	private JButton botonVolver;
	private JPanel PanelSur;
	private JTextField PuertaTextField;
	private JTextField escaleraTextField;
	private JTextField idTextField;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JLabel jLabel2;
	ModeloTablaInmueble modelo;
	ModeloTablaComunidad modeloComunidad;
	ModeloTablaPropietario modeloPropietario;
	Inmueble inmueble;
	boolean esDetalle;
	/**
	 * Auto-generated main method to display this JFrame
	 */
	
	ControlInmueble controladorInmueble = ControlInmueble.getControladorInmueble();  //  @jve:decl-index=0:
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaDetalleInmueble inst = new VentanaDetalleInmueble();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaDetalleInmueble() {
		super();
		initGUI();
	}
	public VentanaDetalleInmueble(TableModel modelo, TableModel modeloCom, TableModel modeloProp){
		super();
		this.modelo = (ModeloTablaInmueble) modelo;
		this.modeloPropietario = (ModeloTablaPropietario) modeloProp;
		this.modeloComunidad = (ModeloTablaComunidad) modeloCom;
		esDetalle = true;
		initGUI();		

	}


	public VentanaDetalleInmueble(Inmueble i){
		super();
		inmueble = i;
		//this.modelo = (ModeloTablaInmueble) modelo;	
		
		esDetalle = false;
		initGUI();
	
	}
	

	private int sugiereNumeroInmueble() throws DAOExcepcion{
		for(int i = 1;i<1000000;i++){
			if(controladorInmueble.EncontrarInmueblePorId(i)==null) return i;
			}
		return -1;
		}
	
	
	private void SalvarNueva() throws InmuebleYaExiste, DAOExcepcion, NumberFormatException, ComunidadNoExiste, PropietarioNoExiste
	{ // salva una nueva película
		// instancia a insertar.....
			
		ControlComunidad comunidades = new ControlComunidad();
		ControlPropietario propietarios = new ControlPropietario();
		
		Inmueble in = new Inmueble(); 
				in.setId(Integer.parseInt(idTextField.getText()));
				in.setComunidad(comunidades.EncontrarComunidadPorId(Integer.parseInt(ComunidadTextField.getText())));
				in.setPropietario(propietarios.EncontrarPropietarioPorId(Integer.parseInt(PropietarioTextField.getText())));
				in.setEscalera(escaleraTextField.getText());
				in.setPiso(PisoTextField.getText()); 
				in.setPuerta(PuertaTextField.getText());
				in.setPorcentaje(Double.parseDouble(PorcentajeTextField.getText()));
				in.setReciboInmuebles(null);
				try {
			controladorInmueble.NuevoInmueble(in);
			modelo.addInmueble(in);
		} catch (DAOConfiguracionExcepcion e2)
	    {
		JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		}
		
        
        
		
	}
	
	private void SalvarEditada() throws NumberFormatException, DAOExcepcion
	{ // Cambiar datos de un inmueble.
		

		
		ControlComunidad comunidades = new ControlComunidad();
		ControlPropietario propietarios = new ControlPropietario();
		
		Inmueble in = new Inmueble(); 
				in.setId(Integer.parseInt(idTextField.getText()));
				in.setComunidad(comunidades.EncontrarComunidadPorId(Integer.parseInt(ComunidadTextField.getText())));
				in.setPropietario(propietarios.EncontrarPropietarioPorId(Integer.parseInt(PropietarioTextField.getText())));
				in.setEscalera(escaleraTextField.getText());
				in.setPiso(PisoTextField.getText()); 
				in.setPuerta(PuertaTextField.getText());
				in.setPorcentaje(Double.parseDouble(PorcentajeTextField.getText()));
				in.setReciboInmuebles(null);
	      
		try {
			controladorInmueble.ActualizarInmueble(in);
		
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
	
	private boolean guardarCambios() throws InmuebleYaExiste, DAOExcepcion, NumberFormatException, ComunidadNoExiste, PropietarioNoExiste
	{ 
		   // entradas correctas.
			if (esDetalle) SalvarNueva();
			else SalvarEditada();
			return true;	
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (esDetalle)this.setTitle("Añadir inmueble");
			else this.setTitle("Editar inmueble");
			{
				jContent = new JPanel();
				getContentPane().add(jContent);
				jContent.setBounds(7, 0, 10, 10);
				jContent.setName("Datos Inmueble");
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
						idTextField.setText(Integer.toString(sugiereNumeroInmueble()));
						idTextField.setBounds(161, 24, 244, 23);
					}
					{
						jLabel2 = new JLabel();
						Panel.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel2.setText("Escalera");
						jLabel2.setBounds(36, 62, 42, 16);
					}
					{
						escaleraTextField = new JTextField();
						Panel.add(escaleraTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						escaleraTextField.setText("Escalera");
						escaleraTextField.setBounds(161, 59, 244, 23);
					}
					{
						jLabel3 = new JLabel();
						Panel.add(jLabel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel3.setText("Piso");
						jLabel3.setBounds(36, 102, 22, 16);
					}
					{
						jLabel1 = new JLabel();
						Panel.add(jLabel1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel1.setText("Puerta");
						jLabel1.setBounds(36, 137, 34, 16);
					}
					{
						PisoTextField = new JTextField();
						Panel.add(PisoTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						PisoTextField.setText("Piso");
						PisoTextField.setBounds(161, 99, 244, 23);
					}
					{
						PuertaTextField = new JTextField();
						Panel.add(PuertaTextField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						PuertaTextField.setText("Puerta");
						PuertaTextField.setBounds(161, 134, 244, 23);
					}
					{
						jLabel4 = new JLabel();
						Panel.add(jLabel4);
						jLabel4.setText("Porcentaje participación");
						jLabel4.setBounds(28, 172, 115, 14);
					}
					{
						PorcentajeTextField = new JTextField();
						Panel.add(PorcentajeTextField);
						PorcentajeTextField.setText("Porcentaje participación");
						PorcentajeTextField.setBounds(161, 169, 244, 21);
					}
					{
						PropietarioTextField = new JTextField();
						Panel.add(PropietarioTextField);
						PropietarioTextField.setText("NIF propietario");
						PropietarioTextField.setBounds(161, 202, 244, 21);
					}
					{
						ComunidadTextField = new JTextField();
						Panel.add(ComunidadTextField);
						ComunidadTextField.setText("Comunidad");
						ComunidadTextField.setBounds(161, 235, 244, 21);
					}
					{
						jLabel5 = new JLabel();
						Panel.add(jLabel5);
						jLabel5.setText("NIF propietario");
						jLabel5.setBounds(28, 205, 72, 14);
					}
					{
						Comunidad = new JLabel();
						Panel.add(Comunidad);
						Comunidad.setText("Comunidad");
						Comunidad.setBounds(28, 238, 115, 14);
					}
				}
				if(!esDetalle){
				idTextField.setText(String.valueOf(inmueble.getId()));
				idTextField.setEditable(false);
				PisoTextField.setText(inmueble.getPiso());
				PuertaTextField.setText(inmueble.getPuerta());
				escaleraTextField.setText(inmueble.getEscalera());
				PorcentajeTextField.setText(String.valueOf(inmueble.getPorcentaje()));
				ComunidadTextField.setText(Integer.toString(inmueble.getComunidad().getIdComunidad()));
				PropietarioTextField.setText(String.valueOf(inmueble.getPropietario().getNif()));
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
								
								try{															
									id =  Integer.parseInt(ComunidadTextField.getText());
									}
								catch (Exception e) {
									guarda=false;
									Object[] options = {"Aceptar"}; 
									int n = JOptionPane.showOptionDialog(
									null, "Formato de comunidad no válido.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null, 
									options,
									options[0]);						
								}
								
								try{ 
									float por;
									por = Float.parseFloat(PorcentajeTextField.getText());
									if (por >100) throw ( new ExcepcionIntervalo());
									}
								catch (Exception e) {
									guarda=false;
									Object[] options = {"Aceptar"}; 
									int n = JOptionPane.showOptionDialog(
									null, "Formato del porcentaje de participación no válido.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null, 
									options,
									options[0]);						
								}
								
														
								try{															
									id =  Integer.parseInt(PropietarioTextField.getText());
									}
								catch (Exception e) {
									guarda=false;
									Object[] options = {"Aceptar"}; 
									int n = JOptionPane.showOptionDialog(
									null, "Formato del propietario no válido.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null, 
									options,
									options[0]);						
								}
								
								if(guarda){
									if(escaleraTextField.getText().length()>0 &&
											PisoTextField.getText().length()>0 &&
											PuertaTextField.getText().length()>0){
										//AÑADIR A BD
											try {
												if (guardarCambios()){
													dispose();}
											} catch (InmuebleYaExiste e) {
												// TODO Auto-generated catch block
												Object[] options = {"Aceptar"}; 
												int n = JOptionPane.showOptionDialog(
												null, "El inmueble ya existe.","Error",JOptionPane.OK_OPTION,
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
