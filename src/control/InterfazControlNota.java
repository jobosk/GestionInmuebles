package control;

import java.util.ArrayList;

import hibernate.NotaInformativa;
import excepciones.DAOExcepcion;

public interface InterfazControlNota {
	public void NuevaNota(NotaInformativa clase) throws DAOExcepcion;
	public void BorrarNota(NotaInformativa clase) throws DAOExcepcion;
	public void BorrarNota(int id) throws DAOExcepcion;
	public void ActualizarNota(NotaInformativa clase) throws DAOExcepcion;
	public void GetNotas() throws DAOExcepcion;
	public int GetNumNotas();
	public NotaInformativa GetActual(int i);
	public NotaInformativa EncontrarNotaPorId(int id) throws DAOExcepcion;
	public NotaInformativa EncontrarNotaPorRecibo(int id_rec) throws DAOExcepcion;
	public ArrayList<NotaInformativa> EncontrarNotasPorComunidad(int id_com) throws DAOExcepcion;
}