package autenticacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.ASMException;
import ejb.BaseDeDatosLocal;
import modelo.Noticias;

@Named(value = "listaNoticias")
@RequestScoped

public class ListaNoticias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ControlAutorizacion ctrl;
	@Inject
	private BaseDeDatosLocal bbdd;

	private String titular;
	private String cuerpo;
	private Noticias noti;

	public ListaNoticias() {
		noti = new Noticias();
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Noticias getNoti() {
		return noti;
	}

	public void setNoti(Noticias noti) {
		this.noti = noti;
	}

	public void aniadirNoticia() {

		try {
			noti = new Noticias(titular, cuerpo);
			bbdd.aniadirNoticia(noti);
			noti = new Noticias();
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}

	}

	public List<Noticias> getDosNoticias() {
		List<Noticias> todas = getTodasNoticias();
		List<Noticias> dos = new ArrayList<Noticias>();
		int n = todas.size() - 1;
		if (n > 1) {
			Random r = new Random();
			int ale = r.nextInt(n);
			dos.add(todas.get(ale));
			todas.remove(ale);
			ale = r.nextInt(n);
			dos.add(todas.get(ale));
		} else {
			dos = todas;
		}

		return dos;
	}

	public List<Noticias> getTodasNoticias() {
		return bbdd.getTodasNoticias();
	}

	public void eliminarNoticia(Noticias noti) {
		try {
			bbdd.eliminarNoticia(noti);
		} catch (ASMException e) {
			FacesMessage fm = new FacesMessage("Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}

}
