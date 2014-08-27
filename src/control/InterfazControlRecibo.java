package control;

import java.util.ArrayList;

import hibernate.ReciboInmueble;
import excepciones.DAOExcepcion;

public interface InterfazControlRecibo {
	public void NuevoRecibo(ReciboInmueble clase) throws DAOExcepcion;
	public void BorrarRecibo(ReciboInmueble clase) throws DAOExcepcion;
	public void BorrarRecibo(int id) throws DAOExcepcion;
	public void ActualizarRecibo(ReciboInmueble clase) throws DAOExcepcion;
	public void GetRecibos() throws DAOExcepcion;
	public int GetNumRecibos();
	public ReciboInmueble GetActual(int i);
	public ReciboInmueble EncontrarReciboPorId(int id) throws DAOExcepcion;
	public ArrayList<ReciboInmueble> EncontrarRecibosPorInmueble(int id_inm) throws DAOExcepcion;
	public ArrayList<ReciboInmueble> EncontrarRecibosPorNota(int id_not) throws DAOExcepcion;
}