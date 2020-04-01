package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *	-------- ENTIDAD ONG -------- 
 *
 *		- Atributos:
 *			· ID_ONG 		(PK/NOT NULL)
 *			· NOMBRE		(NOT NULL)
 *			· AMBITO		(NOT NULL)
 *			· TELEFONO		(NOT NULL)
 *			· EMAIL	  		(NOT NULL)
 *
 *		- Métodos
 *			· Constructor
 *			· Getters y Setters de los atributos anteriormente listados
 *			· HashCode y Equals
 *			· Método toString
 *
 **/

@Entity
public class ONG implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer ID_ONG; //NIF
	@Column(nullable = false)
	private String NOMBRE;
	@Column(nullable = false)
	private String AMBITO;
	@Column(nullable = false, length = 20)
	private Integer TELEFONO;
	@Column(nullable = false)
	private String EMAIL;
	@OneToMany
	private List<Actividad> ONG_ACT = new ArrayList<>();

	public ONG() {
		super();
	}

	@Override
	public String toString() {
		return "ONG [ID_ONG=" + ID_ONG + ", NOMBRE=" + NOMBRE + ", AMBITO=" + AMBITO + ", TELEFONO=" + TELEFONO
				+ ", EMAIL=" + EMAIL + "]";
	}

	public Integer getID_ONG() {
		return ID_ONG;
	}

	public void setID_ONG(Integer iD_ONG) {
		ID_ONG = iD_ONG;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public String getAMBITO() {
		return AMBITO;
	}

	public void setAMBITO(String aMBITO) {
		AMBITO = aMBITO;
	}

	public Integer getTELEFONO() {
		return TELEFONO;
	}

	public void setTELEFONO(Integer tELEFONO) {
		TELEFONO = tELEFONO;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID_ONG == null) ? 0 : ID_ONG.hashCode());
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
		ONG other = (ONG) obj;
		if (ID_ONG == null) {
			if (other.ID_ONG != null)
				return false;
		} else if (!ID_ONG.equals(other.ID_ONG))
			return false;
		return true;
	}
   
}
