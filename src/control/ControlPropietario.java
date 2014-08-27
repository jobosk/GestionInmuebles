package control;

import java.util.ArrayList;

import DAO.DAOPropietario;
import hibernate.Propietario;
import excepciones.DAOExcepcion;

public class ControlPropietario implements InterfazControlPropietario {

	protected DAOPropietario propDAO = null;
	protected ArrayList<Propietario> ListaPropietarios = new ArrayList<Propietario>();

	static private ControlPropietario ref_controladorpropietario = new ControlPropietario();

	public ControlPropietario() {}

	static public ControlPropietario getControladorPropietario(){
		return ref_controladorpropietario;
	}

	@Override
	public void NuevoPropietario(Propietario clase) throws DAOExcepcion {
		propDAO = new DAOPropietario();
		try {
			propDAO.crearPropietario(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarPropietario(Propietario clase) throws DAOExcepcion {
		propDAO = new DAOPropietario();
		try {
			propDAO.borrarPropietario(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarPropietario(int id) throws DAOExcepcion {
		propDAO = new DAOPropietario();
		try {
			propDAO.borrarPropietario(propDAO.encontrarPropietarioPorId(id));
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void ActualizarPropietario(Propietario clase) throws DAOExcepcion {
		propDAO = new DAOPropietario();
		try {
			propDAO.actualizarPropietario(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void GetPropietarios() throws DAOExcepcion {
		propDAO = new DAOPropietario();
		try {
			ListaPropietarios = propDAO.encontrarPropietarios();
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int GetNumPropietarios() {
		return ListaPropietarios.size();
	}

	@Override
	public Propietario GetActual(int i) {
		return (Propietario) ListaPropietarios.get(i);
	}

	@Override
	public Propietario EncontrarPropietarioPorId(int id) throws DAOExcepcion {
		Propietario clase = null;
		propDAO = new DAOPropietario();
		try {
			clase = propDAO.encontrarPropietarioPorId(id);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}
}


