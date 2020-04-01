package Entidades;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 *	-------- SUBENTIDAD DE USUARIO: PAS -------- 
 *
 *		- Atributos:
 *			· TIPO_SERVICIO
 *
 *		- Método:
 *			· Constructor
 **/

@Entity
public class Pas extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String TIPO_SERVICIO;
	
	public Pas() {
		super();
	}
   
}
