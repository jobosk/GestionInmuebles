// default package
// Generated 29-abr-2013 19:22:02 by Hibernate Tools 3.2.4.GA
package hibernate;


import java.util.HashSet;
import java.util.Set;

/**
 * Inmueble generated by hbm2java
 */
public class Inmueble implements java.io.Serializable {

	private int id;
	private Comunidad comunidad;
	private Propietario propietario;
	private String escalera;
	private String piso;
	private String puerta;
	private Double porcentaje;
	private Set reciboInmuebles = new HashSet(0);

	public Inmueble() {
	}

	public Inmueble(int id, Comunidad comunidad, Propietario propietario) {
		this.id = id;
		this.comunidad = comunidad;
		this.propietario = propietario;
	}

	public Inmueble(int id, Comunidad comunidad, Propietario propietario,
			String escalera, String piso, String puerta, Double porcentaje,
			Set reciboInmuebles) {
		this.id = id;
		this.comunidad = comunidad;
		this.propietario = propietario;
		this.escalera = escalera;
		this.piso = piso;
		this.puerta = puerta;
		this.porcentaje = porcentaje;
		this.reciboInmuebles = reciboInmuebles;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Comunidad getComunidad() {
		return this.comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public Propietario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public String getEscalera() {
		return this.escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getPiso() {
		return this.piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getPuerta() {
		return this.puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public Double getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Set getReciboInmuebles() {
		return this.reciboInmuebles;
	}

	public void setReciboInmuebles(Set reciboInmuebles) {
		this.reciboInmuebles = reciboInmuebles;
	}

}
