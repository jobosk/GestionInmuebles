package control;

import hibernate.Concepto;
import excepciones.DAOExcepcion;

public interface InterfazControlConcepto {
	public void NuevoConcepto(Concepto clase) throws DAOExcepcion;
	public void BorrarConcepto(Concepto clase) throws DAOExcepcion;
	public void BorrarConcepto(int id) throws DAOExcepcion;
	public void ActualizarConcepto(Concepto clase) throws DAOExcepcion;
	public void GetConceptos() throws DAOExcepcion;
	public int GetNumConceptos();
	public Concepto GetActual(int i);
	public Concepto EncontrarConceptoPorId(int id) throws DAOExcepcion;
	public Concepto EncontrarConceptoPorLinea(int id_lin) throws DAOExcepcion;
}
