package modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * -------- ENTIDAD ONG --------
 *
 * - Atributos: · ID_ONG (PK/NOT NULL) · NOMBRE (NOT NULL) · AMBITO (NOT NULL) ·
 * TELEFONO (NOT NULL) · EMAIL (NOT NULL)
 *
 * - Métodos · Constructor · Getters y Setters de los atributos anteriormente
 * listados · HashCode y Equals · Método toString
 *
 **/

@NamedQuery(name = "ONG.todas", query = "select u from ONG u")
@Entity
public class ONG implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String ID_ONG;
	@Column(nullable = false)
	private String NOMBRE;
	@Column(nullable = false)
	private String AMBITO;
	@Column(nullable = false, length = 20)
	private Long TELEFONO;
	@Column(nullable = false)
	private String EMAIL;
	@Column(nullable =false, length = 20)
	private String CONTRASENIA;
	@OneToMany(mappedBy = "ong")
	private Set<Actividad> act;

	public String getCONTRASENIA() {
		return CONTRASENIA;
	}

	public void setCONTRASENIA(String cONTRASENIA) {
		CONTRASENIA = cONTRASENIA;
	}
	
	public Set<Actividad> getAct() {
		return act;
	}

	public void setAct(Set<Actividad> act) {
		this.act = act;
	}

	public ONG() {
		super();
	}

	public ONG(String ID, String nombre, String ambito, Long telf, String email, String cONTRASENIA) {
		setID_ONG(ID);
		setNOMBRE(nombre);
		setAMBITO(ambito);
		setTELEFONO(telf);
		setEMAIL(email);
		setCONTRASENIA(cONTRASENIA);
	}

	public ONG(String nif, String contrasenia2) {
		setID_ONG(nif);
		setCONTRASENIA(contrasenia2);
	}

	@Override
	public String toString() {
		return "ONG [ID_ONG=" + ID_ONG + ", NOMBRE=" + NOMBRE + ", AMBITO=" + AMBITO + ", TELEFONO=" + TELEFONO
				+ ", EMAIL=" + EMAIL + "]";
	}

	public String getID_ONG() {
		return ID_ONG;
	}

	public void setID_ONG(String iD_ONG) {
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

	public Long getTELEFONO() {
		return TELEFONO;
	}

	public void setTELEFONO(Long tELEFONO) {
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