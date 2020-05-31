package autenticacion;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.ASMException;
import ejb.BaseDeDatosLocal;
import modelo.Opinion;

@Named(value = "listaOpiniones")
@RequestScoped

public class ListaOpiniones implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ControlAutorizacion ctrl;
	@Inject
	private BaseDeDatosLocal bbdd;
	
	private String nombre;
	private String opinion;
	private Opinion opi;

	public ListaOpiniones() {
		opi = new Opinion();
	}

	public ControlAutorizacion getCtrl() {
		return ctrl;
	}

	public void setCtrl(ControlAutorizacion ctrl) {
		this.ctrl = ctrl;
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

	public Opinion getOpi() {
		return opi;
	}

	public void setOpi(Opinion opi) {
		this.opi = opi;
	}

	public void aniadirOpinion() {
		opi = new Opinion(nombre, opinion);
		try {
			bbdd.aniadirOpinion(opi);
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		opi = new Opinion();
	}

	public List<Opinion> getTodasOpiniones() {
		return bbdd.getTodasOpiniones();
	}
	
	public void eliminarOpinion(Opinion opi) {
		bbdd.eliminarOpinion(opi);
	}

}
