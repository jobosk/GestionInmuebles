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

import control.ControlComunidad;
import hibernate.*;


/**
 * 
 */

/**
 * @author jsanchez
 *
 */
public class Comunidades extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	
	private JPanel jPanelNorte = null;

	private JPanel jPanelOeste = null;

	private JPanel jPanelEste = null;

	private JPanel jPanelCentral = null;

	private JScrollPane jScrollPaneTabla = null;

	private JTable jTableComunidads = null;
	// a�adido a mano, modelo de la tabla,
    // conectar la capa de negocio.
		
	ControlComunidad controladorComunidad = ControlComunidad.getControladorComunidad();  //  @jve:decl-index=0:
	
	// definido como clase local, al final del fichero
	ModeloTabla MiModeloTabla = new ModeloTabla();

	
	int filaseleccionada = -1;

	private JButton jButtonSalida = null;

	/**
	 * This is the default constructor
	 */
	public Comunidades() {
		super();
		initialize();
		
	}
	
	public void GetComunidads()
	// rellena el JTable con las pel�culas encontradas
	{
		
		try {
			this.controladorComunidad.GetComunidades();
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
		Comunidad i = null;

	    // rellena el contenido de la tabla.
	    for (int i1 = 0; i1 < controladorComunidad.GetNumComunidades(); i1++) {
	      i = (Comunidad) controladorComunidad.GetActual(i1);
	      
	      this.jTableComunidads.getModel().setValueAt(new Integer(i.getIdComunidad()),i1, 0);
	      this.jTableComunidads.getModel().setValueAt(new String(i.getCalle()),i1,1);
	      this.jTableComunidads.getModel().setValueAt(new String(i.getEstado()),i1,2);
	      this.jTableComunidads.getModel().setValueAt(new Integer(i.getMaxRecibosPendientes()),i1,3);
	      
	    }
	    // selecciona la primera fila
	    if (jTableComunidads.getRowCount() > 0)
	    	jTableComunidads.addRowSelectionInterval(0, 0);
	}
	
	// No se utiliza en esta implementaci�n.
	@SuppressWarnings("unused")
	private boolean hayFilaSeleccionada()
	{
		int fila = jTableComunidads.getSelectedRow();
		if ((0<=fila)&(fila<=jTableComunidads.getRowCount()-1)) return true;
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
		this.setTitle("Ver Comunidades");
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
			jScrollPaneTabla.setViewportView(getJTableComunidads());
		}
		return jScrollPaneTabla;
	}

	/**
	 * This method initializes jTableComunidads	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTableComunidads() {
		if (jTableComunidads == null) {
			jTableComunidads = new JTable();
			jTableComunidads.setModel(MiModeloTabla);
			// evitar selecci�n multiple
			jTableComunidads.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    // cambiar el color de la cabecera de la tabla.
		    JTableHeader CabeceraTabla = jTableComunidads.getTableHeader();
		    CabeceraTabla.setBackground(new Color(216, 228, 248));
		    CabeceraTabla.setForeground(new Color(16, 101, 158));
		}
		return jTableComunidads;
	}
//////////////////////////////// Modelo de la tabla ///////////////////////
//	 Modelo para la tabla.........
	@SuppressWarnings("serial")
	static class ModeloTabla extends AbstractTableModel
	     // implements MouseListener si desea manejar eventos de rat�n sobre la tabla.
	{
	  private String[] columnNames = {"ID",
	                                  "Calle",
	                                  "Estado",
	                                  "N�mero m�ximo de recibos pendientes"
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
