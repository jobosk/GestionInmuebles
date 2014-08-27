package DAO;

import java.util.ArrayList;

import hibernate.ReciboInmueble;
import excepciones.DAOExcepcion;

public interface InterfazDAORecibo {
	public void crearRecibo(ReciboInmueble clase) throws DAOExcepcion;
	public void borrarRecibo(ReciboInmueble clase) throws DAOExcepcion;
	public void actualizarRecibo(ReciboInmueble clase) throws DAOExcepcion;
	public ArrayList<ReciboInmueble> encontrarRecibos() throws DAOExcepcion;
	public ArrayList<ReciboInmueble> encontrarRecibosPorInmueble(int id_inm) throws DAOExcepcion;
	public ArrayList<ReciboInmueble> encontrarRecibosPorNota(int id_not) throws DAOExcepcion;
	public ReciboInmueble encontrarReciboPorId(int id) throws DAOExcepcion;
}
