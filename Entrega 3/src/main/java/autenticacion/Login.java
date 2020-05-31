/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacion;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.ASMException;
import ejb.BaseDeDatosLocal;
import ejb.ContraseniaInvalidaException;
import ejb.CuentaInexistenteException;
import modelo.ONG;
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

	private Long DNI;
	private String NIF;

	private String contrasenia;
	private Date f_nacimiento;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private Rol rol;
	private String t_servicio;
	private String despacho;
	private String departamento;

	private Usuario usuario;
	private Pas pas;
	private Pdi pdi;
	private ONG ong;

	public ONG getOng() {
		return ong;
	}

	public void setOng(ONG ong) {
		this.ong = ong;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	@Inject
	private ControlAutorizacion ctrl;

	@Inject
	private BaseDeDatosLocal bbdd;

	public Login() {

	}

	public ControlAutorizacion getCtrl() {
		return ctrl;
	}

	public void setCtrl(ControlAutorizacion ctrl) {
		this.ctrl = ctrl;
	}

	public Long getDNI() {
		return DNI;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setDNI(Long DNI) {
		this.DNI = DNI;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String autenticar() {

		try {
			Usuario usuario = new Usuario(getDNI(), getContrasenia());
			usuario = bbdd.usuarioCompleto(usuario);
			Rol rol = usuario.getRol();

			if (rol == Rol.PAS) {
				pas = new Pas(usuario, this.t_servicio);
				bbdd.compruebaLogin(pas);
				ctrl.setPas(bbdd.refrescarPas(pas));
			} else if (rol == Rol.UPDI) {
				pdi = new Pdi(usuario, this.despacho, this.departamento);
				bbdd.compruebaLogin(pdi);
				ctrl.setPdi(bbdd.refrescarPdi(pdi));
			} else {
				bbdd.compruebaLogin(usuario);
				ctrl.setUsuario(bbdd.refrescarUsuario(usuario));
			}
			return ctrl.home();

		} catch (CuentaInexistenteException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe");
			FacesContext.getCurrentInstance().addMessage("login:user", fm);
		} catch (ContraseniaInvalidaException e) {
			FacesMessage fm = new FacesMessage("La contraseña no es correcta");
			FacesContext.getCurrentInstance().addMessage("login:pass", fm);
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}

		return null;
	}

	public String autenticarONG() {
		try {
			ONG aux = new ONG(getNIF(), getContrasenia());
			bbdd.compruebaLogin(aux);
			ctrl.setOng(bbdd.refrescarOng(aux));
			return ctrl.home();
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}

		return null;
	}

	public Object rellenarUsuario() {
		Usuario usu = new Usuario();
		usu.setDNI(this.DNI);
		usu.setApellido1(this.apellido1);
		usu.setApellido2(this.apellido2);
		usu.setEmail(this.email);
		usu.setNombre(this.nombre);
		usu.setF_nacimiento(this.f_nacimiento);
		usu.setRol(rol);
		usu.setPassword(this.contrasenia);
		Pas pas = new Pas(usu);
		Pdi pdi = new Pdi(usu);
		pas.setTIPO_SERVICIO(this.t_servicio);
		pdi.setDepartamento(this.departamento);
		pdi.setDespacho(this.despacho);

		if (usu.getRol() == Rol.UPDI) {
			return pdi;
		} else if (usu.getRol() == Rol.PAS) {
			return pas;
		}

		return (Usuario) usu;
	}

	public String aniadirUsuario(Usuario usuario) throws ASMException {
		try {
			Object o = rellenarUsuario();
			if (o instanceof Pas) {
				bbdd.aniadirUsuario((Pas) o);
			} else if (o instanceof Pdi) {
				bbdd.aniadirUsuario((Pdi) o);
			} else if (o instanceof Usuario) {
				bbdd.aniadirUsuario((Usuario) o);
			}
			return ctrl.home();

		} catch (CuentaInexistenteException e) {
			FacesMessage fm = new FacesMessage("La cuenta ya existe");
			FacesContext.getCurrentInstance().addMessage("login:DNI", fm);
		}
		return null;

	}

	public Date getF_nacimiento() {
		return f_nacimiento;
	}

	public void setF_nacimiento(Date f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BaseDeDatosLocal getBbdd() {
		return bbdd;
	}

	public void setBbdd(BaseDeDatosLocal bbdd) {
		this.bbdd = bbdd;
	}

	public String getT_servicio() {
		return t_servicio;
	}

	public void setT_servicio(String t_servicio) {
		this.t_servicio = t_servicio;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}
