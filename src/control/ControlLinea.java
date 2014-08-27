package control;

import java.util.ArrayList;

import DAO.DAOLinea;
import hibernate.LineaFactura;
import excepciones.DAOExcepcion;

public class ControlLinea implements InterfazControlLinea {

	protected DAOLinea linDAO = null;
	protected ArrayList<LineaFactura> ListaLineas = new ArrayList<LineaFactura>();

	static private ControlLinea ref_controladorLinea = new ControlLinea();

	public ControlLinea() {}

	static public ControlLinea getControladorLinea() {
		return ref_controladorLinea;
	}

	@Override
	public void NuevaLinea(LineaFactura clase) throws DAOExcepcion {
		linDAO = new DAOLinea();
		try {
			linDAO.crearLinea(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarLinea(LineaFactura clase) throws DAOExcepcion {
		linDAO = new DAOLinea();
		try {
			linDAO.borrarLinea(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarLinea(int id) throws DAOExcepcion {
		linDAO = new DAOLinea();
		try {
			linDAO.borrarLinea(linDAO.encontrarLineaPorId(id));
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void ActualizarLinea(LineaFactura clase) throws DAOExcepcion {
		linDAO = new DAOLinea();
		try {
			linDAO.actualizarLinea(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void GetLineas() throws DAOExcepcion {
		linDAO = new DAOLinea();
		try {
			ListaLineas = linDAO.encontrarLineas();
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int GetNumLineas() {
		return ListaLineas.size();
	}

	@Override
	public LineaFactura GetActual(int i) {
		return (LineaFactura) ListaLineas.get(i);
	}

	@Override
	public LineaFactura EncontrarLineaPorId(int id) throws DAOExcepcion {
		LineaFactura clase = null;
		linDAO = new DAOLinea();
		try {
			clase = linDAO.encontrarLineaPorId(id);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}

	@Override
	public ArrayList<LineaFactura> EncontrarLineasPorFactura(int id_fac) throws DAOExcepcion {
		ArrayList<LineaFactura> clases = null;
		linDAO = new DAOLinea();
		try {
			clases = linDAO.encontrarLineasPorFactura(id_fac);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clases;
	}

	@Override
	public ArrayList<LineaFactura> EncontrarLineasPorConcepto(int id_con) throws DAOExcepcion {
		ArrayList<LineaFactura> clases = null;
		linDAO = new DAOLinea();
		try {
			clases = linDAO.encontrarLineasPorConcepto(id_con);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clases;
	}	
}