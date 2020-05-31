package ejb;

import static modelo.T_Estado.ACTIVO;
import static modelo.T_Estado.CANCELADO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Actividad;
import modelo.EstadoSolicitud;
import modelo.EstadoSolicitudKey;
import modelo.Noticias;
import modelo.ONG;
import modelo.Opinion;
import modelo.Pas;
import modelo.Pdi;
import modelo.T_Estado;
import modelo.Usuario;

@Stateless
public class BaseDeDatos implements BaseDeDatosLocal {

	@PersistenceContext(unitName = "Entrega 2")
	private EntityManager em;

	/**
	 * Comprobamos el DNI y la contraseña del Usuario u, pudiendo lanzar dos
	 * excepciones: - La cuenta no existe - Contraseña incorrecta
	 * 
	 * @param u
	 * @return
	 * 
	 */

	@Override
	public void compruebaLogin(Usuario u) throws ASMException {
		Usuario usu = em.find(Usuario.class, u.getDNI());

		if (usu == null) {
			throw new ASMException("La cuenta no existe.");
		}

		if (!usu.getPassword().equals(u.getPassword())) {
			throw new ASMException("Contraseña incorrecta.");
		}
	}

	/**
	 * 
	 * Devolvemos el objeto Usuario completo, al que se le ha sido pasado por
	 * parámetro solamente dicho objeto con los campos DNI y contrasenia
	 * 
	 * @param u
	 * @return Usuario
	 * 
	 */

	@Override
	public Usuario refrescarUsuario(Usuario u) throws ASMException {
		Usuario usu = em.find(Usuario.class, u.getDNI());
		System.out.println(usu.toString());
		em.refresh(usu);
		return usu;
	}

	/**
	 * 
	 * Devolvemos el objeto Pas completo, al que se le ha sido pasado por parámetro
	 * solamente dicho objeto con los campos DNI y contrasenia
	 * 
	 * @param p
	 * @return Pas
	 * 
	 */

	@Override
	public Pas refrescarPas(Pas p) throws ASMException {
		Pas pas = em.find(Pas.class, p.getDNI());
		System.out.println(pas.toString());
		em.refresh(pas);
		return pas;
	}

	/**
	 * 
	 * Devolvemos el objeto Pdi completo, al que se le ha sido pasado por parámetro
	 * solamente dicho objeto con los campos DNI y contrasenia
	 * 
	 * @param p
	 * @return Pdi
	 * 
	 */

	@Override
	public Pdi refrescarPdi(Pdi p) throws ASMException {
		Pdi pdi = em.find(Pdi.class, p.getDNI());
		System.out.println(pdi.toString());
		em.refresh(pdi);
		return pdi;
	}

	/**
	 * 
	 * Devolvemos el objeto ONG completo, al que se le ha sido pasado por parámetro
	 * solamente dicho objeto con los campos NIF y contrasenia
	 * 
	 * @param p
	 * @return Pdi
	 * 
	 */

	@Override
	public ONG refrescarOng(ONG aux) throws ASMException {
		compruebaLogin(aux);
		ONG ong = em.find(ONG.class, aux.getID_ONG());
		System.out.println(ong.toString());
		em.refresh(ong);
		return ong;
	}

	/**
	 * Este método debe insertar la actividad que se le pasa como argumento de la
	 * lista de Actividades. Debe comprobar que la actividad existe en dicha lista.
	 * 
	 * @param actividad
	 * @return
	 * 
	 */

	@Override
	public void aniadirActividad(Actividad actividad) throws ASMException {

		if (actividad.getAMBITO() == null)
			throw new ASMException("El ámbito no puede estar vacía");
		if (actividad.getDESCRIPCION().isEmpty())
			throw new ASMException("La descripción no puede estar vacía");
		if (actividad.getF_FIN().equals(null))
			throw new ASMException("La fecha de fin no puede estar vacía");
		if (actividad.getF_INICIO().equals(null))
			throw new ASMException("La fecha de inicio no puede estar vacía");
		if (actividad.getProfesor().equals(null))
			throw new ASMException("La actividad no puede estar sin supervisor");
		em.persist(em.merge(actividad));
	}

	/**
	 * Esta función devuelve una lista de todas las actividades que existen
	 * 
	 * @param
	 * @return List<Actividad>
	 * 
	 */

