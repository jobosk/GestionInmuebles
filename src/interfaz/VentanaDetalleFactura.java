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

import javax.swing.AbstractButton;
import javax.swing.JButton;
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
public class VentanaDetalleFactura extends javax.swing.JFrame {
	private JPanel Panel;
	private JLabel label1;
	private JPanel jContent;
	private JButton botonGuardar;
	private JButton botonVolver;
	private JPanel PanelSur;
	private JTextField idTextField;
	private JLabel jLabel2;
	ModeloTablaFactura modelo;

	private JFormattedTextField fechaTextField1;
	Comunidad com;
	Factura factura;
	private JDateChooser jFechaPeticion;
	boolean esDetalle;
	/**
	 * Auto-generated main method to display this JFrame
	 */

	private int sugiereNumeroFactura() throws DAOExcepcion{
		for(int i = 1;i<1000000;i++){
			if(controladorFactura.EncontrarFacturaPorId(i)==null) return i;
			}
		return -1;
		}
	ControlFactura controladorFactura = ControlFactura.getControladorFactura();  //  @jve:decl-index=0:
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaDetalleFactura inst = new VentanaDetalleFactura();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaDetalleFactura() {
		super();
		initGUI();
	}
	public VentanaDetalleFactura(ModeloTablaFactura modelo,Comunidad i){
		super();
		com = i;
		this.modelo = (ModeloTablaFactura) modelo;	
		
		esDetalle = true;
		initGUI();
	}
	


	public VentanaDetalleFactura(Factura f,Comunidad i){
		super();
		com = i;
		factura =f;
		//this.modelo = (ModeloTablaFactura) modelo;	
		
		esDetalle = false;
		initGUI();
	
	}
	
	
	private void SalvarNueva() throws FacturaYaExiste, DAOExcepcion, NumberFormatException, ComunidadNoExiste, PropietarioNoExiste
	{
		// instancia a insertar.....
			
			
	
		Factura in = new Factura(); 
				
				in.setNumFactura(Integer.parseInt(idTextField.getText()));	
				in.setFechaFactura(jFechaPeticion.getDate().toString()); 				
				in.setComunidad(com);
				in.setLineaFacturas(null);
				in.setNotaInformativa(null);
				
				try {
			controladorFactura.NuevaFactura(in);
			modelo.addFactura(in);
		} catch (DAOConfiguracionExcepcion e2)
	    {
		JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		}
		
        
        
		
	}
	
	private void SalvarEditada() throws NumberFormatException, DAOExcepcion
	{ // Cambiar datos de un concepto.
		

		
		
	/*	Factura in = new Factura(); 
		in.setComunidad(factura.getComunidad());
		in.setLineaFacturas(factura.getLineaFacturas());
		in.setNotaInformativa(factura.getNotaInformativa());*/
		factura.setFechaFactura(jFechaPeticion.getDate().toString()); 
	
	      
		try {
			controladorFactura.ActualizarFactura(factura);
		
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
	
	private boolean guardarCambios() throws FacturaYaExiste, DAOExcepcion, NumberFormatException, ComunidadNoExiste, PropietarioNoExiste
	{ 
		   // entradas correctas.
			if (esDetalle) SalvarNueva();
			else SalvarEditada();
			return true;	
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (esDetalle)this.setTitle("Añadir factura");
			else this.setTitle("Editar factura");
			{
				jContent = new JPanel();
				getContentPane().add(jContent);
				jContent.setBounds(7, 0, 10, 10);
				jContent.setName("Datos Factura");
				{
					Panel = new JPanel();
					jContent.add(Panel);
					Panel.setLayout(null);
					Panel.setPreferredSize(new java.awt.Dimension(470, 288));
					{
						label1 = new JLabel();
						Panel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						label1.setText("Número");
						label1.setBounds(36, 26, 113, 19);
					}
					{
						idTextField = new JTextField();
						Panel.add(idTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						idTextField.setText(Integer.toString(sugiereNumeroFactura()));
						idTextField.setBounds(161, 24, 244, 23);
					}
					{
						jLabel2 = new JLabel();
						Panel.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel2.setText("Fecha");
						jLabel2.setBounds(36, 101, 42, 16);
					}
					{
						{
						Calendar calendar = Calendar.getInstance(); 
						calendar.set(Calendar.HOUR, 0);        
						Date date1 = calendar.getTime();         
						jFechaPeticion = new JDateChooser("dd/MM/yyyy","##-##-####",'-');
						jFechaPeticion.setDate(date1);
						Panel.add(jFechaPeticion, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jFechaPeticion.setBounds(161, 94, 244, 23);
						}
					}
				}
				if(!esDetalle){
				idTextField.setText(String.valueOf(factura.getNumFactura()));
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
									if(idTextField.getText().length()>0 )
											{
										//AÑADIR A BD
											try {
												if (guardarCambios()){
													dispose();}
											} catch (FacturaYaExiste e) {
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
