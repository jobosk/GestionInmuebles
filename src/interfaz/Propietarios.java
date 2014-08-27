package interfaz;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

import control.ControlInmueble;
import control.ControlPropietario;
import hibernate.*;


/**
 * 
 */

/**
 * @author �lvaro
 *
 */
public class Propietarios extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	
	private JPanel jPanelNorte = null;

	private JPanel jPanelOeste = null;

	private JPanel jPanelEste = null;

	private JPanel jPanelCentral = null;

	private JScrollPane jScrollPaneTabla = null;

	private JTable jTablePropietarios = null;
	// a�adido a mano, modelo de la tabla,
    // conectar la capa de negocio.
		
	ControlPropietario controladorPropietario = ControlPropietario.getControladorPropietario();
	
	// definido como clase local, al final del fichero
	ModeloTabla MiModeloTabla = new ModeloTabla();

	
	int filaseleccionada = -1;

	private JButton jButtonSalida = null;

	/**
	 * This is the default constructor
	 */
	public Propietarios() {
		super();
		initialize();
		
	}
	
	public void GetPropietarios()
	// rellena el JTable con las pel�culas encontradas
	{
		
		try {
			this.controladorPropietario.GetPropietarios();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la capa de acceso a datos", "No se puede acceder a Pel�culas", JOptionPane.ERROR_MESSAGE);
		}  catch (DAOConfiguracionExcepcion e2)
	    {
		JOptionPane.showMessageDialog(null, "Error severo en BD", "Se detendr� el programa.", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	    }
		
		////////////////////////////////////
		Propietario i = null;

	    // rellena el contenido de la tabla.
	    for (int i1 = 0; i1 < controladorPropietario.GetNumPropietarios(); i1++) {
	      i = (Propietario) controladorPropietario.GetActual(i1);
	      
	      this.jTablePropietarios.getModel().setValueAt(new Integer(i.getNif()),i1, 0);
	      this.jTablePropietarios.getModel().setValueAt(new String(i.getNombre()),i1,1);
	      this.jTablePropietarios.getModel().setValueAt(new String(i.getTelefono()),i1,2);
	      this.jTablePropietarios.getModel().setValueAt(new String(i.getDireccion()),i1,3);
	      this.jTablePropietarios.getModel().setValueAt(new String(i.getPoblacion()),i1,4);
	      this.jTablePropietarios.getModel().setValueAt(new String(i.getNumeroCuenta()),i1,5);
	      this.jTablePropietarios.getModel().setValueAt(new String (i.getEntidad()),i1,6);
	      this.jTablePropietarios.getModel().setValueAt(new String(i.getObservaciones()),i1,7);
	      this.jTablePropietarios.getModel().setValueAt(new String(i.getFechaAlta()),i1,8);
	      //this.jTablePropietarios.getModel().setValueAt(new Integer(i.getInmuebles().size()),i1,9);
	      
	    }
	    // selecciona la primera fila
	    if (jTablePropietarios.getRowCount() > 0)
	    	jTablePropietarios.addRowSelectionInterval(0, 0);
	}
	
	// No se utiliza en esta implementaci�n.
	@SuppressWarnings("unused")
	private boolean hayFilaSeleccionada()
	{
		int fila = jTablePropietarios.getSelectedRow();
		if ((0<=fila)&(fila<=jTablePropietarios.getRowCount()-1)) return true;
		else return false;
	}
	
	
	
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(690, 431);
		this.setContentPane(getJContentPane());
		this.setTitle("Ver propietarios");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				cerrarventana();
			}
		});
	}
	
	private void cerrarventana()
	{  // cierra la ventana actual y abre la ventana que hizo la invocaci�n
		this.setVisible(false);
		return;
		
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanelNorte(), BorderLayout.NORTH);
			jContentPane.add(getJPanelOeste(), BorderLayout.WEST);
			jContentPane.add(getJPanelEste(), BorderLayout.EAST);
			jContentPane.add(getJPanelCentral(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButtonSalir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	
	
	

	/**
	 * This method initializes jPanelNorte	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelNorte() {
		if (jPanelNorte == null) {
			jPanelNorte = new JPanel();
			jPanelNorte.setLayout(new FlowLayout());
			jPanelNorte.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelNorte.setMinimumSize(new Dimension(44, 150));
			jPanelNorte.setPreferredSize(new Dimension(4, 40));
			
		}
		return jPanelNorte;
	}

	/**
	 * This method initializes jPanelOeste	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelOeste() {
		if (jPanelOeste == null) {
			jPanelOeste = new JPanel();
			jPanelOeste.setLayout(new GridBagLayout());
			jPanelOeste.setPreferredSize(new Dimension(50, 10));
			
		}
		return jPanelOeste;
	}

	/**
	 * This method initializes jPanelEste	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelEste() {
		if (jPanelEste == null) {
			jPanelEste = new JPanel();
			jPanelEste.setLayout(new FlowLayout());
			jPanelEste.setPreferredSize(new Dimension(90, 10));
			
			jPanelEste.add(getJButtonSalida(), null);
		}
		return jPanelEste;
	}

	/**
	 * This method initializes jPanelCentral	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelCentral() {
		if (jPanelCentral == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			jPanelCentral = new JPanel();
			jPanelCentral.setLayout(new GridBagLayout());
			jPanelCentral.add(getJScrollPaneTabla(), gridBagConstraints);
		}
		return jPanelCentral;
	}

	/**
	 * This method initializes jScrollPaneTabla	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneTabla() {
		if (jScrollPaneTabla == null) {
			jScrollPaneTabla = new JScrollPane();
			jScrollPaneTabla.setViewportView(getJTablePropietarios());
		}
		return jScrollPaneTabla;
	}

	/**
	 * This method initializes jTablePropietarios	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTablePropietarios() {
		if (jTablePropietarios == null) {
			jTablePropietarios = new JTable();
			jTablePropietarios.setModel(MiModeloTabla);
			// evitar selecci�n multiple
			jTablePropietarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    // cambiar el color de la cabecera de la tabla.
		    JTableHeader CabeceraTabla = jTablePropietarios.getTableHeader();
		    CabeceraTabla.setBackground(new Color(216, 228, 248));
		    CabeceraTabla.setForeground(new Color(16, 101, 158));
		}
		return jTablePropietarios;
	}
//////////////////////////////// Modelo de la tabla ///////////////////////
//	 Modelo para la tabla.........
	@SuppressWarnings("serial")
	static class ModeloTabla extends AbstractTableModel
	     // implements MouseListener si desea manejar eventos de rat�n sobre la tabla.
	{
	  private String[] columnNames = {"NIF",
	                                  "Nombre",
	                                  "Tel�fono",
	                                  "Direcci�n",
	                                  "Poblaci�n",
	                                  "N�mero de cuenta",
	                                  "Entidad",
	                                  "Observaciones",
	                                  "Fecha de alta"
	                                 };
	  
	  
	  
    
     

	  private Vector data = new Vector(); // cada elemento del vector ser� una fila de la tabla.

	  public ModeloTabla() {

	  }

	  public Vector getFila(int row) {
	    return (Vector) data.get(row);
	  }

	   public int getColumnCount() {
	      return columnNames.length;
	  }

	  public int getRowCount() {
	      if (data==null) return 0;
	      else return data.size();
	  }

	  public String getColumnName(int col) {
	      return columnNames[col];
	  }

	  public Object getValueAt(int row, int col) {
	      // int length = Array.getLength(this.data.get(col));
	     if (row <0 || row >= getRowCount()) return " ";
	     Vector fila = (Vector) data.get(row);
	     return fila.get(col);
	  }

	  @SuppressWarnings("unchecked")
	public void setValueAt(Object value, int row, int col) {
	      if (row < getRowCount()) { // la fila existe.
	         Vector fila = (Vector) data.get(row);
	         if (col < fila.size()) { // existe la columna
	                       fila.set(col, value);
	                       data.set(row,fila);
	             }
	             else { // se a�ade al final..
	                    fila.add(value);
	                    data.set(row,fila);
	                   }
	      }
	      else { // la fila no existe.
	        Vector fila = new Vector();
	        fila.add(value);
	        data.add(fila);

	      }
	      fireTableCellUpdated(row, col);
	  }


	  @SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
	      return getValueAt(0, c).getClass();
	  }


	  public boolean isCellEditable(int row, int col) {
	      return false;
	  }

	  @SuppressWarnings("unchecked")
	public void addfila() {
	   // a�ade una fila vac�a, se asigna con setValueat
	   Vector fila = new Vector();
	   for (int i=0; i<8; i++) fila.add(new String(""));
	   data.add(fila);
	   this.fireTableRowsInserted(data.size(),data.size());
	  }

	  public void borrarfila(int row) {
	    if (row < 0 || row >= getRowCount()) {}
	    else {
	      data.remove(row);
	      this.fireTableRowsDeleted(row, row);
	    }
	  }
	} // fin modelo para la tabla...
	/**
	 * This method initializes jButtonSalida	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSalida() {
		if (jButtonSalida == null) {
			jButtonSalida = new JButton();
			jButtonSalida.setText("Salir");
			jButtonSalida.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cerrarventana();
				}
			});
		}
		return jButtonSalida;
	}

	
//////////////////////////////// Fin modelo de la tabla ///////////////////
}  //  @jve:decl-index=0:visual-constraint="10,10"
