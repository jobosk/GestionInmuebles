package DAO;

import java.util.ArrayList;

import hibernate.Inmueble;
import excepciones.DAOExcepcion;

public interface InterfazDAOInmueble {
	public void crearInmueble(Inmueble clase) throws DAOExcepcion;
	public void borrarInmueble(Inmueble clase) throws DAOExcepcion;
	public void actualizarInmueble(Inmueble clase) throws  DAOExcepcion;
	public ArrayList<Inmueble> encontrarInmuebles() throws DAOExcepcion;
	public ArrayList<Inmueble> encontrarInmueblesPorComunidad(int id_com) throws DAOExcepcion;
	public ArrayList<Inmueble> encontrarInmueblesPorPropietario(int id_prop) throws DAOExcepcion;
	public Inmueble encontrarInmueblePorId(int id) throws DAOExcepcion;
	public Inmueble encontrarInmueblePorRecibo(int id_rec) throws DAOExcepcion;
}
