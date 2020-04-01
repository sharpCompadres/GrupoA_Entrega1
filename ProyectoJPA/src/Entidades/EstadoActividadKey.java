package Entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EstadoActividadKey {

	@Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "act_id")
	private Long act_id;
	
}
