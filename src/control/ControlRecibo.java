package control;

import java.util.ArrayList;

import DAO.DAORecibo;
import hibernate.ReciboInmueble;
import excepciones.DAOExcepcion;

public class ControlRecibo implements InterfazControlRecibo {

	protected DAORecibo recDAO = null;
	protected ArrayList<ReciboInmueble> ListaRecibos = new ArrayList<ReciboInmueble>();

	static private ControlRecibo ref_controladorRecibo = new ControlRecibo();

	public ControlRecibo() {}

	static public ControlRecibo getControladorRecibo() {
		return ref_controladorRecibo;
	}

	@Override
	public void NuevoRecibo(ReciboInmueble clase) throws DAOExcepcion {
		this.recDAO = new DAORecibo();
		try {
			recDAO.crearRecibo(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarRecibo(ReciboInmueble clase) throws DAOExcepcion {
		recDAO = new DAORecibo();
		try {
			recDAO.borrarRecibo(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarRecibo(int id) throws DAOExcepcion {
		recDAO = new DAORecibo();
		try {
			recDAO.borrarRecibo(recDAO.encontrarReciboPorId(id));
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void ActualizarRecibo(ReciboInmueble clase) throws DAOExcepcion {
		recDAO = new DAORecibo();
		try {
			recDAO.actualizarRecibo(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void GetRecibos() throws DAOExcepcion {
		recDAO = new DAORecibo();
		try {
			ListaRecibos = recDAO.encontrarRecibos();
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int GetNumRecibos() {
		return ListaRecibos.size();
	}

	@Override
	public ReciboInmueble GetActual(int i) {
		return (ReciboInmueble) ListaRecibos.get(i);
	}

	@Override
	public ReciboInmueble EncontrarReciboPorId(int id) throws DAOExcepcion {
		ReciboInmueble clase = null;
		recDAO = new DAORecibo();
		try {
			clase = recDAO.encontrarReciboPorId(id);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}

	@Override
	public ArrayList<ReciboInmueble> EncontrarRecibosPorInmueble(int id_inm) throws DAOExcepcion {
		ArrayList<ReciboInmueble> clases = null;
		recDAO = new DAORecibo();
		try {
			clases = recDAO.encontrarRecibosPorInmueble(id_inm);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clases;
	}
	
	@Override
	public ArrayList<ReciboInmueble> EncontrarRecibosPorNota(int id_not) throws DAOExcepcion {
		ArrayList<ReciboInmueble> clases = null;
		recDAO = new DAORecibo();
		try {
			clases = recDAO.encontrarRecibosPorNota(id_not);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clases;
	}
}