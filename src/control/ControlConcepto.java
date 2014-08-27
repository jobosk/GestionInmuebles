package control;

import java.util.ArrayList;

import DAO.DAOConcepto;
import hibernate.Concepto;
import excepciones.DAOExcepcion;

public class ControlConcepto implements InterfazControlConcepto {

	protected DAOConcepto conDAO = null;
	protected ArrayList<Concepto> ListaConceptos = new ArrayList<Concepto>();

	static private ControlConcepto ref_controladorConcepto = new ControlConcepto();

	public ControlConcepto() {}

	static public ControlConcepto getControladorConcepto() {
		return ref_controladorConcepto;
	}

	@Override
	public void NuevoConcepto(Concepto clase) throws DAOExcepcion {
		conDAO = new DAOConcepto();
		try {
			conDAO.crearConcepto(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarConcepto(Concepto clase) throws DAOExcepcion {
		conDAO = new DAOConcepto();
		try {
			conDAO.borrarConcepto(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarConcepto(int id) throws DAOExcepcion {
		conDAO = new DAOConcepto();
		try {
			conDAO.borrarConcepto(conDAO.encontrarConceptoPorId(id));
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void ActualizarConcepto(Concepto clase) throws DAOExcepcion {
		conDAO = new DAOConcepto();
		try {
			conDAO.actualizarConcepto(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void GetConceptos() throws DAOExcepcion {
		conDAO = new DAOConcepto();
		try {
			ListaConceptos = conDAO.encontrarConceptos();
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int GetNumConceptos() {
		return ListaConceptos.size();
	}

	@Override
	public Concepto GetActual(int i) {
		return (Concepto) ListaConceptos.get(i);
	}

	@Override
	public Concepto EncontrarConceptoPorId(int id) throws DAOExcepcion {
		Concepto clase = null;
		conDAO = new DAOConcepto();
		try {
			clase = conDAO.encontrarConceptoPorId(id);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}

	@Override
	public Concepto EncontrarConceptoPorLinea(int id_lin) throws DAOExcepcion {
		Concepto clase = null;
		conDAO = new DAOConcepto();
		try {
			clase = conDAO.encontrarConceptoPorLinea(id_lin);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}
}