	@Override
	public List<Actividad> getTodasActividades() {
		return em.createNamedQuery("Actividad.todas", Actividad.class).getResultList();
	}

	/**
	 * Este método elimina la actividad pasada por parámetro de la lista de
	 * actividades.
	 * 
	 * @param actividad
	 * @return
	 * 
	 */

	@Override
	public void eliminarActividad(Actividad actividad) {
		List<EstadoSolicitud> todas = em.createNamedQuery("EstadoSolicitud.todas", EstadoSolicitud.class)
				.getResultList();
		for (EstadoSolicitud es : todas) {
			if (actividad.getID_ACTIVIDAD().equals(es.getActividad().getID_ACTIVIDAD())) {
				em.remove(em.merge(es));
			}
		}

		em.remove(em.merge(actividad));

	}

	public List<EstadoSolicitud> getTodosEstadosSolicitud() {
		return em.createNamedQuery("EstadoSolicitud.todas", EstadoSolicitud.class).getResultList();
	}

	/**
	 * Este método genera una relación entre el Usuario usuario y la Actividad
	 * actividad. (De modo que el usuario queda inscrito a dicha actividad)
	 * 
	 * @param actividad
	 * @param usuario
	 * @return
	 * 
	 */

	@Override
	public void inscripcion(Actividad actividad, Usuario usuario) {
		// TODO Auto-generated method stub
		EstadoSolicitudKey esk = new EstadoSolicitudKey(usuario.getDNI(), actividad.getID_ACTIVIDAD());
		EstadoSolicitud es = new EstadoSolicitud(esk, actividad, usuario, T_Estado.PENDIENTE);
		em.persist(em.merge(es));
	}

	/**
	 * Cuando queremos la lista de actividades de un Usuario, esta función devuelve
	 * una lista de todas las actividades pertenecientes al Long dni que pasamos por
	 * parámetro
	 * 
	 * @param dni
	 * @return List<Actividad>
	 * 
	 */

	@Override
	public List<Actividad> getLactUsuario(Long dni) {
		List<EstadoSolicitud> todas = em.createNamedQuery("EstadoSolicitud.todas", EstadoSolicitud.class)
				.getResultList();
		List<Actividad> pasAct = new ArrayList<Actividad>();
		for (EstadoSolicitud es : todas) {
			if (es.getUsuario().getDNI().equals(dni)) {
				pasAct.add(es.getActividad());
			}
		}
		return pasAct;
	}

	/**
	 * Esta función devuelve la lista total de todos los Usuarios de la base de
	 * datos
	 * 
	 * @param
	 * @return List<Usuario>
	 * 
	 */

	@Override
	public List<Usuario> getUsuarios() {
		return em.createNamedQuery("Usuario.todas", Usuario.class).getResultList();
	}

	/**
	 * Este método persiste en la base de datos el Usuario que se le haya pasado
	 * como parámetro. Lo hacemos con Object u para que no importe el tipo, y
	 * persista con las características de cada uno, tanto Usuario, como PDI o PAS
	 * 
	 * @param u
	 * @return
	 * 
	 */

	@Override
	public void aniadirUsuario(Object u) throws ASMException {
		Usuario usu = em.find(Usuario.class, ((Usuario) u).getDNI());

		if (usu != null) {
			throw new ASMException("La cuenta ya existe.");
		} else {
			if (u instanceof Pas) {
				Pas pas2 = (Pas) u;
				em.persist(pas2);
			} else if (u instanceof Pdi) {
				Pdi pdi2 = (Pdi) u;
				em.persist(pdi2);
			} else if (u instanceof Usuario) {
				Usuario usu2 = (Usuario) u;
				em.persist(usu2);
			}
		}

	}


	/**
	 * Elimina de la base de datos el usuario pasado por parámetro
	 * 
	 * @param u
	 * @return
	 * 
	 */

	@Override
	public void eliminarUsuario(Usuario u) throws EJBTransactionRolledbackException {
		List<EstadoSolicitud> todas = em.createNamedQuery("EstadoSolicitud.todas", EstadoSolicitud.class)
				.getResultList();
		for (EstadoSolicitud es : todas) {
			if (es.getUsuario().getDNI().equals(u.getDNI())) {
				em.remove(em.merge(es));
			}
		}

		em.remove(em.merge(u));
	}

