package Entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *	-------- PKs TABLA ESTADOACTIVIDAD -------- 
 **/

@Embeddable
public class EstadoActividadKey implements Serializable{

	@Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "act_id")
	private Long act_id;

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
		EstadoActividadKey other = (EstadoActividadKey) obj;
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
	
}
