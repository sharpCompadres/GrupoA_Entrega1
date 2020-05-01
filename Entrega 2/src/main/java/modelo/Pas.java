package modelo;

import static modelo.Usuario.Rol.PAS;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

/**
 * -------- SUBENTIDAD DE USUARIO: PAS --------
 *
 * - Atributos: · TIPO_SERVICIO
 *
 * - Método: · Constructor · Getters y Setters
 **/

@Entity
public class Pas extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String TIPO_SERVICIO;
	private Rol rol;

	public void setTipo_servicio(String TIPO_SERVICIO) {
		this.TIPO_SERVICIO = TIPO_SERVICIO;
	}

	public String getTipo_servicio() {
		return this.TIPO_SERVICIO;
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
	public Pas(String nombre, String contrasenia, String DNI) {
		super(nombre, contrasenia, DNI, PAS);
	}

	public Pas(String dNI, String eMAIL, String pASSWORD, String nOMBRE, String aPELLIDO1, String apellido2,
			Date f_NACMIMIENTO, String TIPO_SERVICIO) {

		super(dNI, eMAIL, pASSWORD, nOMBRE, aPELLIDO1, apellido2, f_NACMIMIENTO, PAS);
		setRol(PAS);
		setTipo_servicio(TIPO_SERVICIO);
	}

}
