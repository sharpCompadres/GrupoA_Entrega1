package autenticacion;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.BaseDeDatosLocal;
import modelo.Actividad;
import modelo.Usuario;

@Named(value = "listaUsuarios")
@RequestScoped

public class ListaUsuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ControlAutorizacion ctrl;
	private Usuario usuario;

	@Inject
	private BaseDeDatosLocal bbdd;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getTodosLosUsuarios() {
		return bbdd.getUsuarios();

	}

	public void borrarUsuario(Usuario usuario) {
		bbdd.eliminarUsuario(usuario);
	}

	public ControlAutorizacion getCtrl() {
		return ctrl;
	}

	public void setCtrl(ControlAutorizacion ctrl) {
		this.ctrl = ctrl;
	}

	public String accederActividad(Actividad act) {
		ctrl.setAct(act);
		return ctrl.alumnosApuntados();
	}
	
	public List<Usuario> getListaApuntados(Actividad act) {
		return bbdd.getListaApuntados(act);
	}


}
