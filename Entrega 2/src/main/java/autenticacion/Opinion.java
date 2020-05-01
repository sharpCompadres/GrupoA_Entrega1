package autenticacion;

public class Opinion {

	private String nombre;
	private String opinion;

	public Opinion(String nombre, String opinion) {
		this.nombre = nombre;
		this.opinion = opinion;
	}

	public Opinion() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

}