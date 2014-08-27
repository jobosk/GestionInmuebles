package control;

import java.util.ArrayList;

import DAO.DAOFactura;
import hibernate.Factura;
import hibernate.LineaFactura;
import excepciones.DAOExcepcion;

public class ControlFactura implements InterfazControlFactura {

	protected DAOFactura facDAO = null;
	protected ArrayList<Factura> ListaFacturas = new ArrayList<Factura>();

	static private ControlFactura ref_controladorFactura = new ControlFactura();

	public ControlFactura() {}

	static public ControlFactura getControladorFactura() {
		return ref_controladorFactura;
	}

	@Override
	public void NuevaFactura(Factura clase) throws DAOExcepcion {
		facDAO = new DAOFactura();
		try {
			facDAO.crearFactura(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarFactura(Factura clase) throws DAOExcepcion {
		facDAO = new DAOFactura();
		ControlLinea conlin = ControlLinea.getControladorLinea();
		ArrayList<LineaFactura> lineas = conlin.EncontrarLineasPorFactura(clase.getNumFactura());
		try {
			if(lineas.size() != 0){
				for(int i = 0; i < lineas.size(); i++){
					conlin.BorrarLinea((LineaFactura) lineas.get(i));
				}
			}
			facDAO.borrarFactura(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void BorrarFactura(int id) throws DAOExcepcion {
		facDAO = new DAOFactura();
		ControlLinea conlin = ControlLinea.getControladorLinea();
		ArrayList<LineaFactura> lineas = conlin.EncontrarLineasPorFactura(id);
		try {
			if(lineas.size() != 0){
				for(int i = 0; i < lineas.size(); i++){
					conlin.BorrarLinea((LineaFactura) lineas.get(i));
				}
			}				
			facDAO.borrarFactura(facDAO.encontrarFacturaPorId(id));
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void ActualizarFactura(Factura clase) throws DAOExcepcion {
		facDAO = new DAOFactura();
		try {
			facDAO.actualizarFactura(clase);
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void GetFacturas() throws DAOExcepcion {
		facDAO = new DAOFactura();
		try {
			ListaFacturas = facDAO.encontrarFacturas();
		} catch (DAOExcepcion e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int GetNumFacturas() {
		return ListaFacturas.size();
	}

	@Override
	public Factura GetActual(int i) {
		return (Factura) ListaFacturas.get(i);
	}

	@Override
	public Factura EncontrarFacturaPorId(int id) throws DAOExcepcion {
		Factura clase = null;
		facDAO = new DAOFactura();
		try {
			clase = facDAO.encontrarFacturaPorId(id);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clase;
	}

	@Override
	public ArrayList<Factura> EncontrarFacturasPorComunidad(int id_com) throws DAOExcepcion {
		facDAO = new DAOFactura();
		ArrayList<Factura> clases = null;
		try {
			clases = facDAO.encontrarFacturasPorComunidad(id_com);
		} catch (DAOExcepcion e) {
			throw e;
		}
		return clases;
	}

	@Override
	public Double CalcularImporteFactura(Factura clase) throws DAOExcepcion {
		ControlLinea conlin = ControlLinea.getControladorLinea();
		ArrayList<LineaFactura> lineas = conlin.EncontrarLineasPorFactura(clase.getNumFactura());
		double importe = 0.0;
		for(int i=0; i<lineas.size();i++){
			importe += lineas.get(i).getImporteLinea();
		}
		return importe;
	}
}