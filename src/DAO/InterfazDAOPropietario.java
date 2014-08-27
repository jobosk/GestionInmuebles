package DAO;

import java.util.ArrayList;

import hibernate.Propietario;
import excepciones.DAOExcepcion;

public interface InterfazDAOPropietario {
	public void crearPropietario(Propietario clase) throws DAOExcepcion;
	public void borrarPropietario(Propietario clase) throws DAOExcepcion;
	public void actualizarPropietario(Propietario clase) throws DAOExcepcion;
	public ArrayList<Propietario> encontrarPropietarios() throws DAOExcepcion; 
	public Propietario encontrarPropietarioPorId(int id) throws DAOExcepcion;
}
