package ejb;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Local;

import modelo.Actividad;
import modelo.EstadoSolicitud;
import modelo.Noticias;
import modelo.ONG;
import modelo.Opinion;
import modelo.Pas;
import modelo.Pdi;
import modelo.Usuario;

@Local
public interface BaseDeDatosLocal {
	void aniadirActividad(Actividad actividad) throws ASMException;

	void eliminarActividad(Actividad actividad) throws EJBTransactionRolledbackException;

	void inscripcion(Actividad actividad, Usuario usuario);

	void compruebaLogin(Usuario u) throws ASMException;

	void compruebaLogin(ONG o) throws ASMException;

	void aniadirUsuario(Object u) throws ASMException;

	void eliminarUsuario(Usuario usuario);

	List<Actividad> getTodasActividades();

	List<Actividad> getLactUsuario(Long dni);

	List<Usuario> getUsuarios();

	Usuario refrescarUsuario(Usuario u) throws ASMException;

	Pas refrescarPas(Pas p) throws ASMException;

	Pdi refrescarPdi(Pdi p) throws ASMException;

	Usuario usuarioCompleto(Usuario u) throws ASMException;

	List<Opinion> getTodasOpiniones();

	void aniadirOpinion(Opinion opi);

	void eliminarOpinion(Opinion opi);

	void aniadirONG(ONG ong) throws ASMException;

	List<ONG> getONGs();

	void eliminarONG(ONG ong);

	ONG getFullONG(String ong_elegida) throws ASMException;

	ONG refrescarOng(ONG aux) throws ASMException;

	List<Actividad> getLactONG(String nif);

	List<EstadoSolicitud> getTodosEstadosSolicitud();

	List<Pdi> getProfesores();

	List<Usuario> getListaApuntados(Actividad act);

	void aceptarUno(EstadoSolicitud es) throws ASMException;

	void rechazarUno(EstadoSolicitud es) throws ASMException;

	void aceptarTodos(Actividad act) throws ASMException;

	void rechazarTodos(Actividad act) throws ASMException;

	void aniadirNoticia(Noticias noti) throws ASMException;

	List<Noticias> getTodasNoticias();

	void eliminarNoticia(Noticias noti) throws ASMException;
}
