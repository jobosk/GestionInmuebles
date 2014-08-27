package control;

import hibernate.Comunidad;
import excepciones.DAOExcepcion;

public interface InterfazControlComunidad {
	public void NuevaComunidad(Comunidad clase) throws DAOExcepcion;
	public void BorrarComunidad(Comunidad clase) throws DAOExcepcion;
	public void BorrarComunidad(int id) throws DAOExcepcion;
	public void ActualizarComunidad(Comunidad clase) throws DAOExcepcion;
	public void GetComunidades() throws DAOExcepcion;
	public int GetNumComunidades();
	public Comunidad GetActual(int i);
	public Comunidad EncontrarComunidadPorId(int id) throws DAOExcepcion;
	public Comunidad EncontrarComunidadPorRecibo(int id_rec) throws DAOExcepcion;
}
