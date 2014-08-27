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
import gestion.GestionInmuebles;
import gestion.GestionPropietarios;
import hibernate.Comunidad;
import hibernate.Concepto;
import hibernate.Inmueble;
import hibernate.Propietario;

import reports.Informe;
import tablas.*;

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
public class VentanaPrincipal extends javax.swing.JFrame {

	//private JMenuItem deleteMenuItem;
	//private JSeparator jSeparator1;
	//private JMenuItem pasteMenuItem;
	private JMenuItem fuenteMenuItem;
	private JMenuItem fondoMenuItem;
	private JMenu jMenu4;
	private JMenuItem salirMenuItem;
	private JSeparator jSeparator2;
	private JFrame jFrame = null;

	private JMenuItem guardarComoMenuItem;
	private JToolBar BarraHerramientas;
	private JButton botonGuardar;
	private JButton botonAbrir;
	private JPanel PanelEste;
	private JMenuItem guardarMenuItem;
	private JMenuItem cargarFileMenuItem;

	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	private JScrollPane Modelo;
	private JScrollPane ModeloPropietarios;
	private JScrollPane ModeloComunidades;
	private JButton jButton1;
	private JScrollPane ModeloConceptos;
	private JButton botonActualizar;
	private JTabbedPane jTabbedPane2;
	private JTabbedPane jTabbedPane1;
	private JButton botonDetalle;
	private JColorChooser ColorFondo;

	private JLabel BarraEstado;
	private JDateChooser jFechaPeticion;
	private JButton botonBorrar;
	private JButton botonEditar;
	private JTable tabla;
	private JTable tablaPropietarios;
	private JTable tablaComunidades;
	private JTable tablaConceptos;
	private JPanel panelSur;
	private JButton botonAñadir;
	private ImageIcon error =new ImageIcon(getClass().getClassLoader().getResource("interfaz/icoError.gif"));
	String fichero = null;
	int selectedIndex;
	

