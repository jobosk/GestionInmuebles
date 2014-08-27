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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
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
import hibernate.NotaInformativa;
import hibernate.Propietario;
import hibernate.ReciboInmueble;

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
public class VentanaFacturaComunidad extends javax.swing.JFrame {

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
	private JButton botonGenerarNota;
	private JLabel Comunidad;
	private JButton controlLineas;
	private JButton botonActualizar;
	private JTabbedPane jTabbedPane2;
	private JTabbedPane jTabbedPane1;
	private JColorChooser ColorFondo;

	private JLabel BarraEstado;
	private JDateChooser jFechaPeticion;
	private JButton botonBorrar;
	private JButton botonEditar;
	private JTable tablaFacturas;
	private JPanel panelSur;
	private JButton botonAñadir;
	String fichero = null;
	int selectedIndex;
	private ImageIcon error =new ImageIcon(getClass().getClassLoader().getResource("interfaz/icoError.gif"));

	boolean cambios = false;
	Informe informe = new Informe();

	private ModeloTablaFactura modeloFacturas = new ModeloTablaFactura(new GestionFacturas());
	ControlNota controladorNota= ControlNota.getControladorNota(); //  @jve:decl-index=0:
	ControlRecibo controladorRecibo= ControlRecibo.getControladorRecibo();//  @jve:decl-index=0:
	ControlComunidad controladorComunidad= ControlComunidad.getControladorComunidad();

	private JButton verNotajButton1;
	ControlFactura controladorFactura= ControlFactura.getControladorFactura();
	GestionLineas gestionLineas = new GestionLineas();
	private Comunidad c;
	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaFacturaComunidad inst = new VentanaFacturaComunidad();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaFacturaComunidad() {
		super();
		initGUI();
	}
	public VentanaFacturaComunidad(Comunidad com) {
		super();
		c=com;
		initGUI();
	}


