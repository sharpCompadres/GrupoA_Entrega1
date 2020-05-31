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
import modelo.ONG;

@Named(value = "listaONGs")
@RequestScoped

public class ListaONGS implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ControlAutorizacion ctrl;
	private ONG ong;

	@Inject
	private BaseDeDatosLocal bbdd;

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	private String id;
	private String nombre;
	private String ambito;
	private Long telf;
	private String email;
	private String contrasenia;

	public ONG getOng() {
		return ong;
	}

	public void setOng(ONG ong) {
		this.ong = ong;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getTelf() {
		return telf;
	}

	public void setTelf(Long telf) {
		this.telf = telf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void aniadirONG() {
		try {
			ong = new ONG(id, nombre, ambito, telf, email, contrasenia);
			bbdd.aniadirONG(ong);
			ong = new ONG();
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}

	}

	public List<ONG> getONGs() {
		return bbdd.getONGs();
	}

	public void eliminarONG(ONG ong) {
		bbdd.eliminarONG(ong);
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
