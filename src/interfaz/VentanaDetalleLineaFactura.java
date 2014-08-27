package interfaz;

import hibernate.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import control.*;
import excepciones.ComunidadNoExiste;
import excepciones.FacturaYaExiste;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;
import excepciones.ExcepcionIntervalo;
import excepciones.InmuebleYaExiste;
import excepciones.LineaFacturaYaExiste;
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
public class VentanaDetalleLineaFactura extends javax.swing.JFrame {
	private JPanel Panel;
	private JLabel label1;
	private JLabel conceptojLabel1;
	private JComboBox conceptojComboBox1;
	private JLabel observacionjLabel1;
	private JLabel informacionjLabel1;
	private JTextField observacionjTextField1;
	private JTextField importeTextField1;
	private JPanel jContent;
	private JButton botonGuardar;
	private JButton botonVolver;
	private JPanel PanelSur;
	private JTextField idTextField;
	private JLabel jLabel2;
	ModeloTablaLineaFactura modelo;

	private JFormattedTextField fechaTextField1;
	Factura com;
	LineaFactura lineaFactura;
	boolean esDetalle;
	/**
	 * Auto-generated main method to display this JFrame
	 */

	ControlLinea controladorLineaFactura = ControlLinea.getControladorLinea();  //  @jve:decl-index=0:

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaDetalleLineaFactura inst = new VentanaDetalleLineaFactura();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaDetalleLineaFactura() {
		super();
		initGUI();
	}
	public VentanaDetalleLineaFactura(ModeloTablaLineaFactura modelo,Factura i){
		super();
		com = i;
		this.modelo = (ModeloTablaLineaFactura) modelo;	

		esDetalle = true;
		initGUI();
	}



	public VentanaDetalleLineaFactura(Factura f,LineaFactura linea){
		super();
		com = f;
		lineaFactura=linea;
		//this.modelo = (ModeloTablaLineaFactura) modelo;	

		esDetalle = false;
		initGUI();

	}

	private int sugiereNumeroLineaFactura() throws DAOExcepcion{
		for(int i = 1;i<1000000;i++){
			if(controladorLineaFactura.EncontrarLineaPorId(i)==null)
				return i;
		}
		return -1;
	}

	private void SalvarNueva() throws LineaFacturaYaExiste, DAOExcepcion, NumberFormatException
	{
		// instancia a insertar.....



		LineaFactura in = new LineaFactura(); 

		in.setIdLinea(Integer.parseInt(idTextField.getText()));	
		in.setObservacion(observacionjTextField1.getText()); 				
		in.setFactura(com);
		in.setImporteLinea(Double.parseDouble(importeTextField1.getText()));
		in.setConcepto((Concepto) conceptojComboBox1.getSelectedItem());

		try {
			controladorLineaFactura.NuevaLinea(in);
			modelo.addLinea(in);
		} catch (DAOConfiguracionExcepcion e2)
		{
			JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		}




	}

	private void SalvarEditada() throws NumberFormatException, DAOExcepcion
	{ // Cambiar datos de un concepto.

		lineaFactura.setObservacion(observacionjTextField1.getText()); 				
		lineaFactura.setFactura(com);
		lineaFactura.setImporteLinea(Double.parseDouble(importeTextField1.getText()));
		lineaFactura.setConcepto((Concepto) conceptojComboBox1.getSelectedItem());

		try {
			controladorLineaFactura.ActualizarLinea(lineaFactura);

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

	private boolean guardarCambios() throws LineaFacturaYaExiste, DAOExcepcion, NumberFormatException
	{ 
		// entradas correctas.
		if (esDetalle) SalvarNueva();
		else SalvarEditada();
		return true;	
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (esDetalle)this.setTitle("Añadir linea de factura");
			else this.setTitle("Editar línea de factura");
			{
				jContent = new JPanel();
				getContentPane().add(jContent);
				jContent.setBounds(7, 0, 10, 10);
				jContent.setName("Datos LineaFactura");
				{
					Panel = new JPanel();
					jContent.add(Panel);
					Panel.setLayout(null);
					Panel.setPreferredSize(new java.awt.Dimension(470, 288));
					{
						label1 = new JLabel();
						Panel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						label1.setText("Número");
						label1.setBounds(36, 84, 113, 19);
					}
					{
						idTextField = new JTextField();
						Panel.add(idTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						idTextField.setText(Integer.toString(sugiereNumeroLineaFactura()));
						idTextField.setBounds(161, 82, 244, 23);
					}
					{
						jLabel2 = new JLabel();
						Panel.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel2.setText("Importe");
						jLabel2.setBounds(36, 122, 83, 16);
					}
					{
						importeTextField1 = new JTextField();
						Panel.add(importeTextField1);
						importeTextField1.setText("Importe");
						importeTextField1.setBounds(161, 117, 244, 21);
					}
					{
						observacionjTextField1 = new JTextField();
						Panel.add(observacionjTextField1);
						observacionjTextField1.setText("Observación");
						observacionjTextField1.setBounds(161, 150, 244, 21);
					}
					{
						informacionjLabel1 = new JLabel();
						Panel.add(informacionjLabel1);
						informacionjLabel1.setText("Factura: " +com.getNumFactura() + ".  Comunidad: "+com.getComunidad().getIdComunidad()+".");
						informacionjLabel1.setBounds(36, 20, 369, 14);
					}
					{
						observacionjLabel1 = new JLabel();
						Panel.add(observacionjLabel1);
						observacionjLabel1.setText("Observación");
						observacionjLabel1.setBounds(36, 153, 60, 14);
					}
					{
						Vector v = new Vector();
						ControlConcepto controladorConcepto = ControlConcepto.getControladorConcepto();
						for(int i=0;i<controladorConcepto.GetNumConceptos();i++){
							v.add(controladorConcepto.GetActual(i));	

						}


						ComboBoxModel conceptojComboBox1Model = 
							new DefaultComboBoxModel(v);
						conceptojComboBox1 = new JComboBox();

						Panel.add(conceptojComboBox1);
						conceptojComboBox1.setModel(conceptojComboBox1Model);
						conceptojComboBox1.setBounds(161, 183, 244, 21);
					}
					{
						conceptojLabel1 = new JLabel();
						Panel.add(conceptojLabel1);
						conceptojLabel1.setText("Concepto");
						conceptojLabel1.setBounds(36, 186, 46, 14);
					}
					{
					}
				}
				if(!esDetalle){
					idTextField.setText(String.valueOf(lineaFactura.getIdLinea()));
					idTextField.setEditable(false);

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
								int id = -1; double importe = -1.0;
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

								try{importe = Double.parseDouble(importeTextField1.getText());

								}
								catch (Exception e) {
									guarda=false;
									Object[] options = {"Aceptar"}; 
									int n = JOptionPane.showOptionDialog(
									null, "Formato del importe no válido.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null, 
									options,
									options[0]);
								}
								if(guarda){
									if(idTextField.getText().length()>0 )
									{
										//AÑADIR A BD
										try {
											if (guardarCambios()){
												dispose();}
										} catch (LineaFacturaYaExiste e) {
											// TODO Auto-generated catch block
											Object[] options = {"Aceptar"}; 
											int n = JOptionPane.showOptionDialog(
													null, "L ya existe.","Error",JOptionPane.OK_OPTION,
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