	boolean cambios = false;
	Informe informe = new Informe();
	/////////////Inmuebles
	private ModeloTablaInmueble modeloInmuebles = new ModeloTablaInmueble(new GestionInmuebles());
	private ModeloTablaComunidad modeloComunidades = new ModeloTablaComunidad(new GestionComunidades());
	private ModeloTablaPropietario modeloPropietarios = new ModeloTablaPropietario(new GestionPropietarios());
	private ModeloTablaConcepto modeloConceptos = new ModeloTablaConcepto(new GestionConceptos());

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaPrincipal inst = new VentanaPrincipal();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaPrincipal() {
		super();
		initGUI();
	}
	private void guardar(){

		if(fichero==null) guardarComo();
		else{ modeloInmuebles.guardaInmuebles(fichero);
		BarraEstado.setText("Fichero guardado.");
		cambios = false;

		}
	}
	private void guardarComo(){
		JFileChooser selector = new JFileChooser();
		int selection = selector.showSaveDialog(this);
		if(selection==JFileChooser.APPROVE_OPTION){
			fichero=selector.getSelectedFile().getAbsolutePath();
			modeloInmuebles.guardaInmuebles(fichero);
			BarraEstado.setText("Fichero guardado.");
			cambios = false;
		}
	}
	private void cargar(){
		JFileChooser selector = new JFileChooser();
		int selection = selector.showOpenDialog(this);
		if(selection==JFileChooser.APPROVE_OPTION){
			fichero=selector.getSelectedFile().getAbsolutePath();
			//modeloInmuebles.cargaInmuebles(fichero);
			BarraEstado.setText("Fichero cargado.");
			cambios = false;
		}
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
					botonAbrir = new JButton();
					BarraHerramientas.add(botonAbrir);
					botonAbrir.setPreferredSize(new java.awt.Dimension(184, 33));
					botonAbrir.setIcon(new ImageIcon(getClass().getClassLoader().getResource("interfaz/openFile.png")));
					botonAbrir.setOpaque(false);
					botonAbrir.setSize(32, 32);
					botonAbrir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("botonAbrir.actionPerformed, event="+evt);
							//TODO add your code for botonAbrir.actionPerformed
							cargar();
						}
					});
				}
				{
					botonGuardar = new JButton();
					BarraHerramientas.add(botonGuardar);
					botonGuardar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("interfaz/saveFile.png")));
					botonGuardar.setToolTipText("Guardar");
					botonGuardar.setPreferredSize(new java.awt.Dimension(31, 32));
					botonGuardar.setSize(32, 32);
					botonGuardar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("botonGuardar.actionPerformed, event="+evt);
							//TODO add your code for botonGuardar.actionPerformed

							guardar();
						}
					});
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
								modeloComunidades.actualizar();
							} catch (DAOExcepcion e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								modeloInmuebles.actualizar();
							} catch (DAOExcepcion e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								modeloPropietarios.actualizar();
							} catch (DAOExcepcion e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								modeloConceptos.actualizar();
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
					botonAñadir.setText("Añadir");
					botonAñadir.setPreferredSize(new java.awt.Dimension(100, 46));
					botonAñadir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {

							System.out.println("botonAñadir.actionPerformed, event="+evt);
							//TODO add your code for botonAñadir.actionPerformed

							selectedIndex = jTabbedPane1.getSelectedIndex();


							switch (selectedIndex){
							case 0 : //Propietarios
								VentanaDetallePropietario vp=new VentanaDetallePropietario(tablaPropietarios.getModel());
								vp.setVisible(true);
								cambios = true;
								BarraEstado.setText("Propietario añadido.");
								break;
							case 1 ://Comunidades
								VentanaDetalleComunidad vc=new VentanaDetalleComunidad(tablaComunidades.getModel());
								vc.setVisible(true);
								cambios = true;
								BarraEstado.setText("Comunidad añadida.");
								break;
							case 2 : //Inmuebles
								VentanaDetalleInmueble vi=new VentanaDetalleInmueble(tabla.getModel(),tablaComunidades.getModel(),tablaPropietarios.getModel());
								vi.setVisible(true);
								cambios = true;
								BarraEstado.setText("Inmueble añadido.");
								break;
							case 3 : //Concepto
								VentanaDetalleConcepto vcon=new VentanaDetalleConcepto(tablaConceptos.getModel());
								vcon.setVisible(true);
								cambios = true;
								BarraEstado.setText("Concepto añadido.");break;

							default : break;
							}
						}
					});

				}
				{
					botonEditar = new JButton();
					PanelEste.add(botonEditar);
					botonEditar.setText("Editar");
					botonEditar.setSize(100, 50);
					botonEditar.setPreferredSize(new java.awt.Dimension(100, 46));
					botonEditar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("botonEditar.actionPerformed, event="+evt);


							//TODO add your code for botonEditar.actionPerformed
							selectedIndex = jTabbedPane1.getSelectedIndex();


							if(selectedIndex == 2){
								if(tabla.getSelectedRowCount()==1 ){
									System.out.println(selectedIndex);
									int id,num;
									cambios = false;
									num = tabla.getSelectedRow();
									id =  (Integer) tabla.getValueAt(num, 0);
									for(int i = 0;i<tabla.getSize().getWidth();i++){

										if((Integer)tabla.getValueAt(i, 0)==id){
											ControlInmueble controladorInmueble = ControlInmueble.getControladorInmueble(); 
											Inmueble inm;
											try {
												inm = controladorInmueble.EncontrarInmueblePorId(id);
												VentanaDetalleInmueble v2=new VentanaDetalleInmueble(inm);
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
								else {Object[] options = {"Aceptar"}; 
								int n = JOptionPane.showOptionDialog(
										null, "Por favor, selecciona un inmueble.","Error",JOptionPane.OK_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										error, 
										options,
										options[0]);
								}
							}else if(selectedIndex == 0){
								if(tablaPropietarios.getSelectedRowCount()==1 ){
									//EDITAR PROPIETARIO:
									System.out.println(selectedIndex);
									int id,num;
									cambios = false;
									num = tablaPropietarios.getSelectedRow();
									id =  (Integer) tablaPropietarios.getValueAt(num, 0);
									for(int i = 0;i<tablaPropietarios.getSize().getWidth();i++){

										if((Integer)tablaPropietarios.getValueAt(i, 0)==id){
											ControlPropietario controladorPropietario = ControlPropietario.getControladorPropietario(); 
											Propietario prop;
											try {
												prop = controladorPropietario.EncontrarPropietarioPorId(id);
												VentanaDetallePropietario v2=new VentanaDetallePropietario(prop);
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
										null, "Por favor, selecciona un propietario.","Error",JOptionPane.OK_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										error, 
										options,
										options[0]);
								}
							}else if(selectedIndex == 3){
								if(tablaConceptos.getSelectedRowCount()==1 ){
									//EDITAR PROPIETARIO:
									System.out.println(selectedIndex);
									int id,num;
									cambios = false;
									num = tablaConceptos.getSelectedRow();
									id =  (Integer) tablaConceptos.getValueAt(num, 0);
									for(int i = 0;i<tablaConceptos.getSize().getWidth();i++){

										if((Integer)tablaConceptos.getValueAt(i, 0)==id){
											ControlConcepto controladorConcepto = ControlConcepto.getControladorConcepto(); 
											Concepto prop;
											try {
												prop = controladorConcepto.EncontrarConceptoPorId(id);
												VentanaDetalleConcepto v2=new VentanaDetalleConcepto(prop);
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
								else {Object[] options = {"Aceptar"}; 
								int n = JOptionPane.showOptionDialog(
										null, "Por favor, selecciona un concepto.","Error",JOptionPane.OK_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										error, 
										options,
										options[0]);
								}
							}

							else if(selectedIndex == 1)
								if(tablaComunidades.getSelectedRowCount()==1 ){
									//EDITAR COMUNIDAD:
									System.out.println(selectedIndex);
									int id,num;
									cambios = false;
									num = tablaComunidades.getSelectedRow();
									id =  (Integer) tablaComunidades.getValueAt(num, 0);
									for(int i = 0;i<tablaComunidades.getSize().getWidth();i++){

										if((Integer)tablaComunidades.getValueAt(i, 0)==id){
											ControlComunidad controladorComunidad = ControlComunidad.getControladorComunidad(); 
											Comunidad inm;
											try {
												inm = controladorComunidad.EncontrarComunidadPorId(id);
												VentanaDetalleComunidad v2=new VentanaDetalleComunidad(inm);
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
								else {Object[] options = {"Aceptar"}; 
								int n = JOptionPane.showOptionDialog(
										null, "Por favor, selecciona una comunidad.","Error",JOptionPane.OK_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										error, 
										options,
										options[0]);
								}
						}
					
				});
					{
						jTabbedPane1 = new JTabbedPane();
						getContentPane().add(jTabbedPane1, BorderLayout.WEST);
						jTabbedPane1.setPreferredSize(new java.awt.Dimension(840, 394));
						{	Modelo = new JScrollPane();
						getContentPane().add(Modelo, BorderLayout.CENTER);
						{
							tabla = new JTable(modeloInmuebles);
							Modelo.setViewportView(tabla);							

						}




						{ModeloPropietarios = new JScrollPane();
						jTabbedPane1.addTab("Propietarios", null, ModeloPropietarios, null);
						ModeloPropietarios.setPreferredSize(new java.awt.Dimension(488, 190));
						Modelo.setPreferredSize(new java.awt.Dimension(495, 231));
						{
							tablaPropietarios = new JTable(modeloPropietarios);
							ModeloPropietarios.setViewportView(tablaPropietarios);


						}						


						}
						{ModeloComunidades = new JScrollPane();
						jTabbedPane1.addTab("Comunidades", null, ModeloComunidades, null);
						jTabbedPane1.addTab("Inmuebles", null, Modelo, null);

						Modelo.setPreferredSize(new java.awt.Dimension(495, 231));
						{
							tablaComunidades = new JTable(modeloComunidades);
							ModeloComunidades.setViewportView(tablaComunidades);


						}						

						}

						{ModeloConceptos = new JScrollPane();
						jTabbedPane1.addTab("Conceptos", null, ModeloConceptos, null);
						ModeloConceptos.setPreferredSize(new java.awt.Dimension(488, 190));
						Modelo.setPreferredSize(new java.awt.Dimension(495, 231));
						{
							tablaConceptos = new JTable(modeloConceptos);
							ModeloConceptos.setViewportView(tablaConceptos);


						}						


						}


						}
						modeloInmuebles.actualizar();
						modeloComunidades.actualizar();
						modeloPropietarios.actualizar();
						modeloConceptos.actualizar();



					}

			}
			{
				botonBorrar = new JButton();
				PanelEste.add(botonBorrar);
				botonBorrar.setText("Borrar");
				botonBorrar.setPreferredSize(new java.awt.Dimension(100, 46));
				botonBorrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("botonBorrar.actionPerformed, event="+evt);
						//TODO add your code for botonBorrar.actionPerformed
						//BuscaInmueble editar = new BuscaInmueble(jFrame);
						//editar.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
						//editar.setVisible(true);

						selectedIndex = jTabbedPane1.getSelectedIndex();

						if(selectedIndex == 2 ){
							
							if(tabla.getSelectedRowCount()==1){
							int id,num;
							num = tabla.getSelectedRow();
							id =  (Integer) tabla.getValueAt(num, 0);
							for(int i = 0;i<tabla.getSize().getWidth();i++){

								if((Integer)tabla.getValueAt(i, 0)==id){
									//if (editar.getEncontrada())
									{ 	
										// instancia a modificar

										ControlInmueble controladorInmueble = ControlInmueble.getControladorInmueble(); 
										try {
											controladorInmueble.BorrarInmueble(id);
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
									BarraEstado.setText("Inmueble borrado.");
									cambios = true;	
									try {
										modeloInmuebles.actualizar();
									} catch (DAOExcepcion e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;

								}
							}
						}
							else {Object[] options = {"Aceptar"}; 
							int n = JOptionPane.showOptionDialog(
									null, "Por favor, selecciona un inmueble.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									error, 
									options,
									options[0]);
							}
						}
						else if(selectedIndex == 0){
							if(tablaPropietarios.getSelectedRowCount()==1 ){
							int id,num;
							num = tablaPropietarios.getSelectedRow();
							id =  (Integer) tablaPropietarios.getValueAt(num, 0);
							for(int i = 0;i<tablaPropietarios.getSize().getWidth();i++){

								if((Integer)tablaPropietarios.getValueAt(i, 0)==id){
									//if (editar.getEncontrada())
									{ 	
										// instancia a modificar

										ControlPropietario controladorPropietario = ControlPropietario.getControladorPropietario(); 
										try {
											controladorPropietario.BorrarPropietario(id);
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
									BarraEstado.setText("Propietario borrado.");
									cambios = true;	
									try {
										modeloPropietarios.actualizar();
										modeloInmuebles.actualizar();
									} catch (DAOExcepcion e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;

								}
							}
						}
							else {Object[] options = {"Aceptar"}; 
							int n = JOptionPane.showOptionDialog(
									null, "Por favor, selecciona un propietario.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									error, 
									options,
									options[0]);
							}
						}

						else  if(selectedIndex == 3){
							if(tablaConceptos.getSelectedRowCount()==1 ){
						
							int id,num;
							num = tablaConceptos.getSelectedRow();
							id =  (Integer) tablaConceptos.getValueAt(num, 0);
							for(int i = 0;i<tablaConceptos.getSize().getWidth();i++){

								if((Integer)tablaConceptos.getValueAt(i, 0)==id){
									//if (editar.getEncontrada())
									{ 	
										// instancia a modificar

										ControlConcepto controladorConcepto = ControlConcepto.getControladorConcepto(); 
										try {
											controladorConcepto.BorrarConcepto(id);
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
									BarraEstado.setText("Concepto borrado.");
									cambios = true;	
									try {
										modeloConceptos.actualizar();

									} catch (DAOExcepcion e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;

								}
							}
						}
							else {Object[] options = {"Aceptar"}; 
							int n = JOptionPane.showOptionDialog(
									null, "Por favor, selecciona un concepto.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									error, 
									options,
									options[0]);
							}
						}
						

						else if(selectedIndex == 1){
							if(tablaComunidades.getSelectedRowCount()==1){
						
							int id,num;
							num = tablaComunidades.getSelectedRow();
							id =  (Integer) tablaComunidades.getValueAt(num, 0);
							for(int i = 0;i<tablaComunidades.getSize().getWidth();i++){

								if((Integer)tablaComunidades.getValueAt(i, 0)==id){
									//if (editar.getEncontrada())
									{ 	
										// instancia a modificar

										ControlComunidad controladorComunidad = ControlComunidad.getControladorComunidad(); 
										try {
											controladorComunidad.BorrarComunidad(id);
										} catch (DAOExcepcion e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error en la capa de acceso a datos", "No se puede actualiar la Comunidad", JOptionPane.ERROR_MESSAGE);
										}  catch (DAOConfiguracionExcepcion e2)
										{
											JOptionPane.showMessageDialog(null, "Error severo en BD", "Inténtelo más tarde.", JOptionPane.ERROR_MESSAGE);
											// System.exit(0);
										}

									}
									BarraEstado.setText("Comunidad borrada.");
									cambios = true;	
									try {
										modeloComunidades.actualizar();
										modeloInmuebles.actualizar();
									} catch (DAOExcepcion e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;

								}
							}
						}
							else {Object[] options = {"Aceptar"}; 
							int n = JOptionPane.showOptionDialog(
									null, "Por favor, selecciona una comunidad.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									error, 
									options,
									options[0]);
							}
						}
					}

				});

				//Inmueble i= modeloInmuebles.recuperaInmueblePorPosicion(tabla.getSelectedRow());

				{
					botonDetalle = new JButton();
					PanelEste.add(botonDetalle);
					botonDetalle.setText("Detalle");

					botonDetalle.setPreferredSize(new java.awt.Dimension(100, 46));
					botonDetalle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("botonDetalle.actionPerformed, event="+evt);
							//TODO add your code for botonDetalle.actionPerformed

							selectedIndex = jTabbedPane1.getSelectedIndex();

							if(selectedIndex == 2 )
							{
								Inmuebles migestion = new Inmuebles();
								migestion.GetInmuebles();
								migestion.setVisible(true);
							}
							else 	if(selectedIndex == 0 )
							{	
								Propietarios migestion2 = new Propietarios();
								migestion2.GetPropietarios();
								migestion2.setVisible(true);
							}
							else if(selectedIndex == 1 ) {
								Comunidades migestion3 = new Comunidades();
								migestion3.GetComunidads();
								migestion3.setVisible(true);

							}
						}
					});
				}
				{
					jButton1 = new JButton();
					PanelEste.add(jButton1);
					jButton1.setText("Ver Facturas");
					jButton1.setPreferredSize(new java.awt.Dimension(100, 46));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("jButton1.actionPerformed, event="+evt);
							//TODO add your code for jButton1.actionPerformed


							if(tablaComunidades.getSelectedRowCount()==1){
								int num, id;
								System.out.println("Entro aquí");
								num = tablaComunidades.getSelectedRow();
								id =  (Integer) tablaComunidades.getValueAt(num, 0);

								for(int i = 0;i<tablaComunidades.getSize().getWidth();i++){

									if((Integer)tablaComunidades.getValueAt(i, 0)==id){
										ControlComunidad controladorComunidad = ControlComunidad.getControladorComunidad(); 
										Comunidad inm;
										try {

											try {
												modeloComunidades.actualizar();
											} catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											try {
												modeloInmuebles.actualizar();
											} catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											try {
												modeloPropietarios.actualizar();
											} catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											try {
												modeloConceptos.actualizar();
											} catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}

											inm = controladorComunidad.EncontrarComunidadPorId(id);
											VentanaFacturaComunidad vf=new VentanaFacturaComunidad(inm);
											vf.setVisible(true);


											break;
										}
										catch (DAOExcepcion e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}			
									}
								}
							}	
							else {Object[] options = {"Aceptar"}; 
							int n = JOptionPane.showOptionDialog(
									null, "Selecciona una comunidad.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null, 
									options,
									options[0]);
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




									//TODO add your code for guardarMenuItem.actionPerformed

								}
							});
						}
						{
							guardarComoMenuItem = new JMenuItem();
							jMenu3.add(guardarComoMenuItem);
							guardarComoMenuItem.setText("Guardar como...");
							guardarComoMenuItem.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out.println("guardarComoMenuItem.actionPerformed, event="+evt);
									//TODO add your code for guardarComoMenuItem.actionPerformed
									guardarComo();
								}
							});
						}
						{
							cargarFileMenuItem = new JMenuItem();
							jMenu3.add(cargarFileMenuItem);
							cargarFileMenuItem.setText("Cargar de disco");
							cargarFileMenuItem.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out.println("cargarFileMenuItem.actionPerformed, event="+evt);
									//TODO add your code for cargarFileMenuItem.actionPerformed
									cargar();
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

									if(cambios){
										Object[] options = {"Sí", "No","Cancelar"}; 
										int n = JOptionPane.showOptionDialog(
												null, "¿Quieres guardar los cambios?","Salir",JOptionPane.YES_NO_CANCEL_OPTION,
												JOptionPane.QUESTION_MESSAGE,
												null, 
												options,
												options[2]);


										switch(n){

										case 0 : guardar(); dispose();System.exit(0);break;
										case 1 :  dispose();System.exit(0);break;
										case 2 : break;
										}
									}
									else{
										System.out.println("salirMenuItem.actionPerformed, event="+evt);
										dispose();
										System.exit(0);
									}
									//TODO add your code for salirMenuItem.actionPerformed
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
										guardarComoMenuItem.setForeground(CFuente);
										BarraHerramientas.setForeground(CFuente);
										PanelEste.setForeground(CFuente);					
										jMenuBar1.setForeground(CFuente);
										Modelo.setForeground(CFuente);

										jSeparator2.setForeground(CFuente);

										botonGuardar.setForeground(CFuente);
										botonAbrir.setForeground(CFuente);
										PanelEste.setForeground(CFuente);
										guardarMenuItem.setForeground(CFuente);
										cargarFileMenuItem.setForeground(CFuente);

										jMenu3.setForeground(CFuente);
										jMenuBar1.setForeground(CFuente);
										Modelo.setForeground(CFuente);


										BarraEstado.setForeground(CFuente);
										jFechaPeticion.setForeground(CFuente);
										tabla.setForeground(CFuente);
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
											guardarComoMenuItem.setBackground(CFondo);
											BarraHerramientas.setBackground(CFondo);
											PanelEste.setBackground(CFondo);					
											jMenuBar1.setBackground(CFondo);
											Modelo.setBackground(CFondo);

											jSeparator2.setBackground(CFondo);

											botonGuardar.setBackground(CFondo);
											botonAbrir.setBackground(CFondo);
											PanelEste.setBackground(CFondo);
											guardarMenuItem.setBackground(CFondo);
											cargarFileMenuItem.setBackground(CFondo);

											jMenu3.setBackground(CFondo);
											jMenuBar1.setBackground(CFondo);
											Modelo.setBackground(CFondo);


											BarraEstado.setBackground(CFondo);
											jFechaPeticion.setBackground(CFondo);
											tabla.setBackground(CFondo);
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

	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
