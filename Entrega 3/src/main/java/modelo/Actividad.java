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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * -------- ENTIDAD ACTIVIDAD --------
 *
 * - Atributos: · ID_ACTIVIDAD (PK/NOT NULL) · F_INICIO (NOT NULL) · F_FIN (NOT
 * NULL) · DESCRIPCION (NOT NULL) ·AMBITO (NOT NULL)
 *
 * - Métodos: · Constructores · Getters y Setters de los atributos anteriormente
 * listados · HashCode y Equals · Método toString
 *
 **/

@NamedQuery(name = "Actividad.todas", query = "select a from Actividad a")
@Entity
public class Actividad implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@TableGenerator(name = "TABLE_GEN", table = "T_GENERATOR", pkColumnName = "GEN_KEY", pkColumnValue = "TEST", valueColumnName = "GEN_VALUE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long ID_ACTIVIDAD;
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

	@OneToMany(mappedBy = "actividad")
	Set<EstadoSolicitud> ESTADO_SOLICITUD;

	@ManyToOne
	private ONG ong;

	@ManyToOne
	private Pdi profesor;

	public Pdi getProfesor() {
		return profesor;
	}

	public void setProfesor(Pdi profesor) {
		this.profesor = profesor;
	}

	public Actividad() {

	}

	public ONG getOng() {
		return ong;
	}

	public void setOng(ONG ong) {
		this.ong = ong;
	}

	public Actividad(String nombre, String descr, TipoActividad ambito, Date f_inicio, Date f_fin, ONG ong, Pdi pdi) {
		setDESCRIPCION(descr);
		setAMBITO(ambito);
		setNOMBRE(nombre);
		setF_INICIO(f_inicio);
		setF_FIN(f_fin);
		setOng(ong);
		setProfesor(pdi);
	}

	public Actividad(String nombre, Date f_inicio, Date f_fin, String descr, TipoActividad ambito) {
		setF_INICIO(f_inicio);
		setF_FIN(f_fin);
		setDESCRIPCION(descr);
		setAMBITO(ambito);
		setNOMBRE(nombre);

	}

	@Override
	public String toString() {
		return "Actividad [ID_ACTIVIDAD=" + ID_ACTIVIDAD + ", F_INICIO=" + F_INICIO + ", F_FIN=" + F_FIN
				+ ", DESCRIPCION=" + DESCRIPCION + ", AMBITO=" + AMBITO + ", ong=" + ong + "ESTADO=" + ESTADO_SOLICITUD
				+ "]";
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

	public Long getID_ACTIVIDAD() {
		return ID_ACTIVIDAD;
	}

	public void setID_ACTIVIDAD(Long iD_ACTIVIDAD) {
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

	public Set<EstadoSolicitud> getESTADO_SOLICITUD() {
		return ESTADO_SOLICITUD;
	}

	public void setESTADO_SOLICITUD(Set<EstadoSolicitud> eSTADO_SOLICITUD) {
		ESTADO_SOLICITUD = eSTADO_SOLICITUD;
	}

}
