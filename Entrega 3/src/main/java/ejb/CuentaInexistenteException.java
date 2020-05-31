package ejb;

public class CuentaInexistenteException extends ASMException {

	public CuentaInexistenteException() {
	}

	public CuentaInexistenteException(String msg) {
		super(msg);
	}

}
