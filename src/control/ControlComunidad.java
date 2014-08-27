package control;

import java.util.ArrayList;

import DAO.DAOComunidad;
import hibernate.Comunidad;
import hibernate.Inmueble;
import hibernate.Factura;
import hibernate.NotaInformativa;
import excepciones.DAOExcepcion;

public class ControlComunidad implements InterfazControlComunidad {

	protected DAOComunidad comDAO = null;
	protected ArrayList<Comunidad> ListaComunidades = new ArrayList<Comunidad>();

	static private ControlComunidad ref_controladorcomunidad = new ControlComunidad();

	public ControlComunidad() {}

	static public ControlComunidad getControladorComunidad(){
		return ref_controladorcomunidad;
	}

	@Override
	public void NuevaComunidad(Comunidad clase) throws DAOExcepcion {
		comDAO = new DAOComunidad();
		try {
			comDAO.crearComunidad(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarComunidad(Comunidad clase) throws DAOExcepcion {
		comDAO = new DAOComunidad();
		ControlInmueble coninm = ControlInmueble.getControladorInmueble();
		ControlFactura confac = ControlFactura.getControladorFactura();
		ControlNota connot = ControlNota.getControladorNota();
		ArrayList<Inmueble> inmuebles = coninm.EncontrarInmueblesPorComunidad(clase.getIdComunidad());
		ArrayList<Factura> facturas = confac.EncontrarFacturasPorComunidad(clase.getIdComunidad());
		ArrayList<NotaInformativa> notas = connot.EncontrarNotasPorComunidad(clase.getIdComunidad());
		try {
			if(inmuebles.size() != 0){
				for(int i = 0; i < inmuebles.size(); i++){
					coninm.BorrarInmueble((Inmueble) inmuebles.get(i));
				}
			}
			if(facturas.size() != 0){
				for(int i = 0; i < facturas.size(); i++){
					confac.BorrarFactura((Factura) facturas.get(i));
				}
			}
			if(notas.size() != 0){
				for(int i = 0; i < notas.size(); i++){
					connot.BorrarNota((NotaInformativa) notas.get(i));
				}
			}
			comDAO.borrarComunidad(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarComunidad(int id) throws DAOExcepcion {
		comDAO = new DAOComunidad();
		ControlInmueble coninm = ControlInmueble.getControladorInmueble();
		ControlFactura confac = ControlFactura.getControladorFactura();
		ControlNota connot = ControlNota.getControladorNota();
		ArrayList<Inmueble> inmuebles = coninm.EncontrarInmueblesPorComunidad(id);
		ArrayList<Factura> facturas = confac.EncontrarFacturasPorComunidad(id);
		ArrayList<NotaInformativa> notas = connot.EncontrarNotasPorComunidad(id);
		try {
			if(inmuebles.size() != 0){
				for(int i = 0; i < inmuebles.size(); i++){
					coninm.BorrarInmueble((Inmueble) inmuebles.get(i));
				}
			}
			if(facturas.size() != 0){
				for(int i = 0; i < facturas.size(); i++){
					confac.BorrarFactura((Factura) facturas.get(i));
				}
			}
			if(notas.size() != 0){
				for(int i = 0; i < notas.size(); i++){
					connot.BorrarNota((NotaInformativa) notas.get(i));
				}
			}
			comDAO.borrarComunidad(comDAO.encontrarComunidadPorId(id));
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void ActualizarComunidad(Comunidad clase) throws DAOExcepcion {
		comDAO = new DAOComunidad();
		try {
			comDAO.actualizarComunidad(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void GetComunidades() throws DAOExcepcion {
		comDAO = new DAOComunidad();
		try {
			ListaComunidades = comDAO.encontrarComunidades();
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int GetNumComunidades() {
		return ListaComunidades.size();
	}

	@Override
	public Comunidad GetActual(int i) {
		return (Comunidad) ListaComunidades.get(i);
	}

	@Override
	public Comunidad EncontrarComunidadPorId(int id) throws DAOExcepcion {
		Comunidad clase = null;
		comDAO = new DAOComunidad();
		try {
			clase = comDAO.encontrarComunidadPorId(id);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}

	@Override
	public Comunidad EncontrarComunidadPorRecibo(int id_rec) throws DAOExcepcion {
		Comunidad clase = null;
		comDAO = new DAOComunidad();
		try {
			clase = comDAO.encontrarComunidadPorRecibo(id_rec);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}

	public int comprobarEstadoComunidadIncorrecta (Comunidad clase){
		if(clase.getEstado().equalsIgnoreCase("cuadrado")){
			double porcentaje = 0.0;
			ControlInmueble controladorInmueble = ControlInmueble.getControladorInmueble();
			for(int i=0;i<controladorInmueble.GetNumInmuebles();i++){
				if(controladorInmueble.GetActual(i).getComunidad().getIdComunidad() == clase.getIdComunidad()){
					porcentaje +=controladorInmueble.GetActual(i).getPorcentaje();
				}
			}
			if(porcentaje == 100.0)
				return 0; // Porcentaje cuadrado
			else
				return 1; // Porcentaje descuadrado
		}else
			return 2; // El estado no está como "cuadrado".
	}
}