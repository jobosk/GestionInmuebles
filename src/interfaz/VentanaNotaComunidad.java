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
import gestion.GestionNotas;
import gestion.GestionInmuebles;
import gestion.GestionLineas;
import gestion.GestionPropietarios;
import gestion.GestionRecibos;
import hibernate.Comunidad;
import hibernate.Concepto;
import hibernate.NotaInformativa;
import hibernate.Inmueble;
import hibernate.NotaInformativa;
import hibernate.Propietario;
import hibernate.ReciboInmueble;

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
public class VentanaNotaComunidad extends javax.swing.JFrame {


	private JMenuItem fuenteMenuItem;
	private JMenuItem fondoMenuItem;
	private JMenu jMenu4;
	private JMenuItem salirMenuItem;
	private JSeparator jSeparator2;
	private JFrame jFrame = null;

	private JToolBar BarraHerramientas;
	private JMenuItem guardarMenuItem;
	private JMenuItem cargarFileMenuItem;

	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	private JScrollPane Modelo;
	private JScrollPane Modelo2;
	private JLabel Comunidad;
	private JButton botonActualizar;
	private JTabbedPane jTabbedPane2;
	private JTabbedPane jTabbedPane1;
	private JColorChooser ColorFondo;

	private JLabel BarraEstado;
	private JDateChooser jFechaPeticion;
	private JTable tablaNotaInformativas;
	private JTable tablaRecibos;
	private JPanel panelSur;
	String fichero = null;
	int selectedIndex;
	private ImageIcon error =new ImageIcon(getClass().getClassLoader().getResource("interfaz/icoError.gif"));
	
	boolean cambios = false;
	Informe informe = new Informe();

	private ModeloTablaNota modeloNota = new ModeloTablaNota(new GestionNotas());
	private ModeloTablaRecibo modeloRecibo = new ModeloTablaRecibo(new GestionRecibos());
	ControlNota controladorNota= ControlNota.getControladorNota(); //  @jve:decl-index=0:
	ControlRecibo controladorRecibo = ControlRecibo.getControladorRecibo();
	private Comunidad c;
	private NotaInformativa nota;
	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaNotaComunidad inst = new VentanaNotaComunidad();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public VentanaNotaComunidad() {
		super();
		initGUI();
	}
	public VentanaNotaComunidad(Comunidad com/*, NotaInformativa n*/) {
		super();
		c=com;
		initGUI();
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
			this.setPreferredSize(new java.awt.Dimension(572, 446));
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
								modeloNota.actualizar(c);
								modeloRecibo.actualizar(c);
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
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1, BorderLayout.CENTER);
				{	Modelo = new JScrollPane();
				jTabbedPane1.addTab("Notas informativas", null, Modelo, null);
				Modelo.setPreferredSize(new java.awt.Dimension(551, 319));
				{
					tablaNotaInformativas = new JTable(modeloNota);
					Modelo.setViewportView(tablaNotaInformativas);							
					
				}
				{	Modelo2 = new JScrollPane();
				jTabbedPane1.addTab("Recibos", null, Modelo2, null);
				Modelo2.setPreferredSize(new java.awt.Dimension(456, 347));
				{
					tablaRecibos = new JTable(modeloRecibo);
					Modelo2.setViewportView(tablaRecibos);							
					
				}
				}
				
				
				
				
				modeloNota.actualizar(c);

				modeloRecibo.actualizar(c);
				
				}

			}

} catch (Exception e) {
	e.printStackTrace();
}
}
}
