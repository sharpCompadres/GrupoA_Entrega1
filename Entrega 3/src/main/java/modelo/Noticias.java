package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@NamedQuery(name = "Noticias.todas", query = "select u from Noticias u")
@Entity
public class Noticias {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Column(nullable = false)
	private String titular;
	@Column(nullable = false)
	private String cuerpo;

	public Noticias(String titular, String cuerpo) {
		this.titular = titular;
		this.cuerpo = cuerpo;
	}

	public Noticias() {

	}

	public String getTitular() {
		return titular;
	}

	@Override
	public String toString() {
		return "Noticias [id=" + id + ", titular=" + titular + ", cuerpo=" + cuerpo + "]";
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Noticias other = (Noticias) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}