	private int sugiereIdNota() throws DAOExcepcion{
		for(int i = 1;i<1000000000;i++){
			if(controladorNota.EncontrarNotaPorId(i)==null) return i;
		}
		return -1;
	}
	private int sugiereIdRecibo() throws DAOExcepcion{
		for(int i = 1;i<1000000000;i++){
			if(controladorRecibo.EncontrarReciboPorId(i)==null) return i;
		}
		return -1;
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
					Comunidad.setText("  Comunidad: " + c.getIdComunidad()+".               ");
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
								modeloFacturas.actualizar(c);
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
					botonAñadir.setText("Añadir factura");
					botonAñadir.setPreferredSize(new java.awt.Dimension(100, 46));
					botonAñadir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {

							System.out.println("botonAñadir.actionPerformed, event="+evt);
							//TODO add your code for botonAñadir.actionPerformed



							VentanaDetalleFactura vp=new VentanaDetalleFactura(modeloFacturas,c);
							vp.setVisible(true);
							cambios = true;
							BarraEstado.setText("Factura añadida.");


						}
					});

				}
				{
					botonEditar = new JButton();
					PanelEste.add(botonEditar);
					botonEditar.setText("Editar factura");
					botonEditar.setSize(100, 50);
					botonEditar.setPreferredSize(new java.awt.Dimension(100, 46));
					botonEditar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("botonEditar.actionPerformed, event="+evt);




							if(tablaFacturas.getSelectedRowCount()==1 ){
								System.out.println(selectedIndex);
								int id,num;
								cambios = false;
								num = tablaFacturas.getSelectedRow();
								id =  (Integer) tablaFacturas.getValueAt(num, 0);
								for(int i = 0;i<tablaFacturas.getSize().getWidth();i++){
									if((Integer)tablaFacturas.getValueAt(i, 0)==id){
										ControlFactura controladorFactura = ControlFactura.getControladorFactura(); 
										Factura inm;
										try {
											inm = controladorFactura.EncontrarFacturaPorId(id);
											VentanaDetalleFactura v2=new VentanaDetalleFactura(inm,c);
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
									null, "Por favor, selecciona una factura.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									error, 
									options,
									options[0]);
							}

						}
					});
					{

						modeloFacturas.actualizar(c);


					}

				}
				{
					botonBorrar = new JButton();
					PanelEste.add(botonBorrar);
					botonBorrar.setText("Borrar factura");
					botonBorrar.setPreferredSize(new java.awt.Dimension(100, 46));
					botonBorrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("botonBorrar.actionPerformed, event="+evt);
							//TODO add your code for botonBorrar.actionPerformed
							//BuscaInmueble editar = new BuscaInmueble(jFrame);
							//editar.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
							//editar.setVisible(true);

							//REVISAR
							if(tablaFacturas.getSelectedRowCount()==1){
								int id,num;
								num = tablaFacturas.getSelectedRow();
								id =  (Integer) tablaFacturas.getValueAt(num, 0);
								for(int i = 0;i<tablaFacturas.getSize().getWidth();i++){

									if((Integer)tablaFacturas.getValueAt(i, 0)==id){
										//if (editar.getEncontrada())
										{ 	
											// instancia a modificar

											ControlFactura controladorFactura = ControlFactura.getControladorFactura(); 
											try {
												controladorFactura.BorrarFactura(id);
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
										BarraEstado.setText("Factura borrada.");
										cambios = true;	
										try {
											modeloFacturas.actualizar(c);
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
									null, "Por favor, selecciona una factura.","Error",JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									error, 
									options,
									options[0]);
							}
						}			

					});


					{
						controlLineas = new JButton();
						PanelEste.add(controlLineas);
						controlLineas.setText("Control Líneas");
						controlLineas.setPreferredSize(new java.awt.Dimension(100, 46));
						controlLineas.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("controlLineas.actionPerformed, event="+evt);
								//TODO add your code for controlLineas.actionPerformed
								if(tablaFacturas.getSelectedRowCount()==1 ){

									int id,num;
									cambios = false;
									num = tablaFacturas.getSelectedRow();
									id =  (Integer) tablaFacturas.getValueAt(num, 0);
									for(int i = 0;i<tablaFacturas.getSize().getWidth();i++){
										if((Integer)tablaFacturas.getValueAt(i, 0)==id){

											ControlFactura controladorFactura = ControlFactura.getControladorFactura(); 

											Factura inm;
											try {
												inm = controladorFactura.EncontrarFacturaPorId(id);
												VentanaLineaFactura v2=new VentanaLineaFactura(inm);

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
					{
						botonGenerarNota = new JButton();
						PanelEste.add(botonGenerarNota);
						botonGenerarNota.setText("Generar Nota y Recibos");
						botonGenerarNota.setPreferredSize(new java.awt.Dimension(100, 46));
						botonGenerarNota.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("controlLineas.actionPerformed, event="+evt);
								//TODO add your code for controlLineas.actionPerformed

								int estadoComunidad = controladorComunidad.comprobarEstadoComunidadIncorrecta(c);
								if( estadoComunidad == 0){



									///////////////////////////////////////////
									/////////Creamos las notas/////////////////
									///////////////////////////////////////////
									if(tablaFacturas.getSelectedRowCount()>0 ){
										//Añadir: Si la comunidad está cuadrada	
										int id = 0;
										int[] num;
										cambios = false;
										Date fecha = new Date();
										num = tablaFacturas.getSelectedRows();
										NotaInformativa nota = new NotaInformativa();
										try {
											nota.setIdNota(sugiereIdNota());
											nota.setComunidad(c);
											nota.setFechaCalculo(fecha.toString());

										} catch (DAOExcepcion e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										HashSet facturas = new HashSet();
										Double importe = 0.0;
										Factura f;
										for(int k=0;k<tablaFacturas.getSelectedRowCount();k++){
											id =  (Integer) tablaFacturas.getValueAt(num[k], 0);
											try {
												f = controladorFactura.EncontrarFacturaPorId(id);
												importe += controladorFactura.CalcularImporteFactura(f);
												facturas.add(f);
											} catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										System.out.print(importe);

										nota.setFacturas(facturas);
										nota.setImporteNota(importe);
										nota.setReciboInmuebles(null);
										try {
											controladorNota.NuevaNota(nota);
										} catch (DAOExcepcion e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}									


										///////////////////////////////////////////
										/////////Y los recibos para cada inmueble//
										///////////////////////////////////////////
										ControlInmueble controladorInmueble = ControlInmueble.getControladorInmueble();
										ArrayList<Inmueble> inmuebles = null;
										System.out.println("Aqui estoy");
										try {
											inmuebles = controladorInmueble.EncontrarInmueblesPorComunidad(c.getIdComunidad());
										} catch (DAOExcepcion e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										for(int i = 0; i<inmuebles.size();i++){
											System.out.println(inmuebles.get(i).getId());
											ReciboInmueble recibo = new ReciboInmueble();
											try {
												recibo.setIdRecibo(sugiereIdRecibo());
											} catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}

											double importeRecibo = nota.getImporteNota()*inmuebles.get(i).getPorcentaje()/100;
											recibo.setImporte(importeRecibo);
											recibo.setInmueble(inmuebles.get(i));
											recibo.setNotaInformativa(nota);


											////CAMBIAR a una fecha de pago, no de cálculo
											recibo.setFechaPago(nota.getFechaCalculo());






											try {
												controladorRecibo.NuevoRecibo(recibo);
											} catch (DAOExcepcion e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}										

										}





									}
								}
								else if(estadoComunidad == 1 ){

									Object[] options = {"Aceptar"}; 
									int n = JOptionPane.showOptionDialog(
											null, "Porcentajes de la comunidad no cuadrados.","Error",JOptionPane.OK_OPTION,
											JOptionPane.QUESTION_MESSAGE,
											error, 
											options,
											options[0]);


								}

								else {
									Object[] options = {"Aceptar"}; 
									int n = JOptionPane.showOptionDialog(
											null, "Estado de la comunidad diferente a 'Cuadrado'.","Error",JOptionPane.OK_OPTION,
											JOptionPane.QUESTION_MESSAGE,
											error, 
											options,
											options[0]);


								}



								



							}


						});
					}
					{
						verNotajButton1 = new JButton();
						PanelEste.add(verNotajButton1);
						verNotajButton1.setText("Ver Notas");
						verNotajButton1.setPreferredSize(new java.awt.Dimension(100, 46));
						verNotajButton1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("controlLineas.actionPerformed, event="+evt);
								//TODO add your code for controlLineas.actionPerformed


								VentanaNotaComunidad v2=new VentanaNotaComunidad(c);
								v2.setVisible(true);
								cambios = true;

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



										int numer = tablaFacturas.getSelectedRow();
										int idinf =  (Integer) tablaFacturas.getValueAt(numer, 0);
										Comunidad com = null;
										try {
											com = controladorComunidad.EncontrarComunidadPorId(idinf);
										} catch (DAOExcepcion e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										informe.generaInformeNotificacionNuevoRecibo(com,"C://eclipse//reports//naranja.jasper");

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
											tablaFacturas.setForeground(CFuente);
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
												tablaFacturas.setBackground(CFondo);
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
				tablaFacturas = new JTable(modeloFacturas);
				Modelo.setViewportView(tablaFacturas);							

			}







			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
