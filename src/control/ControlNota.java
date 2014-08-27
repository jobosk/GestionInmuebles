package control;

import java.util.ArrayList;

import DAO.DAONota;
import hibernate.NotaInformativa;
import hibernate.ReciboInmueble;
import excepciones.DAOExcepcion;

public class ControlNota implements InterfazControlNota {

	protected DAONota notaDAO = null;
	protected ArrayList<NotaInformativa> ListaNotas = new ArrayList<NotaInformativa>();

	static private ControlNota ref_controladorNota = new ControlNota();

	public ControlNota() {}

	static public ControlNota getControladorNota() {
		return ref_controladorNota;
	}

	@Override
	public void NuevaNota(NotaInformativa clase) throws DAOExcepcion {
		notaDAO = new DAONota();
		try {
			notaDAO.crearNota(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarNota(NotaInformativa clase) throws DAOExcepcion {
		notaDAO = new DAONota();
		ControlRecibo conrec = ControlRecibo.getControladorRecibo();
		ArrayList<ReciboInmueble> recibos = conrec.EncontrarRecibosPorNota(clase.getIdNota());
		try {
			if(recibos.size() != 0){
				for(int i = 0; i < recibos.size(); i++){
					conrec.BorrarRecibo((ReciboInmueble) recibos.get(i));
				}
			}
			notaDAO.borrarNota(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarNota(int id) throws DAOExcepcion {
		notaDAO = new DAONota();
		ControlRecibo conrec = ControlRecibo.getControladorRecibo();
		ArrayList<ReciboInmueble> recibos = conrec.EncontrarRecibosPorNota(id);
		try {
			if(recibos.size() != 0){
				for(int i = 0; i < recibos.size(); i++){
					conrec.BorrarRecibo((ReciboInmueble) recibos.get(i));
				}
			}
			notaDAO.borrarNota(notaDAO.encontrarNotaPorId(id));
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void ActualizarNota(NotaInformativa clase) throws DAOExcepcion {
		notaDAO = new DAONota();
		try {
			notaDAO.actualizarNota(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void GetNotas() throws DAOExcepcion {
		notaDAO = new DAONota();
		try {
			ListaNotas = notaDAO.encontrarNotas();
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int GetNumNotas() {
		return ListaNotas.size();
	}

	@Override
	public NotaInformativa GetActual(int i) {
		return (NotaInformativa) ListaNotas.get(i);
	}

	@Override
	public NotaInformativa EncontrarNotaPorId(int id) throws DAOExcepcion {
		NotaInformativa clase = null;
		notaDAO = new DAONota();
		try {
			clase = notaDAO.encontrarNotaPorId(id);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}
	
	@Override
	public NotaInformativa EncontrarNotaPorRecibo(int id_rec) throws DAOExcepcion {
		NotaInformativa clase = null;
		notaDAO = new DAONota();
		try {
			clase = notaDAO.encontrarNotaPorRecibo(id_rec);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
		return clase;
	}

	@Override
	public ArrayList<NotaInformativa> EncontrarNotasPorComunidad(int id_com) throws DAOExcepcion {
		ArrayList<NotaInformativa> clases = null;
		notaDAO = new DAONota();
		try {
			clases = notaDAO.encontrarNotasPorComunidad(id_com);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
		return clases;
	}
}