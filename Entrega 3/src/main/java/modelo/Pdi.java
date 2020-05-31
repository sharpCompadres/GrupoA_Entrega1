package modelo;

import static modelo.Usuario.Rol.PAS;
import static modelo.Usuario.Rol.UPDI;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * -------- SUBENTIDAD DE USUARIO: PDI --------
 *
 * - Atributos: · DEPARTAMENTO · DESPACHO
 *
 * - Método: 
 * · Constructor 
 * · Getters y Setters 
 * · String toString **/

@NamedQuery(name = "Pdi.todas", query = "select a from Pdi a")
@Entity
public class Pdi extends Usuario implements Serializable {

	@Override
	public String toString() {
		return "Pdi [DEPARTAMENTO=" + DEPARTAMENTO + ", DESPACHO=" + DESPACHO + ", rol=" + rol + ", getDNI()="
				+ getDNI() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getNombre()="
				+ getNombre() + ", getApellido1()=" + getApellido1() + ", getApellido2()=" + getApellido2()
				+ ", getF_nacimiento()=" + getF_nacimiento() + "]";
	}

	private static final long serialVersionUID = 1L;

	private String DEPARTAMENTO;
	private String DESPACHO;
	private Rol rol;

	@OneToMany(mappedBy = "profesor")
	private Set<Actividad> act;
	
	public Set<Actividad> getAct() {
		return act;
	}

	public void setAct(Set<Actividad> act) {
		this.act = act;
	}

	public Pdi() {
		super();
	}

	public String getDepartamento() {
		return DEPARTAMENTO;
	}

	public void setDepartamento(String dEPARTAMENTO) {
		DEPARTAMENTO = dEPARTAMENTO;
	}

	public String getDespacho() {
		return DESPACHO;
	}

	public void setDespacho(String dESPACHO) {
		DESPACHO = dESPACHO;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	// Constructor corto para pruebas
	public Pdi(String nombre, String contrasenia, Long DNI) {
		super(nombre, contrasenia, DNI, UPDI);
	}

	public Pdi(Long long1, String eMAIL, String pASSWORD, String nOMBRE, String aPELLIDO1, String apellido2,
			Date f_NACMIMIENTO, String Departamento, String Despacho) {

		super(long1, eMAIL, pASSWORD, nOMBRE, aPELLIDO1, apellido2, f_NACMIMIENTO, UPDI);
		setRol(UPDI);
		setDepartamento(Departamento);
		setDespacho(Despacho);
	}

	public Pdi(Usuario usu, String despacho, String departamento) {
		// TODO Auto-generated constructor stub
		super(usu.getDNI(), usu.getEmail(), usu.getPassword(), usu.getNombre(), usu.getApellido1(), usu.getApellido2(),
				usu.getF_nacimiento(), UPDI);
		setRol(UPDI);
		setDepartamento(departamento);
		setDespacho(despacho);
	}

	public Pdi(Usuario usu) {
		// TODO Auto-generated constructor stub
		super(usu.getDNI(), usu.getEmail(), usu.getPassword(), usu.getNombre(), usu.getApellido1(), usu.getApellido2(),
				usu.getF_nacimiento(), UPDI);
		setRol(UPDI);
	}
	
	public Pdi(Long dni, String contra) {
		setDNI(dni);
		setPassword(contra);
	}

	public Pdi(Long pdi_supervisor) {
		setDNI(pdi_supervisor);
	}

}
