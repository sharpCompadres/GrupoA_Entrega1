package autenticacion;

import static modelo.Usuario.Rol.ADMINISTRADOR;
import static modelo.Usuario.Rol.USUARIO;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import modelo.Pas;
import modelo.Pdi;
import modelo.TipoActividad;
import modelo.Usuario;

@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {

	private Usuario usuario;
	private Pas pas;
	private Pdi pdi;

	private static BBDD bbdd = new BBDD();

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Pas getPas() {
		return pas;
	}

	public void setPas(Pas pas) {
		this.pas = pas;
	}

	public Pdi getPdi() {
		return pdi;
	}

	public void setPdi(Pdi pdi) {
		this.pdi = pdi;
	}

	public BBDD getBbdd() {
		return bbdd;
	}

	public void setBbdd(BBDD bbdd) {
		this.bbdd = bbdd;
	}

	public String home() {
		String aux = "inicio.xhtml";

		if (usuario != null) {
			if (usuario.getRol().equals(USUARIO)) {
				aux = "normal.xhtml";
			} else if (usuario.getRol().equals(ADMINISTRADOR)) {
				aux = "admin.xhtml";
			}
		} else if (pdi != null) {
			aux = "pdi.xhtml";
		} else if (pas != null) {
			aux = "pas.xhtml";
		}
		return aux;
	}

	public String logout() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.getExternalContext().invalidateSession();
		usuario = null;
		pdi = null;
		pas = null;
		return "inicio.xhtml";
	}

	public ControlAutorizacion() {
	}

	public String listaActividades() {
		return "listaActividades.xhtml";
	}

	public String login() {
		return "login.xhtml";
	}

	public String contacto() {
		return "aboutus2.xhtml";
	}

	public String ayuda() {
		return "ayuda.xhtml";
	}

	public String queEsAps() {
		return "queesaps.xhtml";
	}

	public String opiniones() {
		return "opiniones.xhtml";
	}

	public String aniadirActividad() {
		return "aniadirActividad.xhtml";
	}
}
