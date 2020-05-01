package autenticacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Actividad;
import modelo.Pas;
import modelo.Pdi;
import modelo.Usuario;

@Named(value = "listaOpiniones")
@RequestScoped

public class ListaOpiniones implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ControlAutorizacion ctrl;

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
		ctrl.getBbdd().getOpiniones().add(opi);
		opi = new Opinion();
	}

	public List<Opinion> getTodasOpiniones() {
		return ctrl.getBbdd().getOpiniones();
	}

}
