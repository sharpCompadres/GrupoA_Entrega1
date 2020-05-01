/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacion;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Pas;
import modelo.Pdi;
import modelo.Usuario;
import modelo.Usuario.Rol;

/**
 *
 * @author francis
 */
@Named(value = "login")
@RequestScoped
public class Login {

	private String DNI;
	private String contrasenia;

	@Inject
	private ControlAutorizacion ctrl;

	/**
	 * Creates a new instance of Login
	 */
	public Login() {

	}

	public ControlAutorizacion getCtrl() {
		return ctrl;
	}

	public void setCtrl(ControlAutorizacion ctrl) {
		this.ctrl = ctrl;
	}

	public String getDNI() {
		return DNI;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String autenticar() {
		String aux = "login.xhtml";
		boolean esta = false;
		for (Usuario u : ctrl.getBbdd().getUsuarios()) {
			boolean u_esta = getDNI().equals(u.getDNI());
			boolean p_esta = getContrasenia().equals(u.getPassword());

			if (u_esta && p_esta && !esta) {
				esta = true;
				ctrl.setUsuario(u);
				aux = ctrl.home();
			}

		}

		if (!esta) {
			for (Pas p : getCtrl().getBbdd().getPass()) {
				boolean u_esta = getDNI().equals(p.getDNI());
				boolean p_esta = getContrasenia().equals(p.getPassword());

				if (u_esta && p_esta && !esta) {
					esta = true;
					ctrl.setPas(p);
					aux = ctrl.home();
				}
			}
		}

		if (!esta) {
			for (Pdi p : getCtrl().getBbdd().getPdis()) {
				boolean u_esta = getDNI().equals(p.getDNI());
				boolean p_esta = getContrasenia().equals(p.getPassword());

				if (u_esta && p_esta && !esta) {
					esta = true;
					ctrl.setPdi(p);
					aux = ctrl.home();
				}
			}
		}

		return aux;
	}
}
