package DAO;

import java.util.ArrayList;

import hibernate.Concepto;
import excepciones.DAOExcepcion;

public interface InterfazDAOConcepto {
	public void crearConcepto(Concepto clase) throws DAOExcepcion;
	public void borrarConcepto(Concepto clase) throws DAOExcepcion;
	public void actualizarConcepto(Concepto clase) throws DAOExcepcion;
	public ArrayList<Concepto> encontrarConceptos() throws DAOExcepcion;
	public Concepto encontrarConceptoPorId(int id) throws DAOExcepcion;
	public Concepto encontrarConceptoPorLinea(int id_lin) throws DAOExcepcion;
}
