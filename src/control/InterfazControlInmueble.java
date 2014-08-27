package control;

import java.util.ArrayList;

import hibernate.Inmueble;
import excepciones.DAOExcepcion;

public interface InterfazControlInmueble {
	public void NuevoInmueble(Inmueble clase) throws DAOExcepcion;
	public void BorrarInmueble(Inmueble clase) throws DAOExcepcion;
	public void BorrarInmueble(int id) throws DAOExcepcion;
	public void ActualizarInmueble(Inmueble clase) throws DAOExcepcion;
	public void GetInmuebles() throws DAOExcepcion;
	public int GetNumInmuebles();
	public Inmueble GetActual(int i);
	public Inmueble EncontrarInmueblePorId(int id) throws DAOExcepcion;
	public Inmueble EncontrarInmueblePorRecibo(int id_rec) throws DAOExcepcion;
	public ArrayList<Inmueble> EncontrarInmueblesPorComunidad(int id_com) throws DAOExcepcion;
	public ArrayList<Inmueble> EncontrarInmueblesPorPropietario(int id_prop) throws DAOExcepcion;
}
