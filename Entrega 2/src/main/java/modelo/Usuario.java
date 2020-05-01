package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * -------- ENTIDAD USUARIO --------
 *
 * - Atributos: · DNI (PK/NOT NULL) · EMAIL (NOT NULL) · PASSWORD (NOT NULL) ·
 * NOMBRE (NOT NULL) · APELLIDOS (NOT NULL) · F_NACIMIENTO (NOT NULL)
 *
 * - Métodos · Constructor · Getters y Setters de los atributos anteriormente
 * listados · HashCode y Equals · Método toString
 *
 **/

@Entity
public class Usuario implements Serializable {

	public enum Rol {
		ADMINISTRADOR, USUARIO, PAS, UPDI
	};

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 8)
	private String DNI;
	@Column(nullable = false)
	private String email;
	@Column(length = 8, nullable = false)
	private String password;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido1;
	private String apellido2;
	@Temporal(TemporalType.DATE)
	private Date f_nacimiento;
	@OneToMany(mappedBy = "usuario")
	Set<EstadoActividad> ESTADO;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Rol rol;

	@Override
	public String toString() {
		return "Usuario [DNI=" + DNI + ", EMAIL=" + email + ", PASSWORD=" + password + ", NOMBRE=" + nombre
				+ ", APELLIDOS=" + apellido1 + " " + apellido2 + ", F_NACMIMIENTO=" + f_nacimiento + "]"; // ", ESTADO="
		// + ESTADO + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		return true;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dni) {
		DNI = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Date getF_nacimiento() {
		return this.f_nacimiento;
	}

	public void setF_nacimiento(Date f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	public Set<EstadoActividad> getEstado() {
		return ESTADO;
	}

	public void setEstado(Set<EstadoActividad> eSTADO) {
		ESTADO = eSTADO;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario() {

	}

	// Constructor corto e incompleto para pruebas
	public Usuario(String nombre, String contrasenia, String dni, Rol rol) {
		setDNI(dni);
		setPassword(contrasenia);
		setNombre(nombre);
		setRol(rol);
	}

	public Usuario(String dNI, String eMAIL, String pASSWORD, String nOMBRE, String aPELLIDO1, String apellido2,
			Date f_NACMIMIENTO, Rol rol) {
		setDNI(dNI);
		setEmail(eMAIL);
		setPassword(pASSWORD);
		setNombre(nOMBRE);
		setApellido1(aPELLIDO1);
		setApellido2(apellido2);
		setF_nacimiento(f_NACMIMIENTO);
		setRol(rol);
	}

}
