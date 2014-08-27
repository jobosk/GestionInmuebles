package control;

import java.util.ArrayList;

import hibernate.LineaFactura;
import excepciones.DAOExcepcion;

public interface InterfazControlLinea {
	public void NuevaLinea(LineaFactura clase) throws DAOExcepcion;
	public void BorrarLinea(LineaFactura clase) throws DAOExcepcion;
	public void BorrarLinea(int id) throws DAOExcepcion;
	public void ActualizarLinea(LineaFactura clase) throws DAOExcepcion;
	public void GetLineas() throws DAOExcepcion;
	public int GetNumLineas();
	public LineaFactura GetActual(int i);
	public LineaFactura EncontrarLineaPorId(int id) throws DAOExcepcion;
	public ArrayList<LineaFactura> EncontrarLineasPorFactura(int id_fac) throws DAOExcepcion;
	public ArrayList<LineaFactura> EncontrarLineasPorConcepto(int id_con) throws DAOExcepcion;
}