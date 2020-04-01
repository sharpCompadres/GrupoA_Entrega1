package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Usuario
 *
 */

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 8)
	private Integer DNI;
	@Column(nullable = false)
	private String EMAIL;
	@Column(length = 8, nullable = false)
	private String PASSWORD;
	@Column(nullable = false)
	private String NOMBRE;
	@GeneratedValue
	@Column(nullable = false)
	private String APELLIDOS;
	@Temporal(TemporalType.DATE)
	private Date F_NACMIMIENTO;
	@OneToMany(mappedBy = "usuario")
	Set<EstadoActividad> ESTADO;

	@Override
	public String toString() {
		return "Usuario [DNI=" + DNI + ", EMAIL=" + EMAIL + ", PASSWORD=" + PASSWORD + ", NOMBRE=" + NOMBRE
				+ ", APELLIDOS=" + APELLIDOS + ", F_NACMIMIENTO=" + F_NACMIMIENTO + ", ESTADO=" + ESTADO + "]";
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

	public Integer getDNI() {
		return DNI;
	}

	public void setDNI(Integer dNI) {
		DNI = dNI;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public String getAPELLIDOS() {
		return APELLIDOS;
	}

	public void setAPELLIDOS(String aPELLIDOS) {
		APELLIDOS = aPELLIDOS;
	}

	public Date getF_NACMIMIENTO() {
		return F_NACMIMIENTO;
	}

	public void setF_NACMIMIENTO(Date f_NACMIMIENTO) {
		F_NACMIMIENTO = f_NACMIMIENTO;
	}

	public Set<EstadoActividad> getESTADO() {
		return ESTADO;
	}

	public void setESTADO(Set<EstadoActividad> eSTADO) {
		ESTADO = eSTADO;
	}

	public Usuario() {
		super();
	}

}
