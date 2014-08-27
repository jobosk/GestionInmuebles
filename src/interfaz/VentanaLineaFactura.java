package interfaz;


import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import control.*;

import gestion.GestionComunidades;
import gestion.GestionConceptos;
import gestion.GestionFacturas;
import gestion.GestionInmuebles;
import gestion.GestionLineas;
import gestion.GestionPropietarios;
import hibernate.Comunidad;
import hibernate.Concepto;
import hibernate.Factura;
import hibernate.Inmueble;
import hibernate.LineaFactura;
import hibernate.Propietario;

import reports.Informe;
import tablas.*;

import interfaz.VentanaDetalleFactura;
import excepciones.ComunidadYaExiste;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;
import excepciones.InmuebleNoExiste;

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
public class VentanaLineaFactura extends javax.swing.JFrame {

	//private JMenuItem deleteMenuItem;
	//private JSeparator jSeparator1;
	//private JMenuItem pasteMenuItem;
	private JMenuItem fuenteMenuItem;
	private JMenuItem fondoMenuItem;
	private JMenu jMenu4;
	private JMenuItem salirMenuItem;
	private JSeparator jSeparator2;
	private JFrame jFrame = null;

	private JToolBar BarraHerramientas;
	private JPanel PanelEste;
	private JMenuItem guardarMenuItem;
	private JMenuItem cargarFileMenuItem;

	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	private JScrollPane Modelo;
	private JLabel Comunidad;
	private JButton controlLineas;
	private JButton botonActualizar;
	private JTabbedPane jTabbedPane2;
	private JTabbedPane jTabbedPane1;
	private JButton botonDetalle;
	private JColorChooser ColorFondo;

	private JLabel BarraEstado;
	private JDateChooser jFechaPeticion;
	private JButton botonBorrar;
	private JButton botonEditar;
	private JTable tablaLineaFacturas;
	private JPanel panelSur;
	private JButton botonAñadir;
	String fichero = null;
	int selectedIndex;
	private ImageIcon error =new ImageIcon(getClass().getClassLoader().getResource("interfaz/icoError.gif"));
	
	boolean cambios = false;
	Informe informe = new Informe();

	private ModeloTablaLineaFactura modeloLineaFacturas = new ModeloTablaLineaFactura(new GestionLineas());
	private Factura c;
	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaLineaFactura inst = new VentanaLineaFactura();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaLineaFactura() {
		super();
		initGUI();
	}
	public VentanaLineaFactura(Factura com) {
		super();
		c=com;
		initGUI();
	}



