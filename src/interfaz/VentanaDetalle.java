package interfaz;
/*package interfazGrafica;

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

import negocio.ControladorInmueble;

import hibernate.Comunidad;
import hibernate.Inmueble;
import hibernate.Propietario;
import dominio.ModeloTablaInmueble;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;
import excepciones.InmuebleYaExiste;

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
/*
public class VentanaDetalle extends javax.swing.JFrame {
	private JPanel Panel;
	private JLabel label1;
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
	Inmueble inmueble;
	boolean esDetalle;
	private AbstractButton porcentajeTextField;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	/*ControladorInmueble controladorInmueble = ControladorInmueble.getControladorInmueble();  //  @jve:decl-index=0:

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaDetalle inst = new VentanaDetalle();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaDetalle() {
		super();
		initGUI();
	}

	public VentanaDetalle(TableModel modelo){
		super();
		this.modelo = (ModeloTablaInmueble) modelo;
		esDetalle = true;
		initGUI();		

	}

	public VentanaDetalle(Inmueble i){
		super();
		inmueble = i;
		//this.modelo = (ModeloTablaInmueble) modelo;
		initGUI();
		this.botonGuardar.setEnabled(false);
		idTextField.setText(String.valueOf(i.getId()));
		PisoTextField.setText(i.getPiso());
		PuertaTextField.setText(i.getPuerta());
		escaleraTextField.setText(i.getEscalera());

		//	porcentajeTextField.setText(String.valueOf(i.getPorcentaje()));
	}

	// Salva una nueva película
	private void SalvarNueva() {

		// instancia a insertar.....

		Inmueble in = new Inmueble(Integer.parseInt(idTextField.getText()), new Comunidad(103), new Propietario("33333333C"), escaleraTextField.getText(), PisoTextField.getText(), PuertaTextField.getText(), 77.0, null, null);
		try {
			controladorInmueble.NuevoInmueble(in);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la capa de acceso a datos", "No se puede crear la Película", JOptionPane.ERROR_MESSAGE);
		}  catch (DAOConfiguracionExcepcion e2) {
			JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Cambiar datos de una película.
	private void SalvarEditada() {
		Inmueble in = new Inmueble(Integer.parseInt(idTextField.getText()), new Comunidad(104), new Propietario("44444444D"), escaleraTextField.getText(), PisoTextField.getText(), PuertaTextField.getText(), 77.0, null, null);

		try {
			controladorInmueble.ActualizarInmueble(in);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la capa de acceso a datos", "No se puede actualiar la Película", JOptionPane.ERROR_MESSAGE);
		}  catch (DAOConfiguracionExcepcion e2) {
			JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
			// System.exit(0);
		}
	}

	// Se utiliza para guardar una nueva película o para 
	// guardar los cambios de una edición.
	private boolean guardarCambios() {

		// Entradas correctas.
		if (esDetalle)
			SalvarNueva();
		else
			SalvarEditada();
		return true;
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
						label1.setPreferredSize(new java.awt.Dimension(23, 19));
						label1.setBounds(50, 26, 23, 19);
					}
					{
						idTextField = new JTextField();
						Panel.add(idTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						idTextField.setText("ID");
						idTextField.setBounds(161, 24, 244, 23);
					}
					{
						jLabel2 = new JLabel();
						Panel.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel2.setText("Escalera");
						jLabel2.setBounds(40, 100, 42, 16);
					}
					{
						escaleraTextField = new JTextField();
						Panel.add(escaleraTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						escaleraTextField.setText("Escalera");
						escaleraTextField.setBounds(161, 97, 244, 23);
					}
					{
						jLabel3 = new JLabel();
						Panel.add(jLabel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel3.setText("Piso");
						jLabel3.setBounds(50, 172, 22, 16);
					}
					{
						jLabel1 = new JLabel();
						Panel.add(jLabel1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabel1.setText("Puerta");
						jLabel1.setBounds(44, 244, 34, 16);
					}
					{
						PisoTextField = new JTextField();
						Panel.add(PisoTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						PisoTextField.setText("Piso");
						PisoTextField.setBounds(161, 169, 244, 23);
					}
					{
						PuertaTextField = new JTextField();
						Panel.add(PuertaTextField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						PuertaTextField.setText("Puerta");
						PuertaTextField.setBounds(161, 241, 244, 23);
					}
				}
				{
					PanelSur = new JPanel();
					jContent.add(PanelSur);
					{
						botonGuardar = new JButton();
						PanelSur.add(botonGuardar);
						botonGuardar.setText("Guardar");
						botonGuardar.setPreferredSize(new java.awt.Dimension(86, 23));
						botonGuardar.setEnabled(esDetalle);
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
											null, "ID no válida.","Error",JOptionPane.OK_OPTION,
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
										if (guardarCambios())
											dispose();

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
 */