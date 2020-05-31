package modelo;

import static modelo.Usuario.Rol.PAS;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

/**
 * -------- SUBENTIDAD DE USUARIO: PAS --------
 *
 * - Atributos: 
 * · TIPO_SERVICIO
 *
 * - Método: 
 * · Constructor 
 * · Getters y Setters
 * · String toString
 **/

@Entity
public class Pas extends Usuario implements Serializable {

	@Override
	public String toString() {
		return "Pas [TIPO_SERVICIO=" + TIPO_SERVICIO + ", rol=" + rol + ", getDNI()=" + getDNI() + ", getEmail()="
				+ getEmail() + ", getPassword()=" + getPassword() + ", getNombre()=" + getNombre() + ", getApellido1()="
				+ getApellido1() + ", getApellido2()=" + getApellido2() + ", getF_nacimiento()=" + getF_nacimiento()
				+ ", getEstado()=" + getEstado() + ", getESTADO()=" + getESTADO() + "]";
	}

	private static final long serialVersionUID = 1L;

	private String TIPO_SERVICIO;
	private Rol rol;

	public String getTIPO_SERVICIO() {
		return TIPO_SERVICIO;
	}

	public void setTIPO_SERVICIO(String tIPO_SERVICIO) {
		TIPO_SERVICIO = tIPO_SERVICIO;
	}

	public Pas() {
		super();
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	// Constructor corto para pruebas
	public Pas(String nombre, String contrasenia, Long DNI) {
		super(nombre, contrasenia, DNI, PAS);
	}

	public Pas(Long long1, String eMAIL, String pASSWORD, String nOMBRE, String aPELLIDO1, String apellido2,
			Date f_NACMIMIENTO, String TIPO_SERVICIO) {

		super(long1, eMAIL, pASSWORD, nOMBRE, aPELLIDO1, apellido2, f_NACMIMIENTO, PAS);
		setRol(PAS);
		setTIPO_SERVICIO(TIPO_SERVICIO);

	}

	public Pas(Usuario usu, String t_servicio) {
		// TODO Auto-generated constructor stub
		super(usu.getDNI(), usu.getEmail(), usu.getPassword(), usu.getNombre(), usu.getApellido1(), usu.getApellido2(),
				usu.getF_nacimiento(), PAS);
		setRol(PAS);
		setTIPO_SERVICIO(t_servicio);
	}

	public Pas(Usuario usu) {
		// TODO Auto-generated constructor stub
		super(usu.getDNI(), usu.getEmail(), usu.getPassword(), usu.getNombre(), usu.getApellido1(), usu.getApellido2(),
				usu.getF_nacimiento(), PAS);
		setRol(PAS);
	}
	
	public Pas(Long dni, String contra) {
		setDNI(dni);
		setPassword(contra);
	}
}
