package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EstadoSolicitudKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Long user_id;

	@Column(name = "act_id")
	private Long act_id;

	public EstadoSolicitudKey() {
		super();
	}

	public EstadoSolicitudKey(Long dni, Long id_ACTIVIDAD) {
		setUser_id(dni);
		setAct_id(id_ACTIVIDAD);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((act_id == null) ? 0 : act_id.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		EstadoSolicitudKey other = (EstadoSolicitudKey) obj;
		if (act_id == null) {
			if (other.act_id != null)
				return false;
		} else if (!act_id.equals(other.act_id))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getAct_id() {
		return act_id;
	}

	public void setAct_id(Long act_id) {
		this.act_id = act_id;
	}

}