	/**
	 * Devuelve una lista con todas las opiniones
	 * 
	 * @param
	 * @return List<Opinion>
	 * 
	 */

	public List<Opinion> getTodasOpiniones() {
		return em.createNamedQuery("Opinion.todas", Opinion.class).getResultList();
	}

	/**
	 * Añade una opinion
	 * 
	 * @param opi
	 * @return
	 * @throws ASMException
	 */

	public void aniadirOpinion(Opinion opi) throws ASMException {
		if(opi.getNombre().isEmpty() || opi.getOpinion().isEmpty()) {
			throw new ASMException ("Ni la opinión ni el nombre pueden estar vacíos");
		}
		em.persist(em.merge(opi));
	}

	/**
	 * Elimina una opinion
	 * 
	 * @param opi
	 * @return
	 * 
	 */

	@Override
	public void eliminarOpinion(Opinion opi) {
		em.remove(em.merge(opi));
	}

	/**
	 * Añade una ONG
	 * 
	 * @param ong
	 * @return
	 * 
	 */

	@Override
	public void aniadirONG(ONG ong) throws ASMException {
		if (ong == null) {
			throw new ASMException("No se ha detectado ninguna ONG");
		}

		if (ong.getID_ONG().isEmpty())
			throw new ASMException("NIF no puede estar vacío");

		if (ong.getAMBITO().isEmpty())
			throw new ASMException("Ámbito no puede estar vacío");

		if (ong.getEMAIL().isEmpty())
			throw new ASMException("Email no puede estar vacío");

		if (ong.getTELEFONO() == null)
			throw new ASMException("Teléfono no puede estar vacío");

		if (ong.getNOMBRE().isEmpty())
			throw new ASMException("No se ha detectado ninguna ONG");

		em.persist(em.merge(ong));
	}

	/**
	 * Devuelve una lista de ONGs
	 * 
	 * @param
	 * @return List<ONG>
	 * 
	 */

	@Override
	public List<ONG> getONGs() {
		return em.createNamedQuery("ONG.todas", ONG.class).getResultList();
	}

	/**
	 * Elimina una ONG
	 * 
	 * @param ong
	 * @return
	 * 
	 */

	@Override
	public void eliminarONG(ONG ong) {
		List<Actividad> todas = getTodasActividades();
		for (Actividad a : todas) {
			if (ong.getID_ONG().equals(a.getOng().getID_ONG())) {
				eliminarActividad(em.merge(a));
			}
		}

		em.remove(em.merge(ong));
	}

	/**
	 * Devuelve un objeto ONG completo de la base de datos dado su NIF
	 * 
	 * @param ong_elegida
	 * @return ONG
	 * 
	 */

	@Override
	public ONG getFullONG(String ong_elegida) throws ASMException {
		if (ong_elegida == null) {
			throw new ASMException("No se ha seleccionado ONG");
		} else {
			ONG auxi = em.find(ONG.class, ong_elegida);
			if (auxi != null) {
				return auxi;
			} else {
				throw new ASMException("No se ha encontrado ONG");
			}
		}

	}

	/**
	 * Comprueba el login de ONG
	 * 
	 * @param ong
	 * @return
	 * 
	 */

	@Override
	public void compruebaLogin(ONG o) throws ASMException {
		ONG ong = em.find(ONG.class, o.getID_ONG());

		if (ong == null) {
			throw new ASMException("La cuenta de ONG no existe.");
		}

		if (!ong.getCONTRASENIA().equals(o.getCONTRASENIA())) {
			throw new ASMException("Contraseña incorrecta.");
		}
	}

	/**
	 * Devuelve una lista de las actividades que regenta dicha ONG
	 * 
	 * @param nif
	 * @return List<Actividad>
	 * 
	 */

	@Override
	public List<Actividad> getLactONG(String nif) {
		List<Actividad> todas = getTodasActividades();
		List<Actividad> mias = new ArrayList<Actividad>();
		for (Actividad a : todas) {
			if (a.getOng().getID_ONG().equals(nif)) {
				mias.add(a);
			}
		}
		return mias;
	}

	/**
	 * Devuelve una lista con todos los profesores
	 * 
	 * @param
	 * @return List<Pdi>
	 * 
	 */

