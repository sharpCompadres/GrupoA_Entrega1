package autenticacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.ASMException;
import ejb.BaseDeDatosLocal;
import modelo.Actividad;
import modelo.EstadoSolicitud;
import modelo.ONG;
import modelo.Pdi;
import modelo.T_Estado;
import modelo.TipoActividad;
import modelo.Usuario;

@Named(value = "listaActividades")
@RequestScoped

public class ListaActividades implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ControlAutorizacion ctrl;

	@Inject
	private BaseDeDatosLocal bbdd;

	private Actividad actividadElegida;
	private Actividad act;
	private String nombre;
	private String descr;
	private Date f_inicio;
	private Date f_fin;
	private TipoActividad t_a;
	private String ong_elegida;
	private Long pdi_supervisor;
	private String mail;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getPdi_supervisor() {
		return pdi_supervisor;
	}

	public void setPdi_supervisor(Long pdi_supervisor) {
		this.pdi_supervisor = pdi_supervisor;
	}

	public String getOng_elegida() {
		return ong_elegida;
	}

	public void setOng_elegida(String ong_elegida) {
		this.ong_elegida = ong_elegida;
	}

	public ListaActividades() {
		act = new Actividad();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Actividad getAct() {
		return act;
	}

	public TipoActividad getT_a() {
		return t_a;
	}

	public void setT_a(TipoActividad t_a) {
		this.t_a = t_a;
	}

	public void aniadirActividad() {
		try {
			ONG aux = bbdd.getFullONG(ong_elegida);
			Pdi pdi = new Pdi(pdi_supervisor);
			act = new Actividad(nombre, descr, t_a, f_inicio, f_fin, aux, pdi);
			bbdd.aniadirActividad(act);
			act = new Actividad();
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}

	public Date getF_inicio() {
		return f_inicio;
	}

	public void setF_inicio(Date f_inicio) {
		this.f_inicio = f_inicio;
	}

	public Date getF_fin() {
		return f_fin;
	}

	public void setF_fin(Date f_fin) {
		this.f_fin = f_fin;
	}

	public List<Actividad> getTodasActividades() {
		return bbdd.getTodasActividades();
	}

	public void eliminar(Actividad actividad) {
		bbdd.eliminarActividad(actividad);
	}

	public Actividad getActividadElegida() {
		return actividadElegida;
	}

	public void setActividadElegida(Actividad actividadElegida) {
		this.actividadElegida = actividadElegida;
	}

	public String inscripcion(Actividad actividad) {
		if (ctrl.getUsuario() != null) {
			bbdd.inscripcion(actividad, ctrl.getUsuario());
		} else if (ctrl.getPas() != null) {
			bbdd.inscripcion(actividad, ctrl.getPas());
		} else if (ctrl.getPdi() != null) {
			bbdd.inscripcion(actividad, ctrl.getPdi());
		}

		return ctrl.home();
	}

	public List<Actividad> getLactUsuario(Long dni) {
		return bbdd.getLactUsuario(dni);
	}

	public List<Actividad> getLactONG(String nif) {
		return bbdd.getLactONG(nif);
	}

	public ControlAutorizacion getCtrl() {
		return ctrl;
	}

	public void setCtrl(ControlAutorizacion ctrl) {
		this.ctrl = ctrl;
	}

	public List<ONG> getONGs() {
		return bbdd.getONGs();
	}

	public EstadoSolicitud estadoSolicitudEntidad(Actividad act, Usuario usu) {
		List<EstadoSolicitud> todos = bbdd.getTodosEstadosSolicitud();
		EstadoSolicitud aux = new EstadoSolicitud();
		boolean salir = false;
		for (EstadoSolicitud es : todos) {
			if (es.getUsuario().getDNI().equals(usu.getDNI())
					&& es.getActividad().getID_ACTIVIDAD().equals(act.getID_ACTIVIDAD()) && !salir) {
				aux = es;
				salir = true;
			}
		}			
		return aux;
	}

	public List<Pdi> getPDIs() {
		return bbdd.getProfesores();
	}

	public List<Actividad> getTutorizadas(Pdi pdi) {
		List<Actividad> todas = bbdd.getTodasActividades();
		List<Actividad> tutorizadas = new ArrayList<Actividad>();
		for (Actividad a : todas) {
			if (a.getProfesor().equals(pdi)) {
				tutorizadas.add(a);
			}
		}

		return tutorizadas;
	}

	public void aceptarUno(Usuario usu, Actividad act) {
		EstadoSolicitud es = estadoSolicitudEntidad(act, usu);
		try {
			mail = "";
			bbdd.aceptarUno(es);
			mail = "mailto:" + usu.getEmail();
			mail = mail + "?Subject=Ha sido ACEPTADO en la actividad " + act.getNOMBRE()
					+ "&Body=Me gustaría informarle de que su petición en la actividad " + act.getNOMBRE()
					+ " ha sido ACEPTADA";
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}

	}

	public void rechazarUno(Usuario usu, Actividad act) {
		EstadoSolicitud es = estadoSolicitudEntidad(act, usu);
		try {
			mail = "";
			bbdd.rechazarUno(es);
			mail = "mailto:" + usu.getEmail();
			mail = mail + "?Subject=Ha sido DENEGADO en la actividad " + act.getNOMBRE()
					+ "&Body=Me gustaría informarle de que su petición en la actividad " + act.getNOMBRE()
					+ " ha sido DENEGADA";
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}

	public void aceptarTodos(Actividad act) {
		try {
			mail = "";
			bbdd.aceptarTodos(act);
			for (EstadoSolicitud es : bbdd.getTodosEstadosSolicitud()) {
				if (es.getActividad().getID_ACTIVIDAD().equals(act.getID_ACTIVIDAD())) {
					mail = mail + ", " + es.getUsuario().getEmail();
					mail = "mailto:" + mail;
				}
			}
			mail = mail + "?Subject=Ha sido ACEPTADO en la actividad " + act.getNOMBRE()
					+ "&Body=Me gustaría informarle de que su petición en la actividad " + act.getNOMBRE()
					+ " ha sido ACEPTADA";
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}

	public void rechazarTodos(Actividad act) {
		try {
			mail = "";
			bbdd.rechazarTodos(act);
			for (EstadoSolicitud es : bbdd.getTodosEstadosSolicitud()) {
				if (es.getActividad().getID_ACTIVIDAD().equals(act.getID_ACTIVIDAD())) {
					mail = mail + ", " + es.getUsuario().getEmail();
					mail = "mailto:" + mail;
				}
			}
			mail = mail + "?Subject=Ha sido DENEGADO en la actividad " + act.getNOMBRE()
					+ "&Body=Me gustaría informarle de que su petición en la actividad " + act.getNOMBRE()
					+ " ha sido DENEGADA";

		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}

}
