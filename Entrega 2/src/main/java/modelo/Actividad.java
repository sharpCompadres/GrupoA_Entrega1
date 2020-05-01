package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * -------- ENTIDAD ACTIVIDAD --------
 *
 * - Atributos: · ID_ACTIVIDAD (PK/NOT NULL) · F_INICIO (NOT NULL) · F_FIN (NOT
 * NULL) · DESCRIPCION (NOT NULL) · AMBITO (NOT NULL)
 *
 * - Métodos: · Constructor · Getters y Setters de los atributos anteriormente
 * listados · HashCode y Equals · Método toString
 *
 **/

@Entity
public class Actividad implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String ID_ACTIVIDAD;
	@Column(nullable = false)
	private String NOMBRE;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date F_INICIO;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date F_FIN;
	@Column(nullable = false)
	private String DESCRIPCION;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoActividad AMBITO;
	// @ManyToOne
	// private ONG ongs;
	@OneToMany(mappedBy = "actividad")
	Set<EstadoActividad> ESTADO;

	public Actividad() {

	}

	public Actividad(String nombre, String descr, String ID, TipoActividad ambito) {
		setID_ACTIVIDAD(ID);
		setDESCRIPCION(descr);
		setAMBITO(ambito);
		setNOMBRE(nombre);
	}

	public Actividad(String nombre, String ID, Date f_inicio, Date f_fin, String descr, TipoActividad ambito) {
		setID_ACTIVIDAD(ID);
		setF_INICIO(f_inicio);
		setF_FIN(f_fin);
		setDESCRIPCION(descr);
		setAMBITO(ambito);
		setNOMBRE(nombre);
	}

	@Override
	public String toString() {
		return "Actividad [ID_ACTIVIDAD=" + ID_ACTIVIDAD + ", F_INICIO=" + F_INICIO + ", F_FIN=" + F_FIN
				+ ", DESCRIPCION=" + DESCRIPCION + ", AMBITO=" + AMBITO + // ", ongs=" + ongs + ",
				"ESTADO=" + ESTADO + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID_ACTIVIDAD == null) ? 0 : ID_ACTIVIDAD.hashCode());
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
		Actividad other = (Actividad) obj;
		if (ID_ACTIVIDAD == null) {
			if (other.ID_ACTIVIDAD != null)
				return false;
		} else if (!ID_ACTIVIDAD.equals(other.ID_ACTIVIDAD))
			return false;
		return true;
	}

	public String getID_ACTIVIDAD() {
		return ID_ACTIVIDAD;
	}

	public void setID_ACTIVIDAD(String iD_ACTIVIDAD) {
		ID_ACTIVIDAD = iD_ACTIVIDAD;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public Date getF_INICIO() {
		return F_INICIO;
	}

	public void setF_INICIO(Date f_INICIO) {
		F_INICIO = f_INICIO;
	}

	public Date getF_FIN() {
		return F_FIN;
	}

	public void setF_FIN(Date f_FIN) {
		F_FIN = f_FIN;
	}

	public String getDESCRIPCION() {
		return DESCRIPCION;
	}

	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}

	public TipoActividad getAMBITO() {
		return AMBITO;
	}

	public void setAMBITO(TipoActividad aMBITO) {
		AMBITO = aMBITO;
	}

	public Set<EstadoActividad> getESTADO() {
		return ESTADO;
	}

	public void setESTADO(Set<EstadoActividad> eSTADO) {
		ESTADO = eSTADO;
	}

}