	@Override
	public List<Pdi> getProfesores() {
		return em.createNamedQuery("Pdi.todas", Pdi.class).getResultList();
	}

	/**
	 * Devuelve una lista con todos los alumnos que están apuntados en la Actividad
	 * act
	 * 
	 * @param act
	 * @return List<Usuario>
	 * 
	 */

	@Override
	public List<Usuario> getListaApuntados(Actividad act) {
		List<EstadoSolicitud> todas = getTodosEstadosSolicitud();
		List<Usuario> apuntados = new ArrayList<Usuario>();
		for (EstadoSolicitud es : todas) {
			if (es.getActividad().getID_ACTIVIDAD().equals(act.getID_ACTIVIDAD())) {
				apuntados.add(es.getUsuario());
			}
		}
		return apuntados;
	}

	/**
	 * Acepta a un Usuario usu en una Actividad act
	 * 
	 * @param es
	 * @return
	 * @throws ASMException
	 * 
	 */

	@Override
	public void aceptarUno(EstadoSolicitud es) throws ASMException {
		EstadoSolicitud actual = em.find(EstadoSolicitud.class, es.getId());
		if (actual == null) {
			throw new ASMException("No se ha encontrado estado solicitud");
		}
		actual.setESTADO(ACTIVO);
		System.out.println("pre: " + actual);
		em.merge(actual);
		System.out.println("post: " + actual);
	}

	/**
	 * 
	 * Rechaza a un Usuario usu en una Actividad act
	 * 
	 * @param es
	 * @return
	 * @throws ASMException
	 * 
	 */

	@Override
	public void rechazarUno(EstadoSolicitud es) throws ASMException {
		EstadoSolicitud actual = em.find(EstadoSolicitud.class, es.getId());
		if (actual == null) {
			throw new ASMException("No se ha encontrado estado solicitud");
		}
		actual.setESTADO(CANCELADO);
		System.out.println("pre: " + actual);
		em.merge(actual);
		System.out.println("post: " + actual);
	}

	/**
	 * 
	 * Acepta a todos los usuarios en una Actividad act
	 * 
	 * @param act
	 * @return
	 * @throws ASMException
	 */

	@Override
	public void aceptarTodos(Actividad act) throws ASMException {
		List<EstadoSolicitud> todos = getTodosEstadosSolicitud();
		for (EstadoSolicitud es : todos) {
			if (es.getActividad().getID_ACTIVIDAD().equals(act.getID_ACTIVIDAD())) {
				aceptarUno(es);
			}
		}
	}

	/**
	 * 
	 * Rechaza a todos los usuarios en una Actividad act
	 * 
	 * @param act
	 * @return
	 * @throws ASMException
	 * 
	 */

	@Override
	public void rechazarTodos(Actividad act) throws ASMException {
		List<EstadoSolicitud> todos = getTodosEstadosSolicitud();
		for (EstadoSolicitud es : todos) {
			if (es.getActividad().getID_ACTIVIDAD().equals(act.getID_ACTIVIDAD())) {
				rechazarUno(es);
			}
		}
	}

	/**
	 * 
	 * Añade una noticia
	 * 
	 * @param noti
	 * @return
	 * @throws ASMException
	 * 
	 */

	@Override
	public void aniadirNoticia(Noticias noti) throws ASMException {
		if (noti == null) {
			throw new ASMException("No se ha detectado ninguna Noticia");
		}

		if (noti.getTitular().isEmpty())
			throw new ASMException("Titular no puede estar vacío");

		if (noti.getCuerpo().isEmpty())
			throw new ASMException("El cuerpo no puede estar vacío");

		em.persist(em.merge(noti));
	}

	/**
	 * 
	 * Devuelve una lista con todas las noticias
	 * 
	 * @return List<Noticias>
	 * @throws ASMException
	 * 
	 */

	@Override
	public List<Noticias> getTodasNoticias() {
		return em.createNamedQuery("Noticias.todas", Noticias.class).getResultList();
	}

	/**
	 * 
	 * Elimina una noticia
	 * 
	 * @param noti
	 * 
	 */

	@Override
	public void eliminarNoticia(Noticias noti) throws ASMException {
		Noticias n = em.find(Noticias.class, noti.getId());
		if (n == null) {
			throw new ASMException("Noticia no encontrada");
		}
		em.remove(em.merge(n));
	}
}
