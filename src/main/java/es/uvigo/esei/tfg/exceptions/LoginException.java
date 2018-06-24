package es.uvigo.esei.tfg.exceptions;

public class LoginException extends Exception {
	private static final long serialVersionUID = 1L;

	public LoginException(String error) {
		super(error);
	}
}