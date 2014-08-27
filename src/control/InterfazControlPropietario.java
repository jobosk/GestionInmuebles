package control;

import hibernate.Propietario;
import excepciones.DAOExcepcion;

public interface InterfazControlPropietario {
	public void NuevoPropietario(Propietario clase) throws DAOExcepcion;
	public void BorrarPropietario(Propietario clase) throws DAOExcepcion;
	public void BorrarPropietario(int id) throws DAOExcepcion;
	public void ActualizarPropietario(Propietario clase) throws DAOExcepcion;
	public void GetPropietarios() throws DAOExcepcion;
	public int GetNumPropietarios();
	public Propietario GetActual(int i);
	public Propietario EncontrarPropietarioPorId(int id) throws DAOExcepcion;
}
