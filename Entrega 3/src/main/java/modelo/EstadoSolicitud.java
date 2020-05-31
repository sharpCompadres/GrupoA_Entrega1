package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;

/**
 * -------- ENTIDAD ESTADO SOLICITUD -------- (Tabla intermedia, atrib. de
 * relación)
 *
 * - Atributos: · Respectivas claves primarias · Getters y Setters · ESTADO
 *
 * - Método: · Constructores
 *
 **/

@NamedQuery(name = "EstadoSolicitud.todas", query = "select ea from EstadoSolicitud ea")
@Entity
public class EstadoSolicitud implements Serializable {

	@Override
	public String toString() {
		return "EstadoSolicitud [id=" + id + ", usuario=" + usuario.getDNI() + ", actividad="
				+ actividad.getID_ACTIVIDAD() + ", ESTADO=" + ESTADO + "]";
	}

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	EstadoSolicitudKey id;

	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name = "DNI")
	Usuario usuario;

	@ManyToOne
	@MapsId("act_id")
	@JoinColumn(name = "ID_ACTIVIDAD")
	Actividad actividad;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private T_Estado ESTADO;

	public Usuario getUsuario() {
		return usuario;
	}

	public EstadoSolicitud() {
		super();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public EstadoSolicitud(Usuario usuario, Actividad actividad) {
		this.usuario = usuario;
		this.actividad = actividad;
	}

	public EstadoSolicitud(EstadoSolicitudKey esk, Actividad actividad2, Usuario usuario2, T_Estado estado) {
		setId(esk);
		setActividad(actividad2);
		setUsuario(usuario2);
		setESTADO(estado);
	}

	public T_Estado getESTADO() {
		return ESTADO;
	}

	public void setESTADO(T_Estado eSTADO) {
		ESTADO = eSTADO;
	}

	public EstadoSolicitudKey getId() {
		return id;
	}

	public void setId(EstadoSolicitudKey id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actividad == null) ? 0 : actividad.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		EstadoSolicitud other = (EstadoSolicitud) obj;
		if (actividad == null) {
			if (other.actividad != null)
				return false;
		} else if (!actividad.equals(other.actividad))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
