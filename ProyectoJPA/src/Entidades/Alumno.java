package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


/**
 *	-------- SUBENTIDAD DE USUARIO: ALUMNO -------- 
 *
 *		- Atributos:
 *			· GRADO_FACULTAD
 *
 *		- Método:
 *			· Constructor
 **/

@Entity
public class Alumno extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String GRADO_FACULTAD;
	
	@OneToMany
	private List<Informe> informes = new ArrayList<>(); 
	
	public Alumno() {
		super();
	}

}
