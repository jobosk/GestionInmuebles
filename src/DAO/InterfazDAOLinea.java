package DAO;

import java.util.ArrayList;

import hibernate.LineaFactura;
import excepciones.DAOExcepcion;

public interface InterfazDAOLinea {
	public void crearLinea(LineaFactura clase) throws DAOExcepcion;
	public void borrarLinea(LineaFactura clase) throws DAOExcepcion;
	public void actualizarLinea(LineaFactura clase) throws DAOExcepcion;
	public ArrayList<LineaFactura> encontrarLineas() throws DAOExcepcion;
	public ArrayList<LineaFactura> encontrarLineasPorFactura(int id_fac) throws DAOExcepcion;
	public ArrayList<LineaFactura> encontrarLineasPorConcepto(int id_con) throws DAOExcepcion;
	public LineaFactura encontrarLineaPorId(int id) throws DAOExcepcion;
}