	private void initGUI() {
		try 
		{
			this.setTitle("Gestión Inmuebles");
			{
				BarraHerramientas = new JToolBar();
				getContentPane().add(BarraHerramientas, BorderLayout.NORTH);
				BarraHerramientas.setPreferredSize(new java.awt.Dimension(588, 30));
				{
					Comunidad = new JLabel();
					BarraHerramientas.add(Comunidad);
					Comunidad.setText("Factura: "+c.getNumFactura()+".  "+"  Comunidad: " + c.getComunidad().getIdComunidad() +".               ");
					Comunidad.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
				}
				{
					botonActualizar = new JButton();
					BarraHerramientas.add(botonActualizar);
					botonActualizar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("interfaz/Actualizar.png")));
					botonActualizar.setPreferredSize(new java.awt.Dimension(31,32));
					botonActualizar.setToolTipText("Actualizar");
					botonActualizar.setSize(32, 32);
					botonActualizar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("botonGuardar.actionPerformed, event="+evt);
							//TODO add your code for botonGuardar.actionPerformed
							
							try {
								modeloLineaFacturas.actualizar(c);
							} catch (DAOExcepcion e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					});
				}
			}
			{
				panelSur = new JPanel();
				getContentPane().add(panelSur, BorderLayout.SOUTH);
				panelSur.setPreferredSize(new java.awt.Dimension(365, 31));
				{
					BarraEstado = new JLabel();
					panelSur.add(BarraEstado);
					BarraEstado.setPreferredSize(new java.awt.Dimension(333, 21));
				}
				{	Calendar calendar = Calendar.getInstance(); 
				calendar.set(Calendar.HOUR, 0);        
				Date date1 = calendar.getTime();         
				jFechaPeticion = new JDateChooser("dd/MM/yyyy","##-##-####",'-');
				jFechaPeticion.setDate(date1);
				panelSur.add(jFechaPeticion);
				jFechaPeticion.setPreferredSize(new java.awt.Dimension(126, 21));
				}
			}
			{
				PanelEste = new JPanel();
				getContentPane().add(PanelEste, BorderLayout.EAST);
				FlowLayout PanelEsteLayout = new FlowLayout();
				PanelEste.setLayout(PanelEsteLayout);
				PanelEste.setPreferredSize(new java.awt.Dimension(100, 85));
				PanelEste.setSize(100, 85);
				{
					botonAñadir = new JButton();
					PanelEste.add(botonAñadir);
					botonAñadir.setText("Añadir línea");
					botonAñadir.setPreferredSize(new java.awt.Dimension(100, 46));
					botonAñadir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {

							System.out.println("botonAñadir.actionPerformed, event="+evt);
							//TODO add your code for botonAñadir.actionPerformed



							VentanaDetalleLineaFactura vp=new VentanaDetalleLineaFactura(modeloLineaFacturas,c);
							vp.setVisible(true);
							cambios = true;
							BarraEstado.setText("LineaFactura añadida.");
							

						}
					});

				}
				{
					botonEditar = new JButton();
					PanelEste.add(botonEditar);
					botonEditar.setText("Editar línea");
					botonEditar.setSize(100, 50);
					botonEditar.setPreferredSize(new java.awt.Dimension(100, 46));
					botonEditar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("botonEditar.actionPerformed, event="+evt);



								
							if(tablaLineaFacturas.getSelectedRowCount()==1 ){
								System.out.println(selectedIndex);
								int id,num;
								cambios = false;
								num = tablaLineaFacturas.getSelectedRow();
								id =  (Integer) tablaLineaFacturas.getValueAt(num, 0);
								for(int i = 0;i<tablaLineaFacturas.getSize().getWidth();i++){
									if((Integer)tablaLineaFacturas.getValueAt(i, 0)==id){
										ControlLinea controladorLineaFactura = ControlLinea.getControladorLinea(); 
										LineaFactura inm;
										try {
											inm = controladorLineaFactura.EncontrarLineaPorId(id);
											VentanaDetalleLineaFactura v2=new VentanaDetalleLineaFactura(c,inm);
											v2.setVisible(true);
											cambios = true;
											break;
										}
									
										catch (DAOExcepcion e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}			
									}
									
								}
							}else {Object[] options = {"Aceptar"}; 
							int n = JOptionPane.showOptionDialog(
									null, "Por favor, selecciona una línea.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									error, 
									options,
									options[0]);
							}


						}
					});
					{

						modeloLineaFacturas.actualizar(c);


					}

				}
				{
					botonBorrar = new JButton();
					PanelEste.add(botonBorrar);
					botonBorrar.setText("Borrar línea");
					botonBorrar.setPreferredSize(new java.awt.Dimension(100, 46));
					botonBorrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("botonBorrar.actionPerformed, event="+evt);
							//TODO add your code for botonBorrar.actionPerformed
							//BuscaInmueble editar = new BuscaInmueble(jFrame);
							//editar.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
							//editar.setVisible(true);

							//REVISAR
							if(tablaLineaFacturas.getSelectedRowCount()==1){
								int id,num;
								num = tablaLineaFacturas.getSelectedRow();
								id =  (Integer) tablaLineaFacturas.getValueAt(num, 0);
								for(int i = 0;i<tablaLineaFacturas.getSize().getWidth();i++){

									if((Integer)tablaLineaFacturas.getValueAt(i, 0)==id){
										//if (editar.getEncontrada())
										{ 	
											// instancia a modificar

											ControlLinea controladorLineaFactura = ControlLinea.getControladorLinea(); 
											try {
												controladorLineaFactura.BorrarLinea(id);
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
										BarraEstado.setText("LineaFactura borrada.");
										cambios = true;	
										try {
											modeloLineaFacturas.actualizar(c);
										} catch (DAOExcepcion e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										break;

									}
								}
							}else {Object[] options = {"Aceptar"}; 
							int n = JOptionPane.showOptionDialog(
									null, "Por favor, selecciona una línea.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									error, 
									options,
									options[0]);
							}
						}			

			});

					
					{
						botonDetalle = new JButton();
						PanelEste.add(botonDetalle);
						botonDetalle.setText("Detalle líneas");

						botonDetalle.setPreferredSize(new java.awt.Dimension(100, 46));
						botonDetalle.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("botonDetalle.actionPerformed, event="+evt);
								//TODO add your code for botonDetalle.actionPerformed

								{ //REVISAR
									LineasFactura migestion = new LineasFactura();
									migestion.GetLineaFacturas();
									migestion.setVisible(true);
								}
							}
						});
					}
					{
						controlLineas = new JButton();
						PanelEste.add(controlLineas);
						controlLineas.setText("Control Líneas");
						controlLineas.setPreferredSize(new java.awt.Dimension(100, 46));
						controlLineas.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("controlLineas.actionPerformed, event="+evt);
								//TODO add your code for controlLineas.actionPerformed
								if(tablaLineaFacturas.getSelectedRowCount()==1 ){
									System.out.println(selectedIndex);
									int id,num;
									cambios = false;
									num = tablaLineaFacturas.getSelectedRow();
									id =  (Integer) tablaLineaFacturas.getValueAt(num, 0);
									for(int i = 0;i<tablaLineaFacturas.getSize().getWidth();i++){
										if((Integer)tablaLineaFacturas.getValueAt(i, 0)==id){
											ControlLinea controladorLineaFactura = ControlLinea.getControladorLinea(); 
											LineaFactura inm;
											try {
												inm = controladorLineaFactura.EncontrarLineaPorId(id);
												VentanaDetalleLineaFactura v2=new VentanaDetalleLineaFactura(c,inm);
												v2.setVisible(true);
												cambios = true;
												break;
											}
										
											catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}			
										}
										
									}
								}

							}
						});
					}
					this.setSize(960, 520);
					{
						jMenuBar1 = new JMenuBar();
						setJMenuBar(jMenuBar1);
						{
							jMenu3 = new JMenu();
							jMenuBar1.add(jMenu3);
							jMenu3.setText("Archivo");
							{
								guardarMenuItem = new JMenuItem();
								jMenu3.add(guardarMenuItem);
								guardarMenuItem.setText("Generar informe");
								guardarMenuItem.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										System.out.println("guardarMenuItem.actionPerformed, event="+evt);



										int numer = tablaLineaFacturas.getSelectedRow();
										int idinf =  (Integer) tablaLineaFacturas.getValueAt(numer, 0);
										//informe.generaInforme(idinf);

										//TODO add your code for guardarMenuItem.actionPerformed

									}
								});
							}
							
							{
								jSeparator2 = new JSeparator();
								jMenu3.add(jSeparator2);
							}
							{
								salirMenuItem = new JMenuItem();
								jMenu3.add(salirMenuItem);
								salirMenuItem.setText("Salir");
								salirMenuItem.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {

									System.out.println("Salgo");
									}
									});
							}
						}
						{
							jMenu4 = new JMenu();
							jMenuBar1.add(jMenu4);
							jMenu4.setText("Aspecto");
							{
								fuenteMenuItem = new JMenuItem();
								jMenu4.add(fuenteMenuItem);
								fuenteMenuItem.setText("Color fuente");
								fuenteMenuItem.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										JColorChooser ColorFuente = new JColorChooser();
										Color CFuente = ColorFuente.showDialog(null,"Color fondo",fondoMenuItem.getBackground());
										if(CFuente != null){

											fondoMenuItem.setForeground(CFuente);
											fuenteMenuItem.setForeground(CFuente);
											jMenu4.setForeground(CFuente);
											salirMenuItem.setForeground(CFuente);
											
											BarraHerramientas.setForeground(CFuente);
											PanelEste.setForeground(CFuente);					
											jMenuBar1.setForeground(CFuente);
											Modelo.setForeground(CFuente);

											jSeparator2.setForeground(CFuente);

											PanelEste.setForeground(CFuente);
											guardarMenuItem.setForeground(CFuente);
											cargarFileMenuItem.setForeground(CFuente);

											jMenu3.setForeground(CFuente);
											jMenuBar1.setForeground(CFuente);
											Modelo.setForeground(CFuente);


											BarraEstado.setForeground(CFuente);
											jFechaPeticion.setForeground(CFuente);
											tablaLineaFacturas.setForeground(CFuente);
											panelSur.setForeground(CFuente);
										}
									}
								});
							}
							{
								fondoMenuItem = new JMenuItem();
								jMenu4.add(fondoMenuItem);
								fondoMenuItem.setText("Color fondo");
								fondoMenuItem.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										System.out.println("fondoMenuItem.actionPerformed, event="+evt);
										//TODO add your code for fondoMenuItem.actionPerformed
										{	
											ColorFondo = new JColorChooser();
											Color CFondo = ColorFondo.showDialog(null,"Color fondo",fondoMenuItem.getBackground());
											if(CFondo != null){

												fondoMenuItem.setBackground(CFondo);
												fuenteMenuItem.setBackground(CFondo);
												jMenu4.setBackground(CFondo);
												salirMenuItem.setBackground(CFondo);
												
												BarraHerramientas.setBackground(CFondo);
												PanelEste.setBackground(CFondo);					
												jMenuBar1.setBackground(CFondo);
												Modelo.setBackground(CFondo);

												jSeparator2.setBackground(CFondo);

												PanelEste.setBackground(CFondo);
												guardarMenuItem.setBackground(CFondo);
												cargarFileMenuItem.setBackground(CFondo);

												jMenu3.setBackground(CFondo);
												jMenuBar1.setBackground(CFondo);
												Modelo.setBackground(CFondo);


												BarraEstado.setBackground(CFondo);
												jFechaPeticion.setBackground(CFondo);
												tablaLineaFacturas.setBackground(CFondo);
												panelSur.setBackground(CFondo);




											}


										}

									}
								});
							}

						}
					}
		}

	}
			{	Modelo = new JScrollPane();
			getContentPane().add(Modelo, BorderLayout.CENTER);
			{
				tablaLineaFacturas = new JTable(modeloLineaFacturas);
				Modelo.setViewportView(tablaLineaFacturas);							
				
			}
			
			
			
			
			
			
			
			}
			
} catch (Exception e) {
	e.printStackTrace();
}
}
}
