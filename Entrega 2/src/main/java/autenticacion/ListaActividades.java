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
import modelo.TipoActividad;
import modelo.Usuario;

@Named(value = "listaActividades")
@RequestScoped

public class ListaActividades implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ControlAutorizacion ctrl;

	private Actividad actividadElegida;
	private Actividad act;
	private String nombre;
	private String descr;
	private TipoActividad t_a;

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

	public String aniadirActividad() {
		act = new Actividad(nombre, descr, ctrl.getBbdd().idActividad(), t_a);
		ctrl.getBbdd().getActividades().add(act);
		act = new Actividad();
		return ctrl.listaActividades();
	}

	public List<Actividad> getTodasActividades() {
		return ctrl.getBbdd().getActividades();
	}

	public void eliminar(Actividad actividad) {
		ctrl.getBbdd().getActividades().remove(actividad);
	}

	public Actividad getActividadElegida() {
		return actividadElegida;
	}

	public void setActividadElegida(Actividad actividadElegida) {
		this.actividadElegida = actividadElegida;
	}

	public String inscripcion(Actividad actividad) {
		if (ctrl.getUsuario() != null) {
			Map<Usuario, Set<Actividad>> mapa = ctrl.getBbdd().getUsuActs();
			Set<Actividad> acts = new HashSet<Actividad>();
			if (mapa.containsKey(ctrl.getUsuario())) {
				acts = mapa.get(ctrl.getUsuario());
			}
			acts.add(actividad);
			mapa.put(ctrl.getUsuario(), acts);
			mapa.putIfAbsent(ctrl.getUsuario(), acts);
		} else if (ctrl.getPas() != null) {
			Map<Pas, Set<Actividad>> mapa = ctrl.getBbdd().getPasacts();
			Set<Actividad> acts = new HashSet<Actividad>();
			if (mapa.containsKey(ctrl.getPas())) {
				acts = mapa.get(ctrl.getPas());
			}
			acts.add(actividad);
			mapa.put(ctrl.getPas(), acts);
			mapa.putIfAbsent(ctrl.getPas(), acts);
		} else if (ctrl.getPdi() != null) {
			Map<Pdi, Set<Actividad>> mapa = ctrl.getBbdd().getPdiacts();
			Set<Actividad> acts = new HashSet<Actividad>();
			if (mapa.containsKey(ctrl.getPdi())) {
				acts = mapa.get(ctrl.getPdi());
			}
			acts.add(actividad);
			mapa.put(ctrl.getPdi(), acts);
			mapa.putIfAbsent(ctrl.getPdi(), acts);
		}

		return ctrl.home();
	}

	public List<Actividad> getLactUsuario() {
		List<Actividad> lista = new ArrayList<Actividad>();
		if (ctrl.getUsuario() != null) {
			Map<Usuario, Set<Actividad>> mapa = ctrl.getBbdd().getUsuActs();
			Set<Actividad> acts = mapa.get(ctrl.getUsuario());
			if (acts != null) {
				for (Actividad a : acts) {
					lista.add(a);
				}
			}
		}
		return lista;
	}

	public List<Actividad> getLactPdi() {
		List<Actividad> lista = new ArrayList<Actividad>();
		if (ctrl.getPdi() != null) {
			Map<Pdi, Set<Actividad>> mapa = ctrl.getBbdd().getPdiacts();
			Set<Actividad> acts = mapa.get(ctrl.getPdi());
			if (acts != null) {
				for (Actividad a : acts) {
					lista.add(a);
				}
			}
		}
		return lista;
	}

	public List<Actividad> getLactPas() {
		List<Actividad> lista = new ArrayList<Actividad>();
		if (ctrl.getPas() != null) {
			Map<Pas, Set<Actividad>> mapa = ctrl.getBbdd().getPasacts();
			Set<Actividad> acts = mapa.get(ctrl.getPas());
			if (acts != null) {
				for (Actividad a : acts) {
					lista.add(a);
				}
			}
		}
		return lista;
	}

	public ControlAutorizacion getCtrl() {
		return ctrl;
	}

	public void setCtrl(ControlAutorizacion ctrl) {
		this.ctrl = ctrl;
	}

}
