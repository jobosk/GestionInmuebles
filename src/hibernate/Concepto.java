// default package
// Generated 29-abr-2013 19:22:02 by Hibernate Tools 3.2.4.GA
package hibernate;


import java.util.HashSet;
import java.util.Set;

/**
 * Concepto generated by hbm2java
 */
public class Concepto implements java.io.Serializable {

	private int idConcepto;
	private String claveConcepto;
	private String descripcion;
	private Set lineaFacturas = new HashSet(0);

	public Concepto() {
	}

	public Concepto(int idConcepto) {
		this.idConcepto = idConcepto;
	}

	public Concepto(int idConcepto, String claveConcepto, String descripcion,
			Set lineaFacturas) {
		this.idConcepto = idConcepto;
		this.claveConcepto = claveConcepto;
		this.descripcion = descripcion;
		this.lineaFacturas = lineaFacturas;
	}

	public int getIdConcepto() {
		return this.idConcepto;
	}

	public void setIdConcepto(int idConcepto) {
		this.idConcepto = idConcepto;
	}

	public String getClaveConcepto() {
		return this.claveConcepto;
	}

	public void setClaveConcepto(String claveConcepto) {
		this.claveConcepto = claveConcepto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getLineaFacturas() {
		return this.lineaFacturas;
	}

	public void setLineaFacturas(Set lineaFacturas) {
		this.lineaFacturas = lineaFacturas;
	}
	
	public String toString(){
	      return new String(claveConcepto + ": "+descripcion);
	  }

}