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


/**
 *	-------- ENTIDAD ESTADOACTIVIDAD -------- 
 *		(Tabla intermedia, atrib. de relación)
 *
 *		- Atributos:
 *			· Respectivas claves primarias
 *			· ESTADO	
 *
 *		- Método:
 *			· Constructor
 *
 **/
@Entity
public class EstadoActividad implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	EstadoActividadKey id;
	
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
	
	
	public EstadoActividad() {
		super();
	}
   
}
