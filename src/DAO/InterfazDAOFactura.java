package DAO;

import java.util.ArrayList;

import hibernate.Factura;
import excepciones.DAOExcepcion;

public interface InterfazDAOFactura {
	public void crearFactura(Factura clase) throws DAOExcepcion;
	public void borrarFactura(Factura clase) throws DAOExcepcion;
	public void actualizarFactura(Factura clase) throws DAOExcepcion;
	public ArrayList<Factura> encontrarFacturas() throws DAOExcepcion;
	public ArrayList<Factura> encontrarFacturasPorComunidad(int id_com) throws DAOExcepcion;
	public Factura encontrarFacturaPorId(int id) throws DAOExcepcion;
}
