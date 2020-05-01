package autenticacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.Actividad;
import modelo.Pas;
import modelo.Pdi;
import modelo.TipoActividad;
import modelo.Usuario;
import modelo.Usuario.Rol;

public class BBDD {

	private List<Actividad> actividades;
	private List<Usuario> usuarios;
	private List<Pas> pass;
	private List<Pdi> pdis;
	private Map<Usuario, Set<Actividad>> usuacts;
	private Map<Pdi, Set<Actividad>> pdiacts;
	private Map<Pas, Set<Actividad>> pasacts;
	private List<Opinion> opiniones;
	private static int act_id = 0;

	@SuppressWarnings("deprecation")
	public BBDD() {
		actividades = new ArrayList<>();
		usuarios = new ArrayList<Usuario>();
		pdis = new ArrayList<Pdi>();
		pass = new ArrayList<Pas>();
		usuacts = new HashMap<Usuario, Set<Actividad>>();
		pdiacts = new HashMap<Pdi, Set<Actividad>>();
		pasacts = new HashMap<Pas, Set<Actividad>>();
		opiniones = new ArrayList<Opinion>();

		actividades.add(new Actividad("Actividad 1", idActividad(), new Date("21/02/2014"), new Date("21/02/2020"),
				"Aquí su descripción", TipoActividad.VOLUNTARIADO));
		actividades.add(new Actividad("Actividad 2", idActividad(), new Date("21/02/2014"), new Date("21/02/2020"),
				"Aquí su descripción", TipoActividad.SERVICIO_DE_APRENDIZAJE));
		actividades.add(new Actividad("Actividad 3", idActividad(), new Date("21/02/2014"), new Date("21/02/2020"),
				"Aquí su descripción", TipoActividad.EVALUABLE));
		actividades.add(new Actividad("Actividad 4", idActividad(), new Date("21/02/2014"), new Date("21/02/2020"),
				"Aquí su descripción", TipoActividad.VOLUNTARIADO));
		actividades.add(new Actividad("Actividad 5", idActividad(), new Date("21/02/2014"), new Date("21/02/2020"),
				"Aquí su descripción", TipoActividad.SERVICIO_DE_APRENDIZAJE));
		actividades.add(new Actividad("Actividad 6", idActividad(), new Date("21/02/2014"), new Date("21/02/2020"),
				"Aquí su descripción", TipoActividad.EVALUABLE));
		actividades.add(new Actividad("Actividad 7", idActividad(), new Date("21/02/2014"), new Date("21/02/2020"),
				"Aquí su descripción", TipoActividad.SERVICIO_DE_APRENDIZAJE));
		usuarios.add(new Usuario("Jose Antonio", "12345678", "12345678J", Rol.USUARIO));
		usuarios.add(new Usuario("Omar David", "12345678", "12345678O", Rol.ADMINISTRADOR));
		pass.add(new Pas("Cindy Laura", "12345678", "12345678C"));
		pass.add(new Pas("Alberto", "12345678", "12345678A"));
		pdis.add(new Pdi("Ernesto", "12345678", "12345678E"));
		opiniones.add(new Opinion("Juan Gómez", "¡Ha sido una experiencia fantástica, repetiría sin dudarlo!"));
		opiniones.add(new Opinion("Laura Jiménez ", "Una plataforma perfecta, ¡cumple todos los requisitos que quiero!"));
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Pdi> getPdis() {
		return pdis;
	}

	public List<Pas> getPass() {
		return pass;
	}

	public Map<Usuario, Set<Actividad>> getUsuActs() {
		return usuacts;
	}

	public Map<Pas, Set<Actividad>> getPasacts() {
		return pasacts;
	}

	public Map<Pdi, Set<Actividad>> getPdiacts() {
		return pdiacts;
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	public String idActividad() {
		int x = act_id;
		act_id++;
		return "" + x;
	}

}