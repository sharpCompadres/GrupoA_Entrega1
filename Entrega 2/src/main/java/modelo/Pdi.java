package modelo;

import static modelo.Usuario.Rol.UPDI;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

/**
 * -------- SUBENTIDAD DE USUARIO: PDI --------
 *
 * - Atributos: · DEPARTAMENTO · DESPACHO
 *
 * - Método: · Constructor · Getters y Setters
 **/

@Entity
public class Pdi extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String DEPARTAMENTO;
	private String DESPACHO;
	private Rol rol;

	// @OneToMany
	// private List<Informe> informes = new ArrayList<>();

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
	public Pdi(String nombre, String contrasenia, String DNI) {
		super(nombre, contrasenia, DNI, UPDI);
	}

	public Pdi(String dNI, String eMAIL, String pASSWORD, String nOMBRE, String aPELLIDO1, String apellido2,
			Date f_NACMIMIENTO, String Departamento, String Despacho) {

		super(dNI, eMAIL, pASSWORD, nOMBRE, aPELLIDO1, apellido2, f_NACMIMIENTO, UPDI);
		setRol(UPDI);
		setDepartamento(Departamento);
		setDespacho(Despacho);
	}

}
