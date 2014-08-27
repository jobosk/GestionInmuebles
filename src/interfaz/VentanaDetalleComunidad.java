package interfaz;

import hibernate.Comunidad;

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

import control.ControlComunidad;
import control.ControlPropietario;

///import negocio.ControladorComunidad;

import tablas.ModeloTablaComunidad;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;
import excepciones.ComunidadYaExiste;


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
public class VentanaDetalleComunidad extends javax.swing.JFrame {
	private JPanel Panel;
	private JLabel label1;
	private JPanel jContent;
	private JTextField EstadoTextField;
	private JButton botonGuardar;
	private JButton botonVolver;
	private JPanel PanelSur;
	private JTextField Max_RecibosTextField;
	private JTextField calleTextField;
	private JTextField idTextField;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JLabel jLabel2;
	ModeloTablaComunidad modelo;
	Comunidad comunidad;
	boolean esDetalle;
	ControlComunidad controladorComunidad = ControlComunidad.getControladorComunidad();  //  @jve:decl-index=0:
	
	private AbstractButton porcentajeTextField;
	/**
	 * Auto-generated main method to display this JFrame
	 */
	
	//ControladorComunidad controladorComunidad = ControladorComunidad.getControladorComunidad();  //  @jve:decl-index=0:
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaDetalleComunidad inst = new VentanaDetalleComunidad();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaDetalleComunidad() {
		super();
		initGUI();
	}
	public VentanaDetalleComunidad(TableModel modelo){
		super();
		this.modelo = (ModeloTablaComunidad) modelo;
		esDetalle = true;
		initGUI();		

	}


	public VentanaDetalleComunidad(Comunidad i){
		super();
	
		comunidad = i;
		//this.modelo = (ModeloTablaInmueble) modelo;	
		
		esDetalle = false;
		initGUI();

	}

	private int sugiereNumeroComunidad() throws DAOExcepcion{
		for(int i = 1;i<1000000;i++){
			if(controladorComunidad.EncontrarComunidadPorId(i)==null) return i;
			}
		return -1;
		}
	
	private void SalvarNueva() throws ComunidadYaExiste, DAOExcepcion
	{ 
		Comunidad in = new Comunidad();
				in.setIdComunidad(Integer.parseInt(idTextField.getText()));
				in.setCalle(calleTextField.getText());
				in.setEstado(EstadoTextField.getText());
				in.setFacturas(null);
				in.setMaxRecibosPendientes(Integer.parseInt(Max_RecibosTextField.getText()));
				in.setNotaInformativas(null);
				in.setInmuebles(null);
		
		try {
			controladorComunidad.NuevaComunidad(in);
			modelo.addComunidad(in);
		} catch (DAOConfiguracionExcepcion e2)
	    {
		JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		}
		
        
        
		
	}
	
	private void SalvarEditada() throws DAOExcepcion
	{ // Cambiar datos de una película.
		

		Comunidad in = new Comunidad();
		in.setIdComunidad(Integer.parseInt(idTextField.getText()));
		in.setCalle(calleTextField.getText());
		in.setEstado(EstadoTextField.getText());
		//in.setFacturas(null);
		in.setMaxRecibosPendientes(Integer.parseInt(Max_RecibosTextField.getText()));
		//in.setNotaInformativas(null);
		//in.setInmuebles(null);
	      
		try {
		controladorComunidad.ActualizarComunidad(in);
		} catch (DAOConfiguracionExcepcion e2)
	    {
		JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		// System.exit(0);
	    }
	
	}
	
	private boolean guardarCambios() throws ComunidadYaExiste, DAOExcepcion
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
			if (esDetalle)this.setTitle("Añadir comunidad");
			else this.setTitle("Editar comunidad");
			
			{
				jContent = new JPanel();
				getContentPane().add(jContent);
				jContent.setBounds(7, 0, 10, 10);
				jContent.setName("Datos Comunidad");
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
						idTextField.setText(Integer.toString(sugiereNumeroComunidad()));
						idTextField.setBounds(161, 24, 244, 23);
					}
					{
						jLabel2 = new JLabel();
						Panel.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel2.setText("calle");
						jLabel2.setBounds(36, 62, 42, 16);
					}
					{
						calleTextField = new JTextField();
						Panel.add(calleTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						calleTextField.setText("calle");
						calleTextField.setBounds(161, 59, 244, 23);
					}
					{
						jLabel3 = new JLabel();
						Panel.add(jLabel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel3.setText("Estado");
						jLabel3.setBounds(36, 102, 68, 16);
					}
					{
						jLabel1 = new JLabel();
						Panel.add(jLabel1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel1.setText("# máximo de recibos");
						jLabel1.setBounds(36, 135, 125, 21);
					}
					{
						EstadoTextField = new JTextField();
						Panel.add(EstadoTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						EstadoTextField.setText("Estado");
						EstadoTextField.setBounds(161, 99, 244, 23);
					}
					{
						Max_RecibosTextField = new JTextField();
						Panel.add(Max_RecibosTextField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						Max_RecibosTextField.setText("Máx_Recibos");
						Max_RecibosTextField.setBounds(161, 134, 244, 23);
					}
				}
				if(!esDetalle){
					idTextField.setText(String.valueOf(comunidad.getIdComunidad()));
					idTextField.setEditable(false);
					calleTextField.setText(comunidad.getCalle());
					EstadoTextField.setText(comunidad.getEstado());
					Max_RecibosTextField.setText(String.valueOf(comunidad.getMaxRecibosPendientes()));
					this.setName("Editar Comunidad");
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
								try{															
									id =  Integer.parseInt(Max_RecibosTextField.getText());
									}
								catch (Exception e) {
									guarda=false;
									Object[] options = {"Aceptar"}; 
									int n = JOptionPane.showOptionDialog(
									null, "Formato # máximo de recibos pendientes no válido.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null, 
									options,
									options[0]);
								}
								if(guarda){
									if(calleTextField.getText().length()>0 &&
											EstadoTextField.getText().length()>0 &&
											Max_RecibosTextField.getText().length()>0){
										//AÑADIR A BD
											try {
												if (guardarCambios())
													dispose();
											} catch (ComunidadYaExiste e) {
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
