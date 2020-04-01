package Entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Entity implementation class for Entity: Informe
 *
 */

@Entity
public class Informe implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID_INFORME;
	@Column(nullable = false)
	private String DESCRIPCION;
	private Float CALIFICACION;
	@ManyToOne
	private Alumno alumno;
	@ManyToOne
	private Pdi pdi;
	@OneToOne
	@Id
	@PrimaryKeyJoinColumn(name="FK_DEBIL_ACTIVIDAD", referencedColumnName="ID_ACTIVIDAD")
	private Actividad act;
	public Informe() {
		super();
	}
	public Long getID_INFORME() {
		return ID_INFORME;
	}
	public void setID_INFORME(Long iD_INFORME) {
		ID_INFORME = iD_INFORME;
	}
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}
	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}
	public Float getCALIFICACION() {
		return CALIFICACION;
	}
	public void setCALIFICACION(Float cALIFICACION) {
		CALIFICACION = cALIFICACION;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID_INFORME == null) ? 0 : ID_INFORME.hashCode());
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
		Informe other = (Informe) obj;
		if (ID_INFORME == null) {
			if (other.ID_INFORME != null)
				return false;
		} else if (!ID_INFORME.equals(other.ID_INFORME))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Informe [ID_INFORME=" + ID_INFORME + ", DESCRIPCION=" + DESCRIPCION + ", CALIFICACION=" + CALIFICACION
				+ "]";
	}
   
}
