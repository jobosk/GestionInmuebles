package control;

import java.util.ArrayList;

import hibernate.Factura;
import excepciones.DAOExcepcion;

public interface InterfazControlFactura {
	public void NuevaFactura(Factura clase) throws DAOExcepcion;
	public void BorrarFactura(Factura clase) throws DAOExcepcion;
	public void BorrarFactura(int id) throws DAOExcepcion;
	public void ActualizarFactura(Factura clase) throws DAOExcepcion;
	public void GetFacturas() throws DAOExcepcion;
	public int GetNumFacturas();
	public Factura GetActual(int i);
	public Factura EncontrarFacturaPorId(int id) throws DAOExcepcion;
	public ArrayList<Factura> EncontrarFacturasPorComunidad(int id_com) throws DAOExcepcion;
	public Double CalcularImporteFactura(Factura clase) throws DAOExcepcion;
}
