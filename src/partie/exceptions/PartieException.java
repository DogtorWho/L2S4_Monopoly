package partie.exceptions;

/**
 * Exception lancée quand il y'a une erreur dans des fonctions gerant la partie
 */
@SuppressWarnings("serial")
public class PartieException extends Exception {
	public PartieException(String message) {
		super(message);
	}
}
