package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *	-------- SUBENTIDAD DE USUARIO: PDI -------- 
 *
 *		- Atributos:
 *			· DEPARTAMENTO
 *			· DESPACHO
 *
 *		- Método:
 *			· Constructor
 **/

@Entity
public class Pdi extends Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String DEPARTAMENTO;
	private String DESPACHO;
	
	@OneToMany
	private List<Informe> informes = new ArrayList<>(); 
	
	public Pdi() {
		super();
	}
	
   
}
