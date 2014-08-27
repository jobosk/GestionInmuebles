package control;

import java.util.ArrayList;

import DAO.DAOInmueble;
import hibernate.Inmueble;
import hibernate.ReciboInmueble;
import excepciones.DAOExcepcion;

public class ControlInmueble implements InterfazControlInmueble {

	protected DAOInmueble inmDAO = null;
	protected ArrayList<Inmueble> ListaInmuebles = new ArrayList<Inmueble>();

	static private ControlInmueble ref_controladorinmueble = new ControlInmueble();

	public ControlInmueble() {}

	static public ControlInmueble getControladorInmueble() {
		return ref_controladorinmueble;
	}

	@Override
	public void NuevoInmueble(Inmueble clase) throws DAOExcepcion {
		inmDAO = new DAOInmueble();
		try {
			inmDAO.crearInmueble(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarInmueble(Inmueble clase) throws DAOExcepcion {
		inmDAO = new DAOInmueble();
		ControlRecibo conrec = ControlRecibo.getControladorRecibo();
		ArrayList<ReciboInmueble> recibos = conrec.EncontrarRecibosPorInmueble(clase.getId());
		try {
			if(recibos.size() != 0){
				for(int i = 0; i < recibos.size(); i++){
					conrec.BorrarRecibo((ReciboInmueble) recibos.get(i));
				}
			}
			inmDAO.borrarInmueble(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarInmueble(int id) throws DAOExcepcion {
		inmDAO = new DAOInmueble();
		ControlRecibo conrec = ControlRecibo.getControladorRecibo();
		ArrayList<ReciboInmueble> recibos = conrec.EncontrarRecibosPorInmueble(id);
		try {
			if(recibos.size() != 0){
				for(int i = 0; i < recibos.size(); i++){
					conrec.BorrarRecibo((ReciboInmueble) recibos.get(i));
				}
			}
			inmDAO.borrarInmueble(inmDAO.encontrarInmueblePorId(id));
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void ActualizarInmueble(Inmueble clase) throws DAOExcepcion {
		inmDAO = new DAOInmueble();
		try {
			inmDAO.actualizarInmueble(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void GetInmuebles() throws DAOExcepcion {
		inmDAO = new DAOInmueble();
		try {
			ListaInmuebles = inmDAO.encontrarInmuebles();
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int GetNumInmuebles() {
		return ListaInmuebles.size();
	}

	@Override
	public Inmueble GetActual(int i) {
		return (Inmueble) ListaInmuebles.get(i);
	}

	@Override
	public Inmueble EncontrarInmueblePorId(int id) throws DAOExcepcion {
		Inmueble clase = null;
		inmDAO = new DAOInmueble();
		try {
			clase = inmDAO.encontrarInmueblePorId(id);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}
	
	@Override
	public Inmueble EncontrarInmueblePorRecibo(int id_rec) throws DAOExcepcion {
		Inmueble clase = null;
		inmDAO = new DAOInmueble();
		try {
			clase = inmDAO.encontrarInmueblePorRecibo(id_rec);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}

	@Override
	public ArrayList<Inmueble> EncontrarInmueblesPorComunidad(int id_com) throws DAOExcepcion {
		ArrayList<Inmueble> clases = null;
		inmDAO = new DAOInmueble();
		try {
			clases = inmDAO.encontrarInmueblesPorComunidad(id_com);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clases;
	}

	@Override
	public ArrayList<Inmueble> EncontrarInmueblesPorPropietario(int id_prop) throws DAOExcepcion {
		ArrayList<Inmueble> clases = null;
		inmDAO = new DAOInmueble();
		try {
			clases = inmDAO.encontrarInmueblesPorPropietario(id_prop);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clases;
	}
}

