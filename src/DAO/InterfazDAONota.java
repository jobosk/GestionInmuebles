package DAO;

import java.util.ArrayList;

import hibernate.NotaInformativa;
import excepciones.DAOExcepcion;

public interface InterfazDAONota {
	public void crearNota(NotaInformativa clase) throws DAOExcepcion;
	public void borrarNota(NotaInformativa clase) throws DAOExcepcion;
	public void actualizarNota(NotaInformativa clase) throws DAOExcepcion;
	public ArrayList<NotaInformativa> encontrarNotas() throws DAOExcepcion;
	public ArrayList<NotaInformativa> encontrarNotasPorComunidad(int id_com) throws DAOExcepcion;
	public NotaInformativa encontrarNotaPorId(int id) throws DAOExcepcion;
	public NotaInformativa encontrarNotaPorRecibo(int id_rec) throws DAOExcepcion;
}
