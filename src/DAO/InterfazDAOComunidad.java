package DAO;

import java.util.ArrayList;

import hibernate.Comunidad;
import excepciones.DAOExcepcion;

public interface InterfazDAOComunidad {
	public void crearComunidad(Comunidad clase) throws DAOExcepcion;
	public void borrarComunidad(Comunidad clase) throws DAOExcepcion;
	public void actualizarComunidad(Comunidad clase) throws DAOExcepcion;
	public ArrayList<Comunidad> encontrarComunidades() throws DAOExcepcion;
	public Comunidad encontrarComunidadPorId(int id) throws DAOExcepcion;
	public Comunidad encontrarComunidadPorRecibo(int id_rec) throws DAOExcepcion;
}
